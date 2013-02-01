package com.capitati.omtc.core.session;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capitati.omtc.core.notification.IResourceUploadObserver;
import com.capitati.omtc.core.resources.IResource;
import com.capitati.omtc.core.resources.IResourceReader;
import com.capitati.omtc.core.resources.IResourceWriter;
import com.google.common.base.Predicate;

public class SessionTests {
  class TestSession extends Session {
    protected TestSession(
        final UUID theIdentifier,
        final AbstractExecutorService theDelegationExecutorService,
        final int bufferSize) {
      super(theIdentifier, theDelegationExecutorService, bufferSize);
    }

    public Set<IResource> retrieveResources(final Predicate<IResource> theFilter)
    throws Exception {
      return null;
    }

    public void removeResource(final IResource resource) throws Exception {
    }
  }

  private static final String MESSAGE =
      "Whose was it? His who is gone. " +
      "Who shall have it? He who will come. " +
      "Where was the sun? Over the oak. " +
      "Where was the shadow? Under the elm. " +
      "How was it stepped? North by ten and by ten, east by five and by five, " +
      "south by two and by two, west by one and by one, and so under. " +
      "What shall we give for it? All that is ours. " +
      "Why should we give it? For the sake of the trust.";

  private final UUID resourceId = UUID.randomUUID();
  private final Date resourceBirthDate = new Date();

  private URI resourceUri;
  private Mockery mockery;
  private AbstractExecutorService executor;
  private Session session;
  
  @Before
  public void setUp() throws URISyntaxException {
    mockery = new JUnit4Mockery();
    resourceUri = new URI("test://some_resource_or_other");
    executor =  new ThreadPoolExecutor(
        1,
        1,
        0,
        TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1));
    session = new TestSession(UUID.randomUUID(), executor, MESSAGE.length() / 4);
  }

  @After
  public void tearDown() {
    executor.shutdown();
  }
  
  @Test
  public void testSuccessfulUpload() throws Exception {
    final IResourceTransferDelegate delegate = new ResourceUploadDelegate(
        MESSAGE,
        resourceId,
        resourceUri,
        resourceBirthDate);
    final IResourceUploadObserver observer =
        mockery.mock(IResourceUploadObserver.class, "successUploadObserver");
    final IResource targetResource = delegate.getResource(session);
    
    mockery.checking(new Expectations() {{
      oneOf(observer).onSuccess(
          with(equal(session)),
          with(equal(targetResource)));
    }});

    final Future<IResource> futureResource = session.uploadResource(delegate, observer);
    final IResource resource = futureResource.get();

    Assert.assertSame("Wrong resource", targetResource, resource);
  }

  @Test(expected = SessionTestException.class)
  public void testReaderReadFailureOnUpload() throws Throwable {
    final Exception targetException = new SessionTestException();
    final IResourceReader resourceReader =
        mockery.mock(IResourceReader.class, "resourceReader");
    final IResourceWriter resourceWriter =
        mockery.mock(IResourceWriter.class, "resourceWriter");
    final IResourceTransferDelegate delegate =
        mockery.mock(IResourceTransferDelegate.class, "badUploadDelegate");
    final IResourceUploadObserver observer =
        mockery.mock(IResourceUploadObserver.class, "badUploadObserver");
    final IResource resource =
        mockery.mock(IResource.class, "badResource");

    mockery.checking(new Expectations() {{
      allowing(delegate).getResource(with(equal(session)));
      will(returnValue(resource));

      oneOf(resourceReader).read(with(any(byte[].class)));
      will(throwException(targetException));

      oneOf(resourceReader).close();

      oneOf(resourceWriter).close();

      oneOf(delegate).getResourceReader(with(equal(session)));
      will(returnValue(resourceReader));

      oneOf(delegate).getResourceWriter(with(equal(session)));
      will(returnValue(resourceWriter));
      
      oneOf(observer).onError(
          with(equal(session)),
          with(equal(resource)),
          with(equal(targetException)));
    }});

    final Future<IResource> futureResource =
        session.uploadResource(delegate, observer);
    
    try {
      @SuppressWarnings("unused")
      final IResource future = futureResource.get();
    } catch(final ExecutionException ex) {
      throw ex.getCause();
    }
  }

  @Test(expected = SessionTestException.class)
  public void testWriterWriteFailureOnUpload() throws Throwable {
    final Exception targetException = new SessionTestException();
    final IResourceReader resourceReader =
        mockery.mock(IResourceReader.class, "resourceReader");
    final IResourceWriter resourceWriter =
        mockery.mock(IResourceWriter.class, "resourceWriter");
    final IResourceTransferDelegate delegate =
        mockery.mock(IResourceTransferDelegate.class, "badUploadDelegate");
    final IResourceUploadObserver observer =
        mockery.mock(IResourceUploadObserver.class, "badUploadObserver");
    final IResource resource =
        mockery.mock(IResource.class, "badResource");

    mockery.checking(new Expectations() {{
      allowing(delegate).getResource(with(equal(session)));
      will(returnValue(resource));

      oneOf(resourceReader).read(with(any(byte[].class)));
      will(returnValue(7));

      oneOf(resourceReader).close();

      oneOf(resourceWriter).write(with(any(byte[].class)), with(any(int.class)));
      will(throwException(targetException));

      oneOf(resourceWriter).close();

      oneOf(delegate).getResourceReader(with(equal(session)));
      will(returnValue(resourceReader));

      oneOf(delegate).getResourceWriter(with(equal(session)));
      will(returnValue(resourceWriter));

      oneOf(observer).onError(
          with(equal(session)),
          with(equal(resource)),
          with(equal(targetException)));
    }});

    final Future<IResource> futureResource =
        session.uploadResource(delegate, observer);
    
    try {
      @SuppressWarnings("unused")
      final IResource future = futureResource.get();
    } catch(final ExecutionException ex) {
      throw ex.getCause();
    }
  }
}
