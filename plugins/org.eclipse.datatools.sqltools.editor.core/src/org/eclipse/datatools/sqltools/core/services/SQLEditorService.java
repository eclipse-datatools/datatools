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

package org.eclipse.datatools.sqltools.core.services;

import java.util.Collection;

import org.eclipse.datatools.sqltools.editor.IExtendedSaveSupport;
import org.eclipse.datatools.sqltools.launching.IExtendedLaunchSupport;

/**
 * A SQL Editor related service specific to a database definition. This interface allows clients to customize the
 * default behavior of the SQL Editor.
 * @author Hui Cao
 *
 */
public class SQLEditorService
{

    /**
     * Returns a <code>IExtendedLaunchSupport</code> object which is used to execute additional operations before/after launching
     * 
     * @return a <code>IExtendedLaunchSupport</code> object to customize the launching behavior.
     */
    public IExtendedLaunchSupport getExtendedLaunchSupport()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns a <code>IExtendedSaveSupport</code> object which is used to perform additional operations before/after saving a
     * procedural object
     * 
     * @return a <code>IExtendedSaveSupport</code> object to customize the saving behavior
     */
    public IExtendedSaveSupport getExtendedSaveSupport()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * The following actions can be excluded in SQL Editor: 
     * <code>ISQLEditorActionConstants.EXPLAIN_SQL_ACTION_ID</code>
     * <code>ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID</code>
     * <code>ISQLEditorActionConstants.EXECTUE_SELECTION_SQL_ACTION_ID</code>
     * <code>ISQLEditorActionConstants.GROUP_NEW_ROUTINE_ID</code>
     * <code>ISQLEditorActionConstants.GROUP_INSERT_QUERY_ID</code>
     * <code>ISQLEditorActionConstants.RUN_ACTION_ID</code>
     * <code>ISQLEditorActionConstants.DEBUG_ACTION_ID</code>
     * 
     */
    public Collection getExcludedActionIds()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns data server specific actions, which will be added to menu/context menu and tool bar. To appear in the toolbar,
     * the action must define action id.
     * 
     * @return collection of <code>org.eclipse.jface.action.Action</code> objects.
     */
    public Collection getAdditionalActions()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
