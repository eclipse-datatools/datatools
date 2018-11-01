/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.dialogs;

import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A simple input dialog with a multi-line text control.
 * 
 * @author jeremyl
 *
 */
public class MultilineInputDialog extends Dialog {

	private String m_title;
	private String m_columnName;
	private String m_input;
	private Text m_text;
	
	private static final int NUM_LINES = 20;
	
	/**
	 * Creates a dialog with a multi-line text box and a title.
	 * 
	 * @param parentShell
	 * @param title
	 */
	public MultilineInputDialog(Shell parentShell, String title, String columnName) {
		super(parentShell);
		m_title = title;
		m_columnName = columnName;
        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER | SWT.SYSTEM_MODAL);
        setBlockOnOpen(true);
       
	}

	/**
	 * Sets the value in the multi-line text box.
	 * 
	 * @param input the text to pass into the dialog
	 */
	public void setText(String input){
		m_input = input;
	}
	
	/**
	 * Gets the value from the multi-line text box.
	 * 
	 * @return the text from the dialog
	 */
	public String getText(){
		return m_input;
	}

    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        
        String sTitle = "";
        if (m_title != null && m_title.length() > 0){
        	sTitle = m_title;
        }
        if (m_columnName != null && m_columnName.length() > 0){
        	if (sTitle.length() > 0){
        		sTitle = sTitle + " - " + m_columnName;
        	}
        	else {
        		sTitle = m_columnName;
        	}
        }
        shell.setText(sTitle);
    }
	
	protected void okPressed(){
		m_input = m_text.getText();
		if (m_input.length() == 0 ){
			super.getButton(OK).setEnabled(false);
	    }else {
		  super.okPressed();
	    }
	}
	
	protected Control createDialogArea(Composite parent){
		Composite composite = ViewUtility.createComposite(parent, 1, true);
		// Create multi-line text box.
		// Passing SWT.DEFAULT as width lets SWT set the size appropriate to the input.
		// NUM_LINES sets the height for the text box.
		m_text = ViewUtility.createWrappedMultiTextField(parent, SWT.DEFAULT, NUM_LINES, true);
		m_text.setText(m_input);
		
		//BZ203353 drigby 20090406
		m_text.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                updateButtonState();
            }

        });

		return composite;
	}
	
	private void updateButtonState() {
		if (m_text.getText().trim().length() > 0 ){
			super.getButton(OK).setEnabled(true);
		}else {
			super.getButton(OK).setEnabled(false);
		}
		m_text.setFocus();
	}

}
