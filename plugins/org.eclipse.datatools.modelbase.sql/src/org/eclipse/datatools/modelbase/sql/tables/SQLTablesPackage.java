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
package org.eclipse.datatools.modelbase.sql.tables;

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
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesFactory
 * @model kind="package"
 * @generated
 */
public interface SQLTablesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tables"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/tables.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SQLTables"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLTablesPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl <em>Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getTable()
	 * @generated
	 */
	int TABLE = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__COLUMNS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__SUPERTABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__SUBTABLES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__UDT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__TRIGGERS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__INDEX = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__SELF_REF_COLUMN_GENERATION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__INSERTABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__UPDATABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.DerivedTableImpl <em>Derived Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.DerivedTableImpl
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getDerivedTable()
	 * @generated
	 */
	int DERIVED_TABLE = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__EANNOTATIONS = TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__NAME = TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__DEPENDENCIES = TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__DESCRIPTION = TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__LABEL = TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__COMMENTS = TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__EXTENSIONS = TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__PRIVILEGES = TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__COLUMNS = TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__SUPERTABLE = TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__SUBTABLES = TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__SCHEMA = TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__UDT = TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__TRIGGERS = TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__INDEX = TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__SELF_REF_COLUMN_GENERATION = TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__INSERTABLE = TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__UPDATABLE = TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE__QUERY_EXPRESSION = TABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Derived Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERIVED_TABLE_FEATURE_COUNT = TABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.ViewTableImpl <em>View Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.ViewTableImpl
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getViewTable()
	 * @generated
	 */
	int VIEW_TABLE = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__EANNOTATIONS = DERIVED_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__NAME = DERIVED_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__DEPENDENCIES = DERIVED_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__DESCRIPTION = DERIVED_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__LABEL = DERIVED_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__COMMENTS = DERIVED_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__EXTENSIONS = DERIVED_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__PRIVILEGES = DERIVED_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__COLUMNS = DERIVED_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__SUPERTABLE = DERIVED_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__SUBTABLES = DERIVED_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__SCHEMA = DERIVED_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__UDT = DERIVED_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__TRIGGERS = DERIVED_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__INDEX = DERIVED_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__SELF_REF_COLUMN_GENERATION = DERIVED_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__INSERTABLE = DERIVED_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__UPDATABLE = DERIVED_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__QUERY_EXPRESSION = DERIVED_TABLE__QUERY_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Check Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE__CHECK_TYPE = DERIVED_TABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>View Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_TABLE_FEATURE_COUNT = DERIVED_TABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.BaseTableImpl <em>Base Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.BaseTableImpl
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getBaseTable()
	 * @generated
	 */
	int BASE_TABLE = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__EANNOTATIONS = TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__NAME = TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__DEPENDENCIES = TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__DESCRIPTION = TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__LABEL = TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__COMMENTS = TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__EXTENSIONS = TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__PRIVILEGES = TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__COLUMNS = TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__SUPERTABLE = TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__SUBTABLES = TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__SCHEMA = TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__UDT = TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__TRIGGERS = TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__INDEX = TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__SELF_REF_COLUMN_GENERATION = TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__INSERTABLE = TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__UPDATABLE = TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__CONSTRAINTS = TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE__REFERENCING_FOREIGN_KEYS = TABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Base Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE_TABLE_FEATURE_COUNT = TABLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.TemporaryTableImpl <em>Temporary Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.TemporaryTableImpl
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getTemporaryTable()
	 * @generated
	 */
	int TEMPORARY_TABLE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__EANNOTATIONS = BASE_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__NAME = BASE_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__DEPENDENCIES = BASE_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__DESCRIPTION = BASE_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__LABEL = BASE_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__COMMENTS = BASE_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__EXTENSIONS = BASE_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__PRIVILEGES = BASE_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__COLUMNS = BASE_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__SUPERTABLE = BASE_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__SUBTABLES = BASE_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__SCHEMA = BASE_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__UDT = BASE_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__TRIGGERS = BASE_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__INDEX = BASE_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__SELF_REF_COLUMN_GENERATION = BASE_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__INSERTABLE = BASE_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__UPDATABLE = BASE_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__CONSTRAINTS = BASE_TABLE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__REFERENCING_FOREIGN_KEYS = BASE_TABLE__REFERENCING_FOREIGN_KEYS;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__LOCAL = BASE_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Delete On Commit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE__DELETE_ON_COMMIT = BASE_TABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Temporary Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORARY_TABLE_FEATURE_COUNT = BASE_TABLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.PersistentTableImpl <em>Persistent Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.PersistentTableImpl
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getPersistentTable()
	 * @generated
	 */
	int PERSISTENT_TABLE = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__EANNOTATIONS = BASE_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__NAME = BASE_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__DEPENDENCIES = BASE_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__DESCRIPTION = BASE_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__LABEL = BASE_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__COMMENTS = BASE_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__EXTENSIONS = BASE_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__PRIVILEGES = BASE_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__COLUMNS = BASE_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__SUPERTABLE = BASE_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__SUBTABLES = BASE_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__SCHEMA = BASE_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__UDT = BASE_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__TRIGGERS = BASE_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__INDEX = BASE_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__SELF_REF_COLUMN_GENERATION = BASE_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__INSERTABLE = BASE_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__UPDATABLE = BASE_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__CONSTRAINTS = BASE_TABLE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE__REFERENCING_FOREIGN_KEYS = BASE_TABLE__REFERENCING_FOREIGN_KEYS;

	/**
	 * The number of structural features of the '<em>Persistent Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSISTENT_TABLE_FEATURE_COUNT = BASE_TABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl <em>Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getColumn()
	 * @generated
	 */
	int COLUMN = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__EANNOTATIONS = SQLSchemaPackage.TYPED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__NAME = SQLSchemaPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__DEPENDENCIES = SQLSchemaPackage.TYPED_ELEMENT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__DESCRIPTION = SQLSchemaPackage.TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__LABEL = SQLSchemaPackage.TYPED_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__COMMENTS = SQLSchemaPackage.TYPED_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__EXTENSIONS = SQLSchemaPackage.TYPED_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__PRIVILEGES = SQLSchemaPackage.TYPED_ELEMENT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__CONTAINED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__REFERENCED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__TABLE = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Identity Specifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__IDENTITY_SPECIFIER = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Generate Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__GENERATE_EXPRESSION = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Implementation Dependent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__IMPLEMENTATION_DEPENDENT = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__NULLABLE = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__DEFAULT_VALUE = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Scope Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__SCOPE_CHECK = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__SCOPE_CHECKED = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_FEATURE_COUNT = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl <em>Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getTrigger()
	 * @generated
	 */
	int TRIGGER = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subject Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__SUBJECT_TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Action Statement</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__ACTION_STATEMENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Trigger Column</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__TRIGGER_COLUMN = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Action Granularity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__ACTION_GRANULARITY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>When</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__WHEN = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Time Stamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__TIME_STAMP = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Action Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__ACTION_TIME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Update Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__UPDATE_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Insert Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__INSERT_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Delete Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__DELETE_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Old Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__OLD_ROW = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>New Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__NEW_ROW = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Old Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__OLD_TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>New Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__NEW_TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 14;

	/**
	 * The number of structural features of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 15;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.CheckType <em>Check Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.CheckType
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getCheckType()
	 * @generated
	 */
	int CHECK_TYPE = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.ReferenceType <em>Reference Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.ReferenceType
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getReferenceType()
	 * @generated
	 */
	int REFERENCE_TYPE = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.ActionTimeType <em>Action Time Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.ActionTimeType
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getActionTimeType()
	 * @generated
	 */
	int ACTION_TIME_TYPE = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType <em>Action Granularity Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType
	 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getActionGranularityType()
	 * @generated
	 */
	int ACTION_GRANULARITY_TYPE = 11;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.tables.ViewTable <em>View Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ViewTable
	 * @generated
	 */
	EClass getViewTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.ViewTable#getCheckType <em>Check Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Check Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ViewTable#getCheckType()
	 * @see #getViewTable()
	 * @generated
	 */
	EAttribute getViewTable_CheckType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.tables.TemporaryTable <em>Temporary Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Temporary Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.TemporaryTable
	 * @generated
	 */
	EClass getTemporaryTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.TemporaryTable#isLocal <em>Local</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.TemporaryTable#isLocal()
	 * @see #getTemporaryTable()
	 * @generated
	 */
	EAttribute getTemporaryTable_Local();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.TemporaryTable#isDeleteOnCommit <em>Delete On Commit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delete On Commit</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.TemporaryTable#isDeleteOnCommit()
	 * @see #getTemporaryTable()
	 * @generated
	 */
	EAttribute getTemporaryTable_DeleteOnCommit();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.tables.Table <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table
	 * @generated
	 */
	EClass getTable();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getColumns()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Columns();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSupertable <em>Supertable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Supertable</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getSupertable()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Supertable();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSubtables <em>Subtables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Subtables</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getSubtables()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Subtables();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getSchema()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Schema();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getUdt <em>Udt</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Udt</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getUdt()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Udt();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Triggers</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getTriggers()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Triggers();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Index</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getIndex()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_Index();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Table#getSelfRefColumnGeneration <em>Self Ref Column Generation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Self Ref Column Generation</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#getSelfRefColumnGeneration()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_SelfRefColumnGeneration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Table#isInsertable <em>Insertable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Insertable</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#isInsertable()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Insertable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Table#isUpdatable <em>Updatable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Updatable</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table#isUpdatable()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Updatable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.tables.PersistentTable <em>Persistent Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Persistent Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.PersistentTable
	 * @generated
	 */
	EClass getPersistentTable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.tables.DerivedTable <em>Derived Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derived Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.DerivedTable
	 * @generated
	 */
	EClass getDerivedTable();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.tables.DerivedTable#getQueryExpression <em>Query Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Query Expression</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.DerivedTable#getQueryExpression()
	 * @see #getDerivedTable()
	 * @generated
	 */
	EReference getDerivedTable_QueryExpression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.tables.BaseTable <em>Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Base Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.BaseTable
	 * @generated
	 */
	EClass getBaseTable();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.tables.BaseTable#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.BaseTable#getConstraints()
	 * @see #getBaseTable()
	 * @generated
	 */
	EReference getBaseTable_Constraints();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.tables.BaseTable#getReferencingForeignKeys <em>Referencing Foreign Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Referencing Foreign Keys</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.BaseTable#getReferencingForeignKeys()
	 * @see #getBaseTable()
	 * @generated
	 */
	EReference getBaseTable_ReferencingForeignKeys();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.tables.Column <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column
	 * @generated
	 */
	EClass getColumn();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column#getTable()
	 * @see #getColumn()
	 * @generated
	 */
	EReference getColumn_Table();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getIdentitySpecifier <em>Identity Specifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Identity Specifier</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column#getIdentitySpecifier()
	 * @see #getColumn()
	 * @generated
	 */
	EReference getColumn_IdentitySpecifier();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getGenerateExpression <em>Generate Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Generate Expression</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column#getGenerateExpression()
	 * @see #getColumn()
	 * @generated
	 */
	EReference getColumn_GenerateExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Column#isImplementationDependent <em>Implementation Dependent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Dependent</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column#isImplementationDependent()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_ImplementationDependent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Column#isNullable <em>Nullable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nullable</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column#isNullable()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Nullable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column#getDefaultValue()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_DefaultValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Column#getScopeCheck <em>Scope Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope Check</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column#getScopeCheck()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_ScopeCheck();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Column#isScopeChecked <em>Scope Checked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope Checked</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column#isScopeChecked()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_ScopeChecked();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getSchema()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_Schema();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getSubjectTable <em>Subject Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subject Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getSubjectTable()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_SubjectTable();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionStatement <em>Action Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionStatement()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_ActionStatement();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getTriggerColumn <em>Trigger Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Trigger Column</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getTriggerColumn()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_TriggerColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionGranularity <em>Action Granularity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action Granularity</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionGranularity()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_ActionGranularity();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>When</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getWhen()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_When();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getTimeStamp <em>Time Stamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time Stamp</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getTimeStamp()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_TimeStamp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionTime <em>Action Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action Time</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getActionTime()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_ActionTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#isUpdateType <em>Update Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Update Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#isUpdateType()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_UpdateType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#isInsertType <em>Insert Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Insert Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#isInsertType()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_InsertType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#isDeleteType <em>Delete Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delete Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#isDeleteType()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_DeleteType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getOldRow <em>Old Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Old Row</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getOldRow()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_OldRow();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getNewRow <em>New Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Row</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getNewRow()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_NewRow();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getOldTable <em>Old Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Old Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getOldTable()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_OldTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger#getNewTable <em>New Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger#getNewTable()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_NewTable();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.tables.CheckType <em>Check Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Check Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.CheckType
	 * @generated
	 */
	EEnum getCheckType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.tables.ReferenceType <em>Reference Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Reference Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ReferenceType
	 * @generated
	 */
	EEnum getReferenceType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.tables.ActionTimeType <em>Action Time Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Action Time Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ActionTimeType
	 * @generated
	 */
	EEnum getActionTimeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType <em>Action Granularity Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Action Granularity Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType
	 * @generated
	 */
	EEnum getActionGranularityType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SQLTablesFactory getSQLTablesFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.ViewTableImpl <em>View Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.ViewTableImpl
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getViewTable()
		 * @generated
		 */
		EClass VIEW_TABLE = eINSTANCE.getViewTable();

		/**
		 * The meta object literal for the '<em><b>Check Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_TABLE__CHECK_TYPE = eINSTANCE.getViewTable_CheckType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.TemporaryTableImpl <em>Temporary Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.TemporaryTableImpl
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getTemporaryTable()
		 * @generated
		 */
		EClass TEMPORARY_TABLE = eINSTANCE.getTemporaryTable();

		/**
		 * The meta object literal for the '<em><b>Local</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPORARY_TABLE__LOCAL = eINSTANCE.getTemporaryTable_Local();

		/**
		 * The meta object literal for the '<em><b>Delete On Commit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPORARY_TABLE__DELETE_ON_COMMIT = eINSTANCE.getTemporaryTable_DeleteOnCommit();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl <em>Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getTable()
		 * @generated
		 */
		EClass TABLE = eINSTANCE.getTable();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__COLUMNS = eINSTANCE.getTable_Columns();

		/**
		 * The meta object literal for the '<em><b>Supertable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__SUPERTABLE = eINSTANCE.getTable_Supertable();

		/**
		 * The meta object literal for the '<em><b>Subtables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__SUBTABLES = eINSTANCE.getTable_Subtables();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__SCHEMA = eINSTANCE.getTable_Schema();

		/**
		 * The meta object literal for the '<em><b>Udt</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__UDT = eINSTANCE.getTable_Udt();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__TRIGGERS = eINSTANCE.getTable_Triggers();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__INDEX = eINSTANCE.getTable_Index();

		/**
		 * The meta object literal for the '<em><b>Self Ref Column Generation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE__SELF_REF_COLUMN_GENERATION = eINSTANCE.getTable_SelfRefColumnGeneration();

		/**
		 * The meta object literal for the '<em><b>Insertable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE__INSERTABLE = eINSTANCE.getTable_Insertable();

		/**
		 * The meta object literal for the '<em><b>Updatable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE__UPDATABLE = eINSTANCE.getTable_Updatable();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.PersistentTableImpl <em>Persistent Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.PersistentTableImpl
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getPersistentTable()
		 * @generated
		 */
		EClass PERSISTENT_TABLE = eINSTANCE.getPersistentTable();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.DerivedTableImpl <em>Derived Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.DerivedTableImpl
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getDerivedTable()
		 * @generated
		 */
		EClass DERIVED_TABLE = eINSTANCE.getDerivedTable();

		/**
		 * The meta object literal for the '<em><b>Query Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DERIVED_TABLE__QUERY_EXPRESSION = eINSTANCE.getDerivedTable_QueryExpression();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.BaseTableImpl <em>Base Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.BaseTableImpl
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getBaseTable()
		 * @generated
		 */
		EClass BASE_TABLE = eINSTANCE.getBaseTable();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_TABLE__CONSTRAINTS = eINSTANCE.getBaseTable_Constraints();

		/**
		 * The meta object literal for the '<em><b>Referencing Foreign Keys</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BASE_TABLE__REFERENCING_FOREIGN_KEYS = eINSTANCE.getBaseTable_ReferencingForeignKeys();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl <em>Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getColumn()
		 * @generated
		 */
		EClass COLUMN = eINSTANCE.getColumn();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLUMN__TABLE = eINSTANCE.getColumn_Table();

		/**
		 * The meta object literal for the '<em><b>Identity Specifier</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLUMN__IDENTITY_SPECIFIER = eINSTANCE.getColumn_IdentitySpecifier();

		/**
		 * The meta object literal for the '<em><b>Generate Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLUMN__GENERATE_EXPRESSION = eINSTANCE.getColumn_GenerateExpression();

		/**
		 * The meta object literal for the '<em><b>Implementation Dependent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__IMPLEMENTATION_DEPENDENT = eINSTANCE.getColumn_ImplementationDependent();

		/**
		 * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__NULLABLE = eINSTANCE.getColumn_Nullable();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__DEFAULT_VALUE = eINSTANCE.getColumn_DefaultValue();

		/**
		 * The meta object literal for the '<em><b>Scope Check</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__SCOPE_CHECK = eINSTANCE.getColumn_ScopeCheck();

		/**
		 * The meta object literal for the '<em><b>Scope Checked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__SCOPE_CHECKED = eINSTANCE.getColumn_ScopeChecked();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__SCHEMA = eINSTANCE.getTrigger_Schema();

		/**
		 * The meta object literal for the '<em><b>Subject Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__SUBJECT_TABLE = eINSTANCE.getTrigger_SubjectTable();

		/**
		 * The meta object literal for the '<em><b>Action Statement</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__ACTION_STATEMENT = eINSTANCE.getTrigger_ActionStatement();

		/**
		 * The meta object literal for the '<em><b>Trigger Column</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__TRIGGER_COLUMN = eINSTANCE.getTrigger_TriggerColumn();

		/**
		 * The meta object literal for the '<em><b>Action Granularity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__ACTION_GRANULARITY = eINSTANCE.getTrigger_ActionGranularity();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__WHEN = eINSTANCE.getTrigger_When();

		/**
		 * The meta object literal for the '<em><b>Time Stamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__TIME_STAMP = eINSTANCE.getTrigger_TimeStamp();

		/**
		 * The meta object literal for the '<em><b>Action Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__ACTION_TIME = eINSTANCE.getTrigger_ActionTime();

		/**
		 * The meta object literal for the '<em><b>Update Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__UPDATE_TYPE = eINSTANCE.getTrigger_UpdateType();

		/**
		 * The meta object literal for the '<em><b>Insert Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__INSERT_TYPE = eINSTANCE.getTrigger_InsertType();

		/**
		 * The meta object literal for the '<em><b>Delete Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__DELETE_TYPE = eINSTANCE.getTrigger_DeleteType();

		/**
		 * The meta object literal for the '<em><b>Old Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__OLD_ROW = eINSTANCE.getTrigger_OldRow();

		/**
		 * The meta object literal for the '<em><b>New Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__NEW_ROW = eINSTANCE.getTrigger_NewRow();

		/**
		 * The meta object literal for the '<em><b>Old Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__OLD_TABLE = eINSTANCE.getTrigger_OldTable();

		/**
		 * The meta object literal for the '<em><b>New Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__NEW_TABLE = eINSTANCE.getTrigger_NewTable();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.CheckType <em>Check Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.CheckType
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getCheckType()
		 * @generated
		 */
		EEnum CHECK_TYPE = eINSTANCE.getCheckType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.ReferenceType <em>Reference Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.ReferenceType
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getReferenceType()
		 * @generated
		 */
		EEnum REFERENCE_TYPE = eINSTANCE.getReferenceType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.ActionTimeType <em>Action Time Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.ActionTimeType
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getActionTimeType()
		 * @generated
		 */
		EEnum ACTION_TIME_TYPE = eINSTANCE.getActionTimeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType <em>Action Granularity Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType
		 * @see org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl#getActionGranularityType()
		 * @generated
		 */
		EEnum ACTION_GRANULARITY_TYPE = eINSTANCE.getActionGranularityType();

	}

} //SQLTablesPackage
