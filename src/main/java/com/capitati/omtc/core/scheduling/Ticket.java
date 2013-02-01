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

import com.capitati.omtc.core.session.ISession;

public abstract class Ticket<V> {
  private final UUID identifier;
  private final Date startDate;
  private final ISession session;
  private final V priority;

  protected Date endDate;

  protected Ticket(
      final UUID theIdentifier,
      final Date theStartDate,
      final ISession theSession,
      final V thePriority) {
    super();
    identifier = theIdentifier;
    startDate = theStartDate;
    session = theSession;
    priority = thePriority;
    endDate = null;
  }

  public UUID getIdentifier() {
    return identifier;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public ISession getSession() {
    return session;
  }

  public V getPriority() {
    return priority;
  }
}
