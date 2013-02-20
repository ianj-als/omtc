package com.capitati.omtc.core.resources;


/**
 * A glossary.
 * 
 * @author ian
 */
public interface IGlossary extends ITranslationResource {
  /**
   * Retrieve the number of glossary entries.
   *
   * @return The number of entries.
   */
  int getNumberOfEntries();
}
