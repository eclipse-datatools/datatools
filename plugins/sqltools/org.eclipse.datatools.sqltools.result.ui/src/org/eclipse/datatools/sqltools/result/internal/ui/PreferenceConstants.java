/*******************************************************************************
 * Copyright (c) 2005, 2010 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, 
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui;

import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;

/**
 * Preference page related constants
 * 
 * @author Dafan Yang
 */
public class PreferenceConstants
{
    /* Preference page id */
    public static final String PAGE_RESULT                             = "org.eclipse.datatools.sqltools.result.displayOption";
    public static final String PAGE_EXPORT                             = "org.eclipse.datatools.sqltools.result.exportFormat";
    public static final String PAGE_HISTORY                            = "org.eclipse.datatools.sqltools.result.resultHistory";
    public static final String PAGE_RESULTSETVIEWER                    = "org.eclipse.datatools.sqltools.result.resultSetViewerPreference";

    /* SQL Results View display options */
    public static final String SQL_RESULTS_VIEW_MAX_ROW_COUNT          = ResultsConstants.SQL_RESULTS_VIEW_MAX_ROW_COUNT;
    public static final String SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT  = ResultsConstants.SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT;

    public static final String PREFERENCE_PREFIX                       = ResultsViewPlugin.getPluginId();

    // 1: single window 2: multi-windows
    public static final String SQL_RESULTS_VIEW_DISPLAY_WINDOW         = PREFERENCE_PREFIX
                                                                               + ".preferences.display.displaywindow";
    // 1: text mode 2: grid mode
    public static final String SQL_RESULTS_VIEW_DISPLAY_MODE           = PREFERENCE_PREFIX
                                                                               + ".preferences.display.displaymode";
    public static final String SQL_RESULTS_VIEW_SHOW_HEADING           = PREFERENCE_PREFIX
                                                                               + ".preferences.display.showheadings";
    public static final String SQL_RESULTS_VIEW_SHOW_ROW_NUMBER        = PREFERENCE_PREFIX
                                                                               + ".preferences.display.showrownumber";
    public static final String SQL_RESULTS_VIEW_SHOW_ROW_COUNT_MSG     = PREFERENCE_PREFIX
                                                                               + ".preferences.display.showrowcountmsg";
    public static final String SQL_RESULTS_VIEW_NULL_STRING            = PREFERENCE_PREFIX
                                                                               + ".preferences.display.nulldisplaystr";
    public static final String SQL_RESULTS_VIEW_SPLIT_MESSAGES         = PREFERENCE_PREFIX
                                                                               + ".preferences.display.splitmessages";
    public static final String SQL_RESULTS_VIEW_TABS_NUMBER            = PREFERENCE_PREFIX
                                                                               + ".preferences.display.tabsnumber";
    public static final String SQL_RESULTS_VIEW_TABLES_LIMITATION      = PREFERENCE_PREFIX
                                                                               + ".preferences.display.tablesnumber.limitation";
    public static final String VERTICAL_LAYOUT_RESULTS_VIEW            = PREFERENCE_PREFIX
                                                                               + ".preferences.results.vertical";
    public static final String SQL_RESULT_VIEW_SHOW_LABELS			   = PREFERENCE_PREFIX
        																		   + ".preferences.display.showlabels";

    /* Export format options */
    public static final String EXPORT_FORMAT_ADD_XML_HEADER            = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.addxmlheader";
    public static final String EXPORT_FORMAT_XML_HEADER                = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.xmlheader";
    public static final String EXPORT_FORMAT_ADD_XML_ROOT_TAG          = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.addxmlroottag";
    public static final String EXPORT_FORMAT_XML_ROOT_TAG              = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.xmlroottag";
    public static final String EXPORT_FORMAT_OUTPUT_FORMAT             = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.outputformat";
    public static final String EXPORT_FORMAT_DELIMITER                 = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.delimiter";
    public static final String EXPORT_FORMAT_DEFAULT_ENCODEING         = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.defalut";
    public static final String EXPORT_FORMAT_OTHER_ENCODEING           = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.other";
    public static final String EXPORT_FORMAT_OTHER_ENCODEING_SELECTION = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.other.selection";
    public static final String EXPORT_FORMAT_PREF_ENCODING             = PREFERENCE_PREFIX
                                                                               + ".preferences.exportformat.pref_encoding";
    /* Connection profile filters */

    public static final String PROFILE_FILTERS_STATUS_SUCCESS          = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.success";
    public static final String PROFILE_FILTERS_STATUS_FAILED           = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.failed";
    public static final String PROFILE_FILTERS_STATUS_TERMINATED       = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.terminated";
    public static final String PROFILE_FILTERS_STATUS_WARNING          = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.warning";
    public static final String PROFILE_FILTERS_STATUS_CRITICAL         = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.critical";
    public static final String PROFILE_FILTERS_LIMIT_CHECK             = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.limitcheck";
    public static final String PROFILE_FILTERS_LIMIT_NUM               = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.limitnum";
    public static final String PROFILE_FILTERS_UNKNOWNPROFILE          = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.unknownProfile";
    public static final String PROFILE_FILTERS_PROFILE_MAY_CHANGED     = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.profileFilter.change";
    public static final String PROFILE_FILTERS_FILTERED_PROFILES       = PREFERENCE_PREFIX
                                                                               + ".ResultsFilterDialog.filtered.profiles";

    /* Result history display options */
    public static final String RESULT_HISTORY_ALL_COLUMNS              = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.allcolumns";

    public static final String RESULT_HISTORY_STATUS_COLUMN            = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.status";
    public static final String RESULT_HISTORY_OPER_COLUMN              = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.operation";
    public static final String RESULT_HISTORY_FREQ_COLUMN              = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.frequency";
    public static final String RESULT_HISTORY_DATE_COLUMN              = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.date";
    public static final String RESULT_HISTORY_ACTION_COLUMN            = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.action";
    public static final String RESULT_HISTORY_CONSUMER_COLUMN          = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.consumer";
    public static final String RESULT_HISTORY_PROFILE_COLUMN           = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.profile";
    public static final String RESULT_HISTORY_SAVE_HISTORY             = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.autosave";
    public static final String RESULT_HISTORY_CLEAN_HISTORY            = PREFERENCE_PREFIX
                                                                               + ".preferences.resulthistory.autoclean";

    /* Result Set viewer options */
    public static final String RESULT_SET_VIEWER_VIEWERNAME            = PREFERENCE_PREFIX
                                                                               + ".preferences.resultsetviewer.viewername";
    /* Parameter viewer options */
    public static final String PARAMETER_VIEWER_VIEWERNAME             = PREFERENCE_PREFIX
                                                                               + ".preferences.parameterviewer.viewername";
    
    public static final String ELLIPSIS_ENABLED_VALUE_LENGTH           = PREFERENCE_PREFIX
                                                                               + ".preferences.parameterviewer.maxlength";

}
