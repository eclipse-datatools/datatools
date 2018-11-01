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

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLResource;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseOptionsPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSearchPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.caseexpr.CaseSimplePage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.cast.CastBuilderPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.constant.ConstantOptionsPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.constant.NumericConstantBuilderPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.constant.StringConstantBuilderPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.function.FunctionBuilderPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.multiexpr.ExpressionsByOperatorsPage;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.subquery.SubQueryPage;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.StringUtility;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;


public class ExpressionBuilderWizard extends Wizard implements INewWizard {

    protected MainExpressionPage mainExpressionPage;
    protected CaseOptionsPage caseOptionsPage;
    protected CaseSearchPage caseSearchPage;
    protected CaseSimplePage caseSimplePage;
    protected CastBuilderPage castBuilderPage;
    protected ConstantOptionsPage constantOptionsPage;
    protected NumericConstantBuilderPage numericConstantBuilderPage;
    protected StringConstantBuilderPage stringConstantBuilderPage;
    protected FunctionBuilderPage functionBuilderPage;
    protected SubQueryPage subQueryPage;
    protected ExpressionsByOperatorsPage expressionsByOperatorsPage;

    private SQLDomainModel domainModel;
    private SQLQueryObject sqlStatement;
    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;

    public static String MAINEXPRESSIONPAGE = "Main Expression Page"; //$NON-NLS-1$
    public static String CASEOPTIONSPAGE = "CASE Options Page"; //$NON-NLS-1$
    public static String CASEBUILDERPAGE = "CASE Builder Page"; //$NON-NLS-1$
    public static String CONSTANTOPTIONSPAGE = "Constant Options Page"; //$NON-NLS-1$
    public static String NUMERICCONSTANTBUILDERPAGE = "Numeric Constant Builder Page"; //$NON-NLS-1$
    public static String STRINGCONSTANTBUILDERPAGE = "String Constant Builder Page"; //$NON-NLS-1$
    public static String EXPRESSIONSBYOPERATORSPAGE = "Expression By Operators Page"; //$NON-NLS-1$
    public static String SUBQUERYPAGE = "SubQuery Page"; //$NON-NLS-1$
    public static String NUMERICCONSTANT = Messages._UI_NUMERIC_CONSTANT;

    private boolean isColumn;
//TODO add tracing
    public ExpressionBuilderWizard() {
        super();
        domainModel = null;
        sqlStatement = null;
        inputSQLExpression = null;
        this.isColumn = false;
        setWindowTitle(Messages._UI_WIZARD_MAIN_EXPRESSION_TITLE);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin.getPlugin().getClass(),
                SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));
    }
//TODO add tracing
    public ExpressionBuilderWizard(SQLDomainModel domainModel) {
        super();

        this.domainModel = domainModel;
        sqlStatement = null;
        inputSQLExpression = null;
        this.isColumn = false;
        setWindowTitle(Messages._UI_WIZARD_MAIN_EXPRESSION_TITLE);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin.getPlugin().getClass(),
                SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));

    }
//TODO add tracing
    public ExpressionBuilderWizard(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg) {
        super();
        this.domainModel = domainModel;
        sqlStatement = sqlStatementArg;
        inputSQLExpression = null;
        this.isColumn = false;
        setWindowTitle(Messages._UI_WIZARD_MAIN_EXPRESSION_TITLE);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin.getPlugin().getClass(),
                SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));

    }

    public SQLDomainModel getDomainModel() {
        return domainModel;
    }

    public void setDomainModel(SQLDomainModel domainModel) {
        this.domainModel = domainModel;
    }

    public void setIsColumn(boolean isColumn) {
        this.isColumn = isColumn;
    }

    public void setSQLStatement(QueryStatement sqlStatementArg) {
        this.sqlStatement = sqlStatementArg;
    }

    public SQLQueryObject getSQLStatement() {
        return sqlStatement;
    }

    public void setInputExpression(QueryValueExpression in) {
        inputSQLExpression = in;
    }
