/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.debugger.core;

import java.sql.Connection;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugTarget;
import org.eclipse.datatools.sqltools.debugger.model.SPThread;
import org.eclipse.debug.ui.IDebugModelPresentation;

/**
 * Database plugins should implement this interface to provide database-specific
 * features.
 * 
 * @author Hui Cao
 *  
 */

public interface SQLDebuggerConfiguration  
{

    /**
     * 
     * Returns a <code>SPThread</code> used to debug stored procedures.
     * 
     * @param target the debug target in which this thread is contained
     * @param databaseIdentifier on which database this thread is running 
     * @param connectionId the database specific connection id @see org.eclipse.datatools.sqltools.core.services.ConnectionService#getConnectionId(org.eclipse.datatools.sqltools.core.DatabaseIdentifier, java.sql.Connection)
     * @param debuggeeCon the connection to be debugged
     * @return
     * @throws Exception
     */
    public SPThread getSPThread(SPDebugTarget target, DatabaseIdentifier databaseIdentifier, String id, Connection debuggeeCon) throws Exception;
    
    /**
     * 
     * @param profileName
     * @return
     */
    public IDebugHandler createDebugHandler(String profileName);
    
    /**
     * Gets the DebugModelPresentation
     * @return
     */
    public IDebugModelPresentation getDebugModelPresentation();
}
