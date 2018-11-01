/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby.connection;

import java.io.File;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;


/**
 * This service automatically shuts down a Derby database when its last connection gets closed.
 * This allows unlocking the database so it can be used from an external application, when using
 * the embedded server.
 */
public class DerbyShutdownService 
//TODO:  Restore support after new connection manager is implemented
//implements ConnectionManagerListener 
{
   
    public DerbyShutdownService() {
//   	 TODO:  Restore support after new connection manager is implemented
    	//        RDBCorePlugin.getDefault().getConnectionManager().addListener(this);
    }
    
    public void dispose() {
//    	 TODO:  Restore support after new connection manager is implemented
 //       RDBCorePlugin.getDefault().getConnectionManager().removeListener(this);
    }
    
    public void connectionInfoCreated(ConnectionInfo info) {
    }


    public void connectionInfoRemoved(String name) {
    }

    public void connectionInfoRenamed(String previousName, ConnectionInfo info) {
    }

    public void connected(ConnectionInfo info, Connection connection) {
    }

    public void disconnected(ConnectionInfo info, Connection connection) {
        if (!info.getURL().startsWith("jdbc:derby:")) // derby Embedded driver //$NON-NLS-1$
            return;
        try {
//       	 TODO:  Restore support after new connection manager is implemented
        	ConnectionInfo[] infos = new ConnectionInfo[0];
//	        ConnectionInfo[] infos = RDBCorePlugin.getDefault().getConnectionManager().getAllNamedConnectionInfo();
	        int n = 0;
	        for (int i=0; i<infos.length; ++i) {
	            if ( infos[i]!=info &&
	                 infos[i].getSharedConnection()!=null && // connected
	                 infos[i].getDriverClassName().equals(info.getDriverClassName()) &&  // same driver
	                 getDBLocation(infos[i]).equals(getDBLocation(info)) ) // same database         
	                n++;
	        }
	        
	        if (n==0)
	            shutdown(info);
        } catch (Exception ex) {
            int a=0;
        }
    }
    
    protected File getDBLocation(ConnectionInfo info) 
    {
        String url = info.getURL();
        url = url.substring("jdbc:derby:".length()); //$NON-NLS-1$
        int n = url.indexOf(';');
        if (n>=0)
            url = url.substring(0, n);
        return new File(url);
    }
    
    protected void shutdown(ConnectionInfo info)
    {
        String uniqueName = generateName("shutdownInfo", getConnectionNames()); //$NON-NLS-1$
        try {   
//     	 TODO:  Restore support after new connection manager is implemented
 //           ConnectionInfo shutdownInfo = RDBCorePlugin.getDefault().getConnectionManager().createConnectionInfo(info.getDatabaseDefinition(), uniqueName);
//            shutdownInfo.setURL(info.getURL()+";shutdown=true"); //$NON-NLS-1$
//            shutdownInfo.setDatabaseName(info.getDatabaseName());
//            shutdownInfo.setDriverClassName(info.getDriverClassName());
//            shutdownInfo.setLoadingPath(info.getLoadingPath());
//            shutdownInfo.setUserName(info.getUserName());
//            shutdownInfo.setPassword(info.getPassword());
//      	 TODO:  Restore support after new connection manager is implemented           
//            shutdownInfo.connect();
        } catch (Exception ex) {
            // a SQLException is allways thrown when the database shuts down
    	} finally {
//      	 TODO:  Restore support after new connection manager is implemented
 //           RDBCorePlugin.getDefault().getConnectionManager().removeConnectionInfo(uniqueName);
        }
    }
    
    protected static Collection getConnectionNames()
    {
//  	 TODO:  Restore support after new connection manager is implemented
    	ConnectionInfo[] infos = new ConnectionInfo[0];
 //       ConnectionInfo[] infos = RDBCorePlugin.getDefault().getConnectionManager().getAllNamedConnectionInfo();
        Vector v = new Vector();
        for (int i=0; i<infos.length; ++i)
            v.add(infos[i].getName());
        return v;
    }
    
	protected static String generateName(String baseName, Collection exclusionList) {
		String curName = baseName;
		int count = 0;
		while (isIn(curName, exclusionList)) {
			curName = baseName + String.valueOf(count);			
			count++;
		}
		return baseName;
	}
	
	protected static boolean isIn(String s, Collection col) {
	    Iterator it = col.iterator();
	    while (it.hasNext())
	        if (s.equals(it.next()))
	            return true;
	    return false;
	}
}
