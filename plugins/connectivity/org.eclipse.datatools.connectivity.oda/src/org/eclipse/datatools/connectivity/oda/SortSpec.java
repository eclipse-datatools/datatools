/*
 *************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.nls.Messages;

import com.ibm.icu.util.ULocale;

/**
 * A class that encapsulates one or more sort keys for 
 * association with an IQuery.  
 * The class is designed to be extendable to accommodate 
 * additional ways of expressing a sort mode or key.
 * <br><strong>Note</strong>: This class will be deprecated in the release after DTP 1.7, 
 * being replaced by 
 * {@link org.eclipse.datatools.connectivity.oda.spec.result.SortSpecification}.
 */
public class SortSpec
{
	/**
	 * The constant indicating ascending sort order.
	 */
	public static final int sortAsc = 0;
	
	/**
	 * The constant indicating descending sort order.
	 */
	public static final int sortDesc = 1;
	
	private int m_sortMode;
	private List m_sortKeys;
	
	/**
	 * Instantiates a <code>SortSpec</code> object for the defined
	 * <code>sortMode</code>.
	 * @param sortMode	the sort mode of this <code>SortSpec</code>; one of 
	 * 					<code>IDataSetMetaData.sortModeNone</code>, 
	 * 					<code>IDataSetMetaData.sortModeSingleOrder</code>,
	 * 					<code>IDataSetMetaData.sortModeColumnOrder</code>,
	 * 					<code>IDataSetMetaData.sortModeSingleColumn</code>.
	 * @throws IllegalArgumentException	if the <code>sortMode</code> is not a 
	 * 									valid value.
	 */
	public SortSpec( int sortMode )
	{
		if( sortMode != IDataSetMetaData.sortModeNone &&
			sortMode != IDataSetMetaData.sortModeSingleOrder &&
			sortMode != IDataSetMetaData.sortModeColumnOrder &&
			sortMode != IDataSetMetaData.sortModeSingleColumn )
			throw new IllegalArgumentException( 
					Messages.bind( Messages.sortSpec_INVALID_SORT_MODE_SPECIFIED,
									new Integer( sortMode ) ));
		
		m_sortMode = sortMode;
		m_sortKeys = new ArrayList();
	}
	
	/**
	 * Specifies the dynamic sort criteria in this sort mode. 
	 * The sort criteria are specified by an ODA consumer, using 
	 * an ordered list of one or more sort keys. 
	 * The adding sequence of each sortKey corresponds to the major-to-minor 
	 * ordering.  Validation is done against this <code>SortSpec</code>'s sortMode; 
	 * i.e. the method throws an exception when adding a sort key that does not
	 * conform to the sortMode of this <code>SortSpec</code> object.
	 * @param columnName	name of the result set column to apply dynamic sorting
	 * 						on.  The specified column should be one of the columns
	 * 						retrieved in a result set.
	 * @param sortOrder		value that represents the sorting order; one of 
	 * 						<code>sortAsc</code>, <code>sortDesc</code>.
	 * @throws NullPointerException	if <code>columnName</code> is null.
	 * @throws IllegalArgumentException if <code>columnName</code> is empty; or if 
	 * 									<code>sortOrder</code> is not <code>sortAsc</code> 
	 * 									or <code>sortDesc</code>.
	 * @throws IllegalStateException	if the sortMode of this <code>SortSpec</code> 
	 * 									is <code>IDataSetMetaData.sortModeNone</code>; 
	 * 									or if the sortMode of this <code>SortSpec</code> 
	 * 									is <code>IDataSetMetaData.sortModeSingleColumn</code>, 
	 * 									and a sort key is already associated; or if the
	 * 									sortMode of this <code>SortSpec</code> is 
	 * 									<code>IDataSetMetaData.sortModeSingleOrder</code> 
	 * 									and the sort order does not match existing sort orders.
	 */
	public void addSortKey( String columnName, int sortOrder )
	{
		if( columnName == null )
			throw new NullPointerException( 
					Messages.sortSpec_NULL_COLUMN_NAME_SPECIFIED );
		
		if( columnName.length() == 0 )
			throw new IllegalArgumentException( 
					Messages.bind( Messages.sortSpec_INVALID_COLUMN_NAME_SPECIFIED,
									columnName ) );
		
		if( sortOrder != sortAsc && sortOrder != sortDesc )
			throw new IllegalArgumentException( 
					Messages.bind( Messages.sortSpec_INVALID_SORT_ORDER_SPECIFIED,
									new Integer( sortOrder ) ));
		
		if( m_sortMode == IDataSetMetaData.sortModeNone )
			throw new IllegalStateException( 
					Messages.sortSpec_NO_DYNAMIC_SORT_KEY_FOR_SORTMODENONE );
		
		if( m_sortMode == IDataSetMetaData.sortModeSingleColumn &&
			doGetSortKeyCount() > 0 )
			throw new IllegalStateException( 
					Messages.sortSpec_ONE_SORTCOLUMN_FOR_SINGLE_COLUMN_MODE );
		
		if( m_sortMode == IDataSetMetaData.sortModeSingleOrder &&
			doGetSortKeyCount() > 0 )
		{
			// enforce that all sortOrders are the same
			SortKey sortKey = (SortKey) m_sortKeys.get( 0 );
			if( sortKey.getSortOrder() != sortOrder )
				throw new IllegalStateException( 
					Messages.sortSpec_ONE_SORTORDER_FOR_SINGLE_ORDER_MODE );
		}
		
		SortKey sortKey = new SortKey( columnName, sortOrder );
		m_sortKeys.add( sortKey );
	}
	
