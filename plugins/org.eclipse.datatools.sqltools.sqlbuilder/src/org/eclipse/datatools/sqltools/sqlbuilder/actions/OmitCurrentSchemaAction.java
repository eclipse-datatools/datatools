/*******************************************************************************
 * Copyright © 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.OmitCurrentSchemaDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

public class OmitCurrentSchemaAction extends Action {

	SQLDomainModel _domainModel;
    SQLBuilderOmitSchemaInfo _omitSchemaInfo;

    public OmitCurrentSchemaAction(SQLDomainModel domainModel) {
        super(org.eclipse.datatools.sqltools.sqlbuilder.Messages._UI_ACTION_OMIT_CURRENT_SCHEMA);
        _domainModel = domainModel;
        _omitSchemaInfo = domainModel.getOmitSchemaInfo();
    }

    Shell getShell() {
        return org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    public void run() {

    	SQLBuilderOmitSchemaInfo tmpOmitSchemaInfo = new SQLBuilderOmitSchemaInfo();
    	tmpOmitSchemaInfo.copyOmitSchemaInfo(_omitSchemaInfo);
        OmitCurrentSchemaDialog dialog = new OmitCurrentSchemaDialog(getShell(), tmpOmitSchemaInfo, _domainModel.getUserName());

        dialog.create();

        dialog.setBlockOnOpen(true);
        int value = dialog.open();
        if (value == Window.CANCEL){
            return;
        }
        else {
        	_omitSchemaInfo.copyOmitSchemaInfo(tmpOmitSchemaInfo);
        }
    }
}
