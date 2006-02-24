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
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.AdaptableDataSourceProfile;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.OdaProfileUIExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.NewDataSourceWizard;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * A design session for use by an ODA host designer
 * to create or edit an extended ODA data source design instance.
 */
public class DataSourceDesignSession
{
    private String m_odaDataSourceId;
    private NewDataSourceWizard m_wizard;
    private DataSourceEditorPage m_editorPage;
    private OdaDesignSession m_designSession;
    
    /**
     * Starts a design session to create a new 
     * data source design instance with the given name.
     * <br>This supports a simplified request for an editable session, 
     * using the default system locale.
     * @param odaDataSourceId   an ODA data source extension element id 
     * @param aDataSourceName   a unique name that identifies a 
     *                          data source design instance
     * @return  a design session started to create a new data source design
     * @throws OdaException
     */
    public static DataSourceDesignSession startNewDesign( 
            String odaDataSourceId, String aDataSourceName )
        throws OdaException
    {
        // TODO 
        return null;
    }

    /**
     * Starts a design session to create a new 
     * data source design instance 
     * with the properties specified in the given profile instance.
     * <br>This supports a simplified request for an editable session, 
     * using the default system locale.
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
     * @throws OdaException
     */
    public static DataSourceDesignSession startNewDesign( 
                                    String odaDataSourceId,
                                    String newDataSourceName,
                                    String profileInstanceId,
                                    File storageFile, 
                                    boolean linkToProfile )
        throws OdaException
    {
        // TODO
        return null;
    }
    
    /**
     * Requests to start a design session to create or edit 
     * a data source design,
     * as specified in the given ODA design session request.
     * <br>This is responsible for creating a custom wizard page
     * or property page instance for use 
     * to create or edit a data source design.
     * @param request   a design session request, may contain
     *                  a data source design to edit
     * @return  a started design session, ready to create 
     *          a new design, or edit the requested data source design
     * @throws OdaException
     */
    public static DataSourceDesignSession startRequestedDesign( 
                                DesignSessionRequest request )
        throws OdaException
    {
        // TODO
        return null;
    }
    
    /**
     * Requests to start a design session with the given 
     * editor page to edit the data source design,
     * as specified in the given ODA design session request.
     * @param request   a design session request, may contain
     *                  a data source design to edit
     * @param editorPage    the property page instance created
     *              by the ODA host to edit the design
     * @return  a started design session, ready to create 
     *          a new design, or edit the requested data source design
     * @throws OdaException
     */
    public static DataSourceDesignSession startRequestedDesign( 
                                DesignSessionRequest request,
                                DataSourceEditorPage editorPage )
        throws OdaException
    {
        // TODO
        return null;
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
                                    String profileInstanceId,
                                    File storageFile, 
                                    boolean linkToProfile )
        throws OdaException
    {
        // TODO
        return null;
    }
    
    /** Not allowed to instantiate the class directly;
     *  must start a design session using a static start method
     */  
    private DataSourceDesignSession()
    {
        super();
    }

    /**
     * Returns the session request that has started
     * this design session.
     * @return  the design session request, which may specify
     *                  the original data source design to edit
     */
    public DesignSessionRequest getRequest()
    {
        // TODO
        return null;
    }
    
    /**
     * Performs finish on this design session to
     * create or edit its data source design.  This then 
     * gathers the data source definition collected in custom UI designer,
     * and maps into a session response with the new or updated
     * data source design instance.
     * <br>This method must be called only after the corresponding 
     * wizard or editor has performed finish.
     */
    public void finish() throws OdaException
    {
        // TODO 
    }
    
    /**
     * Performs cancel on this design session.
     * The design session is then cancelled and contains
     * session response with a user_cancelled state.
     */
    public void cancel()
    {
        // TODO
    }
    
    /**
     * If the design session is finished or cancelled,
     * returns the completed design session containing a
     * data source design in the session response;
     * otherwise, returns null.
     * <br>A completed response contains the new or updated 
     * data source design instance, and the designer state. 
     * @return  a completed ODA design with the session response
     *          and the original request.
     */
    public OdaDesignSession getOdaDesign()
    {
        // TODO
        return null;
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
        // TODO
        return null;
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
        // TODO
        return null;
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
            m_wizard = OdaProfileUIExplorer.getInstance()
                    .getNewDataSourceWizardByType( m_odaDataSourceId );
        return m_wizard;
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
        PropertyPage editorPage = getExtendedEditorPage();
        if( editorPage.getElement() == null )
            editorPage.setElement( getEditPropertyPageElement() );
        return editorPage;
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
            
        DataSourceDesign editDataSourceDesign = 
            (DataSourceDesign) EcoreUtil.copy( 
                    m_designSession.getRequestDataSourceDesign() );
        return new AdaptableDataSourceProfile( editDataSourceDesign );
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
 
}
