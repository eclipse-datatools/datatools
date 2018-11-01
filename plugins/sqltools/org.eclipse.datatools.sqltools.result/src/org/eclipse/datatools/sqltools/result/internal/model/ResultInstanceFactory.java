/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultConfiguration;
import org.eclipse.datatools.sqltools.result.ResultSetObject;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.datatools.sqltools.result.internal.core.IResultManager;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;

public class ResultInstanceFactory
{
	public static ResultInstanceFactory INSTANCE = new ResultInstanceFactory();
	
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
        IResultSetObject r = new ResultSetObject(resultset, ResultConfiguration.getInstance().getMaxRowCount(), 
                ResultConfiguration.getInstance().getMaxDisplayRowCount(), ResultConfiguration.getInstance().isShowLabel());
        return r;
    }
  
}
