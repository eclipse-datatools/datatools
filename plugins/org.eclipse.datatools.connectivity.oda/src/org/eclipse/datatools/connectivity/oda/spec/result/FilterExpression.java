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

package org.eclipse.datatools.connectivity.oda.spec.result;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;


/**
 * <strong>EXPERIMENTAL</strong>.
 * </p>
 * The abstract base class for all ODA filter expressions.
 * <p>
 * An expression may be validated by an {@link org.eclipse.datatools.connectivity.oda.spec.IValidator} implemented 
 * by an extension of the org.eclipse.datatools.connectivity.oda.dynamicResultSet extension point.
 * </p>
 * @since 3.2 (DTP 1.7)
 */
public abstract class FilterExpression
{
    /**
     * Returns the qualified id of this expression.
     * @return  qualified id
     */
    public String getQualifiedId()
    {
        return getClass().getName();
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
     * @throws OdaException
     */
    public void validate() throws OdaException
    {
        validate( null );
    }

    /**
     * Validates this expression in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The concrete reason is 
     *          defined by the subclass implementing this method.
     */
    public abstract void validate( ValidationContext context ) 
        throws OdaException;

    /**
     * Indicates whether this expression can be negated.
     * @return  true if expression can be negated; false otherwise
     */
    public abstract boolean isNegatable();

}
