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
package org.eclipse.datatools.modelbase.sql.schema;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

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
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaFactory
 * @generated
 */
public interface SQLSchemaPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "schema"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/schema.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SQLSchema"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLSchemaPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl <em>Identity Specifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.IdentitySpecifierImpl
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getIdentitySpecifier()
	 * @generated
	 */
	int IDENTITY_SPECIFIER = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl <em>SQL Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getSQLObject()
	 * @generated
	 */
	int SQL_OBJECT = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_OBJECT__EANNOTATIONS = EcorePackage.ENAMED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_OBJECT__NAME = EcorePackage.ENAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_OBJECT__DEPENDENCIES = EcorePackage.ENAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_OBJECT__DESCRIPTION = EcorePackage.ENAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_OBJECT__LABEL = EcorePackage.ENAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>SQL Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_OBJECT_FEATURE_COUNT = EcorePackage.ENAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__EANNOTATIONS = SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__NAME = SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__DEPENDENCIES = SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__DESCRIPTION = SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__LABEL = SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Generation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__GENERATION_TYPE = SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Start Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__START_VALUE = SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Increment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__INCREMENT = SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Minimum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__MINIMUM = SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Maximum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__MAXIMUM = SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Cycle Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER__CYCLE_OPTION = SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the the '<em>Identity Specifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_SPECIFIER_FEATURE_COUNT = SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.schema.impl.TypedElementImpl <em>Typed Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.TypedElementImpl
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getTypedElement()
	 * @generated
	 */
	int TYPED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__EANNOTATIONS = SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__NAME = SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__DEPENDENCIES = SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__DESCRIPTION = SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__LABEL = SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__CONTAINED_TYPE = SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__REFERENCED_TYPE = SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Typed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT_FEATURE_COUNT = SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.schema.impl.DependencyImpl <em>Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.DependencyImpl
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getDependency()
	 * @generated
	 */
	int DEPENDENCY = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__EANNOTATIONS = SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__NAME = SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__DEPENDENCIES = SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__DESCRIPTION = SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__LABEL = SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Target End</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__TARGET_END = SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependency Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__DEPENDENCY_TYPE = SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY_FEATURE_COUNT = SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl <em>Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getSchema()
	 * @generated
	 */
	int SCHEMA = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__EANNOTATIONS = SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__NAME = SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__DEPENDENCIES = SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__DESCRIPTION = SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__LABEL = SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__TRIGGERS = SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__INDICES = SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__TABLES = SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sequences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__SEQUENCES = SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__DATABASE = SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Assertions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__ASSERTIONS = SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>User Defined Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__USER_DEFINED_TYPES = SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Char Sets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__CHAR_SETS = SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Routines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__ROUTINES = SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__OWNER = SQL_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the the '<em>Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA_FEATURE_COUNT = SQL_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.schema.impl.SequenceImpl <em>Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SequenceImpl
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getSequence()
	 * @generated
	 */
	int SEQUENCE = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__EANNOTATIONS = TYPED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__NAME = TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__DEPENDENCIES = TYPED_ELEMENT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__DESCRIPTION = TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__LABEL = TYPED_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__CONTAINED_TYPE = TYPED_ELEMENT__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__REFERENCED_TYPE = TYPED_ELEMENT__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Identity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__IDENTITY = TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE__SCHEMA = TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl <em>Database</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getDatabase()
	 * @generated
	 */
	int DATABASE = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__EANNOTATIONS = SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__NAME = SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__DEPENDENCIES = SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__DESCRIPTION = SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__LABEL = SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__VENDOR = SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__VERSION = SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Schemas</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__SCHEMAS = SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Authorization Ids</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE__AUTHORIZATION_IDS = SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the the '<em>Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_FEATURE_COUNT = SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.schema.GenerateType <em>Generate Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.schema.GenerateType
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getGenerateType()
	 * @generated
	 */
	int GENERATE_TYPE = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType <em>Referential Action Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getReferentialActionType()
	 * @generated
	 */
	int REFERENTIAL_ACTION_TYPE = 8;

	/**
	 * The meta object id for the '<em>Date</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Date
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getDate()
	 * @generated
	 */
	int DATE = 9;

	/**
	 * The meta object id for the '<em>List</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl#getList()
	 * @generated
	 */
	int LIST = 10;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier <em>Identity Specifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identity Specifier</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier
	 * @generated
	 */
	EClass getIdentitySpecifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getGenerationType <em>Generation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generation Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getGenerationType()
	 * @see #getIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIdentitySpecifier_GenerationType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getStartValue <em>Start Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Value</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getStartValue()
	 * @see #getIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIdentitySpecifier_StartValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getIncrement <em>Increment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Increment</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getIncrement()
	 * @see #getIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIdentitySpecifier_Increment();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getMinimum <em>Minimum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getMinimum()
	 * @see #getIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIdentitySpecifier_Minimum();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getMaximum <em>Maximum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getMaximum()
	 * @see #getIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIdentitySpecifier_Maximum();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#isCycleOption <em>Cycle Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cycle Option</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#isCycleOption()
	 * @see #getIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIdentitySpecifier_CycleOption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.schema.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typed Element</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.TypedElement
	 * @generated
	 */
	EClass getTypedElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.schema.TypedElement#getContainedType <em>Contained Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Contained Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.TypedElement#getContainedType()
	 * @see #getTypedElement()
	 * @generated
	 */
	EReference getTypedElement_ContainedType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.schema.TypedElement#getReferencedType <em>Referenced Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.TypedElement#getReferencedType()
	 * @see #getTypedElement()
	 * @generated
	 */
	EReference getTypedElement_ReferencedType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.schema.Dependency <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dependency</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Dependency
	 * @generated
	 */
	EClass getDependency();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.schema.Dependency#getTargetEnd <em>Target End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target End</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Dependency#getTargetEnd()
	 * @see #getDependency()
	 * @generated
	 */
	EReference getDependency_TargetEnd();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.Dependency#getDependencyType <em>Dependency Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dependency Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Dependency#getDependencyType()
	 * @see #getDependency()
	 * @generated
	 */
	EAttribute getDependency_DependencyType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.schema.Schema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema
	 * @generated
	 */
	EClass getSchema();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Triggers</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getTriggers()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Triggers();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getIndices <em>Indices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Indices</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getIndices()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Indices();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getTables <em>Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tables</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getTables()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Tables();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getSequences <em>Sequences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sequences</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getSequences()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Sequences();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getDatabase()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Database();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getAssertions <em>Assertions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Assertions</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getAssertions()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Assertions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getUserDefinedTypes <em>User Defined Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>User Defined Types</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getUserDefinedTypes()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_UserDefinedTypes();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getCharSets <em>Char Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Char Sets</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getCharSets()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_CharSets();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getRoutines <em>Routines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Routines</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getRoutines()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Routines();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Owner</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getOwner()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Owner();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject <em>SQL Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Object</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject
	 * @generated
	 */
	EClass getSQLObject();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dependencies</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject#getDependencies()
	 * @see #getSQLObject()
	 * @generated
	 */
	EReference getSQLObject_Dependencies();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject#getDescription()
	 * @see #getSQLObject()
	 * @generated
	 */
	EAttribute getSQLObject_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject#getLabel()
	 * @see #getSQLObject()
	 * @generated
	 */
	EAttribute getSQLObject_Label();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.schema.Sequence <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Sequence
	 * @generated
	 */
	EClass getSequence();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.schema.Sequence#getIdentity <em>Identity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Identity</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Sequence#getIdentity()
	 * @see #getSequence()
	 * @generated
	 */
	EReference getSequence_Identity();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.schema.Sequence#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Sequence#getSchema()
	 * @see #getSequence()
	 * @generated
	 */
	EReference getSequence_Schema();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.schema.Database <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database
	 * @generated
	 */
	EClass getDatabase();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getVendor <em>Vendor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database#getVendor()
	 * @see #getDatabase()
	 * @generated
	 */
	EAttribute getDatabase_Vendor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database#getVersion()
	 * @see #getDatabase()
	 * @generated
	 */
	EAttribute getDatabase_Version();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getSchemas <em>Schemas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Schemas</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database#getSchemas()
	 * @see #getDatabase()
	 * @generated
	 */
	EReference getDatabase_Schemas();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getAuthorizationIds <em>Authorization Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Authorization Ids</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database#getAuthorizationIds()
	 * @see #getDatabase()
	 * @generated
	 */
	EReference getDatabase_AuthorizationIds();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.schema.GenerateType <em>Generate Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Generate Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.GenerateType
	 * @generated
	 */
	EEnum getGenerateType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType <em>Referential Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Referential Action Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @generated
	 */
	EEnum getReferentialActionType();

	/**
	 * Returns the meta object for data type '{@link java.util.Date <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Date</em>'.
	 * @see java.util.Date
	 * @model instanceClass="java.util.Date"
	 * @generated
	 */
	EDataType getDate();

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>List</em>'.
	 * @see java.util.List
	 * @model instanceClass="java.util.List"
	 * @generated
	 */
	EDataType getList();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SQLSchemaFactory getSQLSchemaFactory();

} //SQLSchemaPackage
