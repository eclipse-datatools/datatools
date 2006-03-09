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

import java.util.List;

import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Base Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.14 Tables
 * 
 * A table is a collection of rows having one or more columns. [...]
 * 
 * A table is either a base table, a derived table, or a transient table. A base table is either a persistent base table, a global temporary table, a created local temporary table, or a declared local temporary table.
 * 
 * All base tables are updatable. Every column of a base table is an updatable column. Derived tables and transient tables are either updatable or not updatable. The operations of update and delete are permitted for updatable tables, subject to constraining Access Rules. Some updatable tables, including all base tables whose row type is not derived from a user-defined type that is not instantiable, are also insertable-into, in which case the operation of insert is also permitted, again subject to Access Rules.
 * 
 * A base table descriptor describes a base table. In addition to the components of every table descriptor, a base table descriptor includes:
 *  - The name of the base table.
 *  - An indication of whether the table is a persistent base table, a global temporary table, a created local temporary table, or a declared local temporary table.
 *  - If the base table is a global temporary table, a created local temporary table, or a declared local temporary table, then an indication of whether ON COMMIT PRESERVE ROWS was specified or ON COMMIT DELETE ROWS was specified or implied.
 *  - The descriptor of each table constraint specified for the table.
 *  - A non-empty set of functional dependencies, according to the rules given in Subclause 4.18, "Functional dependencies".
 *  - A non-empty set of candidate keys, according to the rules of Subclause 4.19, "Candidate keys".
 *  - A preferred candidate key, which may or may not be additionally designated the primary key, according to the Rules in Subclause 4.18, "Functional dependencies".
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.BaseTable#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.BaseTable#getReferencingForeignKeys <em>Referencing Foreign Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getBaseTable()
 * @model abstract="true"
 * @generated
 */
public interface BaseTable extends Table{
	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.constraints.TableConstraint}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.constraints.TableConstraint#getBaseTable <em>Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getBaseTable_Constraints()
	 * @see org.eclipse.datatools.modelbase.sql.constraints.TableConstraint#getBaseTable
	 * @model type="org.eclipse.datatools.modelbase.sql.constraints.TableConstraint" opposite="BaseTable" containment="true"
	 * @generated
	 */
	EList getConstraints();

	/**
	 * Returns the value of the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getReferencedTable <em>Referenced Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referencing Foreign Keys</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referencing Foreign Keys</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getBaseTable_ReferencingForeignKeys()
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey#getReferencedTable
	 * @model type="org.eclipse.datatools.modelbase.sql.constraints.ForeignKey" opposite="referencedTable"
	 * @generated
	 */
	EList getReferencingForeignKeys();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
	 * @generated
	 */
	List getUniqueConstraints();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
	 * @generated
	 */
	List getForeignKeys();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	PrimaryKey getPrimaryKey();

} // BaseTable
