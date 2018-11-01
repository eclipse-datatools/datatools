/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal;


/**
 * Preference constants used in the SQL Editor preference store. 
 * 
 * @author Hui Cao
 * 
 */
public class PreferenceConstants
{

    // begin: preference page ids
    public static final String PAGE_GENERAL                            = SQLEditorPlugin.PLUGIN_ID + ".GeneralPreferencePage";
    public static final String PAGE_LOGGING                            = SQLEditorPlugin.PLUGIN_ID + ".LoggingPreferencePage";
    public static final String PAGE_TEMPLATE                           = SQLEditorPlugin.PLUGIN_ID + ".TemplatesPreferencePage";
    public static final String PAGE_PERSPECTIVE                        = SQLEditorPlugin.PLUGIN_ID + ".PerspectivePage";
    public static final String PAGE_SQLFILE                            = SQLEditorPlugin.PLUGIN_ID + ".sqlfile";
    public static final String PAGE_CODEASSIST                         = SQLEditorPlugin.PLUGIN_ID + ".codeassist";
    public static final String PAGE_EXPORT                             = SQLEditorPlugin.PLUGIN_ID + ".ExportFormatPreferencePage";
    public static final String PAGE_SQLEDITOR                          = SQLEditorPlugin.PLUGIN_ID + ".SQLEditor";
    public static final String PAGE_SQLDEBUG                           = SQLEditorPlugin.PLUGIN_ID + ".SQLDebug";
    public static final String PAGE_RESULT                             = SQLEditorPlugin.PLUGIN_ID + ".sqlresultsview";
    public static final String PAGE_CONNECTIONOPTIONS                  = SQLEditorPlugin.PLUGIN_ID + ".connectionleveloptions";
    public static final String PAGE_MISC                               = SQLEditorPlugin.PLUGIN_ID + ".miscpage";
    public static final String PAGE_PLAN                               = SQLEditorPlugin.PLUGIN_ID + ".queryplan";
    // end: preference page ids

    public static final String EXECUTE_SQL_ERROR_MODE = "PreferenceConstants.EXECUTE_SQL_ERROR_MODE";

	public static final String EXPLAIN_SQL_ERROR_MODE = "PreferenceConstants.EXPLAIN_SQL_ERROR_MODE";

	public static final String PROMPT_MODE_ALWAYS = "PreferenceConstants.PROMPT_MODE_ALWAYS";

	public static final String PROMPT_MODE_NEVER = "PreferenceConstants.PROMPT_MODE_NEVER";

	public static final String PROMPT_MODE_PROMPT = "PreferenceConstants.PROMPT_MODE_PROMPT";



    /**
	 * A named preference that defines whether hint to make hover sticky should
	 * be shown.
	 */
    public static final String EDITOR_SHOW_TEXT_HOVER_AFFORDANCE = "PreferenceConstants.EDITOR_SHOW_TEXT_HOVER_AFFORDANCE"; //$NON-NLS-1$

    /**
     * A named preference that defines what the target database type is in portability check.
     */
    public static final String EDITOR_PORTABILITY_CHECK_TARGET   = "PreferenceConstants.EDITOR_PORTABILITY_CHECK_TARGET";

    public static final String FAIL_TO_CONNECT_DATABASE                = "sqlfile.fail.to.connect.database";

    public static final String SHOW_SYSTEM_TABLES                  = "show.system.tables";

    public static final String SHOW_SYSTEM_PROCEDURES              = "show.system.procedures";

    public static final String SHOW_OWNER_OF_TABLE                 = "show.owner.of.table";

    public static final String SHOW_SYSTEM_VIEWS                   = "show.system.views";

    public static final String INSERT_SINGLE_PROPOSALS_AUTO            = "insert.single.proposals.auto";

    public static final String ENABLE_AUTO_ACTIVATION                  = "enable.auto.activation";

    public static final String AUTO_ACTIVATION_DELAY                   = "auto.activation.delay";

    public static final String AUTO_ACTIVATION_TRIGGER                 = "auto.activation.trigger";

    // Syntax Validation
    public static final String SYNTAX_VALIDATION                       = "syntax.validation";

    public static final String SYNTAX_VALIDATION_MAX_LINE              = "syntax.validation.max.line";

    public static final String SYNTAX_VALIDATION_MAX_LINE_NUMBER       = "syntax.validation.max.line.number";

    public static final String SHOW_DAILOG_FOR_SYNTAX_VALIDATION       = "show.dailog.for.syntax.validation";
    
    public static final String SHOW_SYNTAX_ERROR_DETAIL                = "show.syntax.error.detail";

    // Typing constants
    public static final String SQLEDITOR_CLOSE_SINGLE_QUOTES           = "SQLEditor.closeSingleQuotes";

    public static final String SQLEDITOR_CLOSE_DOUBLE_QUOTES           = "SQLEditor.closeDoubleQuotes";

    public static final String SQLEDITOR_CLOSE_BRACKETS                = "SQLEditor.closeBrackets";

    public static final String SQLEDITOR_CLOSE_COMMENTS                = "SQLEditor.closeComments";

    public static final String SQLEDITOR_CLOSE_BEGIN_END               = "SQLEditor.closeBeginEndStatement";

    public static final String EXTERNAL_TOOL_CONFIGURED                = "external.tool.configured";

    public static final String EXECUTE_SELECTED_SQL					   = "SQLEditor.executeSelectedSQL";
    
    public static final String EXECUTE_SQL_BETWEEN_DELIMITER		   = "SQLEditor.executeSelectedSQL.betweenDelimiter";
    
    public static final String EXECUTE_SQL_DELIMITER_TYPE              = "SQLEditor.executeSelectedSQL.betweenDelimiter.delimiterType";
    
    public static final String EXECUTE_SQL_CURRENT_LINE				   = "SQLEditor.executeSelectedSQL.currentLine";
    
    public static final String EXECUTE_SQL_BETWEEN_BLANK_LINE		   = "SQLEditor.executeSelectedSQL.betweenBlankLine";

}
