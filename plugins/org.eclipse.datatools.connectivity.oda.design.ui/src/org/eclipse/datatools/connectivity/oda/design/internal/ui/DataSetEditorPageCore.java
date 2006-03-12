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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
        if( page.getWizard() instanceof NewDataSetWizardBase )
        {
            DataSetDesign editDataSetDesign =
                ( (NewDataSetWizardBase) page.getWizard()).getEditingDataSet();
            assert( editDataSetDesign instanceof IAdaptable );
            setElement( (IAdaptable) editDataSetDesign );
        }

        m_wizardPage = page;       
    }

    private DataSetEditorPageCore()
    {
        super();
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents( Composite parent )
    {
        m_wizardPage.createControl( parent );
        return m_wizardPage.getControl();
    }

    /**
     * Returns the unique id of this page within a data set dialog.
     * @return the page unique id
     */
    public String getPageId()
    {
        return m_wizardPage.getName();
    }

    /**
     * Returns the path of the page in a data set preference dialog.
     * @return the page path;
     *          may be null if none is specified
     */
    public String getPagePath()
    {
        return m_wizardPage.getPagePath();
    }

    /**
     * Returns the relative path to an icon that may 
     * be used in the UI in addition to the page's title.
     * @return the title icon file path;
     *          may be null if none is specified
     */
    public String getIconPath()
    {
        return m_wizardPage.getIconPath();
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
        return m_wizardPage.getIconDescriptor();
    }
    
    /**
     * Indicates whether this page should be selected
     * and has initial focus when used in a preference dialog. 
     * @return true if this page should have initial focus; 
     *              false otherwise
     */
    public boolean hasInitialFocus()
    {
        return m_wizardPage.hasInitialFocus();
    }

}
