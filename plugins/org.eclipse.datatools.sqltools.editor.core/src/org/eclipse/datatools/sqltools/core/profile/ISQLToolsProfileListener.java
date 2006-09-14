/**
 * Created on 2006-5-17
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.core.profile;

import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * Defined for SQL Dev Tools, the implementor will be notified when profile events occur, all classes in SQL Dev Tools which need to be
 * notified when profile events occur should implement this interface (instead of IProfileListener, IProfileListener1)
 * and register as an observer of <code>SQLToolsProfileListenersManager</code>
 * 
 * @author Idull
 */
public interface ISQLToolsProfileListener
{
    /**
     * Will be notified when a profile deleted
     * 
     * @param profile the connection profile instance
     */
    public void profileDeleted(IConnectionProfile profile);

    /**
     * Will be notified when a new profile is added
     * 
     * @param profile the connection profile instance
     */
    public void profileAdded(IConnectionProfile profile);

    /**
     * Will be notified when a profile is changed
     * 
     * @param profile the profile instance (after changed)
     * @param oldName the old name of the profile (The name may be changed)
     * @param oldDesc the old description of the profile
     * @param oldAutoConnect the old autoconnect property of the profile
     * @param onlyNameChanged TODO
     * @param oldProfile TODO
     */
    public void profileChanged(IConnectionProfile profile, String oldName, String oldDesc, Boolean oldAutoConnect, boolean onlyNameChanged, ConnectProfile oldProfile);
}
