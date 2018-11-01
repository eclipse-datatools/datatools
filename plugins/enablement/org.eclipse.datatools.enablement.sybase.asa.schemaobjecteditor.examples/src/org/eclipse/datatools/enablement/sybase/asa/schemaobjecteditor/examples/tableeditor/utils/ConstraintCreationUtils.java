/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SQLUtil;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.common.util.EList;


/**
 * @author David Cui
 */
public class ConstraintCreationUtils implements IConstraintCreationConstants
{
    public static String getDataTypeFullFormatString(Column column)
    {
        if(column==null)
        {
            return EMPTY_STRING; 
        }
        return IDataToolsUIServiceManager.INSTANCE.getColumnHelperService().getDataType(column);
    }
    
    /**Get the column according to the given name
     * @param table
     * @param columnName
     * @return
     */
    public static Column getColumnByName(Table table, String columnName)
    {
        if(table==null)
        {
            return null;
        }
        EList allColumns = table.getColumns();
        for (int i = 0; i < allColumns.size(); i++)
        {
            Column column = (Column) allColumns.get(i);
            if (column.getName().equals(columnName))
            {
                return column;
            }
        }
        return null;
    }
    
    /**Get the column according to the given name
     * @param constraint
     * @param columnName
     * @return
     */
    public static Column getColumnByName(UniqueConstraint constraint, String columnName)
    {
        if(constraint == null)
        {
            return null;
        }
        EList allMembers = constraint.getMembers();
        for (int i = 0; i < allMembers.size(); i++)
        {
            Column column = (Column) allMembers.get(i);
            if (column.getName().equals(columnName))
            {
                return column;
            }
        }
        return null;
    }
    
    /**Judge whether two columns can map to create foreign key.
     * Here there is particular case. It is mainly focus on the data types of sysname and longsysname in ASE.
     * Columns with datatype of sysname could map to columns with char or varchar datatype whose length is 30. 
     * And Columns with datatype of longsysname could map to columns with char or varchar datatype whose length is 255. 
     * In the model configuration, We set sysname and longsyanme's properties that lengthSupport to be 'false' 
     * in order to get a right display for them, and defaultLength to be '30' and '255'. 
     * As so trick the model and get the defaultLength to map with a character datatype.
     * @param firstCol
     * @param secondCol
     * @return
     */
    public static boolean canTwoColumnsMap(Column firstCol, Column secondCol)
    {
        if(firstCol == null || secondCol == null)
        {
            return false;
        }
        Table firstTable = firstCol.getTable();
        Table secondTable = secondCol.getTable();
        if(firstTable == null || secondTable == null)
        {
            return false;
        }
        DataType firstDataType = firstCol.getDataType();
        DataType secondDataType = secondCol.getDataType();
        
        if (getOriginalDataTypeFullFormatString(firstDataType,firstTable).equals(
                getOriginalDataTypeFullFormatString(secondDataType,secondTable)))
        {
            return true;
        }
        else if(getOriginalDataType(firstDataType) instanceof CharacterStringDataType &&  getOriginalDataType(secondDataType) instanceof CharacterStringDataType)
        {
            DataType firstOriginal = getOriginalDataType(firstDataType);
            DataType secondOriginal = getOriginalDataType(secondDataType);
            if((firstOriginal.getName().equalsIgnoreCase(DATA_TYPE_LONG_VARCHAR)
                 && !secondOriginal.getName().equalsIgnoreCase(DATA_TYPE_LONG_VARCHAR)) 
                 || !firstOriginal.getName().equalsIgnoreCase(DATA_TYPE_LONG_VARCHAR)
                 && secondOriginal.getName().equalsIgnoreCase(DATA_TYPE_LONG_VARCHAR))
            {
                return false;
            }
            int firstLength = ((CharacterStringDataType)firstOriginal).getLength();
            //here trick the model and get the defaultLength to map with a character datatype.
            if(firstLength==1 && !isLengthSupported(firstTable, firstOriginal))
            {
                int defaultLength = getDefaultLength(firstTable, firstOriginal);
                if(defaultLength>1)
                {
                    firstLength = defaultLength;
                }
            }
            int secondLength = ((CharacterStringDataType)secondOriginal).getLength();
            //here trick the model and get the defaultLength to map with a character datatype.
            if(secondLength==1 && !isLengthSupported(secondTable, secondOriginal))
            {
                int defaultLength = getDefaultLength(secondTable, secondOriginal);
                if(defaultLength>1)
                {
                    secondLength = defaultLength;
                }
            }
            if(firstLength == secondLength)
            {
                return true;
            }
        }
        return false;
    }
    
