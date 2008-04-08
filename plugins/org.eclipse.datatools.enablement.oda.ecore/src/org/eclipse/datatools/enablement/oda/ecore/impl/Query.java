/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import java.util.Collection;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.enablement.oda.ecore.i18n.Messages;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.query.conditions.eobjects.EObjectCondition;
import org.eclipse.emf.query.ocl.conditions.BooleanOCLCondition;
import org.eclipse.emf.query.statements.FROM;
import org.eclipse.emf.query.statements.IQueryResult;
import org.eclipse.emf.query.statements.SELECT;
import org.eclipse.emf.query.statements.WHERE;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;

public class Query implements IQuery {

	private int maxRows = 0;
	private final ColumnDefinition[] columns;
	private final EClassifier contextClassifier;
	private boolean isPrepared;
	private SELECT statement;
	private boolean isClosed;
	private IQueryResult queryResult;
	private final Collection<EObject> eObjects;

	public Query(final Collection<EObject> eObjects, final EClassifier contextClassifier,
			final ColumnDefinition[] columns) {
		this.columns = columns;
		this.contextClassifier = contextClassifier;
		this.eObjects = eObjects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#prepare(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public void prepare(final String queryText) throws OdaException {
		verifyNotClosed();

		if (queryText == null) {
			throw new OdaException("Query text is null");
		}

		isPrepared = true;

		final OCL ocl = org.eclipse.ocl.ecore.OCL.newInstance();
		final EObjectCondition condition;
		try {
			condition = new BooleanOCLCondition<EClassifier, EClass, EObject>(ocl.getEnvironment(), queryText,
					contextClassifier);
		} catch (final ParserException e) {
			throw new OdaException(e);
		}
		statement = new SELECT(maxRows, false, new FROM(eObjects), new WHERE(condition));
	}

	/**
	 * Alternative implementation of Query that takes an EMF SELECT rather than
	 * an OCL String-based query
	 * 
	 * @param statement
	 *            an EMF Query
	 * @throws OdaException
	 *             declaration exists only to keep API consistent with
	 * @see prepare(String)
	 */
	public void prepare(final SELECT statement) throws OdaException {
		this.statement = statement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setAppContext(java.lang.Object)
	 */
	public void setAppContext(final Object context) throws OdaException {
		// do nothing; assumes no support for pass-through context
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#close()
	 */
	public void close() throws OdaException {
		verifyIsPrepared();
		isClosed = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getMetaData()
	 */
	public IResultSetMetaData getMetaData() throws OdaException {
		verifyIsPrepared();
		verifyNotClosed();
		return new ResultSetMetaData(columns);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#executeQuery()
	 */
	public IResultSet executeQuery() throws OdaException {
		verifyIsPrepared();
		verifyNotClosed();

		queryResult = statement.execute();

		final IResultSet resultSet = new ResultSet(this, columns, maxRows);
		resultSet.setMaxRows(getMaxRows());

		return resultSet;
	}

	public IQueryResult getQueryResult() {
		return queryResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setProperty(java.lang.String,
	 *      java.lang.String)
	 */
	public void setProperty(final String name, final String value) throws OdaException {
		// do nothing; assumes no data set query property
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setMaxRows(int)
	 */
	public void setMaxRows(final int max) throws OdaException {
		maxRows = max > 0 ? max : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getMaxRows()
	 */
	public int getMaxRows() throws OdaException {
		verifyNotClosed();
		return maxRows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#clearInParameters()
	 */
	public void clearInParameters() throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(java.lang.String,
	 *      int)
	 */
	public void setInt(final String parameterName, final int value) throws OdaException {
		// only applies to named input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(int, int)
	 */
	public void setInt(final int parameterId, final int value) throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(java.lang.String,
	 *      double)
	 */
	public void setDouble(final String parameterName, final double value) throws OdaException {
		// only applies to named input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(int, double)
	 */
	public void setDouble(final int parameterId, final double value) throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(java.lang.String,
	 *      java.math.BigDecimal)
	 */
	public void setBigDecimal(final String parameterName, final BigDecimal value) throws OdaException {
		// only applies to named input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(int,
	 *      java.math.BigDecimal)
	 */
	public void setBigDecimal(final int parameterId, final BigDecimal value) throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(java.lang.String,
	 *      java.lang.String)
	 */
	public void setString(final String parameterName, final String value) throws OdaException {
		// only applies to named input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(int,
	 *      java.lang.String)
	 */
	public void setString(final int parameterId, final String value) throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(java.lang.String,
	 *      java.sql.Date)
	 */
	public void setDate(final String parameterName, final Date value) throws OdaException {
		// only applies to named input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(int,
	 *      java.sql.Date)
	 */
	public void setDate(final int parameterId, final Date value) throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(java.lang.String,
	 *      java.sql.Time)
	 */
	public void setTime(final String parameterName, final Time value) throws OdaException {
		// only applies to named input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(int,
	 *      java.sql.Time)
	 */
	public void setTime(final int parameterId, final Time value) throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(java.lang.String,
	 *      java.sql.Timestamp)
	 */
	public void setTimestamp(final String parameterName, final Timestamp value) throws OdaException {
		// only applies to named input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(int,
	 *      java.sql.Timestamp)
	 */
	public void setTimestamp(final int parameterId, final Timestamp value) throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(java.lang.String,
	 *      boolean)
	 */
	public void setBoolean(final String parameterName, final boolean value) throws OdaException {
		// only applies to named input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(int,
	 *      boolean)
	 */
	public void setBoolean(final int parameterId, final boolean value) throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(java.lang.String)
	 */
	public void setNull(final String parameterName) throws OdaException {
		// only applies to named input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(int)
	 */
	public void setNull(final int parameterId) throws OdaException {
		// only applies to input parameter
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#findInParameter(java.lang.String)
	 */
	public int findInParameter(final String parameterName) throws OdaException {
		// only applies to named input parameter
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getParameterMetaData()
	 */
	public IParameterMetaData getParameterMetaData() throws OdaException {
		// not necessary here
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setSortSpec(org.eclipse.datatools.connectivity.oda.SortSpec)
	 */
	public void setSortSpec(final SortSpec sortBy) throws OdaException {
		// only applies to sorting, assumes not supported
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#getSortSpec()
	 */
	public SortSpec getSortSpec() throws OdaException {
		// only applies to sorting
		return null;
	}

	/**
	 * If the query has not been prepared then throw an OdaException.
	 * 
	 * @throws OdaException
	 */
	private void verifyIsPrepared() throws OdaException {
		if (!isPrepared) {
			throw new OdaException("Query has not been prepared");
		}
	}

	/**
	 * If the result set is closed then throw an OdaException. This method is
	 * invoked before any method defined in IResultSet is called.
	 * 
	 * @throws OdaException
	 */
	private void verifyNotClosed() throws OdaException {
		if (isClosed) {
			throw new OdaException(Messages.query_alreadyClosed);
		}
	}

}
