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

/**
 * Semantic versioning.
 *
 * @author ian
 * @see <a href="http://semver.org/">Semantic Versioning 2.0.0-rc.1</a>
 */
public interface ISemanticVersion extends Comparable<ISemanticVersion> {
  /**
   * Returns the major version number.
   * 
   * @return The major version number.
   */
  int getMajor();

  /**
   * Returns the minor version number.
   * 
   * @return The minor version number.
   */
  int getMinor();

  /**
   * Return the patch number of the semantic version.
   * 
   * @return The patch version number.
   */
  int getPatch();

  /**
   * Returns the parsed pre-release information from the version.
   * 
   * @return A string array containing the dot delimited pre-release components.
   */
  String[] getPreRelease();

  /**
   * Returns the parsed build information from the version.
   *
   * @return A string array containing the dot delimited build components.
   */
  String[] getBuild();
}
