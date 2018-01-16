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

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Authorization Identifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.34.1 Authorization Identifiers
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getOwnedSchema <em>Owned Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedRoleAuthorization <em>Received Role Authorization</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedRoleAuthorization <em>Granted Role Authorization</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedPrivilege <em>Granted Privilege</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedPrivilege <em>Received Privilege</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getAuthorizationIdentifier()
 * @model abstract="true"
 * @generated
 */
public interface AuthorizationIdentifier extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Owned Schema</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.Schema}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Schema</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Schema</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getAuthorizationIdentifier_OwnedSchema()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getOwner
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.Schema" opposite="owner"
	 * @generated
	 */
	EList getOwnedSchema();

	/**
	 * Returns the value of the '<em><b>Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getAuthorizationIds <em>Authorization Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' reference.
	 * @see #setDatabase(Database)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getAuthorizationIdentifier_Database()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database#getAuthorizationIds
	 * @model opposite="authorizationIds" required="true"
	 * @generated
	 */
	Database getDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getDatabase <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' reference.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(Database value);

	/**
	 * Returns the value of the '<em><b>Received Role Authorization</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantee <em>Grantee</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Received Role Authorization</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Received Role Authorization</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getAuthorizationIdentifier_ReceivedRoleAuthorization()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantee
	 * @model type="org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization" opposite="grantee"
	 * @generated
	 */
	EList getReceivedRoleAuthorization();

	/**
	 * Returns the value of the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantor <em>Grantor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Granted Role Authorization</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Granted Role Authorization</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getAuthorizationIdentifier_GrantedRoleAuthorization()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantor
	 * @model type="org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization" opposite="grantor"
	 * @generated
	 */
	EList getGrantedRoleAuthorization();

	/**
	 * Returns the value of the '<em><b>Granted Privilege</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantor <em>Grantor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Granted Privilege</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Granted Privilege</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getAuthorizationIdentifier_GrantedPrivilege()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantor
	 * @model type="org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege" opposite="grantor"
	 * @generated
	 */
	EList getGrantedPrivilege();

	/**
	 * Returns the value of the '<em><b>Received Privilege</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantee <em>Grantee</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Received Privilege</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Received Privilege</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getAuthorizationIdentifier_ReceivedPrivilege()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantee
	 * @model type="org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege" opposite="grantee" containment="true"
	 * @generated
	 */
	EList getReceivedPrivilege();

} // AuthorizationIdentifier
