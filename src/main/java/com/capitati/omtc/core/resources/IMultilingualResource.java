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
package com.capitati.omtc.core.resources;

import java.util.Map;

import com.capitati.omtc.core.resources.IPrimaryResource;

/**
 * An abstract resource that contains multilingual data.
 *
 * @author ian
 */
public interface IMultilingualResource extends IPrimaryResource {
  /**
   * Retrieve the source language code.
   *
   * @return An implementation defined {@link java.lang.String} object that
   * shall be the source language code.
   */
  String getSourceLanguage();

  /**
   * Retrieve the target language code.
   *
   * @return A {@link java.lang.String} array object that shall list the
   * implementation target language codes.
   */
  String[] getTargetLanguages();

  /**
   * Retrieve the source language sentence count.
   *
   * @return The number of sentences in the source language.
   */
  int getSourceSentenceCount();

  /**
   * Retrieve the target language sentence counts.
   *
   * @return A {@link java.util.Map} object whose keys shall be the target
   * language codes, returned by {@link IMultilingualResource.getTargetLanguage},
   * and values the sentence count for the target language.
   */
  Map<String, Integer> getTargetSentenceCounts();
}
