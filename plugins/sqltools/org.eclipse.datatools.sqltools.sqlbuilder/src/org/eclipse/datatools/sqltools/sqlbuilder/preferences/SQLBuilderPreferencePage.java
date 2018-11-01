/***********************************************************************************************************************
 * Copyright (c) 2007 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.preferences;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Configure SQL Builder preferences.
 * 
 * @author Jeremy Lindop
 */
public class SQLBuilderPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
    private IPreferenceStore _store	= getPreferenceStore();
	
    Button _btnOmitCurrentSchemaInSQL;
    Button _btnUseAUIDAsCurrentSchema;
    Button _btnSpecifyCurrentSchema;
    Text _txtCurrentSchema;

	protected Control createContents(Composite parent) {
		
		// TODO: set help context ID's

		GridData gd = null;
		
        Composite composite = new Composite(parent, SWT.NONE);
        
        GridLayout compositeLayout = new GridLayout(1, true);
        compositeLayout.marginHeight = 0;
        compositeLayout.marginWidth = 0;
        composite.setLayout(compositeLayout);
              
		Label lblCurrentSchemaDesc = new Label(composite, SWT.LEFT);
		lblCurrentSchemaDesc.setText(Messages._UI_PREFERENCES_OMIT_SCHEMA_DESC);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 1;
        lblCurrentSchemaDesc.setLayoutData(gd); 
		
        _btnOmitCurrentSchemaInSQL = new Button(composite, SWT.CHECK);
        _btnOmitCurrentSchemaInSQL.setText(Messages._UI_PREFERENCES_OMIT_SCHEMA_IN_SQL);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 1;
        _btnOmitCurrentSchemaInSQL.setLayoutData(gd);
        
        _btnOmitCurrentSchemaInSQL.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateControls();
            }
        }
        );
        
        Group groupSpecifySchema = new Group(composite, SWT.SHADOW_ETCHED_IN);
		gd = new GridData(SWT.FILL, GridData.BEGINNING, true, false);
		groupSpecifySchema.setLayoutData(gd);

		GridLayout groupSpecifySchemaLayout = new GridLayout(3, true);
		groupSpecifySchema.setLayout(groupSpecifySchemaLayout);
		groupSpecifySchema.setText(Messages._UI_PREFERENCES_OMIT_SCHEMA_SPECIFY_SCHEMA_GROUP_TITLE);
        
		_btnUseAUIDAsCurrentSchema = new Button(groupSpecifySchema, SWT.RADIO);
        _btnUseAUIDAsCurrentSchema.setText(Messages._UI_PREFERENCES_OMIT_SCHEMA_USE_AUID_AS_CURRENT_SCHEMA);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 3;
        _btnUseAUIDAsCurrentSchema.setLayoutData(gd);
        _btnUseAUIDAsCurrentSchema.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateControls();
            }
        }
        );
        
        _btnSpecifyCurrentSchema = new Button(groupSpecifySchema, SWT.RADIO);
        _btnSpecifyCurrentSchema.setText(Messages._UI_PREFERENCES_OMIT_SCHEMA_SCHEMA_NAME);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 1;
        _btnSpecifyCurrentSchema.setLayoutData(gd);
        _btnSpecifyCurrentSchema.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateControls();
            }
        }
        );

        _txtCurrentSchema = new Text(groupSpecifySchema, SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 2;
        _txtCurrentSchema.setLayoutData(gd);
        
        initializeValues();
        updateControls();
        
        return composite;
	}

	public void init(IWorkbench workbench) {
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
	
	/*
     * Initializes states of the controls from the preference store.
     */
    private void initializeValues() {
    	_btnOmitCurrentSchemaInSQL.setSelection(_store.getBoolean(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_IN_SQL));
    	if (_store.getBoolean(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_USE_AUID)){
    		_btnUseAUIDAsCurrentSchema.setSelection(true);
    		_btnSpecifyCurrentSchema.setSelection(false);
    	}
    	else {
    		_btnSpecifyCurrentSchema.setSelection(true);
    		_btnUseAUIDAsCurrentSchema.setSelection(false);
    	}
    	_txtCurrentSchema.setText(_store.getString(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_CURRENT_SCHEMA));
    }

    /*
     * Returns preference store that belongs to the our plugin.
     */
    protected IPreferenceStore doGetPreferenceStore()
    {
        return SQLBuilderPlugin.getPlugin().getPreferenceStore();
    }


    /**
     * Stores the values of the controls back to the preference store. This is called when the user presses the OK or
     * Apply button.
     */
    public boolean performOk()
    {
		_store.setValue(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_IN_SQL, _btnOmitCurrentSchemaInSQL.getSelection());
		_store.setValue(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_USE_AUID, _btnUseAUIDAsCurrentSchema.getSelection());
		_store.setValue(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_CURRENT_SCHEMA, _txtCurrentSchema.getText());
        return super.performOk();
    }	

    /*
     * This is called when the Defaults button is pressed.
     * 
     * @see PreferencePage.performDefaults()
     */
    protected void performDefaults()
    {
        super.performDefaults();

        _btnOmitCurrentSchemaInSQL.setSelection(_store
            .getDefaultBoolean(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_IN_SQL));
        if (_store.getDefaultBoolean(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_USE_AUID)){
        	_btnUseAUIDAsCurrentSchema.setSelection(true);
        	_btnSpecifyCurrentSchema.setSelection(false);
        }
        else {
        	_btnSpecifyCurrentSchema.setSelection(true);
        	_btnUseAUIDAsCurrentSchema.setSelection(false);
        }
        _txtCurrentSchema.setText(_store
        		.getDefaultString(SQLBuilderPreferenceConstants.OMIT_CURRENT_SCHEMA_CURRENT_SCHEMA));
        
        updateControls();
    }
    
}
