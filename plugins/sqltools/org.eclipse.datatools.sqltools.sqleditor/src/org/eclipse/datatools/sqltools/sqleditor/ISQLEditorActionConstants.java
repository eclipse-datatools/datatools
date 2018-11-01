/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

/**
 * This interface defines the action constants used in SQL Editor.
 * @author Li Huang
 * 
 */
public interface ISQLEditorActionConstants extends ITextEditorActionConstants {
	/**
	 * Context menu group for execute actions. Value:
	 * <code>"group.sqleditor.execute"</code>
	 */
	static final String GROUP_SQLEDITOR_EXECUTE = "group.sqleditor.execute"; //$NON-NLS-1$

    /**
     * Context menu group for routine object creation wizard actions. 
     */
	static final String GROUP_SQLEDITOR_WIZARD = "group.sqleditor.wizard";

    /**
     * Context menu group for save actions. 
     */
	static final String GROUP_SQLEDITOR_SAVE = "group.sqleditor.save";

    /**
     * Context menu group for statement insertion actions, such as S/I/U/D statement wizards. 
     */
	static final String GROUP_SQLEDITOR_PASTE = "group.sqleditor.paste";

    /**
     * Context menu group for SQL source actions. 
     */
	static final String GROUP_SQLEDITOR_SOURCE = "group.sqleditor.source";

    /**
     * Context menu group for additional actions. 
     */
	static final String GROUP_SQLEDITOR_ADDITION = "group.sqleditor.addition";

    /**
     * Context menu group for open actions. 
     */
	static final String GROUP_OPEN = "group.sqleditor.open";//$NON-NLS-1$

	/**
	 * Context menu group for database specific actions.
	 */
	static final String GROUP_SQLEDITOR_DB_SUBMENU = "group.sqleditor.databases";

    /**
     * Context menu group for SQL Editor preference action.
     */
	static final String GROUP_SQLEDITOR_PREFERENCE = "group.sqleditor.preference";

    /**
     * Context menu id for content assist action.
     */
	public static final String CONTENT_ASSIST_ACTION_ID = "org.eclipse.ui.edit.text.contentAssist.proposals"; //$NON-NLS-1$

    /**
     * Context menu id for SQL query plan retrieval action.
     */
	public static final String EXPLAIN_SQL_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".ExplainSQLAction"; //$NON-NLS-1$

    /**
     * Context menu id for sql execution action.
     */
	public static final String EXECUTE_SQL_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".ExecuteSQLAction"; //$NON-NLS-1$

    /**
     * Context menu id for selected sql execution action.
     */
	public static final String EXECUTE_SELECTION_SQL_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".ExecuteSelectionAction";
	
	/**
	 * Context menu id for execute selected sql as one statement action. 
	 */
	public static final String EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".ExecuteAsOneStatementAction";

	/**
	 * Context menu id for execute current sql action.
	 */
	public static final String EXECUTE_CURRENT_SQL_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".ExecuteCurrentAction";
	
    /**
     * Context menu id for selected sql edit in DMLDIALOG action.
     */
	public static final String DMLDIALOG_SELECTION_SQL_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".DMLDialogSelectionAction";

    /**
     * Context menu id for show information action.
     */
	public static final String SHOW_INFORMATION_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".show.sql.info"; //$NON-NLS-1$

    /**
     * Context menu manager id for routine object creation actions.
     */
	public static final String GROUP_NEW_ROUTINE_ID = SQLEditorPlugin.PLUGIN_ID + ".newRoutineActions";

    /**
     * Context menu id for event creation action.
     */
	public static final String NEW_EVENT_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".newEventAction";

    /**
     * Context menu id for function creation action.
     */
	public static final String NEW_FUNCTION_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".newFunctionAction";

    /**
     * Context menu id for stored procedure creation action.
     */
	public static final String NEW_PROCEDURE_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".newProcedureAction";

    /**
     * Context menu id for trigger creation action.
     */
	public static final String NEW_TRIGGER_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".newTriggerAction";

    /**
     * Context menu manager id for query creation wizard actions.
     */
	public static final String GROUP_INSERT_QUERY_ID = SQLEditorPlugin.PLUGIN_ID + ".insertQueryActions";

    /**
     * Context menu id for select statement creation action.
     */
	public static final String PASTE_SELECT_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".pasteSelectAction";

    /**
     * Context menu id for update statement creation action.
     */
	public static final String PASTE_UPDATE_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".pasteUpdateAction";

    /**
     * Context menu id for insert statement creation action.
     */
	public static final String PASTE_INSERT_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".pasteInsertAction";

    /**
     * Context menu id for delete statement creation action.
     */
	public static final String PASTE_DELETE_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".pasteDeleteAction";

    /**
     * Context menu id for save to database action.
     */
	public static final String SAVE_TO_DATABASE_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".saveToDatabaseAction";

    /**
     * Context menu id for refresh from database action.
     */
	public static final String REFRESH_FROM_DATABASE_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".refreshFromDatabaseAction";

    /**
     * Context menu id for attach connection profile action.
     */
	public static final String ATTACHE_PROFILE_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".attachProfileAction";

    /**
     * Context menu id for toggle comment action.
     */
	public static final String TOGGLE_COMMENT = SQLEditorPlugin.PLUGIN_ID + ".toggleCommentAction";

    /**
     * Context menu id for the standard workbench save as menu.
     */
	public static final String SAVE_AS_ACTION_ID = "org.eclipse.ui.file.saveAs";

	/**
	 * Context menu id for save selected text as template action.
	 */
	public static final String SAVE_AS_TEMPLATE_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".saveAsTemplateAction";
	
    /**
     * Context menu id for run routine action.
     */
	public static final String RUN_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".runAction";

    /**
     * Context menu id for debug routine action.
     */
	public static final String DEBUG_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".debugAction";

    /**
     * Context menu id for open declaration action.
     */
	public static final String OPEN_DECLARATION_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".openDeclarationAction";

    /**
     * Context menu id for rename action.
     */
	public static final String RENAME_ACTION_ID = SQLEditorPlugin.PLUGIN_ID
			+ ".RenameAction";
    
    /**
     * Context menu id for create database action.
     */
    public static final String CREATE_DATABASE_ACTION_ID = SQLEditorPlugin.PLUGIN_ID
            + ".CreateDBAction";
    
    /**
     * Context menu id for goto matching token action.
     */
    public static final String GOTO_MATCHING_TOKEN_ACTION_ID = SQLEditorPlugin.PLUGIN_ID + ".GotoMatchingTokenAction";
}
