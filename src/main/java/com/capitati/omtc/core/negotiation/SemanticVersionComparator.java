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

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * A comparator for two semantic versions.
 *
 * @author ian
 * @see <a href="http://semver.org/">Semantic Versioning 2.0.0-rc.1</a> for
 * the specification on how this comparator operates.
 */
public class SemanticVersionComparator {
  public static final ImmutablePair<Integer, SemanticVersionComponent> compare(
      final ISemanticVersion verOne,
      final ISemanticVersion verTwo) {
    if(verOne.equals(verTwo) == true) {
      return new ImmutablePair<Integer, SemanticVersionComponent>(
          0, SemanticVersionComponent.INVALID);
    }

    if(verOne.getMajor() == verTwo.getMajor()) {
      if(verOne.getMinor() == verTwo.getMinor()) {
        if(verOne.getPatch() == verTwo.getPatch()) {
          /*
           * Compare the pre-release components.
           */
          final ImmutablePair<Integer, Boolean> preReleaseCompResult =
              compareArray(verOne.getPreRelease(), verTwo.getPreRelease());
          /*
           * Compare the build components.
           */
          final ImmutablePair<Integer, Boolean> buildCompResult =
              compareArray(verOne.getBuild(), verTwo.getBuild());

          /*
           * Unpack the comparison values.
           */
          final int prc = preReleaseCompResult.getLeft();
          final int bc = buildCompResult.getLeft();
          
          if((preReleaseCompResult.getRight() == true) &&
             (buildCompResult.getRight() == true)) {
            /*
             * There was at most one pre-release component, and at most one
             * build component in both versions.
             */
            if((Math.abs(prc) == 1) && (Math.abs(bc) == 1)) {
              return new ImmutablePair<Integer, SemanticVersionComponent>(
                  (Math.signum((float )prc) == 1.0) ? -1 : 1,
                  SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION);
            } else if((Math.abs(prc) == 1) && (bc == 0)) {
              return new ImmutablePair<Integer, SemanticVersionComponent>(
                  (Math.signum((float )prc) == 1.0) ? -1 : 1,
                  SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION);
            }
            
            return new ImmutablePair<Integer, SemanticVersionComponent>(
                bc, SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION);
          } else if((preReleaseCompResult.getRight() == true) &&
              (buildCompResult.getRight() == false)) {
            /*
             * One of the versions had a null pre-release.
             */
            return new ImmutablePair<Integer, SemanticVersionComponent>(
                bc, SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION);
          }

          /*
           * Either one of the version has a null build or both versions where
           * fully specified.
           */
          return new ImmutablePair<Integer, SemanticVersionComponent>(
              (Math.abs(prc) == 1) ? prc : bc,
              SemanticVersionComponent.PRE_RELEASE_AND_BUILD_VERSION);
        } else {
          return new ImmutablePair<Integer, SemanticVersionComponent>(
              verOne.getPatch() - verTwo.getPatch(),
              SemanticVersionComponent.PATCH_VERSION);
        }
      } else {
        return new ImmutablePair<Integer, SemanticVersionComponent>(
            verOne.getMinor() - verTwo.getMinor(),
            SemanticVersionComponent.MINOR_VERSION);
      }
    }

    return new ImmutablePair<Integer, SemanticVersionComponent>(
        verOne.getMajor() - verTwo.getMajor(),
        SemanticVersionComponent.MAJOR_VERSION);
  }

  /**
   * Lexically compare the components of two string arrays.
   *
   * @param one - First string array.
   * @param two - The array that is compared to one.
   * @return If one array is determined to be lexically less than two then
   * -1 is returned. If one is determined to be lexically greater then two then
   * 1 is returned. Otherwise, 0 is returned.
   */
  private static ImmutablePair<Integer, Boolean> compareArray(
      final String[] one,
      final String[] two) {
    if((one == null) && (two == null)) {
      return new ImmutablePair<Integer, Boolean>(0, true);
    }

    if((one == null) && (two != null)) {
      return new ImmutablePair<Integer, Boolean>(-1, true);
    }

    if((one != null) && (two == null)) {
      return new ImmutablePair<Integer, Boolean>(1, true);
    }

    @SuppressWarnings("unchecked")
    final Iterator<String> oneIterator = new ArrayIterator(one);
    @SuppressWarnings("unchecked")
    final Iterator<String> twoIterator = new ArrayIterator(two);
    String oneComp = null;
    String twoComp = null;
    int comp = 0;

    while(true) {
      try {
        oneComp = oneIterator.next();
      } catch(final NoSuchElementException ex) {
        oneComp = null;
      }

      try {
        twoComp = twoIterator.next();
      } catch(final NoSuchElementException ex) {
        twoComp = null;
      }

      if((oneComp == null) && (twoComp == null)) {
        break;
      }

      comp = componentCompare(oneComp, twoComp);
      if(comp != 0) {
        break;
      }
    }

    return new ImmutablePair<Integer, Boolean>(comp, false);
  }

  /**
   * Compare two components from the pre-release and build string arrays as
   * detailed in the Semantic Versioning 2.0.0-rc.1 specification.
   * 
   * @param oneComp - first value.
   * @param twoComp - second value.
   * @return If first value is determined to be less than the second value then
   * return -1. If one value is determined to be greater than the second value
   * then return 1. Otherwise, 0 is returned.
   * @see <a href="http://semver.org/">Semantic Versioning 2.0.0-rc.1</a>
   */
  private static int componentCompare(
      final String oneComp, final String twoComp) {
    if((oneComp == null) && (twoComp == null)) {
      return 0;
    }

    if((oneComp == null) && (twoComp != null)) {
      return -1;
    }
    
    if((oneComp != null) && (twoComp == null)) { 
      return 1;
    }

    boolean isOneInt = false;
    boolean isTwoInt = false;
    int oneInt = 0;
    int twoInt = 0;

    try {
      oneInt = Integer.parseInt(oneComp);
      isOneInt = true;
    } catch(final NumberFormatException ex) {
      isOneInt = false;
    }

    try {
      twoInt = Integer.parseInt(twoComp);
      isTwoInt = true;
    } catch(final NumberFormatException ex) {
      isTwoInt = false;
    }

    final int comp = ((isOneInt == true) && (isTwoInt == true)) ?
        oneInt - twoInt :
        oneComp.compareTo(twoComp);
    final int compValue = (comp > 0) ? 1 : (comp < 0) ? -1 : 0;
    
    return compValue;
  }
}
