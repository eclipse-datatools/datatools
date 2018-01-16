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

import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;

import org.eclipse.datatools.modelbase.sql.schema.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.27 SQL-invoked routines, 5WD-02-Foundation-2002-12 11.50 <SQL-invoked routine>,  Reference: 5WD-02-Foundation-2002-12 4.29.4 Locators  
 * Reference: Information technology - Database languages - SQL - Part 14: XML-Related Specifications (SQL/XML) 12.7 <SQL-invoked routine>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getMode <em>Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#isLocator <em>Locator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getRoutine <em>Routine</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getStringTypeOption <em>String Type Option</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends TypedElement {
	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.routines.ParameterMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 5WD-02-Foundation-2002-12
	 * 
	 * <SQL parameter declaration> ::= [ <parameter mode> ] [ <SQL parameter name> ] <parameter type> [ RESULT ]
	 * 
	 * <parameter mode> ::= IN | OUT | INOUT
	 * 
	 * For functions, defaults to IN.
	 * See enumerated class ParameterMode
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.routines.ParameterMode
	 * @see #setMode(ParameterMode)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getParameter_Mode()
	 * @model
	 * @generated
	 */
	ParameterMode getMode();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getMode <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mode</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.routines.ParameterMode
	 * @see #getMode()
	 * @generated
	 */
	void setMode(ParameterMode value);

	/**
	 * Returns the value of the '<em><b>Locator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 5WD-02-Foundation-2002-12
	 * 4.29.4 Locators
	 * A host parameter, a host variable, an SQL parameter of an external routine, or the value returned by an external
	 * function may be specified to be a locator by specifying AS LOCATOR . A locator is an SQL-session object,
	 * rather than SQL-data, that can be used to reference an SQL-data instance. A locator is either a large object
	 * locator, a user-defined type locator, an array locator, or a multiset locator. 
	 * 
	 * A large object locator is one of the following:
	 * - Binary large object locator, a value of which identifies a binary large object.
	 * - Character large object locator, a value of which identifies a large object character string.
	 * - National character large object locator, a value of which identifies a national large object character string.
	 * 
	 * A user-defined type locator identifies a value of the user-defined type specified by the locator specification.
	 * An array locator identifies a value of the array type specified by the locator specification. A multiset locator
	 * identifies a value of the multiset type specified by the locator specification.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Locator</em>' attribute.
	 * @see #setLocator(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getParameter_Locator()
	 * @model
	 * @generated
	 */
	boolean isLocator();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#isLocator <em>Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locator</em>' attribute.
	 * @see #isLocator()
	 * @generated
	 */
	void setLocator(boolean value);

	/**
	 * Returns the value of the '<em><b>Routine</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Routine</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Routine</em>' container reference.
	 * @see #setRoutine(Routine)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getParameter_Routine()
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getParameters
	 * @model opposite="parameters" required="true"
	 * @generated
	 */
	Routine getRoutine();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getRoutine <em>Routine</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Routine</em>' container reference.
	 * @see #getRoutine()
	 * @generated
	 */
	void setRoutine(Routine value);

	/**
	 * Returns the value of the '<em><b>String Type Option</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String Type Option</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String Type Option</em>' containment reference.
	 * @see #setStringTypeOption(CharacterStringDataType)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getParameter_StringTypeOption()
	 * @model containment="true"
	 * @generated
	 */
	CharacterStringDataType getStringTypeOption();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getStringTypeOption <em>String Type Option</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String Type Option</em>' containment reference.
	 * @see #getStringTypeOption()
	 * @generated
	 */
	void setStringTypeOption(CharacterStringDataType value);

} // Parameter
