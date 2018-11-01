/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.DataTypeProviderExt;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;


/**
 * Data type provider for ASA database.
 * 
 * @author Idull
 */
public class ASADataTypeProvider extends DataTypeProviderExt
{
    protected DatabaseDefinition getDbDefinition()
    {
        return ProfileUtil.getDatabaseDefinition(ASAConfig.getInstance().getDatabaseVendorDefinitionId());
    }

    public DataType[] getAvailableDataTypes(int context, Schema schema, DatabaseIdentifier databaseIdentifier)
    {   
        switch (context)
        {
            case PARAMETER:
            case VARIABLE:
            case RETURN_VALUE:
            case INDEX_COLUMN:
                return getAvailableDataTypes(ALL, schema, databaseIdentifier);
            default:
                break;
        }
        return super.getAvailableDataTypes(context, schema, databaseIdentifier);
    }
}
