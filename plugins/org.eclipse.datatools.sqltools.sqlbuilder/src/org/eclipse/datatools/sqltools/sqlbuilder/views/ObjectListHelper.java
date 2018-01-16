/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.views;

import org.eclipse.swt.widgets.List;

public class ObjectListHelper extends ObjectHelper {

    private List list;

    public ObjectListHelper(List list) {
        super();
        this.list = list;
    }

    public void removeAll() {
        list.removeAll();
    }

    public void addEntry(String entry) {
        list.add(entry);
    }

    public boolean isMultipleSelection() {
        if (getSelectionCount() > 1) {
            return true;
        }
        
        return false;
    }

    public int getSelectionCount() {
        return list.getSelectionCount();
    }

    public int getSelectionIndex() {
        return list.getSelectionIndex();
    }

    public void deselectAll() {
        list.deselectAll();
    }

    public String[] getSelection() {
        return list.getSelection();
    }

    public void selectIndex(int index) {
        list.select(index);
    }
}// ObjectListHelper
