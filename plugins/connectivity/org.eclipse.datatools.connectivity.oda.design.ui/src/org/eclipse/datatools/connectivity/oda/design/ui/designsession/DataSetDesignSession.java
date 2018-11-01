/*
 *************************************************************************
 * Copyright (c) 2006, 2010 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.designsession;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSetDesignSessionBase;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetEditorPage;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * An UI design session for use by an ODA host designer
 * to interact and communicate with custom ODA UI extensions to
 * create or edit an extended ODA data set design instance.
 */
public class DataSetDesignSession extends DataSetDesignSessionBase
{
    
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
            DesignSessionUtil.createNewDataSetRequestSession( newDataSetName, 
                            odaDataSetId, dataSourceDesign );
        DataSetDesignSession newSession = 
            new DataSetDesignSession( odaDesign, true );
        
        // get a new wizard and initialize with odaDesign
        newSession.initWizard();
        
        return newSession;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSetDesignSessionBase#restartNewDesign(java.lang.String, java.lang.String, org.eclipse.datatools.connectivity.oda.design.DataSourceDesign)
     */
    public void restartNewDesign( String newDataSetName,
                                String odaDataSetId, 
                                DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        super.restartNewDesign( newDataSetName, odaDataSetId, dataSourceDesign );
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
            new DataSetDesignSession( odaDesign, false );
                
        // get a new wizard and initialize with odaDesign
        newSession.initWizard();
        
        return newSession;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSetDesignSessionBase#restartEditDesign(org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest, boolean)
     */
    public boolean restartEditDesign( DesignSessionRequest newRequest, boolean resetEditorPages )
        throws OdaException
    {
        DesignSessionUtil.validateRequestSession( newRequest );
        return super.restartEditDesign( newRequest, resetEditorPages );
    }

    /** Not allowed to instantiate the class directly;
     *  must start a design session using a static start method
     */  
    private DataSetDesignSession( OdaDesignSession odaDesign, boolean isForNewDesign )
        throws OdaException
    {
        super( odaDesign, isForNewDesign );
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.design.internal.designsession.DataSetDesignSessionBase#flushEditDesign()
     */
    public OdaDesignSession flush()
        throws OdaException
    {
        return super.flush();
    }
    
    /**
     * Returns the session request that has started
     * this design session.  
     * @return  the design session request, which may specify
     *          the original data set design to edit
     */
    public DesignSessionRequest getRequest()
    {
        return super.getRequest();
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
        return super.finish();
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
        return super.cancel();
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
        return super.getNewWizard();
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
        return super.getWizardStartingPage();
    }

    /**
     * Returns an ordered collection of customized editor pages
     * for use within a design session to edit 
     * an extended ODA data set design instance.
     * Each editor page is an extended PropertyPage
     * that can be used in a preference dialog.
     * Their order is in the same sequence as the
     * dataSetPage elements defined in the plugin extension manifest.
     * @return  an array of customized editor pages
     * @throws OdaException
     */
    public DataSetEditorPage[] getEditorPages() throws OdaException
    {
        return super.getEditorPages();
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
        return super.getEditorPageElement();
    }

}
