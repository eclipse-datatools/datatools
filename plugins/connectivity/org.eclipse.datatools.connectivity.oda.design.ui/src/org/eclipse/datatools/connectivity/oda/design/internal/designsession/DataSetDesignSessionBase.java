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

package org.eclipse.datatools.connectivity.oda.design.internal.designsession;

import java.util.ArrayList;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionRequest;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.SessionStatus;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.OdaProfileUIExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSetUIElement;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetEditorPage;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizard;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * Base class implementation of an UI data set design session for use 
 * by an ODA host designer to interact and communicate with 
 * custom ODA UI extensions to create or edit an extended 
 * ODA data set design instance.
 */
public class DataSetDesignSessionBase
{
    private OdaDesignSession m_odaDesign;
    private String m_odaDataSourceId;
    private DataSetUIElement m_dataSetUIElement;
    private DataSetWizard m_wizard;
    private ArrayList<DataSetEditorPage> m_editorPages;
    private boolean m_inCreateMode;

    /** Not allowed to instantiate the class directly;
     *  must start a design session using a subclass static start method
     */      
    protected DataSetDesignSessionBase( OdaDesignSession odaDesign, boolean isForNewDesign )
        throws OdaException
    {
        initOdaDesign( odaDesign, isForNewDesign );
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
     */
    protected void restartNewDesign( String newDataSetName,
                                String odaDataSetId, 
                                DataSourceDesign dataSourceDesign )
        throws OdaException
    {
        if( m_odaDesign != null )
        {
            if( odaDataSetId.equalsIgnoreCase( 
                    m_odaDesign.getRequestDataSetDesign()
                        .getOdaExtensionDataSetId() ) &&
                EcoreUtil.equals( dataSourceDesign, 
                    m_odaDesign.getRequestDataSourceDesign() ))
            {
                // just update the data set name
                m_odaDesign.getRequestDataSetDesign()
                    .setName( newDataSetName );
                return;     // done
            }
        }
        
        // re-initialize with a new session instance
        OdaDesignSession odaDesign = 
            DesignSessionUtilBase.createNewDataSetRequestSession( newDataSetName, 
                            odaDataSetId, dataSourceDesign );
        initOdaDesign( odaDesign, true );
        
        // get a new wizard and initialize with 
        // this session's odaDesign
        disposePages(); // cannot reuse wizard
        initWizard();              
    }

    /**
     * Restarts the design session to edit the data set design specified 
     * in the new design session request.
     * <br>Specifying false in the resetEditorPages argument requests the design session 
     * to try preserve any user edits made on the session's custom wizard page(s), if feasible.
     * @param newRequest a new design session request to restart the design session
     * @param resetEditorPages  true requests the design session to reset its custom editor pages,
     *          false to try preserve the existing editor pages if feasible
     * @return  true indicates that the restarted design session has reset its editor pages,
     *          false otherwise
     * @throws OdaException
     * @since 3.0.7
     */
    protected boolean restartEditDesign( DesignSessionRequest newRequest, boolean resetEditorPages )
        throws OdaException
    {
        if( m_odaDesign != null && 
            ! resetEditorPages ) // try to preserve existing pages
        {
            if( canReuseEditorPages( newRequest ) )
            {
                // ok to reuse existing session's wizard and custom page(s);
                // simply reset current design session w/ new request
                m_odaDesign.setRequest( newRequest ); 
                m_odaDesign.setResponse( null );
                initWizard();
                return false;     // done; restarted session did not need to use new custom editor pages
            }
        }
        
        // cannot re-use the same custom data set wizard;
        // re-initialize with a new session instance
        OdaDesignSession odaDesign =
            DesignFactory.eINSTANCE.createOdaDesignSession();
        odaDesign.setRequest( newRequest );
        initOdaDesign( odaDesign, false );
        
        // get a new wizard and initialize with this session's odaDesign
        disposePages(); // dispose existing wizard and custom pages
        initWizard();
        return true; // restarted session has reset new custom editor pages
    }
    
    /**
     * Check if the new request is for the same type of oda data set and 
     * same data source design attributes.  If not the same, the custom editor pages
     * must be reset.
     */
    private boolean canReuseEditorPages( DesignSessionRequest newRequest )
    {
        DataSetDesign newRequestDataSetDesign = newRequest.getDataSetDesign();
        if( newRequestDataSetDesign == null )
            return false;
        if( ! m_odaDataSourceId.equalsIgnoreCase( 
                newRequestDataSetDesign.getOdaExtensionDataSourceId() ) )
            return false;   // reset pages for different oda data source type
        if( ! m_odaDesign.getRequestDataSetDesign().getOdaExtensionDataSetId().equalsIgnoreCase( 
                newRequestDataSetDesign.getOdaExtensionDataSetId() ))
            return false;   // reset pages for different oda data set type
            
        // can reuse pages if same data source design attributes
        return EcoreUtil.equals( m_odaDesign.getRequestDataSourceDesign(), 
                                newRequest.getDataSourceDesign() );   
    }
    
    /**
     * Initialize the data set wizard.
     * @throws OdaException
     */
    protected void initWizard()
        throws OdaException
    {
        DataSetWizard wizard = getExtendedWizard();

        // initialize wizard
        wizard.initialize( m_odaDesign, m_dataSetUIElement, m_inCreateMode );
    }
       
    /** 
     * Initializes this session with the given oda design.
     * @param odaDesign
     * @throws OdaException
     */
    private void initOdaDesign( OdaDesignSession odaDesign, boolean isForNewDesign )
        throws OdaException
    {
        m_odaDesign = odaDesign;

        DataSetDesign dataSetDesign = odaDesign.getRequestDataSetDesign();
        m_odaDataSourceId =
            dataSetDesign.getOdaExtensionDataSourceId();
        m_dataSetUIElement = DesignSessionUtilBase
            .getDataSetUIElement( m_odaDataSourceId, 
                    dataSetDesign.getOdaExtensionDataSetId() );  
        m_inCreateMode = isForNewDesign;
    }

    /**
     * Returns the session request that has started
     * this design session.  
     * @return  the design session request, which may specify
     *          the original data set design to edit
     */
    protected DesignSessionRequest getRequest()
    {
        if( m_odaDesign == null )
            return null;
        return m_odaDesign.getRequest();
    }
    
    /**
     * Flushes the current state of this data set design session to the response
     * of the returned OdaDesignSession.
     * It would also reset this design session with the flushed design in a new request. 
     * <br>Flushing a design session preserves and re-uses this session's custom page(s).
     * <br>A design session can be flushed only when it is active, and has not gone through 
     * the finish() operation.
     * @return  a completed OdaDesignSession with the the original request and
     *          the flushed data set design and designer state in the session response.
     * @throws OdaException
     */
    protected OdaDesignSession flush()
        throws OdaException
    {
        if( m_odaDesign == null )
            throw new OdaException( Messages.common_notInDesignSession );

        // triggers saving the latest design state into the response of the current OdaDesignSession
        OdaDesignSession origSession = finishDataSetDesign();
        if( origSession.getResponse() == null )
            throw new OdaException( Messages.designSession_flushFailed );

        // if the flushed session status was ok, copy the flushed session to a new one, and 
        // reset its request with the latest response info;
        // otherwise if session status was not ok, keep the original session request
        if( origSession.getResponse().getSessionStatus() == SessionStatus.OK_LITERAL )
        {
            OdaDesignSession newSession = (OdaDesignSession) EcoreUtil.copy( origSession );
            DesignSessionResponse latestResponse = newSession.getResponse();
            newSession.getRequest().setNewDataAccessDesign( latestResponse.getDataSetDesign() );
            newSession.getRequest().setDesignerState( latestResponse.getDesignerState() );
            newSession.setResponse( null );
            m_odaDesign = newSession;
    
            initWizard();
        }
        
        return origSession;
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
     * @throws OdaException
     */
    protected OdaDesignSession finish() throws OdaException
    {
        // validate if this design session is in a valid state
        if( m_odaDesign == null )
            throw new OdaException( Messages.common_notInDesignSession );

    	OdaDesignSession finishedSession = 
            finishDataSetDesign();
        
        // successfully finished
        m_odaDesign = null;     // reset to complete session
        disposePages();
        return finishedSession;
    }
    
    /**
     * Performs cancel on this design session.
     * The design session is then cancelled and returns a
     * session response with a user_cancelled state.
     * @return  the completed design session containing a
     *          session response with a user_cancelled state
     * @throws IllegalStateException	if this design session is not
     * 				in a valid state, e.g. it is already finished or cancelled
     */
    protected OdaDesignSession cancel() throws IllegalStateException
    {
        // validate if this design session is in a valid state
    	if( m_odaDesign == null )
    		throw new IllegalStateException( Messages.common_notInDesignSession );
    	
        // sets a response with cancel status
        m_odaDesign.setResponseInCancelledState();

        OdaDesignSession cancelledSession = m_odaDesign;
        m_odaDesign = null;     // reset to complete session
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
    protected IWizard getNewWizard() throws OdaException
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
    protected IWizardPage getWizardStartingPage() throws OdaException
    {
        return getNewWizard().getStartingPage();
    }
    
    /**
     * Returns an ODA wizard extended from the base wizard 
     * provided by the ODA Designer UI framework.
     * @return  a DataSetWizard instance
     * @throws OdaException
     */
    protected DataSetWizard getExtendedWizard() 
        throws OdaException
    {
        // validate if this design session is in a valid state
        if( m_odaDesign == null )
            throw new OdaException( Messages.common_notInDesignSession );

        if( m_wizard == null )
        {
            m_wizard = OdaProfileUIExplorer.getInstance()
                    .getDataSetWizard( m_odaDataSourceId, m_dataSetUIElement );

        }
        return m_wizard;
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
    protected DataSetEditorPage[] getEditorPages() throws OdaException
    {
        ArrayList<DataSetEditorPage> editorPages = getExtendedEditorPages();
        return (DataSetEditorPage[]) editorPages.toArray( 
                new DataSetEditorPage[ editorPages.size() ] );
    }
    
    /**
     * Returns an ordered collection of
     * customized ODA data set editor pages,
     * adapted from the custom data set wizard pages
     * contributed by an ODA design ui extension.
     * @return
     * @throws OdaException
     */
    protected ArrayList getExtendedEditorPages()
        throws OdaException
    {
        // validate if this design session is in a valid state
        if( m_odaDesign == null )
            throw new OdaException( Messages.common_notInDesignSession );

    	if( m_editorPages != null )
            return m_editorPages;
        
        IWizardPage[] pages = getExtendedWizard().getPages();
        m_editorPages = new ArrayList<DataSetEditorPage>( pages.length );

        // for each wizard page, convert to an editor page
        for( int i = 0; i < pages.length; i++ )
        {
            if( pages[i] instanceof DataSetWizardPage )
            {
                m_editorPages.add( 
                        new DataSetEditorPage( (DataSetWizardPage) pages[i] ));
            }
        }
        return m_editorPages;
    }
    
    /**
     * Returns a customized editor page's adaptable element 
     * that represents the
     * the extended ODA data set design instance that is
     * being edited.
     * @return
     */
    protected IAdaptable getEditorPageElement()
        throws OdaException
    {
        // validate if this design session is in a valid state
        if( m_odaDesign == null )
            throw new OdaException( Messages.common_notInDesignSession );
            
        DataSetDesign dataSetDesign = 
            m_odaDesign.getRequestDataSetDesign();
        return (IAdaptable) EcoreUtil.copy( dataSetDesign );
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
    protected OdaDesignSession finishDataSetDesign() throws OdaException
    {
        DataSetWizard wizard = getExtendedWizard();

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
        
        if( m_editorPages != null )
        {
            int numPages = m_editorPages.size();
            for( int i = 0; i < numPages; i++ )
            {
                DataSetEditorPage page = 
                    (DataSetEditorPage) m_editorPages.get( i );
                page.dispose();
            }
            m_editorPages = null;
        }
   }

}
