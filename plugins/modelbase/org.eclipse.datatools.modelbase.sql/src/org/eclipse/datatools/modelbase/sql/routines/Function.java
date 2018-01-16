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
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.27 SQL-invoked routines
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Function#isNullCall <em>Null Call</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Function#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Function#getTransformGroup <em>Transform Group</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Function#isTypePreserving <em>Type Preserving</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Function#isMutator <em>Mutator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Function#getReturnTable <em>Return Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Function#getReturnScalar <em>Return Scalar</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Function#getReturnCast <em>Return Cast</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getFunction()
 * @model
 * @generated
 */
public interface Function extends Routine {
	/**
	 * Returns the value of the '<em><b>Null Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * 11.50 <SQL-invoked routine>
	 * <null-call clause> ::= RETURNS NULL ON NULL INPUT | CALLED ON NULL INPUT
	 * 
	 * If PROCEDURE is specified, then:
	 * - <null-call clause> shall not be specified.
	 * 
	 * if <null-call clause> is not specified, then 
	 * - CALLED ON NULL INPUT is implicit.
	 * 
	 * If the SQL-invoked routine is an SQL-invoked function, then:
	 * - The SQL-invoked routine descriptor includes an indication of whether the SQL-invoked routine
	 * is a null-call function.
	 * 
	 * 4.27 SQL-invoked routines
	 * A null-call function is an SQL-invoked function that is defined to return the null value if any of its input arguments is the null value. A null-call function is an SQL-invoked function whose <null-call clause> specifies
	 * "RETURNS NULL ON NULL INPUT".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Null Call</em>' attribute.
	 * @see #setNullCall(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getFunction_NullCall()
	 * @model
	 * @generated
	 */
	boolean isNullCall();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Function#isNullCall <em>Null Call</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Null Call</em>' attribute.
	 * @see #isNullCall()
	 * @generated
	 */
	void setNullCall(boolean value);

	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * 11.50 <SQL-invoked routine>
	 * 
	 * <dispatch clause> ::= STATIC DISPATCH
	 * 
	 * If R is an SQL-invoked regular function and the <SQL parameter declaration list> contains an
	 * <SQL parameter declaration> that specifies a <data type> that is one of:
	 * 1) A user-defined type.
	 * 2) A collection type whose element type is a user-defined type.
	 * 3) A collection type whose element type is a reference type.
	 * 4) A reference type.
	 * then <dispatch clause> shall be specified. Otherwise, <dispatch clause> shall not be specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getFunction_Static()
	 * @model
	 * @generated
	 */
	boolean isStatic();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Function#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);

	/**
	 * Returns the value of the '<em><b>Transform Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * 4.7.5 Transforms for user-defined types
	 * 
	 * Transforms are SQL-invoked functions that are automatically invoked when values of user-defined types are
	 * transferred from SQL-environment to host languages or vice-versa.
	 * 
	 * A transform is associated with a user-defined type. A transform identifies a list of transform groups of up to
	 * two SQL-invoked functions, called the transform functions, each identified by a group name. The group name
	 * of a transform group is an <identifier> such that no two transform groups for a transform have the same group
	 * name. The two transform functions are:
	 * - from-sql function : This SQL-invoked function maps the user-defined type value into a value of an SQL
	 *   pre-defined type, and gets invoked whenever a user-defined type value is passed to a host language 
	 *   program or an external routine.
	 * - to-sql function : This SQL-invoked function maps a value of an SQL predefined type to a value of a
	 *   user-defined type and gets invoked whenever a user-defined type value is supplied by a host language
	 *   program or an external routine.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transform Group</em>' attribute.
	 * @see #setTransformGroup(String)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getFunction_TransformGroup()
	 * @model
	 * @generated
	 */
	String getTransformGroup();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Function#getTransformGroup <em>Transform Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transform Group</em>' attribute.
	 * @see #getTransformGroup()
	 * @generated
	 */
	void setTransformGroup(String value);

	/**
	 * Returns the value of the '<em><b>Type Preserving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Preserving</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Preserving</em>' attribute.
	 * @see #setTypePreserving(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getFunction_TypePreserving()
	 * @model
	 * @generated
	 */
	boolean isTypePreserving();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Function#isTypePreserving <em>Type Preserving</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Preserving</em>' attribute.
	 * @see #isTypePreserving()
	 * @generated
	 */
	void setTypePreserving(boolean value);

	/**
	 * Returns the value of the '<em><b>Mutator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mutator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mutator</em>' attribute.
	 * @see #setMutator(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getFunction_Mutator()
	 * @model
	 * @generated
	 */
	boolean isMutator();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Function#isMutator <em>Mutator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mutator</em>' attribute.
	 * @see #isMutator()
	 * @generated
	 */
	void setMutator(boolean value);

	/**
	 * Returns the value of the '<em><b>Return Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Table</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Table</em>' containment reference.
	 * @see #setReturnTable(RoutineResultTable)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getFunction_ReturnTable()
	 * @model containment="true"
	 * @generated
	 */
	RoutineResultTable getReturnTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Function#getReturnTable <em>Return Table</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Table</em>' containment reference.
	 * @see #getReturnTable()
	 * @generated
	 */
	void setReturnTable(RoutineResultTable value);

	/**
	 * Returns the value of the '<em><b>Return Scalar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Scalar</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Scalar</em>' containment reference.
	 * @see #setReturnScalar(Parameter)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getFunction_ReturnScalar()
	 * @model containment="true"
	 * @generated
	 */
	Parameter getReturnScalar();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Function#getReturnScalar <em>Return Scalar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Scalar</em>' containment reference.
	 * @see #getReturnScalar()
	 * @generated
	 */
	void setReturnScalar(Parameter value);

	/**
	 * Returns the value of the '<em><b>Return Cast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Cast</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Cast</em>' containment reference.
	 * @see #setReturnCast(Parameter)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getFunction_ReturnCast()
	 * @model containment="true"
	 * @generated
	 */
	Parameter getReturnCast();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Function#getReturnCast <em>Return Cast</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Cast</em>' containment reference.
	 * @see #getReturnCast()
	 * @generated
	 */
	void setReturnCast(Parameter value);

} // Function
