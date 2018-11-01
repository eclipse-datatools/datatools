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

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditor;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorImages;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author Hui Cao
 * 
 */
public class EditRoutineAction extends RoutineAction implements IActionDelegate {

	public static final String ACTION_ID = "EditRoutineAction";
	/**
	 * 
	 */
	public EditRoutineAction() {
		init();
	}

	/**
	 * Constructs a CommonSQLObjectAction from the selected resource. This
	 * happens when this action is instantiated by the common action provider.
	 */
	public EditRoutineAction(Object selectedResource) {
		init();
		initSQLObject(this, selectedResource);
		initConnectionProfile();
	}
	
	protected void init() {
		setId(ACTION_ID);
		setText(Messages.EditRoutineAction_label);
    	setToolTipText(Messages.EditRoutineAction_tooltip);
        setImageDescriptor(RoutineEditorImages.getImageDescriptor("routine_editor"));
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
			ProcIdentifier proc = SQLDevToolsUtil.getProcIdentifier(
					new DatabaseIdentifier(_connectionProfile.getName(),
							getDatabaseName()), _sqlObject);
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			try {
				page.openEditor(new ProcEditorInput(proc),
						RoutineEditor.EDITOR_ID);
			} catch (Exception e) {
				RoutineEditorUIActivator.getDefault().log(e);
			}
		}
	}
	
	
}