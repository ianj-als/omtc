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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class Negotiator implements INegotiator {
  private final ISemanticVersion apiVersion;
  private final Map<Capability, List<IServiceResourceCapability>> supportedResources;
  private final Set<IFeatureCapability> featureCapabilities;

  public Negotiator(
      final String theApiVersion,
      final Collection<IServiceResourceCapability> theResourceCapabilities,
      final Collection<IFeatureCapability> theFeatureCapabilities)
  throws SemanticVersionParseException {
    super();
    apiVersion = SemanticVersion.parseVersion(theApiVersion);

    featureCapabilities = (theFeatureCapabilities == null) ?
        new HashSet<IFeatureCapability>() :
        new HashSet<IFeatureCapability>(theFeatureCapabilities);

    /*
     * Group resource capabilities by their unique identifier for possibly
     * different version of the resource revision.
     */
    supportedResources = new HashMap<Capability, List<IServiceResourceCapability>>();
    if(theResourceCapabilities != null) {
      for(IServiceResourceCapability resourceCap : theResourceCapabilities) {
        final Capability capabilityName = resourceCap.getName();

        if(supportedResources.containsKey(capabilityName) == true) {
          final List<IServiceResourceCapability> yeah =
              supportedResources.get(capabilityName);
          yeah.add(resourceCap);
        } else {
          final List<IServiceResourceCapability> yeah =
              new ArrayList<IServiceResourceCapability>();
          yeah.add(resourceCap);
          supportedResources.put(capabilityName, yeah);
        }
      }
    }
  }

  public final IClientCapabilityResponse negotiate(
      final IClientCapabilityRequest negotiationRequest) {
    final ISemanticVersion clientAPIVersion =
        negotiationRequest.getVersionCapability().getVersion();
    final boolean isClientAPISupported = isClientAPISupported(clientAPIVersion);
    final Set<IResourceCapability> unsupportedResources =
        new HashSet<IResourceCapability>();

    /*
     * Work out which of the clients resource kinds are supported by the
     * service.
     */
    for(IResourceCapability resourceCap :
      negotiationRequest.getResourceCapabilities()) {
      final Capability capabilityName = resourceCap.getName();

      /*
       * First check if the resource kind is supported.
       */
      if(supportedResources.containsKey(capabilityName) == true) {
        boolean supported = false;
        
        for(IServiceResourceCapability svcResourceCap :
          supportedResources.get(capabilityName)) {
          if(svcResourceCap.compareVersion(resourceCap) == 0) {
            supported = true;
          }
        }
        
        if(supported == false) {
          unsupportedResources.add(resourceCap);
        }
      } else {
        unsupportedResources.add(resourceCap);
      }
    }

    return new IClientCapabilityResponse() {
      public boolean isClientAPISupported() {
        return isClientAPISupported;
      }
      public Set<IResourceCapability> getUnsupportedResourceCapabilities() {
        return unsupportedResources;
      }
      public Set<IFeatureCapability> getFeatureCapabilities() {
        return featureCapabilities;
      }
    };
  }

  private boolean isClientAPISupported(ISemanticVersion version) {
    final ImmutablePair<Integer, SemanticVersionComponent> versionComp =
        SemanticVersionComparator.compare(apiVersion, version);
    boolean isApiSupported = false;

    switch(versionComp.getRight()) {
    case PRE_RELEASE_AND_BUILD_VERSION:
    case PATCH_VERSION:
    case MINOR_VERSION:
      isApiSupported = true;
      break;

    case MAJOR_VERSION:
    case INVALID:
      isApiSupported = (versionComp.getLeft() == 0) ? true : false;
      break;
    }

    return isApiSupported;
  }
}
