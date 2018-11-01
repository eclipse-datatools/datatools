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

package org.eclipse.datatools.connectivity.oda.spec.result;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;


/**
 * The abstract base class for all ODA filter expressions.
 * <p>
 * An expression may be validated by an {@link org.eclipse.datatools.connectivity.oda.spec.IValidator} implemented 
 * by an extension of the org.eclipse.datatools.connectivity.oda.dynamicResultSet extension point.
 * </p>
 * @since 3.3 (DTP 1.8)
 */
public abstract class FilterExpression
{
    // trace logging variables
    private static final String sm_className = FilterExpression.class.getName();

    /**
     * Returns the qualified id of this expression type.
     * @return  qualified id
     */
    public String getQualifiedId()
    {
        return getClass().getName();
    }
    
    /**
     * Gets the name of this filter expression type.
     * It may be used to identify this in user messages or logging.
     * @return  name of this filter expression
     */
    public String getName()
    {
        return getClass().getSimpleName();
    }

    /**
     * Checks whether two objects are equal using the
     * <code>equals(Object)</code> method of the <code>left</code> object.
     * This method handles <code>null</code> for either the <code>left</code>
     * or <code>right</code> object.
     * @param left the first object to compare; may be <code>null</code>.
     * @param right the second object to compare; may be <code>null</code>.
     * @return <code>true</code> if the two objects are equivalent;
     *         <code>false</code> otherwise.
     */
    protected static final boolean equals( final Object left, final Object right )
    {
        return left == null ? right == null : 
                            ((right != null) && left.equals( right ));
    }

    /**
     * Validates this expression. 
     * @throws OdaException if validation failed. The concrete cause is 
     *          defined by the subclass implementing this method.
     */
    public void validate() throws OdaException
    {
        validate( null );
    }

    /**
     * Validates this expression in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The concrete cause is 
     *          defined by the subclass implementing this method.
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
            QuerySpecificationHelper.logValidationException( sm_className, ex );
            throw ex;
        }
    }

    /**
     * Performs syntactic validation of this expression in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The concrete cause is 
     *          defined by the subclass implementing this method.
     */
    public abstract void validateSyntax( ValidationContext context ) 
        throws OdaException;

    /**
     * Indicates whether this expression can be negated.
     * @return  true if expression can be negated; false otherwise
     */
    public abstract boolean isNegatable();

}
