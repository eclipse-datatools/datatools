/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.datatools.sqltools.sqleditor.preferences.PreferenceMessages;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;

/**
 * Performs SQL Editor default preference value initialization.
 * @author Hui Cao
 *
 */
public class EditorPreferenceInitializer extends AbstractPreferenceInitializer
{

    /**
     * Initializes the preference controls to the default values. These values are used the first time the preference
     * page is displayed or when the user presses the Defaults button in the preferences page.
     */
    public void initializeDefaultPreferences()
    {
        IPreferenceStore store = SQLEditorPlugin.getDefault().getPreferenceStore();

        store.setDefault(PreferenceConstants.EDITOR_PORTABILITY_CHECK_TARGET, SQLEditorPlugin.getDefault().getProperties().getProperty(
            PreferenceConstants.EDITOR_PORTABILITY_CHECK_TARGET, PreferenceMessages.GeneralPreferencePage_portable_target));
        store.setDefault(PreferenceConstants.EDITOR_SHOW_TEXT_HOVER_AFFORDANCE, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.EDITOR_SHOW_TEXT_HOVER_AFFORDANCE, "true")).booleanValue()); //$NON-NLS-1$

        store.setDefault(PreferenceConstants.EXECUTE_SQL_ERROR_MODE, SQLEditorPlugin.getDefault().getProperties().getProperty(
            PreferenceConstants.EXECUTE_SQL_ERROR_MODE, PreferenceConstants.PROMPT_MODE_PROMPT));
        store.setDefault(PreferenceConstants.EXPLAIN_SQL_ERROR_MODE, SQLEditorPlugin.getDefault().getProperties().getProperty(
            PreferenceConstants.EXPLAIN_SQL_ERROR_MODE, PreferenceConstants.PROMPT_MODE_PROMPT));
        store.setDefault(PreferenceConstants.FAIL_TO_CONNECT_DATABASE, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.FAIL_TO_CONNECT_DATABASE, "true")).booleanValue()); //$NON-NLS-1$

        store.setDefault(PreferenceConstants.INSERT_SINGLE_PROPOSALS_AUTO, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.INSERT_SINGLE_PROPOSALS_AUTO, "true")).booleanValue());
        store.setDefault(PreferenceConstants.ENABLE_AUTO_ACTIVATION, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.ENABLE_AUTO_ACTIVATION, "true")).booleanValue());
        store.setDefault(PreferenceConstants.AUTO_ACTIVATION_DELAY, Integer.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.AUTO_ACTIVATION_DELAY, "500")).intValue());
        store.setDefault(PreferenceConstants.AUTO_ACTIVATION_TRIGGER, " .@(");
       
        //code assist
        store.setDefault(PreferenceConstants.SHOW_OWNER_OF_TABLE, false);

        //syntax validation
        store.setDefault(PreferenceConstants.SYNTAX_VALIDATION, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.SYNTAX_VALIDATION, "true")).booleanValue()); //$NON-NLS-1$
        store.setDefault(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE, "true")).booleanValue()); //$NON-NLS-1$
        store.setDefault(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE_NUMBER,Integer.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE_NUMBER,"1000")).intValue());
        store.setDefault(PreferenceConstants.SHOW_DAILOG_FOR_SYNTAX_VALIDATION, true); //$NON-NLS-1$

        // execute selected sql
        store.setDefault(PreferenceConstants.EXECUTE_SELECTED_SQL, SQLEditorPlugin.getDefault().getProperties()
                .getProperty(PreferenceConstants.EXECUTE_SELECTED_SQL, PreferenceConstants.EXECUTE_SQL_CURRENT_LINE));
        store.setDefault(PreferenceConstants.EXECUTE_SQL_DELIMITER_TYPE, SQLEditorPlugin.getDefault().getProperties()
                .getProperty(PreferenceConstants.EXECUTE_SQL_DELIMITER_TYPE, "GO"));

        //typing

        store.setDefault(PreferenceConstants.SQLEDITOR_CLOSE_SINGLE_QUOTES, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.SQLEDITOR_CLOSE_SINGLE_QUOTES, "true")).booleanValue()); //$NON-NLS-1$

        store.setDefault(PreferenceConstants.SQLEDITOR_CLOSE_DOUBLE_QUOTES, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.SQLEDITOR_CLOSE_DOUBLE_QUOTES, "true")).booleanValue()); //$NON-NLS-1$

        store.setDefault(PreferenceConstants.SQLEDITOR_CLOSE_BRACKETS, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.SQLEDITOR_CLOSE_BRACKETS, "true")).booleanValue()); //$NON-NLS-1$

        store.setDefault(PreferenceConstants.SQLEDITOR_CLOSE_COMMENTS, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.SQLEDITOR_CLOSE_COMMENTS, "true")).booleanValue()); //$NON-NLS-1$

        store.setDefault(PreferenceConstants.SQLEDITOR_CLOSE_BEGIN_END, Boolean.valueOf(
            SQLEditorPlugin.getDefault().getProperties().getProperty(PreferenceConstants.SQLEDITOR_CLOSE_BEGIN_END, "true")).booleanValue()); //$NON-NLS-1$


    }


}
