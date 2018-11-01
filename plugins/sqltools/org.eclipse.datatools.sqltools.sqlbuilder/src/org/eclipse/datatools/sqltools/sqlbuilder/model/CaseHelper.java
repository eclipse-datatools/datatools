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
package org.eclipse.datatools.sqltools.sqlbuilder.model;

import org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelFactoryImpl;

//DOCME
public class CaseHelper {

    ValueExpressionCaseSimple caseSimple;
    ValueExpressionCaseSearch caseSearch;
    ValueExpressionCaseElse elseClause;

    SQLQueryModelFactory factory;

    public CaseHelper() {
        factory = SQLQueryModelFactoryImpl.eINSTANCE;
    }

    public void addSearchWhenClause(QuerySearchCondition search, QueryValueExpression result) {
        if (caseSearch == null) {
            createCaseSearchObject();
        }

        ValueExpressionCaseSearchContent content = factory.createValueExpressionCaseSearchContent();
        content.setSearchCondition(search);
        content.setValueExpr(result);
        caseSearch.getSearchContentList().add(content);
    }

    public void addSearchWhenClause(QuerySearchCondition search, QueryValueExpression result, int index) {
        if (caseSearch == null) {
            createCaseSearchObject();
        }
        ValueExpressionCaseSearchContent content = factory.createValueExpressionCaseSearchContent();
        content.setSearchCondition(search);
        content.setValueExpr(result);
        caseSearch.getSearchContentList().add(index, content);
    }

    public void addSimpleWhenClause(QueryValueExpression when, QueryValueExpression result) {
        if (caseSimple == null) {
            createCaseSimpleObject();
        }

        ValueExpressionCaseSimpleContent content = factory.createValueExpressionCaseSimpleContent();
        content.setWhenValueExpr(when);
        content.setResultValueExpr(result);

        caseSimple.getContentList().add(content);
    }

    public void addSimpleWhenClause(QueryValueExpression when, QueryValueExpression result, int index) {
        if (caseSimple == null) {
            createCaseSimpleObject();
        }

        ValueExpressionCaseSimpleContent content = factory.createValueExpressionCaseSimpleContent();
        content.setWhenValueExpr(when);
        content.setResultValueExpr(result);

        caseSimple.getContentList().add(index, content);
    }

    public void addElseClause(QueryValueExpression elseExpr) {
        elseClause = factory.createValueExpressionCaseElse();
        elseClause.setValueExpr(elseExpr);

        if (caseSimple != null) {
            caseSimple.setCaseElse(elseClause);
        }
        else {
            caseSearch.setCaseElse(elseClause);
        }
    }

    public ValueExpressionCase getSQLCaseExpression() {
        if (caseSimple != null) {
            return caseSimple;
        }

        return caseSearch;
    }

    private void createCaseSearchObject() {
        caseSearch = factory.createValueExpressionCaseSearch();
    }

    private void createCaseSimpleObject() {
        caseSimple = factory.createValueExpressionCaseSimple();
    }

    public void setCaseSearchObject(ValueExpressionCaseSearch search) {
        caseSearch = search;
    }

    public void setCaseSimpleObject(ValueExpressionCaseSimple simple) {
        caseSimple = simple;
    }
}