/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.designsession;

import java.io.File;
import java.util.Properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.DesignerUtil;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.OdaProfileUIExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.NewDataSourceWizard;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizardPage;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * Base class implementation of an UI data source design session 
 * for use by an ODA host designer to interact and communicate 
 * with custom ODA UI extensions to create or edit 
 * an extended ODA data source design instance.
 */
public class DataSourceDesignSessionBase
{
    private String m_odaDataSourceId;
    private OdaDesignSession m_designSession;
    private NewDataSourceWizard m_wizard;
    private ProfileReferenceBase m_wizardProfileRef;
    private DataSourceEditorPage m_editorPage;
    
    
    /** Not allowed to instantiate the class directly;
     *  must start a design session using a static start method
     */  
    protected DataSourceDesignSessionBase( String odaDataSourceId )
    {
        assert( odaDataSourceId != null && odaDataSourceId.length() > 0 );
        m_odaDataSourceId = odaDataSourceId;
    }
    
    protected DataSourceDesignSessionBase()
    {
    }
   
    /**
     * Restarts the design session to create a new 
     * data source design instance with the given name
     * for the given ODA data source type, and initializes 
     * with the properties specified in the given profile instance.
     * <br>Restarting a design session on the same 
     * ODA data source type would preserve any
     * user edits made on the session's custom wizard page.
     * @param odaDataSourceId
     * @param newDataSourceName
     * @param profileRef
     * @param request
     * @throws OdaException
     * @see #startNewDesign(String, String, ProfileReferenceBase, DesignSessionRequest)
     */
    protected void restartNewDesign( String odaDataSourceId,
                                String newDataSourceName,
                                ProfileReferenceBase profileRef,
                                DesignSessionRequest request )
        throws OdaException
    {
        // restarting with a different oda data source type,
        // reset the session's context and wizard 
        if( ! m_odaDataSourceId.equals( odaDataSourceId ) )
        {
            m_odaDataSourceId = odaDataSourceId;
            if( m_wizard != null )
                m_wizard.dispose();
            m_wizard = null;
        }

        // initialize the session with given attributes
        initNewDesign( newDataSourceName, profileRef, request );
    }
    
    /**
     * Initializes this design session with given attributes.
     * @param newDataSourceName
     * @param profileRef
     * @param request
     * @throws OdaException
     */
    protected void initNewDesign( String newDataSourceName,
                                ProfileReferenceBase profileRef,
                                DesignSessionRequest request )
        throws OdaException
    {
        NewDataSourceWizard wizard = getExtendedWizard();
        
        // a new data source session has no previous designer state;
        // no need to be concerned with passing designer state to 
        // a new data source wizard
        
        // verifies that the given profile does exist in cache;
        // if wizard was initialize w/ same profileRef,
        // do not change any user edits on wizard page
        Properties profileProps = null;
        String profileName = null;
        String profileDesc = null;
        if( profileRef != null && 
            ! profileRef.equals( m_wizardProfileRef ) )
        {
            profileProps = getProfileProperties( profileRef.getInstanceId() );
            IConnectionProfile profileInstance = OdaProfileExplorer.getInstance().
                                    getProfile( profileRef.getInstanceId() );
            assert( profileInstance != null );
            profileName = profileInstance.getName();
            profileDesc = profileInstance.getDescription();
            
            if( newDataSourceName == null || newDataSourceName.length() == 0 )
                newDataSourceName = profileName;
        }
        
        // initialize wizard with given name and properties, if any
        initWizard( wizard, newDataSourceName, profileDesc, profileProps );
        m_wizardProfileRef = profileRef;

        if( profileRef != null && profileRef.maintainExternalLink() )
            wizard.setLinkedProfile( profileName, profileRef.getStorageFile() );
    }

    protected void initEditDesign( DesignSessionRequest request,
                                    DataSourceEditorPage editorPage )
        throws OdaException
    {
        if( editorPage == null )
            editorPage = getExtendedEditorPage();
        
        // create a top-level OdaDesignSession with given request
        OdaDesignSession odaDesign = 
            DesignFactory.eINSTANCE.createOdaDesignSession();
        odaDesign.setRequest( request );
        
        // Update the editor page's property values with those found
        // in the given request's data source design; 
        // also includes any additional state specified in
        // the design session request
        editorPage.initEditSession( odaDesign );

        if( m_editorPage != editorPage )
        {
            // TODO - log warning if m_editorPage != null
            m_editorPage = editorPage;
        }
        m_designSession = odaDesign;   // hold on till finish editing       
    }

