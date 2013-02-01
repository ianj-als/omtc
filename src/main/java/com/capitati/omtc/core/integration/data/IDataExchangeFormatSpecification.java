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
package com.capitati.omtc.core.integration.data;

import com.capitati.omtc.core.negotiation.IVersion;

/**
 * A description of a data exchange format, specification or standard.
 * Implementers of this interface shall decide on a unique identifier for
 * the data exchange format.
 *
 * @author ian
 */
public interface IDataExchangeFormatSpecification
extends Comparable<IDataExchangeFormatSpecification> {
  /**
   * The short name, probably and acronym, for the format, specification, or
   * standard.
   *
   * @return The short name of the data exchange format.
   */
  String getShortName();

  /**
   * The long name for the format, specification standard.
   *
   * @return The long name of the data exchange format.
   */
  String getLongName();

  /**
   * The lowest version of the data exchange format at which the client supports.
   *
   * @return The lowest supported version.
   */
  IVersion getLowestVersion();

  /**
   * The highest version of the data exchange format the client supports.
   *
   * @return The highest supported version.
   */
  IVersion getHighestVersion();
}
