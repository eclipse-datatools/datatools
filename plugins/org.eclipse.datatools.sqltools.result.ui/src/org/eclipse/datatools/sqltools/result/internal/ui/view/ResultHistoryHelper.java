/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.PreferenceUtil;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Result history related utilities
 * @author Dafan Yang
 */
public class ResultHistoryHelper
{

    /* Totally, there are 7 columns which users can configure to display or not */
    public static final int COLUMN_NUMBER  = 7;
    public static final int STATUS_INDEX   = 0;
    public static final int OPER_INDEX     = 1;
    public static final int FREQ_INDEX     = 2;
    public static final int DATE_INDEX     = 3;
    public static final int ACTION_INDEX   = 4;
    public static final int CONSUMER_INDEX = 5;
    public static final int PROFILE_INDEX  = 6;
    
    /**
     * Returns the configurable column index at the given display index
     * @param index the display index
     * @param usePreference whether to use the preference or the default for the preference
     * @return the configurable column index (There are 7 colums altogether)
     */
    public static int getConfigurableColumnIndex(int index, boolean usePreference)
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        Boolean[] isDisplayed = new Boolean[7];
        
        isDisplayed[STATUS_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_STATUS_COLUMN, usePreference));
        isDisplayed[OPER_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_OPER_COLUMN, usePreference));
        isDisplayed[FREQ_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_FREQ_COLUMN, usePreference));
        isDisplayed[DATE_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_DATE_COLUMN, usePreference));
        isDisplayed[ACTION_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_ACTION_COLUMN, usePreference));
        isDisplayed[CONSUMER_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_CONSUMER_COLUMN, usePreference));
        isDisplayed[PROFILE_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_PROFILE_COLUMN, usePreference));
        
        int count = 0;
        for (int i = 0; i < COLUMN_NUMBER; i++)
        {
            if(isDisplayed[i].booleanValue())
            {   
                if(count == index)
                {
                    return i;
                }
                count ++;
            }
        }
        return -1;
    }
    
    /**
     * Returns the configurable column index based on the column name
     * 
     * @param columnName the column name
     * @return the configurable column index (There are 7 colums altogether)
     */
    public static int getConfigurableColumnIndex(String columnName)
    {
        if (columnName.equals(Messages.ResultHistorySection_status))
        {
            return 0;
        }
        else if (columnName.equals(Messages.ResultHistorySection_operation))
        {
            return 1;
        }
        else if (columnName.equals(Messages.ResultHistorySection_frequency))
        {
            return 2;
        }
        else if (columnName.equals(Messages.ResultHistorySection_date))
        {
            return 3;
        }
        else if (columnName.equals(Messages.ResultHistorySection_action_type))
        {
            return 4;
        }
        else if (columnName.equals(Messages.ResultHistorySection_consumer_name))
        {
            return 5;
        }
        else if (columnName.equals(Messages.ResultHistorySection_connection_profile))
        {
            return 6;
        }
        else
        {
            return -1;
        }
    }
    
    /**
     * Returns the total column number need to be displayed
     * @param usePreference whether to use the preference or the default preference
     * @return the total column number need to be displayed
     */
    public static int getColumnNumber(boolean usePreference)
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        Boolean[] isDisplayed = new Boolean[COLUMN_NUMBER];
        
        isDisplayed[STATUS_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_STATUS_COLUMN, usePreference));
        isDisplayed[OPER_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_OPER_COLUMN, usePreference));
        isDisplayed[FREQ_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_FREQ_COLUMN, usePreference));
        isDisplayed[DATE_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_DATE_COLUMN, usePreference));
        isDisplayed[ACTION_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_ACTION_COLUMN, usePreference));
        isDisplayed[CONSUMER_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_CONSUMER_COLUMN, usePreference));
        isDisplayed[PROFILE_INDEX] = new Boolean(PreferenceUtil.getBoolean(store, PreferenceConstants.RESULT_HISTORY_PROFILE_COLUMN, usePreference));
        
        int count = 0;
        for (int i = 0; i < COLUMN_NUMBER; i++)
        {
            if(isDisplayed[i].booleanValue())
            {   
                count ++;
            }
        }
        return count;
    }
}
