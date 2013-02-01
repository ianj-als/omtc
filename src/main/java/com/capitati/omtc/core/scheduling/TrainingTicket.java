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

import com.capitati.omtc.core.engine.IEngine;
import com.capitati.omtc.core.session.ISession;

public class TrainingTicket<V> extends EngineTicket<V> {
  public TrainingTicket(
      final UUID theIdentifier,
      final Date theStartDate,
      final ISession theSession,
      final V thePriority,
      final IEngine theParticipatingEngine) {
    super(
        theIdentifier,
        theStartDate,
        theSession,
        thePriority,
        theParticipatingEngine);
  }
}
