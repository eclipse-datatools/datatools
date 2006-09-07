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
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assertion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.17.4 Assertions,
 * 5WD-02-Foundation-2002-12 11.37 <assertion definition>
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSearchCondition <em>Search Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion#getConstrainedTables <em>Constrained Tables</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getAssertion()
 * @model
 * @generated
 */
public interface Assertion extends Constraint {
	/**
	 * Returns the value of the '<em><b>Search Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Search Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Search Condition</em>' containment reference.
	 * @see #setSearchCondition(SearchCondition)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getAssertion_SearchCondition()
	 * @model containment="true"
	 * @generated
	 */
	SearchCondition getSearchCondition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSearchCondition <em>Search Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Search Condition</em>' containment reference.
	 * @see #getSearchCondition()
	 * @generated
	 */
	void setSearchCondition(SearchCondition value);

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getAssertions <em>Assertions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(Schema)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getAssertion_Schema()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getAssertions
	 * @model opposite="assertions" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Assertion#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

	/**
	 * Returns the value of the '<em><b>Constrained Tables</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.BaseTable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constrained Tables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constrained Tables</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getAssertion_ConstrainedTables()
	 * @model type="org.eclipse.datatools.modelbase.sql.tables.BaseTable" required="true" derived="true"
	 * @generated
	 */
	EList getConstrainedTables();

} // Assertion
