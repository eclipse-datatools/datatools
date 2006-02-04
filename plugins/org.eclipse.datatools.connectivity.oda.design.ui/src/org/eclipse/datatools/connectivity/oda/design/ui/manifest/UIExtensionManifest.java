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

package org.eclipse.datatools.connectivity.oda.design.ui.manifest;

import org.eclipse.birt.core.framework.IConfigurationElement;
import org.eclipse.birt.core.framework.IExtension;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

/**
 * Encapsulates access to the content of an ODA design-time
 * plug-in extension manifest.
 */
public class UIExtensionManifest
{
    public static final String DATA_SOURCE_ELEMENT_NAME = "dataSourceUI";
    private String m_namespace;
    private String m_dataSourceElementId;
    private DataSourceWizardInfo m_dataSourceWizardInfo;

    UIExtensionManifest( IExtension dataSourceExtn ) throws OdaException
    {
        IConfigurationElement dataSourceElement = 
            ManifestExplorer.getNamedElement( dataSourceExtn, DATA_SOURCE_ELEMENT_NAME );
        assert( dataSourceElement != null );

        m_namespace = dataSourceExtn.getNamespace();
        
        // first cache the data source element's attributes
        m_dataSourceElementId = dataSourceElement.getAttribute( "id" );  //$NON-NLS-1$
        assert( m_dataSourceElementId != null && 
                m_dataSourceElementId.length() > 0 );

        // newDataSourceWizard element
        final String wizardElementName = "newDataSourceWizard";  //$NON-NLS-1$
        IConfigurationElement[] newWizardElements = dataSourceElement.getChildren( wizardElementName );
        if( newWizardElements.length < 1 )
            // TODO - NLS
            throw new OdaException( "Invalid extension; missing required element: " + wizardElementName ); 
        m_dataSourceWizardInfo = new DataSourceWizardInfo( newWizardElements[0] );
    }

    private UIExtensionManifest()
    {
    }

    /**
     * @return Returns the m_namespace.
     */
    public String getNamespace()
    {
        return m_namespace;
    }

    /**
     * @return Returns the m_dataSourceElementId.
     */
    public String getDataSourceElementId()
    {
        return m_dataSourceElementId;
    }

    /**
     * @return Returns the m_dataSourceWizardInfo.
     */
    public DataSourceWizardInfo getDataSourceWizardInfo()
    {
        return m_dataSourceWizardInfo;
    }

}
