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
package org.eclipse.datatools.modelbase.sql.expressions;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Search Condition Default</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Place holder for the default search condition.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault#getSQL <em>SQL</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage#getSearchConditionDefault()
 * @model
 * @generated
 */
public interface SearchConditionDefault extends SQLObject, SearchCondition {
	/**
	 * Returns the value of the '<em><b>SQL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SQL</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SQL</em>' attribute.
	 * @see #setSQL(String)
	 * @see org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage#getSearchConditionDefault_SQL()
	 * @model
	 * @generated
	 */
	String getSQL();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault#getSQL <em>SQL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SQL</em>' attribute.
	 * @see #getSQL()
	 * @generated
	 */
	void setSQL(String value);

} // SearchConditionDefault
