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
 * Abstract resource writer. Implementations of this interface should ensure
 * that the underlying resource is open and ready for writing before any calls
 * to {@link IResourceWriter.write} are made.
 *
 * @author ian
 */
public interface IResourceWriter {
  /**
   * Write the provided chunk of data to the underlying resource.
   *
   * @param chunk - the byte data to write.
   * @param chunkLength - the number of valid bytes in the buffer.
   * @throws IOException - on error writing bytes from buffer to the
   * underlying resource.
   */
  void write(byte[] chunk, int chunkLength) throws IOException;

  /**
   * Close any underlying resource.
   *
   * @throws IOException - on error closing the underlying resource.
   */
  void close() throws IOException;
}
