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

package org.eclipse.datatools.sqltools.core;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.core.dbitem.IDBItem;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.sql.reference.IDatatype;

/**
 * For each connected database, we keep a "control connection". This connection is used for all shared usage to that
 * particular database. This control connection is a wrapper of the connection created by connectivity layer and relies
 * on connectivity layer to do connection caching/pooling. Shared usage refers to the following ones:
 * <UL>
 * <li>Load/create/save routine objects;
 * <li>Get database metadata to present as content assist proposals;
 * <li>Get supported data types;
 * <li>Manage all the internal connections to the same database;
 * <li>etc.
 * </UL>
 * <p>
 * As you can see, all the above mentioned functions can be accomplished by using SQL Model, Database Model and the API
 * from Connectivity layer. This is because SQL Dev Tools evolves at the same time with DTP Model Base project and
 * Connectivity project, so you can view this interface as the ��adapter�� between SQL Dev Tools and the other 2 projects
 * of DTP. In fact, we��ll provide a default implementation of this interface which uses SQL Model, Database Model and
 * the API from Connectivity layer.
 * </p>
 * 
 * @author Yang Liu
 * @author Hui Cao
 */
public interface IControlConnection extends IAdaptable
{
    /**
     * Returns which databaseIdentifier is used for this control connection
     * 
     * @return a <code>DatabaseIdentifier</code> object, key of this control connection
     */
    public DatabaseIdentifier getDatabaseIdentifier();

    /**
     * Should be called to keep in sync when profile name is changed while other profile properties are left unchanged.
     * 
     * @param profileName the connection profile name
     */
    public void profileRenamed(String profileName);

    /**
     * As the profile login name could be different from the database user name. this method will get the database user
     * name.
     * 
     * @return database user name
     * @throws SQLException
     */
    public String getDbUsername() throws SQLException;

    /**
     * Gets the source code of the specified routine. Wrapper method for connectivity layer.
     * 
     * @param proc the identifier of a routine object
     * @return the routine source code
     * @throws SQLException
     */
    public String getProcSource(ProcIdentifier proc) throws SQLException;

    /**
     * gets all routine object identifiers exist in the database.
     * 
     * @return Array of <code>ProcIdentifier</code>
     * @throws SQLException
     */
    public ProcIdentifier[] getAllProcs() throws SQLException;

    /**
     * Whether it's ok to disconnect this control connection.
     * 
     */
    public boolean okToDisconnect();

    /**
     * Disconnects this control connection.
     * 
     * @param force whether force to disconnect even if still in use (by debugger, etc)
     * @return true means successfully disconnected. false means it is still in use.
     */
    public boolean disconnect(boolean force);

    /**
     * same as disconnect(false)
     * 
     * @return true means successfully disconnected. false means it is still in use.
     */
    public boolean disconnect();

    /**
	 * Returns the wrapped connection object. This is a convenience method which
	 * gets the jdbc connection from connection profile.
	 * 
	 * @return the wrapped connection object
	 */
    public Connection getReusableConnection();

    /**
     * Saves the specified routine object into database.
     * 
     * @param proc routine identifier
     * @param string source code of the routine object
     * @throws SQLException
     */
    public void saveRoutine(ProcIdentifier proc, String string) throws SQLException;

    /**
     * NOTE: caller should check whether the return value is null.
     * 
     * @param identifier routine identifier
     * @return can return null.
     */
    public IDBItem getDBItem(ProcIdentifier identifier);

    /**
     * Creates or drops a routine object in the database by executing the definition statement
     * 
     * @param sql the ddl statements
     */
    public void executeDDL(String[] sql) throws SQLException;

    /**
     * Refreshs all the cached routine object definitions. Wrapper method for connectivity layer.
     * 
     */
    public void refresh();

    /**
     * Refreshs the specified cached routine object definitions. Wrapper method for connectivity layer.
     * 
     * @param procIdentifier routine identifier
     */
    public void refresh(ProcIdentifier procIdentifier);

    /**
     * Gets a <code>IDatatype</code> object by name. e.g., given char(10), return a IDatatype object which describe char(10)
     * 
     * @return a <code>IDatatype</code> represented by the given name. 
     * @throws SQLException
     */
    public IDatatype getTypeByNameStr(String nameStr) throws Exception;

    /**
     * Checks if the Text of the procedural object is hidden. (TODO: request Model Base project to add "isSourceHidden"
     * attribute to SQL Routine object so that we can remove this mothed).
     * 
     * @return true if text hidden, else false
     */
    public boolean isTextHidden(DatabaseIdentifier databaseIdentifier, String dbObjectName, int dbObjectType);

    /**
     * As the connection id used inside IControlConnection may be different from those external ids. So sometimes when
     * people have external id and exteranl name, they need to convert to internal id first.
     * For vendors that don't care about it, just return the externalId is fine.
     * 
     * @param externalId external connection id
     * @param exteranlName optional connection name
     * @throws ConnectionException
     */
    public String convertToInternalConnId(String externalId, String exteranlName) throws ConnectionException;

    /**
     * Whether this control connection supports debugging. Later this can be obtained from database definition model. If
     * the return value is true, this should be an instance of <code>IDebuggerControlConnection</code>.
     * 
     * @see IDebuggerControlConnection
     * @return true if so; false otherwise
     */
    public boolean supportsDebugging();

	public Connection createConnection(String[] connId) throws SQLException, CoreException, NoSuchProfileException;
    

}
