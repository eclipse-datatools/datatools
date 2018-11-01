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

package org.eclipse.datatools.enablement.oda.ws.wsdl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Service;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ws.BaseTest;
import org.eclipse.datatools.enablement.oda.ws.util.TestConstants;
import org.eclipse.datatools.enablement.oda.ws.util.TestUtil;
import org.eclipse.datatools.enablement.oda.ws.util.WSDLAdvisor;

/**
 * 
 */

public class WSDLAdvisorTest extends BaseTest
{

	/**
	 * 
	 * @throws Exception
	 */
	public void testQName( ) throws Exception
	{
		Definition definition = WSDLAdvisor.getDefinition( TestConstants.WSDL_FILE_ADDRESS );
		if ( definition == null )
			return;

		Map services = definition.getServices( );
		Iterator srcIT = services.keySet( ).iterator( );
		while ( srcIT.hasNext( ) )
		{
			Service service = (Service) services.get( srcIT.next( ) );
			assertEquals( "AddressServiceService", service.getQName( )
					.getLocalPart( ) );
		}
	}

	/**
	 * 
	 */
	public void testSOAPActionURI( )
	{
		String wsdlURI = TestConstants.WSDL_FILE_CALCULATOR;
		String operationTrace = TestConstants.OPERATION_TRACE_CALCULATOR;

		assertEquals( "",
				WSDLAdvisor.getSOAPActionURI( wsdlURI, operationTrace ) );
	}

	/**
	 * 
	 */
	public void testLocationURI( )
	{
		String wsdlURI = TestConstants.WSDL_FILE_CALCULATOR;
		String operationTrace = TestConstants.OPERATION_TRACE_CALCULATOR;

		assertEquals( "http://localhost:8080/axis/services/Calculator.jws",
				WSDLAdvisor.getLocationURI( wsdlURI, operationTrace ) );
	}

	/**
	 * rpc|simple|value
	 * 
	 * @throws IOException
	 * @throws OdaException 
	 */
	public void testTemplate1( ) throws IOException, OdaException
	{
		WSDLAdvisor wsdlAdvisor=new WSDLAdvisor();
		String template = wsdlAdvisor.getSOAPRequestTemplate( TestConstants.WSDL_FILE_CALCULATOR,
				TestConstants.OPERATION_TRACE_CALCULATOR );
		TestUtil.writeToFile( TestConstants.OUTPUT_FILE_TESTTEMPLATE1, template );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.GOLDEN_FILE_TESTTEMPLATE1 ),
				new File( TestConstants.OUTPUT_FILE_TESTTEMPLATE1 ) ) );
	}

	/**
	 * rpc|complex|value
	 * 
	 * @throws IOException
	 * @throws OdaException 
	 */
	public void testTemplate2( ) throws IOException, OdaException
	{
		WSDLAdvisor wsdlAdvisor=new WSDLAdvisor();
		String template = wsdlAdvisor.getSOAPRequestTemplate( TestConstants.WSDL_FILE_ADDRESS,
				TestConstants.OPERATION_TRACE_ADDRESS );
		TestUtil.writeToFile( TestConstants.OUTPUT_FILE_TESTTEMPLATE2, template );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.GOLDEN_FILE_TESTTEMPLATE2 ),
				new File( TestConstants.OUTPUT_FILE_TESTTEMPLATE2 ) ) );
	}

	/**
	 * rpc|complex|reference
	 * 
	 * @throws IOException
	 */
	public void testTemplate3( ) throws IOException
	{
	}

	/**
	 * doc|complex|value
	 * 
	 * @throws IOException
	 * @throws OdaException 
	 */
	public void testTemplate4( ) throws IOException, OdaException
	{
		WSDLAdvisor wsdlAdvisor=new WSDLAdvisor();
		String template = wsdlAdvisor.getSOAPRequestTemplate( TestConstants.WSDL_FILE_STOCKQUOTES,
				TestConstants.OPERATION_TRACE_STOCKQUOTES );
		TestUtil.writeToFile( TestConstants.OUTPUT_FILE_TESTTEMPLATE4, template );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.GOLDEN_FILE_TESTTEMPLATE4 ),
				new File( TestConstants.OUTPUT_FILE_TESTTEMPLATE4 ) ) );
	}

	/**
	 * doc|complex|reference
	 * 
	 * @throws IOException
	 * @throws OdaException 
	 */
	public void testTemplate5( ) throws IOException, OdaException
	{
		WSDLAdvisor wsdlAdvisor=new WSDLAdvisor();
		String template = wsdlAdvisor.getSOAPRequestTemplate( TestConstants.WSDL_FILE_DNBPATRIOTACT,
				TestConstants.OPERATION_TRACE_DNBPATRIOTACT );
		TestUtil.writeToFile( TestConstants.OUTPUT_FILE_TESTTEMPLATE5, template );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.GOLDEN_FILE_TESTTEMPLATE5 ),
				new File( TestConstants.OUTPUT_FILE_TESTTEMPLATE5 ) ) );
	}

	/**
	 * with|complex|value
	 * 
	 * @throws IOException
	 * @throws OdaException 
	 */
	public void testTemplate6( ) throws IOException, OdaException
	{
		WSDLAdvisor wsdlAdvisor=new WSDLAdvisor();
		String template = wsdlAdvisor.getSOAPRequestTemplate( TestConstants.WSDL_FILE_XGLOBALHISTORICAL,
				TestConstants.OPERATION_TRACE_XGLOBALHISTORICAL );
		TestUtil.writeToFile( TestConstants.OUTPUT_FILE_TESTTEMPLATE6, template );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.GOLDEN_FILE_TESTTEMPLATE6 ),
				new File( TestConstants.OUTPUT_FILE_TESTTEMPLATE6 ) ) );
	}

	/**
	 * with|complex|reference
	 * 
	 * @throws IOException
	 * @throws OdaException 
	 */
	public void testTemplate7( ) throws IOException, OdaException
	{
		WSDLAdvisor wsdlAdvisor=new WSDLAdvisor();
		String template = wsdlAdvisor.getSOAPRequestTemplate( TestConstants.WSDL_FILE_FOREIGNEXCHANGERATE,
				TestConstants.OPERATION_TRACE_FOREIGNEXCHANGERATE );
		TestUtil.writeToFile( TestConstants.OUTPUT_FILE_TESTTEMPLATE7, template );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.GOLDEN_FILE_TESTTEMPLATE7 ),
				new File( TestConstants.OUTPUT_FILE_TESTTEMPLATE7 ) ) );
	}
	
	/**
	 * with|complex|reference
	 * 
	 * @throws IOException
	 * @throws OdaException 
	 */
	public void testGetLocalSOAPResponseTemplate( ) throws IOException, OdaException
	{
		WSDLAdvisor wsdlAdvisor=new WSDLAdvisor();
		String template = wsdlAdvisor.getLocalSOAPResponseTemplate( TestConstants.WSDL_FILE_AWSECOMMERCESERVICE,
				TestConstants.OPERATION_TRACE_AWSECOMMERCESERVICE );
		TestUtil.writeToFile( TestConstants.OUTPUT_FILE_TESTLOCAlSOAPRESPONSETEMPLATE, template );

		assertTrue( TestUtil.compareTextFile( new File( TestConstants.GOLDEN_FILE_TESTLOCAlSOAPRESPONSETEMPLATE ),
				new File( TestConstants.OUTPUT_FILE_TESTLOCAlSOAPRESPONSETEMPLATE ) ) );
	}

}
