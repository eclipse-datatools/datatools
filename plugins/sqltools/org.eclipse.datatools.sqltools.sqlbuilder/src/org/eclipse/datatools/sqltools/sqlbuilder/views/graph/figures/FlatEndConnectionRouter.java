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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

//
// Adapted from org.eclipse.gef.examples.query.figures
//
public class FlatEndConnectionRouter implements ConnectionRouter {

    final static private int INTERSECT = 0;
    final static private int LEFT = 1;
    final static private int RIGHT = 2;

    public int END_LENGTH = 20;

    public void setConstraint(IFigure figure, Object constraint) {
    }

    public void setConstraint(Connection connection, Object constraint) {

    }

    public Object getConstraint(IFigure figure) {
        return null;
    }

    public Object getConstraint(Connection connection) {
        return null;
    }

    protected Rectangle getChopRectangle(ConnectionAnchor anchor) {
        IFigure containerFig = getContainerFigure(anchor);
        return containerFig.getBounds().getCropped(containerFig.getInsets());
    }

    protected IFigure getContainerFigure(ConnectionAnchor anchor) {
        if (getOwnerFigure(anchor) != null) {
            if (getOwnerFigure(anchor).getParent() == null) {
                return getOwnerFigure(anchor);
            }

            return getOwnerFigure(anchor).getParent().getParent().getParent();
        }
        return null;
    }

    protected IFigure getOwnerFigure(ConnectionAnchor anchor) {
        return anchor.getOwner();
    }

    public void remove(Connection connection) {

    }

    public void route(Connection connection) {
        TableConnection conn = (TableConnection) connection;

        Figure startFigure = (Figure) getContainerFigure(conn.getSourceAnchor());
        Figure endFigure = (Figure) getContainerFigure(conn.getTargetAnchor());

        if (startFigure != null && endFigure != null) {
            Rectangle startContainerRect = startFigure.getBounds();
            Rectangle endContainerRect = endFigure.getBounds();

            Rectangle startOwnerRect = getOwnerFigure(conn.getSourceAnchor()).getBounds();
            Rectangle endOwnerRect = getOwnerFigure(conn.getTargetAnchor()).getBounds();

            Rectangle startChopRect = getChopRectangle(conn.getSourceAnchor());
            Rectangle endChopRect = getChopRectangle(conn.getTargetAnchor());

            int startY = startOwnerRect.y + (startOwnerRect.height / 2);
            startY = Math.min(Math.max(startY, startChopRect.y), startChopRect.y + startChopRect.height);

            int endY = endOwnerRect.y + (endOwnerRect.height / 2);
            endY = Math.min(Math.max(endY, endChopRect.y), endChopRect.y + endChopRect.height);

            conn.removeAllPoints();
            int position = getRelativeXPosition(startContainerRect, endContainerRect);

            if (position == LEFT) {
                // The start figure is to the left of the end figure.
                conn.addPoint(new Point(startContainerRect.x + startContainerRect.width, startY));
                conn.addPoint(new Point(startContainerRect.x + startContainerRect.width + END_LENGTH, startY));
                conn.addPoint(new Point(endContainerRect.x - END_LENGTH, endY));
                conn.addPoint(new Point(endContainerRect.x - 1, endY));
            }
            else if (position == RIGHT) {
                // The start figure is to the right of the end figure
                conn.addPoint(new Point(startContainerRect.x - 1, startY));
                conn.addPoint(new Point(startContainerRect.x - END_LENGTH, startY));
                conn.addPoint(new Point(endContainerRect.x + endContainerRect.width + END_LENGTH, endY));
                conn.addPoint(new Point(endContainerRect.x + endContainerRect.width, endY));
            }
            else {
                // If the connected figures are on top of each other, then
                // connect on the left of both figures.
                int xpos = Math.min(startContainerRect.x, endContainerRect.x) - END_LENGTH;
                conn.addPoint(new Point(startContainerRect.x, startY));
                conn.addPoint(new Point(xpos, startY));
                conn.addPoint(new Point(xpos, endY));
                conn.addPoint(new Point(endContainerRect.x, endY));
            }
        }
    }

    public Dimension getMinimumSize(IFigure parent) {
        return new Dimension(0, 0);
    }

    public Dimension getPreferredSize(IFigure parent) {
        return getMinimumSize(parent);
    }

    public void invalidate(Connection connection) {
    }

    private int getRelativeXPosition(Rectangle r1, Rectangle r2) {
        if (r2.x + r2.width < r1.x)
            return RIGHT;
        if (r1.x + r1.width < r2.x)
            return LEFT;
        return INTERSECT;
    }

    public Point getStartPoint(Connection conn) {
        Rectangle rec = conn.getTargetAnchor().getOwner().getBounds();
        return conn.getSourceAnchor().getLocation(rec.getCenter());
    }

    public Point getEndPoint(Connection conn) {
        Rectangle rec = conn.getSourceAnchor().getOwner().getBounds();
        return conn.getTargetAnchor().getLocation(rec.getCenter());
    }
}
