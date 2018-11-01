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
package org.eclipse.datatools.sqltools.routineeditor.ui.actions;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditor;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorImages;
import org.eclipse.datatools.sqltools.routineeditor.ui.launching.LaunchingJob;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author Hui Cao
 * 
 */
public class RunAction extends RoutineAction implements IActionDelegate 
{
    private RoutineEditor     _sqlEditor;


    /**
	 * Constructs a default <code>RunAction</code>. This happens
	 * when user invokes the context menu in DSE.
	 */
    public RunAction()
    {
    	init();
    }

	/**
	 * Constructs a CommonSQLObjectAction from the selected resource. This
	 * happens when this action is instantiated by the common action provider.
	 */
	public RunAction(Object selectedResource) {
		init();
		initSQLObject(this, selectedResource);
		initConnectionProfile();
		update();
	}
	
    /**
     * Constructs a <code>RunAction</code> from a RoutineEditor. This happens
     * when user invokes the context menu in RoutineEditor
     */
    public RunAction(RoutineEditor targetEditor)
    {
    	init();
    	setActiveEditor(targetEditor);
    	update();
    	
    }

	protected void init() {
		setId(ISQLEditorActionConstants.RUN_ACTION_ID);
		setText(Messages.RunAction_label);
    	setToolTipText(Messages.RunAction_tooltip);
        setImageDescriptor(RoutineEditorImages.getImageDescriptor("execute"));
        setActionDefinitionId(ISQLEditorActionConstants.RUN_ACTION_ID);
    	PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpContextIds.RUN_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
	}
    
    public void setActiveEditor(RoutineEditor targetEditor)
    {
        _sqlEditor = targetEditor;
    }

    public void run()
    {
    	run(null);
    }

    public void update()
    {
        boolean enabled = _sqlEditor != null && _sqlEditor.getConnectionInfo().getSharedConnection() != null && _sqlEditor.getEditorInput() instanceof ProcEditorInput;
    	enabled = enabled || (_sqlObject != null && _connectionProfile != null);
        setEnabled(enabled);
    }

	public void run(IAction action) {
		ProcIdentifier proc = null;
        IEditorPart parent = null;
        if (_sqlEditor == null || _sqlEditor.getEditorInput() == null)
        {
            try
            {
    			proc = SQLDevToolsUtil.getProcIdentifier(
    					new DatabaseIdentifier(_connectionProfile.getName(),
    							getDatabaseName()), _sqlObject);
    			IWorkbenchPage page = PlatformUI.getWorkbench()
    					.getActiveWorkbenchWindow().getActivePage();
    			parent = openEditor(proc, page);
    			if(parent == null)
    			{
    				return;
    			}
            }
            catch (Exception e)
            {
            	RoutineEditorUIActivator.getDefault().log(e);
            }
        }
        else
        {
        	ProcEditorInput procEditorInput = (ProcEditorInput) _sqlEditor.getEditorInput();
        	proc = procEditorInput.getProcIdentifier();
            parent = _sqlEditor.getParentEditor();
        }
        if (proc != null)
        {
            LaunchingJob launchingJob = new LaunchingJob(proc, ILaunchManager.RUN_MODE);
            launchingJob.schedule();
        	
        }
		
	}

    protected IEditorPart openEditor(ProcIdentifier proc, IWorkbenchPage page) throws PartInitException
    {
        return page.openEditor(new ProcEditorInput(proc),
        			RoutineEditor.EDITOR_ID);
    }

	
}
