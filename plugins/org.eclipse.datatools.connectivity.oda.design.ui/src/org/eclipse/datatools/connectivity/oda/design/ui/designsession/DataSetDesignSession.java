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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.OdaProfileUIExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DataSourceDesignSession.ProfileReference;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSetUIElement;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.NewDataSetWizard;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * An UI design session for use by an ODA host designer
 * to interact and communicate with custom ODA UI extensions to
 * create or edit an extended ODA data set design instance.
 */
public class DataSetDesignSession
{
    private OdaDesignSession m_designSession;
    private String m_odaDataSourceId;
    private DataSetUIElement m_dataSetUIElement;
    private NewDataSetWizard m_wizard;
    
    /**
     * Starts a design session to create a new 
     * data set design instance with the given name.
     * <br>This supports a simplified request for an editable session, 
     * using the default system locale.
     * @param newDataSetName   a unique name that identifies a data set 
     *          design instance
     * @param odaDataSetId      an ODA data set element id;
     *              may be null if the associated data source extension 
     *              supports only one type of data set 
     * @param dataSourceDesign  the associated data source design instance
     * @return  a design session started to create a new data set design
     * @throws OdaException
     */
    public static DataSetDesignSession startNewDesign( 
                                String newDataSetName,
                                String odaDataSetId, 
                                DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        OdaDesignSession odaDesign = 
            DesignSessionUtil.createNewRequestSession( newDataSetName, 
                            odaDataSetId, dataSourceDesign );
        DataSetDesignSession newSession = 
            new DataSetDesignSession( odaDesign );
        
        // get a new wizard and initialize with odaDesign
        newSession.initWizard();
        
        return newSession;
    }
    
    /**
     * Restarts the design session to create a new 
     * data set design instance with the given name
     * for the given ODA data set type.
     * <br>Restarting a design session on the same 
     * ODA data set type would preserve any
     * user edits made on the session's custom wizard page.
     * @param newDataSetName   a unique name that identifies a data set 
     *          design instance
     * @param odaDataSetId      an ODA data set element id;
     *              may be null if the associated data source extension 
     *              supports only one type of data set 
     * @param dataSourceDesign  the associated data source design instance
     * @throws OdaException
     * @see #startNewDesign(String, String, ProfileReference, DesignSessionRequest)
     */
    public void restartNewDesign( String newDataSetName,
                                String odaDataSetId, 
                                DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        if( m_designSession != null )
        {
            if( odaDataSetId.equalsIgnoreCase( 
                    m_designSession.getRequestDataSetDesign()
                        .getOdaExtensionDataSetId() ) &&
                EcoreUtil.equals( dataSourceDesign, 
                    m_designSession.getRequestDataSourceDesign() ))
            {
                // just update the data set name
                m_designSession.getRequestDataSetDesign()
                    .setName( newDataSetName );
                return;     // done
            }
        }
        
        // re-initialize with a new session instance
        OdaDesignSession odaDesign = 
            DesignSessionUtil.createNewRequestSession( newDataSetName, 
                            odaDataSetId, dataSourceDesign );
        initNewDesign( odaDesign );
        
        // get a new wizard and initialize with 
        // this session's odaDesign
        disposePages(); // cannot reuse wizard
        initWizard();              
    }
    
    /**
     * Initialize the data set wizard.
     * @throws OdaException
     */
    private void initWizard()
        throws OdaException
    {
        NewDataSetWizard wizard = getExtendedWizard();

        // initialize wizard
        wizard.initialize( m_designSession, m_dataSetUIElement );
    }
    
    /**
     * Requests to start a design session to create or edit 
     * a data set design,
     * as specified in the given ODA design session request.
     * @param request   a design session request, may contain
     *                  a data set design to edit
     * @return  a started design session, ready to create 
     *          a new design, or edit the requested data set design
     * @throws OdaException
     */
    public static DataSetDesignSession startEditDesign( 
                                DesignSessionRequest request )
        throws OdaException
    {
        DesignSessionUtil.validateRequestSession( request );

        OdaDesignSession odaDesign =
            DesignFactory.eINSTANCE.createOdaDesignSession();
        odaDesign.setRequest( request );

        DataSetDesignSession newSession = 
            new DataSetDesignSession( odaDesign );
                
        // get a new wizard and initialize with odaDesign
        newSession.initWizard();
        
        return newSession;
    }

