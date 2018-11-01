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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage
 * @generated
 */
public interface DatabaseDefinitionFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DatabaseDefinitionFactory eINSTANCE = org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseDefinitionFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Database Vendor Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Database Vendor Definition</em>'.
	 * @generated
	 */
	DatabaseVendorDefinition createDatabaseVendorDefinition();

	/**
	 * Returns a new object of class '<em>Predefined Data Type Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Predefined Data Type Definition</em>'.
	 * @generated
	 */
	PredefinedDataTypeDefinition createPredefinedDataTypeDefinition();

	/**
	 * Returns a new object of class '<em>Table Space Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Space Definition</em>'.
	 * @generated
	 */
	TableSpaceDefinition createTableSpaceDefinition();

	/**
	 * Returns a new object of class '<em>Stored Procedure Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stored Procedure Definition</em>'.
	 * @generated
	 */
	StoredProcedureDefinition createStoredProcedureDefinition();

	/**
	 * Returns a new object of class '<em>Trigger Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Trigger Definition</em>'.
	 * @generated
	 */
	TriggerDefinition createTriggerDefinition();

	/**
	 * Returns a new object of class '<em>Column Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Column Definition</em>'.
	 * @generated
	 */
	ColumnDefinition createColumnDefinition();

	/**
	 * Returns a new object of class '<em>Constraint Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constraint Definition</em>'.
	 * @generated
	 */
	ConstraintDefinition createConstraintDefinition();

	/**
	 * Returns a new object of class '<em>Index Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Index Definition</em>'.
	 * @generated
	 */
	IndexDefinition createIndexDefinition();

	/**
	 * Returns a new object of class '<em>Extended Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Extended Definition</em>'.
	 * @generated
	 */
	ExtendedDefinition createExtendedDefinition();

	/**
	 * Returns a new object of class '<em>Table Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Table Definition</em>'.
	 * @generated
	 */
	TableDefinition createTableDefinition();

	/**
	 * Returns a new object of class '<em>Sequence Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sequence Definition</em>'.
	 * @generated
	 */
	SequenceDefinition createSequenceDefinition();

	/**
	 * Returns a new object of class '<em>User Defined Type Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Defined Type Definition</em>'.
	 * @generated
	 */
	UserDefinedTypeDefinition createUserDefinedTypeDefinition();

	/**
	 * Returns a new object of class '<em>Query Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Query Definition</em>'.
	 * @generated
	 */
	QueryDefinition createQueryDefinition();

	/**
	 * Returns a new object of class '<em>SQL Syntax Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SQL Syntax Definition</em>'.
	 * @generated
	 */
	SQLSyntaxDefinition createSQLSyntaxDefinition();

	/**
	 * Returns a new object of class '<em>Nickname Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Nickname Definition</em>'.
	 * @generated
	 */
	NicknameDefinition createNicknameDefinition();

	/**
	 * Returns a new object of class '<em>Schema Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Schema Definition</em>'.
	 * @generated
	 */
	SchemaDefinition createSchemaDefinition();

	/**
	 * Returns a new object of class '<em>View Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>View Definition</em>'.
	 * @generated
	 */
	ViewDefinition createViewDefinition();

	/**
	 * Returns a new object of class '<em>Field Qualifier Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field Qualifier Definition</em>'.
	 * @generated
	 */
	FieldQualifierDefinition createFieldQualifierDefinition();

	/**
	 * Returns a new object of class '<em>Debugger Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Debugger Definition</em>'.
	 * @generated
	 */
	DebuggerDefinition createDebuggerDefinition();

	/**
	 * Returns a new object of class '<em>Privileged Element Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Privileged Element Definition</em>'.
	 * @generated
	 */
	PrivilegedElementDefinition createPrivilegedElementDefinition();

	/**
	 * Returns a new object of class '<em>Privilege Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Privilege Definition</em>'.
	 * @generated
	 */
	PrivilegeDefinition createPrivilegeDefinition();

	/**
	 * Returns a new object of class '<em>Constructed Data Type Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constructed Data Type Definition</em>'.
	 * @generated
	 */
	ConstructedDataTypeDefinition createConstructedDataTypeDefinition();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DatabaseDefinitionPackage getDatabaseDefinitionPackage();

} //DatabaseDefinitionFactory
