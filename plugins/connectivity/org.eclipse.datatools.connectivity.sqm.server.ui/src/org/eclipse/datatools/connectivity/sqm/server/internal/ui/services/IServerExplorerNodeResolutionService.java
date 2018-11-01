/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.services;

import org.eclipse.emf.ecore.EObject;

/**
 * @author clare
 */
public interface IServerExplorerNodeResolutionService
{
    /**
     * Will return the EObject node in the Server Explorer
     * @param pathToNavigate - A string built to the requirements of the TransientEObjectUtil class
     */
    public EObject getEObjectNode(String pathToNavigate);
}
