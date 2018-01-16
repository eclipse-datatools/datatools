/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Server</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getServerType <em>Server Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getServerVersion <em>Server Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getUserMappings <em>User Mappings</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getWrapper <em>Wrapper</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getNicknames <em>Nicknames</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getLUWDatabase <em>LUW Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getRemoteServer <em>Remote Server</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWServer()
 * @model abstract="true"
 * @generated
 */
public interface LUWServer extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Server Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Type</em>' attribute.
	 * @see #setServerType(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWServer_ServerType()
	 * @model
	 * @generated
	 */
	String getServerType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getServerType <em>Server Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Type</em>' attribute.
	 * @see #getServerType()
	 * @generated
	 */
	void setServerType(String value);

	/**
	 * Returns the value of the '<em><b>Server Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Version</em>' attribute.
	 * @see #setServerVersion(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWServer_ServerVersion()
	 * @model
	 * @generated
	 */
	String getServerVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getServerVersion <em>Server Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Version</em>' attribute.
	 * @see #getServerVersion()
	 * @generated
	 */
	void setServerVersion(String value);

	/**
	 * Returns the value of the '<em><b>User Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Mappings</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWServer_UserMappings()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping#getServer
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping" opposite="server" containment="true"
	 * @generated
	 */
	EList getUserMappings();

	/**
	 * Returns the value of the '<em><b>Wrapper</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getServers <em>Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wrapper</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wrapper</em>' reference.
	 * @see #setWrapper(LUWWrapper)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWServer_Wrapper()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getServers
	 * @model opposite="servers" required="true"
	 * @generated
	 */
	LUWWrapper getWrapper();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getWrapper <em>Wrapper</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wrapper</em>' reference.
	 * @see #getWrapper()
	 * @generated
	 */
	void setWrapper(LUWWrapper value);

	/**
	 * Returns the value of the '<em><b>Nicknames</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nicknames</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nicknames</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWServer_Nicknames()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getServer
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname" opposite="server"
	 * @generated
	 */
	EList getNicknames();

	/**
	 * Returns the value of the '<em><b>LUW Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getServers <em>Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LUW Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LUW Database</em>' reference.
	 * @see #setLUWDatabase(LUWDatabase)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWServer_LUWDatabase()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getServers
	 * @model opposite="servers" required="true"
	 * @generated
	 */
	LUWDatabase getLUWDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getLUWDatabase <em>LUW Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LUW Database</em>' reference.
	 * @see #getLUWDatabase()
	 * @generated
	 */
	void setLUWDatabase(LUWDatabase value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Options</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWServer_Options()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption" containment="true"
	 * @generated
	 */
	EList getOptions();

	/**
	 * Returns the value of the '<em><b>Remote Server</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer#getLUWServer <em>LUW Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Server</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Server</em>' reference.
	 * @see #setRemoteServer(RemoteServer)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWServer_RemoteServer()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer#getLUWServer
	 * @model opposite="LUWServer" required="true"
	 * @generated
	 */
	RemoteServer getRemoteServer();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getRemoteServer <em>Remote Server</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Server</em>' reference.
	 * @see #getRemoteServer()
	 * @generated
	 */
	void setRemoteServer(RemoteServer value);

} // LUWServer
