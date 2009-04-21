/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultSetObject;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;

public class ResultInstanceFactory
{
	public static ResultInstanceFactory INSTANCE = new ResultInstanceFactory();
	
	private int maxRowCount = Integer.MAX_VALUE;
	
	private int maxDisplayRowCount = Integer.MAX_VALUE;
	
	public void setMaxRowCount(int maxRowCount) {
		this.maxRowCount = maxRowCount;
	}
	
	public int getMaxRowCount()
	{
		return this.maxRowCount;
	}

	public void setMaxDisplayRowCount(int maxDisplayRowCount) {
		this.maxDisplayRowCount = maxDisplayRowCount;
	}
	
	public int getMaxDisplayRowCount()
	{
		return this.maxDisplayRowCount;
	}

	private ResultInstanceFactory(){};
	
    /**
     * Creates a new result instance given the <code>OperationCommand</code> instance
     * 
     * @param cmd the operation request, can not be null
     * @param terminateHandler handler used to terminate this item, can be null
     * @return IResultInstance instance
     */
    public IResultInstance createNewInstance(OperationCommand cmd, Runnable terminateHandler)
    {
        return createNewInstance(cmd, terminateHandler, null);
    }
    
    /**
     * Creates a new result instance given the <code>OperationCommand</code> instance
     * 
     * @param cmd the operation request, can not be null
     * @param terminateHandler handler used to terminate this item, can be null
     * @param parentInstance the parent result instance, can be null 
     * @return IResultInstance instance
     */
    public IResultInstance createNewInstance(OperationCommand cmd, Runnable terminateHandler, IResultInstance parentInstance)
    {
        // check if the OperationCommand instance is valid
        if (cmd == null || cmd.getDisplayString() == null)
        {
            return null;
        }

        //this instance may exist
        IResultManager resultManager = ResultsViewAPI.getInstance().getResultManager();
        IResultInstance instance = ResultsViewAPI.getInstance().getResultInstance(cmd);
        if (instance == null)
        {
        	instance = new ResultInstance(resultManager, cmd, terminateHandler, parentInstance);
       		resultManager.fireAdded(instance);
        }

        return instance;
    }
    
    /**
     * Creates a new result set object instance
     * @param resultset
     * @return
     * @throws SQLException
     */
    public IResultSetObject createResultSetObject(ResultSet resultset) throws SQLException
    {
        IResultSetObject r = new ResultSetObject(resultset, maxRowCount, maxDisplayRowCount);
        return r;
    }

  
}
