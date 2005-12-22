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
package org.eclipse.datatools.modelbase.sql.accesscontrol.impl;

import org.eclipse.datatools.modelbase.sql.accesscontrol.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQLAccessControlFactoryImpl extends EFactoryImpl implements SQLAccessControlFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLAccessControlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SQLAccessControlPackage.PRIVILEGE: return createPrivilege();
			case SQLAccessControlPackage.GROUP: return createGroup();
			case SQLAccessControlPackage.USER: return createUser();
			case SQLAccessControlPackage.ROLE: return createRole();
			case SQLAccessControlPackage.ROLE_AUTHORIZATION: return createRoleAuthorization();
			case SQLAccessControlPackage.TABLE_PRIVILEGE: return createTablePrivilege();
			case SQLAccessControlPackage.DOUBLE_OBJECT_PRIVILEGE: return createDoubleObjectPrivilege();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Privilege createPrivilege() {
		PrivilegeImpl privilege = new PrivilegeImpl();
		return privilege;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Group createGroup() {
		GroupImpl group = new GroupImpl();
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User createUser() {
		UserImpl user = new UserImpl();
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role createRole() {
		RoleImpl role = new RoleImpl();
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleAuthorization createRoleAuthorization() {
		RoleAuthorizationImpl roleAuthorization = new RoleAuthorizationImpl();
		return roleAuthorization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TablePrivilege createTablePrivilege() {
		TablePrivilegeImpl tablePrivilege = new TablePrivilegeImpl();
		return tablePrivilege;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleObjectPrivilege createDoubleObjectPrivilege() {
		DoubleObjectPrivilegeImpl doubleObjectPrivilege = new DoubleObjectPrivilegeImpl();
		return doubleObjectPrivilege;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLAccessControlPackage getSQLAccessControlPackage() {
		return (SQLAccessControlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static SQLAccessControlPackage getPackage() {
		return SQLAccessControlPackage.eINSTANCE;
	}

} //SQLAccessControlFactoryImpl