//TODO add tracing
    public QueryValueExpression getSQLExpression() {

        return updatedSQLExpression;
    }

    boolean isXMLFunction = false;

    public void setIsXMLFunction(boolean isXMLFunction) {
        this.isXMLFunction = isXMLFunction;
    }

    /**
     * WizardPages will call this to set the updated Expression to return back to the builder
     */
    public void setSQLExpression(QueryValueExpression sqlExpr) {
        updatedSQLExpression = sqlExpr;
    }

    public Wizard createSmartGuide() {
        return this;
    }

    public void addPages() {

        if (inputSQLExpression == null || inputSQLExpression instanceof ValueExpressionNullValue || inputSQLExpression instanceof ValueExpressionDefaultValue) {
            addAllPages();
        }
        else if (inputSQLExpression != null && inputSQLExpression instanceof ValueExpressionFunction) {
            functionBuilderPage = new FunctionBuilderPage(domainModel, sqlStatement, (ValueExpressionFunction) inputSQLExpression, isColumn);
            addPage(functionBuilderPage);
        }
        else if (inputSQLExpression != null && inputSQLExpression instanceof ValueExpressionCast) {
            castBuilderPage = new CastBuilderPage(domainModel, sqlStatement, inputSQLExpression);
            addPage(castBuilderPage);
        }
        else if (inputSQLExpression != null && inputSQLExpression instanceof ValueExpressionSimple) {
            if (inputSQLExpression.toString().equals("*") || inputSQLExpression.toString().toUpperCase().equals("NULL") //$NON-NLS-1$
                    || inputSQLExpression.toString().toUpperCase().equals("NOT NULL") || inputSQLExpression.toString().toUpperCase().equals("DEFAULT")
                    || inputSQLExpression.toString().equals("")) {
                addAllPages();
            }
            else if (StringUtility.lastIndexOfAnyBut(inputSQLExpression.getSQL(), NUMERICCONSTANT) == -1 && !(inputSQLExpression.getSQL().equals(""))) {
                numericConstantBuilderPage = new NumericConstantBuilderPage(sqlStatement, inputSQLExpression);
                addPage(numericConstantBuilderPage);
            }
            else {
                stringConstantBuilderPage = new StringConstantBuilderPage(sqlStatement, inputSQLExpression, domainModel);
                stringConstantBuilderPage.setIsXMLFunction(isXMLFunction);
                addPage(stringConstantBuilderPage);
            }
        }
        else if (inputSQLExpression != null && (inputSQLExpression instanceof ValueExpressionCombined || inputSQLExpression instanceof ValueExpressionColumn)) {
            expressionsByOperatorsPage = new ExpressionsByOperatorsPage(domainModel, sqlStatement, inputSQLExpression);
            addPage(expressionsByOperatorsPage);
        }
        else if (inputSQLExpression != null && inputSQLExpression instanceof ValueExpressionCase) {
            if (inputSQLExpression instanceof ValueExpressionCaseSearch) {
                caseSearchPage = new CaseSearchPage(domainModel, sqlStatement, inputSQLExpression);
                addPage(caseSearchPage);
            }
            else if (inputSQLExpression instanceof ValueExpressionCaseSimple) {
                caseSimplePage = new CaseSimplePage(domainModel, sqlStatement, inputSQLExpression);
                addPage(caseSimplePage);
            }
        }
        else if (inputSQLExpression != null && inputSQLExpression instanceof ValueExpressionScalarSelect) {
            subQueryPage = new SubQueryPage(domainModel, inputSQLExpression);
            addPage(subQueryPage);
        }

    }

    private void addAllPages() {
        mainExpressionPage = new MainExpressionPage(domainModel, inputSQLExpression);
        addPage(mainExpressionPage);

        functionBuilderPage = new FunctionBuilderPage(domainModel, sqlStatement, null, isColumn);
        addPage(functionBuilderPage);

        castBuilderPage = new CastBuilderPage(domainModel, sqlStatement, null);
        addPage(castBuilderPage);

        expressionsByOperatorsPage = new ExpressionsByOperatorsPage(domainModel, sqlStatement, null);
        addPage(expressionsByOperatorsPage);

        constantOptionsPage = new ConstantOptionsPage(sqlStatement, null);
        stringConstantBuilderPage = new StringConstantBuilderPage(sqlStatement, null, domainModel);
        numericConstantBuilderPage = new NumericConstantBuilderPage(sqlStatement, null);
        addPage(constantOptionsPage);
        addPage(numericConstantBuilderPage);
        addPage(stringConstantBuilderPage);

        subQueryPage = new SubQueryPage(domainModel, null);
        addPage(subQueryPage);

        caseOptionsPage = new CaseOptionsPage(sqlStatement, null);
        caseSearchPage = new CaseSearchPage(domainModel, sqlStatement, null);
        caseSimplePage = new CaseSimplePage(domainModel, sqlStatement, null);
        addPage(caseOptionsPage);
        addPage(caseSearchPage);
        addPage(caseSimplePage);

    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    public boolean performFinish() {
        boolean rc = true;
        if (functionBuilderPage != null && functionBuilderPage.currentPage()) {
            rc = functionBuilderPage.performOk();
        }
        if (castBuilderPage != null && castBuilderPage.currentPage()) {
            rc = castBuilderPage.performOk();
        }
        if (stringConstantBuilderPage != null && stringConstantBuilderPage.currentPage()) {
            rc = stringConstantBuilderPage.performOk();
        }
        if (numericConstantBuilderPage != null && numericConstantBuilderPage.currentPage()) {
            rc = numericConstantBuilderPage.performOk();
        }
        if (caseSearchPage != null && caseSearchPage.currentPage()) {
            rc = caseSearchPage.performOk();
        }
        if (caseSimplePage != null && caseSimplePage.currentPage()) {
            rc = caseSimplePage.performOk();
        }
        if (expressionsByOperatorsPage != null && expressionsByOperatorsPage.currentPage()) {
            rc = expressionsByOperatorsPage.performOk();
        }
        if (subQueryPage != null && subQueryPage.currentPage()) {
            rc = subQueryPage.performOk();
        }

        return rc;
    }

    public void setAllPagesComplete(boolean bstate) {
        if (functionBuilderPage != null) {
            functionBuilderPage.setPageComplete(bstate);
        }
        if (castBuilderPage != null) {
            castBuilderPage.setPageComplete(bstate);
        }
        if (constantOptionsPage != null) {
            constantOptionsPage.setPageComplete(bstate);
        }
        if (stringConstantBuilderPage != null) {
            stringConstantBuilderPage.setPageComplete(bstate);
        }
        if (numericConstantBuilderPage != null) {
            numericConstantBuilderPage.setPageComplete(bstate);
        }
        if (caseOptionsPage != null) {
            caseOptionsPage.setPageComplete(bstate);
        }
        if (caseSearchPage != null) {
            caseSearchPage.setPageComplete(bstate);
        }
        if (caseSimplePage != null) {
            caseSimplePage.setPageComplete(bstate);
        }
        if (mainExpressionPage != null) {
            mainExpressionPage.setPageComplete(bstate);
        }
        if (expressionsByOperatorsPage != null) {
            expressionsByOperatorsPage.setPageComplete(bstate);
        }
        if (subQueryPage != null) {
            subQueryPage.setPageComplete(bstate);
        }
    }
//TODO add tracing
    public IWizardPage getNextPage(IWizardPage currentPage) {
        WizardPage nextPage = null;
        if (currentPage instanceof MainExpressionPage) {
            String type = ((MainExpressionPage) currentPage).getExpressionType();
            if (type.equals(MainExpressionPage.CASE_EXPRESSION)) {
                nextPage = caseOptionsPage;
            }
            else if (type.equals(MainExpressionPage.CAST_EXPRESSION)) {
                nextPage = castBuilderPage;
            }
            else if (type.equals(MainExpressionPage.FUNCTION_EXPRESSION)) {
                nextPage = functionBuilderPage;
            }
            else if (type.equals(MainExpressionPage.CONSTANT_EXPRESSION)) {
                nextPage = constantOptionsPage;
            }
            else if (type.equals(MainExpressionPage.SUBQUERY_EXPRESSION)) {
                nextPage = subQueryPage;
            }
            else if (type.equals(MainExpressionPage.EXPRESSIONS_BY_OPERATORS)) {
                nextPage = expressionsByOperatorsPage;
            }
            else {
                return null;
            }
            return nextPage;
        }
        else if (currentPage instanceof CaseOptionsPage) {
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
        else if (currentPage instanceof ConstantOptionsPage) {
            Object type = ((ConstantOptionsPage) currentPage).getConstantType();
            if (type.equals(ConstantOptionsPage.NUMERIC_CONSTANT)) {
                nextPage = numericConstantBuilderPage;
            }
            else if (type.equals(ConstantOptionsPage.STRING_CONSTANT)) {
                nextPage = stringConstantBuilderPage;
            }
            else {
                return null;
            }
            return nextPage;
        }
        else {
            return null;
        }
    }

}