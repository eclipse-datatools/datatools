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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl;

import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.widgets.Display;

/**
 * @author ljulien
 */
public abstract class AbstractDecorationService extends LabelProvider implements ILightweightLabelDecorator
{
	protected Map objectMap = new WeakHashMap();

	public void refreshDecoration (Object element)
	{
	    fireLabelChangedEvent (new LabelProviderChangedEvent(this, element));
	}

	/**
	 * Will notify the Model Explorer to redecorate
	 * @param event
	 */
	protected void fireLabelChangedEvent(final LabelProviderChangedEvent event)
	{
	    if (!(event.getElements().length == 1 && event.getElements()[0] == null))
	    {
			Display.getDefault().asyncExec(new Runnable()
			{
				public void run()
				{
					fireLabelProviderChanged(event);
				}
			});
	    }
	}
}
