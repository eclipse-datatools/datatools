/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal.preference;


import org.eclipse.datatools.sqltools.common.ui.preferences.AbstractDBPreferenceFieldPage;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

/**
 * Preference page for Execution Plan View.
 * <p>
 * To provide vendor specific options, vendor should extend "preferenceSection" extionsion point, which is defined in
 * "org.eclipse.datatools.sqltools.common.ui" plugin.
 * 
 * @author Dafan Yang
 */
public class ExecutionPlanPage extends AbstractDBPreferenceFieldPage
{
    private Group  _planViewOrientation;
    private Button _verticalLayout;
    private Button _horizontalLayout;

    public String getPreferencePageId()
    {
        return PreferenceConstants.PLAN_PREFERENCE_PAGE_ID;
    }

    public void init(IWorkbench workbench)
    {
        setPreferenceStore(PlanViewPlugin.getDefault().getPreferenceStore());
    }

    protected Control createContents(Composite parent)
    {
        Composite comp = new Composite(parent, SWT.NONE);
        comp.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        comp.setLayout(layout);

        // composite for global options
        Composite global = new Composite(comp, SWT.NONE);
        global.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        global.setLayout(new GridLayout());

        _planViewOrientation = new Group(global, SWT.NONE);
        _planViewOrientation.setText(Messages.getString("ExecutionPlanPage.orientation.group.name")); //$NON-NLS-1$
        _planViewOrientation.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        _planViewOrientation.setLayout(new GridLayout());

        _verticalLayout = new Button(_planViewOrientation, SWT.RADIO);
        _verticalLayout.setText(Messages.getString("ExecutionPlanPage.vertical.orientation")); //$NON-NLS-1$
        _verticalLayout.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _horizontalLayout = new Button(_planViewOrientation, SWT.RADIO);
        _horizontalLayout.setText(Messages.getString("ExecutionPlanPage.horizontal.orientation")); //$NON-NLS-1$
        _horizontalLayout.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // composite for vendor specific tabs
        Composite tabs = new Composite(comp, SWT.NONE);
        tabs.setLayoutData(new GridData(GridData.FILL_BOTH));
        tabs.setLayout(layout);
        super.createContents(tabs);

        return comp;
    }

    protected void initializeValues()
    {
        IPreferenceStore store = getPreferenceStore();
        _verticalLayout.setSelection(store.getBoolean(PreferenceConstants.VERTICAL_LAYOUT_PLAN_VIEW));
        _horizontalLayout.setSelection(store.getBoolean(PreferenceConstants.HORIZONTAL_LAYOUT_PLAN_VIEW));
        super.initializeValues();
    }

    protected void performDefaults()
    {
        IPreferenceStore store = getPreferenceStore();
        _verticalLayout.setSelection(store.getDefaultBoolean(PreferenceConstants.VERTICAL_LAYOUT_PLAN_VIEW));
        _horizontalLayout.setSelection(store.getDefaultBoolean(PreferenceConstants.HORIZONTAL_LAYOUT_PLAN_VIEW));
        super.performDefaults();
    }

    public boolean performOk()
    {
        IPreferenceStore store = getPreferenceStore();
        store.setValue(PreferenceConstants.VERTICAL_LAYOUT_PLAN_VIEW, _verticalLayout.getSelection());
        store.setValue(PreferenceConstants.HORIZONTAL_LAYOUT_PLAN_VIEW, _horizontalLayout.getSelection());
        return super.performOk();
    }
}
