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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ImagePath;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

public class LoadingNode implements ILabelProvider
{
	private static final String LOADING = ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.EXPLORER.LOADING"); //$NON-NLS-1$
	private static final Set loadingFiles = new HashSet();
	private static final Map placeHolders = new HashMap();

	public static final LoadingNode LOADING_FAMILY = new LoadingNode (); 
	
	private String text;
	private String text1;
	private String text2;
	private String text3;
	private int count = 0;
	private boolean disposed = false;
	private ILoadingService loadingService;

	public static synchronized LoadingNode createPlaceHolder(ILoadingService loadingService)
	{
		LoadingNode node = null;
		if (!placeHolders.containsKey(loadingService))
		{
			placeHolders.put(loadingService, node = new LoadingNode(loadingService));
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
		this.loadingService = loadingService;
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

	public void dispose()
	{
		disposed = true;
		placeHolders.remove(loadingService);
		loadingFiles.remove(loadingService);
	}

	public static synchronized boolean canBeginLoading(ILoadingService loadingService)
	{
		if (!loadingFiles.contains(loadingService))
		{
			loadingFiles.add(loadingService);
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
}
