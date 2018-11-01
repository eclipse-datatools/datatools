/*
 *************************************************************************
 * Copyright (c) 2009, 2010 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec;

import org.eclipse.datatools.connectivity.oda.spec.result.ColumnIdentifier;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.ColumnValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.FunctionValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.valueexpr.SimpleValueExpression;

/**
 * Represents the variable of an expression defined in an ODA query specification.
 * <br>It is the responsibility of an ODA driver to resolve a variable,
 * when evaluating it with an expression.
 * This may be extended to represent complex types of variables.
 * @since 3.3 (DTP 1.8)
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

    public static final String ALIAS_SEPARATOR = "_"; //$NON-NLS-1$
    private static final String FUNCTION_ALIAS_PREFIX = "F_"; //$NON-NLS-1$
       
    private String m_alias;
    private ValueExpression m_valueExpr;
    
    /**
     * Constructor for an expression variable that references a result set column by name or expression.
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
    public ExpressionVariable( String variableIdentfier, VariableType varType )
    {
        switch( varType )
        {
            case RESULT_SET_COLUMN: 
                setColumnExpression( new ColumnIdentifier( variableIdentfier ) ); break;
            case QUERY_EXPRESSION:
            default:
                setValueExpression( new SimpleValueExpression( variableIdentfier )); break;
        }
    }

    /**
     * Constructor for an expression variable that references a result set column by its identifier.
     * @param columnIdentifier  a column identifier
     */
    public ExpressionVariable( ColumnIdentifier columnIdentifier )
    {
        setColumnExpression( columnIdentifier );
    }
    
    /**
     * Constructor for an expression variable that references the specified value expression.
     * @param valueExpr a concrete ValueExpression instance
     */
    public ExpressionVariable( ValueExpression valueExpr )
    {
        setValueExpression( valueExpr );
    }
    
    private void setColumnExpression( ColumnIdentifier columnIdentifier )
    {
        setValueExpression( new ColumnValueExpression( columnIdentifier ) );
    }
    
    private void setValueExpression( ValueExpression valueExpr )
    {
        m_valueExpr = valueExpr;
    }
    
    /**
     * Returns the value expression of this variable.
     * @return an instance of a concrete ValueExpression
     */
    public ValueExpression getValueExpression()
    {
        return m_valueExpr;
    }

    /**
     * Gets the name or expression that identifies the variable in evaluating an expression.
     * @return  the name or expression of the variable
     */
    public String getIdentifier()
    {
        return ( m_valueExpr != null ) ? m_valueExpr.getName() : m_alias; 
    }
    
    /**
     * Gets the variable alias.  
     * @return  the alias, or the identifier if no alias is specified
     */
    public String getAlias()
    {
        if( m_alias != null ) 
            return m_alias;
        
        // a function name is normally a keyword, which should not be used as an alias by default
        if( m_valueExpr instanceof FunctionValueExpression )
            return FUNCTION_ALIAS_PREFIX + m_valueExpr.getName();
        
        return getIdentifier();
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
     * Gets the type of this variable, e.g. a column in a query result set.
     * @return  a VariableType enum value
     */
    public VariableType getType()
    {
        if( m_valueExpr != null )
            return m_valueExpr.getVariableType();
        return VariableType.QUERY_EXPRESSION;   // default
    }
    
    /**
     * Sets the type of variable, e.g. a column in a query result set.
     * @param type  a VariableType enum value
     * @deprecated  replaced by deriving from the type of ValueExpression in this variable
     */
    public void setType( VariableType type )
    {
        // deprecated method
    }

    /**
     * Gets the data provider specific code value of this variable's data type.
     * The valid values are implementation-specific.  
     * This serves as an optional hint that may have been specified at design time.
     * @return      the native data type code of this variable, or null if not available
     * @deprecated  replaced by {@link #getOdaDataType()}
     */
    public Integer getNativeDataType()
    {
        // deprecated method
        return null;    // not available
    }

    /**
     * Sets the data type of this variable in a data provider specific code value.
     * The valid values are implementation-specific.  
     * This serves as an optional hint that may have been specified at design time,
     * and may be ignored by the runtime driver, if appropriate.
     * @param nativeDataTypeCode the native data type code of this variable;
     *              may be null to unset current value
     * @deprecated  replaced by {@link ValueExpression#setOdaDataType(Integer)}
     */
    public void setNativeDataType( Integer nativeDataTypeCode )
    {
        // deprecated method
    }

    /**
     * Gets the ODA-defined code value of this variable's data type. 
     * This serves as an optional hint that may have been specified by the variable expression.
     * @return      the ODA data type code of this variable, or null if not available
     */
    public Integer getOdaDataType()
    {
        if( m_valueExpr != null )
            return m_valueExpr.getOdaDataType();
        return null;    // not available
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( getClass().getSimpleName() + " alias= " + m_alias ); //$NON-NLS-1$
        buffer.append( ", valueExpression = [" ); //$NON-NLS-1$
        buffer.append( m_valueExpr );
        buffer.append( "]" ); //$NON-NLS-1$
        return buffer.toString();
    }
    
}
