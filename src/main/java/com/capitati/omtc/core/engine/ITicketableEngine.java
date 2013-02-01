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

import com.capitati.omtc.core.scheduling.EngineTicket;
import com.capitati.omtc.core.session.ISession;
import com.google.common.base.Predicate;

/**
 * A mixin that provides support for engines that, on submiting tasks, return
 * task tickets. This inferface should be used with the implementer's
 * {@link com.capitati.omtc.core.engine.IEngine} concrete classes to 
 * provide a mechanism to return <em>running</em> tickets. It is the
 * responsibility of the concrete class to manage the appropriate collection
 * of dispensed tickets.
 *
 * @author ian
 *
 * @param <V> - the type of the priority's value.
 */
public interface ITicketableEngine<V> {
  Set<EngineTicket<V>> retrieveTickets(
      ISession session, Predicate<EngineTicket<V>> filter)
  throws Exception;
}
