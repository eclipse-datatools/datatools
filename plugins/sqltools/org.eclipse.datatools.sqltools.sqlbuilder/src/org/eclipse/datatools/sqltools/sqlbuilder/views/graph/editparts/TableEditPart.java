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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LabeledBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Panel;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Font;
import org.eclipse.datatools.modelbase.sql.tables.Table;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.helper.JoinHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

public class TableEditPart extends AbstractGraphicalEditPart implements ISQLEditPart {

    private static Font titleFont = JFaceResources.getTextFont();
    private SQLDomainModel domainModel;

    public TableEditPart(SQLDomainModel model) {
        domainModel = model;
    }

    public void activate() {
        if (isActive())
            return;
        super.activate();
    }

    public DragTracker getDragTracker(Request req) {
        return super.getDragTracker(req);
    }

    public SQLQueryObject getStatement() {
        return (SQLQueryObject) getMyRoot().getModel();
    }

    public EditPart getMyRoot() {
        EditPart part = this.getParent();
        while (!(part instanceof SQLRootEditPart) && part != null) {
            part = getParent();
        }
        return part;
    }

    protected void createEditPolicies() {
        ResizableEditPolicy policy = new ResizableEditPolicy();
        installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, policy);
        installEditPolicy(EditPolicy.LAYOUT_ROLE, policy);
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, policy);
    }

    public IFigure getContentPane() {
        return ((ScrollPane) getFigure()).getContents();
    }

    //
    // Return the ScollPane figure for the table
    //     
    protected IFigure createFigure() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(13, 13));
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(13, 13));
        scrollPane.getVerticalScrollBar().setStepIncrement(17);
        LabeledBorder frameBorder = new org.eclipse.datatools.sqltools.sqlbuilder.views.graph.figures.TableFrameBorder();
        //    frameBorder.setHighContrast(true);
        frameBorder.setFont(titleFont);
        scrollPane.setBorder(frameBorder);

        Panel contents = new Panel();
        //    contents.setBorder(new CompoundBorder(new SimpleLoweredBorder(2), new MarginBorder(new Insets(2,2,0,0))));
        contents.setBorder(new MarginBorder(new Insets(2, 2, 0, 0)));
        contents.setBackgroundColor(ColorConstants.white);

        FlowLayout flowLayout = new FlowLayout(false);
        flowLayout.setMajorSpacing(1);
        flowLayout.setMinorSpacing(0);
        flowLayout.setStretchMinorAxis(true);
        contents.setLayoutManager(flowLayout);
        scrollPane.setContents(contents);
        //    scrollPane.setBorder(new SimpleLoweredBorder(2));

        refreshTitleBar(scrollPane);
        return scrollPane;
        //    return contents;		
    }

    //
    // Called by framework method to get the model children
    // @ return - a Vector of Column objects
    //
    protected List getModelChildren() {
        List columnList = new ArrayList();
        Object model = getModel();
        if (model instanceof TableExpression) {

            TableExpression tableExpr = (TableExpression) model;
            List colList = tableExpr.getColumnList();
            if (colList != null) {
                columnList.addAll(colList);
            }
        }
        return columnList;
    }

    //
    // Create a new child view object for a model object
    //
    protected EditPart createChild(Object model) {
        EditPart part = new ColumnEditPart(model, this, domainModel);
        part.setModel(model);
        return part;
    }

    //
    // Get the Table mof object
    //
    public Table getTable() {
        Object model = getModel();
        Table table = null;
        if (model instanceof Table) {
            table = (Table) model;
        }
        else if (model instanceof TableExpression) {
            TableExpression tableExpr = (TableExpression) model;
            table = TableHelper.getTableForTableExpression(tableExpr);
        }
        return table;
    }

    String getTableAliasName() {
        String name = getTableAlias().getName();
        if (name == null)
            return ""; //$NON-NLS-1$
        return name;
    }

    public TableCorrelation getTableAlias() {
        TableExpression tableExpr = (TableExpression) getModel();
        return tableExpr.getTableCorrelation();
    }

    /**
     * Returns the list of TableExpression objects that this table participates
     * in on the left side.
     * 
     * @returns the list of joins
     */
    List getLeftJoinTables() {
        Object model = getModel();
        List list = new ArrayList();
        if (model instanceof TableExpression) {
            TableExpression tableExpr = (TableExpression) model;
            list = JoinHelper.getLeftJoinsForTable(tableExpr);
        }

        return list;
    }

    /**
     * Returns the list of TableExpression objects that this table participates
     * in on the right side.
     * 
     * @returns the list of joins
     */
    List getRightJoinTables() {
        Object model = getModel();
        List list = new ArrayList();
        if (model instanceof TableExpression) {
            TableExpression tableExpr = (TableExpression) model;
            list = JoinHelper.getRightJoinsForTable(tableExpr);
        }
        return list;
    }

    /**
     * <code>update</code> should be called with the parent and child object involved in the change
     *
     * @param child an <code>Object</code> value
     */
    public void update(Object child) {
        List childList = getChildren();
        ColumnEditPart editPart;

        for (int i = 0; i < childList.size(); i++) {
            editPart = (ColumnEditPart) childList.get(i);
            editPart.update(child);
        }

        if (child == null || child.equals(getModel())) {
            refreshTitleBar();
        }
    }

    private void refreshTitleBar() {
        refreshTitleBar(getFigure());
    }

    //
    // Set the title of the graph table to the table name
    //
    private void refreshTitleBar(IFigure fig) {
        String name = ""; //$NON-NLS-1$
        Object model = getModel();
        if (model instanceof Table) {
            name = ((Table) model).getName();
        }
        else if (model instanceof TableExpression) {
            TableExpression tableExpr = (TableExpression) model;
            name = TableHelper.getExposedTableName(tableExpr);
        }

        if (fig.getBorder() instanceof LabeledBorder) {
            ((LabeledBorder) fig.getBorder()).setLabel(name);
            fig.repaint();
        }
    }

    protected IFigure getLayer(Object layer) {
        return super.getLayer(layer);
    }

}
