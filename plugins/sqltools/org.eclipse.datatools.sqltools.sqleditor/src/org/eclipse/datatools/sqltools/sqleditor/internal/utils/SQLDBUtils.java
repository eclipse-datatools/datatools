/*
* Copyright (c) 2005. IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License 2.0
* which accompanies this distribution, and is available at
* https://www.eclipse.org/legal/epl-2.0/
*
* Contributors:
*     IBM Corporation - initial API and implementation
*/

package org.eclipse.datatools.sqltools.sqleditor.internal.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;

/**
 * Provides database services for the editor.  This includes services for selecting
 * and re-establishing a database connection.
 * 
 * @author Hetty Dougherty
 * @author bgp
 */
public class SQLDBUtils {

    protected static int PROPOSAL_TYPE_INVALID = -1;
    protected static int PROPOSAL_TYPE_TABLES = 1;
    protected static int PROPOSAL_TYPE_COLUMNS = 2;

    /**
     * Creates a SQL Statement and execute the statement using JDBC executeQuery
     * method. This method uses a Statement object to execute the query. It does
     * not close the Statement exlicitly because the ResultSet object will be
     * closed if the Statement is closed.
     * 
     * @param conn the <code>Connection</code> object to use
     * @param sql the SQL statement to execute
     * @return The <code>ResultSet</code> resulting from running the query
     */
    public static ResultSet executeSQL( Connection conn, String sql )
            throws SQLException {
        ResultSet result = null;
        Statement statement = null;
        try {
            statement = conn.createStatement();
            result = statement.executeQuery( sql );
        }
        catch (SQLException ex) {
            if (statement != null)
                statement.close();
            throw ex;
        }
        return result;
    }

    /**
     * Determines if the DB connection described by the given <code>ISQLEditorConnectionInfo</code>
     * is actively connected 
     * 
     * @param connInfo the <code>ISQLEditorConnectionInfo</code> to check
     * @return true if the connection is active, otherwise false
     */
    public static boolean isConnected( ISQLEditorConnectionInfo connInfo ) {
        boolean connected = false;
        
        if (connInfo != null && connInfo.getDatabase() != null) {
            connected = true;
        }
        return connected;
    }
   
    /**
     * Determines if the given <code>ISQLEditorConnectionInfo</code> is references the
     * default user ID.  This is done by checking if both the user ID and password 
     * are empty and non-null.
     * 
     * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to check
     * @return true if the default user ID is being used, otherwise false
     */
    public static boolean isDefaultUser( ISQLEditorConnectionInfo connInfo ) {

        String username = null;
        String password = null;
        
        if (connInfo != null) {
            username = ProfileUtil.getUserName(connInfo.getConnectionProfile());
            password = ProfileUtil.getPassword(connInfo.getConnectionProfile());
        }

        return (username != null && username.length() == 0 && password != null && password.length() == 0);
    }

    /**
     * Determines if a user ID and password prompt is needed for the given
     * <code>ISQLEditorConnectionInfo</code> object.  This is done by checking if either 
     * the user ID or password is null or empty.
     * 
     * @param connInfo the <code>ISQLEditorConnectionInfo</code> to check
     * @param true when userid/password prompt is needed, otherwise false
     */
    public static boolean isPromptNeeded( ISQLEditorConnectionInfo connInfo ) {
        
        String username = null;
        String password = null;
        
        if (connInfo != null) {
            username = ProfileUtil.getUserName(connInfo.getConnectionProfile());
            password = ProfileUtil.getPassword(connInfo.getConnectionProfile());
        }
        
        return (((username == null || username.length() == 0 || password == null || password.length() == 0)) && !isDefaultUser( connInfo ));
    }

