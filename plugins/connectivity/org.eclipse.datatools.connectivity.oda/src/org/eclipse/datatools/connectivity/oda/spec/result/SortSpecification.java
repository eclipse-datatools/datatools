/*
 *************************************************************************
 * Copyright (c) 2004, 2010 Actuate Corporation.
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
import java.util.List;

import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;
import org.eclipse.datatools.connectivity.oda.spec.ValidationContext;
import org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper;

/**
 * Specification of one or more dynamic sort keys of a query result set.
 * <br>Its application would impact the ordering of data rows retrieved in a result set,
 * in addition to any required sort specification expressed in a query text.
 * This may be extended to define additional ways of expressing a sort mode or key.
 * @since 3.3 (DTP 1.8)
 */
public class SortSpecification
{
	/**
	 * The constant that specifies ascending ordering of a sort key.
	 */
	public static final int ORDERING_ASC = 0;
	
	/**
	 * The constant that specifies descending ordering of a sort key.
	 */
	public static final int ORDERING_DESC = 1;
	
	/**
	 * Constants that specify the null ordering specification of a sort key.
	 */
	public static final int NULL_ORDERING_NONE = 0;
    public static final int NULL_ORDERING_FIRST = 1;
    public static final int NULL_ORDERING_LAST = 2;

	private static final int SORT_MODE_UNDEFINED = -1;
	
	private int m_sortMode;
	private List<SortKey> m_sortKeys;

    private static final String LOG_NEWLINE_CHAR = "\n "; //$NON-NLS-1$
    private static final String sm_className = SortSpecification.class.getName();
	
	/**
	 * Base class constructor with no pre-defined restriction on its sort mode.
     * <br>Use {@link org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper#createSortSpecification()} 
     * to create an instance.
	 */
	protected SortSpecification()
	{
	    this( SORT_MODE_UNDEFINED );
	}
	
	/**
	 * Base class constructor with the defined <code>sortMode</code>.
     * By specifiying a sort mode, a sort key that gets added to this specification
     * will be validated to match the sort mode.
     * <br>Use {@link org.eclipse.datatools.connectivity.oda.spec.util.QuerySpecificationHelper#createSortSpecification(int)} 
     * to create an instance.
	 * @param sortMode	the sort mode of this <code>SortSpecification</code>; one of 
	 * 					<code>IDataSetMetaData.sortModeNone</code>, 
	 * 					<code>IDataSetMetaData.sortModeSingleOrder</code>,
	 * 					<code>IDataSetMetaData.sortModeColumnOrder</code>,
	 * 					<code>IDataSetMetaData.sortModeSingleColumn</code>.
	 * @throws IllegalArgumentException	if the <code>sortMode</code> is not a 
	 * 									valid value.
	 */
	protected SortSpecification( int sortMode )
	{
		if( sortMode != IDataSetMetaData.sortModeNone &&
			sortMode != IDataSetMetaData.sortModeSingleOrder &&
			sortMode != IDataSetMetaData.sortModeColumnOrder &&
			sortMode != IDataSetMetaData.sortModeSingleColumn &&
			sortMode != SORT_MODE_UNDEFINED )
			throw new IllegalArgumentException( 
					Messages.bind( Messages.sortSpec_INVALID_SORT_MODE_SPECIFIED,
									new Integer( sortMode ) ));
		
		m_sortMode = sortMode;
		m_sortKeys = new ArrayList<SortKey>();
	}
	
	/**
	 * Adds a dynamic sort key for a specified result set column and sort direction.
	 * See {@link #addSortKey(ColumnIdentifier, int, int)} for more description. 
	 * @param column	identifier of the result set column to apply dynamic sorting
	 * 						on.  The specified column should be one of the columns
	 * 						retrieved in a result set
	 * @param sortDirection	constant value for the sort direction; must be one of the
     *                      constant values: <code>ORDERING_ASC</code>, <code>ORDERING_DESC</code>
	 * @see #addSortKey(ColumnIdentifier, int, int)
	 */
	public void addSortKey( ColumnIdentifier column, int sortDirection )
	{
	    addSortKey( column, sortDirection, NULL_ORDERING_NONE );
	}
	
