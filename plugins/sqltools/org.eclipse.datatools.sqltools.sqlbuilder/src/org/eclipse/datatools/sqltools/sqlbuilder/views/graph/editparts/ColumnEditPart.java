/*******************************************************************************
 * Copyright © 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ButtonModel;
import org.eclipse.draw2d.ChangeEvent;
import org.eclipse.draw2d.ChangeListener;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.FocusEvent;
import org.eclipse.draw2d.FocusListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Toggle;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.tables.Column;

import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionNested;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.InsertHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.UpdateHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.figures.EdgeChopBoxConnector;

/**
 * GEF viewobject for a column
 */

public class ColumnEditPart extends AbstractGraphicalEditPart implements NodeEditPart, ISQLEditPart {

    SQLQueryObject sqlStatement;
    ValueExpressionColumn sqlColumnExpr;
    ValueExpressionColumn currentColumn;
    TableEditPart tableEditPart;
    Hashtable matchingSQLJoins = new Hashtable();

    protected Toggle checkBox;
    protected Label keyIcon; // This is the icon for the primary or foreign key

    final static protected Image unCheckedImage = SQLBuilderPlugin.getSQLImage("icons/checkboxoff.gif"); //$NON-NLS-1$
    final static protected Image checkedImage = SQLBuilderPlugin.getSQLImage("icons/checkboxon.gif"); //$NON-NLS-1$
    protected Label unCheckedLabel = new Label(unCheckedImage);
    protected Label checkedLabel = new Label(checkedImage);
    protected Label label;
    private SQLDomainModel domainModel;

    /**
     * Constructor takes a RDB Column object as input.
     * It is created by TableEditPart
     */
    public ColumnEditPart(Object model, TableEditPart tableEditPart, SQLDomainModel domainModel) {
        if (model instanceof ValueExpressionColumn) {
            currentColumn = (ValueExpressionColumn) model;
        }
        setModel(model);
        this.tableEditPart = tableEditPart;
        this.domainModel = domainModel;
    }

    public TableEditPart getTableEditPart() {
        return tableEditPart;
    }

    // This is our own check box so we can control what the checkbox looks like
    public class MyCheckBox extends Toggle {

        public MyCheckBox(QueryStatement stmt) {
            super(unCheckedLabel);
            if (stmt instanceof QueryDeleteStatement) {
                setEnabled(false);
            }
            addChangeListener(new ChangeListener() {

                public void handleStateChanged(ChangeEvent e) {
                    if (e.getPropertyName() == ButtonModel.SELECTED_PROPERTY) {
                        forwardSetContents(isSelected() ? checkedLabel : unCheckedLabel);
                    }
                }
            });
        }
        
        /**
         * Forwards a setContent call from the change listener.  This is needed
         * because setContents is protected.
         */
        public void forwardSetContents(IFigure contents) {
            setContents( contents );
        }

    }

    public String getToolTip() {
        DataType type = null; //= ((Column)getModel()).getDataType() ;
        Object model = getModel();
        if (model instanceof ValueExpressionColumn) {
            ValueExpressionColumn colExpr = (ValueExpressionColumn) model;
            type = colExpr.getDataType();
        }
        if (type != null) {
            return type.getName();
        }
        return ""; //$NON-NLS-1$
    }

    public class ColumnFigure extends Figure {

        Color bColor = null;
        Color fColor = null;

        public ColumnFigure() {
            super();
            bColor = getBackgroundColor();
            fColor = getForegroundColor();
        }

        public void setSelectedColors() {
            setBackgroundColor(ColorConstants.menuBackgroundSelected);
            setForegroundColor(ColorConstants.menuForegroundSelected);
        }

        public void setDeselectedColors() {
            setBackgroundColor(bColor);
            setForegroundColor(fColor);
        }
    }

    protected IFigure createFigure() {
        ColumnFigure colFigure = new ColumnFigure();

        colFigure.setLayoutManager(new FlowLayout());
        colFigure.setOpaque(true);

        colFigure.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent arg0) {
                if (arg0.gainer != null) {
                    ((ColumnFigure) arg0.gainer).setSelectedColors();
                }
            }

