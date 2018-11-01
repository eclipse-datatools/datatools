/*
 *************************************************************************
 * Copyright (c) 2007, 2010 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsPropertyPage;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.swt.widgets.Composite;

/**
 * Common implementation of data source properties page controls,
 * shared by the ODA data source wizard page and property page that wrap 
 * a Database driver-contributed property page.
 * @since DTP 1.6
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
    
    protected void createCustomControl( Composite parent, boolean isReadOnly ) 
        throws OdaException
    {
        m_wrappedPage.createControl( parent, isReadOnly );
    }

    protected void initCustomControl( Properties profileProps )
    {
        // noop;
        // a db profile page does not allow a caller to re-initialize its controls
        // with another set of profile properties;
        // it is up to the wrapped property page to initialize its controls based on
        // the page's connection profile element        
    }
    
    boolean hasDefaultPropertyPage()
    {
        return( m_wrappedPage instanceof DataSourceEditorPage );
    }
    
    boolean isValid()
    {
        return m_wrappedPage.isValid() && isPageComplete();
    }
    
    private boolean isPageComplete()
    {
        if( m_wrappedPage instanceof ExtensibleProfileDetailsPropertyPage )
        {
            // check if page is missing required properties
            return ((ExtensibleProfileDetailsPropertyPage)m_wrappedPage).determinePageCompletion();
        }
        return true;    // default
    }

    String getErrorMessage()
    {
        return m_wrappedPage.getErrorMessage();
    }
    
    /*
     * Notifies that the OK button of the wrapped page's container has been pressed or triggered.
     */
    public boolean performOk() 
    {
        return m_wrappedPage.performOk();
    }
  
    Properties collectCustomProperties( boolean isSessionEditable )
    {
        if( isSessionEditable )
        {
            // if page is missing required properties, cannot perform Ok;
            // #performOk triggers update of the page profile element w/ 
            // the property values changed on UI page 
            if( isPageComplete() )
                performOk();    
        }

        return getWrappedPageProfile().getBaseProperties(); 
    }
    
    private IConnectionProfile getWrappedPageProfile() 
    {
        return m_wrappedPage.getConnectionProfile();
    }
    
    String getDbProfileProviderId()
    {
        IConnectionProfile connProfile = getWrappedPageProfile();
        if( connProfile instanceof OdaConnectionProfile )
            return ((OdaConnectionProfile) connProfile ).getDirectProviderId();

        return null;
    }

    void cleanup()
    {
        IConnectionProfile propertyPageProfile = getWrappedPageProfile();
        if( propertyPageProfile instanceof OdaConnectionProfile )
            ((OdaConnectionProfile) propertyPageProfile ).close();       
    }
    
}
