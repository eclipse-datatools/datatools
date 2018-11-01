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
package org.eclipse.datatools.enablement.oda.xml.test.util;

/**
 * This class host all the constants that are used in test.
 * 
 */
public class TestConstants
{
	private static String home = "";
	static{
		home = System.getProperty("xml.home");
		if( home == null )
			home = "";
		home = home==""?home:home+"/";
	}
	public static final String SMALL_XML_FILE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/small.xml";
	public static final String SMALL_XSD_FILE_RELATIVE = "small.xsd";
	public static final String SMALL_XML_FILE_RELATIVE = "small.xml";
	public static final String SMALL_XML_RESOURCE = home + "test/org/eclipse/datatools/enablement/oda/xml/input/";
	public static final String RECURSIVE_XML_FILE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/recursive.xml";
	public static final String CRITICAL_XML_FILE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/critical.xml";
	public static final String UTF8BOM = home+"test/org/eclipse/datatools/enablement/oda/xml/input/utf8bom.xml";
	public static final String RECURSIVE_DUPLICATENAME = home+"test/org/eclipse/datatools/enablement/oda/xml/input/recursiveDuplicateName.xml";
	public static final String TEST_FILTER = home+"test/org/eclipse/datatools/enablement/oda/xml/input/test.xml";
	public static final String TEST_RELATIVE_LOCATION = home+"test/org/eclipse/datatools/enablement/oda/xml/input/sample.xml";
	public static final String MIXED_FILTER = home+"test/org/eclipse/datatools/enablement/oda/xml/input/TestMixedFilter.xml";
	public static final String NESTED_TABLE_ROOT_FILTER = home+"test/org/eclipse/datatools/enablement/oda/xml/input/nestedTableRootFilter.xml";
	public static final String EMPTY_ELEMENT = home+"test/org/eclipse/datatools/enablement/oda/xml/input/emptyElement.xml";
	public static final String TABLE_FILTER = home+"test/org/eclipse/datatools/enablement/oda/xml/input/TableFilter.xml";
	public static final String BOOKSTORE_XML_FILE = home + "test/org/eclipse/datatools/enablement/oda/xml/input/BookStore.xml";
	public static final String BOOKSTORE_XSD_FILE = home + "test/org/eclipse/datatools/enablement/oda/xml/input/BookStore.xsd";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_INPUT_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/input/SchemaPopulationUtilTest_getSchemaTree_XSD.xsd";
	public static final String TEST_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/input/test.xsd";
	public static final String TEST_XSD_GROUP = home+"test/org/eclipse/datatools/enablement/oda/xml/input/nestedXSD.xsd";
	public static final String TEST_XSD_COMPLEX = home+"test/org/eclipse/datatools/enablement/oda/xml/input/complex.xsd";
	public static final String TEST_XSD_SELFRECURSIVE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/selfrecursive.xsd";
	public static final String TEST_XSD_COMPLEXRECURSIVE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/complexrecursive.xsd";
	public static final String TEST_XSD_COMPLEXRECURSIVE_1 = home+"test/org/eclipse/datatools/enablement/oda/xml/input/business-process.xsd";
	public static final String NESTED_COMPLEXTYPE_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/input/nestedcomplextype.xsd";
	public static final String DATATYPE_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/input/datatype.xsd";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_NEST_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_NEST_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_NEST_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_NEST_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_DATATYPE = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_DATATYPE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_DATATYPE = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_DATATYPE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_GROUP = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_GROUP.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_GROUP = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_GROUP.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_COMPLEX = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_COMPLEX.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_COMPLEX = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_COMPLEX.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_SELFRECURSIVE = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_SELFRECURSIVE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_SELFRECURSIVE = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_SELFRECURSIVE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_OUTPUT_COMPLEXRECURSIVE = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_COMPLEXRECURSIVE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GOLDEN_COMPLEXRECURSIVE = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_COMPLEXRECURSIVE.txt";


	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XML.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XML.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XSD_WITHOUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XSD_WITHOUT_XML.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XSD_WITHOUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XSD_WITHOUT_XML.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_WITH_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XML_with_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML_WITH_XSD = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XML_with_XSD.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_OUTPUT_XML_BOOKSTORE = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SchemaPopulationUtilTest_getSchemaTree_XML_BOOKSTORE.txt";
	public static final String SCHEMA_POPULATION_UTIL_TEST_GET_SCHEMA_TREE_GOLDEN_XML_BOOKSTORE = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SchemaPopulationUtilTest_getSchemaTree_XML_BOOKSTORE.txt";
	
