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

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.ElementNullability;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ParameterMode;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ValueFormatHints;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DesignSessionUtilBase;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;

/**
 *  An utility class to help an ODA host designer or
 *  an ODA driver's customized designer to manipulate
 *  ODA Design API objects during an ODA design session.
 */
public class DesignSessionUtil extends DesignSessionUtilBase
{
    // class has static methods only; no need to instantiate
    private DesignSessionUtil()
    {
    }
    
    /**
     * Creates an ODA design property collection for the data source public properties
     * defined in an ODA runtime extension manifest.  Their corresponding values 
     * are specified in the given Properties collection.
     * @param odaDataSourceId   an ODA extension data source element ID
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
            getDataSourcePublicPropertiesDefn( odaDataSourceId );

        return createPublicProperties( publicPropDefns, utilProps );
    }
    
    /**
     * Creates an ODA design property collection for the data set public properties
     * defined in an ODA runtime extension manifest.  Their corresponding values 
     * are specified in the given Properties collection.
     * @param odaDataSourceId   an ODA extension data source element ID
     * @param odaDataSetId      an ODA extension data set element id;
     *              may be null if the associated data source extension 
     *              supports only one type of data set 
     * @param utilProps      java.util.properties
     * @return  ODA design public property collection for inclusion
     *          in an OdaDesignSession's Data Set Design
     * @throws OdaException
     */
    public static Properties createDataSetPublicProperties( 
            String odaDataSourceId, String odaDataSetId, 
            java.util.Properties utilProps )
        throws OdaException
    {
        // first get the public property definition in the ODA driver's runtime plugin manifest
        org.eclipse.datatools.connectivity.oda.util.manifest.Property[] publicPropDefns = 
            getDataSetPublicPropertiesDefn( odaDataSourceId, odaDataSetId );
    
        return createPublicProperties( publicPropDefns, utilProps );
    }

    /**
     * Convert specified public properties defined in an ODA runtime extension manifest,
     * and corresponding values specified in the given Properties collection
     * into an ODA design property collection.
     * @param publicPropDefns
     * @param utilProps
     * @return
     */
    public static Properties createPublicProperties( 
            org.eclipse.datatools.connectivity.oda.util.manifest.Property[] publicPropDefns, 
            java.util.Properties utilProps )
    {
        if( publicPropDefns == null || publicPropDefns.length == 0 )
            return null;    // nothing to convert
        
        // create a new design Properties collection
        Properties designProps = 
            DesignFactory.eINSTANCE.createProperties();
        
        // for each defined public property name, get its corresponding
        // value in the specified utilProps, and create design property 
        // with the name and value in an oda design property collection
        for( int i = 0; i < publicPropDefns.length; i++ )
        {
            String propName = publicPropDefns[i].getName();
            String propValue = utilProps.getProperty( propName );
            designProps.setProperty( propName, propValue );
        }
        return designProps;
    }
    
    /**
     * Creates an ODA design property collection for those given data source properties
     * that are not defined in an ODA runtime extension manifest. 
     * These are properties that are not publicly defined. 
     * Their corresponding values 
     * are collected from the given property collection.
     * @param odaDataSourceId   an ODA extension data source element ID
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
            getDataSourcePublicPropertiesDefn( odaDataSourceId );
    
        return createNonPublicProperties( publicPropDefns, utilProps );
    }

    /**
     * Creates an ODA design property collection for those given data set properties
     * that are not defined in an ODA runtime extension manifest. 
     * These are properties that are not publicly defined. 
     * Their corresponding values 
     * are collected from the given property collection.
     * @param odaDataSourceId   an ODA extension data source element ID
     * @param odaDataSetId      an ODA extension data set element id;
     *              may be null if the associated data source extension 
     *              supports only one type of data set 
     * @param utilProps a java.util.Properties collection,
     *                  such as those collected from a connection profile
     * @return  ODA design non-public property collection for inclusion
     *          in an OdaDesignSession's Data Source Design
     * @throws OdaException
     */
    public static Properties createDataSetNonPublicProperties( 
            String odaDataSourceId, String odaDataSetId, 
            java.util.Properties utilProps )
    throws OdaException
    {
        // first get the public property definition in the ODA driver's runtime plugin manifest
        org.eclipse.datatools.connectivity.oda.util.manifest.Property[] publicPropDefns = 
            getDataSetPublicPropertiesDefn( odaDataSourceId, odaDataSetId );
    
        return createNonPublicProperties( publicPropDefns, utilProps );
    }

    /**
     * Convert given Properties collection that are not defined as
     * public properties in an ODA runtime extension manifest,
     * into an ODA design property collection.
     * @param publicPropDefns
     * @param utilProps
     * @return
     */
    public static Properties createNonPublicProperties( 
            org.eclipse.datatools.connectivity.oda.util.manifest.Property[] publicPropDefns, 
            java.util.Properties utilProps )
    {
        Properties designProps = null;
        
        // for each given property name-value pair, whose name is not
        // defined as a public property in the manifest, 
        // create corresponding design property with the name and value
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

            // create a new design Properties collection for the non-public property
            if( designProps == null )
                designProps = DesignFactory.eINSTANCE.createProperties();
            String propValue = utilProps.getProperty( utilPropName );
            designProps.setProperty( utilPropName, propValue );
        }

