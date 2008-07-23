/*
 *************************************************************************
 * Copyright (c) 2007, 2008 Actuate Corporation.
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

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;

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

