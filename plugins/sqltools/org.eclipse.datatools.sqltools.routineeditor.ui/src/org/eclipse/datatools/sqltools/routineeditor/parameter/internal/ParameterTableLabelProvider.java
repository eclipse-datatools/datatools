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

import org.eclipse.datatools.sqltools.common.ui.util.Images;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterWrapper;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Yang Liu
 */
public class ParameterTableLabelProvider implements ITableLabelProvider
{

    /**
     * 
     */
    public ParameterTableLabelProvider()
    {
        super();
    }

    private Image getSelectedImage(boolean selected)
    {
        if (selected)
        {
            return Images.get(Images.IMG_OTHER_CHECKED);
        }
        else
        {
            return Images.get(Images.IMG_OTHER_UNCHECKED);
        }
    }
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    public Image getColumnImage(Object element, int columnIndex)
    {
        if (columnIndex == ParameterTableViewer.NULLCOLUMN)
        {
            ParameterWrapper wrapper = (ParameterWrapper)element;
            return getSelectedImage(wrapper.isNull());
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    public String getColumnText(Object element, int columnIndex)
    {
        String s = getColumnText1(element, columnIndex);
        if (s == null) 
        {
            return "";
        }
        else
        {
            return s;
        }
    }

    public String getColumnText1(Object element, int columnIndex)
    {
        ParameterWrapper wrapper = (ParameterWrapper) element;
        ParameterDescriptor pd = wrapper.getParameterDescriptor();
        switch (columnIndex)
        {
            case ParameterTableViewer.NAMECOLUMN:
                if (pd == null)
                {
                    return "";
                }
                else
                {
                    return pd.getName();
                }
            case ParameterTableViewer.TYPECOLUMN:
                if (pd == null)
                {
                    return "";
                }
                else
                {
                    if (pd.getSqlTypeNameFromParser()!=null)
                    {
                        return pd.getSqlTypeNameFromParser();
                    }
                    else
                    {
                        return pd.getTypeName();
                    }
                }
            case ParameterTableViewer.NULLCOLUMN:
                return "";
            case ParameterTableViewer.VALUECOLUMN:
                if (wrapper.isNull())
                {
                    return "(null)";
                }
                return wrapper.getValue();
            case ParameterTableViewer.INOUTCOLUMN:
                if (pd == null)
                {
                    return "";
                }
                else
                {
                    return pd.getParamTypeAsString();
                }
        }
        return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void addListener(ILabelProviderListener listener)
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    public boolean isLabelProperty(Object element, String property)
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void removeListener(ILabelProviderListener listener)
    {
    }

}
