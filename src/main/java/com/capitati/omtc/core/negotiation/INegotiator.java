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
 * The negotiator that determines whether a client's capabilities are
 * matched with the server's.
 *
 * @author ian
 *
 */
public interface INegotiator {
  /**
   * Negotiate a client's capabilities. This method accepts a negotiation request
   * and constructs a response.
   * 
   * @param negotiationRequest - the client's negotiation request.
   * @return A {@link com.capitati.omtc.core.negotiation.IClientCapabilityResponse}
   * object that holds the server's response to the client request.
   */
  IClientCapabilityResponse negotiate(IClientCapabilityRequest negotiationRequest);
}
