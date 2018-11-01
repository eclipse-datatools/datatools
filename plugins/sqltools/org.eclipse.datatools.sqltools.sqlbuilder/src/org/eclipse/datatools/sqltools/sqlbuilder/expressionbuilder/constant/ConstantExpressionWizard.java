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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.constant;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLResource;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.StringUtility;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;


public class ConstantExpressionWizard extends Wizard implements INewWizard {

    protected NumericConstantBuilderPage numericConstantBuilderPage;
    protected StringConstantBuilderPage stringConstantBuilderPage;
    protected ConstantOptionsPage constantOptionsPage;

    private SQLQueryObject sqlStatement;
    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;

    public static String NUMERICCONSTANT = Messages._UI_NUMERIC_CONSTANT;

    private SQLDomainModel domainModel;

    public ConstantExpressionWizard(SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr, SQLDomainModel model) {
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
        updatedSQLExpression = inputSQLExpression;
        setWindowTitle(Messages._UI_WIZARD_CONSTANT_TITLE);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(SQLBuilderPlugin.getPlugin().getClass(), SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));

        domainModel = model;
    }

    public ConstantExpressionWizard(QueryStatement sqlStatementArg, QueryValueExpression sqlExpr) {
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
        updatedSQLExpression = inputSQLExpression;
        setWindowTitle(Messages._UI_WIZARD_CONSTANT_TITLE);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(SQLBuilderPlugin.getPlugin().getClass(), SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));
    }

    public void addPages() {
        if (inputSQLExpression == null) {
            constantOptionsPage = new ConstantOptionsPage(sqlStatement, null);
            stringConstantBuilderPage = new StringConstantBuilderPage(sqlStatement, null, domainModel);
            numericConstantBuilderPage = new NumericConstantBuilderPage(sqlStatement, null);
            addPage(constantOptionsPage);
            addPage(numericConstantBuilderPage);
            addPage(stringConstantBuilderPage);
        }
        else if (inputSQLExpression != null) {
            if (inputSQLExpression.toString().equals("*") || inputSQLExpression.toString().toUpperCase().equals("NULL")
                    || inputSQLExpression.toString().toUpperCase().equals("IS NOT NULL")) {
                constantOptionsPage = new ConstantOptionsPage(sqlStatement, null);
                stringConstantBuilderPage = new StringConstantBuilderPage(sqlStatement, null, domainModel);
                numericConstantBuilderPage = new NumericConstantBuilderPage(sqlStatement, null);
                addPage(constantOptionsPage);
                addPage(numericConstantBuilderPage);
                addPage(stringConstantBuilderPage);
            }
            else if (StringUtility.lastIndexOfAnyBut(inputSQLExpression.toString(), NUMERICCONSTANT) == -1 && !(inputSQLExpression.toString().equals(""))) {
                numericConstantBuilderPage = new NumericConstantBuilderPage(sqlStatement, inputSQLExpression);
                addPage(numericConstantBuilderPage);
            }
            else if (inputSQLExpression.toString().equals("") || inputSQLExpression.toString().equals("NULL")) {
                constantOptionsPage = new ConstantOptionsPage(sqlStatement, null);
                stringConstantBuilderPage = new StringConstantBuilderPage(sqlStatement, null, domainModel);
                numericConstantBuilderPage = new NumericConstantBuilderPage(sqlStatement, null);
                addPage(constantOptionsPage);
                addPage(numericConstantBuilderPage);
                addPage(stringConstantBuilderPage);
            }
            else {
                stringConstantBuilderPage = new StringConstantBuilderPage(sqlStatement, inputSQLExpression, domainModel);
                addPage(stringConstantBuilderPage);
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
        if (currentPage instanceof ConstantOptionsPage) {
            Object type = ((ConstantOptionsPage) currentPage).getConstantType();
            if (type.equals(ConstantOptionsPage.NUMERIC_CONSTANT)) {
                nextPage = numericConstantBuilderPage;
            }
            else if (type.equals(ConstantOptionsPage.STRING_CONSTANT)) {
                nextPage = stringConstantBuilderPage;
            }
            return nextPage;
        }
        return null;
    }

    public void setConstantOptionsPageComplete(boolean bComplete) {
        if (constantOptionsPage != null) {
            constantOptionsPage.setPageComplete(bComplete);
        }
    }

    public boolean performFinish() {
        if (numericConstantBuilderPage != null && numericConstantBuilderPage.currentPage()) {
            numericConstantBuilderPage.performOk();
        }
        else if (stringConstantBuilderPage != null && stringConstantBuilderPage.currentPage()) {
            stringConstantBuilderPage.performOk();
        }

        return true;
    }
}