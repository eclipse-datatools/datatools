/**
 * Created on 2005-11-12
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.core.tableviewer;

/**
 * 
 * @author sfyu
 */
public class TableDataSaveStatus 
{

    public int _inserted = 0;
    public int _updated = 0;
    public int _deleted = 0;  
    public boolean _duplicateRow = false;
    public String _currentStmt  = "";

    public void reset()
    {
        _inserted = 0;
        _updated = 0;
        _deleted = 0;
        _duplicateRow = false;
        _currentStmt = "";
    }
}
