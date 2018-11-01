/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2009 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.enablement.oda.ecore.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ecore.util.StringUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class ResultSet implements IResultSet {

	private static final String AGGREGATION_SEPARATOR = "\n";

	private int maxRows;
	private int currentRowId;
	private EObject eObject;
	private final ColumnDefinition[] columns;
	private boolean wasNull;
	private boolean isClosed;
	private final Iterator<EObject> queryResultIterator;
	private final Query query;

	public ResultSet(final Query query, final ColumnDefinition[] columns, final int maxRows) {
		this.query = query;
		this.columns = columns;
		this.maxRows = maxRows;
		queryResultIterator = query.getQueryResult().iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getMetaData()
	 */
	public IResultSetMetaData getMetaData() throws OdaException {
		verifyNotClosed();
		return query.getMetaData();
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#setMaxRows(int)
	 */
	public void setMaxRows(final int maxRows) throws OdaException {
		this.maxRows = maxRows;
	}

	/**
	 * Returns the maximum number of rows that can be fetched from this result set.
	 * 
	 * @return the maximum number of rows to fetch.
	 */
	protected int getMaxRows() {
		return maxRows;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#next()
	 */
	public boolean next() throws OdaException {
		verifyNotClosed();
		boolean isSuccessful = false;
		if (maxRows > 0 && currentRowId <= maxRows || maxRows == 0) {
			try {
				eObject = queryResultIterator.next();
				currentRowId++;
				isSuccessful = true;
			} catch (final NoSuchElementException e) {
				isSuccessful = false;
			}
		}
		return isSuccessful;
	}

	private void verifyInScope() throws OdaException {
		if (eObject == null) {
			throw new OdaException("No row is in scope. Try calling next()?");
		}
	}

	/**
	 * If the result set is closed then throw an OdaException. This method is invoked before any method defined in
	 * IResultSet is called.
	 * 
	 * @throws OdaException
	 */
	private void verifyNotClosed() throws OdaException {
		if (isClosed) {
			throw new OdaException(Messages.resultSet_alreadyClosed);
		}
	}

	private void validateColumn(final int index) throws OdaException {
		try {
			@SuppressWarnings("unused")
			final ColumnDefinition c = columns[index - 1];
		} catch (final IndexOutOfBoundsException e) {
			wasNull = true;
			throw new OdaException("No corresponding column for index");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#close()
	 */
	public void close() throws OdaException {
		query.close();
		isClosed = true;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getRow()
	 */
	public int getRow() throws OdaException {
		return currentRowId;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getString(int)
	 */
	public String getString(final int index) throws OdaException {
		validateColumn(index);
		return getFeatureValues(Collections.singleton(eObject), ColumnDefinitionUtil.featuresForColumn(eObject,
				columns[index - 1]));
	}

	private String getFeatureValues(final Collection<EObject> eObjects, final EStructuralFeature[] path) {
		if (path.length == 0) {
			return "";
		}
		final EStructuralFeature feature = path[0];
		final Collection<String> values = new ArrayList<String>();
		for (final EObject eObject : eObjects) {
			final Object value = eObject.eGet(feature);
			if (value != null) {
				if (path.length == 1) {
					values.add(value.toString());
				} else {
					final EStructuralFeature[] nextPath = new EStructuralFeature[path.length - 1];
					System.arraycopy(path, 1, nextPath, 0, nextPath.length);
					@SuppressWarnings("unchecked")
					final String featureValues = getFeatureValues(feature.isMany() ? (Collection<EObject>) value
							: Collections.singleton((EObject) value), nextPath);
					if (featureValues.length() > 0) {
						values.add(featureValues);
					}
				}
			}
		}
		return StringUtil.join(values.toArray(new String[values.size()]), AGGREGATION_SEPARATOR);
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getString(java.lang.String)
	 */
	public String getString(final String columnName) throws OdaException {
		return getString(findColumn(columnName));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getInt(int)
	 */
	public int getInt(final int index) throws OdaException {
		validateColumn(index);
		return Integer.parseInt(getFeatureValues(Collections.singleton(eObject), ColumnDefinitionUtil
				.featuresForColumn(eObject, columns[index - 1])));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getInt(java.lang.String)
	 */
	public int getInt(final String columnName) throws OdaException {
		return getInt(findColumn(columnName));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDouble(int)
	 */
	public double getDouble(final int index) throws OdaException {
		validateColumn(index);
		return Double.parseDouble(getFeatureValues(Collections.singleton(eObject), ColumnDefinitionUtil
				.featuresForColumn(eObject, columns[index - 1])));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDouble(java.lang.String)
	 */
	public double getDouble(final String columnName) throws OdaException {
		return getDouble(findColumn(columnName));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBigDecimal(int)
	 */
	public BigDecimal getBigDecimal(final int index) throws OdaException {
		throw new UnsupportedOperationException();
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBigDecimal(java.lang.String)
	 */
	public BigDecimal getBigDecimal(final String columnName) throws OdaException {
		return getBigDecimal(findColumn(columnName));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDate(int)
	 */
	public Date getDate(final int index) throws OdaException {
		throw new UnsupportedOperationException();
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDate(java.lang.String)
	 */
	public Date getDate(final String columnName) throws OdaException {
		return getDate(findColumn(columnName));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTime(int)
	 */
	public Time getTime(final int index) throws OdaException {
		throw new UnsupportedOperationException();
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTime(java.lang.String)
	 */
	public Time getTime(final String columnName) throws OdaException {
		return getTime(findColumn(columnName));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTimestamp(int)
	 */
	public Timestamp getTimestamp(final int index) throws OdaException {
		throw new UnsupportedOperationException();
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTimestamp(java.lang.String)
	 */
	public Timestamp getTimestamp(final String columnName) throws OdaException {
		return getTimestamp(findColumn(columnName));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(int)
	 */
	public IBlob getBlob(final int index) throws OdaException {
		throw new UnsupportedOperationException();
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(java.lang.String)
	 */
	public IBlob getBlob(final String columnName) throws OdaException {
		return getBlob(findColumn(columnName));
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(int)
	 */
	public IClob getClob(final int index) throws OdaException {
		throw new UnsupportedOperationException();
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(java.lang.String)
	 */
	public IClob getClob(final String columnName) throws OdaException {
		return getClob(findColumn(columnName));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBoolean(int)
	 */
	public boolean getBoolean(final int index) throws OdaException {
		validateColumn(index);
		return Boolean.parseBoolean(getFeatureValues(Collections.singleton(eObject), ColumnDefinitionUtil
				.featuresForColumn(eObject, columns[index - 1])));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBoolean(java.lang.String)
	 */
	public boolean getBoolean(final String columnName) throws OdaException {
		return getBoolean(findColumn(columnName));
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getObject(int)
     */
    public Object getObject( int index ) throws OdaException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IResultSet#getObject(java.lang.String)
     */
    public Object getObject( String columnName ) throws OdaException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /*
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#wasNull()
	 */
	public boolean wasNull() throws OdaException {
		return wasNull;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IResultSet#findColumn(java.lang.String)
	 */
	public int findColumn(final String columnName) throws OdaException {
		verifyInScope();

		for (int i = 0; i < columns.length; i++) {
			final ColumnDefinition column = columns[i];
			if (column.getAttributes().getName().equals(columnName)) {
				return i + 1;
			}
		}
		throw new OdaException("No column matches name");
	}

}
