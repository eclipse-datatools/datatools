/*
 *************************************************************************
 * Copyright (c) 2005, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile.tests;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;

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
            m_testStoreLocation = new Path( TestUtil.getPluginTestDirectory() );
        
        if ( getName().equals( "testGetProfiles" ) )
            ConnectionProfileMgmt.setStorageLocation( m_testStoreLocation );

        if ( getName().equals( "testGetProfilesFromFile" ) )
            m_testFileStore = TestUtil.copyTestStoreFileFromTemplate( 
                    m_testStoreLocation, 
                    MixedEntryStoreName );
        else
            m_testFileStore = TestUtil.copyTestStoreFileFromTemplate( 
                    m_testStoreLocation, 
                    ConnectionProfileMgmt.FILENAME );
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
            // use profile store in path specified in setup by ConnectionProfileMgmt.setStorageLocation
            profiles = m_profileExplorer.getProfileIdentifiersByOdaProviderId( 
                    OdaFlatFileId, null );  
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
            profiles = m_profileExplorer.getProfileIdentifiersByOdaProviderId( 
                    OdaFlatFileId, storeFilePath );
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
        Properties connProps = m_profileExplorer.getProfileProperties( 
                profileInstId, m_testFileStore );
        assertNotNull( connProps );
        // FlatFile has 5 custom properties
        assertEquals( 5, connProps.size() );
    }

    /*
     * Test method for 'org.eclipse.datatools.connectivity.oda.profile.tests.OdaProfileExplorer.getProfileByName(String)'
     */
    public void testGetProfileByName() throws Exception
    {
        // test that either approach finds the same profile instance
        String profileInstId = getFlatFileProfileInstanceId( FlatFileProfileName );
        IConnectionProfile profile = 
            m_profileExplorer.getProfileById( profileInstId, m_testFileStore );
        String profileInstName = profile.getName();
        IConnectionProfile namedProfile = 
            m_profileExplorer.getProfileByName( profileInstName, m_testFileStore );
        assertEquals( profile.getInstanceID(), namedProfile.getInstanceID() );
    }
    
    /*
     * TODO - move to oda.design.ui.profile.wizards tests
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
        Map profiles = m_profileExplorer.getProfileIdentifiersByOdaProviderId( 
                OdaFlatFileId, m_testFileStore );
        Collection profileInstanceIds = profiles.keySet();
        assertNotNull( profileInstanceIds );
        assertTrue( profileInstanceIds.size() > 0 );

        Iterator iter = profileInstanceIds.iterator();
        while( iter.hasNext() )
        {
            String instanceId = (String) iter.next();
            if( OdaFlatFileId.equals( m_profileExplorer.getProfileById( 
                    instanceId, m_testFileStore ).getProviderId() ))
            {     
                assertEquals( profileName, (String) profiles.get( instanceId ) );
                return instanceId;
            }
        }
        fail( "No Flat File profiles found" );
        return null;
    }
}
