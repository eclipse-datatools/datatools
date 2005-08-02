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

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.17 Integrity constraints
 * 
 * Integrity constraints, generally referred to simply as constraints, define the valid states of SQL-data by constrain-ing the values in the base tables. A constraint is either a table constraint, a domain constraint or an assertion.  A constraint is described by a constraint descriptor. A constraint descriptor is either a table constraint descriptor, a domain constraint descriptor or an assertion descriptor. Every constraint descriptor includes:
 *   - The name of the constraint.
 *   - An indication of whether or not the constraint is deferrable.
 *   - An indication of whether the initial constraint mode is deferred or immediate.
 * 
 * No integrity constraint shall be defined using a <search condition> that is not retrospectively deterministic.
 * 
 * The checking of a constraint depends on its constraint mode within the current SQL-transaction. If the constraint mode is immediate, then the constraint is effectively checked at the end of each SQL-statement.  If the constraint mode is deferred, then the constraint is effectively checked when the constraint mode is changed to immediate either explicitly by execution of a <set constraints mode statement> , or implicitly at the end of the current SQL-transaction.
 * 
 * A table constraint is either a unique constraint, a referential constraint or a table check constraint.
 * 
 * A domain constraint is a constraint that is specified for a domain. It is applied to all columns that are based on that domain, and to all values cast to that domain.
 * 
 * An assertion is a named constraint that may relate to the content of individual rows of a table, to the entire contents of a table, or to a state required to exist among a number of tables.
 * 
 * 10.8
 * 2) The <qualified identifier> of <constraint name> shall not be equivalent to the <qualified identifier> of the <constraint name> of any other constraint defined in the same schema.
 * 3) If <constraint check time> is not specified, then INITIALLY IMMEDIATE is implicit.
 * 4) Case:
 * a) If INITIALLY DEFERRED is specified, then:
 *   i) NOT DEFERRABLE shall not be specified.
 *   ii) If DEFERRABLE is not specified, then DEFERRABLE is implicit.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint#isDeferrable <em>Deferrable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint#isInitiallyDeferred <em>Initially Deferred</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint#isEnforced <em>Enforced</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getConstraint()
 * @model abstract="true"
 * @generated
 */
public interface Constraint extends SQLObject{
	/**
	 * Returns the value of the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If True allows you to specify when the DBMS should check the constraint for violation (statement end or transaction end).  If False it will always be after statement end.
	 * 
	 * Could also be named deferralMode with values DEFERRABLE and NOT DEFERRABLE.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deferrable</em>' attribute.
	 * @see #setDeferrable(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getConstraint_Deferrable()
	 * @model 
	 * @generated
	 */
	boolean isDeferrable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint#isDeferrable <em>Deferrable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deferrable</em>' attribute.
	 * @see #isDeferrable()
	 * @generated
	 */
	void setDeferrable(boolean value);

	/**
	 * Returns the value of the '<em><b>Initially Deferred</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If True isDeferrable must be True and constraint check time will be DEFERRED.  If False constraint check time will be IMMEDIATE.
	 * 
	 * Could also be named (initial)ConstraintCheckTime with values INITIALLY DEFERRED and INITIALLY IMMEDIATE.  Default value would be INITIALLY IMMEDIATE.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Initially Deferred</em>' attribute.
	 * @see #setInitiallyDeferred(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getConstraint_InitiallyDeferred()
	 * @model default="false"
	 * @generated
	 */
	boolean isInitiallyDeferred();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint#isInitiallyDeferred <em>Initially Deferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initially Deferred</em>' attribute.
	 * @see #isInitiallyDeferred()
	 * @generated
	 */
	void setInitiallyDeferred(boolean value);

	/**
	 * Returns the value of the '<em><b>Enforced</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enforced</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enforced</em>' attribute.
	 * @see #setEnforced(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getConstraint_Enforced()
	 * @model default="true"
	 * @generated
	 */
	boolean isEnforced();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint#isEnforced <em>Enforced</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enforced</em>' attribute.
	 * @see #isEnforced()
	 * @generated
	 */
	void setEnforced(boolean value);

} // Constraint
