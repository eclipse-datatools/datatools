/**
 * Created on 2005-11-8
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;


import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 
 * @author lihuang
 *
 */
public class TableComboBoxCellEditor extends ComboBoxCellEditor
{
    private CCombo                  _combo;
    public static final int         VISIBLE_ITEM_COUNT = 15;
    protected AccessibleTableViewer _accessibleTableViewer;

    public TableComboBoxCellEditor(AccessibleTableViewer accessibleTableViewer, Composite parent, String[] items, int style)
    {

        super(parent, items, style);
        _accessibleTableViewer = accessibleTableViewer;
    }

    /**
     * Creates a new cell editor with a combo containing the given 
     * list of choices and parented under the given table which is in <code>AccessibleTableViewer</code>.
     * The cell editor value is the zero-based index of the selected item.
     *
     * @param accessibleTableViewer AccessibleTableViewer
     * @param items the list of strings for the combo box
     */
    public TableComboBoxCellEditor(AccessibleTableViewer accessibleTableViewer, String[] items)
    {
        this(accessibleTableViewer, items, SWT.NONE);
    }

    /**
     * Creates a new cell editor with a combo containing the given 
     * list of choices and parented under the given table which is in <code>AccessibleTableViewer</code>.
     * The cell editor value is the zero-based index of the selected item.
     * 
     * @param accessibleTableViewer AccessibleTableViewer
     * @param items the list of strings for the combo box
     * @param style the style bits
     */
    public TableComboBoxCellEditor(AccessibleTableViewer accessibleTableViewer, String[] items, int style)
    {
        super(accessibleTableViewer.getTable(), items, style);
        _accessibleTableViewer = accessibleTableViewer;
        setItems(items);
        if(items.length > VISIBLE_ITEM_COUNT)
        {
            _combo.setVisibleItemCount(VISIBLE_ITEM_COUNT);
        }
        else
        {
            _combo.setVisibleItemCount(items.length);
        }
    }

    protected Control createControl(Composite parent)
    {
        Control c = super.createControl(parent);
        c.addTraverseListener(new TraverseListener()
        {
            public void keyTraversed(TraverseEvent e)
            {
                handleTraverse(e);

            }
        }
        );
        c.addFocusListener(new FocusListener()
        {

            public void focusGained(FocusEvent e)
            {
                setText();
            }

            public void focusLost(FocusEvent e)
            {
            }
        }
        );
        _combo = (CCombo)c;
        return c;
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
     * To support tab navigator, override this method.
     */
    protected void keyReleaseOccured(KeyEvent keyEvent)
    {
        if (keyEvent.character == '\u001b')
        {
            // Escape character
            fireCancelEditor();
        }
    }

    private void setText()
    {
        IRowData rowData = _accessibleTableViewer.getRow();
        if (rowData != null)
        {
            int col = _accessibleTableViewer.getCursor().getColumn();
            if (rowData.getValue(col) instanceof String)
            {
                String text = (String) rowData.getValue(col);
                if (text != null)
                {
                    ((CCombo) getControl()).setText(text);
                }
            }
        }
    }

    public void setItems(String[] items)
    {
        //Set combo visible itemCount
        if(items.length > VISIBLE_ITEM_COUNT)
        {
            _combo.setVisibleItemCount(VISIBLE_ITEM_COUNT);
        }
        else
        {
            _combo.setVisibleItemCount(items.length);
        }
        super.setItems(items);
    }

    public CCombo getCombo()
    {
        return _combo;
    }
}