            public void focusLost(FocusEvent arg0) {
                if (arg0.loser != null) {
                    ((ColumnFigure) arg0.loser).setDeselectedColors();
                }
            }
        });
        checkBox = new MyCheckBox(domainModel.getSQLStatement());
        checkBox.setOpaque(true);
        checkBox.setBackgroundColor(ColorConstants.white);
        checkBox.setForegroundColor(ColorConstants.black);
        checkBox.addChangeListener(new CheckBoxListener());
        checkBox.setFocusTraversable(false);
        checkBox.setRequestFocusEnabled(false);

        keyIcon = new Label(""); //$NON-NLS-1$
        colFigure.add(checkBox);

        label = new Label(""); //$NON-NLS-1$
        colFigure.add(label);

        if (TableHelper.isPrimaryKey(currentColumn)) {
            keyIcon.setIcon(SQLBuilderPlugin.getSQLImage("icons/PrimaryKey.gif")); // default to the primary key //$NON-NLS-1$
            colFigure.add(keyIcon);
        }
        else if (TableHelper.isForeignKey(currentColumn)) {
            keyIcon.setIcon(SQLBuilderPlugin.getSQLImage("icons/ForeignKey.gif")); // default to the primary key //$NON-NLS-1$
            colFigure.add(keyIcon);
        }

        return colFigure;
    }

    public void setModel(Object model) {
        super.setModel(model);
    }

    /**
     * ColumnNodeInputPolicy handles connection.
     */
    protected void createEditPolicies() {
        SQLQueryObject statement = (SQLQueryObject) getMyRoot().getModel();

        if (statement instanceof QuerySelectStatement || statement instanceof QuerySelect) {
            // only allow drag editing for selects
            if (statement instanceof QuerySelectStatement) {
                statement = SelectHelper.getQuerySelect((QuerySelectStatement) statement);
            }
            if (statement != null) {
                ColumnEditPolicy connIp = new ColumnEditPolicy((QuerySelect) statement);
                installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, connIp);

                ColumnSelectionEditPolicy colEditPol = new ColumnSelectionEditPolicy();
                installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, colEditPol);
            }
        }
        // No need to create self Policy because we don't allow user to drag the column figures
    }

    /**
     * <code>update</code> should be called with the parent and child object involved in the change
     *
     * @param parent an <code>Object</code> value
     * @param child an <code>Object</code> value
     */
    public void update(Object child) {
        List childList = getSourceConnections();
        JoinEditPart editPart;

        for (int i = 0; i < childList.size(); i++) {
            editPart = (JoinEditPart) childList.get(i);
            editPart.refreshVisuals();
        }
        childList = getTargetConnections();

        for (int i = 0; i < childList.size(); i++) {
            editPart = (JoinEditPart) childList.get(i);
            editPart.refreshVisuals();
        }

        if (child instanceof ValueExpressionColumn) {
            ValueExpressionColumn valueExpr = (ValueExpressionColumn) child;

            if (getModel() == valueExpr) {
                refreshVisuals();
                return;
            }
        }

        else if (child == null) {
            // just refresh to be on the safe side
            refreshVisuals();
        }
        refresh();
    }

    protected void selectColumn() {
        // deselect other selected columns
        Iterator colIterator = getTableEditPart().getChildren().iterator();
        while (colIterator.hasNext()) {
            ColumnEditPart colEditPart = (ColumnEditPart) colIterator.next();
            getViewer().deselect(colEditPart);
        }
        // select current column
        getViewer().select(this);
    }

    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connVO) {
        return new EdgeChopBoxConnector(getFigure(), ((AbstractGraphicalEditPart) getParent()).getFigure());
    }

    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new EdgeChopBoxConnector(getFigure(), ((AbstractGraphicalEditPart) getParent()).getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connVO) {
        return new EdgeChopBoxConnector(getFigure(), ((AbstractGraphicalEditPart) getParent()).getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new EdgeChopBoxConnector(getFigure(), ((AbstractGraphicalEditPart) getParent()).getFigure());
    }

    /**
     * Set the column name and check box status
     */
    protected void refreshVisuals() {
        getLabel().setText(currentColumn.getName());
        //    getLabel().setFont(columnFont);
        getCheckBox().setSelected(isColumnSelected());
        // BZ223133 drigby 6 Jan 2009
        // Flag up the "SELECT *" by grouping  the checkbox backgrounds to black
        // Get the current statement or query select expression
		SQLQueryObject sqlStatement = getStatement();

		// Get the query select expression that contains the select clause list.
		QuerySelect querySelect = null;
		if (sqlStatement instanceof QuerySelect) {
		    querySelect = (QuerySelect) sqlStatement;
		}
		else if (sqlStatement instanceof QuerySelectStatement) {
		    QuerySelectStatement selectStmt = (QuerySelectStatement) sqlStatement;
		    querySelect = SelectHelper.getQuerySelect(selectStmt);
		}
		
		// If the query select is "SELECT *", then change the column checkbox background color.
		if (querySelect != null) {
		    if (SelectHelper.isSelectStarQuery(querySelect)) {
				getCheckBox().setBackgroundColor(ColorConstants.black);
			} else {
				getCheckBox().setBackgroundColor(ColorConstants.white);
			}
		}
    }

    protected Toggle getCheckBox() {
        return checkBox;
    }

    public DragTracker getDragTracker(Request request) {
        SQLQueryObject statement = (SQLQueryObject) getMyRoot().getModel();
        if (statement instanceof QuerySelectStatement || statement instanceof QuerySelect) {
            // only allow dragging for select statements
            ConnectionCreationDragTracker tracker = new ConnectionCreationDragTracker(this);
            return tracker;
        }
        return super.getDragTracker(request);
    }

    public Label getLabel() {
        return label;
    }

    public SQLQueryObject getStatement() {
        return (SQLQueryObject) getMyRoot().getModel();
    }

    public EditPart getMyRoot() {
        List childList = getRoot().getChildren();
        Iterator iterator = childList.iterator();
        while (iterator.hasNext()) {
            Object child = iterator.next();
            if (child instanceof SQLRootEditPart) {
                return (EditPart) child;
            }
        }
        return null;
    }

    /**
     * Determine if the column is included in the statement
     */
    private boolean isColumnSelected() {
        boolean status = false;
        sqlStatement = (SQLQueryObject) getMyRoot().getModel();

        if (sqlStatement instanceof QuerySelectStatement || sqlStatement instanceof QuerySelect) {
            if (currentColumn != null) {
                if (sqlStatement instanceof QuerySelectStatement) {
                    status = SelectHelper.isResultColumn(sqlStatement, currentColumn);
                }
                else if (sqlStatement instanceof QuerySelect) {
                    status = SelectHelper.isResultColumn((QuerySelect) sqlStatement, currentColumn);
                }
            }
            else {
                status = false;
            }
        }
        else if (sqlStatement instanceof QueryInsertStatement) {
            QueryInsertStatement insertStmt = (QueryInsertStatement) sqlStatement;
            List columnList = insertStmt.getTargetColumnList();
            if (currentColumn != null) {
                status = isColumnInColumnList(currentColumn, columnList);
            }
        }
        else if (sqlStatement instanceof QueryUpdateStatement) {
            QueryUpdateStatement updateStmt = (QueryUpdateStatement) sqlStatement;
            List columnList = UpdateHelper.getTargetColumns(updateStmt);
            if (currentColumn != null) {
                status = isColumnInColumnList(currentColumn, columnList);
            }
        }

        return status;
    }

    boolean isColumnInColumnList(ValueExpressionColumn column, List colList) {
        boolean contains = false;
        if (colList != null) {
            Iterator columnItr = colList.iterator();
            String colName = column.getName();
            String currentName;
            while (!(contains) && columnItr.hasNext()) {
                currentName = ((ValueExpressionColumn) columnItr.next()).getName();
                if (colName.equals(currentName)) {
                    contains = true;
                }
            }

        }
        return contains;
    }

    /**
     * Called by GEF to create a connection betw 2 columns.
     *  - GEF called this when getModelSourceConnections() or
     *    getModelTargetConnections return a non-empty vector
     *  - Each connection maps to a connection object (a JoinEditPart)
     *  - Each JoinEditPart has to be backed by a unique
     *    MOF object
     */
    protected ConnectionEditPart createConnection(Object model) {
        ConnectionEditPart part = (ConnectionEditPart) getRoot().getViewer().getEditPartRegistry().get(model);
        if (part == null) {
            part = new JoinEditPart((PredicateBasic) model, (SQLQueryObject)matchingSQLJoins.get(model));
        }
        return part;
    }

    /**
     * Return the EMF objects that made up the target connection
     * See documentation for getModelTargetConnections()
     */
    protected List getModelSourceConnections() {
        List sources = new ArrayList();
        List allJoin;
        allJoin = tableEditPart.getLeftJoinTables();
        allJoin.addAll(tableEditPart.getRightJoinTables());

/*        if (allJoin.isEmpty()) {
            return sources;
        }
*/        
        Iterator iterator = allJoin.iterator();
        while (iterator.hasNext()) {
            TableJoined join = (TableJoined) iterator.next();
            QuerySearchCondition searchCond = join.getJoinCondition();
            List predicateList = findLeftColumnPredicates(searchCond);

            // Join predicates may be "backwards", that is, for a join
            // where the table is the left side of the join, the predicate
            // may have the column reference on the right side of the predicate.
            // So check for "right" predicates as well.  
            // Note: For now we will assume that a column can be referenced on 
            // the left or right side of a join condition, but not on both sides 
            // in the same condition.
            // This is not needed. Retained as commented till testing is over
            //if (predicateList.size() == 0) {
           //     predicateList = findRightColumnPredicates(searchCond);
           // }

            Iterator iter = predicateList.iterator();
            while (iter.hasNext()) {
                PredicateBasic predicate = (PredicateBasic) iter.next();
                sources.add(predicate);
                matchingSQLJoins.put(predicate, join);
            }
        }
        
        // look up the WHERE clause to see if this column is part of any condition
        QuerySearchCondition whereCondition = null;
        if(sqlStatement instanceof QuerySelectStatement){
        	QuerySelect qSelect = SelectHelper.getQuerySelect((QuerySelectStatement)sqlStatement);
        	if(qSelect != null){
        		whereCondition = qSelect.getWhereClause();
        	}
        }
        else if(sqlStatement instanceof QuerySelect ){
        	whereCondition = ((QuerySelect)sqlStatement).getWhereClause();
        }
       	if(whereCondition != null){
    		List whereClausePredicates = findLeftColumnPredicates(whereCondition);
    		Iterator itr = whereClausePredicates.iterator();
    		while(itr.hasNext()){
                PredicateBasic predicate = (PredicateBasic) itr.next();
                sources.add(predicate);
                matchingSQLJoins.put(predicate, whereCondition);
    		}
    	}
        return sources;
    }

    //Returns a list of predicates in the given search condition, in which the current column (representd by this 
    //instance of ColumnEditPart) is on the left side of the predicate

    private List findLeftColumnPredicates(QuerySearchCondition cond) {
        List result = new ArrayList();
        if (cond instanceof PredicateBasic) {
            PredicateBasic sqlPredicate = (PredicateBasic) cond;

            if(sqlPredicate.getRightValueExpr() instanceof ValueExpressionColumn &&
            		sqlPredicate.getLeftValueExpr() instanceof ValueExpressionColumn){
            	ValueExpressionColumn colExpr = (ValueExpressionColumn) sqlPredicate.getLeftValueExpr();
                if ((colExpr.getName().trim().equalsIgnoreCase(currentColumn.getName().trim()))) {
                    TableExpression currentTable = currentColumn.getParentTableExpr();
                    TableExpression predTable = colExpr.getTableExpr();
                    if (currentTable != null && currentTable.getName() != null && 
                        predTable != null && predTable.getName() != null) {
                        if ((currentTable.getName().trim().equalsIgnoreCase(predTable.getName().trim())))
                            result.add(sqlPredicate);
                    }
                }
            }
        }
        else if (cond instanceof SearchConditionCombined) {
            SearchConditionCombined grp = (SearchConditionCombined) cond;
            List nestedResult = findLeftColumnPredicates(grp.getLeftCondition());
            if (nestedResult != null) {
                result.addAll(nestedResult);
            }
            nestedResult = findLeftColumnPredicates(grp.getRightCondition());
            if (nestedResult != null) {
                result.addAll(nestedResult);
            }
        }
        else if (cond instanceof SearchConditionNested) {
            QuerySearchCondition nestedCondition = ((SearchConditionNested) cond).getNestedCondition();
            List nestedResults = findLeftColumnPredicates(nestedCondition);
            if (nestedResults != null) {
                result.addAll(nestedResults);
            }
        }

        return result;
    }

    /**
     * Return the MOF objects that made up the target connection
     *
     * Example:
     *   Given:
     *     - Table1, with c1, c2, c3 and Table2 with x1, x2, x3
     *     - make 2 connections betw c1<->x1, and c2<->x2
     *
     * The following shows the call sequences for getModelTargetConnections() and
     * getModelSourceConnections() and what object should be returned at each call
     * so that GEF will draw the 2 connections.
     *   - (T) stands for getModelTargetConnections()
     *   - (S) stands for getModelSourceConnections()
     *
     * T: c1       c1<->x1     *
     * S: c1
     * T: c2       c2<->x2     **
     * S: c2
     * T: c3
     * S: c3
     *
     * T: x1
     * S: x1       c1<->x1     *
     * T: x2
     * S: x2       c2<->x2     **
     * T: x3
     * S: x3
     *
     * The key is that * must return the same object, and ** must also return the same object
     * This object is the SQLPredicate in our case, but in general a unique MOF object for the
     * ConnectionEditPart is required.
     */
    protected List getModelTargetConnections() {
        List targets = new ArrayList();
        List allJoin;
        allJoin = tableEditPart.getRightJoinTables();
        allJoin.addAll(tableEditPart.getLeftJoinTables());
/*        if (allJoin.isEmpty()) {
            return targets;
        }
*/
        Iterator iterator = allJoin.iterator();
        while (iterator.hasNext()) {
            TableJoined join = (TableJoined) iterator.next();
            QuerySearchCondition searchCond = join.getJoinCondition();
            List predicates = findRightColumnPredicates(searchCond);

            // Join predicates may be "backwards", that is, for a join
            // where the table is the right side of the join, the predicate
            // may have the column reference on the left side of the predicate.
            // So check for "left" predicates as well.
            // Note: For now we will assume that a column can be referenced on 
            // the left or right side of a join condition, but not on both sides 
            // in the same condition.
            
            // This is not needed. Retained as commented till testing is over
            //if (predicates.size() == 0) {
            //    predicates = findLeftColumnPredicates(searchCond);
            //}

            Iterator iter = predicates.iterator();
            while (iter.hasNext()) {
                PredicateBasic predicate = (PredicateBasic) iter.next();
                if (predicate != null) {
                    targets.add(predicate);
                    matchingSQLJoins.put(predicate, join);
                }
            }
        }
        
        // look up the WHERE clause to see if this column is part of any condition
        QuerySearchCondition whereCondition = null;
        if(sqlStatement instanceof QuerySelectStatement){
        	QuerySelect qSelect = SelectHelper.getQuerySelect((QuerySelectStatement)sqlStatement);
        	if(qSelect != null){
        		whereCondition = qSelect.getWhereClause();
        	}
        }
        else if(sqlStatement instanceof QuerySelect ){
        	whereCondition = ((QuerySelect)sqlStatement).getWhereClause();
        }

    	if(whereCondition != null){
    		List whereClausePredicates = findRightColumnPredicates(whereCondition);
    		Iterator itr = whereClausePredicates.iterator();
    		while(itr.hasNext()){
                PredicateBasic predicate = (PredicateBasic) itr.next();
                targets.add(predicate);
                matchingSQLJoins.put(predicate, whereCondition);
    		}
    	}
        return targets;
    }

    // Returns a list of predicates in the given search condition, in which the current column (representd by this 
    // instance of ColumnEditPart) is on the right side of the predicate, and both sides of the predicate are 
    // instances of ValueExpressionColumn
    private List findRightColumnPredicates(QuerySearchCondition cond) {
        List result = new ArrayList();
        if (cond instanceof PredicateBasic) {
            PredicateBasic sqlPredicate = (PredicateBasic) cond;
            
            if(sqlPredicate.getRightValueExpr() instanceof ValueExpressionColumn &&
            		sqlPredicate.getLeftValueExpr() instanceof ValueExpressionColumn){
            	ValueExpressionColumn colExpr = (ValueExpressionColumn) sqlPredicate.getRightValueExpr();
                //if (colExpr.getSQL().trim().equalsIgnoreCase(currentColumn.getSQL().trim()))
                if ((colExpr.getName().trim().equalsIgnoreCase(currentColumn.getName().trim()))) {
                    TableExpression currentTable = currentColumn.getParentTableExpr();
                    TableExpression predTable = colExpr.getTableExpr();
                    if (currentTable != null && currentTable.getName() != null && 
                        predTable != null && predTable.getName() != null) {
                        if ((currentTable.getName().trim().equalsIgnoreCase(predTable.getName().trim()))) {
                            result.add(sqlPredicate);
                        }
                    }
                }
            }
        }
        else if (cond instanceof SearchConditionCombined) {
            SearchConditionCombined grp = (SearchConditionCombined) cond;
            List nestedResult = findRightColumnPredicates(grp.getLeftCondition());
            if (nestedResult != null) {
                result.addAll(nestedResult);
            }
            nestedResult = findRightColumnPredicates(grp.getRightCondition());
            if (nestedResult != null) {
                result.addAll(nestedResult);
            }
        }
        else if (cond instanceof SearchConditionNested) {
            QuerySearchCondition nestedCondition = ((SearchConditionNested) cond).getNestedCondition();
            List nestedResults = findRightColumnPredicates(nestedCondition);
            if (nestedResults != null) {
                result.addAll(nestedResults);
            }
        }
        return result;
    }

    /**
     * Call back when the check box for the column is selected
     */
    public void checkBoxAction(boolean status) {
        if (status) // include the column
        {
            if (sqlStatement instanceof QuerySelectStatement || sqlStatement instanceof QuerySelect) {
                sqlColumnExpr = ExpressionHelper.createValueExpressionColumn(currentColumn);
                SelectHelper.appendResultColumn(sqlStatement, sqlColumnExpr, ""); //$NON-NLS-1$
                SelectHelper.refresh(sqlStatement);
            }
            else if (sqlStatement instanceof QueryInsertStatement) {
                EditingDomain editDomain = domainModel.getEditingDomain();
                QueryInsertStatement insStatement = (QueryInsertStatement) sqlStatement;
                Column col = TableHelper.getColumnForColumnExpression(insStatement.getTargetTable(), currentColumn);
                InsertHelper.addDefaultInsertValue(editDomain, insStatement, col);

            }
            else if (sqlStatement instanceof QueryUpdateStatement) {
                QueryUpdateStatement updateStmt = (QueryUpdateStatement) sqlStatement;
                Column col = TableHelper.getColumnForColumnExpression(updateStmt.getTargetTable(), currentColumn);
                UpdateHelper.addColumn(updateStmt, col);
            }
        }
        else // remove the column
        {
            if (sqlStatement instanceof QuerySelectStatement || sqlStatement instanceof QuerySelect) {
                sqlColumnExpr = ExpressionHelper.createValueExpressionColumn(currentColumn);
                if (sqlStatement instanceof QuerySelectStatement) {
                    SelectHelper.removeAllColumnFromOrderBy((QuerySelectStatement) sqlStatement, sqlColumnExpr);
                }
                SelectHelper.removeAllColumnFromResultColumns(sqlStatement, sqlColumnExpr);
                SelectHelper.refresh(sqlStatement);
            }
            else if (sqlStatement instanceof QueryInsertStatement) {
                InsertHelper.removeColumn((QueryInsertStatement) sqlStatement, currentColumn);
            }
            else if (sqlStatement instanceof QueryUpdateStatement) {

                UpdateHelper.removeColumn((QueryUpdateStatement) sqlStatement, currentColumn);
            }
        }
    }

    protected class CheckBoxListener implements ChangeListener {

        //
        // Call the ColumnEditPart to update the model based on selection
        //
        public void handleStateChanged(ChangeEvent event) {
            MyCheckBox checkbox = (MyCheckBox) event.getSource();

            if (event.getPropertyName().equals(ButtonModel.PRESSED_PROPERTY) && checkbox.getModel().isPressed() == false) {
                checkBoxAction(checkbox.isSelected());
            }
        }
    }
}