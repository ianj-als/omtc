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
import com.capitati.omtc.core.scheduling.QueryTicket;
import com.capitati.omtc.core.session.ISession;

/**
 * A mixin to add support to querying an engine. A single source sentence
 * is sent to an engine which can be used, amongst other things, for testing.
 * Implementers should mixin this interface with their
 * {@link com.capitati.omtc.core.engine.IEngine} implementations.
 *
 * @author ian
 *
 * @param <V> - the type of the task's priority value.
 */
public interface IQueryableEngine<V> extends ITicketableEngine<V> {
  /**
   * Send a source sentence to an engine.
   *
   * @param session - the invoking session.
   * @param sourceSentence - the source sentence to send to the engine
   * @param engineObserver - an observer to be notified of completion.
   * @return A query ticket object.
   * @throws Exception On an error.
   */
  QueryTicket<V> query(
      ISession session,
      String sourceSentence,
      IPriority<V> priority,
      ITicketObserver<QueryTicket<V>, V> ticketObserver) throws Exception;
}
