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

import java.util.Set;

import com.capitati.omtc.core.engine.IEngine;
import com.capitati.omtc.core.resources.IDerivedResource;
import com.capitati.omtc.core.resources.IPrimaryResource;
import com.capitati.omtc.core.scheduling.IPriority;
import com.capitati.omtc.core.scheduling.ITicketObserver;
import com.capitati.omtc.core.scheduling.TranslationTicket;
import com.capitati.omtc.core.session.ISession;
import com.google.common.base.Predicate;

public interface ITranslator<V,
                               T extends IPrimaryResource,
                               G extends IPrimaryResource>
extends IDerivedResource {
  /**
   * Retrieve the MT engine used with this translator.
   *
   * @return A {@link com.capitati.omtc.core.engine.IEngine} object, otherwise
   * null if no MT engine is required for this translator.  
   */
  IEngine getEngine();

  /**
   * Retrieve the set of glossaries being used with this translator.
   *
   * @return A {@link java.util.Set} of
   * objects implementing the
   * {@link com.capitati.omtc.core.resources.IPrimaryResource} interface,
   * otherwise an empty set if no glossaries are employed.
   */
  Set<G> getGlossaries();

  /**
   * Retrieve the set of translation memories being used with the translator.
   *
   * @return A {@link java.util.Set} of objects implementing the
   * {@link com.capitati.omtc.core.resources.IPrimaryResource} interface,
   * otherwise an empty set if no translation memories are used.
   */
  Set<T> getTranslationMemories();

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
   * @throws Exception On a scheduling error.
   */
  TranslationTicket<V, T, G> scheduleTranslation(
      ISession session,
      IPrimaryResource resourceToTranslate,
      IPriority<V> thePriority,
      ITicketObserver<TranslationTicket<V, T, G>, V> translationObserver)
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
   * @throws Exception On a scheduling error.
   */
  TranslationTicket<V, T, G> scheduleTranslation(
      ISession session,
      String sourceSentence,
      IPriority<V> thePriority,
      ITicketObserver<TranslationTicket<V, T, G>, V> translationObserver)
  throws Exception;

  /**
   * Retrieve the dispensed translation tickets.
   *
   * @param session - the invoking session.
   * @param filter - a predicate used to filter the translation tickets.
   * @return A {@link java.util.Set} of currently scheduled translation
   * tickets. It is the implementations responsibility to manage the in-flight
   * collection of tickets.
   * @throws Exception On error retrieving translations.
   */
  Set<TranslationTicket<V, T, G>> retrieveTranslations(
      ISession session,
      Predicate<TranslationTicket<V, T, G>> filter) throws Exception;
}
