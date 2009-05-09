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

package org.eclipse.datatools.connectivity.oda.spec.result.filter;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;

/**
 * A negated runtime filter expression.
 * @since 3.2 (DTP 1.7)
 */
public class NotExpression extends FilterExpression
{
    private FilterExpression m_expression;

    /**
     * Gets this expression's name.
     * @return the simple name of this class
     */
    public static String getName()
    {
        return NotExpression.class.getSimpleName();
    }

    public NotExpression( FilterExpression expression ) 
    {
        Assert.isNotNull( expression );
        m_expression = expression;
    }

    /**
     * Returns the filter expression to be negated.
     * @return  any type of filter expression
     */
    public FilterExpression getNegatingExpression()
    {
        return m_expression;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#validate(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( ValidationContext context ) throws OdaException
    {
        if( context != null && context.getValidator() != null )
            context.getValidator().validate( this, context );

        // validate the child expression
        if( m_expression == null )
            throw new OdaException( Messages.bind( Messages.querySpec_NOT_EXPR_MISSING_CHILD, this ));

        if( ! m_expression.isNegatable() )
            throw new OdaException( Messages.bind( Messages.querySpec_NOT_EXPR_INCOMPATIBLE,
                    m_expression.getQualifiedId(), getQualifiedId() ));

        m_expression.validate( context );
    }
   
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals( final Object object ) 
    {
        if( !(object instanceof NotExpression) )
            return false;

        final NotExpression that = (NotExpression) object;
        return m_expression.equals( that.m_expression );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression#isNegatable()
     */
    @Override
    public boolean isNegatable()
    {
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return super.toString() + " (" + m_expression + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }
    
}
