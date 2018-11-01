/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SQLEditorFileEditorInput;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.views.execute.SQLScrapbookSelectConnectionAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IMemento;
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

    /**
	 * Constructs a SQLScrapbookEditorInput using an IFile. 
	 * The connection info will be restored from the workspace metadata.
	 * @param file
	 */
	public SQLScrapbookEditorInput(IFile file) {
        super(file);
			
		ISQLEditorConnectionInfo connectionInfo = SQLFileUtil.getConnectionInfo(file);
		setConnectionInfo(SQLFileUtil.getConnectionInfo4Scrapbook(connectionInfo));
		
    }

	/**
	 * Constructs a SQLScrapbookEditorInput using an IFile and an ISQLEditorConnectionInfo. 
	 * The connection info will be saved.
	 * @param file
	 * @param connectionInfo
	 */
	public SQLScrapbookEditorInput(IFile file, ISQLEditorConnectionInfo connectionInfo) {
        super(file);
        setConnectionInfo( connectionInfo );
        SQLFileUtil.setEncodedConnectionInfo(file, connectionInfo.encode());
    }

    public SQLScrapbookEditorInput(IFile file, String statementSQL) {
        this(file);
        this.statementSQL = statementSQL;
    }

    // tau 17.07.04
    public ISQLEditorConnectionInfo getConnectionInfo(boolean dialog) {
        ISQLEditorConnectionInfo connectionInfo = getConnectionInfo();
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
            ISQLEditorConnectionInfo connectionInfo = (ISQLEditorConnectionInfo) event.getNewValue();
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
    	if (editorSite == null)
    	{
    		return;
    	}
        IActionBars bars = editorSite.getActionBars();
        ISQLEditorConnectionInfo connectionInfo = getConnectionInfo();
        if (bars != null && connectionInfo != null && connectionInfo.getConnectionProfile() != null && connectionInfo.getDatabaseName() != null) {
            bars.getStatusLineManager().setErrorMessage(null);
            bars.getStatusLineManager().setMessage(
                    connectionInfo.getConnectionProfile().getName()
                            + " ("
                            + connectionInfo.getDatabaseName()
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

    /**
     * Returns the id of the element factory which should be used to re-create this
     * object.
     * 
     * @see org.eclipse.ui.IPersistableElement#getFactoryId()
     */
    public String getFactoryId() {
        return SQLScrapbookEditorInputFactory.ID_FACTORY;
    }

    /**
     * Saves the state of the object in the given memento.
     * 
     * @param memento the storage area for object's state
     * @see org.eclipse.ui.IPersistableElement#saveState(org.eclipse.ui.IMemento)
     */
    public void saveState(IMemento memento) {
        SQLScrapbookEditorInputFactory.saveState( memento, this );
    }


}
