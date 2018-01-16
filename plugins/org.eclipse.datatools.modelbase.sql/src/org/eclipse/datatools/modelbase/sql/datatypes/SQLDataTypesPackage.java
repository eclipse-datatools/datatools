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
package org.eclipse.datatools.modelbase.sql.datatypes;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
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
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory
 * @model kind="package"
 * @generated
 */
public interface SQLDataTypesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "datatypes"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/datatypes.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SQLDataTypes"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLDataTypesPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataTypeImpl <em>Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDataType()
	 * @generated
	 */
	int DATA_TYPE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The number of structural features of the '<em>Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_TYPE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeImpl <em>User Defined Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getUserDefinedType()
	 * @generated
	 */
	int USER_DEFINED_TYPE = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__EANNOTATIONS = DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__NAME = DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__DEPENDENCIES = DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__DESCRIPTION = DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__LABEL = DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__COMMENTS = DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__EXTENSIONS = DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__PRIVILEGES = DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__SCHEMA = DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE__ORDERING = DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>User Defined Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypeImpl <em>SQL Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getSQLDataType()
	 * @generated
	 */
	int SQL_DATA_TYPE = 22;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_TYPE__EANNOTATIONS = DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_TYPE__NAME = DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_TYPE__DEPENDENCIES = DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_TYPE__DESCRIPTION = DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_TYPE__LABEL = DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_TYPE__COMMENTS = DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_TYPE__EXTENSIONS = DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_TYPE__PRIVILEGES = DATA_TYPE__PRIVILEGES;

	/**
	 * The number of structural features of the '<em>SQL Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.PredefinedDataTypeImpl <em>Predefined Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.PredefinedDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getPredefinedDataType()
	 * @generated
	 */
	int PREDEFINED_DATA_TYPE = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE__EANNOTATIONS = SQL_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE__NAME = SQL_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE__DEPENDENCIES = SQL_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE__DESCRIPTION = SQL_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE__LABEL = SQL_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE__COMMENTS = SQL_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE__EXTENSIONS = SQL_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE__PRIVILEGES = SQL_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE = SQL_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Predefined Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDEFINED_DATA_TYPE_FEATURE_COUNT = SQL_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ConstructedDataTypeImpl <em>Constructed Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ConstructedDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getConstructedDataType()
	 * @generated
	 */
	int CONSTRUCTED_DATA_TYPE = 21;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE__EANNOTATIONS = DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE__NAME = DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE__DEPENDENCIES = DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE__DESCRIPTION = DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE__LABEL = DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE__COMMENTS = DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE__EXTENSIONS = DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE__PRIVILEGES = DATA_TYPE__PRIVILEGES;

	/**
	 * The number of structural features of the '<em>Constructed Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTED_DATA_TYPE_FEATURE_COUNT = DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CollectionDataTypeImpl <em>Collection Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.CollectionDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getCollectionDataType()
	 * @generated
	 */
	int COLLECTION_DATA_TYPE = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE__EANNOTATIONS = CONSTRUCTED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE__NAME = CONSTRUCTED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE__DEPENDENCIES = CONSTRUCTED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE__DESCRIPTION = CONSTRUCTED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE__LABEL = CONSTRUCTED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE__COMMENTS = CONSTRUCTED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE__EXTENSIONS = CONSTRUCTED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE__PRIVILEGES = CONSTRUCTED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE__ELEMENT_TYPE = CONSTRUCTED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Collection Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_DATA_TYPE_FEATURE_COUNT = CONSTRUCTED_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.NumericalDataTypeImpl <em>Numerical Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.NumericalDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getNumericalDataType()
	 * @generated
	 */
	int NUMERICAL_DATA_TYPE = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__EANNOTATIONS = PREDEFINED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__NAME = PREDEFINED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__DEPENDENCIES = PREDEFINED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__DESCRIPTION = PREDEFINED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__LABEL = PREDEFINED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__COMMENTS = PREDEFINED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__EXTENSIONS = PREDEFINED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__PRIVILEGES = PREDEFINED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__PRIMITIVE_TYPE = PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE__PRECISION = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Numerical Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERICAL_DATA_TYPE_FEATURE_COUNT = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterStringDataTypeImpl <em>Character String Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterStringDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getCharacterStringDataType()
	 * @generated
	 */
	int CHARACTER_STRING_DATA_TYPE = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__EANNOTATIONS = PREDEFINED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__NAME = PREDEFINED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__DEPENDENCIES = PREDEFINED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__DESCRIPTION = PREDEFINED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__LABEL = PREDEFINED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__COMMENTS = PREDEFINED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__EXTENSIONS = PREDEFINED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__PRIVILEGES = PREDEFINED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__PRIMITIVE_TYPE = PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__LENGTH = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Coercibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__COERCIBILITY = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fixed Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__FIXED_LENGTH = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Collation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__COLLATION_NAME = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Character Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE__CHARACTER_SET = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Character String Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_STRING_DATA_TYPE_FEATURE_COUNT = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.RowDataTypeImpl <em>Row Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.RowDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getRowDataType()
	 * @generated
	 */
	int ROW_DATA_TYPE = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE__EANNOTATIONS = CONSTRUCTED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE__NAME = CONSTRUCTED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE__DEPENDENCIES = CONSTRUCTED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE__DESCRIPTION = CONSTRUCTED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE__LABEL = CONSTRUCTED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE__COMMENTS = CONSTRUCTED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE__EXTENSIONS = CONSTRUCTED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE__PRIVILEGES = CONSTRUCTED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE__FIELDS = CONSTRUCTED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Row Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_DATA_TYPE_FEATURE_COUNT = CONSTRUCTED_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ArrayDataTypeImpl <em>Array Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ArrayDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getArrayDataType()
	 * @generated
	 */
	int ARRAY_DATA_TYPE = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__EANNOTATIONS = COLLECTION_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__NAME = COLLECTION_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__DEPENDENCIES = COLLECTION_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__DESCRIPTION = COLLECTION_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__LABEL = COLLECTION_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__COMMENTS = COLLECTION_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__EXTENSIONS = COLLECTION_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__PRIVILEGES = COLLECTION_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__ELEMENT_TYPE = COLLECTION_DATA_TYPE__ELEMENT_TYPE;

	/**
	 * The feature id for the '<em><b>Max Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE__MAX_CARDINALITY = COLLECTION_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DATA_TYPE_FEATURE_COUNT = COLLECTION_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.MultisetDataTypeImpl <em>Multiset Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.MultisetDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getMultisetDataType()
	 * @generated
	 */
	int MULTISET_DATA_TYPE = 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE__EANNOTATIONS = COLLECTION_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE__NAME = COLLECTION_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE__DEPENDENCIES = COLLECTION_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE__DESCRIPTION = COLLECTION_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE__LABEL = COLLECTION_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE__COMMENTS = COLLECTION_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE__EXTENSIONS = COLLECTION_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE__PRIVILEGES = COLLECTION_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE__ELEMENT_TYPE = COLLECTION_DATA_TYPE__ELEMENT_TYPE;

	/**
	 * The number of structural features of the '<em>Multiset Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTISET_DATA_TYPE_FEATURE_COUNT = COLLECTION_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.BooleanDataTypeImpl <em>Boolean Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.BooleanDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getBooleanDataType()
	 * @generated
	 */
	int BOOLEAN_DATA_TYPE = 9;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE__EANNOTATIONS = PREDEFINED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE__NAME = PREDEFINED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE__DEPENDENCIES = PREDEFINED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE__DESCRIPTION = PREDEFINED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE__LABEL = PREDEFINED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE__COMMENTS = PREDEFINED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE__EXTENSIONS = PREDEFINED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE__PRIVILEGES = PREDEFINED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE__PRIMITIVE_TYPE = PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The number of structural features of the '<em>Boolean Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_DATA_TYPE_FEATURE_COUNT = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.IntervalDataTypeImpl <em>Interval Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.IntervalDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getIntervalDataType()
	 * @generated
	 */
	int INTERVAL_DATA_TYPE = 10;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__EANNOTATIONS = PREDEFINED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__NAME = PREDEFINED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__DEPENDENCIES = PREDEFINED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__DESCRIPTION = PREDEFINED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__LABEL = PREDEFINED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__COMMENTS = PREDEFINED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__EXTENSIONS = PREDEFINED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__PRIVILEGES = PREDEFINED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__PRIMITIVE_TYPE = PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Leading Qualifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__LEADING_QUALIFIER = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Trailing Qualifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__TRAILING_QUALIFIER = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Leading Field Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__LEADING_FIELD_PRECISION = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Trailing Field Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__TRAILING_FIELD_PRECISION = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Fractional Seconds Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Interval Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_DATA_TYPE_FEATURE_COUNT = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.BinaryStringDataTypeImpl <em>Binary String Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.BinaryStringDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getBinaryStringDataType()
	 * @generated
	 */
	int BINARY_STRING_DATA_TYPE = 11;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__EANNOTATIONS = PREDEFINED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__NAME = PREDEFINED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__DEPENDENCIES = PREDEFINED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__DESCRIPTION = PREDEFINED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__LABEL = PREDEFINED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__COMMENTS = PREDEFINED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__EXTENSIONS = PREDEFINED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__PRIVILEGES = PREDEFINED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__PRIMITIVE_TYPE = PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE__LENGTH = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Binary String Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_STRING_DATA_TYPE_FEATURE_COUNT = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterSetImpl <em>Character Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterSetImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getCharacterSet()
	 * @generated
	 */
	int CHARACTER_SET = 12;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Repertoire</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__REPERTOIRE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Collation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__DEFAULT_COLLATION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__ENCODING = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Character String Data Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__CHARACTER_STRING_DATA_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Character Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_SET_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.TimeDataTypeImpl <em>Time Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.TimeDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getTimeDataType()
	 * @generated
	 */
	int TIME_DATA_TYPE = 13;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__EANNOTATIONS = PREDEFINED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__NAME = PREDEFINED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__DEPENDENCIES = PREDEFINED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__DESCRIPTION = PREDEFINED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__LABEL = PREDEFINED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__COMMENTS = PREDEFINED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__EXTENSIONS = PREDEFINED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__PRIVILEGES = PREDEFINED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__PRIMITIVE_TYPE = PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Fractional Seconds Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Time Zone</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE__TIME_ZONE = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Time Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_DATA_TYPE_FEATURE_COUNT = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DistinctUserDefinedTypeImpl <em>Distinct User Defined Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DistinctUserDefinedTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDistinctUserDefinedType()
	 * @generated
	 */
	int DISTINCT_USER_DEFINED_TYPE = 14;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS = USER_DEFINED_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__NAME = USER_DEFINED_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES = USER_DEFINED_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__DESCRIPTION = USER_DEFINED_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__LABEL = USER_DEFINED_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__COMMENTS = USER_DEFINED_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__EXTENSIONS = USER_DEFINED_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__PRIVILEGES = USER_DEFINED_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__SCHEMA = USER_DEFINED_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__ORDERING = USER_DEFINED_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Predefined Representation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION = USER_DEFINED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Distinct User Defined Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT = USER_DEFINED_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl <em>Structured User Defined Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getStructuredUserDefinedType()
	 * @generated
	 */
	int STRUCTURED_USER_DEFINED_TYPE = 15;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__EANNOTATIONS = USER_DEFINED_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__NAME = USER_DEFINED_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__DEPENDENCIES = USER_DEFINED_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__DESCRIPTION = USER_DEFINED_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__LABEL = USER_DEFINED_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__COMMENTS = USER_DEFINED_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__EXTENSIONS = USER_DEFINED_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__PRIVILEGES = USER_DEFINED_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__SCHEMA = USER_DEFINED_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__ORDERING = USER_DEFINED_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Instantiable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__INSTANTIABLE = USER_DEFINED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__FINAL = USER_DEFINED_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Super</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__SUPER = USER_DEFINED_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__SUB = USER_DEFINED_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES = USER_DEFINED_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE__METHODS = USER_DEFINED_TYPE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Structured User Defined Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURED_USER_DEFINED_TYPE_FEATURE_COUNT = USER_DEFINED_TYPE_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.AttributeDefinitionImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getAttributeDefinition()
	 * @generated
	 */
	int ATTRIBUTE_DEFINITION = 16;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__EANNOTATIONS = SQLSchemaPackage.TYPED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__NAME = SQLSchemaPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__DEPENDENCIES = SQLSchemaPackage.TYPED_ELEMENT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__DESCRIPTION = SQLSchemaPackage.TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__LABEL = SQLSchemaPackage.TYPED_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__COMMENTS = SQLSchemaPackage.TYPED_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__EXTENSIONS = SQLSchemaPackage.TYPED_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__PRIVILEGES = SQLSchemaPackage.TYPED_ELEMENT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__CONTAINED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__REFERENCED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Scope Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__SCOPE_CHECK = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__SCOPE_CHECKED = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION__DEFAULT_VALUE = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Attribute Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_DEFINITION_FEATURE_COUNT = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ExactNumericDataTypeImpl <em>Exact Numeric Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ExactNumericDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getExactNumericDataType()
	 * @generated
	 */
	int EXACT_NUMERIC_DATA_TYPE = 26;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__EANNOTATIONS = NUMERICAL_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__NAME = NUMERICAL_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__DEPENDENCIES = NUMERICAL_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__DESCRIPTION = NUMERICAL_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__LABEL = NUMERICAL_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__COMMENTS = NUMERICAL_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__EXTENSIONS = NUMERICAL_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__PRIVILEGES = NUMERICAL_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__PRIMITIVE_TYPE = NUMERICAL_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__PRECISION = NUMERICAL_DATA_TYPE__PRECISION;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE__SCALE = NUMERICAL_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exact Numeric Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXACT_NUMERIC_DATA_TYPE_FEATURE_COUNT = NUMERICAL_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.FixedPrecisionDataTypeImpl <em>Fixed Precision Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.FixedPrecisionDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getFixedPrecisionDataType()
	 * @generated
	 */
	int FIXED_PRECISION_DATA_TYPE = 17;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__EANNOTATIONS = EXACT_NUMERIC_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__NAME = EXACT_NUMERIC_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__DEPENDENCIES = EXACT_NUMERIC_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__DESCRIPTION = EXACT_NUMERIC_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__LABEL = EXACT_NUMERIC_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__COMMENTS = EXACT_NUMERIC_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__EXTENSIONS = EXACT_NUMERIC_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__PRIVILEGES = EXACT_NUMERIC_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__PRIMITIVE_TYPE = EXACT_NUMERIC_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__PRECISION = EXACT_NUMERIC_DATA_TYPE__PRECISION;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE__SCALE = EXACT_NUMERIC_DATA_TYPE__SCALE;

	/**
	 * The number of structural features of the '<em>Fixed Precision Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PRECISION_DATA_TYPE_FEATURE_COUNT = EXACT_NUMERIC_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DomainImpl <em>Domain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DomainImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDomain()
	 * @generated
	 */
	int DOMAIN = 18;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__EANNOTATIONS = DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__NAME = DISTINCT_USER_DEFINED_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__DEPENDENCIES = DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__DESCRIPTION = DISTINCT_USER_DEFINED_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__LABEL = DISTINCT_USER_DEFINED_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__COMMENTS = DISTINCT_USER_DEFINED_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__EXTENSIONS = DISTINCT_USER_DEFINED_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__PRIVILEGES = DISTINCT_USER_DEFINED_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__SCHEMA = DISTINCT_USER_DEFINED_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__ORDERING = DISTINCT_USER_DEFINED_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Predefined Representation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__PREDEFINED_REPRESENTATION = DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__CONSTRAINT = DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN__DEFAULT_VALUE = DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Domain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_FEATURE_COUNT = DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.FieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.FieldImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getField()
	 * @generated
	 */
	int FIELD = 19;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__EANNOTATIONS = SQLSchemaPackage.TYPED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__NAME = SQLSchemaPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__DEPENDENCIES = SQLSchemaPackage.TYPED_ELEMENT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__DESCRIPTION = SQLSchemaPackage.TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__LABEL = SQLSchemaPackage.TYPED_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__COMMENTS = SQLSchemaPackage.TYPED_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__EXTENSIONS = SQLSchemaPackage.TYPED_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__PRIVILEGES = SQLSchemaPackage.TYPED_ELEMENT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__CONTAINED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__REFERENCED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Scope Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__SCOPE_CHECK = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__SCOPE_CHECKED = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_FEATURE_COUNT = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ReferenceDataTypeImpl <em>Reference Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ReferenceDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getReferenceDataType()
	 * @generated
	 */
	int REFERENCE_DATA_TYPE = 20;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__EANNOTATIONS = CONSTRUCTED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__NAME = CONSTRUCTED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__DEPENDENCIES = CONSTRUCTED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__DESCRIPTION = CONSTRUCTED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__LABEL = CONSTRUCTED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__COMMENTS = CONSTRUCTED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__EXTENSIONS = CONSTRUCTED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__PRIVILEGES = CONSTRUCTED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Scope Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__SCOPE_TABLE = CONSTRUCTED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE__REFERENCED_TYPE = CONSTRUCTED_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Reference Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_DATA_TYPE_FEATURE_COUNT = CONSTRUCTED_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl <em>Data Link Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDataLinkDataType()
	 * @generated
	 */
	int DATA_LINK_DATA_TYPE = 23;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__EANNOTATIONS = PREDEFINED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__NAME = PREDEFINED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__DEPENDENCIES = PREDEFINED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__DESCRIPTION = PREDEFINED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__LABEL = PREDEFINED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__COMMENTS = PREDEFINED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__EXTENSIONS = PREDEFINED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__PRIVILEGES = PREDEFINED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__PRIMITIVE_TYPE = PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__LENGTH = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Link Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__LINK_CONTROL = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Integrity Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__INTEGRITY_CONTROL = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Read Permission</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__READ_PERMISSION = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Write Permission</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__WRITE_PERMISSION = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Recovery</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__RECOVERY = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Unlink</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE__UNLINK = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Data Link Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_LINK_DATA_TYPE_FEATURE_COUNT = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeOrderingImpl <em>User Defined Type Ordering</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeOrderingImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getUserDefinedTypeOrdering()
	 * @generated
	 */
	int USER_DEFINED_TYPE_ORDERING = 24;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Ordering Form</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__ORDERING_FORM = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ordering Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__ORDERING_CATEGORY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ordering Routine</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING__ORDERING_ROUTINE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>User Defined Type Ordering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_TYPE_ORDERING_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DateDataTypeImpl <em>Date Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DateDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDateDataType()
	 * @generated
	 */
	int DATE_DATA_TYPE = 25;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE__EANNOTATIONS = PREDEFINED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE__NAME = PREDEFINED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE__DEPENDENCIES = PREDEFINED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE__DESCRIPTION = PREDEFINED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE__LABEL = PREDEFINED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE__COMMENTS = PREDEFINED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE__EXTENSIONS = PREDEFINED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE__PRIVILEGES = PREDEFINED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE__PRIMITIVE_TYPE = PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The number of structural features of the '<em>Date Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_DATA_TYPE_FEATURE_COUNT = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ApproximateNumericDataTypeImpl <em>Approximate Numeric Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ApproximateNumericDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getApproximateNumericDataType()
	 * @generated
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE = 27;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__EANNOTATIONS = NUMERICAL_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__NAME = NUMERICAL_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__DEPENDENCIES = NUMERICAL_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__DESCRIPTION = NUMERICAL_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__LABEL = NUMERICAL_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__COMMENTS = NUMERICAL_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__EXTENSIONS = NUMERICAL_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__PRIVILEGES = NUMERICAL_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__PRIMITIVE_TYPE = NUMERICAL_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE__PRECISION = NUMERICAL_DATA_TYPE__PRECISION;

	/**
	 * The number of structural features of the '<em>Approximate Numeric Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPROXIMATE_NUMERIC_DATA_TYPE_FEATURE_COUNT = NUMERICAL_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.IntegerDataTypeImpl <em>Integer Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.IntegerDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getIntegerDataType()
	 * @generated
	 */
	int INTEGER_DATA_TYPE = 28;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__EANNOTATIONS = EXACT_NUMERIC_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__NAME = EXACT_NUMERIC_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__DEPENDENCIES = EXACT_NUMERIC_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__DESCRIPTION = EXACT_NUMERIC_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__LABEL = EXACT_NUMERIC_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__COMMENTS = EXACT_NUMERIC_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__EXTENSIONS = EXACT_NUMERIC_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__PRIVILEGES = EXACT_NUMERIC_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__PRIMITIVE_TYPE = EXACT_NUMERIC_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__PRECISION = EXACT_NUMERIC_DATA_TYPE__PRECISION;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE__SCALE = EXACT_NUMERIC_DATA_TYPE__SCALE;

	/**
	 * The number of structural features of the '<em>Integer Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_DATA_TYPE_FEATURE_COUNT = EXACT_NUMERIC_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.XMLDataTypeImpl <em>XML Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.XMLDataTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getXMLDataType()
	 * @generated
	 */
	int XML_DATA_TYPE = 29;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE__EANNOTATIONS = PREDEFINED_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE__NAME = PREDEFINED_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE__DEPENDENCIES = PREDEFINED_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE__DESCRIPTION = PREDEFINED_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE__LABEL = PREDEFINED_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE__COMMENTS = PREDEFINED_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE__EXTENSIONS = PREDEFINED_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE__PRIVILEGES = PREDEFINED_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE__PRIMITIVE_TYPE = PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

	/**
	 * The number of structural features of the '<em>XML Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XML_DATA_TYPE_FEATURE_COUNT = PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ElementTypeImpl <em>Element Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ElementTypeImpl
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getElementType()
	 * @generated
	 */
	int ELEMENT_TYPE = 30;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__EANNOTATIONS = SQLSchemaPackage.TYPED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__NAME = SQLSchemaPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__DEPENDENCIES = SQLSchemaPackage.TYPED_ELEMENT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__DESCRIPTION = SQLSchemaPackage.TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__LABEL = SQLSchemaPackage.TYPED_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__COMMENTS = SQLSchemaPackage.TYPED_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__EXTENSIONS = SQLSchemaPackage.TYPED_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__PRIVILEGES = SQLSchemaPackage.TYPED_ELEMENT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__CONTAINED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__REFERENCED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Data Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE__COLLECTION_DATA_TYPE = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Element Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_TYPE_FEATURE_COUNT = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType <em>Coercibility Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getCoercibilityType()
	 * @generated
	 */
	int COERCIBILITY_TYPE = 31;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType <em>Interval Qualifier Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getIntervalQualifierType()
	 * @generated
	 */
	int INTERVAL_QUALIFIER_TYPE = 32;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.OrderingType <em>Ordering Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingType
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getOrderingType()
	 * @generated
	 */
	int ORDERING_TYPE = 33;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType <em>Ordering Category Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getOrderingCategoryType()
	 * @generated
	 */
	int ORDERING_CATEGORY_TYPE = 34;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType <em>Primitive Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getPrimitiveType()
	 * @generated
	 */
	int PRIMITIVE_TYPE = 35;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption <em>Link Control Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getLinkControlOption()
	 * @generated
	 */
	int LINK_CONTROL_OPTION = 36;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption <em>Integrity Control Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getIntegrityControlOption()
	 * @generated
	 */
	int INTEGRITY_CONTROL_OPTION = 37;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption <em>Read Permission Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getReadPermissionOption()
	 * @generated
	 */
	int READ_PERMISSION_OPTION = 38;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption <em>Write Permission Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getWritePermissionOption()
	 * @generated
	 */
	int WRITE_PERMISSION_OPTION = 39;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption <em>Unlink Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getUnlinkOption()
	 * @generated
	 */
	int UNLINK_OPTION = 40;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType <em>User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Defined Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType
	 * @generated
	 */
	EClass getUserDefinedType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getSchema()
	 * @see #getUserDefinedType()
	 * @generated
	 */
	EReference getUserDefinedType_Schema();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getOrdering <em>Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ordering</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getOrdering()
	 * @see #getUserDefinedType()
	 * @generated
	 */
	EReference getUserDefinedType_Ordering();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataType
	 * @generated
	 */
	EClass getDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType <em>Predefined Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Predefined Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType
	 * @generated
	 */
	EClass getPredefinedDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType#getPrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primitive Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType#getPrimitiveType()
	 * @see #getPredefinedDataType()
	 * @generated
	 */
	EAttribute getPredefinedDataType_PrimitiveType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType <em>Collection Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType
	 * @generated
	 */
	EClass getCollectionDataType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Element Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType#getElementType()
	 * @see #getCollectionDataType()
	 * @generated
	 */
	EReference getCollectionDataType_ElementType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType <em>Numerical Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Numerical Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType
	 * @generated
	 */
	EClass getNumericalDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType#getPrecision <em>Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType#getPrecision()
	 * @see #getNumericalDataType()
	 * @generated
	 */
	EAttribute getNumericalDataType_Precision();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType <em>Character String Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Character String Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType
	 * @generated
	 */
	EClass getCharacterStringDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getLength()
	 * @see #getCharacterStringDataType()
	 * @generated
	 */
	EAttribute getCharacterStringDataType_Length();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCoercibility <em>Coercibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coercibility</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCoercibility()
	 * @see #getCharacterStringDataType()
	 * @generated
	 */
	EAttribute getCharacterStringDataType_Coercibility();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#isFixedLength <em>Fixed Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fixed Length</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#isFixedLength()
	 * @see #getCharacterStringDataType()
	 * @generated
	 */
	EAttribute getCharacterStringDataType_FixedLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCollationName <em>Collation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collation Name</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCollationName()
	 * @see #getCharacterStringDataType()
	 * @generated
	 */
	EAttribute getCharacterStringDataType_CollationName();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCharacterSet <em>Character Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Character Set</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCharacterSet()
	 * @see #getCharacterStringDataType()
	 * @generated
	 */
	EReference getCharacterStringDataType_CharacterSet();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.RowDataType <em>Row Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Row Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.RowDataType
	 * @generated
	 */
	EClass getRowDataType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.datatypes.RowDataType#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.RowDataType#getFields()
	 * @see #getRowDataType()
	 * @generated
	 */
	EReference getRowDataType_Fields();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType <em>Array Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType
	 * @generated
	 */
	EClass getArrayDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType#getMaxCardinality <em>Max Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Cardinality</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType#getMaxCardinality()
	 * @see #getArrayDataType()
	 * @generated
	 */
	EAttribute getArrayDataType_MaxCardinality();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.MultisetDataType <em>Multiset Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multiset Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.MultisetDataType
	 * @generated
	 */
	EClass getMultisetDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.BooleanDataType <em>Boolean Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.BooleanDataType
	 * @generated
	 */
	EClass getBooleanDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType <em>Interval Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interval Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType
	 * @generated
	 */
	EClass getIntervalDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingQualifier <em>Leading Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Leading Qualifier</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingQualifier()
	 * @see #getIntervalDataType()
	 * @generated
	 */
	EAttribute getIntervalDataType_LeadingQualifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingQualifier <em>Trailing Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trailing Qualifier</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingQualifier()
	 * @see #getIntervalDataType()
	 * @generated
	 */
	EAttribute getIntervalDataType_TrailingQualifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingFieldPrecision <em>Leading Field Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Leading Field Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingFieldPrecision()
	 * @see #getIntervalDataType()
	 * @generated
	 */
	EAttribute getIntervalDataType_LeadingFieldPrecision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingFieldPrecision <em>Trailing Field Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trailing Field Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingFieldPrecision()
	 * @see #getIntervalDataType()
	 * @generated
	 */
	EAttribute getIntervalDataType_TrailingFieldPrecision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fractional Seconds Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getFractionalSecondsPrecision()
	 * @see #getIntervalDataType()
	 * @generated
	 */
	EAttribute getIntervalDataType_FractionalSecondsPrecision();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType <em>Binary String Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary String Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType
	 * @generated
	 */
	EClass getBinaryStringDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType#getLength()
	 * @see #getBinaryStringDataType()
	 * @generated
	 */
	EAttribute getBinaryStringDataType_Length();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet <em>Character Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Character Set</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet
	 * @generated
	 */
	EClass getCharacterSet();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getRepertoire <em>Repertoire</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repertoire</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getRepertoire()
	 * @see #getCharacterSet()
	 * @generated
	 */
	EAttribute getCharacterSet_Repertoire();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getDefaultCollation <em>Default Collation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Collation</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getDefaultCollation()
	 * @see #getCharacterSet()
	 * @generated
	 */
	EAttribute getCharacterSet_DefaultCollation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getEncoding <em>Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encoding</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getEncoding()
	 * @see #getCharacterSet()
	 * @generated
	 */
	EAttribute getCharacterSet_Encoding();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getCharacterStringDataType <em>Character String Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Character String Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getCharacterStringDataType()
	 * @see #getCharacterSet()
	 * @generated
	 */
	EReference getCharacterSet_CharacterStringDataType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getSchema()
	 * @see #getCharacterSet()
	 * @generated
	 */
	EReference getCharacterSet_Schema();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType <em>Time Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType
	 * @generated
	 */
	EClass getTimeDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fractional Seconds Precision</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType#getFractionalSecondsPrecision()
	 * @see #getTimeDataType()
	 * @generated
	 */
	EAttribute getTimeDataType_FractionalSecondsPrecision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType#isTimeZone <em>Time Zone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Zone</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType#isTimeZone()
	 * @see #getTimeDataType()
	 * @generated
	 */
	EAttribute getTimeDataType_TimeZone();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType <em>Distinct User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Distinct User Defined Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType
	 * @generated
	 */
	EClass getDistinctUserDefinedType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType#getPredefinedRepresentation <em>Predefined Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Predefined Representation</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType#getPredefinedRepresentation()
	 * @see #getDistinctUserDefinedType()
	 * @generated
	 */
	EReference getDistinctUserDefinedType_PredefinedRepresentation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType <em>Structured User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Structured User Defined Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType
	 * @generated
	 */
	EClass getStructuredUserDefinedType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#isInstantiable <em>Instantiable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instantiable</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#isInstantiable()
	 * @see #getStructuredUserDefinedType()
	 * @generated
	 */
	EAttribute getStructuredUserDefinedType_Instantiable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#isFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#isFinal()
	 * @see #getStructuredUserDefinedType()
	 * @generated
	 */
	EAttribute getStructuredUserDefinedType_Final();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSuper <em>Super</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Super</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSuper()
	 * @see #getStructuredUserDefinedType()
	 * @generated
	 */
	EReference getStructuredUserDefinedType_Super();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSub <em>Sub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSub()
	 * @see #getStructuredUserDefinedType()
	 * @generated
	 */
	EReference getStructuredUserDefinedType_Sub();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getAttributes()
	 * @see #getStructuredUserDefinedType()
	 * @generated
	 */
	EReference getStructuredUserDefinedType_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Methods</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getMethods()
	 * @see #getStructuredUserDefinedType()
	 * @generated
	 */
	EReference getStructuredUserDefinedType_Methods();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition <em>Attribute Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Definition</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition
	 * @generated
	 */
	EClass getAttributeDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#getScopeCheck <em>Scope Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope Check</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#getScopeCheck()
	 * @see #getAttributeDefinition()
	 * @generated
	 */
	EAttribute getAttributeDefinition_ScopeCheck();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#isScopeChecked <em>Scope Checked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope Checked</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#isScopeChecked()
	 * @see #getAttributeDefinition()
	 * @generated
	 */
	EAttribute getAttributeDefinition_ScopeChecked();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#getDefaultValue()
	 * @see #getAttributeDefinition()
	 * @generated
	 */
	EAttribute getAttributeDefinition_DefaultValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType <em>Fixed Precision Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fixed Precision Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType
	 * @generated
	 */
	EClass getFixedPrecisionDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.Domain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.Domain
	 * @generated
	 */
	EClass getDomain();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.datatypes.Domain#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraint</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.Domain#getConstraint()
	 * @see #getDomain()
	 * @generated
	 */
	EReference getDomain_Constraint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.Domain#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.Domain#getDefaultValue()
	 * @see #getDomain()
	 * @generated
	 */
	EAttribute getDomain_DefaultValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.Field
	 * @generated
	 */
	EClass getField();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.Field#getScopeCheck <em>Scope Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope Check</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.Field#getScopeCheck()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_ScopeCheck();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.Field#isScopeChecked <em>Scope Checked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope Checked</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.Field#isScopeChecked()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_ScopeChecked();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType <em>Reference Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType
	 * @generated
	 */
	EClass getReferenceDataType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType#getScopeTable <em>Scope Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Scope Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType#getScopeTable()
	 * @see #getReferenceDataType()
	 * @generated
	 */
	EReference getReferenceDataType_ScopeTable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType#getReferencedType <em>Referenced Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType#getReferencedType()
	 * @see #getReferenceDataType()
	 * @generated
	 */
	EReference getReferenceDataType_ReferencedType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.ConstructedDataType <em>Constructed Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constructed Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ConstructedDataType
	 * @generated
	 */
	EClass getConstructedDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType <em>SQL Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType
	 * @generated
	 */
	EClass getSQLDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType <em>Data Link Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Link Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType
	 * @generated
	 */
	EClass getDataLinkDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getLength()
	 * @see #getDataLinkDataType()
	 * @generated
	 */
	EAttribute getDataLinkDataType_Length();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getLinkControl <em>Link Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link Control</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getLinkControl()
	 * @see #getDataLinkDataType()
	 * @generated
	 */
	EAttribute getDataLinkDataType_LinkControl();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getIntegrityControl <em>Integrity Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integrity Control</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getIntegrityControl()
	 * @see #getDataLinkDataType()
	 * @generated
	 */
	EAttribute getDataLinkDataType_IntegrityControl();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getReadPermission <em>Read Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Read Permission</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getReadPermission()
	 * @see #getDataLinkDataType()
	 * @generated
	 */
	EAttribute getDataLinkDataType_ReadPermission();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getWritePermission <em>Write Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Write Permission</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getWritePermission()
	 * @see #getDataLinkDataType()
	 * @generated
	 */
	EAttribute getDataLinkDataType_WritePermission();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#isRecovery <em>Recovery</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recovery</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#isRecovery()
	 * @see #getDataLinkDataType()
	 * @generated
	 */
	EAttribute getDataLinkDataType_Recovery();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getUnlink <em>Unlink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unlink</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getUnlink()
	 * @see #getDataLinkDataType()
	 * @generated
	 */
	EAttribute getDataLinkDataType_Unlink();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering <em>User Defined Type Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Defined Type Ordering</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering
	 * @generated
	 */
	EClass getUserDefinedTypeOrdering();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingForm <em>Ordering Form</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordering Form</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingForm()
	 * @see #getUserDefinedTypeOrdering()
	 * @generated
	 */
	EAttribute getUserDefinedTypeOrdering_OrderingForm();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingCategory <em>Ordering Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordering Category</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingCategory()
	 * @see #getUserDefinedTypeOrdering()
	 * @generated
	 */
	EAttribute getUserDefinedTypeOrdering_OrderingCategory();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingRoutine <em>Ordering Routine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ordering Routine</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingRoutine()
	 * @see #getUserDefinedTypeOrdering()
	 * @generated
	 */
	EReference getUserDefinedTypeOrdering_OrderingRoutine();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.DateDataType <em>Date Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DateDataType
	 * @generated
	 */
	EClass getDateDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.ExactNumericDataType <em>Exact Numeric Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exact Numeric Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ExactNumericDataType
	 * @generated
	 */
	EClass getExactNumericDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.datatypes.ExactNumericDataType#getScale <em>Scale</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ExactNumericDataType#getScale()
	 * @see #getExactNumericDataType()
	 * @generated
	 */
	EAttribute getExactNumericDataType_Scale();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType <em>Approximate Numeric Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Approximate Numeric Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType
	 * @generated
	 */
	EClass getApproximateNumericDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType <em>Integer Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType
	 * @generated
	 */
	EClass getIntegerDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType <em>XML Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XML Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType
	 * @generated
	 */
	EClass getXMLDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.datatypes.ElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ElementType
	 * @generated
	 */
	EClass getElementType();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.datatypes.ElementType#getCollectionDataType <em>Collection Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Collection Data Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ElementType#getCollectionDataType()
	 * @see #getElementType()
	 * @generated
	 */
	EReference getElementType_CollectionDataType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType <em>Coercibility Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Coercibility Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType
	 * @generated
	 */
	EEnum getCoercibilityType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType <em>Interval Qualifier Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Interval Qualifier Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @generated
	 */
	EEnum getIntervalQualifierType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.OrderingType <em>Ordering Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Ordering Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingType
	 * @generated
	 */
	EEnum getOrderingType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType <em>Ordering Category Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Ordering Category Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType
	 * @generated
	 */
	EEnum getOrderingCategoryType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Primitive Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType
	 * @generated
	 */
	EEnum getPrimitiveType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption <em>Link Control Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Link Control Option</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption
	 * @generated
	 */
	EEnum getLinkControlOption();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption <em>Integrity Control Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Integrity Control Option</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption
	 * @generated
	 */
	EEnum getIntegrityControlOption();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption <em>Read Permission Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Read Permission Option</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption
	 * @generated
	 */
	EEnum getReadPermissionOption();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption <em>Write Permission Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Write Permission Option</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption
	 * @generated
	 */
	EEnum getWritePermissionOption();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption <em>Unlink Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unlink Option</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption
	 * @generated
	 */
	EEnum getUnlinkOption();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SQLDataTypesFactory getSQLDataTypesFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeImpl <em>User Defined Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getUserDefinedType()
		 * @generated
		 */
		EClass USER_DEFINED_TYPE = eINSTANCE.getUserDefinedType();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_DEFINED_TYPE__SCHEMA = eINSTANCE.getUserDefinedType_Schema();

		/**
		 * The meta object literal for the '<em><b>Ordering</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_DEFINED_TYPE__ORDERING = eINSTANCE.getUserDefinedType_Ordering();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataTypeImpl <em>Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDataType()
		 * @generated
		 */
		EClass DATA_TYPE = eINSTANCE.getDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.PredefinedDataTypeImpl <em>Predefined Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.PredefinedDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getPredefinedDataType()
		 * @generated
		 */
		EClass PREDEFINED_DATA_TYPE = eINSTANCE.getPredefinedDataType();

		/**
		 * The meta object literal for the '<em><b>Primitive Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE = eINSTANCE.getPredefinedDataType_PrimitiveType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CollectionDataTypeImpl <em>Collection Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.CollectionDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getCollectionDataType()
		 * @generated
		 */
		EClass COLLECTION_DATA_TYPE = eINSTANCE.getCollectionDataType();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_DATA_TYPE__ELEMENT_TYPE = eINSTANCE.getCollectionDataType_ElementType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.NumericalDataTypeImpl <em>Numerical Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.NumericalDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getNumericalDataType()
		 * @generated
		 */
		EClass NUMERICAL_DATA_TYPE = eINSTANCE.getNumericalDataType();

		/**
		 * The meta object literal for the '<em><b>Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMERICAL_DATA_TYPE__PRECISION = eINSTANCE.getNumericalDataType_Precision();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterStringDataTypeImpl <em>Character String Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterStringDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getCharacterStringDataType()
		 * @generated
		 */
		EClass CHARACTER_STRING_DATA_TYPE = eINSTANCE.getCharacterStringDataType();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_STRING_DATA_TYPE__LENGTH = eINSTANCE.getCharacterStringDataType_Length();

		/**
		 * The meta object literal for the '<em><b>Coercibility</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_STRING_DATA_TYPE__COERCIBILITY = eINSTANCE.getCharacterStringDataType_Coercibility();

		/**
		 * The meta object literal for the '<em><b>Fixed Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_STRING_DATA_TYPE__FIXED_LENGTH = eINSTANCE.getCharacterStringDataType_FixedLength();

		/**
		 * The meta object literal for the '<em><b>Collation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_STRING_DATA_TYPE__COLLATION_NAME = eINSTANCE.getCharacterStringDataType_CollationName();

		/**
		 * The meta object literal for the '<em><b>Character Set</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHARACTER_STRING_DATA_TYPE__CHARACTER_SET = eINSTANCE.getCharacterStringDataType_CharacterSet();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.RowDataTypeImpl <em>Row Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.RowDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getRowDataType()
		 * @generated
		 */
		EClass ROW_DATA_TYPE = eINSTANCE.getRowDataType();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROW_DATA_TYPE__FIELDS = eINSTANCE.getRowDataType_Fields();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ArrayDataTypeImpl <em>Array Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ArrayDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getArrayDataType()
		 * @generated
		 */
		EClass ARRAY_DATA_TYPE = eINSTANCE.getArrayDataType();

		/**
		 * The meta object literal for the '<em><b>Max Cardinality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_DATA_TYPE__MAX_CARDINALITY = eINSTANCE.getArrayDataType_MaxCardinality();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.MultisetDataTypeImpl <em>Multiset Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.MultisetDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getMultisetDataType()
		 * @generated
		 */
		EClass MULTISET_DATA_TYPE = eINSTANCE.getMultisetDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.BooleanDataTypeImpl <em>Boolean Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.BooleanDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getBooleanDataType()
		 * @generated
		 */
		EClass BOOLEAN_DATA_TYPE = eINSTANCE.getBooleanDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.IntervalDataTypeImpl <em>Interval Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.IntervalDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getIntervalDataType()
		 * @generated
		 */
		EClass INTERVAL_DATA_TYPE = eINSTANCE.getIntervalDataType();

		/**
		 * The meta object literal for the '<em><b>Leading Qualifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERVAL_DATA_TYPE__LEADING_QUALIFIER = eINSTANCE.getIntervalDataType_LeadingQualifier();

		/**
		 * The meta object literal for the '<em><b>Trailing Qualifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERVAL_DATA_TYPE__TRAILING_QUALIFIER = eINSTANCE.getIntervalDataType_TrailingQualifier();

		/**
		 * The meta object literal for the '<em><b>Leading Field Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERVAL_DATA_TYPE__LEADING_FIELD_PRECISION = eINSTANCE.getIntervalDataType_LeadingFieldPrecision();

		/**
		 * The meta object literal for the '<em><b>Trailing Field Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERVAL_DATA_TYPE__TRAILING_FIELD_PRECISION = eINSTANCE.getIntervalDataType_TrailingFieldPrecision();

		/**
		 * The meta object literal for the '<em><b>Fractional Seconds Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERVAL_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION = eINSTANCE.getIntervalDataType_FractionalSecondsPrecision();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.BinaryStringDataTypeImpl <em>Binary String Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.BinaryStringDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getBinaryStringDataType()
		 * @generated
		 */
		EClass BINARY_STRING_DATA_TYPE = eINSTANCE.getBinaryStringDataType();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINARY_STRING_DATA_TYPE__LENGTH = eINSTANCE.getBinaryStringDataType_Length();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterSetImpl <em>Character Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterSetImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getCharacterSet()
		 * @generated
		 */
		EClass CHARACTER_SET = eINSTANCE.getCharacterSet();

		/**
		 * The meta object literal for the '<em><b>Repertoire</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_SET__REPERTOIRE = eINSTANCE.getCharacterSet_Repertoire();

		/**
		 * The meta object literal for the '<em><b>Default Collation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_SET__DEFAULT_COLLATION = eINSTANCE.getCharacterSet_DefaultCollation();

		/**
		 * The meta object literal for the '<em><b>Encoding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_SET__ENCODING = eINSTANCE.getCharacterSet_Encoding();

		/**
		 * The meta object literal for the '<em><b>Character String Data Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHARACTER_SET__CHARACTER_STRING_DATA_TYPE = eINSTANCE.getCharacterSet_CharacterStringDataType();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHARACTER_SET__SCHEMA = eINSTANCE.getCharacterSet_Schema();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.TimeDataTypeImpl <em>Time Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.TimeDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getTimeDataType()
		 * @generated
		 */
		EClass TIME_DATA_TYPE = eINSTANCE.getTimeDataType();

		/**
		 * The meta object literal for the '<em><b>Fractional Seconds Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION = eINSTANCE.getTimeDataType_FractionalSecondsPrecision();

		/**
		 * The meta object literal for the '<em><b>Time Zone</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_DATA_TYPE__TIME_ZONE = eINSTANCE.getTimeDataType_TimeZone();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DistinctUserDefinedTypeImpl <em>Distinct User Defined Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DistinctUserDefinedTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDistinctUserDefinedType()
		 * @generated
		 */
		EClass DISTINCT_USER_DEFINED_TYPE = eINSTANCE.getDistinctUserDefinedType();

		/**
		 * The meta object literal for the '<em><b>Predefined Representation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION = eINSTANCE.getDistinctUserDefinedType_PredefinedRepresentation();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl <em>Structured User Defined Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.StructuredUserDefinedTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getStructuredUserDefinedType()
		 * @generated
		 */
		EClass STRUCTURED_USER_DEFINED_TYPE = eINSTANCE.getStructuredUserDefinedType();

		/**
		 * The meta object literal for the '<em><b>Instantiable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURED_USER_DEFINED_TYPE__INSTANTIABLE = eINSTANCE.getStructuredUserDefinedType_Instantiable();

		/**
		 * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRUCTURED_USER_DEFINED_TYPE__FINAL = eINSTANCE.getStructuredUserDefinedType_Final();

		/**
		 * The meta object literal for the '<em><b>Super</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURED_USER_DEFINED_TYPE__SUPER = eINSTANCE.getStructuredUserDefinedType_Super();

		/**
		 * The meta object literal for the '<em><b>Sub</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURED_USER_DEFINED_TYPE__SUB = eINSTANCE.getStructuredUserDefinedType_Sub();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES = eINSTANCE.getStructuredUserDefinedType_Attributes();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURED_USER_DEFINED_TYPE__METHODS = eINSTANCE.getStructuredUserDefinedType_Methods();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.AttributeDefinitionImpl <em>Attribute Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.AttributeDefinitionImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getAttributeDefinition()
		 * @generated
		 */
		EClass ATTRIBUTE_DEFINITION = eINSTANCE.getAttributeDefinition();

		/**
		 * The meta object literal for the '<em><b>Scope Check</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_DEFINITION__SCOPE_CHECK = eINSTANCE.getAttributeDefinition_ScopeCheck();

		/**
		 * The meta object literal for the '<em><b>Scope Checked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_DEFINITION__SCOPE_CHECKED = eINSTANCE.getAttributeDefinition_ScopeChecked();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_DEFINITION__DEFAULT_VALUE = eINSTANCE.getAttributeDefinition_DefaultValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.FixedPrecisionDataTypeImpl <em>Fixed Precision Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.FixedPrecisionDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getFixedPrecisionDataType()
		 * @generated
		 */
		EClass FIXED_PRECISION_DATA_TYPE = eINSTANCE.getFixedPrecisionDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DomainImpl <em>Domain</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DomainImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDomain()
		 * @generated
		 */
		EClass DOMAIN = eINSTANCE.getDomain();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN__CONSTRAINT = eINSTANCE.getDomain_Constraint();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN__DEFAULT_VALUE = eINSTANCE.getDomain_DefaultValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.FieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.FieldImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getField()
		 * @generated
		 */
		EClass FIELD = eINSTANCE.getField();

		/**
		 * The meta object literal for the '<em><b>Scope Check</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__SCOPE_CHECK = eINSTANCE.getField_ScopeCheck();

		/**
		 * The meta object literal for the '<em><b>Scope Checked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__SCOPE_CHECKED = eINSTANCE.getField_ScopeChecked();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ReferenceDataTypeImpl <em>Reference Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ReferenceDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getReferenceDataType()
		 * @generated
		 */
		EClass REFERENCE_DATA_TYPE = eINSTANCE.getReferenceDataType();

		/**
		 * The meta object literal for the '<em><b>Scope Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_DATA_TYPE__SCOPE_TABLE = eINSTANCE.getReferenceDataType_ScopeTable();

		/**
		 * The meta object literal for the '<em><b>Referenced Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE_DATA_TYPE__REFERENCED_TYPE = eINSTANCE.getReferenceDataType_ReferencedType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ConstructedDataTypeImpl <em>Constructed Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ConstructedDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getConstructedDataType()
		 * @generated
		 */
		EClass CONSTRUCTED_DATA_TYPE = eINSTANCE.getConstructedDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypeImpl <em>SQL Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getSQLDataType()
		 * @generated
		 */
		EClass SQL_DATA_TYPE = eINSTANCE.getSQLDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl <em>Data Link Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDataLinkDataType()
		 * @generated
		 */
		EClass DATA_LINK_DATA_TYPE = eINSTANCE.getDataLinkDataType();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_LINK_DATA_TYPE__LENGTH = eINSTANCE.getDataLinkDataType_Length();

		/**
		 * The meta object literal for the '<em><b>Link Control</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_LINK_DATA_TYPE__LINK_CONTROL = eINSTANCE.getDataLinkDataType_LinkControl();

		/**
		 * The meta object literal for the '<em><b>Integrity Control</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_LINK_DATA_TYPE__INTEGRITY_CONTROL = eINSTANCE.getDataLinkDataType_IntegrityControl();

		/**
		 * The meta object literal for the '<em><b>Read Permission</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_LINK_DATA_TYPE__READ_PERMISSION = eINSTANCE.getDataLinkDataType_ReadPermission();

		/**
		 * The meta object literal for the '<em><b>Write Permission</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_LINK_DATA_TYPE__WRITE_PERMISSION = eINSTANCE.getDataLinkDataType_WritePermission();

		/**
		 * The meta object literal for the '<em><b>Recovery</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_LINK_DATA_TYPE__RECOVERY = eINSTANCE.getDataLinkDataType_Recovery();

		/**
		 * The meta object literal for the '<em><b>Unlink</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_LINK_DATA_TYPE__UNLINK = eINSTANCE.getDataLinkDataType_Unlink();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeOrderingImpl <em>User Defined Type Ordering</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeOrderingImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getUserDefinedTypeOrdering()
		 * @generated
		 */
		EClass USER_DEFINED_TYPE_ORDERING = eINSTANCE.getUserDefinedTypeOrdering();

		/**
		 * The meta object literal for the '<em><b>Ordering Form</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DEFINED_TYPE_ORDERING__ORDERING_FORM = eINSTANCE.getUserDefinedTypeOrdering_OrderingForm();

		/**
		 * The meta object literal for the '<em><b>Ordering Category</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USER_DEFINED_TYPE_ORDERING__ORDERING_CATEGORY = eINSTANCE.getUserDefinedTypeOrdering_OrderingCategory();

		/**
		 * The meta object literal for the '<em><b>Ordering Routine</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER_DEFINED_TYPE_ORDERING__ORDERING_ROUTINE = eINSTANCE.getUserDefinedTypeOrdering_OrderingRoutine();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DateDataTypeImpl <em>Date Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.DateDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getDateDataType()
		 * @generated
		 */
		EClass DATE_DATA_TYPE = eINSTANCE.getDateDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ExactNumericDataTypeImpl <em>Exact Numeric Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ExactNumericDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getExactNumericDataType()
		 * @generated
		 */
		EClass EXACT_NUMERIC_DATA_TYPE = eINSTANCE.getExactNumericDataType();

		/**
		 * The meta object literal for the '<em><b>Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXACT_NUMERIC_DATA_TYPE__SCALE = eINSTANCE.getExactNumericDataType_Scale();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ApproximateNumericDataTypeImpl <em>Approximate Numeric Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ApproximateNumericDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getApproximateNumericDataType()
		 * @generated
		 */
		EClass APPROXIMATE_NUMERIC_DATA_TYPE = eINSTANCE.getApproximateNumericDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.IntegerDataTypeImpl <em>Integer Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.IntegerDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getIntegerDataType()
		 * @generated
		 */
		EClass INTEGER_DATA_TYPE = eINSTANCE.getIntegerDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.XMLDataTypeImpl <em>XML Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.XMLDataTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getXMLDataType()
		 * @generated
		 */
		EClass XML_DATA_TYPE = eINSTANCE.getXMLDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.ElementTypeImpl <em>Element Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.ElementTypeImpl
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getElementType()
		 * @generated
		 */
		EClass ELEMENT_TYPE = eINSTANCE.getElementType();

		/**
		 * The meta object literal for the '<em><b>Collection Data Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_TYPE__COLLECTION_DATA_TYPE = eINSTANCE.getElementType_CollectionDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType <em>Coercibility Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getCoercibilityType()
		 * @generated
		 */
		EEnum COERCIBILITY_TYPE = eINSTANCE.getCoercibilityType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType <em>Interval Qualifier Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getIntervalQualifierType()
		 * @generated
		 */
		EEnum INTERVAL_QUALIFIER_TYPE = eINSTANCE.getIntervalQualifierType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.OrderingType <em>Ordering Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingType
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getOrderingType()
		 * @generated
		 */
		EEnum ORDERING_TYPE = eINSTANCE.getOrderingType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType <em>Ordering Category Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getOrderingCategoryType()
		 * @generated
		 */
		EEnum ORDERING_CATEGORY_TYPE = eINSTANCE.getOrderingCategoryType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType <em>Primitive Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getPrimitiveType()
		 * @generated
		 */
		EEnum PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption <em>Link Control Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getLinkControlOption()
		 * @generated
		 */
		EEnum LINK_CONTROL_OPTION = eINSTANCE.getLinkControlOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption <em>Integrity Control Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getIntegrityControlOption()
		 * @generated
		 */
		EEnum INTEGRITY_CONTROL_OPTION = eINSTANCE.getIntegrityControlOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption <em>Read Permission Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getReadPermissionOption()
		 * @generated
		 */
		EEnum READ_PERMISSION_OPTION = eINSTANCE.getReadPermissionOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption <em>Write Permission Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getWritePermissionOption()
		 * @generated
		 */
		EEnum WRITE_PERMISSION_OPTION = eINSTANCE.getWritePermissionOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption <em>Unlink Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption
		 * @see org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl#getUnlinkOption()
		 * @generated
		 */
		EEnum UNLINK_OPTION = eINSTANCE.getUnlinkOption();

	}

} //SQLDataTypesPackage
