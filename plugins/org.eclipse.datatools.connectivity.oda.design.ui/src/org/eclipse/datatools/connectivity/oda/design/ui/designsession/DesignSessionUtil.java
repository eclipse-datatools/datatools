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
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.DesignerUtil;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

/**
 *  An utility class to help an ODA host designer or
 *  an ODA driver's customized designer to manipulate
 *  ODA Design API objects during an ODA design session.
 */
public class DesignSessionUtil 
{
    // class has static methods only; no need to instantiate
    private DesignSessionUtil()
    {
    }
    
    /**
     * Creates an ODA design property collection for the public properties
     * defined in an ODA runtime extension manifest.  Their corresponding values 
     * are collected from the given profile property collection.
     * @param odaDataSourceId   the ODA extension data source element ID
     * @param utilProps      java.util.properties, such as those collected from a connection profile
     * @return  ODA design public property collection for inclusion
     *          in an OdaDesignSession's Data Source Design
     * @throws OdaException
     */
    public static Properties createDataSourcePublicProperties( 
                String odaDataSourceId,
                java.util.Properties utilProps )
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
            String propValue = utilProps.getProperty( propName );
            designProps.setProperty( propName, propValue );
        }
        return designProps;
    }
    
    /**
     * Creates an ODA design property collection for those given properties
     * that are not defined in an ODA runtime extension manifest. 
     * These are properties that are not publicly defined. 
     * Their corresponding values 
     * are collected from the given profile property collection.
     * @param odaDataSourceId   the ODA extension data source element ID
     * @param utilProps a java.util.Properties collection,
     *                  such as those collected from a connection profile
     * @return  ODA design non-public property collection for inclusion
     *          in an OdaDesignSession's Data Source Design
     * @throws OdaException
     */
    public static Properties createDataSourceNonPublicProperties( 
                String odaDataSourceId,
                java.util.Properties utilProps )
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
        Iterator iter = utilProps.keySet().iterator();
        while( iter.hasNext() )
        {
            String utilPropName = (String) iter.next();

            boolean isPublicProp = false;
            for( int i = 0; i < publicPropDefns.length; i++ )
            {
                if( utilPropName.equalsIgnoreCase( 
                        publicPropDefns[i].getName() ))
                {
                    isPublicProp = true;
                    break;
                }
            }
            if( isPublicProp )
                continue;   // skip public property

            String propValue = utilProps.getProperty( utilPropName );
            designProps.setProperty( utilPropName, propValue );
        }
        return designProps;
    }
    
    /**
     * Returns the connection profile instance externally referenced, 
     * i.e. linked, by the given data source design.
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
     * Finds and returns the property definition specified in
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
