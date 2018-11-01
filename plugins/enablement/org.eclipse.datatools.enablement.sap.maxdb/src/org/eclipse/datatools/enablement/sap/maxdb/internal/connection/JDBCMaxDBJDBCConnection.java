/*******************************************************************************
 * Copyright (c) 2008 SAP AG
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Wolfgang Auer - initial implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sap.maxdb.internal.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.Version;
import org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection;
import org.eclipse.datatools.enablement.sap.maxdb.MaxDBEnablementPlugin;

public class JDBCMaxDBJDBCConnection extends JDBCConnection {

	
	private Version mMaxDBServerVersion = Version.NULL_VERSION;
	
	public JDBCMaxDBJDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
	}
	
	public Version getProviderVersion() {
		return mMaxDBServerVersion;
	}
	
	public String getProviderName() {
		return MaxDBEnablementPlugin.getResourceString("MaxDBName"); //$NON-NLS-1$
	}
	
	protected void initVersions() {
		
		String versionString = "";  //$NON-NLS-1$
		try {			
			super.initVersions();			
			DatabaseMetaData dbmd = ((Connection) getRawConnection()).getMetaData();			
			versionString = dbmd.getDatabaseProductVersion();
			parseVersion(versionString);			
		}catch(Exception ex){
			mMaxDBServerVersion = Version.valueOf(versionString);
		}
	}
	
	private void parseVersion(String versionStr){
		 /*parser assumes a syntax of versionStr like "7.4.2 Build 000-000-000-000 "*/
        int startpos=0, endpos=0, curpos=0;
        versionStr = versionStr.substring(10);
        while ( Character.isSpaceChar(versionStr.charAt(curpos)))
          curpos++;
        startpos = curpos;
        while ( versionStr.charAt(curpos) != '.')
          curpos++;
        endpos = curpos;
        int majorVersion = Integer.parseInt(versionStr.substring(startpos, endpos));
        startpos = ++curpos;
        while ( versionStr.charAt(curpos) != '.')
          curpos++;
        endpos = curpos;
        int minorVersion = Integer.parseInt(versionStr.substring(startpos, endpos));
        startpos = ++curpos;
        while (! Character.isSpaceChar(versionStr.charAt(curpos)))
          curpos++;
        endpos = curpos;
        int clVersion = Integer.parseInt(versionStr.substring(startpos, endpos));
        while ( Character.isSpaceChar(versionStr.charAt(curpos)))
          curpos++;
        /*String buildNumber = versionStr.substring(curpos);
        while (! Character.isSpaceChar(versionStr.charAt(curpos)))
          curpos++;
        while ( Character.isSpaceChar(versionStr.charAt(curpos)))
          curpos++;
        startpos = curpos;
        while ( versionStr.charAt(curpos) != '-')
          curpos++;
        endpos = curpos;
        String plVersion = versionStr.substring(startpos, endpos);*/
        
        mMaxDBServerVersion = new Version(majorVersion,minorVersion,clVersion,"");  //$NON-NLS-1$
	}
}
