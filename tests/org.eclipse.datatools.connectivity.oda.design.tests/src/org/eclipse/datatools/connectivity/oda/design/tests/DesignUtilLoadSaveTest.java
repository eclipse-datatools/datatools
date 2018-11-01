/*
 *************************************************************************
 * Copyright (c) 2007, 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.design.AxisAttributes;
import org.eclipse.datatools.connectivity.oda.design.AxisType;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.CustomData;
import org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse;
import org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.DynamicValuesQuery;
import org.eclipse.datatools.connectivity.oda.design.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.design.FilterExpressionType;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.OrExpression;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.ResultSubset;
import org.eclipse.datatools.connectivity.oda.design.SortDirectionType;
import org.eclipse.datatools.connectivity.oda.design.SortKey;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * Test cases for loading and saving OdaDesignSession utility in DesignUtil.  
 */
public class DesignUtilLoadSaveTest extends TestCase
{
    private String m_testFilePath;
    private File m_tempOutFile;
    
    protected void setUp() throws Exception
    {
        super.setUp();
        if( m_testFilePath == null )
        {
            try
            {
                m_testFilePath = TestUtil.getPluginTestFilePath();
            }
            catch( IOException ex )
            {
                ex.printStackTrace();
                fail();
            }
        }
    }

    protected void tearDown() throws Exception
    {        
        cleanupFile( m_tempOutFile );
        m_tempOutFile = null;
        super.tearDown();
    }

    public void testLoadOdaDesignSession()
    {
        OdaDesignSession design = loadOdaDesignSession( new File( getSampleDbTestFilePath() ));
        assertEquals( "sampledb Data Set", design.getResponseDataSetDesign().getName() ); //$NON-NLS-1$
        assertNotNull( design.getRequestDataSetDesign() );
        assertNotNull( design.getResponseDataSetDesign() );
    }
    
    private OdaDesignSession loadOdaDesignSession( File testFile )
    {
        OdaDesignSession design = null;
        try
        {
            design = DesignUtil.loadOdaDesignSession( testFile );
        }
        catch( Exception ex )
        {
            fail();
        }
        assertNotNull( design );
        return design;
    }
    
    private String getGoldenTestFilePath()
    {
        return m_testFilePath + "/golden"; //$NON-NLS-1$
    }
    
    private String getSampleDbTestFilePath()
    {
        return getGoldenTestFilePath() + "/jdbcBirtSampleSession.xml"; //$NON-NLS-1$
    }
    
    private String getSampleDbTestMigrateDefaultValueFilePath()
    {
        return getGoldenTestFilePath() + "/jdbcBirtSampleSession_multDefValues.xml"; //$NON-NLS-1$
    }
    
    private String getSampleDbTestMigrateDefaultValueOutFilePath()
    {
        return getGoldenTestFilePath() + "/jdbcBirtSampleSession_multDefValues_out.xml"; //$NON-NLS-1$
    }
    
    private String getSampleDbResourceFilePath()
    {
        return getGoldenTestFilePath() + "/BirtSampleResourceSession.xml"; //$NON-NLS-1$
    }
    
    private String getTempOutFilePath()
    {
        return m_testFilePath + "/tempOut.xml"; //$NON-NLS-1$
    }

    public void testSaveDesignSession()
    {
        File goldenFile = new File( getSampleDbTestFilePath() );
        OdaDesignSession design = loadOdaDesignSession( goldenFile );

        File tempOut = getTempOutFile();
        saveDesignSession( design, tempOut );
        assertTrue( compareFileContent( goldenFile, tempOut ) );
        
        // modify the design and test nonEqual output
        design.getResponseDataSetDesign().setName( "test change in name" ); //$NON-NLS-1$
        saveDesignSession( design, tempOut );
        assertFalse( compareFileContent( goldenFile, tempOut ) );
    }
    
