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

/**
 * The core implementation of the Data Set Wizard Page base class 
 * provided in the ODA designer UI framework. 
 * <br>It provides the framework to allow an user to create
 * an extended ODA data set design instance.
 */
public abstract class DataSetWizardPageCore extends WizardPage
{

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
     * Returns the data set design instance for initialization
     * of the customized control of this wizard page.
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
     * Returns the designer state instance for initialization
     * of the customized control of this wizard page.
     * @return
     */
    protected DesignerState getInitializationDesignerState()
    {
        NewDataSetWizardBase wizard = getOdaWizard();
        if( wizard == null )
            return null;
        return wizard.copyDesignerState();
    }
    
    /**
     * Returns the design session locale for initialization
     * of the customized control of this wizard page.
     * A custom page may choose to honor or ignore such request.
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
     * of the customized control of this wizard page.
     * A custom page may choose to honor or ignore such request.
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

}
