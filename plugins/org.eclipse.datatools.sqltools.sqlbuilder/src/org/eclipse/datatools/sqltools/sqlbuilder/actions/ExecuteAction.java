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
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderEditor;
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

    public void setSQLBuilder(SQLBuilder sqlBuilder){
    	_sqlBuilder = sqlBuilder;
    }
 

	public DatabaseIdentifier getDatabaseIdentifier() {

        if (_sqlBuilder != null) {
    		return _sqlBuilder.getDomainModel().getDatabaseIdentifier();
    	}
        return null;
	}


	public Runnable getPostRun() {
        Runnable postRun = new Runnable()
        {
            public void run()
            {
            	IEditorPart activeEditor =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            	if (activeEditor instanceof SQLBuilderEditor){
            		if (((SQLBuilderEditor)activeEditor).getSQLBuilder() == _sqlBuilder){
            			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(activeEditor);
            		}
            	}
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
		setEnabled(_sqlBuilder.getDomainModel().isConnected());
	}

}
