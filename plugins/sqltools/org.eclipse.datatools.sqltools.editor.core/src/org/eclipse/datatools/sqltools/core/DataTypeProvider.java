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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Provides data types for given context
 * 
 * @author Dafan Yang
 */
public class DataTypeProvider
{
    public static final String        LENGTH_FEATURE                   = "length";
    public static final String        PRECISION_FEATURE                = "precision";
    public static final String        SCALE_FEATURE                    = "scale";
    public static final String        NAME_FEATURE                     = "name";

    protected static final String[][] RECOMMEND_LENGTH_PRECISION_SCALE = new String[][]
                                                                       {
        new String[]
        {
        "binary", "10"
        }, new String[]
        {
        "char", "5"
        }, new String[]
        {
        "decimal", "8", "3"
        }, new String[]
        {
        "numeric", "8", "3"
        }, new String[]
        {
        "varchar", "20"
        }, new String[]
        {
        "float", "4"
        }, new String[]
        {
        "varbinary", "10"
        }, new String[]
        {
        "unichar", "10"
        }, new String[]
        {
        "univarchar", "20"
        }, new String[]
        {
        "nchar", "10"
        }, new String[]
        {
        "nvarchar", "20"
        }
                                                                       };

    // Predefined context
    
    // Stands for all available data types
    public static final int           ALL                              = 0;
    // Predefined data types
    public static final int           PREDEFINED                       = 1;
    // User-defined data types
    public static final int           UDT                              = 2;
    // Data types for table columns
    public static final int           TABLE_COLUMN                     = 3;
    // Data types for parameter of procedural objects
    public static final int           PARAMETER                        = 4;
    // Data type for variable of procedural objects
    public static final int           VARIABLE                         = 5;
    // Data type for return value of procedural objects
    public static final int           RETURN_VALUE                     = 6;
    // Data type for table column of indexes
    public static final int           INDEX_COLUMN                     = 7;

    // The base value for database specific context
    public static final int           DB_SPECIFIC_CONTEXT_BASE         = 100;

    /**
     * Returns the available data types of the given context.<br>
     * Subclass may need to fine-tune this method, for example, for paramter context, all the large data types wont be
     * returned by default, but this is not always the case for all databases.<br>
     * 
     * @param context the context in which the data types will be used
     * @see #ALL
     * @see #PREDEFINED
     * @see #UDT
     * @see #TABLE_COLUMN
     * @see #PARAMETER
     * @see #VARIABLE
     * @see #RETURN_VALUE
     * @see #INDEX_COLUMN
     * 
     * @param schema a schema object thru. which the UDT can be obtained
     */
    public DataType[] getAvailableDataTypes(int context, Schema schema, DatabaseIdentifier databaseIdentifier)
    {
        List predefinedTypes = new ArrayList();
        PredefinedDataTypeDefinition[] preTypeDefs = getPredefinedDataTypeDefinitions();
        DatabaseDefinition dbdef = getDbDefinition();
        if (preTypeDefs != null && dbdef != null)
        {
            for (int i = 0; i < preTypeDefs.length; i++)
            {
                PredefinedDataType dType = dbdef.getPredefinedDataType(preTypeDefs[i]);
                if (dType != null)
                {
                    String[] lengthPrecisionScale = getLengthPrecisionScale(dType.getName());
                    if (lengthPrecisionScale != null)
                    {
                        if (lengthPrecisionScale.length > 1 && preTypeDefs[i].isLengthSupported())
                        {
                            EStructuralFeature f = dType.eClass().getEStructuralFeature(LENGTH_FEATURE);
                            dType.eSet(f, Integer.valueOf(lengthPrecisionScale[1]));
                        }
                        else if (lengthPrecisionScale.length > 1 && preTypeDefs[i].isPrecisionSupported())
                        {
                            EStructuralFeature f = dType.eClass().getEStructuralFeature(PRECISION_FEATURE);
                            dType.eSet(f, Integer.valueOf(lengthPrecisionScale[1]));
                        }

                        if (lengthPrecisionScale.length > 2 && preTypeDefs[i].isScaleSupported())
                        {
                            EStructuralFeature feature = dType.eClass().getEStructuralFeature(SCALE_FEATURE);
                            dType.eSet(feature, Integer.valueOf(lengthPrecisionScale[2]));
                        }
                    }
                    predefinedTypes.add(dType);
                }
            }
        }

        if (context == PREDEFINED)
        {
            return (DataType[]) predefinedTypes.toArray(new DataType[predefinedTypes.size()]);
        }

        List udts = new ArrayList();
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
            
            if (schemas != null)
            {
                Iterator i = schemas.iterator();
                while (i.hasNext())
                {
                    Schema s = (Schema)i.next();
                    udts.addAll(s.getUserDefinedTypes());
                }
            }
            else
            {
                udts.addAll(schema.getUserDefinedTypes());
            }
        }

