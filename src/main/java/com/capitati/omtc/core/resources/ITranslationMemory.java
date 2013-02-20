package com.capitati.omtc.core.resources;

/**
 * A translation memory.
 *
 * @author ian
 */
public interface ITranslationMemory extends ITranslationResource {
  /**
   * Retrieves the number of segments from the translation memory.
   *
   * @return The segment count of the translation memory.
   */
  int getNumberOfSegments();
}
