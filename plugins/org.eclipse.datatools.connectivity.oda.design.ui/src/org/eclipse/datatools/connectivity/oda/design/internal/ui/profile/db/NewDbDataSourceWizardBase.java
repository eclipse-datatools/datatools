/*
 *************************************************************************
 * Copyright (c) 2007, 2010 Actuate Corporation.
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
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.NewDataSourceWizardBase#isValid(java.lang.String, org.eclipse.datatools.connectivity.oda.profile.internal.OdaConnectionProfile)
     */
    public boolean isValid( String odaDataSourceId, 
                              OdaConnectionProfile odaProfile )
    {
        if( ! getOdaDataSourceId().equals( odaDataSourceId ) )
            return false;
        
        if( getPageCount() == 0 )   // no wizard page is setup yet, 
            return true;            // still open to any type of handling 
        
        // if creating a new design from scratch, wizard must be setup to select a db profile type
        if( odaProfile == null )
            return ( m_dbSelectionPage != null );
        
        // creating from a profile reference, expects existing page to be a profile wizard page
        if( m_dbProfileWizPage == null )
            return false;
        
        // check if existing db profile wizard page is setup for the specified db profile type
        String dbProviderId = DbProfileUtil.getDbProviderIdFromProfileProperties( odaProfile );
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
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.NewDataSourceWizardBase#setDataSourceDesignProperties(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign, java.util.Properties)
     * 
     * Overrides base class behavior to assign relevant custom profile properties
     * as private properties in the specified data source design.  
     * This is intended for use by an ODA extension that serves as a wrapper of 
     * other connection profiles, and has no pre-defined property definition 
     * in its manifest.
     */
    protected void setDataSourceDesignProperties( DataSourceDesign newDesign,
            Properties customPropertyValues ) 
        throws OdaException
    {
        // if using external profile reference, do not import profile properties in the design
        if( isCreatingFromProfile() )
            DbProfileUtil.updateDataSourceDesignExternalProfileProvider( newDesign, customPropertyValues );
        else
            super.setDataSourceDesignProperties( newDesign, customPropertyValues );
    }
    
}
