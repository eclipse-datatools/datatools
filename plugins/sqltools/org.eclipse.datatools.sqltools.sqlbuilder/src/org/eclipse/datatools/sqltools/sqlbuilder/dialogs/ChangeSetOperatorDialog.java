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

import org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Dialog that lets the user select a new SQL Set Operator.
 * 
 * @author jeremyl/drigby
 *
 */
public class ChangeSetOperatorDialog extends Dialog {

	protected int _operatorType;
	protected Combo _comboType;

    /*
     * Class for associating a set operator constant with the operator name
     */
	class OperatorTypeInfo {
    	int _type;
    	String _name;
    	
    	OperatorTypeInfo(int type, String name){
    		_type = type;
    		_name = name;
    	}
    	
    };

	protected OperatorTypeInfo[] _arrOperatorInfo = {
	   	    	
		new OperatorTypeInfo(QueryCombinedOperator.UNION, SQLBuilderConstants.P_OPERATOR_UNION),
	    new OperatorTypeInfo(QueryCombinedOperator.INTERSECT, SQLBuilderConstants.P_OPERATOR_INTERSECT),
		new OperatorTypeInfo(QueryCombinedOperator.EXCEPT, SQLBuilderConstants.P_OPERATOR_EXCEPT ),
	    new OperatorTypeInfo(QueryCombinedOperator.UNION_ALL, SQLBuilderConstants.P_OPERATOR_UNION_ALL ),
	    new OperatorTypeInfo(QueryCombinedOperator.INTERSECT_ALL, SQLBuilderConstants.P_OPERATOR_INTERSECT_ALL ),
	    new OperatorTypeInfo(QueryCombinedOperator.EXCEPT_ALL, SQLBuilderConstants.P_OPERATOR_EXCEPT_ALL)
	
	};
    
    /**
     * Creates a dialog for selecting statement type settings with an OK and Cancel button.
     */

    public ChangeSetOperatorDialog(Shell parentShell, int operatorType) {
        super(parentShell);
        _operatorType = operatorType;

        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER | SWT.SYSTEM_MODAL);
        setBlockOnOpen(true);
    }
	
    public int open() {
        return super.open();
    }

    protected void configureShell(Shell shell) {
        super.configureShell(shell);

        shell.setText(Messages._UI_DIALOG_CHANGE_SET_OPERATOR_TITLE);
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
		
        Group groupStatementType = new Group(composite, SWT.SHADOW_ETCHED_IN);
		gd = new GridData(SWT.FILL, GridData.BEGINNING, true, false);
		groupStatementType.setLayoutData(gd);

		GridLayout groupStatementTypeLayout = new GridLayout(1, true);
		groupStatementType.setLayout(groupStatementTypeLayout);
		groupStatementType.setText(Messages._UI_DIALOG_CHANGE_SET_OPERATOR_GROUP_TITLE);
      
		_comboType = new Combo(groupStatementType, SWT.DROP_DOWN);
        gd = new GridData(SWT.FILL, SWT.BOTTOM, true, false);
        gd.horizontalSpan = 1;
        _comboType.setLayoutData(gd);
		
		for (int i = 0; i < _arrOperatorInfo.length; i++){
			_comboType.add(_arrOperatorInfo[i]._name);
		}
		
        _comboType.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
            	updateOperatorType();
            }
        }
        );

        initControls();
        
        return composite;
    }

    public int getOperatorType(){
    	return _operatorType;
    }
    
    protected void initControls(){
    	
    	String sOperatorType = _arrOperatorInfo[0]._name;
    	for (int i = 0; i < _arrOperatorInfo.length; i++){
    		if (_arrOperatorInfo[i]._type == _operatorType){
    			sOperatorType = _arrOperatorInfo[i]._name;
    			break;
    		}
    	}
    	_comboType.setText(sOperatorType);
    }
    
    protected void updateOperatorType(){
    	String selectedType = _comboType.getText();
    	
    	for (int i = 0; i < _arrOperatorInfo.length; i++){
    		if (_arrOperatorInfo[i]._name.equals(selectedType)){
    			_operatorType = _arrOperatorInfo[i]._type;
    			break;
    		}
    	}
    }
}
