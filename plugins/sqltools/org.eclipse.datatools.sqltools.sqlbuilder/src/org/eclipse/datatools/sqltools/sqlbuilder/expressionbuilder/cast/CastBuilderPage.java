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
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.cast;

import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.ExpressionBuilderWizard;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BuilderUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ObjectComboHelper;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


public class CastBuilderPage extends WizardPage implements SelectionListener, KeyListener {

    protected SQLQueryObject sqlStatement;
    private Composite mainPanel;
    private SQLDomainModel domainModel;

    QueryValueExpression inputSQLExpression;
    QueryValueExpression updatedSQLExpression;

    private static final String asString = "AS";

    private Combo castClauseCombo;
    private ObjectComboHelper castClauseComboHelper;
    private Combo dataTypeClauseCombo;
    private Label firstLabel;
    private Combo scopeClause;

    private Label lengthLabel;
    private Label leftBracket;
    private Text dataTypePrecisionText;
    private Label commaLabel;
    private Text dataTypeScaleText;
    private Label rightBracket;

    private Text previewExpressionText;
    private ValueExpressionCast sqlCast;

    public CastBuilderPage(SQLDomainModel domainModel, SQLQueryObject sqlStatementArg, QueryValueExpression sqlExpr) {
        super(Messages._UI_WIZARD_CAST_BUILDER_TITLE);
        setTitle(Messages._UI_WIZARD_CAST_BUILDER_TITLE);
        setDescription(Messages._UI_WIZARD_CAST_BUILDER_HEADING);
        setPageComplete(true);
        sqlStatement = sqlStatementArg;
        inputSQLExpression = sqlExpr;
        this.domainModel = domainModel;
        sqlCast = null;
    }

