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

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.Locale;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.SessionStatus;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DesignerLogger;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSetPageInfo;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.DataSetUIElement;
import org.eclipse.datatools.connectivity.oda.design.ui.manifest.UIManifestExplorer;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * The internal ODA data set wizard base class implementation that 
 * looks up and adds customized wizard page to create an 
 * extended ODA data set design instance.
 * All its public methods are for internal use only, and might not be
 * backward compatible in future releases.
 */
public class DataSetWizardBase extends Wizard
{
    private OdaDesignSession m_designSession;
    private DataSetUIElement m_dataSetUIElement;
    private boolean m_isCreatingNewDesign;
    
    private DataSetDesign m_editDataSetDesign;
    private DesignerState m_responseDesignerState;
    private SessionStatus m_responseSessionStatus;

    // logging variable
    private static final String sm_className = DataSetWizardBase.class.getName();

    protected DataSetWizardBase()
    {
        super();
    }

    /**
     * Initializes this wizard with specified oda design
     * and data set ui extension element info.
     * @param odaDesign
     * @param dataSetUIElement
     * @throws OdaException
     * @deprecated since 3.2.2; replaced by {@link #initialize(OdaDesignSession, DataSetUIElement, boolean)}
     */
    public void initialize( OdaDesignSession odaDesign, DataSetUIElement dataSetUIElement )
        throws OdaException
    {
        initialize( odaDesign, dataSetUIElement, true );
    }

