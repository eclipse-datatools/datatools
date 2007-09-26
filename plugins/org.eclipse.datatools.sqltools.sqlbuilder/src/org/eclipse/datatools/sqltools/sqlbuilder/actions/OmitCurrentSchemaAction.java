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
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.OmitCurrentSchemaDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

public class OmitCurrentSchemaAction extends Action {

    Object object;
    SQLDomainModel domainModel;

    public OmitCurrentSchemaAction(SQLDomainModel domainModel) {
        super(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_OMIT_CURRENT_SCHEMA);
        this.domainModel = domainModel;
    }

    public void setStatement(Object obj) {
        object = obj;

        if (object instanceof QueryInsertStatement) {
        }
        else if (object instanceof QueryUpdateStatement) {
        }
        else if (object instanceof QueryDeleteStatement) {
        }

    }

    Shell getShell() {
        return org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    public void run() {

        OmitCurrentSchemaDialog dialog = new OmitCurrentSchemaDialog(getShell(), domainModel, object);

        dialog.create();

        dialog.setBlockOnOpen(true);
        int value = dialog.open();
        if (value == Window.CANCEL)
            return;
    }
}
