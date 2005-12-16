/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.editor.core.connection;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * This interface defines all the connection information require by a <code>SQLEditor</code>.
 * 
 * @author Hui Cao
 */
public interface ISQLEditorConnectionInfo {
	
    /**
     * Gets a representing this object. Contains connection profile name and database name if any.
     * @return connection name
     */
    public String getName();
    
    /**
     * Gets the <code>DatabaseVendorDefinition</code> object associated with this input.
     * The <code>DatabaseVendorDefinition</code> object contains the information needed
     * to do parsing, syntax highlighting, and syntax validation.
     * 
     * @return the <code>DatabaseVendorDefinition</code> object associated with this input, can never be null.
     * A <code>CommonDatabaseVendorDefinition</code> object will be return if not specified.
     */
    public DatabaseVendorDefinition getDatabaseVendorDefinition();
    
    /**
     * Gets the <code>IConnectionProfile</code> object associated with this input.
     * The <code>IConnectionProfile</code> object contains the information needed
     * to connect to a database.
     * 
     * @return the <code>IConnectionProfile</code> object associated with this input
     * or <code>null</code> if none
     */
    public IConnectionProfile getConnectionProfile();

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
     * Gets the default schema name to use with the connection or database
     * associated with this input.
     * 
     * @return the default schema name to use with this input, or null if none
     */
    public String getDefaultSchemaName();
    
    /**
     * Sets the <code>IConnectionProfile</code> associated with this input to the given 
     * object.
     * 
     * @param connInfo the <code>IConnectionProfile</code> object to set
     */
    public void setConnectionProfile( IConnectionProfile connInfo );

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
     * 
     * @param schemaname the default schema name to set
     */
    public void setDefaultSchemaName( String schemaName );

    /**
     * Sets the <code>DatabaseVendorDefinition</code> associated with this input to the given 
     * object.
     * 
     * @param dbVendorDef the <code>DatabaseVendorDefinition</code> object to set
     */
    public void setDatabaseVendorDefinition(DatabaseVendorDefinition dbVendorDef);


	/**
	 * Encodes the given <code>ISQLEditorConnectionInfo</code> object for persistency.
	 * @see decode()
	 * @param info
	 * @return A encoded String
	 */
	public String encode();
	
}
