/*
 *************************************************************************
 * Copyright (c) 2006, 2012 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.designsession;

import java.io.File;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataSetParameters;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.ElementNullability;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.OutputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.ParameterDefinition;
import org.eclipse.datatools.connectivity.oda.design.ParameterMode;
import org.eclipse.datatools.connectivity.oda.design.Properties;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ValueFormatHints;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DesignSessionUtilBase;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DesignerLogger;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaProfileFactory;
import org.eclipse.datatools.connectivity.oda.spec.manifest.FilterExpressionDefinition;
import org.eclipse.datatools.connectivity.oda.spec.manifest.ResultExtensionExplorer;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 *  An utility class to help an ODA host designer or
 *  an ODA driver's customized designer to manipulate
 *  ODA Design API objects during an ODA design session.
 */
public class DesignSessionUtil extends DesignSessionUtilBase
{
    private static final String EMPTY_STRING = "";   //$NON-NLS-1$
    // logging variable
    private static final String sm_className = DesignSessionUtil.class.getName();

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
     * @param utilProps      collection of property name and value pairs
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
     * @param publicPropDefns   public properties defined in an ODA runtime extension manifest
     * @param utilProps         collection of property name and value pairs
     * @return  converted collection of ODA design public properties 
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
            String propValue = ( utilProps != null ) ?
                                utilProps.getProperty( propName ) : null;
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
     * @param publicPropDefns   public properties defined in an ODA runtime extension manifest
     * @param utilProps a java.util.Properties collection,
     *                  such as those collected from a connection profile
     * @return  converted collection of ODA design non-public properties 
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
     * A convenience method to create a new DesignSessionRequest instance 
     * that contains a new data source design with the specified design attributes.
     * @param odaDataSourceId   the ODA data source extension id
     * @param newDataSourceName an unique name to identify the data source design instance
     * @param newDataSourceDisplayName  display name of the data source design; may be null
     * @param applResourceBaseURI   the base URI of general purpose resources of an ODA consumer application; may be null
     * @param designResourceBaseURI the base URI of design resources of an ODA consumer application; may be null
     * @return  a new DesignSessionRequest instance with the specified data source design attributes
     * @throws OdaException if specified argument(s) are invalid
     * @since 3.0.7
     */
    public static DesignSessionRequest createNewDataSourceRequest( 
            String odaDataSourceId,
            String newDataSourceName,
            String newDataSourceDisplayName,
            URI applResourceBaseURI,
            URI designResourceBaseURI )
        throws OdaException
    {
        return DesignSessionUtilBase.createNewDataSourceRequest( odaDataSourceId, 
                newDataSourceName, newDataSourceDisplayName, applResourceBaseURI, designResourceBaseURI );
    }

    // TODO - Expose as API method for client to create a request for use
    //        with DataSourceDesignSession#startNewDesignFromProfile
//    public static DesignSessionRequest createNewDataSourceProfileRequest( 
//            URI applResourceBaseURI,
//            URI designResourceBaseURI )
//    {
//        return DesignSessionUtilBase.createNewDataSourceProfileRequest( 
//                applResourceBaseURI, designResourceBaseURI );
//    }
    
    /**
     * A convenience method to assign the specified resource base URI(s) to the specified data source design.
     * @param dataSourceDesign  a data source design instance
     * @param applResourceBaseURI   the base URI of general purpose resources of an ODA consumer application; may be null
     * @param designResourceBaseURI the base URI of design resources of an ODA consumer application; may be null
     * @throws NullPointerException if dataSourceDesign argument is null
     * @since 3.0.7
     */
    public static void setDataSourceResourceIdentifiers(
            DataSourceDesign dataSourceDesign, URI applResourceBaseURI, URI designResourceBaseURI )
    {    
        DesignSessionUtilBase.setDataSourceResourceIdentifiers( dataSourceDesign, applResourceBaseURI, designResourceBaseURI );
    }

    /**
     * Create a runtime ResourceIdentifiers,
     * based on the resource URIs defined by the specified designResourceIdentifiers.
     * @param designResourceIdentifiers  a design resource identifier instance
     *          defined by the host application
     * @return  a new runtime ResourceIdentifiers that was converted from
     *          the specified designResourceIdentifiers
    * @since 3.2.6 (DTP 1.9.2)
     */
    public static org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers createRuntimeResourceIdentifiers( 
            ResourceIdentifiers designResourceIdentifiers )
    {
        return DesignSessionUtilBase.createRuntimeResourceIdentifiers( designResourceIdentifiers );
    }

