package com.capitati.omtc.core.resources;

public interface IMonolingualResource extends IPrimaryResource {
  /**
   * Retrieve the language code.
   *
   * @return An implementation defined {@link java.lang.String} object that
   * shall be the language code.
   */
  String getLanguage();

  /**
   * Retrieve the number of sentences in the monolingual resource.
   *
   * @return The sentence count for the resource.
   */
  int getSentenceCount();
}
