/*******************************************************************************
 * Copyright © 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.criteria;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

public class CriteriaModifier implements ICellModifier {    

    public boolean canModify(Object element, String property) {
        // check for instance of single predicate expression
        if (element instanceof CriteriaElement) {
            CriteriaElement criteriaElement = (CriteriaElement) element;
            String op = criteriaElement.getOperator();
            if (op != null && property.equals(CriteriaGridViewer.P_STATEMENT_VALUE)) {
                if (op.equals("IS NULL") || op.equals("IS NOT NULL")) {
                    return false;
                }
            }
            else if (op != null && property.equals(CriteriaGridViewer.P_STATEMENT_COLUMN)) {
                if (op.equals("EXISTS")) {
                    return false;
                }
            }
        }
        return true;
    }

    Object currentData, currentName, currentValue;

    public void modify(Object element, String name, Object value) {
        Object data = ((TableItem) element).getData();
        
        // appears that the table returns the table element as the value if there
        // is no change to the cell contents - don't need to modify anything in this case
        // In Eclipse 3.1, modify() seems to called twice - with actual update and then with 
        // TableElement as update value. Don't updated instance values if no update required,
        // since asynchronous task for actual update may not have completed yet and is using
        // same instance of Modifier. 
        if (data != value ){
        	currentData = data;
        	currentName = name;
        	currentValue = value;
        	
        	Display.getCurrent().asyncExec(new Runnable() {
        		
        		public void run() {
        			((CriteriaElement) currentData).setElementProperty(currentName, currentValue);
        		}
        	});
        }
        return;
    }

    public Object getValue(Object target, String property) {
        return target;
    }
}
