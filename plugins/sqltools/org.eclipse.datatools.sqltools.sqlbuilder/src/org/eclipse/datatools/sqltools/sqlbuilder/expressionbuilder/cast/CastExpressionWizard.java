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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.cast;

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

public class CastExpressionWizard extends Wizard implements INewWizard {

    protected CastBuilderPage castBuilderPage;

    private SQLQueryObject sqlStatement;
    private QueryValueExpression sqlExpression;
    private QueryValueExpression updatedSQLExpression;
    private SQLDomainModel domainModel;

    public CastExpressionWizard(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        sqlStatement = sqlStatementArg;
        sqlExpression = sqlExpr;
        this.domainModel = domainModel;
        setWindowTitle(Messages._UI_WIZARD_CAST_EXPRESSION_TITLE);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(SQLBuilderPlugin.getPlugin().getClass(), SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));
    }

    public Wizard createSmartGuide() {
        return this;
    }

    public void addPages() {
        castBuilderPage = new CastBuilderPage(domainModel, sqlStatement, sqlExpression);
        addPage(castBuilderPage);

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
        if (castBuilderPage.currentPage()) {
            castBuilderPage.performOk();
        }
        return true;
    }
}