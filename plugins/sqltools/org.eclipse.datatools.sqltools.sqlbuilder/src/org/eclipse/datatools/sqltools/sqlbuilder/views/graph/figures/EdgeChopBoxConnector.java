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
package org.eclipse.datatools.sqltools.sqlbuilder.views.graph.figures;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class EdgeChopBoxConnector extends AbstractConnectionAnchor {

    private IFigure fChopFigure;

    public EdgeChopBoxConnector(IFigure owner, IFigure chopFigure) {
        super(owner);
        fChopFigure = chopFigure;
    }

    public Point getLocation(Point reference) {
        Rectangle chopR = fChopFigure.getBounds().getCropped(fChopFigure.getInsets());
        Rectangle startFigureR = getOwner().getBounds();

        Point p;

        // The y point is always the midpoint of the startFigure
        // chopped to the bounds of y bounds of the start chop figure.
        int y = startFigureR.y + (startFigureR.height / 2);
        y = Math.min(Math.max(y, chopR.y), chopR.y + chopR.height);

        if (startFigureR.x > reference.x) {
            // The start figure is to the right of the reference
            // point so connect to the left
            p = new Point(chopR.x, y);
        }
        else if (startFigureR.x + startFigureR.width < reference.x) {
            // The start figure is to the left of the reference  
            // point so connect to the right
            p = new Point(chopR.x + chopR.width, y);
        }
        else {
            // If the figures are ontop of one another, always
            // connect on the left.
            p = new Point(chopR.x, y);
        }
        return p;
    }
}
