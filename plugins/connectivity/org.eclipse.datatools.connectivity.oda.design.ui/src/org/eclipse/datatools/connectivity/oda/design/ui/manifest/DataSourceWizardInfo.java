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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;

/**
 * Represents the customizable behavior defined by an ODA UI Extension
 * for its new data source wizard dialog. 
 * It encapsulates the content of the <i>newDataSourceWizard</i> element
 * defined in the ODA Design UI extension point.
 */
public class DataSourceWizardInfo
{
    private String m_windowTitle;
    private boolean m_includesProgressMonitor = true; // default value
    private String m_pageTitle;
    private String m_pageClassName;
    
    DataSourceWizardInfo( IConfigurationElement wizardElement )
        throws OdaException
    {
        // required attribute
        final String classAttributeName = "pageClass";  //$NON-NLS-1$
        m_pageClassName = wizardElement.getAttribute( classAttributeName );
        if( m_pageClassName == null || m_pageClassName.length() == 0 )
            throw new OdaException( 
                    Messages.bind( Messages.manifest_missingAttributeValue,
                                    classAttributeName )); 
        
        // optional attributes
        m_windowTitle = wizardElement.getAttribute( "windowTitle" );  //$NON-NLS-1$
        m_pageTitle = wizardElement.getAttribute( "pageTitle" );  //$NON-NLS-1$

        String boolValue = wizardElement.getAttribute( "includesProgressMonitor" );  //$NON-NLS-1$
        if( boolValue != null )
        {
            if ( boolValue.equalsIgnoreCase( "true" ) ||   //$NON-NLS-1$
                 boolValue.equalsIgnoreCase( "false" ) )   //$NON-NLS-1$
                m_includesProgressMonitor = Boolean.valueOf( boolValue ).booleanValue();
        }
    }
    
    DataSourceWizardInfo()
    {
    }

    /**
     * @return Returns the m_windowTitle.
     */
    public String getWindowTitle()
    {
        return m_windowTitle;
    }

    /**
     * @return Returns the m_includesProgressMonitor.
     */
    public boolean includesProgressMonitor()
    {
        return m_includesProgressMonitor;
    }

    /**
     * @return Returns the m_pageClassName.
     */
    public String getPageClassName()
    {
        return m_pageClassName;
    }

    /**
     * @return Returns the m_pageTitle.
     */
    public String getPageTitle()
    {
        return m_pageTitle;
    }

}
