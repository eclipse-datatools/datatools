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

package org.eclipse.datatools.connectivity.oda.design.ui.pages.impl;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * Default implementation of the abstract base class 
 * for a customized ODA data source wizard page. 
 * <br>It provides a generic properties wizard page
 * to allow an user to specify values for the data source connection
 * properties defined by an ODA extension in 
 * its <i>dataSource.properties</i> element of the 
 * <i>org.eclipse.datatools.connectivity.oda.dataSource</i> 
 * extension point.
 */
public class DefaultDataSourceWizardPage extends DataSourceWizardPage
{
    private DefaultDataSourcePageHelper m_pageHelper = null;
    private Properties m_dataSourceProps = null;

    public DefaultDataSourceWizardPage( String pageName )
    {
        super( pageName );
        setMessage( DefaultDataSourcePageHelper.DEFAULT_MESSAGE );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPageCustomControl( Composite parent )
    {
        if ( m_pageHelper == null )
            m_pageHelper = createDataSourcePageHelper( );  
        
        try
        {
            m_pageHelper.createCustomControl( parent );
        }
        catch( OdaException e )
        {
            e.printStackTrace();
        }
        m_pageHelper.initCustomControl( m_dataSourceProps );   // in case init was called before create 
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceWizardPage#initPageCustomControl(java.util.Properties)
     */
    public void setInitialProperties( Properties dataSourceProps )
    {
        m_dataSourceProps = dataSourceProps;
        if( m_pageHelper == null )
            return;     // ignore, wait till createPageCustomControl to initialize
        m_pageHelper.initCustomControl( m_dataSourceProps );        
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage#refresh()
     */
    public void refresh()
    {
        // enable/disable all controls on page in respect of the editable session state
        enableAllControls( getControl(), isSessionEditable() );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceWizardPage#collectCustomProperties()
     */
    public Properties collectCustomProperties()
    {
        if( m_pageHelper != null ) 
            return m_pageHelper.collectCustomProperties( m_dataSourceProps );

        return ( m_dataSourceProps != null ) ?
                    m_dataSourceProps : new Properties();
    }
    
    /**
     * Instantiates the page helper that provides core implementation
     * of this wizard page.
     * @return
     */
    protected DefaultDataSourcePageHelper createDataSourcePageHelper( )
    {
        return new DefaultDataSourcePageHelper( this );
    }

    /**
     * Returns the page helper that provides core implementation
     * for this wizard page.
     * @return 
     */
    protected DefaultDataSourcePageHelper getPageHelper()
    {
        return m_pageHelper;
    }
    
}
