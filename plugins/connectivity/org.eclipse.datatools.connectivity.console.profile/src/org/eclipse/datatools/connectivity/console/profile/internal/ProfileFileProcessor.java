/*
 *************************************************************************
 * Copyright (c) 2008, 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.console.profile.internal;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.console.profile.nls.Messages;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.internal.CategoryProvider;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileMgmt;
import org.eclipse.datatools.connectivity.internal.security.SecurityManager;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.Property;
import org.eclipse.datatools.connectivity.security.ICipherProvider;

/**
 *  Responsible for processing a connection profile storage file,
 *  such as reading a storage file, interacting with an user to 
 *  view or change its content, and saving user changes.
 */
public class ProfileFileProcessor
{
    private static final String PROP_PROMPT_DELIMITER = Messages.profileFile_inputPromptDelimiter;
    private static final String PROFILE_LINE_SEPARATOR = "-----------------------------"; //$NON-NLS-1$
    
    private SystemIOUtil m_ioUtil;
    private IConnectionProfile[] m_profiles;
    private boolean m_wasInputFileEncrypted;
    private PropertyEditor m_propEditor = new PropertyEditor();

    ProfileFileProcessor( SystemIOUtil util ) 
    {
        if( util == null )
            throw new IllegalArgumentException( "ProfileFileHandler( null )" ); //$NON-NLS-1$
        m_ioUtil = util;
    }
    
    /**
     * Loads the specified connection profile storage file.
     * Tracks whether the specified file was encrypted.
     * @param inFile
     * @return  the number of profiles read from file
     */
    int loadProfileFile( File inFile )
    {
        m_ioUtil.printNewLine();
        m_ioUtil.println( Messages.bind(  Messages.profileFile_statusReadingFile, inFile.getAbsolutePath() ) );
        
        try
        {
            m_wasInputFileEncrypted = ConnectionProfileMgmt.isEncrypted( inFile );
            ICipherProvider cipherProvider = m_wasInputFileEncrypted ?
                            getCipherProvider( inFile ) :
                            null;
            m_profiles = ConnectionProfileMgmt.loadCPs( inFile, cipherProvider );
        }
        catch( Exception ex )
        {
            m_ioUtil.printException( Messages.profileFile_errorReadingFile, ex );
            m_ioUtil.println( Messages.profileFile_errorInstruction );
            return 0;
        }

        if( m_profiles == null || m_profiles.length == 0 )
            m_ioUtil.println( Messages.profileFile_noProfileFound );

        return ( m_profiles == null ) ? 0 : m_profiles.length;
    }
    
    private static ICipherProvider getCipherProvider( File profileStoreFile )
    {
        return SecurityManager.getInstance().getCipherProvider( profileStoreFile );
    }
    
    /**
     * Saves the current state of all the profile instances into the specified output file.
     * This is normally called after the profiles have been updated by {@link #updateProfiles(String)}.
     * If the input file read by {@link #loadProfileFile(File)} was encrypted, the output file
     * is automatically saved in encrypted format. 
     * Otherwise, gives user a choice to encrypt the output file.
     * @param outFile
     */
    void saveUpdatedProfiles( File outFile )
    {
        ICipherProvider cipherProvider = null;
        if( m_wasInputFileEncrypted )    // input file was encrypted
            cipherProvider = getCipherProvider( outFile );   // must save in encrypted format
        else
        {
            // give user a choice to save in encrypted format
            if( m_ioUtil.promptYesNoResponse( Messages.profileFile_askEncryptOutput ) )
                cipherProvider = getCipherProvider( outFile );
        }
        
        // proceed to save
        m_ioUtil.printNewLine();
        m_ioUtil.println( Messages.bind( Messages.profileFile_statusSavingFile, outFile.getAbsoluteFile() ));
        try
        {
            ConnectionProfileMgmt.saveCPs( m_profiles, outFile, cipherProvider );
        }
        catch( CoreException ex )
        {
            m_ioUtil.printException( Messages.profileFile_errorSavingFile, ex );
            return;
        }
        
        m_ioUtil.println( Messages.profileFile_statusDone );
    }
    
