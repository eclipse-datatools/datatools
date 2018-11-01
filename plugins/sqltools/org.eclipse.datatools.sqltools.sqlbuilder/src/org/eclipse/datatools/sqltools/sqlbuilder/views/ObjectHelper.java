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

import java.util.ArrayList;

import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;


abstract public class ObjectHelper {

    private ArrayList items = new ArrayList();

    public ObjectHelper() {
    }

    public void clear() {
        items.clear();
        removeAll();
    }

    public void addItems(LabelValuePair[] newItems) {
        for (int i = 0; i < newItems.length; i++) {
            addItem(newItems[i]);
        }
    }

    public void setItems(LabelValuePair[] newItems) {
        items.clear();
        //    this.items = items;
        removeAll();
        addItems(newItems);
    }

    public void addItem(LabelValuePair item) {
        items.add(item);
        addEntry(item.fLabel);
    }

    abstract public void removeAll();

    abstract public void addEntry(String entry);

    abstract public int getSelectionIndex();

    abstract public void selectIndex(int index);

    public LabelValuePair getLabelValuePair(int index) {
        return (LabelValuePair) items.get(index);
    }

    public LabelValuePair getSelectedLabelValuePair() {
        int index = getSelectionIndex();
        if (index >= 0) {
            return getLabelValuePair(index);//items[index];
        }
        return null;
    }

    public Object getSelectedObject() {
        LabelValuePair item = getSelectedLabelValuePair();
        if (item != null) {
            return getSelectedLabelValuePair().fValue;
        }
        return null;
    }

    public Object getObjectAt(int i) {
        if (i >= 0 && i < items.size()) {
            return getLabelValuePair(i).fValue;
        }
        return null;
    }

    public int indexOf(Object obj) {
        return indexOfValue(obj);
    }

    public int indexOfValue(Object obj) {
        for (int i = 0; i < items.size(); i++) {
            if ((getLabelValuePair(i).fValue == null && obj == null) || 
                    (getLabelValuePair(i).fValue != null && getLabelValuePair(i).fValue.equals(obj))) {
                return i;
            }
        }
        return -1;
    }

    public int indexOf(String label) {
        for (int i = 0; i < items.size(); i++) {
            if (getLabelValuePair(i).fLabel.equals(label)) {
                return i;
            }
        }
        return -1;
    }

    // use this one if you potentially have 2 objects with the same
    // value but under different labels (eg Integer)
    public void select(String label, Object value) {
        for (int i = 0; i < items.size(); i++) {
            if ((getLabelValuePair(i).fValue == null && value == null) || 
                    (getLabelValuePair(i).fValue != null && getLabelValuePair(i).fValue.equals(value))) {
                if (label == null) {
                    selectIndex(i);
                    return;
                }
                else if (label.equals(getLabelValuePair(i).fLabel)) {
                    selectIndex(i);
                    return;
                }
            }
        }
    }

    public void select(Object object) {
        select(null, object);
    }
}
