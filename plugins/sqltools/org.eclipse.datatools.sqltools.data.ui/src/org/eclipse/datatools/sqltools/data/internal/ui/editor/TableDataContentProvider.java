/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.ui.editor;

import java.util.Vector;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableData;
import org.eclipse.datatools.sqltools.data.internal.ui.DataUIPlugin;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class TableDataContentProvider implements IStructuredContentProvider {

    protected ITableData tableData;

    
    public void dispose() {

    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        this.tableData = (ITableData)newInput;
    }

    public Object[] getElements(Object inputElement) {
        if (tableData==null)
            return new String[] {};       
        
        try {
            Vector rows = tableData.getRows();
            int rc = rows.size();
        	if (!tableData.isReadonly())
        	    rc++;
            Object[] a = new Object[rc];
            tableData.getRows().toArray(a);   
            if (!tableData.isReadonly())
                a[rc-1] = new Object();  //insertion row
            return a;
        } catch (Exception ex) {
            DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, ex.getMessage(), ex);
            return new Object[] {};
        }
    }

}
