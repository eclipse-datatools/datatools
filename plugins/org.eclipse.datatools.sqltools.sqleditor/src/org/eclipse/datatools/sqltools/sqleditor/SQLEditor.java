/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.SQLConnectAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLEditorContentOutlinePage;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLSourceViewer;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLSourceViewerConfiguration;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLDBProposalsService;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLColorProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension2;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.texteditor.DefaultRangeIndicator;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.TextOperationAction;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;


/**
 * This class is responsible for configuring the SQL editor.
 */
public class SQLEditor extends TextEditor implements IPropertyChangeListener {
	public static final String PLUGIN_NAME = "org.eclipse.datatools.sqltools.sqleditor"; //$NON-NLS-1$
	public static final String HELP_CONTEXT_ID = PLUGIN_NAME + ".sqleditorhelp"; //$NON-NLS-1$
	
    /** The content outline page providing the outline view for the editor content. */
    private SQLEditorContentOutlinePage fOutlinePage;
    /** The projection (code folding) support object. */
    private ProjectionSupport fProjectionSupport;
    /** The document setup participant object, which is used partition the edit text. */
    private SQLEditorDocumentSetupParticipant fDocSetupParticipant;
    /** The content assist proposal service for database objects. */
    private ISQLDBProposalsService fDBProposalsService;

    /**
     * Constructs an instance of this class. This is the default constructor.
     */
    public SQLEditor() {
        super();
    }

    /**
     * Creates and installs the editor actions.
     * 
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#createActions()
     */
    protected void createActions() {
        super.createActions();
        ResourceBundle bundle = getResourceBundle();

        IAction a = new TextOperationAction( bundle,
                "ContentAssistProposal.", this, ISourceViewer.CONTENTASSIST_PROPOSALS ); //$NON-NLS-1$
        a.setActionDefinitionId( ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS );
        setAction( "ContentAssistProposal", a ); //$NON-NLS-1$

        a = new TextOperationAction( bundle, "ContentAssistTip.", this, ISourceViewer.CONTENTASSIST_CONTEXT_INFORMATION ); //$NON-NLS-1$
        a.setActionDefinitionId( ITextEditorActionDefinitionIds.CONTENT_ASSIST_CONTEXT_INFORMATION );
        setAction( "ContentAssistTip", a ); //$NON-NLS-1$

        a = new TextOperationAction( bundle, "ContentFormat.", this, ISourceViewer.FORMAT ); //$NON-NLS-1$
        setAction( "ContentFormat", a ); //$NON-NLS-1$

        
    }

    /**
     * Creates and returns the content outline page for the SQL editor.
     * 
     * @return the new content outline page
     */
    protected SQLEditorContentOutlinePage createContentOutlinePage() {
        SQLEditorContentOutlinePage outlinePage = new SQLEditorContentOutlinePage( getDocumentProvider(), this );

        return outlinePage;
    }

    /**
     * Creates the SWT controls for the editor.
     * 
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl( Composite parent ) {
        super.createPartControl( parent );
        setProjectionSupport( createProjectionSupport() );
        
        /* Now that we have enabled source folding, make sure everything is
         * expanded.
         */
        ProjectionViewer viewer = (ProjectionViewer) getSourceViewer();
        viewer.doOperation( ProjectionViewer.TOGGLE );
        
