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

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BaseWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;


/** 
 * Full Select Statement Design Pane
 */
public class FullSelectViewer extends BaseWindow {

    FullSelectGridViewer fullSelectGridViewer;
    FullSelectOrderViewer fullSelectOrderViewer;

    QueryExpressionBody queryExprBody;
    SQLDomainModel domainModel;

    public FullSelectViewer(SQLDomainModel domain) {
        super(domain);
        domainModel = domain;
    }

    public Control createControl(Composite parent) {
        client = ViewUtility.createComposite(parent, 1);

        //add a summary grid - two columns:  first shows Select/Values, second shows operator
        fullSelectGridViewer = new FullSelectGridViewer(domainModel, client);

        fullSelectOrderViewer = new FullSelectOrderViewer(domainModel, client);

        return client;
    }

    // use this flag so we know whether the widget event should cause a statement name update
    protected boolean inSetInput = false;

    public void setInput(Object element) {
        inSetInput = true;
        super.setInput(element);

        queryExprBody = (QueryExpressionBody) element;

        // notify full select viewer
        fullSelectGridViewer.setInput(queryExprBody);

        // notify order by viewer
        fullSelectOrderViewer.setInput(queryExprBody);

        inSetInput = false;
    }

    public void handleEvent(Event event) {
    }

    public void setEnabled(boolean enable) {
        fullSelectGridViewer.setEnabled(enable);
        fullSelectOrderViewer.setEnabled(enable);
    }
}
