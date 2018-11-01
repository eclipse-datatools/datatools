/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.data.internal.core.editor;

import java.sql.ResultSet;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * Interface for external extensions to provide filters to restrict the results returned when
 * querying a table.  Extending the related extension-point and implementing this interface
 * allows users to select the columns and/or where condition to restrict the results returned
 * for editing.
 * @author Quy On
 */
public interface ITableEditorResultFilter
{
	/**
	 * Gets whether or not an external filter dialog or wizard should be launched to get result filters
	 * @return true if filter dialog will be launched, false if no filter dialog
	 */
	public boolean isUseExternalFilterDialog();
	
	/**
	 * Opens the external dialog.
	 * This method can be no-op if isUseExternalFilterDialog() returns false.
	 * @return true if user selects the OK or Finish button, false if Cancel is selected
	 */
	public boolean open();
	
	/**
	 * Gets whether or not the external filter implementation will return a Result Set.
	 * @return true if a Result Set is to be returned by the external filter implementation,
	 * false if a SQL query string is returned instead.
	 */
	public boolean isReturningResultSet();
	
	/**
	 * Gets the SQL string to be used to query the database when the table editor is constructed
	 * This method can return null if isReturningResultSet() is true
	 * @return the SQL query string
	 */
	public String getSQLQueryString();
	
	/**
	 * Gets the Result Set for the construction of the table editor.
	 * This method can return null if isReturningResultSet() is false.
	 * @return the Result Set returned by the external filter implementation
	 */
	public ResultSet getResultSet();
	
	/**
	 * Sets the Table to be edited
	 * @param aTable the table that is being edited
	 */
	public void setTable(Table aTable);
}
