/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.common.ui.dialog.SaveAsDialog;
import org.eclipse.datatools.sqltools.common.ui.sqlstatementarea.SQLStatementArea;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.Messages;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SQLExecutionJobListener;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core.ScriptsExecutionRunnable;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * The preview dialog when saving the editor
 * 
 * @author Idull
 */
public class SavePreviewDialog extends Dialog
{
    private Button              _notShowAgain;
    private Button              _execute;
    private Button              _saveAs;
    private Button              _cancel;
    private String              _scripts;
    private DatabaseIdentifier  _databaseIdentifier;
    private ISchemaObjectEditor _editor;
    private SQLStatementArea    _sta;
    private Map                 _actions   = new HashMap();
    IPreferenceStore            _store     = SOEUIPlugin.getDefault().getPreferenceStore();
    private boolean             _syncSave;
    private IProgressMonitor    _monitor;
    private boolean             _executing = false;
    private String              _groupExecutionDspString;
    private String              _consumerName;

    /**
     * Constructor
     * 
     * @param parentShell the parent shell
     */
    public SavePreviewDialog(Shell parentShell, String scripts, DatabaseIdentifier databaseIdentifier,
            ISchemaObjectEditor editor, boolean syncSave, IProgressMonitor monitor, String groupExecutionDspString,
            String consumerName)
    {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        _scripts = scripts;
        _databaseIdentifier = databaseIdentifier;
        _editor = editor;
        _syncSave = syncSave;
        _monitor = monitor;
        _groupExecutionDspString = groupExecutionDspString;
        _consumerName = consumerName;
    }

