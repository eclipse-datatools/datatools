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
package org.eclipse.datatools.connectivity.sqm.core.rte;

import java.util.Hashtable;
import java.util.Vector;




public class RefreshManager {
	
	private static RefreshManager instance = null;
	
	private RefreshManager(){
	}
	
	public static RefreshManager getInstance(){
		if (instance == null)
			instance = new RefreshManager();
		
		return instance;
	}
	
	//object can be null. It means the listener care about all the object refresh event
	public void AddListener(ICatalogObject interestedObject,ICatalogObjectListener listener){
		if (interestedObject == null) {
			if (!this.globalListeners.contains(listener)) {
				this.globalListeners.add(listener);
			}
		} else {
			if (this.listeners.containsKey(interestedObject)){
				Vector listenerLinks = (Vector) this.listeners.get(interestedObject);
				if (!listenerLinks.contains(listener)){
					listenerLinks.add(listener);
				}
			} else {
				Vector listenerLinks = new Vector();
				listenerLinks.add(listener);
				this.listeners.put(interestedObject,listenerLinks);
			}
		}
	}
	public void removeListener(ICatalogObject interestedObject, ICatalogObjectListener listener){
		if (interestedObject == null){
			if (this.globalListeners.contains(listener)) {
				this.globalListeners.remove(listener);
			}
		} else {
			if (this.listeners.containsKey(interestedObject)){
				Vector listenerLinks = (Vector) this.listeners.get(interestedObject);
				if (listenerLinks.contains(listener)){
					listenerLinks.remove(listener);
				}
			}
		}
	}

	public void clearListener(){
		this.listeners.clear();
		this.globalListeners.clear();
	}
	
	public void referesh(ICatalogObject object){
		for (int i = 0; i < this.globalListeners.size(); i++) {
			((ICatalogObjectListener)this.globalListeners.elementAt(i)).notifyChanged(object,ICatalogObjectListener.EventTypeEnumeration.ELEMENT_REFRESH);
		}
	
		if (this.listeners.containsKey(object)) {
			Vector listenerLinks = (Vector) this.listeners.get(object);
			for (int i = 0; i < listenerLinks.size(); i++ ){
				ICatalogObjectListener listener = (ICatalogObjectListener)listenerLinks.elementAt(i);
				listener.notifyChanged(object,ICatalogObjectListener.EventTypeEnumeration.ELEMENT_REFRESH);
			}
		}
	}
	
	private Hashtable listeners = new Hashtable();
	private Vector globalListeners = new Vector();
}
