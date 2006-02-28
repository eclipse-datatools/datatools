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

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import java.util.Iterator;
import java.util.Properties;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.ui.wizards.ProfileDetailsPropertyPage;

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

    /**
     * Sub-class may override this method to further update
     * the given data source design, as needed.
     * <br>Examples of custom data source design updates include 
     * setting its private properties, and
     * dynamically define a property's design attributes  
     * per design instance.
     * <br>This method is called when performing finish on a
     * data source editing session.
     * @param design    a data source design instance for further updates
     * @return  the updated data source design instance
     */
    protected abstract DataSourceDesign collectDataSourceDesign( 
                                    DataSourceDesign design );

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
        if( props != null )   // associated with a profile
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

    /**
     * Provides a new data source design that can be edited
     * and applied in a design session's response.
     * The editing data source is deep copied from the one
     * provided in the design session's request.
     * @return
     */
    protected DataSourceDesign getEditingDataSource()
    {
        IAdaptable element = getElement();
        if( element == null || 
            ! ( element instanceof AdaptableDataSourceProfile ) )
        {
            return DesignFactory.eINSTANCE.createDataSourceDesign();
        }
        
        // should have thrown exception within init if it failed

        DataSourceDesign dataSourceDesign =
            ((AdaptableDataSourceProfile) element).getDataSourceDesign();
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
        if( getElement() != null )
        {
            if( getElement() instanceof AdaptableDataSourceProfile &&
                m_designSession == requestSession  )
                return;     // already initialized
        }
            
        // associate a copy of the request's data source design 
        // as the element being edited in this page
        setElement( DesignerUtil.getAdaptableDataSourceDesign( 
                                              requestSession ) );
    
        // hold on design session info till finish editing
        m_designSession = requestSession;   
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
            throw new OdaException( "Invalid state found in design session" );
        }
        
        // if no response is not available, perhaps performOk is not called;
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
        catch( OdaException e )
        {
            // TODO error handling
            editedDataSource = null;
        }
            
        // update design session with edited data source design, which
        // could be null if error had occurred
        boolean isSessionOk = ( editedDataSource != null );
        assert( m_designSession != null );
        m_designSession.setNewResponse( isSessionOk, editedDataSource );
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
            throw new OdaException( "Not editing a data source." );
        
        // gets a copy of the data source design, and updates
        // with the pubic properties collected by
        // this editor page
        DataSourceDesign editedDesign = getEditingDataSource();

        editedDesign.setPublicProperties(
                DesignSessionUtil.createDataSourcePublicProperties( 
                        editedDesign.getOdaExtensionDataSourceId(),
                        collectProperties() ));

        // calls abstract method provided by custom extension
        // to further specify its data source design
        return collectDataSourceDesign( editedDesign );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    public void dispose()
    {
        // calls abstract method provided by custom extension
        cleanup();
        super.dispose();
    }

}
