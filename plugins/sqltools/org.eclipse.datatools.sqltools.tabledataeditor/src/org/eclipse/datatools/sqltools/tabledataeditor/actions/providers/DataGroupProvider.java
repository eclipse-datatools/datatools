/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.tabledataeditor.actions.providers;

import org.eclipse.datatools.sqltools.internal.tabledataeditor.util.ResourceLoader;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.navigator.CommonActionProvider;

public class DataGroupProvider extends CommonActionProvider
{
    public static final String DATA_MENU_ID = "org.eclipse.datatools.connectivity.sqm.server.ui.data"; //$NON-NLS-1$
    private static final String DATA_MENU = ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.EXPLORER.DATA");//$NON-NLS-1$ 
    private static final String EDIT_GROUP = "group.edit"; //$NON-NLS-1$

    public void fillContextMenu(IMenuManager menu)
    {
        menu.appendToGroup(EDIT_GROUP, new MenuManager(DATA_MENU, DATA_MENU_ID));
    }
}
