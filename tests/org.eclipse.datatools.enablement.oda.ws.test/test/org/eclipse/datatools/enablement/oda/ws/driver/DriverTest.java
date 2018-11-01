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

package org.eclipse.datatools.enablement.oda.ws.driver;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ws.BaseTest;
import org.eclipse.datatools.enablement.oda.ws.impl.Driver;
import org.eclipse.datatools.enablement.oda.ws.util.TestConstants;
import org.eclipse.datatools.enablement.oda.ws.util.WSDLAdvisor;

/**
 * 
 */

public class DriverTest extends BaseTest
{

	/**
	 * 
	 * @throws OdaException
	 */
	public void testDriver1( ) throws OdaException
	{
		String wsdlURI = TestConstants.WSDL_FILE_TEMPCONVERT;
		String operationTrace = TestConstants.OPERATION_TRACE_TEMPCONVERT;
		String xmlQueryText = "table0#-TNAME-#table0#:#[/soap:Envelope/soap:Body/CelsiusToFahrenheitResponse/CelsiusToFahrenheitResult]#:#{CelsiusToFahrenheitResult;STRING;}";

		IQuery query = prepareQuery( wsdlURI, operationTrace, xmlQueryText );
		query.setInt( 1, 37 );

		IResultSet resultSet = query.executeQuery( );;
		while ( resultSet.next( ) )
			assertEquals( 99, resultSet.getInt( 1 ) );
	}

	/**
	 * @throws OdaException
	 * 
	 */
	public void testDriver2( ) throws OdaException
	{
		String wsdlURI = TestConstants.WSDL_FILE_STOCKQUOTES;
		String operationTrace = TestConstants.OPERATION_TRACE_STOCKQUOTES;
		String xmlQueryText = "table0#-TNAME-#table0#:#[/soap:Envelope/soap:Body/GetQuotesResponse/GetQuotesResult/Quote]#:#{StockTicker;STRING;/StockTicker}";

		IQuery query = prepareQuery( wsdlURI, operationTrace, xmlQueryText );
		query.setString( 1, "ibm" );

		IResultSet resultSet = query.executeQuery( );;
		while ( resultSet.next( ) )
			assertEquals( "IBM", resultSet.getString( 1 ) );
	}

	private IQuery prepareQuery( String wsdlURI, String operationTrace,
			String xmlQueryText ) throws OdaException
	{
		IDriver driver = new Driver( );
		IConnection connection = driver.getConnection( null );

		Properties prop = new Properties( );
		prop.setProperty( "wsdlURI", wsdlURI );
		connection.open( prop );

		IQuery query = connection.newQuery( null );
		query.setProperty( "operationTrace", operationTrace );
		query.setProperty( "xmlQueryText", xmlQueryText );
		WSDLAdvisor wsdlAdvisor=new WSDLAdvisor();
		query.prepare( wsdlAdvisor.getSOAPRequestTemplate( wsdlURI,
				operationTrace ) );

		return query;
	}

}
