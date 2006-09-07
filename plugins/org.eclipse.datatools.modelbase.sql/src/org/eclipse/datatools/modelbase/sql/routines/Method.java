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
package org.eclipse.datatools.modelbase.sql.routines;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.27 SQL-invoked routines 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Method#isOverriding <em>Overriding</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Method#isConstructor <em>Constructor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getMethod()
 * @model
 * @generated
 */
public interface Method extends Function {
	/**
	 * Returns the value of the '<em><b>Overriding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overriding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overriding</em>' attribute.
	 * @see #setOverriding(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getMethod_Overriding()
	 * @model
	 * @generated
	 */
	boolean isOverriding();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Method#isOverriding <em>Overriding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overriding</em>' attribute.
	 * @see #isOverriding()
	 * @generated
	 */
	void setOverriding(boolean value);

	/**
	 * Returns the value of the '<em><b>Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constructor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constructor</em>' attribute.
	 * @see #setConstructor(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getMethod_Constructor()
	 * @model
	 * @generated
	 */
	boolean isConstructor();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Method#isConstructor <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constructor</em>' attribute.
	 * @see #isConstructor()
	 * @generated
	 */
	void setConstructor(boolean value);

} // Method
