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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder;

import java.util.Vector;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BuilderUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;

public class ExpressionsComboBoxCellEditor extends EditComboBoxCellEditor {

    public static String ADDFUNCTION = Messages._UI_COMBO_ADDFUNCTION;
    public static String ADDCASE = Messages._UI_COMBO_ADDCASE;
    public static String ADDCAST = Messages._UI_COMBO_ADDCAST;
    public static String ADDCONSTANT = Messages._UI_COMBO_ADDCONSTANT;
    public static String SELECTSUBQUERY = Messages._UI_COMBO_SELECTSUBQUERY;
    public static String ADDEXPRBYOPERATOR = Messages._UI_COMBO_ADDEXPRBYOPERATOR;

    private boolean includeFunction = true;
    private boolean includeCase = true;
    private boolean includeCast = true;
    private boolean includeConstant = true;
    private boolean includeSubQuery = true;
    private boolean includeExprByOperator = true;

    private QueryStatement sqlStatement;
    private LabelValuePair[] expressionsArray;
    private Vector firstItems;

    public ExpressionsComboBoxCellEditor(Composite parent, QueryStatement sqlStmt) {
        super(parent, null, true);
        this.sqlStatement = sqlStmt;
        firstItems = null;
    }

    public ExpressionsComboBoxCellEditor(Composite parent, QueryStatement sqlStmt, Vector firstItems) {
        super(parent, null, true);
        this.sqlStatement = sqlStmt;
        this.firstItems = firstItems;
    }

    public void includeFunction(boolean includeIt) {
        includeFunction = includeIt;
    }

    public void includeCase(boolean includeIt) {
        includeCase = includeIt;
    }

    public void includeCast(boolean includeIt) {
        includeCast = includeIt;
    }

    public void includeConstant(boolean includeIt) {
        includeConstant = includeIt;
    }

    public void includeSubQuery(boolean includeIt) {
        includeSubQuery = includeIt;
    }

    public void includeExprByOperator(boolean includeIt) {
        includeExprByOperator = includeIt;
    }

    protected void doSetValue(Object value) {
        super.doSetValue(value);
    }

    protected LabelValuePair createComboBoxItem(String newValue) {
        return new LabelValuePair(newValue, ExpressionHelper.createExpression(newValue));
    }

    public void fillItems() {
        Vector availableExpressionsComboBoxItemsVector = new Vector();

        Vector availableExpressionsVector = new Vector();

        if (sqlStatement != null) {
            //availableExpressionsVector = BuilderUtility.getColumnVector(sqlStatement);  // add column expressions
            availableExpressionsVector.addAll(BuilderUtility.getColumnVector(sqlStatement));
        }
        else {
            availableExpressionsVector = new Vector();
        }

        if (firstItems != null) {
            availableExpressionsVector.addAll(0, firstItems);
        }

        if (includeFunction) {
            availableExpressionsVector.add(ADDFUNCTION);
        }
        if (includeCase) {
            availableExpressionsVector.add(ADDCASE);
        }
        if (includeCast) {
            availableExpressionsVector.add(ADDCAST);
        }
        if (includeConstant) {
            availableExpressionsVector.add(ADDCONSTANT);
        }
        if (includeSubQuery) {
            availableExpressionsVector.add(SELECTSUBQUERY);
        }
        if (includeExprByOperator) {
            availableExpressionsVector.add(ADDEXPRBYOPERATOR);
        }

        for (int i = 0; i < availableExpressionsVector.size(); i++) {
            if (availableExpressionsVector.elementAt(i) instanceof QueryValueExpression) {
                availableExpressionsComboBoxItemsVector.addElement(new LabelValuePair(availableExpressionsVector.elementAt(i).toString(),
                        availableExpressionsVector.elementAt(i)));
            }
            else if (availableExpressionsVector.elementAt(i) instanceof String) {
                availableExpressionsComboBoxItemsVector.addElement(new LabelValuePair(availableExpressionsVector.elementAt(i).toString(),
                        availableExpressionsVector.elementAt(i)));
            }
        }
        expressionsArray = new LabelValuePair[availableExpressionsComboBoxItemsVector.size()];
        availableExpressionsComboBoxItemsVector.copyInto(expressionsArray);
        createItems(expressionsArray);
    }
}