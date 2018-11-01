/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.manifest;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;

/**
 * Encapsulates access to the content of an ODA design-time
 * plug-in extension manifest.
 */
public class UIExtensionManifest
{
    static final String DATA_SOURCE_ELEMENT_NAME = "dataSourceUI"; //$NON-NLS-1$
    static final String DATA_SET_ELEMENT_NAME = "dataSetUI"; //$NON-NLS-1$
    static final String DATA_SOURCE_WIZARD_ELEMENT_NAME = "newDataSourceWizard";  //$NON-NLS-1$

    private String m_namespace;
    private String m_dataSourceElementId;
    private DataSourceWizardInfo m_dataSourceWizardInfo;
    private Hashtable m_dataSetUIElements;

    UIExtensionManifest( IExtension dataSourceExtn ) throws OdaException
    {
        // first process the dataSourceUI element
        IConfigurationElement dataSourceElement = 
            UIManifestExplorer.getNamedElement( dataSourceExtn, DATA_SOURCE_ELEMENT_NAME );
        assert( dataSourceElement != null );

        m_namespace = dataSourceExtn.getContributor().getName();
        
        // first cache the data source element's attributes
        m_dataSourceElementId = dataSourceElement.getAttribute( "id" );  //$NON-NLS-1$
        assert( m_dataSourceElementId != null && 
                m_dataSourceElementId.length() > 0 );

        // newDataSourceWizard element associated with dataSourceUI
        IConfigurationElement[] newWizardElements = 
            dataSourceElement.getChildren( DATA_SOURCE_WIZARD_ELEMENT_NAME );
        if( newWizardElements.length < 1 )  // expects one element
            throw new OdaException( 
                    Messages.bind( Messages.manifest_missingAttributeValue,
                                DATA_SOURCE_WIZARD_ELEMENT_NAME )); 
        m_dataSourceWizardInfo = new DataSourceWizardInfo( newWizardElements[0] );

        // data set UI definition elements in the same extension
        m_dataSetUIElements = getDataSetUIElements( dataSourceExtn );
    }

    UIExtensionManifest()
    {
    }
    
    /**
     * Returns a collection of dataSetUI elements in the given extension.
     */
    private Hashtable getDataSetUIElements( IExtension extension )
        throws OdaException
    {
        Hashtable dataSetElements = new Hashtable();
        IConfigurationElement[] configElements =
            UIManifestExplorer.getNamedElements( extension, DATA_SET_ELEMENT_NAME );
        for( int i = 0, size = configElements.length; i < size; i++ )
        {
            IConfigurationElement configElement = configElements[i];

            String dataSetId = configElement.getAttribute( "id" );  //$NON-NLS-1$

            // if duplicated data set ids exist in the extension,  
            // only the last one applies
            dataSetElements.put( dataSetId, 
                            new DataSetUIElement( configElement ) );
        }
        
        if( dataSetElements.size() < 1 )    // expects one or more
        	throw new OdaException( 
                Messages.bind( Messages.manifest_dataSetUi_missingElement,
                		DATA_SET_ELEMENT_NAME ));
        
        return dataSetElements;
    }

    /**
     * Returns the namespace of the plugin that contributes this extension.
     * @return
     */
    public String getNamespace()
    {
        return m_namespace;
    }

    /**
     * Returns the fully qualified ID that uniquely identifies 
     * the ODA data source extension within an ODA consumer 
     * application's environment.
     * @return the attribute value in <i>dataSourceUI.id</i>
     */
    public String getDataSourceElementId()
    {
        return m_dataSourceElementId;
    }

    /**
     * Returns the definition of customizable behavior of a 
     * data source wizard that allows an user to create 
     * a new ODA data source design instance.
     * @return the attribute value in <i>dataSourceUI.newDataSourceWizard</i>
     */
    public DataSourceWizardInfo getDataSourceWizardInfo()
    {
        return m_dataSourceWizardInfo;
    }
    
    /**
     * Returns an array of DataSetUIElement instances that
     * represent the dataSetUI elements defined in
     * this data source extension.
     * @return  an array of data set ui elements 
     */
    public DataSetUIElement[] getDataSetUIElements()
    {
        if( m_dataSetUIElements == null )
            return new DataSetUIElement[0];
        
        Collection dataSetUIs = m_dataSetUIElements.values();
        return (DataSetUIElement[]) dataSetUIs.toArray( 
                    new DataSetUIElement[ dataSetUIs.size() ] );
    }
    
    /**
     * Returns an array of ids of the dataSetUI elements 
     * defined in this data source extension.
     * @return  an array of data set element ids.
     */
    public String[] getDataSetUIElementIDs()
    {
        if( m_dataSetUIElements == null )
            return new String[0];
        
        Set dataSetIDs = m_dataSetUIElements.keySet();
        return (String[]) dataSetIDs.toArray( 
                    new String[ dataSetIDs.size() ] );
    }
    
    /**
     * Returns the DataSetUIElement instance that
     * represents the dataSetUI element with the given id
     * defined in this data source extension.
     * If the given data set element id is null and the data source UI
     * extension has only one data set element, that
     * data set element will be returned by default.
     * @param dataSetElementID  the id of the data set ui element.
     * @return  the data set element definition.
     * @throws OdaException if there is no data set ui definition associated 
     *                   with the specified data set element id, or 
     *                   if there are more than one data set elements 
     *                   when no element id is specified.
     */
    public DataSetUIElement getDataSetUIElement( String dataSetElementID ) 
        throws OdaException
    {
        if( dataSetElementID == null || dataSetElementID.length() == 0 )
        {
            // find default data set element and return it if found
            if( m_dataSetUIElements == null ||
                m_dataSetUIElements.size() != 1 )
                throw new OdaException( Messages.manifest_missingDataSetElementId );

            Collection dataSetTypes = m_dataSetUIElements.values();
            assert( dataSetTypes.size() == 1 );
            return (DataSetUIElement) dataSetTypes.toArray()[0];
        }
        
        DataSetUIElement dataSetUIDefn = 
            (DataSetUIElement) m_dataSetUIElements.get( dataSetElementID );

        if( dataSetUIDefn == null )
            throw new OdaException( 
                    Messages.bind( Messages.manifest_invalidDataSetElementId,
                                    dataSetElementID ));
        
        return dataSetUIDefn;
    }

}