	/**
	 * Returns the sort mode of this <code>SortSpec</code> object.
	 * @return	the sort mode of this <code>SortSpec</code>; one of 
	 * 			<code>IDataSetMetaData.sortModeNone</code>, 
	 * 			<code>IDataSetMetaData.sortModeSingleOrder</code>,
	 * 			<code>IDataSetMetaData.sortModeColumnOrder</code>,
	 * 			<code>IDataSetMetaData.sortModeSingleColumn</code>.
	 */
	public int getSortMode()
	{
		return m_sortMode;
	}
	
	/**
	 * Returns the number of sort keys associated with this <code>SortSpec</code> 
	 * object.
	 * @return	the number of sort keys associated with this 
	 * 			<code>SortSpec</code> object.
	 */
	public int getSortKeyCount()
	{
		return doGetSortKeyCount();
	}
	
	private int doGetSortKeyCount()
	{
		return m_sortKeys.size();
	}
	
	/**
	 * Returns the result set column name of the sort key at the <code>index</code> 
	 * position.
	 * @param index		index of the sort key (1-based).
	 * @return			the name of the result set column for the specified sort key.
	 * @throws 			IndexOutOfBoundsException if <code>index</code> is out of range 
	 * 					(index < 1 || index > getSortKeyCount()).
	 */
	public String getSortColumn( int index )
	{
		validateIndex( index );
	
		// need to map from 1-based to 0-based collection index.
		SortKey sortKey = (SortKey) m_sortKeys.get( index - 1 );
		return sortKey.getColumnName();
	}
	
	/**
	 * Returns the sort order of the sort key at the <code>index</code> position.
	 * @param index		index of the sort key (1-based).
	 * @return			the sort order for the specified sort key.
	 * @throws 			IndexOutOfBoundsException if <code>index</code> is out of range 
	 * 					(index < 1 || index > getSortKeyCount()).
	 */
	public int getSortOrder( int index )
	{
		validateIndex( index );
	
		// need to map from 1-based to 0-based collection index.
		SortKey sortKey = (SortKey) m_sortKeys.get( index - 1 );
		return sortKey.getSortOrder();
	}
	
	private void validateIndex( int index )
	{
		int count = doGetSortKeyCount();
		if( index < 1 || index > count )
			throw new IndexOutOfBoundsException( 
                    Messages.bind( Messages.sortSpec_INDEX_OUT_OF_BOUND,
                            new Integer( index ), new Integer( count ) ));
	}
	
