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

import com.capitati.omtc.core.integration.data.IDataExchangeFormatSpecification;
import com.capitati.omtc.core.scheduling.CompositionTicket;
import com.capitati.omtc.core.scheduling.IPriority;
import com.capitati.omtc.core.scheduling.ITicketObserver;
import com.capitati.omtc.core.session.ISession;

/**
 * A mixin for composing two engines. Implementers are to mixin this
 * interface with their {@link com.capitati.omtc.core.engine.IEngine}
 * implementation.
 *
 * @author ian
 *
 * @param <V> - the priority's type.
 */
public interface IComposableEngine<V> extends ITicketableEngine<V> {
  /**
   * Compose two engines. Suppose two engines E<sub>1</sub> and E<sub>2</sub>,
   * this composition shall produce an engine that is
   * E<sub>resultant</sub> = E<sub>2</sub>(E<sub>1</sub>(s)).
   * 
   * @param session - the invoking session.
   * @param engine - the other engine to compose, i.e., E<sub>2</sub>.
   * @param exchangeFormat - the exchange data format that the engines will use
   * to communicate.
   * @param resultantEngine - the engine that the composition operation
   * shall create if successful.
   * @param priority - the priority of this task.
   * @param engineObserver - the observer that will be notified on completion
   * of the task.
   * @return A {@link com.capitati.omtc.core.scheduling.CompositionTicket} object.
   * @throws Exception On an error.
   */
  CompositionTicket<V> compose(
      ISession session,
      IEngine engine,
      IDataExchangeFormatSpecification exchangeFormat,
      IEngine resultantEngine,
      IPriority<V> priority,
      ITicketObserver<CompositionTicket<V>, V> engineObserver) throws Exception;
}
