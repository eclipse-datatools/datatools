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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.datatools.modelbase.sql.tables.Column;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.commands.CreateJoinCommand;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.commands.MoveJoinCommand;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.figures.FlatEndConnectionRouter;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.figures.TableConnection;

public class ColumnEditPolicy extends org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy

{

    public final static Display display = Display.getDefault();
    private static Font tooltipFont = display.getSystemFont();

    protected Figure fTargetFeedback;
    protected Figure fSourceDataType;
    protected Figure fTargetDataType;

    protected QuerySelect selectStatement;

    public ColumnEditPolicy(QuerySelect selectStatement) {
        this.selectStatement = selectStatement;
    }

    protected Connection createDummyConnection(Request req) {
        Connection conn = new TableConnection();
        conn.setConnectionRouter(new FlatEndConnectionRouter());

        GraphicalEditPart hostEditPart = (GraphicalEditPart) getHost();
        if (hostEditPart instanceof ColumnEditPart) {
            ColumnEditPart columnPart = (ColumnEditPart) hostEditPart;
            String toolTip = columnPart.getToolTip();
            if (!toolTip.equals("")) { //$NON-NLS-1$
                fSourceDataType = createToolTip(columnPart.getLabel().getBounds().getExpanded(0, 0).getBottomLeft().translate(0, 2), toolTip);
            }
        }

        return conn;
    }

    protected void eraseCreationFeedback(CreateConnectionRequest request) {
        if (fSourceDataType != null) {
            removeFeedback(fSourceDataType);
            fSourceDataType = null;
        }
        super.eraseCreationFeedback(request);
    }

    //
    // Erase feedback indicating that the view object is no longer
    // the target of a drag.
    //
    protected void eraseTargetConnectionFeedback(DropRequest request) {
        if (fTargetFeedback != null) {
            removeFeedback(fTargetFeedback);
            fTargetFeedback = null;
        }
        if (fTargetDataType != null) {
            removeFeedback(fTargetDataType);
            fTargetDataType = null;
        }
    }

    protected ColumnEditPart getColumnEditPart() {
        return (ColumnEditPart) getHost();
    }

    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        CreateJoinCommand command;
        command = (CreateJoinCommand) request.getStartCommand();
        if (command == null)
            return null;
        command.setTargetTable(getTableExpr());
        command.setTargetColumn(getColumnExpr());
        return command;
    }

    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        CreateJoinCommand command = new CreateJoinCommand();
        request.setStartCommand(command);
        command.setSelectStatement(selectStatement);
        command.setSourceTable(getTableExpr());
        command.setSourceColumn(getColumnExpr());
        return command;
    }

    //
    // Call by GEF when a connection is moved
    //
    protected Command getReconnectSourceCommand(ReconnectRequest request)

    {
        //SQLBuilderPlugin.getPlugin().getLogger().writeTrace("move start!");

        MoveJoinCommand cmd = new MoveJoinCommand();
        cmd.setJoinPart((JoinEditPart) request.getConnectionEditPart());
        cmd.setSelectStatement(selectStatement);
        cmd.setColumnPart(getColumnEditPart());
        cmd.setChangeSource(true);
        return cmd;
    }

    //
    // Call by GEF when a connection is moved
    //
    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        MoveJoinCommand cmd = new MoveJoinCommand();
        cmd.setJoinPart((JoinEditPart) request.getConnectionEditPart());
        cmd.setColumnPart(getColumnEditPart());
        cmd.setSelectStatement(selectStatement);
        cmd.setChangeSource(false);
        return cmd;
    }

    protected Column getRDBColumn() {
        return (Column) getHost().getModel();
    }

    protected ValueExpressionColumn getColumnExpr() {
        ValueExpressionColumn colExpr = null;
        Object modelObj = getHost().getModel();
        if (modelObj instanceof ValueExpressionColumn) {
            colExpr = (ValueExpressionColumn) modelObj;
        }
        return colExpr;
    }

    protected TableCorrelation getSQLCorrelation() {
        return ((ColumnEditPart) getHost()).getTableEditPart().getTableAlias();
    }

    private TableExpression getTableExpr() {
        TableExpression tblExpr = null;
        Object modelObj = ((ColumnEditPart) getHost()).getTableEditPart().getModel();
        if (modelObj instanceof TableExpression) {
            tblExpr = (TableExpression) modelObj;
        }
        return tblExpr;
    }

    protected void showTargetConnectionFeedback(DropRequest request) {
        if (fTargetFeedback == null) {
            GraphicalEditPart hostEditPart = (GraphicalEditPart) getHost();
            RectangleFigure rectFig = new RectangleFigure();
            rectFig.setLineStyle(Graphics.LINE_DOT);
            rectFig.setFill(false);
            IFigure f = hostEditPart.getFigure();
            rectFig.setBounds(f.getBounds().getExpanded(0, 0));
            addFeedback(rectFig);
            fTargetFeedback = rectFig;

            if (hostEditPart instanceof ColumnEditPart) {
                ColumnEditPart columnPart = (ColumnEditPart) hostEditPart;
                String toolTip = columnPart.getToolTip();
                if (!toolTip.equals("")) { //$NON-NLS-1$
                    fTargetDataType = createToolTip(request.getLocation().translate(0, 15), toolTip);
                }
            }
        }
    }

    private Figure createToolTip(Point location, String toolTipText) {
        Figure toolTipFigure = new Label(toolTipText);
        toolTipFigure.setFont(tooltipFont);
        toolTipFigure.setBorder(new LineBorder());
        toolTipFigure.setSize(toolTipFigure.getPreferredSize().getExpanded(2, 2));

        toolTipFigure.setLocation(location);
        toolTipFigure.setForegroundColor(ColorConstants.tooltipForeground);
        toolTipFigure.setBackgroundColor(ColorConstants.tooltipBackground);
        toolTipFigure.setOpaque(true);
        addFeedback(toolTipFigure);
        return toolTipFigure;
    }

}