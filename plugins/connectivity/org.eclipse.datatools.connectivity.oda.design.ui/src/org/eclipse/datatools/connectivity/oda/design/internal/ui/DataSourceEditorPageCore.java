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

import java.util.Iterator;
import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.Locale;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.internal.designsession.DesignerLogger;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.ProfileSelectionEditorPage;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.profile.OdaProfileExplorer;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Composite;

/**
 * The core implementation of the Data Source Editor Page base class 
 * provided in the ODA designer UI framework.  It extends the 
 * DTP connection profile wizard framework.  
 * <br>It can be used, outside of the Data Source 
 * Explorer UI, by an user to edit the connection properties' values
 * of an extended ODA data source definition.
 */
public abstract class DataSourceEditorPageCore extends ProfileDetailsPropertyPage
{
    private OdaDesignSession m_designSession;
    private Properties m_dataSourceProps;
    private DesignerState m_responseDesignerState;
    private Boolean m_setPingButtonVisible;
    private Boolean m_setPingButtonEnabled;

    // logging variable
    private static final String sm_className = DataSourceEditorPageCore.class.getName();

    /**
     * Updates the specified data source design with design properties
     * collected in this page.
     * Sub-class may override or extend this method to further update
     * the given data source design, as needed.
     * The default behavior saves the page's edited properties 
     * in the specified design, based on the data source property 
     * definitions specified in the ODA extension's manifest.
     * <br>Examples of custom data source design updates include 
     * setting its private properties, and
     * dynamically define a property's design attributes  
     * per design instance.
     * <br>This method is called when performing finish on a
     * data source editing session.
     * @param design    a data source design instance being updated
     * @return  the updated data source design instance, or
     *      null if an error exists and unable to update the design
     */
    protected DataSourceDesign collectDataSourceDesign( 
                                    DataSourceDesign design )
    {
        Properties propertyValuePairs = collectProperties();

        // save the page's properties in the design
        try
        {
            setDataSourceDesignProperties( design, propertyValuePairs );
        }
        catch( OdaException ex )
        {
            // log warning about exception
            DesignerLogger logger = DesignerLogger.getInstance();
            logger.warning( sm_className, "collectDataSourceDesign( DataSourceDesign )",  //$NON-NLS-1$
                    "Caught exception while assigning property name-value pairs to data source design properties.", ex ); //$NON-NLS-1$
        }
        
        return design;
    }

    /**
     * Assigns the relevant data source properties to the
     * specified data source design instance.
     * Sub-class may override or extend this method as needed.
     * The default behavior saves the specified properties, 
     * based on the data source property definitions specified in 
     * an ODA extension's manifest.
     * @param design    the data source design definition to update
     * @param propertyValuePairs  data source property name-value pairs
     * @throws OdaException
     * @since DTP 1.6
     */
    protected void setDataSourceDesignProperties( DataSourceDesign design,
                                                  Properties propertyValuePairs ) 
        throws OdaException
    {
        design.setPublicProperties(
                DesignSessionUtil.createDataSourcePublicProperties( 
                        design.getOdaExtensionDataSourceId(),
                        propertyValuePairs ));
        design.setPrivateProperties( 
                DesignSessionUtil.createDataSourceNonPublicProperties( 
                        design.getOdaExtensionDataSourceId(),
                        propertyValuePairs ));
    }
    
    /**
     * Cleans up before the page is disposed.
     * Default implementation does nothing.  Sub-class
     * may override to clean up custom operations such as
     * closing a connection.
     */
    protected abstract void cleanup();

    
    protected DataSourceEditorPageCore()
    {
        super();
        noDefaultAndApplyButton();
    }

    /**
     * Returns the associated profile instance's properties.
     * If none is associated, returns own cached properties.
     * @return  properties in associated profile instance, or
     *          own cached properties
     */
    public Properties getDataSourceProperties()
    {
        Properties props = getProfileProperties();
        if( props != null )   // page element is associated with a profile
            return props;
        
        return getPageDataProperties();    // use own cached values
    }

