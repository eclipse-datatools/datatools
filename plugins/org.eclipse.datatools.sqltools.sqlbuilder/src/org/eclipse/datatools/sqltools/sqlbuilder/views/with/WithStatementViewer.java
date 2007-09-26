/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.views.with;

import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BaseWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.help.WorkbenchHelp;


/**
 * SQLWithStatement viewer
 */
public class WithStatementViewer extends BaseWindow {

    Text nameField;
    int fieldWidth = 60;
    int mleHeight = 60;
    QuerySelectStatement withStatement;

    public WithStatementViewer(SQLDomainModel domain) {
        super(domain);
    }

    public Control createControl(Composite parent) {
        client = ViewUtility.createComposite(parent, 1);

        ViewUtility.createLabel(client, Messages._UI_LABEL_WITH_STATEMENT);
        nameField = new Text(client, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
        WorkbenchHelp.setHelp(nameField, SQLBuilderContextIds.SQDW_STMT_NAME_FIELD);
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.widthHint = fieldWidth;
        nameField.setLayoutData(data);

        return client;
    }

    public void setInput(Object element) {
        super.setInput(element);

        if (element instanceof QuerySelectStatement) {
            withStatement = (QuerySelectStatement) element;
            initializeFields();
        }
    }

    private void initializeFields() {
        String name = withStatement.getName();
        nameField.setText((name == null) ? "" : name);
    }

    public void handleEvent(Event event) {
    }

    public void setEnabled(boolean enable) {
        //it appears to be working even without doing anything here.
    }
}