    public void testAddOptionalResourceIDElement()
    {
        String odaDesignURIString = "http://www.eclipse.org/datatools/connectivity/oda/design"; //$NON-NLS-1$
        URI odaDesignURI = null;
        try
        {
            odaDesignURI = new URI( odaDesignURIString );
        }
        catch( URISyntaxException ex )
        {
            fail();
        }

        // assign baseURI each in URI and String format, and test their format conversion
        ResourceIdentifiers resourceIDs = DesignFactory.eINSTANCE.createResourceIdentifiers();
        resourceIDs.setApplResourceBaseURIString( odaDesignURIString );        
        resourceIDs.setDesignResourceBaseURI( odaDesignURI );
        assertEquals( odaDesignURIString, resourceIDs.getDesignResourceBaseURIString() );
        assertEquals( odaDesignURI, resourceIDs.getApplResourceBaseURI() );
        
        // add the ResourceIdentifiers to an ODA design session, and test its persistent format
        File goldenFile = new File( getSampleDbTestFilePath() );
        OdaDesignSession design = loadOdaDesignSession( goldenFile );        
        DataSourceDesign dataSourceDesign = design.getRequestDataSourceDesign();
        dataSourceDesign.setHostResourceIdentifiers( resourceIDs );

        File tempOut = getTempOutFile();
        saveDesignSession( design, tempOut );

        goldenFile = new File( getSampleDbResourceFilePath() );
        assertTrue( compareFileContent( goldenFile, tempOut ) );
    }
    
