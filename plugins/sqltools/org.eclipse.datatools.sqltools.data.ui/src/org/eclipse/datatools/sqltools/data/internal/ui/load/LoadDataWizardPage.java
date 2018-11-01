/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.ui.load;

import org.eclipse.datatools.sqltools.data.internal.ui.DataUIPlugin;
import org.eclipse.datatools.sqltools.data.internal.ui.FileFormatWizardPage;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


public class LoadDataWizardPage extends FileFormatWizardPage
{
    
    protected Button replaceCheckbox;
    
    protected static final String SETTING_REPLACE = "Replace data"; //$NON-NLS-1$
    
    public LoadDataWizardPage(String pageName)
    {
        super(pageName, false);
    }
    
    protected void createControl1(Composite parent)
    {
        super.createControl1(parent);
        
        Composite composite = (Composite)getControl();
        replaceCheckbox = new Button(composite, SWT.CHECK);
        replaceCheckbox.setText(Messages.getString("LoadDataWizardPage.ReplaceData")); //$NON-NLS-1$
        replaceCheckbox.setSelection(true);
        GridData gd = new GridData();
        gd.horizontalSpan = 3;
        replaceCheckbox.setLayoutData(gd);
    }
    
    public boolean getReplace()
    {
        return replaceCheckbox.getSelection();
    }
    
    protected void loadSettings()
    {
        super.loadSettings();
        
        IDialogSettings settings = DataUIPlugin.getDefault().getDialogSettings().getSection(SETTINGS_SECTION_NAME);
        
        if (settings!=null) {
            if (settings.get(SETTING_REPLACE)!=null)
                replaceCheckbox.setSelection( settings.getBoolean(SETTING_REPLACE) );
        }
    }
    
    public void saveSettings()
    {
        super.saveSettings();
        
        IDialogSettings settings = DataUIPlugin.getDefault().getDialogSettings().getSection(SETTINGS_SECTION_NAME);
        
        settings.put(SETTING_REPLACE, replaceCheckbox.getSelection());
    }
    
    public String getHelpID() {
    	return "org.eclipse.wst.rdb.data.ui.infopop.load"; //$NON-NLS-1$
    }
    
}
