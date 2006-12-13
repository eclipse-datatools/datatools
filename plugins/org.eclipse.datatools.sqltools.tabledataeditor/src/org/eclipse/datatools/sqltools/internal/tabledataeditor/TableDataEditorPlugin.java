/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.tabledataeditor;

import org.eclipse.ui.plugin.AbstractUIPlugin;

public class TableDataEditorPlugin extends AbstractUIPlugin
{
    // The shared instance.
    private static TableDataEditorPlugin plugin;

    /**
     * The constructor.
     */
    public TableDataEditorPlugin()
    {
        super();
        plugin = this;
    }

    /**
     * Returns the shared instance.
     */
    public static TableDataEditorPlugin getDefault()
    {
        return plugin;
    }
}
