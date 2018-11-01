/*
 *************************************************************************
 * Copyright (c) 2006, 2011 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.designsession;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;
import org.eclipse.datatools.connectivity.oda.consumer.services.impl.ProviderUtil;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.ElementNullability;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSetUIElement;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.connectivity.oda.profile.Constants;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataSetType;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.Property;

/**
 *  Base class implementation of an utility class to help an ODA host designer or
 *  an ODA driver's customized designer to manipulate
 *  ODA Design API objects during an ODA design session.
 */
public class DesignSessionUtilBase
{
    private static final String DEFAULT_PROPERTY_PROVIDER_ID = Constants.CONN_PROFILE_APPL_ID;
    
    // logging variable
    private static final String sm_className = DesignSessionUtilBase.class.getName();

    // class has static methods only; no need to instantiate
    protected DesignSessionUtilBase()
    {
    }

    /**
     * Finds and returns the property definition specified in
     * an ODA extension that implements the 
     * oda.datasource run-time extension point.
     */
    protected static Property[] getDataSourcePublicPropertiesDefn( 
            String odaDataSourceId )
        throws OdaException
    {
        try
        {
            ExtensionManifest runtimeManifest = 
                ManifestExplorer.getInstance().getExtensionManifest( 
                        odaDataSourceId );

            return runtimeManifest.getVisibleProperties();
        }
        catch( IllegalArgumentException ex )
        {
            throw new OdaException( ex );
        }        
    }

    /**
     * Finds and returns the property definition specified for the given 
     * oda data source element id and oda data set element id in
     * an ODA extension that implements the 
     * oda.datasource run-time extension point.
     */
    protected static Property[] getDataSetPublicPropertiesDefn( 
            String odaDataSourceId, String odaDataSetId )
        throws OdaException
    {
        try
        {
            ExtensionManifest runtimeManifest = 
                ManifestExplorer.getInstance().getExtensionManifest( 
                        odaDataSourceId );
            DataSetType dataSetType = runtimeManifest.getDataSetType( odaDataSetId );
            
            return dataSetType.getVisibleProperties();
        }
        catch( IllegalArgumentException ex )
        {
            throw new OdaException( ex );
        }        
    }
    
    /**
     * Obtains the effective property names and values for use during a design session to open
     * a connection to a data source.
     * @param dataSourceDesign  a data source design definition that contains
     *              static connection properties info. 
     *              It may include properties that reference an
     *              externalized connection profile instance, whose property 
     *              name-value pairs would take precedence over the static property values
     * @return  the set of effective property name-value pairs for opening a data source connection
     * @throws OdaException
     * @since 3.0.6
     */
    protected static java.util.Properties getEffectiveDataSourceProperties( 
            DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        java.util.Properties candidateProps = DesignUtil.convertDataSourceProperties( dataSourceDesign );

        IPropertyProvider propProvider = null;
        if( dataSourceDesign != null )
        {
            String effectiveDataSourceId = dataSourceDesign.getEffectiveOdaExtensionId();
            propProvider = getPropertyProvider( effectiveDataSourceId );

            // if the effective data source does not have own provider, 
            // try with the oda data source type being extended
            if( propProvider == null )
            {
                String baseDataSourceId = dataSourceDesign.getOdaExtensionDataSourceId();
                if( ! effectiveDataSourceId.equalsIgnoreCase( baseDataSourceId ) )
                    propProvider = getPropertyProvider( baseDataSourceId );
            }
        }
        if( propProvider == null )   // oda data source does not have own provider, use the system default
            propProvider = getPropertyProvider( DEFAULT_PROPERTY_PROVIDER_ID );
        
        if( propProvider != null )
        {
            candidateProps = propProvider.getDataSourceProperties( candidateProps, 
                                createResourceIdentifiersContext( dataSourceDesign.getHostResourceIdentifiers() ));
        }
    
        return candidateProps;
    }

    /**
    * Gets the property provider of the specified ODA data source extension.
    */
    private static IPropertyProvider getPropertyProvider( String odaDataSourceId )
    {
        IPropertyProvider propProvider = null;
        try
        {
            propProvider = 
                ProviderUtil.createPropertyProvider( odaDataSourceId );
        }
        catch( OdaException ex )
        {
            DesignerLogger logger = DesignerLogger.getInstance();
            logger.warning( sm_className, "getPropertyProvider(String)", //$NON-NLS-1$
                    "Unable to get ODA Profile property provider.", ex ); //$NON-NLS-1$
            return null;
        }
    
        return propProvider;
    }