    /**
     * Updates the editor page's data source properties with 
     * values specified in the given collection.
     * The updated values will be used to initialize the
     * editor page's custom contents.
     * @param props the collection of property name value pairs,
     *        that may contain both public and private properties
     *        of the ODA extension.
     */
    protected void updateDataSourceProperties( Properties props )
    {
        if( props == null || props.isEmpty() )
            return; // nothing to update
        
        Properties associatedProps = getDataSourceProperties();
    
        Iterator propKeys = props.keySet().iterator();
        while( propKeys.hasNext() )
        {
            String propName = (String) propKeys.next();
            associatedProps.setProperty( propName, 
                                props.getProperty( propName ) );
        }
    }

    /**
     * Returns the associated profile instance's properties,
     * or null if not associated with a profile.
     * @return
     */
    protected Properties getProfileProperties()
    {
        IConnectionProfile profile = null;
        try
        {
            profile = getConnectionProfile();
        }
        catch( RuntimeException e )
        {
            // ok to ignore
        }

        if( profile != null )
            return profile.getBaseProperties();  
        return null;
    }

    /**
     * Returns the data source properties cached directly
     * by this editor page.  The properties may contain both
     * public and private properties of the ODA extension.
     * @return 
     */
    protected Properties getPageDataProperties()
    {
        if( m_dataSourceProps == null )
            m_dataSourceProps = new Properties();
        return m_dataSourceProps;
    }

    protected AdaptableDataSourceProfile getProfileElement()
    {
        return ( getElement() instanceof AdaptableDataSourceProfile ) ?
                (AdaptableDataSourceProfile) getElement() : null;
    }
    
    /**
     * Provides a new data source design that can be edited
     * and applied in a design session's response.
     * The editing data source was deep copied from the one
     * provided in the design session's request.
     * @return
     */
    protected DataSourceDesign getEditingDataSource()
    {
        if( getProfileElement() == null )
        {
            return DesignFactory.eINSTANCE.createDataSourceDesign();
        }
        
        // should have thrown exception within init if it failed

        DataSourceDesign dataSourceDesign =
            getProfileElement().getDataSourceDesign();
        assert( dataSourceDesign != null );
        return dataSourceDesign;
    }

    /**
     * Initializes the editor page with a design session info object
     * that contains a request to edit its data source design.
     * This initializes the page's properties with corresponding values
     * found or referenced in the data source design. 
     * @param requestSession  the design session with a request
     *              that contains the data source design being edited
     */
    public void initEditSession( OdaDesignSession requestSession ) throws OdaException
    {
        // check if already initialized with same design session
        if( getProfileElement() != null &&
            m_designSession == requestSession )
        {
            return;     // already initialized
        }
            
        // reset cached profiles for edit session with linked profile in design,
        // so the page element will have the current profile instance
        if( requestSession.getRequestDataSourceDesign() != null &&
            requestSession.getRequestDataSourceDesign().hasLinkToProfile() )
            OdaProfileExplorer.getInstance().refresh();          

        // associate a copy of the request's data source design 
        // as the element being edited in this page
        setElement( DesignerUtil.getAdaptableDataSourceDesign( 
                                              requestSession ) );
    
        // hold on design session info till finish editing
        m_designSession = requestSession;   
    }

    /**
     * Initializes the specified ProfileSelectionEditorPage for this design edit session.
     * An optional method for a subclass to control the behavior of the profile selection page
     * in an edit session.
     * @param profileSelectionPage
     * @since DTP 1.6
     */
    public void initProfileSelectionEditSession( ProfileSelectionEditorPage profileSelectionPage )
    {
        // no-op by default; subclass may override
    }

    protected OdaDesignSession getDesignSession()
    {
        return m_designSession;
    }
    
    /**
     * Indicates whether the editor page is in the midst
     * of a design session to edit a data source definition.
     * @return
     */
    public boolean isInOdaDesignSession()
    {
        return ( m_designSession != null );
    }
    
    /**
     * Returns the ODA data source element id that 
     * uniquely identifies the ODA run-time data source extension
     * of the ODA driver's custom designer plug-in.
     * @return
     */
    protected String getOdaDataSourceId()
    {
        if( ! isInOdaDesignSession() )
            return getConnectionProfile().getProviderId();
        return getEditingDataSource().getOdaExtensionDataSourceId();   	
    }
    
    /**
     * Returns a copy of the data source design instance used in
     * initialization of the customized control of this extended editor page.
     * A copy is returned to prevent updates to the request design.
     * @return
     */
    protected DataSourceDesign getInitializationDesign()
    {
        if( ! isInOdaDesignSession() )
            return null;

        return (DataSourceDesign) EcoreUtil.copy( 
                    m_designSession.getRequestDataSourceDesign() );
    }

