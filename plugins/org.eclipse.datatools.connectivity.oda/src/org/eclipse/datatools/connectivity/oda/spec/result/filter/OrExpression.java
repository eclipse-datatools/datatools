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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;

/**
 * A runtime composite filter expression whose child expressions are combined by the Or boolean operator.  
 * This composite expression is evaluated to be true if any of its child expressions is evaluated as true.  
 * @since 3.2 (DTP 1.7)
 */
public class OrExpression extends CompositeExpression
{
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.result.filter.CompositeExpression#validateChildren(org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    @Override
    protected void validateChildren( ValidationContext context )
            throws OdaException
    {
        validateMinElements( 2 );
        super.validateChildren( context );
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals( final Object object )
    {
        if( !(object instanceof OrExpression) )
            return false;

        final OrExpression that = (OrExpression) object;
        return equals( this.getExpressions(), that.getExpressions() );
    }

}
