/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWGenericServer.java,v 1.7 2007/01/15 18:40:05 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Server</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericNicknames <em>Generic Nicknames</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericWrapper <em>Generic Wrapper</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericServer()
 * @model
 * @generated
 */
public interface LUWGenericServer extends LUWServer {
	/**
	 * Returns the value of the '<em><b>Generic Nicknames</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname#getGenericServer <em>Generic Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generic Nicknames</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generic Nicknames</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericServer_GenericNicknames()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname#getGenericServer
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname" opposite="genericServer" transient="true" volatile="true"
	 * @generated
	 */
	EList getGenericNicknames();

	/**
	 * Returns the value of the '<em><b>Generic Wrapper</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper#getGenericServers <em>Generic Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generic Wrapper</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generic Wrapper</em>' reference.
	 * @see #setGenericWrapper(LUWGenericWrapper)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericServer_GenericWrapper()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper#getGenericServers
	 * @model opposite="genericServers" required="true" transient="true" volatile="true"
	 * @generated
	 */
	LUWGenericWrapper getGenericWrapper();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericWrapper <em>Generic Wrapper</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generic Wrapper</em>' reference.
	 * @see #getGenericWrapper()
	 * @generated
	 */
	void setGenericWrapper(LUWGenericWrapper value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList getNicknames();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setWrapper(LUWWrapper newGenericWrapper);

} // LUWGenericServer
