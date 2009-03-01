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

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.SortSpec;

/**
 * <strong>EXPERIMENTAL</strong>.
 * Specification of the characteristics of an {@link IResultSet} to be retrieved
 * by the associated {@link IQuery}.
 * <br>Its application would impact the shape of data retrieved in a result set,
 * in addition to any specification expressed in a query text.
 * @since 3.2 (DTP 1.7)
 */
public class ResultSetSpecification
{
    private FilterExpression m_filterSpec;
    private ResultProjection m_projectionSpec;
    private SortSpecification m_sortSpec;
    
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
     * This will replace {@link IQuery#setSortSpec(SortSpec)} when it becomes API.
     * @param sortBy    a {@link SortSpecification}
     */
    public void setSortSpecification( SortSpecification sortSpec )
    {
        m_sortSpec = sortSpec;
    }
    
    /**
     * Gets the current sorting specification of a query result set.
     * <p>
     * This will replace {@link IQuery#getSortSpec()} when it becomes API.
     * @return  the current {@link SortSpecification},
     *          or null if not explicitly specified
     */
    public SortSpecification getSortSpecification()
    {
        return m_sortSpec;
    }
        
}
