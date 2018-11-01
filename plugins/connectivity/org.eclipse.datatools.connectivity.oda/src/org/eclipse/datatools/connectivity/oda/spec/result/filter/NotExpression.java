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

package org.eclipse.datatools.connectivity.oda.spec.result.filter;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil;

/**
 * A negated runtime filter expression.
 * @since 3.2 (DTP 1.7)
 */
public class NotExpression extends CompositeExpression
{
    public NotExpression( FilterExpression expression ) 
    {
        Assert.isNotNull( expression );
        super.add( expression );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.filter.CompositeExpression#add(org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression)
     */
    @Override
    public CompositeExpression add( FilterExpression expression )
    {
        throw new IllegalStateException( Messages.bind( Messages.querySpec_MAX_ONE_NEGATING_EXPR, 
                getName() ));
    }

    /**
     * Returns the filter expression to be negated.
     * @return  any type of filter expression
     */
    public FilterExpression getNegatingExpression()
    {
        return ( childCount() == 0 ) ?
                    null :
                    this.getExpressions().get( 0 );
    }
   
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.filter.CompositeExpression#validateChildren(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    protected void validateChildren( ValidationContext context )
            throws OdaException
    {
        try
        {
            validateMinElements( 1 );

            // validate the child expression
            FilterExpression negatingExpr = getNegatingExpression();
            if( ! negatingExpr.isNegatable() )
            {
                throw ValidatorUtil.newOdaException( Messages.bind( Messages.querySpec_NOT_EXPR_INCOMPATIBLE,
                            negatingExpr.getName(), getName() ), 
                        negatingExpr.getQualifiedId() );
            }

            super.validateChildren( context, false );
        }
        catch( OdaException ex )
        {
            // set this NotExpression as a root cause of exception
            throw ValidatorUtil.newFilterExprException( this, ex );
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals( final Object object ) 
    {
        if( !(object instanceof NotExpression) )
            return false;

        final NotExpression that = (NotExpression) object;
        return getNegatingExpression().equals( that.getNegatingExpression() );
    }
    
}
