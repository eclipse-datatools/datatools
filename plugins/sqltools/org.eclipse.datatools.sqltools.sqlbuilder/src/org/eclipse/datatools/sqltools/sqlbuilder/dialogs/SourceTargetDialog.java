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

package org.eclipse.datatools.sqltools.sqlbuilder.dialogs;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.JoinHelper;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


/**
 * Dialog to help define the source/target column
 * for a join. This might be replaced once we know how to
 * initiate a drag tracker
 */

public class SourceTargetDialog extends Dialog {

    Combo srcColumnField;
    Vector allSrcColumn = new Vector();
    Combo srcTableField;
    Combo tgtTableField;
    Vector tableCache = new Vector();
    Combo tgtColumnField;
    Vector allTgtColumn = new Vector();
    ValueExpressionColumn sourceColumn;
    TableExpression targetTable;
    ValueExpressionColumn targetColumn;
    TableExpression sourceTable;
    QuerySelect querySelect;
    String dialogInstr = "";
    ComboBoxModifyListener comboModifyListener;
    DefineTypeComposite defineTypeComposite;
    SQLDomainModel domainModel;
    int joinType = TableJoinedOperator.DEFAULT_INNER;

    public SourceTargetDialog(Shell shell, TableExpression srcTable, 
            QuerySelect qSelect, SQLDomainModel domainModel) {
        super(shell);
        dialogInstr = Messages._UI_DIALOG_SOURCE_AND_TARGET_TEXT;
        comboModifyListener = new ComboBoxModifyListener();
        sourceTable = srcTable;
        querySelect = qSelect;
        this.domainModel = domainModel;
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages._UI_DIALOG_SOURCE_AND_TARGET_TITLE);
    }

    /**
     * Populate Source & Target Table combo boxes
     */
    public void initializeTableCombos() {
        // TableExpression src = sourceTable.getReferencedTable();
        populateWithTableCoulmns(sourceTable, srcColumnField, allSrcColumn);        

        List tableList = StatementHelper.getTableExpressionsInQuerySelect(querySelect);
        Iterator tableItr = tableList.iterator();
        while (tableItr.hasNext()) {
            TableExpression table = (TableExpression) tableItr.next();
            String tableName = table.getName(); //TODO deal with alias
            tableCache.addElement(table);
            /*      String alias = sqlCor.getName();
             if (alias != null && !alias.equals(""))
             {
             alias = " (" + alias + ")";
             }
             String tableName = sqlCor.getReferencedTable().getName() + alias;
             */srcTableField.add(tableName);
            tgtTableField.add(tableName);
        }

        if (allSrcColumn.size() > 0) {
            srcColumnField.select(0);
        }
        srcTableField.select(srcTableField.indexOf(sourceTable.getName()));
        tgtTableField.select(0);

        TableExpression tableExpr = (TableExpression) tableCache.elementAt(0);
        populateWithTableCoulmns(tableExpr, tgtColumnField, allTgtColumn);
        setTypes();
    }

    protected void buttonPressed(int buttonId) {
        if (buttonId == Dialog.OK) {
            joinType = defineTypeComposite.getJoinKind();
            int index = tgtTableField.getSelectionIndex();
            if (index < 0) {
                targetTable = null;
            }
            else {
                targetTable = (TableExpression) tableCache.elementAt(index);
            }

            index = srcTableField.getSelectionIndex();
            if (index < 0) {
                sourceTable = null;
            }
            else {
                sourceTable = (TableExpression) tableCache.elementAt(index);
            }

            index = srcColumnField.getSelectionIndex();
            if (index < 0) {
                sourceColumn = null;
            }
            else {
                sourceColumn = (ValueExpressionColumn) allSrcColumn.get(index);
            }

            index = tgtColumnField.getSelectionIndex();
            if (index < 0) {
                targetColumn = null;
            }
            else {
                targetColumn = (ValueExpressionColumn) allTgtColumn.get(index);
            }
            
            List fromContent = querySelect.getFromClause();
            int status = JoinHelper.checkJoin(fromContent, sourceTable, targetTable, 
                    sourceColumn, targetColumn, false);
            if (status != JoinHelper.JOIN_VALID) {
                MessageBox message = new MessageBox(getShell(), SWT.OK | SWT.ICON_ERROR);
                message.setText(Messages._UI_MSG_INVALID_JOIN_TITLE);
                String lineSeparator = System.getProperties().getProperty("line.separator");
                String msgStr = Messages._WARN_INVALID_JOIN;
                if (status == JoinHelper.JOIN_COL_TYPE_MISMATCH) {
                    msgStr = Messages._WARN_BAD_JOIN_1;
                    msgStr += lineSeparator;
                    msgStr += NLS.bind(Messages._WARN_BAD_JOIN_TYPES, sourceColumn, sourceColumn.getDataType().getName());
                    msgStr += lineSeparator;
                    msgStr += NLS.bind(Messages._WARN_BAD_JOIN_TYPES, targetColumn, targetColumn.getDataType().getName());
                }
                else if (status == JoinHelper.JOIN_COL_USED) {
                    msgStr = Messages._WARN_COLUMN_USED;
                }
                message.setMessage(msgStr);
                message.open();
                return;
            }
        }
        super.buttonPressed(buttonId);
    }

    //
    // Get the target table for the join
    //
    public TableExpression getTargetTable() {
        return targetTable;
    }

    //
    // Get the source table for the join
    //
    public TableExpression getSourceTable() {
        return sourceTable;
    }

    //
    // Get the source column
    //
    public ValueExpressionColumn getSourceColumn() {
        return sourceColumn;
    }

    //
    // Get the target column
    //
    public ValueExpressionColumn getTargetColumn() {
        return targetColumn;
    }

    // Get the type for the join
    public int getJoinType() {
        return joinType;
    }

    //
    // Create the controls
    //
    Label srcType;

    Label targetType;

    public Control createDialogArea(Composite parent) {
        Composite client = (Composite) super.createDialogArea(parent);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(client, SQLBuilderContextIds.SQLG_CREATE_JOIN_DIALOG);
        GridLayout layout = (GridLayout) client.getLayout();
        layout.numColumns = 1;
        layout.makeColumnsEqualWidth = true;
        client.setLayout(layout);
        GridData gData = (GridData) client.getLayoutData();
        gData.grabExcessHorizontalSpace = true;
        gData.grabExcessVerticalSpace = true;
        client.setLayoutData(gData);

        Group grp1 = ViewUtility.createGroup(client, 2, 
                Messages._UI_GROUP_SOURCE, false);
        ViewUtility.createLabel(grp1, 
                Messages._UI_LABEL_TARGET_TABLE);
        srcTableField = ViewUtility.createComboBox(grp1);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(srcTableField, 
                SQLBuilderContextIds.SQLG_CREATE_JOIN_DIALOG);

        ViewUtility.createLabel(grp1, 
                Messages._UI_LABEL_COLUMN);
        srcColumnField = ViewUtility.createComboBox(grp1);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(srcColumnField, 
                SQLBuilderContextIds.SQLG_CREATE_JOIN_DIALOG);

        ViewUtility.createLabel(grp1, 
                Messages._UI_LABEL_JOIN_SRCCOL_TYPE);
        srcType = ViewUtility.createLabel(grp1, "");

        Group grp2 = ViewUtility.createGroup(client, 2, 
                Messages._UI_GROUP_TARGET, false);
        ViewUtility.createLabel(grp2, Messages._UI_LABEL_TABLE);
        tgtTableField = ViewUtility.createComboBox(grp2);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(tgtTableField, SQLBuilderContextIds.SQLG_CREATE_JOIN_DIALOG);

        ViewUtility.createLabel(grp2, Messages._UI_LABEL_TARGET_COLUMN);
        tgtColumnField = ViewUtility.createComboBox(grp2);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(tgtColumnField, SQLBuilderContextIds.SQLG_CREATE_JOIN_DIALOG);

        ViewUtility.createLabel(grp2, Messages._UI_LABEL_JOIN_TGTCOL_TYPE);
        targetType = ViewUtility.createLabel(grp2, "");

        defineTypeComposite = new DefineTypeComposite(client, domainModel, 
                TableJoinedOperator.DEFAULT_INNER);
        Composite typeComposite = defineTypeComposite.getControl();
        GridData typeGridData = (GridData) typeComposite.getLayoutData();
        typeGridData.grabExcessHorizontalSpace = true;
        typeGridData.grabExcessVerticalSpace = true;
        typeComposite.setLayoutData(typeGridData);

        initializeTableCombos();

        srcTableField.forceFocus();

        srcColumnField.addModifyListener(comboModifyListener);
        srcTableField.addModifyListener(comboModifyListener);
        tgtColumnField.addModifyListener(comboModifyListener);
        tgtTableField.addModifyListener(comboModifyListener);

        return client;
    }

    public void setTypes() {
        ValueExpressionColumn srcColumn, tgtColumn;
        DataType type;
        int index = srcColumnField.getSelectionIndex();
        if (index < 0) {
            srcColumn = null;
            srcType.setText("");
        }
        else {
            srcColumn = (ValueExpressionColumn) allSrcColumn.get(index);
            type = srcColumn.getDataType();
            if (type != null) {
                srcType.setText(type.getName());
            }
        }

        index = tgtColumnField.getSelectionIndex();
        if (index < 0) {
            tgtColumn = null;
            targetType.setText("");
        }
        else {
            tgtColumn = (ValueExpressionColumn) allTgtColumn.get(index);
            type = tgtColumn.getDataType();
            if (type != null) {
                targetType.setText(type.getName());
            }
        }
    }

    void populateWithTableCoulmns(TableExpression table, Combo combo, Vector colVector) {
        List colList = table.getColumnList();
        Iterator itr = colList.iterator();
        combo.removeAll();
        colVector.clear();
        ValueExpressionColumn col;
        while (itr.hasNext()) {
            col = (ValueExpressionColumn) itr.next();
            colVector.add(col);
            combo.add(col.getName());
        }
        combo.select(0);
    }
    
    //
    // Populate the target column list based on target table choice
    // We use a ModifyListener instead of a SelectionListener because of a LINUX, GTK problem
    // looping in the widgetSelected()/handleEvent() code..... defect: 271098
    private class ComboBoxModifyListener implements ModifyListener {

        public void modifyText(ModifyEvent e) {
            // Linux special case....  The first time we come in here, the selection index could be
            // -1 (which means no selection/text).  This is because in Linux, there are two events
            // when changing a selection.  One event occurs because of the removal of the old
            // selection/text.  And another event of selecting/putting the text.
            // We are interested in the second event.
            if (srcTableField.getSelectionIndex() >= 0 && tgtTableField.getSelectionIndex() >= 0) {
                srcColumnField.removeModifyListener(comboModifyListener);
                srcTableField.removeModifyListener(comboModifyListener);
                tgtColumnField.removeModifyListener(comboModifyListener);
                tgtTableField.removeModifyListener(comboModifyListener);

                int index;

                if (e.widget == tgtTableField || e.widget == srcColumnField) {
                    index = tgtTableField.getSelectionIndex();
                    if (index < 0)
                        return;

                    if (e.widget == tgtTableField) {
                        TableExpression tableExpr = (TableExpression) tableCache.elementAt(index);
                        populateWithTableCoulmns(tableExpr, tgtColumnField, allTgtColumn);
                    }

                    // make sure that we reset the selection in the combo
                    if (sourceTable == targetTable) {
                        tgtColumnField.select(srcColumnField.getSelectionIndex());
                    }
                }
                else if (e.widget == srcTableField || e.widget == tgtColumnField) {
                    index = srcTableField.getSelectionIndex();
                    if (index < 0)
                        return;

                    if (e.widget == srcTableField) {
                        TableExpression tableExpr = (TableExpression) tableCache.elementAt(index);
                        populateWithTableCoulmns(tableExpr, srcColumnField, allSrcColumn);
                    }

                    // make sure that we reset the selection in the combo
                    if (sourceTable == targetTable) {
                        srcColumnField.select(tgtColumnField.getSelectionIndex());
                    }
                }
                setTypes();

                srcColumnField.addModifyListener(comboModifyListener);
                srcTableField.addModifyListener(comboModifyListener);
                tgtColumnField.addModifyListener(comboModifyListener);
                tgtTableField.addModifyListener(comboModifyListener);
            }
        }
    }
}
