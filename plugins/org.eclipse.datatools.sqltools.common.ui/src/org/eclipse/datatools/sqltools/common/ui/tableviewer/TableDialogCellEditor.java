/**
 * Created on 2006-08-21
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * A dialog cell editor that supports tab traverse function. Dialog cell editors usually have a label control on the
 * left and a button on the right. Pressing the button opens a dialog window (for example, a color dialog or a file
 * dialog) to change the cell editor's value. The cell editor's value is the value of the dialog.
 * <p>
 * Subclasses need implement the following method:
 * <ul>
 * <li><code>openDialogBox</code>: opens the dialog box when the end user presses the button</li>
 * </ul>
 * </p>
 * 
 * @author lihuang
 * 
 */

public abstract class TableDialogCellEditor extends DialogCellEditor
{

    protected AccessibleTableViewer _accessibleTableViewer;

    /**
     * Creates a new dialog cell editor parented under the given AccessibleTableViewer.
     * 
     * @param accessibleTableViewer AccessibleTableViewer
     */
    public TableDialogCellEditor(AccessibleTableViewer accessibleTableViewer)
    {
        super(accessibleTableViewer.getTable(), SWT.NONE);
        _accessibleTableViewer = accessibleTableViewer;
    }

    /**
     * Creates the button for this cell editor under the given parent control.
     * <p>
     * The default implementation of this framework method creates the button display on the right hand side of the
     * dialog cell editor. Subclasses may extend or reimplement.
     * </p>
     * 
     * @param parent the parent control
     * @return the new button control
     */
    protected Button createButton(Composite parent)
    {
        Button result = new Button(parent, SWT.DOWN);
        result.setText("..."); //$NON-NLS-1$

        // when focus is lost, should save current value
        result.addFocusListener(new FocusAdapter()
        {
            public void focusLost(FocusEvent event)
            {
                TableDialogCellEditor.this.focusLost();
            }
        }
        );

        // //when "ESC" is pressed, should consume this event to prevent the main dialog from closing
        result.addTraverseListener(new TraverseListener()
        {
            public void keyTraversed(TraverseEvent event)
            {
                if (event.detail == SWT.TRAVERSE_ESCAPE || event.detail == SWT.TRAVERSE_RETURN)
                {
                    event.doit = false;
                }
                handleTraverse(event);
            }
        }
        );

        return result;
    }

    protected void handleTraverse(TraverseEvent e)
    {
        if (e.detail == SWT.TRAVERSE_TAB_PREVIOUS || e.detail == SWT.TRAVERSE_TAB_NEXT)
        {
            fireApplyEditorValue();
            deactivate();
            _accessibleTableViewer.getCursor().handleTraverse(e);
            _accessibleTableViewer.getCursor().edit();
        }
    }

    /**
     * Opens a dialog box under the given parent control and returns the dialog's value when it closes, or
     * <code>null</code> if the dialog was cancelled or no selection was made in the dialog.
     * <p>
     * This framework method must be implemented by concrete subclasses. It is called when the user has pressed the
     * button and the dialog box must pop up.
     * </p>
     * 
     * @param cellEditorWindow the parent control cell editor's window so that a subclass can adjust the dialog box
     *            accordingly
     * @return the selected value, or <code>null</code> if the dialog was cancelled or no selection was made in the
     *         dialog
     */
    public abstract Object openDialogBox(Control cellEditorWindow);

}
