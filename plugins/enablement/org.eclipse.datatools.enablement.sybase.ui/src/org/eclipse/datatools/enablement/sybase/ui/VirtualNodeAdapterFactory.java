/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ui;

/**
 * @author linsong
 */
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.enablement.sybase.VirtualNodeAdapter;

public class VirtualNodeAdapterFactory implements IAdapterFactory 
{

	public Object getAdapter(Object adaptableObject, Class adapterType) 
	{
		if(adapterType.isAssignableFrom(VirtualNodeAdapter.class))
		{
			IVirtualNode vn = (IVirtualNode)adaptableObject;
			return new VirtualNodeAdapter(vn.getGroupID());
		}
		return null;
	}

	public Class[] getAdapterList() 
	{
		return new Class[]{IVirtualNode.class};
	}

}
