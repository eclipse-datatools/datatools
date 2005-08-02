/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.connectivity.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.views.execute.SQLScrapbookSelectConnectionAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorFileEditorInput;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.texteditor.AbstractDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * File based editor input for SQL scrapbook. 
 */
public class SQLScrapbookEditorInput extends SQLEditorFileEditorInput implements
        IPropertyChangeListener {

    private String statementSQL;

    private IAction connectionAction;

    private IEditorSite editorSite;

	public SQLScrapbookEditorInput(IFile file) {
        super(file);
    }	

    public SQLScrapbookEditorInput(IFile file, ConnectionInfo connectionInfo) {
        super(file);
        setConnectionInfo( connectionInfo );
    }

    public SQLScrapbookEditorInput(IFile file, String statementSQL) {
        super(file);
        this.statementSQL = statementSQL;
    }

    // tau 17.07.04
    public ConnectionInfo getConnectionInfo(boolean dialog) {
        ConnectionInfo connectionInfo = getConnectionInfo();
        if (connectionInfo == null && connectionAction != null && dialog) {
            connectionAction.run();
        }
        return connectionInfo;
    }

	public void setStatementSQL(String statementSQL) {
		this.statementSQL = statementSQL;
	}
	
    public String getStatementSQL() {
        return statementSQL;
    }

    /**
     * @return Returns the editorSite.
     */
    public IEditorSite getEditorSite() {
        return editorSite;
    }

    /**
     * @param editorSite
     *            The editorSite to set.
     */
    public void setEditorSite(IEditorSite editorSite) {
        this.editorSite = editorSite;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {

        if (event.getProperty().equals(
                SQLScrapbookSelectConnectionAction.CONNECTION)) {
            ConnectionInfo connectionInfo = (ConnectionInfo) event.getNewValue();
            setConnectionInfo( connectionInfo );
        }

        // 19.07.04 tau
        IEditorPart editor = null;
        IWorkbenchPage page = editorSite.getPage();

        if (page != null) {
            editor = page.getActiveEditor();
        }

        if (editor != null && editor instanceof ITextEditor) {
            ((AbstractDocumentProvider) ((ITextEditor) editor).getDocumentProvider()).setCanSaveDocument(this);
        }

        showMessageConnection(); // 17.07.04
    }

    public void showMessageConnection() {
        IActionBars bars = editorSite.getActionBars();
        ConnectionInfo connectionInfo = getConnectionInfo();
        if (bars != null && connectionInfo != null) {
            bars.getStatusLineManager().setErrorMessage(null);
            bars.getStatusLineManager().setMessage(
                    connectionInfo.getName()
                            + " ("
                            + connectionInfo.getDatabaseName()
                            + ": "
                            + connectionInfo.getURL()
                            + ")");
            bars.updateActionBars();
        } else if (bars != null && connectionInfo == null) {
            bars.getStatusLineManager().setErrorMessage("No connection!");
        }

    }

    /**
     * @return Returns the setConnectionAction. tau 09.07.04
     */
    public IAction getConnectionAction() {
        return connectionAction;
    }

    /**
     * @param setConnectionAction
     *            The setConnectionAction to set. tau 09.07.04
     */
    public void setConnectionAction(IAction setConnectionAction) {
        this.connectionAction = setConnectionAction;
    }

}
