/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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
 * Represents the definition of a customized page that 
 * an extension contributes to an ODA host designer's data set dialog.
 */
public class DataSetPageInfo
{
    static final String ID_ATTRIBUTE = "id"; //$NON-NLS-1$
    static final String CLASS_ATTRIBUTE = "wizardPageClass"; //$NON-NLS-1$

    private String m_id;
    private String m_wizardPageClassName;
    private String m_displayName;
    private String m_path;
    private String m_icon;

    DataSetPageInfo( IConfigurationElement dataSetPageElement ) 
        throws OdaException
    {
        m_id = dataSetPageElement.getAttribute( ID_ATTRIBUTE );
        if( m_id == null || m_id.length() == 0 )
            throw new OdaException( 
                    Messages.bind( Messages.manifest_missingAttributeValue,
                                    ID_ATTRIBUTE ));

        m_wizardPageClassName = dataSetPageElement.getAttribute( CLASS_ATTRIBUTE );
        if( m_wizardPageClassName == null || m_wizardPageClassName.length() == 0 )
            throw new OdaException( 
                    Messages.bind( Messages.manifest_missingAttributeValue,
                                    CLASS_ATTRIBUTE ));

        m_displayName = dataSetPageElement.getAttribute( "displayName" ); //$NON-NLS-1$
        m_path = dataSetPageElement.getAttribute( "path" ); //$NON-NLS-1$
        m_icon = dataSetPageElement.getAttribute( "icon" ); //$NON-NLS-1$
    }

    /**
     * Returns the unique name of this page within a data set dialog.
     * @return the attribute value in <i>dataSetUI.dataSetPage.id</i>
     */
    public String getPageId()
    {
        return m_id;
    }

    /**
     * Returns the fully qualified class name that implements 
     * a custom data set wizard page that allows an user
     * to create or edit an ODA data set design instance.
     * <br>The wizard page class must extend from the ODA UI
     * framework's data set wizard page base class.  
     * See plugin schema for details.
     * @return the attribute value in <i>dataSetUI.dataSetPage.wizardPageClass</i>
     */
    public String getWizardPageClassName()
    {
        return m_wizardPageClassName;
    }

    /**
     * Returns the page title or descriptive name of a customized page
     * that will be displayed in the UI of this page.
     * @return the attribute value in <i>dataSetUI.dataSetPage.displayName</i>;
     *          may be null if none is specified
     */
    public String getDisplayName()
    {
        // Default to its id, if no display name is specified
        if ( m_displayName == null || m_displayName.length() == 0 )
            return getPageId();
        return m_displayName;
    }

    /**
     * Returns the path of the page in a data set preference dialog.
     * @return the attribute value in <i>dataSetUI.dataSetPage.path</i>;
     *          may be null if none is specified
     */
    public String getPath()
    {
        return m_path;
    }

    /**
     * Returns the relative path to an icon that may 
     * be used in the UI in addition to the page's display name.
     * @return the attribute value in <i>dataSetUI.dataSetPage.icon</i>;
     *          may be null if none is specified
     */
    public String getIcon()
    {
        return m_icon;
    }
    
}
