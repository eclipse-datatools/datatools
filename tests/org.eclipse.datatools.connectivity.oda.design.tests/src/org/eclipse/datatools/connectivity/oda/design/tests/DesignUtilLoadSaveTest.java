/*
 *************************************************************************
 * Copyright (c) 2007, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import java.util.Date;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ExpressionVariable;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.OrExpression;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
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
        newStaticParamDefn.addStaticValue( "static value 2 " );        
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
    
    public void testInputElementMigrateDefaultValues()
    {
        File goldenFile = new File( getSampleDbTestMigrateDefaultValueFilePath() );
        OdaDesignSession design = loadOdaDesignSession( goldenFile );
        DataSetDesign dataSetDesign = design.getResponseDataSetDesign();
        
        DataSetParameters dataSetParams = dataSetDesign.getParameters();
        Iterator<ParameterDefinition> iter = dataSetParams.getParameterDefinitions().iterator();
        while( iter.hasNext() )
        {
            ParameterDefinition newParamDefn = (ParameterDefinition) EcoreUtil.copy( iter.next() );
            InputElementAttributes inputElementAttrs = newParamDefn.getInputAttributes().getElementAttributes();
            
            String existingDefaultValue = newParamDefn.getDefaultScalarValue();
            assertEquals( existingDefaultValue, inputElementAttrs.getDefaultScalarValue() );
            
            assertEquals( 1, newParamDefn.getDefaultValueCount() );
            assertEquals( 1, inputElementAttrs.getDefaultValueCount() );
            
            // test default value accessors in ParameterDefinition
            String newDefaultValue = "new default value 2";
            newParamDefn.addDefaultValue( newDefaultValue );
            assertEquals( 2, newParamDefn.getDefaultValueCount() );
            assertEquals( newDefaultValue, newParamDefn.getDefaultValues().getValues().get( 2-1 ));
            
            // test default value accessors in InputElementAttributes
            newDefaultValue = "new default value 3";
            inputElementAttrs.addDefaultValue( newDefaultValue );
            assertEquals( 3, inputElementAttrs.getDefaultValueCount() );
            assertEquals( newDefaultValue, inputElementAttrs.getDefaultValues().getValues().get( 3-1 ));
            
            // test backward compatibility of deprecated method, which reset and overrides all exisitng values
            inputElementAttrs.setDefaultScalarValue( newDefaultValue );
            assertEquals( 1, inputElementAttrs.getDefaultValueCount() );
            assertEquals( newDefaultValue, inputElementAttrs.getDefaultValues().getValues().get( 0 ));
        }
        
        // test saving updated design session with the filter expression
        File tempOut = getTempOutFile();
        saveDesignSession( design, tempOut );

        File goldenOutFile = new File( 
                getSampleDbTestMigrateDefaultValueOutFilePath() );
        assertTrue( compareFileContent( goldenOutFile, tempOut ) );    
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

