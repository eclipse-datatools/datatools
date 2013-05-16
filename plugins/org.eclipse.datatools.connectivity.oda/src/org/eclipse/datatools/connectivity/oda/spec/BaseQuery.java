/*
 *************************************************************************
 * Copyright (c) 2013 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

/**
 * <strong>EXPERIMENTAL</strong><br>
 * Specification of a query base in a {@link QuerySpecification}, on which other 
 * query characteristics are to be applied.
 * A query base is optional in a QuerySpecification, which defers its specification till 
 * an {@link org.eclipse.datatools.connectivity.oda.IQuery} is prepared.
 * When a non-empty query text is provided in the call to the
 * {@link org.eclipse.datatools.connectivity.oda.IQuery#prepare(String)} method, it
 * would override and take precedence over this, even if specified.
 * @since 3.4 (DTP 1.11)
 */
public abstract class BaseQuery
{

    /**
     * Returns the qualified id of this base query type.
     * @return  qualified id
     */
    public String getQualifiedId()
    {
        return getClass().getName();
    }
    
    /**
     * Gets the name of this base query type.
     * It may be used to identify this in user messages or logging.
     * @return  name of this base query type
     */
    public String getName()
    {
        return getClass().getSimpleName();
    }

    /**
     * Validates this base query. 
     * @throws OdaException if validation failed. The cause is 
     *          defined by a concrete subclass implementing this method.
     */
    public void validate() throws OdaException
    {
        validate( null );
    }

    /**
     * Validates this query in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is 
     *          defined by a concrete subclass implementing this method.
     */
    public void validate( ValidationContext context ) 
        throws OdaException
    {
        try
        {
            validateSyntax( context );

            // pass this to custom validator, if exists, for further overall validation
            if( context != null && context.getValidator() != null )
                context.getValidator().validate( this, context );
        }
        catch( OdaException ex )
        {
            // log the exception before re-throwing it to the caller
            QuerySpecificationHelper.logValidationException( getName(), ex );
            throw ex;
        }
    }

    /**
     * Performs syntactic validation of this base query in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is 
     *          defined by a concrete subclass implementing this method.
     */
    public abstract void validateSyntax( ValidationContext context ) 
        throws OdaException;

}
