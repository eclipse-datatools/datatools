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

import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.13 Columns, fields, and attributes
 * 
 * The terms column, field, and attribute refer to structural components of tables, row types, and structured types, respectively, in analogous fashion. As the structure of a table consists of one or more columns, so does the structure of a row type consist of one or more fields and that of a structured type one or more attributes. Every structural element, whether a column, a field, or an attribute, is primarily a name paired with a declared type.
 * 
 * The elements of a structure are ordered. Elements in different positions in the same structure can have the same declared type but not the same name. Although the elements of a structure are distinguished from each other by name, in some circumstances the compatibility of two structures (for the purpose at hand) is determined solely by considering the declared types of each pair of elements at the same ordinal position.
 * 
 * An attribute A is described by an attribute descriptor. An attribute descriptor includes:
 *  - The name of the attribute.
 *  - The data type descriptor of the declared type of A.
 *  - The ordinal position of A within the structured type that contains it.
 *  - The value of the implicit or explicit <attribute default> of A.
 *  - If the data type of the attribute is a reference type, then an indication of whether reference values shall be checked, and the <reference scope check action> , if specified or implied.
 *  - The name of the structured type defined by the <user-defined type definition> that defines A.
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
public interface AttributeDefinition extends TypedElement{
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
