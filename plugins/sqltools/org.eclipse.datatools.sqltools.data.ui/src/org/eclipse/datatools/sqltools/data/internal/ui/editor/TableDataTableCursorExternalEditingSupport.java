/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.ui.editor;


import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.sqltools.data.internal.ui.DataUIPlugin;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;

/**
 * TableDataTableCursorExternalEditingSupport provides the TableDataEditor with support for
 * handling the org.eclipse.wst.rdb.data.ui.externalTableDataEditor extension point. 
 * @author sschaer
 */
public class TableDataTableCursorExternalEditingSupport extends TableDataTableCursor{

    /** the editor this cursor belongs to */
    private ITableDataEditor editor;
    
    protected Button button;
    
    protected IExternalTableDataEditor[] cellEditors;
    
    /**
	 * @param tableViewer this editor's TableViewer
     * @param editor this cursor's TableDataEditor
	 */
	public TableDataTableCursorExternalEditingSupport(TableViewer tableViewer, ITableDataEditor editor) {
	    super(tableViewer);
        this.editor = editor;
        
        cellEditors = new IExternalTableDataEditor[ editor.getTableData().getColumnCount() ];
        for (int i=0; i<cellEditors.length; ++i) {
        	cellEditors[i] = DataUIPlugin.getDefault().newExternalTableDataCellEditor(editor, i);
        }
        
        GridLayout gd = new GridLayout();
        gd.marginHeight = 0;
        gd.marginWidth = 0;
        setLayout(gd);
                
        button = new Button(this, SWT.FLAT);
        button.setText("..."); //$NON-NLS-1$
        button.setLayoutData(new GridData(GridData.FILL_VERTICAL|GridData.GRAB_HORIZONTAL|GridData.HORIZONTAL_ALIGN_END));
        button.setVisible( cellEditors[getColumn()]!=null );
        
        button.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e) {
				editWithEditor();
				setFocus();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
        });
        
        addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				button.setVisible( cellEditors[getColumn()]!=null );				
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);				
			}        	
        });
        
        setTabList(new Control[] {});
	}
	
	
	public boolean setFocus() {
		// Keep the focus, do not pass it to the button
		checkWidget ();
		return forceFocus ();
	}

	
	/**
	 * @see org.eclipse.datatools.sqltools.data.internal.ui.editor.TableDataTableCursor#edit()
	 */
	public void editWithEditor() {
		try{
        	IExternalTableDataEditor externalEditor = cellEditors[getColumn()];
        	if (externalEditor != null){
        		externalEditor.externalEdit(editor);
        	} else{
        		super.edit();
        	}
        } catch (Exception ex) {
            DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, ex.getMessage(), ex);
            // something went wrong with the external 
            // editing. Try inline editing instead 
            super.edit();
        }
    }    
	
	protected void handleTraverse(TraverseEvent event)
	{
		if (event.detail==SWT.TRAVERSE_RETURN) {
			editWithEditor();
			event.doit = false;
		} else {
			super.handleTraverse(event);
		}		    
	}
}
