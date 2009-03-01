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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;

/**
 * <strong>EXPERIMENTAL</strong>.
 * Specification for the projection of an {@link IResultSet} to be retrieved
 * by an associated {@link IQuery}.
 * @since 3.2 (DTP 1.7)
 */
public class ResultProjection
{
    private Map<Integer,AggregateExpression> m_aggregateSpecByColumnPos;
    
    /**
     * Specifies the aggregate expression on the specified result set column.
     * @param resultColumnNum   the projected column number (1-based) in the result set
     * @param aggregate an {@link AggregateExpression} whose output value is projected on the 
     *                  specified result set column
     * @throws OdaException
     */
    public void setProjection( int resultColumnNum, AggregateExpression aggregate ) throws OdaException
    {
        if( resultColumnNum < 1 )
            throw new OdaException( new IllegalArgumentException() );
        getAggregateColumns().put( Integer.valueOf( resultColumnNum ), aggregate );
    }
    
    /**
     * Gets the aggregate expression on the specified result set column.
     * @param resultColumnNum  the projected column number (1-based) in the result set
     * @return  an {@link AggregateExpression} whose output value is projected on the 
     *                  specified result set column
     */
    public AggregateExpression getAggregateProjection( int resultColumnNum )
    {
        return getAggregateColumns().get( Integer.valueOf( resultColumnNum ) );
    }
    
    /**
     * Returns a map of projected column numbers with corresponding aggregate expression.
     * @return  the projected column aggregate map; the map may be empty if no aggregate is specified
     */
    public Map<Integer,AggregateExpression> getAggregateColumns()
    {
        if( m_aggregateSpecByColumnPos == null )
        {
            m_aggregateSpecByColumnPos = new HashMap<Integer,AggregateExpression>(5);
        }
        return m_aggregateSpecByColumnPos;
    }
    
    /**
     * Hides the specified result column in the query result.
     * @param resultColumnNum
     * @throws OdaException
     */
    public void hideResultColumn( int resultColumnNum ) throws OdaException
    {
        // TODO
        throw new OdaException( new UnsupportedOperationException());
    }
        
    /**
     * Appends a new result column to the query result set.  
     * The appended result column can be referenced in the result set by the specified variable's alias.
     * @param columnExprVariable  a variable that resolves to the column expression of the new column
     * @throws OdaException
     */
    public void addResultColumn( ExpressionVariable columnExprVariable ) throws OdaException
    {
        // TODO
        throw new OdaException( new UnsupportedOperationException());
    }
    
    /**
     * Specifies the aggregate projection on a new result set column.
     * The appended result column can be referenced in the result set by the specified aggregate's alias.
     * @param aggregate an {@link AggregateExpression} whose output value is projected on the 
     *                  new result set column
     * @throws OdaException
     */
    public void addResultColumn( AggregateExpression aggregate ) throws OdaException
    {
        // TODO
        throw new OdaException( new UnsupportedOperationException());
    }
    
}
