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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.SessionStatus;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DesignerLogger;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase.ProfileReferenceBase;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.AdaptableDataSourceProfile;
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
    private IUpdateDesignTask m_updateDesignTask;

    // logging variable
    private static final String sm_className = ProfileSelectionEditorPage.class.getName();

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
     * Check whether the page has an invalid state in its content.
     * This is separate from the {@link #isValid()} state of the page.
     * One may proceed with saving this state, which will lead to automatic removal of the invalid content. 
     * @return  true if this page has a valid content; false otherwise
     */
    public boolean hasValidContent()
    {    
        AdaptableDataSourceProfile adaptableProfile = getProfileElement();
        if( adaptableProfile == null )
            return true;     // nothing to check
        
        // Check if the profile referenced by the editing data source design, if any, is not found,
        // or if the referenced profile store file does not exist
        if( getEditingDataSource().hasLinkToProfile() )
        {
            if( ! adaptableProfile.hasLinkedProfile() || 
                getEditingDataSource().getLinkedProfileStoreFile() == null )
            {
                return false;
            }
        }
        
        return true;
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
                    true,
                    design.getHostResourceIdentifiers() );       
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
        
        // super might not haved called collectDataSourceDesign if a session response 
        // already exists from another editor page; force the call to
        // update response's dataSourceDesign with the latest linked profile definition
        if( responseSession.getResponse().getSessionStatus() == SessionStatus.OK_LITERAL )
            collectDataSourceDesign( 
                responseSession.getResponseDataSourceDesign() ); 

        return responseSession;
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#collectDataSourceDesign(org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     */
    protected DataSourceDesign collectDataSourceDesign( DataSourceDesign design )
    {
        if( m_pageHelper == null )
            return design;  // page control is not created yet, no changes to design
        
        ProfileReferenceBase profileRef = collectEditedProfileRef();       
        if( profileRef != null )
        {
            // no delegation is specified, perform default update task
            if( m_updateDesignTask == null ) 
            {
                design = super.collectDataSourceDesign( design );
            }
            else    // the update task is delegated, use it to perform collectDataSourceDesign
            {
                try
                {
                    design = m_updateDesignTask.collectDataSourceDesign( design, this, 
                                        profileRef.getProfileInstance() );
                }
                catch( OdaException ex )
                {
                    // log warning about exception
                    DesignerLogger logger = DesignerLogger.getInstance();
                    logger.warning( sm_className, "collectDataSourceDesign( DataSourceDesign )",  //$NON-NLS-1$
                            "Caught exception thrown by delegated collectDataSourceDesign task.", ex ); //$NON-NLS-1$
                }
            }
        }
        
        // adds attributes of linked profile, if specified, to the data source design; 
        // ignores any data source design name change
        if( profileRef != null && profileRef.maintainExternalLink() )
        {
            design.setLinkedProfileName( profileRef.getName() );
            design.setLinkedProfileStoreFilePath( profileRef.getStorageFilePathPropertyValue() );
        }
        else
        {
            design.setLinkedProfileName( null );
            design.setLinkedProfileStoreFile( null );
        }
        return design; 
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage#collectCustomProperties(java.util.Properties)
     */
    public Properties collectCustomProperties( Properties candidateProps )
    {
        ProfileReferenceBase profileRef = collectEditedProfileRef();
        if( profileRef == null )
            return candidateProps;  // has no changes
        
        // return latest selected profile's properties, 
        // regardless of whether an external link is maintained;
        // the external link will be used later to determine whether to
        // apply these profile properties
        Properties selectedProfileProps =
            profileRef.getProfileInstance().getBaseProperties();
        return selectedProfileProps;
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
    
    public boolean requiresExternalProfileLink()
    {
        if( m_pageHelper == null )
            return false;
        return m_pageHelper.requiresExternalProfileLink();
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSourceEditorPageCore#getHostResourceIdentifiers()
     */
    protected ResourceIdentifiers getHostResourceIdentifiers()
    {
        // exposes visibility to the page helper
        return super.getHostResourceIdentifiers();        
    }     
    
    /**
     * Assigns the delegated task to perform collectDataSourceDesign and update the data source design.
     * @param task	the delegated task; may be null to unset the delegation
     * @since DTP 1.6
     */
    public void delegatesTask( IUpdateDesignTask task )
    {
        m_updateDesignTask = task;
    }
    
    /**
     * Delegation of the page's task to update a data source design.
     * @since DTP 1.6
     */
    public interface IUpdateDesignTask
    {
        /**
         * The delegated operation to update the specified data source design with 
         * the selected connection profile's design properties.
         * @param design                the data source design to update from
         * @param delegator             this ProfileSelectionEditorPage that delegates the task
         * @param selectedConnProfile   the currently selected connection profile instance
         * @return  the updated data source design
         * @throws OdaException
         */
        public DataSourceDesign collectDataSourceDesign( DataSourceDesign design, 
                                        final ProfileSelectionEditorPage delegator,
                                        final IConnectionProfile selectedConnProfile )
            throws OdaException;

    }
    
}
