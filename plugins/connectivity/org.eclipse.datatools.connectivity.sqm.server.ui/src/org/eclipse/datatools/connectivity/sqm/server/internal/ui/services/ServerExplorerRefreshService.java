/*******************************************************************************
 * Copyright (c) 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.server.internal.ui.services;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.actions.popup.RevisedRefreshAction;
import org.eclipse.jface.viewers.StructuredSelection;


/**
 * @author ljulien
 */
public class ServerExplorerRefreshService implements IServerExplorerRefreshService
{
    public void refresh(Object objectToRefresh)
    {
    	RevisedRefreshAction action = new RevisedRefreshAction(null);
        action.selectionChanged(null, new StructuredSelection(objectToRefresh));
        action.run();
    }
}