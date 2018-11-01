/*******************************************************************************
 * Copyright 2000, 2018 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.dialogs;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.osgi.util.NLS;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * Dialog that shows the settings for Omitting or Including the current
 * schema name in generated SQL.
 * 
 * @author jeremyl
 *
 */
public class OmitCurrentSchemaDialog extends Dialog {

    IOmitSchemaInfo _omitSchemaInfo;
    String _userName;

    Button _btnUseAUIDAsCurrentSchema;
    Button _btnSpecifyCurrentSchema;
    Text _txtCurrentSchema;
    Button _btnOmitCurrentSchemaInSQL;
    
    /**
     * Creates a dialog for OmitCurrentSchema settings with an OK and Cancel button.
     */

    public OmitCurrentSchemaDialog(Shell parentShell, IOmitSchemaInfo omitSchemaInfo, String userName) {
        super(parentShell);
        _omitSchemaInfo = omitSchemaInfo;
        _userName = userName;

        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER | SWT.SYSTEM_MODAL);
        setBlockOnOpen(true);
    }
	
    public int open() {
        return super.open();
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);

        shell.setText(Messages._UI_DIALOG_OMIT_SCHEMA_TITLE);
    }
    
    protected void buttonPressed(int buttonId) {
        if (buttonId == Dialog.OK){
        	
        }
        else if (buttonId == Dialog.CANCEL) {
            setReturnCode(Dialog.CANCEL);
        }
        close();
    }
    
    /**
     * Creates and returns the contents of an area of the dialog which appears
     * below the message and above the button bar.
     */
    public Control createDialogArea(Composite parent) {

		GridData gd = null;
		
        Composite composite = new Composite(parent, SWT.NONE);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, SQLBuilderContextIds.SQLB_ADD_TABLE_DIALOG); // TODO
        
        GridLayout compositeLayout = new GridLayout(1, true);
        composite.setLayout(compositeLayout);
		gd = new GridData(SWT.FILL, GridData.FILL, true, false);
		composite.setLayoutData(gd);

       
        _btnOmitCurrentSchemaInSQL = new Button(composite, SWT.CHECK);
        _btnOmitCurrentSchemaInSQL.setText(Messages._UI_DIALOG_OMIT_SCHEMA_IN_SQL);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 1;
        _btnOmitCurrentSchemaInSQL.setLayoutData(gd);

        _btnOmitCurrentSchemaInSQL.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateValues();
            	updateControls();
            }
        }
        );
        
        Group groupSpecifySchema = new Group(composite, SWT.SHADOW_ETCHED_IN);
		gd = new GridData(SWT.FILL, GridData.BEGINNING, true, false);
		groupSpecifySchema.setLayoutData(gd);

		GridLayout groupSpecifySchemaLayout = new GridLayout(3, true);
		groupSpecifySchema.setLayout(groupSpecifySchemaLayout);
		groupSpecifySchema.setText(Messages._UI_DIALOG_OMIT_SCHEMA_SPECIFY_SCHEMA_GROUP_TITLE);
        
        _btnUseAUIDAsCurrentSchema = new Button(groupSpecifySchema, SWT.RADIO);
        String _useAUIDText;
        if (_userName != null && _userName.length() > 0){
        	_useAUIDText = NLS.bind(Messages._UI_DIALOG_OMIT_SCHEMA_USE_AUID_USER_AS_CURRENT_SCHEMA, _userName);
        }
        else {
        	_useAUIDText = Messages._UI_DIALOG_OMIT_SCHEMA_USE_AUID_AS_CURRENT_SCHEMA;
        }
        _btnUseAUIDAsCurrentSchema.setText(_useAUIDText);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 3;
        _btnUseAUIDAsCurrentSchema.setLayoutData(gd);
        _btnUseAUIDAsCurrentSchema.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateValues();
            	updateControls();
            }
        }
        );
        
        _btnSpecifyCurrentSchema = new Button(groupSpecifySchema, SWT.RADIO);
        _btnSpecifyCurrentSchema.setText(Messages._UI_DIALOG_OMIT_SCHEMA_SCHEMA_NAME);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 1;
        _btnSpecifyCurrentSchema.setLayoutData(gd);
        _btnSpecifyCurrentSchema.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateValues();
            	updateControls();
            }
        }
        );

       
        _txtCurrentSchema = new Text(groupSpecifySchema, SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 2;
        _txtCurrentSchema.setLayoutData(gd);
        _txtCurrentSchema.addModifyListener(new ModifyListener()
        {
			public void modifyText(ModifyEvent e) {
            	updateValues();
            	updateControls();
			}
        	
        }
        );
        
        initializeValues();
        updateControls();
        
        return composite;
    }

	private void updateControls() {
		if (_btnOmitCurrentSchemaInSQL.getSelection()){
			_btnUseAUIDAsCurrentSchema.setEnabled(true);
			_btnSpecifyCurrentSchema.setEnabled(true);
			if (_btnUseAUIDAsCurrentSchema.getSelection()){
				_txtCurrentSchema.setEnabled(false);
			}
			else {
				_txtCurrentSchema.setEnabled(true);
			}
		}
		else {
			_btnUseAUIDAsCurrentSchema.setEnabled(false);
			_btnSpecifyCurrentSchema.setEnabled(false);
			_txtCurrentSchema.setEnabled(false);
		}
	}

	private void updateValues() {
		_omitSchemaInfo.setOmitCurrentSchema(_btnOmitCurrentSchemaInSQL.getSelection() ? true : false);
		_omitSchemaInfo.setUseAUIDAsCurrentSchema(_btnUseAUIDAsCurrentSchema.getSelection() ? true : false);
		_omitSchemaInfo.setCurrentSchema(_txtCurrentSchema.getText() == null ? "" : _txtCurrentSchema.getText());
		
	}
	
	/*
     * Initializes states of the controls from the preference store.
     */
    private void initializeValues() {
    	_btnOmitCurrentSchemaInSQL.setSelection(_omitSchemaInfo.getOmitCurrentSchema());
    	if (_omitSchemaInfo.getUseAUIDAsCurrentSchema()){
    		_btnUseAUIDAsCurrentSchema.setSelection(true);
    		_btnSpecifyCurrentSchema.setSelection(false);
    	}
    	else {
    		_btnSpecifyCurrentSchema.setSelection(true);
    		_btnUseAUIDAsCurrentSchema.setSelection(false);
    	}
    	if (_omitSchemaInfo.getCurrentSchema() != null 
    		&& _omitSchemaInfo.getCurrentSchema().length() > 0){
    		_txtCurrentSchema.setText(_omitSchemaInfo.getCurrentSchema());
    	}
    }

    
    
}
