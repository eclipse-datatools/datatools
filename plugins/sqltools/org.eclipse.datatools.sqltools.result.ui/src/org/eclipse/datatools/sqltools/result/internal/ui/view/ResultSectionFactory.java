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

import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.PreferenceUtil;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

/**
 * Factory to create the result section instance
 * 
 * @author Dafan Yang
 */
public class ResultSectionFactory
{
    private static final int   MULTI_GRID             = 1;
    private static final int   MULTI_TEXT             = 2;
    private static final int   SINGLE_GRID            = 3;
    private static final int   SINGLE_TEXT            = 4;

    /**
     * Returns a result set section according current setting
     * @param comp the parent composite
     * @return an instance of <code>ResultSection</code>
     */
    public static ResultSection createResultSection(Composite comp, ResultsViewControl resultsViewControl)
    {
        int mode = getDisplayMode(resultsViewControl);
        ResultSection resultSection = null;
        switch (mode)
        {
            case MULTI_GRID:
                resultSection = new MultipleTabsGridSection(comp, resultsViewControl);
                break;
            case MULTI_TEXT:
                resultSection = new MultipleTabsTextSection(comp, resultsViewControl);
                break;
            case SINGLE_GRID:
                resultSection = new SingleWindowGridSection(comp, resultsViewControl);
                break;
            case SINGLE_TEXT:
                resultSection = new SingleWindowTextSection(comp, resultsViewControl);
                break;
            default:
                break;
        }
        return resultSection;
    }

    /**
     * Returns the display mode combination from the preference store
     *  
     *  @param resultsViewControl the ResultsViewControl
     * @return the display mode
     */
    private static int getDisplayMode(ResultsViewControl resultsViewControl)
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        int windows = PreferenceUtil.getInt(store, PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW, resultsViewControl.getUsePreferences());
        int mode = PreferenceUtil.getInt(store, PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE, resultsViewControl.getUsePreferences());
        if ((windows == 1) && (mode == 1))
        {
            return SINGLE_TEXT;
        }
        if ((windows == 1) && (mode == 2))
        {
            return SINGLE_GRID;
        }
        if ((windows == 2) && (mode == 1))
        {
            return MULTI_TEXT;
        }
        if ((windows == 2) && (mode == 2))
        {
            return MULTI_GRID;
        }
        return MULTI_GRID;
    }
}