    /**
     * Initializes this wizard.
     * @since 3.2.2 (DTP 1.7.2)
     */
    public void initialize( OdaDesignSession odaDesign, DataSetUIElement dataSetUIElement, 
                            boolean isForNewDesign )
        throws OdaException
    {
        m_designSession = odaDesign;
        m_dataSetUIElement = dataSetUIElement;
        m_editDataSetDesign = null;
        m_isCreatingNewDesign = isForNewDesign;
        
        if( getPageCount() == 0 )
            addCustomPages();
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    public boolean performFinish()
    {
        try
        {
            finishDataSetDesign();
        }
        catch( OdaException ex )
        {
            // log warning about exception
            DesignerLogger logger = DesignerLogger.getInstance();
            logger.warning( sm_className, "performFinish",  //$NON-NLS-1$
                    "Caught exception while finishDataSetDesign.", ex ); //$NON-NLS-1$
            return false;
        }
        return true;
    }
    
    /**
     * Returns the design session with a response updated
     * with the data set design collected by the 
     * ODA custom page(s).
     * @return
     * @throws OdaException
     */
    public OdaDesignSession getResponseSession()
        throws OdaException
    {
        if( m_designSession.getResponse() == null )
            finishDataSetDesign();
        return m_designSession;
    }
    
    /**
     * Adds ODA custom page(s) to this wizard.
     * @throws OdaException
     */
    protected void addCustomPages()
        throws OdaException
    {
        String odaUIPluginId = getOdaDesignerPluginId();
        
        // get all page info from ODA data set ui element,
        // and create corresponding wizard page for each page definition
        DataSetPageInfo[] pageDefns = 
            m_dataSetUIElement.getPageDefinitions();
        String initialPageId = m_dataSetUIElement.getInitialPageId();

        for( int i = 0; i < pageDefns.length; i++ )
        {
            DataSetPageInfo pageInfo = pageDefns[i];
            
            DataSetWizardPageCore page = 
                createCustomPage( odaUIPluginId, pageInfo );
            
            if( initialPageId != null &&
                page.getName().equalsIgnoreCase( initialPageId ) )
            {
                page.setHasInitialFocus();
                initialPageId = null; // reset so no more pages get initial focus
            }
            
            // adds the custom page to this wizard
            addPage( page );
        }
    }

    /**
     * Returns the plugin id that implements the
     * <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code>
     * extension point, and specifies the ODA data source element id,
     * of the requested data source design.
     * @return
     */
    protected String getOdaDesignerPluginId()
    {
        return UIManifestExplorer.getInstance().getOdaDesignerId( 
                    m_designSession.getRequestDataSourceDesign()
                        .getOdaExtensionDataSourceId() );
    }

    /**
     * Instantiates a custom data set wizard page,
     * and assigns its attributes as defined in the plugin extension manifest.
     * @param odaUIPluginId     the plugin id of the ODA driver ui that
     *                      implements the oda design ui extension point
     * @param pageInfo      content of the dataSetPage element defined
     *                      in the plugin extension manifest
     * @return  a new instance of DataSetWizardPage contributed by
     *          the ODA driver ui extension 
     * @throws OdaException
     */
    protected DataSetWizardPageCore createCustomPage( 
            String odaUIPluginId, DataSetPageInfo pageInfo ) 
        throws OdaException
    {
        DataSetWizardPageCore page =
            createWizardPage( odaUIPluginId,
                    pageInfo.getWizardPageClassName(),
                    pageInfo.getPageId() );        
        
        // a valid wizard page, subclass from DataSetWizardPage;
        // overrides page title, if specified
        String pageTitle = pageInfo.getDisplayName();
        if( pageTitle != null && pageTitle.length() > 0 )
            page.setTitle( pageTitle );

        page.setPagePath( pageInfo.getPath() );
        page.setIconFilePath( pageInfo.getIcon() );
        return page;
    }

    /**
     * Instantiates the data set wizard page class 
     * contributed by an ODA data source ui plugin.
     * Uses its constructor with pageName argument.
     * @param pluginId
     * @param wizardPageClassName
     * @param pageId    for use as the page name constructor argument
     * @return
     * @throws OdaException
     */
    protected DataSetWizardPageCore createWizardPage( 
                                    String pluginId, 
                                    String wizardPageClassName,
                                    String pageId )
        throws OdaException
    {
        // instantiate using a class constructor 
        // with a single pageName argument,
        // use pageId as page name
        Object pageInstance;
        try
        {
            pageInstance = DesignerUtil.createInstanceWithStringArg( 
                    pluginId,
                    wizardPageClassName, pageId );
        }
        catch( RuntimeException ex )
        {
            throw new OdaException( ex );
        }
        
        // requires specified class to extend from
        // the public DataSetWizardPage base class
        if( ! ( pageInstance instanceof DataSetWizardPage ))
        {
            throw new OdaException( 
                    Messages.bind( Messages.extension_mustInheritFromODAPage, 
                                        wizardPageClassName,
                                        DataSetWizardPage.class.getName() )); 
        }

        return ( DataSetWizardPageCore ) pageInstance;
    }

    /**
     * Returns a copy of the data set design specified
     * in the session request.
     * It provides initialization design data for the ODA wizard
     * and its custom pages.
     * A copy is used to prevent updates to the request design.
     * @return
     */
    DataSetDesign copyRequestDataSetDesign()
    {
        if( m_designSession == null )
            return null;
            
        return (DataSetDesign) EcoreUtil.copy( 
                    m_designSession.getRequestDataSetDesign() );
    }

    /**
     * Returns a copy of the designer state specified
     * in the session request.
     * It provides initialization data for the ODA wizard
     * and its custom pages to restore the state of a
     * previous design session.
     * A copy is used to prevent updates to the request design.
     * @return
     */
    DesignerState copyRequestDesignerState()
    {
        if( m_designSession == null ||
            m_designSession.getRequest().getDesignerState() == null )
            return null;
            
        return (DesignerState) EcoreUtil.copy( 
                    m_designSession.getRequest().getDesignerState() );
    }

    /**
     * Returns a copy of the session locale specified
     * in the session request.
     * It provides initialization data for the ODA wizard
     * and its custom pages to adopt the requested locale.
     * A custom page may choose the type of locale to support.
     * A copy is used to prevent updates to the request design.
     * @return
     */
    Locale copySessionLocale()
    {
        if( m_designSession == null ||
            m_designSession.getRequest().getSessionLocale() == null )
            return null;
            
        return (Locale) EcoreUtil.copy( 
                    m_designSession.getRequest().getSessionLocale() );
    }
    
    /**
     * Indicates whether the current design session should be
     * an editable session or read-only.
     * It provides initialization data for the ODA wizard
     * and its custom pages to adopt the requested editable state.
     * A custom page may choose to honor or ignore such request.
     * @return
     */
    boolean isSessionEditable()
    {
        if( m_designSession == null )
            return true;    // default

        return m_designSession.getRequest().isEditable();       
    }
    
    /**
     * Returns the resource identifiers of the ODA consumer application, if available.
     * @return  a ResourceIdentifiers instance; may be null if none is specified
     */
    ResourceIdentifiers getHostResourceIdentifiers()
    {
        if( m_designSession == null )
            return null;
        
        DataSourceDesign dataSourceDesign = m_designSession.getRequestDataSourceDesign();
        return ( dataSourceDesign != null ) ? dataSourceDesign.getHostResourceIdentifiers() : null;
    }
    
    /**
     * Returns the current data set design being edited.
     * This returned design instance can be applied directly
     * in a design session's response.
     * The editing data set design is initialized with a 
     * deep copy of the one
     * provided in a design session's request.
     * @return
     */
    protected DataSetDesign getEditingDataSet()
    {
        if( m_editDataSetDesign == null )
        {
            m_editDataSetDesign = copyRequestDataSetDesign();
        }
        return m_editDataSetDesign;
    }
    
    /**
     * Updates and returns the current data set design instance 
     * being edited, by collecting relevant updates from 
     * the specified custom data set page.
     * <br>Relevant updates may include associated response state,
     * provided by a custom data set page implementation.
     * @param dataSetPage
     * @return the data set design instance updated 
     *          by specified data set page
     */
    protected DataSetDesign collectDataSetDesignFromPage( DataSetWizardPageCore dataSetPage )
    {
        m_editDataSetDesign =
            dataSetPage.finishDataSetDesign( getEditingDataSet() );
        return m_editDataSetDesign;
    }
    
    /**
     * Performs finish on the design session with
     * a new data set design, which contains 
     * the data set design definition
     * collected by the custom wizard page(s).
     * @throws OdaException
     */
    protected void finishDataSetDesign() throws OdaException
    {
        // iterate thru each page to update data set design
        IWizardPage[] pages = getPages();
        for( int i = 0; i < pages.length; i++ )
        {
            IWizardPage page = pages[i];
            if( ! ( page instanceof DataSetWizardPageCore ))
                continue;   // skip
            
            DataSetWizardPageCore dataSetPage = (DataSetWizardPageCore) page;
            collectDataSetDesignFromPage( dataSetPage );
        }
        
        // assign data set design in the design response
        boolean isSessionOk = ( m_editDataSetDesign != null );
        m_designSession.setNewResponse(
                isSessionOk, m_editDataSetDesign );
        
        updateResponseWithState( m_designSession.getResponse() );
    }
    
    /**
     * Update the specified response with the wizard's central
     * copy of response session status and designer state,  
     * collected from associated data set page(s).
     * @param response
     */
    void updateResponseWithState( DesignSessionResponse response )
    {
        // get custom sessionState, set by extended wizard or associated page(s),
        // and assign to specified response
        SessionStatus customStatus = getResponseSessionStatus();
        if( customStatus != null &&
            customStatus.getValue() != SessionStatus.OK )
        {
            // a custom error state takes precedence over default setting
            SessionStatus defaultStatus = response.getSessionStatus();
            if( defaultStatus == null ||
                customStatus.getValue() != defaultStatus.getValue() )
            {
                response.setSessionStatus( customStatus );
                response.getDataAccessDesign()
                    .setDataSetDesign( null );
            }                
        }
        
        // get designerState, set by extended class or associated page(s), 
        // and assign to specified response
        DesignerState customState = getResponseDesignerState();
        if( customState != null )
            response.setDesignerState( customState );        
    }
    
    /**
     * Returns the custom designer state specified by 
     * the corresponding setter method.
     * May return null if none is specified.
     * @return
     */
    protected DesignerState getResponseDesignerState()
    {
        return m_responseDesignerState;
    }
    
    /**
     * For use by an extended wizard or associated wizard page(s)
     * to optionally assign a custom designer state, for inclusion
     * in the ODA design session response.
     * @param customDesignerState   a designer state instance
     *              that preserves the current session's internal state
     *              so that it can be restored in a subsequent design session
     */
    protected void setResponseDesignerState( DesignerState customDesignerState )
    {
        m_responseDesignerState = customDesignerState;
    }

    /**
     * Returns the custom response session status specified by 
     * the corresponding setter method.
     * May return null if none is specified.
     * @return 
     */
    protected SessionStatus getResponseSessionStatus()
    {
        return m_responseSessionStatus;
    }

    /**
     * For use by an extended wizard or associated wizard page(s)
     * to optionally assign a custom response session status, 
     * for inclusion in the ODA design session response.
     * @param sessionStatus the status of the current session to
     *              indicate how an ODA host designer should process
     *              a design session response
     */
    protected void setResponseSessionStatus( SessionStatus sessionStatus )
    {
        m_responseSessionStatus = sessionStatus;
    }

    /**
     * Indicates whether this wizard is creating a new data set design or editing an existing design.
     * @return  true if creating a new data set design; false if editing an existing data set design
     * @since 3.2.2 (DTP 1.7.2)
     */
    protected boolean isCreatingNewDesign()
    {
        return m_isCreatingNewDesign;
    }
    
}
