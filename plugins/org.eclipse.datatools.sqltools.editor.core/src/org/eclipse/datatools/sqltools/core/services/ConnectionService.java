/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.core.services;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionInitializer;

/**
 * A connection related service specific to a database definition.
 * @author Hui Cao
 *
 */
public class ConnectionService 
{

    /**
     * Returns a <code>Runnable</code> which can be used to kill the connection. This <code>Runnable</code> instance
     * will be used to terminate a running statement. This is necessary because <code>jdbc.sql.Statement.cancel()</code>
     * and <code>jdbc.sql.Connection.close()</code> won¡¯t always be able to do the job.
     * 
     * @param databaseIdentifier uniquely identifies a database
     * @param conn the connection object
     * @return a <code>Runnable</code> which can be used to kill the connection.
     */
    public Runnable getConnectionKiller(DatabaseIdentifier databaseIdentifier, Connection conn)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns an identifier for the connection. This id should be unique across all clients.
     * 
     * @param databaseIdentifier uniquely identifies a database
     * @param conn the connection object
     * @return an identifier for the connection
     */
    public int getConnectionId(DatabaseIdentifier databaseIdentifier, Connection conn)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Returns an IControlConnection object which is used for all shared usage to that particular database.
     * 
     * @param databaseIdentifier uniquely identifies a database
     * @return an IControlConnection object which is used for all shared usage to that particular database.
     */
    public IControlConnection createControlConnection(DatabaseIdentifier databaseIdentifier) throws SQLException
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns a IConnectionInitializer object which is used to initialize connection to proper state. Clients may want
     * to let end user customize the connection status by providing preference pages. The extension point
     * preferenceSections is interesting to data server vendors if they want to contribute preference controls to an
     * existing DTP preference page.
     * 
     * @see org.eclipse.datatools.common.ui.preferences.AbstractDBPreferenceFieldPage
     * 
     * @return a IConnectionInitializer object which is used to initialize connection to proper state.
     */
    public IConnectionInitializer getConnectionInitializer()
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
	 * Creates a jdbc connection from the given connection profile name and
	 * database name. By default, the connection is retrieved from connetivity
	 * layer, but vendors may override this method to use different approaches.
	 * 
	 * @param profileName
	 * @param dbName
	 * @return
	 */
    public Connection createConnection(String profileName, String dbName)
    {
    	return ProfileUtil.createConnection(profileName, dbName);
    }

}
