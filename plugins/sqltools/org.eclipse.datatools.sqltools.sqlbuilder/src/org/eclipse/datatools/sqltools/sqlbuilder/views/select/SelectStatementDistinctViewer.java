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

package org.eclipse.datatools.sqltools.sqlbuilder.views.select;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;


/**
 * Create the viewer for DISTINCT checkbox
 */
public class SelectStatementDistinctViewer extends ContentViewer implements SelectionListener {

    protected QueryStatement currentStatement;
    protected SQLDomainModel domainModel;
    protected Composite mainUIComponent;

    private Button checkBox;

    public SelectStatementDistinctViewer(SQLDomainModel sqlDomainModel) {
        domainModel = sqlDomainModel;
        currentStatement = sqlDomainModel.getSQLStatement();
        setContentProvider(sqlDomainModel.createContentProvider());
    }

    public void setInput(Object input) {
        setDistinct(input);
        if (input instanceof QueryStatement) {
            currentStatement = (QueryStatement) input;
        }
        
        super.setInput(input);
    }

    public void refresh() {
        setDistinct(getInput());
    }

    public void setDistinct(Object input) {
        if (input instanceof QuerySelectStatement || input instanceof QuerySelect) {
            QuerySelect qSelect = null;
            if (input instanceof QuerySelectStatement) {
                QuerySelectStatement select = (QuerySelectStatement) input;
                qSelect = SelectHelper.getQuerySelect(select);
            }
            else {
                qSelect = (QuerySelect) input;
            }
            if (qSelect != null && qSelect.isDistinct() == true) {
                checkBox.setSelection(true);
            }
            else {
                checkBox.setSelection(false);
            }
        }
    }

    public Control createControl(Composite parent) {
        // Create the main UI container
        mainUIComponent = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();

        layout.marginHeight = 5;
        layout.marginWidth = 5;
        layout.horizontalSpacing = 5;
        layout.verticalSpacing = 5;

        layout.numColumns = 3;
        mainUIComponent.setLayout(layout);

        mainUIComponent.setLayoutData(ViewUtility.createHorizontalFill());

        checkBox = ViewUtility.createCheckBox(mainUIComponent, "DISTINCT"); //$NON-NLS-1$
        checkBox.addSelectionListener(this);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(checkBox, SQLBuilderContextIds.SQLB_SELECT_VIEW);

        hookControl(getControl());
        return getControl();
    }

    public Control getControl() {
        return mainUIComponent;
    }

    public void widgetSelected(SelectionEvent event) {
        if (event.widget != null && event.widget == checkBox) {
            QuerySelect qSelect = SelectHelper.getQuerySelect((QuerySelectStatement) currentStatement);
            if (qSelect != null) {
                qSelect.setDistinct(checkBox.getSelection());
                SelectHelper.refresh((QuerySelectStatement) currentStatement);
            }
        }
    }

    public void widgetDefaultSelected(SelectionEvent event) {
    }

	public void setEnabled(boolean enable) {
		checkBox.setEnabled(enable);
	}
    
    public ISelection getSelection() {
        return null;
    }

    public void setSelection(ISelection selection, boolean reveal) {
    }
    
}