    public void testCreateResultSetCriteriaDesign()
    {
        final String filterExprExtId = "org.eclipse.datatools.connectivity.oda.design.tests";   //$NON-NLS-1$
        
        File goldenFile = new File( getSampleDbTestFilePath() );
        OdaDesignSession design = loadOdaDesignSession( goldenFile );
        DataSetDesign dataSetDesign = design.getResponseDataSetDesign();
        
        ExpressionVariable exprVariable = DesignFactory.eINSTANCE.createExpressionVariable();
        exprVariable.setIdentifier( "CUSTOMERNAME" ); //$NON-NLS-1$
        exprVariable.setNativeDataTypeCode( 4 ); // integer
        
        ExpressionArguments exprArgs1 = DesignFactory.eINSTANCE.createExpressionArguments();

        DataSetParameters dataSetParams = dataSetDesign.getParameters();
        
        // test that the localizable attribute is properly handled by EcoreUtil.copy
        {
            ParameterDefinition paramDefn = dataSetParams.getParameterDefinitions().get( 0 );
            String desc = "default desc"; //$NON-NLS-1$
            String descKey = "myDescKey"; //$NON-NLS-1$
            paramDefn.getAttributes().setUiDescription( desc );
            paramDefn.getAttributes().getUiHints().setDescriptionKey( descKey );
            
            DataSetParameters copy = (DataSetParameters) EcoreUtil.copy( dataSetParams );
            ParameterDefinition paramDefnCopy = copy.getParameterDefinitions().get( 0 );
            assertEquals( desc, paramDefnCopy.getAttributes().getUiHints().getDescription() );
            assertEquals( descKey, paramDefnCopy.getAttributes().getUiHints().getDescriptionKey() );
        }
        
        Iterator<ParameterDefinition> iter = dataSetParams.getParameterDefinitions().iterator();
        while( iter.hasNext() )
        {
            ExpressionParameterDefinition newDynamicParamDefn = 
                exprArgs1.addDynamicParameter( (ParameterDefinition) EcoreUtil.copy( iter.next() ) );

            newDynamicParamDefn.addStaticValue( "ineffective static value" ); //$NON-NLS-1$
            assertFalse( newDynamicParamDefn.hasEffectiveStaticValues() );
        }
        ExpressionParameterDefinition newStaticParamDefn = exprArgs1.addStaticParameter( new Date() );
        assertTrue( newStaticParamDefn.hasEffectiveStaticValues() );
        
        CustomFilterExpression customExpr1 = DesignFactory.eINSTANCE.createCustomFilterExpression();
        customExpr1.setDeclaringExtensionId( filterExprExtId );
        customExpr1.setId( "1007" ); //$NON-NLS-1$
        customExpr1.setContextVariable( exprVariable );
        customExpr1.setContextArguments( exprArgs1 );
        customExpr1.setIsOptional( true );
        
        CustomFilterExpression customExpr2 = DesignFactory.eINSTANCE.createCustomFilterExpression();
        customExpr2.setDeclaringExtensionId( filterExprExtId );
        customExpr2.setId( "10005" ); //$NON-NLS-1$
        customExpr2.setContextVariable( exprVariable );

        ExpressionArguments exprArgs2 = DesignFactory.eINSTANCE.createExpressionArguments();
        newStaticParamDefn = exprArgs2.addStaticParameter( "static value 1" ); //$NON-NLS-1$
        newStaticParamDefn.addStaticValue( "static value 2 " );         //$NON-NLS-1$
        customExpr2.setContextArguments( exprArgs2 );

        assertTrue( newStaticParamDefn.hasEffectiveStaticValues() );
        assertEquals( 2, newStaticParamDefn.getEffectiveStaticValueCount() );
        
        OrExpression orExpr = DesignFactory.eINSTANCE.createOrExpression();
        orExpr.add( customExpr1 );
        orExpr.add( customExpr2 );
        
        DynamicFilterExpression dynamicFilterExpr = DesignFactory.eINSTANCE.createDynamicFilterExpression();
        dynamicFilterExpr.setContextVariable( exprVariable );
        dynamicFilterExpr.setContextArguments( exprArgs1 );
        dynamicFilterExpr.setIsOptional( false );
        FilterExpressionType defaultType = DesignFactory.eINSTANCE.createFilterExpressionType();
        defaultType.setDeclaringExtensionId( filterExprExtId );
        defaultType.setId( "10005" ); //$NON-NLS-1$
        dynamicFilterExpr.setDefaultType( defaultType );
        assertEquals( defaultType, dynamicFilterExpr.getDefaultType() );
        
        dataSetDesign.getPrimaryResultSet().getCriteria().setFilterSpecification( orExpr );
        
        // create sort spec in result set criteria
        SortKey sortKey1 = DesignFactory.eINSTANCE.createSortKey();
        sortKey1.setColumnPosition( 1 );

        assertTrue( sortKey1.getColumnName().length() == 0 ); // default empty name if position is set
        assertEquals( SortDirectionType.ASCENDING, sortKey1.getSortDirection() );   // default sort direction
        
        dataSetDesign.getPrimaryResultSet().getCriteria().addRowSortKey( sortKey1 );
        
        // test the created filter expressions are valid
        try
        {
            DesignUtil.validateObject( dataSetDesign );
        }
        catch( IllegalStateException ex )
        {
            fail();
        }  
        
        // test saving updated design session with the filter expression
        File tempOut = getTempOutFile();
        saveDesignSession( design, tempOut );
    }
    