	/**
     * Adds a dynamic sort key for a specified result set column, sort direction and null ordering. 
     * The sort criteria are specified by an ODA consumer, using 
     * an ordered list of one or more sort keys. 
     * The adding sequence of each sortKey corresponds to the major-to-minor 
     * ordering.  Validation is done against this sortMode if defined in constructor; 
     * i.e. this method throws an exception when adding a sort key that does not
     * conform to a defined sortMode of this <code>SortSpecification</code>.
     * @param column    identifier of the result set column to apply dynamic sorting
     *                      on.  The specified column should be one of the columns
     *                      retrieved in a result set
     * @param sortDirection constant value for the sort direction; must be one of the
     *                      constant values: <code>ORDERING_ASC</code>, <code>ORDERING_DESC</code>
     * @param nullOrdering  constant value for the ordering of null values vs. non-null values 
     *                      in the sort order; must be one of the constant values: 
     *                      <code>NULL_ORDERING_NONE</code>, 
     *                      <code>NULL_ORDERING_FIRST</code>,
     *                      <code>NULL_ORDERING_LAST</code>
     * @throws NullPointerException if <code>column</code> is null.
     * @throws IllegalArgumentException if specified column is invalid; or if specified
     *                                  sortDirection or nullOrdering  has invalid value.
     * @throws IllegalStateException    if the sortMode of this <code>SortSpecification</code> 
     *                                  is <code>IDataSetMetaData.sortModeNone</code>, 
     *                                  or <code>IDataSetMetaData.sortModeSingleColumn</code>, 
     *                                  and a sort key is already associated; 
     *                                  or if the sortMode is 
     *                                  <code>IDataSetMetaData.sortModeSingleOrder</code> 
     *                                  and the sort direction does not match existing directions
     * @see #addSortKey(ColumnIdentifier, int)
	 */
	public void addSortKey( ColumnIdentifier column, int sortDirection, int nullOrdering )
	{
		if( column == null )
			throw new NullPointerException( 
					Messages.sortSpec_NULL_COLUMN_NAME_SPECIFIED );
		
		if( ! column.isValid() )
			throw new IllegalArgumentException( 
					Messages.bind( Messages.sortSpec_INVALID_COLUMN_NAME_SPECIFIED,
									column ) );
		
		if( sortDirection != ORDERING_ASC && sortDirection != ORDERING_DESC )
			throw new IllegalArgumentException( 
					Messages.bind( Messages.sortSpec_INVALID_SORT_ORDER_SPECIFIED,
									new Integer( sortDirection ) ));
		
		if( nullOrdering < NULL_ORDERING_NONE || nullOrdering > NULL_ORDERING_LAST )
            throw new IllegalArgumentException( 
                    Messages.bind( Messages.sortSpec_INVALID_NULL_ORDERING,
                                    new Integer( nullOrdering ) ));
		    
		if( m_sortMode == IDataSetMetaData.sortModeNone )
			throw new IllegalStateException( 
					Messages.sortSpec_NO_DYNAMIC_SORT_KEY_FOR_SORTMODENONE );
		
		if( m_sortMode == IDataSetMetaData.sortModeSingleColumn &&
			getSortKeyCountImpl() > 0 )
			throw new IllegalStateException( 
					Messages.sortSpec_ONE_SORTCOLUMN_FOR_SINGLE_COLUMN_MODE );
		
		if( m_sortMode == IDataSetMetaData.sortModeSingleOrder &&
			getSortKeyCountImpl() > 0 )
		{
			// enforce that all sortOrders are the same
			SortKey sortKey = (SortKey) m_sortKeys.get( 0 );
			if( sortKey.getSortDirection() != sortDirection )
				throw new IllegalStateException( 
					Messages.sortSpec_ONE_SORTORDER_FOR_SINGLE_ORDER_MODE );
		}
		
		SortKey sortKey = new SortKey( column, sortDirection, nullOrdering );
		m_sortKeys.add( sortKey );
	}
	
	/**
	 * Returns the sort mode of this <code>SortSpecification</code> object.
	 * @return	the sort mode of this <code>SortSpecification</code>; one of 
	 * 			<code>IDataSetMetaData.sortModeNone</code>, 
	 * 			<code>IDataSetMetaData.sortModeSingleOrder</code>,
	 * 			<code>IDataSetMetaData.sortModeColumnOrder</code>,
	 * 			<code>IDataSetMetaData.sortModeSingleColumn</code>.
	 */
	public int getSortMode()
	{
	    if( m_sortMode != SORT_MODE_UNDEFINED )
	        return m_sortMode;

        // if sortMode is undefined, derive the mode based on existing sort keys
        int numSortKeys = getSortKeyCount();
        if( numSortKeys == 0 )
            return IDataSetMetaData.sortModeNone;
        if( numSortKeys == 1 )
            return IDataSetMetaData. sortModeSingleColumn;
        
        // check if multiple sort keys have different sort direction
        int firstKeySortDirection = getSortDirection( 1 );
        for( int i=2; i <= numSortKeys; i++ )
        {
            if( firstKeySortDirection != getSortDirection(i) )
                return IDataSetMetaData.sortModeColumnOrder;
        }
        
        // all sort keys have the same sort direction
        return IDataSetMetaData.sortModeSingleOrder;
	}
	
