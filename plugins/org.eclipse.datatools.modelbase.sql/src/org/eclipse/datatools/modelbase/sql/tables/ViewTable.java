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
 * A representation of the model object '<em><b>View Table</b></em>'.
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
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.ViewTable#getCheckType <em>Check Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getViewTable()
 * @model
 * @generated
 */
public interface ViewTable extends DerivedTable {
	/**
	 * Returns the value of the '<em><b>Check Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.tables.CheckType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.CheckType
	 * @see #setCheckType(CheckType)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getViewTable_CheckType()
	 * @model
	 * @generated
	 */
	CheckType getCheckType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.ViewTable#getCheckType <em>Check Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Check Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.tables.CheckType
	 * @see #getCheckType()
	 * @generated
	 */
	void setCheckType(CheckType value);

} // ViewTable
