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
package com.capitati.omtc.core.session;

import com.capitati.omtc.core.resources.IResource;
import com.capitati.omtc.core.resources.IResourceReader;
import com.capitati.omtc.core.resources.IResourceWriter;

/**
 * The delegated resource transfer mechanism.
 * 
 * @author ianjohnson
 */
public interface IResourceTransferDelegate {
  /**
   * Constructs an instance of {@link com.capitati.omtc.core.resources.IResourceReader}
   * which is an object that is used to read chunks of data from a source
   * resource. Implementers of this method should take this opportunity to
   * open a resource where ever that may be, e.g., local disk, remote client.
   *
   * @param theInvokingSession - the session for whom this method is being called.
   * @return An implementation of the {@link com.capitati.omtc.core.resources.IResourceReader}
   * interface that shall provide data for a resource.
   * @throws Exception On any error encountered.
   */
  IResourceReader getResourceReader(ISession theInvokingSession) throws Exception;

  /**
   * Constructs an instance of {@link com.capitati.omtc.core.resources.IResourceWriter}
   * which is an object that is used to write chunks of data to a destination
   * resource. Implementers of this method should take this opportunity to
   * open a resource where ever it may needed to be, e.g., local disk, remote client.
   * 
   * @param theInvokingSession - the session for whom this method is being called.
   * @return An implementation of the {@link com.capitati.omtc.core.resources.IResourceWriter}
   * interface that is to handle the writing of resource data.
   * @throws Exception On any error encountered.
   */
  IResourceWriter getResourceWriter(ISession theInvokingSession) throws Exception;

  /**
   * Implementers of this method should return a {@link com.capitati.omtc.core.resources.IResource}
   * object that represents the resource that is begin transfered.
   * Please note: for documents, translation memories, and glossaries etc instances of
   * {@link com.capitati.omtc.core.resources.IPrimaryResource} should be
   * constructed. However, for resources that are derived, e.g., engines, then
   * instances of type {@link com.capitati.omtc.core.resources.IDerivedResource}
   * should be returned.
   *
   * @param theInvokingSession - the session that invoked the transfer action.
   * @return A {@link com.capitati.omtc.core.resources.IResource}
   * object that presents the resource uploaded
   */
  IResource getResource(ISession theInvokingSession);
}
