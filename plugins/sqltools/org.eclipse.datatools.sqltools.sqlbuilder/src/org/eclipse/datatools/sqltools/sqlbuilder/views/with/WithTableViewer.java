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

package org.eclipse.datatools.sqltools.sqlbuilder.views.with;

import java.util.Iterator;

import org.eclipse.datatools.modelbase.sql.query.ColumnName;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BaseWindow;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;


/**
 * SQLWithTable viewer
 */
public class WithTableViewer extends BaseWindow {

    Text tableName;
    Text columnName;

    Button add, remove;
    List columnList;

    QuerySelectStatement withStatement;
    WithTableSpecification withTable;

    int fieldWidth = 60;
    int mleHeight = 60;

    public WithTableViewer(SQLDomainModel domain) {
        super(domain);
    }

    public Control createControl(Composite parent) {
        client = ViewUtility.createComposite(parent, 1);

        Group tGrp = ViewUtility.createGroup(client, 1, Messages._UI_GROUP_WITH_TABLE, false);
        tableName = ViewUtility.createTextField(tGrp, fieldWidth);

        // Create the Column Group Box
        Group group = ViewUtility.createGroup(client, 3, Messages._UI_GROUP_WITH_COLUMN, true);

        columnName = ViewUtility.createTextField(group, fieldWidth);
        GridData data = (GridData) columnName.getLayoutData();
        data.verticalAlignment = GridData.BEGINNING;
        columnName.setData(data);

        Composite group2 = ViewUtility.createComposite(group, 1);
        add = ViewUtility.createPushButton(group2, Messages._UI_BUTTON_ADD_RIGHT);
        remove = ViewUtility.createPushButton(group2, Messages._UI_BUTTON_REMOVE_LEFT);

        columnList = new org.eclipse.swt.widgets.List(group, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.verticalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        columnList.setLayoutData(data);

        tableName.addListener(SWT.Modify, this);
        columnName.addListener(SWT.Modify, this);

        columnList.addListener(SWT.Selection, this);
        add.addListener(SWT.Selection, this);
        remove.addListener(SWT.Selection, this);

        return client;
    }

    public void setInput(Object element) {
        super.setInput(element);

        if (element instanceof WithTableSpecification) {
            withTable = (WithTableSpecification) element;
            withStatement = withTable.getQueryExpressionRoot().getSelectStatement();
            initializeFields();
        }
    }

    private void initializeFields() {
        // Initialize the with table name
        tableName.removeListener(SWT.Modify, this);
        if (withTable.getName() != null) {
            tableName.setText(withTable.getName());
        }
        else {
            tableName.setText("");
        }
        columnName.setText("");

        Iterator iterator = withTable.getColumnNameList().iterator();
        columnList.removeAll();
        while (iterator.hasNext()) {
            ColumnName cn = (ColumnName) iterator.next();
            columnList.add(cn.getName());
        }
        tableName.addListener(SWT.Modify, this);
    }

    public void handleEvent(Event event) {
        if (event.widget == add && columnName.getText().trim().length() > 0) {
            columnList.add(columnName.getText());
            ColumnName newCN = StatementHelper.createColumnName(columnName.getText());
            withTable.getColumnNameList().add(newCN);
            columnName.setText("");
        }

        if (event.widget == remove) {
            int index = columnList.getSelectionIndex();
            if (index < 0) {
                return;
            }

            EList columns = withTable.getColumnNameList();
            if (index < columns.size()) {
                columnList.remove(index);
                withTable.getColumnNameList().remove(index);
            }
        }

        if (event.widget == tableName) {
            withTable.setName(tableName.getText());
        }
        SelectHelper.refresh(withTable.getQueryExpressionRoot().getSelectStatement());
    }

}