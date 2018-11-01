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

package org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BaseWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;


public class ValuesViewer extends BaseWindow {

    SQLDomainModel domain;

    Object element;

    public ValuesViewer(SQLDomainModel domain) {
        super(domain);
    }

    public void handleEvent(Event e) {
    }

    public Control createControl(Composite c) {
        client = ViewUtility.createComposite(c, 1);

        Label helpText = new Label(client, SWT.HORIZONTAL | SWT.WRAP);
        helpText.setText(Messages._UI_LABEL_FULL_SELECT_VALUES_HELP);

        return client;
    }
}
