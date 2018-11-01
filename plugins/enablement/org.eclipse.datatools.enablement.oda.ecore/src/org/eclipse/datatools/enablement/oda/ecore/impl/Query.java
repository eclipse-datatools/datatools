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

import static java.lang.String.valueOf;
import static org.eclipse.datatools.enablement.oda.ecore.util.StringUtil.countChars;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.spec.QuerySpecification;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
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
	private final EClassifier contextClassifier;
	private boolean isPrepared;
	private boolean isClosed;
	private IQueryResult queryResult;
	private final Collection<EObject> eObjects;
	private final Map<String, String> properties;
	private ColumnDefinition[] columnDefinitions;
	private Object[] inputParameters;
	private IExecutionStrategy executionStrategy;

	public Query(final Collection<EObject> eObjects, final EClassifier contextClassifier) {
		this.contextClassifier = contextClassifier;
		this.eObjects = eObjects;
		properties = new HashMap<String, String>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#prepare(java.lang.String)
	 */
	public void prepare(final String queryText) throws OdaException {
		verifyNotClosed();
		if (queryText == null) {
			throw new OdaException("Query text is null");
		}
		inputParameters = new Object[countChars('?', queryText) + 1];
		executionStrategy = new BooleanOCLQueryExecutionStrategy(queryText);
		isPrepared = true;
	}

	/**
	 * Alternative implementation of Query that takes an EMF SELECT rather than an OCL String-based query
	 * 
	 * @param statement
	 *            an EMF Query
	 * @throws OdaException
	 *             declaration exists only to keep API consistent with
	 * @see prepare(String)
	 */
	public void prepare(final SELECT statement) throws OdaException {
		verifyNotClosed();
		if (statement == null) {
			throw new OdaException("The statement is null");
		}
		executionStrategy = new StatementExecutionStrategy(statement);
		isPrepared = true;
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
		return new ResultSetMetaData(getColumnDefinitions());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#executeQuery()
	 */
	public IResultSet executeQuery() throws OdaException {
		verifyIsPrepared();
		verifyNotClosed();
		return executionStrategy.executeQuery();
	}

	public IQueryResult getQueryResult() {
		return queryResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setProperty(java.lang.String, java.lang.String)
	 */
	public void setProperty(final String name, final String value) throws OdaException {
		if (value != null) {
			properties.put(name, value);
		}
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
		inputParameters = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(java.lang.String, int)
	 */
	public void setInt(final String parameterName, final int value) throws OdaException {
		final int parameterId = findInParameter(parameterName);
		verifyParameterIndex(parameterId);
		inputParameters[findInParameter(parameterName)] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(int, int)
	 */
	public void setInt(final int parameterId, final int value) throws OdaException {
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(java.lang.String, double)
	 */
	public void setDouble(final String parameterName, final double value) throws OdaException {
		final int parameterId = findInParameter(parameterName);
		verifyParameterIndex(parameterId);
		inputParameters[findInParameter(parameterName)] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(int, double)
	 */
	public void setDouble(final int parameterId, final double value) throws OdaException {
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(java.lang.String, java.math.BigDecimal)
	 */
	public void setBigDecimal(final String parameterName, final BigDecimal value) throws OdaException {
		final int parameterId = findInParameter(parameterName);
		verifyParameterIndex(parameterId);
		inputParameters[findInParameter(parameterName)] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(int, java.math.BigDecimal)
	 */
	public void setBigDecimal(final int parameterId, final BigDecimal value) throws OdaException {
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(java.lang.String, java.lang.String)
	 */
	public void setString(final String parameterName, final String value) throws OdaException {
		final int parameterId = findInParameter(parameterName);
		verifyParameterIndex(parameterId);
		inputParameters[findInParameter(parameterName)] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(int, java.lang.String)
	 */
	public void setString(final int parameterId, final String value) throws OdaException {
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(java.lang.String, java.sql.Date)
	 */
	public void setDate(final String parameterName, final Date value) throws OdaException {
		final int parameterId = findInParameter(parameterName);
		verifyParameterIndex(parameterId);
		inputParameters[findInParameter(parameterName)] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(int, java.sql.Date)
	 */
	public void setDate(final int parameterId, final Date value) throws OdaException {
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(java.lang.String, java.sql.Time)
	 */
	public void setTime(final String parameterName, final Time value) throws OdaException {
		final int parameterId = findInParameter(parameterName);
		verifyParameterIndex(parameterId);
		inputParameters[findInParameter(parameterName)] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(int, java.sql.Time)
	 */
	public void setTime(final int parameterId, final Time value) throws OdaException {
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(java.lang.String, java.sql.Timestamp)
	 */
	public void setTimestamp(final String parameterName, final Timestamp value) throws OdaException {
		final int parameterId = findInParameter(parameterName);
		verifyParameterIndex(parameterId);
		inputParameters[findInParameter(parameterName)] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(int, java.sql.Timestamp)
	 */
	public void setTimestamp(final int parameterId, final Timestamp value) throws OdaException {
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(java.lang.String, boolean)
	 */
	public void setBoolean(final String parameterName, final boolean value) throws OdaException {
		final int parameterId = findInParameter(parameterName);
		verifyParameterIndex(parameterId);
		inputParameters[findInParameter(parameterName)] = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(int, boolean)
	 */
	public void setBoolean(final int parameterId, final boolean value) throws OdaException {
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = value;
	}

	/* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(int, java.lang.Object)
     */
    public void setObject( int parameterId, Object value ) throws OdaException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setObject(java.lang.String, java.lang.Object)
     */
    public void setObject( String parameterName, Object value )
            throws OdaException
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(java.lang.String)
	 */
	public void setNull(final String parameterName) throws OdaException {
		final int parameterId = findInParameter(parameterName);
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(int)
	 */
	public void setNull(final int parameterId) throws OdaException {
		verifyParameterIndex(parameterId);
		inputParameters[parameterId] = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IQuery#findInParameter(java.lang.String)
	 */
	public int findInParameter(final String parameterName) throws OdaException {
		// TODO: not yet implemented
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

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getSpecification()
     */
    @SuppressWarnings("restriction")
    public QuerySpecification getSpecification()
    {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#setSpecification(org.eclipse.datatools.connectivity.oda.spec.QuerySpecification)
     */
    @SuppressWarnings("restriction")
    public void setSpecification( QuerySpecification querySpec )
            throws OdaException, UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#getEffectiveQueryText()
     */
    public String getEffectiveQueryText()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IQuery#cancel()
     */
    public void cancel() throws OdaException, UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    private void verifyParameterIndex(final int parameterId) throws OdaException {
		if (parameterId >= inputParameters.length) {
			throw new OdaException("There are more parameters specified for input than the query can handle.");
		}
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
	 * If the result set is closed then throw an OdaException. This method is invoked before any method defined in IResultSet
	 * is called.
	 * 
	 * @throws OdaException
	 */
	private void verifyNotClosed() throws OdaException {
		if (isClosed) {
			throw new OdaException(Messages.query_alreadyClosed);
		}
	}

	private ColumnDefinition[] getColumnDefinitions() {
		if (columnDefinitions != null) {
			return columnDefinitions;
		}
		final Collection<ColumnDefinition> definitions = new ArrayList<ColumnDefinition>();
		final String columnsList = properties.get(Constants.CONNECTION_COLUMN_DEFINITIONS);
		if (columnsList != null) {
			final String[] columnNames = columnsList.split(",");
			for (final String columnName : columnNames) {
				definitions.add(ColumnDefinitionUtil.createFor(columnName));
			}
		}
		columnDefinitions = definitions.toArray(new ColumnDefinition[definitions.size()]);
		return columnDefinitions;
	}

	static interface IExecutionStrategy {
		IResultSet executeQuery() throws OdaException;
	}

	private final class StatementExecutionStrategy implements IExecutionStrategy {

		private final SELECT statement;

		StatementExecutionStrategy(final SELECT statement) {
			this.statement = statement;
		}

		public IResultSet executeQuery() throws OdaException {
			queryResult = statement.execute();
			final IResultSet resultSet = new ResultSet(Query.this, getColumnDefinitions(), maxRows);
			resultSet.setMaxRows(getMaxRows());
			return resultSet;
		}
	}

	private final class BooleanOCLQueryExecutionStrategy implements IExecutionStrategy {

		private final String queryText;

		BooleanOCLQueryExecutionStrategy(final String queryText) {
			this.queryText = queryText;
		}

		@SuppressWarnings("unchecked")
		public IResultSet executeQuery() throws OdaException {
			final OCL ocl = org.eclipse.ocl.ecore.OCL.newInstance();
			final EObjectCondition condition;
			try {
				condition = new BooleanOCLCondition<EClassifier, EClass, EObject>(ocl.getEnvironment(), parameterizeQuery(),
						contextClassifier);
			} catch (final ParserException e) {
				throw new OdaException(e);
			}
			final SELECT statement = new SELECT(maxRows, false, new FROM(eObjects), new WHERE(condition));
			queryResult = statement.execute();
			final IResultSet resultSet = new ResultSet(Query.this, getColumnDefinitions(), maxRows);
			resultSet.setMaxRows(getMaxRows());
			return resultSet;
		}

		private String parameterizeQuery() {
			String parameterizedQuery = queryText;
			for (int i = 1; i < inputParameters.length; i++) {
				parameterizedQuery = parameterizedQuery.replaceFirst("\\?", valueOf(inputParameters[i]));
			}
			return parameterizedQuery;
		}
	}
}
