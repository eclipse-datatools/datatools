/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditorModelListener;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * The notifier for the model change listeners. It is itself a model listener, it accepts all events from the model
 * object and model's contaiment objects, also from the additional model defined in <code>ISchemaObjectEditModel</code>,
 * then delegate the events to all the listeners of this notifier.
 * 
 * @see ISchemaObjectEditModel
 * @author Idull
 */
public class SchemaObjectEditorModelListenersNotifier extends AdapterImpl
{
    private ListenerList _listeners;

    public SchemaObjectEditorModelListenersNotifier()
    {
        super();
        _listeners = new ListenerList();
    }

    public void addModelListener(ISchemaObjectEditorModelListener listener)
    {
        if (listener != null)
        {
            _listeners.add(listener);
        }
    }

    public void removeModelListener(ISchemaObjectEditorModelListener listener)
    {
        if (listener != null)
        {
            _listeners.remove(listener);
        }
    }

    public void removeAllListeners()
    {
        _listeners.clear();
    }

    private void registerListenerForObject(EObject obj)
    {
        if (!obj.eAdapters().contains(this))
        {
            obj.eAdapters().add(this);
        }

        // add listener for containment feature
        Iterator containments = ((EClass) obj.eClass()).getEAllContainments().iterator();
        while (containments.hasNext())
        {
            Object containmentObj = obj.eGet((EStructuralFeature) containments.next());
            if (containmentObj instanceof EList)
            {
                Iterator iter = ((EList) containmentObj).iterator();
                while (iter.hasNext())
                {
                    registerListenerForObject((EObject) iter.next());
                }
            }
            else if (containmentObj instanceof EObject)
            {
                registerListenerForObject((EObject) containmentObj);
            }
        }
    }

    /**
     * Registers listeners for all SQL objects and their containment features containing in the given editor input
     * 
     * @param input the editor input
     */
    public void registerListener(ISchemaObjectEditorInput input)
    {
        ISchemaObjectEditModel model = input.getEditModelObject();
        if (model == null)
        {
            return;
        }

        registerListenerForObject(model.getMainSQLObject());

        Map additionalObjs = model.getAdditionalSQLObjects();
        Iterator iter = additionalObjs.values().iterator();
        while (iter.hasNext())
        {
            Object value = iter.next();
            if (value instanceof Collection)
            {
                Collection col = (Collection) value;
                if (col == null)
                {
                    continue;
                }

                Iterator colIter = col.iterator();
                while (colIter.hasNext())
                {
                    Object item = colIter.next();
                    if (item instanceof EObject)
                    {
                        registerListenerForObject((EObject) item);
                    }
                }
            }
            else if (value instanceof EObject)
            {
                registerListenerForObject((EObject) value);
            }
        }
    }

    public void notifyChanged(Notification msg)
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            try
            {
                ((ISchemaObjectEditorModelListener) listeners[i]).notifyChanged(msg);
            }
            catch (Exception e)
            {
                // do nothing
            }
        }
    }
}
