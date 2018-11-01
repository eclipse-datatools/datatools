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

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;

/**
 * This interface defines all the connection information require by a <code>SQLEditor</code>.
 * 
 * @author Hui Cao
 */
public interface ISQLEditorConnectionInfo {
	
    /**
     * Gets a String representing this object. Contains vendor definition, connection profile name and database name if any.
     * @return connection name
     */
    public String getName();
    
    /**
     * Gets the <code>DatabaseVendorDefinition</code> object associated with this input.
     * The <code>DatabaseVendorDefinition</code> object contains the information needed
     * to do parsing, syntax highlighting, and syntax validation.
     * 
     * @return the <code>DatabaseVendorDefinition</code> object associated with this input, can never be null.
     * A default <code>DatabaseVendorDefinition</code> object will be return if not specified.
     */
    public DatabaseVendorDefinition getDatabaseVendorDefinition();
    
    /**
     * Gets the <code>DatabaseVendorDefinitionId</code> object associated with this input.
     * The <code>DatabaseVendorDefinitionId</code> object identifies a <code>DatabaseVendorDefinition</code> object
     * 
     * @return the <code>DatabaseVendorDefinitionId</code> object associated with this input, can never be null.
     * A default <code>DatabaseVendorDefinitionId</code> object will be return if not specified.
     */
    public DatabaseVendorDefinitionId getDatabaseVendorDefinitionId();
    
    /**
     * Gets the <code>IConnectionProfile</code> object associated with this input.
     * The <code>IConnectionProfile</code> object contains the information needed
     * to connect to a database.
     * 
     * @return the <code>IConnectionProfile</code> object associated with this input
     * or <code>null</code> if none or profile name is invalid
     */
    public IConnectionProfile getConnectionProfile();

    /**
     * Gets the <code>IConnectionProfile</code> name associated with this input.
     * The <code>IConnectionProfile</code> object contains the information needed
     * to connect to a database.
     * 
     * @return the <code>IConnectionProfile</code> name associated with this input
     * or <code>null</code> if none
     */
    public String getConnectionProfileName();
    
    /**
     * Gets the <code>Database</code> object associated with this input.  The
     * <code>Database</code> object provides access to database metadata (catalog)
     * information.
     * 
     * @return the <code>Database</code> object associated with this input, or
     * <code>null</code> if none
     */
    public Database getDatabase();
    
    /**
     * Gets the database name associated with this input. 
     * @see #getDatabase()
     * 
     * @return the database name associated with this input, or
     * <code>null</code> if none
     */
    public String getDatabaseName();
    
    /**
     * Gets the default schema name to use with the connection or database
     * associated with this input.
     * 
     * @return the default schema name to use with this input, or null if none
     */
    public String getDefaultSchemaName();
    
    /**
     * Sets the <code>IConnectionProfile</code> name associated with this input to the given 
     * object.
     * 
     * @param profileName the <code>IConnectionProfile</code> name to set
     */
    public void setConnectionProfileName( String profileName );

    /**
     * Sets the <code>Database</code> object associated with this input to the
     * given object.
     * 
     * @param database the <code>Database</code> object to set
     */
    public void setDatabase( Database database );

    /**
     * Sets the default schema name to use with the connection or database
     * associated with this input.
     * TODO register connection listener on connection profile to set the schema name here
     * 
     * @param schemaname the default schema name to set
     */
    public void setDefaultSchemaName( String schemaName );

    /**
     * Sets the <code>DatabaseVendorDefinitionId</code> associated with this input to the given 
     * object.
     * 
     * @param dbVendorDefId the <code>DatabaseVendorDefinitionId</code> object to set
     */
    public void setDatabaseVendorDefinitionId(DatabaseVendorDefinitionId dbVendorDefId);

	/**
	 * Retrieves the shared connection.
	 * @return if no shared connection set, return null.
	 */
	public Connection getSharedConnection();
	

	/**
	 * Encodes the given <code>ISQLEditorConnectionInfo</code> object for persistency.
	 * @see decode()
	 * @param info
	 * @return A encoded String
	 */
	public String encode();

	public int getProfileStatus();

	public void setProfileStatus(int status);
	
	public boolean isConnected();
}