    /**
     * Returns a copy of the designer state specified
     * in the design session request.
     * It provides initialization data for this extended editor page
     * to restore the state of a previous design session.
     * A copy is returned to prevent updates to the request design.
     * @return
     */
    protected DesignerState getInitializationDesignerState()
    {
        if( ! isInOdaDesignSession() ||
            m_designSession.getRequest().getDesignerState() == null )
            return null;
                
        return (DesignerState) EcoreUtil.copy( 
                        m_designSession.getRequest().getDesignerState() );
    }
    
    /**
     * Returns a copy of the session locale specified
     * in the design session request.
     * It provides initialization data for this extended editor page
     * to adopt the requested locale.
     * An extended editor page may choose to honor or ignore such request.
     * A copy is returned to prevent updates to the request design.
     * @return
     */
    protected Locale getInitializationLocale()
    {
        if( ! isInOdaDesignSession() ||
            m_designSession.getRequest().getSessionLocale() == null )
            return null;
                
        return (Locale) EcoreUtil.copy( 
                        m_designSession.getRequest().getSessionLocale() );
    }
    
    /**
     * Indicates whether the current design session has requested
     * for an editable or read-only session.
     * It may be used by an extended editor page for initialization
     * of its customized control to be read-only.
     * An extended editor page may choose to honor or ignore such request.
     * @return
     * @since 3.0.4
     */
    protected boolean isEditableSessionRequested()
    {
        if( ! isInOdaDesignSession() )
            return true;    // default
        return m_designSession.getRequest().isEditable();       
    }

    /**
     * Indicates whether the data source properties may be edited by
     * a custom page in the current design session.  
     * It takes into account whether an external connection profile 
     * reference is maintained; in which case, any user edits on a
     * custom page is ignored anyway, and thus the properties are not
     * considered editable.
     * It may be used by an extended editor page for initialization
     * of its customized control to be read-only.
     * An extended editor page may choose to honor or ignore such request.
     * @return  true if the data source properties may be edited by
     *          a custom page in the current design session; false otherwise
     */
    protected boolean isSessionEditable()
    {
        return ( isEditableSessionRequested() &&
                 ! getEditingDataSource().hasLinkToProfile() );
    }
    
