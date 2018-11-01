/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.parameter.internal;

import org.eclipse.datatools.sqltools.routineeditor.parameter.EventParameter;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

/**
 * A cell modifier for event parameters
 * @author Hui Cao
 *
 */
public class EventParameterCellModifier implements ICellModifier
{
    private TableViewer _tableViewer;
    /**
     * 
     */
    public EventParameterCellModifier(TableViewer tableViewer)
    {
        super();
        _tableViewer = tableViewer;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property)
    {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
     */
    public Object getValue(Object element, String property)
    {
        EventParameter wrapper = (EventParameter)element;
        if (EventParameterTableDialog.NAME_COLUMN.equals(property)) 
        {
            return wrapper.getName();
        }
        else 
        {
            return wrapper.getValue();
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, Object value)
    {
        EventParameter wrapper = (EventParameter)(((TableItem)element).getData());
        if (EventParameterTableDialog.NAME_COLUMN.equals(property)) 
        {
            wrapper.setName((String)value);
        }
        else 
        {
            wrapper.setValue((String)value);
        }
        _tableViewer.refresh();
    }

}
