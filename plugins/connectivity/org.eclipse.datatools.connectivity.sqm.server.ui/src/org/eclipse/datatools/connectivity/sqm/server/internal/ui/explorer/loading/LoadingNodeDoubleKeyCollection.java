/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.loading;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class LoadingNodeDoubleKeyCollection{
	
	private Map collection = new HashMap();
	
	public boolean containsKey (Object parent, ILoadingService loadingService){
		boolean containsKey = false;
		
		Iterator keys = collection.keySet().iterator();
		while (keys.hasNext()){
			DoubleKey key = (DoubleKey)keys.next();
			if ((key.getParent() == parent) && (key.getLoadingService() == loadingService)){
				containsKey = true;
				break;
			}
		}	
		return containsKey;		
	}
	
	public void put(Object parent, ILoadingService loadingService, Object node){
		collection.put(new DoubleKey(parent, loadingService), node);
	}
	
	public ILoadingService queryLoadingService(Object parent, LoadingNode placeHolder){
		ILoadingService loadingService = null;
		Iterator keys = collection.keySet().iterator();
		while (keys.hasNext()){
			DoubleKey key = (DoubleKey)keys.next();
			if ((key.getParent() == parent) && (collection.get(key) == placeHolder)){
				loadingService = key.getLoadingService();
			}
		}
		return loadingService;		
	}
		
	public void remove(Object parent, ILoadingService loadingService){
		Iterator keys = collection.keySet().iterator();
		while (keys.hasNext()){
			DoubleKey key = (DoubleKey)keys.next();
			if ((key.getParent() == parent) && (key.getLoadingService() == loadingService)){
				collection.remove(key);
				break;
			}
		}	
	}
	
	public boolean isEmpty(){
		return collection.isEmpty();		
	}
	
	private class DoubleKey{
		
		Object parent;
		ILoadingService loadingService;
		
		public DoubleKey(Object parent, ILoadingService loadingService){
			this.parent = parent;
			this.loadingService = loadingService;
		}
		
		public void setParent(Object parent){
			this.parent = parent;
		}
		
		public void setLoadingService(ILoadingService loadingService){
			this.loadingService = loadingService;
		}	
		
		public Object getParent(){
			return this.parent;
		}
		
		public ILoadingService getLoadingService(){
			return this.loadingService;
		}
	}
}
