/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.parameter.internal;


import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.ISqlDataValidator;
import org.eclipse.datatools.sqltools.core.dbitem.IParamListener;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterWrapper;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Yang Liu
 */
public class ParameterTableDialog extends TitleAreaDialog
{
    ParameterTableViewer _parameterTable;

    ParameterWrapper[]   _wrappers;

    ParameterTableDialog _dialog;

    String               _wrongParam;

    int                  BUTTON_LOADDEFALUT_ID =100;
    
    static final String        FILE_EXTENSION        = ".xml";

    private void loadDefaultValue()
    {
        if (_wrappers!=null) 
        {
            for (int i=0;i<_wrappers.length;i++)
            {
                ParameterDescriptor param =  _wrappers[i].getParameterDescriptor();
                if (param!=null) 
                {
                    String value = param.getDefaultValue();
                    if (value!=null) 
                    {
                        _wrappers[i].setValue(value);
                        _wrappers[i].setNull(false);
                    }
                    else 
                    {
                        _wrappers[i].setValue(null);
                        _wrappers[i].setNull(true);
                    }
                }
            }
        }
    }

    protected void setFillLayoutData(Button button)
    {
        GridData data = new GridData(GridData.GRAB_HORIZONTAL);
        data.heightHint = convertVerticalDLUsToPixels(IDialogConstants.BUTTON_HEIGHT);
        int widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
        data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT,
            SWT.DEFAULT, true).x);
        button.setLayoutData(data);
    }
    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent)
    {
        //create loaddefault button
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);

        parent.setLayoutData(gridData);
        ((GridLayout) parent.getLayout()).makeColumnsEqualWidth = false;
        Button btn = createButton(parent, BUTTON_LOADDEFALUT_ID, Messages
            .getString("ParameterTableDialog.button.label.LoadDefault"), true); //$NON-NLS-1$
        setFillLayoutData(btn);

        btn.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
                loadDefaultValue();
                _parameterTable.refresh();
            }
        }
        );
        super.createButtonsForButtonBar(parent);
    }

    /**
     * @param parentShell
     */
    public ParameterTableDialog(Shell parentShell, ParameterWrapper[] wrappers)
    {
        super(parentShell);
        _dialog = this;
        setShellStyle(getShellStyle() | SWT.RESIZE);
        this._wrappers = wrappers;
        for (int i = 0; i < wrappers.length; i++)
        {
            wrappers[i].addParamListener(listener);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText(Messages.getString("ParameterTableDialog.title")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent)
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent.getShell(), HelpUtil.getContextId(IHelpContextIds.SP_LAUNCH_CONFIGURATION_PARAMETERS, SQLEditorPlugin.getDefault().getBundle().getSymbolicName())); 

        Composite composite = (Composite) super.createDialogArea(parent);
        int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

        _parameterTable = new ParameterTableViewer(composite, _wrappers, style);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = 400;
        gd.heightHint = 200;
        _parameterTable.getControl().setLayoutData(gd);
        setTitle(Messages.getString("ParameterTableDialog.title")); //$NON-NLS-1$
        //DmptoolPlugin.getResultManager().addParamManagerListener(listener);
        return composite;

    }
    /**
     * this listener's main job is to monitor the validation result. If there is  any 
     * problems in user's input, the relative information should be shown to user.
     */
    IParamListener listener = new IParamListener()
    {
        public void paramValueUpdated(ParameterDescriptor pd, String value)
        {
            _dialog.getButton(IDialogConstants.OK_ID).setEnabled(true);
            _dialog.setErrorMessage(null);
            _dialog.setMessage(null);

            //we must check all parameters
            for (int i = 0; i < _wrappers.length; i++)
            {
                ParameterWrapper pw = _wrappers[i];
                int status = pw.getStatus();
                switch(status)
                {
                    case ISqlDataValidator.VALIDATE_SUCCESS:
                        {
                            if(pw.getMessage() != null)
                            {
                                pw.setMessage(null);
                            }
                            break;
                        }
                    case ISqlDataValidator.VALIDATE_FAIL_CONVERT_SUCCESS:
                        {
                            if (pw.getMessage() != null)
                            {
                                _dialog.setMessage(pw.getMessage(),IMessageProvider.WARNING);
                                pw.setMessage(null);
                                break;
                            }
                            break;
                        }
                    case ISqlDataValidator.CONVERT_FAIL:
                        {
                            if (pw.getMessage() != null)
                            {
                                _dialog.getButton(IDialogConstants.OK_ID).setEnabled(false);
                                _dialog.setErrorMessage(pw.getMessage());
                                return;
                            }
                            return;
                        }
                    case ISqlDataValidator.SYS_ERROR:
                        {
                            if (pw.getMessage() != null)
                            {
                                _dialog.getButton(IDialogConstants.OK_ID).setEnabled(false);
                                _dialog.setErrorMessage(pw.getMessage());
                                return;
                            }
                            return;
                        }
                }
            }
        }
    }
    ;
}
