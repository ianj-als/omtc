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
package com.capitati.omtc.core.engine;

import java.util.Set;

import com.capitati.omtc.core.resources.IPrimaryResource;
import com.capitati.omtc.core.scheduling.IPriority;
import com.capitati.omtc.core.scheduling.ITicketObserver;
import com.capitati.omtc.core.scheduling.TrainingTicket;
import com.capitati.omtc.core.session.ISession;

/**
 * A mixin to provide retraining capability if the engine implementation
 * can support it.
 *
 * @author ian
 *
 * @param <V> - the type of the priority value.
 */
public interface IRetrainableEngine<V> extends ITicketableEngine<V> {
  /**
   * Retain an engine using a set of primary resources.
   *
   * @param session - the invoking session.
   * @param retrainingResources - a {@link java.util.Set} of
   * {@link com.capitati.omtc.core.resources.IPrimaryResource} objects that
   * shall be used to retain the engine.
   * @param priority - the priority of the retraining task.
   * @param engineObserver - the observer listening for the retrain to complete.
   * @return A training ticket.
   * @throws Exception On an error.
   */
  TrainingTicket<V> retrain(
      ISession session,
      Set<IPrimaryResource> retrainingResources,
      IPriority<V> priority,
      ITicketObserver<TrainingTicket<V>, V> engineObserver) throws Exception;
}
