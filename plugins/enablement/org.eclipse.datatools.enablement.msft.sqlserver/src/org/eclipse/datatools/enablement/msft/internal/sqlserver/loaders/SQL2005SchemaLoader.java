/*******************************************************************************
 * Copyright (c) 2008, 2013 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *     Actuate Corporation - bug fix Bugzilla 348160
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.models.SqlServerSchema;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class SQL2005SchemaLoader extends JDBCSchemaLoader {

	private static final String SCHEMA_QUERY = "select CATALOG_NAME as \'" + COLUMN_TABLE_CATALOG + "\', SCHEMA_NAME as \'" + COLUMN_TABLE_SCHEM + "\' from catalogName.INFORMATION_SCHEMA.SCHEMATA"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	
	private static final String getSchemaQuery(String catalogName)
	{
		return SCHEMA_QUERY.replaceAll("catalogName", catalogName); //$NON-NLS-1$
	}
	
	public SQL2005SchemaLoader() {
		super(null);
	}
	
	public SQL2005SchemaLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	protected Schema createSchema() {
		return new SqlServerSchema();
	}
	
	protected ResultSet createResultSet() throws SQLException {		
        try {
            // BZ 348160
            return super.createResultSet();
        }
        catch( Exception e ) {
            // fall back to use the original approach, which may have access permission issue
    		String query = getSchemaQuery(getCatalog().getName());
    		
    		if (getJDBCFilterPattern() != null
    				&& getJDBCFilterPattern().length() > 0) {
    			String filter = " AND ALIAS LIKE " + getJDBCFilterPattern();//$NON-NLS-1$
    			query = query + filter;
    		}
    		query = query + " ORDER BY " + COLUMN_TABLE_SCHEM; //$NON-NLS-1$
    
    		return getCatalogObject().getConnection().createStatement().executeQuery(query);
        }
	}

    protected boolean isSchemaInCatalog( ResultSet rs ) throws SQLException {
        if( super.isSchemaInCatalog( rs ) )
            return true;

        // handles the case where the JDBC driver, e.g. sqljdbc 1.2, returns inconsistent catalog name,
        // i.e. if the result set's catalogName is "dbo", ignore it and
        // simply checks whether this schema's catalog is the current catalog or
        // one for objects without a catalog
        String catalogName = rs.getString(COLUMN_TABLE_CATALOG);
        if( catalogName == null )
            return false;

        if( catalogName.equals( "dbo" ) ) //$NON-NLS-1$
            return isCurrentCatalog();

        return false;
    }

}
