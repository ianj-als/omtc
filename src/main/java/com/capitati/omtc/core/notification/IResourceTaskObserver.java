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
package com.capitati.omtc.core.notification;

import com.capitati.omtc.core.resources.IResource;
import com.capitati.omtc.core.session.ISession;

/**
 * A generic resource task observer. On successful completion of a task the
 * onSuccess() method shall be called. Whereas, on an error the onError()
 * method shall be called. The onError() method provides an object, of type F,
 * shall be provided which is the object that was used to invoke the task.
 *
 * @author ianjohnson
 */
public interface IResourceTaskObserver {
  /**
   * Called on successful completion of a resource task.
   * 
   * @param notification
   * @param resource
   * @param invokingSession
   */
  void onSuccess(
      ISession invokingSession,
      IResource resource);

  /**
   * Called when a resource task has failed.
   * 
   * @param notification
   * @param invokingSession
   * @param exception
   */
  void onError(
      ISession session,
      IResource resource,
      Exception exception);
}
