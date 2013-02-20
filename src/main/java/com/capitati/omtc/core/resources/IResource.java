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

import java.net.URI;
import java.util.Date;
import java.util.UUID;

/**
 * An abstract and immutable resource. This is anything in OMTC that is used by
 * a user in an MT system, e.g. documents, translation memories, glossaries etc.
 *
 * @author ian
 */
public interface IResource {
  /**
   * Retrieve the unique identifier for the resource.
   *
   * @return A resource's unqiue identifier as a {@link java.util.UUID} object.
   */
  UUID getIdentifier();

  /**
   * Retrieve the location of the resource. The scheme of the URI shall
   * determine how to access the resource; this is implementation defined.
   *
   * @return A resource's location as a {@link java.net.URI} object.
   */
  URI getURI();

  /**
   * Retrieve the creation date of the resource.
   *
   * @return A resource's creation date as a {@link java.util.Date} object.
   */
  Date getBirthDate();
}
