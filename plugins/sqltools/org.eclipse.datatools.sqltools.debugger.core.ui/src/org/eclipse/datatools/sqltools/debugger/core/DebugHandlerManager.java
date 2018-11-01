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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IResourceDisposeListener;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.ServerIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ConnectProfile;
import org.eclipse.datatools.sqltools.core.profile.ISQLToolsProfileListener;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.profile.SQLToolsProfileListenersManager;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLToolsConnectListener;
import org.eclipse.datatools.sqltools.editor.core.connection.SQLToolsConnectListenersManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.osgi.util.NLS;

/**
 * Used to manage all debug handlers: creation, destroy
 * 
 * @author Idull
 */
public class DebugHandlerManager implements IDebugHandlerManager, ISQLToolsProfileListener, ISQLToolsConnectListener
{
    /**
     * Holds one debug handler for each profile
     */
    Map _debugHandlers = new Hashtable();

    public DebugHandlerManager()
    {
        SQLToolsProfileListenersManager pManager = SQLToolsProfileListenersManager.getInstance();
        pManager.addProfileListener(this);
        SQLToolsConnectListenersManager cManager = SQLToolsConnectListenersManager.getInstance();
        cManager.addConnectListener(this);
    }

    public synchronized IDebugHandler getOrCreateDebugHandler(String profileName)
    {
        IDebugHandler debugHandler = (IDebugHandler) _debugHandlers.get(profileName);
        if (debugHandler == null)
        {
        	SQLDevToolsConfiguration conf = SQLToolsFacade.getConfigurationByProfileName(profileName);

            // the factory may be null for generic JDBC profile
            if (conf != null && conf instanceof SQLDebuggerConfiguration)
            {
            	SQLDebuggerConfiguration factory = (SQLDebuggerConfiguration)conf;
                debugHandler = factory.createDebugHandler(profileName);
                _debugHandlers.put(profileName, debugHandler);
                EditorCorePlugin.getControlConnectionManager().addResourceDisposeListener(profileName,
                    new DebugHandlerDisposeListener(debugHandler));
                debugHandler.init();
            }
        }
        return debugHandler;
    }

    public synchronized void dispose()
    {
        SQLToolsProfileListenersManager pManager = SQLToolsProfileListenersManager.getInstance();
        pManager.removeProfileListener(this);
        SQLToolsConnectListenersManager cManager = SQLToolsConnectListenersManager.getInstance();
        cManager.removeConnectListener(this);

        Iterator iter = _debugHandlers.values().iterator();
        while (iter.hasNext())
        {
            IDebugHandler handler = (IDebugHandler) iter.next();
            handler.dispose();
        }
        _debugHandlers.clear();
    }

    public boolean okToClose(ConnectEvent event)
    {
        IConnectionProfile connectionProfile = event.getConnectionProfile();        
        return !hasDebuggingProcObjects(connectionProfile.getName());        
    }

    public void aboutToClose(ConnectEvent event)
    {

    }

    public void profileConnected(ConnectEvent event)
    {
        IConnectionProfile p = event.getConnectionProfile();
        if (p != null)
        {
            //create DebugHandler so that connections will show in external client view 
            getOrCreateDebugHandler(p.getName());
        }
    }

    public void closeConnection(ConnectEvent event)
    {
        IDebugHandler handler = (IDebugHandler) _debugHandlers.get(event.getConnectionProfile().getName());
        if (handler != null)
        {
            handler.dispose();
            _debugHandlers.remove(event.getConnectionProfile().getName());
        }
    }

    public void profileChanged(IConnectionProfile profile, String oldName, String oldDesc, Boolean oldAutoConnect, boolean onlyNameChanged, ConnectProfile oldProfile)
    {
        IDebugHandler handler = (IDebugHandler) _debugHandlers.get(oldName);        
        if (handler != null)
        {
            _debugHandlers.remove(oldName);
            handler.setProfileName(profile.getName());
            _debugHandlers.put(profile.getName(), handler);
            EditorCorePlugin.getControlConnectionManager().removeResourceDisposeListener(oldName);
            EditorCorePlugin.getControlConnectionManager().addResourceDisposeListener(profile.getName(),
                new DebugHandlerDisposeListener(handler));    
            
            if (!oldName.equals(profile.getName()))
            {
                // Change the profile name hold in the client connection for external client
                ClientConInfo[] clientCons = handler.getClientConInfos();
                if (clientCons != null && clientCons.length > 0)
                {
                    for (int i = 0; i < clientCons.length; i++)
                    {
                        DatabaseIdentifier di = clientCons[i].getDatabaseIdentifier();
                        if (di.getProfileName().equals(oldName))
                        {
                            di.setProfileName(profile.getName());
                        }
                    }
                }
            }            
        }        
        if (!oldName.equals(profile.getName()))
        {
            //Changes the profile name hold in DatabaseIdentifier of ProcIdentifier
            SPDebugModelUtil.changeProfileName(oldName, profile.getName());
        }
    }

