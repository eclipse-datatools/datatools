package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Colors used in the SQL editor
 */
public class ColorProvider
{
    public static final RGB BACKGROUND          = new RGB(255, 255, 255);

    public static final RGB MULTI_LINE_COMMENT  = new RGB(64, 128, 128);

    public static final RGB SINGLE_LINE_COMMENT = new RGB(64, 128, 128);

    public static final RGB DEFAULT             = new RGB(0, 0, 0);

    public static final RGB KEYWORD             = new RGB(127, 0, 85);

    public static final RGB TYPE                = new RGB(64, 0, 200);

    public static final RGB STRING              = new RGB(0, 0, 255);

    public static final RGB SQL_CODE_DEFAULT    = new RGB(63, 95, 191);

    public static final RGB SQL_CODE_KEYWORD    = new RGB(100, 100, 100);

    public static final RGB SQL_CODE_TAG        = new RGB(127, 159, 191);

    protected Map           fColorTable         = new HashMap(10);

    /**
     * Method disposes of the colors.
     */
    public void dispose()
    {
        Iterator e = fColorTable.values().iterator();
        while (e.hasNext())
        ((Color) e.next()).dispose();
    }

    /**
     * A getter method that returns a color.
     * 
     * @param rgb
     * @return Color
     */
    public Color getColor(RGB rgb)
    {
        Color color = (Color) fColorTable.get(rgb);

        if (color == null)
        {
            color = new Color(Display.getCurrent(), rgb);
            fColorTable.put(rgb, color);
        }

        return color;
    }
}