	/**
	 * Returns an array of all column names for the sort key of a 
	 * <code>sortModeSingleOrder</code> <code>SortSpec</code> object.
	 * @return	an array of all column names for the sort keys of a 
	 * 			<code>sortModeSingleOrder</code> <code>SortSpec</code> 
	 * 			object; an empty array if no sort keys are associated 
	 * 			with this <code>SortSpec</code>.
	 * @throws IllegalStateException	if this <code>SortSpec</code>'s sort 
	 * 									mode is not <code>sortModeSingleOrder</code>.
	 */
	public String[] getSortColumns()
	{
		if( m_sortMode != IDataSetMetaData.sortModeSingleOrder )
			throw new IllegalStateException( 
					Messages.sortSpec_ONLY_IN_SINGLE_ORDER_MODE );
		
		int size = doGetSortKeyCount();
		String[] sortColumns = new String[ size ];
		
		for( int i = 0; i < size; i++ )
		{
			SortKey sortKey = (SortKey) m_sortKeys.get( i );
			String columnName = sortKey.getColumnName();
			sortColumns[i] = columnName;
		}

		return sortColumns;
	}
	
	/**
	 * Returns the sort order for the sort keys of a <code>sortModeSingleOrder</code>
	 * <code>SortSpec</code> object.
	 * @return	the sort order for the sort keys of a <code>sortModeSingleOrder</code> 
	 * 			<code>SortSpec</code> object; the default value, <code>sortAsc</code>, 
	 * 			if no sort keys are associated with this <code>SortSpec</code>.
	 * @throws IllegalStateException	if this <code>SortSpec</code>'s sort 
	 * 									mode is not <code>sortModeSingleOrder</code>.
	 */
	public int getSortOrder()
	{
		if( m_sortMode != IDataSetMetaData.sortModeSingleOrder )
			throw new IllegalStateException( 
					Messages.sortSpec_ONLY_IN_SINGLE_ORDER_MODE );
		
		// if there are no sortKeys associated with this SortSpec, then it does not
		// matter what we return here.  Since the caller will get an empty string array
		// from getSortColumns(), it would not know which columns to apply this value for.
		if( doGetSortKeyCount() == 0 )
			return sortAsc;
		
		// since all the sort orders will be the same (enforced by addSortKey()),
		// we could just return the sort order of any of our sort keys.
		SortKey sortKey = (SortKey) m_sortKeys.get( 0 );
		return sortKey.getSortOrder();
	}
	
	/**
	 * Sets the locale of this <code>SortSpec</code>. Enables this <code>SortSpec</code> 
	 * to return localized error messages. The default locale is <code>en_US</code>.
	 * @param locale	the locale used for localizing error messages.
	 * @deprecated	obsolete; migrated to use NLS Messages class
	 */
	public void setLocale( ULocale locale )
	{
//		m_locale = locale;
	}
	
	/**
	 * Returns a string representation of this <code>SortSpec</code>.
	 * @return	a string representation of this <code>SortSpec</code>.
	 */
	public String toString()
	{
		// override default toString() for convenient debugging and logging
		return "Sort Mode: " + sortModeAsString( m_sortMode ) +  //$NON-NLS-1$
			   ", Sort Keys: " + m_sortKeys; //$NON-NLS-1$
	}
	
	/* 
	 * A helper function for converting a sortOrder to its string representation.
	 */	
	private static String sortOrderAsString( int sortOrder )
	{
		switch( sortOrder )
		{
			case sortAsc:
				return "Ascending"; //$NON-NLS-1$
				
			case sortDesc:
				return "Descending"; //$NON-NLS-1$
				
			default:
				// addSortKey() validation should ensure correct value
				return ""; //$NON-NLS-1$
		}
	}
	
	/*
	 * A helper function for converting a sortMode to its string representation.
	 */ 	
	private static String sortModeAsString( int sortMode )
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
			
			default:
				// constructor checks ensure correct values
				return ""; //$NON-NLS-1$
		}
	}
	
	/*
	 * A simple private helper class that stores the state of 
	 * each sort key.
	 */
	private static final class SortKey
	{
		private String m_columnName;
		private int m_sortOrder;
		
		// the arguments are to be validated by SortSpec class
		private SortKey( String columnName, int sortOrder )
		{
			m_columnName = columnName;
			m_sortOrder = sortOrder;
		}
		
		private String getColumnName()
		{
			return m_columnName;
		}
		
		private int getSortOrder()
		{
			return m_sortOrder;
		}
		
		public String toString()
		{
			return "{" + m_columnName + ", " +  //$NON-NLS-1$ //$NON-NLS-2$
				   sortOrderAsString( m_sortOrder ) + "}"; //$NON-NLS-1$
		}
	}
}
