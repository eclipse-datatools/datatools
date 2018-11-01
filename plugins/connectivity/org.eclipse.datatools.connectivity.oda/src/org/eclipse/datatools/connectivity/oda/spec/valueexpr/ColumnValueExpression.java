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

package org.eclipse.datatools.connectivity.oda.spec.valueexpr;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable.VariableType;
import org.eclipse.datatools.connectivity.oda.spec.result.ColumnIdentifier;
import org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil;

/**
 * A concrete value expression whose value is based on 
 * that of a referenced result set column.
 * <br>An ODA driver that implements the ODA dynamicResultSet extension point
 * must support this type of expressions defined in a 
 * {@link org.eclipse.datatools.connectivity.oda.spec.QuerySpecification}.
 * @since 3.2.2 (DTP 1.7.2)
 */
public class ColumnValueExpression extends AtomicValueExpression
{
    private ColumnIdentifier m_columnRef;
    
    public ColumnValueExpression( ColumnIdentifier columnRef )
    {
        if( columnRef == null )
            throw new NullPointerException( Messages.bind( Messages.querySpec_NULL_CONSTRUCTOR_ARG, 
                    ColumnValueExpression.class.getName() ));
        if( ! columnRef.isValid() )
            throw new IllegalArgumentException( Messages.bind( Messages.querySpec_ILLEGAL_CONSTRUCTOR_ARG, 
                    ColumnValueExpression.class.getName() ) );
        
        m_columnRef = columnRef;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#getName()
     */
    @Override
    public String getName()
    {
        if( m_columnRef.hasNameExpression() )
            return m_columnRef.getNameExpression();    // use the column name or expression
        return m_columnRef.getNumber().toString();  // use the column ordinal value
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.valueexpr.AtomicValueExpression#getVariableType()
     */
    @Override
    public VariableType getVariableType()
    {
        return VariableType.RESULT_SET_COLUMN;
    }

    /**
     * Gets the referenced column number, if specified. 
     * @return  column number, or null if not specified
     */
    public Integer getColumnNumber()
    {
        return getColumnReference().getNumber();
    }

    /**
     * Gets the referenced column name or expression.
     * @return  column name or expression
     */
    public String getColumnExpression()
    {
        return getColumnReference().getNameExpression();
    }
    
    /**
     * Gets the column reference of this value expression.
     * @return 
     */
    public ColumnIdentifier getColumnReference()
    {
        return m_columnRef;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        if( ! getColumnReference().isValid() )
            throw ValidatorUtil.newValueExprException( 
                    Messages.bind( Messages.querySpec_INVALID_COLUMN_IDENTIFIER, getColumnReference() ), 
                    this );
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( getName() + SPACE );
        buffer.append( getColumnReference() );
        return buffer.toString();
    }

}
