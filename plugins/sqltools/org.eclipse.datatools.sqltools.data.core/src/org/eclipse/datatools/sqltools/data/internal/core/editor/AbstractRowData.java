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
package org.eclipse.datatools.sqltools.data.internal.core.editor;

/**
 * This abstract implementation of IRowData keeps track of the old and new (unsaved) values for each row,
 * And also handle the life cycle of the row (ORIGINAL, INSERTED, UPDATED, DELETED).
 * Extendors should implement a save() method to commit the changes to the database.
 * @author groux
 */
public abstract class AbstractRowData implements IRowData
{
    // Possible state transitions ( OLD STATE - action - NEW STATE )
    // load - ORIGINAL
    // insert - INSERTED
    // ORIGINAL - update - UPDATED
    // ORIGINAL - delete - DELETED
    // UPDATED - delete - DELETED
    // INSERTED - delete
    // UPDATED - save - ORIGINAL
    // INSERTED - save - ORIGINAL
    // INSERTED - revert
    // UPDATED - revert - ORIGINAL
    // DELETED - revert - ORIGINAL
    
    protected int state;    
    public static final int STATE_ORIGINAL = 0;
    public static final int STATE_UPDATED = 1;
    public static final int STATE_DELETED = 2;
    public static final int STATE_INSERTED = 3;
    
    protected Object[] newData;
    protected Object[] oldData;
    
    public AbstractRowData(int state, Object[] data)
    {
        this.state = state;
        this.newData = data;
        
        if (state==STATE_ORIGINAL)
            oldData = (Object[])data.clone();
    }
    
    
    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
    /**
     * When the modifications have been saved to the database, this method is called on rows of type
     * STATE_UPDATED or STATE_INSERTED to reflect the state of the databse.
     */
    public void resetToOriginal()
    {
        state = STATE_ORIGINAL;
        oldData = (Object[])newData.clone();
    }
    
    public void revertToOriginal()
    {
        state = STATE_ORIGINAL;
        newData = (Object[])oldData.clone();
    }
    
    public Object getValue(int col) {
        return newData[col];
    }
   
    public void updateValue(int col, Object value)
    {
        if (state==STATE_ORIGINAL)
            state = STATE_UPDATED;
        newData[col] = value;
    }
}
