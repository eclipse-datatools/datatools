/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.DataTypeStringParser;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;


/**
 * Extension of data type provider for DMP where quick SQL parser is used to parse the data type string
 * 
 * @author Idull
 */
public class DataTypeProviderExt extends DataTypeProvider
{
    protected DataTypeStringParser getDataTypeStringParser()
    {
        return new QuickDataTypeParser();
    }

    /**
     * 
     * @param typeString
     * @param schema
     * @param context
     * @param databaseIdentifier
     * @param refresh
     * @return
     */
    public DataType getDataType(String typeString, Schema schema, int context, DatabaseIdentifier databaseIdentifier, boolean refresh)
    {
    	if(refresh)
    	{
            if (schema != null)
            {
                List schemas = null;
                
                // Get schemas from catalog
                if (schema.getCatalog() != null && schema.getCatalog().getSchemas() != null)
                {
                    schemas = schema.getCatalog().getSchemas();
                }
                
                // Try to get schemas from database
                if(schemas == null)
                {
                    if (schema.getDatabase() != null && schema.getDatabase().getSchemas() != null)
                    {
                        schemas = schema.getDatabase().getSchemas();
                    }
                }
                
                if(schemas != null)
                {
                	Iterator i = schemas.iterator();
                	while(i.hasNext())
                	{
                		Object obj = i.next();
                		if(obj instanceof ICatalogObject)
                		{
                			((ICatalogObject)obj).refresh();
                		}
                	}
                }
            }
    	}
    	
    	return getDataType(typeString, schema, context, databaseIdentifier);
    }
    
    public DataType getDataType(String typeString, Schema schema, int context, DatabaseIdentifier databaseIdentifier)
    {
        DataType dType = super.getDataType(typeString, schema, context, databaseIdentifier);
        if (dType == null)
        {
            return null;
        }

        // Perform further check to see if length/precision/scale is provided for the predefined type which does not support
        if (!(dType instanceof PredefinedDataType))
        {
            return dType;
        }
        PredefinedDataType pdType = (PredefinedDataType) dType;
        PredefinedDataTypeDefinition typeDef = getPredefinedDataTypeDefinition(pdType);
        if (typeDef == null)
        {
            return dType;
        }

        DataTypeStringParser parser = getDataTypeStringParser();
        String[] typeInfo = parser.parseDatatype(typeString);
        
        // No length/precision/scale specified
        if (typeInfo.length == 1)
        {
            return dType;
        }

        if (typeInfo.length == 2 && !typeDef.isLengthSupported() && !typeDef.isPrecisionSupported())
        {
            return null;
        }

        if (typeInfo.length == 3 && !typeDef.isScaleSupported())
        {
            return null;
        }
        
        if(typeInfo.length > 3)
        {
            return null;
        }

        return dType;
    }

    /**
     * TODO: will pull up to DTP 
     * @param dataType
     * @param qualified
     * @param databaseIdentifier
     * @return
     */
    public String getDataTypeString(DataType dataType, DatabaseIdentifier databaseIdentifier)
    {
        if (dataType == null)
        {
            return "";
        }
        DatabaseDefinition def = getDbDefinition();
        if (dataType instanceof PredefinedDataType)
        {
            return def.getPredefinedDataTypeFormattedName((PredefinedDataType) dataType);
        }
        else if (dataType instanceof UserDefinedType)
        {
            String quote = SQLDevToolsUtil.isQuotedIdentifierOn(databaseIdentifier) ? "\"" : "'";

            //as long as the name contains ", we need to quote because it's part of the name
            if (dataType.getName().contains("\""))
            {
                return SQLUtil.quote(dataType.getName(), quote);
            }
            String typeName = SQLDevToolsUtil.quoteWhenNecessary(dataType.getName(), databaseIdentifier, quote);
            return typeName;
        }
        return null;
    }
}
