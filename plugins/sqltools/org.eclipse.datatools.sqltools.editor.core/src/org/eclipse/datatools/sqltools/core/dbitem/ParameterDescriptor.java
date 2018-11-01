/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.core.dbitem;

import java.sql.DatabaseMetaData;
import java.sql.Types;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.result.Parameter;

/**
 * This class is the meta information of a parameter passed to a procedure call.
 * 
 * @author Yang Liu
 */
public class ParameterDescriptor
{
	
    String             _name;
    String             _typeName;
    String             _comment;
    int                _sqlDataType;
    short              _nullable;
    int                _parmType;
    int                _precision;
    short              _scale;
    DatabaseIdentifier _databaseIdentifier;
    String             _defaultValue;
    String             _sqlTypeNameFromParser; // parsed from editor

    /**
     * Constructs a ParameterDescriptor
     * 
     * @param databaseIdentifier
     * @param name parameter name
     * @param parmType
     * @see #org.eclipse.datatools.modelbase.sql.routines.ParameterMode
     * @param sqlDataType data type defined in <code>java.sql.Types</code>
     * @param precision parameter precision
     * @param scale parameter scale
     * @param typeName parameter type name
     * @param nullable
     * @see <code>DatabaseMetaData</code>
     * @param comment optional comment
     */
    public ParameterDescriptor(DatabaseIdentifier databaseIdentifier, String name, int parmType, int sqlDataType,
            int precision, short scale, String typeName, short nullable, String comment)
    {
        this._name = name;
        this._parmType = parmType;
        this._sqlDataType = sqlDataType;
        this._typeName = typeName;
        this._nullable = nullable;
        this._comment = comment;
        this._precision = precision;
        this._scale = scale;

        this._databaseIdentifier = databaseIdentifier;
    }

    /**
     * 
     * @return the parameter name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * Returns how <code>NULL</code> values are allowed.
     * 
     * @return either <code>DatabaseMetaData.procedureNoNulls</code>,
     *         <code>DatabaseMetaData.procedureNullableUnknown</code> or
     *         <code>DatabaseMetaData.procedureNullable</code>
     */
    protected int getNullable()
    {
        return _nullable;
    }

    /**
     * 
     * @return whether <code>NULL</code> values are allowed.
     */
    public boolean canBeNull()
    {
        return _nullable != DatabaseMetaData.procedureNoNulls;
    }

    /**
     * 
     * @return parameter in/out type
     */
    public int getParmType()
    {
        return _parmType;
    }

    /**
     * 
     * @param paramType parameter in/out type
     */
    public void setParmType(int paramType)
    {
        _parmType = paramType;
    }

    /**
     * 
     * @return parameter data type
     */
    public int getSqlDataType()
    {
        return _sqlDataType;
    }

    /**
     * 
     * @return parameter data type name
     */
    public String getTypeName()
    {
        return _typeName;
    }

    /**
     * Retrieves the designated parameter's number of decimal digits.
     * 
     * @return parameter data type precision
     */
    public int getPrecision()
    {
        return _precision;
    }

    /**
     * Retrieves the designated parameter's number of digits to right of the decimal point.
     * 
     * @return parameter data type scale
     */
    public short getScale()
    {
        return _scale;
    }

    /**
     * @return the string representation of the parameter in/out type
     */
    public String getParamTypeAsString()
    {
        // TODO MO
        // return ParameterMode.get(_parmType).toString();
    	 switch (_parmType)
         {
             case DatabaseMetaData.procedureColumnIn:
                 return Parameter.INPUT;
             case DatabaseMetaData.procedureColumnInOut:
                 return Parameter.IN_OUT;
             case DatabaseMetaData.procedureColumnOut:
                 return Parameter.OUTPUT;
             case DatabaseMetaData.procedureColumnResult:
                 return Parameter.RESULT;
             case DatabaseMetaData.procedureColumnReturn:
                 return Parameter.RETURN;
             case DatabaseMetaData.procedureColumnUnknown:
             default:
                 return Parameter.UNKNOWN;
         }
    }

    /**
     * @return whether the parameter is a string type
     */
    public boolean isStringType()
    {
        return _sqlDataType == Types.CHAR || _sqlDataType == Types.VARCHAR || _sqlDataType == Types.LONGVARCHAR
                || _sqlDataType == Types.DATE || _sqlDataType == Types.TIME || _sqlDataType == Types.TIMESTAMP;
    }

    /**
     * @return whether this is a output parameter
     */
    public boolean isOutput()
    {
        return _parmType == DatabaseMetaData.procedureColumnOut || _parmType == DatabaseMetaData.procedureColumnReturn;
    }

    /**
     * @return the default value.
     */
    public String getDefaultValue()
    {
        return _defaultValue;
    }

    /**
     * @param value The default value to set.
     */
    public void setDefaultValue(String value)
    {
        _defaultValue = value;
    }

    public DatabaseIdentifier getDatabaseIdentifier()
    {
        return _databaseIdentifier;
    }

    /**
     * @return the sql type name given by parser.
     */
    public String getSqlTypeNameFromParser()
    {
        return _sqlTypeNameFromParser;
    }

    /**
     * @param typeNameFromParser the sql type name given by parser.
     */
    public void setSqlTypeNameFromParser(String typeNameFromParser)
    {
        _sqlTypeNameFromParser = typeNameFromParser;
    }

    public void setNullable(short nullable)
    {
        _nullable = nullable;
    }

}
