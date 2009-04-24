/**
 * Created on 2006-11-2
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

/**
 * For the Readonly setting for EnhancedTableViewer
 * 
 * @author Hui Wan
 */
public interface IEnhanceTableDataReadOnly
{

    /**
     * Check wheteher the row data is readonly 
     * 
     * @param row The rowdata
     * @param index the column index
     * @return
     */
    public boolean isReadOnly(Object row, int index);
    
}
