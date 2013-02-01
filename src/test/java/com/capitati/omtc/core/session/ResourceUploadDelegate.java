package com.capitati.omtc.core.session;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;

import com.capitati.omtc.core.resources.IResource;
import com.capitati.omtc.core.resources.IResourceReader;
import com.capitati.omtc.core.resources.IResourceWriter;

public class ResourceUploadDelegate implements IResourceTransferDelegate {
  private final IResource resource;
  private final String source;
  private byte[] destination;

  public ResourceUploadDelegate(
      final String theSource,
      final UUID theResourceId,
      final URI theResourceUri,
      final Date theResourceBirthDate) {
    source = theSource;
    resource = new PrimaryResource(
        theResourceId, theResourceUri, theResourceBirthDate);
  }

  public IResourceReader getResourceReader(final ISession theInvokingSession)
  throws Exception {
    return new IResourceReader() {
      private final byte[] readerSource = source.getBytes();
      private int noBytesRead = 0;

      public int read(final byte[] buffer) throws IOException {
        if(noBytesRead >= readerSource.length) {
          return -1;
        }

        int bytesRead = 0;
        for(int idx = 0;
            (idx < buffer.length) && ((noBytesRead + bytesRead) < readerSource.length);
            idx++) {
          buffer[idx] = readerSource[noBytesRead + idx];
          bytesRead += 1;
        }
        
        noBytesRead += bytesRead;

        return bytesRead;
      }

      public void close() throws IOException {
      }
    };
  }

  public IResourceWriter getResourceWriter(final ISession theInvokingSession)
  throws Exception {
    return new IResourceWriter() {
      public void write(byte[] chunk, int chunkLength) throws IOException {
        destination = ArrayUtils.addAll(
            destination,
            ArrayUtils.subarray(chunk, 0, chunkLength));
      }

      public void close() throws IOException {
      }
    };
  }

  public IResource getResource(final ISession theInvokingSession) {
    return resource;
  }

  public byte[] getDestination() {
    return destination;
  }
}
