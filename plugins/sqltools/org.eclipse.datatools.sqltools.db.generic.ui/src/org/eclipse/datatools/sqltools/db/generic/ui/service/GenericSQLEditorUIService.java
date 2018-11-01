/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.db.generic.ui.service;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.datatools.sqltools.core.services.SQLEditorUIService;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;


/**
 * @author Hui Cao
 *
 */
public class GenericSQLEditorUIService extends SQLEditorUIService
{
    private ArrayList              exc       = null;

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.IDBFactory#getExcludedActionIds()
     */
    public Collection getExcludedActionIds()
    {
        if (exc == null)
        {
            exc = new ArrayList();
            exc.add(ISQLEditorActionConstants.DEBUG_ACTION_ID);
            exc.add(ISQLEditorActionConstants.GROUP_INSERT_QUERY_ID);
            exc.add(ISQLEditorActionConstants.GROUP_NEW_ROUTINE_ID);
        }
        return exc;
    }


}
