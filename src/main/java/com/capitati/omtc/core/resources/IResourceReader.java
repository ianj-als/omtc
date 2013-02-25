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
package com.capitati.omtc.core.resources;

import java.io.IOException;

/**
 * An abstract resource reader. Implementations of this interface should
 * ensure that the underlying resource is open and ready for reading before any
 * calls to {@link IResourceReader.read}. 
 *
 * @author ian
 */
public interface IResourceReader {
  /**
   * Read bytes from the underlying resource into the buffer provided.
   *
   * @param buffer - the buffer to read resource bytes in to.
   * @return The number of bytes read, and written into the buffer.
   * @throws IOException on error reading the underlying resource.
   */
  int read(byte[] buffer) throws IOException;

  /**
   * Close the underlying resource associated with this reader.
   *
   * @throws IOException on encountering an error closing the underlying
   * resource.
   */
  void close() throws IOException;
}
