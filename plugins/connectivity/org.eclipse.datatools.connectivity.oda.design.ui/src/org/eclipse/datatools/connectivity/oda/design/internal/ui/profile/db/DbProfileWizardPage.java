/*
 *************************************************************************
 * Copyright (c) 2007, 2009 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * The base class implementation of an ODA data source wizard page that wraps 
 * the driver-contributed property page of a Database connection profile type.
 * @since DTP 1.6
 */
public class DbProfileWizardPage extends DataSourceWizardPage
{
    private DbProfilePageWrapper m_pageHelper = null;
    private Properties m_dataSourceProps = null;
    private ProfilePropertyPage m_wrappedPage;

    public DbProfileWizardPage( String pageName, ProfilePropertyPage dbPropertyPage )
    {
        super( pageName );

        // expects argument to be not null
        m_wrappedPage = dbPropertyPage;
        setTitle( m_wrappedPage.getConnectionProfile().getName() );
        setMessage( DbProfilePageWrapper.DEFAULT_MESSAGE );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPageCustomControl( Composite parent )
    {
        if( m_pageHelper == null )
            m_pageHelper = createDataSourcePageWrapper( );
        
        try
        {
            // specifies isReadOnly option based on the editable session state
            m_pageHelper.createCustomControl( parent, ! isSessionEditable() );
        }
        catch( OdaException e )
        {
            e.printStackTrace();
        }
        
        m_pageHelper.initCustomControl( m_dataSourceProps );   // in case init was called before create         
        
        // hide the wizard page's button when it is created; the wrapped property page's button is used instead
        setPingButtonVisible( false );  
    }
    
    /**
     * Instantiates the page helper that provides core implementation
     * of this wizard page.
     * @return
     */
    protected DbProfilePageWrapper createDataSourcePageWrapper( )
    {
        return new DbProfilePageWrapper( this, m_wrappedPage );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceWizardPage#initPageCustomControl(java.util.Properties)
     */
    public void setInitialProperties( Properties dataSourceProps )
    {
        m_dataSourceProps = dataSourceProps;
        if( m_pageHelper != null )
            m_pageHelper.initCustomControl( m_dataSourceProps );        
        // ignore if m_pageHelper is null, wait till createPageCustomControl to initialize
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#refresh()
     */
    public void refresh()
    {
        // noop;
        // the editable session state is handled when custom control is first created
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceWizardPage#collectCustomProperties()
     */
    public Properties collectCustomProperties()
    {
        if( m_pageHelper != null ) 
        {
            boolean isSessionEditable = false;
            if( getWizard() instanceof NewDbDataSourceWizardBase )
                isSessionEditable = ! ((NewDbDataSourceWizardBase)getWizard()).isCreatingFromProfile();

            return  m_pageHelper.collectCustomProperties( isSessionEditable );
        }

        return ( m_dataSourceProps != null ) ?
                    m_dataSourceProps : new Properties();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceWizardPageCore#getNextPage()
     */
    public IWizardPage getNextPage()
    {
        // ODA data source designer supports a single property page only
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    public boolean isPageComplete()
    {
        if( m_pageHelper == null ) 
            return super.isPageComplete();
        return m_pageHelper.isValid();
    }
    
    String getDbProfileProviderId()
    {
        if( m_pageHelper == null ) 
            return null;
        return m_pageHelper.getDbProfileProviderId();
    }
    
}
