/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.sqltools.result.core.IResultManagerListener;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.internal.utils.SerializationHelper;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;

/**
 * A standard implementation of IResultManager.
 * 
 * @author Dafan Yang
 */
public class ResultManager implements IResultManager
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long      serialVersionUID = -344302636933874156L;
    transient private ListenerList _listeners       = new ListenerList();
    
    private static transient String _ClassVersionID = SerializationHelper.getResultManagerVersion();
    
    /**
     * The map between operation request and result instance. All results include sub-results are maintained in this map
     */
    private Map                _operationInstanceMap            = new Hashtable();
    
    private List                   _results         = new Vector();

	protected ResultManager()
    {
    }

    public void addResultManagerListener(IResultManagerListener listener)
    {
        _listeners.add(listener);
    }

    public void fireAdded(IResultInstance instance)
    {
    	_operationInstanceMap.put(instance.getOperationCommand(), instance);
    	
    	boolean isSubInstance = instance.getParentResult() != null;
    	if(!isSubInstance)
        {
        	_results.add(instance);
        	
        	Object[] listeners = this._listeners.getListeners();
            for (int i = 0; i < listeners.length; i++)
            {
                ((IResultManagerListener) listeners[i]).resultInstanceCreated(instance);
            }
        }
    }

    protected void fireAllRemoved()
    {
        Object[] listeners = this._listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((IResultManagerListener) listeners[i]).allResultInstancesRemoved();
        }
    }

    public void fireAppended(IResultInstance instance, ResultItem r, int index)
    {
        Object[] listeners = this._listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((IResultManagerListener) listeners[i]).resultInstanceAppended(instance, r, index);
        }
    }

    public void fireInstanceReset(IResultInstance instance)
    {
        Object[] listeners = this._listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((IResultManagerListener) listeners[i]).resultInstanceReset(instance);
        }
    }

    public void fireParametersShow(IResultInstance instance, List params)
    {
        Object[] listeners = this._listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((IResultManagerListener) listeners[i]).parametersShow(instance, params);
        }
    }

    protected void fireRemoved(IResultInstance instance)
    {
        Object[] listeners = this._listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((IResultManagerListener) listeners[i]).resultInstanceRemoved(instance);
        }
    }

    protected void fireRemoved(IResultInstance[] instances)
    {
        Object[] listeners = this._listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((IResultManagerListener) listeners[i]).resultInstancesRemoved(instances);
        }
    }
    
    public void fireStatusUpdated(IResultInstance instance)
    {
        Object[] listeners = this._listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((IResultManagerListener) listeners[i]).resultInstanceStatusUpdated(instance);
        }
    }

    public IResultInstance[] getAllResults()
    {
        synchronized (_results)
        {
            return (IResultInstance[]) _results.toArray(new IResultInstance[_results.size()]);
        }
    }

    public IResultInstance getInstance(OperationCommand cmd)
    {
        return (IResultInstance) _operationInstanceMap.get(cmd);
    }
    
    public void removeAllFinished()
    {
        boolean removed = false;
        synchronized (_results)
        {
            //clear the map
            _operationInstanceMap.clear();
            for (Iterator iter = _results.iterator(); iter.hasNext();)
            {
                IResultInstance instance = (IResultInstance) iter.next();
                if (instance.isFinished())
                {
                    iter.remove();
                    removed = true;
                    instance.dispose();
                }
            }
        }
        if (removed)
        {
            fireAllRemoved();
        }
    }

    public void removeResultInstance(IResultInstance instance)
    {
        boolean removed;
        synchronized (_results)
        {
            removed = _results.remove(instance);
            if(instance.getSubResults().size() > 0)
            {
                for(Iterator iter = instance.getSubResults().iterator(); iter.hasNext();)
                {
                    Object subri = iter.next();
                    
                    if(subri instanceof IResultInstance)
                    {
                        _operationInstanceMap.remove(((IResultInstance)subri).getOperationCommand());
                        ((IResultInstance)subri).dispose();
                    }
                }
            }
            _operationInstanceMap.remove(instance.getOperationCommand());
            instance.dispose();
        }
        if (removed)
        {
            fireRemoved(instance);
        }
    }

    public void removeResultInstances(IResultInstance[] instances)
    {
        if (instances != null)
        {
            boolean removed = false;
            synchronized (_results)
            {
                for(int i=0;i<instances.length;i++)
                {
                    if(instances[i] != null)
                    {
                        boolean succeeded = _results.remove(instances[i]);
                        if(instances[i].getSubResults().size() > 0)
                        {
                            for(Iterator iter = instances[i].getSubResults().iterator(); iter.hasNext();)
                            {
                                Object subri  = iter.next();
                                
                                if(subri instanceof IResultInstance)
                                {
                                    _operationInstanceMap.remove(((IResultInstance)subri).getOperationCommand());
                                    ((IResultInstance)subri).dispose();
                                }
                            }
                        }
                        _operationInstanceMap.remove(instances[i].getOperationCommand());
                        instances[i].dispose();
                        if(succeeded && !removed)
                        {
                            removed = true;
                        }
                    }
                }
            }
            if(removed)
            {
                fireRemoved(instances);
            }
        }
    }

    public void removeResultManagerListener(IResultManagerListener listener)
    {
        _listeners.remove(listener);
    }  
    
    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        Object obj = stream.readObject();
        if(!(obj instanceof String) || !((String)obj).startsWith(SerializationHelper.RESULT_FLAG))
        {
            throw new ClassVersionIncompatibleException("Invalid result file format.");
        }
        else if(!SerializationHelper.resultManagerVersionCompatible(obj.toString()))
        {
            throw new ClassVersionIncompatibleException(obj.toString(), SerializationHelper.getResultManagerVersion());
        }
        stream.defaultReadObject();
        _listeners = new ListenerList();
    }
    
    private void writeObject(ObjectOutputStream oos) throws IOException, ClassNotFoundException
    {
        oos.writeObject(_ClassVersionID);
        oos.defaultWriteObject();
    }
    
    public void initializeContent(IResultManager manager) 
    {
    	this._operationInstanceMap = ((ResultManager)manager)._operationInstanceMap;
    	this._results = ((ResultManager)manager)._results;
    }
}
