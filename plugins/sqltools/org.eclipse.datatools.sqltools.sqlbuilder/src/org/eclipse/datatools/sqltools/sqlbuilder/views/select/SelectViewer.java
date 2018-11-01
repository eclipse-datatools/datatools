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

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.Workbook;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaGridViewer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;


public class SelectViewer extends ContentViewer {

    SQLDomainModel sqlDomainModel;
    SelectStatementDistinctViewer selectStatementDistinctViewer;
    SelectGridViewer selectGridViewer;
    CriteriaGridViewer criteriaView;
    CriteriaGridViewer havingView;
    GroupByViewer groupByViewer;
    Composite canvas;

    public SelectViewer(SQLDomainModel model) {
        sqlDomainModel = model;
    }

    public void setInput(Object input) {
        selectStatementDistinctViewer.setInput(input);
        selectGridViewer.setInput(input);
        criteriaView.setInput(input);
        havingView.setInput(input);
        groupByViewer.setInput(input);
    }

    public Control getControl() {
        return canvas;
    }

    public Control createControl(Composite parent) {
        canvas = new Composite(parent, SWT.NULL);

        selectStatementDistinctViewer = new SelectStatementDistinctViewer(sqlDomainModel);
        selectStatementDistinctViewer.createControl(canvas);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(canvas, SQLBuilderContextIds.SQLB_SELECT_VIEW);
        Workbook workbook = new Workbook(canvas);

        selectGridViewer = new SelectGridViewer(sqlDomainModel, workbook.getClientComposite());
        workbook.addPage(selectGridViewer.getControl(), Messages._UI_WORKBOOKPAGE_COLUMNS, null, null); 

        selectGridViewer.getTable().setLinesVisible(true);
        selectGridViewer.getTable().setLayoutData(ViewUtility.createFill());

        criteriaView = new CriteriaGridViewer(SWT.FULL_SELECTION | SWT.HIDE_SELECTION, sqlDomainModel, workbook.getClientComposite(), false);
        workbook.addPage(criteriaView.getControl(), Messages._UI_WORKBOOKPAGE_CONDITIONS, null, null);

        criteriaView.getTable().setLayoutData(ViewUtility.createFill());
        groupByViewer = new GroupByViewer(sqlDomainModel);
        groupByViewer.createControl(workbook.getClientComposite());

        workbook.addPage(groupByViewer.getControl(), Messages._UI_WORKBOOKPAGE_GROUPS, null, null); 
        groupByViewer.getControl().setLayoutData(ViewUtility.createFill());

        havingView = new CriteriaGridViewer(SWT.FULL_SELECTION | SWT.HIDE_SELECTION, sqlDomainModel, workbook.getClientComposite(), true);
        workbook.addPage(havingView.getControl(), Messages._UI_WORKBOOKPAGE_GROUP_CONDITIONS, null, null); 

        criteriaView.getTable().setLayoutData(ViewUtility.createFill());

        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        canvas.setLayout(layout);
        return getControl();
    }

    public void refresh() {

    }

    public ISelection getSelection() {
        return null;
    }

    public void setSelection(ISelection selection, boolean reveal) {
    }

    public void setEnabled(boolean enable) {
        selectStatementDistinctViewer.setEnabled(enable);
        selectGridViewer.setEnabled(enable);
        criteriaView.setEnabled(enable);
        havingView.setEnabled(enable);
        groupByViewer.setEnabled(enable);
    }
}