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
import org.eclipse.datatools.connectivity.oda.spec.ValueExpression;

/**
 * A complex value expression with a nested expression.
 * @since 3.2.2 (DTP 1.7.2)
 */
public class NestedValueExpression extends ValueExpression
{
    private static final String NAME_PREFIX = "NESTED_"; //$NON-NLS-1$
    
    private ValueExpression m_nestedExpr;
    
    public NestedValueExpression( ValueExpression nestedExpr )
    {
        if( nestedExpr == null )
            throw new NullPointerException( Messages.bind( Messages.querySpec_NULL_CONSTRUCTOR_ARG, 
                    NestedValueExpression.class.getName() ) );
        
        m_nestedExpr = nestedExpr;
    }

    /**
     * Returns the nested value expression.
     * @return 
     */
    public ValueExpression getNestedExpression()
    {
        return m_nestedExpr;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    public void validateSyntax( ValidationContext context ) throws OdaException
    {
        m_nestedExpr.validateSyntax( context );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#getName()
     */
    @Override
    public String getName()
    {
        return NAME_PREFIX + m_nestedExpr.getName();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#getOdaDataType()
     */
    @Override
    public Integer getOdaDataType()
    {
        // no data type explicitly specified, derive from the nested expression
        if( super.getOdaDataType() == null )
            return m_nestedExpr.getOdaDataType();
        
        return super.getOdaDataType();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.ValueExpression#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( NestedValueExpression.class.getSimpleName() + SPACE + LEFT_PARANTHESIS );
        buffer.append( m_nestedExpr.getName() + RIGHT_PARANTHESIS );
        buffer.append( "\n      nestedExpr=" + LEFT_CURLY_BRACKET + m_nestedExpr + RIGHT_CURLY_BRACKET ); //$NON-NLS-1$
        return buffer.toString();
    }

}