    /**
     * Returns the session request that has started
     * this design session.  May return null if none
     * was used to start this design session.
     * @return  the design session request, which may specify
     *          the original data source design to edit
     */
    protected DesignSessionRequest getRequest()
    {
        if( m_designSession == null )
            return null;
        return m_designSession.getRequest();
    }
    
    /**
     * Performs finish on this design session to
     * create or edit its data source design.  This then 
     * gathers the data source definition collected in custom UI designer,
     * and maps into a session response with a new or updated
     * data source design instance.
     * <br>This method must be called only after the corresponding 
     * wizard or editor has performed finish.
     * <br>Returns a completed design session with a 
     * session response that contains a new or updated 
     * data source design instance, and the designer state. 
     * @return  a completed ODA design with the session response
     *          and the original request, if any.
     */
    protected OdaDesignSession finish() throws OdaException
    {
        OdaDesignSession finishedSession;
        if( m_wizard != null )
            finishedSession = finishNewDataSource();
        else    // in an edit session
            finishedSession = finishEditDataSource();
        
        // successfully finished
        m_designSession = null;     // reset to complete session
        disposePages();
        return finishedSession;
    }
    
    /**
     * Performs cancel on this design session.
     * The design session is then cancelled and contains
     * session response with a user_cancelled state.
     * @return  the completed design session containing a
     *          session response with a user_cancelled state
     */
    protected OdaDesignSession cancel()
    {
        if( m_designSession == null )
        {
            // currently in a session to create a new design instance;
            // create a design session object with an 
            // empty DataAccessDesign in the request
            m_designSession = 
                DesignFactory.eINSTANCE.createRequestDesignSession( null );
        }

        // sets a response with cancel status
        m_designSession.setResponseInCancelledState();

        OdaDesignSession cancelledSession = m_designSession;
        m_designSession = null;     // reset to complete session
        disposePages();
        
        return cancelledSession;
    }

    /**
     * Dispose any current pages of the session.
     * Ensures they would not be re-used if the session restarts.
     */
    private void disposePages()
    {
        if( m_wizard != null )
        {
            m_wizard.dispose();
            m_wizard = null;
        }
        
        if( m_editorPage != null )
        {
            m_editorPage.dispose();
            m_editorPage = null;
        }

        m_wizardProfileRef = null;
    }
        
    /**
     * Returns an ODA wizard for use within this design session
     * to create a new, extended ODA data source design instance.
     * @return  a wizard instance to embed in a wizard dialog
     *          for use within this started design session
     * @throws OdaException
     */
    protected IWizard getNewWizard() throws OdaException
    {
        return getExtendedWizard();
    }

    /** 
     * Returns a customized starting wizard page
     * for use within this design session to create
     * a new, extended ODA data source design instance.
     * @return  a customized wizard page to add in a wizard
     *          for use within this started design session
     * @throws OdaException
     */
    protected IWizardPage getWizardStartingPage() throws OdaException
    {
        return getExtendedWizard().getCustomStartingPage();
    }
    
    /**
     * Returns an ODA wizard extended from the base wizard 
     * provided by the ODA Designer UI framework.
     * @return  a NewDataSourceWizard instance
     * @throws OdaException
     */
    protected NewDataSourceWizard getExtendedWizard() 
        throws OdaException
    {
        if( m_wizard == null )
        {
            m_wizard = OdaProfileUIExplorer.getInstance()
                    .getNewDataSourceWizardByType( m_odaDataSourceId );

            // if data source extension has not specified newWizard for 
            // connectionProfile extension point, use default base class
            if( m_wizard == null )    
                m_wizard = new NewDataSourceWizard( m_odaDataSourceId );
        }
        return m_wizard;
    }

