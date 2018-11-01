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

package org.eclipse.datatools.connectivity.oda.flatfile.tests.tsv;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.tests.ResultSetMetaDataTestBase;
import org.eclipse.datatools.connectivity.oda.flatfile.tests.TestUtil;

/**
 * 
 */

public class ResultSetMetaDataTest extends ResultSetMetaDataTestBase
{

	/**
	 * 
	 * @throws OdaException
	 */
	public ResultSetMetaDataTest( ) throws OdaException
	{
		super( );
	}

	/**
	 * 
	 */
	protected String getDelimiter( )
	{
		return CommonConstants.DELIMITER_TAB_VALUE;
	}

	/**
	 * 
	 */
	protected String getDelimiterName( )
	{
		return CommonConstants.DELIMITER_TAB;
	}

	/**
	 * 
	 */
	protected String getSuffix( )
	{
		return TestUtil.SUFFIX_TAB;
	}

	/**
	 * 
	 */
	protected String getExtension( )
	{
		return TestUtil.TSV_EXTENSION;
	}
}
