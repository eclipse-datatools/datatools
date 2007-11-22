/**
 * Created on 2004-10-21
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.preferences;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hui Cao
 *  
 */
public class GeneralPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{

    private TabFolder              _tabFolder                = null;
    /**
     * @param style
     */
    public GeneralPreferencePage()
    {
        this(PreferenceMessages.GeneralPreferencePage_title, GRID); 
        // TODO Auto-generated constructor stub
    }

    /**
     * @param title
     * @param style
     */
    public GeneralPreferencePage(String title, int style)
    {
        this(title, null, style);
    }

    /**
     * @param title
     * @param image
     * @param style
     */
    public GeneralPreferencePage(String title, ImageDescriptor image, int style)
    {
        super(title, image, style);
        setPreferenceStore(SQLEditorPlugin.getDefault().getPreferenceStore());
        setDescription(PreferenceMessages.GeneralPreferencePage_description); 
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.PreferencePage#createControl(Composite)
     */
    public void createControl(Composite parent)
    {
        super.createControl(parent);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), HelpUtil.getContextId(IHelpContextIds.PREFERENCES_DATABASE_DEVELOPMENT, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    protected void createFieldEditors()
    {
        addField(new BooleanFieldEditor(PreferenceConstants.EDITOR_SHOW_TEXT_HOVER_AFFORDANCE,
            PreferenceMessages.GeneralPreferencePage_hover_affordance, getFieldEditorParent())); 
        String[][] pmModes = new String[][]
        {
            {
                PreferenceMessages.GeneralPreferencePage_execute_always, PreferenceConstants.PROMPT_MODE_ALWAYS
            }
            ,
            {
                PreferenceMessages.GeneralPreferencePage_execute_never, PreferenceConstants.PROMPT_MODE_NEVER
            }
            ,
            {
                PreferenceMessages.GeneralPreferencePage_execute_prompt, PreferenceConstants.PROMPT_MODE_PROMPT
            }
        }
        ;
        addField(new RadioGroupFieldEditor(PreferenceConstants.EXECUTE_SQL_ERROR_MODE, PreferenceMessages.GeneralPreferencePage_execute_error_mode, 3, pmModes, getFieldEditorParent(),true/*useGroup*/));
        // add save before refactor option if the editor is dirty
        pmModes = new String[][]
        {
            {
                PreferenceMessages.GeneralPreferencePage_savebeforerenaming_always, "true"
            }
            ,
            {
                PreferenceMessages.GeneralPreferencePage_savebeforerenaming_prompt, "false"
            }
        }
        ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench)
    {
    }

    /**
     * This method initializes _tabFolder
     */
    private void createTabFolder()
    {
        org.eclipse.swt.layout.GridData gridData12 = new org.eclipse.swt.layout.GridData();

        _tabFolder = new TabFolder(getFieldEditorParent(), SWT.NONE);
        gridData12.horizontalSpan = 2;
        gridData12.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData12.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData12.grabExcessHorizontalSpace = true;
        gridData12.grabExcessVerticalSpace = true;
        gridData12.heightHint = 100;
        _tabFolder.setLayoutData(gridData12);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 5;
        layout.marginWidth = 5;
        _tabFolder.setLayout(layout);

    }

}
