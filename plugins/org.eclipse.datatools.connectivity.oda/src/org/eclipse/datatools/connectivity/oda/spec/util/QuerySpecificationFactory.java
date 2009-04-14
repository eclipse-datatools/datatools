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

package org.eclipse.datatools.connectivity.oda.spec.util;

import org.eclipse.datatools.connectivity.oda.spec.AdvancedQuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.connectivity.oda.spec.result.FilterExpression;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultProjection;
import org.eclipse.datatools.connectivity.oda.spec.result.ResultSetSpecification;
import org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification;

/**
 * The factory base class to create instances of query specification classes 
 * for use in preparing a query.
 * <br>An ODA dynamicResultSet extension may extend this base class to create
 * customized specification classes.
 */
public class QuerySpecificationFactory
{
    /**
     * Returns an empty query specification instance.
     * @return  a new {@link QuerySpecification} instance
     */
    protected QuerySpecification createQuerySpecification()
    {
        return new QuerySpecification();
    }
    
    /**
     * Returns an empty advanced query specification instance.
     * @return  a new {@link AdvancedQuerySpecification} instance
     */
    protected AdvancedQuerySpecification createAdvancedQuerySpecification()
    {
        return new AdvancedQuerySpecification();
    }
    
    /**
     * Returns an empty result set specification instance.
     * @return  a new {@link ResultSetSpecification} instance
     */
    protected ResultSetSpecification createResultSetSpecification()
    {
        return new ResultSetSpecification();
    }
    
    /**
     * Returns an empty result projection instance.
     * @return  a new {@link ResultProjection} instance
     */
    protected ResultProjection createResultProjection()
    {
        return new ResultProjection();
    }
    
    /**
     * Returns an empty sort specification instance.
     * @return  a new {@link SortSpecification} instance
     */
    protected SortSpecification createSortSpecification()
    {
        return new SortSpecification();
    }
   
    /**
     * Returns an empty sort specification instance.
     * By specifiying a sort mode, a sort key that gets added to the sort specification
     * will be validated to match the sort mode.
     * @param sortMode  the sort mode of this <code>SortSpecification</code>; one of 
     *                  <code>IDataSetMetaData.sortModeNone</code>, 
     *                  <code>IDataSetMetaData.sortModeSingleOrder</code>,
     *                  <code>IDataSetMetaData.sortModeColumnOrder</code>,
     *                  <code>IDataSetMetaData.sortModeSingleColumn</code>.
     * @return  a new {@link SortSpecification} instance with the defined mode,
     */
    protected SortSpecification createSortSpecification( int sortMode )
    {
        return new SortSpecification( sortMode );
    }

    /**
     * A convenience method to create a query specification instance with 
     * the specified filter expression root, result projection, and/or sort specification.
     * @param filterExpr    a composite or atomic FilterExpression with corresponding variable 
     *                  and argument values; may be null  
     * @param projectionSpec    the projection specification of a query result set; may be null 
     * @param sortSpec  the SortSpecification representing sorting characteristics; may be null  
     *                  of a query result set
     * @return  a new {@link QuerySpecification} instance
     */
    public QuerySpecification createQuerySpecification( FilterExpression filterExpr,
            ResultProjection projectionSpec, SortSpecification sortSpec )
    {
        ResultSetSpecification resultSetSpec = createResultSetSpecification();
        resultSetSpec.setFilterSpecification( filterExpr );
        resultSetSpec.setResultProjection( projectionSpec );
        resultSetSpec.setSortSpecification( sortSpec );
        
        QuerySpecification newQuerySpec = createQuerySpecification();
        newQuerySpec.setResultSetSpecification( resultSetSpec );
        return newQuerySpec;
    }

}
