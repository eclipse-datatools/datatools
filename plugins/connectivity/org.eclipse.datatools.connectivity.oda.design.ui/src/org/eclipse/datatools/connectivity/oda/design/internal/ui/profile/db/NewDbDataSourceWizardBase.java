/*
 *************************************************************************
 * Copyright (c) 2007, 2013 Actuate Corporation.
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
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.ProfileReferenceBase;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.NewDataSourceWizard;
import org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.datatools.connectivity.ui.wizards.ProfilePropertyPage;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * The base class implementation of an ODA data source wizard that manages 
 * the driver-contributed wizard page of a Database connection profile type.
 */
public class NewDbDataSourceWizardBase extends NewDataSourceWizard
{
    private DbTypesSelectionPage m_dbSelectionPage;
    private DbProfileWizardPage m_dbProfileWizPage;
    
    protected NewDbDataSourceWizardBase( String odaDataSourceId )
        throws OdaException
    {
        super( odaDataSourceId );
    }

    protected NewDbDataSourceWizardBase()
    {
        super();
    }
    
    @Override
    public boolean isValid( String odaDataSourceId, ProfileReferenceBase profileRef )
    {
        if( ! getOdaDataSourceId().equals( odaDataSourceId ) )
            return false;
        
        if( getPageCount() == 0 )   // no wizard page is setup yet, 
            return true;            // still open to any type of handling 
        
        // if not creating from a profile, wizard must be setup to select a db profile type
        IConnectionProfile odaProfile = ( profileRef == null || ! profileRef.maintainExternalLink() ) ? 
                                        null : 
                                        profileRef.getProfileInstance();
        if( odaProfile == null )
            return ( m_dbSelectionPage != null );
        
        // creating from a profile reference, expects existing page to be a profile wizard page
        if( m_dbProfileWizPage == null )
            return false;

        if( ! (odaProfile instanceof OdaConnectionProfile) )
            return false;

        // check if existing db profile wizard page is setup for the specified db profile type
        String dbProviderId = DbProfileUtil.getDbProviderIdFromProfileProperties( (OdaConnectionProfile)odaProfile );
        if( dbProviderId == null )
            dbProviderId = odaProfile.getProviderId();
        if( ! dbProviderId.equals( m_dbProfileWizPage.getDbProfileProviderId() ))
            return false;   // not the same db profile provider
        
        // does have a matching page contribution that handles the same db profile type;
        // but since a db profile page does not allow a caller to re-initialize its controls
        // with another set of profile properties,
        // can only reuse if wizard is either not currently editing a profile, or 
        // editing the same profile instance
        if( ! hasLinkToProfile() )
            return true;
        
        return ( odaProfile.equals( getLinkedProfile() ));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.NewDataSourceWizardBase#addPages()
     */
    public void addPages()
    {
        super.addPages();
        
        // always hide the profile name page that preceeds the DbTypesSelectionPage
        setSkipProfileNamePage( true );
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.NewDataSourceWizardBase#addCustomPages()
     */
    public void addCustomPages()
    {
        // tries to create a new data source from a linked db profile instance
        if( hasLinkToProfile() )
        {        
            IConnectionProfile selectedProfile = getLinkedProfile();
            if( selectedProfile instanceof OdaConnectionProfile )
            {
                m_dbProfileWizPage = createDbProfileWizardPage( (OdaConnectionProfile) selectedProfile );
                if( m_dbProfileWizPage != null )
                {
                    addPage( m_dbProfileWizPage );          
                    return;     // done
                }
            }

            // not able to use the custom profile page for the referenecd profile;
            // falls through to ask user to select a db profile type
            // TODO raise user warning

        }
        
        // no db profile instance to create from,
        // ask user to select a db profile type instead
        addPage( getDbProfileTypesSelectionPage() );
    }
    
    private DbTypesSelectionPage getDbProfileTypesSelectionPage()
    {
        if( m_dbSelectionPage == null )
            m_dbSelectionPage = new DbTypesSelectionPage();
        return m_dbSelectionPage;        
    }

    private DbProfileWizardPage getDbProfileWizardPage()
    {
        return m_dbProfileWizPage;
    }
    
    protected boolean isCreatingFromProfile()
    {
        return hasLinkToProfile() && getDbProfileWizardPage() != null;
    }
    
    private DbProfileWizardPage createDbProfileWizardPage( OdaConnectionProfile odaDbProfile )
    {
        assert( odaDbProfile != null );

        // create the db profile's custom property page contribution and its wrapper
        ProfilePropertyPage customDbPropPage = 
            DbProfileUtil.createDbPropertyPage( odaDbProfile, getOdaDataSourceId() );
            
        if( customDbPropPage == null )
            return null; 

        // wraps the db profile property page in an ODA data source wizard page        
        return new DbProfileWizardPage( customDbPropPage.getTitle(), customDbPropPage );
    }
        
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.NewDataSourceWizardBase#getCustomStartingPage()
     */
    public IWizardPage getCustomStartingPage()
    {
        IWizardPage customPage = getCustomWizardPage();
        if( customPage != null )
            return customPage;

        // no db profile specific page, 
        // use the page that asks user to select a db profile type instead
        return getDbProfileTypesSelectionPage();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.NewDataSourceWizardBase#getCustomWizardPage()
     */
    protected DataSourceWizardPage getCustomWizardPage()
    {
        // override; the custom page is not created from an ODA manifest configuration element
        if( isCreatingFromProfile() )
            return getDbProfileWizardPage();
        return null;    // the DbTypesSelectionPage is not a DataSourceWizardPage
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.NewDataSourceWizardBase#collectCustomProperties()
     */
    protected Properties collectCustomProperties()
    {
        Properties dbProfileProps;
        String dbProviderId = null;
        
        if( isCreatingFromProfile() )
        {
            dbProfileProps = getDbProfileWizardPage().collectCustomProperties();
            dbProviderId = getDbProfileWizardPage().getDbProfileProviderId();
        }
        else    // not creating from an existing db profile instance
        {
            IWizard cpNodeWizard = getDbProfileTypesSelectionPage().getSelectedNodeWizard();
            if( ! (cpNodeWizard instanceof NewConnectionProfileWizard ) )
                return new Properties();
            
            // use the custom db wizard to collect profile properties from its page
            NewConnectionProfileWizard castedCpNodeWizard = 
                                (NewConnectionProfileWizard) cpNodeWizard;
            dbProfileProps = castedCpNodeWizard.getProfileProperties();
            dbProviderId = castedCpNodeWizard.getProfileProviderID();
        }
        
        // add the db profile provider id to the base properties collected
        // from a connection profile instance
        if( dbProfileProps == null )
            dbProfileProps = new Properties();
        DbProfileUtil.setDbProviderIdInProperties( dbProfileProps, dbProviderId );
        return dbProfileProps;
    }
    
}
