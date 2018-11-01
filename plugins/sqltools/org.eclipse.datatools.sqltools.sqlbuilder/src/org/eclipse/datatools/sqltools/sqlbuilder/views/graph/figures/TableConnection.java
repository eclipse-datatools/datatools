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

import org.eclipse.draw2d.AnchorListener;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;

public class TableConnection extends PolylineConnection implements PositionConstants, Connection, AnchorListener {

    private ConnectionRouter connectionRouter = new FlatEndConnectionRouter();

    protected ConnectionAnchor sourceAnchor, targetAnchor;
    protected Label joinTypeIcon;

    protected PolygonDecoration startShape, endShape;

    protected static final PointList FUNNEL_SHAPE = new PointList();
    protected static final Image innerJoin, outerJoin, leftOuterJoin, rightOuterJoin;
    static {
        FUNNEL_SHAPE.addPoint(0, 1);
        FUNNEL_SHAPE.addPoint(0, -1);
        FUNNEL_SHAPE.addPoint(-1, 0);

        //      FUNNEL_SHAPE.addPoint(-3, 4);
        //      FUNNEL_SHAPE.addPoint(0, 4);
        //      FUNNEL_SHAPE.addPoint(0, -3);
        //      FUNNEL_SHAPE.addPoint(-3, -3);
        //      FUNNEL_SHAPE.addPoint(-6, 0);

        innerJoin = SQLBuilderPlugin.getSQLImage("icons/innerjoin.gif"); //$NON-NLS-1$
        outerJoin = SQLBuilderPlugin.getSQLImage("icons/outerjoin.gif"); //$NON-NLS-1$
        leftOuterJoin = SQLBuilderPlugin.getSQLImage("icons/outerjoin_left.gif"); //$NON-NLS-1$
        rightOuterJoin = SQLBuilderPlugin.getSQLImage("icons/outerjoin_right.gif"); //$NON-NLS-1$
    }

    public TableConnection() {
        setOutline(true);
        joinTypeIcon = new Label(""); //$NON-NLS-1$
        joinTypeIcon.setLabelAlignment(LEFT);
        joinTypeIcon.setTextPlacement(SOUTH_EAST);
        setJoinType(TableJoinedOperator.DEFAULT_INNER);

        startShape = new PolygonDecoration();
        startShape.setBackgroundColor(ColorConstants.buttonDarker);
        startShape.setTemplate(FUNNEL_SHAPE);
        startShape.setScale(6, 3);

        endShape = new PolygonDecoration();
        endShape.setBackgroundColor(ColorConstants.buttonDarker);
        endShape.setTemplate(FUNNEL_SHAPE);
        endShape.setScale(6, 3);

        add(startShape);
        add(endShape);
        add(joinTypeIcon);
    }

    public void setJoinType(int joinType) {
        switch (joinType) {
            case TableJoinedOperator.DEFAULT_INNER:
                joinTypeIcon.setIcon(innerJoin);
                break;
            case TableJoinedOperator.LEFT_OUTER:
                joinTypeIcon.setIcon(leftOuterJoin);
                break;
            case TableJoinedOperator.RIGHT_OUTER:
                joinTypeIcon.setIcon(rightOuterJoin);
                break;
            case TableJoinedOperator.FULL_OUTER:
                joinTypeIcon.setIcon(outerJoin);
                break;
        } // end of switch ()
    }

 
    public ConnectionRouter getConnectionRouter() {
        // This is needed since getConnectionRouter() is called from the 
        // the constructor of the parent PolylineConnection - before the
        // local connectionRouter variable is set.

        if (connectionRouter != null) {
            return connectionRouter;
        }
        
        return super.getConnectionRouter();
    }

    public ConnectionAnchor getSourceAnchor() {
        return sourceAnchor;
    }

    public ConnectionAnchor getTargetAnchor() {
        return targetAnchor;
    }

    /**
     * Called just before the receiver is being removed from its parent
     */
    public void removeNotify() {
        getConnectionRouter().remove(this);
        super.removeNotify();
    }

    public void setConnectionRouter(ConnectionRouter cr) {
        if (cr == null)
            connectionRouter = new FlatEndConnectionRouter();
        else
            connectionRouter = cr;
    }

    public void setTargetAnchor(ConnectionAnchor anchor) {
        if (targetAnchor != null)
            targetAnchor.removeAnchorListener(this);
        if (anchor != null)
            anchor.addAnchorListener(this);
        targetAnchor = anchor;
        revalidate();
    }

