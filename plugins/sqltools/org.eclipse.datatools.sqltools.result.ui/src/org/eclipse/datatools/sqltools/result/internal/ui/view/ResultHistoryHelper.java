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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    
    public static final Map COLUMN_PREFERENCE_INDEX_MAP = new HashMap();
    public static final Map COLUMN_PREFERENCE_ORDER_MAP = new HashMap();
    public static final Map COLUMN_NAME_PREFERENCE_MAP = new HashMap();
    
    public static String[] COLUMN_PREFERENCE = {
        PreferenceConstants.RESULT_HISTORY_STATUS_COLUMN,
        PreferenceConstants.RESULT_HISTORY_OPER_COLUMN,
        PreferenceConstants.RESULT_HISTORY_FREQ_COLUMN,
        PreferenceConstants.RESULT_HISTORY_DATE_COLUMN,
        PreferenceConstants.RESULT_HISTORY_ACTION_COLUMN,
        PreferenceConstants.RESULT_HISTORY_CONSUMER_COLUMN,
        PreferenceConstants.RESULT_HISTORY_PROFILE_COLUMN
    };
    
    public static String[] COLUMN_NAME = {
        Messages.ResultHistorySection_status,
        Messages.ResultHistorySection_operation,
        Messages.ResultHistorySection_frequency,
        Messages.ResultHistorySection_date,
        Messages.ResultHistorySection_action_type,
        Messages.ResultHistorySection_consumer_name,
        Messages.ResultHistorySection_connection_profile
    };
    
    static
    {
        for(int i = 0; i < COLUMN_PREFERENCE.length; i++)
        {
            COLUMN_PREFERENCE_INDEX_MAP.put(COLUMN_PREFERENCE[i], new Integer(i));
        }
        
        for(int i = 0; i < COLUMN_NAME.length; i++)
        {
            COLUMN_NAME_PREFERENCE_MAP.put(COLUMN_NAME[i], COLUMN_PREFERENCE[i]);
        }
    }
    
    public static void refreshOrderFromPreference(boolean usePreference)
    {
        COLUMN_PREFERENCE_ORDER_MAP.clear();
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        
        for(int i = 0; i < COLUMN_PREFERENCE.length; i++)
        {
            int order = PreferenceUtil.getInt(store, COLUMN_PREFERENCE[i], usePreference);
            
            COLUMN_PREFERENCE_ORDER_MAP.put(COLUMN_PREFERENCE[i], new Integer(order));
        }
    }
    
    
    /**
     * Returns the configurable column index at the given display index
     * @param order the display index
     * @param usePreference whether to use the preference or the default for the preference
     * @return the configurable column index (There are 7 colums altogether)
     */
    public static int getConfigurableColumnIndex(int order, boolean usePreference)
    {
        if(order < 0 || order > 6)
        {
            return -1;
        }
        
        refreshOrderFromPreference(usePreference);
        String preferenceString = null;
        
        for(Iterator iter = COLUMN_PREFERENCE_ORDER_MAP.keySet().iterator(); iter.hasNext();)
        {
            Object obj = iter.next();
            if(((Integer)COLUMN_PREFERENCE_ORDER_MAP.get(obj)).intValue() == order)
            {
                preferenceString = (String)obj;
                break;
            }
        }
        
        if(preferenceString != null)
        {
            return ((Integer)COLUMN_PREFERENCE_INDEX_MAP.get(preferenceString)).intValue();
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
        refreshOrderFromPreference(true);
        
        Object obj = COLUMN_NAME_PREFERENCE_MAP.get(columnName);
        
        if(obj != null && COLUMN_PREFERENCE_ORDER_MAP.get(obj) != null)
        {
            return ((Integer)COLUMN_PREFERENCE_ORDER_MAP.get(obj)).intValue();
        }
        
        return -1;
    }
    
    /**
     * Returns the total column number need to be displayed
     * @param usePreference whether to use the preference or the default preference
     * @return the total column number need to be displayed
     */
    public static int getColumnNumber(boolean usePreference)
    {
        refreshOrderFromPreference(usePreference);
        
        int count = 0;
        
        for(Iterator iter = COLUMN_PREFERENCE_ORDER_MAP.keySet().iterator(); iter.hasNext();)
        {
            Object preference = iter.next();
            if(((Integer) COLUMN_PREFERENCE_ORDER_MAP.get(preference)).intValue() >= 0)
            {
                count++;
            }
        }
        
        return count;
    }
}
