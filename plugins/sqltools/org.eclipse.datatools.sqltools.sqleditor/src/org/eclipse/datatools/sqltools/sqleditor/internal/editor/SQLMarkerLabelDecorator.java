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

import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.common.ui.util.ImageDescriptorRegistry;
import org.eclipse.datatools.sqltools.common.ui.util.ImageImageDescriptor;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.MarkerAnnotation;

/**
 * @author Hui Cao
 *  
 */
public class SQLMarkerLabelDecorator implements ILabelDecorator, ILightweightLabelDecorator
{

    private ImageDescriptorRegistry _fRegistry;
    private SQLEditor               _editor;

    /**
     *  
     */
    public SQLMarkerLabelDecorator(SQLEditor editor)
    {
        super();
        _editor = editor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image, java.lang.Object)
     */
    public Image decorateImage(Image image, Object element)
    {
        if (!(element instanceof Node))
        {
            return image;
        }
        int[] adornmentFlags = computeAdornmentFlags((Node) element);
        ImageDescriptor baseImage = new ImageImageDescriptor(image);
        for (int i = 0; i < adornmentFlags.length; i++)
        {
            if (adornmentFlags[i] != 0)
            {
                Rectangle bounds = image.getBounds();
                baseImage = new SQLImageDescriptor(baseImage, adornmentFlags[i], new Point(bounds.width, bounds.height));
            }
        }
        return getRegistry().get(baseImage);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String, java.lang.Object)
     */
    public String decorateText(String text, Object element)
    {
        return text;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void addListener(ILabelProviderListener listener)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose()
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    public boolean isLabelProperty(Object element, String property)
    {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void removeListener(ILabelProviderListener listener)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
     *      org.eclipse.jface.viewers.IDecoration)
     */
    public void decorate(Object element, IDecoration decoration)
    {
        // TODO Auto-generated method stub

    }

    private int[] computeAdornmentFlags(Node node)
    {
        int info[] = new int[] 
        {
            0, 0
        }
        ;
        try
        {
            IAnnotationModel model = _editor.getDocumentProvider().getAnnotationModel(_editor.getEditorInput());
            IDocument document = node.getDocument();

            if (model != null)
            {
                info[0] = getErrorTicksFromNode(node, model, document);
                info[1] = getPortableFlagFromNode(node, model, document);
            }
        }
        catch (Exception e)
        {
        	SQLEditorPlugin.getDefault().log("SQLMarkerLabelDecorator.failed.to.compute.adornment.flags", e);
        }

        return info;
    }

    private int getPortableFlagFromNode(Node node, IAnnotationModel model, IDocument document) throws CoreException
    {
        int info = 0;
        if (model != null)
        {
            Iterator iter = model.getAnnotationIterator();
            while ((info != SQLImageDescriptor.PORTABLE) && iter.hasNext())
            {
                Annotation curr = (Annotation) iter.next();
                IMarker marker = isAnnotationInRange(model, curr, node, document, IMarker.TASK);
                if (marker != null)
                {
                    info = SQLImageDescriptor.PORTABLE;
                }
            }
        }
        return info;
    }

    private int getErrorTicksFromNode(Node node, IAnnotationModel model, IDocument document) throws CoreException
    {
        int info = 0;
        if (model != null)
        {
            Iterator iter = model.getAnnotationIterator();
            while ((info != SQLImageDescriptor.ERROR) && iter.hasNext())
            {
                Annotation curr = (Annotation) iter.next();
                IMarker marker = isAnnotationInRange(model, curr, node, document, IMarker.PROBLEM);
                if (marker != null)
                {
                    int priority = marker.getAttribute(IMarker.SEVERITY, -1);
                    if (priority == IMarker.SEVERITY_WARNING)
                    {
                        info = SQLImageDescriptor.WARNING;
                    }
                    else if (priority == IMarker.SEVERITY_ERROR)
                    {
                        info = SQLImageDescriptor.ERROR;
                    }
                }
            }
        }
        return info;
    }

    private IMarker isAnnotationInRange(IAnnotationModel model, Annotation annot, Node node, IDocument document,
        String markerType) throws CoreException
    {
        if (annot instanceof MarkerAnnotation)
        {
            IMarker marker = ((MarkerAnnotation) annot).getMarker();
            if (marker.exists() && marker.isSubtypeOf(markerType))
            {
                Position pos = model.getPosition(annot);
                if (pos != null && (node == null || isInside(pos.getOffset(), node, document)))
                {
                    return marker;
                }
            }
        }
        return null;
    }

    /**
     * Tests if a position is inside the source range of an element.
     * 
     * @param pos Position to be tested.
     * @param node
     * @return boolean Return <code>true</code> if position is located inside the source element.
     * @throws CoreException Exception thrown if element range could not be accessed.
     * 
     * @since 2.1
     */
    protected boolean isInside(int pos, Node node, IDocument document) throws CoreException
    {
        return (node.getStartOffset() <= pos && node.getGreatestEndOffset() >= pos);
    }

    private ImageDescriptorRegistry getRegistry()
    {
        if (_fRegistry == null)
        {
            _fRegistry = new ImageDescriptorRegistry();
        }
        return _fRegistry;
    }

}
