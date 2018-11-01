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
package org.eclipse.datatools.modelbase.derby;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;

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
 * @see org.eclipse.datatools.modelbase.derby.DerbyModelFactory
 * @model kind="package"
 * @generated
 */
public interface DerbyModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "derby";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/derby/derby.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "DerbyModel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DerbyModelPackage eINSTANCE = org.eclipse.datatools.modelbase.derby.impl.DerbyModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.derby.impl.SynonymImpl <em>Synonym</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.derby.impl.SynonymImpl
	 * @see org.eclipse.datatools.modelbase.derby.impl.DerbyModelPackageImpl#getSynonym()
	 * @generated
	 */
	int SYNONYM = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__EANNOTATIONS = SQLTablesPackage.TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__NAME = SQLTablesPackage.TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__DEPENDENCIES = SQLTablesPackage.TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__DESCRIPTION = SQLTablesPackage.TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__LABEL = SQLTablesPackage.TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__COMMENTS = SQLTablesPackage.TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__EXTENSIONS = SQLTablesPackage.TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__PRIVILEGES = SQLTablesPackage.TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__COLUMNS = SQLTablesPackage.TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__SUPERTABLE = SQLTablesPackage.TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__SUBTABLES = SQLTablesPackage.TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__SCHEMA = SQLTablesPackage.TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__UDT = SQLTablesPackage.TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__TRIGGERS = SQLTablesPackage.TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__INDEX = SQLTablesPackage.TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__INSERTABLE = SQLTablesPackage.TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__UPDATABLE = SQLTablesPackage.TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM__TABLE = SQLTablesPackage.TABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Synonym</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNONYM_FEATURE_COUNT = SQLTablesPackage.TABLE_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.derby.impl.DerbySchemaImpl <em>Derby Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.derby.impl.DerbySchemaImpl
	 * @see org.eclipse.datatools.modelbase.derby.impl.DerbyModelPackageImpl#getDerbySchema()
	 * @generated
	 */
	int DERBY_SCHEMA = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__EANNOTATIONS = SQLSchemaPackage.SCHEMA__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__NAME = SQLSchemaPackage.SCHEMA__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__DEPENDENCIES = SQLSchemaPackage.SCHEMA__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__DESCRIPTION = SQLSchemaPackage.SCHEMA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__LABEL = SQLSchemaPackage.SCHEMA__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__COMMENTS = SQLSchemaPackage.SCHEMA__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__EXTENSIONS = SQLSchemaPackage.SCHEMA__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__PRIVILEGES = SQLSchemaPackage.SCHEMA__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__TRIGGERS = SQLSchemaPackage.SCHEMA__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Indices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__INDICES = SQLSchemaPackage.SCHEMA__INDICES;

	/**
	 * The feature id for the '<em><b>Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__TABLES = SQLSchemaPackage.SCHEMA__TABLES;

	/**
	 * The feature id for the '<em><b>Sequences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__SEQUENCES = SQLSchemaPackage.SCHEMA__SEQUENCES;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__DATABASE = SQLSchemaPackage.SCHEMA__DATABASE;

	/**
	 * The feature id for the '<em><b>Catalog</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__CATALOG = SQLSchemaPackage.SCHEMA__CATALOG;

	/**
	 * The feature id for the '<em><b>Assertions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__ASSERTIONS = SQLSchemaPackage.SCHEMA__ASSERTIONS;

	/**
	 * The feature id for the '<em><b>User Defined Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__USER_DEFINED_TYPES = SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES;

	/**
	 * The feature id for the '<em><b>Char Sets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__CHAR_SETS = SQLSchemaPackage.SCHEMA__CHAR_SETS;

	/**
	 * The feature id for the '<em><b>Routines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__ROUTINES = SQLSchemaPackage.SCHEMA__ROUTINES;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA__OWNER = SQLSchemaPackage.SCHEMA__OWNER;

	/**
	 * The number of structural features of the '<em>Derby Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DERBY_SCHEMA_FEATURE_COUNT = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.derby.Synonym <em>Synonym</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Synonym</em>'.
	 * @see org.eclipse.datatools.modelbase.derby.Synonym
	 * @generated
	 */
	EClass getSynonym();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.derby.Synonym#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.modelbase.derby.Synonym#getTable()
	 * @see #getSynonym()
	 * @generated
	 */
	EReference getSynonym_Table();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.derby.DerbySchema <em>Derby Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Derby Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.derby.DerbySchema
	 * @generated
	 */
	EClass getDerbySchema();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DerbyModelFactory getDerbyModelFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.derby.impl.SynonymImpl <em>Synonym</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.derby.impl.SynonymImpl
		 * @see org.eclipse.datatools.modelbase.derby.impl.DerbyModelPackageImpl#getSynonym()
		 * @generated
		 */
		EClass SYNONYM = eINSTANCE.getSynonym();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNONYM__TABLE = eINSTANCE.getSynonym_Table();

			/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.derby.impl.DerbySchemaImpl <em>Derby Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.derby.impl.DerbySchemaImpl
		 * @see org.eclipse.datatools.modelbase.derby.impl.DerbyModelPackageImpl#getDerbySchema()
		 * @generated
		 */
		EClass DERBY_SCHEMA = eINSTANCE.getDerbySchema();

	}

} //DerbyModelPackage
