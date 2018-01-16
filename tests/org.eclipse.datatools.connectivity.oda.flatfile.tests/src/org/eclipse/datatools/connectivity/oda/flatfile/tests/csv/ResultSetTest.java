/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.oda.flatfile.tests.csv;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.tests.ResultSetTestBase;
import org.eclipse.datatools.connectivity.oda.flatfile.tests.TestUtil;

/**
 * test case for ResultSet implementation under comma sperated CSV
 */

public class ResultSetTest extends ResultSetTestBase
{
	/**
	 * 
	 * @throws OdaException
	 */
	public ResultSetTest( ) throws OdaException
	{
		super( );
	}

	/**
	 * 
	 */
	protected String getDelimiter( )
	{
		return CommonConstants.DELIMITER_COMMA_VALUE;
	}

	/**
	 * 
	 */
	protected String getSuffix( )
	{
		return TestUtil.SUFFIX_COMMA;
	}

	/**
	 * 
	 */
	protected String getDelimiterName( )
	{
		return CommonConstants.DELIMITER_COMMA;
	}

	/**
	 * 
	 */
	protected String getExtension( )
	{
		return TestUtil.CSV_EXTENSION;
	}
}