    /** Not allowed to instantiate the class directly;
     *  must start a design session using a static start method
     */  
    private DataSetDesignSession( OdaDesignSession odaDesign )
        throws OdaException
    {
        initNewDesign( odaDesign );
    }
    
    /** 
     * Initializes this session with the given oda design.
     * @param odaDesign
     * @throws OdaException
     */
    private void initNewDesign( OdaDesignSession odaDesign )
        throws OdaException
    {
        m_designSession = odaDesign;

        DataSetDesign dataSetDesign = odaDesign.getRequestDataSetDesign();
        m_odaDataSourceId =
            dataSetDesign.getOdaExtensionDataSourceId();
        m_dataSetUIElement = DesignSessionUtil
            .getDataSetUIElement( m_odaDataSourceId, 
                    dataSetDesign.getOdaExtensionDataSetId() );  
    }

    /**
     * Returns the session request that has started
     * this design session.  
     * @return  the design session request, which may specify
     *          the original data set design to edit
     */
    public DesignSessionRequest getRequest()
    {
        if( m_designSession == null )
            return null;
        return m_designSession.getRequest();
    }
    
    /**
     * Performs finish on this design session to
     * create or edit its data set design.  This then 
     * gathers the data set definition collected in custom UI designer,
     * and maps into a session response with the new or updated
     * data set design instance.
     * <br>This method must be called only after the corresponding 
     * wizard or editor page has performed finish.
     * @return  a completed ODA design with the session response
     *          and the original request, if any.
     */
    public OdaDesignSession finish() throws OdaException
    {
        OdaDesignSession finishedSession = 
            finishNewDataSet();
        
        // successfully finished
        m_designSession = null;     // reset to complete session
        disposePages();
        return finishedSession;
    }
    
    /**
     * Performs cancel on this design session.
     * The design session is then cancelled and returns a
     * session response with a user_cancelled state.
     * @return  the completed design session containing a
     *          session response with a user_cancelled state
     */
    public OdaDesignSession cancel()
    {
        // sets a response with cancel status
        m_designSession.setResponseInCancelledState();

        OdaDesignSession cancelledSession = m_designSession;
        m_designSession = null;     // reset to complete session
        disposePages();
        
        return cancelledSession;
    }
    
    /**
     * Returns an ODA wizard for use within this design session
     * to create a new, extended ODA data set design instance.
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
     * for use within this design session to create or edit
     * an extended ODA data set design instance.
     * @return  a customized wizard page
     *          for use within this started design session
     * @throws OdaException
     */
    public IWizardPage getWizardStartingPage() throws OdaException
    {
        return getNewWizard().getStartingPage();
    }

    /**
     * Returns a customized editor page
     * for use within a design session to edit 
     * an extended ODA data set design instance.
     * @return  a customized wizard page
     * @throws OdaException
     */
    public IWizardPage getEditorPage() throws OdaException
    {
        return getWizardStartingPage();
    }
    
    /**
     * Returns a customized editor page's adaptable element 
     * that represents the
     * the extended ODA data set design instance that is
     * being edited.
     * @return
     */
    public IAdaptable getEditorPageElement()
        throws OdaException
    {
        // validate if start was successfully called earlier
        if( m_designSession == null )
            throw new OdaException( Messages.common_notInDesignSession );
            
        DataSetDesign dataSetDesign = 
            m_designSession.getRequestDataSetDesign();
        return (IAdaptable) EcoreUtil.copy( dataSetDesign );
    }
    
    /**
     * Returns an ODA wizard extended from the base wizard 
     * provided by the ODA Designer UI framework.
     * @return  a NewDataSetWizard instance
     * @throws OdaException
     */
    protected NewDataSetWizard getExtendedWizard() 
        throws OdaException
    {
        if( m_wizard == null )
        {
            m_wizard = OdaProfileUIExplorer.getInstance()
                    .getDataSetWizard( m_odaDataSourceId, m_dataSetUIElement );

        }
        return m_wizard;
    }    

    /**
     * Performs finish on the current ODA design session to
     * create a new data set design.  This 
     * gathers the data set design definition collected in UI designer
     * and maps into an OdaDesignSession with a response.
     * <br>This method must be called only after the corresponding 
     * wizard or editor has performed finish.
     * @return  the completed design session containing the
     *          new data set design within the session response
     */
    protected OdaDesignSession finishNewDataSet() throws OdaException
    {
        NewDataSetWizard wizard = getExtendedWizard();

        return wizard.getResponseSession();
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
    }
}
