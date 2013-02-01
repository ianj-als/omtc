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

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Implementation of semantic versioning.
 *
 * @author ian
 * @see <a href="http://semver.org/">Semantic Versioning 2.0.0-rc.1</a>
 */
public class SemanticVersion implements ISemanticVersion {
  public static final String IDENTIFIER_SEPARATOR = ".";
  private static final String PRE_RELEASE_SEPARATOR = "-";
  private static final String BUILD_SEPARATOR = "+";
  private static final String GROUP_NAME_MAJOR = "major";
  private static final String GROUP_NAME_MINOR = "minor";
  private static final String GROUP_NAME_PATCH = "patch";
  private static final String GROUP_NAME_PRERELEASE = "prerelease";
  private static final String GROUP_NAME_BUILD = "build";
  private static final Pattern versionPattern =
      Pattern.compile(
          "(?<" + GROUP_NAME_MAJOR + ">\\d+)\\." +
          "(?<" + GROUP_NAME_MINOR + ">\\d+)\\." +
          "(?<" + GROUP_NAME_PATCH + ">\\d+)" +
          "(([" + PRE_RELEASE_SEPARATOR + "](?<" + GROUP_NAME_PRERELEASE + ">[0-9A-Z-.]+))?" +
          "([" + BUILD_SEPARATOR + "](?<" + GROUP_NAME_BUILD + ">[0-9A-Z-.]+))?)?",
          Pattern.CASE_INSENSITIVE);

  public static ISemanticVersion parseVersion(final String version)
  throws SemanticVersionParseException {
    final Matcher matcher = versionPattern.matcher(version);

    if(matcher.matches() == false) {
      throw new SemanticVersionParseException(version);
    }

    final int theMajor = Integer.parseInt(matcher.group(GROUP_NAME_MAJOR));
    final int theMinor = Integer.parseInt(matcher.group(GROUP_NAME_MINOR));
    final int thePatch = Integer.parseInt(matcher.group(GROUP_NAME_PATCH));
    final String thePreRelease = matcher.group(GROUP_NAME_PRERELEASE);
    final String theBuild = matcher.group(GROUP_NAME_BUILD);

    return new SemanticVersion(
        theMajor,
        theMinor,
        thePatch,
        thePreRelease,
        theBuild);
  }

  private final int major;
  private final int minor;
  private final int patch;
  private final String[] preRelease;
  private final String[] build;

  public SemanticVersion(
      final int theMajorVersion,
      final int theMinorVersion,
      final int thePatchVersion) {
    super();
    if((theMajorVersion < 0) ||
       (theMinorVersion < 0) ||
       (thePatchVersion <0)) {
      throw new IllegalArgumentException(
          "Major, minor, or patch version cannot be negative.");
    }
    major = theMajorVersion;
    minor = theMinorVersion;
    patch = thePatchVersion;
    preRelease = null;
    build = null;
  }

  public SemanticVersion(
      final int theMajorVersion,
      final int theMinorVersion,
      final int thePatchVersion,
      final String thePreRelease,
      final String theBuild) {
    super();
    if((theMajorVersion < 0) ||
        (theMinorVersion < 0) ||
        (thePatchVersion <0)) {
      throw new IllegalArgumentException(
          "Major, minor, or patch version cannot be negative.");
    }
    major = theMajorVersion;
    minor = theMinorVersion;
    patch = thePatchVersion;
    preRelease = (thePreRelease != null) ?
        thePreRelease.split("[" + IDENTIFIER_SEPARATOR + "]") :
        null;
    build = (theBuild != null) ?
        theBuild.split("[" + IDENTIFIER_SEPARATOR + "]") :
        null;
  }

  public int getMajor() {
    return major;
  }

  public int getMinor() {
    return minor;
  }

  public int getPatch() {
    return patch;
  }

  public String[] getPreRelease() {
    return preRelease;
  }

  public String[] getBuild() {
    return build;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(build);
    result = prime * result + major;
    result = prime * result + minor;
    result = prime * result + patch;
    result = prime * result + Arrays.hashCode(preRelease);
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
    SemanticVersion other = (SemanticVersion) obj;
    if(!Arrays.equals(build, other.build))
      return false;
    if(major != other.major)
      return false;
    if(minor != other.minor)
      return false;
    if(patch != other.patch)
      return false;
    if(!Arrays.equals(preRelease, other.preRelease))
      return false;
    return true;
  }

  @Override
  public String toString() {
    final StringBuffer repr =
        new StringBuffer(String.format("%d.%d.%d", major, minor, patch));

    if(preRelease != null) {
      repr.append(PRE_RELEASE_SEPARATOR);
      repr.append(StringUtils.join(preRelease, IDENTIFIER_SEPARATOR));
    }
    
    if(build != null) {
      repr.append(BUILD_SEPARATOR);
      repr.append(StringUtils.join(build, IDENTIFIER_SEPARATOR));
    }

    return repr.toString();
  }

  public int compareTo(final ISemanticVersion theOtherVersion) {
    return SemanticVersionComparator.compare(this, theOtherVersion).getLeft();
  }
}
