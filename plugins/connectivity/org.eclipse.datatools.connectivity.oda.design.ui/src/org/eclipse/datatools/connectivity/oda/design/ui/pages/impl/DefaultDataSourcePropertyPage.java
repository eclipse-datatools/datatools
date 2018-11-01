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

import org.eclipse.datatools.connectivity.internal.ui.dialogs.ExceptionHandler;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.swt.widgets.Composite;

/**
 * Default implementation of the abstract base class 
 * for a customized ODA data source editor page. 
 * <br>It provides a generic properties editor page
 * to allow an user to edit values for the data source connection
 * properties defined by an ODA extension in 
 * its <i>dataSource.properties</i> element of the 
 * <i>org.eclipse.datatools.connectivity.oda.dataSource</i> 
 * extension point.
 */
public class DefaultDataSourcePropertyPage extends DataSourceEditorPage
{
    private DefaultDataSourcePageHelper m_pageHelper = null;

    public DefaultDataSourcePropertyPage()
    {
        super();
        setMessage( DefaultDataSourcePageHelper.DEFAULT_MESSAGE );
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

    DataSourceDesign getCurrentDataSource()
    {
    	 return getEditingDataSource();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceEditorPage#createAndInitCustomControl(org.eclipse.swt.widgets.Composite, java.util.Properties)
     */
    protected void createAndInitCustomControl( Composite parent, Properties profileProps )
    {
        if( m_pageHelper == null )
            m_pageHelper = createDataSourcePageHelper( );

        try
        {
            m_pageHelper.createCustomControl( parent );
        }
        catch( OdaException e )
        {
        	ExceptionHandler.showException( getControl().getShell(), 
            	Messages.ui_errorLabel, 
            	Messages.ui_errorCreatingCustomCtrls, 
            	e );
        	
        	return;
        }
        
        m_pageHelper.initCustomControl( profileProps );
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
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#refresh(java.util.Properties)
     */
    protected void refresh( Properties customConnectionProps  )
    {
        if( m_pageHelper != null )
            m_pageHelper.initCustomControl( customConnectionProps );
        
        // enable/disable all controls on page in respect of the editable session state
        enableAllControls( getControl(), isSessionEditable() );
    }

}