    /**
     * Create a runtime ResourceIdentifiers,
     * based on the resource URIs defined by the specified designResourceIdentifiers.
     * @param designResourceIdentifiers
     * @return
     * @since 3.2.6 (DTP 1.9.2)
     */
    protected static org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers createRuntimeResourceIdentifiers( 
            ResourceIdentifiers designResourceIdentifiers )
    {
        if( designResourceIdentifiers == null ||
            ( designResourceIdentifiers.getApplResourceBaseURI() == null &&
              designResourceIdentifiers.getDesignResourceBaseURI() == null ) )
            return null;  // no resource base info to create from
        
        // create a runtime ResourceIdentifies from the appl resource base specified in the design version
        org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers runtimeResourceIds =
            new org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers();
        runtimeResourceIds.setApplResourceBaseURI( designResourceIdentifiers.getApplResourceBaseURI() );
        runtimeResourceIds.setDesignResourceBaseURI( designResourceIdentifiers.getDesignResourceBaseURI() );
        return runtimeResourceIds;
    }

    /**
    * Create an application context Map with the entry of a runtime ResourceIdentifiers,
    * based on the resource URIs defined by the specified designResourceIdentifiers.
    * @param designResourceIdentifiers  a design resource identifier instance
     *          defined by the host application
    * @return  a new design session appContext Map with the entry of a runtime ResourceIdentifiers
    *          that was converted from the specified designResourceIdentifiers
    * @since 3.2.6 (DTP 1.9.2)
     */
    protected static Map<String,Object> createResourceIdentifiersContext( ResourceIdentifiers designResourceIdentifiers )
    {
        org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers runtimeResourceIds = 
                createRuntimeResourceIdentifiers( designResourceIdentifiers );
        if( runtimeResourceIds == null )
            return Collections.emptyMap();  // nothing to put into the context map
        
        // store the runtime ResourceIdentifies in an appContext Map to return
        Map<String,Object> designSessionAppContext = new HashMap<String,Object>(1);
        String resourceIdKey = 
            org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers.ODA_APP_CONTEXT_KEY_CONSUMER_RESOURCE_IDS;
        designSessionAppContext.put( resourceIdKey, runtimeResourceIds );
        return designSessionAppContext;
    }

    /**
     * Returns the data set ui element manifest that
     * defines customized data set designer
     * that an ODA data provider extends to allow an user
     * to create or edit an ODA data set design instance.
     * This encapsulates the child elements for the data set wizard page(s) 
     * and editor page(s).  
     * @param odaDataSourceId
     * @param odaDataSetId
     * @return
     * @throws OdaException
     */
    static DataSetUIElement getDataSetUIElement( String odaDataSourceId,
                                        String odaDataSetId )
        throws OdaException
    {
        DataSetUIElement dataSetElement = UIManifestExplorer.getInstance()
                .getDataSetUIElement( odaDataSourceId, odaDataSetId );
        if( dataSetElement == null )
            throw new OdaException( 
                    Messages.bind( Messages.designSession_missingDataSetUIElement,
                            odaDataSourceId, odaDataSetId ));
        return dataSetElement;
    }

    /**
     * Validates the specified design session request.
     * If valid, returns the request's ODA data source element id.
     * @param requestSession
     * @return
     * @throws OdaException if specified session request is invalid
     */
    protected static String validateRequestSessionImpl( 
                    DesignSessionRequest requestSession )
        throws OdaException
    {
        String odaDataSourceId = null;
        try
        {
            odaDataSourceId =
                DesignUtil.validateRequestSession( requestSession );
        }
        catch( IllegalStateException ex )
        {
            throw new OdaException( ex );
        }
    
        // done validation
        return odaDataSourceId;
    }

