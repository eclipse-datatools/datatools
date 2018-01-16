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
import org.eclipse.datatools.modelbase.sql.tables.Column;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index Member</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * IndexMember is an EObject.  It does not have a name or associated SQL descriptor so it is not an SQLObject.  This is the way we have chosen to model Index columns because EMF does not support association classes.
 * The "expression" reference was added to support function/expression-based indexes.  The expression relationship and the column relationship should be mutually exclusive; that is, one and only one of these should be set.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getIncrementType <em>Increment Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getColumn <em>Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndexMember()
 * @model
 * @generated
 */
public interface IndexMember extends SQLObject{
	/**
	 * Returns the value of the '<em><b>Increment Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.constraints.IncrementType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Increment Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Increment Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.IncrementType
	 * @see #setIncrementType(IncrementType)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndexMember_IncrementType()
	 * @model
	 * @generated
	 */
	IncrementType getIncrementType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getIncrementType <em>Increment Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Increment Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.IncrementType
	 * @see #getIncrementType()
	 * @generated
	 */
	void setIncrementType(IncrementType value);

	/**
	 * Returns the value of the '<em><b>Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column</em>' reference.
	 * @see #setColumn(Column)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndexMember_Column()
	 * @model
	 * @generated
	 */
	Column getColumn();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getColumn <em>Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column</em>' reference.
	 * @see #getColumn()
	 * @generated
	 */
	void setColumn(Column value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(IndexExpression)
	 * @see org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage#getIndexMember_Expression()
	 * @model containment="true"
	 * @generated
	 */
	IndexExpression getExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(IndexExpression value);

} // IndexMember