    public void testCreateAxisAttributesDesign()
    {
        File goldenFile = new File( getSampleDbTestFilePath() );
        OdaDesignSession design = loadOdaDesignSession( goldenFile );
        DataSetDesign dataSetDesign = design.getResponseDataSetDesign();
        ColumnDefinition columnDefn =
            dataSetDesign.getPrimaryResultSet().getResultSetColumns()
                .getResultColumnDefinitions().get( 0 );
        assertNotNull( columnDefn );
        
        AxisAttributes axisAttrs = DesignFactory.eINSTANCE.createAxisAttributes();
        columnDefn.setMultiDimensionAttributes( axisAttrs );
        axisAttrs.setAxisType( AxisType.DIMENSION_MEMBER_LITERAL );
        axisAttrs.setRelatedColumns( DesignFactory.eINSTANCE.createResultSubset() );
        ResultSubset relatedResultColumns = axisAttrs.getRelatedColumns();
        relatedResultColumns.addColumnIdentifier( "column1" );
        relatedResultColumns.addColumnIdentifier( null, 2 );
        
        assertEquals( 2, relatedResultColumns.getColumnIdentifiers().getIdentifiers().size() );
        // check that null column name is automatically converted to empty string
        assertEquals( "", relatedResultColumns.getColumnIdentifiers().getIdentifiers().get( 1 ).getName() ); //$NON-NLS-1$
    }
    
//    public void testInputElementMigrateDefaultValues()
//    {
//        File goldenFile = new File( getSampleDbTestMigrateDefaultValueFilePath() );
//        OdaDesignSession design = loadOdaDesignSession( goldenFile );
//        DataSetDesign dataSetDesign = design.getResponseDataSetDesign();
//        
//        DataSetParameters dataSetParams = dataSetDesign.getParameters();
//        for( ParameterDefinition aParamDefn : dataSetParams.getParameterDefinitions() )
//        {
//            InputElementAttributes inputElementAttrs = aParamDefn.getInputAttributes().getElementAttributes();
//            
//            String existingDefaultValue = aParamDefn.getDefaultScalarValue();
//            assertEquals( existingDefaultValue, inputElementAttrs.getDefaultScalarValue() );
//            
//            int initialDefaultValueCount = 4;
//            assertEquals( initialDefaultValueCount, aParamDefn.getDefaultValueCount() );
//            assertEquals( initialDefaultValueCount, inputElementAttrs.getDefaultValueCount() );
//            
//            // test default value accessors in ParameterDefinition
//            Date defaultDateValue = null;
//            try
//            {
//                defaultDateValue = new SimpleDateFormat("MM/dd/yyyy").parse( "06/01/2010" );
//            }
//            catch( ParseException ex )
//            {
//                fail();
//            }
//            aParamDefn.addDefaultValue( defaultDateValue );
//            int defaultValuePos = initialDefaultValueCount + 1;
//            assertEquals( defaultValuePos, aParamDefn.getDefaultValueCount() );
//            assertEquals( defaultDateValue, aParamDefn.getDefaultValues().getValues().get( defaultValuePos-1 ));
//            
//            // test default value accessors in InputElementAttributes
//            Integer newDefaultValue2 = Integer.valueOf( 3 );
//            inputElementAttrs.addDefaultValue( newDefaultValue2 );
//            defaultValuePos++;
//            assertEquals( defaultValuePos, inputElementAttrs.getDefaultValueCount() );
//            assertEquals( newDefaultValue2, inputElementAttrs.getDefaultValues().getValues().get( defaultValuePos-1 ));
//            
//            // test including CustomData in default StaticValues
//            CustomData customData = DesignFactory.eINSTANCE.createCustomData();
//            customData.setProviderId( "org.eclipse.birt.report.model.adapter.oda" );
//            customData.setValue( defaultDateValue );
//            inputElementAttrs.addDefaultValue( customData );
//            defaultValuePos++;
//            assertEquals( defaultValuePos, inputElementAttrs.getDefaultValueCount() );
//            assertEquals( customData, inputElementAttrs.getDefaultValues().getValues().get( defaultValuePos-1 ));
//            assertEquals( defaultDateValue, customData.getValue() );
//            assertEquals( defaultDateValue.toString(), customData.getDisplayValue() );
//
//            String displayValue = "June 1, 2010";
//            customData.setDisplayValue( displayValue );
//            assertEquals( displayValue, customData.getDisplayValue() );
//        }
//        
//        // test saving updated design session with the filter expression and additional default values
//        File tempOut = getTempOutFile();
//        saveDesignSession( design, tempOut );
//
//        File goldenOutFile = new File( 
//                getSampleDbTestMigrateDefaultValueOutFilePath() );
//        assertTrue( compareFileContent( goldenOutFile, tempOut ) );    
//        
//        // test backward compatibility of deprecated method, which reset and overrides all exisitng values
//        for( ParameterDefinition aParamDefn : dataSetParams.getParameterDefinitions() )
//        {
//            InputElementAttributes inputElementAttrs = aParamDefn.getInputAttributes().getElementAttributes();
//            
//            assertEquals( 7, inputElementAttrs.getDefaultValueCount() );
//            
//            String newDefaultValue = "new default value";
//            inputElementAttrs.setDefaultScalarValue( newDefaultValue );
//            assertEquals( 1, inputElementAttrs.getDefaultValueCount() );
//            assertEquals( newDefaultValue, inputElementAttrs.getDefaultValues().getValues().get( 0 ));
//        }
//    }
    
