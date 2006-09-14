package org.eclipse.datatools.sqltools.debugger.core;

import org.eclipse.datatools.sqltools.core.ServerIdentifier;

/**
 * Manages all debug handlers
 * @author Idull
 */
public interface IDebugHandlerManager
{
    /**
     * Returns or creates the debug handler for the given profile.
     * @param profileName
     */
    public IDebugHandler getOrCreateDebugHandler(String profileName);

    /**
     * Returns the debug handler for the given profile, may return null if there is no debug handler for this profile
     * 
     * @param profileName
     * @return
     */
    public IDebugHandler getDebugHandler(String profileName);

    /**
     * Disposes all debug handlers in this manager
     */
    public void dispose();

    /**
     * Returns the debug handler for the given server
     * @param serverIdentifier
     * @return
     */
    public IDebugHandler[] getDebugHandler(ServerIdentifier serverIdentifier);

    /**
     * Returns all debug handlers
     * @return
     */
    public IDebugHandler[] getDebugHandlers();

    /**
     * Returns all server ids which are connected
     * @return
     */
    public ServerIdentifier[] getServerIdentifiers();
}
