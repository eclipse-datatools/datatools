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

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.OmitCurrentSchemaDialog;
import org.eclipse.jface.window.Window;

public class OmitCurrentSchemaAction extends SQLBuilderAction  {

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public OmitCurrentSchemaAction() {
        super(Messages._UI_ACTION_OMIT_CURRENT_SCHEMA);
    }
   
    public void run() {
    	if (getSQLBuilder() != null){
    		OmitSchemaInfo omitSchemaInfo = getSQLBuilder().getDomainModel().getOmitSchemaInfo();
    		String userName = getSQLBuilder().getDomainModel().getUserName();
            OmitSchemaInfo tmpOmitSchemaInfo = new OmitSchemaInfo();
            tmpOmitSchemaInfo.copyOmitSchemaInfo(omitSchemaInfo);
            OmitCurrentSchemaDialog dialog = new OmitCurrentSchemaDialog(getShell(), tmpOmitSchemaInfo, userName);

            dialog.create();

            dialog.setBlockOnOpen(true);
            int value = dialog.open();
            if (value == Window.CANCEL){
            	return;
            }
            else {
            	omitSchemaInfo.copyOmitSchemaInfo(tmpOmitSchemaInfo);
            }
    	}
    }
}
