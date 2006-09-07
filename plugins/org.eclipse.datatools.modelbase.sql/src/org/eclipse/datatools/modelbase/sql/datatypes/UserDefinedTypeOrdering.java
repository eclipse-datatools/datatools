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
package org.eclipse.datatools.modelbase.sql.datatypes;

import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Defined Type Ordering</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.7 User-defined types
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingForm <em>Ordering Form</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingCategory <em>Ordering Category</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingRoutine <em>Ordering Routine</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getUserDefinedTypeOrdering()
 * @model
 * @generated
 */
public interface UserDefinedTypeOrdering extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Ordering Form</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.OrderingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordering Form</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordering Form</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingType
	 * @see #setOrderingForm(OrderingType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getUserDefinedTypeOrdering_OrderingForm()
	 * @model
	 * @generated
	 */
	OrderingType getOrderingForm();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingForm <em>Ordering Form</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering Form</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingType
	 * @see #getOrderingForm()
	 * @generated
	 */
	void setOrderingForm(OrderingType value);

	/**
	 * Returns the value of the '<em><b>Ordering Category</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordering Category</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordering Category</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType
	 * @see #setOrderingCategory(OrderingCategoryType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getUserDefinedTypeOrdering_OrderingCategory()
	 * @model
	 * @generated
	 */
	OrderingCategoryType getOrderingCategory();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingCategory <em>Ordering Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering Category</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType
	 * @see #getOrderingCategory()
	 * @generated
	 */
	void setOrderingCategory(OrderingCategoryType value);

	/**
	 * Returns the value of the '<em><b>Ordering Routine</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordering Routine</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordering Routine</em>' reference.
	 * @see #setOrderingRoutine(Routine)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getUserDefinedTypeOrdering_OrderingRoutine()
	 * @model required="true"
	 * @generated
	 */
	Routine getOrderingRoutine();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering#getOrderingRoutine <em>Ordering Routine</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering Routine</em>' reference.
	 * @see #getOrderingRoutine()
	 * @generated
	 */
	void setOrderingRoutine(Routine value);

} // UserDefinedTypeOrdering
