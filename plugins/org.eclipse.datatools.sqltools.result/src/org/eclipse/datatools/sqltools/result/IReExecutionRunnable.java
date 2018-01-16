/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result;

public interface IReExecutionRunnable
{
    /**
     * Re-executes the given operation. This method will not be invoked in UI thread for stabilitiy consideration.
     * 
     * @param cmd operation instance
     */
    public void reExecute(OperationCommand cmd);
}
