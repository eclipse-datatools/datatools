/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui;

/**
 * The resource change event
 * 
 * @author Idull
 */
public interface IResourceChangeEvent
{
    public static final int PRE_DELETE  = 0;
    public static final int POST_DELETE = 1;
    public static final int PRE_RENAME  = 2;
    public static final int POST_RENAME = 3;
    public static final int PRE_CHANGE  = 4;
    public static final int POST_CHANGE = 5;

    /**
     * Returns the type of the event
     * 
     * @see #PRE_DELETE
     * @see #POST_DELETE
     * @see #PRE_RENAME
     * @see #POST_RENAME
     * @see #PRE_CHANGE
     * @see #POST_CHANGE
     * @return
     */
    public int getType();

    /**
     * Returns the resouce object, which will be useful when the resource is changed.
     * 
     * @return
     */
    public Object getSource();
}
