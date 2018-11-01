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
 * Switch the display mode between text and grid
 * 
 * @author Dafan Yang
 */
public class TextModeDisplayAction extends Action implements IUpdate
{
    public TextModeDisplayAction()
    {
        super();
        setImageDescriptor(Images.DESC_TEXT_MODE);
        setToolTipText(Messages.TextModeDisplayAction_textmode_tip);
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.ACTION_TEXT_MODE_DISPLAY, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void run()
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        boolean isText = store.getInt(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE) == 1;
        // 1. text mode; 2. grid mode
        if(isText)
        {
            store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE, 2);
        }
        else
        {
            store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE, 1);
        }
    }

    public void update()
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        boolean isText = store.getInt(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE) == 1;
        setChecked(isText);
    }
}
