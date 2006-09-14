package org.eclipse.datatools.sqltools.core;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;

/**
 * Manages the connection pool for control connections
 * @author Idull
 */
public interface IControlConnectionPoolManager
{
    /**
     * Returns a connection for control connection to use
     * @param databaseIdentifier the database identifier
     * @return 
     */
    public Connection getConnection(DatabaseIdentifier databaseIdentifier) throws SQLException, NoSuchProfileException;

    /**
     * Closes all connections for the given profile
     * @param profileName
     */
    public void closeConnections(String profileName);

    /**
     * Closes all connections for all control connections
     *
     */
    public void cleanUp();

    /**
     * Notifies the profile rename event
     * @param oldName
     * @param newName
     */
    public void profileRenamed(String oldName, String newName);
}
