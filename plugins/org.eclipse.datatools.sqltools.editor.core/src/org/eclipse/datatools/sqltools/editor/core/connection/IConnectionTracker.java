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
package org.eclipse.datatools.sqltools.editor.core.connection;

/**
 * This is a simple interface used for notify the listener that a connection is closed.
 * @author Yang Liu
 */
public interface IConnectionTracker
{
    /**
     * Notifies the listener that the connection being tracked is to be closed.
     */
    public void connectionAboutToBeClosed();

    /**
     * Notifies the listener that the connection being tracked is closed.
     */
    public void connectionClosed();
}
