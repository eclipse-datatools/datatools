/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.connectivity.oda.flatfile.tests.ssv;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.tests.ResultSetTestBase;
import org.eclipse.datatools.connectivity.oda.flatfile.tests.TestUtil;

/**
 * test case for ResultSet implementation under semicolon sperated CSV
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
		return CommonConstants.DELIMITER_SEMICOLON_VALUE;
	}

	/**
	 * 
	 */
	protected String getSuffix( )
	{
		return TestUtil.SUFFIX_SEMICOLON;
	}

	/**
	 * 
	 */
	protected String getDelimiterName( )
	{
		return CommonConstants.DELIMITER_SEMICOLON;
	}

	/**
	 * 
	 */
	protected String getExtension( )
	{
		return TestUtil.SSV_EXTENSION;
	}
}
