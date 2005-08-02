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

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlFactory
 * @generated
 */
public interface SQLAccessControlPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "accesscontrol"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/accesscontrol.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SQLAccessControl"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLAccessControlPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl <em>Authorization Identifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getAuthorizationIdentifier()
	 * @generated
	 */
	int AUTHORIZATION_IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the the '<em>Authorization Identifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl <em>Privilege</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getPrivilege()
	 * @generated
	 */
	int PRIVILEGE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Grantable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__GRANTABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__ACTION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Grantor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__GRANTOR = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__OBJECT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the the '<em>Privilege</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.GroupImpl <em>Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.GroupImpl
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getGroup()
	 * @generated
	 */
	int GROUP = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__EANNOTATIONS = AUTHORIZATION_IDENTIFIER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__NAME = AUTHORIZATION_IDENTIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__DEPENDENCIES = AUTHORIZATION_IDENTIFIER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__DESCRIPTION = AUTHORIZATION_IDENTIFIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__LABEL = AUTHORIZATION_IDENTIFIER__LABEL;

	/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__OWNED_SCHEMA = AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA;

	/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__RECEIVED_ROLE_AUTHORIZATION = AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION;

	/**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__GRANTED_ROLE_AUTHORIZATION = AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION;

	/**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__GRANTED_PRIVILEGE = AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE;

	/**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__RECEIVED_PRIVILEGE = AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE;

	/**
	 * The feature id for the '<em><b>User</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__USER = AUTHORIZATION_IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_FEATURE_COUNT = AUTHORIZATION_IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.UserImpl
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getUser()
	 * @generated
	 */
	int USER = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__EANNOTATIONS = AUTHORIZATION_IDENTIFIER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__NAME = AUTHORIZATION_IDENTIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DEPENDENCIES = AUTHORIZATION_IDENTIFIER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DESCRIPTION = AUTHORIZATION_IDENTIFIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__LABEL = AUTHORIZATION_IDENTIFIER__LABEL;

	/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__OWNED_SCHEMA = AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA;

	/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__RECEIVED_ROLE_AUTHORIZATION = AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION;

	/**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__GRANTED_ROLE_AUTHORIZATION = AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION;

	/**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__GRANTED_PRIVILEGE = AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE;

	/**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__RECEIVED_PRIVILEGE = AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE;

	/**
	 * The number of structural features of the the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = AUTHORIZATION_IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleImpl
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getRole()
	 * @generated
	 */
	int ROLE = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__EANNOTATIONS = AUTHORIZATION_IDENTIFIER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NAME = AUTHORIZATION_IDENTIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DEPENDENCIES = AUTHORIZATION_IDENTIFIER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DESCRIPTION = AUTHORIZATION_IDENTIFIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__LABEL = AUTHORIZATION_IDENTIFIER__LABEL;

	/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__OWNED_SCHEMA = AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA;

	/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__RECEIVED_ROLE_AUTHORIZATION = AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION;

	/**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__GRANTED_ROLE_AUTHORIZATION = AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION;

	/**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__GRANTED_PRIVILEGE = AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE;

	/**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__RECEIVED_PRIVILEGE = AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE;

	/**
	 * The feature id for the '<em><b>Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__ROLE_AUTHORIZATION = AUTHORIZATION_IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_FEATURE_COUNT = AUTHORIZATION_IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleAuthorizationImpl <em>Role Authorization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleAuthorizationImpl
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getRoleAuthorization()
	 * @generated
	 */
	int ROLE_AUTHORIZATION = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Grantable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__GRANTABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__ROLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Grantee</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__GRANTEE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Grantor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__GRANTOR = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the the '<em>Role Authorization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.TablePrivilegeImpl <em>Table Privilege</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.TablePrivilegeImpl
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getTablePrivilege()
	 * @generated
	 */
	int TABLE_PRIVILEGE = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__EANNOTATIONS = PRIVILEGE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__NAME = PRIVILEGE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__DEPENDENCIES = PRIVILEGE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__DESCRIPTION = PRIVILEGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__LABEL = PRIVILEGE__LABEL;

	/**
	 * The feature id for the '<em><b>Grantable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__GRANTABLE = PRIVILEGE__GRANTABLE;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__ACTION = PRIVILEGE__ACTION;

	/**
	 * The feature id for the '<em><b>Grantor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__GRANTOR = PRIVILEGE__GRANTOR;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__OBJECT = PRIVILEGE__OBJECT;

	/**
	 * The feature id for the '<em><b>With Hierarchy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE__WITH_HIERARCHY = PRIVILEGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Table Privilege</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_PRIVILEGE_FEATURE_COUNT = PRIVILEGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.DoubleObjectPrivilegeImpl <em>Double Object Privilege</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.DoubleObjectPrivilegeImpl
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getDoubleObjectPrivilege()
	 * @generated
	 */
	int DOUBLE_OBJECT_PRIVILEGE = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__EANNOTATIONS = PRIVILEGE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__NAME = PRIVILEGE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__DEPENDENCIES = PRIVILEGE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__DESCRIPTION = PRIVILEGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__LABEL = PRIVILEGE__LABEL;

	/**
	 * The feature id for the '<em><b>Grantable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__GRANTABLE = PRIVILEGE__GRANTABLE;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__ACTION = PRIVILEGE__ACTION;

	/**
	 * The feature id for the '<em><b>Grantor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__GRANTOR = PRIVILEGE__GRANTOR;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__OBJECT = PRIVILEGE__OBJECT;

	/**
	 * The feature id for the '<em><b>Object2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE__OBJECT2 = PRIVILEGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Double Object Privilege</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OBJECT_PRIVILEGE_FEATURE_COUNT = PRIVILEGE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier <em>Authorization Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Authorization Identifier</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier
	 * @generated
	 */
	EClass getAuthorizationIdentifier();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getOwnedSchema <em>Owned Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Owned Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getOwnedSchema()
	 * @see #getAuthorizationIdentifier()
	 * @generated
	 */
	EReference getAuthorizationIdentifier_OwnedSchema();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedRoleAuthorization <em>Received Role Authorization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Received Role Authorization</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedRoleAuthorization()
	 * @see #getAuthorizationIdentifier()
	 * @generated
	 */
	EReference getAuthorizationIdentifier_ReceivedRoleAuthorization();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedRoleAuthorization <em>Granted Role Authorization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Granted Role Authorization</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedRoleAuthorization()
	 * @see #getAuthorizationIdentifier()
	 * @generated
	 */
	EReference getAuthorizationIdentifier_GrantedRoleAuthorization();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedPrivilege <em>Granted Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Granted Privilege</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getGrantedPrivilege()
	 * @see #getAuthorizationIdentifier()
	 * @generated
	 */
	EReference getAuthorizationIdentifier_GrantedPrivilege();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedPrivilege <em>Received Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Received Privilege</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getReceivedPrivilege()
	 * @see #getAuthorizationIdentifier()
	 * @generated
	 */
	EReference getAuthorizationIdentifier_ReceivedPrivilege();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege <em>Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Privilege</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege
	 * @generated
	 */
	EClass getPrivilege();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#isGrantable <em>Grantable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grantable</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#isGrantable()
	 * @see #getPrivilege()
	 * @generated
	 */
	EAttribute getPrivilege_Grantable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getAction()
	 * @see #getPrivilege()
	 * @generated
	 */
	EAttribute getPrivilege_Action();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantor <em>Grantor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Grantor</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantor()
	 * @see #getPrivilege()
	 * @generated
	 */
	EReference getPrivilege_Grantor();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Object</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getObject()
	 * @see #getPrivilege()
	 * @generated
	 */
	EReference getPrivilege_Object();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Group <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Group
	 * @generated
	 */
	EClass getGroup();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Group#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>User</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Group#getUser()
	 * @see #getGroup()
	 * @generated
	 */
	EReference getGroup_User();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.User
	 * @generated
	 */
	EClass getUser();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Role#getRoleAuthorization <em>Role Authorization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Role Authorization</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Role#getRoleAuthorization()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_RoleAuthorization();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization <em>Role Authorization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role Authorization</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization
	 * @generated
	 */
	EClass getRoleAuthorization();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#isGrantable <em>Grantable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grantable</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#isGrantable()
	 * @see #getRoleAuthorization()
	 * @generated
	 */
	EAttribute getRoleAuthorization_Grantable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Role</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getRole()
	 * @see #getRoleAuthorization()
	 * @generated
	 */
	EReference getRoleAuthorization_Role();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantee <em>Grantee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Grantee</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantee()
	 * @see #getRoleAuthorization()
	 * @generated
	 */
	EReference getRoleAuthorization_Grantee();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantor <em>Grantor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Grantor</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization#getGrantor()
	 * @see #getRoleAuthorization()
	 * @generated
	 */
	EReference getRoleAuthorization_Grantor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.TablePrivilege <em>Table Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Privilege</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.TablePrivilege
	 * @generated
	 */
	EClass getTablePrivilege();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.TablePrivilege#isWithHierarchy <em>With Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>With Hierarchy</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.TablePrivilege#isWithHierarchy()
	 * @see #getTablePrivilege()
	 * @generated
	 */
	EAttribute getTablePrivilege_WithHierarchy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.DoubleObjectPrivilege <em>Double Object Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Object Privilege</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.DoubleObjectPrivilege
	 * @generated
	 */
	EClass getDoubleObjectPrivilege();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.DoubleObjectPrivilege#getObject2 <em>Object2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Object2</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.DoubleObjectPrivilege#getObject2()
	 * @see #getDoubleObjectPrivilege()
	 * @generated
	 */
	EReference getDoubleObjectPrivilege_Object2();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SQLAccessControlFactory getSQLAccessControlFactory();

} //SQLAccessControlPackage
