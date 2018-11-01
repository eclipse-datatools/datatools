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
package org.eclipse.datatools.sqltools.debugger.core;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;


/**
 * This is an adapter interface. It is used for IControlConnection(s) that support auto attach.
 * 
 * If auto attach is enabled, new connections will be automatically attached for debug.
 * 
 * @author Yang Liu
 */
public interface IControlConnectionExtension
{
    /**
     * Gets whether currently auto attach is enabled.
     * @return
     */
    public boolean getAutoAttachEnabled();

    /**
     * Enables/disables auto attach support.
     * @param b
     */
    public void setAutoAttachEnabled(boolean b);

    /**
     * Creates a new connection using same profile as this.
     * If can't get connection id, may put 0 as the connection id.
     * 
     * @param autoattach if true and auto attach enabled for this, then will also auto
     * 				attach the new connection.
     * 					if false, then will skip the connection.
     * @param connId	return the new connection's connection id. If null, means don't care.
     * @return
     */
    public Connection	createConnection(boolean autoattach, String[] connId) 
        throws SQLException, CoreException, NoSuchProfileException;

}
