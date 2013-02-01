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

import java.util.Map;

import com.capitati.omtc.core.session.ISession;

/**
 * A mixin to provide support for a parameterisable engine. Parameters are keyed
 * with a type K whose type shall be P. Methods are to be implemented that
 * fetch engine parameters and allow updating of parameters.
 *
 * @author ian
 *
 * @param <K> - the type of the parameter key.
 * @param <P> - the type of the parameter value.
 */
public interface IParameterisableEngine<K, P> {
  /**
   * Return the current parameters for an engine.
   * 
   * @param session - the invoking session.
   * 
   * @return A map of parameters.
   * @throws Exception On an error.
   */
  Map<K, P> getParamters(ISession session) throws Exception;

  /**
   * Update the current parameters with the parameters provided. 
   *
   * @param session - the invoking session.
   * @param newParameters - the parameters used to update the current ones.
   * @throws Exception On an error.
   */
  void updateParameters(ISession session, Map<K, P> newParameters) throws Exception;
}
