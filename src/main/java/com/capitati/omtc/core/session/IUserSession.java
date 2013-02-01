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

import com.capitati.omtc.core.security.IUser;

/**
 * A session that is associated with a user. This session would be used by a
 * service implementation that requires user identity.
 * 
 * @author ian
 */
public interface IUserSession extends ISession {
  /**
   * Retrieve the associated user object for the session.
   * 
   * @return The session's associated user object.
   */
  IUser getUser();
}
