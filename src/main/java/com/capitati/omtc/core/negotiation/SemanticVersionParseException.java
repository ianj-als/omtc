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
package com.capitati.omtc.core.negotiation;

public class SemanticVersionParseException extends Exception {
  private static final long serialVersionUID = -3118190471319312408L;

  private final String invalidVersion;
  
  public SemanticVersionParseException(final String theInvalidVersion) {
    super();
    invalidVersion = theInvalidVersion;
  }

  public SemanticVersionParseException(
      final String theInvalidVersion, final Throwable theThrowable) {
    super(theThrowable);
    invalidVersion = theInvalidVersion;
  }

  public SemanticVersionParseException(
      final String theInvalidVersion,
      final Throwable theThrowable,
      final boolean isSuppressionEnabled,
      final boolean isWritableStackTrace) {
    super(null, theThrowable, isSuppressionEnabled, isWritableStackTrace);
    invalidVersion = theInvalidVersion;
  }

  public String getInvalidVersion() {
    return invalidVersion;
  }

  public String getMessage() {
    return String.format("Invalid semantic version: [%s]", invalidVersion);
  }
}