    public void profileAdded(IConnectionProfile profile)
    {
        if (!ProfileUtil.isSupportedProfile(profile))
        {
            return;
        }
    }

    public void profileDeleted(IConnectionProfile profile)
    {
        IDebugHandler handler = (IDebugHandler) _debugHandlers.get(profile.getName());
        if (handler != null)
        {
            handler.dispose();
            _debugHandlers.remove(profile.getName());
            EditorCorePlugin.getControlConnectionManager().removeResourceDisposeListener(profile.getName());

        }
    }

    public void profileChanged(IConnectionProfile profile)
    {
        // do nothing
    }

    class DebugHandlerDisposeListener implements IResourceDisposeListener
    {
        IDebugHandler _debugHandler;

        public DebugHandlerDisposeListener(IDebugHandler debugHandler)
        {
            _debugHandler = debugHandler;
        }
        public void dispose()
        {
            _debugHandler.dispose();
            Iterator iter = _debugHandlers.keySet().iterator();
            String shouldRemoveProfile = null;
            while(iter.hasNext())
            {
                String profile = (String)iter.next();
                if(_debugHandlers.get(profile) == _debugHandler)
                {
                    shouldRemoveProfile = profile;
                    break;
                }
            }

            if(shouldRemoveProfile != null)
            {
                _debugHandlers.remove(shouldRemoveProfile);
            }
        }
    }

    public IDebugHandler[] getDebugHandler(ServerIdentifier serverIdentifier)
    {
        ArrayList handlers = new ArrayList();
        Iterator iter = _debugHandlers.keySet().iterator();
        while(iter.hasNext())
        {
            String profile = (String)iter.next();
            ServerIdentifier serverId = ProfileUtil.getServerIdentifier(new DatabaseIdentifier(profile));
            if(serverId.equals(serverIdentifier))
            {
                handlers.add(_debugHandlers.get(profile));
            }
        }
        return (IDebugHandler[]) handlers.toArray(new IDebugHandler[handlers.size()]);
    }

    public IDebugHandler[] getDebugHandlers()
    {
        return (IDebugHandler[]) _debugHandlers.values().toArray(new IDebugHandler[_debugHandlers.values().size()]);
    }

    public IDebugHandler getDebugHandler(String profileName)
    {
        return (IDebugHandler) _debugHandlers.get(profileName);
    }

    public ServerIdentifier[] getServerIdentifiers()
    {
        ArrayList servers = new ArrayList();
        Iterator iter = _debugHandlers.keySet().iterator();
        while(iter.hasNext())
        {
            ServerIdentifier serverIdentifier = ProfileUtil.getServerIdentifier(new DatabaseIdentifier((String) iter
                .next()));
            if(serverIdentifier != null)
            {
                if(!servers.contains(serverIdentifier))
                {
                    servers.add(serverIdentifier);
                }
            }
        }
        return (ServerIdentifier[]) servers.toArray(new ServerIdentifier[servers.size()]);
    }
    
    /**
     * Checks whether there is any procedural object in debugging session or not with the given 
     * connection profile.  
     * @param connectionProfileName name of connection profile 
     * @return true if there is procedural object in debugging session
     */
    private boolean hasDebuggingProcObjects(String connectionProfileName)
    {
        final String tempName = connectionProfileName;
        String[] procNames = SPDebugModelUtil.hasProcInDebugging(connectionProfileName);
        if (procNames != null && procNames.length > 0)
        {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < procNames.length; i++)
            {                
                sb.append("\"").append(procNames[i]).append("\"");
                if (i != procNames.length - 1)
                {
                    sb.append(",");
                }
            }
            final String tempProcNames = sb.toString();
            DebuggerCoreUIPlugin.getActiveWorkbenchShell().getDisplay().syncExec(new Runnable()
            {
                public void run()
                {
                    IStatus status = new Status(IStatus.WARNING, DebuggerCorePlugin.PLUGIN_ID, 0, NLS.bind(
                            Messages.SQLToolsConnectListener_close_error, tempProcNames), null);                    
                    ErrorDialog.openError(DebuggerCoreUIPlugin.getActiveWorkbenchWindow().getShell(),
                            Messages.SQLToolsConnectListener_close_error_title, NLS.bind(
                                    Messages.SQLToolsConnectListener_close_error_message, tempName), status);
                }
            });
            return true;
        }
        return false;
    }
}
