/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.spec.manifest;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable.VariableType;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataTypeMapping;

/**
 * Represents the restrictions on the types of expression variable that can be applied
 * with an extension-defined expression, as specifed in an extension of the
 * <i>org.eclipse.datatools.connectivity.oda.dynamicResultSet</i> extension point.
 * @since 3.2 (DTP 1.7)
 */
public class VariableRestrictions
{
    public static final String SUB_ELEMENT_VARIABLE_RESTRICTION = "variableRestriction"; //$NON-NLS-1$
    public static final String ATTR_VARIABLE_TYPE = "variableType"; //$NON-NLS-1$
    public static final String ATTR_VARIABLE_TYPE_RESULT_COLUMN = "ResultSetColumn"; //$NON-NLS-1$
    public static final String ATTR_VARIABLE_TYPE_INSTANCE_OF = "InstanceOf"; //$NON-NLS-1$
    public static final String ATTR_VARIABLE_TYPE_QUERY_EXPR = "QueryExpression"; //$NON-NLS-1$

    public static final String SUB_ELEMENT_VARIABLE_RESTRICTION_ODA_DATA_TYPE = "variableOdaDataTypeRestriction"; //$NON-NLS-1$
    public static final String ATTR_ODA_SCALAR_DATA_TYPE = "odaScalarDataType"; //$NON-NLS-1$
    public static final String SUB_ELEMENT_VARIABLE_RESTRICTION_INSTANCE = "variableClassRestriction"; //$NON-NLS-1$  
    public static final String ATTR_RESULT_INSTANCE_TYPE = "class"; //$NON-NLS-1$

    private List<VariableType> m_restrictedVariableTypes;
    @SuppressWarnings("unchecked")
    private Map<VariableType,List> m_restrictedDataTypesByVarType;

    VariableRestrictions( IConfigurationElement exprElement )
    {
        m_restrictedDataTypesByVarType = new HashMap<VariableType,List>(3);
        m_restrictedVariableTypes = processRestrictedVariableTypes( exprElement, m_restrictedDataTypesByVarType );
    }
    
    @SuppressWarnings("unchecked")
    private static List<VariableType> processRestrictedVariableTypes( IConfigurationElement exprElement, 
            Map<VariableType, List> restrictedDataTypesByVarType )
    {        
        IConfigurationElement[] varRestrictionElements = exprElement.getChildren( SUB_ELEMENT_VARIABLE_RESTRICTION );
        
        int arraySize = varRestrictionElements.length;
        if( arraySize == 0 )
            arraySize = 1;
        List<VariableType> restrictedVariableTypes = new ArrayList<VariableType>( arraySize );
        
        for( int i = 0; i < varRestrictionElements.length; i++ )
        {
            VariableType aRestrictedVarType = VariableType.RESULT_SET_COLUMN;  // default if no value is specified
            String value = varRestrictionElements[i].getAttribute( ATTR_VARIABLE_TYPE );
            if( value != null && value.length() > 0 )
            {
                if( value.equalsIgnoreCase( ATTR_VARIABLE_TYPE_RESULT_COLUMN ) )
                    aRestrictedVarType = VariableType.RESULT_SET_COLUMN;
                else if( value.equalsIgnoreCase( ATTR_VARIABLE_TYPE_QUERY_EXPR ) )
                    aRestrictedVarType = VariableType.QUERY_EXPRESSION;
                else if( value.equalsIgnoreCase( ATTR_VARIABLE_TYPE_INSTANCE_OF ) )
                    aRestrictedVarType = VariableType.INSTANCE_OF;
                else // an unexpected value
                    continue;   // skip
            }
            restrictedVariableTypes.add( aRestrictedVarType );
            
            processRestrictedDataTypes( restrictedDataTypesByVarType, aRestrictedVarType, varRestrictionElements[i] );
        }

        if( restrictedVariableTypes.isEmpty() )
            restrictedVariableTypes.add( VariableType.RESULT_SET_COLUMN );    // default if no valid value is specified
        return restrictedVariableTypes;
    }
    
