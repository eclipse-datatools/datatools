/*******************************************************************************
 * Copyright © 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.OmitCurrentSchemaDialog;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
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
    		IOmitSchemaInfo omitSchemaInfo = getSQLBuilder().getDomainModel().getOmitSchemaInfo();
    		String userName = getSQLBuilder().getDomainModel().getUserName();
            IOmitSchemaInfo tmpOmitSchemaInfo = new OmitSchemaInfo();
            tmpOmitSchemaInfo.copy(omitSchemaInfo);
            OmitCurrentSchemaDialog dialog = new OmitCurrentSchemaDialog(getShell(), tmpOmitSchemaInfo, userName);

            dialog.create();

            dialog.setBlockOnOpen(true);
            int value = dialog.open();
            if (value == Window.CANCEL){
            	return;
            }
            else {
            	// Test if omitSchemaInfo has changed
            	boolean hasChanged = ! omitSchemaInfo.equals(tmpOmitSchemaInfo);
            	// Copy tmpOmitSchemaInfo back to omitSchemaInfo
            	omitSchemaInfo.copy(tmpOmitSchemaInfo);
            	// Notify observers
            	if (hasChanged){
            		omitSchemaInfo.setChanged();
            	}
            }
    	}
    }
}
