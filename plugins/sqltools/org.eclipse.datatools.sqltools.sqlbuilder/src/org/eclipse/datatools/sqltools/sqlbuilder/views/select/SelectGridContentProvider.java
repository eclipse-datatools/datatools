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

package org.eclipse.datatools.sqltools.sqlbuilder.views.select;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;

public class SelectGridContentProvider extends GridContentProvider {

    SQLDomainModel domainModel;

    public SelectGridContentProvider(SQLDomainModel domainModel) {
        super(domainModel.getAdapterFactory());
        this.domainModel = domainModel;
    }

    public Object[] getElements(java.lang.Object property) {
    	
    	// This is done is super class AdapterFactoryContentProvider.getElements(), but
    	// since this method does not call the superclass method, it must be done here
    	// as well.  Without this call, notifications will not be enabled for the SQL object
        adapterFactory.adapt(property, IStructuredItemContentProvider.class);
        
        if (property instanceof QuerySelectStatement || property instanceof QuerySelect) {
            tableElements = new Vector();
            QuerySelect qSelect = null;
            if (property instanceof QuerySelectStatement) {
                qSelect = SelectHelper.getQuerySelect((QuerySelectStatement) property);
            }
            else {
                qSelect = (QuerySelect) property;
            }

            if (qSelect != null) {
                List resultColList = qSelect.getSelectClause();
                if (resultColList != null) {
                    tableElements = new Vector();
                    Iterator resultColListIter = resultColList.iterator();
                    while (resultColListIter.hasNext()) {
                        QueryResultSpecification resultSpec = (QueryResultSpecification) resultColListIter.next();
                        if (resultSpec instanceof ResultColumn) {
                            ResultColumn resultCol = (ResultColumn) resultSpec;
                            SelectTableElement tblElement = new SelectTableElement(domainModel, property, resultCol);
                            tableElements.add(tblElement);
                        }
                    }
                }

                if (property instanceof QuerySelectStatement) {
                    List orderByColList = ((QuerySelectStatement) property).getOrderByClause();
                    if (orderByColList != null && orderByColList.size() > 0) {
                        Iterator iterator = orderByColList.iterator();
                        while (iterator.hasNext()) {
                            Object ordCol = iterator.next();
                            if (ordCol instanceof OrderByValueExpression) {
                                OrderByValueExpression ordValExpr = (OrderByValueExpression) ordCol;
                                SelectTableElement tblElement = new SelectTableElement(domainModel, property, ordValExpr);
                                tableElements.add(tblElement);
                            }
                        }
                    }
                }

                // Now add in the blank row
                tableElements.add(new SelectTableElement(domainModel, property, (ResultColumn) null));
            }
            return tableElements.toArray();
        }
        return null;
    }
}