    /**
     * A convenience method to create an application context Map with the entry of 
     * a runtime ResourceIdentifiers,
     * based on the resource URIs defined by the specified designResourceIdentifiers.
     * @param designResourceIdentifiers  a design resource identifier instance
     *          defined by the host application
     * @return  a new design session appContext Map with the entry of a runtime ResourceIdentifiers
     *          that was converted from the specified designResourceIdentifiers
    * @since 3.2.6 (DTP 1.9.2)
    **/
    public static Map<String,Object> createResourceIdentifiersContext( ResourceIdentifiers designResourceIdentifiers )
    {
        return DesignSessionUtilBase.createResourceIdentifiersContext( designResourceIdentifiers );
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
                    Messages.bind( Messages.designSession_invalidProfileNameOrFilePath,
                                linkedProfileName, dataSourceDesign.getLinkedProfileStoreFilePath() ));
        return profile;
    }
    
    /**
     * Creates an ODA connection profile instance from the specified data source design.
     * @param dataSourceDesign  a data source design
     * @param profileName       the name of the new profile; may be null or empty to use the same
     *                          name as that of the specified data source design
     * @return  a new instance of {@link IConnectionProfile} that persists in the DTP profile cache
     * @throws OdaException
     * @since 3.2 (DTP 1.7)
     */
    public static IConnectionProfile createProfile( DataSourceDesign dataSourceDesign, String profileName )
        throws OdaException
    {
        if( dataSourceDesign == null )
            return null;
        
        if( dataSourceDesign.hasLinkToProfile() )
        {
            // clear the linked profile properties to exclude them from the created profile
            dataSourceDesign = (DataSourceDesign) EcoreUtil.copy( dataSourceDesign );
            dataSourceDesign.setLinkedProfileName( null );
            dataSourceDesign.setLinkedProfileStoreFile( null );
        }

        if( profileName == null || profileName.length() == 0 )
            profileName = dataSourceDesign.getName();

        String description = dataSourceDesign.getDisplayName();
        if( description == null )
            description = EMPTY_STRING;

        return OdaProfileFactory.createProfile( profileName, 
                description, 
                dataSourceDesign.getOdaExtensionDataSourceId(), 
                getEffectiveDataSourceProperties( dataSourceDesign ) );
    }
    
    /*
     * @see org.eclipse.datatools.connectivity.oda.design.internal.designsession.DesignSessionUtilBase#getEffectiveDataSourceProperties( org.eclipse.datatools.connectivity.oda.design.Properties )
     */
    public static java.util.Properties getEffectiveDataSourceProperties( 
            DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        return DesignSessionUtilBase.getEffectiveDataSourceProperties( dataSourceDesign );
    }

    /**
     * Indicates whether the given ODA data source type has
     * implemented a valid 
     * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code>
     * extension point.
     * @param odaDataSourceId   an ODA data source extension type's element id 
     * @return  true if specified ODA data source has implemented a valid 
     *          ODA designer extension; false otherwise
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
     * Returns a collection of identifiers of all connection profile instances for
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
    public static Map getProfileIdentifiers( String odaDataSourceId, File storageFile ) 
        throws OdaException
    {
        return OdaProfileExplorer.getInstance().getProfileIdentifiersByOdaProviderId( 
                odaDataSourceId, storageFile );
    }
    
    /**
     * Returns a collection of identifiers of all connection profile instances under
     * the specified profile category id.
     * The profile instances are searched in the given profile storage file.
     * It also caches the matching profiles for subsequent use.
     * @param categoryId    the unique id of a connection profile category
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
     * @since 3.0.6
     */
    public static Map getProfileIdentifiersByCategory( String categoryId, File storageFile ) 
        throws OdaException
    {
        return OdaProfileExplorer.getInstance().getProfileIdentifiersByCategory( 
                categoryId, storageFile );
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
        final String methodName = "toResultSetColumnsDesign( IResultSetMetaData )"; //$NON-NLS-1$
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
                        convertResultColumnNullability( md.isNullable(i) ));
                columnAttrs.setPrecision( md.getPrecision(i) );
                columnAttrs.setScale( md.getScale(i) );
                
                String columnLabel = md.getColumnLabel(i);
                outAttrs.setLabel( columnLabel );

                formatHints.setDisplaySize( md.getColumnDisplayLength(i) );                
                if( columnLabel != null )
                    columnAttrs.setUiDisplayName( columnLabel );
            }
            catch( UnsupportedOperationException ex )
            {
                // ignore; optional attributes
                // log info
                DesignerLogger logger = DesignerLogger.getInstance();
                logger.info( sm_className, methodName, 
                        "Some optional metadata of column " + i + " are not available.", ex ); //$NON-NLS-1$ //$NON-NLS-2$
            }
            catch( OdaException odaEx )
            {
                // ignore; optional attributes
                // log warning about exception
                DesignerLogger logger = DesignerLogger.getInstance();
                logger.warning( sm_className, methodName, 
                        "Some optional metadata of column " + i + " are not available.", odaEx ); //$NON-NLS-1$ //$NON-NLS-2$
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
            adjustParameterDefaultAttributes( paramDefn );
            
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
     * Converts the value of an ODA runtime (result set column) nullability
     * to corresponding value used in an ODA design-time definition.
     * @param odaNullability an ODA runtime nullability constant
     * @return  corresponding constant for use in an ODA design-time definition
     * @deprecated As of 3.0.5, replaced by {@link #convertResultColumnNullability( int )}. 
     */
    public static ElementNullability toElementNullability( int odaNullability )
    {
        // for backward compatibility (Bugzilla 202407)
        return convertResultColumnNullability( odaNullability );
    }

    /**
     * Converts the value of an ODA runtime result set column nullability
     * to corresponding value used in an ODA design-time element definition.
     * @param columnNullability an ODA IResultSetMetaData nullability constant
     * @return  corresponding constant for use in an ODA design-time definition
     */
    public static ElementNullability convertResultColumnNullability( int columnNullability )
    {
        return DesignSessionUtilBase.convertResultColumnNullability( columnNullability );
    }
    
    /**
     * Converts the value of an ODA runtime parameter nullability
     * to corresponding value used in an ODA design-time element definition.
     * @param parameterNullability an ODA IParameterMetaData nullability constant
     * @return  corresponding constant for use in an ODA design-time definition
     */
    public static ElementNullability convertParameterNullability( int parameterNullability )
    {
        return DesignSessionUtilBase.convertParameterNullability( parameterNullability );
    }

    /**
     * Adjusts the parameter's design-time attributes to ensure 
     * that their default values are consistent with those converted from 
     * the runtime metadata.
     */
    private static void adjustParameterDefaultAttributes( ParameterDefinition paramDefn )
    {
        assert( paramDefn != null );
        DataElementAttributes basicAttrs = paramDefn.getAttributes();
        if( basicAttrs == null )
            return;     // no attributes to adjust from
        
        // a required input parameter (default setting) normally does not allow null value;
        // adjusts to optional if it is explicitly defined to allow null value
        if( paramDefn.isInput() && basicAttrs.allowsNull() )
        {
            InputElementAttributes inputAttributes = paramDefn.getEditableInputElementAttributes();
            inputAttributes.setOptional( true );
        }
    }
    
    /**
     * An utility method to look up the definition of the specified custom filter expression design.
     * @param customExpr    a custom filter expression specified in a data set design's filter specification 
     * @return  an instance of {@link FilterExpressionDefinition}, or null if no matching definition is found
     * @since 3.2 (DTP 1.7)
     */
    public static FilterExpressionDefinition getExtensionCustomDefinition( CustomFilterExpression customExpr )
    {
        if( customExpr == null )
            return null;
        return getExtensionCustomDefinition( customExpr.getDeclaringExtensionId(), customExpr.getId() );
    }
    
    /**
     * An utility method to look up the definition of the specified custom filter expression
     * declared by the specified extension.
     * @param extensionId   unique id of an extension that implements the ODA dynamicResultSet extension point
     * @param exprId    id of a custom filter expression 
     * @return  an instance of {@link FilterExpressionDefinition}, or null if no matching definition is found
     * @since 3.2 (DTP 1.7)
     */
    public static FilterExpressionDefinition getExtensionCustomDefinition( String extensionId, String exprId )
    {
        try
        {
            return ResultExtensionExplorer.getInstance().getExtensionFilterDefinition( extensionId, exprId );
        }
        catch( IllegalArgumentException ex )
        {
            // ignore exception
        }
        catch( OdaException ex )
        {
            // ignore exception
        }
        return null;
    }
    
}
