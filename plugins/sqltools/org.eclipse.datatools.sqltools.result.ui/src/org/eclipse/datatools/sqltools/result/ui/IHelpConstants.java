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
package org.eclipse.datatools.sqltools.result.ui;

/**
 * 
 * This file contains all the context help ID that are used by following DTP plugins:
 * 
 * TPS_helpKey_constants_for_plug-in: org.eclipse.datatools.sqltools.result
 * 
 * @author bshen
 */
public interface IHelpConstants
{
    
    /**
     * Used for SQL result view.
     */
    public static final String VIEW_SQL_RESULT                              = "view_sql_result";

    /**
     * Used for SQL result view's SQL Results View preference page.
     */
    public static final String PREFERENCE_PAGE_SQL_RESULTS_VIEW              = "preference_page_sql_result_view";
    
    /**
     * Used for SQL result view's Export Format preference page.
     */
    public static final String PREFERENCE_PAGE_EXPORT_FORMAT                = "preference_page_export_format";
    
    /**
     * Used for SQL result view's History preference page.
     */
    public static final String PREFERENCE_PAGE_RESULT_HISTORY               = "preference_page_result_history";
    
    /**
     * Used for SQL result view's Re-execute action.
     */
    public static final String ACTION_REEXECUTE                             = "action_reexecute";
    
    /**
     * Used for SQL result view's Remove All action.
     */
    public static final String ACTION_REMOVE_ALL_VISIBLE_FINISHED_RESULT    = "action_remove_all_visible_finished_result";
    
    /**
     * Used for SQL result view's Remove action.
     */
    public static final String ACTION_REMOVE_RESULT                         = "action_remove_result";
    
    /**
     * Used for SQL result view's Save History action.
     */
    public static final String ACTION_SAVE_RESULT_INSTANCE                  = "action_save_result_instance";
    
    /**
     * Used for SQL result view's Terminate action.
     */
    public static final String ACTION_TERMINATE_INSTANCE                    = "action_terminate_instance";
    
    /**
     * Used for SQL result view's All Results action.
     */
    public static final String ACTION_EXPORT_ALL_RESULTSETS                 = "action_export_all_resultsets";
    
    /**
     * Used for SQL result view's Export Current Result action.
     */
    public static final String ACTION_EXPORT_RESULTSET                      = "action_export_resultset";
    
    /**
     * Used for SQL result view's Save All Results action.
     */
    public static final String ACTION_SAVE_ALL_RESULTSETS                   = "action_save_all_resultsets";
    
    /**
     * Used for SQL result view's Save Current Result action.
     */
    public static final String ACTION_SAVE_RESULTSET                        = "action_save_resultset";
    
    /**
     * Used for SQL result view's Print Current Result action.
     */
    public static final String ACTION_PRINT_RESULTSET                       = "action_print_resultset";
    
    /**
     * Used for SQL result view's Open Filters action.
     */
    public static final String ACTION_OPEN_FILTER_DIALOG                    = "action_open_filter_dialog";
    
    /**
     * Used for SQL result view's Display result in single tab action.
     */
    public static final String ACTION_SINGLE_TAB_DISPLAY                    = "action_single_tab_display";
    
    /**
     * Used for SQL result view's Display result in text mode action.
     */
    public static final String ACTION_TEXT_MODE_DISPLAY                     = "action_text_mode_display";
    
    /**
     * Used for SQL result view's Save All Results/ Save Result dialog.
     */
    public static final String DIALOG_SAVE_RESULTSET                        = "dialog_save_resultset";
    
    /**
     * Used for SQL result view's SQL Results Filters dialog.
     */
    public static final String DIALOG_RESULTS_FILTER                        = "dialog_results_filter";
    
    /**
     * Used for SQL result view's Export Result wizard.
     */
    public static final String WIZARD_RESULT_EXPORT                         = "wizard_result_export";
}