    public void createControl(Composite parent) {
        mainPanel = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(mainPanel, SQLBuilderContextIds.SQLE_CAST_BUILDER_PAGE);
        GridLayout mainPanelLayout = new GridLayout();
        mainPanel.setLayout(mainPanelLayout);
        mainPanel.setLayoutData(ViewUtility.createFill());

        Composite topPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout topPanelLayout = new GridLayout();
        topPanelLayout.numColumns = 7;
        topPanelLayout.marginWidth = 0;
        topPanelLayout.marginHeight = 0;

        topPanel.setLayout(topPanelLayout);
        topPanel.setLayoutData(ViewUtility.createHorizontalFill());

        Label castLabel = new Label(topPanel, SWT.BORDER);
        castLabel.setText("CAST (");

        castClauseCombo = new Combo(topPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
        castClauseComboHelper = new ObjectComboHelper(castClauseCombo);
        fillCastClauseCombo();
        castClauseCombo.setLayoutData(ViewUtility.createHorizontalFill());
        castClauseCombo.select(0); // Initial selection is 0
        castClauseCombo.addSelectionListener(this);
        castClauseCombo.setToolTipText(castClauseCombo.getText());

        Label asLabel = new Label(topPanel, SWT.BORDER);
        asLabel.setText(asString);

        dataTypeClauseCombo = new Combo(topPanel, SWT.DROP_DOWN | SWT.READ_ONLY);
        dataTypeClauseCombo.setLayoutData(ViewUtility.createHorizontalFill());
        fillDataTypeClauseCombo();
        dataTypeClauseCombo.select(0); // Initial selection is 0
        dataTypeClauseCombo.addSelectionListener(this);
        dataTypeClauseCombo.setToolTipText(dataTypeClauseCombo.getText());

        firstLabel = new Label(topPanel, SWT.BORDER);
        firstLabel.setText(")          ");

        Composite lengthPanel = new Composite(mainPanel, SWT.NONE);
        GridLayout lengthPanelLayout = new GridLayout();
        lengthPanelLayout.numColumns = 6;
        lengthPanelLayout.marginWidth = 0;
        lengthPanel.setLayout(lengthPanelLayout);

        lengthLabel = new Label(lengthPanel, SWT.HORIZONTAL);
        lengthLabel.setLayoutData(ViewUtility.createHorizontalFill());
        lengthLabel.setText(Messages._UI_LABEL_DATATYPE_LENGTH);

        leftBracket = new Label(lengthPanel, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
        leftBracket.setText("  (  ");

        dataTypePrecisionText = new Text(lengthPanel, SWT.SINGLE | SWT.BORDER);
        dataTypePrecisionText.setVisible(false);
        dataTypePrecisionText.setEnabled(false);
        dataTypePrecisionText.addKeyListener(this);

        commaLabel = new Label(lengthPanel, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
        commaLabel.setText(" ,    ");
        commaLabel.setVisible(false);

        dataTypeScaleText = new Text(lengthPanel, SWT.SINGLE | SWT.BORDER);
        dataTypeScaleText.setVisible(false);
        dataTypeScaleText.setEnabled(false);
        dataTypeScaleText.addKeyListener(this);

        rightBracket = new Label(lengthPanel, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
        rightBracket.setText("  )  ");
        rightBracket.setVisible(false);

        Label previewLabel = new Label(mainPanel, SWT.HORIZONTAL);
        previewLabel.setLayoutData(ViewUtility.createHorizontalFill());
        previewLabel.setText(Messages._UI_LABEL_PREVIEW_CAST_EXPRESSION);

        previewExpressionText = new Text(mainPanel, SWT.SINGLE | SWT.READ_ONLY | SWT.WRAP | SWT.BORDER);
        previewExpressionText.setLayoutData(ViewUtility.createFill());
        updatePreview();

        setControl(mainPanel);
    }

    public void widgetDefaultSelected(SelectionEvent e) {
    }

    public void widgetSelected(SelectionEvent se) {
        if (se.widget == castClauseCombo) {
            castClauseCombo.setToolTipText(castClauseCombo.getText());
            updateExpression();
            updatePreview();
        }
        else if (se.widget == dataTypeClauseCombo) {
            dataTypeClauseCombo.setToolTipText(dataTypeClauseCombo.getText());
            updateExpression();
            updateLengthPanel();
            updatePreview();
        }
        else if (se.widget == scopeClause) {
            updatePreview();
        }

    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            fillCastClauseCombo();
            fillDataTypeClauseCombo();
            dataTypeClauseCombo.select(0);
            castClauseCombo.select(0);
            if (sqlCast == null) {
                ExpressionHelper eh = new ExpressionHelper();
                sqlCast = eh.createCast(null, "");

                PredefinedDataType dataType = org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper
                        .getPredefinedDataTypeForNamedType(dataTypeClauseCombo.getText());
                sqlCast.setDataType(dataType);
                updateExpression();
            }
            else if (inputSQLExpression != null) {
                sqlCast = (ValueExpressionCast) inputSQLExpression;
            }

            QueryValueExpression castValExpr = sqlCast.getValueExpr();

            if (castValExpr instanceof ValueExpressionNullValue) {
                castClauseCombo.select(castClauseCombo.indexOf("NULL"));
            }

            if (castValExpr instanceof ValueExpressionVariable) {
                castClauseCombo.select(castClauseCombo.indexOf("?"));
            }

            if (castValExpr instanceof ValueExpressionColumn) {
                castClauseCombo.select(castClauseCombo.indexOf(((ValueExpressionColumn) castValExpr).getName()));
                castClauseComboHelper.select(((ValueExpressionColumn) castValExpr).getName(), castValExpr);
            }

            PredefinedDataType dataType = (PredefinedDataType) sqlCast.getDataType();
            dataTypeClauseCombo.select(dataTypeClauseCombo.indexOf(dataType.getPrimitiveType().getName()));
            updateExpression();
            updatePreview();
            updateLengthPanel();
            updateFinishButton();
        }
    }

    private void updateFinishButton() {
        boolean isComplete;
        isComplete = true;
        setPageComplete(isComplete);
        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setAllPagesComplete(true);
        }
    }

    public boolean performOk() {
        updatedSQLExpression = sqlCast;

        if (getWizard() instanceof ExpressionBuilderWizard) {
            ExpressionBuilderWizard wiz = (ExpressionBuilderWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }
        else if (getWizard() instanceof CastExpressionWizard) {
            CastExpressionWizard wiz = (CastExpressionWizard) getWizard();
            wiz.setSQLExpression(updatedSQLExpression);
        }

        return true;
    }

    private void fillCastClauseCombo() {
        castClauseCombo.removeAll();

        Vector availableExpressionsComboBoxItemsVector = new Vector();

        Vector availableExpressionsVector = new Vector();

        if (sqlStatement != null) {
            availableExpressionsVector.addAll(BuilderUtility.getColumnVector(sqlStatement)); // add column expressions
        }
        else {
            availableExpressionsVector = new Vector();
        }

        for (int i = 0; i < availableExpressionsVector.size(); i++) {
            if (availableExpressionsVector.elementAt(i) instanceof QueryValueExpression) {
                availableExpressionsComboBoxItemsVector.addElement(new LabelValuePair(
                        ((QueryValueExpression) availableExpressionsVector.elementAt(i)).getSQL(), availableExpressionsVector.elementAt(i)));
            }
            else {
                availableExpressionsComboBoxItemsVector.addElement(new LabelValuePair(availableExpressionsVector.elementAt(i).toString(),
                        availableExpressionsVector.elementAt(i)));
            }
        }

        availableExpressionsComboBoxItemsVector.addElement(new LabelValuePair("NULL", null));
        availableExpressionsComboBoxItemsVector.addElement(new LabelValuePair("?", null));

        LabelValuePair[] expressionsArray = new LabelValuePair[availableExpressionsComboBoxItemsVector.size()];
        availableExpressionsComboBoxItemsVector.copyInto(expressionsArray);

        castClauseComboHelper.setItems(expressionsArray);
    }

    private void updateLengthPanel() {
        lengthLabel.setText(Messages._UI_LABEL_DATATYPE_LENGTH);

        leftBracket.setVisible(true);
        leftBracket.setText("  -  ");

        if (domainModel.getVendor().isDB2()) {
            if (dataTypeClauseCombo.getText().equals("DECIMAL") ||
            		dataTypeClauseCombo.getText().equals("NUMERIC")) {
                leftBracket.setText("  (  ");
                leftBracket.setVisible(true);
                dataTypePrecisionText.setVisible(true);
                dataTypePrecisionText.setEnabled(true);
                commaLabel.setText(" ,    ");
                commaLabel.setVisible(true);
                dataTypeScaleText.setVisible(true);
                dataTypeScaleText.setEnabled(true);
                rightBracket.setText("  )  ");
                rightBracket.setVisible(true);
            }
            else if ((dataTypeClauseCombo.getText().equals("CHAR")) || (dataTypeClauseCombo.getText().equals("VARCHAR"))
                    || (dataTypeClauseCombo.getText().equals("LONG VARCHAR")) || (dataTypeClauseCombo.getText().equals("CLOB"))
                    || (dataTypeClauseCombo.getText().equals("GRAPHIC")) || (dataTypeClauseCombo.getText().equals("VARGRAPHIC"))
                    || (dataTypeClauseCombo.getText().equals("LONG VARGRAPHIC")) || (dataTypeClauseCombo.getText().equals("DBCLOB"))
                    || (dataTypeClauseCombo.getText().equals("BLOB"))) {
                leftBracket.setText("  (  ");
                leftBracket.setVisible(true);
                dataTypePrecisionText.setVisible(true);
                dataTypePrecisionText.setEnabled(true);
                commaLabel.setText("  )  ");
                commaLabel.setVisible(true);
                dataTypeScaleText.setVisible(false);
                dataTypeScaleText.setEnabled(false);
                dataTypeScaleText.setText("");
                rightBracket.setVisible(false);
            }
            else {
                leftBracket.setVisible(true);
                leftBracket.setText("  -  ");
                dataTypePrecisionText.setVisible(false);
                dataTypePrecisionText.setEnabled(false);
                dataTypePrecisionText.setText("");
                commaLabel.setVisible(false);
                dataTypeScaleText.setVisible(false);
                dataTypeScaleText.setEnabled(false);
                dataTypeScaleText.setText("");
                rightBracket.setVisible(false);
            }
        }
    }

    private void fillDataTypeClauseCombo() {
        dataTypeClauseCombo.removeAll();

        if (domainModel.getVendor().isOracle()) {
            dataTypeClauseCombo.setItems(DataTypeHelper.oracleDataTypes);
        }
        else if (domainModel.getVendor().isMySQL()) {
            dataTypeClauseCombo.setItems(DataTypeHelper.mySQLDataTypes);
        }
        else if (domainModel.getVendor().isSybase()) {
            dataTypeClauseCombo.setItems(DataTypeHelper.sybaseDataTypes);
        }
        else if (domainModel.getVendor().isCloudscape()) {
            dataTypeClauseCombo.setItems(DataTypeHelper.cloudscapeDataTypes);
        }
        else // if (domainModel.getVendor().isDB2())
        {
            dataTypeClauseCombo.setItems(DataTypeHelper.db2DataTypes);
        }
    }

    private void updatePreview() {
        if (sqlCast != null) {            
        	previewExpressionText.setText(sqlCast.getSQL().trim());
        }
    }

    private void updateExpression() {
        String castClauseString = castClauseCombo.getText();

        if (castClauseString.equals("NULL")) {
            SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
            ValueExpressionNullValue nullExpr = factory.createValueExpressionNullValue();
            sqlCast.setValueExpr(nullExpr);
        }
        else if (castClauseString.equals("?")) {
            SQLQueryModelFactory factory = SQLQueryModelFactory.eINSTANCE;
            ValueExpressionVariable varExpr = factory.createValueExpressionVariable();
            varExpr.setName("?");
            sqlCast.setValueExpr(varExpr);
        }
        else {
            int index = castClauseCombo.getSelectionIndex();
            QueryValueExpression valExpr = (QueryValueExpression) castClauseComboHelper.getObjectAt(index);
            QueryValueExpression newValExpr = ExpressionHelper.createValueExpressionColumn((ValueExpressionColumn)valExpr);
            sqlCast.setValueExpr(newValExpr);
        }
        PredefinedDataType dataType = org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper.getPredefinedDataTypeForNamedType(dataTypeClauseCombo.getText());
        //following code sets the length of the cast datatype where applicable
        if (dataTypePrecisionText.getText().length() > 0) {
            int dataTypeLength = 0;
            int dataTypeScale = 0;
            try {
                dataTypeLength = Integer.parseInt(dataTypePrecisionText.getText());
            }
            catch (Exception exp) {
                dataTypeLength = 0;
            }
            if (dataTypeLength <= 0) {
                dataTypeLength = 0;
            }

            try {
                dataTypeScale = Integer.parseInt(dataTypeScaleText.getText());
            }
            catch (Exception exp) {
                dataTypeScale = 0;
            }
            if (dataTypeScale <= 0) {
                dataTypeScale = 0;
            }
            if (dataTypeLength > 0 || dataTypeScale > 0) {
                if (dataType instanceof BinaryStringDataType) {
                    ((BinaryStringDataType) dataType).setLength(dataTypeLength);
                }
                else if (dataType instanceof CharacterStringDataType) {
                    ((CharacterStringDataType) dataType).setLength(dataTypeLength);
                }
                else if (dataType instanceof FixedPrecisionDataType && (dataTypeClauseCombo.getText().equals("DECIMAL") ||
                		dataTypeClauseCombo.getText().equals("NUMERIC"))) {
                    ((FixedPrecisionDataType) dataType).setPrecision(dataTypeLength);
                    ((FixedPrecisionDataType) dataType).setScale(dataTypeScale);
                }
            }
        }
        sqlCast.setDataType(dataType);
    }

    public boolean currentPage() {
        return isCurrentPage();
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        updateExpression();
        updatePreview();
    }

}