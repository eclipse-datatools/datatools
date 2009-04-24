/**
 * Created on 2006-6-10
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.sqlstatementarea;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.text.source.ISharedTextColors;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Manages SWT color objects. Until the <code>dispose</code> method is called, 
 * the same color object is returned for equal <code>RGB</code> values.
 * <p>
 * @author Shi-feng Yu
 */
public class SharedTextColors implements ISharedTextColors
{

    /** The display table. */
    private Map _displayTable;

    /**
     * Create an instance of shared color manager.
     * <p>
     * Note: The caller who creates the instance has the resposibility to invoke
     * <code>dispose()</code> to release the Color resources.
     */
    public SharedTextColors()
    {
        super();
    }

    /*
     * @see ISharedTextColors#getColor(RGB)
     */
    public Color getColor(RGB rgb)
    {
        if (rgb == null)
        {
            return null;
        }
        if (_displayTable == null)
        {
            _displayTable = new HashMap(2);
        }
        Display display = Display.getCurrent();
        Map colorTable = (Map) _displayTable.get(display);
        if (colorTable == null)
        {
            colorTable = new HashMap(10);
            _displayTable.put(display, colorTable);
        }
        Color color = (Color) colorTable.get(rgb);
        if (color == null)
        {
            color = new Color(display, rgb);
            colorTable.put(rgb, color);
        }
        return color;
    }

    /*
     * @see ISharedTextColors#dispose()
     */
    public void dispose()
    {
        if (_displayTable != null)
        {
            Iterator j = _displayTable.values().iterator();
            while (j.hasNext())
            {
                Iterator i = ((Map) j.next()).values().iterator();
                while (i.hasNext())
                {
                    ((Color) i.next()).dispose();
                }
            }
        }
    }
}
