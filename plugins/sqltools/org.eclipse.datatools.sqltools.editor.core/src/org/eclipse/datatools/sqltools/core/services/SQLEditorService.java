/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/

package org.eclipse.datatools.sqltools.core.services;

import org.eclipse.datatools.sqltools.editor.contentassist.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.launching.IExtendedLaunchSupport;

/**
 * A SQL Editor related service specific to a database definition. This interface allows clients to customize the
 * default behavior of the SQL Editor.
 * 
 * @author Hui Cao
 * 
 */
public class SQLEditorService
{

    /**
     * Returns a <code>IExtendedLaunchSupport</code> object which is used to execute additional operations
     * before/after launching
     * 
     * @return a <code>IExtendedLaunchSupport</code> object to customize the launching behavior.
     */
    public IExtendedLaunchSupport getExtendedLaunchSupport()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns a <code>ISQLDBProposalsService</code> object which is used to execute operations to load db proposals
     * 
     * @param connInfo
     * @return a <code>ISQLDBProposalsService</code> object to perform the DB proposals loading behavior.
     */
    public ISQLDBProposalsService getSQLDBProposalsService(ISQLEditorConnectionInfo connInfo)
    {
        return null;
    }
}
