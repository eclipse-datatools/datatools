/**
 * Created on 2006-5-17
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.editor.core.connection;

import org.eclipse.datatools.connectivity.ConnectEvent;

/**
 * All classes in SQL Dev Tools which need to be notified when connect events occur should implement this interface instead of
 * <code>IConnectListener</code> to reduce the coupling between SQL Dev Tools and Connectivity layer, and meanwhile make the
 * boundary clear.
 * <p>
 * NOTE: we do not throw out <code>CoreException</code> in <code>openConnection</code> and
 * <code>closeConnection</code>, and implementors can do anything in these two methods
 * 
 * @author Idull
 */
public interface ISQLToolsConnectListener
{
    /**
     * Asks if the profile can be closed currently
     * 
     * @param event the connect event
     * @return <true> if the profile can be close, <false> otherwise
     */
    public boolean okToClose(ConnectEvent event);

    /**
     * The profile is about to close
     * 
     * @param event the connect event
     */
    public void aboutToClose(ConnectEvent event);

    /**
     * The profile is connected. There is no guarantee that all components (which depend on connectivity) are
     * successfully connected
     * 
     * @param event the connect event
     */
    public void profileConnected(ConnectEvent event);

    /**
     * The profile is to be disconnected
     * 
     * @param event the connect event
     */
    public void closeConnection(ConnectEvent event);
}
