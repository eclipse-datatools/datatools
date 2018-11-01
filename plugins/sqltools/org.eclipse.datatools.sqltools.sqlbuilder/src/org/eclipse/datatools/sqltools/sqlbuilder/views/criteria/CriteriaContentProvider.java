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
package org.eclipse.datatools.sqltools.sqlbuilder.views.criteria;

import java.lang.ref.WeakReference;
import java.util.Vector;

import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;

import org.eclipse.datatools.modelbase.sql.query.Predicate;
import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;

public class CriteriaContentProvider extends GridContentProvider {

    QuerySearchCondition searchCon;
    boolean isHaving = false;
    SQLDomainModel domainModel;
    protected boolean firstClause;

    public CriteriaContentProvider(SQLDomainModel domainModel, boolean isHavingClause) {
        super(domainModel.getAdapterFactory());
        this.domainModel = domainModel;
        this.isHaving = isHavingClause;
    }

    /**
     * Get all the predicates from the where clause and map each of
     * into a CriteriaElement
     */
    public Object[] getElements(Object property) {

       	// This is done is super class AdapterFactoryContentProvider.getElements(), but
    	// since this method does not call the superclass method, it must be done here
    	// as well.  Without this call, notifications will not be enabled for the SQL object
    	adapterFactory.adapt(property, IStructuredItemContentProvider.class);
    	
        SQLQueryObject queryStmt = null;
        tableElements = new Vector();
        searchCon = null;
        if (property instanceof QueryStatement || property instanceof QuerySelect) {
            queryStmt = (SQLQueryObject) property;
            if ((!isHaving)) {
                searchCon = StatementHelper.getSearchCondition(queryStmt);
            }
            else {
                searchCon = StatementHelper.getHavingCondition(queryStmt);
            }
            firstClause = true;
            if (searchCon != null) {
                getAllPredicates(property, searchCon);
                if (searchCon == null) {
                    createNewCriteriaElement(property, null);
                }
            }
            else {
                createNewCriteriaElement(property, null);
            }
        }

        return tableElements.toArray();
    }

    //
    // Create a CriteriaElement for each predicate
    //
    private void getAllPredicates(java.lang.Object property, QuerySearchCondition cond) {
        if (cond instanceof Predicate) {
            createNewCriteriaElement(property, (Predicate) cond);
            firstClause = false;
        }
        else if (cond instanceof SearchConditionCombined) {
            QuerySearchCondition left = ((SearchConditionCombined) cond).getLeftCondition();
            getAllPredicates(property, left);

            QuerySearchCondition right = ((SearchConditionCombined) cond).getRightCondition();
            getAllPredicates(property, right);
        }
    }

    //
    // Make a CriteriaElement and add to the vector
    //
    void createNewCriteriaElement(java.lang.Object property, Predicate predicate) {
        CriteriaElement criElement = new CriteriaElement(domainModel, property, searchCon, predicate, isHaving, firstClause);
        // creating the weak reference so that criteria element can find other criteria elements
        WeakReference tableElementRef = new WeakReference(tableElements);
        criElement.setCriteriaElementVectorRef(tableElementRef);
        tableElements.add(criElement);
    }
}
