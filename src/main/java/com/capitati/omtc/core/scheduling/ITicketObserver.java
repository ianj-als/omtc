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

/**
 * An observer that watches for tickets starting and completing.
 *
 * @author ian
 *
 * @param <T> - the type of the ticket that has completed.
 */
public interface ITicketObserver<T extends Ticket<V>, V> {
  /**
   * Notify the observer that a ticketed task has been submitted. This method
   * shall be called at post-submission and pre-starting time.
   *
   * @param ticket - the ticket representing the submitted task.
   */
  void notifySubmitted(T ticket);

  /**
   * Notify the observer that the ticketed task as begun.
   * 
   * @param ticket - the ticket that has started.
   */
  void notifyStarted(T ticket);

  /**
   * Notify the observer that the ticketed task has ended successfully.
   * 
   * @param ticket - the ticket that has completed.
   */
  void notifySuccess(T ticket);

  /**
   * Notify the observer that the ticketed task has generated an error.
   *
   * @param ticket - the ticket that has completed.
   * @param exception - the exception that represents the error.
   */
  void notifyError(T ticket, Exception exception);
}
