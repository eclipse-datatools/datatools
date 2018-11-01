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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.help.IWorkbenchHelpSystem;

public class NumericConstantBuilderPage extends WizardPage implements SelectionListener
{
    protected SQLQueryObject sqlStatement;
    
    private Composite mainPanel;
    private Text constantText;

    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;

    /* Define regular expressions for SQL numeric constants.  These can be either integer, decimal,
     * or floating point constants.  (See a SQL Reference for more information.)  We need two patterns,
     * one for use during editing of the constant text that allows for incomplete input, and one for 
     * checking the final input that is used to enable the Finish button. */
    private static final String REGEX_TEMP = "[+-]|\\.|[+-].|([+-]?((\\d+(\\.?\\d*)?)|(\\.?\\d+))([eE][+-]?\\d*)?)";
    private static final String REGEX_FINAL = "[+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))([eE][+-]?\\d+)?";
    private static final String DEFAULT_TEXT = "0";

    private Button clearButton;

    public NumericConstantBuilderPage(SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_NUMERIC_CONSTANT_TITLE);
        setTitle(Messages._UI_WIZARD_NUMERIC_CONSTANT_HEADER);
        setDescription(Messages._UI_WIZARD_NUMERIC_CONSTANT_EXPL);
        setPageComplete(true);
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
    }

    public void createControl(Composite parent) {
        mainPanel = new Composite(parent, SWT.NONE);
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchHelpSystem helpSystem = workbench.getHelpSystem();
        helpSystem.setHelp(mainPanel, SQLBuilderContextIds.SQLE_NUMERIC_CONSTANT_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Composite topPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout topPanelLayout = new GridLayout();
        topPanelLayout.numColumns = 2;
        topPanelLayout.marginWidth = 0;
        topPanelLayout.marginHeight = 0;
        topPanelLayout.horizontalSpacing = 0;
        topPanelLayout.makeColumnsEqualWidth = false;

        topPanel.setLayout(topPanelLayout);
        topPanel.setLayoutData(ViewUtility.createHorizontalFill());

        /* Add a text field for the numeric constant value. */
        constantText = new Text(topPanel, SWT.SINGLE | SWT.READ_ONLY | SWT.H_SCROLL | SWT.BORDER);
        constantText.setLayoutData(ViewUtility.createHorizontalFill());
        constantText.setText(DEFAULT_TEXT);
        constantText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        constantText.setEnabled(true);
        constantText.setEditable(true);
        
        /* Add a verify listener to the numeric constant text field so that only valid characters for 
         * a SQL numeric constant are allowed in the field. */
        constantText.addListener (SWT.Verify, new Listener () {
            /* Create the pattern for the verification. */
            Pattern pattern = Pattern.compile(REGEX_TEMP);           
            
            /* Handle the verification event. */
            public void handleEvent(Event e) {
                e.doit = false;
                
                /* Handle backspace and delete characters. */
                if (e.character == '\b' || e.character == '\u007F') {
                    e.doit = true;
                }
                else {
                    /* Create a temp string containing the updated text.  The event contains only the
                     * user's new input, which might be a single character (if typed) or a string 
                     * (if pasted into the field). */
                    String currentText = constantText.getText();
                    StringBuffer newTextBuf = new StringBuffer(currentText);
                    char[] chars = e.text.toCharArray();
                    int index = e.start - 1;
                    for (int i = 0; i < e.text.length(); i++) {
                        index++;
                        if (index >= newTextBuf.length()) {
                            newTextBuf.append(chars[i]);
                        }
                        else {
                            newTextBuf.setCharAt(index, chars[i]);
                        }
                    }

                    /* Validate the updated text value. */
                    Matcher matcher = pattern.matcher(newTextBuf);
                    if (matcher.matches()) {
                        e.doit = true;
                    }
                }
            }
        });

        /* Add a modify listener to the numeric constant text field so we can update the Finish button 
         * based on the updated content of the constant text field.  (We can't do this in the verify
         * handler because it finishes before the text field text is updated.)*/
        constantText.addListener (SWT.Modify, new Listener () {
            /* Handle the Modify event. */
            public void handleEvent(Event e) {
                updateFinishButton();
            }
        });

        Composite functionButtonPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout functionButtonPanelLayout = new GridLayout();
        functionButtonPanelLayout.numColumns = 4;
        functionButtonPanelLayout.makeColumnsEqualWidth = true;
        functionButtonPanel.setLayout(functionButtonPanelLayout);
        functionButtonPanel.setLayoutData(ViewUtility.createHorizontalFill());

        clearButton = new Button(functionButtonPanel, SWT.PUSH);
        clearButton.setText(Messages._UI_BUTTON_CLEAR);
        clearButton.setLayoutData(ViewUtility.createFill());
        clearButton.addSelectionListener(this);

        updateFinishButton();
        setControl(mainPanel);
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

    public void widgetSelected(SelectionEvent se) {
        if (se.widget == clearButton) {
            doClear();
        }
    }

    public void setInputExpression(QueryValueExpression in) {
        inputSQLExpression = in;
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        constantText.setEnabled(true);
        constantText.setEditable(true);
        if (visible) {
            updateFinishButton();
            if (inputSQLExpression != null) {
                String input = inputSQLExpression.getSQL();
                constantText.setText(input);
                constantText.append("");
            }
            constantText.setFocus();
        }
    }

    private void updateFinishButton() {
        boolean isComplete = false;

        /* Validate the numeric constant value and set the finish button state based on the result. */
        Pattern pattern = Pattern.compile(REGEX_FINAL);
        String text = constantText.getText();
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            isComplete = true;
        }
        
        /* Update the wizard finish button.  Note: the page complete code checks the wizard complete state, 
         * so set the wizard complete first. */
        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setAllPagesComplete(isComplete);
        }
        else if (getWizard() instanceof ConstantExpressionWizard) {
            ConstantExpressionWizard wiz = (ConstantExpressionWizard) getWizard();
            wiz.setConstantOptionsPageComplete(isComplete);
        }
        
        setPageComplete(isComplete);
    }

    public boolean performOk() {
        String finalNumber;
        finalNumber = constantText.getText();

        QueryValueExpression sqlConstantExpr = ExpressionHelper.createExpression(finalNumber);

        updatedSQLExpression = sqlConstantExpr;

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }
        else if (getWizard() instanceof ConstantExpressionWizard) {
            ConstantExpressionWizard wiz = (ConstantExpressionWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }

        return true;
    }

    private void doClear() {
        constantText.setText("0");
    }

    public boolean currentPage() {
        return isCurrentPage();
    }

}

