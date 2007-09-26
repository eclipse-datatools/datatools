package org.eclipse.datatools.sqltools.sqlbuilder.dialogs;

import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.preferences.SQLBuilderPreferenceConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.help.WorkbenchHelp;

/**
 * Dialog that shows the settings for Omitting or Including the current
 * schema name in generated SQL.
 * 
 * @author jeremyl
 *
 */
public class OmitCurrentSchemaDialog extends Dialog {

    private IPreferenceStore _store	= doGetPreferenceStore();
	
    SQLDomainModel domainModel;
    Object object; // SQLStatement

    Button _btnUseAUIDAsCurrentSchema;
    Button _btnSpecifyCurrentSchema;
    Text _txtCurrentSchema;
    Button _btnOmitCurrentSchemaInSQL;
    
    /**
     * Creates a dialog for OmitCurrentSchema settings with an OK and Cancel button.
     */

    public OmitCurrentSchemaDialog(Shell parentShell, SQLDomainModel domainModel, Object obj) {
        super(parentShell);
        this.domainModel = domainModel;
        this.object = obj;

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
        WorkbenchHelp.setHelp(composite, SQLBuilderContextIds.SQLB_ADD_TABLE_DIALOG); // TODO
        
        GridLayout compositeLayout = new GridLayout(1, true);
        composite.setLayout(compositeLayout);

       
		Group groupOmitSchema = new Group(composite, SWT.SHADOW_ETCHED_IN);
		gd = new GridData(SWT.FILL, GridData.BEGINNING, true, false);
		groupOmitSchema.setLayoutData(gd);

		GridLayout groupOmitSchemaLayout = new GridLayout(3, true);
		groupOmitSchema.setLayout(groupOmitSchemaLayout);
		groupOmitSchema.setText(Messages._UI_DIALOG_OMIT_SCHEMA_GROUP_TITLE);

        _btnOmitCurrentSchemaInSQL = new Button(groupOmitSchema, SWT.CHECK);
        _btnOmitCurrentSchemaInSQL.setText(Messages._UI_DIALOG_OMIT_SCHEMA_IN_SQL);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 3;
        _btnOmitCurrentSchemaInSQL.setLayoutData(gd);

        _btnOmitCurrentSchemaInSQL.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateControls();
            }
        }
        );
        
        _btnUseAUIDAsCurrentSchema = new Button(groupOmitSchema, SWT.RADIO);
        _btnUseAUIDAsCurrentSchema.setText(Messages._UI_DIALOG_OMIT_SCHEMA_USE_AUID_AS_CURRENT_SCHEMA);
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
        
        _btnSpecifyCurrentSchema = new Button(groupOmitSchema, SWT.RADIO);
        _btnSpecifyCurrentSchema.setText(Messages._UI_DIALOG_OMIT_SCHEMA_SPECIFY_SCHEMA_IN_SQL);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 3;
        _btnSpecifyCurrentSchema.setLayoutData(gd);
        _btnSpecifyCurrentSchema.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateControls();
            }
        }
        );

        Label lblCurrentSchema = new Label(groupOmitSchema, SWT.LEFT);
        lblCurrentSchema.setText(Messages._UI_DIALOG_OMIT_SCHEMA_CURRENT_SCHEMA);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 1;
        lblCurrentSchema.setLayoutData(gd);
        
        _txtCurrentSchema = new Text(groupOmitSchema, SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 2;
        _txtCurrentSchema.setLayoutData(gd);
        
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
    
}
