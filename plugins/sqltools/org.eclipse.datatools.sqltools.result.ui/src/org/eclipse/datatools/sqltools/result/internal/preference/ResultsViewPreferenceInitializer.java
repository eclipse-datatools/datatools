/*******************************************************************************
 * Copyright (c) 2005, 2010 Sybase, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;

/**
 * The preference initializer for SQL Results View.
 * @author Dafan Yang
 */
public class ResultsViewPreferenceInitializer extends AbstractPreferenceInitializer
{

    public ResultsViewPreferenceInitializer()
    {
        super();
    }

    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        
        // Set default values of SQL Results View display options page
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW, 2);
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE, 2);
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_HEADING, true);
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_COUNT_MSG, true);
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_NUMBER, true);
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_MAX_ROW_COUNT, 500);
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT, 500);
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING, "NULL"); //$NON-NLS-1$
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_SPLIT_MESSAGES, true);
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_TABS_NUMBER, 50);
        store.setDefault(PreferenceConstants.SQL_RESULTS_VIEW_TABLES_LIMITATION, 50);
        store.setDefault(PreferenceConstants.VERTICAL_LAYOUT_RESULTS_VIEW, false);
        store.setDefault(PreferenceConstants.SQL_RESULT_VIEW_SHOW_LABELS, true);
        
        // Set default values of export format options page
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_ADD_XML_HEADER, true);
        String xmlHeaderFormat = Messages.ExportFormatPage_xmlexportformat_xmlheader_text; 
        String xmlHeader = NLS.bind(xmlHeaderFormat, new String[]
        {
            "UTF-8" //$NON-NLS-1$
            });        
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_XML_HEADER, xmlHeader);
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_ADD_XML_ROOT_TAG, true);
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_XML_ROOT_TAG, Messages.ExportFormatPage_xmlexportformat_xmlroottag_text); 
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_OUTPUT_FORMAT, 0);
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_DELIMITER, ";"); //$NON-NLS-1$
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_DEFAULT_ENCODEING, true);
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING, false);
        store.setDefault(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING_SELECTION, -1);
        
        // Set default values of result history options page
        store.setDefault(PreferenceConstants.RESULT_HISTORY_ALL_COLUMNS, false);
        
        store.setDefault(PreferenceConstants.RESULT_HISTORY_STATUS_COLUMN, 0);
        store.setDefault(PreferenceConstants.RESULT_HISTORY_OPER_COLUMN, 1);
        store.setDefault(PreferenceConstants.RESULT_HISTORY_FREQ_COLUMN, -1);
        store.setDefault(PreferenceConstants.RESULT_HISTORY_DATE_COLUMN, 2);
        store.setDefault(PreferenceConstants.RESULT_HISTORY_ACTION_COLUMN, -1);
        store.setDefault(PreferenceConstants.RESULT_HISTORY_CONSUMER_COLUMN, -1);
        store.setDefault(PreferenceConstants.RESULT_HISTORY_PROFILE_COLUMN, 3);
        
        store.setDefault(PreferenceConstants.RESULT_HISTORY_SAVE_HISTORY, true);
        store.setDefault(PreferenceConstants.RESULT_HISTORY_CLEAN_HISTORY, false);
        
        // Results filters        
        store.setDefault(PreferenceConstants.PROFILE_FILTERS_STATUS_SUCCESS, true);
        store.setDefault(PreferenceConstants.PROFILE_FILTERS_STATUS_FAILED, true);
        store.setDefault(PreferenceConstants.PROFILE_FILTERS_STATUS_TERMINATED, true);
        store.setDefault(PreferenceConstants.PROFILE_FILTERS_STATUS_WARNING, true);
        store.setDefault(PreferenceConstants.PROFILE_FILTERS_STATUS_CRITICAL, true);

        store.setDefault(PreferenceConstants.PROFILE_FILTERS_LIMIT_CHECK, false);
        store.setDefault(PreferenceConstants.PROFILE_FILTERS_LIMIT_NUM, 50);
        store.setDefault(PreferenceConstants.PROFILE_FILTERS_UNKNOWNPROFILE,false);
        
        // Set values of result set viewer
        store.setDefault(PreferenceConstants.RESULT_SET_VIEWER_VIEWERNAME, 
        		ResultSetViewerPreferencePage.DEFAULT_VIEWER);
        store.setValue(PreferenceConstants.RESULT_SET_VIEWER_VIEWERNAME, 
        		ResultSetViewerPreferencePage.getViewerNameFromExtension());
        
        // Set values of parameter viewer
        store.setDefault(PreferenceConstants.PARAMETER_VIEWER_VIEWERNAME, 
                ResultSetViewerPreferencePage.PARAM_DEFAULT_VIEWER);
        store.setValue(PreferenceConstants.PARAMETER_VIEWER_VIEWERNAME, 
                ResultSetViewerPreferencePage.getParameterViewerNameFromExtension());
        store.setDefault(PreferenceConstants.ELLIPSIS_ENABLED_VALUE_LENGTH, 60);
    }

}
