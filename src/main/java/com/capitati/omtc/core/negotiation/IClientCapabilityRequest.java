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

import java.util.Set;


/**
 * A description of the client's capabilities.
 *
 * @author ian
 */
public interface IClientCapabilityRequest {
  /**
   * Implementers shall return the client's supported API version capability.
   *
   * @return A {@link com.capitati.omtc.core.negotiation.IAPICapability}
   * objects which represent the client's supported API version.
   */
  IAPICapability getVersionCapability();

  /**
   * Implementers shall return a set of the
   * {@link com.capitati.omtc.core.negotiation.IResourceCapability} objects
   * which shall represent the resource kinds which it expects the service
   * to support.
   *
   * @return A set of {@link com.capitati.omtc.core.negotiation.IResourceCapability}
   * objects.
   */
  Set<IResourceCapability> getResourceCapabilities();
}
