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

import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.FullSelectTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.OrderByTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.fullselect.ValueTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.insert.InsertTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.select.SelectTableElement;
import org.eclipse.datatools.sqltools.sqlbuilder.views.update.UpdateTreeElement;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.custom.TableTreeItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;


public class Modifier implements ICellModifier {    

    public boolean canModify(Object element, String property) {
        return true;
    }

    public Object getValue(Object target, String property) {
        return target;
    }

    protected Object currentData, currentProperty, currentValue;

    public void modify(java.lang.Object element, java.lang.String property, java.lang.Object value) {

        Object data = null;
        if (element instanceof TableItem) {
            data = ((TableItem) element).getData();
        }
        else if (element instanceof TableTreeItem) {
            data = ((TableTreeItem) element).getData();
        }
        
        // appears that the table returns the table element as the value if there
        // is no change to the cell contents - don't need to modify anything in this case
        // In Eclipse 3.1, modify() seems to called twice - with actual update and then with 
        // TableElement as update value. Don't updated instance values if no update required,
        // since asynchronous task for actual update may not have completed yet and is using
        // same instance of Modifier. 
        if (data != value) {  
        	currentData = data;
        	currentProperty = property;
        	currentValue = value;
            Display.getCurrent().asyncExec(new Runnable() {

                public void run() {

                    if (currentData instanceof SelectTableElement) {
                        ((SelectTableElement) currentData).modify(currentProperty, currentValue);
                    }
                    else if (currentData instanceof InsertTableElement) {
                        ((InsertTableElement) currentData).modify(currentProperty, currentValue);
                    }
                    else if (currentData instanceof UpdateTreeElement) {
                        ((UpdateTreeElement) currentData).modify(currentProperty, currentValue);
                    }
                    else if (currentData instanceof FullSelectTableElement) {
                        ((FullSelectTableElement) currentData).modify(currentProperty, currentValue);
                    }
                    else if (currentData instanceof ValueTableElement) {
                        ((ValueTableElement) currentData).modify(currentProperty, currentValue);
                    }
                    else if (currentData instanceof OrderByTableElement) {
                        ((OrderByTableElement) currentData).modify(currentProperty, currentValue);
                    }
                }
            });
        }
    }
}
