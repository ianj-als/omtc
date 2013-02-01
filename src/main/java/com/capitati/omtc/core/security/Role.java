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

public enum Role {
  INVALID(-1, "Invalid"),
  TRANSLATOR(1, "Translator"),
  ENGINE_MANAGER(3, "Engine Manager"),
  ADMINISTRATOR(7, "Administrator"),
  SERVICE_ADMINISTRATOR(8, "Service Administrator");

  private final int code;
  private final String name; 

  private Role(final int theCode, final String theName) {
    code = theCode;
    name = theName;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }
}
