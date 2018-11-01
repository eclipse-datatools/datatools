/*
 *************************************************************************
 * Copyright (c) 2006, 2008 Actuate Corporation.
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

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.ui.PingJob;
import org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * The core implementation of the Data Source Wizard Page base class 
 * provided in the ODA designer UI framework.  It extends the 
 * DTP connection profile wizard framework.  
 * <br>It can be used, outside of the Data Source 
 * Explorer UI, by an user to create an extended ODA data source definition 
 * with connection properties and corresponding values.
 */
public abstract class DataSourceWizardPageCore extends
        ConnectionProfileDetailsPage
{
    private Boolean m_setPingButtonVisible;
    
    /**
     * Sub-class may override the method to further update
     * the given data source design, as needed.
     * <br>Examples of custom data source design updates include 
     * setting its private properties, and
     * dynamically define a property's design attributes  
     * per design instance.
     * <br>This method is called when the wizard performs finish.
     * @param design    a data source design instance for further updates
     * @return  the updated data source design instance, or
     *      null if an error exists and unable to update the design
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

    /**
     * Refresh this page's control display as needed to reflect
     * the latest state of the data source design.
     * @since 3.0.4
     */
    protected abstract void refresh();

    /*
     * Implements base class constructor.
     */
    protected DataSourceWizardPageCore( String name )
    {
        super( name );
    }

    /*
     * Implements base class constructor.
     */
    protected DataSourceWizardPageCore( String pageName, String title,
            ImageDescriptor titleImage )
    {
        super( pageName, title, titleImage );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizardPage#getNextPage()
     */
    public IWizardPage getNextPage()
    {
        // skip profile summary page if in ODA design session
        NewDataSourceWizardBase wizard = getOdaWizard();       
        return( wizard != null && wizard.isInOdaDesignSession() ) ? 
                null : super.getNextPage();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    public boolean isPageComplete()
    {
        return isControlCreated() && super.isPageComplete();
    }

    /**
     * Returns the page's wizard container that is extended
     * in the ODA Design UI framework to handle creation
     * of a new data source design.
     * @return
     */
    protected NewDataSourceWizardBase getOdaWizard()
    {
        if( getWizard() instanceof NewDataSourceWizardBase )
            return (NewDataSourceWizardBase) getWizard();
        return null;
    }
    
    /**
     * Returns the ODA data source element id that 
     * uniquely identifies the ODA run-time data source extension
     * of the ODA driver's custom designer plug-in.
     * @return
     */
    protected String getOdaDataSourceId()
    {
        return getOdaWizard().getOdaDataSourceId();
    }
    
    /**
     * Returns the ODA driver's custom designer plug-in id that 
     * implements the DTP oda.design.ui.dataSource extension point.
     * @return
     */
    protected String getOdaDesignerId()
    {
        return getOdaWizard().getOdaDesignerId();
    }
    
    /**
     * Returns the resource identifiers of the ODA consumer application, if available.
     * @return  a ResourceIdentifiers instance; may be null if none is specified
     * @since 3.0.7
     */
    protected ResourceIdentifiers getHostResourceIdentifiers()
    {
        NewDataSourceWizardBase wizard = getOdaWizard();       
        return ( wizard != null ) ? wizard.getHostResourceIdentifiers() : null;
    }
    
    /**
     * Performs finish to
     * create a new data source design instance.
     * Calls a subclass extended method to provide further
     * updates to the given data source design instance.
     * @return  the updated data source design instance
     * @throws OdaException
     */
    public DataSourceDesign finishDataSourceDesign(
                                DataSourceDesign design )
        throws OdaException
    {
        // calls abstract method provided by custom extension
        // to further specify its data source design
        return collectDataSourceDesign( design );
    }
    
    /**
     * Allows an extended wizard page
     * to optionally assign a custom designer state, for inclusion
     * in the ODA design session response.
     * @param customDesignerState   a designer state instance
     *              that preserves the current session's internal state
     *              so that it can be restored in a subsequent design session
     */
    protected void setResponseDesignerState( DesignerState customDesignerState )
    {
        NewDataSourceWizardBase wizard = getOdaWizard();
        if( wizard != null )
            wizard.setResponseDesignerState( customDesignerState );
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
     * @see org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl( Composite parent )
    {
        // since connecting to an ODA data source does not provide additional info in DSE,
        // hide the option to auto connect for all oda data sources
        setAutoConnectOnFinishDefault( false );
        setShowAutoConnectOnFinish( false );
        setShowAutoConnect( false );    // auto connect at workbench startup
        
        super.createControl( parent );
        
        // now that all control contents are created, go ahead and 
        // override visibility of the inherited Test Connection ping button
        if( m_setPingButtonVisible != null )
            super.setPingButtonVisible( m_setPingButtonVisible.booleanValue() );
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.ui.wizards.ConnectionProfileDetailsPage#createTestConnectionRunnable(org.eclipse.datatools.connectivity.IConnectionProfile)
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
    
    /**
     * Indicates whether the data source properties may be edited by
     * a custom page in the current design session.  
     * It takes into account whether an external connection profile 
     * reference is maintained; in which case, any user edits on a
     * custom page is ignored anyway, and thus the properties are not
     * considered editable.
     * It may be used by an extended wizard page for initialization
     * of its customized control to be read-only.
     * An extended wizard page may choose to honor or ignore such request.
     * @return  true if the data source properties may be edited by
     *          a custom page in the current design session; false otherwise
     * @since 3.0.4
     */
    protected boolean isSessionEditable()
    {
        NewDataSourceWizardBase wizard = getOdaWizard();       
        if( wizard == null )
            return true;    // default
        return wizard.isSessionEditable();       
    }

}