    /**
     * Returns the resource identifiers of the ODA consumer application, if available.
     * @return  a ResourceIdentifiers instance; may be null if none is specified
     * @since 3.0.7
     */
    protected ResourceIdentifiers getHostResourceIdentifiers()
    {
        if( ! isInOdaDesignSession() )
            return null;
        DataSourceDesign dataSourceDesign = m_designSession.getRequest().getDataSourceDesign();
        return ( dataSourceDesign != null ) ? 
                dataSourceDesign.getHostResourceIdentifiers() : null;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     */
    public boolean performOk()
    {
        // updates connection profile 
        // only if editor page was started by DSE        
        if( ! isInOdaDesignSession() )
            return super.performOk();    // done
        
        return finishEditSession();
    }
    
    /**
     * Returns the finished design session with a response
     * that contains the edited data source design.
     * <br>If the edited data source design has error,
     * the returned design session would contain a response
     * with an error session status.
     * @return  the design session with a response that
     *          contains the edited data source design
     * @throws OdaException if the method is called 
     *          in an invalid session state
     */
    public OdaDesignSession getEditSessionResponse() throws OdaException
    {
        // verify that edit session is in correct state
        if( ! isInOdaDesignSession() )
        {
            throw new OdaException( Messages.common_notInDesignSession );
        }
        
        // if response is not available, perhaps performOk is not called;
        // go ahead and trigger to finish the current edit session
        if( m_designSession.getResponse() == null )
            finishEditSession();
        
        OdaDesignSession responseSession = m_designSession;
        m_designSession = null;     // reset for next session
        return responseSession;
    }

    /**
     * Performs finish on the current ODA design session to
     * add a response with the edited data source design 
     * definition.  
     * @return  true if the edited data source is processed
     *          successfully; false otherwise
     */
    protected boolean finishEditSession()
    {
        DataSourceDesign editedDataSource = null;
        try
        {
            editedDataSource = finishDataSourceDesign();
        }
        catch( OdaException ex )
        {
            editedDataSource = null;
            // log warning about exception
            DesignerLogger logger = DesignerLogger.getInstance();
            logger.warning( sm_className, "finishEditSession",  //$NON-NLS-1$
                    "Caught exception while finishDataSourceDesign.", ex ); //$NON-NLS-1$
        }
            
        // update design session with edited data source design, which
        // could be null if error had occurred
        boolean isSessionOk = ( editedDataSource != null );
        assert( m_designSession != null );
        m_designSession.setNewResponse( isSessionOk, editedDataSource );
        
        // get designerState, if set by an extended editor page, 
        // and assign to session response
        DesignerState customState = getResponseDesignerState();
        if( customState != null )
            m_designSession.getResponse().setDesignerState( customState );

        return isSessionOk;
    }
    
    /**
     * Performs finish on the current ODA design session to
     * edit a data source design instance.
     * Calls subclass extended method to provide further
     * updates to the data source design instance.
     * @return  the edited data source design instance
     * @throws OdaException
     */
    protected DataSourceDesign finishDataSourceDesign()
        throws OdaException
    {
        if( ! isInOdaDesignSession() )
            throw new OdaException( Messages.common_notInDesignSession );
        
        // gets a copy of the data source design, and updates
        // with the property values collected by
        // this editor page
        return collectDataSourceDesign( getEditingDataSource() );
    }
    
    /**
     * Returns the custom designer state specified by 
     * an extended editor page via the corresponding setter method.
     * May return null if none is specified.
     * @return
     */
    protected DesignerState getResponseDesignerState()
    {
        return m_responseDesignerState;
    }
    
    /**
     * Allows an extended editor page 
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
     * Refresh this editor page's control display as needed to reflect
     * the latest state of the data source design being edited.
     * Refreshing may be needed when another editor page
     * has updated the editing data source design instance.
	 * @since 3.0.4
     */
    public void refresh()
    {
        refresh( getDataSourceProperties() );
    }

    /**
     * Refresh this page's control display as needed to reflect
     * the state of the specified data source design's connection properties.
     * @param customConnectionProps
     */
    protected abstract void refresh( Properties customConnectionProps );

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    public void dispose()
    {
        // calls abstract method provided by custom extension
        cleanup();
        super.dispose();
    }

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage#setPingButtonEnabled(boolean)
     */
    @Override
    public void setPingButtonEnabled( boolean enabled )
    {
        /* The enabled state setting takes effect during #createControl 
         * if this is called before the ping button is created.
         */
        m_setPingButtonEnabled = null;  // first reset previous state
        
        // saves the state if the ping button is not created yet
        if( this.btnPing == null )      
            m_setPingButtonEnabled = new Boolean( enabled );
        else
            super.setPingButtonEnabled( enabled );
    }

    /**
     * Marks the inherited Test Connection (Ping) button as visible
     * if the argument is true, and marks it invisible otherwise. 
     * <br>The visibility state setting takes effect 
     * during <code>createControl</code> if this is called before the 
     * ping button is created.
     * @param enabled   the new visibility state
     */
    protected void setPingButtonVisible( boolean visible )
    {
        m_setPingButtonVisible = null;  // first reset previous state
        
        // saves the state if the ping button is not created yet
        if( this.btnPing == null )      
            m_setPingButtonVisible = new Boolean( visible );
        else
            super.setPingButtonVisible( visible );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.PreferencePage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl( Composite parent )
    {
        super.createControl( parent );
        
        // now that all control contents are created, go ahead and 
        // override the enabled and visibility state of the inherited Test Connection ping button
        if( m_setPingButtonEnabled != null )
            super.setPingButtonEnabled( m_setPingButtonEnabled.booleanValue() );
        if( m_setPingButtonVisible != null )
            super.setPingButtonVisible( m_setPingButtonVisible.booleanValue() );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage#createTestConnectionRunnable(org.eclipse.datatools.connectivity.IConnectionProfile)
     */
    protected Runnable createTestConnectionRunnable( final IConnectionProfile profile )
    {
        return new Runnable() 
        {
            public void run() 
            {
                IConnection conn = PingJob.createTestConnection( profile );

                Throwable exception = PingJob.getTestConnectionException( conn );
                if( conn != null )
                    conn.close();
                PingJob.PingUIJob.showTestConnectionMessage( getShell(), exception );
            }
        };
    }

}