	/**
	 * Returns the number of sort keys associated with this <code>SortSpecification</code> 
	 * object.
	 * @return	the number of sort keys associated with this 
	 * 			<code>SortSpecification</code> object.
	 */
	public int getSortKeyCount()
	{
		return getSortKeyCountImpl();
	}
	
	protected int getSortKeyCountImpl()
	{
		return m_sortKeys.size();
	}
	
	/**
	 * Returns the result set column identifier of the sort key 
     * at the specified position.
	 * @param pos       sequence position of the sort key (1-based).
     * @return          the name of the result set column for the specified sort key.
     * @throws          IndexOutOfBoundsException if <code>pos</code> is out of range 
     *                  (pos < 1 || pos > getSortKeyCount()).
	 */
	public ColumnIdentifier getSortColumn( int pos )
	{
        ColumnIdentifier column = getSortKey( pos ).getColumn();
        return ( column != null && column.isValid() ) ? column : null;
	}
	
	/**
	 * Returns the sort direction of the sort key at the specified position.
	 * @param pos		sequence position of the sort key (1-based)
	 * @return			constant value of the sort direction for the specified sort key
	 * @throws 			IndexOutOfBoundsException if <code>pos</code> is out of range 
	 * 					(pos < 1 || pos > getSortKeyCount()).
	 */
	public int getSortDirection( int pos )
	{
		return getSortKey( pos ).getSortDirection();
	}

	/**
     * Returns the null ordering of the sort key at the specified position.
     * @param pos       sequence position of the sort key (1-based)
     * @return          constant value of the null ordering type for the specified sort key
     * @throws          IndexOutOfBoundsException if <code>pos</code> is out of range 
     *                  (pos < 1 || pos > getSortKeyCount()).
	 */
    public int getNullOrdering( int pos )
    {
        return getSortKey( pos ).getNullOrdering();
    }

    protected SortKey getSortKey( int pos ) throws IndexOutOfBoundsException
    {
        validatePosition( pos );
        
        // maps from 1-based to 0-based collection index.
        return (SortKey) m_sortKeys.get( pos - 1 );
    }
    
	protected void validatePosition( int pos )
	{
		int count = getSortKeyCountImpl();
		if( pos < 1 || pos > count )
			throw new IndexOutOfBoundsException( 
                    Messages.bind( Messages.sortSpec_INDEX_OUT_OF_BOUND,
                            new Integer( pos ), new Integer( count ) ));
	}
	
	/**
	 * Returns an array of all column identifiers for the sort keys.
	 * @return	an array of all column identifiers for the sort keys;
	 *          may be an empty array if no sort keys are associated 
	 * 			with this <code>SortSpecification</code>
	 */
	public ColumnIdentifier[] getSortColumns()
	{
		int size = getSortKeyCountImpl();
		ColumnIdentifier[] sortColumns = new ColumnIdentifier[ size ];
		
		for( int i = 0; i < size; i++ )
		{
			SortKey sortKey = (SortKey) m_sortKeys.get( i );
			ColumnIdentifier column = sortKey.getColumn();
			sortColumns[i] = column;
		}

		return sortColumns;
	}
	
