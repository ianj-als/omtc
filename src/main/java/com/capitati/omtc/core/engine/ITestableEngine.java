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
import com.capitati.omtc.core.scheduling.TestingTicket;
import com.capitati.omtc.core.session.ISession;

/**
 * 
 * @author ian
 *
 * @param <P>
 */
public interface ITestableEngine<V> extends ITicketableEngine<V> {
  TestingTicket<V> test(
      ISession session,
      Set<IPrimaryResource> testingResources,
      IPriority<V> priority,
      ITicketObserver<TestingTicket<V>, V> engineObserver) throws Exception;
}
