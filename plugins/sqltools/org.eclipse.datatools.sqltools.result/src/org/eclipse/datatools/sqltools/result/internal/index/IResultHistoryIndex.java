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
package org.eclipse.datatools.sqltools.result.internal.index;

import org.eclipse.datatools.sqltools.result.model.IResultInstance;

/**
 * An index used to search the result history
 * 
 * @author Dafan Yang
 */
public interface IResultHistoryIndex
{
    /**
     * Adds a new instance for indexing
     * 
     * @param instance a result instance
     */
    public void addResult(IResultInstance instance);
    
    /**
     * Adds a list of result instances for indexing
     * 
     * @param instances a list of result instances
     */
    public void addResults(IResultInstance[] instances);
    
    /**
     * Removes the specified result instance
     * 
     * @param instance a result instance
     */
    public void removeResult(IResultInstance instance);
    
    /**
     * Removes a list of result instances
     * 
     * @param instances a list of result instances
     */
    public void removeResults(IResultInstance[] instances);
    
    /**
     * Refreshes the given result instance
     * @param instance a result instance
     */
    public void refreshResult(IResultInstance instance);
    
    /**
     * Searches the index by the given expression and returns the searching results
     * 
     * @param expression the search expression
     * @return the searching results
     */
    public IResultInstance[] search(String expression);
}
