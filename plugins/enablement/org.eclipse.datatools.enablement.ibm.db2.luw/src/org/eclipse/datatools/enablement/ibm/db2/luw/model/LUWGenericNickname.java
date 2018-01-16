/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWGenericNickname.java,v 1.7 2007/01/15 18:40:05 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic Nickname</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname#getGenericServer <em>Generic Server</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericNickname()
 * @model
 * @generated
 */
public interface LUWGenericNickname extends LUWNickname {
	/**
	 * Returns the value of the '<em><b>Generic Server</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericNicknames <em>Generic Nicknames</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generic Server</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generic Server</em>' reference.
	 * @see #setGenericServer(LUWGenericServer)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericNickname_GenericServer()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericNicknames
	 * @model opposite="genericNicknames" required="true" transient="true" volatile="true"
	 * @generated
	 */
	LUWGenericServer getGenericServer();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname#getGenericServer <em>Generic Server</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generic Server</em>' reference.
	 * @see #getGenericServer()
	 * @generated
	 */
	void setGenericServer(LUWGenericServer value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setServer(LUWServer newGenericServer);

} // LUWGenericNickname
