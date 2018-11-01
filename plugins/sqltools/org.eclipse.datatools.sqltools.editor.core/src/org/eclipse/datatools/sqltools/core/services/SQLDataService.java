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

import org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DataTypeValidator;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ISqlDataValidator;

/**
 * A SQL Data service that handles data types and data validation.
 * @author Hui Cao
 * 
 */
public class SQLDataService
{

    /**
     * Returns a ISqlDataValidator object which is used to validate data types and values for a database
     * 
     * @param databaseIdentifier uniquely identifies a database
     * @return SQL data validator
     */
    public ISqlDataValidator getSQLDataValidator(DatabaseIdentifier databaseIdentifier)
    {
        return null;
    }

    /**
     * Retrieves all datatypes used for parameters & variables. This is different with datatypes defined in database
     * model in that it includes default length for some data types.
     * @return data type array
     */
    public String[] getDatatypes()
    {
        return null;
    }

    /**
     * Retrieves aliases for getDatatypes().e.g. "national char" is an alias for "nchar".
     * 
     * @return data type aliases
     */
    public String[] getDatatypeAliases()
    {
        return null;
    }

	/** 
	 * Gets the JDBC datatype identifier corresponding to the given named  datatype.
	 * The default implementation simply delegates to {@link org.eclipse.datatools.modelbase.sql.query.helper.DataTypeHelper#getJDBCTypeForNamedType}.
	 * TODO replace SQLUtil.convert2SQLType with this method
	 * @param aTypeName a  type name to use to lookup a JDBC datatype without parentheses
	 * @return int a JDBC type identifier.
	 */
	public static int getJDBCTypeForNamedType( String aTypeName ) {
		return DataTypeHelper.getJDBCTypeForNamedType(aTypeName);
	}
	
    /**
     * Returns the data type provider for this database
     * @return the data type provider
     */
    public DataTypeProvider getDataTypeProvider()
    {
        return new DataTypeProvider();
    }
    
    /**
     * Returns the data type validator for this database
     * @return the data type validator
     */
    public DataTypeValidator getDataTypeValidator()
    {
        return new DataTypeValidator();
    }
}
