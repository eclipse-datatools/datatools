/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui;

import java.util.ResourceBundle;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.services.ActionService;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.routineeditor.ui.actions.DebugAction;
import org.eclipse.datatools.sqltools.routineeditor.ui.actions.RefreshFromDatabaseAction;
import org.eclipse.datatools.sqltools.routineeditor.ui.actions.RunAction;
import org.eclipse.datatools.sqltools.routineeditor.ui.actions.SaveToDatabaseAction;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

/**
 * A SQL Editor handles routine objects including stored procedure, function,
 * trigger, and event.
 * 
 * @see org.eclipse.datatools.modelbase.sql.routines.Routine
 * @author Hui Cao
 * 
 */
public class RoutineEditor extends SQLEditor {

	public static final String EDITOR_ID = EditorConstants.ROUTINE_EDITOR_ID;

	private class ProfileStatusChecker implements IPartListener2
	{
	    public void partActivated(IWorkbenchPartReference partRef)
	    {
	            switch (getConnectionInfo().getProfileStatus())
	            {
	                case EditorConstants.CP_STATUS_DELETED:
	                	getConnectionInfo().setConnectionProfileName(null);
	                    //don't break
	                case EditorConstants.CP_STATUS_PROP_CHANGED:
	                case EditorConstants.CP_STATUS_DISCONNECTED:
	                	//FIXME necessary?
	                	getConnectionInfo().setProfileStatus( EditorConstants.CP_STATUS_OTHER);
	                	refreshActionStatus();
	                	refreshConnectionStatus();
	                    if (isDirty())
	                    {
	                        String msg = Messages.SQLEditor_profile_disconnected;
	                        forceSaveAs(msg);
	                    }
	                    else
	                    {
	                        close(false);
	                    }
	                    
	                    break;
	                default:
	                    break;
	            }
	    }

	    private void forceSaveAs(String message)
	    {
	        if (MessageDialog.openConfirm(getSite().getShell(), Messages.SQLEditor_profile_information, message))
	        {
	            doSaveAs();
	            if (getEditorInput() instanceof ProcEditorInput)
	            {
	                close(false);
	            }
	        }
	        else
	        {
	            close(false);
	        }
	    }

	    public void partBroughtToTop(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partClosed(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partDeactivated(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partOpened(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partHidden(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partVisible(IWorkbenchPartReference partRef)
	    {
	    }

	    public void partInputChanged(IWorkbenchPartReference partRef)
	    {
	    }

	}
	protected ProfileStatusChecker                              _profileChecker                   = new ProfileStatusChecker();
	
	private Action _saveToDBAction = null;

	private Action _refreshAction = null;

	public RoutineEditor() {
		super();
		setDocumentProvider(RoutineEditorDocumentProviderFactory
				.getDocumentProvider(null));
	}

	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		getSite().getPage().addPartListener(_profileChecker);
	}
	
	protected void initializeEditor() {
		super.initializeEditor();
	}

	protected void createActions() {
		super.createActions();
		IActionBars bars = ((IEditorSite) getSite()).getActionBars();

		IAction a = new RunAction(this);
		setAction(ISQLEditorActionConstants.RUN_ACTION_ID, a); //$NON-NLS-1$
		bars.setGlobalActionHandler(ISQLEditorActionConstants.RUN_ACTION_ID, a);

		a = new DebugAction(this);
		setAction(ISQLEditorActionConstants.DEBUG_ACTION_ID, a); //$NON-NLS-1$
		bars.setGlobalActionHandler(ISQLEditorActionConstants.DEBUG_ACTION_ID, a);
		
		// save to database
		_saveToDBAction = new SaveToDatabaseAction(getConstructedResourceBundle(), "SQLEditor.action.savetodb.", this);
		setAction(ISQLEditorActionConstants.SAVE_TO_DATABASE_ACTION_ID, _saveToDBAction);
		bars.setGlobalActionHandler(ISQLEditorActionConstants.SAVE_TO_DATABASE_ACTION_ID, _saveToDBAction);
        markAsContentDependentAction(ISQLEditorActionConstants.SAVE_TO_DATABASE_ACTION_ID, true);

		// refresh from database
		_refreshAction = new RefreshFromDatabaseAction(getConstructedResourceBundle(), "SQLEditor.action.refresh.", this);
		setAction(ISQLEditorActionConstants.REFRESH_FROM_DATABASE_ACTION_ID, _refreshAction);
		bars.setGlobalActionHandler(ISQLEditorActionConstants.REFRESH_FROM_DATABASE_ACTION_ID, _refreshAction);
        markAsContentDependentAction(ISQLEditorActionConstants.REFRESH_FROM_DATABASE_ACTION_ID, true);
	}

	protected void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
		// TODO add debug action
		menu.add(new Separator());

		// 2006-07-20
		// Fix 380668-1 Users are confused by 'Execute' and 'Run' context menu
		// in the editor.
		// Keep Execute All, but will only be enabled when you��re editing a SQL
		// file. Disabled at all other times.
		menu.remove(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID);
		SQLDevToolsUIConfiguration config = SQLToolsUIFacade
				.getConfigurationByVendorIdentifier(getConnectionInfo()
						.getDatabaseVendorDefinitionId());
		ActionService actionService = config.getActionService();
        if (actionService.supportsAction(ISQLEditorActionConstants.RUN_ACTION_ID))
        {
            // Run
            addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_EXECUTE, ISQLEditorActionConstants.RUN_ACTION_ID);
        }
		if (actionService.supportsAction(ISQLEditorActionConstants.DEBUG_ACTION_ID))
        {
            // Debug
            addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_EXECUTE,
                    ISQLEditorActionConstants.DEBUG_ACTION_ID);
        }
		addAction(menu, ITextEditorActionConstants.GROUP_UNDO, ISQLEditorActionConstants.REFRESH_FROM_DATABASE_ACTION_ID);
		addAction(menu, ISQLEditorActionConstants.GROUP_SQLEDITOR_SAVE, ISQLEditorActionConstants.SAVE_TO_DATABASE_ACTION_ID);
	}

