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

import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;


/**
 * @author Hui Cao
 *  
 */
public class SQLImageDescriptor extends CompositeImageDescriptor
{
    /** Flag to render the waring adornment */
    public final static int WARNING  = 0x001;

    /** Flag to render the error adornment */
    public final static int ERROR    = 0x002;

    /** Flag to render the portability task adornment */
    public final static int PORTABLE = 0x004;

    private ImageDescriptor _fBaseImage;
    private int             _fFlags;
    private Point           _fSize;

    /**
     * Creates a new SQLImageDescriptor.
     * 
     * @param baseImage an image descriptor used as the base image
     * @param flags flags indicating which adornments are to be rendered. See <code>setAdornments</code> for valid
     *            values.
     * @param size the size of the resulting image
     * @see #setAdornments(int)
     */
    public SQLImageDescriptor(ImageDescriptor baseImage, int flags, Point size)
    {
        _fBaseImage = baseImage;
        Assert.isNotNull(_fBaseImage);
        _fFlags = flags;
        Assert.isTrue(_fFlags >= 0);
        _fSize = size;
        Assert.isNotNull(_fSize);
    }

    /**
     * Sets the descriptors adornments. Valid values are:</code> WARNING <code>,</code> ERROR <code>,
     * <code>PORTABLE</code>, or any combination of those.
     * 
     * @param adornments the image descritpors adornments
     */
    public void setAdornments(int adornments)
    {
        Assert.isTrue(adornments >= 0);
        _fFlags = adornments;
    }

    /**
     * Returns the current adornments.
     * 
     * @return the current adornments
     */
    public int getAdronments()
    {
        return _fFlags;
    }

    /**
     * Sets the size of the image created by calling <code>createImage()</code>.
     * 
     * @param size the size of the image returned from calling <code>createImage()</code>
     * @see ImageDescriptor#createImage()
     */
    public void setImageSize(Point size)
    {
        Assert.isNotNull(size);
        Assert.isTrue(size.x >= 0 && size.y >= 0);
        _fSize = size;
    }

    /**
     * Returns the size of the image created by calling <code>createImage()</code>.
     * 
     * @return the size of the image created by calling <code>createImage()</code>
     * @see ImageDescriptor#createImage()
     */
    public Point getImageSize()
    {
        return new Point(_fSize.x, _fSize.y);
    }

    /*
     * (non-Javadoc) Method declared in CompositeImageDescriptor
     */
    protected Point getSize()
    {
        return _fSize;
    }

    /*
     * (non-Javadoc) Method declared on Object.
     */
    public boolean equals(Object object)
    {
        if (object == null || !SQLImageDescriptor.class.equals(object.getClass()))
        {
            return false;
        }

        SQLImageDescriptor other = (SQLImageDescriptor) object;
        return (_fBaseImage.equals(other._fBaseImage) && _fFlags == other._fFlags && _fSize.equals(other._fSize));
    }

    /*
     * (non-Javadoc) Method declared on Object.
     */
    public int hashCode()
    {
        return _fBaseImage.hashCode() | _fFlags | _fSize.hashCode();
    }

    /*
     * (non-Javadoc) Method declared in CompositeImageDescriptor
     */
    protected void drawCompositeImage(int width, int height)
    {
        ImageData bg = getImageData(_fBaseImage);

        drawImage(bg, 0, 0);

        drawTopRight();
        drawBottomRight();
        drawBottomLeft();

    }

    private ImageData getImageData(ImageDescriptor descriptor)
    {
        ImageData data = descriptor.getImageData(); // see bug 51965: getImageData can return null
        if (data == null)
        {
            data = DEFAULT_IMAGE_DATA;
            SQLEditorPlugin.getDefault().log(("Image data not available: " + descriptor.toString())); //$NON-NLS-1$
        }
        return data;
    }

    private void drawTopRight()
    {
        //No top right decorators now
    }

    private void drawBottomRight()
    {
        Point size = getSize();
        int x = size.x;
        int flags = _fFlags;

        if ((flags & PORTABLE) != 0)
        {
        	ImageData data = getImageData(ImageDescriptor.createFromImage(SQLEditorResources.getImage("over16/portable_co")));
            x -= data.width;
            drawImage(data, x, size.y - data.height);
        }
    }

    private void drawBottomLeft()
    {
        Point size = getSize();
        int x = 0;
        if ((_fFlags & ERROR) != 0)
        {
        	ImageData data = getImageData(ImageDescriptor.createFromImage(SQLEditorResources.getImage("over16/error_co")));
            drawImage(data, x, size.y - data.height);
            x += data.width;
        }
        if ((_fFlags & WARNING) != 0)
        {
        	ImageData data = getImageData(ImageDescriptor.createFromImage(SQLEditorResources.getImage("over16/warning_co")));
            drawImage(data, x, size.y - data.height);
            x += data.width;
        }

    }

}
