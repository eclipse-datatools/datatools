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
package org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Our own Layout algorithm
 */
public class RootViewXYLayout extends XYLayout {

    static final int maxWidth = 120, maxHeight = 100;

    //
    // Layout the children in the RootView
    //
    protected Dimension calculatePreferredSize(IFigure f, int w, int h) {
        int x = 10, y = 10;

        List children = f.getChildren();

        Iterator iterator = children.iterator();
        // Loop through children
        while (iterator.hasNext()) {
            IFigure figure = (IFigure) iterator.next();

            if (!(figure instanceof Label)) {
                Rectangle r = (Rectangle) getConstraint(figure);
                Dimension figPrefSize = figure.getPreferredSize();
                int width = figPrefSize.width + 2;
                int height = figPrefSize.height;
                if (r == null) {
                    // This is the set size of 100x80
                    //        r = new Rectangle(x,y, 100, 80);
                    if (width > maxWidth) {
                        width = maxWidth;
                        height += 14; // add 14 since that is our scrollbar size is 13(since we are going to be adding one due to shrinking the width
                    }

                    if (height > maxHeight) {
                        height = maxHeight;
                        if (width + 14 < maxWidth) {
                            width += 14;
                        }
                        else {
                            width = maxWidth;
                        }
                    }

                    r = new Rectangle(x, y, width, height);
                    setConstraint(figure, r);
                }
                x = x + r.width + 85;
            }
        }
        return super.calculatePreferredSize(f, x, y);
    }
}
