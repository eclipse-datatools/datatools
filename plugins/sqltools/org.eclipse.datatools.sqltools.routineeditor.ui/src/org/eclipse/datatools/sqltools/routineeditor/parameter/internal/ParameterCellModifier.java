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

import org.eclipse.datatools.sqltools.core.dbitem.ParameterWrapper;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author Yang Liu
 */
public class ParameterCellModifier implements ICellModifier
{

    ParameterTableViewer	_viewer;
    /**
     * 
     */
    public ParameterCellModifier(ParameterTableViewer viewer)
    {
        super();
        _viewer = viewer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property)
    {
        ParameterWrapper wrapper = (ParameterWrapper)element;
        if (ParameterTableViewer.COLUMNPROPERTIES[ParameterTableViewer.NULLCOLUMN].equals(property))
        {
            return wrapper.getParameterDescriptor().canBeNull();
        }
        else if (ParameterTableViewer.COLUMNPROPERTIES[ParameterTableViewer.VALUECOLUMN].equals(property))
        {
            if (wrapper.isNull())
            {
                wrapper.setNull(false);
                _viewer.refresh();
            }
            return true;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
     */
    public Object getValue(Object element, String property)
    {
        ParameterWrapper wrapper = (ParameterWrapper)element;
        // TODO Auto-generated method stub
        if (ParameterTableViewer.COLUMNPROPERTIES[ParameterTableViewer.NULLCOLUMN].equals(property)) 
        {
            return new Boolean(wrapper.isNull());
        }
        else if (ParameterTableViewer.COLUMNPROPERTIES[ParameterTableViewer.VALUECOLUMN].equals(property)) 
        {
            String v = wrapper.getValue();
            if (v==null) return "";
            else return v;
        }
        return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, Object value)
    {
        TableItem item = (TableItem)element;
        ParameterWrapper wrapper = (ParameterWrapper)item.getData();

        // When user input nothing in the editor cell, we should take it as null, otherwise, user will be confused.

        if(value == null ||value.toString().equalsIgnoreCase(""))
        {
            wrapper.setValue(null);
            wrapper.setNull(true);
        }
        else if (ParameterTableViewer.COLUMNPROPERTIES[ParameterTableViewer.NULLCOLUMN].equals(property)) 
        {
            boolean b = ((Boolean)value).booleanValue();
            wrapper.setValue(null);
            wrapper.setNull(b);
        }
        else if (ParameterTableViewer.COLUMNPROPERTIES[ParameterTableViewer.VALUECOLUMN].equals(property)) 
        {
            String s = (String)value;
            wrapper.setValue(s);
        }
        _viewer.refresh();
        //        return "hello";
    }

}
