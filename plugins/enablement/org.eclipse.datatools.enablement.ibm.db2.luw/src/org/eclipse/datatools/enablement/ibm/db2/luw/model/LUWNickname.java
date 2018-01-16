/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nickname</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getRemoteDataSet <em>Remote Data Set</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getServer <em>Server</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNickname()
 * @model abstract="true"
 * @generated
 */
public interface LUWNickname extends LUWTable{
	/**
	 * Returns the value of the '<em><b>Remote Data Set</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet#getNickname <em>Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Data Set</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Data Set</em>' reference.
	 * @see #setRemoteDataSet(RemoteDataSet)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNickname_RemoteDataSet()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet#getNickname
	 * @model opposite="nickname" required="true"
	 * @generated
	 */
	RemoteDataSet getRemoteDataSet();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getRemoteDataSet <em>Remote Data Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Data Set</em>' reference.
	 * @see #getRemoteDataSet()
	 * @generated
	 */
	void setRemoteDataSet(RemoteDataSet value);

	/**
	 * Returns the value of the '<em><b>Server</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getNicknames <em>Nicknames</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server</em>' reference.
	 * @see #setServer(LUWServer)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWNickname_Server()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getNicknames
	 * @model opposite="nicknames" required="true"
	 * @generated
	 */
	LUWServer getServer();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getServer <em>Server</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server</em>' reference.
	 * @see #getServer()
	 * @generated
	 */
	void setServer(LUWServer value);

} // LUWNickname
