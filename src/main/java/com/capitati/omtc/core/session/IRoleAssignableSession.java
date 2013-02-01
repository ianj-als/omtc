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
import com.capitati.omtc.core.security.Role;

/**
 * A mixin for providing a session the ability to grant and revoke roles from
 * users of the machine translation service. Implementers of this interface
 * should integrate with any back-end authorisation service that may be used
 * alongside the concrete service.
 * 
 * @author ianjohnson
 */
public interface IRoleAssignableSession extends IUserRetrievableSession {
  /**
   * Grant a role to a user.
   * 
   * @param theRole - the role to grant.
   * @param theUser - the user.
   * @throws Exception On any errors that occur during the granting process.
   */
  void grantRoleToUser(Role theRole, IUser theUser) throws Exception;

  /**
   * Revoke a role from a user.
   * 
   * @param theRole - the role to revoke from the user.
   * @param theUser - the user.
   * @throws Exception On any errors that occur during the revocation process.
   */
  void revokeRoleFromUser(Role theRole, IUser theUser) throws Exception;
}
