/*
 ******************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 ******************************************************************************
 */

package org.eclipse.datatools.enablement.oda.ws.impl;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ws.soap.SOAPParameter;

/**
 * Implementation class of IParameterMetaData for an ODA runtime driver. <br>
 * For demo purpose, the auto-generated method stubs have hard-coded
 * implementation that returns a pre-defined set of meta-data and query results.
 * A custom ODA driver is expected to implement own data source specific
 * behavior in its place.
 */
public class ParameterMetaData implements IParameterMetaData
{

	private SOAPParameter[] parameters;

	public ParameterMetaData( SOAPParameter[] parameters )
	{
		this.parameters = parameters;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterCount()
	 */
	public int getParameterCount( ) throws OdaException
	{
		assertNotNull( );
		return parameters.length;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterMode(int)
	 */
	public int getParameterMode( int param ) throws OdaException
	{
		assertNotNull( );
		return IParameterMetaData.parameterModeIn;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterType(int)
	 */
	public int getParameterType( int param ) throws OdaException
	{
		assertNotNull( );
		return java.sql.Types.CHAR;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterTypeName(int)
	 */
	public String getParameterTypeName( int param ) throws OdaException
	{
		assertNotNull( );
		int nativeTypeCode = getParameterType( param );
		return Driver.getNativeDataTypeName( nativeTypeCode );
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getPrecision(int)
	 */
	public int getPrecision( int param ) throws OdaException
	{
		assertNotNull( );
		return -1;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getScale(int)
	 */
	public int getScale( int param ) throws OdaException
	{
		assertNotNull( );
		return -1;
	}

	/*
	 * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#isNullable(int)
	 */
	public int isNullable( int param ) throws OdaException
	{
		assertNotNull( );
		return IParameterMetaData.parameterNullableUnknown;
	}

	public String getParameterName( int param ) throws OdaException
	{
		assertNotNull( );
		return parameters[param - 1].getName( );
	}

	private void assertNotNull( ) throws OdaException
	{
		if ( parameters == null )
			throw new OdaException( );// TODO
	}

}
