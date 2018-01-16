/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.generic.ui;

import org.eclipse.datatools.sqltools.core.services.ActionService;
import org.eclipse.datatools.sqltools.core.services.SQLEditorUIService;
import org.eclipse.datatools.sqltools.db.generic.ui.service.GenericActionService;
import org.eclipse.datatools.sqltools.db.generic.ui.service.GenericSQLEditorUIService;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;

public class GenericDBUIConfiguation extends SQLDevToolsUIConfiguration 
{
    /**
     * Returns the Action service
     * 
     */
    public ActionService getActionService() {
        return new GenericActionService();
    }
    
	/**
	 * Returns the SQL Editor service associated with this database definition
	 * 
	 */
	public SQLEditorUIService getSQLEditorUIService() {
		return new GenericSQLEditorUIService();
	}
    
}
