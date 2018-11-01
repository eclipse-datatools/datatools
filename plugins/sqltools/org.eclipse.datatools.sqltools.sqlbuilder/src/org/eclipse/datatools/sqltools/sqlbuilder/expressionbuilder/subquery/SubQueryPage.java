/*******************************************************************************
 * Copyright 2000, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.subquery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserException;
import org.eclipse.datatools.sqltools.parsers.sql.SQLParserInternalException;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WorkbenchUtility;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.PageBook;


public class SubQueryPage extends WizardPage implements SelectionListener {

    private SQLDomainModel domainModel;
    private QueryValueExpression inputSQLExpression;
    private Text newExpressionText;
    private QueryExpressionRoot choiceQueryExpr;

    private Button existingSelectButton;
    private Button createNewSelectButton;
    private PageBook pageBook;
    private Composite mainPanel;
    private Composite existingSelectWorkArea;
    private Composite createNewSelectWorkArea;
    private Text freeFormText;

    Combo queryStatementCombo;
    List stmtsList = new ArrayList(0);

    public SubQueryPage(SQLDomainModel domainModel, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_SUBQUERY_HEADING);
        setTitle(Messages._UI_WIZARD_SUBQUERY_HEADING);
        setDescription(Messages._UI_WIZARD_SUBQUERY_EXPL);
        setPageComplete(false);
        this.domainModel = domainModel;
        this.inputSQLExpression = sqlExpr;
    }

    public void createControl(Composite parent) {
        mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_SUBQUERY_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Label infoLabel = new Label(mainPanel, SWT.LEFT | SWT.HORIZONTAL);
        infoLabel.setText(Messages._UI_LABEL_CHOICES);
        infoLabel.setLayoutData(ViewUtility.createHorizontalFill());

        existingSelectButton = new Button(mainPanel, SWT.RADIO);
        existingSelectButton.setText(Messages._UI_RADIO_EXISTING_STATEMENTS);
        existingSelectButton.addSelectionListener(this);
        existingSelectButton.setSelection(true);

        createNewSelectButton = new Button(mainPanel, SWT.RADIO);
        createNewSelectButton.setText(Messages._UI_RADIO_SCRATCH_SELECT);
        createNewSelectButton.addSelectionListener(this);
        createNewSelectButton.setSelection(false);

        pageBook = new PageBook(mainPanel, SWT.NONE);

        existingSelectWorkArea = new Composite(pageBook, SWT.NONE);
        existingSelectWorkArea.setLayout(new GridLayout());
        existingSelectWorkArea.setLayoutData(ViewUtility.createFill());

        queryStatementCombo = ViewUtility.createComboBox(existingSelectWorkArea);
        queryStatementCombo.addSelectionListener(this);

        Label previewFunctionLabel = new Label(existingSelectWorkArea, SWT.LEFT | SWT.HORIZONTAL);
        previewFunctionLabel.setText(Messages._UI_LABEL_EXISTING_SELECT);
        newExpressionText = new Text(existingSelectWorkArea, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        newExpressionText.setLayoutData(ViewUtility.createFill());
        newExpressionText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (getExistingSelectButton().getSelection() && !getNewExpressionText().getText().equals("")) {
                    setPageComplete(true);

                    if (getWizard() instanceof ExpressionBuilderWizard) {
                        ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
                        wiz.setAllPagesComplete(true);
                    }
                }
                else {
                    setPageComplete(false);
                }
            }
        });

        createNewSelectWorkArea = new Composite(pageBook, SWT.NONE);
        createNewSelectWorkArea.setLayout(new GridLayout());
        createNewSelectWorkArea.setLayoutData(ViewUtility.createFill());

        Label createNewSelectInfoLabel = new Label(createNewSelectWorkArea, SWT.LEFT | SWT.HORIZONTAL);
        createNewSelectInfoLabel.setText(Messages._UI_LABEL_NEW_SELECT_INFO);

        freeFormText = new Text(createNewSelectWorkArea, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        freeFormText.setLayoutData(ViewUtility.createFill());
        freeFormText.addModifyListener(new ModifyListener() {

            public void modifyText(ModifyEvent e) {
                if (getCreateNewSelectButton().getSelection() && !getFreeFormText().getText().equals("")) {
                    setPageComplete(true);

                    if (getWizard() instanceof ExpressionBuilderWizard) {
                        ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
                        wiz.setAllPagesComplete(true);
                    }
                }
                else {
                    setPageComplete(false);
                }
            }
        });

        GridData gd = new GridData();
        GC gc = new GC(previewFunctionLabel);
        Point pointSize = gc.stringExtent(Messages._UI_LABEL_NEW_SELECT_INFO);
        gd.widthHint = pointSize.x + gc.getAdvanceWidth('M');
        gd.horizontalAlignment = GridData.FILL;
        gd.grabExcessHorizontalSpace = true;
        gd.verticalAlignment = GridData.FILL;
        gd.grabExcessVerticalSpace = true;

        pageBook.setLayoutData(gd);

        pageBook.showPage(existingSelectWorkArea);
        setControl(mainPanel);
    }

    public Button getCreateNewSelectButton() {
        return createNewSelectButton;
    }

    public Button getExistingSelectButton() {
        return existingSelectButton;
    }

    public Text getFreeFormText() {
        return freeFormText;
    }

    public Text getNewExpressionText() {
        return newExpressionText;
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

    public void widgetSelected(SelectionEvent se) {
        if (se.widget == queryStatementCombo) {
            int index = queryStatementCombo.getSelectionIndex();
            if (index >= 0) {
                String selection = queryStatementCombo.getItem(index);
                choiceQueryExpr = getQueryExprForName(selection);
            }
            updateFinishButton();
            updateNewExpressionText();
        }
        else if (se.widget == existingSelectButton) {
            pageBook.showPage(existingSelectWorkArea);
            updateFinishButton();
        }
        else if (se.widget == createNewSelectButton) {
            pageBook.showPage(createNewSelectWorkArea);
            updateFinishButton();
        }
    }

    QueryExpressionRoot getQueryExprForName(String name) {
        QueryStatement stmt = null;
        QueryExpressionRoot root = null;
        Iterator stmtsItr = stmtsList.iterator();
        if (stmtsItr != null) {
            IFile file = null;
            String fileName;
            while (stmtsItr.hasNext()) {
                file = (IFile) stmtsItr.next();
                fileName = file.getName();
                fileName = fileName.substring(0, fileName.indexOf(SQLBuilderConstants.SQL_FILE_EXTENSION)); //strip the .sql part
                if (name.equals(fileName)) {
                    break;
                }
            }
            if (file != null) {
                String fileContent = (WorkbenchUtility.readFileContentsToString(file, true)).trim();
                try {
                    stmt = domainModel.parse(fileContent, true);
                }
                catch (SQLParserException e) {
                	// TODO: handle exception
                }
                catch (SQLParserInternalException e) {
                	// TODO: handle exception
                }
                if (stmt instanceof QuerySelectStatement) {
                    root = ((QuerySelectStatement) stmt).getQueryExpr();
                }
            }
        }
        return root;

    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            initializeFields();
            if (inputSQLExpression instanceof ValueExpressionScalarSelect) {
                QueryExpressionRoot inputExpr = ((ValueExpressionScalarSelect) inputSQLExpression).getQueryExpr();
                if (inputExpr != null) {
                    existingSelectButton.setSelection(true);
                    createNewSelectButton.setSelection(false);
                    existingSelectButton.notifyListeners(SWT.Selection, new Event());
                    choiceQueryExpr = inputExpr;
                    queryStatementCombo.clearSelection();
                    updateNewExpressionText();
                }
                else {
                    existingSelectButton.setSelection(false);
                    createNewSelectButton.setSelection(true);
                    createNewSelectButton.notifyListeners(SWT.Selection, new Event());
                    choiceQueryExpr = null;
                    freeFormText.setText(inputSQLExpression.getSQL());
                    freeFormText.notifyListeners(SWT.Modify, new Event());
                }
            }
            updateFinishButton();
        }
    }

    private void initializeFields() {
        queryStatementCombo.removeAll();
        IProject project = domainModel.getProject();
        stmtsList = WorkbenchUtility.getSelectStatementsFromProject(project, domainModel);
        Iterator stmtsItr = stmtsList.iterator();
        String name;
        IFile file;
        while (stmtsItr.hasNext()) {
            file = (IFile) stmtsItr.next();
            name = file.getName();
            name = name.substring(0, name.indexOf(SQLBuilderConstants.SQL_FILE_EXTENSION)); //strip the .sql part
            queryStatementCombo.add(name);
        }

    }

    private void updateFinishButton() {
        boolean isComplete = false;

        if (existingSelectButton.getSelection() && !(newExpressionText.getText().equals(""))) {
            isComplete = true;
        }
        else if (createNewSelectButton.getSelection() && !(freeFormText.getText().equals(""))) {
            isComplete = true;
        }

        setPageComplete(isComplete);

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setAllPagesComplete(isComplete);
        }
    }

    public boolean performOk() {
        ValueExpressionScalarSelect newExpr = null;

        if (existingSelectButton.getSelection()) {
            // Clone this sub-query. It belongs to this statement now
            newExpr = parseIt(newExpressionText.getText());
        }
        else if (createNewSelectButton.getSelection()) {
            newExpr = parseIt(freeFormText.getText());
        }

        if (newExpr != null) {
            if (getWizard() instanceof ExpressionBuilderWizard) {
                ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
                wiz.setSQLExpression(newExpr);
            }
            else if (getWizard() instanceof SubQueryWizard) {
                SubQueryWizard wiz = (SubQueryWizard) getWizard();
                wiz.setSQLExpression(newExpr);
            }
            return true;
        }

        return false;
    }

    /**
     * Parse the statement and create a new object for the sub-select.
     * Add the new query to this expression
     */
    private ValueExpressionScalarSelect parseIt(String selectText) {
        ValueExpressionScalarSelect expr = null;
        QueryStatement query = null;
        try {
            query = domainModel.parse(selectText);
        }
        catch (SQLParserException e) {
        }
        catch (SQLParserInternalException e) {
        }
        ExpressionHelper eh = new ExpressionHelper();
        expr = eh.createScalarSelect(query);
        return expr;
    }

    private void updateNewExpressionText() {
        if (choiceQueryExpr != null) {
            newExpressionText.setText(choiceQueryExpr.getSQL());
        }
        else {
            newExpressionText.setText("");
        }
    }

    public boolean currentPage() {
        return isCurrentPage();
    }
}