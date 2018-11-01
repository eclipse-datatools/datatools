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
package org.eclipse.datatools.sqltools.sqleditor.internal.editor;

import java.util.ArrayList;

import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeployable;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParamDefList;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLStatement;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode;
import org.eclipse.datatools.sqltools.sql.ui.SQLImageService;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

class SQLLabelProvider extends LabelProvider
{
    private ArrayList _fLabelDecorators;

    public Image getBaseImage(Object element)
    {
        if (!(element instanceof SimpleNode))
        {
            return null;
        }

        if (element instanceof IASTSQLStatement || element instanceof IASTDeployable)
        {
            return SQLImageService.INSTANCE.getNodeImage((Node)element);
        }

        if (element instanceof IASTSQLParam)
        {
            return SQLEditorResources.getImage("datatype");
        }

        if (element instanceof IASTSQLParamDefList)
        {
            return SQLEditorResources.getImage("parameter");
        }

        return null;
    }

    public String getBaseText(Object element)
    {
        if (element instanceof SimpleNode)
        {
            return ((SimpleNode) element).toString();
        }
        return super.getText(element);
    }

    /**
     * Adds a decorator to the label provider
     */
    public void addLabelDecorator(ILabelDecorator decorator)
    {
        if (_fLabelDecorators == null)
        {
            _fLabelDecorators = new ArrayList(2);
        }
        _fLabelDecorators.add(decorator);
    }

    protected Image decorateImage(Image image, Object element)
    {
        if (_fLabelDecorators != null && image != null)
        {
            for (int i = 0; i < _fLabelDecorators.size(); i++)
            {
                ILabelDecorator decorator = (ILabelDecorator) _fLabelDecorators.get(i);
                image = decorator.decorateImage(image, element);
            }
        }
        return image;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ILabelProvider#getImage
     */
    public Image getImage(Object element)
    {
        return decorateImage(getBaseImage(element), element);
    }

    protected String decorateText(String text, Object element)
    {
        if (_fLabelDecorators != null && text.length() > 0)
        {
            for (int i = 0; i < _fLabelDecorators.size(); i++)
            {
                ILabelDecorator decorator = (ILabelDecorator) _fLabelDecorators.get(i);
                text = decorator.decorateText(text, element);
            }
        }
        return text;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ILabelProvider#getText
     */
    public String getText(Object element)
    {
        return decorateText(getBaseText(element), element);
    }

    /*
     * (non-Javadoc)
     * 
     * @see IBaseLabelProvider#dispose
     */
    public void dispose()
    {
        if (_fLabelDecorators != null)
        {
            for (int i = 0; i < _fLabelDecorators.size(); i++)
            {
                ILabelDecorator decorator = (ILabelDecorator) _fLabelDecorators.get(i);
                decorator.dispose();
            }
            _fLabelDecorators = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see IBaseLabelProvider#addListener(ILabelProviderListener)
     */
    public void addListener(ILabelProviderListener listener)
    {
        if (_fLabelDecorators != null)
        {
            for (int i = 0; i < _fLabelDecorators.size(); i++)
            {
                ILabelDecorator decorator = (ILabelDecorator) _fLabelDecorators.get(i);
                decorator.addListener(listener);
            }
        }
        super.addListener(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see IBaseLabelProvider#removeListener(ILabelProviderListener)
     */
    public void removeListener(ILabelProviderListener listener)
    {
        if (_fLabelDecorators != null)
        {
            for (int i = 0; i < _fLabelDecorators.size(); i++)
            {
                ILabelDecorator decorator = (ILabelDecorator) _fLabelDecorators.get(i);
                decorator.removeListener(listener);
            }
        }
        super.removeListener(listener);
    }

    public static ILabelDecorator[] getDecorators(boolean errortick, ILabelDecorator extra)
    {
        if (errortick)
        {
            if (extra == null)
            {
                return new ILabelDecorator[] 
                {
                }
                ;
            }
            else
            {
                return new ILabelDecorator[] 
                {
                    extra
                }
                ;
            }
        }
        if (extra != null)
        {
            return new ILabelDecorator[] 
            {
                extra
            }
            ;
        }
        return null;
    }

}
