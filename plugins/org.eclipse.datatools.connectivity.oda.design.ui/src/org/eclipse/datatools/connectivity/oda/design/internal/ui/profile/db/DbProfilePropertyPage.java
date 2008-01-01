/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.pages.impl.DefaultDataSourcePropertyPage;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.swt.widgets.Composite;

/**
 * The base class implementation of an ODA data source editor page that wraps 
 * the driver-contributed property page of a Database connection profile type.
 */
public class DbProfilePropertyPage extends DataSourceEditorPage
{
    private DbProfilePageWrapper m_pageHelper = null;

    public DbProfilePropertyPage()
    {
        super();
        setMessage( DbProfilePageWrapper.DEFAULT_MESSAGE );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceEditorPage#createAndInitCustomControl(org.eclipse.swt.widgets.Composite, java.util.Properties)
     */
    protected void createAndInitCustomControl( Composite parent, Properties profileProps )
    {
        if( m_pageHelper == null )
            m_pageHelper = createDbProfilePageWrapper();

        if( m_pageHelper != null )
        {
            try
            {
                m_pageHelper.createCustomControl( parent );
            }
            catch( OdaException e )
            {
                e.printStackTrace();
            }
            
            m_pageHelper.initCustomControl( profileProps );
        }
        else  // could not create a db profile page
        {
            // use the default oda property page instead
            DataSourceEditorPage odaDefaultPage = 
                createDefaultPropertyPage( profileProps );
            odaDefaultPage.createControl( parent );
            m_pageHelper = new DbProfilePageWrapper( this, odaDefaultPage );
        }
        
        // hide this page's button when it is created; the wrapped property page's button 
        // is used instead
        setPingButtonVisible( false );              
    }
    
    /**
     * Instantiates the page helper that provides core implementation
     * of this wizard page.
     * @return
     */
    protected DbProfilePageWrapper createDbProfilePageWrapper()
    {
        IConnectionProfile pageProfile = getConnectionProfile();
        OdaConnectionProfile odaDbProfile = ( pageProfile instanceof OdaConnectionProfile ) ?
                                (OdaConnectionProfile) pageProfile :
                                new OdaConnectionProfile( pageProfile );

        // create the db profile's custom property page contribution for its wrapper
        ProfilePropertyPage customDbPropPage = 
            DbProfileUtil.createDbPropertyPage( odaDbProfile, getOdaDataSourceId() );
        
        if( customDbPropPage == null )
            return null;
        
        // wraps the db profile property page in an ODA data source page        
        return new DbProfilePageWrapper( this, customDbPropPage );
    }

    private DataSourceEditorPage createDefaultPropertyPage( Properties dbProfileProps )
    {
        /* TODO - extend DefaultDataSourcePropertyPage, and 
         * update page's initialized DataSourceDesign with dbProfileProps, 
         * so that the page can create controls for the dbProfileProps.
         * It needs to however mask property value like password.
         * For now, create the default property page with an error message.
         */
        
        String errorMessage = null;
        if( ! isInOdaDesignSession() )
        {
            errorMessage = Messages.dbProfilePage_notInDesignSession;
        }
        else
            errorMessage = Messages.dbProfilePage_noCustomPage;

        DefaultDataSourcePropertyPage odaDefaultPage = new DefaultDataSourcePropertyPage();

        try
        {
            odaDefaultPage.initEditSession( getDesignSession() );
        }
        catch( OdaException ex )
        {
            errorMessage += "\n"; //$NON-NLS-1$
            errorMessage += ex.toString();
        }
        setMessage( errorMessage );       
        
        return odaDefaultPage;
    }
    
    /**
     * Returns the page helper that provides core implementation
     * for this wizard page.
     */
    protected DbProfilePageWrapper getPageHelper()
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
        
        // enable/disable all controls on page based on the editable session state
        enableAllControls( getControl(), isSessionEditable() );
        
        // re-enable the custom db property page's ping button, even if 
        // its properties are not editable
        if( m_pageHelper != null && ! isSessionEditable() && 
            ! m_pageHelper.hasDefaultPropertyPage() )
            m_pageHelper.setWrappedPingButtonEnabled( true );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceEditorPage#collectCustomProperties(java.util.Properties)
     */
    public Properties collectCustomProperties( Properties profileProps )
    {
        if( m_pageHelper == null )
            return profileProps;

        Properties dbProfileProps =
            m_pageHelper.collectCustomProperties( profileProps );
        
        // reset/add the db profile provider id to the base properties 
        // collected from a connection profile instance
        DbProfileUtil.setDbProviderIdInProperties( dbProfileProps, 
                                getDbProfileProviderId() );
        return dbProfileProps;
    }
    
    String getDbProfileProviderId()
    {
        if( m_pageHelper == null ) 
            return null;
        return m_pageHelper.getDbProfileProviderId();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#collectDataSourceDesign(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     * 
     * Overrides base class behavior to assign relevant custom profile properties
     * as private properties in the specified data source design.  
     * This is intended for use by an ODA extension that serves as a wrapper of 
     * other connection profiles, and has no pre-defined property definition 
     * in its manifest.
     */
    protected DataSourceDesign collectDataSourceDesign( DataSourceDesign design )
    {
        Properties dbPropertyValues = collectProperties();
        
        // saves relevant updated db properties in the design
        try
        {
            DbProfileUtil.updateDataSourceDesignManifestProperties( design, dbPropertyValues );
        }
        catch( OdaException ex )
        {
            // TODO error handling
            ex.printStackTrace();
        }
        
        return design;
    }

}
