/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;

/**
 * Test cases for loading and saving OdaDesignSession utility in DesignUtil.  
 */
public class DesignUtilLoadSaveTest extends TestCase
{
    private String m_testFilePath;
    
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
        super.tearDown();
    }

    public void testLoadOdaDesignSession()
    {
        OdaDesignSession design = loadOdaDesignSession( new File( getSampleDbTestFile() ));
        assertEquals( "sampledb Data Set", design.getResponseDataSetDesign().getName() );
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
        return m_testFilePath + "/golden";
    }
    
    private String getSampleDbTestFile()
    {
        return getGoldenTestFilePath() + "/jdbcBirtSampleSession.xml";
    }
    
    private String getTempOutFile()
    {
        return m_testFilePath + "/tempOut.xml";
    }

    public void testSaveDesignSession()
    {
        File goldenFile = new File( getSampleDbTestFile() );
        OdaDesignSession design = loadOdaDesignSession( goldenFile );

        File tempOut = new File( getTempOutFile() );
        saveDesignSession( design, tempOut );
        assertTrue( compareFileContent( goldenFile, tempOut ) );
        
        // modify the design and test nonEqual output
        design.getResponseDataSetDesign().setName( "test change in name" );
        saveDesignSession( design, tempOut );
        assertFalse( compareFileContent( goldenFile, tempOut ) );
        
        cleanupFile( tempOut );
    }
    
    private void saveDesignSession( OdaDesignSession design, File tempOut )
    {
        try
        {
            DesignUtil.saveOdaDesignSession( design, tempOut );
        }
        catch( Exception ex )
        {
            cleanupFile( tempOut );
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
    
    private void cleanupFile( File file )
    {
        if( file != null && file.exists() )
            file.delete();
    }
    
}

