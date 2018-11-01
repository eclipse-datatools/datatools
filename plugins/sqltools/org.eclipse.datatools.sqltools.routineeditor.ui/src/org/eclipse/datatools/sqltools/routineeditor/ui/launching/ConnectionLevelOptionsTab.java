/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui.launching;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.common.ui.preferences.IDataServerLaunchPreferenceSection;
import org.eclipse.datatools.sqltools.common.ui.preferences.PreferencesRegistry;
import org.eclipse.datatools.sqltools.common.ui.util.SWTUtils;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.routineeditor.launching.RoutineLaunchConfigurationAttribute;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorImages;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * On this tab, user can configure the connection level options. By default, we check the "Use Default" radio box to let
 * the user use default values in the preference store. If the user checks the "User customize" radio box, the options'
 * values in the preference store will be copied for the first time.
 * 
 * @author Dafan Yang
 */
public class ConnectionLevelOptionsTab extends AbstractLaunchConfigurationTab implements
        RoutineLaunchConfigurationAttribute
{
    public static final String CONNECTION_LEVEL_OPTIONS_PAGE = "org.eclipse.datatools.sqltools.editor.core.preferences.ConnectionLevelOptionsPage"; //$NON-NLS-1$

    private Composite          _comp;

    private Button             _useDefault;

    private Button             _userDefined;

    private DatabaseIdentifier _databaseIdentifier;

    private Map                _sections;

    private Composite          _noPageAvailableComp;

    public ConnectionLevelOptionsTab()
    {
        super();
        _sections = new HashMap();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent)
    {
        _comp = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.verticalSpacing = 0;
        _comp.setLayout(layout);

        Composite topComp = new Composite(_comp, SWT.NONE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        layout = new GridLayout();
        layout.verticalSpacing = 0;
        layout.marginHeight = 8;
        layout.marginWidth = 8;
        topComp.setLayout(layout);

        _useDefault = new Button(topComp, SWT.RADIO);
        _useDefault.setText(Messages.ConnectionLevelOptionsTab_use_default);
        _useDefault.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _userDefined = new Button(topComp, SWT.RADIO);
        _userDefined.setText(Messages.ConnectionLevelOptionsTab_user_customize);
        _userDefined.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        /**
         * Only one composite is visible at one time.
         */
        Composite bottomComp = new Composite(_comp, SWT.NONE);
        FormLayout formlayout = new FormLayout();
        formlayout.marginHeight = 0;
        formlayout.marginWidth = 0;
        bottomComp.setLayout(formlayout);
        bottomComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Map sections = PreferencesRegistry.INSTANCE.getPageSections(CONNECTION_LEVEL_OPTIONS_PAGE);
        for (Iterator iter = sections.keySet().iterator(); iter.hasNext();)
        {
            String id = (String) iter.next();
            String name = id;
            if (name == null)
            {
                continue;
            }
            IDataServerLaunchPreferenceSection section = (IDataServerLaunchPreferenceSection) sections.get(id);
            if (section != null)
            {
                Composite dbComp = new Composite(bottomComp, SWT.NONE);
                layout = new GridLayout();
                dbComp.setLayout(layout);
                FormData data = new FormData();
                dbComp.setLayoutData(data);
                dbComp.setVisible(false);

                section.createSectionComposite(dbComp);
                section.setMode(IDataServerLaunchPreferenceSection.LAUNCH_MODE);
                _sections.put(name, section);
            }
        }
        _noPageAvailableComp = new Composite(bottomComp, SWT.NONE);
        layout = new GridLayout();
        layout.marginWidth = 10;
        _noPageAvailableComp.setLayout(layout);
        Label noPageLabel = new Label(_noPageAvailableComp, SWT.NONE);
        noPageLabel.setText(Messages.ConnectionLevelOptionsTab_no_page_defined);
        _noPageAvailableComp.setVisible(false);

        this.setControl(_comp);

        // All widgets in this composite are listened
        Listener listener = new Listener()
        {
            public void handleEvent(Event event)
            {
                ConnectionLevelOptionsTab.this.updateLaunchConfigurationDialog();
            }
        };
        SWTUtils.listenModify(_comp, listener, null);
        _useDefault.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
                Iterator iter = _sections.values().iterator();
                while (iter.hasNext())
                {
                    IDataServerLaunchPreferenceSection accessor = (IDataServerLaunchPreferenceSection) iter.next();
                    accessor.getSectionComposite().getParent().setVisible(false);
                }
                _noPageAvailableComp.setVisible(false);
                ConnectionLevelOptionsTab.this.updateLaunchConfigurationDialog();
            }
        });

        _userDefined.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
                SQLDevToolsConfiguration conf = SQLToolsFacade.getConfigurationByProfileName(_databaseIdentifier
                        .getProfileName());
                IDataServerLaunchPreferenceSection accessor = (IDataServerLaunchPreferenceSection) _sections.get(conf
                        .getDatabaseVendorDefinitionId().toString());
                if (accessor != null)
                {
                    accessor.getSectionComposite().getParent().setVisible(true);
                    _noPageAvailableComp.setVisible(false);
                }
                else
                {
                    _noPageAvailableComp.setVisible(true);
                }

                Iterator iter = _sections.values().iterator();
                while (iter.hasNext())
                {
                    IDataServerLaunchPreferenceSection acc = (IDataServerLaunchPreferenceSection) iter.next();
                    if (acc != accessor)
                    {
                        acc.getSectionComposite().getParent().setVisible(false);
                    }
                }
                ConnectionLevelOptionsTab.this.updateLaunchConfigurationDialog();
            }
        });
    }

    private void disableOptionTabIfNecessary()
    {
        if (_databaseIdentifier == null || _databaseIdentifier.getProfileName() == null
                || _databaseIdentifier.getProfileName().trim().length() == 0)
        {
            return;
        }
        SQLDevToolsConfiguration conf = SQLToolsFacade.getConfigurationByProfileName(_databaseIdentifier
                .getProfileName());
        IDataServerLaunchPreferenceSection accessor = (IDataServerLaunchPreferenceSection) _sections.get(conf
                .getDatabaseVendorDefinitionId().toString());

        // If no option page defined, disable all
        if (accessor == null)
        {
            _useDefault.setEnabled(false);
            _userDefined.setEnabled(false);

            Iterator iter = _sections.values().iterator();
            while (iter.hasNext())
            {
                IDataServerLaunchPreferenceSection acc = (IDataServerLaunchPreferenceSection) iter.next();
                if (acc != accessor)
                {
                    acc.getSectionComposite().getParent().setVisible(false);
                }
            }
            _noPageAvailableComp.setVisible(true);
            ConnectionLevelOptionsTab.this.updateLaunchConfigurationDialog();
        }
        else
        {
            _useDefault.setEnabled(true);
            _userDefined.setEnabled(true);
            _noPageAvailableComp.setVisible(false);
            ConnectionLevelOptionsTab.this.updateLaunchConfigurationDialog();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration)
    {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
     */
    public void initializeFrom(ILaunchConfiguration configuration)
    {
        initUI(configuration);
        disableOptionTabIfNecessary();
        if (_databaseIdentifier.getProfileName() != null && !_databaseIdentifier.getProfileName().trim().equals("")) //$NON-NLS-1$
        {
            SQLDevToolsConfiguration conf = SQLToolsFacade.getConfigurationByProfileName(_databaseIdentifier
                    .getProfileName());
            IDataServerLaunchPreferenceSection accessor = (IDataServerLaunchPreferenceSection) _sections.get(conf
                    .getDatabaseVendorDefinitionId().toString());
            if (accessor == null)
            {
                return;
            }
            IPreferenceStore store = RoutineEditorUIActivator.getDefault().getPreferenceStore();
            accessor.launchConfigurationInitialize(configuration, store);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void performApply(ILaunchConfigurationWorkingCopy configuration)
    {
        /**
         * performApply will be invoked frequently. NOTE that only when the user clicks "Apply" button will Eclipse save
         * the change to underlying file.
         */
        if (_useDefault.getSelection())
        {
            configuration.setAttribute(ROUTINE_LAUNCH_OPTION_TYPE, 1);
        }
        else if (_userDefined.getSelection())
        {
            configuration.setAttribute(ROUTINE_LAUNCH_OPTION_TYPE, 2);
            try
            {
                String profileName = configuration.getAttribute(ROUTINE_LAUNCH_PROFILENAME, (String) (null));
                String dbName = configuration.getAttribute(ROUTINE_LAUNCH_DATABASENAME, (String) null);
                _databaseIdentifier = new DatabaseIdentifier(profileName, dbName);
            }
            catch (CoreException e)
            {
                return;
            }

            SQLDevToolsConfiguration conf = SQLToolsFacade.getConfigurationByProfileName(_databaseIdentifier
                    .getProfileName());
            IDataServerLaunchPreferenceSection accessor = (IDataServerLaunchPreferenceSection) _sections.get(conf
                    .getDatabaseVendorDefinitionId().toString());
            if (accessor != null)
            {
                accessor.saveConfiguration(configuration);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
     */
    public String getName()
    {
        return Messages.ConnectionLevelOptionsTab_options;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#activated(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void activated(ILaunchConfigurationWorkingCopy workingCopy)
    {
        /**
         * If we can not get the profileName from the LaunchConfigurationWorkingCopy, we don't display this page to the
         * user, we set the active tab to the main tab instead.
         */
        try
        {
            String profileName = workingCopy.getAttribute(ROUTINE_LAUNCH_PROFILENAME, (String) (null));
            String dbName = workingCopy.getAttribute(ROUTINE_LAUNCH_DATABASENAME, (String) (null));
            _databaseIdentifier = new DatabaseIdentifier(profileName, dbName);
            // if the profile on the UI is invalid, should not activate it
            if (_databaseIdentifier.getProfileName() != null
                    && !_databaseIdentifier.getProfileName().trim().equals("") && ProfileUtil.profileExist(_databaseIdentifier.getProfileName())) //$NON-NLS-1$
            {
                super.activated(workingCopy);
            }
            else
            {
                ILaunchConfigurationTab[] tabs = getLaunchConfigurationDialog().getTabs();
                int tabsCount = tabs.length;
                ILaunchConfigurationTab mainTab = null;
                for (int i = 0; i < tabsCount; i++)
                {
                    if (tabs[i].getName().equals(Messages.ConnectionLevelOptionsTab_maintab))
                    {
                        mainTab = tabs[i];
                        break;
                    }
                }
                if (mainTab == null)// may not occur
                {
                    mainTab = tabs[0];
                }
              //LaunchConfigurationDialog implements ILaunchConfigurationListener
                if (getLaunchConfigurationDialog() instanceof ILaunchConfigurationListener)
                {
                    getLaunchConfigurationDialog().setActiveTab(mainTab);
                }
            }
        }
        catch (CoreException ce)
        {
            RoutineEditorUIActivator.getDefault().log(ce);
        }

    }

    private void initUI(ILaunchConfiguration configuration)
    {
        try
        {
            String profileName = configuration.getAttribute(ROUTINE_LAUNCH_PROFILENAME, (String) (null));
            String dbName = configuration.getAttribute(ROUTINE_LAUNCH_DATABASENAME, (String) (null));
            _databaseIdentifier = new DatabaseIdentifier(profileName, dbName);

            if (profileName != null && !profileName.trim().equals("")) //$NON-NLS-1$
            {
                // Open this tab at the very first time, we check the "Use
                // default" box
                if ((configuration.getAttribute(ROUTINE_LAUNCH_OPTION_TYPE, -1) == 1 || configuration.getAttribute(
                        ROUTINE_LAUNCH_OPTION_TYPE, -1) == -1)
                        && !_userDefined.getSelection())
                {
                    _useDefault.setSelection(true);
                    _userDefined.setSelection(false);
                    _useDefault.notifyListeners(SWT.Selection, new Event());
                }
                else if ((configuration.getAttribute(ROUTINE_LAUNCH_OPTION_TYPE, -1) == 1 || configuration
                        .getAttribute(ROUTINE_LAUNCH_OPTION_TYPE, -1) == -1)
                        && _userDefined.getSelection())
                {
                    ILaunchConfigurationWorkingCopy wc = configuration.getWorkingCopy();
                    wc.setAttribute(ROUTINE_LAUNCH_OPTION_TYPE, 2);
                    wc.doSave();
                    _useDefault.setSelection(false);
                    _userDefined.setSelection(true);
                    _userDefined.notifyListeners(SWT.Selection, new Event());
                }
                else if (configuration.getAttribute(ROUTINE_LAUNCH_OPTION_TYPE, -1) == 2)
                {
                    _useDefault.setSelection(false);
                    _userDefined.setSelection(true);
                    _userDefined.notifyListeners(SWT.Selection, new Event());
                }
            }
        }
        catch (CoreException e)
        {
            RoutineEditorUIActivator.getDefault().log(e);
        }
    }

    /**
     * Reset the composite's children, deselect the check box,radio box,combo box and set the Text widget to empty
     * 
     * @param comp
     */
    private void resetComposte(Composite comp)
    {
        Control[] children = comp.getChildren();
        int count = children.length;
        for (int i = 0; i < count; i++)
        {
            Control child = children[i];
            if (child instanceof Combo)
            {
                Combo combo = (Combo) child;
                combo.deselectAll();
            }
            else if (child instanceof Button)
            {
                Button button = (Button) child;
                button.setSelection(false);
            }
            else if (child instanceof Text)
            {
                Text text = (Text) child;
                text.setText(Messages.ConnectionLevelOptionsTab_9);
            }
            else if (child instanceof Composite)
            {
                resetComposte((Composite) child);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getImage()
     */
    public Image getImage()
    {
        return RoutineEditorImages.getImage(Messages.ConnectionLevelOptionsTab_10);
    }

}
