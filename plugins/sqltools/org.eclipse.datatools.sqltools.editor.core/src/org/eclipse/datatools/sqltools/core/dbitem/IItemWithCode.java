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
package org.eclipse.datatools.sqltools.core.dbitem;

import java.sql.SQLException;

/**
 * Represents a database object in the form of source code.
 * @author Yang Liu
 */
public interface IItemWithCode
{
    /**
     * Gets the underlying code representing this object.
     * @return
     * @throws SQLException
     */
    public String getCode() throws SQLException;

    /**
     * Uses the specified code to recreate the undeying database object.
     * Normally when success, this item will also refresh itself.
     * @param code
     */
    public void save(String code) throws SQLException;

    /**
     * Drops the underlying database object
     * @param code
     */
    public void drop() throws SQLException;
    
    /**
     * Given the line number, finds out the valid line number that is 
     * greater than number that can set breakpoint.
     * 
     * If for some reason, this operation is not supported, the original number will be returned.
     * @param number
     * @return -1 is this line and following line are all invalid.
     */
    public int getValidBreakpointLocation(int number) throws SQLException;
}
