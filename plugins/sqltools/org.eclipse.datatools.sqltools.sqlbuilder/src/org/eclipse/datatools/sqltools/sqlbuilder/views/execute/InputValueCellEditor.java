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

package org.eclipse.datatools.sqltools.sqlbuilder.views.execute;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.MultilineInputDialog;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.CCombo;

/**
 * Extends the ComboBoxCellEditor launch the input dialog if selected by user 
 */
public class InputValueCellEditor extends ComboBoxCellEditor 
    implements SelectionListener {
        
    protected CCombo myCombo;    
    private Table myTable;
    private int lastSelectedRow = -1; // latest row selected by user
    private int lastEditedIndex = -1; // row last edited
    private ParameterTableViewer myViewer;    
    
    /**
     * Creates an instance of InputValueCellEditor
     * @param parent the parent Composite
     * @param viewer the parent viewer 
     * @param items the list of combo items     
     */
    public InputValueCellEditor(Composite parent, ParameterTableViewer viewer, String[] items) {    	
        super(parent, items); 
        myViewer = viewer;
        myTable = (Table)parent;
        myTable.addSelectionListener(this);               
    }
    
    /**
     * Creates the Control for InputValueCellEditor
     * @param parent the parent composite
     */
    protected Control createControl(Composite parent) {
    	myCombo = (CCombo)super.createControl(parent);        
        myCombo.addSelectionListener(this);        
        return (myCombo);
    }    
    
    public void focusLost() {                
        if (lastSelectedRow != lastEditedIndex) {
        	return;
        }
        
        String textData = myCombo.getText();        
        if (lastSelectedRow != -1 && textData != null) {        	
            // Set the new text to paramterElement
            TableItem tabItem = myTable.getItem(lastSelectedRow);
            Object obj = tabItem.getData();
            if (obj instanceof ParameterElement) {            	
                ((ParameterElement)obj).setValue(textData);
                // refresh parent viewer 
                refreshViewer();              
            }
        } 
        //fireApplyEditorValue();
    }
    
    /**
     * Refreshes the viewer
     */
    protected void refreshViewer() {
        Display.getCurrent().asyncExec(new Runnable() {

            public void run() {
                myViewer.refresh();
            }
        });
        myViewer.getParameterMarkerPage().updateFinishButton();
    }

    public void widgetDefaultSelected(SelectionEvent evt) {
        // Ignore        
    }    
    
    public void widgetSelected(SelectionEvent evt) {    	
        Object source = evt.getSource();
        if (source == myCombo) {        	
            String text = showInputEditor();
        }
        else if (source == myTable) {        	
            lastSelectedRow = myTable.getSelectionIndex();            
        }
    }    
    
    protected void doSetValue(Object value) {                
        if (value instanceof ParameterElement) {
            lastEditedIndex = myTable.getSelectionIndex();
        	ParameterElement ele = (ParameterElement)value;            
            myCombo.setText(ele.getColumnText(2));            
        }      
    } 
    
    /**
     * Lauches the input editor to allow user to enter long text.
     * The current content of the row will be the initial text of the dialog.
     * <p>
     * Text entered by user will be used to set the value for the Parameter element
     * @return the text entered by user
     */
    protected String showInputEditor() {
        TableItem tabItem = myTable.getItem(myTable.getSelectionIndex());
        ParameterElement parm = (ParameterElement)tabItem.getData();
        String initialText = parm.getColumnText(2);
    	MultilineInputDialog inputDialog = new MultilineInputDialog(Display.getDefault().getActiveShell(), 
    			Messages._UI_SPECIFY_VALUE_TITLE, parm.getColumnText(0));
    	inputDialog.setText(initialText);
    	inputDialog.open();
    	String text = inputDialog.getText();
        parm.setValue(text);
        doSetValue(parm);
        refreshViewer();
        return text;
    }
}