    /**
     * Interacts with an user to view and update each of the connection profile instance(s) 
     * found in the input file specified in {@link #loadProfileFile(File)}.
     * @param profileName   The name of a connection profile.  If a name is specified, 
     *              only that profile instance if found, will be promped for updates.
     *              A null value indicates to update all connection profile instances in the input file.
     * @return  true if at least one profile has updates made to its properties; false otherwise
     */
    boolean updateProfiles( String profileName )
    {
        final boolean editingSingleProfile = profileName != null && profileName.trim().length() > 0;

        if( m_profiles == null || m_profiles.length == 0 )
        {
            if( editingSingleProfile )
                m_ioUtil.printNewLine( Messages.bind( Messages.profileFile_specifiedProfileNotFound, profileName ));
            return false;
        }
        
        if( ! editingSingleProfile )
            m_ioUtil.println( Messages.bind( Messages.profileFile_statusIteratingProfiles, 
                                        new Integer( m_profiles.length ) ));
        
        boolean foundNamedProfile = false;
        boolean hasUpdates = false;
        for( int i = 0; i < m_profiles.length && ! foundNamedProfile; i++ ) 
        {
            IConnectionProfile profile = m_profiles[i];
            if( editingSingleProfile )
            {
                if( ! profile.getName().equalsIgnoreCase( profileName ) )
                    continue;
                foundNamedProfile = true;
            }
            
            // prompt whether to update this profile instance
            m_ioUtil.printNewLine( PROFILE_LINE_SEPARATOR );

            String profileType = profile.getProvider().getCategory().getId().equals( CategoryProvider.ID_CATEGORY_UNKNOWN ) ?
                        profile.getProviderId() : profile.getProviderName();
            m_ioUtil.println( Messages.bind( Messages.profileFile_profileIdentifier, profileType ) 
                              + PROP_PROMPT_DELIMITER + profile.getName() );
            if( ! m_ioUtil.promptYesNoResponse( Messages.profileFile_askUpdateProfile ) )
                continue;
            
            Properties props = profile.getBaseProperties();
            if( props.getProperty( IJDBCConnectionProfileConstants.DRIVER_CLASS_PROP_ID ) != null ) // has DB property
                hasUpdates |= updateDatabaseProperties( profile );
            else
                hasUpdates |= updateOdaProfileProperties( profile );
        }
        
        if( editingSingleProfile && ! foundNamedProfile )
            m_ioUtil.printNewLine( Messages.bind(  Messages.profileFile_specifiedProfileNotFound, profileName ));
        m_ioUtil.println( Messages.profileFile_statusDone );
        return hasUpdates;
    }
    
    private boolean updateDatabaseProperties( IConnectionProfile profile )
    {
        return DatabaseProfileHandler.updateProfileProperties( profile, m_propEditor );
    }
    
    private boolean updateOdaProfileProperties( IConnectionProfile profile )
    {
        return OdaProfileHandler.updateProfileProperties( profile, m_propEditor );
    }
    
    /**
     * Responsbile for interacting with users to view and edit a property.
     */
    private class PropertyEditor
    {        
        private static final String MASKED_VALUE = "*****"; //$NON-NLS-1$
        private static final String NULL_DISPLAY_VALUE = "<null>"; //$NON-NLS-1$
        private static final String VALUE_DELIMITER = "\""; //$NON-NLS-1$
        /**
         * Gets and displays the current value of the specified propertyKey and 
         * interacts with user to input a new value.
         * The password property value is masked when displayed.
         */
        private String promptNewPropertyValue( Properties props, String propertyKey, String propertyDisplayName )
        {
            boolean maskValue = propertyKey.equals( IJDBCConnectionProfileConstants.PASSWORD_PROP_ID );
            return promptNewPropertyValue( props, propertyKey, propertyDisplayName, maskValue );
        }
        
