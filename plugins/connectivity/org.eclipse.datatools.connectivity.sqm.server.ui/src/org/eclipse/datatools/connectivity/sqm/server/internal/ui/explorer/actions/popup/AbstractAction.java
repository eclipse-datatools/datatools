/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.actions.popup;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

/**
 * @author ljulien
 */
public abstract class AbstractAction implements IViewActionDelegate, IActionDelegate2
{

	/**
	 * Will get called back each time that the selection is getting updated
	 */
	public void selectionChanged (IAction action, ISelection sel)
	{
		setSelection (sel);
	}

    /**
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
     */
    public void init(IViewPart view)
    {
    }

    /**
	 * @return the selected object - The object will have to be adapted by subclasses
	 */
	protected List getMultipleSelection (Class type) throws NullSelectionException
	{
		List linkedList = new LinkedList ();
		if (getSelection () instanceof IStructuredSelection)
		{
			for (Iterator i = ((IStructuredSelection)getSelection ()).iterator(); i.hasNext();)
			{
				Object nextSelected = i.next();
				if (type.isAssignableFrom(nextSelected.getClass()))
				{
					linkedList.add(nextSelected);
				}
			}
		}
		setSelection(null);
		return linkedList;
	}

    /**
	 * @return the selected object - The object will have to be adapted by subclasses
	 */
	protected Object getUniqueSelection (Class type) throws NullSelectionException
	{
		if (getSelection () instanceof IStructuredSelection)
		{
			for (Iterator i = ((IStructuredSelection)getSelection ()).iterator(); i.hasNext();)
			{
				Object nextSelected = i.next();
				if (nextSelected instanceof IAdaptable)
				{
					setSelection(null);
					return ((IAdaptable)nextSelected).getAdapter(type);
				}
			}
		}
		throw new NullSelectionException ();
	}

    /**
     * @see org.eclipse.ui.IActionDelegate2#init(org.eclipse.jface.action.IAction)
     */
    public void init(IAction action)
    {
    }

    /**
     * @see org.eclipse.ui.IActionDelegate2#dispose()
     */
    public void dispose()
    {
    }

    /**
     * @see org.eclipse.ui.IActionDelegate2#runWithEvent(org.eclipse.jface.action.IAction, org.eclipse.swt.widgets.Event)
     */
    public void runWithEvent(IAction action, Event event)
    {
        run (action);
    }
	
	
	protected abstract void setSelection (ISelection selection);
	protected abstract ISelection getSelection ();
		
	/**
	 * Used when the selection retrieved from the selection service does not make any sense
	 * @author ljulien
	 */
	class NullSelectionException extends Exception
	{

		/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = -3990867374859303441L;
		
	}
}
