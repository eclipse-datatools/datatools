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

package org.eclipse.datatools.sqltools.editor.core.connection;

import java.sql.Connection;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.debug.core.ILaunchConfiguration;


/**
 * As we are using connection pool and some configurations of the pooled connection may have been changed during last
 * session, we should reset them into the default status everytime a connection is retrieved from the pool.
 * 
 * @author Hui Cao
 *  
 */
public interface IConnectionInitializer
{
    /**
     * Uses the global options to initialize the connection
     * 
     */
    public void init(DatabaseIdentifier databaseIdentifier, Connection conn);

    /**
     * Uses the given launch configuration to initialize the connection. If the given launch configuration use
     * default(global) options in preference store, then will delegate to init(String profileName, Connection conn)
     * 
     * @param databaseIdentifier
     * @param conn the connection object
     * @param configuration
     */
    public void init(DatabaseIdentifier databaseIdentifier, Connection conn, ILaunchConfiguration configuration);

    /**
     * Reverts to database default options when put back the connection to the connection pool.
     * @param databaseIdentifier
     * @param conn the connection object
     */
    public void revert(DatabaseIdentifier databaseIdentifier, Connection conn);
}
