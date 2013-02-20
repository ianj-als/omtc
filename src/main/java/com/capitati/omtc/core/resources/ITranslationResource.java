package com.capitati.omtc.core.resources;

import java.util.Map;

import com.capitati.omtc.core.resources.IPrimaryResource;

/**
 * An abstract resource that will be used in some translation operation.
 *
 * @author ian
 */
public interface ITranslationResource extends IPrimaryResource {
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
  String[] getTargetLanguage();

  /**
   * Retrieve the source language word count.
   *
   * @return The number of words in the source language.
   */
  int getSourceWordCount();

  /**
   * Retrieve the target language word counts.
   *
   * @return A {@link java.util.Map} object whose keys shall be the target
   * language codes, returned by {@link ITranslationResource.getTargetLanguage},
   * and values the word count for the target language.
   */
  Map<String, Integer> getTargetWordCounts();
}