        return designProps;
    }

    /**
     * Creates a new OdaDesignSession instance with a session
     * request that contains a new data set design of
     * the specified name and odaDataSetId,
     * associated with the specified data source design instance.
     * @param newDataSetName   a unique name that identifies a data set 
     *          design instance
     * @param odaDataSetId      an ODA data set element id;
     *              may be null if the associated data source extension 
     *              supports only one type of data set 
     * @param dataSourceDesign  the associated data source design instance
     * @return  a new OdaDesignSession instance with a session
     *          request of the specified design attributes
     * @throws OdaException if specified arguments are invalid
     */
    public static OdaDesignSession createNewDataSetRequestSession( 
                            String newDataSetName,
                            String odaDataSetId, 
                            DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        return DesignSessionUtilBase.createNewDataSetRequestSession( 
                    newDataSetName, odaDataSetId, dataSourceDesign );
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
            throw new OdaException( 
                    Messages.bind( Messages.designSession_invalidProfileName,
                                linkedProfileName ));
        return profile;
    }
        
    /**
     * Indicates whether the given ODA data source type has
     * implemented a valid 
     * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code>
     * extension point.
     * @param odaDataSourceId   an ODA data source extension type's element id 
     * @return
     */
    public static boolean hasValidOdaDesignUIExtension( 
                                    String odaDataSourceId )
    {
        Object manifest;
        try
        {
            manifest =
                UIManifestExplorer.getInstance()
                    .getExtensionManifest( odaDataSourceId );
        }
        catch( OdaException ex )
        {
            // has invalid manifest
            manifest = null;
        }

        return ( manifest != null );
    }
    
    /**
     * Returns a collection of identifiers of 
     * all connection profile instances for
     * the given ODA data source extension type.
     * The profile instances are searched in the given profile storage file.
     * It also caches the matching profiles for subsequent use.
     * @param odaDataSourceId   an ODA data source extension type's element id 
     * @param storageFile   a file that stores profile instances;
     *                      may be null, which means to use the
     *                      default DTP profiles storage file
     * @return  a <code>Map</code> containing the instance Id
     *          and display name of all existing profiles of the given odaDataSourceId.
     *          The connection profiles' instance Id and display name
     *          are stored as the key and value strings in the returned <code>Map</code> instance.
     *          Returns an empty collection if there are 
     *          no matching connection profiles found in given storageFile.
     * @throws OdaException if error in reading from given storageFile,
     *                      or in processing the found profiles
     */
    public static Map getProfileIdentifiers( String odaDataSourceId, 
                                            File storageFile ) 
        throws OdaException
    {
        if( storageFile == null )
            return OdaProfileExplorer.getInstance().getProfiles( 
                    odaDataSourceId );
        return OdaProfileExplorer.getInstance().getProfiles( 
                odaDataSourceId, storageFile );
    }
    
    /**
     * Validates the specified design session request.
     * If valid, returns the request's ODA data source element id.
     * @param requestSession
     * @return
     * @throws OdaException if specified session request is invalid
     */
    static String validateRequestSession( 
                    DesignSessionRequest requestSession )
        throws OdaException
    {
        return validateRequestSessionImpl( requestSession );    
    }
    
    /**
     * Validates the specified data source design instance.
     * @param dataSourceDesign
     * @throws OdaException if specified design is invalid
     */
    static void validateDataSourceDesign( 
                        DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        validateDataSourceDesignImpl( dataSourceDesign );
    }

    /**
     * Converts the contents of an ODA runtime result set meta data
     * to an ODA design-time ResultSetColumns instance.
	 * This may be used by an ODA custom page to
	 * populate a data set design instance with its
	 * result set meta data definition.
     * @param md    ODA runtime result set meta data instance
     * @return  the converted ResultSetColumns instance
     * @throws OdaException
     */
    public static ResultSetColumns toResultSetColumnsDesign( 
                            IResultSetMetaData md )
        throws OdaException
    {
        if( md == null || md.getColumnCount() == 0 )
            return null; // nothing to convert
        
        ResultSetColumns rsColumns =
            DesignFactory.eINSTANCE.createResultSetColumns();
        int columnCount = md.getColumnCount();
        for( int i = 1; i <= columnCount; i++ )
        {
            ColumnDefinition columnDef =
                DesignFactory.eINSTANCE.createColumnDefinition();

            DataElementAttributes columnAttrs =
                DesignFactory.eINSTANCE.createDataElementAttributes();
            columnAttrs.setPosition( i );
            columnAttrs.setNativeDataTypeCode( md.getColumnType(i) );

            ValueFormatHints formatHints = 
                    DesignFactory.eINSTANCE.createValueFormatHints();
            OutputElementAttributes outAttrs = 
                    DesignFactory.eINSTANCE.createOutputElementAttributes();

            try
            {
                columnAttrs.setName( md.getColumnName(i) );
                columnAttrs.setNullability( 
                        toElementNullability( md.isNullable(i) ));
                columnAttrs.setPrecision( md.getPrecision(i) );
                columnAttrs.setScale( md.getScale(i) );
                
                outAttrs.setLabel( md.getColumnLabel(i) );

                formatHints.setDisplaySize( md.getColumnDisplayLength(i) );                
            }
            catch( UnsupportedOperationException e )
            {
                // ignore; optional attributes
                // TODO - log info
            }
            catch( OdaException odaEx )
            {
                // ignore; optional attributes
                // TODO - log info
            }

            columnDef.setAttributes( columnAttrs );
            outAttrs.setFormattingHints( formatHints );
            columnDef.setUsageHints( outAttrs );
            
            rsColumns.getResultColumnDefinitions().add( columnDef );
        }
        
        return rsColumns;
    }

    /**
     * Converts the contents of an ODA runtime parameters meta data
     * to an ODA design-time DataSetParameters instance.
     * This may be used by an ODA custom page to
     * populate a data set design instance with its
     * parameter meta data definitions.
     * @param pmd    ODA runtime parameters meta data instance
     * @return  the converted DataSetParameters instance
     * @throws OdaException
     */
    public static DataSetParameters toDataSetParametersDesign( 
                        IParameterMetaData pmd )
        throws OdaException
    {
        return toDataSetParametersDesign( pmd, null );
    }

    /**
     * Converts the contents of an ODA runtime parameters meta data
     * to an ODA design-time DataSetParameters instance.
     * This may be used by an ODA custom page to
     * populate a data set design instance with its
     * parameter meta data definitions.
     * @param pmd    ODA runtime parameters meta data instance
     * @param defaultMode   default design-time parameter mode to apply
     *                      if a parameter's mode is not recognized;
     *                      may be null for no default value 
     * @return  the converted DataSetParameters instance
     * @throws OdaException
     */
    public static DataSetParameters toDataSetParametersDesign( 
                        IParameterMetaData pmd, ParameterMode defaultMode )
        throws OdaException
    {
        if( pmd == null || pmd.getParameterCount() == 0 )
            return null; // nothing to convert
        
        DataSetParameters dataSetParams =
            DesignFactory.eINSTANCE.createDataSetParameters();
        
        // iterate thru each runtime parameter's metadata,
        // and convert to corresponding design object
        int paramCount = pmd.getParameterCount();
        for( int i = 1; i <= paramCount; i++ )
        {
            ParameterDefinition paramDefn =
                DesignFactory.eINSTANCE.createParameterDefinition();

            ParameterMode designMode = toParameterModeDesign( 
                    pmd.getParameterMode(i), defaultMode );
            if( designMode != null )
                paramDefn.setInOutMode( designMode );            

            DataElementAttributes paramAttrs =
                DesignFactory.eINSTANCE.createDataElementAttributes();
            paramAttrs.setPosition( i );
            paramAttrs.setNativeDataTypeCode( pmd.getParameterType(i) );

            toElementOptionalAttributes( paramAttrs, pmd, i );
            
            paramDefn.setAttributes( paramAttrs );
            
            dataSetParams.getParameterDefinitions().add( paramDefn );
        }

        return dataSetParams;
    }

    /**
     * Converts the specified ODA runtime parameter mode value
     * to corresponding ODA design-time parameter mode value.
     * @param runtimeParamMode  an ODA runtime parameter mode value
     * @return  the corresponding ODA design-time parameter mode value
     */
    public static ParameterMode toParameterModeDesign( 
                                    int runtimeParamMode ) 
    {
        return toParameterModeDesign( runtimeParamMode, null );
    }
   
    /**
     * Converts the specified ODA runtime parameter mode value
     * to corresponding ODA design-time parameter mode value.
     * @param runtimeParamMode  an ODA runtime parameter mode value
     * @param defaultMode       default design-time parameter mode to apply
     *                          if specified runtimeParamMode is not recognized;
     *                          may be null for no default value 
     * @return  the corresponding ODA design-time parameter mode value
     */
    public static ParameterMode toParameterModeDesign( 
                                    int runtimeParamMode, ParameterMode defaultMode ) 
    {
        ParameterMode designMode = null;
        switch( runtimeParamMode )
        {
            case IParameterMetaData.parameterModeIn:
                designMode = ParameterMode.IN_LITERAL; break;
            case IParameterMetaData.parameterModeInOut:
                designMode = ParameterMode.IN_OUT_LITERAL; break;
            case IParameterMetaData.parameterModeOut:
                designMode = ParameterMode.OUT_LITERAL; break;
            default:
                designMode = defaultMode; break;
        }
        return designMode;
    }

    /**
     * Converts the value of the ODA runtime nullability
     * to that of the ODA design-time definition.
     * @param odaNullability an ODA runtime nullability value
     * @return
     */
    public static ElementNullability toElementNullability( int odaNullability )
    {
        return DesignSessionUtilBase.toElementNullability( odaNullability );
    }
    
}
