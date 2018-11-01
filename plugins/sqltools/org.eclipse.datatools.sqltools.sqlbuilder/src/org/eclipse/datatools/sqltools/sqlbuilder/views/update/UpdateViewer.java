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

package org.eclipse.datatools.sqltools.sqlbuilder.views.update;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
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


public class UpdateViewer extends ContentViewer {

    private SQLDomainModel sqlDomainModel;
    UpdateDesignViewer updateDesignViewer;
    CriteriaGridViewer criteriaView;

    public UpdateViewer(SQLDomainModel model) {
        sqlDomainModel = model;
        setContentProvider(model.createContentProvider());
    }

    public void setInput(Object input) {
        updateDesignViewer.setInput(input);
        criteriaView.setInput(input);
        super.setInput(input);
    }

    public Control getControl() {
        return canvas;
    }

    public void refresh() {
    }

    Composite canvas;

    public Control createControl(Composite parent) {
        canvas = new Composite(parent, SWT.NULL);

        Workbook workbook = new Workbook(canvas);

        updateDesignViewer = new UpdateDesignViewer(sqlDomainModel);
        updateDesignViewer.createControl(workbook.getClientComposite());
        updateDesignViewer.getControl().setLayoutData(ViewUtility.createFill());
        workbook.addPage(updateDesignViewer.getControl(), Messages._UI_WORKBOOK_SET, null, null);

        criteriaView = new CriteriaGridViewer(SWT.FULL_SELECTION | SWT.HIDE_SELECTION, sqlDomainModel, workbook.getClientComposite(), false);
        criteriaView.getTable().setLayoutData(ViewUtility.createFill());
        workbook.addPage(criteriaView.getControl(), Messages._UI_WORKBOOK_WHERE, null, null);

        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        canvas.setLayout(layout);
        hookControl(canvas);
        return getControl();
    }

    public ISelection getSelection() {
        return null;
    }

    public void setSelection(ISelection selection, boolean reveal) {
    }

    public void setEnabled(boolean enable) {
        criteriaView.setEnabled(enable);
        updateDesignViewer.setEnabled(enable);
    }
}