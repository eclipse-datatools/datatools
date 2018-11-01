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

package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLResource;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

public class CaseExpressionWizard extends Wizard implements INewWizard {

    private CaseOptionsPage caseOptionsPage;
    private CaseSearchPage caseSearchPage;
    private CaseSimplePage caseSimplePage;

    private SQLDomainModel domainModel;
    private SQLQueryObject sqlStatement;
    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;

    public CaseExpressionWizard(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        this.domainModel = domainModel;
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
        updatedSQLExpression = inputSQLExpression;
        setWindowTitle(Messages._UI_WIZARD_CASE_EXPRESSION_TITLE);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(SQLBuilderPlugin.getPlugin().getClass(), SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));
    }

    public Wizard createSmartGuide() {
        return this;
    }

    public void addPages() {
        if (inputSQLExpression == null) {
            caseOptionsPage = new CaseOptionsPage(sqlStatement, null);
            caseSearchPage = new CaseSearchPage(domainModel, sqlStatement, null);
            caseSimplePage = new CaseSimplePage(domainModel, sqlStatement, null);
            addPage(caseOptionsPage);
            addPage(caseSearchPage);
            addPage(caseSimplePage);
        }
        else if (inputSQLExpression != null) {
            if (inputSQLExpression instanceof ValueExpressionCaseSearch) {
                caseSearchPage = new CaseSearchPage(domainModel, sqlStatement, inputSQLExpression);
                addPage(caseSearchPage);
            }
            else if (inputSQLExpression instanceof ValueExpressionCaseSimple) {
                caseSimplePage = new CaseSimplePage(domainModel, sqlStatement, inputSQLExpression);
                addPage(caseSimplePage);
            }
            else {
                caseOptionsPage = new CaseOptionsPage(sqlStatement, null);
                caseSearchPage = new CaseSearchPage(domainModel, sqlStatement, null);
                caseSimplePage = new CaseSimplePage(domainModel, sqlStatement, null);
                addPage(caseOptionsPage);
                addPage(caseSearchPage);
                addPage(caseSimplePage);
            }
        }
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    public void setSQLExpression(QueryValueExpression sqlExpr) {
        updatedSQLExpression = sqlExpr;
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public IWizardPage getNextPage(IWizardPage currentPage) {
        WizardPage nextPage = null;
        if (currentPage instanceof CaseOptionsPage) {
            Object type = ((CaseOptionsPage) currentPage).getCaseType();
            if (type.equals(CaseOptionsPage.CASE_SEARCH_WHEN_CLAUSE)) {
                nextPage = caseSearchPage;
            }
            else if (type.equals(CaseOptionsPage.CASE_SIMPLE_WHEN_CLAUSE)) {
                nextPage = caseSimplePage;
            }
            else {
                return null;
            }
            return nextPage;
        }

        return null;
    }

    public void setCaseOptionsPageComplete(boolean bComplete) {
        if (caseOptionsPage != null) {
            caseOptionsPage.setPageComplete(bComplete);
        }
        if (caseSearchPage != null) {
            caseSearchPage.setPageComplete(bComplete);
        }
        if (caseSimplePage != null) {
            caseSimplePage.setPageComplete(bComplete);
        }
    }

    public boolean performFinish() {
        if (caseSearchPage != null && caseSearchPage.currentPage()) {
            caseSearchPage.performOk();
        }
        else if (caseSimplePage != null && caseSimplePage.currentPage()) {
            caseSimplePage.performOk();
        }

        return true;
    }

}