    /**Judge whether a given data type support length
     * @param table
     * @param dataType
     * @return
     */
    private static boolean isLengthSupported(Table table, DataType dataType)
    {
        DatabaseDefinition def = getDatabaseDefinition(table);
        return def.getPredefinedDataTypeDefinition(dataType.getName()).isLengthSupported();
    }
    
    /**Get DatabaseDefinition by a given table
     * @param table
     * @return
     */
    public static DatabaseDefinition getDatabaseDefinition(Table table)
    {
        DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
        return dbRegistry.getDefinition(ModelUtil.getDatabase(table.getSchema()));
    }
    
    /**Get the default length of the given data type
     * @param table
     * @param dataType
     * @return
     */
    private static int getDefaultLength(Table table, DataType dataType)
    {
        DatabaseDefinition def = getDatabaseDefinition(table);
        int defaultLength = def.getPredefinedDataTypeDefinition(dataType.getName()).getDefaultLength();
        return defaultLength;
    }
    
    /**Get fully formated String of data type
     * @param datatype
     * @param table
     * @return
     */
    private static String getOriginalDataTypeFullFormatString(DataType datatype,Table table)
    {
        DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
        DatabaseDefinition definition = dbRegistry.getDefinition(ModelUtil.getDatabase(table.getSchema()));
        if(datatype != null) 
        {
            if (datatype instanceof PredefinedDataType) 
            {
                return definition.getPredefinedDataTypeFormattedName((PredefinedDataType)datatype);     
            }
            else if(datatype instanceof DistinctUserDefinedType)
            {
                return definition.getPredefinedDataTypeFormattedName(((DistinctUserDefinedType)datatype).getPredefinedRepresentation());
            }
            else 
            {
                return datatype.getName();
            }
        }
        return EMPTY_STRING;
    }
    
    /**Get the primitive data type of a UDT. If it already is a primitive data type, return itself.
     * @param datatype
     * @return
     */
    private static DataType getOriginalDataType(DataType datatype)
    {
        if(datatype instanceof DistinctUserDefinedType)
        {
            return ((DistinctUserDefinedType)datatype).getPredefinedRepresentation();
        }
        return datatype;
    }
    
    /**
     * If a string is null or its length is 0, return true, else return false.
     * @param str
     * @return
     */ 
    public static boolean isNothing(String str)
    {
        if (str == null || str.trim().length() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**Get the validator message of a given object name
     * @param databaseIdentifier
     * @param table
     * @param objectName
     * @return
     */
    public static ValidatorMessage getValidatorMessage(DatabaseIdentifier databaseIdentifier, Table table,
            String objectName)
    {
        if (objectName.equals(Messages.ConstraintNameTextDefault))
        {
            return null;
        }
        //Database database = ModelUtil.getDatabase(table.getSchema());
        //String profile = ModelUtil.getConnectionProfile(database).getName();
        String dbType = ProfileUtil.getDatabaseVendorDefinitionId(databaseIdentifier.getProfileName()).toString();
        SQLService service = SQLToolsFacade.getSQLService(databaseIdentifier, dbType);
        if (service != null)
        {
            IIdentifierValidator validator = service.getIdentifierValidator();
            // TODO Use IIdentifierValidator.IDENTIFIER_TYPE_UNKNOW?
            ValidatorMessage errorMsg = validator.isValid(SQLUtil.quoteWhenNecessary(objectName, databaseIdentifier),
                    IIdentifierValidator.IDENTIFIER_TYPE_UNKNOW, databaseIdentifier);
            return errorMsg;
        }
        return null;
    }
}
