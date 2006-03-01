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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DataSourceDesignSession.ProfileReference;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * An UI design session for use by an ODA host designer
 * to interact and communicate with custom ODA UI extensions to
 * create or edit an extended ODA data set design instance.
 */
public class DataSetDesignSession
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
        // TODO 
        return null;
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
    public static DataSetDesignSession restartNewDesign( 
                                String newDataSetName,
                                String odaDataSetId, 
                                DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        // TODO 
        return null;
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
        // TODO
        return null;
    }

    /** Not allowed to instantiate the class directly;
     *  must start a design session using a static start method
     */  
    private DataSetDesignSession()
    {
    }

    /**
     * Returns the session request that has started
     * this design session.  May return null if none
     * was used to start this design session.
     * @return  the design session request, which may specify
     *          the original data set design to edit
     */
    public DesignSessionRequest getRequest()
    {
        // TODO
        return null;
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
        // TODO 
        return null;
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
        // TODO
        return null;
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
        // TODO
        return null;
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
        // TODO
        return null;
    }

}
