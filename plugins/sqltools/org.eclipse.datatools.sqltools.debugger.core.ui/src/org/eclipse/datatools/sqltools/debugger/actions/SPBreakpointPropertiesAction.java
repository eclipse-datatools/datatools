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
package org.eclipse.datatools.sqltools.debugger.actions;

import org.eclipse.datatools.sqltools.debugger.breakpoint.SPLineBreakpoint;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.PropertyDialogAction;


/**
 * Presents the standard properties dialog to configure the attibutes of a SP Breakpoint.
 * 
 * @author Yang Liu
 */
public class SPBreakpointPropertiesAction implements IObjectActionDelegate 
{

    private IWorkbenchPart fPart;
    private SPLineBreakpoint fBreakpoint;

    /**
     * @see IActionDelegate#run(IAction)
     */
    public void run(IAction action) 
    {
        PropertyDialogAction propertyAction= 
            new PropertyDialogAction(getShell(), new ISelectionProvider() 
        {
            public void addSelectionChangedListener(ISelectionChangedListener listener) 
            {
            }
            public ISelection getSelection() 
            {
                return new StructuredSelection(getBreakpoint());
            }
            public void removeSelectionChangedListener(ISelectionChangedListener listener) 
            {
            }
            public void setSelection(ISelection selection) 
            {
            }
        }
        );
        propertyAction.run();
    }

    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) 
    {
        if (selection instanceof IStructuredSelection) 
        {
            IStructuredSelection ss= (IStructuredSelection)selection;
            if (ss.isEmpty() || ss.size() > 1) 
            {
                return;
            }
            Object element= ss.getFirstElement();
            if (element instanceof SPLineBreakpoint) 
            {
                setBreakpoint((SPLineBreakpoint)element);
            }
        }
    }

    protected Shell getShell()
    {
        if (fPart == null)
        {
            return DebuggerCoreUIPlugin.getActiveWorkbenchShell();
        }
        else
        {
            return fPart.getSite().getShell();
        }
    }
    protected IWorkbenchPart getActivePart() 
    {
        return fPart;
    }

    protected void setActivePart(IWorkbenchPart part) 
    {
        fPart = part;
    }

    protected SPLineBreakpoint getBreakpoint() 
    {
        return fBreakpoint;
    }

    public void setBreakpoint(SPLineBreakpoint breakpoint) 
    {
        fBreakpoint = breakpoint;
    }
    /**
     * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) 
    {
        setActivePart(targetPart);
    }
}
