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

package org.eclipse.datatools.sqltools.sqlbuilder.views.delete;

import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.criteria.CriteriaGridViewer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class DeleteViewer extends ContentViewer {

    private SQLDomainModel sqlDomainModel;
    CriteriaGridViewer criteriaView;

    public DeleteViewer(SQLDomainModel model) {
        sqlDomainModel = model;
    }

    public void setInput(Object input) {
        criteriaView.setInput(input);
    }

    public Control getControl() {
        return canvas;
    }

    Composite canvas;

    public Control createControl(Composite parent) {
        canvas = new Composite(parent, SWT.NULL);

        criteriaView = new CriteriaGridViewer(SWT.FULL_SELECTION | SWT.HIDE_SELECTION, sqlDomainModel, canvas, false);
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
        criteriaView.setEnabled(enable);
    }
}// DeleteViewer
