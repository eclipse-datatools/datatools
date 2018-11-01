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

package org.eclipse.datatools.connectivity.oda.spec.valueexpr;

import java.sql.Types;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.ValueExpression;

/**
 * A complex value expression whose value is resolved by combining
 * two value expressions with a combined operator.
 * @since 3.2.2 (DTP 1.7.2)
 */
public class CombinedValueExpression extends ValueExpression
{
    private ValueExpression m_leftExpr;
    private ValueExpression m_rightExpr;
    private CombinedValueExpressionOperator m_operator;
    
    public CombinedValueExpression( ValueExpression leftExpr, 
            CombinedValueExpressionOperator operator, 
            ValueExpression rightExpr )
    {
        if( leftExpr == null || rightExpr == null || operator == null )
            throw new NullPointerException( Messages.bind( Messages.querySpec_NULL_CONSTRUCTOR_3ARGS, 
                    new Object[]{CombinedValueExpression.class.getName(),leftExpr, operator, rightExpr} ));

        m_leftExpr = leftExpr;
        m_rightExpr = rightExpr;
        m_operator = operator;
    }

    /**
     * Returns the left value expression.
     * @return  an instance of ValueExpression
     */
    public ValueExpression getLeftExpression()
    {
        return m_leftExpr;
    }

    /**
     * Returns the right value expression.
     * @return an instance of ValueExpression
     */
    public ValueExpression getRightExpression()
    {
        return m_rightExpr;
    }

    /**
     * Returns the combined operator.
     * @return 
     */
    public CombinedValueExpressionOperator getCombinedOperator()
    {
        return m_operator;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        m_leftExpr.validateSyntax( context );
        m_rightExpr.validateSyntax( context );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#getName()
     */
    @Override
    public String getName()
    {
        return m_leftExpr.getName() + 
        ExpressionVariable.ALIAS_SEPARATOR + 
        m_operator.getLiteral() + 
        ExpressionVariable.ALIAS_SEPARATOR + 
        m_rightExpr.getName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#getOdaDataType()
     */
    @Override
    public Integer getOdaDataType()
    {
        // no data type explicitly specified
        if( super.getOdaDataType() != null )
            return super.getOdaDataType();

        // not a built-in combined operator type
        if( CombinedValueExpressionOperator.get( m_operator.getId() ) == null )
            return UNKNOWN_ODA_DATA_TYPE;

        // derive from the combined operator and/or combined expressions
        // compare operator by id in case it is a custom contributed instance
        if( m_operator.getId().equals( CombinedValueExpressionOperator.CONCATENATE ) )  // string concatenation
            return Integer.valueOf( Types.CHAR );
        
        Integer leftOdaDataType = m_leftExpr.getOdaDataType();
        Integer rightOdaDataType = m_rightExpr.getOdaDataType();
        if( leftOdaDataType == rightOdaDataType || 
            (leftOdaDataType != null && leftOdaDataType.equals( rightOdaDataType )) )
            return rightOdaDataType;

        // for remaining built-in combined operator types

        if( leftOdaDataType == null || leftOdaDataType == UNKNOWN_ODA_DATA_TYPE ||
            rightOdaDataType == null || rightOdaDataType == UNKNOWN_ODA_DATA_TYPE )
            return UNKNOWN_ODA_DATA_TYPE;

        if( isNumeric( leftOdaDataType ) && isNumeric( rightOdaDataType ) )
        {
            int leftOdaDataTypeCode = leftOdaDataType.intValue();
            int rightOdaDataTypeCode = rightOdaDataType.intValue();

            // combined data type is the most scaled data type of the 2 operands
            if( leftOdaDataTypeCode == Types.DECIMAL || rightOdaDataTypeCode == Types.DECIMAL )
                return Integer.valueOf( Types.DECIMAL );
            if( leftOdaDataTypeCode == Types.DOUBLE || rightOdaDataTypeCode == Types.DOUBLE )
                return Integer.valueOf( Types.DOUBLE );
            if( leftOdaDataTypeCode == Types.INTEGER || rightOdaDataTypeCode == Types.INTEGER )
                return Integer.valueOf( Types.INTEGER );
        }
        
        // TODO - handles other data types besides numeric ones      
        return UNKNOWN_ODA_DATA_TYPE;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( CombinedValueExpression.class.getSimpleName() + SPACE );
        buffer.append( m_leftExpr.getName() + SPACE + m_operator.getLiteral() + SPACE + m_rightExpr.getName() );
        buffer.append( "\n     leftExpr=" + LEFT_CURLY_BRACKET + m_leftExpr + RIGHT_CURLY_BRACKET ); //$NON-NLS-1$
        buffer.append( "\n     operator= " + m_operator ); //$NON-NLS-1$
        buffer.append( "\n     rightExpr=" + LEFT_CURLY_BRACKET + m_rightExpr + RIGHT_CURLY_BRACKET ); //$NON-NLS-1$
        return buffer.toString();
    }
   
}
