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

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;

/**
 * 
 * @author Hui Cao
 * 
 */
public class RoutineActionProvider extends CommonActionProvider {

	public RoutineActionProvider() {
		// TODO Auto-generated constructor stub
	}

    private IStructuredSelection selection;

	/**
	 * @param selection
	 */
	private void setSelection ( IStructuredSelection selection ) {
        this.selection = selection;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ICommonActionProvider#setActionContext(org.eclipse.ui.actions.ActionContext)
	 */
	public void setActionContext(ActionContext aContext) {
        if (aContext.getSelection() instanceof IStructuredSelection)
        {
        	setSelection((IStructuredSelection) aContext.getSelection());
        }
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ICommonActionProvider#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager manager) {
		boolean hasSelection = false;
		Object firstElement = selection.getFirstElement();
		if (selection != null && firstElement != null)
			hasSelection = true;
		RoutineAction routineAction;
		if (hasSelection && firstElement instanceof SQLObject && SQLDevToolsUtil.getProcType((SQLObject)firstElement)!= ProcIdentifier.TYPE_SQL) {
			routineAction = new DropRoutineAction(firstElement);
			manager.insertAfter("slot2",
					(IAction) routineAction);
			routineAction = new DebugAction(firstElement);
			manager.insertAfter("slot1",
					(IAction) routineAction);
			routineAction = new RunAction(firstElement);
			manager.insertAfter("slot1",
					(IAction) routineAction);
			routineAction = new EditRoutineAction(firstElement);
			manager.insertAfter("slot1",
					(IAction) routineAction);
			//TODO SQL drop
		}

		return;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.ICommonActionProvider#fillActionBars(org.eclipse.ui.IActionBars)
	 */
	public void fillActionBars(IActionBars bars) {
        bars.updateActionBars();
        bars.getMenuManager().update();
        return;
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.actions.ActionGroup#setContext(org.eclipse.ui.actions.ActionContext)
	 */
	public void setContext(ActionContext context) {
		super.setContext(context);
		if (context != null && context.getSelection() instanceof IStructuredSelection)
        {
        	setSelection((IStructuredSelection) context.getSelection());
        }
	}
	
	protected IStructuredSelection getSelection()
	{
		return selection;
	}
	
}
