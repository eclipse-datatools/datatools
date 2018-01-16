/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import org.eclipse.jface.viewers.ISelection;


/**
 * 
 * @author lihuang
 * 
 */
public class CellSelection implements ISelection
{

    protected Object _row = null;
    protected int    _col = -1;

    public CellSelection(Object row, int col)
    {
        _row = row;
        _col = col;
    }

    public boolean isEmpty()
    {
        if (_row != null && _col != -1)
        {
            return false;
        }
        return true;
    }

}