        /* Set a help context ID to enable F1 help. */
        PlatformUI.getWorkbench().getHelpSystem().setHelp( parent, HELP_CONTEXT_ID );
    }

    /**
     * Creates, configures, and returns a <code>ProjectionSupport</code>
     * object for this editor.
     * 
     * @return the <code>ProjectSupport</code> object to use with this editor
     */
    protected ProjectionSupport createProjectionSupport() {
        ProjectionViewer viewer = (ProjectionViewer) getSourceViewer();
        ProjectionSupport projSupport = new ProjectionSupport( viewer, getAnnotationAccess(), getSharedColors() );
        projSupport.addSummarizableAnnotationType( "org.eclipse.ui.workbench.texteditor.error" ); //$NON-NLS-1$
        projSupport.addSummarizableAnnotationType( "org.eclipse.ui.workbench.texteditor.warning" ); //$NON-NLS-1$
        projSupport.install();

        return projSupport;
    }

    /**
     * Creates the source viewer to be used by this editor.
     * 
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#createSourceViewer(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.jface.text.source.IVerticalRuler, int)
     */
    protected ISourceViewer createSourceViewer( Composite parent, IVerticalRuler ruler, int styles ) {
        SQLSourceViewer viewer = new SQLSourceViewer( parent, ruler, getOverviewRuler(), isOverviewRulerVisible(),
                styles );

        return viewer;
    }

    /**
     * Creates the source viewer configuation to be used by this editor.
     * 
     * @return the new source viewer configuration object
     */
    protected SQLSourceViewerConfiguration createSourceViewerConfiguration() {
        SQLSourceViewerConfiguration config = new SQLSourceViewerConfiguration( this );
        ISQLDBProposalsService proposalsService = getDBProposalsService();
        if (proposalsService != null) {
            config.setDBProposalsService( proposalsService );
        }

        return config;
    }

    /**
     * Dispose of resources held by this editor.
     * 
     * @see IWorkbenchPart#dispose()
     */
    public void dispose() {
        SQLEditorContentOutlinePage outlinePage = getOutlinePage();
        if (outlinePage != null) {
            outlinePage.setInput( null );
        }
        super.dispose();
    }

    /**
     * Abandons all modifications applied to this text editor's input element's
     * textual presentation since the last save operation.
     * 
     * @see ITextEditor#doRevertToSaved()
     */
    public void doRevertToSaved() {
        super.doRevertToSaved();
        updateOutlinePage();
    }

    /**
     * Saves the content of this editor.
     * 
     * @param progressMonitor the progress monitor for communicating result
     *            state or <code>null</code>
     * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave( IProgressMonitor monitor ) {
        super.doSave( monitor );
        updateOutlinePage();
    }

    /**
     * Saves the contents of this editor to another object.
     * 
     * @see org.eclipse.ui.ISaveablePart#doSaveAs()
     */
    public void doSaveAs() {
        super.doSaveAs();
        updateOutlinePage();
    }

    /**
     * Sets the input of the outline page after this class has set input.
     * 
     * @param input the new input for the editor
     * @see org.eclipse.ui.editors.text.TextEditor#doSetInput(org.eclipse.ui.IEditorInput)
     */
    public void doSetInput( IEditorInput input ) throws CoreException {
        super.doSetInput( input );

        /* Make sure the document partitioner is set up. The document setup
         * participant sets up document partitioning, which is used for text
         * colorizing and other text features.
         */
        IDocumentProvider docProvider = this.getDocumentProvider();
        if (docProvider != null) {
            IDocument doc = docProvider.getDocument( input );
            if (doc != null) {
                SQLEditorDocumentSetupParticipant docSetupParticipant = getDocumentSetupParticipant();
                docSetupParticipant.setup( doc );
            }
        }
        
        if (input instanceof ISQLEditorInput)
        {
            ISQLEditorInput sqlEditorInput = (ISQLEditorInput) input;
            fDBProposalsService = new SQLDBProposalsService( sqlEditorInput.getConnectionInfo());
            SourceViewerConfiguration config = getSourceViewerConfiguration();
            if (config != null && config instanceof SQLSourceViewerConfiguration) {
                SQLSourceViewerConfiguration sqlConfig = (SQLSourceViewerConfiguration) config;
                sqlConfig.setDBProposalsService( fDBProposalsService );
            }
            
        }

        /* Show the connection status in the status area at the bottom of the
         * workbench window.
         */
        refreshConnectionStatus();
        
        /* Pass the input along to the outline page. */
        SQLEditorContentOutlinePage outlinePage = getOutlinePage();
        if (outlinePage != null) {
            outlinePage.setInput( input );
        }
    }

    /**
     * Sets up this editor's context menu before it is made visible.
     * 
     * @see org.eclipse.ui.texteditor.AbstractTextEditor#editorContextMenuAboutToShow(org.eclipse.jface.action.IMenuManager)
     */
    protected void editorContextMenuAboutToShow( IMenuManager menu ) {
        super.editorContextMenuAboutToShow( menu );

        menu.add( new Separator() );
        addAction( menu, "ContentAssistProposal" ); //$NON-NLS-1$
        addAction( menu, "ContentAssistTip" ); //$NON-NLS-1$
        addAction( menu, "ContentFormat" ); //$NON-NLS-1$

        menu.add( new Separator() );
        addAction( menu, "SQLEditor.runAction" ); //$NON-NLS-1$
    }

    /**
     * Gets an adapter for the given class. Returns the SQL content outline page
     * if the get request is for an outline page. Otherwise returns a projection
     * adapter if one hasn't already been created.
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     * @see org.eclipse.jface.text.source.projection.ProjectionSupport#getAdapter(org.eclipse.jface.text.source.ISourceViewer,
     *      java.lang.Class)
     */
    public Object getAdapter( Class classForWhichAdapterNeeded ) {
        Object adapter = null;

        /* Get and return the content outline page, if that's what's requested. */
        if (IContentOutlinePage.class.equals( classForWhichAdapterNeeded )) {
            SQLEditorContentOutlinePage outlinePage = getOutlinePage();
//            if (outlinePage == null) {
//                outlinePage = createContentOutlinePage();
//                setOutlinePage( outlinePage );
//                if (getEditorInput() != null) {
//                    outlinePage.setInput( getEditorInput() );
//                }
//            }
            adapter = outlinePage;
        }
        /* Delegate getting the adapter to the projection support object,
         * if there is one. Projection refers to the ability to visibly collapse
         * and expand sections of the document.
         */
        else if (adapter == null) {
            ProjectionSupport projSupport = getProjectionSupport();
            if (projSupport != null) {
                adapter = projSupport.getAdapter( getSourceViewer(), classForWhichAdapterNeeded );
            }
        }

        /* If we still don't have an adapter let the superclass handle it. */
        if (adapter == null) {
            adapter = super.getAdapter( classForWhichAdapterNeeded );
        }

        return adapter;
    }

    /**
     * Gets the connection info object of the editor input of this editor.
     * 
     * @return the current connection info object if the editor has one,
     *         otherwise null
     */
    public ISQLEditorConnectionInfo getConnectionInfo() {
        if (getEditorInput() instanceof ISQLEditorInput)
        {
            return ((ISQLEditorInput)getEditorInput()).getConnectionInfo();
        }
        return null;
    }

    /**
	 * Sets the connection info object of the editor input of this editor. Also
	 * updates connection related features such as actions, status line, syntax highlighting, and syntax validation, etc.
	 * 
	 * @param connInfo
	 *            the new connection info object
	 */
    public void setConnectionInfo(ISQLEditorConnectionInfo connInfo) {
    	if (getEditorInput() instanceof ISQLEditorInput)
    	{
    		ISQLEditorConnectionInfo oldConnInfo = getConnectionInfo();
    		((ISQLEditorInput)getEditorInput()).setConnectionInfo(connInfo);
    		
    		//save the document to persistent the connection info with the input
            IRunnableWithProgress progressOp = new IRunnableWithProgress()
            {
                public void run(IProgressMonitor monitor)
                {
                    doSave(monitor);
                }
            }
            ;

            try
            {
                // Do the save.
                Workbench.getInstance().getActiveWorkbenchWindow().run(false, true, progressOp);
            }
            catch (InvocationTargetException e)
            {
                Throwable targetExc = e.getTargetException();
                String title = SQLEditorResources.getString("EditorManager.operationFailed", new Object[]
                {
                    SQLEditorResources.getString("Save")
                }
                ); //$NON-NLS-1$
                IStatus status = new Status(IStatus.WARNING, SQLEditorPlugin.PLUGIN_ID, IStatus.OK,targetExc.getMessage(), targetExc );
                SQLEditorPlugin.getDefault().log(status);
                MessageDialog.openError(getEditorSite().getShell(), SQLEditorResources.getString("common.error"), //$NON-NLS-1$
                title + ':' + targetExc.getMessage());
            }
            catch (InterruptedException e)
            {
                // Ignore. The user pressed cancel.
                return;
            }

            if (connInfo != null && !connInfo.getDatabaseVendorDefinitionId().equals(oldConnInfo.getDatabaseVendorDefinitionId()))
    		{
    			//when the database definition is different, reset the SourceViewer for correct syntax highlight
            	((ISourceViewerExtension2) getSourceViewer()).unconfigure();
            	SQLSourceViewerConfiguration configuration = createSourceViewerConfiguration();
            	setSourceViewerConfiguration( configuration );
            	getSourceViewer().configure(configuration);
            	//we have to do this since only configuring the source viewer doesn't work. PresentationReconciler won't get notified of the document changed event. 
            	setInput(getEditorInput());
            }
                
            //TODO: implement the following functions
//            refreshActionStatus();
//            updateStatusLine();
//            _fSQLUpdater.run();
//
//            fireConnectionProfileAttached();
    		
    	}
    }
    
    /**
     * Gets the <code>Database</code> object associated with this input.
     * 
     * @return the <code>Database</code> object associated with this input
     */
    public Database getDatabase() {
        if (getEditorInput() instanceof ISQLEditorInput)
        {
            return ((ISQLEditorInput)getEditorInput()).getConnectionInfo().getDatabase();
        }
        return null;
    }
    
    /**
     * Gets the <code>DBProposalsService</code> object that provides content
     * assist services for this editor.
     * 
     * @return the current <code>DBProposalsService</code> object
     */
    public ISQLDBProposalsService getDBProposalsService() {
        return fDBProposalsService;
    }

    /**
     * Gets the default schema name to use with the connection or database
     * associated with this input.
     * 
     * @return the default schema name to use with this input, or null if none
     */
    public String getDefaultSchemaName() {
        if (getEditorInput() instanceof ISQLEditorInput)
        {
            return ((ISQLEditorInput)getEditorInput()).getConnectionInfo().getDefaultSchemaName();
        }
        return null;
    }

    /**
     * Gets the document setup participant object associated with this editor.
     * The setup participant sets the partitioning type for the document.
     * 
     * @return the current document setup participant
     */
    public SQLEditorDocumentSetupParticipant getDocumentSetupParticipant() {
        if (fDocSetupParticipant == null) {
            fDocSetupParticipant = new SQLEditorDocumentSetupParticipant();
        }
        return fDocSetupParticipant;
    }

    /**
     * Gets the outline page associated with this editor, if there is one.
     * 
     * @return the outline page associated with this editor
     */
    protected SQLEditorContentOutlinePage getOutlinePage() {
        return fOutlinePage;
    }

    /**
     * Gets the <code>ProjectionSupport</code> object associated with this
     * editor.
     * 
     * @return the current <code>ProjectionSupport</code> object
     */
    protected ProjectionSupport getProjectionSupport() {
        return fProjectionSupport;
    }

    /**
     * Gets the resource bundle associated with this editor.
     * 
     * @return the resource bundle associated with this editor.
     */
    public ResourceBundle getResourceBundle() {
        return SQLEditorResources.getResourceBundle();
    }

    /**
     * Gets the color provider for colorizing SQL source code.
     * 
     * @return the SQL color provider
     */
    public SQLColorProvider getSQLColorProvider() {
        return SQLEditorPlugin.getDefault().getSQLColorProvider();
    }

    /**
     * Initializes the editor.
     * 
     * @see org.eclipse.ui.editors.text.TextEditor#initializeEditor()
     */
    protected void initializeEditor() {
        super.initializeEditor();
        setSourceViewerConfiguration( createSourceViewerConfiguration() );
        setRangeIndicator( new DefaultRangeIndicator() );
    }
    
    /**
     * Handles notifications to the object that a property has changed.
     * 
     * @param event the property change event object describing which property
     *            changed and how
     */
    public void propertyChange( PropertyChangeEvent event ) {
        if (event.getProperty().equals( SQLConnectAction.CONNECTION )) {
            //TODO refresh db proposal service
            refreshConnectionStatus();
        }
    }

    /**
     * Sets the document setup participant object associated with this editor to
     * the given object. The setup participant sets the partitioning type for
     * the document.
     * 
     * @return the current document setup participant
     */
    public void setDocumentSetupParticipant( SQLEditorDocumentSetupParticipant docSetupParticipant ) {
        fDocSetupParticipant = docSetupParticipant;
    }

    
    /**
     * Asks this part to take focus within the workbench.
     * 
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() {
        super.setFocus();
        
        /* Make sure the connection status message is up to date.  It doesn't
         * automatically get updated if the user switches editor instances.
         */
        refreshConnectionStatus();
    }
    
    /**
     * Sets the current outline page to the given outline page object.
     * 
     * @param outlinePage the outline page to use
     */
    protected void setOutlinePage( SQLEditorContentOutlinePage outlinePage ) {
        fOutlinePage = outlinePage;
    }

    /**
     * Sets the <code>ProjectionSupport</code> object associated with this
     * editor.
     * 
     * @param projSupport the <code>ProjectionSupport</code> object to use
     */
    protected void setProjectionSupport( ProjectionSupport projSupport ) {
        fProjectionSupport = projSupport;
    }

    /**
     * Refreshes the status area indicating the connection state.
     */
    public void refreshConnectionStatus() {
        IEditorSite editorSite = getEditorSite();
        if (editorSite != null) {
            IActionBars actionBars = editorSite.getActionBars();
            if (actionBars != null) {
                IStatusLineManager statusLineMgr = actionBars.getStatusLineManager();
                ISQLEditorConnectionInfo connInfo = getConnectionInfo();
                if (connInfo != null && connInfo.getConnectionProfile() != null) {
                    statusLineMgr.setErrorMessage( null );
                    String connStatus = connInfo.getConnectionProfile().getName() ;
                    statusLineMgr.setMessage( connStatus );
                }
                else {
                    String connStatus = SQLEditorResources.getString( "SQLEditor.connection.status.noConnection" ); //$NON-NLS-1$
                    statusLineMgr.setErrorMessage( connStatus );
                }
                actionBars.updateActionBars();
            }
        }
    }

    /**
     * Updates the outline page (if there is one) to reflect the latest changes
     * in the editor.
     */
    protected void updateOutlinePage() {
        SQLEditorContentOutlinePage outlinePage = getOutlinePage();
        if (outlinePage != null) {
            outlinePage.update();
        }
    }

    /**
     * Prompts the user to select an existing connection or create a new one.
     * Returns a <code>ISQLEditorConnectionInfo</code> object for the chosen connection. 
     * 
     * @return the <code>ISQLEditorConnectionInfo</code> object for the selected connection or
     * null if none selected
     */
    public ISQLEditorConnectionInfo requestConnectionFromUser() {
        //TODO: call "SQL connection" dialog
        return null;
    }

    /**
     * Makes the source viewer public.
     * @return The source viewer used to create this editor
     */
    public ISourceViewer getSV()
    {
        return getSourceViewer();
    }


} // end class