    @SuppressWarnings("unchecked")
    private static void processRestrictedDataTypes( Map<VariableType, List> restrictedDataTypesByVarType, VariableType type, IConfigurationElement varRestrictionElement )
    {
        if( type == VariableType.RESULT_SET_COLUMN || type == VariableType.QUERY_EXPRESSION )
        {
            IConfigurationElement[] dataTypeElements = varRestrictionElement.getChildren( SUB_ELEMENT_VARIABLE_RESTRICTION_ODA_DATA_TYPE );    
            if( dataTypeElements.length > 0 )
            {
                List<Integer> restrictedDataTypes = new ArrayList<Integer>( dataTypeElements.length );
                restrictedDataTypesByVarType.put( type, restrictedDataTypes );
                
                for( int i = 0; i < dataTypeElements.length; i++ )
                {
                    String odaDataTypeLiteral = dataTypeElements[i].getAttribute( ATTR_ODA_SCALAR_DATA_TYPE );
                    int odaDataTypeCode = DataTypeMapping.toOdaDataTypeCode( odaDataTypeLiteral );
                    if( odaDataTypeCode == Types.NULL )
                        continue;   // ignore
                    
                    restrictedDataTypes.add( new Integer( odaDataTypeCode ) );
                }
            }
        }
        else if( type == VariableType.INSTANCE_OF )   // instance type restrictions
        {
            IConfigurationElement[] dataTypeElements = varRestrictionElement.getChildren( SUB_ELEMENT_VARIABLE_RESTRICTION_INSTANCE );    
            if( dataTypeElements.length > 0 )
            {
                List<String> restrictedClassTypes = new ArrayList<String>( dataTypeElements.length );
                restrictedDataTypesByVarType.put( type, restrictedClassTypes );
                
                for( int i = 0; i < dataTypeElements.length; i++ )
                {
                    String className = dataTypeElements[i].getAttribute( ATTR_RESULT_INSTANCE_TYPE );
                    if( className == null || className.length() == 0 )
                        continue;   // ignore
                    
                    restrictedClassTypes.add( className );
                }
            }
        }
        
        // ignore data type restriction on other unexpected variable types        
        // TODO - log warning
    }

    /**
     * Indicates whether the specified type of variable can be applied for evaluation with this expression type.
     * @param type  the type of variable; its value must be one of the pre-defined {@link ExpressionVariable#VariableType}
     * @return  true if the specified variable type is supported by this type of custom expression; false otherwise
     */
    public boolean supportsVariableType( VariableType type )
    {
        if( m_restrictedVariableTypes.contains( type ) )
            return true;     // support is explicitly specified, done
        
        // A QueryExpression variable type is a superset that covers any value expression types 
        // including a ResultSetColumn reference
        if( type == VariableType.RESULT_SET_COLUMN )
            return m_restrictedVariableTypes.contains( VariableType.QUERY_EXPRESSION );

        return false;
    }

    /**
     * Returns the restrictions on the type of variables that can be applied with this type of expression.
     * @return an array of {@link VariableType} supported by this expression type
     */
    public VariableType[] getRestrictedVariableTypes()
    {
        return m_restrictedVariableTypes.toArray( new VariableType[ m_restrictedVariableTypes.size() ] );
    }
    
    /**
     * Indicates whether this has any restrictions on the data type of 
     * the specified type of variable.
     * @param type  the type of variable; its value must be one of the pre-defined {@link ExpressionVariable#VariableType}
     * @return  true if data type restrictions exists; false if this has no data type restrictions
     */
    public boolean hasDataTypeRestrictions( VariableType type )
    {
        if( getRestrictedDataTypeList(type).size() > 0 )    // has explicit data type restrictions
            return true;
        
        // result column data type restrictions are implied from those of VariableType.QUERY_EXPRESSION, 
        // if none explicitly defined
        if( type == VariableType.RESULT_SET_COLUMN )
            return getRestrictedDataTypeList(VariableType.QUERY_EXPRESSION).size() > 0;

        return false;
    }
    
