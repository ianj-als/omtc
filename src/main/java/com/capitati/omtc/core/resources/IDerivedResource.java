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

import java.util.Set;

/**
 * This is a representation of a resource that is composed of other resources.
 * A translation engine is a concrete example of this interface.
 *
 * @author ian
 */
public interface IDerivedResource extends IResource {
  /**
   * Retrieve the resources that have been composed to create a derived resource.
   *
   * @return A {@link java.util.Set} whose entries are the composed resources.
   */
  Set<IResource> getCreationResources();
}
