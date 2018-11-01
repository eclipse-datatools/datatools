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

/**
 * This interface is the callback interface used by the IControlConnection to send information
 * or request to the individual debugger (or other kind of connection observer).
 * 
 * @author Yang Liu
 */
public interface IConnectionObserver
{
    /**
     * This is called by the IControlConnection to notify the debuggee observer of something change for the specified
     * connection.
     * 
     * @param connid connection id @see org.eclipse.datatools.sqltools.core.services.ConnectionService#getConnectionId(org.eclipse.datatools.sqltools.core.DatabaseIdentifier, java.sql.Connection)
     * @param event this is defined by different database type. Currently, for ASE it will be ASEEvent; for ASA, will be
     *            an integer object containing the event id.
     */
    public void connectionEventOccured(String connid, Object event);

    /**
     * Notifies the observer that the observable is shutting down. So no more 
     * connectionEventOccured event will be fired after this point.
     */
    public void observableShutdown();

    /**
     * Requests the observer to stop observing the client connection.
     * This is only a oneway request, the observer may or may not really disconnect. But if
     * they do really disconnect, should call {@link org.eclipse.datatools.sqltools.core.IControlConnection#unregisterInternalConn(String)}
     * @param connid the database specific connection id @see org.eclipse.datatools.sqltools.core.services.ConnectionService#getConnectionId(org.eclipse.datatools.sqltools.core.DatabaseIdentifier, java.sql.Connection)
     */
    public void requestDisconnect(String connid);
}
