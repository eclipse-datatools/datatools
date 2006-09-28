/*
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.swt.widgets.Composite;

/**
 * xml property page
 * 
 */
public class XMLPropertyPage extends DataSourceEditorPage
{
    private XMLSelectionPageHelper m_pageHelper;

    public XMLPropertyPage()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceEditorPage#collectCustomProperties(java.util.Properties)
     */
    public Properties collectCustomProperties( Properties profileProps )
    {
        if( m_pageHelper == null )
            return profileProps;

        return m_pageHelper.collectCustomProperties( profileProps );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceEditorPage#createAndInitCustomControl(org.eclipse.swt.widgets.Composite, java.util.Properties)
     */
    protected void createAndInitCustomControl( Composite parent, Properties profileProps )
    {
        if( m_pageHelper == null )
            m_pageHelper = new XMLSelectionPageHelper( this );

        m_pageHelper.createCustomControl( parent );
        m_pageHelper.initCustomControl( profileProps );
    }    
}
