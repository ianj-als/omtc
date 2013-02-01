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
 * Implementers should define the priority policy for their product. A priority
 * description is used to provide the parameters that shall be used to generate
 * a priority object. The description may include SLA, due date, user's price
 * plan, one-off task payment details that would improve a task's priority etc.
 *
 * @author ianjohnson
 *
 * @param <V> the type of the priority value.
 * @param <D> the type of the priority description. This is a type that provides
 * all the parameters that will be combined to create a priority object.
 */
public interface IPriorityMechanism<V, D> {
  /**
   * Taking a priority description generate a priority object that is the
   * combination of the description.
   * 
   * @param priorityDescription - the parameters to use to generate the priority.
   * @return A priority object.
   * @throws Exception
   */
  IPriority<V> getPriority(D priorityDescription) throws Exception;
}
