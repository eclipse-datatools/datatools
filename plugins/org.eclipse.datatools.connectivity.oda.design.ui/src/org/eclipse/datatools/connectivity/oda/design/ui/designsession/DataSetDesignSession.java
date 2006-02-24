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
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * A design session for use by an ODA host designer
 * to create or edit an extended ODA data set design instance.
 */
public class DataSetDesignSession
{
    
    /**
     * Starts a design session to create a new 
     * data set design instance with the given name.
     * <br>This supports a simplified request for an editable session, 
     * using the default system locale.
     * @param odaDataSourceId   an ODA data source extension element id 
     * @param odaDataSetId      an ODA data set element id;
     *              may be null if the data source extension 
     *              supports only one type of data set  
     * @param aDataSetName   a unique name that identifies a data set 
     *          design instance
     * @return  a design session started to create a new data set design
     * @throws OdaException
     */
    public static DataSetDesignSession startNewDesign( 
            String odaDataSourceId, String odaDataSetId, 
            String aDataSetName )
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
    public static DataSetDesignSession startRequestedDesign( DesignSessionRequest request )
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
        super();
    }

    /**
     * Returns the session request that has started
     * this design session.
     * @return  the design session request, which may specify
     *                  the original data set design to edit
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
     * returns the completed design session containing the
     * edited data set design in the session response;
     * otherwise, returns null.
     * <br>A completed response contains the new or updated 
     * data set design instance, and the designer state. 
     * @return  a completed design session with the session response
     *          and the original request.
     */
    public OdaDesignSession getResponseSession()
    {
        // TODO
        return null;
    }
    
    /**
     * Returns the ODA wizard for use within this design session
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
     * Returns the customized starting wizard page
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
