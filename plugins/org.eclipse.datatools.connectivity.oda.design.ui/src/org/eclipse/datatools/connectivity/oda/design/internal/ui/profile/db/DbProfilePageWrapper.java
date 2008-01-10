/*
 *************************************************************************
 * Copyright (c) 2007, 2008 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.swt.widgets.Composite;

/**
 * Common implementation of data source properties page controls,
 * shared by the ODA data source wizard page and property page that wrap 
 * a Database driver-contributed property page.
 */
public class DbProfilePageWrapper 
{
    static final String DEFAULT_MESSAGE = Messages.dbProfilePage_defaultPageMessage; 

//    private DbProfileWizardPage m_wizardPage = null;
//    private DbProfilePropertyPage m_propertyPage = null;    
    private ProfilePropertyPage m_wrappedPage;
        
    protected DbProfilePageWrapper( DbProfileWizardPage page, ProfilePropertyPage wrappedPage )
    {
    	assert( page != null && wrappedPage != null );
//        m_wizardPage = page;
        m_wrappedPage = wrappedPage;
        init();
    }

    protected DbProfilePageWrapper( DbProfilePropertyPage page, ProfilePropertyPage wrappedPage )
    {
        assert( page != null && wrappedPage != null );
//        m_propertyPage = page;
        wrappedPage.setContainer( page.getContainer() );
        m_wrappedPage = wrappedPage;
        init();
    }
    
    private void init()
    {
    }

    
    protected void createCustomControl( Composite parent ) throws OdaException
    {
        m_wrappedPage.createControl( parent );
    }

    protected void initCustomControl( Properties profileProps )
    {
        // noop;
        // a db profile page does not allow a caller to re-initialize its controls
        // with another set of profile properties;
        // it is up to the wrapped property page to initialize its controls based on
        // the page's connection profile element        
    }
    
    void setWrappedPingButtonEnabled( boolean enabled )
    {
        // if page has Test Connection button, set its enabled state
        if( m_wrappedPage instanceof ProfileDetailsPropertyPage )
        {
            ((ProfileDetailsPropertyPage) m_wrappedPage).setPingButtonEnabled( enabled );
        }      
    }
    
    boolean isValid()
    {
        return m_wrappedPage.isValid();
    }
    
    boolean hasDefaultPropertyPage()
    {
        return( m_wrappedPage instanceof DataSourceEditorPage );
    }

    /*
     * Notifies that the OK button of the wrapped page's container has been pressed.
     */
    public boolean performOk() 
    {
        return m_wrappedPage.performOk();
    }
  
    Properties collectCustomProperties( Properties initialProps )
    {
        return getOdaConnectionProfile().getBaseProperties(); 
    }
    
    private IConnectionProfile getOdaConnectionProfile() 
    {
        IConnectionProfile dbProfile = m_wrappedPage.getConnectionProfile();
        if( dbProfile instanceof OdaConnectionProfile )
        {
            // ensure that if this is an oda wrapper, its oda wrapper id is visible
            ((OdaConnectionProfile) dbProfile ).setHideWrapperId( false );
        }
        return dbProfile;
    }
    
    String getDbProfileProviderId()
    {
        IConnectionProfile connProfile = getOdaConnectionProfile();
        if( connProfile instanceof OdaConnectionProfile )
            return ((OdaConnectionProfile) connProfile ).getDirectProviderId();

        return null;
    }

}
