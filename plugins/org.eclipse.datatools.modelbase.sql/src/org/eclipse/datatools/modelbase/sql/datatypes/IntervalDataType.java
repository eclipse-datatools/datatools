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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interval Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.6.2 Intervals
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingQualifier <em>Leading Qualifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingQualifier <em>Trailing Qualifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingFieldPrecision <em>Leading Field Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingFieldPrecision <em>Trailing Field Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType()
 * @model
 * @generated
 */
public interface IntervalDataType extends PredefinedDataType {
	/**
	 * Returns the value of the '<em><b>Leading Qualifier</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Leading Qualifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Leading Qualifier</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #setLeadingQualifier(IntervalQualifierType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_LeadingQualifier()
	 * @model
	 * @generated
	 */
	IntervalQualifierType getLeadingQualifier();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingQualifier <em>Leading Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leading Qualifier</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #getLeadingQualifier()
	 * @generated
	 */
	void setLeadingQualifier(IntervalQualifierType value);

	/**
	 * Returns the value of the '<em><b>Trailing Qualifier</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trailing Qualifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trailing Qualifier</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #setTrailingQualifier(IntervalQualifierType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_TrailingQualifier()
	 * @model
	 * @generated
	 */
	IntervalQualifierType getTrailingQualifier();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingQualifier <em>Trailing Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trailing Qualifier</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #getTrailingQualifier()
	 * @generated
	 */
	void setTrailingQualifier(IntervalQualifierType value);

	/**
	 * Returns the value of the '<em><b>Leading Field Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Leading Field Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Leading Field Precision</em>' attribute.
	 * @see #setLeadingFieldPrecision(int)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_LeadingFieldPrecision()
	 * @model
	 * @generated
	 */
	int getLeadingFieldPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingFieldPrecision <em>Leading Field Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leading Field Precision</em>' attribute.
	 * @see #getLeadingFieldPrecision()
	 * @generated
	 */
	void setLeadingFieldPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Trailing Field Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trailing Field Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trailing Field Precision</em>' attribute.
	 * @see #setTrailingFieldPrecision(int)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_TrailingFieldPrecision()
	 * @model
	 * @generated
	 */
	int getTrailingFieldPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingFieldPrecision <em>Trailing Field Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trailing Field Precision</em>' attribute.
	 * @see #getTrailingFieldPrecision()
	 * @generated
	 */
	void setTrailingFieldPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Fractional Seconds Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fractional Seconds Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fractional Seconds Precision</em>' attribute.
	 * @see #setFractionalSecondsPrecision(int)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_FractionalSecondsPrecision()
	 * @model
	 * @generated
	 */
	int getFractionalSecondsPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fractional Seconds Precision</em>' attribute.
	 * @see #getFractionalSecondsPrecision()
	 * @generated
	 */
	void setFractionalSecondsPrecision(int value);

} // IntervalDataType
