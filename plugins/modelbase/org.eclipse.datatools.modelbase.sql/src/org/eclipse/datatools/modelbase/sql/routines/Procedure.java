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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Procedure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.27 SQL-invoked routines, 5WD-02-Foundation-2002-12 11.50 <SQL-invoked routine>
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Procedure#getMaxResultSets <em>Max Result Sets</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Procedure#isOldSavePoint <em>Old Save Point</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.routines.Procedure#getResultSet <em>Result Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getProcedure()
 * @model
 * @generated
 */
public interface Procedure extends Routine {
	/**
	 * Returns the value of the '<em><b>Max Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Maximun number of result sets the procedure can return.  If the stored procedure returns more result sets than is specified by this value, then a database error will be generated.
	 * 
	 * From 5WD-02-Foundation-2002-12
	 * 11.50 <SQL-invoked routine>
	 * <dynamic result sets characteristic> ::= DYNAMIC RESULT SETS <maximum dynamic result sets>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Max Result Sets</em>' attribute.
	 * @see #setMaxResultSets(int)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getProcedure_MaxResultSets()
	 * @model
	 * @generated
	 */
	int getMaxResultSets();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Procedure#getMaxResultSets <em>Max Result Sets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Result Sets</em>' attribute.
	 * @see #getMaxResultSets()
	 * @generated
	 */
	void setMaxResultSets(int value);

	/**
	 * Returns the value of the '<em><b>Old Save Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * From 5WD-02-Foundation-2002-12
	 * 4.27 SQL-invoked routines
	 * An SQL-invoked procedure may optionally be specified to require a new savepoint level to be established when it is invoked and destroyed on return from the executed routine body. The alternative of not taking a savepoint can also be directly specified with OLD SAVEPOINT LEVEL . When an SQL-invoked function is invoked a new savepoint level is always established.
	 * 
	 * 11.50 <SQL-invoked routine>
	 * <savepoint level indication> ::= NEW SAVEPOINT LEVEL | OLD SAVEPOINT LEVEL
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Old Save Point</em>' attribute.
	 * @see #setOldSavePoint(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getProcedure_OldSavePoint()
	 * @model
	 * @generated
	 */
	boolean isOldSavePoint();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.routines.Procedure#isOldSavePoint <em>Old Save Point</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Old Save Point</em>' attribute.
	 * @see #isOldSavePoint()
	 * @generated
	 */
	void setOldSavePoint(boolean value);

	/**
	 * Returns the value of the '<em><b>Result Set</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Set</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage#getProcedure_ResultSet()
	 * @model type="org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable" containment="true"
	 * @generated
	 */
	EList getResultSet();

} // Procedure
