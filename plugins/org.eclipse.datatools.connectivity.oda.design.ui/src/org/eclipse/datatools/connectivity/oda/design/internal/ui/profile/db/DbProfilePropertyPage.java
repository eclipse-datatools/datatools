/*
 *************************************************************************
 * Copyright (c) 2007, 2009 Actuate Corporation.
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
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.ProfileSelectionEditorPage;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.ProfileSelectionEditorPage.IUpdateDesignTask;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.pages.impl.DefaultDataSourcePropertyPage;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaProfileFactory;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.swt.widgets.Composite;

/**
 * The base class implementation of an ODA data source editor page that wraps 
 * the driver-contributed property page of a Database connection profile type.
 * @since DTP 1.6
 */
public class DbProfilePropertyPage extends DataSourceEditorPage
{
    private DbProfilePageWrapper m_pageHelper = null;
    private IUpdateDesignTask m_profileUpdateDesignTask;

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
                // specifies isReadOnly option based on the editable session state
                m_pageHelper.createCustomControl( parent, ! isSessionEditable() );
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
        
        IConnectionProfile customDbPropPageProfile = pageProfile;   // use the same profile by default
        if( pageProfile instanceof OdaConnectionProfile )
        {
            if( ! ((OdaConnectionProfile)pageProfile).hasWrappedProfile() )
                customDbPropPageProfile = createTransientProfile( pageProfile.getBaseProperties() );
        }
        if( customDbPropPageProfile == null )
            return null;

        OdaConnectionProfile customPageOdaDbProfile = ( customDbPropPageProfile instanceof OdaConnectionProfile ) ?
                                (OdaConnectionProfile) customDbPropPageProfile :
                                new OdaConnectionProfile( customDbPropPageProfile );

        // create the db profile's custom property page contribution for its wrapper
        ProfilePropertyPage customDbPropPage = 
            DbProfileUtil.createDbPropertyPage( customPageOdaDbProfile, getOdaDataSourceId() );       
        if( customDbPropPage == null )
            return null;
        
        // wraps the db profile property page in an ODA data source page        
        return new DbProfilePageWrapper( this, customDbPropPage );
    }

    /**
     * Create a transient db profile with the specified properties.
     * @param connProperties
     * @return
     * @since 3.2.2 (DTP 1.7.2)
     */
    protected IConnectionProfile createTransientProfile( Properties connProperties )
    {
        // the data source being edited may have no reference to an external profile store,
        // and contains local profile properties only;
        // try create a transient profile from the data source connection properties
        try
        {
            return OdaProfileFactory.createTransientProfile( connProperties );
        }
        catch( OdaException ex )
        {
            // ignore
        }
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#cleanup()
     */
    @Override
    protected void cleanup()
    {
        super.cleanup();
        if( m_pageHelper == null )
            return;     // nothing to clean up
        // cleanup transient profile, if exists
        m_pageHelper.cleanup();
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
        // noop;
        // a db profile page does not allow a caller to re-initialize its controls
        // with another set of profile properties; and
        // the editable session state is handled when custom control is first created
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.profile.wizards.DataSourceEditorPage#collectCustomProperties(java.util.Properties)
     */
    public Properties collectCustomProperties( Properties profileProps )
    {
        if( m_pageHelper == null )
            return profileProps;

        // gets a copy of the profile's base properties
        Properties dbProfileProps =
            m_pageHelper.collectCustomProperties( isSessionEditable() );
        
        // reset/add the db profile provider id to the base properties 
        // collected from a connection profile instance
        DbProfileUtil.setDbProviderIdInProperties( dbProfileProps, getDbProfileProviderId() );
        
        return dbProfileProps;
    }
    
    String getDbProfileProviderId()
    {
        if( m_pageHelper == null ) 
            return null;
        return m_pageHelper.getDbProfileProviderId();
    }
    
    /**
     * Indicates whether this property page is editing an externalized 
     * connection profile instance.
     * @return  true if page refers to an external connection profile instance; false 
     *          if editing a local data source design with profile properties
     * @since 3.2.2 (DTP 1.7.2)
     */
    protected boolean isEditingExternalProfile()
    {
        IConnectionProfile pageProfile = getConnectionProfile();
        if( pageProfile instanceof OdaConnectionProfile )
            return ((OdaConnectionProfile)pageProfile).hasWrappedProfile();
        return false;
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceEditorPageCore#setDataSourceDesignProperties(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign, java.util.Properties)
     *
     * Overrides base class behavior to assign relevant custom profile properties
     * as private properties in the specified data source design.  
     * This is for use by an ODA extension that serves as a wrapper of 
     * other connection profiles, and has no pre-defined property definition 
     * in its manifest.
     */
    protected void setDataSourceDesignProperties( DataSourceDesign design,
            Properties propertyValuePairs ) 
        throws OdaException
    {
        // if editing external profile reference, do not import profile properties into the design
        if( isEditingExternalProfile() )
            DbProfileUtil.updateDataSourceDesignExternalProfileProvider( design, propertyValuePairs );
        else
            super.setDataSourceDesignProperties( design, propertyValuePairs );
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceEditorPageCore#initProfileSelectionEditSession(org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.ProfileSelectionEditorPage)
     */
    public void initProfileSelectionEditSession( ProfileSelectionEditorPage profileSelectionPage )
    {
        if( profileSelectionPage == null )
            return;     // nothing to initialize

        // override the profile page update design task
        profileSelectionPage.delegatesTask( getProfileSelectionUpdateDesignTask() );
    }
    
    private ProfileSelectionEditorPage.IUpdateDesignTask getProfileSelectionUpdateDesignTask()
    {
        if( m_profileUpdateDesignTask == null )
        {
            m_profileUpdateDesignTask = 
                new ProfileSelectionEditorPage.IUpdateDesignTask()
                {
                    /* (non-Javadoc)
                     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.ProfileSelectionEditorPage.IUpdateDesignTask#collectDataSourceDesign(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign, org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.ProfileSelectionEditorPage, org.eclipse.datatools.connectivity.IConnectionProfile)
                     */
                    public DataSourceDesign collectDataSourceDesign( DataSourceDesign design, 
                                                final ProfileSelectionEditorPage delegator,
                                                final IConnectionProfile selectedConnProfile )
                        throws OdaException
                    {
                        if( selectedConnProfile == null )
                            return design;  // no info to collect for update
                        
                        Properties customProps = collectCustomProperties( selectedConnProfile );
                        
                        setDataSourceDesignProperties( design, customProps );
  
                        return design;
                    }
                    
                    private Properties collectCustomProperties( 
                                            IConnectionProfile selectedConnProfile )
                    {
                        // first get a copy of the selected profile's properties
                        Properties customProps = selectedConnProfile.getBaseProperties();

                        // add the db profile provider id to the base properties 
                        // collected from the selected connection profile instance
                        String dbProfileProviderId = getDbProfileProviderId( selectedConnProfile );
                        DbProfileUtil.setDbProviderIdInProperties( customProps, dbProfileProviderId );

                        return customProps;
                    }
                    
                    private String getDbProfileProviderId( IConnectionProfile connProfile )
                    {
                        if( connProfile instanceof OdaConnectionProfile )
                            return ((OdaConnectionProfile) connProfile ).getDirectProviderId();
                        return null;
                    }

                };
        }
        return m_profileUpdateDesignTask;
    }
    
}
