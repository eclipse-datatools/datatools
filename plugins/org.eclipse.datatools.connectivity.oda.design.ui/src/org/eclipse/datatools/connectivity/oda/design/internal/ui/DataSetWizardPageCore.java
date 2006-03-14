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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignerState;
import org.eclipse.datatools.connectivity.oda.design.Locale;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
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

    /**
     * Sub-class may override the method to further update
     * the given data set design, as needed.
     * <br>Examples of custom data set design updates include 
     * setting its data set query parameters, result set definition,
     * private properties, and
     * dynamically define a public property's design attributes  
     * per design instance.
     * @param design    a data set design instance for further updates
     * @return  the updated data set design instance
     */
    protected abstract DataSetDesign collectDataSetDesign( 
            DataSetDesign design );

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
        
        NewDataSetWizardBase wizard = getOdaWizard();
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
     * Returns a copy of the data set design instance for initialization
     * of the customized control of this extended wizard page.
     * A copy is returned to prevent updates to the request design.
     * @return
     */
    protected DataSetDesign getInitializationDesign()
    {
        NewDataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return null;
        return wizard.copyRequestDataSetDesign();
    }

    /**
     * Returns a copy of the designer state specified
     * in the design session request.
     * It provides initialization data for this extended wizard page
     * to restore the state of a previous design session.
     * A copy is returned to prevent updates to the request design.
     * @return
     */
    protected DesignerState getInitializationDesignerState()
    {
        NewDataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return null;
        return wizard.copyRequestDesignerState();
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
        NewDataSetWizardBase wizard = getOdaWizard();
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
        NewDataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return true;    // default
        return wizard.isSessionEditable();       
    }
    
    /**
     * Returns the page's wizard container provided
     * in the ODA Design UI framework to handle creation
     * of a new data set design.
     * @return
     */
    protected NewDataSetWizardBase getOdaWizard()
    {
        if( getWizard() instanceof NewDataSetWizardBase )
            return (NewDataSetWizardBase) getWizard();
        return null;
    }
    
    /**
     * Performs finish to
     * create or edit a data set design instance.
     * Calls a subclass extended method to provide further
     * updates to the given data set design instance.
     * @return  the updated data set design instance
     * @throws OdaException
     */
    public DataSetDesign finishDataSetDesign(
                                DataSetDesign design )
        throws OdaException
    {
        // calls abstract method provided by custom extension
        // to further specify its data set design
        return collectDataSetDesign( design );
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
        NewDataSetWizardBase wizard = getOdaWizard();
        if( wizard != null )
            wizard.setResponseDesignerState( customDesignerState );
    }

}
