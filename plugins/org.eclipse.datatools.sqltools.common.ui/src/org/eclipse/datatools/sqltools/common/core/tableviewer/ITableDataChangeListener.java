/**
 * Created on 2005-11-18
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.core.tableviewer;

/**
 * 
 * @author Idull
 * 
 */
public interface ITableDataChangeListener
{
    /**
     * Notify the listener that a new row is added.
     * 
     * @param row
     */
    public void rowAdded(IRowData row);

    /**
     * Notify the listener that a row is deleted.
     * 
     * @param row
     */
    public void rowDeleted(IRowData row);

    /**
     * Notify the listener that a row is updated.
     * 
     * @param row
     */
    public void rowDataUpdated(IRowData row, int col, Object oldVal, Object newVal);
}
