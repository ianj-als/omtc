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

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;

import javax.naming.OperationNotSupportedException;

import com.capitati.omtc.core.notification.IResourceDownloadObserver;
import com.capitati.omtc.core.notification.IResourceUploadObserver;
import com.capitati.omtc.core.resources.IResource;
import com.google.common.base.Predicate;

/**
 * A machine translation service session. This session is <em>user-less</em>;
 * this session is for services where user identity is not crucial for using the
 * service.
 * 
 * @author ian
 */
public interface ISession {
  /**
   * Get the session's unique identifier.
   * 
   * @return The unique identifier that is of type {@link java.util.UUID}.
   */
  UUID getIdentifier();

  /**
   * Upload a resource to the service. The upload delegate shall be called to
   * implement the actual mechanism that shall transfer resource data. If
   * specified the {@link com.capitati.omtc.core.notification.IResourceUploadObserver}
   * object shall be notified when the uploading of the resource has completed:
   * either on success or failure.
   * 
   * <p>The returning {@link java.util.concurrent.Future} object,
   * and the optional observer, allows callers to wait synchronously or
   * asynchronously for upload completion.
   * 
   * @param theResourceUploaderDelegate - the delegated uploader mechanism.
   * @param theResourceUploadObserver - optional observer that is notified
   * once the uploading task has completed, either successfully or unsuccessfully.
   * @return A {@link java.util.concurrent.Future} object that waits on the
   * availability of the {@link com.capitati.omtc.core.resources.IResource} object
   * that represents the resource once uploaded.
   * @throws OperationNotSupportedException If no executor service was specified.
   * @throws IllegalArgumentException If no uploading delegate has been
   * provided.
   */
  Future<IResource> uploadResource(
      IResourceTransferDelegate theResourceUploaderDelegate,
      IResourceUploadObserver theResourceUploadObserver)
  throws OperationNotSupportedException;

  /**
   * Download a resource from the service. The download delegate shall be called to
   * implement the actual mechanism that shall transfer resource data. If
   * specified the {@link com.capitati.omtc.core.notification.IResourceDownloadObserver}
   * object shall be notified when the downloading of the resource has completed:
   * either on success or failure.
   * 
   * <p>The returning {@link java.util.concurrent.Future} object,
   * and the optional observer, allows callers to wait synchronously or
   * asynchronously for the downloading to complete.
   * 
   * @param theResourceDownloaderDelegate - the delegated downloading mechanism.
   * @param theResourceDownloadObserver - optional observer that is notified
   * once the downloading task has completed, either successfully or unsuccessfully.
   * @return A {@link java.util.concurrent.Future} object that waits on the
   * availability of the {@link com.capitati.omtc.core.resources.IResource} object
   * that represents the resource once downloaded.
   * @throws OperationNotSupportedException If no executor service was specified.
   * @throws IllegalArgumentException If no uploading delegate had been
   * provided.
   */
  Future<IResource> downloadResource(
      IResourceTransferDelegate theResourceDownloaderDelegate,
      IResourceDownloadObserver theResourceDownloadObserver)
  throws OperationNotSupportedException;

  /**
   * Remove a resource. If the resource implements
   * {@link com.capitati.omtc.core.commons.IDisposable} the the method
   * {@link com.capitati.omtc.core.commons.IDisposable#dispose} should be
   * called to dispose of any used system resources, e.g., disk space.
   *
   * @param resource - the resource to remove.
   * @throws Exception On an error.
   */
  void removeResource(IResource resource) throws Exception;

  /**
   * This method shall return a set of resources that belong to the session.
   * 
   * @param theFilter - the predicate that shall filter the resources returned.
   * @return A {@link java.util.Set} of {@link com.capitati.omtc.core.resource.IResource}
   * objects that are owned by the session and meet the predicate.
   * @throws Exception on error evaluating the predicate.
   */
  Set<IResource> retrieveResources(Predicate<IResource> theFilter)
  throws Exception;
}