        /**
         * Gets and displays the current value of the specified propertyKey; mask the current value if specfied,
         * and interacts with user to input a new value.
         */
        private String promptNewPropertyValue( Properties props, String propertyKey, String propertyDisplayName, boolean maskValue )
        {
            String propValue = maskValue ? MASKED_VALUE : props.getProperty( propertyKey );
            return promptNewPropertyValue( propertyDisplayName, propValue );
        }
        
        /**
         * Displays the specified current value, and interacts with user to input a new value.
         * A null value is displayed as "<null>".  An empty string value or one with only spaces
         * is delimited by double-quotes.
         */
        private String promptNewPropertyValue( String propertyDisplayName, String currentValue )
        {
            String displayValue = currentValue;
            if( displayValue == null )
                displayValue = NULL_DISPLAY_VALUE;
            else if( displayValue.trim().length() == 0 )    // wraps empty string or spaces with delimiters
                displayValue = VALUE_DELIMITER + displayValue + VALUE_DELIMITER;
            
            m_ioUtil.printNewLine( propertyDisplayName + PROP_PROMPT_DELIMITER + displayValue );
            return m_ioUtil.promptForUpdateValue();
        }
        
        private boolean setNewPropertyValue( Properties props, String key, String newValue )
        {
            if( newValue != null )
            {
                props.setProperty( key, newValue );
                return true;
            }
            return false;
        }
        
        private void printNewLine( String message )
        {
            m_ioUtil.printNewLine( message );
        }
    }
    
    /**
     * Handler of ODA connection profile properties.
     */
    private static class OdaProfileHandler
    {
        private static final String MNEMONICS_SYMBOL = "&"; //$NON-NLS-1$

        private static boolean updateProfileProperties( IConnectionProfile profile, PropertyEditor propEditor )
        {
            ExtensionManifest odaManifest = getOdaManifest( profile );
            if( odaManifest == null )   // no ODA manifest found
                return updateRawProperties( profile, propEditor );
            
            Properties profileProps = profile.getBaseProperties();

            Property[] odaPropDefns = odaManifest.getProperties( false );
            Properties propVisibility = odaManifest.getPropertiesVisibility();

            boolean hasUpdates = false;
            String newValue;
            for( int i=0; i < odaPropDefns.length; i++ )
            {
                Property odaPropDefn = odaPropDefns[i];
                if( ! odaPropDefn.isVisible( propVisibility ) || ! odaPropDefn.isEditable( propVisibility ) )
                    continue;

                newValue = propEditor.promptNewPropertyValue( profileProps, odaPropDefn.getName(), 
                                                    getPropertyDisplayName( odaPropDefn ),
                                                    odaPropDefn.isEncryptable() );
                hasUpdates |= propEditor.setNewPropertyValue( profileProps, odaPropDefn.getName(), newValue );
            }
            
            // update the profile's properties
            if( hasUpdates )
                profile.setBaseProperties( profileProps );
            return hasUpdates;
        }
        
        private static ExtensionManifest getOdaManifest( IConnectionProfile profile )
        {
            String odaDataSourceId = profile.getProviderId();
            ExtensionManifest odaManifest = null;
            try
            {
                odaManifest = ManifestExplorer.getInstance().getExtensionManifest( odaDataSourceId );
            }
            catch( Exception ex )
            {
                // ignore
            }
            
            return odaManifest;
        }
        
        private static String getPropertyDisplayName( Property odaPropDefn )
        {
            String displayName = odaPropDefn.getDisplayName();
            // removes any mnemonics character embedded in the display name
            return displayName.replaceAll( MNEMONICS_SYMBOL, SystemIOUtil.EMPTY_STRING );
        }
        
