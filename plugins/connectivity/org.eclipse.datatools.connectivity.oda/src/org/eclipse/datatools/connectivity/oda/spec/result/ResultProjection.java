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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.spec.util.ValidatorUtil;

/**
 * Specification for the projection of an {@link org.eclipse.datatools.connectivity.oda.IResultSet} 
 * to be retrieved by an associated {@link org.eclipse.datatools.connectivity.oda.IQuery}.
 * @since 3.3 (DTP 1.8)
 */
public class ResultProjection
{
    private Map<ColumnIdentifier,AggregateExpression> m_aggregateSpecByColumn;
    private Map<ColumnIdentifier,ExpressionVariable> m_addedColumns;
    private List<ColumnIdentifier> m_hiddenColumns;
    private static final int MAP_INITIAL_CAPACITY = 5;

    private static final String LOG_SUB_ENTRY = "\n * "; //$NON-NLS-1$
    private static final String LOG_PAIR_ENTRY_SEPARATOR = " ->\n    "; //$NON-NLS-1$
    
    /**
     * Base class constructor.
     * <br>Use {@link org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper#createResultProjection()} 
     * to create an instance.
     */
    protected ResultProjection() {}
    
    /**
     * Specifies an aggregate expression whose output value is projected on the 
     * specified result set column identifier.
     * <br>The aggregation is applied on the values of its input source variable(s) 
     * across a set of data records.
     * Each set is grouped by the unique values of all the other result set column(s) 
     * that do not have an aggregate expression projected.  Hidden result set column(s) 
     * are excluded from the groupings.  
     * If the result set has sorting specification defined, the order of the
     * column groupings should be in the same sequence as the sorted columns.
     * <br>A projected tabular result set returns one row for each group. 
     * @param resultColumn   the column identifier in the projected result set targeted for the 
     *          output of the specified aggregate expression
     * @param aggregate an {@link AggregateExpression} whose output value is projected on the 
     *                  specified result set column
     * @throws OdaException
     */
    public void setProjection( ColumnIdentifier resultColumn, AggregateExpression aggregate ) throws OdaException
    {
        validateColumnIdentifier( resultColumn );
        if( getHiddenResultColumns().contains( resultColumn ) )
            throw ValidatorUtil.newAggregateException( 
                    Messages.bind( Messages.querySpec_INVALID_AGGR_HIDE_COLUMN, resultColumn ), 
                    aggregate );
        
        getAggregatedColumns().put( resultColumn, aggregate );
    }
    
    /**
     * Gets the aggregate expression on the specified result set column.
     * @param resultColumnNum  the projected column number (1-based) in the result set
     * @return  an {@link AggregateExpression} whose output value is projected on the 
     *                  specified result set column, or null if none is specified
     * @see #setProjection(ColumnIdentifier, AggregateExpression)
     */
    public AggregateExpression getAggregateProjection( ColumnIdentifier resultColumn )
    {
        return getAggregatedColumns().get( resultColumn );
    }
    
    /**
     * Returns a map of projected columns, each with corresponding aggregate expression.
     * @return  the map of projected column aggregation;
     *          may be empty if no column aggregate is specified
     * @see #setProjection(ColumnIdentifier, AggregateExpression)
     * @see #addResultColumn(AggregateExpression)
     */
    public Map<ColumnIdentifier,AggregateExpression> getAggregatedColumns()
    {
        if( m_aggregateSpecByColumn == null )
            m_aggregateSpecByColumn = new LinkedHashMap<ColumnIdentifier,AggregateExpression>(MAP_INITIAL_CAPACITY);

        return m_aggregateSpecByColumn;
    }
    
    /**
     * Appends a new result column to the query result set.  
     * The appended result column can be referenced in the result set by the specified variable's alias.
     * @param columnExprVariable  a variable that resolves to the value of the new column
     * @throws OdaException
     */
    public void addResultColumn( ExpressionVariable columnExprVariable ) throws OdaException
    {
        getAddedResultColumns().put( new ColumnIdentifier( columnExprVariable.getAlias() ), 
                                    columnExprVariable );
    }
    
