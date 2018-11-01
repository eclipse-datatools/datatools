/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * Switch the display mode between single tab and multiple tabs
 * 
 * @author Dafan Yang
 */
public class SingleTabDisplayAction extends Action implements IUpdate
{
    public SingleTabDisplayAction()
    {
        super();
        setImageDescriptor(Images.DESC_SINGLE_TAB);
        setToolTipText(Messages.SingleTabDisplayAction_single_tab);
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_SINGLE_TAB_DISPLAY, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void run()
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        boolean isSingle = store.getInt(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW) == 1;
        //1: single window 2: multi-windows
        if(isSingle)
        {
            store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW, 2);
        }
        else
        {
            store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW, 1);
        }
    }

    public void update()
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        boolean isSingle = store.getInt(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW) == 1;
        setChecked(isSingle);
    }
}
