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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Privilege</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.34.2 Privileges
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#isGrantable <em>Grantable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getAction <em>Action</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#isWithHierarchy <em>With Hierarchy</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantor <em>Grantor</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantee <em>Grantee</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getActionObjects <em>Action Objects</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getObject <em>Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getPrivilege()
 * @model
 * @generated
 */
public interface Privilege extends SQLObject {
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
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getPrivilege_Grantable()
	 * @model
	 * @generated
	 */
	boolean isGrantable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#isGrantable <em>Grantable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grantable</em>' attribute.
	 * @see #isGrantable()
	 * @generated
	 */
	void setGrantable(boolean value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see #setAction(String)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getPrivilege_Action()
	 * @model
	 * @generated
	 */
	String getAction();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(String value);

	/**
	 * Returns the value of the '<em><b>With Hierarchy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>With Hierarchy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>With Hierarchy</em>' attribute.
	 * @see #setWithHierarchy(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getPrivilege_WithHierarchy()
	 * @model
	 * @generated
	 */
	boolean isWithHierarchy();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#isWithHierarchy <em>With Hierarchy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>With Hierarchy</em>' attribute.
	 * @see #isWithHierarchy()
	 * @generated
	 */
	void setWithHierarchy(boolean value);

	/**
	 * Returns the value of the '<em><b>Grantor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedPrivilege <em>Granted Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grantor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grantor</em>' reference.
	 * @see #setGrantor(AuthorizationIdentifier)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getPrivilege_Grantor()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedPrivilege
	 * @model opposite="grantedPrivilege" required="true"
	 * @generated
	 */
	AuthorizationIdentifier getGrantor();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantor <em>Grantor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grantor</em>' reference.
	 * @see #getGrantor()
	 * @generated
	 */
	void setGrantor(AuthorizationIdentifier value);

	/**
	 * Returns the value of the '<em><b>Grantee</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedPrivilege <em>Received Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grantee</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grantee</em>' container reference.
	 * @see #setGrantee(AuthorizationIdentifier)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getPrivilege_Grantee()
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedPrivilege
	 * @model opposite="receivedPrivilege"
	 * @generated
	 */
	AuthorizationIdentifier getGrantee();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantee <em>Grantee</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grantee</em>' container reference.
	 * @see #getGrantee()
	 * @generated
	 */
	void setGrantee(AuthorizationIdentifier value);

	/**
	 * Returns the value of the '<em><b>Action Objects</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.schema.SQLObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action Objects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action Objects</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getPrivilege_ActionObjects()
	 * @model type="org.eclipse.datatools.modelbase.sql.schema.SQLObject"
	 * @generated
	 */
	EList getActionObjects();

	/**
	 * Returns the value of the '<em><b>Object</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getPrivileges <em>Privileges</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object</em>' reference.
	 * @see #setObject(SQLObject)
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage#getPrivilege_Object()
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject#getPrivileges
	 * @model opposite="privileges" required="true"
	 * @generated
	 */
	SQLObject getObject();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getObject <em>Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object</em>' reference.
	 * @see #getObject()
	 * @generated
	 */
	void setObject(SQLObject value);

} // Privilege
