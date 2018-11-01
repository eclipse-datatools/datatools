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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLResource;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

public class ExpressionsByOperatorsWizard extends Wizard implements INewWizard {

    protected ExpressionsByOperatorsPage expressionsByOperatorsPage;

    private SQLQueryObject QueryStatement;
    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;
    private SQLDomainModel domainModel;

    public ExpressionsByOperatorsWizard(SQLDomainModel domainModel, SQLQueryObject QueryStatementArg, QueryValueExpression sqlExpr) {
        QueryStatement = QueryStatementArg;
        inputSQLExpression = sqlExpr;
        this.domainModel = domainModel;
        setWindowTitle(Messages._UI_WIZARD_EXPRESSION_BY_OPERATOR_TITLE);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(SQLBuilderPlugin.getPlugin().getClass(), SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));
    }

    public Wizard createSmartGuide() {
        return this;
    }

    public void addPages() {
        expressionsByOperatorsPage = new ExpressionsByOperatorsPage(domainModel, QueryStatement, inputSQLExpression);
        addPage(expressionsByOperatorsPage);

        getContainer().updateWindowTitle();
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    public void setSQLExpression(QueryValueExpression sqlExpr) {
        updatedSQLExpression = sqlExpr;
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public boolean performFinish() {
        if (expressionsByOperatorsPage.currentPage()) {
            expressionsByOperatorsPage.performOk();
        }
        return true;
    }
}