        /**
         * Update the profile properties without any property metadata available.
         * All existing values are masked for display.
         */
        private static boolean updateRawProperties( IConnectionProfile profile, PropertyEditor propEditor )
        {
            Properties props = profile.getBaseProperties();
            boolean hasUpdates = false;
            propEditor.printNewLine( Messages.profileFile_noOdaPropertyDefn  );
            
            String newValue;
            Iterator keysIter = props.keySet().iterator();
            while( keysIter.hasNext() )
            {
                String propKey = (String) keysIter.next();
                // since no property definition is available, use key as display name, and mask its existing value 
                newValue = propEditor.promptNewPropertyValue( props, propKey, propKey, true ); 
                hasUpdates |= propEditor.setNewPropertyValue( props, propKey, newValue );
            }
            
            // update the profile's properties
            if( hasUpdates )
                profile.setBaseProperties( props );
            return hasUpdates;
        }
    }
 
    /**
     * Handler of JDBC Database connection profile properties.
     */
    private static class DatabaseProfileHandler
    {
        private static final String PROPERTY_PAIR_SEPARATOR = ","; //$NON-NLS-1$
        private static final String ASSIGNMENT_CHAR = "="; //$NON-NLS-1$

        private static Map sm_dbPropKeys;

        private static boolean updateProfileProperties( IConnectionProfile profile, PropertyEditor propEditor )
        {
            Properties profileProps = profile.getBaseProperties();
            boolean hasUpdates = false;
            
            // prompt to update each pre-defined database property key
            Map dbPropKeys = getDbPropertyKeys();
            String newValue;
            Iterator entryIter = dbPropKeys.entrySet().iterator();
            while( entryIter.hasNext() )
            {
                Map.Entry keyEntry = (Map.Entry) entryIter.next();
                newValue = propEditor.promptNewPropertyValue( profileProps, (String) keyEntry.getKey(), (String) keyEntry.getValue() );
                hasUpdates |= propEditor.setNewPropertyValue( profileProps, (String) keyEntry.getKey(), newValue );
            }

            // handle any additional name value pairs stored in connection profile
            String nameValuePairs = profileProps .getProperty( IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID );
            if( nameValuePairs != null && nameValuePairs.length() > 0) 
            {
                String[] pairs = parseString( nameValuePairs, PROPERTY_PAIR_SEPARATOR );
                for( int i = 0; i < pairs.length; i++ ) 
                {
                    String[] namevalue = parseString( pairs[i], ASSIGNMENT_CHAR );
                    if( namevalue.length >= 2 )
                    {
                        newValue = propEditor.promptNewPropertyValue( namevalue[0], namevalue[1] );
                        hasUpdates |= propEditor.setNewPropertyValue( profileProps, namevalue[0], newValue );
                    }
                }
            }
            
            // update the profile's properties
            if( hasUpdates )
                profile.setBaseProperties( profileProps );
            return hasUpdates;
        }
        
        private static String[] parseString( String str_list, String token ) 
        {
            StringTokenizer tk = new StringTokenizer( str_list, token );
            String[] pieces = new String[tk.countTokens()];
            int index = 0;
            while( tk.hasMoreTokens() )
                pieces[index++] = tk.nextToken();
            return pieces;
        }
        
        /**
         * Returns a collection of Database property key and corresponding display name
         * that are used to open a JDBC connection.
         */
        private static Map getDbPropertyKeys()
        {
            if( sm_dbPropKeys == null )
            {
                sm_dbPropKeys = new LinkedHashMap(5);
                sm_dbPropKeys.put( IJDBCConnectionProfileConstants.URL_PROP_ID, Messages.profileFile_propertyUrl );
                sm_dbPropKeys.put( IDriverMgmtConstants.PROP_DEFN_JARLIST, 
                                            Messages.bind( Messages.profileFile_propertyJarList, IDriverMgmtConstants.PATH_DELIMITER ) );
                sm_dbPropKeys.put( IJDBCConnectionProfileConstants.DRIVER_CLASS_PROP_ID, Messages.profileFile_propertyDriverClass );
                sm_dbPropKeys.put( IJDBCConnectionProfileConstants.USERNAME_PROP_ID, Messages.profileFile_propertyUser );
                sm_dbPropKeys.put( IJDBCConnectionProfileConstants.PASSWORD_PROP_ID, Messages.profileFile_propertyPassword );
            }
            return sm_dbPropKeys;
        }       
    }

}
