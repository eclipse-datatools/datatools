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

package org.eclipse.datatools.connectivity.oda.design.ui.designsession;

import java.io.File;
import java.util.Properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.DesignerUtil;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.OdaProfileUIExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.NewDataSourceWizard;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizardPage;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * An UI design session for use by an ODA host designer
 * to interact and communicate with custom ODA UI extensions to
 * create or edit an extended ODA data source design instance.
 */
public class DataSourceDesignSession
{
    private String m_odaDataSourceId;
    private NewDataSourceWizard m_wizard;
    private DataSourceEditorPage m_editorPage;
    private OdaDesignSession m_designSession;
    
    /**
     * Starts a design session to create a new 
     * data source design instance with the given name
     * for the given ODA data source type. 
     * <br>This method supports a simplified request for an 
     * editable session in the default system locale.
     * @param odaDataSourceId   
     * @param aDataSourceName
     * @return
     * @throws OdaException
     * @see #startNewDesign(String, String, ProfileReference, DesignSessionRequest)
     */
    public static DataSourceDesignSession startNewDesign( 
            String odaDataSourceId, String newDataSourceName )
        throws OdaException
    {
        return startNewDesign( odaDataSourceId, 
                                newDataSourceName, 
                                null, null );
    }

    /**
     * Starts a design session to create a new 
     * data source design instance with the given name
     * for the given ODA data source type, and initializes 
     * with the properties specified in the given profile instance.
     * @param odaDataSourceId   an ODA data source extension element id 
     * @param aDataSourceName   a unique name that identifies a 
     *                          data source design instance
     * @param profileRef    optional reference to an existing profile instance
     *                  kept in an external profile storage file;
     *                  may be null if no reference to an existing profile
     * @param request   optional design session request specification,
     *                  such as session's edit state and locale; 
     *                  may be null for an editable session 
     *                  in the default system locale
     * @return  a design session started to create a new data source design
     * @throws OdaException
     */
    public static DataSourceDesignSession startNewDesign( 
                                String odaDataSourceId,
                                String newDataSourceName,
                                ProfileReference profileRef,
                                DesignSessionRequest request )
        throws OdaException
    {
        // instantiates a new instance and initializes
        DataSourceDesignSession newSession = 
            new DataSourceDesignSession( odaDataSourceId );
        
        newSession.initNewDesign( newDataSourceName, 
                                    profileRef, request );
        return newSession;
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
     * @see #startNewDesign(String, String, ProfileReference, DesignSessionRequest)
     */
    public void restartNewDesign( String odaDataSourceId,
                                String newDataSourceName,
                                ProfileReference profileRef,
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
    private void initNewDesign( String newDataSourceName,
                                ProfileReference profileRef,
                                DesignSessionRequest request )
        throws OdaException
    {
        NewDataSourceWizard wizard = getExtendedWizard();
        
        // pass requested session state to wizard
        if( request != null )
        {
            // TODO
        }
        
        // verifies that the given profile does exist in cache
        Properties profileProps = null;
        String profileName = null;
        if( profileRef != null )
        {
            profileProps = getProfileProperties( profileRef.getInstanceId() );
            profileName = OdaProfileExplorer.getInstance().
                    getProfile( profileRef.getInstanceId() ).getName();
            if( newDataSourceName == null || newDataSourceName.length() == 0 )
                newDataSourceName = profileName;
        }
        
        // initialize wizard with given name and properties, if any
        // TODO - if wizard was initialize w/ same profileRef,
        // do not change any user edits on wizard page
        initWizard( wizard, newDataSourceName, profileProps );

        if( profileRef != null && profileRef.maintainExternalLink() )
            wizard.setLinkedProfile( profileName, profileRef.getStorageFile() );
    }

    /**
     * Requests to start a design session to edit 
     * a data source design,
     * as specified in the given ODA design session request.
     * <br>This is responsible for creating a 
     * custom editor page instance for use 
     * to edit a data source design.
     * @param request   
     * @return  
     * @throws OdaException
     * @see #startEditDesign(DesignSessionRequest, DataSourceEditorPage)
     */
    public static DataSourceDesignSession startEditDesign( 
                                DesignSessionRequest request )
        throws OdaException
    {
        return startEditDesign( request, null ); 
    }
    
    /**
     * Requests to start a design session with the given 
     * editor page to edit a data source design,
     * as specified in the given ODA design session request.
     * @param request   a design session request, must contain
     *                  a data source design to edit
     * @param editorPage    the property page instance created
     *              by the ODA host to edit the design;
     *              may be null, in which case the session will 
     *              create a custom page
     * @return  a started design session, ready to 
     *          edit the requested data source design
     * @throws OdaException
     */
    public static DataSourceDesignSession startEditDesign( 
                                DesignSessionRequest request,
                                DataSourceEditorPage editorPage )
        throws OdaException
    {
        String odaDataSourceId = validateRequestSession( request );

        DataSourceDesignSession newSession = 
            new DataSourceDesignSession( odaDataSourceId );
        
        if( editorPage == null )
            editorPage = newSession.getExtendedEditorPage();
        
        // create a top-level OdaDesignSession with given request
        OdaDesignSession odaDesign = 
            DesignFactory.eINSTANCE.createOdaDesignSession();
        odaDesign.setRequest( request );
        
        // Update the editor page's property values with those found
        // in the given data source design
        editorPage.initEditSession( odaDesign );

        if( newSession.m_editorPage != editorPage )
        {
            // TODO - log warning if m_editorPage != null
            newSession.m_editorPage = editorPage;
        }
        newSession.m_designSession = odaDesign;   // hold on till finish editing

        return newSession;
    }
    
    /**
     * Creates a design session with a new data source design,
     * whose properties and their values are copied from,
     * or referenced to, the given profile instance.
     * <br>This method should be used when an ODA host designer
     * wants to finish a design session right away after
     * a profile is selected, and skips using its
     * custom wizard page.
     * <br>A completed response contains the new or updated 
     * data source design instance, and the designer state. 
     * @param odaDataSourceId   an ODA data source extension element id 
     * @param aDataSourceName   unique name to assign to new data source instance
     * @param profileInstanceId profile instance id; such as the
     *              instance id returned by the 
     *              <code>getProfileIdentifiers</code> method
     * @param storageFile   a file that stores profile instances;
     *              may be null, which means to use the
     *              default DTP profiles storage file
     * @param linkToProfile  "true" indicates to maintain a link to the 
     *              given profile instance and storageFile, and applies its 
     *              latest properties values at run-time.
     *              "false" indicates to work with a copy of the current properties
     *              specified in the profile instance; any future
     *              changes to the profile instance is not applied to
     *              the data source design.
     * @return  a completed design session with the session response,
     *          containing the new data source design,
     *          and the original request.
     * @throws OdaException
     */
    public static OdaDesignSession createNewDesignFromProfile( 
                                    String odaDataSourceId,
                                    String newDataSourceName,
                                    ProfileReference profileRef )
        throws OdaException
    {
        DataSourceDesignSession newSession = 
                        startNewDesign( odaDataSourceId,
                                newDataSourceName,
                                profileRef, null );

        // since wizard page is not added, initiates
        // finish on design session in lieu of page's performFinish
        NewDataSourceWizard wizard = newSession.getExtendedWizard();
        DataSourceDesign newDataSourceDesign =
                        wizard.finishDataSourceDesign();
        
        if( newDataSourceDesign != null )
        {
            newDataSourceDesign.setName( newDataSourceName );
            newDataSourceDesign.setDisplayName( newDataSourceName );
        }
        
        boolean isSessionOk = ( newDataSourceDesign != null );
        return DesignFactory.eINSTANCE.createResponseDesignSession( 
                        isSessionOk, newDataSourceDesign );
    }
    
    /** Not allowed to instantiate the class directly;
     *  must start a design session using a static start method
     */  
    private DataSourceDesignSession( String odaDataSourceId )
    {
        assert( odaDataSourceId != null && odaDataSourceId.length() > 0 );
        m_odaDataSourceId = odaDataSourceId;
    }
    
    private DataSourceDesignSession()
    {
    }

    /**
     * Returns the session request that has started
     * this design session.  May return null if none
     * was used to start this design session.
     * @return  the design session request, which may specify
     *          the original data source design to edit
     */
    public DesignSessionRequest getRequest()
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
    public OdaDesignSession finish() throws OdaException
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
    public OdaDesignSession cancel()
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
    }
        
    /**
     * Returns an ODA wizard for use within this design session
     * to create a new, extended ODA data source design instance.
     * @return  a wizard instance to embed in a wizard dialog
     *          for use within this started design session
     * @throws OdaException
     */
    public IWizard getNewWizard() throws OdaException
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
    public IWizardPage getWizardStartingPage() throws OdaException
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
     * @param dataSourceProps   initialized properties for use by
     *                          wizard; may be null
     */
    private void initWizard( NewDataSourceWizard wizard, 
                             String aDataSourceName,
                             Properties dataSourceProps )
    {
        wizard.setInOdaDesignSession( true );
        
        if( wizard.getPageCount() == 0 )
            wizard.addPages();
        
        if( wizard.getStartingPage() instanceof NewConnectionProfileWizardPage )
        {
            // process the given wizard's profile name page,
            // with automatically assigned attributes.
            NewConnectionProfileWizardPage profileNamePage =
                (NewConnectionProfileWizardPage) wizard.getStartingPage();
            
            profileNamePage.setProfileName( aDataSourceName );
            profileNamePage.setProfileDescription( aDataSourceName );
            profileNamePage.setPageComplete( true );
        }
        
        // pass given properties to wizard for initialization
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
    public PropertyPage getEditorPage() throws OdaException
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
    public IAdaptable getEditPropertyPageElement()
        throws OdaException
    {
        // validate if start was successfully called earlier
        if( m_designSession == null )
            throw new OdaException( "Not in an edit session." );
            
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
                // TODO - replace with default OdaDesignSession resource set editor
                throw new OdaException( "ODA Extension has no editor page." );
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

    private static String validateRequestSession( 
                    DesignSessionRequest requestSession )
        throws OdaException
    {
        // validate if start was not already called 
        if( requestSession == null )
            throw new OdaException( "Invalid argument." );

        try
        {
            DesignUtil.validateObject( requestSession );
        }
        catch( IllegalStateException ex )
        {
            throw new OdaException( ex );
        }

        // validate the given request' data source design
        DataSourceDesign dataSourceDesign = 
            requestSession.getDataAccessDesign().getDataSourceDesign();
        if( dataSourceDesign == null )
            throw new OdaException( "Missing data source design in OdaDesignSession instance." );

        String odaDataSourceId = dataSourceDesign.getOdaExtensionDataSourceId();
        if( odaDataSourceId == null || odaDataSourceId.length() == 0 )
            throw new OdaException( "Missing ODA ID in data source design." );

        // done validation
        return odaDataSourceId;
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
            // TODO - error handling
            // wizard session has error
            e.printStackTrace();
        }
        
        // up to wizard to validate whether it has a valid design to return
        DataSourceDesign newDataSourceDesign = 
            ( wizard != null ) ? 
                    wizard.getDataSourceDesign() : null;
        boolean isSessionOk = ( newDataSourceDesign != null );
        return DesignFactory.eINSTANCE.createResponseDesignSession( 
                isSessionOk, newDataSourceDesign );
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
    public static class ProfileReference
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
        public ProfileReference( String profileInstanceId,
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
        
        public boolean equals( ProfileReference aProfileRef )
        {
            return( this.m_profileInstanceId.equals( aProfileRef.m_profileInstanceId ) &&
                this.m_storageFile.equals( aProfileRef.m_storageFile ) &&
                this.m_maintainLink == aProfileRef.m_maintainLink );
        }
    }
    
}
