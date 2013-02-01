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

import com.capitati.omtc.core.scheduling.IPriority;
import com.capitati.omtc.core.scheduling.ITicketObserver;
import com.capitati.omtc.core.scheduling.UpdateTicket;
import com.capitati.omtc.core.session.ISession;

/**
 * A mixin for engines that support updating. The update operation is ticketed
 * since the operation be computationally expensive. The update operation
 * shall typically mutate the engine. It is recommended that the engine,
 * during an update operation, is mutually excluded.
 *
 * @author ian
 *
 * @param <V> - the type of the priority's value.
 */
public interface IUpdatableEngine<V, P> extends ITicketableEngine<V> {
  /**
   * Update an engine's parameters using key-value pairs specified in a
   * {@link java.util.Map} object.
   *
   * @param session - the invoking session.
   * @param parameters - the update parameters for the engine.
   * @param priority - the task's priority.
   * @param engineObserver - the observer listening for the starting and
   * completion of the task.
   * @return An updating ticket.
   * @throws Exception On an error.
   */
  UpdateTicket<V> updateParameters(
      ISession session,
      P parameters,
      IPriority<V> priority,
      ITicketObserver<UpdateTicket<V>, V> engineObserver) throws Exception;

  /**
   * Retrieve the current parameters of the engine.
   *
   * @param - the invoking session.
   * @return An object that holds the engine's current parameters.
   * @throws Exception On an error.
   */
  P getParameters(ISession session) throws Exception;
}
