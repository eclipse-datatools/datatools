/*******************************************************************************
 * Copyright (c) 2008, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConfigurationType;
import org.eclipse.datatools.connectivity.IConnectListener;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IConnectionProfileProvider;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.internal.ConnectionProfileProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;

/**
 * Impements IConnectionProfile because ConnectionProfile cannot be subclassed
 * to avoid depending on the plug-in registry. This class must work
 * outside of the Eclipse plug-in environment, such as for DeployTask.
 * <p>
 * The following classes do not work in a Java application, 
 * but require the Eclipse plug-in environment:
 * <ul>
 * <li>org.eclipse.datatools.connectivity.drivers.DriverManager
 * <li>DatabaseDefinitionRegistry
 * </ul>
 * 
 * @author Thomas Sharp, sharpt@us.ibm.com
 */
public class ConnectionProfileApp implements IConnectionProfile
{
   protected ConnectionInfo ciApp;
   protected String loadingPath;
   protected Properties baseProperties;
   /** The connected state. */
   protected int state = IConnectionProfile.DISCONNECTED_STATE;
   
   private ConnectionProfileApp() {}
   
   public ConnectionProfileApp(ConnectionInfo conInfo)
   {
      ciApp = conInfo;
   }

   public ConnectionInfo getConnectionInfo()
   {
      return ciApp;
   }

   public String getLoadingPath() {
      return loadingPath;
   }

   public void setLoadingPath(String path) {
      loadingPath = path;
   }
   
   public void addConnectListener(IConnectListener listener)
   {
      // Do nothing
   }

   public void addPropertySetListener(IPropertySetListener listener)
   {
      // Do nothing
   }

   public boolean arePropertiesComplete()
   {
      return false;
   }

   public boolean arePropertiesComplete(String type)
   {
      return false;
   }

   public boolean canWorkOffline()
   {
      return false;
   }

   public IStatus connect()
   {
      return null;
   }

   public void connect(IJobChangeListener listener)
   {
      // do nothing
   }

   public IStatus connectWithoutJob()
   {
      return null;
   }

   public IConnection createConnection(String factoryId, String uid, String pwd)
   {
	   ConnectionProfileProvider cp = new ConnectionProfileProvider("silent.provider");
	   return cp.getConnectionFactory("java.sql.Connection").createConnection(this, uid, pwd);
       
   }

   public IConnection createConnection(String factory)
   {
	   ConnectionProfileProvider cp = new ConnectionProfileProvider("silent.provider");
	   return cp.getConnectionFactory(factory).createConnection(this);
   }

   public IStatus disconnect()
   {
      return null;
   }

   public void disconnect(IJobChangeListener listener)
   {
      // do nothing
   }

   public Properties getBaseProperties()
   {
      return baseProperties;
   }

   public ICategory getCategory()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public IConfigurationType getConfigurationType()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * @return @return the connection state of this profile. One of: 
    * IConnectionProfile.CONNECTED_STATE 
    * or IConnectionProfile.DISCONNECTED_STATE.
    * This does not currently support IConnectionProfile.WORKING_OFFLINE_STATE.
    * By default, this returns the disconnected state.
    */
   public int getConnectionState()
   {
      return state;
   }
   
   /**
    * Sets the connection state. 
    */
   public void setConnectionState(int state)
   {
      this.state = state;
   }

   public String getDescription()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getInstanceID()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public IManagedConnection getManagedConnection(String type)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getName()
   {
      return ciApp.getName();
   }

   public IConnectionProfile getParentProfile()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Map getProfileExtensions()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public Properties getProperties(String type)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public IConnectionProfileProvider getProvider()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getProviderId()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getProviderName()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean isAutoConnect()
   {
      // TODO Auto-generated method stub
      return false;
   }

   public boolean isConnected()
   {
      // TODO Auto-generated method stub
      return false;
   }

   public void removeConnectListener(IConnectListener listener)
   {
      // do nothing
   }

   public void removePropertySetListener(IPropertySetListener listener)
   {
      // do nothing
   }

   public IStatus saveWorkOfflineData()
   {
      return null;
   }

   public void saveWorkOfflineData(IJobChangeListener listener)
   {
      // do nothing
   }

   public void setBaseProperties(Properties props)
   {
      baseProperties = props;
   }

   public void setConnected(boolean connected)
   {
      // do nothing
   }

   public void setProperties(String type, Properties props)
   {
      // do nothing
   }

   public boolean supportsWorkOfflineMode()
   {
      return false;
   }

   public IStatus workOffline()
   {
      return null;
   }

   public void workOffline(IJobChangeListener listener)
   {
      // do nothing
   }
   
}
