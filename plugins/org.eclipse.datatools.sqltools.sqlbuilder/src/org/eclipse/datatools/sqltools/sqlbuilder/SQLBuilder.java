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
import java.util.EventObject;
import java.util.Iterator;
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
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
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
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Sash;
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
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;


/**
 * SQL Query Builder content editor
 */
public class SQLBuilder extends EditorPart implements IEditingDomainProvider, ISelectionProvider, IMenuListener, QueryEventListener,
	Observer
		 {

    protected SQLDomainModel sqlDomainModel;
    protected DesignViewer designViewer;
    protected SQLSourceViewer sourceViewer;
    protected GraphControl graphControl;
    protected SQLTreeViewer contentOutlinePage;
    protected IFile ifile;
    protected boolean created;
    ISQLBuilderEditorInput sqlBuilderEditorInput = null;

    // The following variable are for EMF.edit stuff
    protected AdapterFactoryEditingDomain editingDomain;
    protected SQLBuilderActionBarContributor actionBarContributor;
    protected Object currentSelection;
    protected PageBook pageBook;
    protected SashForm sashForm = null;
    protected Sash sourceAndGraphSash = null;    

    // keep track of the selection of the editor
    protected Collection selectionChangedListeners = new ArrayList();
    protected ISelection editorSelection;

    // flag to detect if resource is removed to prevent model operations
    protected boolean resourceRemoved = false;
    
    // flag to allow for delayed initialization of the connection and Database object
    protected boolean firstFocus = true;
    
    public static SQLBuilder builder = null;

    class ConnectRunnable implements Runnable {
        SQLBuilder fEditor;
        
        public ConnectRunnable( SQLBuilder editor ) {
            fEditor = editor;
        }
        
        public void run() {
            fEditor.connectIfNeeded();
        };
    };

    
    public SQLBuilder() {
        super();

        builder = this;
        sqlDomainModel = new SQLDomainModel();  

        BasicCommandStack commandStack = new BasicCommandStack();
        commandStack.addCommandStackListener(new CommandStackListener() {

            public void commandStackChanged(EventObject event) {
            }
        });

        // Create the editing domain with a special command stack.
        editingDomain = new AdapterFactoryEditingDomain(SQLBuilderPlugin.getAdapterFactory(), commandStack);

        sqlDomainModel.setEditingDomain(editingDomain);
    }

    public boolean inValidateEditCall() {
        //return propertyResourceChangeListener.inValidateEditCall();
        return false;
    }

    public IFile getInputResource() {
        return ifile;
    }

    public void menuAboutToShow(IMenuManager menuManager) {
        menuManager.add(new Separator("additions")); //$NON-NLS-1$
        menuManager.add(new Separator("edit")); //$NON-NLS-1$
        contentOutlinePage.fillContextMenu();

        if (actionBarContributor != null) {
            boolean enableRevert = !SQLBuilder.isStatementProper(sqlDomainModel);
            IAction revertToDefaultAction = actionBarContributor.getAction( SQLBuilderActionBarContributor.REVERT_TO_DEFAULT_ACTION_ID );
            if (revertToDefaultAction != null) {
                revertToDefaultAction.setEnabled(enableRevert);
            }
            
            IAction revertToPreviousAction = actionBarContributor.getAction( SQLBuilderActionBarContributor.REVERT_TO_PREVIOUS_ACTION_ID );
            if (revertToPreviousAction != null) {
                revertToPreviousAction.setEnabled(enableRevert);
            }
            IAction omitCurrentSchemaAction =  actionBarContributor.getAction( SQLBuilderActionBarContributor.OMIT_CURRENT_SCHEMA_ACTION_ID );
            if (omitCurrentSchemaAction != null) {
            	boolean enableOmitCurrentSchema = false;
            	if (getDomainModel() != null && getDomainModel().getDatabaseDefinition() != null){
            		enableOmitCurrentSchema = getDomainModel().getDatabaseDefinition().supportsSchema();
            	}
            	else {
            		enableOmitCurrentSchema = false;
            	}
            	omitCurrentSchemaAction.setEnabled(enableOmitCurrentSchema);
            }
        }
    }

    public MenuManager createContextMenuFor(Viewer viewer) {
        MenuManager contextMenu = new MenuManager("#PopUp"); //$NON-NLS-1$
        contextMenu.add(new Separator("additions")); //$NON-NLS-1$
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);

        Menu menu = contextMenu.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        // Note - contextMenu.getId() returns null at this point.
        getEditorSite().registerContextMenu(contextMenu, viewer);

        return contextMenu;
    }

    public void setFocus() {
        if (sourceViewer != null) {
            sourceViewer.getControl().setFocus();
        }

        /* Try to make sure that we have a database connection so that the 
         * SQL model will be populated when we need it.  We delay connecting
         * so that the user won't get prompted when the Workbench is coming up. */
        if (firstFocus == true) {
            connectIfNeeded();
            firstFocus = false;   
        }
        
        /* Make sure the connection status message is up to date.  It doesn't
         * automatically get updated if the user switches editor instances.
         */
        refreshConnectionStatus();
    }

    private Composite createNestedComposite(Composite parent, int style) {
        Composite comp = new Composite(parent, style);
        GridLayout compLayout = new GridLayout();
        compLayout.marginHeight = 2;
        compLayout.marginWidth = 2;
        comp.setLayout(compLayout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        comp.setLayoutData(data);

        return comp;
    }

    /**
     * Create the UI
     */
    public void createPartControl(Composite composite) {
        sashForm = new SashForm(composite, SWT.VERTICAL);

        // composite for src & label to go on sash
        Composite outsideSrcComp = createNestedComposite(sashForm, SWT.NONE);
        // ratio 0.30
        outsideSrcComp.setData("layout ratio", new Long((((long) 30 << 16) + 99) / 100)); //$NON-NLS-1$

        // composite for src viewer to add border
        Composite srcComposite = createNestedComposite(outsideSrcComp, SWT.BORDER);
        createSourceViewer(srcComposite);

        createGraphViewer(sashForm);

        createDesignViewer(sashForm);

        sashForm.setLayoutData(ViewUtility.createFill());

        getContentOutlinePage(); // make sure everything is initialized        

        ((IChangeNotifier) sqlDomainModel.getAdapterFactory()).addListener(new INotifyChangedListener() {

            //public void notifyChanged(Object object, int eventType, Object
            // feature, Object oldValue, Object newValue, int index)
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

        boolean isProper = sqlDomainModel.isProper();
        updateProperStatement(isProper);
        graphControl.refresh();
        graphControl.setSQLBuilder(this);

        if (!created) {
            //String strSQL = WorkbenchUtility.readFileContentsToString(ifile, true );
            String strSQL = sqlDomainModel.getInitialSource();
            if (strSQL.trim().length() > 0) {
                sourceViewer.setFileSQLStr(strSQL);
            }
        } 

    }

    public void notifyContentChange() {
        updateDirtyStatus();
    }

    public void updateDirtyStatus() {
        firePropertyChange(IEditorPart.PROP_DIRTY);
    }

    public boolean isDirty() {
        boolean dirty = sqlDomainModel.isDirty();;
                
        return dirty;
    }

    public SQLBuilderActionBarContributor getActionBarContributor() {
        return actionBarContributor;
    }
    
    /**
     * Sets the action contributor associated with this editor.  This is called by 
     * the contributor itself.
     */
    public void setActionBarContributor(SQLBuilderActionBarContributor contributor) {
        this.actionBarContributor = contributor;
    }

    protected boolean validateBeforeSave() {
        return SQLBuilderPlugin.getPlugin().getPreferenceStore().getBoolean(SQLBuilderConstants.P_VALIDATE_BEFORE_SAVE);
    }

    public void reparseIfRequired() {
        if (sourceViewer.isTextChanged()) {
            sourceViewer.reparse();
        }
    }

    /**
     * Saves the contents of this editor.
     * <p>
     * @param monitor the progress monitor to use
     * @see org.eclipse.ui#dosave(IProgressMonitor progressMonitor)
     */
    public void doSave(IProgressMonitor progressMonitor) {

        // Bug 3022 : No need to reparse, since the statement is already parsed once.
        // sourceViewer.forceReparse();
        // RATLC01136221 bgp 10Jan2007 - begin
        // Might not have a progressMonitor at all
        if (progressMonitor == null || !progressMonitor.isCanceled()) {
            boolean result = true;
            if (progressMonitor != null)
            	progressMonitor.setTaskName(Messages._UI_SAVING_STATEMENT);
            
            // Sets the text to be used for saving
            sqlDomainModel.setEditorText(sourceViewer.getText());
            result = sqlDomainModel.save();

            if (result == false) {
            	if (progressMonitor!= null)
            		progressMonitor.setCanceled(true);
                updateDirtyStatus();
            }
            else {
            	if (sqlBuilderEditorInput != null
            			&& sqlBuilderEditorInput instanceof SQLBuilderFileEditorInput
            			&& sqlDomainModel.getIFile() != null){
            		SQLBuilderFileEditorInput sQLBuilderFileEditorInput = (SQLBuilderFileEditorInput)sqlBuilderEditorInput;
            		SQLFileUtil.setEncodedOmitSchemaInfo(sQLBuilderFileEditorInput.getFile(), sQLBuilderFileEditorInput.getOmitSchemaInfo().encode());
            	}
            	
                sqlDomainModel.setDirty(false);
                if (sourceViewer != null) {
                    sourceViewer.setTextChanged(false);
                }
            }

            // TODO: Why is this code here?
            // The following code doesn't seem to ever do anything, since I
            // can't find anywhere were ifile is set to a non-null value 
            if (progressMonitor != null && !progressMonitor.isCanceled()) {
                WorkbenchUtility.refreshLocalWorkspaceFile(ifile, progressMonitor);
            }
            updateDirtyStatus();
        }
        // RATLC01136221 bgp 10Jan2007 - end
    }

    /**
     * Try to make sure that we have a database connection so that the 
     * SQL model will be populated when we need it.  We delay connecting
     * so that the user won't get prompted when the Workbench is coming up.
     */
    public void connectIfNeeded() {        
        SQLDomainModel domainModel = getDomainModel();
        Database db = domainModel.getDatabase();
        if (db == null) {
            boolean keepTrying = true; 
            
            /* Find out if we're visible yet. */
            IWorkbench workbench = PlatformUI.getWorkbench();
            IWorkbenchWindow activeWindow = workbench.getActiveWorkbenchWindow();
            if (activeWindow != null) {
                IWorkbenchPage activePage = activeWindow.getActivePage();
                if (activePage != null) {
                    if( activePage.isPartVisible( this )) {
                        /* Once we're visible, try re-establishing the connection. */
                    	ISQLEditorConnectionInfo connInfo = domainModel.getConnectionInfo();
                        if (connInfo != null) {
                            boolean connectedOK = true; // SQLDBUtils.reestablishConnection( connInfo );
                            if (connectedOK == true) {
                                db = connInfo.getDatabase();
                                sqlDomainModel.setDatabase(db);
                                sqlDomainModel.resetVendor(db);
                            }
                            else {
                                keepTrying = false;
                            }
                            
                            /* Force a reparse to rebuild the display with the new 
                             * Database info. */
                            sourceViewer.forceReparse();
                        }
                    }
                }
            }
            
            /* If we still don't have a connection and we didn't cancel the connection attempt, 
             * try it again later. */
            if (db == null && keepTrying == true) {
                ConnectRunnable connectRunnable = new ConnectRunnable( this );
                Display display = workbench.getDisplay();
                int delayTime = 1000; // one second
                display.timerExec( delayTime, connectRunnable ); 
            }
        }
    }
    
    public void doSaveAs() {
    }

    public boolean isSaveAsAllowed() {
        return false;
    }

    public void setResourceRemoved(boolean value) {
        resourceRemoved = value;
    }

    /**
     * This is called during startup.
     */
    public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
        setSite( site );
        setInput( editorInput );
        site.setSelectionProvider( this );

        if (editorInput instanceof ISQLBuilderEditorInput) {
        	sqlBuilderEditorInput = (ISQLBuilderEditorInput) editorInput;
        }
        else if (editorInput instanceof IFileEditorInput){
        	sqlBuilderEditorInput = new SQLBuilderFileEditorInput(((IFileEditorInput) editorInput).getFile());
        }
        
        if (sqlBuilderEditorInput != null) {

            /* Get the basic settings (name, connection, omitSchemaInfo database) from the editor 
             * input. */
            String title = sqlBuilderEditorInput.getName();
            setPartName(title);
            
            OmitSchemaInfo omitSchemaInfo = sqlBuilderEditorInput.getOmitSchemaInfo();
            sqlDomainModel.setOmitSchemaInfo(omitSchemaInfo);
            omitSchemaInfo.addObserver(this);
            
            ISQLEditorConnectionInfo connInfo = sqlBuilderEditorInput.getConnectionInfo();
            sqlDomainModel.setConnectionInfo(connInfo);
            
            // After setting omitSchemaInfo and connectionInfo, call setCurrentSchema
            sqlDomainModel.setCurrentSchema();
                       
            // Calling connInfo.getDatabase() tries to connect to the database
            Database db = connInfo.getDatabase();

            if (db == null && connInfo != null) {
                throw new PartInitException( NLS.bind(Messages._EXC_OPEN_SQL_FILE_NOT_CONNECTED, connInfo.getConnectionProfileName()));
            }
            sqlDomainModel.setDatabase(db);

            /* Load the initial SQL from the editor input. Note that persistance is
             * handled differently if the input is a FileEditorInput vs. a 
             * StorageEditorInput. Handle the file case first. */
            if (sqlBuilderEditorInput instanceof SQLBuilderFileEditorInput) {
            	SQLBuilderFileEditorInput sqlBuilderFileEditorInput = (SQLBuilderFileEditorInput) sqlBuilderEditorInput;
                try {
                    IFile fileResource = sqlBuilderFileEditorInput.getFile();
                    if (fileResource != null) {
                        created = sqlDomainModel.openFileResource(fileResource);
                        if (created == false) {
                            //throw new PartInitException(Messages._EXC_OPEN_SQL_FILE_RESOURCE")); //$NON-NLS-1$
                        	                            
                        }
                    }
                    else {
                        throw new PartInitException(Messages._EXC_OPEN_SQL_FILE_RESOURCE);
                    }
                }
                catch (Exception ex) {
                    //SQLBuilderPlugin.getPlugin().getLogger().writeLog("Cannot
                    // load resource.." + ex);
                    throw new PartInitException(Messages._EXC_OPEN_SQL_FILE_RESOURCE);
                }
            }
            
            /* Handle the case where the input is based on an IStorage
             * object. */
            else if (sqlBuilderEditorInput instanceof SQLEditorStorageEditorInput) {
                SQLEditorStorageEditorInput storageEditorInput = (SQLEditorStorageEditorInput) sqlBuilderEditorInput;
                IStorage storageResource = storageEditorInput.getStorage();
                try {
                    created = sqlDomainModel.openStorageResource(storageResource);
                    if (created == false) {
                        //throw new PartInitException(Messages._ERROR_OPEN_SQL_STORAGE_RESOURCE")); //$NON-NLS-1$
                    }
                }
                catch (Exception ex) {
                    throw new PartInitException(Messages._ERROR_OPEN_SQL_STORAGE_RESOURCE);
                }
            }
            
            /* Otherwise we can't tell what we have. */
            else {
                throw new PartInitException(Messages._ERROR_INPUT_NOT_RECOGNIZED);
            }
            
            /* Show the connection status in the status area at the bottom of the
             * workbench window.
             */
            refreshConnectionStatus();
        }
    }

    /**
     * Graph Viewer
     */
    protected void createGraphViewer(Composite client) {
        graphControl = new GraphControl(sqlDomainModel);
        graphControl.createControl(client);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        graphControl.getControl().setLayoutData(data);

        // ratio 0.25
        graphControl.getControl().setData("layout ratio", new Long((((long) 25 << 16) + 99) / 100)); //$NON-NLS-1$
    }

    /**
     * Design Viewer
     */
    protected void createDesignViewer(Composite client) {
        designViewer = new DesignViewer(sqlDomainModel, client);
        // ratio 0.45
        designViewer.setData("layout ratio", new Long((((long) 45 << 16) + 99) / 100)); //$NON-NLS-1$
    }

    /**
     * Source View
     */
    protected void createSourceViewer(Composite client) {
        sourceViewer = new SQLSourceViewer(sqlDomainModel, client, ifile, true);
        sourceViewer.setQueryEventListener(this);
        sourceViewer.initDBContext();
        sourceViewer.setContentProvider(sqlDomainModel.createContentProvider());
        sourceViewer.setSQLBuilder(this);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        sourceViewer.getControl().getParent().setLayoutData(data);
    }

    public SQLSourceViewer getSourceViewer() {
        return sourceViewer;
    }

    public DesignViewer getDesginViewer() {
        return designViewer;
    }

    public GraphControl getGraphControl() {
        return graphControl;
    }

    //implement ISelectionProvider
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        selectionChangedListeners.add(listener);
    }

    public ISelection getSelection() {
        return editorSelection;
    }

    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        selectionChangedListeners.remove(listener);
    }

    public void setSelection(ISelection selection) {
        editorSelection = selection;
        for (Iterator listeners = selectionChangedListeners.iterator(); listeners.hasNext();) {
            ISelectionChangedListener listener = (ISelectionChangedListener) listeners.next();
            listener.selectionChanged(new SelectionChangedEvent(this, selection));
        }
    }

    public Object getAdapter(Class key) {
        if (key.equals(IContentOutlinePage.class)) {
            return getContentOutlinePage();
        }

        //return EcoreUtil.getAdapter(super.eAdapters(),key);
        return super.getAdapter(key);
    }

    public SQLDomainModel getDomainModel() {
        return sqlDomainModel;
    }

    public EditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * Returns the content outline.
     */
    protected IContentOutlinePage getContentOutlinePage() {
        if (contentOutlinePage == null) {
            QueryStatement sqlStatement = sqlDomainModel.getSQLStatement();
            contentOutlinePage = new SQLTreeViewer(this, sqlDomainModel.createContentProvider(), sqlDomainModel.createLabelProvider(), sqlStatement);
                  
            contentOutlinePage.addSelectionChangedListener(new ISelectionChangedListener() {

                public void selectionChanged(SelectionChangedEvent event) {
                    handleContentOutlineSelection(event.getSelection(), true);
                }
            });

            // Make sure the views are initalized even if the content outline is
            // not there
            handleContentOutlineSelection(new StructuredSelection(sqlStatement), false);
        }

        return contentOutlinePage;
    }

    /**
     * This deals with how we want selection in the outliner to affect the other
     * views.
     */
    public void handleContentOutlineSelection(ISelection selection, boolean fromEvent) {
        currentSelection = WindowUtility.getSelection(selection);
        if (currentSelection != null) {
            graphControl.setInput(currentSelection);
            designViewer.inputChanged(currentSelection);
            // [wsdbu00061547] bgp 29Dec2005 - ignore the "formEvent" parm, since
            // this was preventing the SQL source viewer from getting initialized
            // when the outline view is not visible.
            // if (fromEvent) {
                sourceViewer.setInput(currentSelection); 
            // }
            if (currentSelection instanceof WithTableSpecification || currentSelection instanceof QueryCombined || currentSelection instanceof QueryValues) {
                graphControl.getControl().setVisible(false);
                sashForm.layout(true);
            }
            else if (currentSelection instanceof QuerySelect) {
                graphControl.getControl().setVisible(true);
                sashForm.layout(true);
            }
            else if (currentSelection instanceof QuerySelectStatement || currentSelection instanceof QueryExpressionRoot) {
                QueryExpressionBody queryBody = null;
                if (currentSelection instanceof QuerySelectStatement) {
                    queryBody = SelectHelper.getQueryExpressionBody((QuerySelectStatement) currentSelection);
                }
                else if (currentSelection instanceof QueryExpressionRoot) {
                    QueryExpressionRoot qRoot = (QueryExpressionRoot) currentSelection;
                    queryBody = SelectHelper.getQueryExpressionBody(qRoot.getSelectStatement());
                }
                if (queryBody instanceof QuerySelect) {
                    graphControl.getControl().setVisible(true);
                    sashForm.layout(true);
                }
                else if (queryBody instanceof QueryCombined) {
                    graphControl.getControl().setVisible(false);
                    sashForm.layout(true);
                }
                else if (queryBody instanceof QueryValues) {
                    graphControl.getControl().setVisible(false);
                    sashForm.layout(true);
                }
                else {
                    graphControl.getControl().setVisible(true);
                    sashForm.layout(true);
                }
            }
        }
    }

    public void updateProperStatement(boolean isValid) {
        // Disable sqlbuilder stuff (design, graph, and context menu for
        // outline)
        //....and bring up options to revert to
        // previous or to default
        changeGraphControlEnableState(isValid);
        designViewer.setEnabled(isValid);

        if (isValid) {
            if (contentOutlinePage.getControl() != null) {
                contentOutlinePage.getControl().setEnabled(true);
                contentOutlinePage.refreshTree();
            }
        }
    }

    protected void changeGraphControlEnableState(boolean enable) {
        graphControl.setEnabled(enable);

        if (!enable) {
            MessageDialog.openWarning(Display.getCurrent().getActiveShell(), Messages._UI_VALIDATE_FAILED_TITLE, 
                    Messages._UI_GRAPH_PARSE_FAILED); 
        }
    }
    
    /**
     * Implementation of Observer interface.
     * This method is called when user changes the omit schema settings
     * @param ob the object calling this method
     * @param arg the argument passed to the notifyObservers method
     */
    public void update(Observable ob, Object arg) {    	
    	if (ob instanceof OmitSchemaInfo) {    		
    		//OmitSchemaInfo notifier = (OmitSchemaInfo)ob;
    		//sqlDomainModel.setOmitSchema((notifier.getOmitCurrentSchema()));
    		sqlDomainModel.setCurrentSchema();    		
    		sourceViewer.refreshSource(sqlDomainModel.getSQLStatement().getSQL());    		
    	}    	
    }

    public void reloadFromModel() {
        sourceViewer.refreshSource();
    }

    public static boolean isStatementProper(SQLDomainModel domainModel) {
        return domainModel.isProper();
    }

    public boolean isContentOutlineRootSelected() {
        return contentOutlinePage.isOnlyRootSelected();
    }

    public static SQLBuilder getBuilder() {
        return builder;
    }

    protected IDocumentProvider getDocumentProvider() {
        return getSourceViewer().getDocumentProvider();
    }

    /**
	 * Refreshes the status area indicating the connection state.
	 */
	public void refreshConnectionStatus() {
		IEditorSite editorSite = getEditorSite();
		if (editorSite != null) {
			SQLDomainModel domainModel = getDomainModel();
			ISQLEditorConnectionInfo connInfo = domainModel.getConnectionInfo();
			IActionBars actionBars = editorSite.getActionBars();
//			IEditorActionBarContributor editorContributor = editorSite.getActionBarContributor();
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
            
            /* Enable the Run SQL action. This is a work-around for a problem where the
             * Run action is getting disabled when the focus shifts off the SQL source
             * area. */
//            SQLSourceViewer srcViewer = getSourceViewer();
//            if (srcViewer != null) {
//                IAction runSQLAction = srcViewer.getAction( "RunSQLAction" );
//                if (runSQLAction != null) {
////                    boolean enabled = (connInfo != null ? true : false);
//                    runSQLAction.setEnabled( true );
//                }
//          }
		}
	}

	public void dispose(){
		super.dispose();
	}
	
} // end class
