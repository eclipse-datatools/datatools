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
package org.eclipse.datatools.sqltools.sqlbuilder.views.select;

import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class GroupByViewer extends ContentViewer {

    private SQLDomainModel sqlDomainModel;

    public GroupByViewer(SQLDomainModel model) {
        sqlDomainModel = model;
    }

    public void setInput(Object input) {
        groupByContentViewer.setInput(input);
    }

    public Control getControl() {
        return canvas;
    }

    protected Composite canvas;

    protected GroupByContentViewer groupByContentViewer;

    public Control createControl(Composite parent) {
        canvas = new Composite(parent, SWT.NULL);

        groupByContentViewer = new GroupByContentViewer(sqlDomainModel);
        groupByContentViewer.createControl(canvas);
        groupByContentViewer.getControl().setLayoutData(ViewUtility.createFill());

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
        groupByContentViewer.setEnabled(enable);

    }
}
