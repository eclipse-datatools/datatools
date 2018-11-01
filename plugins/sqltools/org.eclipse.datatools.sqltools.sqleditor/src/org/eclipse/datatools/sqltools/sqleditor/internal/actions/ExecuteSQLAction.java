/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hui Cao
 *  
 */
public class ExecuteSQLAction extends BaseExecuteAction
{
    protected SQLEditor _sqlEditor;

    /**
     * @param text
     * @param image
     */
    public ExecuteSQLAction(SQLEditor targetEditor)
    {
        setText(Messages.ExecuteSQLAction_label);
        setToolTipText(Messages.ExecuteSQLAction_tooltip);
        setImageDescriptor(SQLEditorResources.getImageDescriptor("execute"));
        setActionDefinitionId(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID);
        setId(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID);
        //no need to set image
        setActiveEditor(targetEditor);
        update();

        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpContextIds.EXECUTE_ALL_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void setActiveEditor(SQLEditor targetEditor)
    {
        _sqlEditor = targetEditor;
    }

    public void run()
    {
        super.run();
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(_sqlEditor);
    }

    public void update()
    {
        setEnabled(_sqlEditor != null && _sqlEditor.isConnected() && _sqlEditor.getSQLType()== SQLParserConstants.TYPE_SQL_ROOT);
    }

    public DatabaseIdentifier getDatabaseIdentifier()
    {
    	if (_sqlEditor != null)
    	{
    		String profileName = _sqlEditor.getConnectionInfo().getConnectionProfileName();
    		String dbName = _sqlEditor.getConnectionInfo().getDatabaseName();
    		return new DatabaseIdentifier(profileName, dbName);
    	}
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.actions.BaseExplainAction#getSQLStatements()
     */
    public String getSQLStatements()
    {
    	return _sqlEditor == null ? null : SQLToolsFacade.getDBHelper(getDatabaseIdentifier()).preprocessSQLScript(_sqlEditor.getText());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.actions.BaseExplainAction#getPostRun()
     */
    public Runnable getPostRun()
    {
        Runnable postRun = new Runnable()
        {
            public void run()
            {
            	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(_sqlEditor);
            }
        }
        ;
        return postRun;
    }

    protected SQLEditor getEditor()
    {
        return _sqlEditor;
    }
}
