/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.services;

import org.eclipse.datatools.sqltools.core.services.ActionService;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;

/**
 * ASA implementation of ActionService
 * 
 * @author Hui Wan
 */
public class ASAActionService extends ActionService
{
    public boolean supportsAction(String actionId)
    {
        if(actionId == ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID
                        || actionId == ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID
                        || actionId == ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID)
        {
            return false;
        }
        
        return true;
    }
}
