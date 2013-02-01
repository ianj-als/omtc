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
package com.capitati.omtc.core.translation;

import java.util.HashSet;
import java.util.Set;

import com.capitati.omtc.core.engine.IEngine;
import com.capitati.omtc.core.resources.IPrimaryResource;
import com.capitati.omtc.core.scheduling.IPriority;
import com.capitati.omtc.core.scheduling.ITicketObserver;
import com.capitati.omtc.core.scheduling.TranslationTicket;
import com.google.common.base.Predicate;

/**
 * An associative class that brings together an engine, glossaries and
 * translation memories. These resources are to be used as a translator.
 * 
 * @author ian
 *
 * @param <P> - the type of the priority's value.
 */
public abstract class Translator<V> {
  private final IEngine engine;
  private final Set<IPrimaryResource> glossaries =
      new HashSet<IPrimaryResource>();
  private final Set<IPrimaryResource> translationMemories =
      new HashSet<IPrimaryResource>();

  protected Translator(final IEngine theEngine) {
    super();
    engine = theEngine;
  }

  protected Translator(
      final IEngine theEngine,
      final Set<IPrimaryResource> theGlossaries,
      final Set<IPrimaryResource> theTranslationMemories) {
    super();
    engine = theEngine;
    glossaries.addAll(theGlossaries);
    translationMemories.addAll(theTranslationMemories);
  }

  public IEngine getEngine() {
    return engine;
  }

  public Set<IPrimaryResource> getGlossaries() {
    return glossaries;
  }

  public Set<IPrimaryResource> getTranslationMemories() {
    return translationMemories;
  }

  /**
   * Schedule a translation for a primary resource. It is implementation
   * defined which kind of primary resources shall be translated.
   *
   * @param session - the invoking session.
   * @param resourceToTranslate - the primary resource to translate.
   * @param thePriority - the requested priority.
   * @param translationObserver - the observer that listens for the translation
   * task to starting and complete.
   * @return A translation ticket.
   * @throws Exception On an error.
   */
  public abstract TranslationTicket<V> scheduleTranslation(
      IPrimaryResource resourceToTranslate,
      IPriority<V> thePriority,
      ITicketObserver<TranslationTicket<V>, V> translationObserver)
  throws Exception;

  /**
   * Schedule a translation for a single sentence.
   *
   * @param session - the invoking session.
   * @param sourceSentence - the sentence to translate.
   * @param thePriority - the requested priority.
   * @param translationObserver - the observer that listens for the translation
   * task to starting and complete.
   * @return A translation ticket.
   * @throws Exception On an error.
   */
  public abstract TranslationTicket<V> scheduleTranslation(
      String sourceSentence,
      IPriority<V> thePriority,
      ITicketObserver<TranslationTicket<V>, V> translationObserver)
  throws Exception;

  /**
   * Retrieve the dispensed translation tickets.
   * 
   * @param filter - a predicate used to filter the translation tickets.
   * @return A {@link java.util.Set} of currently scheduled translation
   * tickets. It is the implementations responsibility to manage the in-flight
   * collection of tickets.
   *
   * @throws Exception
   */
  public abstract Set<TranslationTicket<V>> retrieveTranslations(
      Predicate<TranslationTicket<V>> filter) throws Exception;
}
