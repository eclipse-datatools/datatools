/*******************************************************************************
 * Copyright (c) 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.server.internal.ui.services;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.actions.popup.RefreshAction;
import org.eclipse.jface.viewers.StructuredSelection;


/**
 * @author ljulien
 */
public class ServerExplorerRefreshService implements IServerExplorerRefreshService
{
    public void refresh(Object objectToRefresh)
    {
        RefreshAction action = new RefreshAction();
        action.selectionChanged(null, new StructuredSelection(objectToRefresh));
        action.run(null);
    }
}