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

import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.ChangeStatementTypeDialog;
import org.eclipse.jface.window.Window;

/**
 * Action for changing the SQL Statement type, e.g. from SELECT to INSERT.
 * 
 * @author jeremyl
 *
 */
public class ChangeStatementTypeAction extends SQLBuilderAction  {

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public ChangeStatementTypeAction() {
        super(Messages._UI_ACTION_CHANGE_STATEMENT_TYPE);
    }
   
    public void run() {
    	if (getSQLBuilder() != null){
    		int currentStatementType = StatementHelper.getStatementType(getSQLBuilder().getDomainModel().getSQLStatement());
    		ChangeStatementTypeDialog dialog = 
    			new ChangeStatementTypeDialog(getShell(), currentStatementType);

            dialog.create();

            dialog.setBlockOnOpen(true);
            int value = dialog.open();
            if (value == Window.CANCEL){
            	return;
            }
            else {
            	// Test if statement type has changed and change it in SQLBuilder if it has
            	int newStatementType = dialog.getStatementType();
            	if (currentStatementType != newStatementType){
            		getSQLBuilder().changeStatementType(newStatementType);
            	}
            }
    	}
    }
}