	public static final String SAX_PARSER_TEST0_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test0.txt";
	public static final String SAX_PARSER_TEST0_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test0.txt";
	public static final String SAX_PARSER_TEST1_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test1.txt";
	public static final String SAX_PARSER_TEST1_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test1.txt";
	public static final String SAX_PARSER_TEST2_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test2.txt";
	public static final String SAX_PARSER_TEST2_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test2.txt";
	public static final String SAX_PARSER_TEST3_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test3.txt";
	public static final String SAX_PARSER_TEST3_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test3.txt";
	public static final String SAX_PARSER_TEST4_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test4.txt";
	public static final String SAX_PARSER_TEST4_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test4.txt";
	public static final String SAX_PARSER_TEST5_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test5.txt";
	public static final String SAX_PARSER_TEST5_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test5.txt";
	public static final String SAX_PARSER_TEST6_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test6.txt";
	public static final String SAX_PARSER_TEST6_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test6.txt";
	public static final String SAX_PARSER_TEST7_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test7.txt";
	public static final String SAX_PARSER_TEST7_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test7.txt";
	public static final String SAX_PARSER_TEST8_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test8.txt";
	public static final String SAX_PARSER_TEST8_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test8.txt";
	public static final String SAX_PARSER_TEST9_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test9.txt";
	public static final String SAX_PARSER_TEST9_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test9.txt";
	public static final String SAX_PARSER_TEST10_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test10.txt";
	public static final String SAX_PARSER_TEST10_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test10.txt";
	public static final String SAX_PARSER_TEST11_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test11.txt";
	public static final String SAX_PARSER_TEST11_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test11.txt";
	public static final String SAX_PARSER_TEST12_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test12.txt";
	public static final String SAX_PARSER_TEST12_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test12.txt";
	public static final String SAX_PARSER_TEST13_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test13.txt";
	public static final String SAX_PARSER_TEST13_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test13.txt";
	public static final String SAX_PARSER_TEST14_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test14.txt";
	public static final String SAX_PARSER_TEST14_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test14.txt";
	public static final String SAX_PARSER_TEST15_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test15.txt";
	public static final String SAX_PARSER_TEST15_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test15.txt";
	public static final String SAX_PARSER_TEST16_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test16.txt";
	public static final String SAX_PARSER_TEST16_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test16.txt";
	public static final String SAX_PARSER_TEST17_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test17.txt";
	public static final String SAX_PARSER_TEST17_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test17.txt";
	public static final String SAX_PARSER_TEST18_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test18.txt";
	public static final String SAX_PARSER_TEST18_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test18.txt";
	public static final String SAX_PARSER_TEST19_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test19.txt";
	public static final String SAX_PARSER_TEST19_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test19.txt";
	public static final String SAX_PARSER_TEST20_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test20.txt";
	public static final String SAX_PARSER_TEST20_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test20.txt";
	public static final String SAX_PARSER_TEST21_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test21.txt";
	public static final String SAX_PARSER_TEST21_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test21.txt";
	public static final String SAX_PARSER_TEST22_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test22.txt";
	public static final String SAX_PARSER_TEST22_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test22.txt";
	public static final String SAX_PARSER_TEST23_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test23.txt";
	public static final String SAX_PARSER_TEST23_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test23.txt";
	public static final String SAX_PARSER_TEST24_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test24.txt";
	public static final String SAX_PARSER_TEST24_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test24.txt";
	public static final String SAX_PARSER_TEST25_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test25.txt";
	public static final String SAX_PARSER_TEST25_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test25.txt";
	public static final String SAX_PARSER_TEST26_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test26.txt";
	public static final String SAX_PARSER_TEST26_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test26.txt";
	public static final String SAX_PARSER_TEST27_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test27.txt";
	public static final String SAX_PARSER_TEST27_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test27.txt";
	public static final String SAX_PARSER_TEST28_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test28.txt";
	public static final String SAX_PARSER_TEST28_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test28.txt";
	public static final String SAX_PARSER_TEST29_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.test29.txt";
	public static final String SAX_PARSER_TEST29_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.test29.txt";
	public static final String SAX_PARSER_TEST_PARAM_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.testParam.txt";
	public static final String SAX_PARSER_TEST_PARAM_GOLDEN_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/golden/SaxParserTest.testParam.txt";
	
	public static final String XML_FILE_WITH_NAMESPACE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/xmlWithNamespace.xml";
	public static final String XML_FILE_WITH_NAMESPACE2 = home+"test/org/eclipse/datatools/enablement/oda/xml/input/feed_from_google_new.xml";
	
	//public static final String SAX_PARSER_TESTLARGE_OUTPUT_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/output/SaxParserTest.testLarge.txt";

	public static final String LARGE_XML_FILE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/large";

	public static final String HUGE_XML_FOR_PERFORMANCE = home+"test/org/eclipse/datatools/enablement/oda/xml/input/hugeForPerformance.xml";
	
	public static final String SPECIAL_ENCODING_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/input/specialEncoding_ISO-8859-1.xml";
	
	public static final String ANY_RECURSIVE_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/input/anyRecursive.xml";
	
	public static final String DOUBLE_SLASH_XML = home+"test/org/eclipse/datatools/enablement/oda/xml/input/doubleslash.xml";
	
}
