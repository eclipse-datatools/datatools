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
package org.eclipse.datatools.sqltools.sqlbuilder.views.insert;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;

import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;

public class InsertGridContentProvider extends GridContentProvider {

    SQLDomainModel domainModel;

    public InsertGridContentProvider(SQLDomainModel domainModel) {
        super(domainModel.getAdapterFactory());
        this.domainModel = domainModel;
    }

    public Object[] getElements(java.lang.Object property) {
    	
    	// This is done is super class AdapterFactoryContentProvider.getElements(), but
    	// since this method does not call the superclass method, it must be done here
    	// as well.  Without this call, notifications will not be enabled for the SQL object
    	adapterFactory.adapt(property, IStructuredItemContentProvider.class);
    	
        Object[] valuesArray = null;
        if (property instanceof QueryInsertStatement) {
            List tableElementsList = new ArrayList();
            QueryInsertStatement insertStmt = (QueryInsertStatement) property;
            List columnList = insertStmt.getTargetColumnList();
            List valuesRowList = insertStmt.getSourceValuesRowList();
            for (int i = 0; i < columnList.size(); i++) {
                if (valuesRowList != null && !valuesRowList.isEmpty()) {
                    ValueExpressionColumn column = (ValueExpressionColumn) columnList.get(i);

                    ValuesRow row = (ValuesRow) valuesRowList.get(0); //we deal with only single row insert for now
                    List exprList = row.getExprList();
                    int size = exprList.size();
                    QueryValueExpression expr = null;
                    if (columnList.size() == size) {
                        expr = (QueryValueExpression) exprList.get(i);
                    }

                    InsertTableElement tableElement = new InsertTableElement(domainModel, insertStmt, column, expr);
                    tableElementsList.add(tableElement);
                }
            }
            //add a blank row 
            tableElementsList.add(new InsertTableElement(domainModel, insertStmt, null, null));
            valuesArray = tableElementsList.toArray();
        }
        return valuesArray;
    }

}
