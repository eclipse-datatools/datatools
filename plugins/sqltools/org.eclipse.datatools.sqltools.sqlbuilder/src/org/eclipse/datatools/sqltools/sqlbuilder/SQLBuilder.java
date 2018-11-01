/*******************************************************************************
 * Copyright © 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Sybase Inc.
 *     Actuate Corporation - enhancement to maintain SQB UI control state
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder;

import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.SQLBuilderActionBarContributor;
import org.eclipse.datatools.sqltools.sqlbuilder.input.ISQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.ISQLBuilderEditorInputUsageOptions;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInputUsageOptions;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderFileEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ControlStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IControlStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.WindowStateInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLParserUtil;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WindowUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WorkbenchUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.CustomSashForm;
import org.eclipse.datatools.sqltools.sqlbuilder.views.DesignViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.SQLTreeViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.GraphControl;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLSourceViewer;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * UI Component of SQL Query Builder content editor.
 * This can be hosted in an editor (e.g. <code>SQLBuilderEditor</code>) or a dialog.
 */

public class SQLBuilder implements IEditingDomainProvider, Observer,
		IContentChangeListener, IMenuListener {

	protected Composite _parentControl = null;

    protected CustomSashForm _sashMain = null;
	protected CustomSashForm _sashSourceGraph = null;
	protected CustomSashForm _sashGraphOutline = null;
	
	protected static final int DEFAULT_SASHMAIN_WEIGHT1 = 200;
	protected static final int DEFAULT_SASHMAIN_WEIGHT2 = 100;
	protected static final int DEFAULT_SASHSOURCEGRAPH_WEIGHT1 = 100;
	protected static final int DEFAULT_SASHSOURCEGRAPH_WEIGHT2 = 100;
	protected static final int DEFAULT_SASHGRAPHOUTLINE_WEIGHT1 = 200;
	protected static final int DEFAULT_SASHGRAPHOUTLINE_WEIGHT2 = 100;
	protected static final int DEFAULT_SASHMAIN_HEIGHT = 400;
	protected static final int DEFAULT_SASHMAIN_WIDTH = 600;
	
	protected SQLTreeViewer _contentOutlinePage;
	protected DesignViewer _designViewer;
	protected SQLSourceViewer _sourceViewer;
	protected GraphControl _graphControl;

	protected ISQLBuilderEditorInput _sqlBuilderEditorInput = null;
	protected IWindowStateInfo _windowStateInfo = null;
	protected ISQLBuilderEditorInputUsageOptions _editorInputUsageOptions = null;
	protected IFile _iFile;

	protected ListenerList _contentChangeListeners = null;
	protected ListenerList _executeSQLListeners = null;
	
	
	/**
	 *  If this is created from an IEditorPart, this is passed in as a parameter
	 *  to the constructor.
	 */
	protected IEditorPart _editor = null;
	
	protected AdapterFactoryEditingDomain _editingDomain;

	protected SQLDomainModel _sqlDomainModel;

	protected SQLBuilderActionBarContributor _actionBarContributor;

	protected Object _currentSelection;

	protected boolean _loadOnConnection = false;
	
	protected boolean _inputLoaded = false;
	
	protected boolean _clientCreated = false;
	
	protected boolean _inCreateClient = false;

	
	/*
	 * Class for trying to establish a database connection
	 */
	class ConnectRunnable implements Runnable {
		SQLBuilder _sqlBuilder;
		IWorkbenchPart _part;

		public ConnectRunnable(IWorkbenchPart part, SQLBuilder sqlBuilder) {
			_part = part;
			_sqlBuilder = sqlBuilder;
		}

		public void run() {
			_sqlBuilder.connectIfNeeded(_part);
		};
	};

	
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
		
		_contentChangeListeners = new ListenerList();
		_executeSQLListeners = new ListenerList();
	}

	public void addContentChangeListener(IContentChangeListener listener){
		if (_contentChangeListeners != null){
			_contentChangeListeners.add(listener);
		}
	}
	
	public void removeContentChangeListener(IContentChangeListener listener){
		if (_contentChangeListeners != null){
			_contentChangeListeners.remove(listener);
		}
	}
	
	public void addExecuteSQLListener(IExecuteSQLListener listener){
		if (_executeSQLListeners != null){
			_executeSQLListeners.add(listener);
		}
	}
	
	public void removeExecuteSQLListener(IExecuteSQLListener listener){
		if (_executeSQLListeners != null){
			_executeSQLListeners.remove(listener);
		}
	}
	
	/**
	 * Creates the UI component for the <code>SQLBuilder</code>.
	 * This method should be called after <code>setInput(ISQLBuilderEditorInput)</code>.
	 *  
	 * @param parent the parent composite.
	 */
	public void createClient(Composite parent) {
		_inCreateClient = true;
        _parentControl = parent;
		_sashMain = new CustomSashForm(parent, SWT.VERTICAL, CustomSashForm.NO_HIDE_UP);
		int height = DEFAULT_SASHMAIN_HEIGHT;
		int width = DEFAULT_SASHMAIN_WIDTH;
		if ( _sqlBuilderEditorInput != null )
		{
			IWindowStateInfo winState = _sqlBuilderEditorInput.getWindowStateInfo( );
			if ( winState != null )
			{
				height = winState.getHeight( ) <= 0? height:winState.getHeight( );
				width = winState.getWidth( ) <= 0? width:winState.getWidth( );
			}
		}
		
		_sashMain.setSize( width , height );
		
		if (_inputLoaded){
			doCreateClient();
		}
		_inCreateClient = false;
	}

	/*
	 * The part of CreateClient that depends on the input having been loaded
	 */
	private void doCreateClient(){
		
		_sashSourceGraph = new CustomSashForm(_sashMain, SWT.VERTICAL);
		_sashSourceGraph.setLayoutData(ViewUtility.createFill());

		// composite for source & label to go on sash
		Composite outsideSrcComp = ViewUtility.createNestedComposite(_sashSourceGraph,
				SWT.NONE);

		// composite for source viewer to add border
		Composite srcComposite = ViewUtility.createNestedComposite(
				outsideSrcComp, SWT.BORDER);
		createSourceViewer(srcComposite);

		// If it's not in an editor, put the SQLTreeView in a SashForm with the GraphViewer
		_sashGraphOutline = null;
		if (_editor == null){
			_sashGraphOutline = new CustomSashForm(_sashSourceGraph, SWT.HORIZONTAL, CustomSashForm.NO_HIDE_UP);
			_sashGraphOutline.setLayoutData(ViewUtility.createFill());
			
			Composite graphComposite = ViewUtility.createNestedComposite(
					_sashGraphOutline, SWT.BORDER);
			createGraphViewer(graphComposite);
			
		}
		else {
			Composite graphComposite = ViewUtility.createNestedComposite(
					_sashSourceGraph, SWT.NONE);
			createGraphViewer(graphComposite);
		}

		Composite designComposite = ViewUtility.createNestedComposite(
				_sashMain, SWT.BORDER);
		createDesignViewer(designComposite);

		_sashMain.setLayoutData(ViewUtility.createFill());

		// set _clientCreated before getting the ContentOutlinePage
		_clientCreated = true;
		
		if (_sashGraphOutline != null){
			Composite contentOutlineComposite = ViewUtility.createNestedComposite(
					_sashGraphOutline, SWT.BORDER);
			getContentOutlinePage(contentOutlineComposite); // make sure everything is initialized
		}
		else {
			getContentOutlinePage(_sashGraphOutline); // make sure everything is initialized
		}

		if ( useWindowState() ) {
		    restoreWindowState();
		}
		else {
			// Set default weights of sashes
			
			// set weights of _sashMain
			_sashMain.setWeights(new int[] {DEFAULT_SASHMAIN_WEIGHT1, DEFAULT_SASHMAIN_WEIGHT2});

			// set weights of _sashSourceGraph
			_sashSourceGraph.setWeights(new int[] {DEFAULT_SASHSOURCEGRAPH_WEIGHT1, DEFAULT_SASHSOURCEGRAPH_WEIGHT2});

			//  set weights of _sashGraphOutline
			if (_sashGraphOutline != null){
				_sashGraphOutline.setWeights(new int[] {DEFAULT_SASHGRAPHOUTLINE_WEIGHT1, DEFAULT_SASHGRAPHOUTLINE_WEIGHT2});
			}
		}
		
		((IChangeNotifier) getDomainModel().getAdapterFactory())
		.addListener(new INotifyChangedListener() {

			// public void notifyChanged(Object object, int eventType,
			// Object
			// feature, Object oldValue, Object newValue, int index)
			public void notifyChanged(Notification msg) {
				if (Display.getCurrent() != null) {
					Display.getCurrent().asyncExec(new Runnable() {

						public void run() {
							notifyContentChange();
						}
					});
				}
			}
		});
		 
		boolean isProper = _sqlDomainModel.isProper();
		updateProperStatement(isProper);
		_graphControl.refresh();
		_graphControl.setSQLBuilder(this);

		if (!_inputLoaded) {
			_sourceViewer.revertToDefaultSource();
		}
	}
	
	private boolean useWindowState()
	{
		return (_editorInputUsageOptions != null && _windowStateInfo != null
				&& _editorInputUsageOptions.useWindowState() == true);
	}

	
	/**
	 * Tells the SQLBuilder to load the input SQL only after a database
	 * connection has been obtained.
	 */
	public void setLoadOnConnection(boolean loadOnConnection){
		_loadOnConnection = loadOnConnection;;
	}
	/**
	 * Sets the input for the <code>SQLBuilder</code>.
	 * This method should be called before <code>createClient(Composite)</code>.
	 * The <code>waitForConnection</code> parameter indicates that the SQLBuilder should
	 * delay opening the input until a connection to the database has been obtained.
	 * 
	 * @param sqlBuilderEditorInput
	 * @param bWaitForConnection
	 * @throws PartInitException
	 */
	public void setInput(ISQLBuilderEditorInput sqlBuilderEditorInput)
			throws PartInitException, ParseException {

		_sqlBuilderEditorInput = sqlBuilderEditorInput;
		
		if (_sqlBuilderEditorInput != null) {

			// Get the connection, database and omitSchemaInfo
			IOmitSchemaInfo omitSchemaInfo = _sqlBuilderEditorInput
					.getOmitSchemaInfo();
			_sqlDomainModel.setOmitSchemaInfo(omitSchemaInfo);
			((OmitSchemaInfo)omitSchemaInfo).addObserver(this);

			ISQLEditorConnectionInfo connInfo = _sqlBuilderEditorInput.getConnectionInfo();
			_sqlDomainModel.setConnectionInfo(connInfo);


			// After setting omitSchemaInfo and connectionInfo, call
			// setCurrentSchema
			_sqlDomainModel.setCurrentSchema();

			// Calling connInfo.getDatabase() tries to connect to the database
			Database db = connInfo.getDatabase();

			// If there's no database and we were asked only to load when there's a
			// connection, do nothing. The client should call connectIfNeeded.
			if (db == null &&  _loadOnConnection){
				;
			}
			else {
				loadInput();
			}
		}
	}

	/*
	 * Loads the SQL statement from the SQLBuilder's ISQLBuilderEditorInput
	 * 
	 */
	protected void loadInput() throws PartInitException, ParseException {
		
		ISQLEditorConnectionInfo connInfo = _sqlDomainModel.getConnectionInfo();
		Database db = null;
		if (connInfo != null){
			db = connInfo.getDatabase();
		}
		if (db == null && connInfo != null) {
			throw new PartInitException(NLS.bind(
					Messages._EXC_OPEN_SQL_FILE_NOT_CONNECTED, connInfo
							.getConnectionProfileName()));
		}
		
		_sqlDomainModel.setDatabase(db);

		// Load the initial SQL from the editor input. Note that persistance
		// is handled differently if the input is a FileEditorInput vs. a
		// StorageEditorInput. Handle the file case first.
		if (_sqlBuilderEditorInput instanceof SQLBuilderFileEditorInput) {
			SQLBuilderFileEditorInput sqlBuilderFileEditorInput = (SQLBuilderFileEditorInput) _sqlBuilderEditorInput;
			try {
				IFile fileResource = sqlBuilderFileEditorInput.getFile();
				if (fileResource != null) {
					_inputLoaded = _sqlDomainModel.openFileResource(fileResource);
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
		else if (_sqlBuilderEditorInput instanceof SQLEditorStorageEditorInput) {
			SQLEditorStorageEditorInput storageEditorInput = (SQLEditorStorageEditorInput) _sqlBuilderEditorInput;
			IStorage storageResource = storageEditorInput.getStorage();
			try {
				_inputLoaded = _sqlDomainModel
						.openStorageResource(storageResource);
			} catch (Exception ex) {
				throw new PartInitException(
						Messages._ERROR_OPEN_SQL_STORAGE_RESOURCE);
			}
		}

		// Handle the case where the input is based on a String object.
		else if (_sqlBuilderEditorInput instanceof SQLBuilderEditorInput) {
			SQLBuilderEditorInput editorInput = (SQLBuilderEditorInput) _sqlBuilderEditorInput;
			String strSQL = editorInput.getSQL();
			if (strSQL == null || strSQL.length() == 0){
				int statementType = editorInput.getStatementType();
				try {
					_inputLoaded = _sqlDomainModel
							.initializeFromType(statementType);
				} catch (Exception ex) {
					throw new PartInitException(
							Messages._ERROR_OPEN_SQL_STRING_RESOURCE);
				}
			}
			else {
				// If there's no dialect info, load the input's string
				if (editorInput.getSQLStatementInfo().getSQLDialectInfo() == null){
					try {
						_inputLoaded = _sqlDomainModel
								.initializeFromString(strSQL, null);
					} catch (Exception ex) {
						throw new PartInitException(
								Messages._ERROR_OPEN_SQL_STRING_RESOURCE);
					}
				}
				// If there's a dialect, parse according to the dialect
				// then edit the statement for the current connectionInfo
				else { 
					try {
						// Parse according to the dialect then
						// generate according to the 
						// editorInput's connectionInfo.
						// This is done in a utility class rather than the DomainModel
						// so that the member variables in the SQLDomainModel
						// don't get messed up.
						// 
						// NB. This functionality has only been tested in a very
						// rudimentary way and needs more work and testing.
						QueryStatement sqlStatement = SQLParserUtil.parseForDifferentDialect(editorInput, _sqlDomainModel);
						String initialSQL = SQLParserUtil.generateSQL( sqlStatement, editorInput);
						_sqlDomainModel.setSQLStatement(sqlStatement);
						_sqlDomainModel.setInitialSource(initialSQL);
						_sqlDomainModel.getSqlSourceFormat();
						
						String newSQL = initialSQL;
						_inputLoaded = _sqlDomainModel
								.initializeFromString(newSQL, null);
					} catch (Exception ex) {
						throw new PartInitException(
								Messages._ERROR_OPEN_SQL_STRING_RESOURCE);
					}
				}
			}
		}

		// Otherwise we can't tell what we have.
		else {
			throw new PartInitException(
					Messages._ERROR_INPUT_NOT_RECOGNIZED);
		}
		
		_windowStateInfo = _sqlBuilderEditorInput.getWindowStateInfo();
		_editorInputUsageOptions = _sqlBuilderEditorInput.getInputUsageOptions();
		
		// If _inputLoaded was set to false, reset it,
		// find out why and throw appropriate exception
		if (_inputLoaded == false) {
			_inputLoaded = true;
			// Test whether unmatchedSource flag was set in the domain model -
			if (_sqlDomainModel.isUnmatchedSource()){
				throw new ParseException(
						Messages._ERROR_OPEN_INPUT_PARSE_FAILED);
			}
		}

	}
	
	/**
	 * Saves the current OmitSchemaInfo for IFile parameter
	 */
	public void saveOmitSchemaInfo(IFile file){
		if (file != null){
			SQLFileUtil.setEncodedOmitSchemaInfo(file, this.getOmitSchemaInfo().encode());
		}
	}
	
	/**
	 * Creates the Source Viewer
	 */
	protected void createSourceViewer(Composite client) {
		_sourceViewer = new SQLSourceViewer(_sqlDomainModel, client, true);
		_sourceViewer.setContentChangeListener(this);
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

	}

	/**
	 * Creates the Design Viewer
	 */
	protected void createDesignViewer(Composite client) {
		_designViewer = new DesignViewer(_sqlDomainModel, client);

		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		_designViewer.setLayoutData(data);

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
	 * Returns the SQLTreeViewer.
	 * 
	 * @return SQLTreeViewer the SQLTreeViewer.
	 */
	public SQLTreeViewer getSQLTreeViewer() {
		return _contentOutlinePage;
	}
	
	/**
	 * Returns the content outline page.
	 * 
	 * @return IContentOutlinePage the content outline page.
	 */
	public IContentOutlinePage getContentOutlinePage(Composite composite) {
		if (_clientCreated && _contentOutlinePage == null) {
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

			// Calling handleContentOutlineSelection makes sure the views are initialized
			// even if the content outline is not there
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
				setGraphControlState(false);
				_sashSourceGraph.layout(true);
			} else if (_currentSelection instanceof QuerySelect) {
				setGraphControlState(true);
				_sashSourceGraph.layout(true);
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
					setGraphControlState(true);
					_sashSourceGraph.layout(true);
				} else if (queryBody instanceof QueryCombined) {
					setGraphControlState(false);
					_sashSourceGraph.layout(true);
				} else if (queryBody instanceof QueryValues) {
					setGraphControlState(false);
					_sashSourceGraph.layout(true);
				} else {
					setGraphControlState(true);
					_sashSourceGraph.layout(true);
				}
			}
		}
	}

	/*
	 * Helper function for setting the visible or enabled state of the GraphControl. If the SQLBuilder
	 * is in an editor, set its visibility; if it's not in an editor, set its enabled state.
	 */
	private void setGraphControlState(boolean state) {
		if (_editor != null){
			_graphControl.getControl().setVisible(state);
		}
		else {
			_graphControl.getControl().setEnabled(state);
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
	 * Changes the statement type in the SQLBuilder by using the statement template for the
	 * specified type.
	 * Statement type must be a constant from the StatementHelper class
	 * 
	 * @param statementType
	 */
	public void changeStatementType(int statementType){
		// Check that statement type is changing
		if (statementType != StatementHelper.getStatementType(_sqlDomainModel.getSQLStatement())){
			// Make sure graphControl is visible and enabled first
			if (!_graphControl.getControl().isVisible()){
				_graphControl.getControl().setVisible(true);
				_sashGraphOutline.layout(true);
			}
			if (!_graphControl.getControl().isEnabled()){
				_graphControl.getControl().setEnabled(true);
				_sashGraphOutline.layout(true);
			}
			
			
			// Reset the statement type in the domainModel
			_sqlDomainModel.initializeFromType(statementType);
			_sqlDomainModel.clearStatementToTemplate();
			
			// Reset the SQLTreeViewer/OutlineView input
			getSQLTreeViewer().resetInput(_sqlDomainModel.getSQLStatement());

			// Calling handleContentOutlineSelection resets the graph, design and source viewers
			handleContentOutlineSelection(
					new StructuredSelection(_sqlDomainModel.getSQLStatement()), false);
		}
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

		if (!enable && !_inCreateClient) {
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
		if (ob instanceof IOmitSchemaInfo) {
			_sqlDomainModel.setCurrentSchema();
			setDirty(true);
			_sourceViewer.refreshSource(_sqlDomainModel.getSQLStatement()
					.getSQL());
			notifyContentChange();
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
	 * Returns the current editorInputusageOptions of the <code>SQLBuilder</code>. 
	 * @return
	 */
	public ISQLBuilderEditorInputUsageOptions getEditorInputUsageOptions(){
		if (_editorInputUsageOptions == null){
			_editorInputUsageOptions = new SQLBuilderEditorInputUsageOptions(true);
		}
		return _editorInputUsageOptions;
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
					sQLBuilderFileEditorInput.setWindowStateInfo( getWindowStateInfo() );
				}

				setDirty(false);
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

    /**
     * Returns whether the contents of this <code>SQLBuilder</code> have changed since
     * the last save operation.
     *
     * @return <code>true</code> if the contents have been modified and need
     *   saving, and <code>false</code> if they have not changed since the last
     *   save
     */
  	public boolean isDirty() {
		return _sqlDomainModel.isDirty(); 
	}
	
    /**
     * Marks this SQLBuilder's statement as "dirty" (has unsaved changes).
     * 
     * @param dirty true when there are unsaved changes, otherwise false
     */
  	public void setDirty(boolean dirty){
  		_sqlDomainModel.setDirty(dirty);
  	}

  	private void restoreWindowState()
  	{
        final int UNKNOWN_VALUE = IControlStateInfo.CONTROL_STATE_UNKNOWN_VALUE;
        
        int sourceViewerHeight = UNKNOWN_VALUE;
        int graphControlWidth = UNKNOWN_VALUE;
        int graphControlHeight = UNKNOWN_VALUE;
        int outlineViewerWidth = UNKNOWN_VALUE;       
        int designViewerHeight = UNKNOWN_VALUE;

        
        //
        // Get parts of the StateInfo
        //
        
        IWindowStateInfo windowStateInfo = _sqlBuilderEditorInput.getWindowStateInfo();
        if ( windowStateInfo == null ){
            return;     // no state info to restore from
        }
        
        // Get height for SourceViewer
        IControlStateInfo sourceViewerState = windowStateInfo.get( 
        		IControlStateInfo.SOURCE_CONTROL );
        if ( sourceViewerState != null )
            sourceViewerHeight = sourceViewerState.getHeight();
        
        // Get width and height for GraphicalViewer
        IControlStateInfo graphViewerState = windowStateInfo.get( 
        		IControlStateInfo.GRAPHICAL_CONTROL );
        if ( graphViewerState != null )
        {
           	graphControlWidth = graphViewerState.getWidth();
            graphControlHeight = graphViewerState.getHeight();
        }
        
        // Get width for OutlineViewer
        IControlStateInfo outlineViewerState = windowStateInfo.get( 
        		IControlStateInfo.OUTLINE_CONTROL );
        if ( outlineViewerState != null )
        {
           	outlineViewerWidth = outlineViewerState.getWidth();
        }
        
        // Get height for DesignViewer
        IControlStateInfo designViewerState = windowStateInfo.get( 
    		   IControlStateInfo.DESIGN_CONTROL );
        if ( designViewerState != null )
        {
            designViewerHeight = designViewerState.getHeight();
        }
        
        //
        // Restore the Window state to the settings found
        //
        
        // set all weights before setting hidden
        // _sashMain
        if (sourceViewerHeight != UNKNOWN_VALUE && graphControlHeight != UNKNOWN_VALUE
        		&&  designViewerHeight != UNKNOWN_VALUE){
        	_sashMain.setWeights(new int[]{sourceViewerHeight + graphControlHeight, designViewerHeight});      	
        }
        else {
        	_sashMain.setWeights(new int[]{DEFAULT_SASHMAIN_WEIGHT1, DEFAULT_SASHMAIN_WEIGHT2});
        }
        // _sashSourceGraph
        if (sourceViewerHeight != UNKNOWN_VALUE && graphControlHeight != UNKNOWN_VALUE){
        	_sashSourceGraph.setWeights(new int[]{sourceViewerHeight, graphControlHeight});
        }
        else {
           	_sashSourceGraph.setWeights(new int[]{DEFAULT_SASHSOURCEGRAPH_WEIGHT1, DEFAULT_SASHSOURCEGRAPH_WEIGHT2});
        }
        // _sashGraphOutline
        // Must check for _sashGraphOutline's existence because it's not present in the editor
        if (_sashGraphOutline != null){
        	if (graphControlWidth != UNKNOWN_VALUE && outlineViewerWidth != UNKNOWN_VALUE){
        		_sashGraphOutline.setWeights(new int[]{graphControlWidth, outlineViewerWidth});
        	}
        	else {
        		_sashGraphOutline.setWeights(new int[]{DEFAULT_SASHGRAPHOUTLINE_WEIGHT1, DEFAULT_SASHGRAPHOUTLINE_WEIGHT2});
        	}
       }

        
        // set hidden
        // _sashMain
    	if (designViewerState != null && !designViewerState.isVisible()){
    		hideDown(_sashMain);
    	}
        // _sashSourceGraph
    	if (sourceViewerState != null && ! sourceViewerState.isVisible()){
    		hideUp(_sashSourceGraph);
    	}
    	else if (graphViewerState != null && ! graphViewerState.isVisible()){
    		// if there's no outline view, hideDown
    		if (_sashGraphOutline == null){
    			hideDown(_sashSourceGraph);
    		}
    		// else if the outlineViewer should also be hidden, hideDown
    		else if (outlineViewerState != null && ! outlineViewerState.isVisible()){
    			hideDown(_sashSourceGraph);
    		}
    	}
        // _sashGraphOutline
        // Must check for _sashGraphOutline's existence because it's not present in the editor
        if (_sashGraphOutline != null){
        	if (graphViewerState != null && ! graphViewerState.isVisible()
        			&& outlineViewerState != null && ! outlineViewerState.isVisible()){
        		// both should be hidden, so would call hideDown(_sashSourceGraph);
        		// but it's already been done
        	}
        	else if (graphViewerState != null && ! graphViewerState.isVisible()){
        		hideLeft(_sashGraphOutline);
       		}
        	else if (outlineViewerState != null && ! outlineViewerState.isVisible()){
       			hideRight(_sashGraphOutline);
        	}
       }
        
        // Set hide / restore arrows
        if (designViewerState != null && !designViewerState.isHideable()){
        	_sashMain.setNoHideDown(true);
        }
        if (sourceViewerState != null && !sourceViewerState.isHideable()){
        	_sashSourceGraph.setNoHideUp(true);
        }
        if (graphViewerState != null && !graphViewerState.isHideable()){
        	_sashSourceGraph.setNoHideDown(true);
        }
        if (_sashGraphOutline != null){
        	if (outlineViewerState != null && !outlineViewerState.isHideable()){
        		_sashGraphOutline.setNoHideRight(true);
        	}
        }
  	}
  	
  	/*
  	 * Helper method for restoreWindowState
  	 * Forces a hideUp, even if NoHideUp is set for the control
  	 */
	private void hideUp(CustomSashForm sashForm) {
		boolean tmpNoHideUp = sashForm.isNoHideUp();
		sashForm.setNoHideUp(false);
		sashForm.hideUp();
		sashForm.setNoHideUp(tmpNoHideUp);
	}

  	/*
  	 * Helper method for restoreWindowState
  	 * Forces a hideLeft, even if NoHideLeft is set for the control
  	 */
	private void hideLeft(CustomSashForm sashForm) {
		hideUp(sashForm);
	}

	/*
  	 * Helper method for restoreWindowState
  	 * Forces a hideDown, even if NoHideDown is set for the control
  	 */
	private void hideDown(CustomSashForm sashForm) {
		boolean tmpNoHideDown = sashForm.isNoHideDown();
		sashForm.setNoHideDown(false);
		sashForm.hideDown();
		sashForm.setNoHideDown(tmpNoHideDown);
	}

  	/*
  	 * Helper method for restoreWindowState
  	 * Forces a hideRight, even if NoHideRight is set for the control
  	 */
	private void hideRight(CustomSashForm sashForm) {
		hideDown(sashForm);
	}

	/**
	 * Returns the current window state of the <code>SQLBuilder</code>.
	 * @return
	 */
	public IWindowStateInfo getWindowStateInfo()
	{
		// Get the current window state for the SQLBuilder and return it
		if (_windowStateInfo == null){
			_windowStateInfo = new WindowStateInfo();
		}

		saveWindowStateInfo();

		return _windowStateInfo;
	}

	/*
	 * Saves the current WindowStateInfo
	 */
	private void saveWindowStateInfo() {
		_windowStateInfo.setHeight( _parentControl.getSize().y );
		_windowStateInfo.setWidth( _parentControl.getSize().x );
		
		
		// Source control
		IControlStateInfo sourceState = getControlState(_windowStateInfo, IControlStateInfo.SOURCE_CONTROL);
		setControlStateSize(sourceState, getSourceViewer().getControl(), _sashSourceGraph, 0);
		sourceState.setIsHideable(!_sashSourceGraph.isNoHideUp());
		sourceState.setIsVisible(_sashSourceGraph.getWeights()[0] != 0);
		
		// Graphical control
		IControlStateInfo graphicalState = getControlState(_windowStateInfo, IControlStateInfo.GRAPHICAL_CONTROL);
		setControlStateSize(graphicalState, getGraphViewer().getControl(), _sashSourceGraph, 1);
		graphicalState.setIsHideable(!_sashSourceGraph.isNoHideDown());
		graphicalState.setIsVisible(_sashSourceGraph.getWeights()[1] != 0);
		
		// Outline control - if SQB is in an editor, there isn't one
		if (_editor != null){
			_windowStateInfo.remove(IControlStateInfo.OUTLINE_CONTROL);
		}
		else {
			IControlStateInfo outlineState = getControlState(_windowStateInfo, IControlStateInfo.OUTLINE_CONTROL);
			setControlStateSize(outlineState, getSQLTreeViewer().getControl(), _sashGraphOutline, 1);
			outlineState.setIsHideable(!_sashGraphOutline.isNoHideRight());
			outlineState.setIsVisible(_sashGraphOutline.getWeights()[1] != 0);
		}
		
		// Design control
		IControlStateInfo designState = getControlState(_windowStateInfo, IControlStateInfo.DESIGN_CONTROL);
		setControlStateSize(designState, getDesignViewer(), _sashMain, 1);
		designState.setIsHideable(!_sashMain.isNoHideDown());
		designState.setIsVisible(_sashMain.getWeights()[1] != 0);
	}

	/*
	 * Helper method for getWindowStateInfo()
	 */
	private IControlStateInfo getControlState(IWindowStateInfo windowStateInfo, int controlType){
		IControlStateInfo state = windowStateInfo.get(controlType);
		if (state == null){
			state = new ControlStateInfo();
			_windowStateInfo.put(controlType, state);
		}
		return state;
	}
	
	/*
	 * Helper method for getWindowStateInfo()
	 */
	private void setControlStateSize(IControlStateInfo stateInfo, Control control, CustomSashForm sashForm, int iSashIndex){
		Point size = control.getSize();
		int [] weights = sashForm.getWeights();
		if (weights[0] != 0 && weights[1] != 0 ){
			stateInfo.setWidth(size.x);
			stateInfo.setHeight(size.y);
			return;
		}
		else if (sashForm.getOrientation() == SWT.VERTICAL){
			stateInfo.setWidth(size.x);
			if (sashForm.getSavedSizes() != null
					&& sashForm.getSavedSizes().length > iSashIndex
					&& sashForm.getSavedSizes()[iSashIndex] != null) {
				stateInfo.setHeight(sashForm.getSavedSizes()[iSashIndex].y);
			}
			else {
				stateInfo.setHeight(100);
			}
		}
		else if (sashForm.getOrientation() == SWT.HORIZONTAL){
			if (sashForm.getSavedSizes() != null
					&& sashForm.getSavedSizes().length > iSashIndex
					&& sashForm.getSavedSizes()[iSashIndex] != null) {
				stateInfo.setWidth(sashForm.getSavedSizes()[iSashIndex].x);
			}
			else {
				stateInfo.setWidth(200);
			}
			stateInfo.setHeight(size.y);
		}
	}
	
	/*
	 * 
	 */
	protected boolean validateBeforeSave() {
		return SQLBuilderPlugin.getPlugin().getPreferenceStore().getBoolean(
				SQLBuilderConstants.P_VALIDATE_BEFORE_SAVE);
	}
	
	/**
	 * Called when content has changed. This can be a change to the SQL or the
	 * OmitSchemaInfo.
	 * 
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.IContentChangeListener#notifyContentChange()
	 */
	public void notifyContentChange() {
		 Object[] listeners = _contentChangeListeners.getListeners();
		 for (int i = 0; i < listeners.length; ++i) {
		 	((IContentChangeListener) listeners[i]).notifyContentChange();
		 }
	}

	/**
	 * Called when SQL statement has been executed.
	 */
	public void notifySQLExecuted(){
		 Object[] listeners = _executeSQLListeners.getListeners();
		 for (int i = 0; i < listeners.length; ++i) {
		 	((IExecuteSQLListener) listeners[i]).executedSQL();
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
			IAction changeStatementTypeAction = _actionBarContributor
					.getAction(SQLBuilderActionBarContributor.CHANGE_STATEMENT_TYPE_ACTION_ID);
			changeStatementTypeAction.setEnabled(true);
			
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

	public String getSQL() {
		return _sqlDomainModel.getSQLStatement().getSQL();
	}

    /**
     * Gets the <code>ISQLEditorConnectionInfo</code> object associated with this
     * SQLBuilder's statement.
     * 
     * @return ISQLEditorConnectionInfo the SQLBuilder's SQLEditorConnectionInfo object.
     */
	public ISQLEditorConnectionInfo getConnectionInfo() {
		return _sqlDomainModel.getConnectionInfo();
	}

    /**
     * Gets the <code>OmitSchemaInfo</code> object associated with this statement
     *  
     * @return OmitSchemaInfo the SQLBuilder's OmitSchemaInfo object.
     */
	public IOmitSchemaInfo getOmitSchemaInfo() {
		return _sqlDomainModel.getOmitSchemaInfo();
	}
	
	/**
	 * Tries to make sure that we have a database connection so that the SQL model
	 * will be populated when we need it. This allows us to delay connecting so that the user
	 * won't get prompted when the Workbench is coming up.
	 * 
	 * This function should be called by editors based on SQLBuilder when the Workbench opens
	 * with the editor having been open when the workbench last closed. 
	 */
	public void connectIfNeeded(IWorkbenchPart part ) {
		Database db = _sqlDomainModel.getDatabase();
		if (db == null) {
			boolean keepTrying = true;

			/* Find out if we're visible yet. */
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow activeWindow = workbench
					.getActiveWorkbenchWindow();
			if (activeWindow != null) {
				IWorkbenchPage activePage = activeWindow.getActivePage();
				if (activePage != null) {
					if (activePage.isPartVisible(part)) {
						/*
						 * Once we're visible, try re-establishing the
						 * connection.
						 */
						keepTrying = false;
						
						ISQLEditorConnectionInfo connInfo = _sqlDomainModel
								.getConnectionInfo();
						if (connInfo != null) {
							// SQLDBUtils.reestablishConnection(
							// connInfo );
							// The call to connInfo.getDatabase() tries to connect to the database 
							db = connInfo.getDatabase();
							if (db != null){
								try {
									// Now we have a connection, load the input and finish
									// creating the client
									loadInput();
									doCreateClient();
								} catch (PartInitException e) {
									e.printStackTrace();
								}
								catch (ParseException e){
									e.printStackTrace();
								}
							}
						}
					}
				}
			}

			/*
			 * If we still don't have a connection and we didn't cancel the
			 * connection attempt, try it again later.
			 */
			if (db == null && keepTrying == true) {
				ConnectRunnable connectRunnable = new ConnectRunnable(part, this);
				Display display = workbench.getDisplay();
				int delayTime = 1000; // one second
				display.timerExec(delayTime, connectRunnable);
			}
		}
	}
	
}
