package com.capitati.omtc.core.session;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

import com.capitati.omtc.core.resources.IPrimaryResource;

public class PrimaryResource implements IPrimaryResource {
  final UUID resourceId;
  final URI resourceUri;
  final Date resourceBirthDate;

  public PrimaryResource(
      final UUID resourceId,
      final URI resourceUri,
      final Date resourceBirthDate) {
    super();
    this.resourceId = resourceId;
    this.resourceUri = resourceUri;
    this.resourceBirthDate = resourceBirthDate;
  }

  public UUID getIdentifier() {
    return resourceId;
  }

  public URI getURI() {
    return resourceUri;
  }

  public Date getBirthDate() {
    return resourceBirthDate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((resourceBirthDate == null) ? 0 : resourceBirthDate.hashCode());
    result = prime * result
        + ((resourceId == null) ? 0 : resourceId.hashCode());
    result = prime * result
        + ((resourceUri == null) ? 0 : resourceUri.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj)
      return true;
    if(obj == null)
      return false;
    if(getClass() != obj.getClass())
      return false;
    PrimaryResource other = (PrimaryResource) obj;
    if(resourceBirthDate == null) {
      if(other.resourceBirthDate != null)
        return false;
    } else if(!resourceBirthDate.equals(other.resourceBirthDate))
      return false;
    if(resourceId == null) {
      if(other.resourceId != null)
        return false;
    } else if(!resourceId.equals(other.resourceId))
      return false;
    if(resourceUri == null) {
      if(other.resourceUri != null)
        return false;
    } else if(!resourceUri.equals(other.resourceUri))
      return false;
    return true;
  }
}
