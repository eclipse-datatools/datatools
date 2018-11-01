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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.function;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLResource;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

public class FunctionExpressionWizard extends Wizard implements INewWizard {

    protected FunctionBuilderPage functionBuilderPage;

    private SQLDomainModel domainModel;
    private SQLQueryObject sqlStatement;
    private ValueExpressionFunction inputSQLExpression;
    private ValueExpressionFunction updatedSQLExpression;

    public FunctionExpressionWizard(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg, ValueExpressionFunction sqlExpr) {
        this.domainModel = domainModel;
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
        setWindowTitle(Messages._UI_WIZARD_FUNCTION_EXPRESSION_TITLE);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(SQLBuilderPlugin.getPlugin().getClass(), SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));
    }

    public Wizard createSmartGuide() {
        return this;
    }

    public void addPages() {
        functionBuilderPage = new FunctionBuilderPage(domainModel, sqlStatement, inputSQLExpression);
        functionBuilderPage.setTitle(Messages._UI_WIZARD_FUNCTION_BUILDER_HEADING);
        addPage(functionBuilderPage);
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    public void setSQLExpression(ValueExpressionFunction sqlExpr) {
        updatedSQLExpression = sqlExpr;
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public boolean performFinish() {
        boolean ready = functionBuilderPage.performOk();
        return ready;
    }
}