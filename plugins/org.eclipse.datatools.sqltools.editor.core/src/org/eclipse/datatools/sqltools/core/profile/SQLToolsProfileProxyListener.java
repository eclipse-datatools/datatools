/*******************************************************************************
 * Copyright (c) 2005 -- 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.sqltools.core.profile;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IManagedConnectionListener;
import org.eclipse.datatools.connectivity.IProfileListener1;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.editor.core.connection.SQLToolsConnectListenersManager;

/**
 * @author Dafan Yang
 */
public class SQLToolsProfileProxyListener implements IProfileListener1, IManagedConnectionListener
{
    private ArrayList          _profiles = new ArrayList();
    SQLToolsProfileListenersManager _dmpProfileManager;
    SQLToolsConnectListenersManager _dmpConnectManager;

    private static SQLToolsProfileProxyListener INSTANCE = null;
    
    private SQLToolsProfileProxyListener()
    {
    	_dmpProfileManager = SQLToolsProfileListenersManager.getInstance();
        _dmpConnectManager = SQLToolsConnectListenersManager.getInstance();
    }
    
    public static synchronized SQLToolsProfileProxyListener getInstance()
    {
    	if (INSTANCE == null)
    	{
    		INSTANCE = new SQLToolsProfileProxyListener();
    	}
    	return INSTANCE;
    }
    
    public void init(IConnectionProfile[] profiles)
    {
        for (int i = 0; i < profiles.length; i++)
        {
            _profiles.add(new ConnectProfile(profiles[i]));
            IManagedConnection mc = profiles[i].getManagedConnection(ConnectionInfo.class.getName());
            if (mc != null)
            {
            	mc.addConnectionListener(this);
            	if (mc.isConnected() && mc.getConnection() != null)
            	{
            		//to handle auto connect profiles
            		opened(new ConnectEvent(profiles[i], mc, this));
            	}
            }
        }
        
    }

    public void profileAdded(IConnectionProfile profile)
    {
        if (!ProfileUtil.isSupportedProfile(profile))
        {
            return;
        }
        _profiles.add(new ConnectProfile(profile));
        IManagedConnection mc = profile.getManagedConnection(ConnectionInfo.class.getName());
        if (mc != null)
        {
        	mc.addConnectionListener(this);
        }
        _dmpProfileManager.fireProfileAdded(profile);

    }

    public void profileDeleted(IConnectionProfile profile)
    {
        if (!ProfileUtil.isSupportedProfile(profile))
        {
            return;
        }
        //close controlconnection
        //for supporting multi db profile, multi controlConnections are returned by profileName.
        //Those controlConnections are closed when this profile is deleted.
        //Modified by Daniel.
        IControlConnection[] controlConnections = EditorCorePlugin.getControlConnectionManager().getControlConnections(profile.getName());

        if (controlConnections != null )
        {
        	for (int i = 0; i < controlConnections.length; i++)
        	{
        		controlConnections[i].disconnect(true);
        	}
        }

        //self clean up
        _profiles.remove(new ConnectProfile(profile));
        IManagedConnection mc = profile.getManagedConnection(ConnectionInfo.class.getName());
        if (mc != null)
        {
        	mc.removeConnectionListener(this);
        }
        _dmpProfileManager.fireProfileDeleted(profile);
    }