    /**
     * Initialize the given wizard, and process its
     * profile name page with automatically assigned attributes.
     * Adds pages to the given wizard if it has not been 
     * initialized with pages.
     * @param wizard    associated data source wizard
     * @param aDataSourceName   unique name to assign to new data source instance
     * @param aDataSourceDesc   optional profile description; may be null
     * @param dataSourceProps   initialized properties for use by
     *                          wizard; may be null
     */
    private void initWizard( NewDataSourceWizard wizard, 
                             String aDataSourceName,
                             String aDataSourceDesc,
                             Properties dataSourceProps )
        throws OdaException
    {
        wizard.setInOdaDesignSession( true );
        
        wizard.addPages();
        
        if( wizard.getStartingPage() instanceof NewConnectionProfileWizardPage )
        {
            if( aDataSourceName == null || aDataSourceName.length() == 0 )
                throw new OdaException( Messages.designSession_invalidArgument );
                
            // process the given wizard's profile name page,
            // with automatically assigned attributes.
            NewConnectionProfileWizardPage profileNamePage =
                (NewConnectionProfileWizardPage) wizard.getStartingPage();
            
            profileNamePage.setProfileName( aDataSourceName );
            profileNamePage.setProfileDescription( aDataSourceDesc );
            profileNamePage.setPageComplete( true );
        }
        
        // pass given properties to wizard for initialization;
        // if none is specified, keep wizard's existing properties
        if( dataSourceProps != null &&
            ! dataSourceProps.isEmpty() )
        {
            wizard.setInitialProperties( dataSourceProps );
        }
    }

    /**
     * Returns a customized editor page
     * for use within a design session to edit 
     * an extended ODA data source design instance.
     * @return  a customized property page
     * @throws OdaException
     */
    protected PropertyPage getEditorPage() throws OdaException
    {
        return getAdaptableEditorPage();
    }
    
    /**
     * Returns a customized editor page's adaptable element 
     * that represents the
     * the extended ODA data source design instance that is
     * being edited.
     * @return
     */
    protected IAdaptable getEditPropertyPageElement()
        throws OdaException
    {
        // validate if start was successfully called earlier
        if( m_designSession == null )
            throw new OdaException( Messages.common_notInDesignSession );
            
        return DesignerUtil.getAdaptableDataSourceDesign( m_designSession );
    }
    
    protected DataSourceEditorPage getAdaptableEditorPage() throws OdaException
    {
        DataSourceEditorPage editorPage = getExtendedEditorPage();
        if( editorPage.getElement() == null )
            editorPage.setElement( getEditPropertyPageElement() );
        return editorPage;
    }
    
    /**
     * Returns an ODA data source editor page  
     * provided by the ODA Designer UI framework that
     * extends from the base DataSourceEditorPage property page.
     * @return
     * @throws OdaException
     */
    protected DataSourceEditorPage getExtendedEditorPage() throws OdaException
    {
        if( m_editorPage == null )
        {
            m_editorPage = OdaProfileUIExplorer.getInstance()
                    .getDataSourceEditorPage( m_odaDataSourceId );
            if( m_editorPage == null )  // ODA extension did not implement editor page
            {
                throw new OdaException( Messages.extension_missingPropertyPage );
            }
        }
        return m_editorPage;
    }
    
    private Properties getProfileProperties( String profileInstanceId )
        throws OdaException
    {
        Properties profileProps;
        try
        {
            IConnectionProfile profile = OdaProfileExplorer.getInstance()
                .getProfile( profileInstanceId );
            if( ! m_odaDataSourceId.equalsIgnoreCase( 
                    profile.getProviderId() ))
                throw new IllegalArgumentException();
            
            profileProps = profile.getBaseProperties();
        }
        catch( Exception ex )
        {
            throw new OdaException( ex );
        }
        return profileProps;
    }

    /**
     * Performs finish on the current ODA design session to
     * create a new data source design.  This 
     * gathers the data source definition collected in UI designer
     * and maps into an OdaDesignSession with a response.
     * <br>This method must be called only after the corresponding 
     * wizard or editor has performed finish.
     * @return  the completed design session containing the
     *          new data source design within the session response
     */
    protected OdaDesignSession finishNewDataSource() throws OdaException
    {
        NewDataSourceWizard wizard = null;
        try
        {
            wizard = getExtendedWizard();
        }
        catch( OdaException e )
        {
            // TODO - error logging
            // wizard session has error
            e.printStackTrace();
        }
        
        // up to wizard to validate whether it has a valid design to return
        DataSourceDesign newDataSourceDesign = 
            ( wizard != null ) ? 
                    wizard.getDataSourceDesign() : null;
        return createResponseDesignSession( newDataSourceDesign, wizard );
    }

