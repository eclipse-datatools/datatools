/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal.preference;


import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.common.ui.preferences.AbstractDBPreferenceFieldPage;
import org.eclipse.datatools.sqltools.plan.IHelpConstants;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.PreferenceConstants;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
public class ExecutionPlanPage extends AbstractDBPreferenceFieldPage implements IContextProvider
{
    private Group  _planViewOrientation;
    private Button _verticalLayout;
    private Button _horizontalLayout;
    private Button _defaultEncoding = null;
    private Button _otherEncoding   = null;
    private Combo  _fileEncoding    = null;
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
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(parent.getShell(), HelpUtil.getContextId(IHelpConstants.EXECUTION_PLAN_PREFERENCE, PlanViewPlugin.getDefault().getBundle().getSymbolicName()));
        
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
        GridLayout globalLayout = new GridLayout();
        globalLayout.marginHeight = 0;
        globalLayout.marginWidth = 0;
        global.setLayout(globalLayout);

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

        createEncodingGroup(global);
        
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
        _otherEncoding.setSelection(store.getBoolean(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING));
        _fileEncoding.select(store.getInt(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING_SELECTION));
        _defaultEncoding.setSelection(store.getBoolean(PreferenceConstants.EXPORT_FORMAT_DEFAULT_ENCODEING));
        boolean enabled = _defaultEncoding.getSelection();
        _fileEncoding.setEnabled(!enabled);
        super.initializeValues();
    }

    protected void performDefaults()
    {
        IPreferenceStore store = getPreferenceStore();
        _verticalLayout.setSelection(store.getDefaultBoolean(PreferenceConstants.VERTICAL_LAYOUT_PLAN_VIEW));
        _horizontalLayout.setSelection(store.getDefaultBoolean(PreferenceConstants.HORIZONTAL_LAYOUT_PLAN_VIEW));
        _defaultEncoding.setSelection(store.getDefaultBoolean(PreferenceConstants.EXPORT_FORMAT_DEFAULT_ENCODEING));
        _otherEncoding.setSelection(store.getDefaultBoolean(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING));
        _fileEncoding.select(store.getDefaultInt(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING_SELECTION));
        boolean enabled = _defaultEncoding.getSelection();
        _fileEncoding.setEnabled(!enabled);
        super.performDefaults();
    }

    public boolean performOk()
    {
        IPreferenceStore store = getPreferenceStore();
        store.setValue(PreferenceConstants.VERTICAL_LAYOUT_PLAN_VIEW, _verticalLayout.getSelection());
        store.setValue(PreferenceConstants.HORIZONTAL_LAYOUT_PLAN_VIEW, _horizontalLayout.getSelection());
        store.setValue(PreferenceConstants.EXPORT_FORMAT_DEFAULT_ENCODEING, _defaultEncoding.getSelection());

        store.setValue(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING, _otherEncoding.getSelection());
        store.setValue(PreferenceConstants.EXPORT_FORMAT_OTHER_ENCODEING_SELECTION, _fileEncoding.getSelectionIndex());
        store.setValue(PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING, _fileEncoding.getText());
        if (_defaultEncoding.getSelection())
        {
            store.setValue(PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING, System
                    .getProperty("file.encoding", "UTF-8")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return super.performOk();
    }
    
    private void createEncodingGroup(Composite parent)
    {

        Font font = parent.getFont();
        Group group = new Group(parent, SWT.NONE);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        group.setLayoutData(data);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        group.setLayout(layout);
        group.setText(Messages.getString("ExecutionPlanPage.export.group")); //$NON-NLS-1$
        group.setFont(font);

        SelectionAdapter buttonListener = new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateEncodingState(_defaultEncoding.getSelection());
            }
        };

        _defaultEncoding = new Button(group, SWT.RADIO);
        String defaultEnc = System.getProperty("file.encoding", "UTF-8");

        _defaultEncoding.setText(MessageFormat.format(Messages.getString("ExecutionPlanPage.default.enc"), new String[] //$NON-NLS-1$
        {
            defaultEnc
        }));

        data = new GridData();
        data.horizontalSpan = 2;
        _defaultEncoding.setLayoutData(data);
        _defaultEncoding.addSelectionListener(buttonListener);
        _defaultEncoding.setFont(font);

        _otherEncoding = new Button(group, SWT.RADIO);
        _otherEncoding.setText(Messages.getString("ExecutionPlanPage.other.enc")); //$NON-NLS-1$
        _otherEncoding.addSelectionListener(buttonListener);
        _otherEncoding.setFont(font);

        _fileEncoding = new Combo(group, SWT.NONE);
        data = new GridData();
        data.widthHint = convertWidthInCharsToPixels(15);
        _fileEncoding.setFont(font);
        _fileEncoding.setLayoutData(data);

        _fileEncoding.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                updateValidState();
            }
        });

        ArrayList encodings = new ArrayList();
        int n = 0;
        try
        {
            n = Integer.parseInt(Messages.getString("ExportFormatPage.fileencoding.numDefaultEncodings")); //$NON-NLS-1$
        }
        catch (NumberFormatException e)
        {
            // Ignore;
        }
        for (int i = 0; i < n; ++i)
        {
            String enc = Messages.getString("ExportFormatPage.fileencoding.defaultEncoding" + (i + 1)); //$NON-NLS-1$
            if (enc != null)
            {
                encodings.add(enc);
            }
        }

        if (!encodings.contains(defaultEnc))
        {
            encodings.add(defaultEnc);
        }

        String enc = getPreferenceStore().getString(PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING);
        boolean isDefault = ((enc == null) || (enc.length() == 0));

        if (!isDefault && !encodings.contains(enc))
        {
            encodings.add(enc);
        }
        Collections.sort(encodings);
        for (int i = 0; i < encodings.size(); ++i)
        {
            _fileEncoding.add((String) encodings.get(i));
        }

        _fileEncoding.setText(isDefault ? defaultEnc : enc);
        updateEncodingState(isDefault);

    }

    private void updateEncodingState(boolean useDefault)
    {
        _defaultEncoding.setSelection(useDefault);
        _otherEncoding.setSelection(!useDefault);
        _fileEncoding.setEnabled(!useDefault);
    }

    private void updateValidState()
    {
        if (!isEncodingValid())
        {
            setErrorMessage(Messages.getString("ExportFormatPage.fileencoding.unsupportedEncoding")); //$NON-NLS-1$
            setValid(false);
        }
        else
        {
            setErrorMessage(null);
            setValid(true);
        }
    }

    private boolean isEncodingValid()
    {
        return _defaultEncoding.getSelection() || isValidEncoding(_fileEncoding.getText());
    }

    private boolean isValidEncoding(String enc)
    {
        try
        {
            new String(new byte[0], enc);
            return true;
        }
        catch (UnsupportedEncodingException e)
        {
            return false;
        }
    }
    
    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(PlanViewPlugin.getDefault()
                                                                    .getBundle().getSymbolicName());

    public IContext getContext(Object target)
    {
        return contextProviderDelegate.getContext(target);
    }

    public int getContextChangeMask()
    {
        return contextProviderDelegate.getContextChangeMask();
    }

    public String getSearchExpression(Object target)
    {
        return contextProviderDelegate.getSearchExpression(target);
    }
}
