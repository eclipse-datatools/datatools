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
package org.eclipse.datatools.sqltools.result;

import com.ibm.icu.text.DateFormat;

/**
 * The constants
 * 
 * @author Dafan Yang
 */
public class ResultsConstants
{
    private static final String PLUGIN_ID              = ResultsViewPlugin.getPluginId();
    public static final String SQL_RESULTS_VIEW_ID    = "org.eclipse.datatools.sqltools.result.resultView";
//    public static final String PLUGIN_RESOURCE_BUNDLE = PLUGIN_ID + ".PluginResources";

    // the formater to format the data time (using current default pattern)
    public static DateFormat   FORMATTER              = DateFormat.getDateTimeInstance();

    public static final String DB_CP_CATEGORY         = "org.eclipse.datatools.connectivity.db.category";
    
    // extension point
    public static final String RE_EXECUTION_POINT_ID         = "reExecuteScript";
    public static final String EXTENSION_POINT_DATABASE_ID   = "database_id";
    public static final String EXTENSION_POINT_CONSUMER_NAME = "consumer_name";
    public static final String EXTENSION_POINT_CLASS_NAME    = "class";
    
    // preference key
    public static final String SQL_RESULTS_VIEW_MAX_ROW_COUNT          = ResultsConstants.PLUGIN_ID
                                                                               + ".preferences.maxrowcount";
    public static final String SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT  = ResultsConstants.PLUGIN_ID
                                                                               + ".preferences.maxdisplayrowcount";
}
