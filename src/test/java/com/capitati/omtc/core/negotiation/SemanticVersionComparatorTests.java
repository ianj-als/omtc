package com.capitati.omtc.core.negotiation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Assert;
import org.junit.Test;

public class SemanticVersionComparatorTests {
  private interface IVersionsWithChecker {
    ISemanticVersion getLeftVersion();
    ISemanticVersion getRightVersion();
    boolean forwardCheck(int comparisonValue);
    boolean backwardCheck(int comparisonValue);
    SemanticVersionComponent getChangedComponent();
  }

  private static final List<ISemanticVersion> orderedVersions =
      new ArrayList<ISemanticVersion>() {
    private static final long serialVersionUID = 1L;
    {
      add(new SemanticVersion(1, 0, 0, "alpha", null));
      add(new SemanticVersion(1, 0, 0, "alpha.1", null));
      add(new SemanticVersion(1, 0, 0, "beta.2", null));
      add(new SemanticVersion(1, 0, 0, "beta.11", null));
      add(new SemanticVersion(1, 0, 0, "rc.1", null));
      add(new SemanticVersion(1, 0, 0, "rc.1", "build.1"));
      add(new SemanticVersion(1, 0, 0, "rc.2", null));
      add(new SemanticVersion(1, 0, 0));
      add(new SemanticVersion(1, 0, 1));
      add(new SemanticVersion(1, 1, 2));
      add(new SemanticVersion(2, 0, 0));
      add(new SemanticVersion(2, 0, 0, null, "0.3.7"));
      add(new SemanticVersion(2, 3, 7, null, "build"));
      add(new SemanticVersion(2, 3, 7, null, "build.2.b8f12d7"));
      add(new SemanticVersion(2, 3, 7, null, "build.11.e0f985a"));
  }};

  @Test
  public void testComparatorWithLexicalOrdering() {
    for(int idx = 0; idx < orderedVersions.size() - 1; idx++) {
      final ISemanticVersion one = orderedVersions.get(idx);
      final ISemanticVersion two = orderedVersions.get(idx + 1);
      
      System.err.printf(
          "Version [%s] and [%s]...%s",
          one,
          two,
          System.lineSeparator());
      
      Assert.assertTrue(
          String.format(
              "Semantic version [%s] is greater than [%s]",
              one, two),
          SemanticVersionComparator.compare(one, two).getLeft() < 0);
      Assert.assertTrue(
          String.format(
              "Semantic version [%s] is less than [%s]",
              two, one),
          SemanticVersionComparator.compare(two, one).getLeft() > 0);
      Assert.assertTrue(
          String.format(
              "Semantic version [%s] is not equal to itself!", one),
          SemanticVersionComparator.compare(one, one).getLeft() == 0);
      Assert.assertTrue(
          String.format(
              "Semantic version [%s] is not equal to itself!", two),
          SemanticVersionComparator.compare(two, two).getLeft() == 0);
    }
  }

