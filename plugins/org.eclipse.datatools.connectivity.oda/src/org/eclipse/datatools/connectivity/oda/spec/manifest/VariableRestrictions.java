/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    public static final String ATTR_RESULT_COLUMN_DATA_TYPE = "odaScalarDataType"; //$NON-NLS-1$
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
                    String odaDataTypeLiteral = dataTypeElements[i].getAttribute( ATTR_RESULT_COLUMN_DATA_TYPE );
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
        return m_restrictedVariableTypes.contains( type );
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
        return ( getRestrictedDataTypeList(type).size() > 0 );
    }
    
    /**
     * Indicates whether this supports the specified ODA data type of the specified type of variable.
     * @param type  the type of variable; its value must be one of the pre-defined {@link ExpressionVariable#VariableType}
     * @param odaDataType   ODA scalar data type code
     * @return  true if the specified ODA data type is supported; false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean supportsOdaDataType( VariableType type, int odaDataType )
    {
        if( type == VariableType.INSTANCE_OF )  // this type does not support odaDataType
            return false;
        if( ! supportsVariableType( type ) )
            return false;
        
        if( ! hasDataTypeRestrictions( type ) )
            return true;
        
        Iterator<Integer> iter = getRestrictedDataTypeList(type).iterator();
        while( iter.hasNext() )
        {
            if( iter.next().intValue() == odaDataType )
                return true;
        }
        
        // no matching data type found in variable restricted type list
        return false;
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
                restrictedDataTypes = new ArrayList<Integer>();
            else if( type == VariableType.QUERY_EXPRESSION )
                restrictedDataTypes = new ArrayList<Integer>();
            else // if( type == VariableType.INSTANCE_OF )
                restrictedDataTypes = new ArrayList<String>();
            
            m_restrictedDataTypesByVarType.put( type, restrictedDataTypes );
        }
        return restrictedDataTypes;
    }

}
