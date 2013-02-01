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

import com.capitati.omtc.core.engine.IEngine;
import com.capitati.omtc.core.security.IUser;

/**
 * A mixin to provide methods to grant and revoke users access to constructed
 * engines in the machine translation service. Implementers of this interface
 * should associate an engine. And optionally storing the record of a grant or
 * revocation into some persistent backing store. Whether the backing store is
 * a database or authorisation service is implementation defined.
 *
 * @author ianjohnson
 */
public interface IEngineAssignableSession
extends IEngineRetrievableSession, IUserRetrievableSession {
  /**
   * Associate a user with an engine. This association should allow the user
   * to use the engine for a {@link com.capitati.omtc.core.translation.Translator}
   * object. Optionally, the implementation should back this association to some
   * persistent backing store.
   * 
   * @param engine - the engine to grant.
   * @param theUser - the grantee.
   * @throws Exception Any error that is encountered.
   */
  void grantEngineToUser(IEngine engine, IUser theUser) throws Exception;

  /**
   * Dissociate a user from an engine. The user shall no longer be able to use
   * an engine to build a {@link com.capitati.omtc.core.translation.Translator}
   * object. Whether the existing translator objects, that use the engine,
   * shall be destroyed immediately, or that the engine is no longer available
   * for use in a translator is to implementation defined. Optionally, the
   * implementation should back the dissociation to a persistent backing store.
   * 
   * @param engine - the engine to revoke.
   * @param theUser - the revokee.
   * @throws Exception Any error that is encountered.
   */
  void revokeEngineFromUser(IEngine engine, IUser theUser) throws Exception;
}
