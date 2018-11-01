/*
 *************************************************************************
 * Copyright (c) 2009, 2014 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification;
import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;

/**
 * Interface for a custom validator contributed by an extension of the 
 * ODA dynamicResultSet extension point.  It may be used by an ODA consumer
 * to validate a dynamic result set specification or an extension-defined expression that 
 * may be applied at runtime.
 * <br>It is implementation-dependent on the scope of validation covered, and 
 * whether it requires opening a connection to the underlying data source.
 * @since 3.3 (DTP 1.8)
 */
public interface IValidator
{
    /**
     * Validates the specified query specification in the specified context.
     * @param querySpec  a {@link QuerySpecification} to validate
     * @param context      context for validation; may be null which would limit the scope of validation;
     *              should contain the {@link ValidationContext#DATA_PROPERTY_QUERY_TEXT} 
     *              context property value to extend the scope of validation 
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     */
    public void validate( QuerySpecification querySpec, ValidationContext context ) 
        throws OdaException;

    /**
     * Validates the specified result set specification in the specified context.
     * @param resultSetSpec  a {@link ResultSetSpecification} to validate
     * @param context      context for validation; may be null, which would limit the scope of validation;
     *              should contain the {@link ValidationContext#DATA_PROPERTY_QUERY_TEXT} 
     *              context property value to extend the scope of validation 
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     * @see {@link ValidationContext#setQueryText(String)}
     * @see {@link ValidationContext#setConnectionProfile(Object)}
     * @since 3.2.2 (DTP 1.7.2)
     */
    public void validate( ResultSetSpecification resultSetSpec, ValidationContext context ) 
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
     * Performs syntactic validation of the specified filter expression 
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

    /**
     * Performs syntactic validation of the specified aggregate expression 
     * in the specified context. 
     * @param aggrExpr  the aggregate expression to validate
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     */
    public void validateSyntax( AggregateExpression aggrExpr, ValidationContext context )
        throws OdaException;

    /**
     * Validates the specified value expression in the specified context. 
     * @param valueExpr  value expression to validate
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     * @since 3.2.2 (DTP 1.7.2)
     */
    public void validate( ValueExpression valueExpr, ValidationContext context ) 
        throws OdaException;

    /**
     * Performs syntactic validation of the specified value expression 
     * in the specified context. 
     * @param valueExpr  the value expression to validate
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     * @since 3.2.2 (DTP 1.7.2)
     */
    public void validateSyntax( ValueExpression valueExpr, ValidationContext context )
        throws OdaException;
    
    /**
     * Validates the specified sort specification in the specified context.
     * @param sortSpec  a {@link SortSpecification} to validate
     * @param context      context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     */
    public void validate( SortSpecification sortSpec, ValidationContext context ) 
        throws OdaException;

    /**
     * Validates the specified base query in the specified context. 
     * @param baseQuery     a concrete {@link BaseQuery} to validate
     * @param context      context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     * @since 3.4 (DTP 1.11)
     */
    public void validate( BaseQuery baseQuery, ValidationContext context ) 
        throws OdaException;

    /**
     * Performs syntactic validation of the specified base query in the specified context. 
     * @param baseQuery     a concrete {@link BaseQuery} to validate
     * @param context      context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The cause is defined 
     *          by the class implementing this method.
     * @since 3.4 (DTP 1.11)
     */
    public void validateSyntax( BaseQuery baseQuery, ValidationContext context )
        throws OdaException;

    /**
     * Closes any connection handle that a custom validator may have cached in the specified context.
     * @param validationConnection  connection context used for online validation
     * @since 3.2.2 (DTP 1.7.2)
     */
    public void closeConnection( ValidationContext.Connection validationConnection );
    
}
