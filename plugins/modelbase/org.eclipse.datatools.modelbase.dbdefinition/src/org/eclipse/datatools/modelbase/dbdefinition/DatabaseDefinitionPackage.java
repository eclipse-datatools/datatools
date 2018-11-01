/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionFactory
 * @model kind="package"
 * @generated
 */
public interface DatabaseDefinitionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dbdefinition"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/dbdefinition/dbdefinition.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "DBDefinition"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DatabaseDefinitionPackage eINSTANCE = org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl <em>Database Vendor Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getDatabaseVendorDefinition()
	 * @generated
	 */
	int DATABASE_VENDOR_DEFINITION = 0;

	/**
	 * The feature id for the '<em><b>Predefined Data Type Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS = 0;

	/**
	 * The feature id for the '<em><b>Table Space Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION = 1;

	/**
	 * The feature id for the '<em><b>Stored Procedure Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION = 2;

	/**
	 * The feature id for the '<em><b>Trigger Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION = 3;

	/**
	 * The feature id for the '<em><b>Column Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION = 4;

	/**
	 * The feature id for the '<em><b>Constraint Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION = 5;

	/**
	 * The feature id for the '<em><b>Extended Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__EXTENDED_DEFINITIONS = 6;

	/**
	 * The feature id for the '<em><b>Index Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION = 7;

	/**
	 * The feature id for the '<em><b>Table Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION = 8;

	/**
	 * The feature id for the '<em><b>Sequence Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION = 9;

	/**
	 * The feature id for the '<em><b>Udt Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__UDT_DEFINITION = 10;

	/**
	 * The feature id for the '<em><b>Query Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION = 11;

	/**
	 * The feature id for the '<em><b>SQL Syntax Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION = 12;

	/**
	 * The feature id for the '<em><b>Nickname Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION = 13;

	/**
	 * The feature id for the '<em><b>Schema Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION = 14;

	/**
	 * The feature id for the '<em><b>View Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION = 15;

	/**
	 * The feature id for the '<em><b>Debugger Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION = 16;

	/**
	 * The feature id for the '<em><b>Privileged Element Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__PRIVILEGED_ELEMENT_DEFINITIONS = 17;

	/**
	 * The feature id for the '<em><b>Constructed Data Type Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION = 18;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__VENDOR = 19;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__VERSION = 20;

	/**
	 * The feature id for the '<em><b>Constraints Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__CONSTRAINTS_SUPPORTED = 21;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 22;

	/**
	 * The feature id for the '<em><b>Trigger Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__TRIGGER_SUPPORTED = 23;

	/**
	 * The feature id for the '<em><b>Snapshot View Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__SNAPSHOT_VIEW_SUPPORTED = 24;

	/**
	 * The feature id for the '<em><b>Join Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__JOIN_SUPPORTED = 25;

	/**
	 * The feature id for the '<em><b>View Trigger Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__VIEW_TRIGGER_SUPPORTED = 26;

	/**
	 * The feature id for the '<em><b>Tablespaces Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__TABLESPACES_SUPPORTED = 27;

	/**
	 * The feature id for the '<em><b>Maximum Comment Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__MAXIMUM_COMMENT_LENGTH = 28;

	/**
	 * The feature id for the '<em><b>Sequence Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__SEQUENCE_SUPPORTED = 29;

	/**
	 * The feature id for the '<em><b>MQT Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__MQT_SUPPORTED = 30;

	/**
	 * The feature id for the '<em><b>Schema Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__SCHEMA_SUPPORTED = 31;

	/**
	 * The feature id for the '<em><b>Alias Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__ALIAS_SUPPORTED = 32;

	/**
	 * The feature id for the '<em><b>Synonym Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__SYNONYM_SUPPORTED = 33;

	/**
	 * The feature id for the '<em><b>User Defined Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__USER_DEFINED_TYPE_SUPPORTED = 34;

	/**
	 * The feature id for the '<em><b>Domain Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__DOMAIN_SUPPORTED = 35;

	/**
	 * The feature id for the '<em><b>SQL Statement Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__SQL_STATEMENT_SUPPORTED = 36;

	/**
	 * The feature id for the '<em><b>Nickname Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__NICKNAME_SUPPORTED = 37;

	/**
	 * The feature id for the '<em><b>Quoted DML Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__QUOTED_DML_SUPPORTED = 38;

	/**
	 * The feature id for the '<em><b>Quoted DDL Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__QUOTED_DDL_SUPPORTED = 39;

	/**
	 * The feature id for the '<em><b>Xml Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__XML_SUPPORTED = 40;

	/**
	 * The feature id for the '<em><b>MQT Index Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__MQT_INDEX_SUPPORTED = 41;

	/**
	 * The feature id for the '<em><b>Event Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__EVENT_SUPPORTED = 42;

	/**
	 * The feature id for the '<em><b>Sql UDF Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__SQL_UDF_SUPPORTED = 43;

	/**
	 * The feature id for the '<em><b>Stored Procedure Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_SUPPORTED = 44;

	/**
	 * The feature id for the '<em><b>Package Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__PACKAGE_SUPPORTED = 45;

	/**
	 * The feature id for the '<em><b>Authorization Identifier Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__AUTHORIZATION_IDENTIFIER_SUPPORTED = 46;

	/**
	 * The feature id for the '<em><b>Role Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__ROLE_SUPPORTED = 47;

	/**
	 * The feature id for the '<em><b>Group Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__GROUP_SUPPORTED = 48;

	/**
	 * The feature id for the '<em><b>User Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__USER_SUPPORTED = 49;

	/**
	 * The feature id for the '<em><b>Role Authorization Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__ROLE_AUTHORIZATION_SUPPORTED = 50;

	/**
	 * The feature id for the '<em><b>Constructed Data Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_SUPPORTED = 51;

	/**
	 * The feature id for the '<em><b>UDF Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION__UDF_SUPPORTED = 52;

	/**
	 * The number of structural features of the '<em>Database Vendor Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATABASE_VENDOR_DEFINITION_FEATURE_COUNT = 53;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl <em>Predefined Data Type Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getPredefinedDataTypeDefinition()
	 * @generated
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION = 1;

	/**
	 * The feature id for the '<em><b>Leading Field Qualifier Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_DEFINITION = 0;

	/**
	 * The feature id for the '<em><b>Trailing Field Qualifier Definition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_DEFINITION = 1;

	/**
	 * The feature id for the '<em><b>Default Trailing Field Qualifier Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_TRAILING_FIELD_QUALIFIER_DEFINITION = 2;

	/**
	 * The feature id for the '<em><b>Default Leading Field Qualifier Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LEADING_FIELD_QUALIFIER_DEFINITION = 3;

	/**
	 * The feature id for the '<em><b>Length Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>Scale Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__SCALE_SUPPORTED = 5;

	/**
	 * The feature id for the '<em><b>Precision Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__PRECISION_SUPPORTED = 6;

	/**
	 * The feature id for the '<em><b>Key Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__KEY_CONSTRAINT_SUPPORTED = 7;

	/**
	 * The feature id for the '<em><b>Identity Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__IDENTITY_SUPPORTED = 8;

	/**
	 * The feature id for the '<em><b>Multiple Columns Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__MULTIPLE_COLUMNS_SUPPORTED = 9;

	/**
	 * The feature id for the '<em><b>Nullable Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__NULLABLE_SUPPORTED = 10;

	/**
	 * The feature id for the '<em><b>Default Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SUPPORTED = 11;

	/**
	 * The feature id for the '<em><b>Clustering Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__CLUSTERING_SUPPORTED = 12;

	/**
	 * The feature id for the '<em><b>Fill Factor Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__FILL_FACTOR_SUPPORTED = 13;

	/**
	 * The feature id for the '<em><b>Bit Data Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__BIT_DATA_SUPPORTED = 14;

	/**
	 * The feature id for the '<em><b>Maximum Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_VALUE = 15;

	/**
	 * The feature id for the '<em><b>Minimum Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_VALUE = 16;

	/**
	 * The feature id for the '<em><b>Maximum Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_LENGTH = 17;

	/**
	 * The feature id for the '<em><b>Maximum Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_PRECISION = 18;

	/**
	 * The feature id for the '<em><b>Maximum Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_SCALE = 19;

	/**
	 * The feature id for the '<em><b>Minimum Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_SCALE = 20;

	/**
	 * The feature id for the '<em><b>Default Value Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_VALUE_TYPES = 21;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__PRIMITIVE_TYPE = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__NAME = 23;

	/**
	 * The feature id for the '<em><b>Jdbc Enum Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__JDBC_ENUM_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Character Set</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET = 25;

	/**
	 * The feature id for the '<em><b>Encoding Scheme</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME = 26;

	/**
	 * The feature id for the '<em><b>Character Set Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET_SUFFIX = 27;

	/**
	 * The feature id for the '<em><b>Encoding Scheme Suffix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME_SUFFIX = 28;

	/**
	 * The feature id for the '<em><b>Java Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__JAVA_CLASS_NAME = 29;

	/**
	 * The feature id for the '<em><b>Default Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LENGTH = 30;

	/**
	 * The feature id for the '<em><b>Default Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_PRECISION = 31;

	/**
	 * The feature id for the '<em><b>Default Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SCALE = 32;

	/**
	 * The feature id for the '<em><b>Cutoff Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__CUTOFF_PRECISION = 33;

	/**
	 * The feature id for the '<em><b>Length Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_UNIT = 34;

	/**
	 * The feature id for the '<em><b>Ordering Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__ORDERING_SUPPORTED = 35;

	/**
	 * The feature id for the '<em><b>Grouping Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__GROUPING_SUPPORTED = 36;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME = 37;

	/**
	 * The feature id for the '<em><b>Display Name Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME_SUPPORTED = 38;

	/**
	 * The feature id for the '<em><b>Leading Field Qualifier Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_SUPPORTED = 39;

	/**
	 * The feature id for the '<em><b>Trailing Field Qualifier Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_SUPPORTED = 40;

	/**
	 * The feature id for the '<em><b>Field Qualifier Separator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__FIELD_QUALIFIER_SEPARATOR = 41;

	/**
	 * The feature id for the '<em><b>Large Value Specifier Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_SUPPORTED = 42;

	/**
	 * The feature id for the '<em><b>Large Value Specifier Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_NAME = 43;

	/**
	 * The feature id for the '<em><b>Large Value Specifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_LENGTH = 44;

	/**
	 * The feature id for the '<em><b>Length Semantic Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC_SUPPORTED = 45;

	/**
	 * The feature id for the '<em><b>Length Semantic</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC = 46;

	/**
	 * The feature id for the '<em><b>Language Type</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION__LANGUAGE_TYPE = 47;

	/**
	 * The number of structural features of the '<em>Predefined Data Type Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_DEFINITION_FEATURE_COUNT = 48;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl <em>Table Space Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getTableSpaceDefinition()
	 * @generated
	 */
	int TABLE_SPACE_DEFINITION = 2;

	/**
	 * The feature id for the '<em><b>Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__TYPE_SUPPORTED = 0;

	/**
	 * The feature id for the '<em><b>Extent Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__EXTENT_SIZE_SUPPORTED = 1;

	/**
	 * The feature id for the '<em><b>Prefetch Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__PREFETCH_SIZE_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Managed By Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__MANAGED_BY_SUPPORTED = 3;

	/**
	 * The feature id for the '<em><b>Page Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__PAGE_SIZE_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>Buffer Pool Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__BUFFER_POOL_SUPPORTED = 5;

	/**
	 * The feature id for the '<em><b>Default Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__DEFAULT_SUPPORTED = 6;

	/**
	 * The feature id for the '<em><b>Container Maximum Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__CONTAINER_MAXIMUM_SIZE_SUPPORTED = 7;

	/**
	 * The feature id for the '<em><b>Container Initial Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__CONTAINER_INITIAL_SIZE_SUPPORTED = 8;

	/**
	 * The feature id for the '<em><b>Container Extent Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__CONTAINER_EXTENT_SIZE_SUPPORTED = 9;

	/**
	 * The feature id for the '<em><b>Table Space Type</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__TABLE_SPACE_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 11;

	/**
	 * The number of structural features of the '<em>Table Space Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_SPACE_DEFINITION_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl <em>Stored Procedure Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getStoredProcedureDefinition()
	 * @generated
	 */
	int STORED_PROCEDURE_DEFINITION = 3;

	/**
	 * The feature id for the '<em><b>Predefined Data Type Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS = 0;

	/**
	 * The feature id for the '<em><b>Null Input Action Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__NULL_INPUT_ACTION_SUPPORTED = 1;

	/**
	 * The feature id for the '<em><b>Package Generation Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__PACKAGE_GENERATION_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Determininstic Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__DETERMININSTIC_SUPPORTED = 3;

	/**
	 * The feature id for the '<em><b>Returned Null Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__RETURNED_NULL_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>Returned Type Declaration Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED = 5;

	/**
	 * The feature id for the '<em><b>Parameter Init Value Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__PARAMETER_INIT_VALUE_SUPPORTED = 6;

	/**
	 * The feature id for the '<em><b>Parameter Style Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE_SUPPORTED = 7;

	/**
	 * The feature id for the '<em><b>Return Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__RETURN_TYPE_SUPPORTED = 8;

	/**
	 * The feature id for the '<em><b>Parameter Declaration Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED = 9;

	/**
	 * The feature id for the '<em><b>Maximum Action Body Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH = 10;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE = 11;

	/**
	 * The feature id for the '<em><b>Language Type</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__LANGUAGE_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Function Language Type</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__FUNCTION_LANGUAGE_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Procedure Type</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__PROCEDURE_TYPE = 14;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 15;

	/**
	 * The number of structural features of the '<em>Stored Procedure Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORED_PROCEDURE_DEFINITION_FEATURE_COUNT = 16;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl <em>Trigger Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getTriggerDefinition()
	 * @generated
	 */
	int TRIGGER_DEFINITION = 4;

	/**
	 * The feature id for the '<em><b>Maximum Reference Part Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__MAXIMUM_REFERENCE_PART_LENGTH = 0;

	/**
	 * The feature id for the '<em><b>Maximum Action Body Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH = 1;

	/**
	 * The feature id for the '<em><b>Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__TYPE_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>When Clause Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__WHEN_CLAUSE_SUPPORTED = 3;

	/**
	 * The feature id for the '<em><b>Granularity Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__GRANULARITY_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>References Clause Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__REFERENCES_CLAUSE_SUPPORTED = 5;

	/**
	 * The feature id for the '<em><b>Per Column Update Trigger Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__PER_COLUMN_UPDATE_TRIGGER_SUPPORTED = 6;

	/**
	 * The feature id for the '<em><b>Instead Of Trigger Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__INSTEAD_OF_TRIGGER_SUPPORTED = 7;

	/**
	 * The feature id for the '<em><b>Row Trigger Reference Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__ROW_TRIGGER_REFERENCE_SUPPORTED = 8;

	/**
	 * The feature id for the '<em><b>Table Trigger Reference Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__TABLE_TRIGGER_REFERENCE_SUPPORTED = 9;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 10;

	/**
	 * The number of structural features of the '<em>Trigger Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_DEFINITION_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl <em>Column Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getColumnDefinition()
	 * @generated
	 */
	int COLUMN_DEFINITION = 5;

	/**
	 * The feature id for the '<em><b>Identity Column Data Type Definitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION__IDENTITY_COLUMN_DATA_TYPE_DEFINITIONS = 0;

	/**
	 * The feature id for the '<em><b>Identity Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION__IDENTITY_SUPPORTED = 1;

	/**
	 * The feature id for the '<em><b>Computed Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION__COMPUTED_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Identity Start Value Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION__IDENTITY_START_VALUE_SUPPORTED = 3;

	/**
	 * The feature id for the '<em><b>Identity Increment Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION__IDENTITY_INCREMENT_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>Identity Minimum Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION__IDENTITY_MINIMUM_SUPPORTED = 5;

	/**
	 * The feature id for the '<em><b>Identity Maximum Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION__IDENTITY_MAXIMUM_SUPPORTED = 6;

	/**
	 * The feature id for the '<em><b>Identity Cycle Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION__IDENTITY_CYCLE_SUPPORTED = 7;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 8;

	/**
	 * The number of structural features of the '<em>Column Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_DEFINITION_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl <em>Constraint Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getConstraintDefinition()
	 * @generated
	 */
	int CONSTRAINT_DEFINITION = 6;

	/**
	 * The feature id for the '<em><b>Deferrable Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__DEFERRABLE_CONSTRAINT_SUPPORTED = 0;

	/**
	 * The feature id for the '<em><b>Informational Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__INFORMATIONAL_CONSTRAINT_SUPPORTED = 1;

	/**
	 * The feature id for the '<em><b>Clustered Primary Key Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__CLUSTERED_PRIMARY_KEY_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Clustered Unique Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED = 3;

	/**
	 * The feature id for the '<em><b>Primary Key Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__PRIMARY_KEY_NULLABLE = 4;

	/**
	 * The feature id for the '<em><b>Unique Key Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__UNIQUE_KEY_NULLABLE = 5;

	/**
	 * The feature id for the '<em><b>Maximum Check Expression Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__MAXIMUM_CHECK_EXPRESSION_LENGTH = 6;

	/**
	 * The feature id for the '<em><b>Parent Update DRI Rule Type</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__PARENT_UPDATE_DRI_RULE_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Parent Delete DRI Rule Type</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__PARENT_DELETE_DRI_RULE_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Check Option</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__CHECK_OPTION = 9;

	/**
	 * The feature id for the '<em><b>Maximum Primary Key Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH = 10;

	/**
	 * The feature id for the '<em><b>Maximum Foreign Key Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH = 11;

	/**
	 * The feature id for the '<em><b>Maximum Check Constraint Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION__MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH = 12;

	/**
	 * The number of structural features of the '<em>Constraint Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_DEFINITION_FEATURE_COUNT = 13;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl <em>Index Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getIndexDefinition()
	 * @generated
	 */
	int INDEX_DEFINITION = 7;

	/**
	 * The feature id for the '<em><b>Percent Free Terminology</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_DEFINITION__PERCENT_FREE_TERMINOLOGY = 0;

	/**
	 * The feature id for the '<em><b>Percent Free Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_DEFINITION__PERCENT_FREE_CHANGEABLE = 1;

	/**
	 * The feature id for the '<em><b>Clustering Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_DEFINITION__CLUSTERING_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Cluster Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_DEFINITION__CLUSTER_CHANGEABLE = 3;

	/**
	 * The feature id for the '<em><b>Fill Factor Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_DEFINITION__FILL_FACTOR_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>Included Columns Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_DEFINITION__INCLUDED_COLUMNS_SUPPORTED = 5;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 6;

	/**
	 * The number of structural features of the '<em>Index Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_DEFINITION_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ExtendedDefinitionImpl <em>Extended Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ExtendedDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getExtendedDefinition()
	 * @generated
	 */
	int EXTENDED_DEFINITION = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_DEFINITION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_DEFINITION__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Extended Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_DEFINITION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl <em>Table Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getTableDefinition()
	 * @generated
	 */
	int TABLE_DEFINITION = 9;

	/**
	 * The feature id for the '<em><b>Audit Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_DEFINITION__AUDIT_SUPPORTED = 0;

	/**
	 * The feature id for the '<em><b>Data Capture Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_DEFINITION__DATA_CAPTURE_SUPPORTED = 1;

	/**
	 * The feature id for the '<em><b>Edit Proc Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_DEFINITION__EDIT_PROC_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Encoding Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_DEFINITION__ENCODING_SUPPORTED = 3;

	/**
	 * The feature id for the '<em><b>Valid Proc Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_DEFINITION__VALID_PROC_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 5;

	/**
	 * The number of structural features of the '<em>Table Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_DEFINITION_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl <em>Sequence Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getSequenceDefinition()
	 * @generated
	 */
	int SEQUENCE_DEFINITION = 10;

	/**
	 * The feature id for the '<em><b>Predefined Data Type Definitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS = 0;

	/**
	 * The feature id for the '<em><b>Default Data Type Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION__DEFAULT_DATA_TYPE_DEFINITION = 1;

	/**
	 * The feature id for the '<em><b>Type Enumeration Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION__TYPE_ENUMERATION_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Cache Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION__CACHE_SUPPORTED = 3;

	/**
	 * The feature id for the '<em><b>Order Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION__ORDER_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>No Maximum Value String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION__NO_MAXIMUM_VALUE_STRING = 5;

	/**
	 * The feature id for the '<em><b>No Minimum Value String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION__NO_MINIMUM_VALUE_STRING = 6;

	/**
	 * The feature id for the '<em><b>No Cache String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION__NO_CACHE_STRING = 7;

	/**
	 * The feature id for the '<em><b>Cache Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION__CACHE_DEFAULT_VALUE = 8;

	/**
	 * The number of structural features of the '<em>Sequence Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_DEFINITION_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.UserDefinedTypeDefinitionImpl <em>User Defined Type Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.UserDefinedTypeDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getUserDefinedTypeDefinition()
	 * @generated
	 */
	int USER_DEFINED_TYPE_DEFINITION = 11;

	/**
	 * The feature id for the '<em><b>Default Value Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_DEFINITION__DEFAULT_VALUE_SUPPORTED = 0;

	/**
	 * The feature id for the '<em><b>Distinct Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_DEFINITION__DISTINCT_TYPE_SUPPORTED = 1;

	/**
	 * The feature id for the '<em><b>Structured Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_DEFINITION__STRUCTURED_TYPE_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 3;

	/**
	 * The number of structural features of the '<em>User Defined Type Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_DEFINITION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl <em>Query Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getQueryDefinition()
	 * @generated
	 */
	int QUERY_DEFINITION = 12;

	/**
	 * The feature id for the '<em><b>Identifier Quote String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_DEFINITION__IDENTIFIER_QUOTE_STRING = 0;

	/**
	 * The feature id for the '<em><b>Host Variable Marker</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_DEFINITION__HOST_VARIABLE_MARKER = 1;

	/**
	 * The feature id for the '<em><b>Host Variable Marker Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_DEFINITION__HOST_VARIABLE_MARKER_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Cast Expression Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_DEFINITION__CAST_EXPRESSION_SUPPORTED = 3;

	/**
	 * The feature id for the '<em><b>Default Keyword For Insert Value Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_DEFINITION__DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>Extended Grouping Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_DEFINITION__EXTENDED_GROUPING_SUPPORTED = 5;

	/**
	 * The feature id for the '<em><b>Table Alias In Delete Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_DEFINITION__TABLE_ALIAS_IN_DELETE_SUPPORTED = 6;

	/**
	 * The number of structural features of the '<em>Query Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_DEFINITION_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SQLSyntaxDefinitionImpl <em>SQL Syntax Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.SQLSyntaxDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getSQLSyntaxDefinition()
	 * @generated
	 */
	int SQL_SYNTAX_DEFINITION = 13;

	/**
	 * The feature id for the '<em><b>Keywords</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_SYNTAX_DEFINITION__KEYWORDS = 0;

	/**
	 * The feature id for the '<em><b>Operators</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_SYNTAX_DEFINITION__OPERATORS = 1;

	/**
	 * The feature id for the '<em><b>Termination Character</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_SYNTAX_DEFINITION__TERMINATION_CHARACTER = 2;

	/**
	 * The number of structural features of the '<em>SQL Syntax Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_SYNTAX_DEFINITION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.NicknameDefinitionImpl <em>Nickname Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.NicknameDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getNicknameDefinition()
	 * @generated
	 */
	int NICKNAME_DEFINITION = 14;

	/**
	 * The feature id for the '<em><b>Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NICKNAME_DEFINITION__CONSTRAINT_SUPPORTED = 0;

	/**
	 * The feature id for the '<em><b>Index Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NICKNAME_DEFINITION__INDEX_SUPPORTED = 1;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NICKNAME_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 2;

	/**
	 * The number of structural features of the '<em>Nickname Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NICKNAME_DEFINITION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SchemaDefinitionImpl <em>Schema Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.SchemaDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getSchemaDefinition()
	 * @generated
	 */
	int SCHEMA_DEFINITION = 15;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 0;

	/**
	 * The number of structural features of the '<em>Schema Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA_DEFINITION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ViewDefinitionImpl <em>View Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ViewDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getViewDefinition()
	 * @generated
	 */
	int VIEW_DEFINITION = 16;

	/**
	 * The feature id for the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = 0;

	/**
	 * The feature id for the '<em><b>Index Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_DEFINITION__INDEX_SUPPORTED = 1;

	/**
	 * The feature id for the '<em><b>Check Option Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_DEFINITION__CHECK_OPTION_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Check Option Levels Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_DEFINITION__CHECK_OPTION_LEVELS_SUPPORTED = 3;

	/**
	 * The number of structural features of the '<em>View Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_DEFINITION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl <em>Field Qualifier Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getFieldQualifierDefinition()
	 * @generated
	 */
	int FIELD_QUALIFIER_DEFINITION = 17;

	/**
	 * The feature id for the '<em><b>Valid Trailing Field Qualifier Definitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_QUALIFIER_DEFINITION__VALID_TRAILING_FIELD_QUALIFIER_DEFINITIONS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_QUALIFIER_DEFINITION__NAME = 1;

	/**
	 * The feature id for the '<em><b>Maximum Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_QUALIFIER_DEFINITION__MAXIMUM_PRECISION = 2;

	/**
	 * The feature id for the '<em><b>Default Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_QUALIFIER_DEFINITION__DEFAULT_PRECISION = 3;

	/**
	 * The feature id for the '<em><b>Precision Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_QUALIFIER_DEFINITION__PRECISION_SUPPORTED = 4;

	/**
	 * The feature id for the '<em><b>Maximum Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_QUALIFIER_DEFINITION__MAXIMUM_SCALE = 5;

	/**
	 * The feature id for the '<em><b>Default Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_QUALIFIER_DEFINITION__DEFAULT_SCALE = 6;

	/**
	 * The feature id for the '<em><b>Scale Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_QUALIFIER_DEFINITION__SCALE_SUPPORTED = 7;

	/**
	 * The number of structural features of the '<em>Field Qualifier Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_QUALIFIER_DEFINITION_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DebuggerDefinitionImpl <em>Debugger Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DebuggerDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getDebuggerDefinition()
	 * @generated
	 */
	int DEBUGGER_DEFINITION = 18;

	/**
	 * The feature id for the '<em><b>Condition Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBUGGER_DEFINITION__CONDITION_SUPPORTED = 0;

	/**
	 * The number of structural features of the '<em>Debugger Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEBUGGER_DEFINITION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegedElementDefinitionImpl <em>Privileged Element Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegedElementDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getPrivilegedElementDefinition()
	 * @generated
	 */
	int PRIVILEGED_ELEMENT_DEFINITION = 19;

	/**
	 * The feature id for the '<em><b>Privilege Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGED_ELEMENT_DEFINITION__PRIVILEGE_DEFINITIONS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGED_ELEMENT_DEFINITION__NAME = 1;

	/**
	 * The number of structural features of the '<em>Privileged Element Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGED_ELEMENT_DEFINITION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegeDefinitionImpl <em>Privilege Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegeDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getPrivilegeDefinition()
	 * @generated
	 */
	int PRIVILEGE_DEFINITION = 20;

	/**
	 * The feature id for the '<em><b>Action Element Definitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_DEFINITION__ACTION_ELEMENT_DEFINITIONS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_DEFINITION__NAME = 1;

	/**
	 * The number of structural features of the '<em>Privilege Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIVILEGE_DEFINITION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstructedDataTypeDefinitionImpl <em>Constructed Data Type Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ConstructedDataTypeDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getConstructedDataTypeDefinition()
	 * @generated
	 */
	int CONSTRUCTED_DATA_TYPE_DEFINITION = 21;

	/**
	 * The feature id for the '<em><b>Array Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE_DEFINITION__ARRAY_DATATYPE_SUPPORTED = 0;

	/**
	 * The feature id for the '<em><b>Multiset Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE_DEFINITION__MULTISET_DATATYPE_SUPPORTED = 1;

	/**
	 * The feature id for the '<em><b>Row Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE_DEFINITION__ROW_DATATYPE_SUPPORTED = 2;

	/**
	 * The feature id for the '<em><b>Reference Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE_DEFINITION__REFERENCE_DATATYPE_SUPPORTED = 3;

	/**
	 * The feature id for the '<em><b>Cursor Datatype Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE_DEFINITION__CURSOR_DATATYPE_SUPPORTED = 4;

	/**
	 * The number of structural features of the '<em>Constructed Data Type Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE_DEFINITION_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.CheckOption <em>Check Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.CheckOption
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getCheckOption()
	 * @generated
	 */
	int CHECK_OPTION = 22;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.LanguageType <em>Language Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.LanguageType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getLanguageType()
	 * @generated
	 */
	int LANGUAGE_TYPE = 23;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle <em>Parameter Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getParameterStyle()
	 * @generated
	 */
	int PARAMETER_STYLE = 24;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType <em>Parent Delete DRI Rule Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getParentDeleteDRIRuleType()
	 * @generated
	 */
	int PARENT_DELETE_DRI_RULE_TYPE = 25;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType <em>Parent Update DRI Rule Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getParentUpdateDRIRuleType()
	 * @generated
	 */
	int PARENT_UPDATE_DRI_RULE_TYPE = 26;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.ProcedureType <em>Procedure Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ProcedureType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getProcedureType()
	 * @generated
	 */
	int PROCEDURE_TYPE = 27;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType <em>Table Space Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getTableSpaceType()
	 * @generated
	 */
	int TABLE_SPACE_TYPE = 28;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology <em>Percent Free Terminology</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getPercentFreeTerminology()
	 * @generated
	 */
	int PERCENT_FREE_TERMINOLOGY = 29;


	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.dbdefinition.LengthUnit <em>Length Unit</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.dbdefinition.LengthUnit
	 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getLengthUnit()
	 * @generated
	 */
	int LENGTH_UNIT = 30;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition <em>Database Vendor Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database Vendor Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition
	 * @generated
	 */
	EClass getDatabaseVendorDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getVendor <em>Vendor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getVendor()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_Vendor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getVersion()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isConstraintsSupported <em>Constraints Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constraints Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isConstraintsSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_ConstraintsSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getMaximumIdentifierLength()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isTriggerSupported <em>Trigger Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trigger Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isTriggerSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_TriggerSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSnapshotViewSupported <em>Snapshot View Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Snapshot View Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSnapshotViewSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_SnapshotViewSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isJoinSupported <em>Join Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Join Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isJoinSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_JoinSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isViewTriggerSupported <em>View Trigger Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>View Trigger Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isViewTriggerSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_ViewTriggerSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isTablespacesSupported <em>Tablespaces Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tablespaces Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isTablespacesSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_TablespacesSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getMaximumCommentLength <em>Maximum Comment Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Comment Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getMaximumCommentLength()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_MaximumCommentLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSequenceSupported <em>Sequence Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sequence Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSequenceSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_SequenceSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isMQTSupported <em>MQT Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>MQT Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isMQTSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_MQTSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSchemaSupported <em>Schema Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSchemaSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_SchemaSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isAliasSupported <em>Alias Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alias Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isAliasSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_AliasSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSynonymSupported <em>Synonym Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synonym Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSynonymSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_SynonymSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUserDefinedTypeSupported <em>User Defined Type Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Defined Type Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUserDefinedTypeSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_UserDefinedTypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isDomainSupported <em>Domain Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Domain Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isDomainSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_DomainSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSQLStatementSupported <em>SQL Statement Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SQL Statement Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSQLStatementSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_SQLStatementSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isNicknameSupported <em>Nickname Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nickname Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isNicknameSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_NicknameSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isQuotedDMLSupported <em>Quoted DML Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quoted DML Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isQuotedDMLSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_QuotedDMLSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isQuotedDDLSupported <em>Quoted DDL Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quoted DDL Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isQuotedDDLSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_QuotedDDLSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isXmlSupported <em>Xml Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xml Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isXmlSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_XmlSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isMQTIndexSupported <em>MQT Index Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>MQT Index Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isMQTIndexSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_MQTIndexSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isEventSupported <em>Event Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isEventSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_EventSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSqlUDFSupported <em>Sql UDF Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sql UDF Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSqlUDFSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_SqlUDFSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isStoredProcedureSupported <em>Stored Procedure Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stored Procedure Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isStoredProcedureSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_StoredProcedureSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isPackageSupported <em>Package Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isPackageSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_PackageSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isAuthorizationIdentifierSupported <em>Authorization Identifier Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Authorization Identifier Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isAuthorizationIdentifierSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_AuthorizationIdentifierSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isRoleSupported <em>Role Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isRoleSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_RoleSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isGroupSupported <em>Group Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isGroupSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_GroupSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUserSupported <em>User Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUserSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_UserSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isRoleAuthorizationSupported <em>Role Authorization Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role Authorization Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isRoleAuthorizationSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_RoleAuthorizationSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isConstructedDataTypeSupported <em>Constructed Data Type Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constructed Data Type Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isConstructedDataTypeSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_ConstructedDataTypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUDFSupported <em>UDF Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>UDF Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUDFSupported()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EAttribute getDatabaseVendorDefinition_UDFSupported();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getPredefinedDataTypeDefinitions <em>Predefined Data Type Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Predefined Data Type Definitions</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getPredefinedDataTypeDefinitions()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_PredefinedDataTypeDefinitions();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTableSpaceDefinition <em>Table Space Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Table Space Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTableSpaceDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_TableSpaceDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getStoredProcedureDefinition <em>Stored Procedure Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Stored Procedure Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getStoredProcedureDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_StoredProcedureDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTriggerDefinition <em>Trigger Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Trigger Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTriggerDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_TriggerDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getColumnDefinition <em>Column Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Column Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getColumnDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_ColumnDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getConstraintDefinition <em>Constraint Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constraint Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getConstraintDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_ConstraintDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getExtendedDefinitions <em>Extended Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Definitions</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getExtendedDefinitions()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_ExtendedDefinitions();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getIndexDefinition <em>Index Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Index Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getIndexDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_IndexDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTableDefinition <em>Table Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Table Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTableDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_TableDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSequenceDefinition <em>Sequence Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sequence Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSequenceDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_SequenceDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getUdtDefinition <em>Udt Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Udt Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getUdtDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_UdtDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getQueryDefinition <em>Query Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Query Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getQueryDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_QueryDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSQLSyntaxDefinition <em>SQL Syntax Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>SQL Syntax Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSQLSyntaxDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_SQLSyntaxDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getNicknameDefinition <em>Nickname Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Nickname Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getNicknameDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_NicknameDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSchemaDefinition <em>Schema Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Schema Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSchemaDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_SchemaDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getViewDefinition <em>View Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>View Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getViewDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_ViewDefinition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getDebuggerDefinition <em>Debugger Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Debugger Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getDebuggerDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_DebuggerDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getPrivilegedElementDefinitions <em>Privileged Element Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Privileged Element Definitions</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getPrivilegedElementDefinitions()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_PrivilegedElementDefinitions();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getConstructedDataTypeDefinition <em>Constructed Data Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constructed Data Type Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getConstructedDataTypeDefinition()
	 * @see #getDatabaseVendorDefinition()
	 * @generated
	 */
	EReference getDatabaseVendorDefinition_ConstructedDataTypeDefinition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition <em>Predefined Data Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Predefined Data Type Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition
	 * @generated
	 */
	EClass getPredefinedDataTypeDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLeadingFieldQualifierDefinition <em>Leading Field Qualifier Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Leading Field Qualifier Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLeadingFieldQualifierDefinition()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EReference getPredefinedDataTypeDefinition_LeadingFieldQualifierDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getTrailingFieldQualifierDefinition <em>Trailing Field Qualifier Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Trailing Field Qualifier Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getTrailingFieldQualifierDefinition()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EReference getPredefinedDataTypeDefinition_TrailingFieldQualifierDefinition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultTrailingFieldQualifierDefinition <em>Default Trailing Field Qualifier Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Trailing Field Qualifier Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultTrailingFieldQualifierDefinition()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EReference getPredefinedDataTypeDefinition_DefaultTrailingFieldQualifierDefinition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultLeadingFieldQualifierDefinition <em>Default Leading Field Qualifier Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Leading Field Qualifier Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultLeadingFieldQualifierDefinition()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EReference getPredefinedDataTypeDefinition_DefaultLeadingFieldQualifierDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLengthSupported <em>Length Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLengthSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_LengthSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isScaleSupported <em>Scale Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isScaleSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_ScaleSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isPrecisionSupported <em>Precision Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precision Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isPrecisionSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_PrecisionSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isKeyConstraintSupported <em>Key Constraint Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key Constraint Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isKeyConstraintSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_KeyConstraintSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isIdentitySupported <em>Identity Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isIdentitySupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_IdentitySupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isMultipleColumnsSupported <em>Multiple Columns Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiple Columns Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isMultipleColumnsSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_MultipleColumnsSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isNullableSupported <em>Nullable Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nullable Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isNullableSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_NullableSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isDefaultSupported <em>Default Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isDefaultSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_DefaultSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isClusteringSupported <em>Clustering Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clustering Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isClusteringSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_ClusteringSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isFillFactorSupported <em>Fill Factor Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Factor Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isFillFactorSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_FillFactorSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isBitDataSupported <em>Bit Data Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bit Data Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isBitDataSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_BitDataSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumValue <em>Maximum Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Value</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumValue()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_MaximumValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMinimumValue <em>Minimum Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum Value</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMinimumValue()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_MinimumValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumLength <em>Maximum Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumLength()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_MaximumLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumPrecision <em>Maximum Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumPrecision()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_MaximumPrecision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumScale <em>Maximum Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Scale</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumScale()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_MaximumScale();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMinimumScale <em>Minimum Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum Scale</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMinimumScale()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_MinimumScale();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultValueTypes <em>Default Value Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Default Value Types</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultValueTypes()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_DefaultValueTypes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getPrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primitive Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getPrimitiveType()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_PrimitiveType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Name</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getName()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getJdbcEnumType <em>Jdbc Enum Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jdbc Enum Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getJdbcEnumType()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_JdbcEnumType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCharacterSet <em>Character Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Character Set</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCharacterSet()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_CharacterSet();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getEncodingScheme <em>Encoding Scheme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Encoding Scheme</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getEncodingScheme()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_EncodingScheme();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCharacterSetSuffix <em>Character Set Suffix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Character Set Suffix</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCharacterSetSuffix()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_CharacterSetSuffix();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getEncodingSchemeSuffix <em>Encoding Scheme Suffix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encoding Scheme Suffix</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getEncodingSchemeSuffix()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_EncodingSchemeSuffix();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getJavaClassName <em>Java Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Class Name</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getJavaClassName()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_JavaClassName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultLength <em>Default Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultLength()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_DefaultLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultPrecision <em>Default Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultPrecision()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_DefaultPrecision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultScale <em>Default Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Scale</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultScale()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_DefaultScale();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCutoffPrecision <em>Cutoff Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cutoff Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCutoffPrecision()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_CutoffPrecision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLengthUnit <em>Length Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length Unit</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLengthUnit()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_LengthUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isOrderingSupported <em>Ordering Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordering Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isOrderingSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_OrderingSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isGroupingSupported <em>Grouping Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Grouping Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isGroupingSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_GroupingSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDisplayName()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_DisplayName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isDisplayNameSupported <em>Display Name Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isDisplayNameSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_DisplayNameSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLeadingFieldQualifierSupported <em>Leading Field Qualifier Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Leading Field Qualifier Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLeadingFieldQualifierSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_LeadingFieldQualifierSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isTrailingFieldQualifierSupported <em>Trailing Field Qualifier Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trailing Field Qualifier Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isTrailingFieldQualifierSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_TrailingFieldQualifierSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getFieldQualifierSeparator <em>Field Qualifier Separator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Field Qualifier Separator</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getFieldQualifierSeparator()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_FieldQualifierSeparator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLargeValueSpecifierSupported <em>Large Value Specifier Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Large Value Specifier Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLargeValueSpecifierSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_LargeValueSpecifierSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLargeValueSpecifierName <em>Large Value Specifier Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Large Value Specifier Name</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLargeValueSpecifierName()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_LargeValueSpecifierName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLargeValueSpecifierLength <em>Large Value Specifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Large Value Specifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLargeValueSpecifierLength()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_LargeValueSpecifierLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLengthSemanticSupported <em>Length Semantic Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length Semantic Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLengthSemanticSupported()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_LengthSemanticSupported();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLengthSemantic <em>Length Semantic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Length Semantic</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLengthSemantic()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_LengthSemantic();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLanguageType <em>Language Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Language Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLanguageType()
	 * @see #getPredefinedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getPredefinedDataTypeDefinition_LanguageType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition <em>Table Space Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Space Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition
	 * @generated
	 */
	EClass getTableSpaceDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isTypeSupported <em>Type Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isTypeSupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_TypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isExtentSizeSupported <em>Extent Size Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extent Size Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isExtentSizeSupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_ExtentSizeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isPrefetchSizeSupported <em>Prefetch Size Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefetch Size Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isPrefetchSizeSupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_PrefetchSizeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isManagedBySupported <em>Managed By Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Managed By Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isManagedBySupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_ManagedBySupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isPageSizeSupported <em>Page Size Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Size Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isPageSizeSupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_PageSizeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isBufferPoolSupported <em>Buffer Pool Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Buffer Pool Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isBufferPoolSupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_BufferPoolSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isDefaultSupported <em>Default Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isDefaultSupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_DefaultSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerMaximumSizeSupported <em>Container Maximum Size Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container Maximum Size Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerMaximumSizeSupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_ContainerMaximumSizeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerInitialSizeSupported <em>Container Initial Size Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container Initial Size Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerInitialSizeSupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_ContainerInitialSizeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerExtentSizeSupported <em>Container Extent Size Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container Extent Size Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerExtentSizeSupported()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_ContainerExtentSizeSupported();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#getTableSpaceType <em>Table Space Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Table Space Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#getTableSpaceType()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_TableSpaceType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#getMaximumIdentifierLength()
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	EAttribute getTableSpaceDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition <em>Stored Procedure Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stored Procedure Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition
	 * @generated
	 */
	EClass getStoredProcedureDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getPredefinedDataTypeDefinitions <em>Predefined Data Type Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Predefined Data Type Definitions</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getPredefinedDataTypeDefinitions()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EReference getStoredProcedureDefinition_PredefinedDataTypeDefinitions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isNullInputActionSupported <em>Null Input Action Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Null Input Action Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isNullInputActionSupported()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_NullInputActionSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isPackageGenerationSupported <em>Package Generation Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Generation Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isPackageGenerationSupported()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_PackageGenerationSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isDetermininsticSupported <em>Determininstic Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Determininstic Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isDetermininsticSupported()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_DetermininsticSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnedNullSupported <em>Returned Null Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Returned Null Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnedNullSupported()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_ReturnedNullSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnedTypeDeclarationConstraintSupported <em>Returned Type Declaration Constraint Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Returned Type Declaration Constraint Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnedTypeDeclarationConstraintSupported()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_ReturnedTypeDeclarationConstraintSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterInitValueSupported <em>Parameter Init Value Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter Init Value Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterInitValueSupported()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_ParameterInitValueSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterStyleSupported <em>Parameter Style Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter Style Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterStyleSupported()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_ParameterStyleSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnTypeSupported <em>Return Type Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Type Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnTypeSupported()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_ReturnTypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterDeclarationConstraintSupported <em>Parameter Declaration Constraint Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter Declaration Constraint Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterDeclarationConstraintSupported()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_ParameterDeclarationConstraintSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getMaximumActionBodyLength <em>Maximum Action Body Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Action Body Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getMaximumActionBodyLength()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_MaximumActionBodyLength();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getParameterStyle <em>Parameter Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Parameter Style</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getParameterStyle()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_ParameterStyle();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getLanguageType <em>Language Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Language Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getLanguageType()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_LanguageType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getFunctionLanguageType <em>Function Language Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Function Language Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getFunctionLanguageType()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_FunctionLanguageType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getProcedureType <em>Procedure Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Procedure Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getProcedureType()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_ProcedureType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getMaximumIdentifierLength()
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	EAttribute getStoredProcedureDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition <em>Trigger Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition
	 * @generated
	 */
	EClass getTriggerDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumReferencePartLength <em>Maximum Reference Part Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Reference Part Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumReferencePartLength()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_MaximumReferencePartLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumActionBodyLength <em>Maximum Action Body Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Action Body Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumActionBodyLength()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_MaximumActionBodyLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isTypeSupported <em>Type Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isTypeSupported()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_TypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isWhenClauseSupported <em>When Clause Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>When Clause Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isWhenClauseSupported()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_WhenClauseSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isGranularitySupported <em>Granularity Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Granularity Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isGranularitySupported()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_GranularitySupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isReferencesClauseSupported <em>References Clause Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>References Clause Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isReferencesClauseSupported()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_ReferencesClauseSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isPerColumnUpdateTriggerSupported <em>Per Column Update Trigger Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Per Column Update Trigger Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isPerColumnUpdateTriggerSupported()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_PerColumnUpdateTriggerSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isInsteadOfTriggerSupported <em>Instead Of Trigger Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instead Of Trigger Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isInsteadOfTriggerSupported()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_InsteadOfTriggerSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isRowTriggerReferenceSupported <em>Row Trigger Reference Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Trigger Reference Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isRowTriggerReferenceSupported()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_RowTriggerReferenceSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isTableTriggerReferenceSupported <em>Table Trigger Reference Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table Trigger Reference Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isTableTriggerReferenceSupported()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_TableTriggerReferenceSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumIdentifierLength()
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	EAttribute getTriggerDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition <em>Column Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition
	 * @generated
	 */
	EClass getColumnDefinition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#getIdentityColumnDataTypeDefinitions <em>Identity Column Data Type Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Identity Column Data Type Definitions</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#getIdentityColumnDataTypeDefinitions()
	 * @see #getColumnDefinition()
	 * @generated
	 */
	EReference getColumnDefinition_IdentityColumnDataTypeDefinitions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentitySupported <em>Identity Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentitySupported()
	 * @see #getColumnDefinition()
	 * @generated
	 */
	EAttribute getColumnDefinition_IdentitySupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isComputedSupported <em>Computed Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Computed Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isComputedSupported()
	 * @see #getColumnDefinition()
	 * @generated
	 */
	EAttribute getColumnDefinition_ComputedSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityStartValueSupported <em>Identity Start Value Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity Start Value Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityStartValueSupported()
	 * @see #getColumnDefinition()
	 * @generated
	 */
	EAttribute getColumnDefinition_IdentityStartValueSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityIncrementSupported <em>Identity Increment Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity Increment Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityIncrementSupported()
	 * @see #getColumnDefinition()
	 * @generated
	 */
	EAttribute getColumnDefinition_IdentityIncrementSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityMinimumSupported <em>Identity Minimum Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity Minimum Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityMinimumSupported()
	 * @see #getColumnDefinition()
	 * @generated
	 */
	EAttribute getColumnDefinition_IdentityMinimumSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityMaximumSupported <em>Identity Maximum Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity Maximum Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityMaximumSupported()
	 * @see #getColumnDefinition()
	 * @generated
	 */
	EAttribute getColumnDefinition_IdentityMaximumSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityCycleSupported <em>Identity Cycle Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity Cycle Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityCycleSupported()
	 * @see #getColumnDefinition()
	 * @generated
	 */
	EAttribute getColumnDefinition_IdentityCycleSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#getMaximumIdentifierLength()
	 * @see #getColumnDefinition()
	 * @generated
	 */
	EAttribute getColumnDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition <em>Constraint Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition
	 * @generated
	 */
	EClass getConstraintDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isDeferrableConstraintSupported <em>Deferrable Constraint Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deferrable Constraint Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isDeferrableConstraintSupported()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_DeferrableConstraintSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isInformationalConstraintSupported <em>Informational Constraint Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Informational Constraint Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isInformationalConstraintSupported()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_InformationalConstraintSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isClusteredPrimaryKeySupported <em>Clustered Primary Key Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clustered Primary Key Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isClusteredPrimaryKeySupported()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_ClusteredPrimaryKeySupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isClusteredUniqueConstraintSupported <em>Clustered Unique Constraint Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clustered Unique Constraint Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isClusteredUniqueConstraintSupported()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_ClusteredUniqueConstraintSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isPrimaryKeyNullable <em>Primary Key Nullable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primary Key Nullable</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isPrimaryKeyNullable()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_PrimaryKeyNullable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isUniqueKeyNullable <em>Unique Key Nullable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique Key Nullable</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isUniqueKeyNullable()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_UniqueKeyNullable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumCheckExpressionLength <em>Maximum Check Expression Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Check Expression Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumCheckExpressionLength()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_MaximumCheckExpressionLength();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getParentUpdateDRIRuleType <em>Parent Update DRI Rule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Parent Update DRI Rule Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getParentUpdateDRIRuleType()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_ParentUpdateDRIRuleType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getParentDeleteDRIRuleType <em>Parent Delete DRI Rule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Parent Delete DRI Rule Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getParentDeleteDRIRuleType()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_ParentDeleteDRIRuleType();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getCheckOption <em>Check Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Check Option</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getCheckOption()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_CheckOption();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumPrimaryKeyIdentifierLength <em>Maximum Primary Key Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Primary Key Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumPrimaryKeyIdentifierLength()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_MaximumPrimaryKeyIdentifierLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumForeignKeyIdentifierLength <em>Maximum Foreign Key Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Foreign Key Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumForeignKeyIdentifierLength()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_MaximumForeignKeyIdentifierLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumCheckConstraintIdentifierLength <em>Maximum Check Constraint Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Check Constraint Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumCheckConstraintIdentifierLength()
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	EAttribute getConstraintDefinition_MaximumCheckConstraintIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition <em>Index Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition
	 * @generated
	 */
	EClass getIndexDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#getPercentFreeTerminology <em>Percent Free Terminology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percent Free Terminology</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#getPercentFreeTerminology()
	 * @see #getIndexDefinition()
	 * @generated
	 */
	EAttribute getIndexDefinition_PercentFreeTerminology();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isPercentFreeChangeable <em>Percent Free Changeable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percent Free Changeable</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isPercentFreeChangeable()
	 * @see #getIndexDefinition()
	 * @generated
	 */
	EAttribute getIndexDefinition_PercentFreeChangeable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isClusteringSupported <em>Clustering Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clustering Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isClusteringSupported()
	 * @see #getIndexDefinition()
	 * @generated
	 */
	EAttribute getIndexDefinition_ClusteringSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isClusterChangeable <em>Cluster Changeable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cluster Changeable</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isClusterChangeable()
	 * @see #getIndexDefinition()
	 * @generated
	 */
	EAttribute getIndexDefinition_ClusterChangeable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isFillFactorSupported <em>Fill Factor Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Factor Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isFillFactorSupported()
	 * @see #getIndexDefinition()
	 * @generated
	 */
	EAttribute getIndexDefinition_FillFactorSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isIncludedColumnsSupported <em>Included Columns Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Included Columns Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isIncludedColumnsSupported()
	 * @see #getIndexDefinition()
	 * @generated
	 */
	EAttribute getIndexDefinition_IncludedColumnsSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#getMaximumIdentifierLength()
	 * @see #getIndexDefinition()
	 * @generated
	 */
	EAttribute getIndexDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition <em>Extended Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition
	 * @generated
	 */
	EClass getExtendedDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition#getName()
	 * @see #getExtendedDefinition()
	 * @generated
	 */
	EAttribute getExtendedDefinition_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition#getValue()
	 * @see #getExtendedDefinition()
	 * @generated
	 */
	EAttribute getExtendedDefinition_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition <em>Table Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableDefinition
	 * @generated
	 */
	EClass getTableDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isAuditSupported <em>Audit Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Audit Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isAuditSupported()
	 * @see #getTableDefinition()
	 * @generated
	 */
	EAttribute getTableDefinition_AuditSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isDataCaptureSupported <em>Data Capture Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Capture Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isDataCaptureSupported()
	 * @see #getTableDefinition()
	 * @generated
	 */
	EAttribute getTableDefinition_DataCaptureSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isEditProcSupported <em>Edit Proc Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Edit Proc Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isEditProcSupported()
	 * @see #getTableDefinition()
	 * @generated
	 */
	EAttribute getTableDefinition_EditProcSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isEncodingSupported <em>Encoding Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encoding Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isEncodingSupported()
	 * @see #getTableDefinition()
	 * @generated
	 */
	EAttribute getTableDefinition_EncodingSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isValidProcSupported <em>Valid Proc Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid Proc Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isValidProcSupported()
	 * @see #getTableDefinition()
	 * @generated
	 */
	EAttribute getTableDefinition_ValidProcSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#getMaximumIdentifierLength()
	 * @see #getTableDefinition()
	 * @generated
	 */
	EAttribute getTableDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition <em>Sequence Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition
	 * @generated
	 */
	EClass getSequenceDefinition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getPredefinedDataTypeDefinitions <em>Predefined Data Type Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Predefined Data Type Definitions</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getPredefinedDataTypeDefinitions()
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	EReference getSequenceDefinition_PredefinedDataTypeDefinitions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isTypeEnumerationSupported <em>Type Enumeration Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Enumeration Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isTypeEnumerationSupported()
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	EAttribute getSequenceDefinition_TypeEnumerationSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isCacheSupported <em>Cache Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cache Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isCacheSupported()
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	EAttribute getSequenceDefinition_CacheSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isOrderSupported <em>Order Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isOrderSupported()
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	EAttribute getSequenceDefinition_OrderSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoMaximumValueString <em>No Maximum Value String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>No Maximum Value String</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoMaximumValueString()
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	EAttribute getSequenceDefinition_NoMaximumValueString();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoMinimumValueString <em>No Minimum Value String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>No Minimum Value String</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoMinimumValueString()
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	EAttribute getSequenceDefinition_NoMinimumValueString();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoCacheString <em>No Cache String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>No Cache String</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoCacheString()
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	EAttribute getSequenceDefinition_NoCacheString();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getCacheDefaultValue <em>Cache Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cache Default Value</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getCacheDefaultValue()
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	EAttribute getSequenceDefinition_CacheDefaultValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition <em>User Defined Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Defined Type Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition
	 * @generated
	 */
	EClass getUserDefinedTypeDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition#isDefaultValueSupported <em>Default Value Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition#isDefaultValueSupported()
	 * @see #getUserDefinedTypeDefinition()
	 * @generated
	 */
	EAttribute getUserDefinedTypeDefinition_DefaultValueSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition#isDistinctTypeSupported <em>Distinct Type Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distinct Type Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition#isDistinctTypeSupported()
	 * @see #getUserDefinedTypeDefinition()
	 * @generated
	 */
	EAttribute getUserDefinedTypeDefinition_DistinctTypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition#isStructuredTypeSupported <em>Structured Type Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Structured Type Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition#isStructuredTypeSupported()
	 * @see #getUserDefinedTypeDefinition()
	 * @generated
	 */
	EAttribute getUserDefinedTypeDefinition_StructuredTypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition#getMaximumIdentifierLength()
	 * @see #getUserDefinedTypeDefinition()
	 * @generated
	 */
	EAttribute getUserDefinedTypeDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition <em>Query Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition
	 * @generated
	 */
	EClass getQueryDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#getIdentifierQuoteString <em>Identifier Quote String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier Quote String</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#getIdentifierQuoteString()
	 * @see #getQueryDefinition()
	 * @generated
	 */
	EAttribute getQueryDefinition_IdentifierQuoteString();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#getHostVariableMarker <em>Host Variable Marker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host Variable Marker</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#getHostVariableMarker()
	 * @see #getQueryDefinition()
	 * @generated
	 */
	EAttribute getQueryDefinition_HostVariableMarker();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isHostVariableMarkerSupported <em>Host Variable Marker Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host Variable Marker Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isHostVariableMarkerSupported()
	 * @see #getQueryDefinition()
	 * @generated
	 */
	EAttribute getQueryDefinition_HostVariableMarkerSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isCastExpressionSupported <em>Cast Expression Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cast Expression Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isCastExpressionSupported()
	 * @see #getQueryDefinition()
	 * @generated
	 */
	EAttribute getQueryDefinition_CastExpressionSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isDefaultKeywordForInsertValueSupported <em>Default Keyword For Insert Value Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Keyword For Insert Value Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isDefaultKeywordForInsertValueSupported()
	 * @see #getQueryDefinition()
	 * @generated
	 */
	EAttribute getQueryDefinition_DefaultKeywordForInsertValueSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isExtendedGroupingSupported <em>Extended Grouping Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extended Grouping Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isExtendedGroupingSupported()
	 * @see #getQueryDefinition()
	 * @generated
	 */
	EAttribute getQueryDefinition_ExtendedGroupingSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isTableAliasInDeleteSupported <em>Table Alias In Delete Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table Alias In Delete Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition#isTableAliasInDeleteSupported()
	 * @see #getQueryDefinition()
	 * @generated
	 */
	EAttribute getQueryDefinition_TableAliasInDeleteSupported();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition <em>SQL Syntax Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Syntax Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition
	 * @generated
	 */
	EClass getSQLSyntaxDefinition();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition#getKeywords <em>Keywords</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Keywords</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition#getKeywords()
	 * @see #getSQLSyntaxDefinition()
	 * @generated
	 */
	EAttribute getSQLSyntaxDefinition_Keywords();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition#getOperators <em>Operators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Operators</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition#getOperators()
	 * @see #getSQLSyntaxDefinition()
	 * @generated
	 */
	EAttribute getSQLSyntaxDefinition_Operators();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition#getTerminationCharacter <em>Termination Character</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Termination Character</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition#getTerminationCharacter()
	 * @see #getSQLSyntaxDefinition()
	 * @generated
	 */
	EAttribute getSQLSyntaxDefinition_TerminationCharacter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition <em>Nickname Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nickname Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition
	 * @generated
	 */
	EClass getNicknameDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#isConstraintSupported <em>Constraint Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constraint Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#isConstraintSupported()
	 * @see #getNicknameDefinition()
	 * @generated
	 */
	EAttribute getNicknameDefinition_ConstraintSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#isIndexSupported <em>Index Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#isIndexSupported()
	 * @see #getNicknameDefinition()
	 * @generated
	 */
	EAttribute getNicknameDefinition_IndexSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition#getMaximumIdentifierLength()
	 * @see #getNicknameDefinition()
	 * @generated
	 */
	EAttribute getNicknameDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition <em>Schema Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schema Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition
	 * @generated
	 */
	EClass getSchemaDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition#getMaximumIdentifierLength()
	 * @see #getSchemaDefinition()
	 * @generated
	 */
	EAttribute getSchemaDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition <em>View Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition
	 * @generated
	 */
	EClass getViewDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Identifier Length</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#getMaximumIdentifierLength()
	 * @see #getViewDefinition()
	 * @generated
	 */
	EAttribute getViewDefinition_MaximumIdentifierLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isIndexSupported <em>Index Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isIndexSupported()
	 * @see #getViewDefinition()
	 * @generated
	 */
	EAttribute getViewDefinition_IndexSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isCheckOptionSupported <em>Check Option Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Check Option Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isCheckOptionSupported()
	 * @see #getViewDefinition()
	 * @generated
	 */
	EAttribute getViewDefinition_CheckOptionSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isCheckOptionLevelsSupported <em>Check Option Levels Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Check Option Levels Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition#isCheckOptionLevelsSupported()
	 * @see #getViewDefinition()
	 * @generated
	 */
	EAttribute getViewDefinition_CheckOptionLevelsSupported();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition <em>Field Qualifier Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Qualifier Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition
	 * @generated
	 */
	EClass getFieldQualifierDefinition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getValidTrailingFieldQualifierDefinitions <em>Valid Trailing Field Qualifier Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Valid Trailing Field Qualifier Definitions</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getValidTrailingFieldQualifierDefinitions()
	 * @see #getFieldQualifierDefinition()
	 * @generated
	 */
	EReference getFieldQualifierDefinition_ValidTrailingFieldQualifierDefinitions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getName()
	 * @see #getFieldQualifierDefinition()
	 * @generated
	 */
	EAttribute getFieldQualifierDefinition_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getMaximumPrecision <em>Maximum Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getMaximumPrecision()
	 * @see #getFieldQualifierDefinition()
	 * @generated
	 */
	EAttribute getFieldQualifierDefinition_MaximumPrecision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getDefaultPrecision <em>Default Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getDefaultPrecision()
	 * @see #getFieldQualifierDefinition()
	 * @generated
	 */
	EAttribute getFieldQualifierDefinition_DefaultPrecision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#isPrecisionSupported <em>Precision Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precision Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#isPrecisionSupported()
	 * @see #getFieldQualifierDefinition()
	 * @generated
	 */
	EAttribute getFieldQualifierDefinition_PrecisionSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getMaximumScale <em>Maximum Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Scale</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getMaximumScale()
	 * @see #getFieldQualifierDefinition()
	 * @generated
	 */
	EAttribute getFieldQualifierDefinition_MaximumScale();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getDefaultScale <em>Default Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Scale</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getDefaultScale()
	 * @see #getFieldQualifierDefinition()
	 * @generated
	 */
	EAttribute getFieldQualifierDefinition_DefaultScale();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#isScaleSupported <em>Scale Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#isScaleSupported()
	 * @see #getFieldQualifierDefinition()
	 * @generated
	 */
	EAttribute getFieldQualifierDefinition_ScaleSupported();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition <em>Debugger Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Debugger Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition
	 * @generated
	 */
	EClass getDebuggerDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition#isConditionSupported <em>Condition Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition#isConditionSupported()
	 * @see #getDebuggerDefinition()
	 * @generated
	 */
	EAttribute getDebuggerDefinition_ConditionSupported();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition <em>Privileged Element Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Privileged Element Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition
	 * @generated
	 */
	EClass getPrivilegedElementDefinition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition#getPrivilegeDefinitions <em>Privilege Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Privilege Definitions</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition#getPrivilegeDefinitions()
	 * @see #getPrivilegedElementDefinition()
	 * @generated
	 */
	EReference getPrivilegedElementDefinition_PrivilegeDefinitions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition#getName()
	 * @see #getPrivilegedElementDefinition()
	 * @generated
	 */
	EAttribute getPrivilegedElementDefinition_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition <em>Privilege Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Privilege Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition
	 * @generated
	 */
	EClass getPrivilegeDefinition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition#getActionElementDefinitions <em>Action Element Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Action Element Definitions</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition#getActionElementDefinitions()
	 * @see #getPrivilegeDefinition()
	 * @generated
	 */
	EReference getPrivilegeDefinition_ActionElementDefinitions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition#getName()
	 * @see #getPrivilegeDefinition()
	 * @generated
	 */
	EAttribute getPrivilegeDefinition_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition <em>Constructed Data Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constructed Data Type Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition
	 * @generated
	 */
	EClass getConstructedDataTypeDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isArrayDatatypeSupported <em>Array Datatype Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Array Datatype Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isArrayDatatypeSupported()
	 * @see #getConstructedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getConstructedDataTypeDefinition_ArrayDatatypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isMultisetDatatypeSupported <em>Multiset Datatype Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiset Datatype Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isMultisetDatatypeSupported()
	 * @see #getConstructedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getConstructedDataTypeDefinition_MultisetDatatypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isRowDatatypeSupported <em>Row Datatype Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Datatype Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isRowDatatypeSupported()
	 * @see #getConstructedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getConstructedDataTypeDefinition_RowDatatypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isReferenceDatatypeSupported <em>Reference Datatype Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference Datatype Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isReferenceDatatypeSupported()
	 * @see #getConstructedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getConstructedDataTypeDefinition_ReferenceDatatypeSupported();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isCursorDatatypeSupported <em>Cursor Datatype Supported</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cursor Datatype Supported</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition#isCursorDatatypeSupported()
	 * @see #getConstructedDataTypeDefinition()
	 * @generated
	 */
	EAttribute getConstructedDataTypeDefinition_CursorDatatypeSupported();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getDefaultDataTypeDefinition <em>Default Data Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Data Type Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getDefaultDataTypeDefinition()
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	EReference getSequenceDefinition_DefaultDataTypeDefinition();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.dbdefinition.CheckOption <em>Check Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Check Option</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.CheckOption
	 * @generated
	 */
	EEnum getCheckOption();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.dbdefinition.LanguageType <em>Language Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Language Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.LanguageType
	 * @generated
	 */
	EEnum getLanguageType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle <em>Parameter Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Parameter Style</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle
	 * @generated
	 */
	EEnum getParameterStyle();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType <em>Parent Delete DRI Rule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Parent Delete DRI Rule Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType
	 * @generated
	 */
	EEnum getParentDeleteDRIRuleType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType <em>Parent Update DRI Rule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Parent Update DRI Rule Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType
	 * @generated
	 */
	EEnum getParentUpdateDRIRuleType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.dbdefinition.ProcedureType <em>Procedure Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Procedure Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ProcedureType
	 * @generated
	 */
	EEnum getProcedureType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType <em>Table Space Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Table Space Type</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType
	 * @generated
	 */
	EEnum getTableSpaceType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology <em>Percent Free Terminology</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Percent Free Terminology</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology
	 * @generated
	 */
	EEnum getPercentFreeTerminology();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.dbdefinition.LengthUnit <em>Length Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Length Unit</em>'.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.LengthUnit
	 * @generated
	 */
	EEnum getLengthUnit();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DatabaseDefinitionFactory getDatabaseDefinitionFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl <em>Database Vendor Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getDatabaseVendorDefinition()
		 * @generated
		 */
		EClass DATABASE_VENDOR_DEFINITION = eINSTANCE.getDatabaseVendorDefinition();

		/**
		 * The meta object literal for the '<em><b>Predefined Data Type Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS = eINSTANCE.getDatabaseVendorDefinition_PredefinedDataTypeDefinitions();

		/**
		 * The meta object literal for the '<em><b>Table Space Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_TableSpaceDefinition();

		/**
		 * The meta object literal for the '<em><b>Stored Procedure Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_StoredProcedureDefinition();

		/**
		 * The meta object literal for the '<em><b>Trigger Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_TriggerDefinition();

		/**
		 * The meta object literal for the '<em><b>Column Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_ColumnDefinition();

		/**
		 * The meta object literal for the '<em><b>Constraint Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_ConstraintDefinition();

		/**
		 * The meta object literal for the '<em><b>Extended Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__EXTENDED_DEFINITIONS = eINSTANCE.getDatabaseVendorDefinition_ExtendedDefinitions();

		/**
		 * The meta object literal for the '<em><b>Index Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_IndexDefinition();

		/**
		 * The meta object literal for the '<em><b>Table Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_TableDefinition();

		/**
		 * The meta object literal for the '<em><b>Sequence Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_SequenceDefinition();

		/**
		 * The meta object literal for the '<em><b>Udt Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__UDT_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_UdtDefinition();

		/**
		 * The meta object literal for the '<em><b>Query Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_QueryDefinition();

		/**
		 * The meta object literal for the '<em><b>SQL Syntax Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_SQLSyntaxDefinition();

		/**
		 * The meta object literal for the '<em><b>Nickname Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_NicknameDefinition();

		/**
		 * The meta object literal for the '<em><b>Schema Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_SchemaDefinition();

		/**
		 * The meta object literal for the '<em><b>View Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_ViewDefinition();

		/**
		 * The meta object literal for the '<em><b>Debugger Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_DebuggerDefinition();

		/**
		 * The meta object literal for the '<em><b>Privileged Element Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__PRIVILEGED_ELEMENT_DEFINITIONS = eINSTANCE.getDatabaseVendorDefinition_PrivilegedElementDefinitions();

		/**
		 * The meta object literal for the '<em><b>Constructed Data Type Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION = eINSTANCE.getDatabaseVendorDefinition_ConstructedDataTypeDefinition();

		/**
		 * The meta object literal for the '<em><b>Vendor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__VENDOR = eINSTANCE.getDatabaseVendorDefinition_Vendor();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__VERSION = eINSTANCE.getDatabaseVendorDefinition_Version();

		/**
		 * The meta object literal for the '<em><b>Constraints Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__CONSTRAINTS_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_ConstraintsSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getDatabaseVendorDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '<em><b>Trigger Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__TRIGGER_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_TriggerSupported();

		/**
		 * The meta object literal for the '<em><b>Snapshot View Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__SNAPSHOT_VIEW_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_SnapshotViewSupported();

		/**
		 * The meta object literal for the '<em><b>Join Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__JOIN_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_JoinSupported();

		/**
		 * The meta object literal for the '<em><b>View Trigger Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__VIEW_TRIGGER_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_ViewTriggerSupported();

		/**
		 * The meta object literal for the '<em><b>Tablespaces Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__TABLESPACES_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_TablespacesSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Comment Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__MAXIMUM_COMMENT_LENGTH = eINSTANCE.getDatabaseVendorDefinition_MaximumCommentLength();

		/**
		 * The meta object literal for the '<em><b>Sequence Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__SEQUENCE_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_SequenceSupported();

		/**
		 * The meta object literal for the '<em><b>MQT Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__MQT_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_MQTSupported();

		/**
		 * The meta object literal for the '<em><b>Schema Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__SCHEMA_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_SchemaSupported();

		/**
		 * The meta object literal for the '<em><b>Alias Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__ALIAS_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_AliasSupported();

		/**
		 * The meta object literal for the '<em><b>Synonym Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__SYNONYM_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_SynonymSupported();

		/**
		 * The meta object literal for the '<em><b>User Defined Type Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__USER_DEFINED_TYPE_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_UserDefinedTypeSupported();

		/**
		 * The meta object literal for the '<em><b>Domain Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__DOMAIN_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_DomainSupported();

		/**
		 * The meta object literal for the '<em><b>SQL Statement Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__SQL_STATEMENT_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_SQLStatementSupported();

		/**
		 * The meta object literal for the '<em><b>Nickname Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__NICKNAME_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_NicknameSupported();

		/**
		 * The meta object literal for the '<em><b>Quoted DML Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__QUOTED_DML_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_QuotedDMLSupported();

		/**
		 * The meta object literal for the '<em><b>Quoted DDL Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__QUOTED_DDL_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_QuotedDDLSupported();

		/**
		 * The meta object literal for the '<em><b>Xml Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__XML_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_XmlSupported();

		/**
		 * The meta object literal for the '<em><b>MQT Index Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__MQT_INDEX_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_MQTIndexSupported();

		/**
		 * The meta object literal for the '<em><b>Event Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__EVENT_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_EventSupported();

		/**
		 * The meta object literal for the '<em><b>Sql UDF Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__SQL_UDF_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_SqlUDFSupported();

		/**
		 * The meta object literal for the '<em><b>Stored Procedure Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_StoredProcedureSupported();

		/**
		 * The meta object literal for the '<em><b>Package Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__PACKAGE_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_PackageSupported();

		/**
		 * The meta object literal for the '<em><b>Authorization Identifier Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__AUTHORIZATION_IDENTIFIER_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_AuthorizationIdentifierSupported();

		/**
		 * The meta object literal for the '<em><b>Role Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__ROLE_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_RoleSupported();

		/**
		 * The meta object literal for the '<em><b>Group Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__GROUP_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_GroupSupported();

		/**
		 * The meta object literal for the '<em><b>User Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__USER_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_UserSupported();

		/**
		 * The meta object literal for the '<em><b>Role Authorization Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__ROLE_AUTHORIZATION_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_RoleAuthorizationSupported();

		/**
		 * The meta object literal for the '<em><b>Constructed Data Type Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_ConstructedDataTypeSupported();

		/**
		 * The meta object literal for the '<em><b>UDF Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATABASE_VENDOR_DEFINITION__UDF_SUPPORTED = eINSTANCE.getDatabaseVendorDefinition_UDFSupported();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl <em>Predefined Data Type Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getPredefinedDataTypeDefinition()
		 * @generated
		 */
		EClass PREDEFINED_DATA_TYPE_DEFINITION = eINSTANCE.getPredefinedDataTypeDefinition();

		/**
		 * The meta object literal for the '<em><b>Leading Field Qualifier Definition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_DEFINITION = eINSTANCE.getPredefinedDataTypeDefinition_LeadingFieldQualifierDefinition();

		/**
		 * The meta object literal for the '<em><b>Trailing Field Qualifier Definition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_DEFINITION = eINSTANCE.getPredefinedDataTypeDefinition_TrailingFieldQualifierDefinition();

		/**
		 * The meta object literal for the '<em><b>Default Trailing Field Qualifier Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_TRAILING_FIELD_QUALIFIER_DEFINITION = eINSTANCE.getPredefinedDataTypeDefinition_DefaultTrailingFieldQualifierDefinition();

		/**
		 * The meta object literal for the '<em><b>Default Leading Field Qualifier Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LEADING_FIELD_QUALIFIER_DEFINITION = eINSTANCE.getPredefinedDataTypeDefinition_DefaultLeadingFieldQualifierDefinition();

		/**
		 * The meta object literal for the '<em><b>Length Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_LengthSupported();

		/**
		 * The meta object literal for the '<em><b>Scale Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__SCALE_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_ScaleSupported();

		/**
		 * The meta object literal for the '<em><b>Precision Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__PRECISION_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_PrecisionSupported();

		/**
		 * The meta object literal for the '<em><b>Key Constraint Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__KEY_CONSTRAINT_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_KeyConstraintSupported();

		/**
		 * The meta object literal for the '<em><b>Identity Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__IDENTITY_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_IdentitySupported();

		/**
		 * The meta object literal for the '<em><b>Multiple Columns Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__MULTIPLE_COLUMNS_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_MultipleColumnsSupported();

		/**
		 * The meta object literal for the '<em><b>Nullable Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__NULLABLE_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_NullableSupported();

		/**
		 * The meta object literal for the '<em><b>Default Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_DefaultSupported();

		/**
		 * The meta object literal for the '<em><b>Clustering Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__CLUSTERING_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_ClusteringSupported();

		/**
		 * The meta object literal for the '<em><b>Fill Factor Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__FILL_FACTOR_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_FillFactorSupported();

		/**
		 * The meta object literal for the '<em><b>Bit Data Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__BIT_DATA_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_BitDataSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_VALUE = eINSTANCE.getPredefinedDataTypeDefinition_MaximumValue();

		/**
		 * The meta object literal for the '<em><b>Minimum Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_VALUE = eINSTANCE.getPredefinedDataTypeDefinition_MinimumValue();

		/**
		 * The meta object literal for the '<em><b>Maximum Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_LENGTH = eINSTANCE.getPredefinedDataTypeDefinition_MaximumLength();

		/**
		 * The meta object literal for the '<em><b>Maximum Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_PRECISION = eINSTANCE.getPredefinedDataTypeDefinition_MaximumPrecision();

		/**
		 * The meta object literal for the '<em><b>Maximum Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_SCALE = eINSTANCE.getPredefinedDataTypeDefinition_MaximumScale();

		/**
		 * The meta object literal for the '<em><b>Minimum Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_SCALE = eINSTANCE.getPredefinedDataTypeDefinition_MinimumScale();

		/**
		 * The meta object literal for the '<em><b>Default Value Types</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_VALUE_TYPES = eINSTANCE.getPredefinedDataTypeDefinition_DefaultValueTypes();

		/**
		 * The meta object literal for the '<em><b>Primitive Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__PRIMITIVE_TYPE = eINSTANCE.getPredefinedDataTypeDefinition_PrimitiveType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__NAME = eINSTANCE.getPredefinedDataTypeDefinition_Name();

		/**
		 * The meta object literal for the '<em><b>Jdbc Enum Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__JDBC_ENUM_TYPE = eINSTANCE.getPredefinedDataTypeDefinition_JdbcEnumType();

		/**
		 * The meta object literal for the '<em><b>Character Set</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET = eINSTANCE.getPredefinedDataTypeDefinition_CharacterSet();

		/**
		 * The meta object literal for the '<em><b>Encoding Scheme</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME = eINSTANCE.getPredefinedDataTypeDefinition_EncodingScheme();

		/**
		 * The meta object literal for the '<em><b>Character Set Suffix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET_SUFFIX = eINSTANCE.getPredefinedDataTypeDefinition_CharacterSetSuffix();

		/**
		 * The meta object literal for the '<em><b>Encoding Scheme Suffix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME_SUFFIX = eINSTANCE.getPredefinedDataTypeDefinition_EncodingSchemeSuffix();

		/**
		 * The meta object literal for the '<em><b>Java Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__JAVA_CLASS_NAME = eINSTANCE.getPredefinedDataTypeDefinition_JavaClassName();

		/**
		 * The meta object literal for the '<em><b>Default Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LENGTH = eINSTANCE.getPredefinedDataTypeDefinition_DefaultLength();

		/**
		 * The meta object literal for the '<em><b>Default Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_PRECISION = eINSTANCE.getPredefinedDataTypeDefinition_DefaultPrecision();

		/**
		 * The meta object literal for the '<em><b>Default Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SCALE = eINSTANCE.getPredefinedDataTypeDefinition_DefaultScale();

		/**
		 * The meta object literal for the '<em><b>Cutoff Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__CUTOFF_PRECISION = eINSTANCE.getPredefinedDataTypeDefinition_CutoffPrecision();

		/**
		 * The meta object literal for the '<em><b>Length Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_UNIT = eINSTANCE.getPredefinedDataTypeDefinition_LengthUnit();

		/**
		 * The meta object literal for the '<em><b>Ordering Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__ORDERING_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_OrderingSupported();

		/**
		 * The meta object literal for the '<em><b>Grouping Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__GROUPING_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_GroupingSupported();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME = eINSTANCE.getPredefinedDataTypeDefinition_DisplayName();

		/**
		 * The meta object literal for the '<em><b>Display Name Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_DisplayNameSupported();

		/**
		 * The meta object literal for the '<em><b>Leading Field Qualifier Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_LeadingFieldQualifierSupported();

		/**
		 * The meta object literal for the '<em><b>Trailing Field Qualifier Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_TrailingFieldQualifierSupported();

		/**
		 * The meta object literal for the '<em><b>Field Qualifier Separator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__FIELD_QUALIFIER_SEPARATOR = eINSTANCE.getPredefinedDataTypeDefinition_FieldQualifierSeparator();

		/**
		 * The meta object literal for the '<em><b>Large Value Specifier Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_LargeValueSpecifierSupported();

		/**
		 * The meta object literal for the '<em><b>Large Value Specifier Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_NAME = eINSTANCE.getPredefinedDataTypeDefinition_LargeValueSpecifierName();

		/**
		 * The meta object literal for the '<em><b>Large Value Specifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_LENGTH = eINSTANCE.getPredefinedDataTypeDefinition_LargeValueSpecifierLength();

		/**
		 * The meta object literal for the '<em><b>Length Semantic Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC_SUPPORTED = eINSTANCE.getPredefinedDataTypeDefinition_LengthSemanticSupported();

		/**
		 * The meta object literal for the '<em><b>Length Semantic</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC = eINSTANCE.getPredefinedDataTypeDefinition_LengthSemantic();

		/**
		 * The meta object literal for the '<em><b>Language Type</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE_DEFINITION__LANGUAGE_TYPE = eINSTANCE.getPredefinedDataTypeDefinition_LanguageType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl <em>Table Space Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.TableSpaceDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getTableSpaceDefinition()
		 * @generated
		 */
		EClass TABLE_SPACE_DEFINITION = eINSTANCE.getTableSpaceDefinition();

		/**
		 * The meta object literal for the '<em><b>Type Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__TYPE_SUPPORTED = eINSTANCE.getTableSpaceDefinition_TypeSupported();

		/**
		 * The meta object literal for the '<em><b>Extent Size Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__EXTENT_SIZE_SUPPORTED = eINSTANCE.getTableSpaceDefinition_ExtentSizeSupported();

		/**
		 * The meta object literal for the '<em><b>Prefetch Size Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__PREFETCH_SIZE_SUPPORTED = eINSTANCE.getTableSpaceDefinition_PrefetchSizeSupported();

		/**
		 * The meta object literal for the '<em><b>Managed By Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__MANAGED_BY_SUPPORTED = eINSTANCE.getTableSpaceDefinition_ManagedBySupported();

		/**
		 * The meta object literal for the '<em><b>Page Size Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__PAGE_SIZE_SUPPORTED = eINSTANCE.getTableSpaceDefinition_PageSizeSupported();

		/**
		 * The meta object literal for the '<em><b>Buffer Pool Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__BUFFER_POOL_SUPPORTED = eINSTANCE.getTableSpaceDefinition_BufferPoolSupported();

		/**
		 * The meta object literal for the '<em><b>Default Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__DEFAULT_SUPPORTED = eINSTANCE.getTableSpaceDefinition_DefaultSupported();

		/**
		 * The meta object literal for the '<em><b>Container Maximum Size Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__CONTAINER_MAXIMUM_SIZE_SUPPORTED = eINSTANCE.getTableSpaceDefinition_ContainerMaximumSizeSupported();

		/**
		 * The meta object literal for the '<em><b>Container Initial Size Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__CONTAINER_INITIAL_SIZE_SUPPORTED = eINSTANCE.getTableSpaceDefinition_ContainerInitialSizeSupported();

		/**
		 * The meta object literal for the '<em><b>Container Extent Size Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__CONTAINER_EXTENT_SIZE_SUPPORTED = eINSTANCE.getTableSpaceDefinition_ContainerExtentSizeSupported();

		/**
		 * The meta object literal for the '<em><b>Table Space Type</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__TABLE_SPACE_TYPE = eINSTANCE.getTableSpaceDefinition_TableSpaceType();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_SPACE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getTableSpaceDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl <em>Stored Procedure Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getStoredProcedureDefinition()
		 * @generated
		 */
		EClass STORED_PROCEDURE_DEFINITION = eINSTANCE.getStoredProcedureDefinition();

		/**
		 * The meta object literal for the '<em><b>Predefined Data Type Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORED_PROCEDURE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS = eINSTANCE.getStoredProcedureDefinition_PredefinedDataTypeDefinitions();

		/**
		 * The meta object literal for the '<em><b>Null Input Action Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__NULL_INPUT_ACTION_SUPPORTED = eINSTANCE.getStoredProcedureDefinition_NullInputActionSupported();

		/**
		 * The meta object literal for the '<em><b>Package Generation Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__PACKAGE_GENERATION_SUPPORTED = eINSTANCE.getStoredProcedureDefinition_PackageGenerationSupported();

		/**
		 * The meta object literal for the '<em><b>Determininstic Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__DETERMININSTIC_SUPPORTED = eINSTANCE.getStoredProcedureDefinition_DetermininsticSupported();

		/**
		 * The meta object literal for the '<em><b>Returned Null Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__RETURNED_NULL_SUPPORTED = eINSTANCE.getStoredProcedureDefinition_ReturnedNullSupported();

		/**
		 * The meta object literal for the '<em><b>Returned Type Declaration Constraint Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED = eINSTANCE.getStoredProcedureDefinition_ReturnedTypeDeclarationConstraintSupported();

		/**
		 * The meta object literal for the '<em><b>Parameter Init Value Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__PARAMETER_INIT_VALUE_SUPPORTED = eINSTANCE.getStoredProcedureDefinition_ParameterInitValueSupported();

		/**
		 * The meta object literal for the '<em><b>Parameter Style Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE_SUPPORTED = eINSTANCE.getStoredProcedureDefinition_ParameterStyleSupported();

		/**
		 * The meta object literal for the '<em><b>Return Type Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__RETURN_TYPE_SUPPORTED = eINSTANCE.getStoredProcedureDefinition_ReturnTypeSupported();

		/**
		 * The meta object literal for the '<em><b>Parameter Declaration Constraint Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED = eINSTANCE.getStoredProcedureDefinition_ParameterDeclarationConstraintSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Action Body Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH = eINSTANCE.getStoredProcedureDefinition_MaximumActionBodyLength();

		/**
		 * The meta object literal for the '<em><b>Parameter Style</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE = eINSTANCE.getStoredProcedureDefinition_ParameterStyle();

		/**
		 * The meta object literal for the '<em><b>Language Type</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__LANGUAGE_TYPE = eINSTANCE.getStoredProcedureDefinition_LanguageType();

		/**
		 * The meta object literal for the '<em><b>Function Language Type</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__FUNCTION_LANGUAGE_TYPE = eINSTANCE.getStoredProcedureDefinition_FunctionLanguageType();

		/**
		 * The meta object literal for the '<em><b>Procedure Type</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__PROCEDURE_TYPE = eINSTANCE.getStoredProcedureDefinition_ProcedureType();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORED_PROCEDURE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getStoredProcedureDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl <em>Trigger Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.TriggerDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getTriggerDefinition()
		 * @generated
		 */
		EClass TRIGGER_DEFINITION = eINSTANCE.getTriggerDefinition();

		/**
		 * The meta object literal for the '<em><b>Maximum Reference Part Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__MAXIMUM_REFERENCE_PART_LENGTH = eINSTANCE.getTriggerDefinition_MaximumReferencePartLength();

		/**
		 * The meta object literal for the '<em><b>Maximum Action Body Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH = eINSTANCE.getTriggerDefinition_MaximumActionBodyLength();

		/**
		 * The meta object literal for the '<em><b>Type Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__TYPE_SUPPORTED = eINSTANCE.getTriggerDefinition_TypeSupported();

		/**
		 * The meta object literal for the '<em><b>When Clause Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__WHEN_CLAUSE_SUPPORTED = eINSTANCE.getTriggerDefinition_WhenClauseSupported();

		/**
		 * The meta object literal for the '<em><b>Granularity Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__GRANULARITY_SUPPORTED = eINSTANCE.getTriggerDefinition_GranularitySupported();

		/**
		 * The meta object literal for the '<em><b>References Clause Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__REFERENCES_CLAUSE_SUPPORTED = eINSTANCE.getTriggerDefinition_ReferencesClauseSupported();

		/**
		 * The meta object literal for the '<em><b>Per Column Update Trigger Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__PER_COLUMN_UPDATE_TRIGGER_SUPPORTED = eINSTANCE.getTriggerDefinition_PerColumnUpdateTriggerSupported();

		/**
		 * The meta object literal for the '<em><b>Instead Of Trigger Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__INSTEAD_OF_TRIGGER_SUPPORTED = eINSTANCE.getTriggerDefinition_InsteadOfTriggerSupported();

		/**
		 * The meta object literal for the '<em><b>Row Trigger Reference Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__ROW_TRIGGER_REFERENCE_SUPPORTED = eINSTANCE.getTriggerDefinition_RowTriggerReferenceSupported();

		/**
		 * The meta object literal for the '<em><b>Table Trigger Reference Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__TABLE_TRIGGER_REFERENCE_SUPPORTED = eINSTANCE.getTriggerDefinition_TableTriggerReferenceSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getTriggerDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl <em>Column Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ColumnDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getColumnDefinition()
		 * @generated
		 */
		EClass COLUMN_DEFINITION = eINSTANCE.getColumnDefinition();

		/**
		 * The meta object literal for the '<em><b>Identity Column Data Type Definitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLUMN_DEFINITION__IDENTITY_COLUMN_DATA_TYPE_DEFINITIONS = eINSTANCE.getColumnDefinition_IdentityColumnDataTypeDefinitions();

		/**
		 * The meta object literal for the '<em><b>Identity Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN_DEFINITION__IDENTITY_SUPPORTED = eINSTANCE.getColumnDefinition_IdentitySupported();

		/**
		 * The meta object literal for the '<em><b>Computed Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN_DEFINITION__COMPUTED_SUPPORTED = eINSTANCE.getColumnDefinition_ComputedSupported();

		/**
		 * The meta object literal for the '<em><b>Identity Start Value Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN_DEFINITION__IDENTITY_START_VALUE_SUPPORTED = eINSTANCE.getColumnDefinition_IdentityStartValueSupported();

		/**
		 * The meta object literal for the '<em><b>Identity Increment Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN_DEFINITION__IDENTITY_INCREMENT_SUPPORTED = eINSTANCE.getColumnDefinition_IdentityIncrementSupported();

		/**
		 * The meta object literal for the '<em><b>Identity Minimum Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN_DEFINITION__IDENTITY_MINIMUM_SUPPORTED = eINSTANCE.getColumnDefinition_IdentityMinimumSupported();

		/**
		 * The meta object literal for the '<em><b>Identity Maximum Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN_DEFINITION__IDENTITY_MAXIMUM_SUPPORTED = eINSTANCE.getColumnDefinition_IdentityMaximumSupported();

		/**
		 * The meta object literal for the '<em><b>Identity Cycle Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN_DEFINITION__IDENTITY_CYCLE_SUPPORTED = eINSTANCE.getColumnDefinition_IdentityCycleSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getColumnDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl <em>Constraint Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getConstraintDefinition()
		 * @generated
		 */
		EClass CONSTRAINT_DEFINITION = eINSTANCE.getConstraintDefinition();

		/**
		 * The meta object literal for the '<em><b>Deferrable Constraint Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__DEFERRABLE_CONSTRAINT_SUPPORTED = eINSTANCE.getConstraintDefinition_DeferrableConstraintSupported();

		/**
		 * The meta object literal for the '<em><b>Informational Constraint Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__INFORMATIONAL_CONSTRAINT_SUPPORTED = eINSTANCE.getConstraintDefinition_InformationalConstraintSupported();

		/**
		 * The meta object literal for the '<em><b>Clustered Primary Key Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__CLUSTERED_PRIMARY_KEY_SUPPORTED = eINSTANCE.getConstraintDefinition_ClusteredPrimaryKeySupported();

		/**
		 * The meta object literal for the '<em><b>Clustered Unique Constraint Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED = eINSTANCE.getConstraintDefinition_ClusteredUniqueConstraintSupported();

		/**
		 * The meta object literal for the '<em><b>Primary Key Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__PRIMARY_KEY_NULLABLE = eINSTANCE.getConstraintDefinition_PrimaryKeyNullable();

		/**
		 * The meta object literal for the '<em><b>Unique Key Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__UNIQUE_KEY_NULLABLE = eINSTANCE.getConstraintDefinition_UniqueKeyNullable();

		/**
		 * The meta object literal for the '<em><b>Maximum Check Expression Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__MAXIMUM_CHECK_EXPRESSION_LENGTH = eINSTANCE.getConstraintDefinition_MaximumCheckExpressionLength();

		/**
		 * The meta object literal for the '<em><b>Parent Update DRI Rule Type</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__PARENT_UPDATE_DRI_RULE_TYPE = eINSTANCE.getConstraintDefinition_ParentUpdateDRIRuleType();

		/**
		 * The meta object literal for the '<em><b>Parent Delete DRI Rule Type</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__PARENT_DELETE_DRI_RULE_TYPE = eINSTANCE.getConstraintDefinition_ParentDeleteDRIRuleType();

		/**
		 * The meta object literal for the '<em><b>Check Option</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__CHECK_OPTION = eINSTANCE.getConstraintDefinition_CheckOption();

		/**
		 * The meta object literal for the '<em><b>Maximum Primary Key Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH = eINSTANCE.getConstraintDefinition_MaximumPrimaryKeyIdentifierLength();

		/**
		 * The meta object literal for the '<em><b>Maximum Foreign Key Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH = eINSTANCE.getConstraintDefinition_MaximumForeignKeyIdentifierLength();

		/**
		 * The meta object literal for the '<em><b>Maximum Check Constraint Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_DEFINITION__MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH = eINSTANCE.getConstraintDefinition_MaximumCheckConstraintIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl <em>Index Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.IndexDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getIndexDefinition()
		 * @generated
		 */
		EClass INDEX_DEFINITION = eINSTANCE.getIndexDefinition();

		/**
		 * The meta object literal for the '<em><b>Percent Free Terminology</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_DEFINITION__PERCENT_FREE_TERMINOLOGY = eINSTANCE.getIndexDefinition_PercentFreeTerminology();

		/**
		 * The meta object literal for the '<em><b>Percent Free Changeable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_DEFINITION__PERCENT_FREE_CHANGEABLE = eINSTANCE.getIndexDefinition_PercentFreeChangeable();

		/**
		 * The meta object literal for the '<em><b>Clustering Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_DEFINITION__CLUSTERING_SUPPORTED = eINSTANCE.getIndexDefinition_ClusteringSupported();

		/**
		 * The meta object literal for the '<em><b>Cluster Changeable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_DEFINITION__CLUSTER_CHANGEABLE = eINSTANCE.getIndexDefinition_ClusterChangeable();

		/**
		 * The meta object literal for the '<em><b>Fill Factor Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_DEFINITION__FILL_FACTOR_SUPPORTED = eINSTANCE.getIndexDefinition_FillFactorSupported();

		/**
		 * The meta object literal for the '<em><b>Included Columns Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_DEFINITION__INCLUDED_COLUMNS_SUPPORTED = eINSTANCE.getIndexDefinition_IncludedColumnsSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INDEX_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getIndexDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ExtendedDefinitionImpl <em>Extended Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ExtendedDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getExtendedDefinition()
		 * @generated
		 */
		EClass EXTENDED_DEFINITION = eINSTANCE.getExtendedDefinition();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_DEFINITION__NAME = eINSTANCE.getExtendedDefinition_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENDED_DEFINITION__VALUE = eINSTANCE.getExtendedDefinition_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl <em>Table Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getTableDefinition()
		 * @generated
		 */
		EClass TABLE_DEFINITION = eINSTANCE.getTableDefinition();

		/**
		 * The meta object literal for the '<em><b>Audit Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_DEFINITION__AUDIT_SUPPORTED = eINSTANCE.getTableDefinition_AuditSupported();

		/**
		 * The meta object literal for the '<em><b>Data Capture Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_DEFINITION__DATA_CAPTURE_SUPPORTED = eINSTANCE.getTableDefinition_DataCaptureSupported();

		/**
		 * The meta object literal for the '<em><b>Edit Proc Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_DEFINITION__EDIT_PROC_SUPPORTED = eINSTANCE.getTableDefinition_EditProcSupported();

		/**
		 * The meta object literal for the '<em><b>Encoding Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_DEFINITION__ENCODING_SUPPORTED = eINSTANCE.getTableDefinition_EncodingSupported();

		/**
		 * The meta object literal for the '<em><b>Valid Proc Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_DEFINITION__VALID_PROC_SUPPORTED = eINSTANCE.getTableDefinition_ValidProcSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getTableDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl <em>Sequence Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.SequenceDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getSequenceDefinition()
		 * @generated
		 */
		EClass SEQUENCE_DEFINITION = eINSTANCE.getSequenceDefinition();

		/**
		 * The meta object literal for the '<em><b>Predefined Data Type Definitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEQUENCE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS = eINSTANCE.getSequenceDefinition_PredefinedDataTypeDefinitions();

		/**
		 * The meta object literal for the '<em><b>Default Data Type Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEQUENCE_DEFINITION__DEFAULT_DATA_TYPE_DEFINITION = eINSTANCE.getSequenceDefinition_DefaultDataTypeDefinition();

		/**
		 * The meta object literal for the '<em><b>Type Enumeration Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_DEFINITION__TYPE_ENUMERATION_SUPPORTED = eINSTANCE.getSequenceDefinition_TypeEnumerationSupported();

		/**
		 * The meta object literal for the '<em><b>Cache Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_DEFINITION__CACHE_SUPPORTED = eINSTANCE.getSequenceDefinition_CacheSupported();

		/**
		 * The meta object literal for the '<em><b>Order Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_DEFINITION__ORDER_SUPPORTED = eINSTANCE.getSequenceDefinition_OrderSupported();

		/**
		 * The meta object literal for the '<em><b>No Maximum Value String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_DEFINITION__NO_MAXIMUM_VALUE_STRING = eINSTANCE.getSequenceDefinition_NoMaximumValueString();

		/**
		 * The meta object literal for the '<em><b>No Minimum Value String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_DEFINITION__NO_MINIMUM_VALUE_STRING = eINSTANCE.getSequenceDefinition_NoMinimumValueString();

		/**
		 * The meta object literal for the '<em><b>No Cache String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_DEFINITION__NO_CACHE_STRING = eINSTANCE.getSequenceDefinition_NoCacheString();

		/**
		 * The meta object literal for the '<em><b>Cache Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_DEFINITION__CACHE_DEFAULT_VALUE = eINSTANCE.getSequenceDefinition_CacheDefaultValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.UserDefinedTypeDefinitionImpl <em>User Defined Type Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.UserDefinedTypeDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getUserDefinedTypeDefinition()
		 * @generated
		 */
		EClass USER_DEFINED_TYPE_DEFINITION = eINSTANCE.getUserDefinedTypeDefinition();

		/**
		 * The meta object literal for the '<em><b>Default Value Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DEFINED_TYPE_DEFINITION__DEFAULT_VALUE_SUPPORTED = eINSTANCE.getUserDefinedTypeDefinition_DefaultValueSupported();

		/**
		 * The meta object literal for the '<em><b>Distinct Type Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DEFINED_TYPE_DEFINITION__DISTINCT_TYPE_SUPPORTED = eINSTANCE.getUserDefinedTypeDefinition_DistinctTypeSupported();

		/**
		 * The meta object literal for the '<em><b>Structured Type Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DEFINED_TYPE_DEFINITION__STRUCTURED_TYPE_SUPPORTED = eINSTANCE.getUserDefinedTypeDefinition_StructuredTypeSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DEFINED_TYPE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getUserDefinedTypeDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl <em>Query Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.QueryDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getQueryDefinition()
		 * @generated
		 */
		EClass QUERY_DEFINITION = eINSTANCE.getQueryDefinition();

		/**
		 * The meta object literal for the '<em><b>Identifier Quote String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_DEFINITION__IDENTIFIER_QUOTE_STRING = eINSTANCE.getQueryDefinition_IdentifierQuoteString();

		/**
		 * The meta object literal for the '<em><b>Host Variable Marker</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_DEFINITION__HOST_VARIABLE_MARKER = eINSTANCE.getQueryDefinition_HostVariableMarker();

		/**
		 * The meta object literal for the '<em><b>Host Variable Marker Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_DEFINITION__HOST_VARIABLE_MARKER_SUPPORTED = eINSTANCE.getQueryDefinition_HostVariableMarkerSupported();

		/**
		 * The meta object literal for the '<em><b>Cast Expression Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_DEFINITION__CAST_EXPRESSION_SUPPORTED = eINSTANCE.getQueryDefinition_CastExpressionSupported();

		/**
		 * The meta object literal for the '<em><b>Default Keyword For Insert Value Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_DEFINITION__DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED = eINSTANCE.getQueryDefinition_DefaultKeywordForInsertValueSupported();

		/**
		 * The meta object literal for the '<em><b>Extended Grouping Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_DEFINITION__EXTENDED_GROUPING_SUPPORTED = eINSTANCE.getQueryDefinition_ExtendedGroupingSupported();

		/**
		 * The meta object literal for the '<em><b>Table Alias In Delete Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_DEFINITION__TABLE_ALIAS_IN_DELETE_SUPPORTED = eINSTANCE.getQueryDefinition_TableAliasInDeleteSupported();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SQLSyntaxDefinitionImpl <em>SQL Syntax Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.SQLSyntaxDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getSQLSyntaxDefinition()
		 * @generated
		 */
		EClass SQL_SYNTAX_DEFINITION = eINSTANCE.getSQLSyntaxDefinition();

		/**
		 * The meta object literal for the '<em><b>Keywords</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_SYNTAX_DEFINITION__KEYWORDS = eINSTANCE.getSQLSyntaxDefinition_Keywords();

		/**
		 * The meta object literal for the '<em><b>Operators</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_SYNTAX_DEFINITION__OPERATORS = eINSTANCE.getSQLSyntaxDefinition_Operators();

		/**
		 * The meta object literal for the '<em><b>Termination Character</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_SYNTAX_DEFINITION__TERMINATION_CHARACTER = eINSTANCE.getSQLSyntaxDefinition_TerminationCharacter();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.NicknameDefinitionImpl <em>Nickname Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.NicknameDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getNicknameDefinition()
		 * @generated
		 */
		EClass NICKNAME_DEFINITION = eINSTANCE.getNicknameDefinition();

		/**
		 * The meta object literal for the '<em><b>Constraint Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NICKNAME_DEFINITION__CONSTRAINT_SUPPORTED = eINSTANCE.getNicknameDefinition_ConstraintSupported();

		/**
		 * The meta object literal for the '<em><b>Index Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NICKNAME_DEFINITION__INDEX_SUPPORTED = eINSTANCE.getNicknameDefinition_IndexSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NICKNAME_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getNicknameDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.SchemaDefinitionImpl <em>Schema Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.SchemaDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getSchemaDefinition()
		 * @generated
		 */
		EClass SCHEMA_DEFINITION = eINSTANCE.getSchemaDefinition();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEMA_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getSchemaDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ViewDefinitionImpl <em>View Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ViewDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getViewDefinition()
		 * @generated
		 */
		EClass VIEW_DEFINITION = eINSTANCE.getViewDefinition();

		/**
		 * The meta object literal for the '<em><b>Maximum Identifier Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH = eINSTANCE.getViewDefinition_MaximumIdentifierLength();

		/**
		 * The meta object literal for the '<em><b>Index Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_DEFINITION__INDEX_SUPPORTED = eINSTANCE.getViewDefinition_IndexSupported();

		/**
		 * The meta object literal for the '<em><b>Check Option Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_DEFINITION__CHECK_OPTION_SUPPORTED = eINSTANCE.getViewDefinition_CheckOptionSupported();

		/**
		 * The meta object literal for the '<em><b>Check Option Levels Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_DEFINITION__CHECK_OPTION_LEVELS_SUPPORTED = eINSTANCE.getViewDefinition_CheckOptionLevelsSupported();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl <em>Field Qualifier Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getFieldQualifierDefinition()
		 * @generated
		 */
		EClass FIELD_QUALIFIER_DEFINITION = eINSTANCE.getFieldQualifierDefinition();

		/**
		 * The meta object literal for the '<em><b>Valid Trailing Field Qualifier Definitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_QUALIFIER_DEFINITION__VALID_TRAILING_FIELD_QUALIFIER_DEFINITIONS = eINSTANCE.getFieldQualifierDefinition_ValidTrailingFieldQualifierDefinitions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_QUALIFIER_DEFINITION__NAME = eINSTANCE.getFieldQualifierDefinition_Name();

		/**
		 * The meta object literal for the '<em><b>Maximum Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_QUALIFIER_DEFINITION__MAXIMUM_PRECISION = eINSTANCE.getFieldQualifierDefinition_MaximumPrecision();

		/**
		 * The meta object literal for the '<em><b>Default Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_QUALIFIER_DEFINITION__DEFAULT_PRECISION = eINSTANCE.getFieldQualifierDefinition_DefaultPrecision();

		/**
		 * The meta object literal for the '<em><b>Precision Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_QUALIFIER_DEFINITION__PRECISION_SUPPORTED = eINSTANCE.getFieldQualifierDefinition_PrecisionSupported();

		/**
		 * The meta object literal for the '<em><b>Maximum Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_QUALIFIER_DEFINITION__MAXIMUM_SCALE = eINSTANCE.getFieldQualifierDefinition_MaximumScale();

		/**
		 * The meta object literal for the '<em><b>Default Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_QUALIFIER_DEFINITION__DEFAULT_SCALE = eINSTANCE.getFieldQualifierDefinition_DefaultScale();

		/**
		 * The meta object literal for the '<em><b>Scale Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD_QUALIFIER_DEFINITION__SCALE_SUPPORTED = eINSTANCE.getFieldQualifierDefinition_ScaleSupported();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DebuggerDefinitionImpl <em>Debugger Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DebuggerDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getDebuggerDefinition()
		 * @generated
		 */
		EClass DEBUGGER_DEFINITION = eINSTANCE.getDebuggerDefinition();

		/**
		 * The meta object literal for the '<em><b>Condition Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEBUGGER_DEFINITION__CONDITION_SUPPORTED = eINSTANCE.getDebuggerDefinition_ConditionSupported();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegedElementDefinitionImpl <em>Privileged Element Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegedElementDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getPrivilegedElementDefinition()
		 * @generated
		 */
		EClass PRIVILEGED_ELEMENT_DEFINITION = eINSTANCE.getPrivilegedElementDefinition();

		/**
		 * The meta object literal for the '<em><b>Privilege Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIVILEGED_ELEMENT_DEFINITION__PRIVILEGE_DEFINITIONS = eINSTANCE.getPrivilegedElementDefinition_PrivilegeDefinitions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIVILEGED_ELEMENT_DEFINITION__NAME = eINSTANCE.getPrivilegedElementDefinition_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegeDefinitionImpl <em>Privilege Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.PrivilegeDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getPrivilegeDefinition()
		 * @generated
		 */
		EClass PRIVILEGE_DEFINITION = eINSTANCE.getPrivilegeDefinition();

		/**
		 * The meta object literal for the '<em><b>Action Element Definitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIVILEGE_DEFINITION__ACTION_ELEMENT_DEFINITIONS = eINSTANCE.getPrivilegeDefinition_ActionElementDefinitions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIVILEGE_DEFINITION__NAME = eINSTANCE.getPrivilegeDefinition_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstructedDataTypeDefinitionImpl <em>Constructed Data Type Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.ConstructedDataTypeDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getConstructedDataTypeDefinition()
		 * @generated
		 */
		EClass CONSTRUCTED_DATA_TYPE_DEFINITION = eINSTANCE.getConstructedDataTypeDefinition();

		/**
		 * The meta object literal for the '<em><b>Array Datatype Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRUCTED_DATA_TYPE_DEFINITION__ARRAY_DATATYPE_SUPPORTED = eINSTANCE.getConstructedDataTypeDefinition_ArrayDatatypeSupported();

		/**
		 * The meta object literal for the '<em><b>Multiset Datatype Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRUCTED_DATA_TYPE_DEFINITION__MULTISET_DATATYPE_SUPPORTED = eINSTANCE.getConstructedDataTypeDefinition_MultisetDatatypeSupported();

		/**
		 * The meta object literal for the '<em><b>Row Datatype Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRUCTED_DATA_TYPE_DEFINITION__ROW_DATATYPE_SUPPORTED = eINSTANCE.getConstructedDataTypeDefinition_RowDatatypeSupported();

		/**
		 * The meta object literal for the '<em><b>Reference Datatype Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRUCTED_DATA_TYPE_DEFINITION__REFERENCE_DATATYPE_SUPPORTED = eINSTANCE.getConstructedDataTypeDefinition_ReferenceDatatypeSupported();

		/**
		 * The meta object literal for the '<em><b>Cursor Datatype Supported</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRUCTED_DATA_TYPE_DEFINITION__CURSOR_DATATYPE_SUPPORTED = eINSTANCE.getConstructedDataTypeDefinition_CursorDatatypeSupported();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.CheckOption <em>Check Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.CheckOption
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getCheckOption()
		 * @generated
		 */
		EEnum CHECK_OPTION = eINSTANCE.getCheckOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.LanguageType <em>Language Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.LanguageType
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getLanguageType()
		 * @generated
		 */
		EEnum LANGUAGE_TYPE = eINSTANCE.getLanguageType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle <em>Parameter Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getParameterStyle()
		 * @generated
		 */
		EEnum PARAMETER_STYLE = eINSTANCE.getParameterStyle();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType <em>Parent Delete DRI Rule Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getParentDeleteDRIRuleType()
		 * @generated
		 */
		EEnum PARENT_DELETE_DRI_RULE_TYPE = eINSTANCE.getParentDeleteDRIRuleType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType <em>Parent Update DRI Rule Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getParentUpdateDRIRuleType()
		 * @generated
		 */
		EEnum PARENT_UPDATE_DRI_RULE_TYPE = eINSTANCE.getParentUpdateDRIRuleType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.ProcedureType <em>Procedure Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.ProcedureType
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getProcedureType()
		 * @generated
		 */
		EEnum PROCEDURE_TYPE = eINSTANCE.getProcedureType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType <em>Table Space Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getTableSpaceType()
		 * @generated
		 */
		EEnum TABLE_SPACE_TYPE = eINSTANCE.getTableSpaceType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology <em>Percent Free Terminology</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getPercentFreeTerminology()
		 * @generated
		 */
		EEnum PERCENT_FREE_TERMINOLOGY = eINSTANCE.getPercentFreeTerminology();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.dbdefinition.LengthUnit <em>Length Unit</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.dbdefinition.LengthUnit
		 * @see org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionPackageImpl#getLengthUnit()
		 * @generated
		 */
		EEnum LENGTH_UNIT = eINSTANCE.getLengthUnit();

	}

} //DatabaseDefinitionPackage
