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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Temporary Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.14 Tables
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.TemporaryTable#isLocal <em>Local</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.TemporaryTable#isDeleteOnCommit <em>Delete On Commit</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTemporaryTable()
 * @model
 * @generated
 */
public interface TemporaryTable extends BaseTable {
	/**
	 * Returns the value of the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local</em>' attribute.
	 * @see #setLocal(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTemporaryTable_Local()
	 * @model
	 * @generated
	 */
	boolean isLocal();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.TemporaryTable#isLocal <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local</em>' attribute.
	 * @see #isLocal()
	 * @generated
	 */
	void setLocal(boolean value);

	/**
	 * Returns the value of the '<em><b>Delete On Commit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delete On Commit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delete On Commit</em>' attribute.
	 * @see #setDeleteOnCommit(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getTemporaryTable_DeleteOnCommit()
	 * @model
	 * @generated
	 */
	boolean isDeleteOnCommit();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.TemporaryTable#isDeleteOnCommit <em>Delete On Commit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delete On Commit</em>' attribute.
	 * @see #isDeleteOnCommit()
	 * @generated
	 */
	void setDeleteOnCommit(boolean value);

} // TemporaryTable
