/*
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.spec.manifest;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * Represents the ODA Data Set Type supported by an extension of the
 * <i>org.eclipse.datatools.connectivity.oda.dynamicResultSet</i> extension point.
 * @since 3.2 (DTP 1.7)
 */
public class SupportedDataSetType
{
    public static final String ELEMENT_NAME = "supportedDataSetType"; //$NON-NLS-1$
    public static final String ATTR_DATA_SOURCE_ID = "odaDataSourceId"; //$NON-NLS-1$
    public static final String ATTR_DATA_SET_ID = "odaDataSetId"; //$NON-NLS-1$

    private String m_odaDataSourceId;
    private String m_odaDataSetId;

    SupportedDataSetType( IConfigurationElement dataSetTypeElement ) throws OdaException
    {
        init( dataSetTypeElement );
    }
    
    private void init( IConfigurationElement dataSetTypeElement ) throws OdaException
    {
        m_odaDataSourceId = dataSetTypeElement.getAttribute( ATTR_DATA_SOURCE_ID );
        if( m_odaDataSourceId == null || m_odaDataSourceId.length() == 0 )
            throw new OdaException( Messages.bind( Messages.querySpec_MISSING_EXT_POINT_ATTR_VALUE, 
                new Object[] { ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                    dataSetTypeElement.getContributor().getName(), ATTR_DATA_SOURCE_ID, ELEMENT_NAME} ));

        m_odaDataSetId = dataSetTypeElement.getAttribute( ATTR_DATA_SET_ID );
        if( m_odaDataSetId == null || m_odaDataSetId.length() == 0 )
            throw new OdaException( Messages.bind( Messages.querySpec_MISSING_EXT_POINT_ATTR_VALUE, 
                new Object[] { ResultExtensionExplorer.DTP_ODA_DYNAMIC_RESULT_SETS_EXT_POINT, 
                    dataSetTypeElement.getContributor().getName(), ATTR_DATA_SET_ID, ELEMENT_NAME} ));
    }

    /**
     * Returns the id of the ODA data source type for which this contributor 
     * is contributing custom filter expressions.
     * @return id of an ODA data source extension
     */
    public String getOdaDataSourceId()
    {
        return m_odaDataSourceId;
    }

    /**
     * Returns the id of an ODA data set type defined within the supported data source extension.
     * @return id of an ODA data set defined within the data source extension
     */
    public String getOdaDataSetId()
    {
        return m_odaDataSetId;
    }
    
    /**
     * Indicates whether this matches the specified data set type defined within the specified data source type.
     * @param odaDataSourceId   id of an ODA data source extension
     * @param odaDataSetId      id of an ODA data set defined within the data source extension
     * @return  true if the specified type matches; false otherwise
     */
    public boolean matches( String odaDataSourceId, String odaDataSetId )
    {
        return( getOdaDataSourceId().equals( odaDataSourceId ) &&
                getOdaDataSetId().equals( odaDataSetId ) );
    }
    
}
