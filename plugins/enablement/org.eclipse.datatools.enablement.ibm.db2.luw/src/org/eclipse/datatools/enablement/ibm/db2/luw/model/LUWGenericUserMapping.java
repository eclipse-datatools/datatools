/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWGenericUserMapping.java,v 1.7 2007/01/15 18:40:05 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generic User Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping#getRemoteUser <em>Remote User</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping#getRemotePassword <em>Remote Password</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericUserMapping()
 * @model
 * @generated
 */
public interface LUWGenericUserMapping extends LUWUserMapping {
	/**
	 * Returns the value of the '<em><b>Remote User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote User</em>' attribute.
	 * @see #setRemoteUser(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericUserMapping_RemoteUser()
	 * @model
	 * @generated
	 */
	String getRemoteUser();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping#getRemoteUser <em>Remote User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote User</em>' attribute.
	 * @see #getRemoteUser()
	 * @generated
	 */
	void setRemoteUser(String value);

	/**
	 * Returns the value of the '<em><b>Remote Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Password</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Password</em>' attribute.
	 * @see #setRemotePassword(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWGenericUserMapping_RemotePassword()
	 * @model
	 * @generated
	 */
	String getRemotePassword();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping#getRemotePassword <em>Remote Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Password</em>' attribute.
	 * @see #getRemotePassword()
	 * @generated
	 */
	void setRemotePassword(String value);

} // LUWGenericUserMapping