    /**
     * Returns a map of result columns that are to be dynamically added to the query result set.
     * Each result column in the map is associated with an input variable 
     * that resolves to the column value.
     * @return  the map of projected new result columns; 
     *          may be empty if no column is to be dynamically added
     * @see #addResultColumn(ExpressionVariable)
     * @see #addResultColumn(AggregateExpression)
     */
    public Map<ColumnIdentifier,ExpressionVariable> getAddedResultColumns()
    {
        if( m_addedColumns == null )
            m_addedColumns = new LinkedHashMap<ColumnIdentifier, ExpressionVariable>(MAP_INITIAL_CAPACITY);
        
        return m_addedColumns;
    }
    
    /**
     * Hides the specified result column in the query result.
     * Any associated aggregate projection is also removed.
     * @param resultColumn
     * @throws OdaException
     */
    public void hideResultColumn( ColumnIdentifier resultColumn ) throws OdaException
    {
        validateColumnIdentifier( resultColumn );

        // remove aggregate projection, if any, on the hidden column
        getAggregatedColumns().remove( resultColumn );
        
        // if specified column is a dynamically added column
        if( getAddedResultColumns().containsKey( resultColumn ) )
        {
            // simply remove it from added column collection
            getAddedResultColumns().remove( resultColumn );
        }
        else    // add to dynamically hidden column collection, if not already exists
        {
            if( ! getHiddenResultColumns().contains( resultColumn ) )
                getHiddenResultColumns().add( resultColumn );
        }
    }

    /**
     * Returns a list of result columns that are to be dynamically hidden from the query result set.
     * @return  the map of projected hidden result columns; 
     *          may be empty if no column is to be dynamically hidden
     * @see #hideResultColumn(ColumnIdentifier)
     */
    public List<ColumnIdentifier> getHiddenResultColumns()
    {
        if( m_hiddenColumns == null )
            m_hiddenColumns = new ArrayList<ColumnIdentifier>(MAP_INITIAL_CAPACITY);
        
        return m_hiddenColumns;
    }
    
    /**
     * Indicates whether this has an empty content.
     * @return  true if this has an empty content; false otherwise
     * @since 3.3.1 (DTP 1.8.1)
     */
    public boolean isEmpty()
    {
        return( getAddedResultColumns().isEmpty() && 
                getAggregatedColumns().isEmpty() && 
                getHiddenResultColumns().isEmpty() );
    }
    
    private void validateColumnIdentifier( ColumnIdentifier resultColumn ) throws OdaException
    {
        if( resultColumn == null || ! resultColumn.isValid() )
            throw new OdaException( new IllegalArgumentException( 
                    Messages.bind( Messages.querySpec_INVALID_COLUMN_IDENTIFIER, resultColumn )) );
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer( ResultProjection.class.getSimpleName() + " [" ); //$NON-NLS-1$
        
        buffer.append( "\nAdded result columns <ColumnIdentifier, ExpressionVariable>:" ); //$NON-NLS-1$
        if( m_addedColumns != null )
        {
            for( Entry<ColumnIdentifier, ExpressionVariable> addedColumnPair : m_addedColumns.entrySet() )
            {
                buffer.append( LOG_SUB_ENTRY + addedColumnPair.getKey() +
                        LOG_PAIR_ENTRY_SEPARATOR + addedColumnPair.getValue() );
            }
        }
        
        buffer.append( "\nHidden result columns <ColumnIdentifier>: " ); //$NON-NLS-1$
        if( m_hiddenColumns != null )
        {
            for( ColumnIdentifier hiddenColumnIdentifier : m_hiddenColumns )
                buffer.append( LOG_SUB_ENTRY + hiddenColumnIdentifier );
        }
        
        buffer.append( "\nAggregated columns <ColumnIdentifier, AggregateExpression>: " ); //$NON-NLS-1$
        if( m_aggregateSpecByColumn != null )
        {
            for( Entry<ColumnIdentifier, AggregateExpression> aggrColumnPair : m_aggregateSpecByColumn.entrySet() )
            {
                buffer.append( LOG_SUB_ENTRY + aggrColumnPair.getKey() +
                        LOG_PAIR_ENTRY_SEPARATOR + aggrColumnPair.getValue() );
            }
        }
        
        buffer.append( "]" ); //$NON-NLS-1$
        return buffer.toString();
    }
    
}