    private void initializeActions()
    {
        CommonAction action = new CommonAction(_sta.getViewer().getTextOperationTarget(), ITextOperationTarget.COPY,
                Messages.SavePreviewDialog_copy);
        action.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
                ISharedImages.IMG_TOOL_COPY));
        _actions.put(ITextEditorActionConstants.COPY, action);

        action = new CommonAction(_sta.getViewer().getTextOperationTarget(), ITextOperationTarget.SELECT_ALL,
                Messages.SavePreviewDialog_select_all);
        _actions.put(ITextEditorActionConstants.SELECT_ALL, action);
    }

    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText(Messages.SavePreviewDialog_preview);
    }

    protected Control createContents(Composite parent)
    {
        parent.setLayout(new GridLayout());

        Composite outter = new Composite(parent, SWT.NONE);
        outter.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        outter.setLayout(layout);

        Composite top = new Composite(outter, SWT.NONE);
        top.setLayout(new GridLayout());
        GridData gd = new GridData(GridData.FILL_BOTH);
        top.setLayoutData(gd);
        createSQLArea(top);

        Composite bottom = new Composite(outter, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 4;
        bottom.setLayout(layout);
        bottom.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _notShowAgain = new Button(bottom, SWT.CHECK);
        _notShowAgain.setText(Messages.SavePreviewDialog_not_show_again);
        gd = new GridData();
        gd.grabExcessHorizontalSpace = true;
        gd.widthHint = 400;
        _notShowAgain.setLayoutData(gd);
        _notShowAgain.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                _store.setValue(Constants.PREFERENCE_ALWAYS_SHOW_PREVIEW, !_notShowAgain.getSelection());
            }
        });

        _execute = new Button(bottom, SWT.NONE);
        _execute.setText(Messages.SavePreviewDialog_execute);
        _execute.setLayoutData(new GridData());
        _execute.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                ScriptsExecutionRunnable scriptsRunnable = new ScriptsExecutionRunnable(_scripts, _databaseIdentifier,
                        new SQLExecutionJobListener(_editor, _monitor), _syncSave, _monitor, _groupExecutionDspString,
                        _consumerName);
                scriptsRunnable.run();
                _executing = true;
                SavePreviewDialog.this.close();
            }
        });

        _saveAs = new Button(bottom, SWT.NONE);
        _saveAs.setText(Messages.SavePreviewDialog_save_as);
        _saveAs.setLayoutData(new GridData());
        _saveAs.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                SaveAsDialog dlg = new SaveAsDialog(SOEUIPlugin.getActiveWorkbenchShell(), _scripts);

                IPreferenceStore store = SOEUIPlugin.getDefault().getPreferenceStore();
                boolean isOpen = store
                        .getBoolean(org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants.PREFERENCE_OPEN_FILE_AFTER_SAVEAS);

                dlg.setOriginalName(_editor.getDisplayName() + "_edit.sql");
                dlg.setOpenMode(isOpen);
                dlg.open();
                IEditorPart editor = dlg.getEditor();
                if (editor != null && (editor instanceof SQLEditor))
                {
                    ISQLEditorConnectionInfo connInfo = new SQLEditorConnectionInfo(SQLToolsFacade
                            .getConfigurationByProfileName(_databaseIdentifier.getProfileName())
                            .getDatabaseVendorDefinitionId(), _databaseIdentifier.getProfileName(), _databaseIdentifier
                            .getDBname(), _databaseIdentifier.getDBname());
                    ((SQLEditor) editor).setConnectionInfo(connInfo);
                }
                SavePreviewDialog.this.close();
            }
        });

        _cancel = new Button(bottom, SWT.NONE);
        _cancel.setText(Messages.SavePreviewDialog_cancel);
        _cancel.setLayoutData(new GridData());
        _cancel.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                SavePreviewDialog.this.close();
            }
        });

        boolean dontShowPreview = !_store.getBoolean(Constants.PREFERENCE_ALWAYS_SHOW_PREVIEW);
        _notShowAgain.setSelection(dontShowPreview);
        return outter;
    }

    private void createSQLArea(Composite comp)
    {
        String dbType = _editor.getEditorDescriptor().getVendorName()
                + "_" + _editor.getEditorDescriptor().getVersion(); //$NON-NLS-1$
        _sta = new SQLStatementArea(comp, SWT.BORDER, new SQLSourceViewerService(), true);
        _sta.setInput(_scripts, dbType);
        _sta.setEditable(false);
        _sta.setEnabled(true);
        _sta.configureViewer(new SQLSourceViewerConfiguration(dbType));
        _sta.setLayoutData(new GridData(GridData.FILL_BOTH));
        initializeActions();
        createContextMenu();
    }

    private void createContextMenu()
    {
        final MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                // update items' status
                Iterator iter = _actions.values().iterator();
                while (iter.hasNext())
                {
                    Object obj = iter.next();
                    if (obj instanceof IUpdate)
                    {
                        IUpdate action = (IUpdate) obj;
                        action.update();
                    }
                }
                menuMgr.add((IAction) _actions.get(ITextEditorActionConstants.COPY));
                menuMgr.add((IAction) _actions.get(ITextEditorActionConstants.SELECT_ALL));
            }
        });
        Menu menu = menuMgr.createContextMenu(_sta.getViewer().getTextWidget());
        _sta.getViewer().getTextWidget().setMenu(menu);
    }

    public boolean close()
    {
        if (!_executing && _monitor != null)
        {
            _monitor.setCanceled(true);
        }
        return super.close();
    }
}

class CommonAction extends Action implements IUpdate
{
    private int                  _operationCode;
    private ITextOperationTarget _target;

    /**
     * 
     */
    public CommonAction(ITextOperationTarget target, int operationCode, String itemName)
    {
        super();
        _operationCode = operationCode;
        _target = target;
        this.setText(itemName);
    }

    /**
     * delegate the call to ITextOperationTarget instance
     */
    public void run()
    {
        _target.doOperation(_operationCode);
    }

    public void update()
    {
        boolean wasEnabled = isEnabled();
        boolean isEnabled = (_target != null && _target.canDoOperation(_operationCode));
        setEnabled(isEnabled);

        if (wasEnabled != isEnabled)
        {
            firePropertyChange(ENABLED, wasEnabled ? Boolean.TRUE : Boolean.FALSE, isEnabled ? Boolean.TRUE
                    : Boolean.FALSE);
        }
    }

}
