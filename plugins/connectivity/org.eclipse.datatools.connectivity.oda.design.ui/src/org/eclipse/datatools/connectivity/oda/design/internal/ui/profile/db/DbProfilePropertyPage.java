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

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.AdaptableDataSourceProfile;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.ProfileSelectionEditorPage;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.ProfileSelectionEditorPage.IUpdateDesignTask;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.pages.impl.DefaultDataSourcePropertyPage;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaProfileFactory;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
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
    private boolean m_canEditProps = true;

    public DbProfilePropertyPage()
    {
        super();
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

        // if no wrapped db profile and no reference to external profile, 
        // try create a transient db profile instance if the minimum required property is known
        if( ! hasLinkedProfileInPageElement() &&
            ! getEditingDataSource().hasLinkToProfile() )
        {
            Properties pageProps = pageProfile.getBaseProperties();
            if( hasMinimumProperties( pageProps ) )
                customDbPropPageProfile = createTransientProfile( pageProps );
            else    // missing required properties to edit 
                m_canEditProps = false;
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
        
        String pageMsg = m_canEditProps && isSessionEditable() ? 
                Messages.dbProfilePage_editPageMessage : DbProfilePageWrapper.DEFAULT_MESSAGE;
        setMessage( pageMsg );
        
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

    /*
     * Creates a default property page with a warning/error message.
     */
    private DataSourceEditorPage createDefaultPropertyPage( Properties profileProps )
    {
        DefaultDataSourcePropertyPage defaultPropPage = new DefaultDataSourcePropertyPage();
        String errorMessage = isInOdaDesignSession() ? 
                ( m_canEditProps ? 
                        Messages.dbProfilePage_noCustomPage : 
                        Messages.dbProfilePage_invalidDataSource ) :
                Messages.dbProfilePage_notInDesignSession;

        /*
         * Update page's initialized DataSourceDesign with specified profileProps, 
         * so that the page can create controls with the profileProps values.
         */
        OdaDesignSession requestSession = getDesignSession();
        if( requestSession.getRequestDataSourceDesign() != null &&
            ! requestSession.getRequestDataSourceDesign().hasLinkToProfile() )  // editing local properties
        {
            // makes a copy of the request session to update the data source design w/ local profileProps
            requestSession = (OdaDesignSession) EcoreUtil.copy( requestSession );

            DataSourceDesign localRequestDesign = requestSession.getRequestDataSourceDesign();
            try
            {
                super.setDataSourceDesignProperties( localRequestDesign, profileProps );
            }
            catch( OdaException ex )
            {
                errorMessage += "\n"; //$NON-NLS-1$
                errorMessage += ex.toString();
            }
        }
        
        try
        {
            defaultPropPage.initEditSession( requestSession );
        }
        catch( OdaException ex )
        {
            errorMessage += "\n"; //$NON-NLS-1$
            errorMessage += ex.toString();
        }

        int messageType = m_canEditProps ? IMessageProvider.WARNING : IMessageProvider.ERROR;
        setMessage( errorMessage, messageType );       
        
        // missing required properties to test connection
        if( ! m_canEditProps )
            defaultPropPage.setPingButtonEnabled( false );
        
        return defaultPropPage;
    }
    
    /**
     * Returns the page helper that provides core implementation
     * for this wizard page.
     */
    protected DbProfilePageWrapper getPageHelper()
    {
        return m_pageHelper;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#getErrorMessage()
     */
    @Override
    public String getErrorMessage()
    {
        return m_pageHelper != null ? m_pageHelper.getErrorMessage() : super.getErrorMessage();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#refresh(java.util.Properties)
     */
    protected void refresh( Properties customConnectionProps  )
    {
        // no-op;
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
    
    private boolean hasLinkedProfileInPageElement()
    {
        AdaptableDataSourceProfile pageProfile = getProfileElement();
        return ( pageProfile != null && pageProfile.hasLinkedProfile() );
    }
    
    private static boolean hasMinimumProperties( Properties props )
    {
        // check that the properties contain at least the driver definition id
        return( props.getProperty( ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID ) != null );
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
        return hasLinkedProfileInPageElement();
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
        // if external profile reference is required, do not import profile properties into the design
        if( getUpdateDesignTask().requiresExternalProfile() )
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
            m_profileUpdateDesignTask = new UpdateDesignTask();
        }
        return m_profileUpdateDesignTask;
    }
    
    private UpdateDesignTask getUpdateDesignTask()
    {
        return (UpdateDesignTask) getProfileSelectionUpdateDesignTask();
    }
    
    class UpdateDesignTask implements ProfileSelectionEditorPage.IUpdateDesignTask
    {
        private ProfileSelectionEditorPage m_delegator;
        
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

            m_delegator = delegator;
            Properties customProps = collectCustomProperties( selectedConnProfile );
            
            setDataSourceDesignProperties( design, customProps );
            m_delegator = null;     // done with delegated collection of properties; reset

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
        
        private boolean requiresExternalProfile()
        {
            if( m_delegator == null )
                return false;    // not being delegated to collect selected profile's data source properties
            return m_delegator.requiresExternalProfileLink();
        }
    }
    
}
