/*
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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

package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
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

        m_pageHelper.setResourceIdentifiers( this.getHostResourceIdentifiers( ) );
        m_pageHelper.createCustomControl( parent );
        m_pageHelper.initCustomControl( profileProps );
    }
    

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#refresh(java.util.Properties)
     */
    protected void refresh( Properties customConnectionProps  )
    {       
        // enable/disable all controls on page in respect of the editable session state
        enableAllControls( getControl(), isSessionEditable() );
    }
    
    protected Runnable createTestConnectionRunnable( final IConnectionProfile profile )
    {
    	return m_pageHelper.createTestConnectionRunnable( profile );
    }
}
