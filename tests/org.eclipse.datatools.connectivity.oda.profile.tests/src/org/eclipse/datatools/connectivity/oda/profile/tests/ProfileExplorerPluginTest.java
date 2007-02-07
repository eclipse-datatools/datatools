/*
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.osgi.framework.Bundle;

import junit.framework.TestCase;

/**
 * Plugin Test cases of the Oda Profile Explorer.
 */
public class ProfileExplorerPluginTest extends TestCase
{
    private static final String OdaFlatFileId = 
        "org.eclipse.datatools.connectivity.oda.flatfile";
    private static final String FlatFileProfileName = 
        "My Flat File Profile 1";
    private static final String MixedEntryStoreName = "DtpProfiles_encrpt.dat";

    private OdaProfileExplorer m_profileExplorer = null;
    private IPath m_testStoreLocation = null;
 //       "G:/connectivity/plugins/org.eclipse.datatools.connectivity.oda.profile.tests/src";
    private File m_testFileStore;
    
    protected void setUp() throws Exception
    {
        super.setUp();
        if( m_profileExplorer == null )
            m_profileExplorer = OdaProfileExplorer.getInstance();

        if( m_testStoreLocation == null )
            m_testStoreLocation = new Path( getPluginTestDirectory() );
        ConnectionProfileMgmt.setStorageLocation( m_testStoreLocation );

        if ( getName().equals( "testGetProfilesFromFile" ) )
            m_testFileStore = copyTestStoreFileFromTemplate( 
                    m_testStoreLocation, 
                    MixedEntryStoreName );
        else
            m_testFileStore = copyTestStoreFileFromTemplate( 
                    m_testStoreLocation, 
                    ConnectionProfileMgmt.FILENAME );
    }

    private File copyTestStoreFileFromTemplate( IPath storeLocation, String storeFilename )
        throws Exception
    {
        // getting profile has the side effect of adding to 
        // the profiles cache, which updates the profiles store.
        // So use a temporary store file copied from a template
        // for use in tests
        File profileStoreTemplate = storeLocation.append( 
                "tmplt_" + storeFilename ).toFile();
        if ( ! profileStoreTemplate.exists() )
            fail( profileStoreTemplate.getPath() + " template store file does not exist." );

        File testFile = storeLocation.append( storeFilename ).toFile();
        if ( testFile.exists() )
            testFile.delete();
        testFile.createNewFile();
        
        FileChannel sourceChan = new FileInputStream( profileStoreTemplate ).getChannel();
        FileChannel destChan = new FileOutputStream( testFile ).getChannel();
        destChan.transferFrom( sourceChan, 0, sourceChan.size() );
        sourceChan.close();
        destChan.close();
        return testFile;
    }
    
    protected void tearDown() throws Exception
    {
        ConnectionProfileMgmt.setStorageLocation( null );
        m_testFileStore.delete();
        m_testFileStore = null;
        super.tearDown();
    }

    /*
     * Test method for 'org.eclipse.datatools.connectivity.oda.profile.tests.OdaProfileExplorer.getProfiles(String)'
     */
    public void testGetProfiles() throws Exception
    {
        Map profiles = null;
        try
        {
            profiles = m_profileExplorer.getProfiles( OdaFlatFileId );
        }
        catch( OdaException e )
        {
            fail( e.toString() );
        }

        assertNotNull( profiles );
        assertEquals( 1, profiles.size() );
    }

    /*
     * Test method for 'org.eclipse.datatools.connectivity.oda.profile.tests.OdaProfileExplorer.getProfiles(String, File)'
     */
    public void testGetProfilesFromFile() throws Exception
    {
        File storeFilePath = m_testStoreLocation.append( MixedEntryStoreName ).toFile();
        Map profiles;
        try
        {
            profiles = m_profileExplorer.getProfiles( OdaFlatFileId, storeFilePath );
        }
        catch( OdaException e )
        {
            e.printStackTrace();
            throw e;
        }
        assertEquals( 2, profiles.size() );
    }

    /*
     * Test method for 'org.eclipse.datatools.connectivity.oda.profile.tests.OdaProfileExplorer.getProfileProperties(String)'
     */
    public void testGetProfileProperties() throws Exception
    {       
        String profileInstId = getFlatFileProfileInstanceId();
        Properties connProps = m_profileExplorer.getProfileProperties( profileInstId );
        assertNotNull( connProps );
        // the VersionProviderConnection base class owns 4 properties;
        // FlatFile adds 3 custom properties
        assertEquals( 7, connProps.size() );
    }

    /*
     * Test method for 'org.eclipse.datatools.connectivity.oda.profile.tests.OdaProfileExplorer.getProfileByName(String)'
     */
    public void testGetProfileByName() throws Exception
    {
        // test that either approach finds the same profile instance
        String profileInstId = getFlatFileProfileInstanceId( FlatFileProfileName );
        String profileInstName = m_profileExplorer.getProfile( profileInstId ).getName();
        assertEquals( m_profileExplorer.getProfile( profileInstId ),
                      m_profileExplorer.getProfileByName( profileInstName, m_testFileStore ) );
    }
    
    /*
     * TODO - move to oda.design.ui.profile.wizards
     */
/*    public void testGetCustomWizard() throws Exception
    {
        String profileInstId = getFlatFileProfileInstanceId();
        NewDataSourceWizard wizard = m_profileExplorer.getCustomWizard( profileInstId );
        assertNotNull( wizard );
    }
*/
    
    private String getFlatFileProfileInstanceId() throws OdaException
    {
        return getFlatFileProfileInstanceId( FlatFileProfileName );
    }
    
    private String getFlatFileProfileInstanceId( String profileName ) throws OdaException
    {
        Map profiles = m_profileExplorer.getProfiles( OdaFlatFileId );
        Collection profileInstanceIds = profiles.keySet();
        assertNotNull( profileInstanceIds );
        assertTrue( profileInstanceIds.size() > 0 );

        Iterator iter = profileInstanceIds.iterator();
        while( iter.hasNext() )
        {
            String instanceId = (String) iter.next();
            if( OdaFlatFileId.equals( m_profileExplorer.getProfile( instanceId ).getProviderId() ))
            {     
                assertEquals( profileName, (String) profiles.get( instanceId ) );
                return instanceId;
            }
        }
        fail( "No Flat File profiles found" );
        return null;
    }
    
    private String getPluginTestDirectory() throws Exception
    {
        Bundle bundle = Platform.getBundle( getClass().getPackage().getName() );
        URL url = bundle.getEntry( "src" );
        return FileLocator.toFileURL( url ).getPath();
    }
}