	public void doSave(IProgressMonitor monitor)
    {
    	try {
			if (getEditorInput() instanceof ProcEditorInput) {
				// This is caused by closing the connection profile without saving the
				// routine
				if (!isConnected()) {
					doSaveAs();
				} else {
					super.doSave(monitor);
				}
			}
		} finally{
			if (!monitor.isCanceled())
			{
				monitor.done();
			}
		}

    }

	public void doSaveAs() {
		ISQLEditorConnectionInfo connInfo = getConnectionInfo();
        Shell shell = getSite().getShell();
        IProgressMonitor progressMonitor=  getProgressMonitor();
        IEditorInput input = getEditorInput();

        SaveAsDialog dialog = new SaveAsDialog(shell);

        if (input instanceof SQLEditorStorageEditorInput)
        {
            dialog.setOriginalName(((SQLEditorStorageEditorInput) input).getName() + ".sql");
        }

        dialog.create();

        IDocumentProvider provider = getDocumentProvider();
        if (provider == null)
        {
            // editor has programmatically been closed while the dialog was open
            return;
        }

        if (dialog.open() == Window.CANCEL)
        {
            if (progressMonitor != null)
            {
                progressMonitor.setCanceled(true);
            }
            return;
        }

        IPath filePath = dialog.getResult();
        if (filePath == null)
        {
            if (progressMonitor != null)
            {
                progressMonitor.setCanceled(true);
            }
            return;
        }


        boolean success = false;
        IDocumentProvider newProvider = null;
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IFile file = workspace.getRoot().getFile(filePath);
        final IEditorInput newInput = new FileEditorInput(file);
        try
        {
//        	InputStream inputStream = ((SQLEditorStorageEditorInput) input).getStorage().getContents();
//        	if (file.exists())
//        	{
//        		//overwrite
//        		file.setContents(inputStream, true, false, progressMonitor);
//        		inputStream.close();
//        	}
//        	else
//        	{
//        		file.create(inputStream, true, progressMonitor);
//        	}
            IWorkbenchPage page = SQLEditorPlugin.getActiveWorkbenchPage();
            SQLEditor editor = (SQLEditor)page.openEditor(newInput,  EditorConstants.SQLFILE_EDITOR_ID);
            newProvider = editor.getDocumentProvider();
            newProvider.aboutToChange(newInput);
            IDocument newDoc = newProvider.getDocument(newInput);
            newDoc.set(provider.getDocument(input).get());
			newProvider.saveDocument(progressMonitor, newInput, newDoc, true);
			editor.setConnectionInfo(connInfo);
            this.close(false);
            success = true;
        }
        catch (CoreException x)
        {
            IStatus status = x.getStatus();
            if (status == null || status.getSeverity() != IStatus.CANCEL)
            {
                String title = Messages.Editor_error_save_title; //$NON-NLS-1$
                String msg = NLS.bind(Messages.Editor_error_save_message, new Object[]
                {
                    x.getMessage()
                }
                ); //$NON-NLS-1$

                if (status != null)
                {
                    switch (status.getSeverity())
                    {
                        case IStatus.INFO:
                            MessageDialog.openInformation(shell, title, msg);
                            break;
                        case IStatus.WARNING:
                            MessageDialog.openWarning(shell, title, msg);
                            break;
                        default:
                            MessageDialog.openError(shell, title, msg);
                    }
                }
                else
                {
                    MessageDialog.openError(shell, title, msg);
                }
            }
        }
        catch (Exception x)
        {
        	String title = Messages.Editor_error_save_title; //$NON-NLS-1$
            String msg = NLS.bind(Messages.Editor_error_save_message, new Object[]
            {
                x.getMessage()
            }
            ); //$NON-NLS-1$
        	MessageDialog.openError(shell, title, msg);
        } finally {
			newProvider.changed(newInput);
		}
        
        if (progressMonitor != null)
        {
            progressMonitor.setCanceled(!success);
        }
	}

