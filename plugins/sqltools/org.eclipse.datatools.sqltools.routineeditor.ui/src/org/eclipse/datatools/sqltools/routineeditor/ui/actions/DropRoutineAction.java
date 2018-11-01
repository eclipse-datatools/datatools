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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.IItemWithCode;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorImages;
import org.eclipse.datatools.sqltools.routineeditor.ui.util.RoutineUIUtil;
import org.eclipse.datatools.sqltools.sql.util.DSEUtil;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IActionDelegate;

/**
 * 
 * @author Hui Cao
 * 
 */
public class DropRoutineAction extends RoutineAction implements IActionDelegate {

    public static final String ID = "org.eclipse.datatools.sqltools.routineeditor.ui.actions.DropRoutineAction";
	/**
	 * 
	 */
	public DropRoutineAction() {
		init();
	}

	/**
	 * Constructs a CommonSQLObjectAction from the selected resource. This
	 * happens when this action is instantiated by the common action provider.
	 */
	public DropRoutineAction(Object selectedResource) {
		init();
		initSQLObject(this, selectedResource);
		initConnectionProfile();
	}
	
	protected void init() {
        setId(ID);
		setText(Messages.DropRoutineAction_label);
    	setToolTipText(Messages.DropRoutineAction_tooltip);
        setImageDescriptor(RoutineEditorImages.getImageDescriptor("drop"));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}

	public void run() {
		if (_sqlObject != null && _connectionProfile != null) {
			boolean ok = MessageDialog.openConfirm(null, Messages.DropRoutineAction_label, NLS.bind(Messages.DropRoutineAction_message, _sqlObject.getName()));
			if (!ok)
			{
				return;
			}
			
			DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(_connectionProfile.getName(),
										getDatabaseName());
			ProcIdentifier proc = SQLDevToolsUtil.getProcIdentifier(
					databaseIdentifier, _sqlObject);
	        try {
				IControlConnection controlConnection = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(databaseIdentifier);
				IItemWithCode item = (IItemWithCode)controlConnection.getDBItem(proc);
				item.drop();
			} catch (Exception e) {
				RoutineEditorUIActivator.getDefault().log(e);
			}
	        //fix CR:379301

	        RoutineUIUtil.closeEditor(proc);
	        DSEUtil.refreshParent(_sqlObject);
	        deleteLaunchConfigration(proc);

		}
	}
	
    /**
     * @param proc
     */
    private static void deleteLaunchConfigration(ProcIdentifier proc)
    {
        ILaunchConfigurationType type = LaunchHelper.getLaunchConfigType();
        List candidates = Collections.EMPTY_LIST;
        try
        {
            ILaunchConfiguration[] configs = DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations(type);
            candidates = new ArrayList(configs.length);
            for (int i = 0; i < configs.length; i++)
            {
                if (!LaunchHelper.isAdHocSQL(configs[i]))
                {
                    ProcIdentifier  lConfigProc = LaunchHelper.readProcIdentifier(configs[i]);
                    if (lConfigProc == null)
                    {
                        continue;
                    }
                    String name1 = proc.getProcName();
                    String name2 = lConfigProc.getProcName();
                    String ownerName1 = proc.getOwnerName();
                    String ownerName2 = lConfigProc.getOwnerName();
                    int type1 = proc.getType();
                    int type2 = lConfigProc.getType();
                    String dbName1 = proc.getDatabaseName();
                    String dbName2 = lConfigProc.getDatabaseName();

                    if (name1 == null || name2 == null || ownerName1 == null || ownerName2 == null)
                    {
                        RoutineEditorUIActivator.getDefault().log(NLS.bind(Messages.dmpActionHandler_deleteLaunchConfigration_NameOrOwnerNull, new String[]{name1, name2,
                            ownerName1, ownerName2}));

                    }
                    else if (name1.equals(name2) && 
                    ownerName1.equals(ownerName2) && 
                        type1 == type2 && 
                        dbName1.equals(dbName2))
                    {
                        candidates.add(configs[i]);
                    }
                }
            }
            for (Iterator iter = candidates.iterator(); iter.hasNext();)
            {
                ILaunchConfiguration lc = (ILaunchConfiguration) iter.next();
                lc.delete();
            }
        }
        catch (CoreException ex)
        {
        	RoutineEditorUIActivator.getDefault().log(ex);
        }
    }


}