/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.internal.refresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObjectListener;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;

/**
 * @author Hui Cao
 */
public class RefreshManager2
{

 private RefreshManager _delegate = RefreshManager.getInstance();

    private static RefreshManager2 instance = null;

    private RefreshManager2()
    {
    }

    public static RefreshManager2 getInstance()
    {
        if (instance == null)
        {
            instance = new RefreshManager2();
        }
        return instance;
    }

    public void addListener(ICatalogObject interestedObject, ICatalogObjectListener2 listener)
    {
        if (interestedObject == null)
        {
            if (!this.globalListeners.contains(listener)) {
                this.globalListeners.add(listener);
            }
        }
        else
        {
            if (this.listeners.containsKey(interestedObject))
            {
                ArrayList listenerLinks = (ArrayList) this.listeners.get(interestedObject);
                if (!listenerLinks.contains(listener))
                {
                    listenerLinks.add(listener);
                }
            }
            else
            {
                ArrayList listenerLinks = new ArrayList();
                listenerLinks.add(listener);
                this.listeners.put(interestedObject, listenerLinks);
            }
        }

    }

    /**
     * Addes global listeners interested in specific object types.
     * 
     * @param type
     * @param listener
     */
    public void addListener(Class type, ICatalogObjectListener2 listener)
    {
        if (type == null)
        {
            if (!this.globalListeners.contains(listener)) {
                this.globalListeners.add(listener);
            }
        }
        else
        {
            if (this.typeListeners.containsKey(type))
            {
                ArrayList listenerLinks = (ArrayList) this.typeListeners.get(type);
                if (!listenerLinks.contains(listener))
                {
                    listenerLinks.add(listener);
                }
            }
            else
            {
                ArrayList listenerLinks = new ArrayList();
                listenerLinks.add(listener);
                this.typeListeners.put(type, listenerLinks);
            }
        }
    }

    public void removeListener(ICatalogObject interestedObject, ICatalogObjectListener2 listener)
    {
        if (interestedObject == null)
        {
            if (this.globalListeners.contains(listener)) {
                this.globalListeners.remove(listener);
            }
        }
        else
        {
            if (this.listeners.containsKey(interestedObject))
            {
                ArrayList listenerLinks = (ArrayList) this.listeners.get(interestedObject);
                if (listenerLinks.contains(listener))
                {
                    listenerLinks.remove(listener);
                }
            }
        }
    }

    public void removeListener(Class type, ICatalogObjectListener2 listener)
    {
        if (type == null)
        {
            if (this.globalListeners.contains(listener)) {
                this.globalListeners.remove(listener);
            }
        }
        else
        {
            if (this.typeListeners.containsKey(type))
            {
                ArrayList listenerLinks = (ArrayList) this.typeListeners.get(type);
                if (listenerLinks.contains(listener))
                {
                    listenerLinks.remove(listener);
                }
            }
        }
    }

    public void clearListener()
    {
        this.listeners.clear();
        this.typeListeners.clear();
        this.globalListeners.clear();
    }

    public void referesh(ICatalogObject object, String context)
    {
        _delegate.referesh(object);
        
        for (int i = 0; i < this.globalListeners.size(); i++)
        {
            ((ICatalogObjectListener2) this.globalListeners.get(i)).notifyChanged(object,
                    ICatalogObjectListener.EventTypeEnumeration.ELEMENT_REFRESH, context);
        }
        
        for (Iterator it = typeListeners.keySet().iterator(); it.hasNext();)
        {
            Class clazz = (Class) it.next();
            if (clazz.isInstance(object))
            {
                ArrayList listenerLinks = (ArrayList) this.typeListeners.get(object);
                for (int i = 0; i < listenerLinks.size(); i++)
                {
                    ICatalogObjectListener2 listener = (ICatalogObjectListener2) listenerLinks.get(i);
                    listener.notifyChanged(object, ICatalogObjectListener.EventTypeEnumeration.ELEMENT_REFRESH, context);
                }
            }
            
        }
        
        if (this.listeners.containsKey(object))
        {
            ArrayList listenerLinks = (ArrayList) this.listeners.get(object);
            for (int i = 0; i < listenerLinks.size(); i++)
            {
                ICatalogObjectListener2 listener = (ICatalogObjectListener2) listenerLinks.get(i);
                listener.notifyChanged(object, ICatalogObjectListener.EventTypeEnumeration.ELEMENT_REFRESH, context);
            }
        }
    }

    private HashMap listeners       = new HashMap();
    private HashMap typeListeners       = new HashMap();
    private ArrayList globalListeners = new ArrayList();

}
