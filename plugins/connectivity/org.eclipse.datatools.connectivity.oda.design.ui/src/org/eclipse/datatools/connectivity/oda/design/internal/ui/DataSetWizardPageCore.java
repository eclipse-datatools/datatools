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

import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.Locale;
import org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers;
import org.eclipse.datatools.connectivity.oda.design.SessionStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The core implementation of the Data Set Wizard Page base class 
 * provided in the ODA designer UI framework. 
 * <br>It provides the framework to allow an user to create
 * an extended ODA data set design instance.
 */
public abstract class DataSetWizardPageCore extends WizardPage
{
    private String m_path;
    private String m_icon;
    private boolean m_hasInitialFocus = false;
    private ImageDescriptor m_iconDescriptor;
    
    private IPreferencePageContainer m_editorContainer;

    /**
     * Performs updates based on the specified
     * data set design, as needed.
     * <br>Examples of custom data set design updates include 
     * setting its data set query parameters, result set definition,
     * private properties, and
     * dynamically define a public property's design attributes  
     * per design instance.
     * @param design    a data set design instance for further updates
     * @return  the updated data set design instance, or
     *      null if an error exists and unable to update the design
     */
    protected abstract DataSetDesign collectDataSetDesign( 
            DataSetDesign design );

    /**
     * Refresh this page's control display as needed to reflect
     * the state of the specified data set design.
     * @param dataSetDesign
     */
    protected abstract void refresh( DataSetDesign dataSetDesign );

    /**
     * Checks whether it is alright to leave this page, 
     * when used as an editor page in a preference dialog. 
     * @return true to allow the page flip; or
     *         false to abort page flipping and the current page 
     *         remains visible
     */
    protected abstract boolean canLeave();
    
    /**
     * Cleans up before the page is disposed.
     * Default implementation does nothing.  Sub-class
     * may override to clean up custom operations such as
     * releasing resources associated to its data set query execution.
     */
    protected abstract void cleanup();

    
    /*
     * Implements base class constructor.
     */
    protected DataSetWizardPageCore( String pageName )
    {
        super( pageName );
    }

