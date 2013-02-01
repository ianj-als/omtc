package com.capitati.omtc.core.negotiation;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;

public class SemanticVersionTests {
  private static final Collection<ISemanticVersion> versions =
      new ArrayList<ISemanticVersion>() {
    private static final long serialVersionUID = 1L;
    {
      add(new SemanticVersion(1, 0, 0, "alpha", null));
      add(new SemanticVersion(1, 0, 0, "alpha.1", null));
      add(new SemanticVersion(1, 0, 0, "beta.2", null));
      add(new SemanticVersion(1, 0, 0, "beta.11", null));
      add(new SemanticVersion(1, 0, 0, "rc.1", null));
      add(new SemanticVersion(1, 0, 0, "rc.1", "build.1"));
      add(new SemanticVersion(1, 0, 0));
      add(new SemanticVersion(1, 0, 0, null, "0.3.7"));
      add(new SemanticVersion(1, 3, 7, null, "build"));
      add(new SemanticVersion(1, 3, 7, null, "build.2.b8f12d7"));
      add(new SemanticVersion(1, 3, 7, null, "build.11.e0f985a"));
  }};

  @Test
  public void testSemanticVersionParsing()
  throws SemanticVersionParseException {
    for(ISemanticVersion target : versions) {
      final ISemanticVersion version =
        SemanticVersion.parseVersion(target.toString());
      Assert.assertEquals(target, version);
    }
  }
}
