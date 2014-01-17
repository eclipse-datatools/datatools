/**
 * <copyright>
 * </copyright>
 *
 * $Id: RelationalRemoteServer.java,v 1.7 2007/10/12 23:05:35 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Remote Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer#getDatabase <em>Database</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getRelationalRemoteServer()
 * @model
 * @generated
 */
public interface RelationalRemoteServer extends SQLObject, RemoteServer {
	/**
	 * Returns the value of the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' reference.
	 * @see #setDatabase(Database)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getRelationalRemoteServer_Database()
	 * @model
	 * @generated
	 */
	Database getDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer#getDatabase <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' reference.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(Database value);

} // RemoteDatabase
