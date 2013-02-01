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

public enum Capability {
  INVALID(CapabilityGroup.INVALID),
  
  API_VERSION(CapabilityGroup.API),
  
  RES_FILE_TMX(CapabilityGroup.RESOURCE),
  RES_FILE_TBX(CapabilityGroup.RESOURCE),
  RES_FILE_UTX(CapabilityGroup.RESOURCE),
  RES_FILE_SRX(CapabilityGroup.RESOURCE),
  RES_FILE_ITS(CapabilityGroup.RESOURCE),
  RES_FILE_XLIFF(CapabilityGroup.RESOURCE),
  RES_FILE_TTX(CapabilityGroup.RESOURCE),
  
  FET_RES_UPLOAD(CapabilityGroup.FEATURE),
  FET_RES_DOWNLOAD(CapabilityGroup.FEATURE),
  
  PRE_REQ_PAYMENT(CapabilityGroup.PREREQUISITE);

  private CapabilityGroup group;

  private Capability(final CapabilityGroup theGroup) {
    group = theGroup;
  }

  public CapabilityGroup getGroup() {
    return group;
  }
}
