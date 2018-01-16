/**
 * Created on Jan 20, 2009
 * 
 * Copyright (c) Sybase, Inc. 2004-2009. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.actions.selection;

import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * This is the factory to get text selection policy for executing "execute selected text" action without selection text.
 * 
 * @author sul
 * 
 */
public class SQLSelectionFactory
{

    private static class FactoryHolder
    {
        public static SQLSelectionFactory _instance = new SQLSelectionFactory();
    }

    protected SQLSelectionFactory()
    {

    }

    public static SQLSelectionFactory getInstance()
    {
        return FactoryHolder._instance;
    }

    public ISQLSelection getSQLSelection(SQLEditor editor)
    {
        IPreferenceStore store = SQLEditorPlugin.getDefault().getPreferenceStore();
        String executedPolicy = store.getString(
                PreferenceConstants.EXECUTE_SELECTED_SQL);

        if (executedPolicy.equalsIgnoreCase(PreferenceConstants.EXECUTE_SQL_BETWEEN_DELIMITER))
        {
            String[] terminators = new String[] { store.getString(PreferenceConstants.EXECUTE_SQL_DELIMITER_TYPE) };
            return new DelimeterSelection(editor, terminators);
        }
        else if (executedPolicy.equalsIgnoreCase(PreferenceConstants.EXECUTE_SQL_CURRENT_LINE))
        {
            return new CurrentLineSelection(editor);
        }
        else
        {
            return new BlankLineSelection(editor);
        }

    }
}
