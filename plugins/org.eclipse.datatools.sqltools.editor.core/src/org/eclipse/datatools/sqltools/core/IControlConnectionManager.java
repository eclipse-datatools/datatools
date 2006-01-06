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

package org.eclipse.datatools.sqltools.core;

import java.sql.SQLException;

import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;

/**
 * This interface is for manage all control connections. You should get a refrence to this interface through:
 * <code>dmpplugin.getControlConnectionManager()</code>
 * <p>
 * It's not intended to be implemented by clients.
 * </p>
 * 
 * @author Yang Liu
 * @author Hui Cao
 */
public interface IControlConnectionManager
{
    /**
     * Gets an controlconnection given the profile. If not exist, may create one.
     * 
     * @param databaseIdentifier database identifier
     * @return IControlConnection object keyed by databaseIdentifier
     * @throws SQLException
     * @throws NoSuchProfileException
     */
    public IControlConnection getOrCreateControlConnection(DatabaseIdentifier databaseIdentifier) throws SQLException,
            NoSuchProfileException;

    /**
     * Checks whether there is a controlconnection exists for the specified database identifier. If yes, return
     * the controlconnection, otherwise return null
     * 
     * @param databaseIdentifier the database identifier
     * @return the corresponding control connection
     */
    public IControlConnection getControlConnection(DatabaseIdentifier databaseIdentifier);

    /**
     * Retrieves all controlconnections registered in this control connection manager
     * 
     * @return all registered control connections
     */
    public IControlConnection[] getControlConnections();


    /**
     * Checks whether there are control connections exist for the specified connection profile. If yes, return
     * the control connection array, otherwise return null
     * 
     * @param profileName the connection profile name
     * @return the corresponding control connections
     */
    public IControlConnection[] getControlConnections(String profileName);

    /**
     * Adds an IControlConnectionListener to receive the control connection update events
     * @param listener the event listener
     */
    public void addControlConnectionListener(IControlConnectionListener listener);

    /**
     * Removes an IControlConnectionListener from receiving the control connection update events
     * @param listener the event listener
     */
    public void removeControlConnectionListener(IControlConnectionListener listener);

    /**
     * Whether there's registered control connection
     * @return true if so; false otherwize
     */
    public boolean hasControlConnection();

    /**
     * Shuts down this control connection manager. It will dispose all control connections
     */
    public void shutdown();
}
