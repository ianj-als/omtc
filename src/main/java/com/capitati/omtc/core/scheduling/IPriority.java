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
 * This presents a priority that will be used to schedule a task in the MT API.
 * 
 * @author ianjohnson
 *
 * @param <V> the type of the priority value.
 */
public interface IPriority<V> {
  /**
   * A getter function to provide the caller with a task's priority. This allows
   * late binding of any priority parameters at call time.
   * 
   * @return The priority's value of type V.
   */
  V getPriority();
}
