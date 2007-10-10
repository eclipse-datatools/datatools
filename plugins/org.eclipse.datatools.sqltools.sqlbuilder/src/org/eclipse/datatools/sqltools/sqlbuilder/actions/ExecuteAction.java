/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.BaseExecuteAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

public class ExecuteAction extends BaseExecuteAction {

	SQLBuilder _sqlBuilder;
	
    /**
     * Creates an instance of this class.  This is the default constructor.
     */
    public ExecuteAction() {
    	this.setText(Messages._UI_MENU_EXECUTE);
    }

    public void setActiveEditor(SQLBuilder sqlBuilder){
    	_sqlBuilder = sqlBuilder;
    }
 

	public DatabaseIdentifier getDatabaseIdentifier() {
    	IEditorPart activeEditor =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

        if (activeEditor instanceof SQLBuilder) {
            SQLBuilder sqlBuilder = (SQLBuilder) activeEditor;
    		String profileName = sqlBuilder.getDomainModel().getConnectionInfo().getConnectionProfileName();
    		String dbName = sqlBuilder.getDomainModel().getConnectionInfo().getDatabaseName();
    		return new DatabaseIdentifier(profileName, dbName);
    	}
        return null;
	}


	public Runnable getPostRun() {
        Runnable postRun = new Runnable()
        {
            public void run()
            {
            	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(_sqlBuilder);
            }
        }
        ;
        return postRun;
	}


	public String getSQLStatements() {
        //Begin - to enforce parse before Execute
        boolean currentTextModified = _sqlBuilder.getSourceViewer().isTextChanged();
        _sqlBuilder.getSourceViewer().setTextChanged(true);
        _sqlBuilder.getSourceViewer().setParseRequired(true); // QVO RATLC01112036 (2006-09-20)
        _sqlBuilder.reparseIfRequired(); //Don't delete
        _sqlBuilder.getSourceViewer().setTextChanged(currentTextModified);
        //End - to enforce parse before Execute

        // execute the query and send results to the Output View
        SQLDomainModel domainModel = _sqlBuilder.getDomainModel();
        if (domainModel != null) {
            QueryStatement stmt = domainModel.getSQLStatement();
            return stmt.getSQL();
        }
        else {
        	return null;
        }
	}


	public void update() {
		ISQLEditorConnectionInfo connectionInfo = _sqlBuilder.getDomainModel().getConnectionInfo();
		setEnabled(_sqlBuilder != null && connectionInfo.isConnected());
	}

}
