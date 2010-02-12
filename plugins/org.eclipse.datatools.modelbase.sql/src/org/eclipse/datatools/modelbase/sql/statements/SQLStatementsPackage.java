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
package org.eclipse.datatools.modelbase.sql.statements;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see org.eclipse.datatools.modelbase.sql.statements.SQLStatementsFactory
 * @model kind="package"
 * @generated
 */
public interface SQLStatementsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "statements"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/statements.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SQLStatements"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLStatementsPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLStatement <em>SQL Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLStatement()
	 * @generated
	 */
	int SQL_STATEMENT = 0;

	/**
	 * The number of structural features of the '<em>SQL Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement <em>SQL Data Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLDataStatement()
	 * @generated
	 */
	int SQL_DATA_STATEMENT = 1;

	/**
	 * The number of structural features of the '<em>SQL Data Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_STATEMENT_FEATURE_COUNT = SQL_STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLSchemaStatement <em>SQL Schema Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLSchemaStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLSchemaStatement()
	 * @generated
	 */
	int SQL_SCHEMA_STATEMENT = 2;

	/**
	 * The number of structural features of the '<em>SQL Schema Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_SCHEMA_STATEMENT_FEATURE_COUNT = SQL_STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement <em>SQL Control Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLControlStatement()
	 * @generated
	 */
	int SQL_CONTROL_STATEMENT = 3;

	/**
	 * The number of structural features of the '<em>SQL Control Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_CONTROL_STATEMENT_FEATURE_COUNT = SQL_STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement <em>SQL Data Change Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLDataChangeStatement()
	 * @generated
	 */
	int SQL_DATA_CHANGE_STATEMENT = 4;

	/**
	 * The number of structural features of the '<em>SQL Data Change Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DATA_CHANGE_STATEMENT_FEATURE_COUNT = SQL_DATA_STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementDefaultImpl <em>SQL Statement Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementDefaultImpl
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLStatementDefault()
	 * @generated
	 */
	int SQL_STATEMENT_DEFAULT = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>SQL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT__SQL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>SQL Statement Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_STATEMENT_DEFAULT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLConnectionStatement <em>SQL Connection Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLConnectionStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLConnectionStatement()
	 * @generated
	 */
	int SQL_CONNECTION_STATEMENT = 6;

	/**
	 * The number of structural features of the '<em>SQL Connection Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_CONNECTION_STATEMENT_FEATURE_COUNT = SQL_STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDiagnosticsStatement <em>SQL Diagnostics Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDiagnosticsStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLDiagnosticsStatement()
	 * @generated
	 */
	int SQL_DIAGNOSTICS_STATEMENT = 7;

	/**
	 * The number of structural features of the '<em>SQL Diagnostics Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DIAGNOSTICS_STATEMENT_FEATURE_COUNT = SQL_STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDynamicStatement <em>SQL Dynamic Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDynamicStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLDynamicStatement()
	 * @generated
	 */
	int SQL_DYNAMIC_STATEMENT = 8;

	/**
	 * The number of structural features of the '<em>SQL Dynamic Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_DYNAMIC_STATEMENT_FEATURE_COUNT = SQL_STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLSessionStatement <em>SQL Session Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLSessionStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLSessionStatement()
	 * @generated
	 */
	int SQL_SESSION_STATEMENT = 9;

	/**
	 * The number of structural features of the '<em>SQL Session Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_SESSION_STATEMENT_FEATURE_COUNT = SQL_STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLTransactionStatement <em>SQL Transaction Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLTransactionStatement
	 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLTransactionStatement()
	 * @generated
	 */
	int SQL_TRANSACTION_STATEMENT = 10;

	/**
	 * The number of structural features of the '<em>SQL Transaction Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SQL_TRANSACTION_STATEMENT_FEATURE_COUNT = SQL_STATEMENT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLStatement <em>SQL Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLStatement
	 * @generated
	 */
	EClass getSQLStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement <em>SQL Data Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Data Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement
	 * @generated
	 */
	EClass getSQLDataStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLSchemaStatement <em>SQL Schema Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Schema Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLSchemaStatement
	 * @generated
	 */
	EClass getSQLSchemaStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement <em>SQL Control Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Control Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement
	 * @generated
	 */
	EClass getSQLControlStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement <em>SQL Data Change Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Data Change Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement
	 * @generated
	 */
	EClass getSQLDataChangeStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLStatementDefault <em>SQL Statement Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Statement Default</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLStatementDefault
	 * @generated
	 */
	EClass getSQLStatementDefault();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.statements.SQLStatementDefault#getSQL <em>SQL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SQL</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLStatementDefault#getSQL()
	 * @see #getSQLStatementDefault()
	 * @generated
	 */
	EAttribute getSQLStatementDefault_SQL();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLConnectionStatement <em>SQL Connection Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Connection Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLConnectionStatement
	 * @generated
	 */
	EClass getSQLConnectionStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDiagnosticsStatement <em>SQL Diagnostics Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Diagnostics Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDiagnosticsStatement
	 * @generated
	 */
	EClass getSQLDiagnosticsStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDynamicStatement <em>SQL Dynamic Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Dynamic Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDynamicStatement
	 * @generated
	 */
	EClass getSQLDynamicStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLSessionStatement <em>SQL Session Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Session Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLSessionStatement
	 * @generated
	 */
	EClass getSQLSessionStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.statements.SQLTransactionStatement <em>SQL Transaction Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SQL Transaction Statement</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.statements.SQLTransactionStatement
	 * @generated
	 */
	EClass getSQLTransactionStatement();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SQLStatementsFactory getSQLStatementsFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLStatement <em>SQL Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLStatement()
		 * @generated
		 */
		EClass SQL_STATEMENT = eINSTANCE.getSQLStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement <em>SQL Data Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDataStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLDataStatement()
		 * @generated
		 */
		EClass SQL_DATA_STATEMENT = eINSTANCE.getSQLDataStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLSchemaStatement <em>SQL Schema Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLSchemaStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLSchemaStatement()
		 * @generated
		 */
		EClass SQL_SCHEMA_STATEMENT = eINSTANCE.getSQLSchemaStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement <em>SQL Control Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLControlStatement()
		 * @generated
		 */
		EClass SQL_CONTROL_STATEMENT = eINSTANCE.getSQLControlStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement <em>SQL Data Change Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDataChangeStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLDataChangeStatement()
		 * @generated
		 */
		EClass SQL_DATA_CHANGE_STATEMENT = eINSTANCE.getSQLDataChangeStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementDefaultImpl <em>SQL Statement Default</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementDefaultImpl
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLStatementDefault()
		 * @generated
		 */
		EClass SQL_STATEMENT_DEFAULT = eINSTANCE.getSQLStatementDefault();

		/**
		 * The meta object literal for the '<em><b>SQL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SQL_STATEMENT_DEFAULT__SQL = eINSTANCE.getSQLStatementDefault_SQL();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLConnectionStatement <em>SQL Connection Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLConnectionStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLConnectionStatement()
		 * @generated
		 */
		EClass SQL_CONNECTION_STATEMENT = eINSTANCE.getSQLConnectionStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDiagnosticsStatement <em>SQL Diagnostics Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDiagnosticsStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLDiagnosticsStatement()
		 * @generated
		 */
		EClass SQL_DIAGNOSTICS_STATEMENT = eINSTANCE.getSQLDiagnosticsStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLDynamicStatement <em>SQL Dynamic Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLDynamicStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLDynamicStatement()
		 * @generated
		 */
		EClass SQL_DYNAMIC_STATEMENT = eINSTANCE.getSQLDynamicStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLSessionStatement <em>SQL Session Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLSessionStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLSessionStatement()
		 * @generated
		 */
		EClass SQL_SESSION_STATEMENT = eINSTANCE.getSQLSessionStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.statements.SQLTransactionStatement <em>SQL Transaction Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.statements.SQLTransactionStatement
		 * @see org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl#getSQLTransactionStatement()
		 * @generated
		 */
		EClass SQL_TRANSACTION_STATEMENT = eINSTANCE.getSQLTransactionStatement();

	}

} //SQLStatementsPackage