        switch (context)
        {
            case UDT:
                return (DataType[]) udts.toArray(new DataType[udts.size()]);
            case ALL:
            case TABLE_COLUMN:
                List all = new ArrayList();
                all.addAll(predefinedTypes);
                all.addAll(udts);
                return (DataType[]) all.toArray(new DataType[all.size()]);
            case PARAMETER:
            case VARIABLE:
            case RETURN_VALUE:
            case INDEX_COLUMN:
                List typesWithoutLargeType = new ArrayList();
                typesWithoutLargeType.addAll(predefinedTypes);
                typesWithoutLargeType.addAll(udts);

                List largeTypes = new ArrayList();
                Iterator i = typesWithoutLargeType.iterator();
                while (i.hasNext())
                {
                    DataType type = (DataType)i.next();
                    PrimitiveType primitiveType = null;
                    if (type instanceof PredefinedDataType)
                    {
                        primitiveType = ((PredefinedDataType) type).getPrimitiveType();
                    }
                    else if (type instanceof DistinctUserDefinedType)
                    {
                        DistinctUserDefinedType udt = (DistinctUserDefinedType) type;
                        PredefinedDataType predefinedType = udt.getPredefinedRepresentation();
                        primitiveType = predefinedType.getPrimitiveType();
                    }

                    if (primitiveType != null)
                    {
                        if (primitiveType == PrimitiveType.BINARY_LARGE_OBJECT_LITERAL
                                || primitiveType == PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL
                                || primitiveType == PrimitiveType.XML_TYPE_LITERAL
                                || primitiveType == PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT_LITERAL)
                        {
                            largeTypes.add(type);
                        }
                    }
                }
                typesWithoutLargeType.removeAll(largeTypes);
                return (DataType[]) typesWithoutLargeType.toArray(new DataType[typesWithoutLargeType.size()]);
            default:
                break;
        }
        return new DataType[0];
    }

    /**
     * Returns the display string of the available datatypes of the given context.
     * 
     * @param context the context in which the data types will be used
     * @param schema a schema object thru. which the UDT can be obtained
     * @return
     */
    public String[] getAvailableDataTypesDisplayString(int context, Schema schema, DatabaseIdentifier databaseIdentifier)
    {
        DataType[] types = getAvailableDataTypes(context, schema, databaseIdentifier);
        if (types == null)
        {
            return new String[0];
        }
        List dispStrings = new ArrayList();
        for (int i = 0; i < types.length; i++)
        {
            if (types[i] == null)
            {
                continue;
            }
            String dispStr = getDataTypeString(types[i], false);
            if (dispStr == null)
            {
                continue;
            }
            if (types[i] instanceof PredefinedDataType)
            {
                dispStr = dispStr.toLowerCase();
            }
            if (!dispStrings.contains(dispStr))
            {
                dispStrings.add(dispStr);
            }
        }
        Collections.sort(dispStrings, new TypeNameComparator());
        return (String[]) dispStrings.toArray(new String[dispStrings.size()]);
    }

    public DataType getDataType(String typeString, Schema schema, DatabaseIdentifier databaseIdentifier)
    {
        return getDataType(typeString, schema, ALL, databaseIdentifier);
    }

    /**
     * Returns the data type instance given the data type string. If the typeString is invalid, returns null.<br>
     * 
     * 
     * @param typeString the string of the data type
     * @param schema 
     * @return a schema object thru. which the UDT can be obtained
     */
    public DataType getDataType(String typeString, Schema schema, int context, DatabaseIdentifier databaseIdentifier)
    {
        if(typeString == null)
        {
            return null;
        }
        DataTypeStringParser parser = getDataTypeStringParser();
        DatabaseDefinition dbdef = getDbDefinition();
        if (parser == null || dbdef == null)
        {
            return null;
        }
        String[] typeInfo = parser.parseDatatype(typeString);
        if (typeInfo == null || typeInfo.length == 0 || typeInfo[0] == null)
        {
            return null;
        }
        DataType[] validTypes = getAvailableDataTypes(context, schema, databaseIdentifier);

        if (validTypes == null || validTypes.length == 0)
        {
            return null;
        }
        for (int i = 0; i < validTypes.length; i++)
        {
            // For predefined data type, check if can get the type definition.
            if (validTypes[i] instanceof PredefinedDataType)
            {
                PredefinedDataTypeDefinition typeDefinition = dbdef.getPredefinedDataTypeDefinition(typeInfo[0]);
                PredefinedDataTypeDefinition typeDef = getPredefinedDataTypeDefinition((PredefinedDataType) validTypes[i]);
                if (typeDefinition == null || typeDefinition != typeDef)
                {
                    continue;
                }
            }
            // For UDT, check if the name can be found
            else if (!((isDataTypeCaseSensitive(databaseIdentifier) && typeInfo[0].equals(validTypes[i].getName())) || (!isDataTypeCaseSensitive(databaseIdentifier) && typeInfo[0]
                    .equalsIgnoreCase(validTypes[i].getName()))))
            {
                continue;
            }

            if (validTypes[i] instanceof PredefinedDataType)
            {
                PredefinedDataType predefinedType = (PredefinedDataType) validTypes[i];
                PredefinedDataTypeDefinition typeDefinition = getPredefinedDataTypeDefinition(predefinedType);
                if (typeDefinition != null)
                {
                    // Create a new instance based on the type definition and parsing result
                    DataType type = dbdef.getPredefinedDataType(typeInfo[0]);
                    if (typeInfo.length > 1 && typeDefinition.isLengthSupported())
                    {
                        EStructuralFeature feature = type.eClass().getEStructuralFeature(LENGTH_FEATURE);
                        type.eSet(feature, new Integer(typeInfo[1]));
                    }
                    else if (typeInfo.length > 1 && typeDefinition.isPrecisionSupported())
                    {
                        EStructuralFeature feature = type.eClass().getEStructuralFeature(PRECISION_FEATURE);
                        type.eSet(feature, new Integer(typeInfo[1]));
                    }

                    if (typeInfo.length > 2 && typeDefinition.isScaleSupported())
                    {
                        EStructuralFeature feature = type.eClass().getEStructuralFeature(SCALE_FEATURE);
                        type.eSet(feature, new Integer(typeInfo[2]));
                    }

                    return type;
                }
            }
            else if (validTypes[i] instanceof UserDefinedType)
            {
                return validTypes[i];
            }
        }
        return null;
    }

    /**
     * Returns the data type string given the data type
     * 
     * @param dataType the data type object
     * @return
     */
    public String getDataTypeString(DataType dataType, boolean qualified)
    {
        if (dataType == null)
        {
            return "";
        }
        else if (dataType instanceof PredefinedDataType)
        {
            if (getDbDefinition() == null)
            {
                return dataType.getName();
            }

            return getDbDefinition().getPredefinedDataTypeFormattedName((PredefinedDataType) dataType);
        }
        else if (dataType instanceof UserDefinedType)
        {
            UserDefinedType udt = (UserDefinedType)dataType;
            if (qualified && udt.getSchema() != null && udt.getSchema().getName() != null)
            {
                return udt.getSchema().getName() + "." + dataType.getName();
            }
            return dataType.getName();
        }
        else
        {
            // Other types,
            return dataType.getName();
        }
    }

    /**
     * Returns the predefined data type definitions of this database
     * 
     * @return
     */
    public PredefinedDataTypeDefinition[] getPredefinedDataTypeDefinitions()
    {
        DatabaseDefinition dbdef = getDbDefinition();
        if (dbdef == null)
        {
            return new PredefinedDataTypeDefinition[0];
        }
        List typeDefs = new ArrayList();
        Iterator iter = dbdef.getPredefinedDataTypes();
        while (iter.hasNext())
        {
            typeDefs.add(iter.next());
        }
        return (PredefinedDataTypeDefinition[]) typeDefs.toArray(new PredefinedDataTypeDefinition[typeDefs.size()]);
    }

    /**
     * Returns name of predefined data types
     * 
     * @return
     */
    public String[] getPredefinedDataTypeNames()
    {
        List dispStrs = new ArrayList();

        PredefinedDataTypeDefinition[] defs = getPredefinedDataTypeDefinitions();
        if (defs == null)
        {
            return null;
        }
        for (int i = 0; i < defs.length; i++)
        {
            if (defs[i] != null)
            {
                String name = (String) defs[i].getName().get(0);
                if (name == null || name.trim().length() == 0)
                {
                    continue;
                }
                if (!dispStrs.contains(name.toLowerCase()))
                {
                    dispStrs.add(name.toLowerCase());
                }
            }
        }
        return (String[]) dispStrs.toArray(new String[dispStrs.size()]);
    }

    /**
     * Subclass need to implement this method to return the database definition instance
     * 
     * @return
     */
    protected DatabaseDefinition getDbDefinition()
    {
        return null;
    }

    protected String[] getLengthPrecisionScale(String typeName)
    {
        for (int i = 0; i < RECOMMEND_LENGTH_PRECISION_SCALE.length; i++)
        {
            if (RECOMMEND_LENGTH_PRECISION_SCALE[i][0].equalsIgnoreCase(typeName))
            {
                return RECOMMEND_LENGTH_PRECISION_SCALE[i];
            }
        }
        return null;
    }

    protected boolean isDataTypeCaseSensitive(DatabaseIdentifier databaseIdentifier)
    {
        if (getDbDefinition() == null || databaseIdentifier == null)
        {
            return false;
        }
        SQLDevToolsConfiguration config = SQLToolsFacade.getConfiguration(databaseIdentifier,
                new DatabaseVendorDefinitionId(getDbDefinition().getProduct(), getDbDefinition().getVersion()));
        if (config == null)
        {
            return false;
        }
        IDatabaseSetting setting = config.getDatabaseSetting(databaseIdentifier);
        if (setting == null)
        {
            return false;
        }
        try
        {
            Object obj = setting.getProperty(IDatabaseSetting.P_CASE_SENSITIVE);
            if (obj == null || !(obj instanceof Boolean))
            {
                return false;
            }
            return ((Boolean) obj).booleanValue();
        }
        catch (Exception e)
        {

        }
        return false;
    }

    protected DataTypeStringParser getDataTypeStringParser()
    {
        return new DataTypeStringParser();
    }

    /**
     * Returns the predefined data type definition given a predefined data type
     * 
     * @param dataType
     * @return
     */
    protected PredefinedDataTypeDefinition getPredefinedDataTypeDefinition(PredefinedDataType dataType)
    {
        if (dataType == null || dataType.getName() == null)
        {
            return null;
        }
        PredefinedDataTypeDefinition[] defs = getPredefinedDataTypeDefinitions();
        for (int i = 0; i < defs.length; i++)
        {
            if (defs[i] != null && defs[i].getName() != null)
            {
                if (matchName(defs[i].getName(), dataType.getName(), false))
                {
                    return defs[i];
                }
            }
        }
        return null;
    }

    protected static boolean matchName(List names, String name, boolean caseSensitive)
    {
        if (names == null || names.size() == 0 || name == null)
        {
            return false;
        }
        Iterator i = names.iterator();
        while (i.hasNext())
        {
            String n = (String)i.next();
            if (n == null)
            {
                continue;
            }
            if (caseSensitive && n.equals(name))
            {
                return true;
            }
            if (!caseSensitive && n.equalsIgnoreCase(name))
            {
                return true;
            }
        }
        return false;
    }
    
    class TypeNameComparator implements Comparator
    {
        public int compare(Object o1, Object o2)
        {
            if (o1 == null || o2 == null)
            {
                return 0;
            }
            if (!(o1 instanceof String) || !(o2 instanceof String))
            {
                return 0;
            }
            String s1 = ((String) o1).toLowerCase();
            String s2 = ((String) o2).toLowerCase();
            return s1.compareTo(s2);
        }
    }
}
