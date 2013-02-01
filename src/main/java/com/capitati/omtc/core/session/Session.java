/*
 * Copyright Capita Translation and Interpreting 2013
 *
 * This file is part of OMTC.
 *
 * OMTC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OMTC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with OMTC.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.capitati.omtc.core.session;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.naming.OperationNotSupportedException;

import com.capitati.omtc.core.notification.IResourceDownloadObserver;
import com.capitati.omtc.core.notification.IResourceUploadObserver;
import com.capitati.omtc.core.resources.IResource;
import com.capitati.omtc.core.resources.IResourceReader;
import com.capitati.omtc.core.resources.IResourceWriter;

/**
 * A machine translation service session. This session is <em>user-less</em>;
 * this session is for services where user identity is not crucial for using the
 * service.
 * 
 * @author ianjohnson
 */
public abstract class Session implements ISession {
  private static final int DEFAULT_BUFFER_SIZE = 1024 * 1024; // 1Mb

  private final UUID id;
  private final int bufferSize;
  protected final ExecutorService executor;

  /**
   * Construct a session that does not support uploading or downloading of
   * resources.
   * 
   * @param theIdentifier - the unique identifier for the session.
   */
  protected Session(final UUID theIdentifier) {
    super();
    id = theIdentifier;
    bufferSize = DEFAULT_BUFFER_SIZE;
    executor = null;
  }

  /**
   * Construct a session that supports uploading and downloading of resources.
   * 
   * @param theIdentifier - the unique identifier for the session.
   * @param theDelegateExecutorService - the executor service in which the
   * upload and download delegates shall run.
   */
  protected Session(
      final UUID theIdentifier,      
      final ExecutorService theDelegationExecutorService) {
    super();
    id = theIdentifier;
    executor = theDelegationExecutorService;
    bufferSize = (executor != null) ? DEFAULT_BUFFER_SIZE : -1;
  }

  /**
   * Construct a session that supports uploading and downloading of resources.
   * 
   * @param theIdentifier - the unique identifier for the session.
   * @param theDelegateExecutorService - the executor service in which the
   * upload and download delegates shall run.
   * @param theTransferBufferSize - the number of bytes available to the
   * upload and download transfer buffer.
   */
  protected Session(
      final UUID theIdentifier,      
      final ExecutorService theDelegationExecutorService,
      final int theTransferBufferSize) {
    super();
    id = theIdentifier;
    bufferSize = (theTransferBufferSize > 0) ? theTransferBufferSize : DEFAULT_BUFFER_SIZE;
    executor = theDelegationExecutorService;
  }

  public UUID getIdentifier() {
    return id;
  }

  public Future<IResource> uploadResource(
      final IResourceTransferDelegate theResourceUploaderDelegate,
      final IResourceUploadObserver theResourceUploadObserver)
  throws OperationNotSupportedException {
    if(executor == null) {
      throw new OperationNotSupportedException("");
    }

    if(theResourceUploaderDelegate == null) {
      throw new IllegalArgumentException("No uploader delegate specified.");
    }

    final ISession invokingSession = this;
    final Callable<IResource> uploadTask = new Callable<IResource>() {
      public IResource call() throws Exception {
        final IResource resource =
            theResourceUploaderDelegate.getResource(invokingSession);

        try {
          upload();

          if(theResourceUploadObserver != null) {
            theResourceUploadObserver.onSuccess(invokingSession, resource);
          }
        } catch(final Exception ex) {
          if(theResourceUploadObserver != null) {
            theResourceUploadObserver.onError(invokingSession, resource, ex);
          }
          throw ex;
        }
        
        return resource;
      }
      
      private void upload() throws Exception {
        final IResourceReader resourceReader =
            theResourceUploaderDelegate.getResourceReader(invokingSession);

        try {
          final IResourceWriter resourceWriter =
              theResourceUploaderDelegate.getResourceWriter(invokingSession);
      
          try {
            Session.copyBytes(resourceReader, resourceWriter, bufferSize);
          } finally {
            resourceWriter.close();
          }
        } finally {
          resourceReader.close();
        }
      }
    };
    final Future<IResource> future = executor.submit(uploadTask);

    return future;
  }

  public Future<IResource> downloadResource(
      final IResourceTransferDelegate theResourceDownloaderDelegate,
      final IResourceDownloadObserver theResourceDownloadObserver) {
    if(theResourceDownloaderDelegate == null) {
      throw new IllegalArgumentException("No downloader delegate specified.");
    }

    final ISession invokingSession = this;
    final Callable<IResource> downloadTask = new Callable<IResource>() {
      public IResource call() throws Exception {
        final IResource resource =
            theResourceDownloaderDelegate.getResource(invokingSession);

        try {
          download();

          if(theResourceDownloadObserver != null) {
            theResourceDownloadObserver.onSuccess(invokingSession, resource);
          }
        } catch(final Exception ex) {
          if(theResourceDownloadObserver != null) {
            theResourceDownloadObserver.onError(invokingSession, resource, ex);
            throw ex;
          }
        }

        return resource;
      }
      
      private void download() throws Exception {
        final IResourceWriter resourceWriter =
            theResourceDownloaderDelegate.getResourceWriter(invokingSession);

        try {
          final IResourceReader resourceReader =
              theResourceDownloaderDelegate.getResourceReader(invokingSession);

          try {
            Session.copyBytes(resourceReader, resourceWriter, bufferSize);
          } finally {
            resourceReader.close();
          }
        } finally {
          resourceWriter.close();
        }
      }
    };
    final Future<IResource> future = executor.submit(downloadTask);
    
    return future;
  }

  /**
   * Writes bytes, read from a resource reader, to a resource writer. This
   * copies the bytes from a source resource to a destination resource,
   * respectively.
   * 
   * @param resourceReader - the object that shall read the source resource data.
   * @param resourceWriter - the object that shall write the resource data to
   * the destination resource.
   * @param bufferSize - the number of bytes used to build the buffer.
   * @throws Exception on an error reading from, or writing to the resources.
   */
  private static void copyBytes(
      final IResourceReader resourceReader,
      final IResourceWriter resourceWriter,
      final int bufferSize) throws Exception {
    final byte[] readBuffer = new byte[bufferSize];
    int bytesRead = resourceReader.read(readBuffer);

    while(bytesRead > -1) {
      resourceWriter.write(readBuffer, bytesRead);
      bytesRead = resourceReader.read(readBuffer);
    }
  }
}
