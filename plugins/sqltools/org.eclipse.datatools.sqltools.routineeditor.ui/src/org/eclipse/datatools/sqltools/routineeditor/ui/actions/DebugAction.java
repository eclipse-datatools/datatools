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
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditor;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorImages;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.launching.LaunchingJob;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Li Huang
 *
 */
public class DebugAction extends RoutineAction
{
    private SQLEditor     _sqlEditor;

    /**
	 * Constructs a default <code>DebugAction</code>. This happens
	 * when user invokes the context menu in DSE.
	 */
    public DebugAction()
    {
    	init();
    }

	/**
	 * Constructs a DebugAction from the selected resource. This
	 * happens when this action is instantiated by the common action provider.
	 */
	public DebugAction(Object selectedResource) {
		init();
		initSQLObject(this, selectedResource);
		initConnectionProfile();
		update();
	}
	

    /**
     * @param text
     * @param image
     */
    public DebugAction(SQLEditor targetEditor)
    {
        init();
        setActiveEditor(targetEditor);
        update();
    }

	protected void init() {
        setId(ISQLEditorActionConstants.DEBUG_ACTION_ID);
		setText(Messages.DebugAction_label);
        setToolTipText(Messages.DebugAction_tooltip);
        setImageDescriptor(RoutineEditorImages.getImageDescriptor("debug_exc"));
        setActionDefinitionId(ISQLEditorActionConstants.DEBUG_ACTION_ID);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpContextIds.DEBUG_ACTION, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
	}

    public void setActiveEditor(SQLEditor targetEditor)
    {
        _sqlEditor = targetEditor;
    }

    public void run()
    {
		ProcIdentifier proc = null;
        if (_sqlEditor == null || _sqlEditor.getEditorInput() == null)
        {
            try
            {
    			proc = SQLDevToolsUtil.getProcIdentifier(
    					new DatabaseIdentifier(_connectionProfile.getName(),
    							getDatabaseName()), _sqlObject);
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
        }
        if (proc != null)
        {
            LaunchingJob launchingJob = new LaunchingJob(proc, ILaunchManager.DEBUG_MODE);
            launchingJob.schedule();
        }
    }

    protected IEditorPart openEditor(ProcIdentifier proc, IWorkbenchPage page) throws PartInitException
    {
        return page.openEditor(new ProcEditorInput(proc),
        			RoutineEditor.EDITOR_ID);
    }

    public void update()
    {
        boolean enabled = false;
        String profileName = null;
        if (_sqlEditor != null && _sqlEditor.getConnectionInfo().getSharedConnection() != null && _sqlEditor.getEditorInput() instanceof ProcEditorInput)
        {
        	profileName = _sqlEditor.getConnectionInfo().getConnectionProfileName(); 
        	enabled = true;
        }
        else if (_sqlObject != null && _connectionProfile != null)
        {
        	enabled = true;
        	profileName = _connectionProfile.getName();
        }
        if (enabled)
        {
            try
            {
            	DatabaseIdentifier di = new DatabaseIdentifier(profileName); 
                IControlConnection controlConnection = EditorCorePlugin.getControlConnectionManager().getControlConnection(di);
                if (controlConnection != null)
                {
                    enabled = controlConnection.supportsDebugging();
                }
                else
                {
                    enabled = false;
                }
            }
            catch (Exception e)
            {
            	RoutineEditorUIActivator.getDefault().log(Messages.common_error, e);
                enabled = false;
            }
        }
        setEnabled(enabled);
    }

	public void run(IAction action) {
		run();
	}
}
