/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.services;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ASADataTypeProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ASADataTypeValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.ASADataValidator;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DataTypeValidator;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ISqlDataValidator;
import org.eclipse.datatools.sqltools.core.services.SQLDataService;


/**
 * @author hcao
 *
 */
public class ASASQLDataService extends SQLDataService
{

	/**
     * Comment for <code>DATATYPE</code> List of all ASA datatypes used for parameters & variables
     */
    public static final String[] DATATYPE       =
    {
        "bigint", "binary(10)", "bit", "char(5)", "date", "decimal(8,3)", "double", "float(4)", "int", "long binary",
            "long varchar", "numeric(8,3)", "real", "smallint", "time", "timestamp", "tinyint", "unsigned bigint","uniqueidentifier",
            "unsigned int", "unsigned smallint", "unsigned tinyint", "varchar(20)", "varbinary(10)"
    }
    ;

    public static final String[] DATATYPE_ALIAS =
    {
        "character", "dec", "integer", "character varying"
    }
    ;


    public String[] getDatatypes()
    {
        return DATATYPE;
    }

    public String[] getDatatypeAliases()
    {
        return DATATYPE_ALIAS;
    }



    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IDBFactory#getSQLDataValidator()
     */
    public ISqlDataValidator getSQLDataValidator(DatabaseIdentifier databaseIdentifier)
    {
        return new ASADataValidator(databaseIdentifier);
    }

    public DataTypeProvider getDataTypeProvider()
    {
        return new ASADataTypeProvider();
    }

    public DataTypeValidator getDataTypeValidator()
    {
        return new ASADataTypeValidator();
    }

    
}
