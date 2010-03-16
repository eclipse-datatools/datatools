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
 * Specification of the characteristics of an {@link org.eclipse.datatools.connectivity.oda.IResultSet} 
 * to be retrieved by the associated {@link org.eclipse.datatools.connectivity.oda.IQuery}.
 * <br>Its application would impact the shape of data retrieved in a result set,
 * in addition to any specification expressed in a query text.
 * @since 3.3 (DTP 1.8)
 */
public class ResultSetSpecification
{
    private FilterExpression m_filterSpec;
    private ResultProjection m_projectionSpec;
    private SortSpecification m_sortSpec;
    
    /**
     * Internal constructor.
     * <br>Use {@link org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper#createResultSetSpecification()} 
     * to create an instance.
     */
    public ResultSetSpecification() {}
    
    /**
     * Specifies the filtering characteristics of a query result set.
     * @param filterExpr    a composite or atomic {@link FilterExpression} 
     *             with corresponding variable and argument values
     */
    public void setFilterSpecification( FilterExpression filterExpr ) 
    {
        m_filterSpec = filterExpr; 
    }

    /**
     * Gets the current filtering specification of a query result set.
     * @return  the current{@link FilterExpression}, 
     *             or null if not explicitly specified
     */
    public FilterExpression getFilterSpecification()
    {
        return m_filterSpec;
    }

    /**
     * Specifies the projection of a query result set.
     * @param projectionSpec    a {@link ResultProjection}
     */
    public void setResultProjection( ResultProjection projectionSpec )
    {
        m_projectionSpec = projectionSpec;
    }
    
    /**
     * Gets the current projection of a query result set.
     * @return  the current {@link ResultProjection}, 
     *          or null if not explicitly specified
     */
    public ResultProjection getResultProjection()
    {
        return m_projectionSpec;
    }

    /**
     * Specifies the sorting characteristics of a query result set.
     * <br>It is up to individual ODA runtme drivers to validate the type of sort specification 
     * that are acceptable to its data provider, based on its level 
     * of dynamic sorting support. 
     * <p>
     * This will replace 
     * {@link org.eclipse.datatools.connectivity.oda.IQuery#setSortSpec(org.eclipse.datatools.connectivity.oda.SortSpec)} 
     * when it becomes API.
     * @param sortBy    a {@link SortSpecification}
     */
    public void setSortSpecification( SortSpecification sortSpec )
    {
        m_sortSpec = sortSpec;
    }
    
    /**
     * Gets the current sorting specification of a query result set.
     * <p>
     * This will replace {@link org.eclipse.datatools.connectivity.oda.IQuery#getSortSpec()} 
     * when it becomes API.
     * @return  the current {@link SortSpecification},
     *          or null if not explicitly specified
     */
    public SortSpecification getSortSpecification()
    {
        return m_sortSpec;
    }

    /**
     * Validates this in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed.  The exception thrown may be a chained OdaException, 
     *          which identifies each of those specification component(s) that has caused 
     *          the validation exception.
     * @see {@link org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil}
     * @since 3.2.2 (DTP 1.7.2)
     */
    public void validate( ValidationContext context ) 
        throws OdaException
    {
        // pass this to custom validator, if exists, for overall validation
        if( context != null && context.getValidator() != null )
            context.getValidator().validate( this, context );
    }
        
}
