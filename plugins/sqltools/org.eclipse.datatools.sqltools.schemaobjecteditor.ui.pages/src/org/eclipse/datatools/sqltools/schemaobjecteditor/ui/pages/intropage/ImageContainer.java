/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * A composite containing an image
 * 
 * @author Idull
 */
public class ImageContainer extends Composite
{

    private Image _image;

    public ImageContainer(Composite parent)
    {
        super(parent, SWT.NONE);
        setBackground(ColorConstants.white);

        addPaintListener(new PaintListener()
        {

            public void paintControl(PaintEvent e)
            {
                if (_image != null)
                {
                    e.gc.drawImage(_image, 0, 0);
                }
            }
        });

        addControlListener(new ControlListener()
        {

            public void controlMoved(ControlEvent e)
            {

            }

            public void controlResized(ControlEvent e)
            {
                if (_image != null)
                {
                    setSize(_image.getBounds().width, _image.getBounds().height);
                }
            }
        });

        addDisposeListener(new DisposeListener()
        {

            public void widgetDisposed(DisposeEvent e)
            {
                if (_image != null && !_image.isDisposed())
                {
                    _image.dispose();
                }
            }
        });
    }

    public void setImage(Image image)
    {
        _image = image;
        if (_image != null)
        {
            setSize(_image.getBounds().width, _image.getBounds().height);
        }
    }
}
