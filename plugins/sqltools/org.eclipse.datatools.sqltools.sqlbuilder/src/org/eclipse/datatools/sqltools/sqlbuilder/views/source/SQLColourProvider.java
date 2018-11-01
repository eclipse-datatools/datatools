/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class SQLColourProvider {

    public final static RGB DEFAULT = new RGB(0, 0, 0); // Black
    public final static RGB KEYWORD = new RGB(127, 0, 85); // Light blue for now.
    public final static RGB COMMENT = new RGB(63, 95, 191); // Red
    public static final RGB STRING = new RGB(0, 128, 0); // Green
    public final static RGB ENTITY = new RGB(0, 128, 0); // Green

    private HashMap fColourTable = new HashMap(10);

    /**
     */
    public void dispose() {

        Iterator e = fColourTable.values().iterator();
        while (e.hasNext())
            ((Color) e.next()).dispose();
    }

    /**
     */
    public void finalize() {
        // assume that the object will be destroyed after the editor exits
        dispose();
    }

    /**
     * 
     * @param rgb
     *            org.eclipse.swt.graphics.RGB
     * @return org.eclipse.swt.graphics.Color
     */
    public Color getColor(RGB rgb) {

        Color color = (Color) fColourTable.get(rgb);
        if (color == null) {
            color = new Color(Display.getCurrent(), rgb);
            fColourTable.put(rgb, color);
        }
        return color;
    }
}