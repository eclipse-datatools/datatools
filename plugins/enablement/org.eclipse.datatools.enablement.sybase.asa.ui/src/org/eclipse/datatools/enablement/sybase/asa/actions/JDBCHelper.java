/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IManagedConnection;
import org.eclipse.datatools.connectivity.drivers.jdbc.JDBCConnection;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.datatools.enablement.sybase.asa.IJDBCASAConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAProfileMessages;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * Collection of JDBC helper functions
 * @author brianf
 *
 */
public class JDBCHelper {
	
	private static String FIRST_CALL = "SELECT NEXT_DATABASE( NULL )"; //$NON-NLS-1$
	private static String FIRST_CALL2 = "SELECT NEXT_DATABASE( "; //$NON-NLS-1$
	private static String NEXT_CALL = "SELECT DB_NAME( "; //$NON-NLS-1$
	
	private static String JDBC_CONN_ID = "java.sql.Connection"; //$NON-NLS-1$
//	private static String CONNINFO_ID = "org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo";
	
	/**
	 * hidden constructor
	 */
	private JDBCHelper() {
		//hidden constructor
	}
	
	/**
	 * Get a list of database names from an ASA connection
	 * @param conn
	 * @return
	 */
	public static String[] getDBNamesFromASA ( Connection conn ) {
		String[] rtnArray = null;
		ArrayList list = new ArrayList();
		try {
			PreparedStatement pstmt = conn.prepareStatement( FIRST_CALL );
			pstmt.clearParameters();
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				PreparedStatement pstmt2 = conn.prepareStatement( NEXT_CALL + id + " )" ); //$NON-NLS-1$
				ResultSet rs2 = pstmt2.executeQuery();
				while (rs2.next()) {
					String name = rs2.getString(1);
					list.add(name);
				}
				boolean cont = true;
				int oldid = id;
				while (cont) {
					pstmt = conn.prepareStatement(FIRST_CALL2 + id + " )"); //$NON-NLS-1$
					rs = pstmt.executeQuery();
					while (rs.next()) {
						id = rs.getInt(1);
						if (id == oldid) {
							cont = false;
							break;
						}
						pstmt2 = conn.prepareStatement( NEXT_CALL + id + " )" ); //$NON-NLS-1$
						rs2 = pstmt2.executeQuery();
						while (rs2.next()) {
							String name = rs2.getString(1);
							list.add(name);
						}
					}
				}
			}
		} catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
		if (list.size() > 0) {
			rtnArray = (String[]) list.toArray(new String[ list.size() ]);
		}
		return rtnArray;
	}

	public static Connection getConnectionForTempProfile(Shell shell, IConnectionProfile cp) {
		IConnection con = cp.createConnection(JDBC_CONN_ID);
		if (con != null && con.getConnectException() == null) {
			Connection conn = null; 
			if (con instanceof JDBCConnection) {
				conn = (Connection) con.getRawConnection();
			}
			return conn;
		}
		else {
			MessageDialog.openError(shell, 
					JDBCASAProfileMessages.getString("MultiASAWizardPage.Error.title.CannotConnect"),  //$NON-NLS-1$
					JDBCASAProfileMessages.getString("MultiASAWizardPage.Error.msg.CannotConnect")); //$NON-NLS-1$
		}
		return null;
	}
	
	public static IConnectionProfile getTemporaryProfile(NewConnectionProfileWizard wiz) {
		ConnectionProfile tempProfile = new ConnectionProfile("tempJDBCForPing","empty",IJDBCASAConnectionProfileConstants.PROVIDER_ID); //$NON-NLS-1$ //$NON-NLS-2$
		Properties props = wiz.getProfileProperties();
        tempProfile.setBaseProperties(props);
        return tempProfile;
	}
	
	public static String getDefaultCatalogNameForProfile ( Connection connection) {
		String defaultCatalog = null;
		if (connection != null) {
			try {
				defaultCatalog = connection.getCatalog();
			} catch (SQLException e) {
				// empty
			}
		}
		return defaultCatalog;
	}

	public static Connection getConnectionForProfile( Shell shell, IConnectionProfile profile ) {
		if (profile != null) {
			if (!profile.isConnected()) {
				profile.connect();
			}
			IManagedConnection mcon = profile.getManagedConnection(JDBC_CONN_ID);
			IConnection con = mcon.getConnection();
//			IConnection con = ProfileConnectionManager.getProfileConnectionManagerInstance().getConnection(profile, CONNINFO_ID);//mProfile.createConnection(ConnectionProfileConstants.PING_FACTORY_ID); //$NON-NLS-1$
			if (con != null && con.getConnectException() == null) {
				Connection conn = null;
				if (con.getRawConnection() instanceof Connection) {
					conn = (Connection) con.getRawConnection();
				}
				return conn;
			}
			else {
				MessageDialog.openError(shell, 
						JDBCASAProfileMessages.getString("MultiASAWizardPage.Error.title.CannotConnect"),  //$NON-NLS-1$
						JDBCASAProfileMessages.getString("MultiASAWizardPage.Error.msg.CannotConnect")); //$NON-NLS-1$
			}
		}
		return null;
	}
}
