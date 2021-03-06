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
package com.capitati.omtc.core.scheduling;

import java.util.Date;
import java.util.UUID;

import com.capitati.omtc.core.resources.IPrimaryResource;
import com.capitati.omtc.core.session.ISession;
import com.capitati.omtc.core.translation.ITranslator;

public class TranslationTicket<V, T extends IPrimaryResource, G extends IPrimaryResource>
extends Ticket<V> {
  private final ITranslator<V, T, G> translator;

  public TranslationTicket(
      final UUID theIdentifier,
      final Date theStartDate,
      final ISession theSession,
      final V thePriority,
      final ITranslator<V, T, G> theTranslator) {
    super(theIdentifier, theStartDate, theSession, thePriority);
    translator = theTranslator;
  }

  public ITranslator<V, T, G> getTranslator() {
    return translator;
  }
}
