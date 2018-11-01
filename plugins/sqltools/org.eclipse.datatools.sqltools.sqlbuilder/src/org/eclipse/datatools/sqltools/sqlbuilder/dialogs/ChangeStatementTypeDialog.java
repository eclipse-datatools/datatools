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

import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Dialog that lets the user select a new SQL statement type.
 * 
 * @author jeremyl
 *
 */
public class ChangeStatementTypeDialog extends Dialog {

	protected int _statementType;
	protected Combo _comboType;

    /*
     * Class for associating a StatementHelper statement type constant with the statement name
     */
	class StatementTypeInfo {
    	int _type;
    	String _name;
    	
    	StatementTypeInfo(int type, String name){
    		_type = type;
    		_name = name;
    	}
    	
    };

	protected StatementTypeInfo[] _arrStatementInfo = {
	    	new StatementTypeInfo(StatementHelper.STATEMENT_TYPE_SELECT, "SELECT"),
			new StatementTypeInfo(StatementHelper.STATEMENT_TYPE_INSERT, "INSERT"),
			new StatementTypeInfo(StatementHelper.STATEMENT_TYPE_UPDATE, "UPDATE"),
			new StatementTypeInfo(StatementHelper.STATEMENT_TYPE_DELETE, "DELETE") };
    
    /**
     * Creates a dialog for selecting statement type settings with an OK and Cancel button.
     */

    public ChangeStatementTypeDialog(Shell parentShell, int statementType) {
        super(parentShell);
        _statementType = statementType;

        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER | SWT.SYSTEM_MODAL);
        setBlockOnOpen(true);
    }
	
    public int open() {
        return super.open();
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);

        shell.setText(Messages._UI_DIALOG_CHANGE_STATEMENT_TYPE_TITLE);
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

		Label lblNote = new Label(composite, SWT.WRAP);
		lblNote.setText(Messages._UI_DIALOG_CHANGE_STATEMENT_TYPE_NOTE);
		gd = new GridData(SWT.FILL, GridData.BEGINNING, true, false);
		lblNote.setLayoutData(gd);
		
        Group groupStatementType = new Group(composite, SWT.SHADOW_ETCHED_IN);
		gd = new GridData(SWT.FILL, GridData.BEGINNING, true, false);
		groupStatementType.setLayoutData(gd);

		GridLayout groupStatementTypeLayout = new GridLayout(1, true);
		groupStatementType.setLayout(groupStatementTypeLayout);
		groupStatementType.setText(Messages._UI_DIALOG_CHANGE_STATEMENT_TYPE_GROUP_TITLE);
      
		_comboType = new Combo(groupStatementType, SWT.DROP_DOWN);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 1;
        _comboType.setLayoutData(gd);
		
		for (int i = 0; i < _arrStatementInfo.length; i++){
			_comboType.add(_arrStatementInfo[i]._name);
		}
		
        _comboType.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateStatementType();
            }
        }
        );

        initControls();
        
        return composite;
    }

    public int getStatementType(){
    	return _statementType;
    }
    
    protected void initControls(){
    	
    	String sStatementType = _arrStatementInfo[0]._name;
    	for (int i = 0; i < _arrStatementInfo.length; i++){
    		if (_arrStatementInfo[i]._type == _statementType){
    			sStatementType = _arrStatementInfo[i]._name;
    			break;
    		}
    	}
    	_comboType.setText(sStatementType);
    }
    
    protected void updateStatementType(){
    	String selectedType = _comboType.getText();
    	
    	for (int i = 0; i < _arrStatementInfo.length; i++){
    		if (_arrStatementInfo[i]._name.equals(selectedType)){
    			_statementType = _arrStatementInfo[i]._type;
    			break;
    		}
    	}
    }
}