    /*
     * Implements base class constructor.
     */
    protected DataSetWizardPageCore( String pageName, String title,
            ImageDescriptor titleImage )
    {
        super( pageName, title, titleImage );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl( Composite parent )
    {
        // base class does nothing; subclass may override
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#setMessage(java.lang.String, int)
     */
    public void setMessage( String newMessage, int newType )
    {
        super.setMessage( newMessage, newType );
        
        // update editor container message if this 
        // is being used in a preference dialog
        if( getEditorContainer() != null )
            getEditorContainer().updateMessage();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#setErrorMessage(java.lang.String)
     */
    public void setErrorMessage( String newMessage )
    {
        super.setErrorMessage( newMessage );
        
        // if this is being used in a preference dialog,
        // set the message with an error type 
        if( getEditorContainer() != null )
            setMessage( newMessage, IMessageProvider.ERROR );
    }

    /**
     * Returns the editor container of this page
     * when wrapped as an editor page in a preference dialog.
     * @return  the preference page container of 
     *          the editor page that wraps this wizard page;
     *          may be <code>null</code> if this page is not used in a
     *          preference dialog, or if this
     *          page has yet to be added to a container
     */
    protected IPreferencePageContainer getEditorContainer()
    {
        return m_editorContainer;
    }

    /**
     * Sets or clears the editor container of this page
     * when used as an editor page in a preference dialog. 
     * @param container the preference page container of 
     *          the editor page that wraps this wizard page.
     */
    void setEditorContainer( IPreferencePageContainer container )
    {
        m_editorContainer = container;
    }

    /**
     * Returns the path of the page in a data set preference dialog.
     * @return the page path;
     *          may be null if none is specified
     */
    String getPagePath()
    {
        return m_path;
    }

    /**
     *  Set the path of the page in a data set preference dialog.
     * @param path
     */
    protected void setPagePath( String path )
    {
        m_path = path;
    }

    /**
     * Returns the relative path of an icon file that may 
     * be used in the UI in addition to the page's title.
     * @return the title icon file path;
     *          may be null if none is specified
     */
    public String getIconFilePath()
    {
        return m_icon;
    }

    /**
     * Set the relative path of an icon file that may 
     * be used in the UI in addition to the page's title.
     * @param iconFilePath the relative path of the icon file, 
     *                  relative to the root of the plug-in
     */
    protected void setIconFilePath( String iconFilePath )
    {
        m_icon = iconFilePath;
    }
    
    /**
     * Returns the image descriptor of an icon
     * that may be used in the UI in addition to the page's title.
     * @return  the image descriptor of the 
     *          dataSetPage.icon extension attribute value;
     *          may be null if none is specified, or unable to locate icon file
     */
    public ImageDescriptor getIconDescriptor()
    {
        if( m_iconDescriptor != null )
            return m_iconDescriptor;
        
        // get the descriptor from the icon file
        String iconPath = getIconFilePath();
        if( iconPath == null || iconPath.length() == 0 )
            return null;
        
        DataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return null;
        
        try
        {
            m_iconDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(
                    	wizard.getOdaDesignerPluginId(),
                    	iconPath );
        }
        catch( RuntimeException e )
        {
            // ignore
            e.printStackTrace();
        }
        return m_iconDescriptor;
    }

    /**
     * Indicates whether this page should be selected
     * and has initial focus when used in a preference dialog. 
     * @return true if this page should have initial focus; 
     *              false otherwise
     */
    boolean hasInitialFocus()
    {
        return m_hasInitialFocus;
    }
    
    /**
     * Specifies whether this page should be selected
     * and has initial focus when used in a preference dialog. 
     */
    protected void setHasInitialFocus()
    {
        m_hasInitialFocus = true; 
    }
    
    /**
     * Returns a copy of the original data set design instance specified
     * in the design session request.  It may be used for initialization
     * of the customized control of this extended page.
     * A copy is returned to prevent updates to the request design.
     * @return
     */
    protected DataSetDesign getInitializationDesign()
    {
        DataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return null;
        return wizard.copyRequestDataSetDesign();
    }

    /**
     * Returns the most current data set design instance being edited.
     * A data set design instance may be edited
     * multiple times by an extended page when a user flips among 
     * pages in a dialog
     * within a data set design session.
     * @return
     */
    protected DataSetDesign getEditingDesign()
    {
        DataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return null;
        return wizard.getEditingDataSet();        
    }
    
    /**
     * Returns a copy of the original designer state specified
     * in the design session request.
     * It provides initialization data for the extended custom page(s)
     * to restore the state of a previous design session.
     * A copy is returned to prevent updates to the request design.
     * @return
     */
    protected DesignerState getInitializationDesignerState()
    {
        DataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return null;
        return wizard.copyRequestDesignerState();
    }

    /**
     * Returns the most current designer state within a design session.
     * May be null if no previous session or none
     * was specified by the custom page(s).
     * @return   a designer state instance that preserves
     *           the last internal state of the extended custom page(s)
     *           so that it can be restored when any custom page is
     *           re-activated in a dialog
     */
    protected DesignerState getEditingDesignerState()
    {
        DataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return null;
        return wizard.getResponseDesignerState();        
    }
    
    /**
     * Returns a copy of the session locale specified
     * in the design session request.
     * It provides initialization data for this extended wizard page
     * to adopt the requested locale.
     * An extended page may choose to honor or ignore such request.
     * A copy is returned to prevent updates to the request design.
     * @return
     */
    protected Locale getInitializationLocale()
    {
        DataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return null;
        return wizard.copySessionLocale();
    }
    
    /**
     * Indicates whether the current design session should be
     * an editable session or read-only.
     * It may be used for initialization
     * of the customized control of this extended wizard page.
     * An extended page may choose to honor or ignore such request.
     * @return
     */
    protected boolean isSessionEditable()
    {
        DataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return true;    // default
        return wizard.isSessionEditable();       
    }
    
    /**
     * Returns the resource identifiers of the ODA consumer application, if available.
     * @return  a ResourceIdentifiers instance; may be null if none is specified
     * @since 3.0.7
     */
    protected ResourceIdentifiers getHostResourceIdentifiers()
    {
        DataSetWizardBase wizard = getOdaWizard();
        return ( wizard != null ) ? wizard.getHostResourceIdentifiers() : null;
    }
    
    /**
     * Returns the page's wizard container provided
     * in the ODA Design UI framework to handle creation
     * of a new data set design.
     * @return
     */
    protected DataSetWizardBase getOdaWizard()
    {
        if( getWizard() instanceof DataSetWizardBase )
            return (DataSetWizardBase) getWizard();
        return null;
    }
    
    /**
     * Performs finish to
     * create or edit a data set design instance,
     * and corresponding session response state.
     * Calls subclass extended methods to provide further
     * updates to the given data set design instance.
     * @return  the updated data set design instance
     */
    protected DataSetDesign finishDataSetDesign( DataSetDesign design )
    {
        // collects custom session response state
        // from an extended page
        collectResponseState();
        
        // calls abstract method provided by custom extension
        // to further specify its data set design
        return collectDataSetDesign( design );
    }

    /**
     * Collects optional session response state 
     * from an extended page.
     * Response state info includes custom session status 
     * and designer state instance. 
     * Default implementation in base class does not specify 
     * any custom response state.
     * Sub-class may override.
     */
    protected void collectResponseState()
    {
        setResponseSessionStatus( null );
        setResponseDesignerState( null );
    }

    /**
     * For use by an extended data set page
     * to optionally specify or clear a custom session status, 
     * for inclusion in the ODA design session response.
     * @param status the status of the current session to
     *              indicate how an ODA host designer should process
     *              a design session response
     */
    protected void setResponseSessionStatus( SessionStatus status )
    {
        DataSetWizardBase wizard = getOdaWizard();
        if( wizard != null )
            wizard.setResponseSessionStatus( status );        
    }

    /**
     * For use by an extended wizard page
     * to optionally assign or clear a customized designer state, 
     * for inclusion in the ODA design session response.
     * @param customDesignerState   a designer state instance
     *              that preserves the current session's internal state
     *              so that it can be restored in a subsequent design session
     */
    protected void setResponseDesignerState( DesignerState customDesignerState )
    {
        DataSetWizardBase wizard = getOdaWizard();
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
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#performHelp()
     */
    public void performHelp( )
	{
		getControl().notifyListeners( SWT.Help, new Event() );
	}

}
