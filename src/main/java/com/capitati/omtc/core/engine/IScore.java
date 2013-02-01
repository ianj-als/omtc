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
package com.capitati.omtc.core.engine;

import java.util.Set;

import com.capitati.omtc.core.resources.IPrimaryResource;

/**
 * A representation of a single evaluation score.
 *
 * @author ian
 */
public interface IScore {
  /**
   * Retrieve the score scheme. Or, which algorithm was used to generate the
   * score.
   * 
   * @return A scoring scheme description object.
   */
  IScoreScheme getScheme();

  /**
   * The evaluated score.
   * 
   * @return The score for the scheme used.
   */
  double getScore();

  /**
   * Retrieve the resources that generated the score.
   *
   * @return A {@link java.util.Set} of primary resource objects.
   */
  Set<IPrimaryResource> getResources();
}