	/**
	 * Returns the sort direction for the sort keys of a <code>sortModeSingleOrder</code>
	 * <code>SortSpecification</code> object.
	 * @return	the sort direction for the sort keys of a <code>sortModeSingleOrder</code> 
	 * 			<code>SortSpecification</code> object; the default value, <code>sortAsc</code>, 
	 * 			if no sort keys are associated with this <code>SortSpecification</code>.
	 * @throws IllegalStateException	if this <code>SortSpecification</code>'s sort 
	 * 									mode is not <code>sortModeSingleOrder</code>.
	 */
	public int getSortDirection()
	{
		if( getSortMode() != IDataSetMetaData.sortModeSingleOrder )
			throw new IllegalStateException( 
					Messages.sortSpec_ONLY_IN_SINGLE_ORDER_MODE );
		
		// if there are no sortKeys associated with this SortSpecification, then it does not
		// matter what we return here.  Since the caller will get an empty string array
		// from getSortColumns(), it would not know which columns to apply this value for.
		if( getSortKeyCountImpl() == 0 )
			return ORDERING_ASC;
		
		// since all the sort directions will be the same (enforced by addSortKey()),
		// we could just return the sort direction of any of our sort keys.
		SortKey sortKey = (SortKey) m_sortKeys.get( 0 );
		return sortKey.getSortDirection();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		// override default toString() for convenient debugging and logging
        StringBuffer buffer = new StringBuffer( SortSpecification.class.getSimpleName() + " [" ); //$NON-NLS-1$
        
        buffer.append( "Sort Mode: " + getSortModeLiteral( getSortMode() ));  //$NON-NLS-1$
        buffer.append( "\nSort Keys: "); //$NON-NLS-1$
        if( m_sortKeys != null )
        {
            for( SortKey sortKey : m_sortKeys )
                buffer.append( LOG_NEWLINE_CHAR + sortKey );
        }
        
        buffer.append( "]" ); //$NON-NLS-1$
        return buffer.toString();
	}
	
	/* 
	 * A helper function for converting a sortOrder to its string representation.
	 */	
	private static String getSortDirectionLiteral( int sortOrder )
	{
		switch( sortOrder )
		{
			case ORDERING_ASC:
				return "Ascending"; //$NON-NLS-1$
				
			case ORDERING_DESC:
				return "Descending"; //$NON-NLS-1$
				
			default:
				// addSortKey() validation should ensure correct value
				return ""; //$NON-NLS-1$
		}
	}

	private static String getNullOrderingLiteral( int nullOrdering )
	{
	    switch( nullOrdering )
	    {
            case NULL_ORDERING_FIRST:   return "Nulls_First"; //$NON-NLS-1$
            case NULL_ORDERING_LAST:   return "Nulls_Last"; //$NON-NLS-1$
            case NULL_ORDERING_NONE:
	        default:                   return "No_Null_Ordering"; //$NON-NLS-1$
	    }
	}
	
	/*
	 * A helper function for converting a sortMode to its string representation.
	 */ 	
	private static String getSortModeLiteral( int sortMode )
	{
		switch( sortMode )
		{
			case IDataSetMetaData.sortModeNone:
				return "sortModeNone"; //$NON-NLS-1$
			
			case IDataSetMetaData.sortModeSingleOrder:
				return "sortModeSingleOrder"; //$NON-NLS-1$
			
			case IDataSetMetaData.sortModeColumnOrder:
				return "sortModeColumnOrder"; //$NON-NLS-1$
			
			case IDataSetMetaData.sortModeSingleColumn:
				return "sortModeSingleColumn"; //$NON-NLS-1$
			
			case SORT_MODE_UNDEFINED:
			    return "sortModeUndefined"; //$NON-NLS-1$
			default:
				// constructor checks ensure correct values
				return ""; //$NON-NLS-1$
		}
	}

    /**
     * Validates this expression in the specified context. 
     * @param context   context for validation; may be null which would limit the scope of validation
     * @throws OdaException if validation failed. The concrete reason is 
     *          defined by the subclass implementing this method.
     */
    public void validate( ValidationContext context ) 
        throws OdaException
    {
        try
        {
            // pass this to custom validator, if exists, for overall validation
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

	/*
	 * A simple private helper class that stores the state of 
	 * each sort key.
	 */
	private static final class SortKey
	{
		private ColumnIdentifier m_column;
		private int m_sortDirection;
		private int m_nullOrdering;
		
		// the arguments are to be validated by SortSpecification class
		private SortKey( ColumnIdentifier column, int sortDirection, int nullOrdering )
		{
			m_column = column;
			m_sortDirection = sortDirection;
			m_nullOrdering = nullOrdering;
		}
		
		private ColumnIdentifier getColumn()
		{
		    return m_column;
		}

		private int getSortDirection()
		{
			return m_sortDirection;
		}
		
		private int getNullOrdering()
		{
		    return m_nullOrdering;
		}
		
		public String toString()
		{
			return "{" + m_column + ", " +  //$NON-NLS-1$ //$NON-NLS-2$
				   getSortDirectionLiteral( m_sortDirection ) + ", " + //$NON-NLS-1$
                   getNullOrderingLiteral( m_nullOrdering ) + "}"; //$NON-NLS-1$
		}
	}
}