    /**
     * Indicates whether this supports the specified ODA data type of the specified type of variable.
     * @param varType  the type of variable; its value must be one of the pre-defined {@link ExpressionVariable#VariableType}
     * @param odaDataType   ODA scalar data type code
     * @return  true if the specified ODA data type is supported; false otherwise
     */
    public boolean supportsOdaDataType( VariableType varType, int odaDataType )
    {
        if( varType == VariableType.INSTANCE_OF )  // this type does not support odaDataType
            return false;
        if( ! supportsVariableType( varType ) )
            return false;
        
        if( ! hasDataTypeRestrictions( varType ) )
            return true;
        
        int[] restrictedDataTypes = ( varType == VariableType.RESULT_SET_COLUMN ) ?
                getResultColumnRestrictedOdaDataTypes() : getQueryExpressionRestrictedOdaDataTypes();
        for( int i=0; i < restrictedDataTypes.length; i++ )
        {
            if( restrictedDataTypes[i] == odaDataType )
                return true;
        }
        
        // no matching data type found in variable restricted type list
        return false;
    }
    
    /**
     * A convenient method to indicate whether this supports all the ODA numeric data types 
     * of the specified type of variable.
     * @param varType  the type of variable; its value must be one of the pre-defined {@link ExpressionVariable#VariableType}
     * @return  true if all ODA numeric data types are supported; false otherwise
     * @see #supportsOdaDataType(VariableType, int)
     */
    public boolean supportsOdaNumericDataTypes( VariableType varType )
    {
        return supportsOdaDataTypes( varType, DataTypeMapping.ODA_NUMERIC_DATA_TYPE_CODES );
    }
    
    /**
     * A convenient method to indicate whether this supports the ODA string/character data type 
     * of the specified type of variable.
     * @param varType  the type of variable; its value must be one of the pre-defined {@link ExpressionVariable#VariableType}
     * @return  true if the ODA string/character data type is supported; false otherwise
     * @see #supportsOdaDataType(VariableType, int)
     */
    public boolean supportsOdaStringDataTypes( VariableType varType )
    {
        return supportsOdaDataTypes( varType, DataTypeMapping.ODA_STRING_DATA_TYPE_CODES );
    }
    
    /**
     * A convenient method to indicate whether this supports all the ODA date and/or datetime data types 
     * of the specified type of variable.
     * @param varType  the type of variable; its value must be one of the pre-defined {@link ExpressionVariable#VariableType}
     * @return  true if all ODA date and/or time data types are supported; false otherwise
     * @see #supportsOdaDataType(VariableType, int)
     */
    public boolean supportsOdaDatetimeDataTypes( VariableType varType )
    {
        return supportsOdaDataTypes( varType, DataTypeMapping.ODA_DATETIME_DATA_TYPE_CODES );
    }
    
    /**
     * A convenient method to indicate whether this supports the ODA boolean data type 
     * of the specified type of variable.
     * @param varType  the type of variable; its value must be one of the pre-defined {@link ExpressionVariable#VariableType}
     * @return  true if the ODA boolean data type is supported; false otherwise
     * @see #supportsOdaDataType(VariableType, int)
     */
    public boolean supportsOdaBooleanDataTypes( VariableType varType )
    {
        return supportsOdaDataTypes( varType, DataTypeMapping.ODA_BOOLEAN_DATA_TYPE_CODES );
    }
    
    private boolean supportsOdaDataTypes( VariableType varType, int[] odaDataTypeCodes )
    {
        for( int i=0; i < odaDataTypeCodes.length; i++ )
        {
            if( ! supportsOdaDataType( varType, odaDataTypeCodes[i] ) )
                return false;
        }
        return true;    // supports all the specified data types
    }
    
