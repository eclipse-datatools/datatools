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

import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Check Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.17.2 Table constraints
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint#getSearchCondition <em>Search Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getCheckConstraint()
 * @model
 * @generated
 */
public interface CheckConstraint extends TableConstraint {
	/**
	 * Returns the value of the '<em><b>Search Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Section 4.17.2
	 * A check constraint is satisfied if and only if the specified <search condition> is not False for any row of a table.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Search Condition</em>' containment reference.
	 * @see #setSearchCondition(SearchCondition)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getCheckConstraint_SearchCondition()
	 * @model containment="true"
	 * @generated
	 */
	SearchCondition getSearchCondition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint#getSearchCondition <em>Search Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Search Condition</em>' containment reference.
	 * @see #getSearchCondition()
	 * @generated
	 */
	void setSearchCondition(SearchCondition value);

} // CheckConstraint
