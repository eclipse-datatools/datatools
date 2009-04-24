/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 * @author Hui Cao
 * 
 */
public class TriggerEventsCompositeProvider
{

    private Shell sShell = null;
    private FormToolkit formToolkit = null;   //  @jve:decl-index=0:visual-constraint=""
    private Composite compositeEvents = null;
    public Button checkInsert = null;
    public Button checkUpdate = null;
    public Button checkDelete = null;

    /**
     * This method initializes formToolkit  
     *  
     * @return org.eclipse.ui.forms.widgets.FormToolkit 
     */
    private FormToolkit getFormToolkit()
    {
        if (formToolkit == null)
        {
            formToolkit = new PseudoFormToolkit(Display.getCurrent());
        }
        return formToolkit;
    }

    /**
     * This method initializes compositeEvents	
     *
     */
    private void createCompositeEvents()
    {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.verticalAlignment = GridData.CENTER;
        compositeEvents = getFormToolkit().createComposite(sShell);
        compositeEvents.setLayoutData(gridData);
        compositeEvents.setLayout(gridLayout);
        checkInsert = getFormToolkit().createButton(compositeEvents, Messages.wizard_createTR_page1_name_label_insert, SWT.CHECK);
        checkInsert.setToolTipText(Messages.wizard_createTR_page1_name_label_insert_tooltip);
        checkUpdate = getFormToolkit().createButton(compositeEvents, Messages.wizard_createTR_page1_name_label_update, SWT.CHECK);
        checkUpdate.setToolTipText(Messages.wizard_createTR_page1_name_label_update_tooltip);
        checkDelete = getFormToolkit().createButton(compositeEvents, Messages.wizard_createTR_page1_name_label_delete, SWT.CHECK);
        checkDelete.setToolTipText(Messages.wizard_createTR_page1_name_label_delete_tooltip);
        
        checkInsert.setEnabled(false);
        checkUpdate.setEnabled(false);
        checkDelete.setEnabled(false);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

        /* Before this is run, be sure to set up the launch configuration (Arguments->VM Arguments)
         * for the correct SWT library path in order to run with the SWT dlls. 
         * The dlls are located in the SWT plugin jar.  
         * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
         *       installation_directory\plugins\org.eclipse.swt.win32_3.1.0.jar
         */
        Display display = Display.getDefault();
        TriggerEventsCompositeProvider thisClass = new TriggerEventsCompositeProvider();
        thisClass.createSShell();
        thisClass.sShell.open();
        while (!thisClass.sShell.isDisposed())
        {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

    /**
     * This method initializes sShell
     */
    private void createSShell()
    {
        sShell = new Shell();
        sShell.setText("Shell");
        createCompositeEvents();
        sShell.setSize(new Point(300, 200));
        sShell.setLayout(new GridLayout());
    }

    public Composite getComposite(Composite parent, FormToolkit formToolkit, int style)
    {
        if (compositeEvents == null)
        {
            this.formToolkit = formToolkit;
            createSShell();
        }
        compositeEvents.setParent(parent);
        return compositeEvents;
    }

}
