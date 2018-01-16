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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 * @author Hui Cao
 * 
 */
public class FullNameCompositeProvider implements INameCompositeProvider
{
    public Label labelDBValue = null;
    public Label labelOwnerValue = null;
    public Text textName = null;

    private int style = NONE, border = NONE;
    private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
    private FormToolkit formToolkit = null;   //  @jve:decl-index=0:visual-constraint=""
    public Composite compositeName = null;
    private Label labelDB = null;
    private Label labelOwner = null;
    private Label labelName = null;
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
     * This method initializes compositeName	
     *
     */
    private void createCompositeName()
    {
        GridData gridData4 = new GridData();
        gridData4.horizontalAlignment = GridData.END;
        gridData4.horizontalIndent = 20;
        gridData4.verticalAlignment = GridData.CENTER;
        GridData gridData3 = new GridData();
        gridData3.horizontalAlignment = GridData.END;
        gridData3.verticalAlignment = GridData.CENTER;
        GridData gridData2 = new GridData();
        gridData2.horizontalAlignment = GridData.END;
        gridData2.horizontalIndent = 20;
        gridData2.verticalAlignment = GridData.CENTER;
        GridData gridData1 = new GridData();
        gridData1.grabExcessHorizontalSpace = true;
        gridData1.verticalAlignment = GridData.CENTER;
        gridData1.horizontalAlignment = GridData.FILL;
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 6;
        gridLayout.makeColumnsEqualWidth = false;
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = GridData.FILL;
        compositeName = getFormToolkit().createComposite(sShell);
        compositeName.setLayoutData(gridData);
        compositeName.setLayout(gridLayout);
        labelDB = getFormToolkit().createLabel(compositeName, Messages.FullNameCompositeProvider_database_name);
        labelDB.setLayoutData(gridData3);
        labelDB.setForeground(formToolkit.getColors().getColor(FormColors.TITLE));//TODO PseudoFormToolkit
        labelDBValue = getFormToolkit().createLabel(compositeName, Messages.FullNameCompositeProvider_dbname_place_holder);
        labelOwner = getFormToolkit().createLabel(compositeName, Messages.FullNameCompositeProvider_owner_name);
        labelOwner.setLayoutData(gridData4);
        labelOwner.setForeground(formToolkit.getColors().getColor(FormColors.TITLE));
        labelOwnerValue = getFormToolkit().createLabel(compositeName, Messages.FullNameCompositeProvider_ownername_place_holder);
        labelName = getFormToolkit().createLabel(compositeName, Messages.FullNameCompositeProvider_name);
        labelName.setLayoutData(gridData2);
        labelName.setForeground(formToolkit.getColors().getColor(FormColors.TITLE));
        textName = getFormToolkit().createText(compositeName, null, SWT.SINGLE | border);
        textName.setToolTipText(Messages.FullNameCompositeProvider_name_tooltip);
        textName.setLayoutData(gridData1);
        
        getFormToolkit().paintBordersFor(compositeName);
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
        FullNameCompositeProvider thisClass = new FullNameCompositeProvider();
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
        sShell.setText("Shell"); //$NON-NLS-1$
        createCompositeName();
        sShell.setSize(new Point(518, 158));
        sShell.setLayout(new GridLayout());
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.routine.components.INameCompositeProvider#getComposite(org.eclipse.swt.widgets.Composite, org.eclipse.ui.forms.widgets.FormToolkit, int)
     */
    public Composite getComposite(Composite parent, FormToolkit formToolkit, int style)
    {
        if (compositeName == null)
        {
            this.formToolkit = formToolkit;
            this.style = style;
            createSShell();
        }
        compositeName.setParent(parent);
        return compositeName;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.routine.components.INameCompositeProvider#setValues(java.lang.String, java.lang.String, java.lang.String)
     */
    public void setValues(String dbName, String owner, String name, String tableName, String tableOwner)
    {
        labelDBValue.setText(get(dbName));
        labelOwnerValue.setText(get(owner));
        textName.setText(get(name));
    }
    
    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.routine.components.INameCompositeProvider#getNameControl()
     */
    public Text getNameControl()
    {
        return textName;
    }

    private String get(String s)
    {
        return (s == null)? "": s; //$NON-NLS-1$
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
