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
package com.capitati.omtc.core.security;

import java.util.UUID;

/**
 * A machine translation service user.
 * 
 * @author ian
 */
public interface IUser {
  /**
   * The unique identifier for the user.
   * 
   * @return The user's unique identity.
   */
  UUID getIdentifier();

  /**
   * Retrieve the user's roles.
   * 
   * @return The roles of the user.
   */
  Role[] getRoles();
}
