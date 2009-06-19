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

package org.eclipse.datatools.connectivity.oda.spec;

/**
 * <strong>EXPERIMENTAL</strong>.
 * Represents the variable of an expression defined in an ODA query specification.
 * <br>It is the responsibility of an ODA driver to resolve a variable,
 * when evaluating it with an expression.
 * This may be extended to represent complex types of variables.
 * @since 3.2 (DTP 1.7)
 */
public class ExpressionVariable
{
    public enum VariableType
    {
        RESULT_SET_COLUMN,          // default
        INSTANCE_OF,
        QUERY_EXPRESSION
//        CUBE_DIMENSION_MEMBER,      // reserved
//        CUBE_DIMENSION_ATTRIBUTE,   // reserved
//        CUBE_MEASURE                // reserved
    }
    
    private String m_identifier;
    private VariableType m_variableType;
    private Integer m_nativeDataTypeCode; 
    private String m_alias;
    
    /**
     * Constructor for an expression variable, which is one of the columns in a result set.
     * @param variableIdentfier the name or expression that identifies the variable to use in evaluating an expression.
     *              The identifier must be in a format recognized by the ODA data provider that will be 
     *              evaluating the expression.  
     */
    public ExpressionVariable( String variableIdentfier )
    {
        this( variableIdentfier, VariableType.RESULT_SET_COLUMN );
    }

    /**
     * Constructor for an expression variable of the specified type.
     * @param variableIdentfier the name or expression that identifies the variable to use in evaluating an expression.
     *              The identifier must be in a format recognized by the ODA data provider that will be 
     *              evaluating the expression.  
     * @param type  the type of variable; its value must be one of the pre-defined {@link VariableType}
     */
    public ExpressionVariable( String variableIdentfier, VariableType type )
    {
        m_identifier = variableIdentfier;
        setType( type );
    }
    
    /**
     * Gets the name or expression that identifies the variable to use in evaluating an expression.
     * @return  the name or expression of the variable
     */
    public String getIdentifier()
    {
        return m_identifier; 
    }
    
    /**
     * Gets the variable alias.  
     * @return  the alias, or the identifier if no alias is specified
     */
    public String getAlias()
    {
        return ( m_alias != null ) ? m_alias : getIdentifier();
    }

    /**
     * Specifies the variable alias.
     * @param alias the alias to set; may be null
     */
    public void setAlias( String alias )
    {
        m_alias = alias;
    }

    /**
     * Gets the type of variable, e.g. a column in a query result set.
     * @return  a VariableType enum value;
     *          default value is RESULT_COLUMN type if not specified
     *          by {@link #setVariableType(VariableType)}.
     */
    public VariableType getType()
    {
        return m_variableType;
    }
    
    /**
     * Sets the type of variable, e.g. a column in a query result set.
     * @param type  a VariableType enum value
     */
    public void setType( VariableType type )
    {
        m_variableType = type;
    }

    /**
     * Gets the data provider specific code value of this variable's data type.
     * The valid values are implementation-specific.  
     * This serves as an optional hint that may have been specified at design time.
     * @return      the native data type code of this variable, or null if not available
     */
    public Integer getNativeDataType()
    {
        return m_nativeDataTypeCode;
    }

    /**
     * Sets the data type of this variable in a data provider specific code value.
     * The valid values are implementation-specific.  
     * This serves as an optional hint that may have been specified at design time,
     * and may be ignored by the runtime driver, if appropriate.
     * @param nativeDataTypeCode the native data type code of this variable;
     *              may be null to unset current value
     */
    public void setNativeDataType( Integer nativeDataTypeCode )
    {
        m_nativeDataTypeCode = nativeDataTypeCode;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( getClass().getSimpleName() + " identifier: " ); //$NON-NLS-1$
        buffer.append( m_identifier );

        buffer.append( ", type: " ); //$NON-NLS-1$
        switch( m_variableType)
        {
            case RESULT_SET_COLUMN: buffer.append( "ResultSetColumn" ); break; //$NON-NLS-1$
            case INSTANCE_OF:   buffer.append( "InstanceOf" ); break; //$NON-NLS-1$
            case QUERY_EXPRESSION: buffer.append( "QueryExpression" ); break; //$NON-NLS-1$
//            case CUBE_DIMENSION_MEMBER: buffer.append( "CubeDimensionMember" ); break; //$NON-NLS-1$
            default: buffer.append( m_variableType.ordinal() ); break;
        }
        return buffer.toString();
    }
    
}
