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
package org.eclipse.datatools.sqltools.result.internal.ui.filters;

import java.util.ArrayList;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.filters.ResultsFilterHelper;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.utils.ProfileUtil;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * The filters dialog
 * @author Dafan Yang
 */
public class ResultsFilterDialog extends TrayDialog implements IContextProvider
{
    private static final String PROVIDER_NAME_POSTFIX = "Connection Profile"; //$NON-NLS-1$
    private Table               _profilesTable;

    private Button              _unknownProfiles;

    //result status filter group
    private Group               _resultStatusGrp;
    private Button              _success;
    private Button              _failed;
    private Button              _terminated;
    private Button              _warning;
    private Button              _criticalError;

    private Button              _numCheck;
    private Text                _resultsNum;

    private static final int    NAME_COLUMN           = 0;
    private static final int    PROVIDER_COLUMN       = 1;
    private static final int    DESC_COLUMN           = 2;
    private IPreferenceStore    _store;
    private static final int    TABLE_WITDH           = 380;

    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName());
    
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
    
    /**
     * Constructor
     * @param parentShell the parent shell
     */
    public ResultsFilterDialog(Shell parentShell)
    {
        super(parentShell);
        _store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        setShellStyle(getShellStyle() | SWT.RESIZE);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent)
    {
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(parent, HelpUtil.getContextId(IHelpConstants.DIALOG_RESULTS_FILTER, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
        
        Composite parentComp = (Composite) super.createDialogArea(parent);

        Composite comp = new Composite(parentComp, SWT.NONE);
        comp.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        comp.setLayout(layout);

        createProfilesTable(comp);

        Composite limitationComp = new Composite(comp, SWT.NONE);
        limitationComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 3;
        limitationComp.setLayout(layout);

        _numCheck = new Button(limitationComp, SWT.CHECK);
        _numCheck.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (_numCheck.getSelection())
                {
                    _resultsNum.setEnabled(true);
                }
                else
                {
                    _resultsNum.setEnabled(false);
                    ResultsFilterDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(true);
                }
            }
        });
        _numCheck.setSelection(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_LIMIT_CHECK));
        _numCheck.setToolTipText(Messages.ResultsFilterDialog_limit_tip);
        _numCheck.setText(Messages.ResultsFilterDialog_limit);

        _resultsNum = new Text(limitationComp, SWT.SINGLE | SWT.BORDER);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        _resultsNum.setLayoutData(gd);
        _resultsNum.setText(Integer.toString(_store.getInt(PreferenceConstants.PROFILE_FILTERS_LIMIT_NUM)));
        _resultsNum.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                int limit = 0;
                try
                {
                    limit = Integer.parseInt(_resultsNum.getText().trim());
                    if (limit < 0)
                    {
                        ResultsFilterDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
                    }
                    else
                    {
                        ResultsFilterDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(true);
                    }
                }
                catch (Exception ex)
                {
                    ResultsFilterDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
                }
            }
        });
        if (!_numCheck.getSelection())
        {
            _resultsNum.setEnabled(false);
        }

        Composite unkownComp = new Composite(comp, SWT.NONE);
        layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        unkownComp.setLayout(layout);
        unkownComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        _unknownProfiles = new Button(unkownComp, SWT.CHECK);
        _unknownProfiles.setText(Messages.ResultsFilterDialog_unknownprofile); 
        _unknownProfiles.setToolTipText(Messages.ResultsFilterDialog_unknownprofile_tooltip); 
        _unknownProfiles.setSelection(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_UNKNOWNPROFILE));

        _resultStatusGrp = new Group(comp, SWT.NONE);
        _resultStatusGrp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        _resultStatusGrp.setText(Messages.ResultsFilterDialog_finishstatus); 
        layout = new GridLayout();
        layout.numColumns = 3;
        _resultStatusGrp.setLayout(layout);

        _success = new Button(_resultStatusGrp, SWT.CHECK);
        _success.setText(Messages.ResultsFilterDialog_succeeded); 
        _success.setSelection(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_SUCCESS));
        _success.setToolTipText(Messages.ResultsFilterDialog_succeeded_tip); 

        _failed = new Button(_resultStatusGrp, SWT.CHECK);
        _failed.setText(Messages.ResultsFilterDialog_failed); 
        _failed.setSelection(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_FAILED));
        _failed.setToolTipText(Messages.ResultsFilterDialog_failed_tip); 

        _terminated = new Button(_resultStatusGrp, SWT.CHECK);
        _terminated.setText(Messages.ResultsFilterDialog_terminated); 
        _terminated.setSelection(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_TERMINATED));
        _terminated.setToolTipText(Messages.ResultsFilterDialog_terminated_tip); 

        _warning = new Button(_resultStatusGrp, SWT.CHECK);
        _warning.setText(Messages.ResultsFilterDialog_warning); 
        _warning.setSelection(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_WARNING));
        _warning.setToolTipText(Messages.ResultsFilterDialog_warning_tooltip); 

        _criticalError = new Button(_resultStatusGrp, SWT.CHECK);
        _criticalError.setText(Messages.ResultsFilterDialog_criticalerror); 
        _criticalError.setSelection(_store.getBoolean(PreferenceConstants.PROFILE_FILTERS_STATUS_CRITICAL));
        _criticalError.setToolTipText(Messages.ResultsFilterDialog_criticalerror_tooltip); 

        return comp;
    }

    /**
     * Creates the connection profiles table
     * 
     * @param comp the parent composite
     */
    private void createProfilesTable(Composite comp)
    {
        _profilesTable = new Table(comp, SWT.V_SCROLL | SWT.CHECK | SWT.BORDER);
        _profilesTable.setHeaderVisible(true);
        _profilesTable.setLinesVisible(true);
        _profilesTable.setToolTipText(Messages.ResultsFilterDialog_profiles_tip); 

        TableColumn nameColumn = new TableColumn(_profilesTable, SWT.NONE);
        nameColumn.setText(Messages.ResultsFilterDialog_profilename); 

        TableColumn typeColumn = new TableColumn(_profilesTable, SWT.NONE);
        typeColumn.setText(Messages.ResultsFilterDialog_profileprovider); 

        TableColumn descColumn = new TableColumn(_profilesTable, SWT.NONE);
        descColumn.setText(Messages.ResultsFilterDialog_profiledesc); 

        IConnectionProfile[] profiles = ProfileUtil.getProfiles();

        for (int i = 0; i < profiles.length; i++)
        {
            TableItem item = new TableItem(_profilesTable, SWT.NONE);
            item.setText(NAME_COLUMN, profiles[i].getName());
            String providerName = profiles[i].getProviderName();
            if (providerName.indexOf(PROVIDER_NAME_POSTFIX) != -1)
            {
                providerName = providerName.substring(0, providerName.length() - PROVIDER_NAME_POSTFIX.length());
            }
            item.setText(PROVIDER_COLUMN, providerName);
            item.setText(DESC_COLUMN, profiles[i].getDescription());
            item.setChecked(!ResultsFilterHelper.isFilteredOut(profiles[i].getName())); 
        }
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);

        if (_profilesTable.getItemCount() < 3)
        {
            gd.heightHint = 4 * (_profilesTable.getItemHeight());
        }
        if (_profilesTable.getItemCount() > 6)
        {
            gd.heightHint = 7 * (_profilesTable.getItemHeight());
        }
        _profilesTable.setLayoutData(gd);

        nameColumn.pack();
        typeColumn.pack();
        descColumn.pack();

        //adjust the width of the table
        int totalWidth = nameColumn.getWidth() + typeColumn.getWidth() + descColumn.getWidth() + 3
                * _profilesTable.getGridLineWidth();

        int moreWidth = TABLE_WITDH - totalWidth - _profilesTable.getGridLineWidth();
        if (moreWidth > 0)
        {
            int aveMoreWidth = moreWidth / 3;
            nameColumn.setWidth(nameColumn.getWidth() + aveMoreWidth);
            typeColumn.setWidth(typeColumn.getWidth() + aveMoreWidth);
            descColumn.setWidth(descColumn.getWidth() + aveMoreWidth);
        }
        else
        {
            //limit the length of description cell
            descColumn.setWidth(TABLE_WITDH - nameColumn.getWidth() - typeColumn.getWidth());
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText(Messages.ResultsFilterDialog_dialogtitle); 
    }

    protected void okPressed()
    {
        ArrayList filteredOutProfiles = new ArrayList();
        for (int i = 0; i < _profilesTable.getItemCount(); i++)
        {
            TableItem item = _profilesTable.getItem(i);
            if (!item.getChecked())
            {
                filteredOutProfiles.add(item.getText(NAME_COLUMN));
            }
        }
        // save the profiles name which are filtered out
        ResultsFilterHelper.saveFilters((String[])filteredOutProfiles.toArray(new String[filteredOutProfiles.size()]));
        
        _store.setValue(PreferenceConstants.PROFILE_FILTERS_STATUS_SUCCESS, _success.getSelection());
        _store.setValue(PreferenceConstants.PROFILE_FILTERS_STATUS_FAILED, _failed.getSelection());
        _store.setValue(PreferenceConstants.PROFILE_FILTERS_STATUS_TERMINATED, _terminated.getSelection());
        _store.setValue(PreferenceConstants.PROFILE_FILTERS_STATUS_WARNING, _warning.getSelection());
        _store.setValue(PreferenceConstants.PROFILE_FILTERS_STATUS_CRITICAL, _criticalError.getSelection());

        // the PROFILE_FILTERS_PROFILE_MAY_CHANGED is used to inform the ResultsView to refresh the result history
        _store.setValue(PreferenceConstants.PROFILE_FILTERS_PROFILE_MAY_CHANGED, !_store
                .getBoolean(PreferenceConstants.PROFILE_FILTERS_PROFILE_MAY_CHANGED));

        _store.setValue(PreferenceConstants.PROFILE_FILTERS_LIMIT_CHECK, _numCheck.getSelection());
        int limit = _store.getDefaultInt(PreferenceConstants.PROFILE_FILTERS_LIMIT_NUM);
        try
        {
            int parsedLimit = Integer.parseInt(_resultsNum.getText().trim());
            if (parsedLimit >= 0)
            {
                limit = parsedLimit;
            }
        }
        catch (Exception e)
        {
            //ignore
        }

        _store.setValue(PreferenceConstants.PROFILE_FILTERS_LIMIT_NUM, limit);
        _store.setValue(PreferenceConstants.PROFILE_FILTERS_UNKNOWNPROFILE, _unknownProfiles.getSelection());

        super.okPressed();
    }
}
