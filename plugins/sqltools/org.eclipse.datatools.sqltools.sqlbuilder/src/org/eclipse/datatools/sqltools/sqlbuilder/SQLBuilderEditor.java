/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.SQLBuilderActionBarContributor;
import org.eclipse.datatools.sqltools.sqlbuilder.input.ISQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderFileEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLSourceViewer;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * SQL Query Builder content editor.
 */
public class SQLBuilderEditor extends EditorPart implements
		ISelectionProvider, IContentChangeListener {

	/**
	 * The SQLBuilder for this editor.
	 */
	protected SQLBuilder _sqlBuilder;

	// flag to allow for delayed initialization of the connection and Database
	// object
	protected boolean _firstFocus = true;

	// keep track of the selection of the editor
	protected Collection _selectionChangedListeners = new ArrayList();
	protected ISelection _editorSelection;

	// flag to detect if resource is removed to prevent model operations
	protected boolean _resourceRemoved = false;

	/**
	 * Constructor for <code>SQLBuilderEditor</code>
	 */
	public SQLBuilderEditor() {
		super();

		_sqlBuilder = new SQLBuilder(this);
		_sqlBuilder.addContentChangeListener(this);

	}

	/**
	 * Overrides {@link org.eclipse.ui.part.WorkbenchPart#dispose()}
	 */
	public void dispose() {
		super.dispose();
	}

	/**
	 * Create the UI for this <code>SQLBuilderEditor</code>.
	 * Implements {@link org.eclipse.ui.part.WorkbenchPart#createPartControl(Composite)}
	 */
	public void createPartControl(Composite composite) {

		/*
		 * Create the UI component.
		 */
		_sqlBuilder.createClient(composite);

	}

	/**
	 * Called during startup.
	 * 
	 * Implements {@link org.eclipse.ui.part.EditorPart#init(IEditorSite, IEditorInput)}
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {

		ISQLBuilderEditorInput sqlBuilderEditorInput = null;
		if (editorInput instanceof ISQLBuilderEditorInput) {
			sqlBuilderEditorInput = (ISQLBuilderEditorInput) editorInput;
		} else if (editorInput instanceof IFileEditorInput) {
			sqlBuilderEditorInput = new SQLBuilderFileEditorInput(
					((IFileEditorInput) editorInput).getFile());
		}

		setSite(site);
		setInput(editorInput);
		
		// Set flag telling the _sqlBuilder to wait for a database connection before
		// loading the SQL in the input
		_sqlBuilder.setLoadOnConnection(true);
		
		// Set the _sqlBuilder's input
		try {
			_sqlBuilder.setInput(sqlBuilderEditorInput);
		}
		catch (PartInitException e){
			e.printStackTrace();
		} catch (ParseException e) {
			String sSQL = sqlBuilderEditorInput.getSQL();
			if (sSQL != null && sSQL.trim().length() > 0){
				String sMessage = e.getLocalizedMessage() + Messages._QUESTION_MESSAGE_OPEN_INPUT_PARSE_FAILED;
			
				MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
						Messages._QUESTION_TITLE_OPEN_INPUT_PARSE_FAILED, sMessage);
			}
		}
		
		site.setSelectionProvider(this);

		// Show the connection status in the status area at the bottom of
		// the workbench window.
		refreshConnectionStatus();

		// Get the name from the editorInput
		String title = editorInput.getName();
		setPartName(title);
	}

	/**
	 * Implements {@link org.eclipse.ui.part.EditorPart#doSave(IProgressMonitor)}
	 * 
	 * @param monitor the progress monitor to use.
	 */
	public void doSave(IProgressMonitor progressMonitor) {
		_sqlBuilder.doSave(progressMonitor);
	}

	/**
	 * Implements {@link org.eclipse.ui.part.EditorPart#doSaveAs()}
	 */
	public void doSaveAs() {
	}

	/**
	 * Implements {@link org.eclipse.ui.part.EditorPart#isSaveAsAllowed()}
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	public boolean isDirty() {
		return _sqlBuilder.isDirty();
	}

	public void setResourceRemoved(boolean value) {
		_resourceRemoved = value;
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.IContentChangeListener#notifyContentChange()}
	 */
	public void notifyContentChange() {
		updateDirtyStatus();
	}

	public void updateDirtyStatus() {
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	
	public void setFocus() {
	    /* 
         * Make sure the source viewer gets the focus.  Bad things happen if nothing in
         * the editor has the focus.
         */
        SQLSourceViewer sourceViewer = _sqlBuilder.getSourceViewer();
        if (sourceViewer != null) {
            sourceViewer.getControl().setFocus();
        }
        
		/*
		 * Try to make sure that we have a database connection so that the SQL
		 * model will be populated when we need it. We delay connecting so that
		 * the user won't get prompted when the Workbench is coming up.
		 */
		if (_firstFocus == true) {
			_firstFocus = false;
			_sqlBuilder.connectIfNeeded(this);
		}

		/*
		 * Make sure the connection status message is up to date. It doesn't
		 * automatically get updated if the user switches editor instances.
		 */
		refreshConnectionStatus();
	}

	public SQLBuilder getSQLBuilder() {
		return _sqlBuilder;
	}
	
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			if (_sqlBuilder != null) {
				return _sqlBuilder.getContentOutlinePage(null);
			}
		}

		// return EcoreUtil.getAdapter(super.eAdapters(),key);
		return super.getAdapter(key);
	}

	public SQLBuilderActionBarContributor getActionBarContributor() {
		return _sqlBuilder.getActionBarContributor();
	}

	/**
	 * Sets the action contributor associated with this editor. This is called
	 * by the contributor itself.
	 */
	public void setActionBarContributor(
			SQLBuilderActionBarContributor contributor) {
		_sqlBuilder.setActionBarContributor(contributor);
	}

	/**
	 * Refreshes the status area indicating the connection state.
	 */
	public void refreshConnectionStatus() {
		IEditorSite editorSite = getEditorSite();
		if (editorSite != null) {
			ISQLEditorConnectionInfo connInfo = _sqlBuilder.getConnectionInfo();
			IActionBars actionBars = editorSite.getActionBars();
			if (actionBars != null) {
				IStatusLineManager statusLineMgr = actionBars
						.getStatusLineManager();
				if (connInfo != null) {
					statusLineMgr.setErrorMessage(null);
					String connStatus = connInfo.getName();
					statusLineMgr.setMessage(connStatus);

				} else {
					String connStatus = SQLEditorResources.SQLEditor_connection_status_noConnection;
					statusLineMgr.setErrorMessage(connStatus);
				}
				actionBars.updateActionBars();
			}
		}
	}

	// implement ISelectionProvider
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		_selectionChangedListeners.add(listener);
	}

	public ISelection getSelection() {
		if (_editorSelection != null){
			System.out.println("non null _editorSelection");
		}
		return _editorSelection;
	}

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		_selectionChangedListeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
		_editorSelection = selection;
		for (Iterator listeners = _selectionChangedListeners.iterator(); listeners
				.hasNext();) {
			ISelectionChangedListener listener = (ISelectionChangedListener) listeners
					.next();
			listener
					.selectionChanged(new SelectionChangedEvent(this, selection));
		}
	}


} // end class