    public void testAddResourceKeys()
    {
        File goldenFile = new File( getSampleDbTestFilePath() );
        OdaDesignSession design = loadOdaDesignSession( goldenFile );
        DataSourceDesign dataSourceDesign = design.getResponseDataSourceDesign();

        String displayName = dataSourceDesign.getDisplayName();
        
        // adding resource key
        String displayNameKey = "sourceDesignKey";
        dataSourceDesign.setDisplayNameKey( displayNameKey );
        assertEquals( displayNameKey, dataSourceDesign.getDisplayNameKey() );
        // test that the display name remains the same after having added resource key
        assertEquals( displayName, dataSourceDesign.getDisplayName() );
        
        // add default resource string
        displayName = "newDefault";
        dataSourceDesign.setDisplayName( displayName );
        assertEquals( displayName, dataSourceDesign.getDisplayName() );
        // test that the key remains the same after having added resource default
        assertEquals( displayNameKey, dataSourceDesign.getDisplayNameKey() );

        // change resource key
        displayNameKey = " sourceDesignKey2 ";
        dataSourceDesign.setDisplayNameKey( displayNameKey );
        assertEquals( displayNameKey.trim(), dataSourceDesign.getDisplayNameKey() );
        // test that the display name remains the same after having changed resource key
        assertEquals( displayName, dataSourceDesign.getDisplayName() );
        
        // change default resource string
        displayName = " %new Default 2 ";
        dataSourceDesign.setDisplayName( displayName );
        assertEquals( displayName, dataSourceDesign.getDisplayName() );
        // test that the key remains the same after having added resource default
        assertEquals( displayNameKey.trim(), dataSourceDesign.getDisplayNameKey() );
        
        // change default resource string to contain literal key prefix
        displayName = "%new Default 2 ";
        dataSourceDesign.setDisplayName( displayName );
        assertEquals( displayName, dataSourceDesign.getDisplayName() );
        // test that the key remains the same after having added resource default
        assertEquals( displayNameKey.trim(), dataSourceDesign.getDisplayNameKey() );
        
        // test the added resource keys are valid and can be saved
        try
        {
            DesignUtil.validateObject( dataSourceDesign );
        }
        catch( IllegalStateException ex )
        {
            fail();
        }  
        
        // test saving updated design session with the resource keys
        File tempOut = getTempOutFile();
        saveDesignSession( design, tempOut );

        // reset resource key
        displayNameKey = null;
        dataSourceDesign.setDisplayNameKey( displayNameKey );
        assertEquals( displayNameKey, dataSourceDesign.getDisplayNameKey() );
        // test that the display name remains the same after having changed resource key
        assertEquals( displayName, dataSourceDesign.getDisplayName() );
        
        displayName = null;
        dataSourceDesign.setDisplayName( displayName );
        assertEquals( displayName, dataSourceDesign.getDisplayName() );
        // test that the display name remains the same after having changed resource key
        assertEquals( displayNameKey, dataSourceDesign.getDisplayNameKey() );
        
        // test invalid resource key
        boolean hasException = false;
        try
        {
            dataSourceDesign.setDisplayNameKey( " invalid key " );  // contains embedded white space
        }
        catch( IllegalArgumentException ex )
        {
            hasException = true;
        }
        assertTrue( hasException );
    }
   
