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
package org.eclipse.datatools.modelbase.sql.constraints;

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
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsFactory
 * @generated
 */
public interface SQLConstraintsPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "constraints"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/constraints.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SQLConstraints"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLConstraintsPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.ConstraintImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__DEFERRABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__INITIALLY_DEFERRED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__ENFORCED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.AssertionImpl <em>Assertion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.AssertionImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getAssertion()
	 * @generated
	 */
	int ASSERTION = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__EANNOTATIONS = CONSTRAINT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__NAME = CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__DEPENDENCIES = CONSTRAINT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__DESCRIPTION = CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__LABEL = CONSTRAINT__LABEL;

	/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__DEFERRABLE = CONSTRAINT__DEFERRABLE;

	/**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__INITIALLY_DEFERRED = CONSTRAINT__INITIALLY_DEFERRED;

	/**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__ENFORCED = CONSTRAINT__ENFORCED;

	/**
	 * The feature id for the '<em><b>Search Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__SEARCH_CONDITION = CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__SCHEMA = CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Constrained Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION__CONSTRAINED_TABLES = CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Assertion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERTION_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.TableConstraintImpl <em>Table Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.TableConstraintImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getTableConstraint()
	 * @generated
	 */
	int TABLE_CONSTRAINT = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT__EANNOTATIONS = CONSTRAINT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT__NAME = CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT__DEPENDENCIES = CONSTRAINT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT__DESCRIPTION = CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT__LABEL = CONSTRAINT__LABEL;

	/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT__DEFERRABLE = CONSTRAINT__DEFERRABLE;

	/**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT__INITIALLY_DEFERRED = CONSTRAINT__INITIALLY_DEFERRED;

	/**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT__ENFORCED = CONSTRAINT__ENFORCED;

	/**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT__BASE_TABLE = CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Table Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_CONSTRAINT_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ReferenceConstraintImpl <em>Reference Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.ReferenceConstraintImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getReferenceConstraint()
	 * @generated
	 */
	int REFERENCE_CONSTRAINT = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__EANNOTATIONS = TABLE_CONSTRAINT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__NAME = TABLE_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__DEPENDENCIES = TABLE_CONSTRAINT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__DESCRIPTION = TABLE_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__LABEL = TABLE_CONSTRAINT__LABEL;

	/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__DEFERRABLE = TABLE_CONSTRAINT__DEFERRABLE;

	/**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__INITIALLY_DEFERRED = TABLE_CONSTRAINT__INITIALLY_DEFERRED;

	/**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__ENFORCED = TABLE_CONSTRAINT__ENFORCED;

	/**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__BASE_TABLE = TABLE_CONSTRAINT__BASE_TABLE;

	/**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT__MEMBERS = TABLE_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Reference Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_CONSTRAINT_FEATURE_COUNT = TABLE_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl <em>Check Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getCheckConstraint()
	 * @generated
	 */
	int CHECK_CONSTRAINT = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__EANNOTATIONS = TABLE_CONSTRAINT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__NAME = TABLE_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__DEPENDENCIES = TABLE_CONSTRAINT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__DESCRIPTION = TABLE_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__LABEL = TABLE_CONSTRAINT__LABEL;

	/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__DEFERRABLE = TABLE_CONSTRAINT__DEFERRABLE;

	/**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__INITIALLY_DEFERRED = TABLE_CONSTRAINT__INITIALLY_DEFERRED;

	/**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__ENFORCED = TABLE_CONSTRAINT__ENFORCED;

	/**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__BASE_TABLE = TABLE_CONSTRAINT__BASE_TABLE;

	/**
	 * The feature id for the '<em><b>Search Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT__SEARCH_CONDITION = TABLE_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Check Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_CONSTRAINT_FEATURE_COUNT = TABLE_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl <em>Foreign Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getForeignKey()
	 * @generated
	 */
	int FOREIGN_KEY = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__EANNOTATIONS = REFERENCE_CONSTRAINT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__NAME = REFERENCE_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__DEPENDENCIES = REFERENCE_CONSTRAINT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__DESCRIPTION = REFERENCE_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__LABEL = REFERENCE_CONSTRAINT__LABEL;

	/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__DEFERRABLE = REFERENCE_CONSTRAINT__DEFERRABLE;

	/**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__INITIALLY_DEFERRED = REFERENCE_CONSTRAINT__INITIALLY_DEFERRED;

	/**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__ENFORCED = REFERENCE_CONSTRAINT__ENFORCED;

	/**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__BASE_TABLE = REFERENCE_CONSTRAINT__BASE_TABLE;

	/**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__MEMBERS = REFERENCE_CONSTRAINT__MEMBERS;

	/**
	 * The feature id for the '<em><b>Match</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__MATCH = REFERENCE_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>On Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__ON_UPDATE = REFERENCE_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>On Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__ON_DELETE = REFERENCE_CONSTRAINT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Unique Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__UNIQUE_CONSTRAINT = REFERENCE_CONSTRAINT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Referenced Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__REFERENCED_MEMBERS = REFERENCE_CONSTRAINT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Unique Index</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__UNIQUE_INDEX = REFERENCE_CONSTRAINT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the the '<em>Foreign Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY_FEATURE_COUNT = REFERENCE_CONSTRAINT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.UniqueConstraintImpl <em>Unique Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.UniqueConstraintImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getUniqueConstraint()
	 * @generated
	 */
	int UNIQUE_CONSTRAINT = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__EANNOTATIONS = REFERENCE_CONSTRAINT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__NAME = REFERENCE_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__DEPENDENCIES = REFERENCE_CONSTRAINT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__DESCRIPTION = REFERENCE_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__LABEL = REFERENCE_CONSTRAINT__LABEL;

	/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__DEFERRABLE = REFERENCE_CONSTRAINT__DEFERRABLE;

	/**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__INITIALLY_DEFERRED = REFERENCE_CONSTRAINT__INITIALLY_DEFERRED;

	/**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__ENFORCED = REFERENCE_CONSTRAINT__ENFORCED;

	/**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__BASE_TABLE = REFERENCE_CONSTRAINT__BASE_TABLE;

	/**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__MEMBERS = REFERENCE_CONSTRAINT__MEMBERS;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__FOREIGN_KEY = REFERENCE_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Unique Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT_FEATURE_COUNT = REFERENCE_CONSTRAINT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.PrimaryKeyImpl <em>Primary Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.PrimaryKeyImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getPrimaryKey()
	 * @generated
	 */
	int PRIMARY_KEY = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__EANNOTATIONS = UNIQUE_CONSTRAINT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__NAME = UNIQUE_CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__DEPENDENCIES = UNIQUE_CONSTRAINT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__DESCRIPTION = UNIQUE_CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__LABEL = UNIQUE_CONSTRAINT__LABEL;

	/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__DEFERRABLE = UNIQUE_CONSTRAINT__DEFERRABLE;

	/**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__INITIALLY_DEFERRED = UNIQUE_CONSTRAINT__INITIALLY_DEFERRED;

	/**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__ENFORCED = UNIQUE_CONSTRAINT__ENFORCED;

	/**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__BASE_TABLE = UNIQUE_CONSTRAINT__BASE_TABLE;

	/**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__MEMBERS = UNIQUE_CONSTRAINT__MEMBERS;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY__FOREIGN_KEY = UNIQUE_CONSTRAINT__FOREIGN_KEY;

	/**
	 * The number of structural features of the the '<em>Primary Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY_FEATURE_COUNT = UNIQUE_CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl <em>Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getIndex()
	 * @generated
	 */
	int INDEX = 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__CLUSTERED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__FILL_FACTOR = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__UNIQUE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__MEMBERS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__FOREIGN_KEY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Included Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__INCLUDED_MEMBERS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the the '<em>Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexMemberImpl <em>Index Member</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.IndexMemberImpl
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getIndexMember()
	 * @generated
	 */
	int INDEX_MEMBER = 9;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_MEMBER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_MEMBER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_MEMBER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_MEMBER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_MEMBER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Increment Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_MEMBER__INCREMENT_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_MEMBER__COLUMN = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Index Member</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_MEMBER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.MatchType <em>Match Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.MatchType
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getMatchType()
	 * @generated
	 */
	int MATCH_TYPE = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.constraints.IncrementType <em>Increment Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.constraints.IncrementType
	 * @see org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl#getIncrementType()
	 * @generated
	 */
	int INCREMENT_TYPE = 11;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion <em>Assertion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assertion</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Assertion
	 * @generated
	 */
	EClass getAssertion();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSearchCondition <em>Search Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Search Condition</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSearchCondition()
	 * @see #getAssertion()
	 * @generated
	 */
	EReference getAssertion_SearchCondition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSchema()
	 * @see #getAssertion()
	 * @generated
	 */
	EReference getAssertion_Schema();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion#getConstrainedTables <em>Constrained Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Constrained Tables</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Assertion#getConstrainedTables()
	 * @see #getAssertion()
	 * @generated
	 */
	EReference getAssertion_ConstrainedTables();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint#isDeferrable <em>Deferrable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deferrable</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Constraint#isDeferrable()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_Deferrable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint#isInitiallyDeferred <em>Initially Deferred</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initially Deferred</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Constraint#isInitiallyDeferred()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_InitiallyDeferred();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint#isEnforced <em>Enforced</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enforced</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Constraint#isEnforced()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_Enforced();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.TableConstraint <em>Table Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Constraint</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.TableConstraint
	 * @generated
	 */
	EClass getTableConstraint();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.constraints.TableConstraint#getBaseTable <em>Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Base Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.TableConstraint#getBaseTable()
	 * @see #getTableConstraint()
	 * @generated
	 */
	EReference getTableConstraint_BaseTable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint <em>Reference Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference Constraint</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint
	 * @generated
	 */
	EClass getReferenceConstraint();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint#getMembers <em>Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Members</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint#getMembers()
	 * @see #getReferenceConstraint()
	 * @generated
	 */
	EReference getReferenceConstraint_Members();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint <em>Check Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Check Constraint</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint
	 * @generated
	 */
	EClass getCheckConstraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint#getSearchCondition <em>Search Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Search Condition</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint#getSearchCondition()
	 * @see #getCheckConstraint()
	 * @generated
	 */
	EReference getCheckConstraint_SearchCondition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Foreign Key</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey
	 * @generated
	 */
	EClass getForeignKey();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getMatch <em>Match</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Match</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getMatch()
	 * @see #getForeignKey()
	 * @generated
	 */
	EAttribute getForeignKey_Match();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getOnUpdate <em>On Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Update</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getOnUpdate()
	 * @see #getForeignKey()
	 * @generated
	 */
	EAttribute getForeignKey_OnUpdate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getOnDelete <em>On Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Delete</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getOnDelete()
	 * @see #getForeignKey()
	 * @generated
	 */
	EAttribute getForeignKey_OnDelete();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueConstraint <em>Unique Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Unique Constraint</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueConstraint()
	 * @see #getForeignKey()
	 * @generated
	 */
	EReference getForeignKey_UniqueConstraint();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getReferencedMembers <em>Referenced Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Referenced Members</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getReferencedMembers()
	 * @see #getForeignKey()
	 * @generated
	 */
	EReference getForeignKey_ReferencedMembers();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueIndex <em>Unique Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Unique Index</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getUniqueIndex()
	 * @see #getForeignKey()
	 * @generated
	 */
	EReference getForeignKey_UniqueIndex();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint <em>Unique Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unique Constraint</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint
	 * @generated
	 */
	EClass getUniqueConstraint();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint#getForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Foreign Key</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint#getForeignKey()
	 * @see #getUniqueConstraint()
	 * @generated
	 */
	EReference getUniqueConstraint_ForeignKey();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey <em>Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primary Key</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey
	 * @generated
	 */
	EClass getPrimaryKey();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.Index <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index
	 * @generated
	 */
	EClass getIndex();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#getSchema()
	 * @see #getIndex()
	 * @generated
	 */
	EReference getIndex_Schema();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#isClustered <em>Clustered</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clustered</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#isClustered()
	 * @see #getIndex()
	 * @generated
	 */
	EAttribute getIndex_Clustered();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getFillFactor <em>Fill Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Factor</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#getFillFactor()
	 * @see #getIndex()
	 * @generated
	 */
	EAttribute getIndex_FillFactor();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#isUnique()
	 * @see #getIndex()
	 * @generated
	 */
	EAttribute getIndex_Unique();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getMembers <em>Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Members</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#getMembers()
	 * @see #getIndex()
	 * @generated
	 */
	EReference getIndex_Members();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#getTable()
	 * @see #getIndex()
	 * @generated
	 */
	EReference getIndex_Table();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Foreign Key</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#getForeignKey()
	 * @see #getIndex()
	 * @generated
	 */
	EReference getIndex_ForeignKey();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.constraints.Index#getIncludedMembers <em>Included Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Included Members</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index#getIncludedMembers()
	 * @see #getIndex()
	 * @generated
	 */
	EReference getIndex_IncludedMembers();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember <em>Index Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index Member</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.IndexMember
	 * @generated
	 */
	EClass getIndexMember();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getIncrementType <em>Increment Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Increment Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getIncrementType()
	 * @see #getIndexMember()
	 * @generated
	 */
	EAttribute getIndexMember_IncrementType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Column</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getColumn()
	 * @see #getIndexMember()
	 * @generated
	 */
	EReference getIndexMember_Column();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.constraints.MatchType <em>Match Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Match Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.MatchType
	 * @generated
	 */
	EEnum getMatchType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.constraints.IncrementType <em>Increment Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Increment Type</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.IncrementType
	 * @generated
	 */
	EEnum getIncrementType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SQLConstraintsFactory getSQLConstraintsFactory();

} //SQLConstraintsPackage