    /**
     * Indicates whether this supports the specified class of the specified type of variable.
     * @param type  the type of variable; its value must be one of the pre-defined {@link ExpressionVariable#VariableType}
     * @param className the type of instance variable
     * @return  true if the specified class is supported; false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean supportsClassType( VariableType type, String className )
    {
        if( type != VariableType.INSTANCE_OF )    // this type does not support class type
            return false;
        if( ! supportsVariableType( type ) )
            return false;

        if( ! hasDataTypeRestrictions( type ) )
            return true;
        
        Iterator<String> iter = getRestrictedDataTypeList(type).iterator();
        while( iter.hasNext() )
        {
            if( iter.next().equalsIgnoreCase( className ) )
                return true;
        }
        
        // no matching type found in variable restricted type list
        return false;
    }
    
    /**
     * Gets the collection of ODA data type(s) of result set column variables that can be applied with this expression.
     * For example, a pattern matching expression may only be applicable to a result set column with a String data type.
     * @return  an array of ODA data type code(s) allowed;
     *          an empty array indicates this expression has no data type restrictions, i.e. all data types are compatible.     
     */
    @SuppressWarnings("unchecked")
    public int[] getResultColumnRestrictedOdaDataTypes()
    {
        List<Integer> restrictedDataTypeList = 
            (List<Integer>) getRestrictedDataTypeList( VariableType.RESULT_SET_COLUMN );
        
        // result column data type restrictions are implied from those of VariableType.QUERY_EXPRESSION, 
        // if none explicitly defined
        if( restrictedDataTypeList.isEmpty() && ! m_restrictedVariableTypes.contains( VariableType.RESULT_SET_COLUMN ) )
            restrictedDataTypeList = (List<Integer>) getRestrictedDataTypeList( VariableType.QUERY_EXPRESSION );

        return convertListToArrayInt( restrictedDataTypeList );
    }
    
    /**
     * Gets the collection of ODA data type(s) of query expression variables that can be applied with this expression.
     * @return  An array of ODA data type code(s) allowed;
     *          an empty array indicates this expression has no data type restrictions, i.e. all data types are compatible.
     */
    @SuppressWarnings("unchecked")
    public int[] getQueryExpressionRestrictedOdaDataTypes()
    {
        List<Integer> restrictedDataTypeList = 
            (List<Integer>) getRestrictedDataTypeList( VariableType.QUERY_EXPRESSION );
        
        return convertListToArrayInt( restrictedDataTypeList );
    }

    /* 
     * Converts to an array of int.
     */
    private static int[] convertListToArrayInt( List<Integer> restrictedDataTypeList )
    {
        int[] restrictedTypeCodes = new int[restrictedDataTypeList.size()];
        for( int i=0; i < restrictedDataTypeList.size(); i++ )
        {
            restrictedTypeCodes[i] = restrictedDataTypeList.get( i ).intValue();
        }
        return restrictedTypeCodes;
    }
    
    /**
     * Gets the collection of class names of instance variables that can be applied with this expression.
     * @return  An array of class names allowed;
     *          an empty array indicates this expression has no type restrictions, i.e. all types are compatible.
     */
    @SuppressWarnings("unchecked")
    public String[] getInstanceRestrictedTypes()
    {
        List<String> restrictedDataTypeList = 
            (List<String>) getRestrictedDataTypeList( VariableType.INSTANCE_OF );
        return restrictedDataTypeList.toArray( new String[ restrictedDataTypeList.size() ] );
    }
    
    @SuppressWarnings("unchecked")
    private List getRestrictedDataTypeList( VariableType type )
    {
        if( m_restrictedDataTypesByVarType == null )
            m_restrictedDataTypesByVarType = new HashMap<VariableType,List>();
        
        List restrictedDataTypes = m_restrictedDataTypesByVarType.get( type );
        if( restrictedDataTypes == null )
        {
            if( type == VariableType.RESULT_SET_COLUMN )
                restrictedDataTypes = new ArrayList<Integer>(0);
            else if( type == VariableType.QUERY_EXPRESSION )
                restrictedDataTypes = new ArrayList<Integer>(0);
            else // if( type == VariableType.INSTANCE_OF )
                restrictedDataTypes = new ArrayList<String>(0);
            
            m_restrictedDataTypesByVarType.put( type, restrictedDataTypes );
        }
        return restrictedDataTypes;
    }

}
