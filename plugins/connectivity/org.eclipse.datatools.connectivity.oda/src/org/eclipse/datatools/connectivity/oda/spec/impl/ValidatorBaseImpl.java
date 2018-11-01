/*
 *************************************************************************
 * Copyright (c) 2009, 2013 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec.impl;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.BaseQuery;
import org.eclipse.datatools.connectivity.oda.spec.IValidator;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext.Connection;
import org.eclipse.datatools.connectivity.oda.spec.ValueExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification;
import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

/**
 * A base class that provides stub implementation of all the {@link IValidator} interface methods.
 * <br>An oda.dynamicResultSet extension may optionally extend this base class for its 
 * IValidator implementation, and overrides only those methods that it supports.
 */
public class ValidatorBaseImpl implements IValidator
{
    protected ValidatorBaseImpl() {}
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#closeConnection(org.eclipse.datatools.connectivity.oda.spec.ValidationContext.Connection)
     */
    public void closeConnection( Connection validationConnection )
    {
        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.QuerySpecification, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( QuerySpecification querySpec,
            ValidationContext context ) throws OdaException
    {
        if( querySpec == null )
            return;     // nothing to validate

        // set atomic query text if exists in context for validation
        boolean hasSetQueryText = setContextQueryTextFromSpec( context, querySpec );

        validate( querySpec.getResultSetSpecification(), context );
        if( querySpec.getBaseQuery() != null )
            validate( querySpec.getBaseQuery(), context );

        // restore the original state in context
        if( hasSetQueryText )
            context.setQueryText( null );

        // sub-class to extend to validate the data set query properties and/or 
        // input parameters in querySpec, as appropriate
    }

    protected boolean setContextQueryTextFromSpec( ValidationContext context, QuerySpecification querySpec )
    {
        if( context == null || context.getQueryText() != null )
            return false;     // no context, or already has query text; leave it as is
        
        if( ! QuerySpecificationHelper.hasAtomicQueryText( querySpec ) )
            return false;     // no query text available in the querySpec
        
        context.setQueryText( QuerySpecificationHelper.getAtomicQuery( querySpec ).getQueryText() );
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( ResultSetSpecification resultSetSpec,
            ValidationContext context ) throws OdaException
    {
        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( FilterExpression filterExpr, ValidationContext context )
            throws OdaException
    {
        validateSyntax( filterExpr, context );
        
        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( AggregateExpression aggrExpr,
            ValidationContext context ) throws OdaException
    {
        validateSyntax( aggrExpr, context );

        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.ValueExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( ValueExpression valueExpr, ValidationContext context )
            throws OdaException
    {
        validateSyntax( valueExpr, context );

        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validate( SortSpecification sortSpec, ValidationContext context )
            throws OdaException
    {
        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validate(org.eclipse.datatools.connectivity.oda.spec.BaseQuery, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     * @since 3.4 (DTP 1.11)
     */
    public void validate( BaseQuery baseQuery, ValidationContext context )
        throws OdaException
    {
        validateSyntax( baseQuery, context );
        
        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validateSyntax( FilterExpression filterExpr,
            ValidationContext context ) throws OdaException
    {
        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.result.AggregateExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validateSyntax( AggregateExpression aggrExpr,
            ValidationContext context ) throws OdaException
    {
        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.ValueExpression, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     */
    public void validateSyntax( ValueExpression valueExpr,
            ValidationContext context ) throws OdaException
    {
        // sub-class to extend
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.spec.IValidator#validateSyntax(org.eclipse.datatools.connectivity.oda.spec.BaseQuery, org.eclipse.datatools.connectivity.oda.spec.ValidationContext)
     * @since 3.4 (DTP 1.11)
     */
    public void validateSyntax( BaseQuery baseQuery, ValidationContext context ) 
        throws OdaException
    {
        // sub-class to extend
    }

}
