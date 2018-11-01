/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui.launching;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.sql.ui.dialogs.SQLPainterDlg;
import org.eclipse.datatools.sqltools.common.ui.util.SWTUtils;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.UIComponentService;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorUIActivator;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditorImages;
import org.eclipse.datatools.sqltools.routineeditor.util.RoutineUtil;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationListener;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * @author Yang Liu
 */
public class RoutineMainTab extends AbstractLaunchConfigurationTab
{
    static String                 LINE_SEPARATOR = System.getProperty("line.separator");

    private Group                 _grProfile;
    private Combo                 _comboProfile;
    private Label                 _labelProfileName;
    private Label                 _labelDbName;
    private Combo                 _comboDbName;

    private Group                 _grRadios;
    private Button                _rbProc;
    private Button                _rbFunction;
    private Button                _rbEvent;
    private Button                _rbSQL;

    private Button                _rbTrigger;

    private Group                 _grRunString;
    private Text                  _txtObjname;
    private Button                _pbRunBrowse;

    private Group                 _grParameters;
    private Text                  _txtPreview;
    private Button                _pbConfigParameter;

    private List                  _parameterList      = new ArrayList();
    private List                  _backupList ;
    private Map                   _eventTriggerParams = new HashMap();

    FontMetrics					  _fontMetrics;
    private String                _defaultPreview;
    private MenuManager           _ctxMenuMgr;
    private IAction               _psqlSelectAction;
    private IAction               _psqlInsertAction;
    private IAction               _psqlUpdateAction;
    private IAction               _psqlDeleteAction;

    /**
     * _currentProc need to be always synced with the _txtObjname. When is adHoc, _currentProc will be null 
     */
    ProcIdentifier				  _currentProc;
    ProcIdentifier                _backupProc;

    ILaunchConfiguration		  _configuration; 

    /**
     *  
     */
    public RoutineMainTab()
    {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#getName()
     */
    public String getName()
    {
        return Messages.RoutineMainTab_name; 
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#getImage()
     */
    public Image getImage()
    {
        Image image = RoutineEditorImages.getImage("launch_configuration");
        return image; 
    }

    public int convertHeightInCharsToPixels(int chars) 
    {
        if (_fontMetrics == null) return 0;
        return _fontMetrics.getHeight() * chars;
    }
    protected void initializeDialogUnits(Control control) 
    {
        // Compute and store a font metric
        GC gc = new GC(control);
        gc.setFont(JFaceResources.getDialogFont());
        _fontMetrics = gc.getFontMetrics();
        gc.dispose();
    }
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent)
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent.getShell(), HelpUtil.getContextId(IHelpContextIds.LAUNCH_CONFIGURATION_MAIN, SQLEditorPlugin.getDefault().getBundle().getSymbolicName())); 

