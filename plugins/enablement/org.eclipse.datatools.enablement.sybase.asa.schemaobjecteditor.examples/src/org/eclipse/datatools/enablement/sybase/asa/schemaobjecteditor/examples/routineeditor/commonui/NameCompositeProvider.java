/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
public class NameCompositeProvider implements INameCompositeProvider
{
    /**
     * Style constant for the basic layout (value is 0).
     */
    public static final int NONE = 0;

    /**
     * Style constant to show database name (value is 1&lt;&lt;1).
     */
    public static final int SHOW_DATABASE = 1 << 1;
    
    /**
     * Style constant to show comment (value is 1&lt;&lt;2).
     */
    public static final int SHOW_COMMENT = 1 << 2;

    public Text textComment = null;
    private Label labelOwnerValue = null;
    public Text text = null;

    private int style = NONE, border = NONE;
    private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
    private FormToolkit formToolkit = null;   //  @jve:decl-index=0:visual-constraint=""
    public Composite compositeName = null;
    private Label labelOwner = null;
    private Label labelName = null;
    private Label labelComment = null;

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
        GridData gridData6 = new GridData();
        gridData6.horizontalSpan = 3;
        gridData6.verticalAlignment = GridData.FILL;
        gridData6.grabExcessVerticalSpace = true;
        gridData6.grabExcessHorizontalSpace = true;
        gridData6.verticalSpan = 3;
        gridData6.heightHint = 50;
        gridData6.horizontalAlignment = GridData.FILL;
        GridData gridData5 = new GridData();
        gridData5.horizontalAlignment = GridData.BEGINNING;
        gridData5.verticalAlignment = GridData.BEGINNING;
        GridData gridData4 = new GridData();
        gridData4.horizontalAlignment = GridData.BEGINNING;
        gridData4.horizontalIndent = 0;
        gridData4.verticalAlignment = GridData.CENTER;
        GridData gridData2 = new GridData();
        gridData2.horizontalAlignment = GridData.BEGINNING;
        gridData2.horizontalIndent = 20;
        gridData2.verticalAlignment = GridData.CENTER;
        GridData gridData1 = new GridData();
        gridData1.grabExcessHorizontalSpace = true;
        gridData1.verticalAlignment = GridData.CENTER;
        gridData1.horizontalAlignment = GridData.FILL;
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 4;
        gridLayout.makeColumnsEqualWidth = false;
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = GridData.FILL;
        compositeName = getFormToolkit().createComposite(sShell);
        compositeName.setLayoutData(gridData);
        compositeName.setLayout(gridLayout);
        labelOwner = getFormToolkit().createLabel(compositeName, Messages.NameCompositeProvider_owner_name);
        labelOwner.setLayoutData(gridData4);
        labelOwner.setForeground(formToolkit.getColors().getColor(FormColors.TITLE));
        labelOwnerValue = getFormToolkit().createLabel(compositeName, Messages.NameCompositeProvider_owner_name_place_holder);
        labelName = getFormToolkit().createLabel(compositeName, Messages.NameCompositeProvider_name);
        labelName.setForeground(formToolkit.getColors().getColor(FormColors.TITLE));
        labelName.setLayoutData(gridData2);
        text = getFormToolkit().createText(compositeName, null, SWT.SINGLE | border );
        text.setToolTipText(Messages.NameCompositeProvider_name_tooltip);
        text.setLayoutData(gridData1);
        labelComment = getFormToolkit().createLabel(compositeName, Messages.NameCompositeProvider_comment);
        labelComment.setForeground(formToolkit.getColors().getColor(FormColors.TITLE));
        labelComment.setLayoutData(gridData5);
        textComment = getFormToolkit().createText(compositeName, null, border | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL );
        textComment.setToolTipText(Messages.NameCompositeProvider_comment_tooltip);
        textComment.setLayoutData(gridData6);
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
        NameCompositeProvider thisClass = new NameCompositeProvider();
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

    public Composite getComposite(Composite parent, FormToolkit formToolkit, int style)
    {
        if (compositeName == null)
        {
            this.formToolkit = formToolkit;
            this.style = style;
            border = style & BORDER;
            createSShell();
        }
        compositeName.setParent(parent);
        return compositeName;
    }

    public Text getNameControl()
    {
        return text;
    }

    public void setValues(String dbName, String owner, String name, String tableName, String tableOwner)
    {
        labelOwnerValue.setText(get(owner));
        text.setText(get(name));
    }

    private String get(String s)
    {
        return (s == null)? "": s; //$NON-NLS-1$
    }


}  //  @jve:decl-index=0:visual-constraint="10,10"
