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


package org.eclipse.datatools.sqltools.editor;

import java.sql.Connection;

import org.eclipse.ui.editors.text.TextEditor;

/**
 * This interface can be implemented by clients who are interested in customizing the save behavior of the SQL Editor.
 * 
 * @author Shifeng Yu
 */

public interface IExtendedSaveSupport
{

    /**
     * Performs all pretreatment logic in the preSave method
     * 
     * @param conn the connection object
     * @param editor
     */
    public void preSave(Connection conn, TextEditor editor);

    /**
     * Performs all post treatment logic in the preSave method
     * 
     * @param conn the connection object
     * @param editor
     */
    public void postSave(Connection conn, TextEditor editor);

}
