/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.loading;

import org.eclipse.jface.viewers.AbstractTreeViewer;

public interface ILoadingService
{
	public static final Object[] EMPTY_ELEMENT_ARRAY = new Object[0];

	public Object[] load (Object parent);
	public String getLoadingDescription();
	
	public class Loading 
	{
		public Object [] getChildren (AbstractTreeViewer viewer, Object parent, ILoadingService service)
		{
	    	if (!(parent instanceof LoadingNode))
	    	{
				LoadingNode placeHolder = LoadingNode.createPlaceHolder(service);
				if (placeHolder != null && LoadingNode.canBeginLoading(service))
				{
					new LoadingJob(viewer, placeHolder, parent, service).schedule();
					return new Object[] { placeHolder };
				}
	    	}
			return EMPTY_ELEMENT_ARRAY;
		}
	}
}
