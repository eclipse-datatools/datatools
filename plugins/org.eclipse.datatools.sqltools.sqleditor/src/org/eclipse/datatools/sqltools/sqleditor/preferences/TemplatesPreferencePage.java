/** Created on 2004-9-13
 *
 * Copyright (c) Sybase, Inc. 2004-2006   
 * All rights reserved.                                    
 */
package org.eclipse.datatools.sqltools.sqleditor.preferences;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.templates.TemplatePreferencePage;

/**
 * @author Hui Cao
 *
 */

/**
 * @see org.eclipse.jface.preference.PreferencePage
 */
public class TemplatesPreferencePage extends TemplatePreferencePage implements IWorkbenchPreferencePage 
{

    public TemplatesPreferencePage() 
    {
        setPreferenceStore(SQLEditorPlugin.getDefault().getPreferenceStore());
        setTemplateStore(SQLEditorPlugin.getDefault().getTemplateStore());
        setContextTypeRegistry(SQLEditorPlugin.getDefault().getTemplateContextTypeRegistry());
    }

    protected boolean isShowFormatterSetting() 
    {
        //since we have no formatter
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent)
    {
        super.createControl(parent);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), HelpUtil.getContextId(IHelpContextIds.PREFERENCES_TEMPLATES, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public boolean performOk() 
    {
        boolean ok= super.performOk();

        SQLEditorPlugin.getDefault().savePluginPreferences();

        return ok;
    }
}