        initializeDialogUnits(parent);
        ModifyListener modifyListener = new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                setDirty(true);
                updateLaunchConfigurationDialog();
            }
        }
        ;

        Composite comp = new Composite(parent, SWT.NONE);
        this.setControl(comp);

        int topNumColumn = 1;
        GridLayout topLayout = new GridLayout();
        topLayout.numColumns = topNumColumn;
        comp.setLayout(topLayout);

        GridData griddata;

        _grProfile = new Group(comp, SWT.NONE);
        _grProfile.setText(Messages.RoutineMainTab_profile); 
        griddata = new GridData(GridData.FILL_HORIZONTAL);
        griddata.horizontalSpan = topNumColumn;
        _grProfile.setLayoutData(griddata);
        GridLayout profileLayout = new GridLayout();
        profileLayout.numColumns = 4;
        profileLayout.horizontalSpacing = 10;
        _grProfile.setLayout(profileLayout);

        _labelProfileName = new Label(_grProfile, SWT.NONE);
        _labelProfileName.setText(Messages.RoutineMainTab_profile);

        _comboProfile = new Combo(_grProfile, SWT.READ_ONLY);
        _comboProfile.setItems(ProfileUtil.getSupportedDatabaseProfiles());
        _comboProfile.setVisibleItemCount(20);
        _comboProfile.setLayoutData(new GridData(110, 25));
        _comboProfile.addModifyListener(new ModifyListener()
        {

            public void modifyText(ModifyEvent e)
            {
                if (_comboDbName != null)
                {
                    _comboDbName.removeAll();
                    String profileName = _comboProfile.getText();
                    if (profileName != null)
                    {
                        List list = ProfileUtil.getDatabaseList(profileName);
                        Iterator iterator = list.iterator();
                        while (iterator.hasNext())
                        {
                            _comboDbName.add(iterator.next().toString());
                        }
                    }
                }

            }
        }
        );

        _labelDbName = new Label(_grProfile, SWT.NONE);
        _labelDbName.setText(Messages.RoutineMainTab_dbname);
        _comboDbName = new Combo(_grProfile, SWT.READ_ONLY);
        _comboDbName.setVisibleItemCount(20);
        _comboDbName.setLayoutData(new GridData(110, 20));

        _comboDbName.addModifyListener(new ModifyListener()
        {

            public void modifyText(ModifyEvent e)
            {
                setDirty(true);
                updateLaunchConfigurationDialog();

            }
        }
        );

        _grRadios = new Group(comp, SWT.NONE);
        _grRadios.setText(Messages.RoutineMainTab_type); 
        griddata = new GridData(GridData.FILL_HORIZONTAL);
        griddata.horizontalSpan = topNumColumn;
        _grRadios.setLayoutData(griddata);
        GridLayout radioLayout = new GridLayout();
        radioLayout.marginHeight = 0;
        radioLayout.marginWidth = 0;
        radioLayout.numColumns = 2;
        radioLayout.makeColumnsEqualWidth = true;
        _grRadios.setLayout(radioLayout);

        _rbProc = createRadioButton(_grRadios, Messages.RoutineMainTab_storedprocedure); 
        //Since ASE only support SQLJ UDF( which can't be debugged) and 
        //ASA treat UDF as the same as SP, so there's no need to add this radio button
        _rbFunction = createRadioButton(_grRadios, Messages.RoutineMainTab_udf); 
        _rbTrigger = createRadioButton(_grRadios, Messages.RoutineMainTab_trigger); 
        _rbEvent = createRadioButton(_grRadios, Messages.RoutineMainTab_eventhandler); 
        _rbSQL = createRadioButton(_grRadios, Messages.RoutineMainTab_adhoc); 
        SelectionListener selectionListener = new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                setDirty(true);
                updateLaunchConfigurationDialog();

                try
                {
                    if (_currentProc == null)
                    {
                        _txtPreview.setText("");
                        return;
                    }
                    if (_currentProc.getType()==getCurrentTypeSelection() && _configuration != null)
                    {
                        _txtPreview.setText(LaunchHelper.constructDirectInvocationSQLString(_configuration));
                    }
                    else if (getCurrentTypeSelection()==ProcIdentifier.TYPE_SQL && _configuration != null)
                    {
                        _txtPreview.setText(LaunchHelper.constructDirectInvocationSQLString(_configuration));
                    }
                    else
                    {
                        _txtPreview.setText("");
                    }
                }
                catch (CoreException e1)
                {
                    _txtPreview.setText("");
                }
                catch (SQLException e1)
                {
                    _txtPreview.setText("");
                }
                catch (NoSuchProfileException e1)
                {
                    _txtPreview.setText("");
                }
            }
        }
        ;
        _rbProc.addSelectionListener(selectionListener);
        _rbFunction.addSelectionListener(selectionListener);
        _rbEvent.addSelectionListener(selectionListener);
        _rbSQL.addSelectionListener(selectionListener);
        _rbTrigger.addSelectionListener(selectionListener);

        _grRunString = new Group(comp, SWT.NONE);
        _grRunString.setText(Messages.RoutineMainTab_objectname); 
        griddata = new GridData(GridData.FILL_HORIZONTAL);
        griddata.horizontalSpan = topNumColumn;
        _grRunString.setLayoutData(griddata);
        GridLayout objnameLayout = new GridLayout();
        objnameLayout.numColumns = 2;
        _grRunString.setLayout(objnameLayout);

        _txtObjname = new Text(_grRunString, SWT.BORDER);
        griddata = new GridData(GridData.FILL_HORIZONTAL);
        griddata.grabExcessHorizontalSpace = true;
        //griddata.heightHint = convertHeightInCharsToPixels(3);
        _txtObjname.setLayoutData(griddata);
        //_txtObjname.addModifyListener(modifyListener);

        _pbRunBrowse = createPushButton(_grRunString, Messages.RoutineMainTab_run_browse, null); 
        _pbRunBrowse.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                ProcIdentifier s = null;
                try
                {
                    if (_comboProfile.getText() != null && _comboDbName.getText() != null)
                    {
                        DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(_comboProfile.getText(), _comboDbName.getText());

                        s = LaunchUI.selectDatabaseObject(getShell(), databaseIdentifier,
                            getCurrentTypeSelection(), _currentProc);
                    }
                }
                catch (Exception e1)
                {
                    MessageDialog.openError(getShell(), Messages.RoutineMainTab_msg_error, e1.getMessage()); 
                    return;
                }
                if (s != null && !s.equals(_currentProc))
                {
                    setDirty(true);
                    _currentProc = s;
                    _txtObjname.setText(s.getLongDisplayString());
                }
                if (getCurrentTypeSelection()==ProcIdentifier.TYPE_SP ||getCurrentTypeSelection()==ProcIdentifier.TYPE_UDF)
                {
                    if (_currentProc!=null)
                    {
                        try
                        {
                            ParameterDescriptor[] pds;
                            pds = LaunchHelper.getParameterDescriptors(_currentProc);
                            convertParameters(pds);
                            //this._txtObjname.setText(_currentProc.getCallableString());
                            _defaultPreview = RoutineUtil.constructSPUDFDirectInvocationString(_currentProc, _parameterList, pds, true);
                            if (_defaultPreview!=null)
                            {
                                _txtPreview.setText(_defaultPreview);
                            }

                        }
                        catch (Exception e1)
                        {
                            //this.setErrorMessage(DmpMessages.getString("RoutineMainTab.msg.error.metadata") + e1.getMessage()); //$NON-NLS-1$
                            e1.printStackTrace();
                        }
                    }
                }
                else if (getCurrentTypeSelection()==ProcIdentifier.TYPE_EVENT)
                {
                    _txtPreview.setText(RoutineUtil.constructTriggerEventString(_currentProc, _eventTriggerParams, true));
                }

                updateLaunchConfigurationDialog();
            }
        }
        );

        //add a bottom composite containing the parameter group to control the height of this group
        Composite botComp = new Composite(comp, SWT.NONE);
        GridLayout botLayout = new GridLayout();
        botLayout.marginHeight = 0;
        botLayout.marginWidth = 0;
        botComp.setLayout(botLayout);

        _grParameters = new Group(botComp, SWT.NONE);
        _grParameters.setText(Messages.RoutineMainTab_parameters); 
        griddata = new GridData(GridData.FILL_HORIZONTAL);
        griddata.horizontalSpan = topNumColumn;
        griddata.heightHint = convertHeightInCharsToPixels(8);
        _grParameters.setLayoutData(griddata);

        GridLayout parameterLayout = new GridLayout();
        parameterLayout.numColumns = 2;
        _grParameters.setLayout(parameterLayout);

        _txtPreview = new Text(_grParameters, SWT.BORDER|SWT.READ_ONLY|SWT.MULTI|SWT.V_SCROLL|SWT.LEFT_TO_RIGHT);
        griddata = new GridData(GridData.FILL_BOTH);
        griddata.grabExcessHorizontalSpace = true;
        //griddata.heightHint = convertHeightInCharsToPixels(3);
        _txtPreview.setLayoutData(griddata);
        _txtPreview.addModifyListener(modifyListener);

        _pbConfigParameter = createPushButton(_grParameters, Messages.RoutineMainTab_configparam, null); 
        _pbConfigParameter.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (getCurrentTypeSelection() == ProcIdentifier.TYPE_SQL || getCurrentTypeSelection() == ProcIdentifier.TYPE_TRIGGER)
                {
                	SQLDevToolsUIConfiguration conf = SQLToolsUIFacade.getConfigurationByProfileName(_comboProfile.getText());
                	UIComponentService componentService = conf.getUIComponentService();
					if (componentService.supportsDMLDialog())
                	{
                		Menu menu = createContextMenuManager(componentService).createContextMenu(_pbRunBrowse);
                		menu.setVisible(true);
                	}
                	else
                	{
                		MessageDialog.openInformation(getShell(), Messages.RoutineMainTab_sql_unimplemented_title, Messages.RoutineMainTab_sql_unimplemented_msg);
                	}
                    return;
                }
                else if (getCurrentTypeSelection() == ProcIdentifier.TYPE_EVENT)
                {
                    //TODO get event parameter list from sql model Routine
                    final String[] pNames = new String[] {};
                    try
                    {
                        LaunchUI.configEventParameters(getShell(), pNames, _eventTriggerParams,_currentProc);
                    }
                    catch (Exception e1)
                    {
                        MessageDialog.openError(getShell(), Messages.RoutineMainTab_msg_error, Messages.RoutineMainTab_msg_error_metadata + e1.getMessage()); 
                        e1.printStackTrace();
                        return;
                    }
                    _txtPreview.setText(RoutineUtil.constructTriggerEventString(_currentProc, _eventTriggerParams, true));
                    setDirty(true);
                    updateLaunchConfigurationDialog();

                    return;
                }
                else
                // must be SP or UDF
                {
                    ParameterDescriptor[] pds;
                    try
                    {
                        pds = LaunchHelper.getParameterDescriptors(_currentProc);
                    }
                    catch (Exception e1)
                    {
                        MessageDialog.openError(getShell(), Messages.RoutineMainTab_msg_error, Messages.RoutineMainTab_msg_error_metadata + e1.getMessage()); 
                        e1.printStackTrace();
                        return;
                    }
                    List newValues = LaunchUI.configParameter(getShell(), pds, _parameterList, true, _configuration);
                    if (newValues != null)
                    {
                        _parameterList = newValues;
                        _txtPreview.setText(RoutineUtil.constructSPUDFDirectInvocationString(_currentProc, _parameterList, pds, true));
                        setDirty(true);
                        updateLaunchConfigurationDialog();
                    }
                }
            }
        }
        );

        GridData bgd = new GridData(GridData.FILL_HORIZONTAL);
        //if it is a instance of LaunchConfigurationDialog, we set the heighHint
        //if it is a instance of LaunchConfigurationsDialog, we just let it be
        //LaunchConfigurationDialog implements ILaunchConfigurationListener
        if(this.getLaunchConfigurationDialog() instanceof ILaunchConfigurationListener)
        {
            bgd.heightHint = 240;
        }
        botComp.setLayoutData(bgd);
    }

    private MenuManager createContextMenuManager(UIComponentService service)
    {
        _ctxMenuMgr = new MenuManager(Messages.pasteSQL); 
        _psqlSelectAction = new SQLPainterAction(service, Messages.pasteSQL_select, UIComponentService.SELECT); 
        _psqlInsertAction = new SQLPainterAction(service, Messages.pasteSQL_insert, UIComponentService.INSERT); 
        _psqlUpdateAction = new SQLPainterAction(service, Messages.pasteSQL_update, UIComponentService.UPDATE); 
        _psqlDeleteAction = new SQLPainterAction(service, Messages.pasteSQL_delete, UIComponentService.DELETE); 

        if (getCurrentTypeSelection() != ProcIdentifier.TYPE_TRIGGER)
        {
            _ctxMenuMgr.add(_psqlSelectAction);
        }
        _ctxMenuMgr.add(_psqlInsertAction);
        _ctxMenuMgr.add(_psqlUpdateAction);
        _ctxMenuMgr.add(_psqlDeleteAction);
        return _ctxMenuMgr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration)
    {
        ProcIdentifier pi = LaunchUI.getActiveProcIdentifier();
        if (pi != null)
        {
            LaunchHelper.initializeConfiguration(configuration, pi);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
     */
    public void initializeFrom(ILaunchConfiguration configuration)
    {
        try
        {
            if (configuration == null)
            {
                configuration = _configuration;
            }
            DatabaseIdentifier databaseIdentifier = LaunchHelper.readDatabaseIdentifier(configuration);
            String profile = databaseIdentifier.getProfileName();
            String dbName = databaseIdentifier.getDBname();
            _comboProfile.setText(profile == null ? "" : profile); //$NON-NLS-1$
            if (dbName != null)
            {
                _comboDbName.add(dbName);
                _comboDbName.setText(dbName);
            }
            boolean issql = LaunchHelper.isAdHocSQL(configuration);
            //reset all radio buttons, fix bug:364508
            _rbProc.setSelection(false);
            _rbEvent.setSelection(false);
            _rbSQL.setSelection(false);
            _rbTrigger.setSelection(false);
            _rbFunction.setSelection(false);

            if (issql)
            {
                _rbSQL.setSelection(true);
                _currentProc = null;
            }
            else
            {
                _currentProc = LaunchHelper.readProcIdentifier(configuration);
                if (_currentProc == null)
                {
                    _rbProc.setSelection(true);
                    this._txtObjname.setText(""); //$NON-NLS-1$
                }
                else
                {
                    this._txtObjname.setText(_currentProc.getLongDisplayString());
                    switch(_currentProc.getType())
                    {
                        case ProcIdentifier.TYPE_SP:
                            _rbProc.setSelection(true);
                            _parameterList = LaunchHelper.readParameterList(configuration);
                            break;
                        case ProcIdentifier.TYPE_UDF:
                            _rbFunction.setSelection(true);
                            _parameterList = LaunchHelper.readParameterList(configuration);
                            break;
                        case ProcIdentifier.TYPE_EVENT:
                            _rbEvent.setSelection(true);
                            _eventTriggerParams = LaunchHelper.readEventParameter(configuration);
                            break;
                        case ProcIdentifier.TYPE_TRIGGER:
                            this._txtObjname.setEditable(true);
                            this._txtObjname.setEnabled(true);
                            _rbTrigger.setSelection(true);
                            break;
                        default:
                            _rbProc.setSelection(true);
                            break;
                    }
                }

            }
            
            //Changed for Bug 200509
            String sql = LaunchHelper.readLaunchSQLStatement(configuration);
            
            if (sql == null || sql.trim().length() < 1)
            {
                try
                {
                    if (_currentProc != null && _currentProc.getType() == getCurrentTypeSelection()
                            && configuration != null)
                    {
                        sql = LaunchHelper.constructDirectInvocationSQLString(configuration);
                    }
                    else
                    {
                        sql = "";
                    }
                }
                catch (Exception e1)
                {
                    sql = "";
                } 
            }
            _txtPreview.setText(sql);
        }
        catch (CoreException ex)
        {
        }
    }

    /**
     * get current type selection from UI
     * 
     * @return
     */
    protected int getCurrentTypeSelection()
    {
        //      0. stored procedure. 1. udf. 2. event handler. 3. adhoc sql
        int type = ProcIdentifier.TYPE_SP;
        if (_rbProc.getSelection())
        {
            type = ProcIdentifier.TYPE_SP;
        }
        else if (_rbFunction.getSelection())
        {
            type = ProcIdentifier.TYPE_UDF;
        }
        else if (_rbEvent.getSelection())
        {
            type = ProcIdentifier.TYPE_EVENT;
        }
        else if (_rbSQL.getSelection())
        {
            type = ProcIdentifier.TYPE_SQL;
        }
        else if (_rbTrigger.getSelection())
        {
            type = ProcIdentifier.TYPE_TRIGGER;
        }

        return type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
     */
    public void performApply(ILaunchConfigurationWorkingCopy configuration)
    {
        String s = _txtPreview.getText();
        if (s!=null)
        {
            s = s.replaceAll(LINE_SEPARATOR,"\n");
        }

        if (getCurrentTypeSelection() == ProcIdentifier.TYPE_SP || getCurrentTypeSelection() == ProcIdentifier.TYPE_UDF)
        {
            if (_currentProc == null)
            {
                _currentProc = _backupProc;
            }
            if (_parameterList == null)
            {
                _parameterList = _backupList;
            }
            LaunchHelper.saveSPUDF(configuration, _currentProc, _parameterList, s);
            _configuration = configuration;
            _backupProc = _currentProc;
            _backupList = _parameterList;
        }
        else if (getCurrentTypeSelection() == ProcIdentifier.TYPE_EVENT)
        {
            LaunchHelper.saveEvent(configuration, _currentProc, _eventTriggerParams, s);
        }
        else if (getCurrentTypeSelection() == ProcIdentifier.TYPE_TRIGGER)
        {
            LaunchHelper.saveTrigger(configuration, _currentProc, s);
        }
        else
        {
            LaunchHelper.saveAdHocSQL(configuration, _comboProfile.getText(), _comboDbName.getText(), s);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.AbstractLaunchConfigurationTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
     */
    public boolean isValid(ILaunchConfiguration launchConfig)
    {
        return updateStatus();
    }

    protected boolean updateStatus()
    {
        setErrorMessage(null);
        setMessage(null);

        // _txtPreview.setText(""); //$NON-NLS-1$

        // fist check profile name.
        String profileName = _comboProfile.getText();
        String dbName = _comboDbName.getText();
        DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(profileName, dbName);

        if (profileName == null || profileName.trim().length() == 0 || !ProfileUtil.isDatabaseProfile(databaseIdentifier.getProfileName()) || dbName == null || dbName.trim().length() == 0)
        {
            if (profileName == null || profileName.trim().length() == 0 || !ProfileUtil.isDatabaseProfile(databaseIdentifier.getProfileName()))
            {
                this.setErrorMessage(Messages.RoutineMainTab_status_invalid_profile); 
            }
            else if (dbName == null || dbName.trim().length() == 0)
            {
                this.setErrorMessage(Messages.RoutineMainTab_status_invalid_dbName); 
            }
            SWTUtils.recursiveEnable(_grRadios, false);
            SWTUtils.recursiveEnable(_grRunString, false);
            SWTUtils.recursiveEnable(_grParameters, false);
            return false;
        }
        boolean isSQLEmpty = false;
        String previewText = this._txtPreview.getText();
        if (previewText == null || previewText.trim().length() ==0) 
        {
            this.setErrorMessage(Messages.RoutineMainTab_status_adhoc); 
            isSQLEmpty = true;;
        }
        if (_rbTrigger.getSelection())
        {
            if (previewText!=null) 
            {
                int i = previewText.trim().indexOf(" ");
                String firstword=previewText.trim();
                if (i>0)
                {
                    firstword = previewText.trim().substring(0, i);
                }
                boolean noselect = "INSERT".equalsIgnoreCase(firstword) || "DELETE".equalsIgnoreCase(firstword)
                    || "UPDATE".equalsIgnoreCase(firstword);
                if (!noselect)
                {
                    this.setErrorMessage(Messages.RoutineMainTab_status_trigger_noselect); 
                    isSQLEmpty = true;
                }
            }
        }
        // profile is ok.
        SWTUtils.recursiveEnable(_grRadios, true);
        SWTUtils.recursiveEnable(_grRunString, true);

        DatabaseDefinition dbDef = ProfileUtil.getDatabaseDefinition(databaseIdentifier.getProfileName()); 
        if (dbDef != null)
        {
            if (dbDef.supportsEvents())
            {
                _rbEvent.setEnabled(true);
            }
            else
            {
                if (_rbEvent.getSelection())
                {
                    _rbSQL.setSelection(true);
                }
                _rbEvent.setEnabled(false);
            }
            if (dbDef.supportsSQLUDFs())
            {
                _rbFunction.setEnabled(true);
            }
            else
            {
                if (_rbFunction.getSelection())
                {
                    _rbSQL.setSelection(true);
                }
                _rbFunction.setEnabled(false);
            }
            if (dbDef.supportsTriggers())
            {
                _rbTrigger.setEnabled(true);
            }
            else
            {
                if (_rbTrigger.getSelection())
                {
                    _rbSQL.setSelection(true);
                }
                _rbTrigger.setEnabled(false);
            }
            if (dbDef.supportsStoredProcedures())
            {
            	_rbProc.setEnabled(true);
            }
            else
            {
            	if (_rbProc.getSelection())
            	{
            		_rbSQL.setSelection(true);
            	}
            	_rbProc.setEnabled(false);
            }

        }
        
        _txtObjname.setEditable(false);
        if (_rbSQL.getSelection() )
        {
            // is trigger SQL, so parameter is ignored.
            _pbConfigParameter.setText(Messages.RoutineMainTab_sql); 

            //_txtObjname.setText(_defaultPreview);
            _txtPreview.setEditable(true);
            _txtPreview.setEnabled(true);
            _pbRunBrowse.setEnabled(false);
            _grParameters.setEnabled(true); 
            _pbConfigParameter.setEnabled(true);
            _txtObjname.setText("");
            //_currentProc = null;

            //SWTUtils.recursiveEnable(_grParameters, false);
            if (isSQLEmpty)
            {
                return false;
            }
            return true;
        }
        else
        {
        	_pbRunBrowse.setEnabled(true);
            _pbRunBrowse.setText(Messages.RoutineMainTab_run_browse); 
            _pbConfigParameter.setText(Messages.RoutineMainTab_configparam); 
            if (_rbProc.getSelection())
            {
                _txtPreview.setEditable(false);
                _txtPreview.setEnabled(false);
                if (_currentProc!=null)
                {
                    String s = _comboProfile.getText();
                    if (s.equals(_currentProc.getProfileName())&&_currentProc.getType()==ProcIdentifier.TYPE_SP)
                    {
                        _txtObjname.setText(_currentProc.getLongDisplayString());
                    }
                    else
                    {
                        _txtObjname.setText("");
                    }
                }
                else 
                {
                    _txtObjname.setText("");
                }                
            }
            else if ( _rbFunction.getSelection())
            {
                _txtPreview.setEditable(false);
                _txtPreview.setEnabled(false);
                if (_currentProc!=null)
                {
                    String s = _comboProfile.getText();
                    if (s.equals(_currentProc.getProfileName())&&_currentProc.getType()==ProcIdentifier.TYPE_UDF)
                    {
                        _txtObjname.setText(_currentProc.getLongDisplayString());
                    }
                    else
                    {
                        _txtObjname.setText("");
                    }
                }
                else 
                {
                    _txtObjname.setText("");
                }
            }
            else if (_rbEvent.getSelection())
            {
                _txtPreview.setEditable(false);
                _txtPreview.setEnabled(false);
                if (_currentProc!=null &&_currentProc.getType()==ProcIdentifier.TYPE_EVENT)
                {
                    String s = _comboProfile.getText();
                    if (s.equals(_currentProc.getProfileName()))
                    {
                        _txtObjname.setText(_currentProc.getLongDisplayString());
                    }
                    else
                    {
                        _txtObjname.setText("");
                    }
                }
                else 
                {
                    _txtObjname.setText("");
                }
            }
            else if (_rbTrigger.getSelection())
            {
                // is trigger SQL, so parameter is ignored.
                _pbConfigParameter.setText(Messages.RoutineMainTab_sql); 

                //_txtObjname.setText(_defaultPreview);
                _txtPreview.setEditable(true);
                _txtPreview.setEnabled(true);
                _pbConfigParameter.setEnabled(true);
                if (_currentProc!=null &&_currentProc.getType()==ProcIdentifier.TYPE_TRIGGER)
                {
                    String s = _comboProfile.getText();
                    if (s.equals(_currentProc.getProfileName()))
                    {
                        _txtObjname.setText(_currentProc.getLongDisplayString());
                    }
                    else
                    {
                        _txtObjname.setText("");
                    }
                }
                else 
                {
                    _txtObjname.setText("");
                }

            }
            String runstrmsg = checkRunStringValid();
            if (runstrmsg == null)
            {
                SWTUtils.recursiveEnable(_grParameters, true);
                _pbConfigParameter.setEnabled(true);

                if (isSQLEmpty)
                {
                    return false;
                }
                return true;
            }
            else
            {
                SWTUtils.recursiveEnable(_grParameters, false);
                setErrorMessage(runstrmsg);
                return false;
            }
        }

    }

    /**
     * change the parameterlist to match the parameter meta data
     * 
     * @param pds
     */
    private void convertParameters(ParameterDescriptor[] pds)
    {
        resize(_parameterList, pds.length, null);
        for (int i = 0; i < pds.length; i++)
        {
            if (pds[i].isOutput())
            _parameterList.set(i, null);
        }
    }

    /**
     * @return null means ok. else a string for the error message
     */
    private String checkRunStringValid()
    {
        if (_txtObjname.getText().length() != 0)
        {
            return null;
        }
        else
        {
            switch (getCurrentTypeSelection())
            {
                case ProcIdentifier.TYPE_UDF:
                    return Messages.RoutineMainTab_status_function; 
                case ProcIdentifier.TYPE_EVENT:
                    return Messages.RoutineMainTab_status_event; 
                case ProcIdentifier.TYPE_TRIGGER:
                    return Messages.RoutineMainTab_status_trigger; 
                case ProcIdentifier.TYPE_SP:
                default:
                    return Messages.RoutineMainTab_status_sp; 
            }
        }
    }
    
    /**
     * resize the list to the specified size. If originally the list is bigger than the size,
     * then trailing element will be removed. If orginally the list is smaller, then will use
     * "fill" to fill into the empty space.
     * 
     * After the method, list.size() == size
     * @param list
     * @param size must >= 0
     */
    public static void resize(List list, int size, Object fill)
    {
        if (size < 0)
        {
            throw new IllegalArgumentException(Messages.RoutineMainTab_invalid_size); 
        }
        int diff = list.size()-size;
        if (diff < 0)
        {
            // list is smaller than size, need fill in more things
            while(diff<0) 
            {
                list.add(fill); diff ++; 
            }
        }
        else if (diff > 0)
        {
            for (int i=list.size()-1; diff > 0; i--, diff--)   
            {
                list.remove(i);
            }
        }
    }

    private final class SQLPainterAction extends Action
    {
        private String _statmentType;
        private UIComponentService _service;

        /**
         * @param text
         */
        public SQLPainterAction(UIComponentService service, String text, String statementType)
        {
            super(text);
            this._statmentType = statementType;
            this._service = service;
        }

        public void run()
        {
            try
            {
                // when doing SQLPainter, means _currentProc is null. No need to track it.
                String oldRunString = _txtPreview.getText();
                String s;
                String statment = _txtPreview.getText();
                String profileName = _comboProfile.getText();
                String dbName = _comboDbName.getText();
                String parameter = null;
//                String parametersType = SQLDialog.VARIABLE;
                //TODO WS
                String parametersType = null;

                String tableName = null;
                if (_currentProc != null && _currentProc.getType() == ProcIdentifier.TYPE_TRIGGER)
                {
                    String ownerName = _currentProc.getOwnerName(); 
                    tableName = _currentProc.getTableName();
                    if (ownerName != null && ownerName.length() > 0)
                    {
                        tableName = ownerName + "." + tableName;  
                    }
                }
                SQLPainterDlg spd = _service.getDMLDialog(getShell(), _statmentType, statment, profileName, dbName, parametersType,
                		parameter, tableName, new HashMap());
                s = spd.load();
                if (s != null && !s.equals("") && !s.equals(oldRunString)) //$NON-NLS-1$
                {
                    setDirty(true);
                    _txtPreview.setText(s);
                }
            }
            catch (Exception e)
            {
            	RoutineEditorUIActivator.getDefault().log(e);
                MessageDialog.openError(getShell(), Messages.RoutineMainTab_msg_error, e.getMessage()); 
                return;
            }
        }
    }

}
