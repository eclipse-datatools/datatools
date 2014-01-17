/**
 * <copyright>
 * </copyright>
 *
 * $Id: RemoteServer.java,v 1.8 2008/01/29 00:04:56 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Remote Server</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer#getLUWServer <em>LUW Server</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getRemoteServer()
 * @model
 * @generated
 */
public interface RemoteServer extends EObject {
	/**
	 * Returns the value of the '<em><b>LUW Server</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getRemoteServer <em>Remote Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LUW Server</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LUW Server</em>' reference.
	 * @see #setLUWServer(LUWServer)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getRemoteServer_LUWServer()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getRemoteServer
	 * @model opposite="remoteServer" required="true"
	 * @generated
	 */
	LUWServer getLUWServer();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer#getLUWServer <em>LUW Server</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LUW Server</em>' reference.
	 * @see #getLUWServer()
	 * @generated
	 */
	void setLUWServer(LUWServer value);

} // RemoteServer