    public void profileChanged(IConnectionProfile profile)
    {
        //this method is replaced by profileChanged(profile, oldname, olddesc, oldautoconnect)
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.servers.core.IProfileListener1#profileChanged(com.sybase.stf.servers.core.IConnectionProfile, java.lang.String, java.lang.String, java.lang.Boolean)
     */
    public void profileChanged(IConnectionProfile profile, String oldName, String oldDesc, Boolean oldAutoConnect)
    {
        if (!ProfileUtil.isSupportedProfile(profile))
        {
            return;
        }

        ConnectProfile oldProfile = this.findOldProfile(profile);
        //profile not changed
        if (oldProfile == null)
        {
            return;
        }
        ConnectProfile dmpConnectProfile = new ConnectProfile(profile);
        boolean onlyNameChanged = oldProfile.isOnlyNameChanged(dmpConnectProfile);

        oldName = oldProfile.getName();
        //controlconnection

        IControlConnection[] controlCons = EditorCorePlugin.getControlConnectionManager().getControlConnections(oldName);
        if (controlCons != null )
        {
        	// for supporting multi db profile, multi controlConnections are
        	// returned by profileName.
			// Those controlConnections are closed when this profile is deleted.
			// Modified by Daniel.
			for (int n = 0; n < controlCons.length; n++) {
				if (onlyNameChanged) {
					controlCons[n].profileRenamed(profile.getName());
				} else {
					controlCons[n].disconnect(true);
				}
			}
        }
        _dmpProfileManager.fireProfileChanged(profile, oldName, oldDesc, oldAutoConnect, onlyNameChanged, oldProfile);
    }

    private ConnectProfile findOldProfile(IConnectionProfile profile)
    {
        ProfileManager pManager = ProfileManager.getInstance();
        IConnectionProfile[] profiles = pManager.getProfiles();

        Iterator pIter = _profiles.iterator();
        ConnectProfile oldProfile = null;

        while (pIter.hasNext())
        {
            ConnectProfile p = (ConnectProfile) pIter.next();
            boolean isFound = false;
            for (int i = 0; i < profiles.length; i++)
            {
                if (p.equals(new ConnectProfile(profiles[i])))
                {
                    isFound = true;
                    break;
                }
            }
            if (!isFound)
            {
                oldProfile = p;
                break;
            }
        }

        if (oldProfile == null)
        {
            // should not happen
            return null;
        }

        _profiles.remove(oldProfile);
        _profiles.add(new ConnectProfile(profile));
        return oldProfile;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.servers.core.IConnectListener#okToClose(com.sybase.stf.servers.core.ConnectEvent)
     */
    public boolean okToClose(ConnectEvent event)
    {
//        String profileName = event.getConnectionProfile().getName();
        boolean okToClose = _dmpConnectManager.fireOkToClose(event);
        return okToClose;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.servers.core.IConnectListener#openConnection(com.sybase.stf.servers.core.ConnectEvent)
     */
    public void opened(ConnectEvent event)
    {
        final IConnectionProfile profile = event.getConnectionProfile();
        String dbName = ProfileUtil.getProfileDatabaseName(profile.getName());
        DatabaseIdentifier databaseIdentifier = new DatabaseIdentifier(profile.getName(), dbName);

        // sync controlconnection
        try
        {
        	// TODO test
			if (databaseIdentifier.getDBname() != null
					&& !databaseIdentifier.getDBname().trim().equals("")) {
				EditorCorePlugin.getControlConnectionManager()
						.getOrCreateControlConnection(databaseIdentifier);
			}

            // fire all dmp connect listeners
            try
            {
                _dmpConnectManager.fireProfileConnected(event);
            }
            catch (Exception e)
            {
                // in fact, we do not throw out exception at this point
            }
        }
        catch (Exception ce)
        {
        	EditorCorePlugin.getDefault().log(ce);
        }
    }

    public void closed(ConnectEvent event)
    {
        // fire all dmp connect listeners
        try
        {
            _dmpConnectManager.fireCloseConnection(event);
        }
        catch (Exception e)
        {
            if (EditorCorePlugin.getDefault() == null)
            {
            	return;
            }
            // in fact, we do not throw out exception at this point
        	EditorCorePlugin.getDefault().log(e);
        }

        IConnectionProfile profile = event.getConnectionProfile();

        //to prevent NPE during workbench close
        if (EditorCorePlugin.getDefault() == null)
        {
        	return;
        }
        //dispose the registered resources
        EditorCorePlugin.getControlConnectionManager().fireDispose(profile.getName());

        IControlConnection[] cs = EditorCorePlugin.getControlConnectionManager().getControlConnections(profile.getName());
        if (cs != null && cs.length > 0)
        {
            for (int i = 0; i < cs.length; i++)
            {
                cs[i].disconnect(true);
            }
        }
    }

	public void aboutToClose(ConnectEvent event) {
        // fire all dmp connect listeners
        try
        {
            _dmpConnectManager.fireAboutToClose(event);
        }
        catch (Exception e)
        {
            // in fact, we do not throw out exception at this point
        }

	}

	public void modified(ConnectEvent event) {
		// TODO Auto-generated method stub
		
	}

}
