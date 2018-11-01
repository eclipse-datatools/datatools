/*******************************************************************************
 * Copyright (c) 2005 Exadel Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import java.lang.reflect.Method;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionInitializer;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.internal.externalfile.ExternalSQLFileEditorInput;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions.ScrapbookExecuteSQLAction;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions.ScrapbookExecuteSelectionSQLAction;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions.SetConnectionInfoAction;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.AbstractConnectionInfoComposite;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.ConnectionInfoComposite2;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.util.SQLFileUtil;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.GenericSQLMatchingPairs;
import org.eclipse.datatools.sqltools.sqleditor.internal.matching.GenericSQLPairMatcher;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.source.ICharacterPairMatcher;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.ILocationProvider;

public class SQLScrapbookEditor extends SQLEditor {

	public static final String EDITOR_ID = EditorConstants.SQLFILE_EDITOR_ID;

	private Map /* <ISQLEditorConnectionInfo, ScrapbookOpenedConnection> */ connections;
	private ConnectionService _conService = null;
	    
    public class ToolbarSourceViewer extends AdaptedSourceViewer implements Listener
    {
        private AbstractConnectionInfoComposite connBar;
        private boolean initialized = false;
        public ToolbarSourceViewer(Composite parent, IVerticalRuler verticalRuler,
                IOverviewRuler overviewRuler, boolean showAnnotationsOverview, int styles, ISQLEditorConnectionInfo initialConnInfo)
        {
            super(parent, verticalRuler, overviewRuler, showAnnotationsOverview, styles);
            //by now, createControl has been called
            if (initialConnInfo != null)
            {
            	connBar.init(initialConnInfo);
            }
        }
        
        protected void createControl(Composite parent, int styles)
        {
            Composite fDefaultComposite= new Composite(parent, SWT.NONE);
            GridLayout gridLayout = new GridLayout();
            gridLayout.numColumns = 1;
            gridLayout.horizontalSpacing = 0;
            gridLayout.marginWidth = 0;
            gridLayout.marginBottom = 0;
            
            fDefaultComposite.setLayout(gridLayout);
            fDefaultComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

            //since SQL Editor is always left to right, we need to tell the composite explicitly about the orientation
            //don't call SQLScrapbookEditor.this.getConnectionInfo() because of NPE
            connBar = new ConnectionInfoComposite2(fDefaultComposite, Window.getDefaultOrientation(), this, 
                    null, null, AbstractConnectionInfoComposite.STYLE_SHOW_COMMIT_MODE | AbstractConnectionInfoComposite.STYLE_SEPARATE_TYPE_NAME
                    | AbstractConnectionInfoComposite.STYLE_SHOW_STATUS | AbstractConnectionInfoComposite.STYLE_SINGLE_GROUP | AbstractConnectionInfoComposite.STYLE_LAZY_INIT);
            connBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            ((GridLayout)connBar.getLayout()).marginWidth = 12;
            
            
            Composite textComposite= new Composite(fDefaultComposite, SWT.NONE);
            textComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            textComposite.setLayout(new FillLayout());
            super.createControl(textComposite, styles);
            
        }

        public void handleEvent(Event event)
        {
            String content = getDocument().get();
            doSetConnectionInfo(connBar.getConnectionInfo());
            getDocument().set(content);
            
            SQLScrapbookEditor.this.refreshMatcher();
        }
        
        public void refreshConnectionStatus()
        {
            if (connBar != null)
            {
                if (!initialized)
                {
                    connBar.init();
                    initialized = true;
                }
                
                //refresh commit mode info in ConnectionInfoComposite according commit mode info from editor input.
                if(connBar.getConnectionInfo() instanceof ScrapbookEditorConnectionInfo 
                        && getConnectionInfo() instanceof ScrapbookEditorConnectionInfo)
                {
                    ((ScrapbookEditorConnectionInfo) connBar.getConnectionInfo()).setAutoCommit(
                            ((ScrapbookEditorConnectionInfo) getConnectionInfo()).isAuto());
                }
                
                connBar.refreshConnectionStatus();
            }
        }
        
        protected void handleDispose()
        {
            if(this.getDocument()!= null && SQLScrapbookEditor.this._fSQLUpdater != null)
            {
                this.getDocument().removeDocumentListener(SQLScrapbookEditor.this._fSQLUpdater);
            }
            super.handleDispose();
        }
    }
    
	private IAction _setConnectionInfoAction;
	
	public SQLScrapbookEditor() {
		super();
		this.connections = new HashMap();
	}

	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
	}
	
	protected void initializeEditor() {
		super.initializeEditor();
		setRulerContextMenuId("#SQLScrapbookEditorRulerContext");
        setDocumentProvider(SqlscrapbookPlugin.getDefault().getSQLEditorDocumentProvider());
	}

	public void doSetInput(IEditorInput input) throws CoreException {
		ISQLEditorInput newInput = null;
		if (input instanceof SQLScrapbookEditorInput) {
            newInput = (SQLScrapbookEditorInput) input;
        } else if (input instanceof IFileEditorInput) {
			newInput = new SQLScrapbookEditorInput(((IFileEditorInput) input).getFile());
        } else if (input instanceof IPathEditorInput) {
        	IFileStore store = EFS.getLocalFileSystem().getStore(((IPathEditorInput) input).getPath());
        	newInput = new ExternalSQLFileEditorInput(store);
        } else if (input instanceof ILocationProvider) {
        	IFileStore store = EFS.getLocalFileSystem().getStore(((ILocationProvider) input).getPath(input));
        	newInput = new ExternalSQLFileEditorInput(store);
		} else if (input instanceof IAdaptable) {
			URI uri = (URI)((IAdaptable)input).getAdapter(URI.class);
			if (uri == null)
			{
				//TO run DTP both on Eclipse 3.2 and 3.3, we have to use java reflection to get the URI info from Eclipse 3.3 object FileStoreEditorInput
				Class clazz = input.getClass();
				try {
					Method getURI = clazz.getMethod("getURI", null);
					uri = (URI)getURI.invoke(input, null);
				} catch (Exception e) {
				}
			}
			if (uri != null)
			{
				IFileStore store = EFS.getStore(uri);
				newInput = new ExternalSQLFileEditorInput(store);
			}
		}
		
		if (newInput == null){
			super.doSetInput(input);			
		} else {
			if (input instanceof SQLScrapbookEditorInput) {
				((SQLScrapbookEditorInput)newInput).setEditorSite(this.getEditorSite());
			}
			super.doSetInput(newInput);			
		}
		
	}

	protected void createActions() {
		super.createActions();
        ResourceBundle bundle = Messages.getBundleForConstructedKeys();

        _setConnectionInfoAction = new SetConnectionInfoAction(bundle,
						"SetConnectionInfo.", this);
		setAction( ISQLEditorActionConstants.ATTACHE_PROFILE_ACTION_ID, _setConnectionInfoAction ); //$NON-NLS-1$

	    // Replace the execute selected/all SQL actions with scrapbook own corresponding actions.
        IActionBars bars = ((IEditorSite) getSite()).getActionBars();
        
        setAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID, new ScrapbookExecuteSelectionSQLAction(this));
        markAsSelectionDependentAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID, true);
        bars.setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID,
                getAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID));
        
        setAction(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID, new ScrapbookExecuteSQLAction(this));
        bars.setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID,
                getAction(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID));
	}

	protected void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
        menu.add( new Separator() );
        addAction( menu, ISQLEditorActionConstants.ATTACHE_PROFILE_ACTION_ID); //$NON-NLS-1$
	}

    public void requestConnection()
    {
    	_setConnectionInfoAction.run();
    }
    
	public void setConnectionInfo(ISQLEditorConnectionInfo connInfo) {
	    this.doSetConnectionInfo(connInfo);
        //updates the internal toolbar when user changes the connection info
        getSite().getShell().getDisplay().asyncExec(new Runnable(){
            public void run()
            {
                AbstractConnectionInfoComposite connBar = ((ToolbarSourceViewer) getSV()).connBar;
                
                connBar.init(
                        getConnectionInfo().getDatabaseVendorDefinitionId().toString(), getConnectionInfo()
                        .getConnectionProfileName(), getConnectionInfo().getDatabaseName());
                
                // Set the commit-mode info when scrapbook doSave as another one.
                if(connBar.getConnectionInfo() instanceof ScrapbookEditorConnectionInfo 
                        && getConnectionInfo() instanceof ScrapbookEditorConnectionInfo)
                {
                    ((ScrapbookEditorConnectionInfo) connBar.getConnectionInfo()).setAutoCommit(
                            ((ScrapbookEditorConnectionInfo) getConnectionInfo()).isAuto());
                }
                
                connBar.refreshConnectionStatus();
            }
        });
	}	

	protected void doSetConnectionInfo(ISQLEditorConnectionInfo connInfo) {
        ISQLEditorConnectionInfo preConnInfo = getConnectionInfo();

        ISQLEditorConnectionInfo scrapbookConnInfo = connInfo instanceof ScrapbookEditorConnectionInfo ? connInfo : SQLFileUtil.getConnectionInfo4Scrapbook(connInfo);
        if (scrapbookConnInfo.encode().equals(preConnInfo.encode()))
        {
            super.setConnectionInfo(scrapbookConnInfo);
        }
        else
        {
            String content = getSV().getDocument().get();
            super.setConnectionInfo(scrapbookConnInfo);
            getSV().getDocument().set(content);
        }
	    
	    // refresh title tooltip
	    getSite().getShell().getDisplay().asyncExec(new Runnable(){
            public void run()
            {
                setTitleToolTip(getTitleToolTip());
            }
        });
	}	
	
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		// Release the connection
        releaseConnection();
	}	
	
    public void doSave(IProgressMonitor monitor)
    {
        if (getEditorInput() instanceof SQLEditorStorageEditorInput)
        {
            super.doSaveAs();
        }
        
        super.doSave(monitor);
    }
    
    public void doSaveAs()
    {
        super.doSaveAs();
        super.doSave(null);
    }
    
    public void createPartControl(Composite parent)
    {
        super.createPartControl(parent);
    }
    
    protected AdaptedSourceViewer doCreateSourceViewer(Composite parent, IVerticalRuler ruler, int styles)
    {
        return new ToolbarSourceViewer(parent, ruler, getOverviewRuler(), isOverviewRulerVisible(),
                styles, getConnectionInfo());
    }
    
    public void refreshConnectionStatus()
    {
        super.refreshConnectionStatus();
        if (getSV() != null)
        {
            ((ToolbarSourceViewer)getSV()).refreshConnectionStatus();
        }
    }

    public void refreshMatcher()
    {
        // When the connection profile is changed, the corresponding matcher will be set.
        SQLDevToolsUIConfiguration sqlDevToolsConfig = SQLToolsUIFacade.getConfiguration(getDBType(),
                getDatabaseIdentifier());

        // It needs support by sql editor service of each specific database.
        ICharacterPairMatcher matcher = sqlDevToolsConfig.getSQLEditorUIService().getSQLPairMatcher();
        
        // If it's not supported, use default generic sql matcher.
        if(matcher == null)
        {
            matcher = new GenericSQLPairMatcher(GenericSQLMatchingPairs.getInstance());
        }
        
        SQLScrapbookEditor.this.setPairMatcher(matcher);
    }
    
    /**
     * Get the connection which this SQLScrapbookEditor is holding.
     * 
     * @return <li>A connection which auto-commit is false, when commit-mode is manual.
     *      <p><li><code>null</code>, when commit-mode is automatic.
     */
    public Connection getConnection()
    {
        ISQLEditorConnectionInfo connInfo = getConnectionInfo();
        
        if(connInfo instanceof ScrapbookEditorConnectionInfo)
        {
        	ScrapbookEditorConnectionInfo scrapbookConnInfo = ((ScrapbookEditorConnectionInfo) connInfo);
            if(scrapbookConnInfo.isAuto())
            {
                return null;
            }
        }
        
        try
        {
        	ScrapbookOpenedConnection openedConnection = (ScrapbookOpenedConnection) this.connections.get(connInfo);
        	if (openedConnection != null)
        	{
        		Connection _connection = openedConnection.getConnection();
        		if(_connection == null || _connection.isClosed())
        		{
        			fetchConnection();
        		}
        	}
        	else
        	{
        		fetchConnection();
        	}
        }
        catch (SQLException e)
        {
            SqlscrapbookPlugin.log(e);
        }
        
        ScrapbookOpenedConnection openedConnection = (ScrapbookOpenedConnection) this.connections.get(connInfo);
        return openedConnection.getConnection();
    }
    
    /**
     * Get a available connection from connection pool and set its auto-commit to be false
     * to support operation of manual commit mode.
     */
    private void fetchConnection()
    {
        try
        {
            String profileName = getConnectionInfo().getConnectionProfileName();
            String dbName = getConnectionInfo().getDatabaseName();
            DatabaseIdentifier _databaseIdentifier = new DatabaseIdentifier(profileName, dbName);
            
            SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByProfileName(_databaseIdentifier.getProfileName());
            _conService = f.getConnectionService();
            
            Connection _connection = _conService.createConnection(_databaseIdentifier, true);
            int _connid = SQLToolsFacade.getConnectionId(_databaseIdentifier, _connection);
            this.connections.put(getConnectionInfo(), new ScrapbookOpenedConnection(_databaseIdentifier, _connection, _connid));
            
            IConnectionInitializer init = SQLToolsFacade.getConfiguration(null, _databaseIdentifier).getConnectionService().getConnectionInitializer();
            if (init != null)
            {
                //if the configuration is null, will use the default options to initialize
                init.init(_databaseIdentifier, _connection);
            }
            
            if(_connection != null && !_connection.isClosed())
            {
                _connection.setAutoCommit(false);
            }
        }
        catch (SQLException e)
        {
            SqlscrapbookPlugin.log(e);
        }
        catch (NoSuchProfileException e)
        {
            SqlscrapbookPlugin.log(e);
        }
    }
    
    /**
     * Set the auto-commit to be true and release the connection back to connection pool.
     */
    private void releaseConnection()
    {
    	Collection allConnections = this.connections.values();
    	Iterator entriesIterator = allConnections.iterator();
    	
    	while (entriesIterator.hasNext()) {
			ScrapbookOpenedConnection currOpenedConnection = (ScrapbookOpenedConnection) entriesIterator.next();
			DatabaseIdentifier _databaseIdentifier = currOpenedConnection.getDatabaseIdentifier();
			Connection _connection = currOpenedConnection.getConnection();
			int _connid = currOpenedConnection.getConnid();

			if(_connection == null)
	        {
	            return;
	        }
	        
	        try
	        {
	            if(_conService != null)
	            {
	                if(!_connection.isClosed())
	                {
	                    _connection.setAutoCommit(true);
	                }
	                _conService.closeConnection(_connection, _connid, _databaseIdentifier);
	            }
	        }
	        catch (SQLException e)
	        {
	            SqlscrapbookPlugin.log(e);
	        }
		}
    }
}
