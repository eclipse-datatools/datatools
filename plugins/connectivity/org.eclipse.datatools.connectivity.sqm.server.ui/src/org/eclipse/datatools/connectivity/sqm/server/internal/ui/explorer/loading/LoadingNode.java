/*******************************************************************************
 * Copyright (c) 2001, 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.loading;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ImagePath;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

public class LoadingNode implements ILabelProvider
{
	private static final String LOADING = ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.EXPLORER.LOADING"); //$NON-NLS-1$
	private static final LoadingNodeDoubleKeyCollection loadingFiles = new LoadingNodeDoubleKeyCollection();
	private static final LoadingNodeDoubleKeyCollection placeHolders = new LoadingNodeDoubleKeyCollection();

	public static final LoadingNode LOADING_FAMILY = new LoadingNode (); 
	
	private String text;
	private String text1;
	private String text2;
	private String text3;
	private int count = 0;
	private boolean disposed = false;

	public static synchronized LoadingNode createPlaceHolder(Object parent, ILoadingService loadingService)
	{
		LoadingNode node = null;
		if (!placeHolders.containsKey(parent, loadingService))
		{
			placeHolders.put(parent, loadingService, node = new LoadingNode(loadingService));
		}
		return node;
	}
	
	private LoadingNode ()
	{
	}
	
	public LoadingNode(ILoadingService loadingService)
	{
		text = NLS.bind(LOADING, loadingService.getLoadingDescription());
		text1 = text + "."; //$NON-NLS-1$
		text2 = text + ".."; //$NON-NLS-1$
		text3 = text + "..."; //$NON-NLS-1$
	}

	public String getText(Object element)
	{
		switch (count % 4)
		{
			case 0:
				return text;
			case 1:
				return text1;
			case 2:
				return text2;
			case 3:
			default:
				return text3;
		}
	}

	public Image getImage(Object element)
	{
		switch (count = (++count % 4))
		{
			case 0:
				return ResourceLoader.INSTANCE.queryImageFromRegistry(ImagePath.LOADING1);
			case 1:
				return ResourceLoader.INSTANCE.queryImageFromRegistry(ImagePath.LOADING2);
			case 2:
				return ResourceLoader.INSTANCE.queryImageFromRegistry(ImagePath.LOADING3);
			case 3:
			default:
				return ResourceLoader.INSTANCE.queryImageFromRegistry(ImagePath.LOADING4);
		}
	}

	public boolean isDisposed()
	{
		return disposed;
	}

	public void dispose(Object parent, LoadingNode placeHolder)
	{
		disposed = true;

		ILoadingService loadingService = placeHolders.queryLoadingService(parent, placeHolder);
		 
		loadingFiles.remove(parent, loadingService);
		placeHolders.remove(parent, loadingService);		
	}

	public static synchronized boolean canBeginLoading(Object parent, ILoadingService loadingService)
	{
		if (!loadingFiles.containsKey(parent, loadingService))
		{
			loadingFiles.put(parent, loadingService, null);
			return true;
		}
		return false;
	}
	
	public static boolean isLoading ()
	{
		synchronized (loadingFiles)
		{
			return !loadingFiles.isEmpty();
		}
	}
	
	public void addListener(ILabelProviderListener listener)
	{
	}

	public boolean isLabelProperty(Object element, String property)
	{
		return false;
	}

	public void removeListener(ILabelProviderListener listener)
	{
	}

	public void dispose() {	
	}
}


