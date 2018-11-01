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

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;

public class ObjectComboHelper extends ObjectHelper {

    private Combo combo;
    private CCombo ccombo;

    public ObjectComboHelper(Combo combo) {
        super();
        this.combo = combo;
    }

    public ObjectComboHelper(CCombo ccombo) {
        super();
        this.ccombo = ccombo;
    }

    public void removeAll() {
        if (combo != null) {
            combo.removeAll();
        }
        else if (ccombo != null) {
            ccombo.removeAll();
        }
    }

    public void addEntry(String entry) {
        if (combo != null) {
            combo.add(entry);
        }
        else if (ccombo != null) {
            ccombo.add(entry);
        }
    }

    public int getSelectionIndex() {
        if (combo != null) {
            return combo.getSelectionIndex();
        }
        else if (ccombo != null) {
            return ccombo.getSelectionIndex();
        }
        return -1;
    }

    public void selectIndex(int index) {
        if (combo != null) {
            combo.select(index);
        }
        else if (ccombo != null) {
            ccombo.select(index);
        }
    }
}
