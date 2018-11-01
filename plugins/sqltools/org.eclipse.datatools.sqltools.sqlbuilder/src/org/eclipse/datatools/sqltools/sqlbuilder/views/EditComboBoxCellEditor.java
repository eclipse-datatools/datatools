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
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import java.util.Arrays;
import java.util.Vector;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.sqltools.sqlbuilder.model.ExpressionHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;


public abstract class EditComboBoxCellEditor extends CellEditor {
    
    protected Composite fEditor;
    protected CCombo combo;

    protected String fSelection;
    protected LabelValuePair[] fItems;
    protected Object fValue;
    protected boolean fAddItems;
    //added to keep the value of corresponding Combo box
    private boolean needQuotes = false;
    private int pairDataType;

    public void createItems(LabelValuePair[] items) {
        fItems = items;
    }

    public LabelValuePair[] getComboBoxItems() {
        return fItems;
    }

    private class ComboCellLayout extends Layout {

        public void layout(Composite editor, boolean force) {
            Rectangle bounds = editor.getClientArea();
            combo.setBounds(0, 0, bounds.width, bounds.height);
        }

        public Point computeSize(Composite editor, int wHint, int hHint, boolean force) {
            if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
                return new Point(wHint, hHint);
            }
            Point size = combo.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            // BZ 225491 drigby@sybase.com 5 Nov 2008
            //int width = 0;
            //if (fItems != null) {
            //    GC gc = new GC(combo);
            //    for (int i = 0; i < fItems.length; i++) {
            //        width = Math.max(width, gc.textExtent(fItems[i].fLabel).x);
            //    }
            //    width += gc.textExtent("M").x;
            //    gc.dispose();
            //}
            //size.x = width;
            size.x = editor.getBounds().width;
            return size;
        }
    }

    public EditComboBoxCellEditor(Composite parent, boolean addItems) {
        super(parent);
        fItems = new LabelValuePair[1];
        fItems[0] = new LabelValuePair("", "");
        fAddItems = addItems;
    }

    /**
     * Creates a new combo box cell editor with the given choices.
     */
    public EditComboBoxCellEditor(Composite parent, LabelValuePair[] items, boolean addItems) {
        super(parent);
        fItems = items;
        combo.setText("");
        fAddItems = addItems;
    }

    protected void fireCancelEditor() {
        newItem = null;
        super.fireCancelEditor();
    }

    protected void fireApplyEditorValue() {
        super.fireApplyEditorValue();
    }

    private int keyCount = 0;

    protected void keyReleaseOccured(KeyEvent keyEvent) {
        keyCount++;
        super.keyReleaseOccured(keyEvent);
    }

    protected LabelValuePair newItem = null;

    public void deactivate() {
        super.deactivate();
        if (focusControl != null && !focusControl.isDisposed()) {
            focusControl.setFocus();
        }
        keyCount = 0;
    }

    public void activate() {
        if (fAddItems && newItem != null) {
            boolean addNeeded = true;
            // now check if the new value already exists in the list
            if (fItems != null) {
                int listSize = fItems.length;
                for (int i = 0; i < listSize; i++) {
                    if (newItem.fLabel.equals(fItems[i].fLabel)) {
                        // no need to add
                        addNeeded = false;
                        break;
                    }
                }
            }

            if (addNeeded) {
                // now add the new item that we created earlier
                java.util.List tmpList = null;
                Vector vectTemp;
                if (fItems != null) {
                    tmpList = Arrays.asList(fItems);
                    vectTemp = new Vector(tmpList);
                }
                else {
                    vectTemp = new Vector();
                }
                vectTemp.addElement(newItem);
                LabelValuePair[] newItems = new LabelValuePair[vectTemp.size()];
                for (int i = 0; i < vectTemp.size(); i++) {
                    newItems[i] = (LabelValuePair) vectTemp.elementAt(i);
                }
                fItems = newItems;
            }
        }
        newItem = null;
        if (Display.getCurrent() != null) {
            focusControl = Display.getCurrent().getFocusControl();
        }
        super.activate();
    }

    Control focusControl = null;
    KeyAdapter keyAdapter = null;

    protected KeyAdapter getKeyAdapter() {
        if (keyAdapter == null) {
            return new KeyAdapter() {

                public void keyPressed(KeyEvent e) {
                    if (e.keyCode == SWT.ARROW_DOWN || e.keyCode == SWT.ARROW_UP) {
                        activate();
                    }
                }

                public void keyReleased(KeyEvent e) {
                    if ((getControl() == null) || getControl().isDisposed())
                        return;

                    if (e.keyCode == SWT.ESC) {
                        // Escape key
                        fireCancelEditor();
                        keyReleaseOccured(e);
                    }
                    else if (e.keyCode != SWT.ARROW_DOWN && e.keyCode != SWT.ARROW_UP) {
                        int index = combo.getSelectionIndex();
                        if (index >= 0) {
                            // handles Enter key to select case
                            String selection = combo.getItem(index);
                            for (int i = 0; i < fItems.length; i++) {
                                if (fItems[i].fLabel.equals(selection)) {
                                    newItem = createComboBoxItem(selection);
                                    doSetValue(fItems[i].fValue);
                                    break;
                                }
                            }
                        }
                        else {
                            // user has typed something into the combo
                            newItem = createComboBoxItem(appendQuotes(combo.getText()));
                            doSetValue(newItem.fValue);
                        }
                        keyReleaseOccured(e);
                    }
                }
            };
        }
        
        return keyAdapter;
    }

    /**
     * Creates the actual UI repesentation.
     */
    protected Control createControl(Composite parent) {

        fEditor = new Composite(parent, SWT.NONE);
        fEditor.setLayout(new ComboCellLayout());

        combo = new CCombo(fEditor, SWT.NONE);
        combo.setBackground(parent.getBackground());
        combo.setText("");

        combo.addKeyListener(getKeyAdapter());
        combo.addTraverseListener(new TraverseListener() {

            // ignore a traverse event if it is the escape key or the enter key
            // since a cell editor is active
            public void keyTraversed(TraverseEvent e) {
                if (e.detail == SWT.TRAVERSE_ESCAPE || e.detail == SWT.TRAVERSE_RETURN) {
                    e.doit = false;
                }
            }
        });

        combo.addSelectionListener(new SelectionAdapter() {

            public void widgetDefaultSelected(SelectionEvent event) {
                if (combo.getSelectionIndex() >= 0)
                    fireApplyEditorValue();
                else
                    commitValue();

                deactivate();
                combo.removeKeyListener(getKeyAdapter());

            }

            public void widgetSelected(SelectionEvent event) {
                // If an item has been selected, process 
                // otherwise skip.
                // This event doesn't close the edtior, since the
                // selection may be the result of the arrow buttons
                // being used to navigate through the selections,
                // instead of the selection being made by a mouse
                // User must do an overt action to close editor 
                // (e.g. press Enter, click another object, etc.) 
                if (combo.getSelectionIndex() >= 0) {
                    String selection = combo.getItem(combo.getSelectionIndex());
                    for (int i = 0; i < fItems.length; i++) {
                        if (fItems[i].fLabel.equals(selection)) {
                            doSetValue(fItems[i].fValue);

                            break;
                        }
                    }

                }
            }
        });

        combo.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                commitValue();
                forwardFocusLost();
            }

            public void focusGained(FocusEvent e) {
                refreshComboItems();
                fillCombo();
            }
        });

        setValueValid(true);

        return fEditor;
    }

    protected void commitValue() {
        if (keyCount > 0) {
            combo.setText(appendQuotes(combo.getText()));
            LabelValuePair comboItem = createComboBoxItem(combo.getText());
            doSetValue(comboItem.fValue);
            keyCount = 0;
        }
        fireApplyEditorValue();
    }

    protected void refreshComboItems() // subclasses override this method
    {
    }

    protected void fillCombo() {
        if (fItems != null) {
            String[] labels = new String[fItems.length];
            for (int i = 0; i < labels.length; i++) {
                LabelValuePair item = fItems[i];
                labels[i] = item.fLabel;
                if (fSelection == null && fValue != null && fValue.equals(item.fValue)) {
                    fSelection = item.fLabel;
                }
            }

            combo.setItems(labels);
            if (fSelection != null) {
                combo.setText(fSelection);
            }
        }
    }

    protected Object doGetValue() {
        return fValue;
    }

    protected void doSetFocus() {
        combo.setText("");
        combo.setFocus();
    }

    protected void doSetValue(Object value) {
        fValue = value;
    }

    /**
     * Forwards a focus-lost event from the combo focus listener to this class.
     */
    public void forwardFocusLost() {
        focusLost();
    }

    /**
     * Creates the new LabelValuePair
     *
     * Example body for creating simple expressions based on text input
     *
     *   ExpressionHelper eh = new ExpressionHelper();
     *   return new LabelValuePair(newValue, eh.createExpression(newValue));
     *
     * Or, for creating String objects
     *
     *   return new LabelValuePair(newValue, newValue);
     *
     */
    protected abstract LabelValuePair createComboBoxItem(String newValue);

    private ExpressionHelper eh = new ExpressionHelper(); //b370 nb 20040729

    /**
     * Returns the context for the values entered in this combo
     * @return Returns the quotesContext.
     */
    public String getQuotesContext() {
        //b370 nb 20040729 - new method
        return eh.getQuotesContext();
    }

    /**
     * Sets context for the values entered in this combo
     * @param quotesContext The quotesContext to set.
     */
    public void setQuotesContext(String quotesContext) {
        //b370 nb 20040729 - new method
        eh.setQuotesContext(quotesContext);
    }

    /**
     * Sets boolean NeedQuotes to indicate if value entered in this combo 
     * should be single quoted
     * @return Sets boolean NeedQuotes.
     */
    public void setNeedQuotes(boolean value) {
        //b370 nb 20040729 - new method 
        this.needQuotes = value;
    }

    /**
     * Returns boolean NeedQuotes to indicate if value entered in this combo 
     * should be single quoted
     * @return NeedQuotes.
     */
    public boolean getNeedQuotes() {
        //b370 nb 20040729 - new method
        return this.needQuotes;
    }

    /**
     * Sets the DataType of PairSource.
     * @return 
     */
    public void setPairDataType(int dtype) {
        //b370 nb 20040729 - new method
        this.pairDataType = dtype;
    }

    /**
     * Sets the DataType of PairSource.
     * @return 
     */
    public void setPairDataType(DataType dtype) {
        //b370 nb 20040729 - new method
        if (dtype instanceof PredefinedDataType) {
            int dataTypeInt = ((PredefinedDataType) dtype).getPrimitiveType().getValue();
            this.pairDataType = dataTypeInt;
        }
    }

    /**
     * Returns the DataType of PairSource.
     * @return datatype
     */
    public int getPairDataType() {
        //b370 nb 20040729 - new method
        return this.pairDataType;
    }

    /**
     * Appends Single quotes to the string passed
     * @param  String value which needs to be single quoted
     * @return String with the Quotes Appended
     */
    public String appendQuotes(String value) {
        //b370 nb 20040729 - new method
        if (needQuotes) {
            value = eh.appendQuotes(this.getPairDataType(), value);
        }
        return value;

    }

}