  /*
   * Versions with both pre-release and build
   */
  private static final List<IVersionsWithChecker> versionsWithPreReleaseAndBuild =
      new ArrayList<IVersionsWithChecker>() {
    private static final long serialVersionUID = 1L;
    {
      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.1");
        }
        public ISemanticVersion getRightVersion() {
          // Arrrgh, v4!
          return new SemanticVersion(4, 0, 0, "rc.1", "build.2.b8f12d7");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.MAJOR_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.1");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 5, 0, "rc.1", "build.2.b8f12d7");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.MINOR_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.1");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 8, "rc.1", "build.2.b8f12d7");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PATCH_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.1");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.2.b8f12d7");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.1");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, "rc.2", "build.2.b8f12d7");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.1");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, "rc.2", "build.1");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });
  }};
  
  @Test
  public void testPreReleaseAndBuildComponentOrdering() {
    runVersionsWithCheckTest(versionsWithPreReleaseAndBuild);
  }

  /*
   * These versions invoke the true, true code path for pre-release and
   * build comparisons.
   */
  private static final List<IVersionsWithChecker> versionWithNullComponents =
  new ArrayList<IVersionsWithChecker>() {
    private static final long serialVersionUID = 1L;
    {
      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 1);
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PATCH_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 2, 0);
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.MINOR_VERSION;
        }        
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(3, 2, 0);
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.MAJOR_VERSION;
        }        
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", null);
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, null, "build.2");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.2");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue == 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return forwardCheck(comparisonValue);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.INVALID;
        }        
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", null);
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, null, "build.2");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.2");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0);
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "beta.11", null);
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, null, "build.11.e0f985a");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, null, "build.1");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, null, "build.2");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }        
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, null, "build.2");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, null, "build.1");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }        
      });

      add(new IVersionsWithChecker() {
        public ISemanticVersion getLeftVersion() {
          return new SemanticVersion(1, 0, 0, "rc.1", "build.1");
        }
        public ISemanticVersion getRightVersion() {
          return new SemanticVersion(1, 0, 0, null, "build.2.b8f12d7");
        }
        public boolean forwardCheck(int comparisonValue) {
          return (comparisonValue < 0);
        }
        public boolean backwardCheck(int comparisonValue) {
          return (comparisonValue > 0);
        }
        public SemanticVersionComponent getChangedComponent() {
          return SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION;
        }
      });
  }};

  @Test
  public void testVersionsWithNullPreReleaseOrBuild() {
    runVersionsWithCheckTest(versionWithNullComponents);
  }

  @Test
  public void testVersionParser() throws SemanticVersionParseException {
    final List<ISemanticVersion> versions = new ArrayList<ISemanticVersion>() {
      private static final long serialVersionUID = -1726840289543320508L;
    {
      add(new SemanticVersion(4, 5, 9));
      add(new SemanticVersion(3, 6, 2, "rc.2.deadbeef", null));
      add(new SemanticVersion(10, 0, 8, null, "build.667"));
      add(new SemanticVersion(0, 5, 10, "rc.10.a4b56", "build.b39de2"));
    }};

    for(ISemanticVersion version : versions) {
      Assert.assertEquals(
          String.format("Failed to parse version [%s]", version),
          SemanticVersion.parseVersion(version.toString()),
          version);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeMajorVersionConstruction() {
    @SuppressWarnings("unused")
    final ISemanticVersion sv = new SemanticVersion(-1, 5, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeMinorVersionConstruction() {
    @SuppressWarnings("unused")
    final ISemanticVersion sv = new SemanticVersion(1, -5, 8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativePatchVersionConstruction() {
    @SuppressWarnings("unused")
    final ISemanticVersion sv = new SemanticVersion(1, 5, -8);
  }

  private void runVersionsWithCheckTest(
      final List<IVersionsWithChecker> vwcCollection) {
    for(IVersionsWithChecker vwc : vwcCollection) {
      System.err.printf(
          "Version [%s] and [%s]...%s",
          vwc.getLeftVersion(),
          vwc.getRightVersion(),
          System.lineSeparator());

      final ImmutablePair<Integer, SemanticVersionComponent> comparison =
          SemanticVersionComparator.compare(
              vwc.getLeftVersion(),
              vwc.getRightVersion());

      Assert.assertTrue(
          String.format(
              "Semantic versions [%s] and [%s] failed check.",
              vwc.getLeftVersion(), vwc.getRightVersion()),
          vwc.forwardCheck(comparison.getLeft()));
      Assert.assertTrue(
          String.format(
              "Semantic versions [%s] and [%s] failed expected component change:" +
              "got [%s], expected [%s]",
              vwc.getLeftVersion(),
              vwc.getRightVersion(),
              comparison.getRight(),
              vwc.getChangedComponent()),
          comparison.getRight() == vwc.getChangedComponent());
    }
  }
}
