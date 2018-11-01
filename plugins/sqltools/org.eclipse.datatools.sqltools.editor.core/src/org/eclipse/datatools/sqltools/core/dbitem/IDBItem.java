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

import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;

/**
 * This interface is used for a database object item.
 * 
 * An database object item is externally identified by a <code>ProcIdentifer</code>. And always 
 * attached to an IControlConnection. It can be used to cache certain meta data.
 *  
 * @author Yang Liu
 */
public interface IDBItem
{
    /**
     * Gets the proc identifier.
     */
    public ProcIdentifier	getProcIdentifier();

    /**
     * Gets the container control connection.
     */
    public IControlConnection	getControlConnection();

    /**
     * Informs this IDBItem to refresh its cached meta data.
     *
     */
    public void refresh();

    /**
     * Disposes this item. Means the IControlConnection is no longer using it, do NOT mean
     * to delete the underlying database object.
     *
     */
    public void dispose();
}
