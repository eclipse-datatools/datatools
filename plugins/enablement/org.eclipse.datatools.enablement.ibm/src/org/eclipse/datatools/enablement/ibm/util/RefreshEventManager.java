/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogObjectEvent;
import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogObjectEventListener;

public class RefreshEventManager
{
	private static RefreshEventManager refreshEventManager;
	private RefreshManager delegateRefreshManager = RefreshManager.getInstance();
	
	private HashMap<ICatalogObject, ArrayList<ICatalogObjectEventListener>> catalogObjectEventListeners;
	private ArrayList<ICatalogObjectEventListener> globalCatalogObjectEventListeners;
	
	private RefreshEventManager()
	{
		catalogObjectEventListeners = new HashMap<ICatalogObject, ArrayList<ICatalogObjectEventListener>>();
		globalCatalogObjectEventListeners = new ArrayList<ICatalogObjectEventListener>();
	}
	
	public static RefreshEventManager getInstance()
	{
		if (refreshEventManager == null) {
			refreshEventManager = new RefreshEventManager();
		}
		
		return refreshEventManager;
	}
	
	public void addCatalogObjectEventListener(
			ICatalogObject interestedObject, ICatalogObjectEventListener listener)
	{
		if (interestedObject == null) {
			if (!globalCatalogObjectEventListeners.contains(listener)) {
				globalCatalogObjectEventListeners.add(listener);
			}
		} else {
			if (catalogObjectEventListeners.containsKey(interestedObject)) {
				ArrayList<ICatalogObjectEventListener> listeners = 
					catalogObjectEventListeners.get(interestedObject);
				if (!listeners.contains(listener)) {
					listeners.add(listener);
				}
			} else {
				ArrayList<ICatalogObjectEventListener> listeners = 
					new ArrayList<ICatalogObjectEventListener>();
				listeners.add(listener);
				catalogObjectEventListeners.put(interestedObject, listeners);
			}
		}
	}
	
	public void removeCatalogObjectEventListener(
			ICatalogObject interestedObject, ICatalogObjectEventListener listener)
	{
		if (interestedObject == null) {
			if (globalCatalogObjectEventListeners.contains(listener)) {
				globalCatalogObjectEventListeners.remove(listener);
			}
		} else {
			if (catalogObjectEventListeners.containsKey(interestedObject)) {
				ArrayList<ICatalogObjectEventListener> listeners = 
					catalogObjectEventListeners.get(interestedObject);
				if (listeners.contains(listener)) {
					listeners.remove(listener);
				}
			}
		}
	}
	
	public void refresh(CatalogObjectEvent event)
	{
		delegateRefreshManager.referesh(event.element);
		
		for (ICatalogObjectEventListener listener : globalCatalogObjectEventListeners) {
			listener.notifyChanged(event);
		}
		
		if (catalogObjectEventListeners.containsKey(event.element)) {
			ArrayList<ICatalogObjectEventListener> listeners = catalogObjectEventListeners.get(event.element);
			for (ICatalogObjectEventListener listener : listeners) {
				listener.notifyChanged(event);
			}
		}
	}
}
