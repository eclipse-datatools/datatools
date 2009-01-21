/**
 * Created on 2005-1-27
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.preferences;

import org.eclipse.osgi.util.NLS;

public final class PreferenceMessages extends NLS 
{

    private static final String BUNDLE_NAME = PreferenceMessages.class.getPackage().getName()+".PreferenceMessages";//$NON-NLS-1$

    private PreferenceMessages() 
    {
        // Do not instantiate
    }

    public static String CodeAssist_description;
    public static String CodeAssist_general_title;
    public static String CodeAssist_showSystemGroup;
    public static String CodeAssist_showSystemTables;
    public static String CodeAssist_showSystemProcedures;
    public static String CodeAssist_showOwnerOfTable;
    public static String CodeAssist_asa_tips;
    public static String CodeAssist_showSystemViews;
    public static String CodeAssist_asa_titile;
    public static String CodeAssist_insertSingleProposals;
    public static String CodeAssist_autoActivationGroup;
    public static String CodeAssist_enableAuto;
    public static String CodeAssist_autoActivationDelay;
    public static String CodeAssist_autoActivationTriggers;
    public static String SQLEditor_general_title;
    public static String SQLEditor_syntaxValidationGroup;
    public static String SQLEditor_syntaxValidation;
    public static String SQLEditor_syntaxValidation_tooltip;
    public static String SQLEditor_maxLineButton;
    public static String SQLEditor_maxLineButton_tooltip;
    public static String SQLEditor_showPromptDialog;
    public static String SQLEditor_showSyntaxErorrDetail;
    public static String SQLEditor_showSyntaxErorrDetail_tooltip;
    public static String SQLEditor_executeCurrentSQLGroup;
    public static String SQLEditor_executeSQLBetweenDelimiter;
    public static String SQLEditor_executeSQLBetweenDelimiter_tip;
    public static String SQLEditor_executeCurrentLine;
    public static String SQLEditor_executeCurrentLine_tip;
    public static String SQLEditor_executeSQLBetweenBlankLine;
    public static String SQLEditor_executeSQLBetweenBlankLine_tip;
    public static String SQLEditor_typing_title;
    public static String SQLEditor_typingAidsGroup_title;
    public static String SQLEditor_typingAidsTable_columnName1;
    public static String SQLEditor_typingAidsTable_columnName2;
    public static String SQLEditor_typingAidsTable_columnName3;
    public static String SQLEditor_closeSingleQuotes;
    public static String SQLEditor_closeSingleQuotes_description;
    public static String SQLEditor_closeSingleQuotes_preview;
    public static String SQLEditor_closeDoubleQuotes;
    public static String SQLEditor_closeDoubleQuotes_description;
    public static String SQLEditor_closeDoubleQuotes_preview;
    public static String SQLEditor_closeBrackets;
    public static String SQLEditor_closeBrackets_description;
    public static String SQLEditor_closeBrackets_preview;
    public static String SQLEditor_closeComments;
    public static String SQLEditor_closeComments_description;
    public static String SQLEditor_closeComments_preview;
    public static String SQLEditor_beginEndStatement;
    public static String SQLEditor_beginEndStatement_description;
    public static String SQLEditor_beginEndStatement_preview;
    public static String SQLEditor_typingAidsTable_context_all;
    public static String SQLEditor_typingAids_preview;
    public static String General_max_row_num;
    public static String General_invalid_int;
    public static String GeneralPreferencePage_portable_target;
    public static String GeneralPreferencePage_execute_error_mode;
    public static String GeneralPreferencePage_execute_always;
    public static String GeneralPreferencePage_execute_never;
    public static String GeneralPreferencePage_execute_prompt;
    public static String GeneralPreferencePage_savebeforerenaming_prompt;
    public static String GeneralPreferencePage_savebeforerenaming_always;
    public static String GeneralPreferencePage_title;
    public static String GeneralPreferencePage_description;
    public static String GeneralPreferencePage_hover_affordance;
    public static String GeneralPreferencePage_portable_check;
	public static String SyntaxColoringPage_description;
	public static String SyntaxColoringPage_color;
	public static String SyntaxColoringPage_bold;
	public static String SyntaxColoringPage_italic;
	public static String SyntaxColoringPage_strikethrough;
	public static String SyntaxColoringPage_underline;
	public static String SyntaxColoringPage_preview;
	public static String SyntaxColoringPage_syntax_items;
	public static String SyntaxColoringPage_default_foreground_color;
	
    static 
    {
        NLS.initializeMessages(BUNDLE_NAME, PreferenceMessages.class);
    }
}