    public void testAddResourceFileName()
    {
        File goldenFile = new File( getSampleDbTestFilePath() );
        OdaDesignSession design = loadOdaDesignSession( goldenFile );
        DataSourceDesign dataSourceDesign = design.getResponseDataSourceDesign();

        String resourceFileName = "dummyFile.properties";
        dataSourceDesign.setResourceFile( resourceFileName );
        assertEquals( resourceFileName, dataSourceDesign.getResourceFile() );
        
        // test invalid resource file name
        boolean hasException = false;
        try
        {
            dataSourceDesign.setResourceFile( "dummyFile" );    // missing expected suffix
        }
        catch( IllegalArgumentException ex )
        {
            hasException = true;
        }
        assertTrue( hasException );        
    }
    
    public void testNullDataSourceDesignRef()
    {
        File goldenFile = new File( getSampleDbTestFilePath() );
        OdaDesignSession design = loadOdaDesignSession( goldenFile );
        DataSetDesign dataSetDesign = design.getResponseDataSetDesign();

        dataSetDesign.setDataSourceDesign( null );
        
        // test invalid null DataSourceDesign reference
        DesignSessionResponse designResponse = design.getResponse();
        boolean hasException = false;
        try
        {
            DesignUtil.validateObject( designResponse );
        }
        catch( IllegalStateException ex )
        {
            hasException = true;
        }
        assertTrue( hasException );
        
        // test valid null DataSourceDesign reference in DynamicValuesQuery
        design = loadOdaDesignSession( goldenFile );    // reload original content
        dataSetDesign = design.getResponseDataSetDesign();
        DataSetParameters dataSetParams = dataSetDesign.getParameters();
        for( ParameterDefinition aParamDefn : dataSetParams.getParameterDefinitions() )
        {           
            DataSetDesign nestedDataSetDesign = (DataSetDesign) EcoreUtil.copy( dataSetDesign );
            nestedDataSetDesign.setDataSourceDesign( null );    // set nested data set to reference a null data source

            DynamicValuesQuery dynamicQuery = DesignFactory.eINSTANCE.createDynamicValuesQuery();
            dynamicQuery.setDataSetDesign( nestedDataSetDesign );
            dynamicQuery.setValueColumn( "1" ); //$NON-NLS-1$

            InputElementAttributes inputElementAttrs = aParamDefn.getInputAttributes().getElementAttributes();
            inputElementAttrs.setDynamicValueChoices( dynamicQuery );           
        }
        // test the null reference in this case is valid 
        designResponse = design.getResponse();
        try
        {
            DesignUtil.validateObject( designResponse );
        }
        catch( IllegalStateException ex )
        {
            fail();     // not expecting a validation error
        }  
    }
   
    private void saveDesignSession( OdaDesignSession design, File tempOut )
    {
        try
        {
            DesignUtil.saveOdaDesignSession( design, tempOut );
        }
        catch( Exception ex )
        {
            fail();
        }
    }
    
    private boolean compareFileContent( File file1, File file2 )
    {
        boolean match = true;
        BufferedReader file1BufReader = null;
        BufferedReader file2BufReader = null;
        try
        {
            file1BufReader = new BufferedReader( new FileReader( file1 ) ); 
            file2BufReader = new BufferedReader( new FileReader( file2 ) );
        }
        catch( FileNotFoundException ex )
        {
            match = false;
            ex.printStackTrace();
        } 
        
        try
        {
            String file1Line, file2Line;
            while( match && (file1Line = file1BufReader.readLine() ) != null )
            {
                file2Line = file2BufReader.readLine();
                match = file1Line.equals( file2Line );
            }
        }
        catch( IOException ex )
        {
            match = false;
            ex.printStackTrace();
        }

        try
        {
            file1BufReader.close();
            file2BufReader.close();
        }
        catch( IOException ex )
        {
            ex.printStackTrace();
        }
        
        return match;
    }
    
    private File getTempOutFile()
    {
        if( m_tempOutFile == null )
            m_tempOutFile = new File( getTempOutFilePath() );
        return m_tempOutFile;
    }
    
    private void cleanupFile( File file )
    {
        if( file != null && file.exists() )
            file.delete();
    }
    
}

