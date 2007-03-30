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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.SessionStatus;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.ProfileReferenceBase;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.swt.widgets.Composite;

/**
 *  Internal ODA connection profile editor page for users to update selection 
 *  of a connection profile used in a data source design.
 *  Edits are limited to selection of profile instances from the same 
 *  ODA data source type specified in the existing data source design.
 *  No edits to the data source design name are allowed.
 *  For use by internal packages only.
 * @since   3.0.4
 */
public class ProfileSelectionEditorPage extends DataSourceEditorPage
{
    private ProfileSelection m_initProfileSelection;
    private ProfileSelectionPageHelper m_pageHelper;

    public ProfileSelectionEditorPage()
    {
        setMessage( Messages.profilePage_pageTitle );
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#createAndInitCustomControl(org.eclipse.swt.widgets.Composite, java.util.Properties)
     */
    protected void createAndInitCustomControl( Composite parent,
            Properties profileProps )
    {
        if( m_pageHelper == null )
            m_pageHelper = new ProfileSelectionPageHelper( this );
        
        m_pageHelper.createControl( parent );
        m_pageHelper.initControl( getInitializeProfileSelection() ); 

        setPingButtonVisible( false );  

        if( ! isEditableSessionRequested() )
            getControl().setEnabled( false );
    }
    
    /**
     * Returns the ProfileSelection object that represents the initialized
     * value of the data source design.  This method is expected to get called
     * prior to any user edits.
     * @return
     */
    private ProfileSelection getInitializeProfileSelection()
    {
        if( m_initProfileSelection == null )
        {
            DataSourceDesign design = getEditingDataSource();
            m_initProfileSelection =
                new ProfileSelection(
                        design.getOdaExtensionDataSourceId(),
                        design.getName(),
                        createProfileRef( design ) );
        }
        return m_initProfileSelection;
    }
    
    /**
     * Creates a ProfileReferenceBase object based on the
     * linked connection profile definition, if any, in the specified design.
     */
    private ProfileReferenceBase createProfileRef( DataSourceDesign design )
    {
        String profileInstanceName = design.getLinkedProfileName();
        if( profileInstanceName == null || profileInstanceName.length() == 0 )
            return null;    // no linked profile info
        
        return new ProfileReferenceBase( profileInstanceName,
                    design.getLinkedProfileStoreFilePath(),
                    true );       
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#getTitle()
     */
    public String getTitle()
    {
        return Messages.profilePage_pageLabel;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceEditorPageCore#getEditSessionResponse()
     */
    public OdaDesignSession getEditSessionResponse() throws OdaException
    {
        OdaDesignSession responseSession = super.getEditSessionResponse();
        assert( responseSession.getResponse() != null );
        
        // update response's dataSourceDesign with linked profile definition
        if( responseSession.getResponse().getSessionStatus() == SessionStatus.OK_LITERAL )
            collectDataSourceDesign( 
                responseSession.getResponseDataSourceDesign() ); 

        return responseSession;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#collectCustomProperties(java.util.Properties)
     */
    public Properties collectCustomProperties( Properties candidateProps )
    {
        ProfileReferenceBase profileRef = collectEditedProfileRef();
        if( profileRef == null )
            return candidateProps;  // has no changes
        
        // updates candidates with latest selected profile's properties,
        // regardless of whether an external link is maintained;
        // the external link will be used later to determine whether to
        // apply these profile properties
        Properties selectedProfileProps =
            profileRef.getProfileInstance().getBaseProperties();
        if( selectedProfileProps != null )
            candidateProps.putAll( selectedProfileProps );
        return candidateProps;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#collectDataSourceDesign(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     */
    protected DataSourceDesign collectDataSourceDesign( DataSourceDesign design )
    {
        // adds attributes of linked profile, if specified,
        // to the data source design; 
        // ignores any data source design name change
        ProfileReferenceBase profileRef = collectEditedProfileRef();
        
        if( profileRef != null && profileRef.maintainExternalLink() )
        {
            design.setLinkedProfileName( profileRef.getName() );
            design.setLinkedProfileStoreFile( profileRef.getStorageFile() );
        }
        else
        {
            design.setLinkedProfileName( null );
            design.setLinkedProfileStoreFile( null );
        }
        return design; 
    }

    /**
     * Edits only accept changes in the selection of a profile instance.
     * This is thus only interested in collecting the profile reference info.
     */
    private ProfileReferenceBase collectEditedProfileRef()
    {
        if( m_pageHelper == null )
            return null;

        ProfileSelection selectedProfile = m_pageHelper.collectProfileSelection();
        if( selectedProfile == null )
            return null;        
        return selectedProfile.getProfileRef();        
    }
    
}
