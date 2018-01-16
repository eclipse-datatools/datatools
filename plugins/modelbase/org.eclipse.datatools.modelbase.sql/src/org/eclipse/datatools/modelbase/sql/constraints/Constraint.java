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
 * Reference: 5WD-02-Foundation-2002-12 4.17 Integrity constraints
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
public interface Constraint extends SQLObject {
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
