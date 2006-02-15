/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.designsession;

import java.util.Iterator;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.Property;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.DesignerUtil;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

/**
 *  An utility class to help an ODA host designer or
 *  an ODA driver's customized designer to manipulate
 *  ODA Design API objects.
 */
public class DesignSessionUtil 
{
    // class has static methods only; no need to instantiate
    private DesignSessionUtil()
    {
    }
        
    /**
     * Creates and returns an OdaDesignSession instance with a 
     * DesignSessionRequest that contains the given DataSourceDesign.
     * <br>This may be used by an ODA host designer to create 
     * a design session instance, for use to create/edit a 
     * data source design definition,
     * or to create a new data set design with a given data source design.
     * @param   dataSourceDesign    may be null for a session to
     *                              create a new data source design
     */
    public static OdaDesignSession createNewRequestSession( DataSourceDesign dataSourceDesign )
    {
        OdaDesignSession newSession = DesignFactory.eINSTANCE.createOdaDesignSession();
        newSession.setNewRequest( dataSourceDesign );
        return newSession;
    }
    
    /**
     * Creates and returns a new OdaDesignSession instance with a
     * DesignSessionResponse that contains the given session status
     * and DataSourceDesign.
     * <br>This method may be used by an ODA Designer extension
     * to create a new design session instance with a response that contains
     * a new or edited data source design definition.
     * <br>The returned session instance also includes an appropriate
     * DesignSessionRequest to fulfill the API content contract.
     * @param isSessionOk
     * @param dataSourceDesign
     * @return
     */
    public static OdaDesignSession createNewResponseSession( 
            boolean isSessionOk, DataSourceDesign dataSourceDesign )
    {
        // create a design session with an empty DataAccess in the request
        OdaDesignSession newSession = createNewRequestSession( null );
        
        // add a response with the given DataSourceDesign
        return setSessionResponse( newSession, isSessionOk, dataSourceDesign );
    }

    /**
     * Updates the given OdaDesignSession instance with a
     * DesignSessionResponse that contains the given session status
     * and DataSourceDesign.
     * <br>This method may be used by an ODA Designer extension
     * to edit a design session instance with a response that contains
     * the edited data source design definition.
     * @param designSession
     * @param isSessionOk
     * @param dataSourceDesign
     * @return
     */
    public static OdaDesignSession setSessionResponse( OdaDesignSession designSession,
            boolean isSessionOk, DataSourceDesign dataSourceDesign )
    {
        // sets a response with the given session status and DataSourceDesign
        designSession.setNewResponse( isSessionOk, dataSourceDesign );
        return designSession;
    }
    
    /**
     * Extract the public and private properties defined in an 
     * ODA data source design definition, and returns in a new
     * Properties collection.
     * @param dataSourceDesign
     * @return
     */
    public static java.util.Properties createDataSourcePropertiesFromDesign( DataSourceDesign dataSourceDesign )
    {
        java.util.Properties publicProps = convertPropertiesFromDesign( 
                dataSourceDesign.getPublicProperties() );
        java.util.Properties propCollection = convertPropertiesFromDesign( 
                dataSourceDesign.getPrivateProperties() );

        // merge public and private properties, with public values
        // taking precedence if same property key is used
        propCollection.putAll( publicProps );
        return propCollection;        
    }

    /**
     * Convert the given properties defined in an 
     * ODA data source design definition, and returns in a new
     * Properties collection.
     * @param designProps
     * @return
     */
    public static java.util.Properties convertPropertiesFromDesign( Properties designProps )
    {
        java.util.Properties propCollection = new java.util.Properties();
        if( designProps == null || designProps.getProperties() == null )
            return propCollection;  // return an empty collection
        
        Iterator itr = designProps.getProperties().iterator();
        while( itr.hasNext() )
        {
            Property designProp = (Property) itr.next();
            propCollection.setProperty( designProp.getNameValue().getName(),
                                    designProp.getNameValue().getValue() );
        }
        return propCollection;
    }
    
    /**
     * Creates an ODA design public property collection for the properties
     * defined in an ODA runtime extension manifest, with the property values 
     * collected from the given profile property collection.
     * @param odaDataSourceId   the ODA extension data source element ID
     * @param profileProps      java.util.properties, such as those collected from a connection profile
     * @return  ODA design public property collection for inclusion
     *          in an OdaDesignSession's Data Source Design
     * @throws OdaException
     */
    public static Properties createDesignPropertiesFromProfile( 
                String odaDataSourceId,
                java.util.Properties profileProps )
            throws OdaException
    {
        // first get the public property definition in the ODA driver's runtime plugin manifest
        org.eclipse.datatools.connectivity.oda.util.manifest.Property[] publicPropDefns = 
            getPublicPropertiesDefn( odaDataSourceId );

        // create a new design Properties collection
        Properties designProps = 
            DesignFactory.eINSTANCE.createProperties();
        
        // for each defined public property name, get its corresponding
        // value in the profile, and create design property with name and value
        // in collection
        for( int i = 0; i < publicPropDefns.length; i++ )
        {
            String propName = publicPropDefns[i].getName();
            String propValue = profileProps.getProperty( propName );
            designProps.setProperty( propName, propValue );
        }
        return designProps;
    }
    
    /**
     * Returns the connection profile instance referenced or linked by
     * the given data source design.
     * @param dataSourceDesign
     * @return  linked connection profile instance, or null
     *          if no external profile is referenced
     * @throws OdaException if referenced connection profile is not found
     */
    public static IConnectionProfile getLinkedProfile( DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        if( ! dataSourceDesign.hasLinkToProfile() )
            return null;

        String linkedProfileName = dataSourceDesign.getLinkedProfileName();
        IConnectionProfile profile = OdaProfileExplorer.getInstance()
            .getProfileByName( linkedProfileName,
                    dataSourceDesign.getLinkedProfileStoreFile() );
        if( profile == null )   // not found
            throw new OdaException( "Unable to locate the linked profile: " + linkedProfileName );
        return profile;
    }
        
    /**
     * Find and return the property definition specified in
     * an ODA extension that implements the 
     * oda.datasource run-time extension point.
     */
    private static org.eclipse.datatools.connectivity.oda.util.manifest.Property[] 
        getPublicPropertiesDefn( String odaDataSourceId )
        throws OdaException
    {
        try
        {
            ExtensionManifest runtimeManifest = 
                ManifestExplorer.getInstance().getExtensionManifest( 
                        odaDataSourceId );
            return runtimeManifest.getProperties();
        }
        catch( IllegalArgumentException ex )
        {
            throw DesignerUtil.newOdaException( ex );
        }        
    }

}
