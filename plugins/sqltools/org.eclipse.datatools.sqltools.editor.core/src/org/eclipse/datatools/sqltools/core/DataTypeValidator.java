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

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.osgi.util.NLS;

/**
 * Validate given data type in given context
 * 
 * @author Dafan Yang
 */
public class DataTypeValidator
{
    /**
     * Return null if valid Return error message otherwise
     * 
     * @param typeString
     * @param schema
     * @param context
     */
    public String isValidDataTypeString(String typeString, Schema schema, int context,
            DatabaseIdentifier databaseIdentifier)
    {
        DataTypeProvider typeProvider = getDataTypeProvider();
        if (typeProvider == null)
        {
            return Messages.DataTypeValidator_no_db_definition;
        }
        DataType t = typeProvider.getDataType(typeString, schema, databaseIdentifier);
        DataType type = typeProvider.getDataType(typeString, schema, context, databaseIdentifier);
        if (type == null && t != null)
        {
            return NLS.bind(Messages.DataTypeValidator_can_not_use_in_current_context, typeString);
        }
        else if (type == null && t == null)
        {
            return NLS.bind(Messages.DataTypeValidator_invalid_type, typeString);
        }

        if (type instanceof PredefinedDataType)
        {
            return PredefinedDataTypeValidator.isValidPredefinedType((PredefinedDataType) type, ProfileUtil
                    .getDatabaseDefinition(getDatabaseVendorDefinitionId()));
        }
        return null;
    }

    /**
     * Return null if valid Return error message otherwise
     * 
     * @param dataType
     * @param schema
     * @param context
     * @return
     */
    public String isValidDataType(DataType dataType, Schema schema, int context, DatabaseIdentifier databaseIdentifier)
    {
        DataTypeProvider typeProvider = getDataTypeProvider();
        if (typeProvider == null)
        {
            return Messages.DataTypeValidator_no_db_definition;
        }
        String str = typeProvider.getDataTypeString(dataType, false);
        return isValidDataTypeString(str, schema, context, databaseIdentifier);
    }

    protected DatabaseVendorDefinitionId getDatabaseVendorDefinitionId()
    {
        return null;
    }

    /**
     * Returns the data type provider of this database
     * 
     * @return
     */
    protected DataTypeProvider getDataTypeProvider()
    {
        DatabaseVendorDefinitionId id = getDatabaseVendorDefinitionId();
        if (id == null)
        {
            return null;
        }
        SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByVendorIdentifier(id);
        if (config == null)
        {
            return null;
        }
        return config.getSQLDataService().getDataTypeProvider();
    }
}
