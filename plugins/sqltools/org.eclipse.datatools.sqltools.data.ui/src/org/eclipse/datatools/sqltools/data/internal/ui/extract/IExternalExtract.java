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

package org.eclipse.datatools.sqltools.data.internal.ui.extract;

import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.data.internal.core.common.Output;

/**
 * Clients extending the externalTableDataExtract extension-point must implement
 */
public interface IExternalExtract
{
	/**
	 * Sets the Table from which the data is to be extracted
	 * @param table the Table Object
	 */
	public void setTable(Table table);
	
	/**
	 * Sets the file path for which the extracted data is to be saved
	 * @param path the file path to save the extracted data
	 */
	public void setFilePath(String path);
	
	/**
	 * Sets the delimiters for the columns and strings designations
	 * @param colDelim the column delimiter
	 * @param stDelim the string delimiter
	 */
	public void setDelimiters(String colDelim, String stDelim);
	
	/**
	 * Returns whether or not to use external extract
	 */
	public boolean isUseExternalExtract();
	
	/**
	 * Performs the table data extraction
	 * @param output the outputItemAdapter
	 * @return the output code indicating the status
	 * @see import org.eclipse.datatools.sqltools.data.internal.core.common.Output;
	 */
	public int doExtract(Output output);
}
