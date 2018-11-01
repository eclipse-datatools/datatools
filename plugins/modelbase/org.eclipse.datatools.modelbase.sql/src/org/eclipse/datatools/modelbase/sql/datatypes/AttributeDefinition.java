/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.datatypes;

import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.13 Columns, fields, and attributes
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#getScopeCheck <em>Scope Check</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#isScopeChecked <em>Scope Checked</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#getDefaultValue <em>Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getAttributeDefinition()
 * @model
 * @generated
 */
public interface AttributeDefinition extends TypedElement {
	/**
	 * Returns the value of the '<em><b>Scope Check</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope Check</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope Check</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @see #setScopeCheck(ReferentialActionType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getAttributeDefinition_ScopeCheck()
	 * @model
	 * @generated
	 */
	ReferentialActionType getScopeCheck();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#getScopeCheck <em>Scope Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope Check</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType
	 * @see #getScopeCheck()
	 * @generated
	 */
	void setScopeCheck(ReferentialActionType value);

	/**
	 * Returns the value of the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope Checked</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope Checked</em>' attribute.
	 * @see #setScopeChecked(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getAttributeDefinition_ScopeChecked()
	 * @model
	 * @generated
	 */
	boolean isScopeChecked();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#isScopeChecked <em>Scope Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope Checked</em>' attribute.
	 * @see #isScopeChecked()
	 * @generated
	 */
	void setScopeChecked(boolean value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(String)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getAttributeDefinition_DefaultValue()
	 * @model
	 * @generated
	 */
	String getDefaultValue();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(String value);

} // AttributeDefinition