	public int getSQLType() {
		/** The default sqlType is root */
		int sqlType = SQLParserConstants.TYPE_SQL_ROOT;
		if (getEditorInput() instanceof ProcEditorInput) {
			int procType = ((ProcEditorInput) getEditorInput())
					.getProcIdentifier().getType();
			switch (procType) {
			case ProcIdentifier.TYPE_SP:
				sqlType = SQLParserConstants.TYPE_SQL_CREATE_PROCEDURE;
				break;
			case ProcIdentifier.TYPE_UDF:
				sqlType = SQLParserConstants.TYPE_SQL_CREATE_FUNCTION;
				break;
			case ProcIdentifier.TYPE_EVENT:
				sqlType = SQLParserConstants.TYPE_SQL_CREATE_EVENT;
				break;
			case ProcIdentifier.TYPE_TRIGGER:
				sqlType = SQLParserConstants.TYPE_SQL_CREATE_TRIGGER;
				break;
			}
		}
		return sqlType;
	}

	public void dispose() {
        try
        {
            DatabaseIdentifier databaseIdentifier = getDatabaseIdentifier();
			if (EditorCorePlugin.getControlConnectionManager().getControlConnection(databaseIdentifier) != null)
            {
                IControlConnection controlCon = EditorCorePlugin.getControlConnectionManager()
                    .getOrCreateControlConnection(databaseIdentifier);
                controlCon.refresh(getCurrProcIdentifier());
            }
        }
        catch (Exception ex)
        {
        	RoutineEditorUIActivator.getDefault().log(ex);
        }

		super.dispose();
		getSite().getPage().removePartListener(_profileChecker);
	}	
	
    /**
     * Gets the resource bundle associated with this editor.
     * 
     * @return the resource bundle associated with this editor.
     */
    public ResourceBundle getConstructedResourceBundle() {
        return org.eclipse.datatools.sqltools.routineeditor.ui.actions.Messages.getResourceBundle();
    }
    
    public ProcIdentifier getCurrProcIdentifier()
    {
    	IEditorInput input = getEditorInput();
    	ProcIdentifier currProc = null;
    	if (input instanceof ProcEditorInput)
    	{
    		currProc = ((ProcEditorInput) input).getProcIdentifier();
    	}
    	return currProc;
    }

	public void setConnectionInfo(ISQLEditorConnectionInfo connInfo) {
		super.setConnectionInfo(connInfo);
		ProcIdentifier proc = getCurrProcIdentifier();
		if (proc != null)
		{
			proc.getDatabaseIdentifier().setProfileName(connInfo.getConnectionProfileName());
			//refresh title tooltip
			//BZ200108 to avoid Invalid Thread Access
			SQLEditorPlugin.getDisplay().asyncExec(new Runnable()
            {
                public void run()
                {
                    setTitleToolTip(getTitleToolTip());
                }
            });			
	        
		}
	}
    
    
    
}
