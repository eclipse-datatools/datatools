/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.ui.load;

import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.data.internal.core.common.Output;

/**
 * Clients extending the externalTableDataLoad extension-point must implement
 */
public interface IExternalLoad
{
	/**
	 * Sets the Table to which the data is to be loaded
	 * @param table the Table Object
	 */
	public void setTable(Table table);
	
	/**
	 * Sets the file path from which the data is to be retrieved
	 * @param path the file path to retrieve the saved data
	 */
	public void setFilePath(String path);
	
	/**
	 * Sets the delimiters for the columns and strings designations
	 * @param colDelim the column delimiter
	 * @param stDelim the string delimiter
	 */
	public void setDelimiters(String colDelim, String stDelim);
	
	/**
	 * Sets whether or not to replace existing data in the table
	 * @param replace replace existing data if true, do not replace if false
	 */
	public void setReplace(boolean replace);
	
	/**
	 * Returns whether or not to use the external load	 
	 */
	public boolean isUseExternalLoad();
	
	/**
	 * Performs the table load
	 * @param output the Output object
	 * @return the output code indicating the status
	 * @see import org.eclipse.datatools.sqltools.data.internal.core.common.Output;
	 */
	public int doLoad(Output output);
}
