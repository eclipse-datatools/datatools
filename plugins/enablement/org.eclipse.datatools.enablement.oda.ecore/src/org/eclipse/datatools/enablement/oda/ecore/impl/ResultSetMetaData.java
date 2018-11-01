/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
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

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;

public class ResultSetMetaData implements IResultSetMetaData {

	private final ColumnDefinition[] columns;

	public ResultSetMetaData(final ColumnDefinition[] columns) {
		this.columns = columns;
	}

	public int getColumnCount() throws OdaException {
		return columns.length;
	}

	public String getColumnName(final int index) throws OdaException {
		validateColumnIndex(index);
		return columns[index - 1].getAttributes().getName();
	}

	public String getColumnLabel(final int index) throws OdaException {
		validateColumnIndex(index);
		return columns[index - 1].getAttributes().getUiHints().getDisplayName();
	}

	public int getColumnType(final int index) throws OdaException {
		validateColumnIndex(index);
		return columns[index - 1].getAttributes().getNativeDataTypeCode();
	}

	public String getColumnTypeName(final int index) throws OdaException {
		validateColumnIndex(index);
		return DataTypes.getNativeType(columns[index - 1].getAttributes().getNativeDataTypeCode());
	}

	public int getColumnDisplayLength(final int index) throws OdaException {
		validateColumnIndex(index);
		return columns[index - 1].getUsageHints().getFormattingHints().getDisplaySize();
	}

	public int getPrecision(final int index) throws OdaException {
		validateColumnIndex(index);
		return columns[index - 1].getAttributes().getPrecision();
	}

	public int getScale(final int index) throws OdaException {
		validateColumnIndex(index);
		return columns[index - 1].getAttributes().getScale();
	}

	public int isNullable(final int index) throws OdaException {
		validateColumnIndex(index);
		return columns[index - 1].getAttributes().getNullability().getValue();
	}

	/**
	 * Evaluate whether the value of given column number is valid.
	 * 
	 * @param index
	 *            column number (1-based)
	 * @throws OdaException
	 *             if the given index value is invalid
	 */
	private void validateColumnIndex(final int index) throws OdaException {
		if (index > getColumnCount() || index < 1) {
			throw new OdaException("Invalid column index");
		}
	}
}
