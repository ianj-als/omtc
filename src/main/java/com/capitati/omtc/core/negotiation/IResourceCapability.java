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
 * Implementations of this interface shall specify the version range
 * of a specific resource kind, e.g., XLIFF.
 *
 * @author ian
 */
public interface IResourceCapability extends ICapability {
  /**
   * The lowest version of the resource that is supported.
   * 
   * @return A string that represents the resource kind's lower supported
   * version.
   */
  String getLowerVersion();

  /**
   * The highest version of the resource that is supported.
   * 
   * @return A string that represents the resource kind's highest supported
   * version.
   */
  String getHigherVersion();
}
