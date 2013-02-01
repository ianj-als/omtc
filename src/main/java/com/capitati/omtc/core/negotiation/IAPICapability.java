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
 * The semantic version capability of the API.
 *
 * @author ian
 */
public interface IAPICapability extends ICapability {
  /**
   * Retrieve the semantic version of the API.
   * 
   * @return An instance of {@link com.capitati.omtc.core.negotiation.ISemanticVersion}
   * which represents the semantic version of the API.
   */
  ISemanticVersion getVersion();
}
