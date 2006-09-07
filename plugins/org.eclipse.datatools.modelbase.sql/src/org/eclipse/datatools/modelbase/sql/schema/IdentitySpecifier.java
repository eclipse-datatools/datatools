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
package org.eclipse.datatools.modelbase.sql.schema;

import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identity Specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.14.4 Identity columns
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getGenerationType <em>Generation Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getStartValue <em>Start Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getIncrement <em>Increment</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getMinimum <em>Minimum</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getMaximum <em>Maximum</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#isCycleOption <em>Cycle Option</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getIdentitySpecifier()
 * @model
 * @generated
 */
public interface IdentitySpecifier extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Generation Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.schema.GenerateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generation Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.GenerateType
	 * @see #setGenerationType(GenerateType)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getIdentitySpecifier_GenerationType()
	 * @model
	 * @generated
	 */
	GenerateType getGenerationType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getGenerationType <em>Generation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generation Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.GenerateType
	 * @see #getGenerationType()
	 * @generated
	 */
	void setGenerationType(GenerateType value);

	/**
	 * Returns the value of the '<em><b>Start Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Value</em>' attribute.
	 * @see #setStartValue(BigInteger)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getIdentitySpecifier_StartValue()
	 * @model
	 * @generated
	 */
	BigInteger getStartValue();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getStartValue <em>Start Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Value</em>' attribute.
	 * @see #getStartValue()
	 * @generated
	 */
	void setStartValue(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Increment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Increment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Increment</em>' attribute.
	 * @see #setIncrement(BigInteger)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getIdentitySpecifier_Increment()
	 * @model
	 * @generated
	 */
	BigInteger getIncrement();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getIncrement <em>Increment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Increment</em>' attribute.
	 * @see #getIncrement()
	 * @generated
	 */
	void setIncrement(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Minimum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minimum</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minimum</em>' attribute.
	 * @see #setMinimum(BigInteger)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getIdentitySpecifier_Minimum()
	 * @model
	 * @generated
	 */
	BigInteger getMinimum();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getMinimum <em>Minimum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minimum</em>' attribute.
	 * @see #getMinimum()
	 * @generated
	 */
	void setMinimum(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Maximum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum</em>' attribute.
	 * @see #setMaximum(BigInteger)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getIdentitySpecifier_Maximum()
	 * @model
	 * @generated
	 */
	BigInteger getMaximum();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#getMaximum <em>Maximum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum</em>' attribute.
	 * @see #getMaximum()
	 * @generated
	 */
	void setMaximum(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Cycle Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cycle Option</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cycle Option</em>' attribute.
	 * @see #setCycleOption(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getIdentitySpecifier_CycleOption()
	 * @model
	 * @generated
	 */
	boolean isCycleOption();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier#isCycleOption <em>Cycle Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cycle Option</em>' attribute.
	 * @see #isCycleOption()
	 * @generated
	 */
	void setCycleOption(boolean value);

} // IdentitySpecifier
