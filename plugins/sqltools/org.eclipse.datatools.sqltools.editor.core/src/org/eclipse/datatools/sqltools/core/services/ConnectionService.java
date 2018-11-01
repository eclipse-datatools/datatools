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

package org.eclipse.datatools.sqltools.core.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.IConnectionInitializer;
import org.eclipse.datatools.sqltools.internal.core.AbstractControlConnection;

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
        return new AbstractControlConnection(EditorCorePlugin.getControlConnectionManager(), databaseIdentifier);
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

    /**
     * Returns a connection for caller to use. By default there's no connection pool associated with a connection 
     * profile and this method just ignore the usePool parameter and delegates to {@link #createConnection(String, String)}.
     * Vendors who has the requirement to use connection pool may override this method.
     * @param databaseIdentifier the database identifier
     * @param usePool whether to retrieve the connection from connection pool.
     * @return a <code>Connection</code> instance
     * @throws SQLException if database error occurs
     */
    public Connection createConnection(DatabaseIdentifier databaseIdentifier,
			boolean usePool) throws SQLException, NoSuchProfileException {
		return createConnection(databaseIdentifier.getProfileName(), databaseIdentifier.getDBname());
	}
    /**
	 * Returns the default properties of the given profile for creating a
	 * connection to that profile
	 * 
	 * @param profileName
	 *            the profile name
	 * @return properties of this profile
	 * @throws NoSuchProfileException
	 *             if this profile does not exist
	 */
    public Properties getDefaultConnectionProperties(String profileName) throws NoSuchProfileException
    {
        Properties props = new Properties();
        Properties profProps = ProfileUtil.getProfile(profileName).getBaseProperties();
        props.put("user", profProps.getProperty(ProfileUtil.UID)); //$NON-NLS-1$
        props.put("password", profProps.getProperty(ProfileUtil.PWD)); //$NON-NLS-1$
        //TODO CONN get properties
//        String nameValuePairs = profProps.getProperty(IJDBCProfilePropertyConstants.PROP_DB_CONN_PROPS);
//        if (nameValuePairs != null && nameValuePairs.length() > 0)
//        {
//            String[] pairs = ProfileUtil.parseString(nameValuePairs, ","); //$NON-NLS-1$
//            for (int i = 0; i < pairs.length; i++)
//            {
//                String[] namevalue = ProfileUtil.parseString(pairs[i], "="); //$NON-NLS-1$
//                props.setProperty(namevalue[0], namevalue[1]);
//            }
//        }
        return props;
    }

    /**
     * Closes the connection object. Subclasses may need to do further log or cleanup jobs.
     * @param connection
     * @param connId 
     * @param databaseIdentifier
     * @throws SQLException
     */
    public void closeConnection(Connection connection, int connId, DatabaseIdentifier databaseIdentifier) throws SQLException
    {
        ProfileUtil.closeConnection(databaseIdentifier.getProfileName(), databaseIdentifier.getDBname(), connection);
    }


    /**
     * Get an ConnectionProcessor
     * @return
     */
    public ConnectionProcessor getConnectionProcessor(DatabaseIdentifier databaseIdentifier){
        return null;
    }
    
    /**
     * This interface can be implemented to do vendor-specific processing of the connection, 
     * e.g. parse the SQLException and append messages to SQL results view. 
     */
    public interface ConnectionProcessor {
        /**
         * Initializes the environment for running
         * @param con
         */
        public void prepare(Connection con);
        
        /**
         * Does the specific thing
         * @param object object to be processed, e.g. OperationCommand
         */
        public void process(Object object);
        
        /**
         * Releases all resources that were initialized at the point of preparation
         */
        public void release();
    }

}
