/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.tabledataeditor.actions.providers;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.popup.AbstractAction;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.popup.providers.AbstractSubMenuActionProvider;
import org.eclipse.datatools.sqltools.tabledataeditor.actions.SampleContentAction;
import org.eclipse.jface.action.ActionContributionItem;

public class SampleContentProvider extends AbstractSubMenuActionProvider
{
    private static final AbstractAction action = new SampleContentAction();

    protected String getSubMenuId()
    {
        return DataGroupProvider.DATA_MENU_ID;
    }

    protected AbstractAction getAction()
    {
        return action;
    }

    protected ActionContributionItem getActionContributionItem()
    {
        return ITEM;
    }
}
