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
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;


abstract public class BaseWindow extends Viewer implements Listener {

    SQLDomainModel domain;
    Object element;

    public BaseWindow(SQLDomainModel domain) {
        this.domain = domain;
    }

    protected Composite client;

    /**
     * Framework override
     */
    public Control getControl() {
        return client;
    }

    public SQLDomainModel getDomainModel() {
        return domain;
    }

    protected Object input;

    public Object getInput() {
        return input;
    }

    public void setInput(Object input) {
        this.input = input;
    }

    public ISelection getSelection() {
        return null;
    }

    public void setSelection(ISelection selection, boolean reveal) {
    }

    public void refresh() {
    }

}
