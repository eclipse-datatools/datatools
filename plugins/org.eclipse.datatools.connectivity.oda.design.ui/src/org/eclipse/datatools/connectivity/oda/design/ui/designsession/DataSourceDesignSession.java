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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSourceDesignSessionBase;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * An UI design session for use by an ODA host designer
 * to interact and communicate with custom ODA UI extensions to
 * create or edit an extended ODA data source design instance.
 */
public class DataSourceDesignSession extends DataSourceDesignSessionBase
{
    
    /**
     * Starts a design session to create a new 
     * data source design instance with the given name
     * for the given ODA data source type. 
     * <br>This method supports a simplified request for an 
     * editable session in the default system locale.
     * @param odaDataSourceId   an ODA data source extension element id 
     * @param newDataSourceName a unique name that identifies a 
     *                          data source design instance
     * @return  a started design session, ready to create a new data source design
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
     * @param newDataSourceName a unique name that identifies a 
     *                          data source design instance
     * @param profileRef    optional reference to an existing profile instance
     *                  kept in an external profile storage file;
     *                  may be null if no reference to an existing profile
     * @param request   optional design session request specification,
     *                  such as session's edit state and locale; 
     *                  may be null for an editable session 
     *                  in the default system locale
     * @return  a started design session, ready to create a new data source design
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
        super.restartNewDesign( odaDataSourceId, newDataSourceName, profileRef, request );
    }

    /**
     * Requests to start a design session to edit 
     * a data source design,
     * as specified in the given ODA design session request.
     * <br>This is responsible for creating a 
     * custom editor page instance for use 
     * to edit a data source design.
     * @param request   a design session request, must contain
     *                  a data source design to edit
     * @return  a started design session, ready to 
     *          edit the requested data source design
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
        String odaDataSourceId = 
            DesignSessionUtil.validateRequestSession( request );

        DataSourceDesignSession newSession = 
            new DataSourceDesignSession( odaDataSourceId );
        
        newSession.initEditDesign( request, editorPage );
        return newSession;
    }
    
    /**
     * Creates a design session with a new data source design,
     * whose properties and their values are copied from,
     * or referenced to, the given profile reference.
     * <br>This method should be used when an ODA host designer
     * wants to finish a design session right away after
     * a profile is selected, and skips using its
     * custom wizard page.
     * <br>A completed response contains the new or updated 
     * data source design instance, and the designer state. 
     * @param odaDataSourceId   an ODA data source extension element id 
     * @param newDataSourceName unique name to assign to new data source instance;
     *                          may be null or empty, in which case the profile name
     *                          is applied
     * @param profileRef        reference to an existing profile instance
     *                  kept in an external profile storage file
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

        return newSession.finishNewDesignFromProfile( newDataSourceName, profileRef );
    }
    
    /** Not allowed to instantiate the class directly;
     *  must start a design session using a static start method
     */  
    private DataSourceDesignSession( String odaDataSourceId )
    {
        super( odaDataSourceId );
    }
    
    private DataSourceDesignSession()
    {
        super();
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
        return super.getRequest();
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
        return super.finish();
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
        return super.cancel();
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
        return super.getNewWizard();
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
        return super.getWizardStartingPage();
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
        return super.getEditorPage();
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
        return super.getEditPropertyPageElement();
    }
        
    /**
     * Represents the reference information of an external
     * connection profile.
     */
    public static class ProfileReference extends ProfileReferenceBase
    {
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
            super( profileInstanceId, storageFile, maintainExternalLink );
        }

    }

    
}
