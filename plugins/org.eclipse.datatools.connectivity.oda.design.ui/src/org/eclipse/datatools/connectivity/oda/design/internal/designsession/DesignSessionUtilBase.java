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

package org.eclipse.datatools.connectivity.oda.design.internal.designsession;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.ElementNullability;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSetUIElement;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataSetType;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

/**
 *  Base class implementation of an utility class to help an ODA host designer or
 *  an ODA driver's customized designer to manipulate
 *  ODA Design API objects during an ODA design session.
 */
public class DesignSessionUtilBase
{
    // class has static methods only; no need to instantiate
    protected DesignSessionUtilBase()
    {
    }

    /**
     * Finds and returns the property definition specified in
     * an ODA extension that implements the 
     * oda.datasource run-time extension point.
     */
    protected static org.eclipse.datatools.connectivity.oda.util.manifest.Property[] 
        getDataSourcePublicPropertiesDefn( String odaDataSourceId )
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
            throw new OdaException( ex );
        }        
    }

    /**
     * Finds and returns the property definition specified for the given 
     * oda data source element id and oda data set element id in
     * an ODA extension that implements the 
     * oda.datasource run-time extension point.
     */
    protected static org.eclipse.datatools.connectivity.oda.util.manifest.Property[] 
        getDataSetPublicPropertiesDefn( String odaDataSourceId, String odaDataSetId )
        throws OdaException
    {
        try
        {
            ExtensionManifest runtimeManifest = 
                ManifestExplorer.getInstance().getExtensionManifest( 
                        odaDataSourceId );
            DataSetType dataSetType = runtimeManifest.getDataSetType( odaDataSetId );
            return dataSetType.getProperties();
        }
        catch( IllegalArgumentException ex )
        {
            throw new OdaException( ex );
        }        
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
        try
        {
            paramAttrs.setPrecision( pmd.getPrecision(i) );
            paramAttrs.setScale( pmd.getScale(i) );
            paramAttrs.setNullability( 
                    toElementNullability( pmd.isNullable(i) ));
        }
        catch( UnsupportedOperationException e )
        {
            // ignore; optional attributes
            // TODO - log info
            e.printStackTrace();
        }
        catch( OdaException odaEx )
        {
            // ignore; optional attributes
            // TODO - log info
            odaEx.printStackTrace();
        }
    }

    /**
     * Converts the value of the ODA runtime nullability
     * to that of the ODA design-time definition.
     * @param odaNullability an ODA runtime nullability value
     * @return
     */
    protected static ElementNullability toElementNullability( int odaNullability )
    {
        ElementNullability toValue;
        switch( odaNullability )
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

}