    public void setSourceAnchor(ConnectionAnchor anchor) {
        if (sourceAnchor != null)
            sourceAnchor.removeAnchorListener(this);
        if (anchor != null)
            anchor.addAnchorListener(this);
        sourceAnchor = anchor;
        revalidate();
    }

    public void anchorMoved(ConnectionAnchor anchor) {
        revalidate();
    }

    public PointList getPoints() {
        return super.getPoints();
    }

    public PointList getThickPoints() {
        PointList thickpoints = new PointList();
        PointList points = getPoints();
        int numPoints = points.size();
        for (int i = 0; i < numPoints; i++) {
            Point currentPoint = points.getPoint(i);
            currentPoint.y -= 1;
            thickpoints.addPoint(currentPoint);
        } // end of for ()
        for (int i = numPoints - 1; i >= 0; i--) {
            Point currentPoint = points.getPoint(i);
            currentPoint.y += 1;
            thickpoints.addPoint(currentPoint);
        } // end of for ()
        return thickpoints;
    }

    public void setPoints(PointList newPoints) {
        int numPoints = newPoints.size();
        for (int i = 0; i < numPoints; i++) {
            Point currentPoint = newPoints.getPoint(i);
            addPoint(currentPoint);
        } 
    }

    protected void fillShape(Graphics g) {
        g.fillPolygon(getThickPoints());
    }

    protected void outlineShape(Graphics g) {
        g.setLineWidth(3);
        super.outlineShape(g);
        setLineWidth(1);
        setForegroundColor(getBackgroundColor());
        super.outlineShape(g);
    }

    public Rectangle getBounds() {
        if (bounds == null) {
            super.getBounds();
            if (joinTypeIcon != null)
                bounds.union(joinTypeIcon.getBounds());
            if (startShape != null)
                bounds.union(startShape.getBounds());
            if (endShape != null)
                bounds.union(endShape.getBounds());
        }
        return bounds;
    }

    public void layout() {
        if (getSourceAnchor() != null && getTargetAnchor() != null) {
            getConnectionRouter().route(this);
        }
        super.layout();
        bounds = null;
        fireMoved();
    }

    public void validate() {
        if (isValid())
            return;

        erase();
        // If there is no layout manager for the connection,
        // then ask the anchors for the start and end points.
        if (getLayoutManager() == null && getSourceAnchor() != null && getTargetAnchor() != null) {
            Point center = getTargetAnchor().getReferencePoint();
            setStart(getSourceAnchor().getLocation(center));
            center = getSourceAnchor().getReferencePoint();
            setEnd(getTargetAnchor().getLocation(center));
        }
        super.validate();
        if (getSourceAnchor().getOwner() != null && getTargetAnchor().getOwner() != null) {
            validateJoinIcon();
            validateStartShape();
            validateEndShape();
            repaint();
        }
    }

    protected void validateStartShape() {
        if (startShape == null)
            return;
        startShape.setLocation(getStart());
        startShape.setReferencePoint(super.getPoints().getPoint(1));
        bounds = null;
    }

    protected void validateEndShape() {
        if (endShape == null)
            return;
        // ugly hack to fix off by 1 bug
        Point endPoint = getEnd();
        endPoint.x += 1;
        endShape.setLocation(endPoint);
        endShape.setReferencePoint(super.getPoints().getPoint(2));
        bounds = null;
    }

    protected void validateJoinIcon() {
        PointList points = super.getPoints();

        Point leftPoint = points.getPoint(1);
        Point rightPoint = points.getPoint(2);

        int minX = Math.min(leftPoint.x, rightPoint.x);
        int maxX = Math.max(leftPoint.x, rightPoint.x);

        int minY = Math.min(leftPoint.y, rightPoint.y);
        int maxY = Math.max(leftPoint.y, rightPoint.y);

        org.eclipse.swt.graphics.Rectangle imageRect = joinTypeIcon.getIcon().getBounds();

        int x = minX + (maxX - minX) / 2 // add half the distance between the max and minimum x coordinates
                - (imageRect.width / 2); // subtract half the image width to center the image correctly

        int y = minY + (maxY - minY) / 2 // add half the distance between the max and minimum y coordinates
                - (imageRect.height / 2); // subtract half the image height to center the image correctly

        Point newPoint = new Point(x, y);
        joinTypeIcon.setLocation(newPoint);
        joinTypeIcon.setBounds(joinTypeIcon.getIconBounds());
        bounds = null;
    }

    public Object getRoutingConstraint() {
        return null;
    }

    public void setRoutingConstraint(Object cons) {

    }

}
