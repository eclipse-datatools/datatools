/*******************************************************************************
 * Copyright © 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder;

import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.SQLBuilderActionBarContributor;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WindowUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WorkbenchUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.DesignViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.SQLTreeViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.GraphControl;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.QueryEventListener;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLSourceViewer;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * UI Component of SQL Query Builder content editor.
 * This can be hosted in an editor (e.g. <code>SQLBuilderEditor</code>) or a dialog.
 */

public class SQLBuilder implements IEditingDomainProvider, Observer,
		QueryEventListener, IMenuListener {

	protected SashForm _sashForm = null;
	protected SQLTreeViewer _contentOutlinePage;

	protected DesignViewer _designViewer;
	protected SQLSourceViewer _sourceViewer;
	protected GraphControl _graphControl;

	ISQLBuilderEditorInput _sqlBuilderEditorInput = null;
	protected IFile _iFile;

	/**
	 *  If this is created from an IEditorPart, this is passed in as a parameter
	 *  to the constructor.
	 */
	protected IEditorPart _editor = null;
	
	protected AdapterFactoryEditingDomain _editingDomain;

	protected SQLDomainModel _sqlDomainModel;

	protected SQLBuilderActionBarContributor _actionBarContributor;

	protected Object _currentSelection;

	protected boolean _created;

	/**
	 * Constructor for <code>SQLBuilder</code>.  This constructor should be called when
	 * a component other than an editor is being created.
	 */
	public SQLBuilder() {
		this(null);
	}

	/**
	 * Constructor for <code>SQLBuilder</code>. This constructor should be called when
	 * an Editor is being created.
	 * 
	 * @param ed the editor that is creating this <code>SQLBuilder</code>.
	 */
	public SQLBuilder(IEditorPart ed) {
		_editor = ed;

		_sqlDomainModel = new SQLDomainModel();

		BasicCommandStack commandStack = new BasicCommandStack();
		commandStack.addCommandStackListener(new CommandStackListener() {
			public void commandStackChanged(EventObject event) {
			}
		});

		// Create the editing domain with the command stack.
		_editingDomain = new AdapterFactoryEditingDomain(SQLBuilderPlugin
				.getAdapterFactory(), commandStack);

		_sqlDomainModel.setEditingDomain(_editingDomain);
	}

	/**
	 * Creates the UI component for the <code>SQLBuilder</code>.
	 * This method should be called after <code>setInput(ISQLBuilderEditorInput)</code>.
	 *  
	 * @param parent the parent composite.
	 */
	public void createClient(Composite parent) {
		_sashForm = new SashForm(parent, SWT.VERTICAL);

		// composite for source & label to go on sash
		Composite outsideSrcComp = ViewUtility.createNestedComposite(_sashForm,
				SWT.NONE);
		// ratio 0.30
		outsideSrcComp.setData(
				"layout ratio", new Long((((long) 30 << 16) + 99) / 100)); //$NON-NLS-1$

		// composite for source viewer to add border
		Composite srcComposite = ViewUtility.createNestedComposite(
				outsideSrcComp, SWT.BORDER);
		createSourceViewer(srcComposite);

		
		// If it's not in an editor, put the SQLTreeView in a SashForm with the GraphViewer
		SashForm graphSash = null;
		if (_editor == null){
			graphSash = new SashForm(_sashForm, SWT.HORIZONTAL);
			graphSash.setLayoutData(ViewUtility.createFill());
			
			createGraphViewer(graphSash);
			
		}
		else {
			createGraphViewer(_sashForm);
		}

		createDesignViewer(_sashForm);

		_sashForm.setLayoutData(ViewUtility.createFill());

		getContentOutlinePage(graphSash); // make sure everything is initialized

		// If it's not in an editor, set the weights of the graphical view's SashForm
		if (_editor == null){
			graphSash.setWeights(new int[] {2, 1});
		}
		
		 // The client should make this call, not the SQLBuilder
//		((IChangeNotifier) ui.getDomainModel().getAdapterFactory())
//		.addListener(new INotifyChangedListener() {
//
//			// public void notifyChanged(Object object, int eventType,
//			// Object
//			// feature, Object oldValue, Object newValue, int index)
//			public void notifyChanged(Notification msg) {
//				if (Display.getCurrent() != null) {
//					Display.getCurrent().asyncExec(new Runnable() {
//
//						public void run() {
//							updateDirtyStatus();
//						}
//					});
//				}
//			}
//		});
		 
		boolean isProper = _sqlDomainModel.isProper();
		updateProperStatement(isProper);
		_graphControl.refresh();
		_graphControl.setSQLBuilder(this);

		if (!_created) {
			// String strSQL = WorkbenchUtility.readFileContentsToString(ifile,
			// true );
			String strSQL = _sqlDomainModel.getInitialSource();
			if (strSQL.trim().length() > 0) {
				_sourceViewer.setFileSQLStr(strSQL);
			}
		}

	}

	/**
	 * Sets the input for the <code>SQLBuilder</code>.
	 * This method should be called before <code>createClient(Composite)</code>.
	 * 
	 * @param sqlBuilderEditorInput
	 * @throws PartInitException
	 */
	public void setInput(ISQLBuilderEditorInput sqlBuilderEditorInput)
			throws PartInitException {

		_sqlBuilderEditorInput = sqlBuilderEditorInput;
		
		if (sqlBuilderEditorInput != null) {

			// Get the connection, database and omitSchemaInfo
			OmitSchemaInfo omitSchemaInfo = sqlBuilderEditorInput
					.getOmitSchemaInfo();
			_sqlDomainModel.setOmitSchemaInfo(omitSchemaInfo);
			omitSchemaInfo.addObserver(this);

			ISQLEditorConnectionInfo connInfo = sqlBuilderEditorInput
					.getConnectionInfo();
			_sqlDomainModel.setConnectionInfo(connInfo);

			// After setting omitSchemaInfo and connectionInfo, call
			// setCurrentSchema
			_sqlDomainModel.setCurrentSchema();

			// Calling connInfo.getDatabase() tries to connect to the database
			Database db = connInfo.getDatabase();

			if (db == null && connInfo != null) {
				throw new PartInitException(NLS.bind(
						Messages._EXC_OPEN_SQL_FILE_NOT_CONNECTED, connInfo
								.getConnectionProfileName()));
			}
			_sqlDomainModel.setDatabase(db);

			// Load the initial SQL from the editor input. Note that persistance
			// is handled differently if the input is a FileEditorInput vs. a
			// StorageEditorInput. Handle the file case first.
			if (sqlBuilderEditorInput instanceof SQLBuilderFileEditorInput) {
				SQLBuilderFileEditorInput sqlBuilderFileEditorInput = (SQLBuilderFileEditorInput) sqlBuilderEditorInput;
				try {
					IFile fileResource = sqlBuilderFileEditorInput.getFile();
					if (fileResource != null) {
						_created = _sqlDomainModel.openFileResource(fileResource);
						if (_created == false) {
							// throw new
							// PartInitException(Messages._EXC_OPEN_SQL_FILE_RESOURCE"));
							// //$NON-NLS-1$

						}
					} else {
						throw new PartInitException(
								Messages._EXC_OPEN_SQL_FILE_RESOURCE);
					}
				} catch (Exception ex) {
					// SQLBuilderPlugin.getPlugin().getLogger().writeLog("Cannot
					// load resource.." + ex);
					throw new PartInitException(
							Messages._EXC_OPEN_SQL_FILE_RESOURCE);
				}
			}

			// Handle the case where the input is based on an IStorage object.
			else if (sqlBuilderEditorInput instanceof SQLEditorStorageEditorInput) {
				SQLEditorStorageEditorInput storageEditorInput = (SQLEditorStorageEditorInput) sqlBuilderEditorInput;
				IStorage storageResource = storageEditorInput.getStorage();
				try {
					_created = _sqlDomainModel
							.openStorageResource(storageResource);
					if (_created == false) {
						// throw new
						// PartInitException(Messages._ERROR_OPEN_SQL_STORAGE_RESOURCE"));
						// //$NON-NLS-1$
					}
				} catch (Exception ex) {
					throw new PartInitException(
							Messages._ERROR_OPEN_SQL_STORAGE_RESOURCE);
				}
			}

			// Otherwise we can't tell what we have.
			else {
				throw new PartInitException(
						Messages._ERROR_INPUT_NOT_RECOGNIZED);
			}

		}
	}

	/**
	 * Creates the Source Viewer
	 */
	protected void createSourceViewer(Composite client) {
		_sourceViewer = new SQLSourceViewer(_sqlDomainModel, client, _iFile, true);
		_sourceViewer.setQueryEventListener(this);
		_sourceViewer.initDBContext();
		_sourceViewer.setContentProvider(_sqlDomainModel.createContentProvider());
		_sourceViewer.setSQLBuilder(this);

		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		_sourceViewer.getControl().getParent().setLayoutData(data);
	}

	/**
	 * Creates the Graph Viewer
	 */
	protected void createGraphViewer(Composite client) {
		_graphControl = new GraphControl(_sqlDomainModel);
		_graphControl.createControl(client);

		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		_graphControl.getControl().setLayoutData(data);

		// ratio 0.25
		_graphControl.getControl().setData(
				"layout ratio", new Long((((long) 25 << 16) + 99) / 100)); //$NON-NLS-1$
	}

	/**
	 * Creates the Design Viewer
	 */
	protected void createDesignViewer(Composite client) {
		_designViewer = new DesignViewer(_sqlDomainModel, client);
		// ratio 0.45
		_designViewer.setData(
				"layout ratio", new Long((((long) 45 << 16) + 99) / 100)); //$NON-NLS-1$
	}

	/**
	 * Returns the SourceViewer.
	 * 
	 * @return SQLSourceViewer the SourceViewer.
	 */
	public SQLSourceViewer getSourceViewer() {
		return _sourceViewer;
	}

	/**
	 * Returns the GraphViewer.
	 * 
	 * @return GraphControl the GraphViewer.
	 */
	public GraphControl getGraphViewer() {
		return _graphControl;
	}

	/**
	 * Returns the DesignViewer.
	 * 
	 * @return DesignViewer the DesignViewer.
	 */
	public DesignViewer getDesignViewer() {
		return _designViewer;
	}

	/**
	 * Returns the content outline page.
	 * 
	 * @return IContentOutlinePage the content outline page.
	 */
	public IContentOutlinePage getContentOutlinePage(Composite composite) {
		if (_contentOutlinePage == null) {
			QueryStatement sqlStatement = _sqlDomainModel.getSQLStatement();
			_contentOutlinePage = new SQLTreeViewer(this, _sqlDomainModel
					.createContentProvider(), _sqlDomainModel
					.createLabelProvider(), sqlStatement);

			// Composite is non-null if SQLTreeViewer is not being created for
			// and editor, i.e. it's a component within the SQLBuilder rather
			// than being hosted in the Outline View.
			if (composite != null){
				_contentOutlinePage.createControl(composite);
				GridData data = new GridData();
				data.verticalAlignment = GridData.FILL;
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				data.grabExcessVerticalSpace = true;
				_contentOutlinePage.getControl().setLayoutData(data);
			}
			
			_contentOutlinePage
					.addSelectionChangedListener(new ISelectionChangedListener() {

						public void selectionChanged(SelectionChangedEvent event) {
							handleContentOutlineSelection(event.getSelection(),
									true);
						}
					});

			// Make sure the views are initalized even if the content outline is
			// not there
			handleContentOutlineSelection(
					new StructuredSelection(sqlStatement), false);
		}

		return _contentOutlinePage;
	}

	/**
	 * This deals with how we want selection in the outliner to affect the other
	 * views.
	 * 
	 * @param selection selection passed to <code>handleContentOutlineSelection</code>.
	 * @param fromEvent not used.
	 */
	public void handleContentOutlineSelection(ISelection selection,
			boolean fromEvent) {
		_currentSelection = WindowUtility.getSelection(selection);
		if (_currentSelection != null) {
			_graphControl.setInput(_currentSelection);
			_designViewer.inputChanged(_currentSelection);
			// [wsdbu00061547] bgp 29Dec2005 - ignore the "fromEvent" parm,
			// since
			// this was preventing the SQL source viewer from getting
			// initialized
			// when the outline view is not visible.
			// if (fromEvent) {
			_sourceViewer.setInput(_currentSelection);
			// }
			if (_currentSelection instanceof WithTableSpecification
					|| _currentSelection instanceof QueryCombined
					|| _currentSelection instanceof QueryValues) {
				_graphControl.getControl().setVisible(false);
				_sashForm.layout(true);
			} else if (_currentSelection instanceof QuerySelect) {
				_graphControl.getControl().setVisible(true);
				_sashForm.layout(true);
			} else if (_currentSelection instanceof QuerySelectStatement
					|| _currentSelection instanceof QueryExpressionRoot) {
				QueryExpressionBody queryBody = null;
				if (_currentSelection instanceof QuerySelectStatement) {
					queryBody = SelectHelper
							.getQueryExpressionBody((QuerySelectStatement) _currentSelection);
				} else if (_currentSelection instanceof QueryExpressionRoot) {
					QueryExpressionRoot qRoot = (QueryExpressionRoot) _currentSelection;
					queryBody = SelectHelper.getQueryExpressionBody(qRoot
							.getSelectStatement());
				}
				if (queryBody instanceof QuerySelect) {
					_graphControl.getControl().setVisible(true);
					_sashForm.layout(true);
				} else if (queryBody instanceof QueryCombined) {
					_graphControl.getControl().setVisible(false);
					_sashForm.layout(true);
				} else if (queryBody instanceof QueryValues) {
					_graphControl.getControl().setVisible(false);
					_sashForm.layout(true);
				} else {
					_graphControl.getControl().setVisible(true);
					_sashForm.layout(true);
				}
			}
		}
	}

	/**
	 * Returns whether the root of the content outline view is selected.
	 * 
	 * @return boolean true if the root is selected, otherwise false.
	 */
	public boolean isContentOutlineRootSelected() {
		return _contentOutlinePage.isOnlyRootSelected();
	}

	/**
	 * Enables / disables SQLBuilder controls and actions depending on whether
	 * the current SQL is valid or not.
	 * 
	 * @param isValid whether the current SQL is valid.
	 */
	public void updateProperStatement(boolean isValid) {
		// Disable sqlbuilder stuff (design, graph, and context menu for
		// outline)
		// ....and bring up options to revert to
		// previous or to default
		changeGraphControlEnableState(isValid);
		_designViewer.setEnabled(isValid);

		if (isValid) {
			if (_contentOutlinePage.getControl() != null) {
				_contentOutlinePage.getControl().setEnabled(true);
				_contentOutlinePage.refreshTree();
			}
		}
	}

	/**
	 * Tests whether the current SQL is valid.
	 * 
	 * @param domainModel the domain model for the SQL.
	 * @return boolean true if the SQL is valid, otherwise false.
	 */
	public static boolean isStatementProper(SQLDomainModel domainModel) {
		return domainModel.isProper();
	}

	/*
	 * Enables / disables the GraphControl.
	 */
	protected void changeGraphControlEnableState(boolean enable) {
		_graphControl.setEnabled(enable);

		if (!enable) {
			MessageDialog.openWarning(Display.getCurrent().getActiveShell(),
					Messages._UI_VALIDATE_FAILED_TITLE,
					Messages._UI_GRAPH_PARSE_FAILED);
		}
	}

	/**
	 * Reparses the SQL in the SourceViewer if it has changed.
	 * 
	 */
	public void reparseIfRequired() {
		if (_sourceViewer.isTextChanged()) {
			_sourceViewer.reparse();
		}
	}

	/**
	 * Reloads the SourceViewer from the SQL Model.
	 */
	public void reloadFromModel() {
		_sourceViewer.refreshSource();
	}

	/**
	 * Returns the <code>EditingDomain</code> belonging to this <code>SQLBuilder</code>
	 */
	public EditingDomain getEditingDomain() {
		return _editingDomain;
	}

	/*
	 * Returns the <code>IDocumentProvider</code> belonging to this <code>SQLBuilder</code>.
	 */
	protected IDocumentProvider getDocumentProvider() {
		return getSourceViewer().getDocumentProvider();
	}

	/**
	 * Implementation of Observer interface. This method is called when user
	 * changes the omit schema settings.
	 * 
	 * @param ob the object calling this method
	 * @param arg the argument passed to the notifyObservers method
	 */
	public void update(Observable ob, Object arg) {
		if (ob instanceof OmitSchemaInfo) {
			_sqlDomainModel.setCurrentSchema();
			_sourceViewer.refreshSource(_sqlDomainModel.getSQLStatement()
					.getSQL());
		}
	}

	/**
	 * Returns the <code>SQLDomainModel</code> belonging to this <code>SQLBuilder</code>.
	 */
	public SQLDomainModel getDomainModel() {
		return _sqlDomainModel;
	}

	/**
	 * Returns the <code>ISQLBuilderEditorInput</code> belonging to this <code>SQLBuilder</code>.
	 */
	public ISQLBuilderEditorInput getSQLBuilderEditorInput() {
		return _sqlBuilderEditorInput;
	}

	/**
	 * Returns the <code>IFile</code> belonging to this <code>SQLBuilder</code>, which
	 * may be null.
	 */
	public IFile getFile() {
		return _iFile;
	}

	/**
	 * Returns whether the <code>SQLBuilder</code> is currently validating the SQL.
	 */
	public boolean inValidateEditCall() {
		// return propertyResourceChangeListener.inValidateEditCall();
		return false;
	}

	/**
	 * Returns the <code>SQLBuilderActionBarContributor</code> belonging to this <code>SQLBuilder</code>.
	 */
	public SQLBuilderActionBarContributor getActionBarContributor() {
		if (_actionBarContributor == null){
			_actionBarContributor = new SQLBuilderActionBarContributor();
			_actionBarContributor.setActiveSQLBuilder(this);
		}
		return _actionBarContributor;
	}

	/**
	 * Sets the <code>SQLBuilderActionBarContributor</code> for this <code>SQLBuilder</code>.
	 */
	public void setActionBarContributor(
			SQLBuilderActionBarContributor contributor) {
		_actionBarContributor = contributor;

	}
	
	/**
     * Saves the current statement in the file resource associated with this <code>SQLBuilder</code>.
     * This method should be called by editors which consume the  <code>SQLBuilder</code>.
     * If the SQLBuilder has an input which is not file based, the SQL is not saved. In this case, it is the
     * responsibility of the consumer of the <code>SQLBuilder</code> to save the SQL.
	 * 
	 * @param progressMonitor progressMonitor used during save.
	 */
	public void doSave(IProgressMonitor progressMonitor) {
		// Bug 3022 : No need to reparse, since the statement is already parsed
		// once.
		// sourceViewer.forceReparse();
		// RATLC01136221 bgp 10Jan2007 - begin
		// Might not have a progressMonitor at all
		if (progressMonitor == null || !progressMonitor.isCanceled()) {
			boolean result = true;
			if (progressMonitor != null)
				progressMonitor.setTaskName(Messages._UI_SAVING_STATEMENT);

			// Sets the text to be used for saving
			_sqlDomainModel.setEditorText(_sourceViewer.getText());
			result = _sqlDomainModel.save();

			if (result == false) {
				if (progressMonitor != null)
					progressMonitor.setCanceled(true);
				notifyContentChange();
			} else {
				if (_sqlBuilderEditorInput != null
						&& _sqlBuilderEditorInput instanceof SQLBuilderFileEditorInput
						&& _sqlDomainModel.getIFile() != null) {
					SQLBuilderFileEditorInput sQLBuilderFileEditorInput = (SQLBuilderFileEditorInput) _sqlBuilderEditorInput;
					SQLFileUtil.setEncodedOmitSchemaInfo(
							sQLBuilderFileEditorInput.getFile(),
							sQLBuilderFileEditorInput.getOmitSchemaInfo()
									.encode());
				}

				_sqlDomainModel.setDirty(false);
				if (_sourceViewer != null) {
					_sourceViewer.setTextChanged(false);
				}
			}

			// TODO: Why is this code here?
			// The following code doesn't seem to ever do anything, since I
			// can't find anywhere were ifile is set to a non-null value
			if (progressMonitor != null && !progressMonitor.isCanceled()) {
				WorkbenchUtility.refreshLocalWorkspaceFile(getFile(),
						progressMonitor);
			}
			notifyContentChange();
		}
		// RATLC01136221 bgp 10Jan2007 - end
	}

	/*
	 * @
	 */
	protected boolean validateBeforeSave() {
		return SQLBuilderPlugin.getPlugin().getPreferenceStore().getBoolean(
				SQLBuilderConstants.P_VALIDATE_BEFORE_SAVE);
	}
	
	/**
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.views.source.QueryEventListener#notifyContentChange()
	 */
	public void notifyContentChange() {
		if (_editor != null && _editor instanceof QueryEventListener){
			((QueryEventListener)_editor).notifyContentChange();
		}
	}

	/**
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(IMenuManager)
	 */
	public void menuAboutToShow(IMenuManager menuManager) {
		menuManager.add(new Separator("additions")); //$NON-NLS-1$
		menuManager.add(new Separator("edit")); //$NON-NLS-1$

		_contentOutlinePage.fillContextMenu();

		if (getActionBarContributor() != null) {
			boolean enableRevert = !SQLBuilder
					.isStatementProper(getDomainModel());
			IAction revertToDefaultAction = _actionBarContributor
					.getAction(SQLBuilderActionBarContributor.REVERT_TO_DEFAULT_ACTION_ID);
			if (revertToDefaultAction != null) {
				revertToDefaultAction.setEnabled(enableRevert);
			}

			IAction revertToPreviousAction = _actionBarContributor
					.getAction(SQLBuilderActionBarContributor.REVERT_TO_PREVIOUS_ACTION_ID);
			if (revertToPreviousAction != null) {
				revertToPreviousAction.setEnabled(enableRevert);
			}
			IAction omitCurrentSchemaAction = _actionBarContributor
					.getAction(SQLBuilderActionBarContributor.OMIT_CURRENT_SCHEMA_ACTION_ID);
			if (omitCurrentSchemaAction != null) {
				boolean enableOmitCurrentSchema = false;
				if (getDomainModel() != null
						&& getDomainModel().getDatabaseDefinition() != null) {
					enableOmitCurrentSchema = getDomainModel()
							.getDatabaseDefinition().supportsSchema();
				} else {
					enableOmitCurrentSchema = false;
				}
				omitCurrentSchemaAction.setEnabled(enableOmitCurrentSchema);
			}
		}
	}
	
	/**
	 * Creates context menu for a viewer.
	 * 
	 * @param viewer the viewer.
	 * @return MenuManager the context menu created.
	 */
	public MenuManager createContextMenuFor(Viewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp"); //$NON-NLS-1$
		contextMenu.add(new Separator("additions")); //$NON-NLS-1$
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);

		Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		if (_editor != null){
			_editor.getEditorSite().registerContextMenu(contextMenu, viewer);
		}
		
		return contextMenu;
	}

}
