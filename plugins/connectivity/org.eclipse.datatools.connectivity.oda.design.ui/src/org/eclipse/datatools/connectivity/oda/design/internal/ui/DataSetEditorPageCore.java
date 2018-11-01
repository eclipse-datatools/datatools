/*
 *************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.DesignSessionResponse;
import org.eclipse.datatools.connectivity.oda.design.OdaDesignSession;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * The core implementation of the Data Set Editor Page base class 
 * provided by the ODA design UI framework,  
 * to allow an user to edit
 * an extended ODA data set design instance.
 * <br>It adapts from a data set wizard page contributed
 * by an ODA design ui extension, to a PropertyPage
 * for use in an ODA host designer's preference dialog.
 */
public class DataSetEditorPageCore extends PropertyPage
{
    private DataSetWizardPageCore m_wizardPage;
    
    /*
     * Constructor to adapt a data set wizard page
     * to a property page.
     */
    protected DataSetEditorPageCore( DataSetWizardPageCore page )
    {
        super();

        if( page.getWizard() instanceof DataSetWizardBase )
        {
            DataSetDesign editDataSetDesign =
                page.getOdaWizard().getEditingDataSet();
            assert( editDataSetDesign instanceof IAdaptable );
            setElement( (IAdaptable) editDataSetDesign );
        }

        m_wizardPage = page;       
    }

    DataSetEditorPageCore()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents( Composite parent )
    {
        noDefaultAndApplyButton();
        getCustomPage().createControl( parent );
        
        Control wrappedControl = getCustomPage().getControl();
        setHelpContext( wrappedControl );
        return wrappedControl;
    }

    /**
     * Sets the help context id, if exists, of the given wrapped page control
     * on this wrapper's top level control.
     */
    private void setHelpContext( Control wrappedControl )
    {
        if( wrappedControl == null )
            return;     // nothing to set
        
        Object wrappedPageHelpContextId = 
            wrappedControl.getData( "org.eclipse.ui.help" ); //$NON-NLS-1$
        if( wrappedPageHelpContextId != null && wrappedPageHelpContextId instanceof String )
            PlatformUI.getWorkbench().getHelpSystem()
                .setHelp( getControl(), (String) wrappedPageHelpContextId );
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.PreferencePage#setContainer(org.eclipse.jface.preference.IPreferencePageContainer)
     */
    public void setContainer( IPreferencePageContainer container )
    {
        super.setContainer( container );
        getCustomPage().setEditorContainer( container );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#getTitle()
     */
    public String getTitle() 
    {
		return getCustomPage().getTitle();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#getMessage()
	 */
	public String getMessage() 
	{
		return getCustomPage().getMessage();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#getMessageType()
	 */
	public int getMessageType() 
	{
		return getCustomPage().getMessageType();
	}

	/* (non-Javadoc)
     * @see org.eclipse.jface.preference.PreferencePage#okToLeave()
     */
    public boolean okToLeave()
    {
        return super.okToLeave() && getCustomPage().canLeave();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.PreferencePage#performHelp()
     */
    public void performHelp()
    {
        getCustomPage().performHelp();
    }

    /**
     * Returns the unique id of this page within a data set dialog.
     * @return the page unique id
     */
    public String getPageId()
    {
        return getCustomPage().getName();
    }

    /**
     * Returns the path of the page in a data set preference dialog.
     * @return the page path;
     *          may be null if none is specified
     */
    public String getPagePath()
    {
        return getCustomPage().getPagePath();
    }

    /**
     * Returns the relative path of an icon file that may 
     * be used in the UI in addition to the page's title.
     * @return the title icon file path;
     *          may be null if none is specified
     */
    public String getIconFilePath()
    {
        return getCustomPage().getIconFilePath();
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
        return getCustomPage().getIconDescriptor();
    }
    
    /**
     * Indicates whether this page should be selected
     * and has initial focus when used in a preference dialog. 
     * @return true if this page should have initial focus; 
     *              false otherwise
     */
    public boolean hasInitialFocus()
    {
        return getCustomPage().hasInitialFocus();
    }

    /**
     * Returns the most current data set design being edited
     * by this property page.
     * @return
     */
    protected DataSetDesign getEditingDataSet()
    {
        DataSetDesign changedDataSetDesign = getChangedDataSet();
        if( changedDataSetDesign == null )
            return (DataSetDesign) getElement();     // mine is current
        
        // update this property page's adaptable element
        assert( changedDataSetDesign instanceof IAdaptable );
        setElement( (IAdaptable) changedDataSetDesign );
        
        return changedDataSetDesign;
    }

    /**
     * Returns the more current editing data set if different
     * from this property page's adaptable element.
     * It may be different if another editor page
     * has updated the editing data set design instance.
     * Its use is private; the method is useful only when
     * called before this page's adaptable element is updated.
     * @return  the changed data set design, or 
     *          null if no changes are found.
     */
    private DataSetDesign getChangedDataSet()
    {
        IAdaptable element = getElement();
        assert( element instanceof DataSetDesign );
        DataSetDesign myDesign = (DataSetDesign) element;

        DataSetDesign editedDataSetDesign =
            getCustomPage().getOdaWizard().getEditingDataSet();

        // compare their content if not the same instance
        if( myDesign != editedDataSetDesign &&
            ! EcoreUtil.equals( myDesign, editedDataSetDesign ) )
            return editedDataSetDesign;   
        return null;    // no changed data set design
    }   
    
    /**
     * Returns the most current design session response 
     * being edited, by collecting relevant updates from 
     * the corresponding wrapped data set page.
     * @return the current design session response that contains
     *         the data set design instance and designer state
     *         updated by this wrapped data set page
     */
    public DesignSessionResponse collectPageResponse()
    {
        DataSetDesign updatedDesign = collectDataSetDesign(); 

        // assign data set design in a session response
        boolean isSessionOk = ( updatedDesign != null );
        OdaDesignSession nestedSession = 
            DesignFactory.eINSTANCE.createOdaDesignSession();
        nestedSession.setNewResponse( isSessionOk, updatedDesign );

        // assign collected response state to the session response
        DesignSessionResponse pageResponse = nestedSession.getResponse();
        getCustomPage().getOdaWizard().updateResponseWithState( pageResponse );
        return pageResponse;
    }
    
    /**
     * Returns the most current data set design instance 
     * being edited, by collecting relevant updates from 
     * the corresponding wrapped data set page.
     * @return the data set design instance updated 
     *          by this wrapped data set page
     */
    private DataSetDesign collectDataSetDesign()
    {
        // first get the wrapped page to update the central
        // copy of editing data set design
        getCustomPage().getOdaWizard()
            .collectDataSetDesignFromPage( getCustomPage() );
        
        // update my adaptable element if changed,
        // and return this page's adaptable element
        return getEditingDataSet();
    }

    /**
     * Refresh this editor page's adaptable element and
     * control display as needed to reflect
     * the latest state of the data set design being edited.
     * Refreshing may be needed when another editor page
     * has updated the editing data set design instance.
     */
    public void refresh()
    {
        if( m_wizardPage != null )
            m_wizardPage.refresh( getEditingDataSet() );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    public void dispose()
    {
        super.dispose();
        m_wizardPage = null;
    }

    /**
     * Validates that the wrapped custom wizard page exists, and returns it.
     * Throws IllegalStateException if no associated custom wizard page.
     */
    protected DataSetWizardPageCore getCustomPage()
    {
        if( m_wizardPage == null )
            throw new IllegalStateException( Messages.common_notInDesignSession );
    
        return m_wizardPage;
    }
    
}