    public static String getDefaultSchemaName(ISQLEditorConnectionInfo connInfo) {
    	DatabaseIdentifier dbid = new DatabaseIdentifier(connInfo.getConnectionProfileName());
    	String defaultSchemaName = ProfileUtil.getProfileUserName(dbid, false);
    
    	return defaultSchemaName;
    }

//    /**
//     * Prompts for the user ID and password and updates the given <code>ISQLEditorConnectionInfo</code>
//     * object
//     * 
//     * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to update
//     * @param promptMessage a message to display at the top of the prompt
//     * @return true if the user clicks OK, otherwise false
//     */
//    public static boolean promptIDPW( ISQLEditorConnectionInfo connInfo, String promptMessage ) {
//        boolean ok = false;
//        if (connInfo != null) {
//            String username = ProfileUtil.getUserName(connInfo.getConnectionProfile());
//            if (username == null || username.length() == 0)
//                username = System.getProperty( "user.name" ); //$NON-NLS-1$
//            UserIdentification idDialog = new UserIdentification( username, promptMessage );
//            if (idDialog.open() == Window.OK) {
//                username = idDialog.getUserNameInformation();
//                String password = idDialog.getPasswordInformation();
//                connInfo.setUserName( username == null ? "" : username ); //$NON-NLS-1$
//                connInfo.setPassword( password == null ? "" : password ); //$NON-NLS-1$
//                ok = true;
//                try {
//                    connInfo.saveISQLEditorConnectionInfo();
//                }
//                catch (Exception e) {
//                    ok = false;
//                }
//            }
//        }
//        return ok;
//    }
//
//    /**
//     * Determines if we are able to connect, prompts the user if needed,
//     * reconstructs connnection information if needed, and reconnects, if possible.
//     * 
//     * @param connInfo the <code>ISQLEditorConnectionInfo</code> to use to reestablish the connection
//     * @return true if we are able to re-establish the connection, otherwise false
//     */
//    public static boolean reestablishConnection( ISQLEditorConnectionInfo connInfo ) {
//        boolean reestablished = false;
//        
//        if (connInfo != null) {
//            reestablished = true;
//            
//            if (isConnected( connInfo ) == false) {
//                /* Prompt for the userid and password if needed. */
//                if (isPromptNeeded( connInfo ) == true) {
//                    if (promptIDPW( connInfo, null ) == false) {
//                        /* The user cancelled from the userid/password prompt. */
//                        reestablished = false;;
//                    }
//                }
//            }
//
//            /* If things are OK so far, test the connection. */
//            if (reestablished == true) {
//                StringBuffer msg = new StringBuffer();
//                reestablished = testConnection( connInfo, msg );
//                
//                if (msg.length() > 0) {
//                    MessageDialog.openError( Display.getCurrent().getActiveShell(),
//                            SQLEditorResources.getString( "SQLEditor.connection.errorDialog.title" ), //$NON-NLS-1$
//                            SQLEditorResources.getString( "SQLeditor.connection.errorDialog.message", //$NON-NLS-1$
//                                    new Object[] { msg.toString() } ) );
//                }
//            }
//        }
//        
//        return reestablished;
//    }    
//
//    /**
//     * Tests the connection indicated by the given <code>ISQLEditorConnectionInfo</code>
//     * and returns whether or not the connection is valid.  Error messages, if any,
//     * are returned in the given <code>StringBuffer</code>.
//     * 
//     * @param connInfo the <code>ISQLEditorConnectionInfo</code> for the connection to test
//     * @param msgBuf a buffer for error messages if any are generated by the test
//     * @return true if the connection is OK
//     */
//    public static boolean testConnection( ISQLEditorConnectionInfo connInfo, StringBuffer msgBuf ) {
//        boolean connectedOK = false;
//        if (connInfo != null) {
//            connectedOK = true;
//            Connection testConn = connInfo.getSharedConnection();
//            if (testConn == null) {
//                connectedOK = false;
//                try {
//                    testConn = connInfo.connect();
//                    if (testConn != null) {
//                        connInfo.setSharedConnection( testConn );
//                        new DatabaseProviderHelper().setDatabase( testConn,
//                                connInfo, connInfo.getDatabaseName() );
//                        connInfo.saveISQLEditorConnectionInfo();
//                        connectedOK = true;
//                    }
//                }
//                catch (Exception e) {
//                    msgBuf.append( e.getMessage() );
//                }
//            }
//        }
//        
//        return connectedOK;
//    }
    
}