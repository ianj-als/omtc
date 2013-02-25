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

import java.net.URI;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.capitati.omtc.core.engine.IEngine;
import com.capitati.omtc.core.resources.IDerivedResource;
import com.capitati.omtc.core.resources.IPrimaryResource;
import com.capitati.omtc.core.resources.IResource;
import com.capitati.omtc.core.scheduling.IPriority;
import com.capitati.omtc.core.scheduling.ITicketObserver;
import com.capitati.omtc.core.scheduling.TranslationTicket;
import com.capitati.omtc.core.session.ISession;
import com.google.common.base.Predicate;

/**
 * An associative class that brings together an engine, glossaries and
 * translation memories. These resources are to be used as a translator.
 * 
 * @author ian
 *
 * @param <P> - the type of the priority's value.
 */
public abstract class Translator<V,
                                    T extends IPrimaryResource,
                                    G extends IPrimaryResource>
implements IDerivedResource {
  private final UUID identifier;
  private final URI location;
  private final Date birthDate;
  private final IEngine engine;
  private final Set<G> glossaries = new HashSet<G>();
  private final Set<T> translationMemories = new HashSet<T>();

  protected Translator(
      final UUID theIdentifier,
      final URI theLocation,
      final Date theBirthDate,
      final IEngine theEngine) {
    super();
    identifier = theIdentifier;
    location = theLocation;
    birthDate = theBirthDate;
    engine = theEngine;
  }

  protected Translator(
      final UUID theIdentifier,
      final URI theLocation,
      final Date theBirthDate,
      final IEngine theEngine,
      final Set<G> theGlossaries,
      final Set<T> theTranslationMemories) {
    super();
    identifier = theIdentifier;
    location = theLocation;
    birthDate = theBirthDate;
    engine = theEngine;
    glossaries.addAll(theGlossaries);
    translationMemories.addAll(theTranslationMemories);
  }

  public UUID getIdentifier() {
    return identifier;
  }

  public URI getURI() {
    return location;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public Set<IResource> getCreationResources() {
    final Set<IResource> creationResources = new HashSet<IResource>(glossaries);

    creationResources.addAll(translationMemories);

    return creationResources;
  }

  public IEngine getEngine() {
    return engine;
  }

  public Set<G> getGlossaries() {
    return new HashSet<G>(glossaries);
  }

  public Set<T> getTranslationMemories() {
    return new HashSet<T>(translationMemories);
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
   * @throws Exception On a scheduling error.
   */
  public abstract TranslationTicket<V, T, G> scheduleTranslation(
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
  public abstract TranslationTicket<V, T, G> scheduleTranslation(
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
  public abstract Set<TranslationTicket<V, T, G>> retrieveTranslations(
      ISession session,
      Predicate<TranslationTicket<V, T, G>> filter) throws Exception;
}
