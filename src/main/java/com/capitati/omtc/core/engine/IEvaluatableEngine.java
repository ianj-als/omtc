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
import com.capitati.omtc.core.scheduling.EvaluationTicket;
import com.capitati.omtc.core.scheduling.IPriority;
import com.capitati.omtc.core.scheduling.ITicketObserver;
import com.capitati.omtc.core.session.ISession;

/**
 * A mixin to provide support for evaluating an engine. Implementers are to
 * mixin this interface with their implementation of
 * {@link com.capitati.omtc.core.engine.IEngine}.
 * 
 * @author ian
 *
 * @param <V> - the task's priority type.
 */
public interface IEvaluatableEngine<V> extends ITicketableEngine<V> {
  /**
   * Evaluate an engine. On completion of this the evaluation task the
   * implementation should store the scores calculated so that
   * {@link com.capitati.omtc.core.engine.IEvaluatableEngine#getScores} can
   * return appropriate data.
   *
   * @param session - the invoking session.
   * @param evaluationResources - the primary resources to use for evaluation.
   * @param priority - the priority of the task.
   * @param engineObserver - the observer to listen for evaluation completion.
   * @return An evaluation ticket.
   * @throws Exception On an error.
   */
  EvaluationTicket<V> evaluate(
      ISession session,
      Set<IPrimaryResource> evaluationResources,
      IPriority<V> priority,
      ITicketObserver<EvaluationTicket<V>, V> engineObserver) throws Exception;

  /**
   * Retrieve any scores associated with an engine. The method
   * {@link com.capitati.omtc.core.engine.IEvaluatableEngine#evaluate}
   * should provided information, internally, to enable this method to
   * return the scores calculated during a call, and subsequent calls, to
   * {@link com.capitati.omtc.core.engine.IEvaluatableEngine#evaluate}.
   * Without running an evaluation, it is implementation defined whether,this
   * method returns an empty set.
   *
   * @param session - the invoking session.
   * @return A {@link java.util.Set} of
   * {@link com.capitati.omtc.core.engine.IScore} objects the represent the
   * scores from an evaluation.
   */
  Set<IScore> getScores(ISession session) throws Exception;
}
