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
package org.eclipse.datatools.sqltools.sql.parser.ast;


/**
 * 
 * @author Hui Cao
 * 
 */
public interface IASTTrigger extends IASTDeployable
{
    public abstract org.eclipse.datatools.sqltools.sql.parser.ast.Node getTriggerEvents();

    public abstract void setTriggerEvents(org.eclipse.datatools.sqltools.sql.parser.ast.Node events);

    public boolean isDeleteType();

    public void setDeleteType(boolean deleteType);

    public boolean isInsertType();

    public void setInsertType(boolean insertType);

    public boolean isUpdateType();

    public void setUpdateType(boolean updateType);

    public String getTableName();

    /**
     * Sets the subject table
     * @param tableName could be full name
     */
    public void setTableName(String tableName);

}
