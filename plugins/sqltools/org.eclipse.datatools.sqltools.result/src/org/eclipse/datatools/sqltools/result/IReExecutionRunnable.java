/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
