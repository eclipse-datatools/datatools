/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;


/**
 * This interface defines all public constants for the SQL Editor plug-in.
 * @author Hui Cao
 */

public interface EditorConstants
{
    public static final int      CP_STATUS_OTHER                  = 0;
    public static final int      CP_STATUS_DELETED                = 1;
    public static final int      CP_STATUS_DISCONNECTED           = 2;
    public static final int      CP_STATUS_RENAMED                = 3;
    public static final int      CP_STATUS_PROP_CHANGED           = 4;
    public static final int      CP_STATUS_CONNECTED              = 5;


	/**
	 * Marker type contant for SQL portability targets.
	 */
    public static final String PORTABILITY_MARKER_TYPE = SQLEditorPlugin.PLUGIN_ID + ".portabilitytask";           //$NON-NLS-1$
    /**
     * Marker type contant for SQL syntax errors.
     */
    public static final String SYNTAX_MARKER_TYPE      = SQLEditorPlugin.PLUGIN_ID + ".syntaxproblem";             //$NON-NLS-1$
    
    /**
     * Data source explorer view id
     */
	public static String DATA_SOURCE_EXPLORER = "org.eclipse.datatools.connectivity.DataSourceExplorerNavigator";
	
	/**
	 * Results view id
	 */
	public static String RESULTS_VIEW = "org.eclipse.datatools.sqltools.result.resultView";
	
	/**
	 * Error Log view id
	 */
	public static String LOG_VIEW = "org.eclipse.pde.runtime.LogView";

	/**
	 * The length of SQL statement short description 
	 */
    public static final int    SQL_SHORT_DESC_LENGTH   = 40;
    
    public static final String EDITOR_ID = "org.eclipse.datatools.sqltools.sqleditor.SQLEditor";

	public static final String ROUTINE_EDITOR_ID = "org.eclipse.datatools.sqltools.routineeditor.internal.RoutineEditor";

	public static final String SQLFILE_EDITOR_ID = "org.eclipse.datatools.sqltools.sqlscrapbook.SQLScrapbookEditor";

}