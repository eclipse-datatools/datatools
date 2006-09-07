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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structured User Defined Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.7 User-defined types
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#isInstantiable <em>Instantiable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSuper <em>Super</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSub <em>Sub</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getMethods <em>Methods</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getStructuredUserDefinedType()
 * @model
 * @generated
 */
public interface StructuredUserDefinedType extends UserDefinedType {
	/**
	 * Returns the value of the '<em><b>Instantiable</b></em>' attribute.
	 * The default value is <code>"True"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instantiable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instantiable</em>' attribute.
	 * @see #setInstantiable(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getStructuredUserDefinedType_Instantiable()
	 * @model default="True"
	 * @generated
	 */
	boolean isInstantiable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#isInstantiable <em>Instantiable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instantiable</em>' attribute.
	 * @see #isInstantiable()
	 * @generated
	 */
	void setInstantiable(boolean value);

	/**
	 * Returns the value of the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Final</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Final</em>' attribute.
	 * @see #setFinal(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getStructuredUserDefinedType_Final()
	 * @model
	 * @generated
	 */
	boolean isFinal();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#isFinal <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final</em>' attribute.
	 * @see #isFinal()
	 * @generated
	 */
	void setFinal(boolean value);

	/**
	 * Returns the value of the '<em><b>Super</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSub <em>Sub</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super</em>' reference.
	 * @see #setSuper(StructuredUserDefinedType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getStructuredUserDefinedType_Super()
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSub
	 * @model opposite="sub"
	 * @generated
	 */
	StructuredUserDefinedType getSuper();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSuper <em>Super</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super</em>' reference.
	 * @see #getSuper()
	 * @generated
	 */
	void setSuper(StructuredUserDefinedType value);

	/**
	 * Returns the value of the '<em><b>Sub</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSuper <em>Super</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getStructuredUserDefinedType_Sub()
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType#getSuper
	 * @model type="org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType" opposite="super"
	 * @generated
	 */
	EList getSub();

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getStructuredUserDefinedType_Attributes()
	 * @model type="org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition" containment="true"
	 * @generated
	 */
	EList getAttributes();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.routines.Method}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getStructuredUserDefinedType_Methods()
	 * @model type="org.eclipse.datatools.modelbase.sql.routines.Method" containment="true"
	 * @generated
	 */
	EList getMethods();

} // StructuredUserDefinedType
