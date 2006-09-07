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

import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Derived Table</b></em>'.
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
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.DerivedTable#getQueryExpression <em>Query Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getDerivedTable()
 * @model abstract="true"
 * @generated
 */
public interface DerivedTable extends Table {
	/**
	 * Returns the value of the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query Expression</em>' containment reference.
	 * @see #setQueryExpression(QueryExpression)
	 * @see org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage#getDerivedTable_QueryExpression()
	 * @model containment="true"
	 * @generated
	 */
	QueryExpression getQueryExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.tables.DerivedTable#getQueryExpression <em>Query Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query Expression</em>' containment reference.
	 * @see #getQueryExpression()
	 * @generated
	 */
	void setQueryExpression(QueryExpression value);

} // DerivedTable
