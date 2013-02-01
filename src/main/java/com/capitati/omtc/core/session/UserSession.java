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
import java.util.concurrent.ExecutorService;

import com.capitati.omtc.core.security.IUser;

/**
 * A session that is associated with a user. This session would be used by a
 * service implementation that requires user identity.
 * 
 * @author ianjohnson
 */
public abstract class UserSession extends Session implements IUserSession {
  protected final IUser user;

  /**
   * Construct a user session that does not support downloading or uploading.
   * 
   * @param theIdentifier - the unique identifier for the session.
   * @param theUser - the associated user object.
   */
  protected UserSession(final UUID theIdentifier, final IUser theUser) {
    super(theIdentifier);
    user = theUser;
  }

  /**
   * Construct a user session that supports downloading and uploading.
   * 
   * @param theIdentifier - the unique identifier for the session.
   * @param theUser - the associated user object.
   * @param theDelegateExecutorService - the executor service in which the
   * upload and download delegates shall run.
   */
  protected UserSession(
      final UUID theIdentifier,
      final IUser theUser,
      final ExecutorService theDelegationExecutorService) {
    super(theIdentifier, theDelegationExecutorService);
    user = theUser;
  }

  /**
   * 
   * @param theIdentifier - the unique identifier for the session.
   * @param theUser - the associated user object.
   * @param theDelegationExecutorService -the executor service in which the
   * upload and download delegates shall run.
   * @param theTransferBufferSize - the number of bytes available to the
   * upload and download transfer buffer.
   */
  protected UserSession(
      final UUID theIdentifier,
      final IUser theUser,
      final ExecutorService theDelegationExecutorService,
      final int theTransferBufferSize) {
    super(theIdentifier, theDelegationExecutorService, theTransferBufferSize);
    user = theUser;
  }

  /**
   * Retrieve the associated user object for the session.
   * 
   * @return The session's associated user object.
   */
  public IUser getUser() {
    return user;
  }
}