    /**
     * Performs finish on the current ODA design session to
     * create a new data source design from a specified connection profile.
     * @param newDataSourceName unique name to assign to the new data source instance;
     *                          may be null or empty, in which case the profile name
     *                          is applied
     * @param profileRef        reference to an existing profile instance
     *                          kept in an external profile storage file
     * @return  the completed design session containing the
     *          new data source design within the session response
     * @throws OdaException
     */
    protected OdaDesignSession finishNewDesignFromProfile(
                                    String newDataSourceName,
                                    ProfileReferenceBase profileRef )
        throws OdaException
    {
        // since wizard page is not added, initiates
        // finish on wizard's design session in lieu of page's performFinish
        NewDataSourceWizard wizard = getExtendedWizard();
        DataSourceDesign newDataSourceDesign =
                        wizard.finishDataSourceDesign();
        
        // applies the profile identifers to the new data source design
        if( newDataSourceDesign != null )
        {
            IConnectionProfile profileInstance = OdaProfileExplorer.getInstance().
                                    getProfile( profileRef.getInstanceId() );
            assert( profileInstance != null );
            
            if( newDataSourceName == null || newDataSourceName.length() == 0 )
                newDataSourceName = profileInstance.getName();
            newDataSourceDesign.setName( newDataSourceName );
            newDataSourceDesign.setDisplayName( profileInstance.getDescription() );
        }
        
        return createResponseDesignSession( newDataSourceDesign, wizard );        
    }
    
    /**
     * Creates a completed design session containing the
     * specified new data source design within the session response.
     * Also includes any custom designer state
     * that may be optionally set by an extended wizard and its
     * custom pages.
     * @param newDataSourceDesign
     * @param wizard
     * @return
     */
    protected static OdaDesignSession createResponseDesignSession( 
            DataSourceDesign newDataSourceDesign,
            NewDataSourceWizard wizard )
    {
        boolean isSessionOk = ( newDataSourceDesign != null );
        OdaDesignSession responseSession =
            DesignFactory.eINSTANCE.createResponseDesignSession( 
                isSessionOk, newDataSourceDesign );
        
        // get designerState, if set by extended class, 
        // and assign to session response
        if( wizard != null )
        {
            DesignerState customState = wizard.getResponseDesignerState();
            if( customState != null )
                responseSession.getResponse().setDesignerState( customState );
        }
        
        return responseSession;
    }
    
    /**
     * Performs finish on the current ODA design session to
     * edit a data source design.  
     * <br>This method must be called only after performOk on
     * the editor page is done.
     * @return  the completed design session containing the
     *          edited data source design within the session response
     * @throws OdaException
     */
    protected OdaDesignSession finishEditDataSource()
        throws OdaException
    {
        assert( m_designSession != null );
                   
        DataSourceEditorPage editorPage = null;
        try
        {
            editorPage = getExtendedEditorPage();
        }
        catch( OdaException ex )
        {
            // TODO - log error
            throw ex;    // editor page session has error
        }
        
        assert( editorPage != null );
        
        return editorPage.getEditSessionResponse();
    }
    
        
    /**
     * Represents the reference information of an external
     * connection profile.
     */
    public static class ProfileReferenceBase
    {
        private String m_profileInstanceId;
        private File m_storageFile; 
        private boolean m_maintainLink;
        
        /**
         * Constructor.
         * @param profileInstanceId profile instance id; such as the
         *              instance id returned by the 
         *              <code>DesignSessionUtil.getProfileIdentifiers</code> method
         * @param storageFile   a file that stores profile instances;
         *              may be null, which means to use the
         *              default DTP profiles storage file
         * @param maintainExternalLink  "true" indicates to maintain a link to the 
         *              given profile instance and storageFile, and applies its 
         *              latest properties values at run-time.
         *              "false" indicates to work with a copy of the current properties
         *              specified in the profile instance; any future
         *              changes to the profile instance is not applied to
         *              the data source design.
         */
        public ProfileReferenceBase( String profileInstanceId,
                                File storageFile, 
                                boolean maintainExternalLink )
        {
            m_profileInstanceId = profileInstanceId;
            m_storageFile = storageFile;
            m_maintainLink = maintainExternalLink;
        }
        
        public String getInstanceId()
        {
            return m_profileInstanceId;
        }
        
        public File getStorageFile()
        {
            return m_storageFile;
        }
        
        public boolean maintainExternalLink()
        {
            return m_maintainLink;
        }
        
        public boolean equals( ProfileReferenceBase aProfileRef )
        {
            if( aProfileRef == null )
                return false;
            
            return( this.m_profileInstanceId.equals( aProfileRef.m_profileInstanceId ) &&
                this.m_storageFile.equals( aProfileRef.m_storageFile ) &&
                this.m_maintainLink == aProfileRef.m_maintainLink );
        }
    }

}
