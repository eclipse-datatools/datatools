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

package org.eclipse.datatools.connectivity.oda.spec;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;

/**
 * <strong>EXPERIMENTAL</strong>.
 * Interface for a custom validator contributed by an extension of the 
 * ODA dynamicResultSet extension point.  It may be used by an ODA consumer
 * to validate an extension-defined expression or a dynamic query specification that 
 * may be applied at runtime.
 * <br>It is implementation-dependent on the scope of validation covered, and 
 * whether it requires opening a connection to the underlying data source.
 * @since 3.2 (DTP 1.7)
 */
public interface IValidator
{
    /**
     * Validates the specified query specification in the specified context.
     * @param querySpec  a {@link QuerySpecification} to validate
     * @param context      context for validation; may be null which would limit the scope of validation;
     *              should specify data for {@link ValidationContext#DATA_PROPERTY_QUERY_TEXT} 
     *              context property to extend the scope of validation 
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     */
    public void validate( QuerySpecification querySpec, ValidationContext context ) 
        throws OdaException;

    /**
     * Validates the specified filter expression in the specified context. 
     * @param filterExpr  the filter expression to validate; may be the root of an expression tree
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     */
    public void validate( FilterExpression filterExpr, ValidationContext context ) 
        throws OdaException;

    /**
     * Performs syntactic validation of the specified custom atomic filter expression 
     * in the specified context. 
     * @param filterExpr  the filter expression to validate; 
     *              may be a single filter node at the root, or nested
     *              within a filter expression tree
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     */
    public void validateSyntax( FilterExpression filterExpr, ValidationContext context )
        throws OdaException;

    /**
     * Validates the specified aggregate expression in the specified context. 
     * @param aggrExpr  aggregate expression to validate
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     */
    public void validate( AggregateExpression aggrExpr, ValidationContext context ) 
        throws OdaException;

}
