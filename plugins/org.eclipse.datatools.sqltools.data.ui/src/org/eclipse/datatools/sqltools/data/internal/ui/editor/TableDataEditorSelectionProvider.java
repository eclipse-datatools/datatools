/*
 * Created on Sep 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.datatools.sqltools.data.internal.ui.editor;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TableDataEditorSelectionProvider implements ISelectionProvider
{
	protected ITableDataEditor editor;
	protected ListenerList<ISelectionChangedListener> selectionChangedListeners = new ListenerList<>();
	
	public TableDataEditorSelectionProvider(ITableDataEditor editor)
	{
		this.editor = editor;
		registerSelectionListener();
	}
	
    protected void registerSelectionListener() {
		editor.getCursor().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			    selectionChanged();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				selectionChanged();
			}
		});       
    }
	
    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        selectionChangedListeners.add(listener);        
    }

    public ISelection getSelection() {    	
        if (editor.getCursor().getRow()==null)
            return null;
        else 
        	return new StructuredSelection( new TableDataCell(editor, editor.getCursor().getRow().getData(), editor.getCursor().getColumn()) );
    }

    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        selectionChangedListeners.remove(listener);        
    }

    public void setSelection(ISelection selection) {
        // TODO Auto-generated method stub
    }
	
	public void selectionChanged() {
	    for (ISelectionChangedListener listener : this.selectionChangedListeners) {
			Platform.run(new SafeRunnable() {
				public void run() {
					listener.selectionChanged(new SelectionChangedEvent(TableDataEditorSelectionProvider.this, getSelection()));
				}
			});		
		}
	}
}
