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

package org.eclipse.datatools.enablement.oda.ws.util;

/**
 * This class hosts all the constants that are used in testing.
 * 
 */
public class TestConstants
{

	private static String home = "";
	static
	{
		home = System.getProperty( "wsdl.home" );
		if ( home == null )
			home = "";
		home = home == "" ? home : home + "/";
	}

	// wsdlFiles
	public static final String WSDL_FILE_ADDRESS = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/input/Address.wsdl";
	public static final String WSDL_FILE_CALCULATOR = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/input/Calculator.wsdl";
	public static final String WSDL_FILE_DNBPATRIOTACT = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/input/DnBPatriotAct.wsdl";
	public static final String WSDL_FILE_FOREIGNEXCHANGERATE = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/input/ForeignExchangeRate.wsdl";
	public static final String WSDL_FILE_STOCKQUOTES = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/input/StockQuotes.wsdl";
	public static final String WSDL_FILE_TEMPCONVERT = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/input/tempconvert.wsdl";
	public static final String WSDL_FILE_XGLOBALHISTORICAL = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/input/xGlobalHistorical.wsdl";
	public static final String WSDL_FILE_AWSECOMMERCESERVICE = home
	+ "test/org/eclipse/datatools/enablement/oda/ws/input/AWSECommerceService.wsdl";

	// operationTraces
	public static final String OPERATION_TRACE_ADDRESS = "AddressServiceService$-$Address$-$updateAddress";
	public static final String OPERATION_TRACE_CALCULATOR = "CalculatorService$-$Calculator$-$subtract";
	public static final String OPERATION_TRACE_DNBPATRIOTACT = "PatriotActComplianceService$-$PatriotActComplianceServiceSoap$-$PatriotActCompliance";
	public static final String OPERATION_TRACE_FOREIGNEXCHANGERATE = "ForeignExchangeRates$-$ForeignExchangeRatesSoap$-$GetLatestRates";
	public static final String OPERATION_TRACE_STOCKQUOTES = "StockQuotes$-$StockQuotesSoap$-$GetStockQuotes";
	public static final String OPERATION_TRACE_TEMPCONVERT = "TempConvert$-$TempConvertSoap$-$CelsiusToFahrenheit";
	public static final String OPERATION_TRACE_XGLOBALHISTORICAL = "XigniteGlobalHistorical$-$XigniteGlobalHistoricalSoap$-$GetGlobalLastClosingPrice";
	public static final String OPERATION_TRACE_AWSECOMMERCESERVICE = "AWSECommerceService$-$AWSECommerceServicePort$-$ItemSearch";
	
	// goldenFiles
	public static final String GOLDEN_FILE_TESTTEMPLATE1 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testTemplate1.txt";
	public static final String GOLDEN_FILE_TESTTEMPLATE2 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testTemplate2.txt";
	public static final String GOLDEN_FILE_TESTTEMPLATE3 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testTemplate3.txt";
	public static final String GOLDEN_FILE_TESTTEMPLATE4 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testTemplate4.txt";
	public static final String GOLDEN_FILE_TESTTEMPLATE5 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testTemplate5.txt";
	public static final String GOLDEN_FILE_TESTTEMPLATE6 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testTemplate6.txt";
	public static final String GOLDEN_FILE_TESTTEMPLATE7 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testTemplate7.txt";
	public static final String GOLDEN_FILE_TESTTEMPLATE8 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testTemplate8.txt";
	public static final String GOLDEN_FILE_TESTTEMPLATE9 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testTemplate9.txt";
	public static final String GOLDEN_FILE_TESTLOCAlSOAPRESPONSETEMPLATE = home
	+ "test/org/eclipse/datatools/enablement/oda/ws/golden/WSDLAdvisorTest.testLocalSoapResponseTemplate.txt";

	// outputFiles
	public static final String OUTPUT_FILE_TESTTEMPLATE1 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testTemplate1.txt";
	public static final String OUTPUT_FILE_TESTTEMPLATE2 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testTemplate2.txt";
	public static final String OUTPUT_FILE_TESTTEMPLATE3 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testTemplate3.txt";
	public static final String OUTPUT_FILE_TESTTEMPLATE4 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testTemplate4.txt";
	public static final String OUTPUT_FILE_TESTTEMPLATE5 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testTemplate5.txt";
	public static final String OUTPUT_FILE_TESTTEMPLATE6 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testTemplate6.txt";
	public static final String OUTPUT_FILE_TESTTEMPLATE7 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testTemplate7.txt";
	public static final String OUTPUT_FILE_TESTTEMPLATE8 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testTemplate8.txt";
	public static final String OUTPUT_FILE_TESTTEMPLATE9 = home
			+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testTemplate9.txt";
	public static final String OUTPUT_FILE_TESTLOCAlSOAPRESPONSETEMPLATE = home
	+ "test/org/eclipse/datatools/enablement/oda/ws/output/WSDLAdvisorTest.testLocalSoapResponseTemplate.txt";

	// miscellaneous

}
