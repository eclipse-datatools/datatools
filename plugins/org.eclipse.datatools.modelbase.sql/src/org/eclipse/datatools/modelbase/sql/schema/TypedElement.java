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

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Typed Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.TypedElement#getContainedType <em>Contained Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.TypedElement#getReferencedType <em>Referenced Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getTypedElement()
 * @model abstract="true"
 * @generated
 */
public interface TypedElement extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contained Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contained Type</em>' containment reference.
	 * @see #setContainedType(SQLDataType)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getTypedElement_ContainedType()
	 * @model containment="true"
	 * @generated
	 */
	SQLDataType getContainedType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.TypedElement#getContainedType <em>Contained Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contained Type</em>' containment reference.
	 * @see #getContainedType()
	 * @generated
	 */
	void setContainedType(SQLDataType value);

	/**
	 * Returns the value of the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Type</em>' reference.
	 * @see #setReferencedType(UserDefinedType)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getTypedElement_ReferencedType()
	 * @model
	 * @generated
	 */
	UserDefinedType getReferencedType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.TypedElement#getReferencedType <em>Referenced Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Type</em>' reference.
	 * @see #getReferencedType()
	 * @generated
	 */
	void setReferencedType(UserDefinedType value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set the type for this TypedElement
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void setDataType(DataType newType);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Get the type for this TypedElement
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	DataType getDataType();

} // TypedElement