    /**
     * Validates the specified data source design instance.
     * @param dataSourceDesign
     * @throws OdaException if specified design is invalid
     */
    protected static void validateDataSourceDesignImpl( 
                        DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        try
        {
            DesignUtil.validateDataSourceDesign( dataSourceDesign );
        }
        catch( IllegalStateException ex )
        {
            throw new OdaException( ex );
        }
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
    protected static DesignSessionRequest createNewDataSourceRequest( 
            String odaDataSourceId,
            String newDataSourceName,
            String newDataSourceDisplayName,
            URI applResourceBaseURI,
            URI designResourceBaseURI )
        throws OdaException
    {
        // validate input argument(s)
        if( odaDataSourceId == null || odaDataSourceId.length() == 0 || 
            newDataSourceName == null || newDataSourceName.length() == 0  )
            throw new OdaException( Messages.designSession_invalidArgument );
        
        // create a new data source design with the specified design attributes
        DataSourceDesign newDesign = DesignFactory.eINSTANCE.createDataSourceDesign();
        
        newDesign.setOdaExtensionId( odaDataSourceId );
            // no need to explicitly set OdaExtensionDataSourceId
        newDesign.setName( newDataSourceName );
        newDesign.setDisplayName( newDataSourceDisplayName ); 
        setDataSourceResourceIdentifiers( newDesign, applResourceBaseURI, designResourceBaseURI );
        
        // create a new request with the data source design
        DesignSessionRequest newRequest = DesignFactory.eINSTANCE.createDesignSessionRequest();
        newRequest.setNewDataAccessDesign( newDesign );
        
        validateRequestSessionImpl( newRequest );
    
        return newRequest;
    }

    /**
     * A convenience method to create a new DesignSessionRequest instance 
     * that contains a new data source design with the specified resource attributes.
     * The returned instance can be used to request a design session for
     * connection profile selection.
     * @param applResourceBaseURI   the base URI of general purpose resources of an ODA consumer application; may be null
     * @param designResourceBaseURI the base URI of design resources of an ODA consumer application; may be null
     * @return  a new DesignSessionRequest instance with the specified resource attributes
     * @since 3.0.7
     */
    protected static DesignSessionRequest createNewDataSourceProfileRequest( 
            URI applResourceBaseURI,
            URI designResourceBaseURI )
    {
        // create a new data source design with the specified design attributes;
        // an oda data source id will be specified later by a profile selection
        DataSourceDesign newDesign = DesignFactory.eINSTANCE.createDataSourceDesign();       
        setDataSourceResourceIdentifiers( newDesign, applResourceBaseURI, designResourceBaseURI );
        
        // create a new request with the data source design
        DesignSessionRequest newRequest = DesignFactory.eINSTANCE.createDesignSessionRequest();
        newRequest.setNewDataAccessDesign( newDesign );    
        return newRequest;
    }
    
    /**
     * A convenience method to assign the specified resource base URI(s) to the specified data source design.
     * @param dataSourceDesign  a data source design instance
     * @param applResourceBaseURI   the base URI of general purpose resources of an ODA consumer application; may be null
     * @param designResourceBaseURI the base URI of design resources of an ODA consumer application; may be null
     * @throws NullPointerException if dataSourceDesign argument is null
     * @since 3.0.7
     */
    protected static void setDataSourceResourceIdentifiers(
            DataSourceDesign dataSourceDesign, 
            URI applResourceBaseURI, 
            URI designResourceBaseURI )
    {
        if( dataSourceDesign == null )
            throw new NullPointerException( "dataSourceDesign: " + dataSourceDesign ); //$NON-NLS-1$

        ResourceIdentifiers resourceIDs = dataSourceDesign.getHostResourceIdentifiers();
        if( resourceIDs == null )
        {
            if( applResourceBaseURI == null && designResourceBaseURI == null )
                return;     // nothing new to set; done

            resourceIDs = DesignFactory.eINSTANCE.createResourceIdentifiers();
            dataSourceDesign.setHostResourceIdentifiers( resourceIDs );
        }
        
        resourceIDs.setApplResourceBaseURI( applResourceBaseURI );
        resourceIDs.setDesignResourceBaseURI( designResourceBaseURI );        
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
    protected static OdaDesignSession createNewDataSetRequestSession( 
                            String newDataSetName,
                            String odaDataSetId, 
                            DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        // validate input arguments
        if( newDataSetName == null || newDataSetName.length() == 0 ||
                dataSourceDesign == null )
            throw new OdaException( Messages.designSession_invalidArgument );
        
        OdaDesignSession newSession =
            DesignFactory.eINSTANCE
                .createRequestDesignSession( dataSourceDesign );
    
        DataSetDesign newDataSetDesign = 
            newSession.getRequestDataSetDesign();
        newDataSetDesign.setName( newDataSetName );
        newDataSetDesign.setOdaExtensionDataSetId( odaDataSetId );
    
        validateRequestSessionImpl( newSession.getRequest() );
    
        return newSession;
    }
    
    /**
     * Converts optional parameter data element attributes
     * and assigns to the specified DataElementAttributes. 
     * @param paramAttrs    a DataElementAttributes instance
     * @param pmd    ODA runtime parameters meta data instance
     * @param i     1-based parameter index
     * @throws OdaException
     */
    protected static void toElementOptionalAttributes( 
            DataElementAttributes paramAttrs, 
            IParameterMetaData pmd, 
            int i )
    {
        // iterate thru each optional attribute, even if any of them
        // has an exception
        int count = 1;
        while( count > 0 )
        {
            try
            {
                switch( count++ )
                {
                case 1:
                    paramAttrs.setName( pmd.getParameterName(i) );
                    continue;
                case 2:
                    paramAttrs.setPrecision( pmd.getPrecision(i) );
                    continue;
                case 3:
                    paramAttrs.setScale( pmd.getScale(i) );
                    continue;
                case 4:
                    paramAttrs.setNullability( 
                            convertParameterNullability( pmd.isNullable(i) ));
                default:
                    return;  // no more attributes, exit iteration
                }
            }
            catch( UnsupportedOperationException ex )
            {
                // ignore; optional attributes
                ex.printStackTrace();
                // log info
                DesignerLogger logger = DesignerLogger.getInstance();
                logger.info( sm_className, "toElementOptionalAttributes",  //$NON-NLS-1$
                        "The optional metadata attribute " + (count - 1) +  //$NON-NLS-1$
                        " of parameter " + i + " is not available.", ex ); //$NON-NLS-1$ //$NON-NLS-2$
            }
            catch( OdaException odaEx )
            {
                // ignore; optional attributes
                odaEx.printStackTrace();
                // log warning about exception
                DesignerLogger logger = DesignerLogger.getInstance();
                logger.warning( sm_className, "toElementOptionalAttributes",  //$NON-NLS-1$
                        "The optional metadata attribute " + (count - 1) +  //$NON-NLS-1$
                        " of parameter " + i + " is not available.", odaEx ); //$NON-NLS-1$ //$NON-NLS-2$
           }
        }
    }

    /**
     * Converts the value of an ODA runtime result set column nullability
     * to corresponding value used in an ODA design-time element definition.
     * @param columnNullability an ODA IResultSetMetaData nullability constant
     * @return  corresponding constant for use in an ODA design-time definition
     */
    protected static ElementNullability convertResultColumnNullability( int columnNullability )
    {
        ElementNullability toValue;
        switch( columnNullability )
        {
            case IResultSetMetaData.columnNoNulls:
                toValue = ElementNullability.NOT_NULLABLE_LITERAL; break;
            case IResultSetMetaData.columnNullableUnknown:
                toValue = ElementNullability.UNKNOWN_LITERAL; break;
            case IResultSetMetaData.columnNullable:
            default:
                toValue = ElementNullability.NULLABLE_LITERAL; break;
        }
        return toValue;
    }
    
    /**
     * Converts the value of an ODA runtime parameter nullability
     * to corresponding value used in an ODA design-time element definition.
     * @param parameterNullability an ODA IParameterMetaData nullability constant
     * @return  corresponding constant for use in an ODA design-time definition
     */
    protected static ElementNullability convertParameterNullability( int parameterNullability )
    {
        ElementNullability toValue;
        switch( parameterNullability )
        {
            case IParameterMetaData.parameterNoNulls:
                toValue = ElementNullability.NOT_NULLABLE_LITERAL; break;
            case IParameterMetaData.parameterNullableUnknown:
                toValue = ElementNullability.UNKNOWN_LITERAL; break;
            case IParameterMetaData.parameterNullable:
            default:
                toValue = ElementNullability.NULLABLE_LITERAL; break;
        }
        return toValue;
    }

}
