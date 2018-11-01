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
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

public class SQLRootEditPart extends AbstractGraphicalEditPart implements ISQLEditPart {

    protected SQLDomainModel domainModel;

    public SQLRootEditPart(SQLDomainModel domainModel) {
        this.domainModel = domainModel;
    }

    /**
     * Create the RootViewXYLayout
     */
    protected org.eclipse.draw2d.IFigure createFigure() {
        Figure f = new Figure();
        f.setLayoutManager(new RootViewXYLayout());
        //    f.setLayoutManager(new XYLayout());
        f.setBackgroundColor(ColorConstants.white);
        f.setOpaque(true);

        return f;
    }

    protected void createEditPolicies() {
        EditPolicy policy = new RootViewXYLayoutEditPolicy();
        policy.setHost(this);
        installEditPolicy("layout", policy); //$NON-NLS-1$
    }

    protected EditPart createChild(Object model) {
        EditPart editPart = new TableEditPart(domainModel);
        editPart.setModel(model);
        return editPart;
    }

    /**
     * Called by framework method to get the model children
     * @ return - a Vector of Table objects
     */
    public List getModelChildren() {  // [RATLC01122262] bgp 04Aug2006
        Object model = getModel();
        List tableList = null;
        if (model instanceof QueryStatement) {
            QueryStatement stmt = (QueryStatement) model;
            tableList = StatementHelper.getTablesForStatement(stmt);
        }
        else if (model instanceof QuerySelect) {
            QuerySelect qSelect = (QuerySelect) model;
            tableList = StatementHelper.getTableExpressionsInQuerySelect(qSelect);
        }
        // can not return null, return empty list
        if (tableList == null) {
            tableList = new ArrayList();
        }
        return tableList;
    }

    /**
     * <code>update</code> should be called with the parent and child object involved in the change
     *
     * @param parent an <code>Object</code> value
     * @param child an <code>Object</code> value
     */
    public void update(Object parent, Object child) {
    	List modelChildren = getChildren();
        TableEditPart editPart;

        for (int i = 0; i < modelChildren.size(); i++) {
            editPart = (TableEditPart) modelChildren.get(i);
            editPart.update(child);
        }
        if (parent == null || parent instanceof QueryStatement) {
            refresh();
        }
    }

    /*
     * We override this method....AbstractEditPart.reorderChild()....
     * The visual representation of the tables are positioned on the screen according to a number (an
     * index number).  The model which represents the tables and the model to represent the tables 
     * visually are coupled together.  In AbstractEditPart.refreshChildren(), adding a table is handled by
     * adding a TableEditPart to a list.  It simply adds the new table to the end of the list and visually
     * adds it at that same index.  That is 'the problem'.  A problem scenario is when there
     * are multiple tables and a table (except for the last table) is removed.  
     * AbstractEditPart.refreshChildren() handles this by removing this table from the lists.  The list 
     * representing the model will have it's elements shifted to the left, however the visual representations 
     * on screen will not be affected.  So, when adding another table, it will show the visual representation 
     * on what it believes to be the next avaliable spot.  But this spot will be occupied.  Thus we have overlap.
     * 
     * reorders the given child to the specified index.  Also reorder the visual representations.
     */
    
    // RATLC01112779 SQL builder > "Replace table " context menu option  failed 
    // Over riding this method has a do nothing method meeses up the display refresh
    
//TODO investigate if we still need this as mentioned above and uncomment if needed
/*   protected void reorderChild(EditPart editpart, int index) {
          List children = getChildren();
         Vector movingVector = new Vector();
         
         for (int removeIndex = index; removeIndex < children.size(); removeIndex++) {
         removeChildVisual(((EditPart) children.get(removeIndex)));
         
         if (removeIndex != index + 1) {
         movingVector.addElement(children.get(removeIndex));
         }
         }
         
         children.remove(editpart);
         children.add(index, editpart);
         addChildVisual(editpart, index);
         
         for (int addIndex = 0; addIndex < movingVector.size(); addIndex++) {
         addChildVisual(((EditPart) movingVector.elementAt(addIndex)), addIndex + index + 1);	  	
         }
    }*/

    public SQLQueryObject getStatement() {
        SQLQueryObject retStmt = null;
        Object obj = getModel();
        if (obj instanceof QuerySelect) {
            retStmt = (QuerySelect) obj;
        }
        else if (obj instanceof QueryStatement) {
            retStmt = (QueryStatement) obj;
        }
        return retStmt;
    }

    protected IFigure getLayer(Object layer) {
        return super.getLayer(layer);
    }

    protected EditPartViewer getEditPartViewer() {
        return getRoot().getViewer();
    }

    public String toString() {
        return "SQLRootEditPart";//$NON-NLS-1$
    }
}