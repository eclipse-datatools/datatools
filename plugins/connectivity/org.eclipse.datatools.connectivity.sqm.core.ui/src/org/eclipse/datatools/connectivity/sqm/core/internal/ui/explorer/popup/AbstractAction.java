/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.popup;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.navigator.CommonViewer;

public abstract class AbstractAction extends Action implements ISelectionChangedListener
{
	protected SelectionChangedEvent event;
	protected CommonViewer viewer;
	
	public AbstractAction ()
	{
		initialize ();
	}
	
	protected abstract void initialize ();

	protected void initializeAction (ImageDescriptor image, ImageDescriptor disabledImage, String text, String toopTip)
    {
		if (image != null)
		{
			this.setImageDescriptor(image);
		}
		if (disabledImage != null)
		{
			this.setDisabledImageDescriptor(disabledImage);
		}
		this.setText(text);
		this.setToolTipText(toopTip);
    }

	public void setCommonViewer (CommonViewer viewer)
	{
		this.viewer = viewer;
	}
	
    public void selectionChanged(SelectionChangedEvent event)
    {
    	this.event = event;
    }
}
