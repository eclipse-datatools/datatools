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
 * @model kind="package"
 * @generated
 */
public interface SQLAccessControlPackage extends EPackage {
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
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Authorization Identifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AUTHORIZATION_IDENTIFIER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

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
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

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
	 * The feature id for the '<em><b>With Hierarchy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__WITH_HIERARCHY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Grantor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__GRANTOR = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Grantee</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__GRANTEE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Action Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__ACTION_OBJECTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE__OBJECT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Privilege</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

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
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__COMMENTS = AUTHORIZATION_IDENTIFIER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__EXTENSIONS = AUTHORIZATION_IDENTIFIER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__PRIVILEGES = AUTHORIZATION_IDENTIFIER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__OWNED_SCHEMA = AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__DATABASE = AUTHORIZATION_IDENTIFIER__DATABASE;

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
	 * The number of structural features of the '<em>Group</em>' class.
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
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__COMMENTS = AUTHORIZATION_IDENTIFIER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__EXTENSIONS = AUTHORIZATION_IDENTIFIER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__PRIVILEGES = AUTHORIZATION_IDENTIFIER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__OWNED_SCHEMA = AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DATABASE = AUTHORIZATION_IDENTIFIER__DATABASE;

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
	 * The feature id for the '<em><b>Group</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__GROUP = AUTHORIZATION_IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = AUTHORIZATION_IDENTIFIER_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__COMMENTS = AUTHORIZATION_IDENTIFIER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__EXTENSIONS = AUTHORIZATION_IDENTIFIER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__PRIVILEGES = AUTHORIZATION_IDENTIFIER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__OWNED_SCHEMA = AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DATABASE = AUTHORIZATION_IDENTIFIER__DATABASE;

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
	 * The number of structural features of the '<em>Role</em>' class.
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
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

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
	 * The number of structural features of the '<em>Role Authorization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_AUTHORIZATION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

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
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier#getDatabase()
	 * @see #getAuthorizationIdentifier()
	 * @generated
	 */
	EReference getAuthorizationIdentifier_Database();

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
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#isWithHierarchy <em>With Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>With Hierarchy</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#isWithHierarchy()
	 * @see #getPrivilege()
	 * @generated
	 */
	EAttribute getPrivilege_WithHierarchy();

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
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantee <em>Grantee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grantee</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getGrantee()
	 * @see #getPrivilege()
	 * @generated
	 */
	EReference getPrivilege_Grantee();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getActionObjects <em>Action Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Action Objects</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege#getActionObjects()
	 * @see #getPrivilege()
	 * @generated
	 */
	EReference getPrivilege_ActionObjects();

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
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.User#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Group</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.User#getGroup()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Group();

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
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SQLAccessControlFactory getSQLAccessControlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl <em>Authorization Identifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getAuthorizationIdentifier()
		 * @generated
		 */
		EClass AUTHORIZATION_IDENTIFIER = eINSTANCE.getAuthorizationIdentifier();

		/**
		 * The meta object literal for the '<em><b>Owned Schema</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA = eINSTANCE.getAuthorizationIdentifier_OwnedSchema();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHORIZATION_IDENTIFIER__DATABASE = eINSTANCE.getAuthorizationIdentifier_Database();

		/**
		 * The meta object literal for the '<em><b>Received Role Authorization</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION = eINSTANCE.getAuthorizationIdentifier_ReceivedRoleAuthorization();

		/**
		 * The meta object literal for the '<em><b>Granted Role Authorization</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION = eINSTANCE.getAuthorizationIdentifier_GrantedRoleAuthorization();

		/**
		 * The meta object literal for the '<em><b>Granted Privilege</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE = eINSTANCE.getAuthorizationIdentifier_GrantedPrivilege();

		/**
		 * The meta object literal for the '<em><b>Received Privilege</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE = eINSTANCE.getAuthorizationIdentifier_ReceivedPrivilege();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl <em>Privilege</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getPrivilege()
		 * @generated
		 */
		EClass PRIVILEGE = eINSTANCE.getPrivilege();

		/**
		 * The meta object literal for the '<em><b>Grantable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIVILEGE__GRANTABLE = eINSTANCE.getPrivilege_Grantable();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIVILEGE__ACTION = eINSTANCE.getPrivilege_Action();

		/**
		 * The meta object literal for the '<em><b>With Hierarchy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIVILEGE__WITH_HIERARCHY = eINSTANCE.getPrivilege_WithHierarchy();

		/**
		 * The meta object literal for the '<em><b>Grantor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIVILEGE__GRANTOR = eINSTANCE.getPrivilege_Grantor();

		/**
		 * The meta object literal for the '<em><b>Grantee</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIVILEGE__GRANTEE = eINSTANCE.getPrivilege_Grantee();

		/**
		 * The meta object literal for the '<em><b>Action Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIVILEGE__ACTION_OBJECTS = eINSTANCE.getPrivilege_ActionObjects();

		/**
		 * The meta object literal for the '<em><b>Object</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIVILEGE__OBJECT = eINSTANCE.getPrivilege_Object();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.GroupImpl <em>Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.GroupImpl
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getGroup()
		 * @generated
		 */
		EClass GROUP = eINSTANCE.getGroup();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GROUP__USER = eINSTANCE.getGroup_User();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.UserImpl <em>User</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.UserImpl
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getUser()
		 * @generated
		 */
		EClass USER = eINSTANCE.getUser();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__GROUP = eINSTANCE.getUser_Group();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleImpl
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getRole()
		 * @generated
		 */
		EClass ROLE = eINSTANCE.getRole();

		/**
		 * The meta object literal for the '<em><b>Role Authorization</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__ROLE_AUTHORIZATION = eINSTANCE.getRole_RoleAuthorization();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleAuthorizationImpl <em>Role Authorization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleAuthorizationImpl
		 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl#getRoleAuthorization()
		 * @generated
		 */
		EClass ROLE_AUTHORIZATION = eINSTANCE.getRoleAuthorization();

		/**
		 * The meta object literal for the '<em><b>Grantable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROLE_AUTHORIZATION__GRANTABLE = eINSTANCE.getRoleAuthorization_Grantable();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_AUTHORIZATION__ROLE = eINSTANCE.getRoleAuthorization_Role();

		/**
		 * The meta object literal for the '<em><b>Grantee</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_AUTHORIZATION__GRANTEE = eINSTANCE.getRoleAuthorization_Grantee();

		/**
		 * The meta object literal for the '<em><b>Grantor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE_AUTHORIZATION__GRANTOR = eINSTANCE.getRoleAuthorization_Grantor();

}

} //SQLAccessControlPackage
