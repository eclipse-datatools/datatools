/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.accesscontrol;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Authorization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.34.3 Roles
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#isGrantable <em>Grantable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantee <em>Grantee</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantor <em>Grantor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getRoleAuthorization()
 * @model
 * @generated
 */
public interface RoleAuthorization extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Grantable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grantable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grantable</em>' attribute.
	 * @see #setGrantable(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getRoleAuthorization_Grantable()
	 * @model
	 * @generated
	 */
	boolean isGrantable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#isGrantable <em>Grantable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grantable</em>' attribute.
	 * @see #isGrantable()
	 * @generated
	 */
	void setGrantable(boolean value);

	/**
	 * Returns the value of the '<em><b>Role</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Role#getRoleAuthorization <em>Role Authorization</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' reference.
	 * @see #setRole(Role)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getRoleAuthorization_Role()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Role#getRoleAuthorization
	 * @model opposite="roleAuthorization" required="true"
	 * @generated
	 */
	Role getRole();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getRole <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' reference.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(Role value);

	/**
	 * Returns the value of the '<em><b>Grantee</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedRoleAuthorization <em>Received Role Authorization</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grantee</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grantee</em>' reference.
	 * @see #setGrantee(AuthorizationIdentifier)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getRoleAuthorization_Grantee()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedRoleAuthorization
	 * @model opposite="receivedRoleAuthorization" required="true"
	 * @generated
	 */
	AuthorizationIdentifier getGrantee();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantee <em>Grantee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grantee</em>' reference.
	 * @see #getGrantee()
	 * @generated
	 */
	void setGrantee(AuthorizationIdentifier value);

	/**
	 * Returns the value of the '<em><b>Grantor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedRoleAuthorization <em>Granted Role Authorization</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grantor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grantor</em>' reference.
	 * @see #setGrantor(AuthorizationIdentifier)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getRoleAuthorization_Grantor()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedRoleAuthorization
	 * @model opposite="grantedRoleAuthorization" required="true"
	 * @generated
	 */
	AuthorizationIdentifier getGrantor();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantor <em>Grantor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grantor</em>' reference.
	 * @see #getGrantor()
	 * @generated
	 */
	void setGrantor(AuthorizationIdentifier value);

} // RoleAuthorization
