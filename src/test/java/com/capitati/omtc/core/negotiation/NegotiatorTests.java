package com.capitati.omtc.core.negotiation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NegotiatorTests {
  public Mockery mockery;
  public IServiceResourceCapability resourceOne;
  public IServiceResourceCapability resourceTwo;
  public IServiceResourceCapability resourceThree;
  public IServiceResourceCapability resourceFour;
  public IFeatureCapability featureOne;
  public IFeatureCapability featureTwo;
  public IPrerequisiteCapability prereq;
  public List<IServiceResourceCapability> resources;
  public Set<IFeatureCapability> features;

  @Before
  public void setUp() {
    mockery = new JUnit4Mockery();
    resourceOne = mockery.mock(IServiceResourceCapability.class, "resourceOne");
    resourceTwo = mockery.mock(IServiceResourceCapability.class, "resourceTwo");
    resourceThree = mockery.mock(IServiceResourceCapability.class, "resourceThree");
    resourceFour = mockery.mock(IServiceResourceCapability.class, "resourceFour");
    featureOne = mockery.mock(IFeatureCapability.class, "featureOne");
    featureTwo = mockery.mock(IFeatureCapability.class, "featureTwo");
    prereq = mockery.mock(IPrerequisiteCapability.class, "prereqOne");

    resources =
        new ArrayList<IServiceResourceCapability>() {
          private static final long serialVersionUID = 1L;
          {
            add(resourceOne);
            add(resourceTwo);
            add(resourceThree);
            add(resourceFour);
          }};
          
    features =
        new HashSet<IFeatureCapability>() {
          private static final long serialVersionUID = 1L;
          {
            add(featureOne);
            add(featureTwo);
          }};
  }

  @Test
  public void testNegotiation() throws SemanticVersionParseException {
    final ISemanticVersion clientVersion = SemanticVersion.parseVersion("1.3.7");
    final IResourceCapability clientResourceCapOne = new IResourceCapability() {
      public Capability getName() {
        return Capability.RES_FILE_XLIFF;
      }
      public String getLowerVersion() {
        return "1.6";
      }
      public String getHigherVersion() {
        return "2.0";
      }
    };
    final IResourceCapability clientResourceCapTwo = new IResourceCapability() {
      public Capability getName() {
        return Capability.RES_FILE_XLIFF;
      }
      public String getLowerVersion() {
        return "7.0";
      }
      public String getHigherVersion() {
        return "10.0";
      }
    };
    final IResourceCapability clientResourceCapThree = new IResourceCapability() {
      public Capability getName() {
        return Capability.RES_FILE_TMX;
      }
      public String getLowerVersion() {
        return "0.8";
      }
      public String getHigherVersion() {
        return "10.5";
      }
    };
    final IResourceCapability clientResourceCapFour = new IResourceCapability() {
      public Capability getName() {
        return Capability.RES_FILE_TTX;
      }
      public String getLowerVersion() {
        return "2.718";
      }
      public String getHigherVersion() {
        return "3.141";
      }
    };
    final Set<IResourceCapability> clientResourceCapabilities =
        new HashSet<IResourceCapability>() {
          private static final long serialVersionUID = 1L;
          {
            add(clientResourceCapOne);
            add(clientResourceCapTwo);
            add(clientResourceCapThree);
            add(clientResourceCapFour);
          }};

    mockery.checking(new Expectations() {{
      allowing(resourceOne).getName();
      will(returnValue(Capability.RES_FILE_XLIFF));
      allowing(resourceOne).getLowerVersion();
      will(returnValue("1.5"));
      allowing(resourceOne).getHigherVersion();
      will(returnValue("2.3"));
      allowing(resourceOne).compareVersion(with(clientResourceCapOne));
      will(returnValue(0));
      allowing(resourceOne).compareVersion(with(clientResourceCapTwo));
      will(returnValue(1));
      allowing(resourceOne).compareVersion(with(any(IResourceCapability.class)));
      will(returnValue(-1));

      allowing(resourceTwo).getName();
      will(returnValue(Capability.RES_FILE_XLIFF));
      allowing(resourceTwo).getLowerVersion();
      will(returnValue("3.7"));
      allowing(resourceTwo).getHigherVersion();
      will(returnValue("4.2"));
      allowing(resourceTwo).compareVersion(with(any(IResourceCapability.class)));
      will(returnValue(-1));

      allowing(resourceThree).getName();
      will(returnValue(Capability.RES_FILE_TMX));
      allowing(resourceThree).getLowerVersion();
      will(returnValue("0.2"));
      allowing(resourceThree).getHigherVersion();
      will(returnValue("1.0"));
      allowing(resourceThree).compareVersion(clientResourceCapThree);
      will(returnValue(0));
      allowing(resourceThree).compareVersion(with(any(IResourceCapability.class)));
      will(returnValue(-1));

      allowing(resourceFour).getName();
      will(returnValue(Capability.RES_FILE_TBX));
      allowing(resourceFour).getLowerVersion();
      will(returnValue("0.8"));
      allowing(resourceFour).getHigherVersion();
      will(returnValue("10.5"));
      allowing(resourceFour).compareVersion(with(any(IResourceCapability.class)));
      will(returnValue(-1));

      allowing(featureOne).getName();
      will(returnValue(Capability.FET_RES_DOWNLOAD));
      allowing(featureOne).getPrerequisiteCapabilities();
      will(returnValue(new HashSet<IPrerequisiteCapability>()));

      allowing(featureTwo).getName();
      will(returnValue(Capability.FET_RES_UPLOAD));
      allowing(featureTwo).getPrerequisiteCapabilities();
      will(returnValue(new HashSet<IPrerequisiteCapability>() {
        private static final long serialVersionUID = 1L;
        {
          add(new IPrerequisiteCapability() {
            public Capability getName() {
              return Capability.PRE_REQ_PAYMENT;
            }
          });
        }}));
    }});

    final INegotiator negotiator = new Negotiator("1.5.1", resources, features);
    final IClientCapabilityRequest request = new IClientCapabilityRequest() {
      public IAPICapability getVersionCapability() {
       return new IAPICapability() {
         public Capability getName() {
           return Capability.API_VERSION;
         }
         public ISemanticVersion getVersion() {
           return clientVersion;
         }
       };
      }
      public Set<IResourceCapability> getResourceCapabilities() {
        return clientResourceCapabilities;
      }
    };
    final IClientCapabilityResponse response = negotiator.negotiate(request);

    final Set<IResourceCapability> targetUnsupportedResources =
        new HashSet<IResourceCapability>() {
          private static final long serialVersionUID = 4358016718704699346L;
          {
            add(clientResourceCapTwo);
            add(clientResourceCapFour);
          }};

    Assert.assertTrue(response.isClientAPISupported());
    Assert.assertEquals(
        targetUnsupportedResources,
        response.getUnsupportedResourceCapabilities());
    Assert.assertEquals(features, response.getFeatureCapabilities());
  }

  @Test
  public void testAPIVersionNotSupported() throws SemanticVersionParseException {
    final INegotiator negotiator = new Negotiator("1.5.1", null, null);
    final IClientCapabilityRequest requestLow = new IClientCapabilityRequest() {
      public IAPICapability getVersionCapability() {
       return new IAPICapability() {
         public Capability getName() {
           return Capability.API_VERSION;
         }
         public ISemanticVersion getVersion() {
           return new SemanticVersion(0, 9, 0, "alpha.1", "build.1234");
         }
       };
      }
      public Set<IResourceCapability> getResourceCapabilities() {
        return new HashSet<IResourceCapability>();
      }
    };
    final IClientCapabilityResponse responseLow = negotiator.negotiate(requestLow);
    final IClientCapabilityRequest requestHigh = new IClientCapabilityRequest() {
      public IAPICapability getVersionCapability() {
       return new IAPICapability() {
         public Capability getName() {
           return Capability.API_VERSION;
         }
         public ISemanticVersion getVersion() {
           return new SemanticVersion(2, 3, 7);
         }
       };
      }
      public Set<IResourceCapability> getResourceCapabilities() {
        return new HashSet<IResourceCapability>();
      }
    };
    final IClientCapabilityResponse responseHigh = negotiator.negotiate(requestHigh);
    
    Assert.assertFalse(responseLow.isClientAPISupported());
    Assert.assertFalse(responseHigh.isClientAPISupported());
  }
}
