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

import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;


public class TableDataEditorInput implements IEditorInput {

    protected Table table;
    
    public TableDataEditorInput(Table table)
    {
        this.table = table;
    }

    public boolean exists() {
        return table!=null;
    }


    public ImageDescriptor getImageDescriptor() {
        return null;
    }


    public String getName() {
        return table.getName();
    }

    
    public IPersistableElement getPersistable() {
        return null;
    }

    public String getToolTipText() {        
        return table.getSchema().getName() + "." + table.getName(); //$NON-NLS-1$
    }

    public Object getAdapter(Class adapter) {
        return null;
    }

    public Table getTable() {
        return table;
    }
    
    public boolean equals(Object o)
    {
        if (o instanceof TableDataEditorInput)
            return this.table==((TableDataEditorInput)o).table;
        else
            return false;
    }
}
