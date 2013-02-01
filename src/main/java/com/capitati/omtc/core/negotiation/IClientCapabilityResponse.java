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
 * The response to a client negotiation.
 *
 * @author ian
 */
public interface IClientCapabilityResponse {
  /**
   * Does the service support the client's API revision. If this method returns
   * false then the service should send the response back to the client and
   * disconnect the session.
   *
   * @return True if the client's API is supported. Otherwise, returns false.
   */
  boolean isClientAPISupported();

  /**
   * The collection of data exchange formats that the service does not support
   * but the client does. If the set of service supported data exchange formats
   * is <em>S</em>, and the client supported data exchange formats is <em>C</em>
   * then the returned collection shall be <em>C - S</em>. This is based, firstly,
   * on the capability, and then the resource capability's version range.
   * <p>
   * When the client receives this set it needs to make a decision whether to
   * continue with its session. For example, if all of the client's supported
   * exchange formats are returned here then it is pointless continuing with
   * the session. The service does not "talk" any of client's supported formats.
   *
   * @return A set of
   * {@link com.capitati.omtc.core.negotiation.IResourceCapability}
   * objects that the client supports but the service does not.
   */
  Set<IResourceCapability> getUnsupportedResourceCapabilities();

  /**
   * The supported feature capabilities of the service.
   *
   * @return A set of {@link com.capitati.omtc.core.negotiation.IFeatureCapability}
   * objects.
   */
  Set<IFeatureCapability> getFeatureCapabilities();
}
