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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.sqltools.data.internal.core.common.IColumnDataAccessor;
import org.eclipse.datatools.sqltools.data.internal.core.editor.IRowData;
import org.eclipse.datatools.sqltools.data.internal.ui.DataUIPlugin;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableDataLabelProvider implements ITableLabelProvider
{
    protected static final int MAX_LABEL_LENGTH = Integer.MAX_VALUE;
   
    public TableDataLabelProvider()
    {
    }
    
    public Image getColumnImage(Object element, int columnIndex) {        
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {
        
        if (!(element instanceof IRowData))
            return (columnIndex==0)?Messages.getString("TableDataLabelProvider.newRow"):""; //$NON-NLS-1$ //$NON-NLS-2$
    
        IRowData row = (IRowData)element;
    
        try {
            Object o = row.getValue(columnIndex);
            IColumnDataAccessor acc = row.getTable().getColumnDataAccessor(columnIndex);
            int type = row.getTable().getColumnType(columnIndex);
            String s = acc.getLabel(o, type);
            if (s==null)
            {            	
            	return "";
            	//return Messages.getString("TableDataLabelProvider.null"); //$NON-NLS-1$
            }
            else {
                boolean truncated = acc.isSnippet(o, type);
        	    if (s.length()>MAX_LABEL_LENGTH) {
        	        s = s.substring(0, MAX_LABEL_LENGTH);
        	        truncated = true;
        	    }
        	    if (truncated)
        	        s += "..."; //$NON-NLS-1$  
        	    return s;
            }
        } catch (Exception ex) {
            DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, ex.getMessage(), ex);
            return Messages.getString("TableDataLabelProvider.error"); //$NON-NLS-1$
        }
    }

    public void addListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub
        
    }

    public void dispose() {
    }

    public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub
        
    }

    


}
