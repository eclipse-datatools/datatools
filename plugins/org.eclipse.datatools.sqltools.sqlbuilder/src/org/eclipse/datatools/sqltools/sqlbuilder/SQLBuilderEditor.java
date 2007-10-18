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
package org.eclipse.datatools.sqltools.sqlbuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.SQLBuilderActionBarContributor;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.QueryEventListener;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.jface.action.IStatusLineManager;
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
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * SQL Query Builder content editor.
 */
public class SQLBuilderEditor extends EditorPart implements
		ISelectionProvider, QueryEventListener {

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

	class ConnectRunnable implements Runnable {
		SQLBuilderEditor _editor;

		public ConnectRunnable(SQLBuilderEditor editor) {
			_editor = editor;
		}

		public void run() {
			_editor.connectIfNeeded();
		};
	};

	/**
	 * Constructor for <code>SQLBuilderEditor</code>
	 */
	public SQLBuilderEditor() {
		super();

		_sqlBuilder = new SQLBuilder(this);

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

		/*
		 * Add a listener for changes to the SQLBuilderEditor's domain model.
		 */
		((IChangeNotifier) _sqlBuilder.getDomainModel().getAdapterFactory())
				.addListener(new INotifyChangedListener() {

					public void notifyChanged(Notification msg) {
						if (Display.getCurrent() != null) {
							Display.getCurrent().asyncExec(new Runnable() {

								public void run() {
									updateDirtyStatus();
								}
							});
						}
					}
				});

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
		_sqlBuilder.setInput(sqlBuilderEditorInput);
		site.setSelectionProvider(this);

		// Show the connection status in the status area at the bottom of
		// the workbench window.
		refreshConnectionStatus();

		if (editorInput != null) {

			// Get the name from the editorInput
			String title = editorInput.getName();
			setPartName(title);
		}
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
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.views.source.QueryEventListener#notifyContentChange()}
	 */
	public void notifyContentChange() {
		updateDirtyStatus();
	}

	public void updateDirtyStatus() {
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	public boolean isDirty() {
		return _sqlBuilder.getDomainModel().isDirty();
	}

	public void setResourceRemoved(boolean value) {
		_resourceRemoved = value;
	}

	// implement ISelectionProvider
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		_selectionChangedListeners.add(listener);
	}

	public ISelection getSelection() {
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

	public void setFocus() {
		if (_sqlBuilder.getSourceViewer() != null) {
			_sqlBuilder.getSourceViewer().getControl().setFocus();
		}

		/*
		 * Try to make sure that we have a database connection so that the SQL
		 * model will be populated when we need it. We delay connecting so that
		 * the user won't get prompted when the Workbench is coming up.
		 */
		if (_firstFocus == true) {
			connectIfNeeded();
			_firstFocus = false;
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
	 * Try to make sure that we have a database connection so that the SQL model
	 * will be populated when we need it. We delay connecting so that the user
	 * won't get prompted when the Workbench is coming up.
	 */
	public void connectIfNeeded() {
		SQLDomainModel domainModel = _sqlBuilder.getDomainModel();
		Database db = domainModel.getDatabase();
		if (db == null) {
			boolean keepTrying = true;

			/* Find out if we're visible yet. */
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow activeWindow = workbench
					.getActiveWorkbenchWindow();
			if (activeWindow != null) {
				IWorkbenchPage activePage = activeWindow.getActivePage();
				if (activePage != null) {
					if (activePage.isPartVisible(this)) {
						/*
						 * Once we're visible, try re-establishing the
						 * connection.
						 */
						ISQLEditorConnectionInfo connInfo = domainModel
								.getConnectionInfo();
						if (connInfo != null) {
							boolean connectedOK = true; // SQLDBUtils.reestablishConnection(
							// connInfo );
							if (connectedOK == true) {
								db = connInfo.getDatabase();
								domainModel.setDatabase(db);
								domainModel.resetVendor(db);
							} else {
								keepTrying = false;
							}

							/*
							 * Force a reparse to rebuild the display with the
							 * new Database info.
							 */
							_sqlBuilder.getSourceViewer().forceReparse();
						}
					}
				}
			}

			/*
			 * If we still don't have a connection and we didn't cancel the
			 * connection attempt, try it again later.
			 */
			if (db == null && keepTrying == true) {
				ConnectRunnable connectRunnable = new ConnectRunnable(this);
				Display display = workbench.getDisplay();
				int delayTime = 1000; // one second
				display.timerExec(delayTime, connectRunnable);
			}
		}
	}

	/**
	 * Refreshes the status area indicating the connection state.
	 */
	public void refreshConnectionStatus() {
		IEditorSite editorSite = getEditorSite();
		if (editorSite != null) {
			SQLDomainModel domainModel = _sqlBuilder.getDomainModel();
			ISQLEditorConnectionInfo connInfo = domainModel.getConnectionInfo();
			IActionBars actionBars = editorSite.getActionBars();
			// IEditorActionBarContributor editorContributor =
			// editorSite.getActionBarContributor();
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

			/*
			 * Enable the Run SQL action. This is a work-around for a problem
			 * where the Run action is getting disabled when the focus shifts
			 * off the SQL source area.
			 */
			// SQLSourceViewer srcViewer = getSourceViewer();
			// if (srcViewer != null) {
			// IAction runSQLAction = srcViewer.getAction( "RunSQLAction" );
			// if (runSQLAction != null) {
			// // boolean enabled = (connInfo != null ? true : false);
			// runSQLAction.setEnabled( true );
			// }
			// }
		}
	}

} // end class
