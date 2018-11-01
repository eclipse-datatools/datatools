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
package org.eclipse.datatools.sqltools.sqlbuilder.views.insert;

import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.DesignViewer;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class InsertViewer extends ContentViewer {

    private SQLDomainModel sqlDomainModel;
    InsertTypeViewer insertTypeViewer;
    DesignViewer designViewer;

    public InsertViewer(SQLDomainModel model) {
        sqlDomainModel = model;
        setContentProvider(sqlDomainModel.createContentProvider());
    }

    public void setInput(Object input) {
        insertTypeViewer.setInput(input);
        super.setInput(input);
    }

    public Control getControl() {
        return canvas;
    }

    public void refresh() {
        insertTypeViewer.setInput(getInput()); 
        
        // Work around solution for cases where user modifies a column and the value
        // does not show immediately [wsdbu00045516]
        if (designViewer != null) {
        	designViewer.forceRefresh();
        }
    }
    
    /**
     * Sets the design viewer
     * @param viewer the design viewer
     */
    public void setDesignViewer(DesignViewer viewer) {
    	designViewer = viewer;
    }

    Composite canvas;

    public Control createControl(Composite parent) {
        canvas = new Composite(parent, SWT.NULL);

        insertTypeViewer = new InsertTypeViewer(sqlDomainModel);
        insertTypeViewer.createControl(canvas);
        insertTypeViewer.getControl().setLayoutData(ViewUtility.createFill());

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
        insertTypeViewer.setEnabled(enable);
    }
}// SelectViewer
