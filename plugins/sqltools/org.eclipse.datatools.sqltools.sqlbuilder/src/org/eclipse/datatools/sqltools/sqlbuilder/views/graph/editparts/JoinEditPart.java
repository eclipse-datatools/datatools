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

import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.JoinHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SearchConditionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.figures.FlatEndConnectionRouter;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.figures.TableConnection;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

/**
 * This is the view object for the connection line betw 2 columns
 * The condition can be from a Join or a WHERE clause
 * It is backed by the MOF SQLPredicate object
 */
public class JoinEditPart extends AbstractConnectionEditPart implements ISQLEditPart {

	SQLQueryObject joinSource; // this can be a TableJoined or query SearchCondition 
    PredicateBasic sqlPredicate;

    public JoinEditPart(PredicateBasic predicate, SQLQueryObject sqlJoinsource) {
        setModel(predicate);

        this.sqlPredicate = predicate;
        joinSource = sqlJoinsource;
    }

    public PredicateBasic getSQLPredicate() {
        return sqlPredicate;
    }

    public TableExpression getSourceTable() {
        TableExpression tableExpr = null;
        QueryValueExpression expr = sqlPredicate.getLeftValueExpr();
        if (expr instanceof ValueExpressionColumn) {
            tableExpr = ExpressionHelper.getTableExprForValueExpressionColumn((ValueExpressionColumn) expr);
        }
        return tableExpr;
    }

    public TableExpression getTargetTable() {
        TableExpression tableExpr = null;
        QueryValueExpression expr = sqlPredicate.getRightValueExpr();
        if (expr instanceof ValueExpressionColumn) {
            tableExpr = ExpressionHelper.getTableExprForValueExpressionColumn((ValueExpressionColumn) expr);
        }
        return tableExpr;
    }

    public ValueExpressionColumn getSourceColumn() {
        ValueExpressionColumn col = null;
        QueryValueExpression expr = sqlPredicate.getLeftValueExpr();
        if (expr instanceof ValueExpressionColumn) {
            col = (ValueExpressionColumn) expr;
        }
        return col;
    }

    public ValueExpressionColumn getTargetColumn() {
        ValueExpressionColumn col = null;
        QueryValueExpression expr = sqlPredicate.getRightValueExpr();
        if (expr instanceof ValueExpressionColumn) {
            col = (ValueExpressionColumn) expr;
        }
        return col;
    }

    /**
     * Returns a newly created Figure to represent these type of
     * EditParts.
     *
     * @return  The created Figure.
     */
    protected IFigure createFigure() {
        return new TableConnection();
    }

    protected void refreshVisuals() {
        TableConnection connection = (TableConnection) getFigure();
        if(joinSource instanceof TableJoined){
        	connection.setJoinType( ((TableJoined)joinSource).getJoinOperator().getValue());
        }
        else {
        	connection.setJoinType(TableJoinedOperator.DEFAULT_INNER);
        }
    }

    static final Font tooltipFont = Display.getCurrent().getSystemFont();

    protected void createEditPolicies() {
        installEditPolicy("reconnect", new ConnectionEndpointEditPolicy() { //$NON-NLS-1$

            protected IFigure fTargetFeedback;

            protected Connection createDummyConnection(org.eclipse.gef.requests.ReconnectRequest request) {
                Connection conn = new TableConnection();
                conn.setConnectionRouter(new FlatEndConnectionRouter());

                ConnectionEditPart connEditPart = request.getConnectionEditPart();
                EditPart stationaryPart = null;
                if (request.getType() == RequestConstants.REQ_CONNECTION_START) // string
                {
                    stationaryPart = connEditPart.getTarget();
                }
                else if (request.getType() == RequestConstants.REQ_CONNECTION_END) {
                    stationaryPart = connEditPart.getSource();
                }

                if (stationaryPart instanceof ColumnEditPart) {
                    ColumnEditPart columnPart = (ColumnEditPart) stationaryPart;
                    String toolTip = columnPart.getToolTip();

                    if (!toolTip.equals("")) { //$NON-NLS-1$
                        fTargetFeedback = createToolTip(columnPart.getLabel().getBounds().getExpanded(0, 0).getBottomLeft().translate(0, 2), toolTip);
                    }
                }

                return conn;
            }

            //
            // Erase feedback indicating that the view object is no longer
            // the target of a drag.
            //
            protected void eraseConnectionMoveFeedback(org.eclipse.gef.requests.ReconnectRequest request) {
                if (fTargetFeedback != null) {
                    removeFeedback(fTargetFeedback);
                    fTargetFeedback = null;
                }
                super.eraseConnectionMoveFeedback(request);
            }

            private Figure createToolTip(Point location, String toolTipText) {
                Figure toolTipFigure = new org.eclipse.draw2d.Label(toolTipText);
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

        });
    }

    public SQLQueryObject getSQLJoin() {
    	return joinSource;
    }

    public SQLQueryObject getStatement() {
        return (SQLQueryObject) getMyRoot().getModel();
    }

    public EditPart getMyRoot() {
        EditPart root = getRoot();
        Iterator iter = root.getChildren().iterator();
        EditPart part = null;
        while (iter.hasNext() && !(part instanceof SQLRootEditPart)) {
            part = (EditPart) iter.next();
        } // end of while ()
        return part;
    }

    /**
     * Removes the join condition (predicate) represented by this
     * part.  If the join has only this one predicate, then the
     * entire join is removed.
     */
    public void removeJoin() {
        SQLQueryObject obj = getStatement();
        QuerySelect querySelect = null;
        List fromClauseList = null;

        if(obj instanceof QuerySelectStatement){
	        QuerySelectStatement selectStmt = (QuerySelectStatement) obj;
	        querySelect = SelectHelper.getQuerySelect(selectStmt);
	        fromClauseList = querySelect.getFromClause();
        }
        else if (obj instanceof QuerySelect ){
        	querySelect = (QuerySelect)obj;
   	        fromClauseList = ((QuerySelect)obj).getFromClause();
        }
        
        if( joinSource instanceof TableJoined){
	        	JoinHelper.removeJoinCondition(fromClauseList, (TableJoined)joinSource, sqlPredicate);
	    }
	    else if( joinSource instanceof QuerySearchCondition){
	    	QuerySearchCondition newCondition = SearchConditionHelper.removePredicateFromCondition(sqlPredicate, (QuerySearchCondition)joinSource);
	    	if(querySelect != null){
	    		querySelect.setWhereClause(newCondition);
	    	}
	    	
	    }
	    SelectHelper.refresh(obj);
    }
}
