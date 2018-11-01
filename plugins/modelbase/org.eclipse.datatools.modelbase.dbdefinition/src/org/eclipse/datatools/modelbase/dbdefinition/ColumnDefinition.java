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
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#getIdentityColumnDataTypeDefinitions <em>Identity Column Data Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentitySupported <em>Identity Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isComputedSupported <em>Computed Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityStartValueSupported <em>Identity Start Value Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityIncrementSupported <em>Identity Increment Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityMinimumSupported <em>Identity Minimum Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityMaximumSupported <em>Identity Maximum Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityCycleSupported <em>Identity Cycle Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition()
 * @model
 * @generated
 */
public interface ColumnDefinition extends EObject{
	/**
	 * Returns the value of the '<em><b>Identity Column Data Type Definitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Column Data Type Definitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Column Data Type Definitions</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition_IdentityColumnDataTypeDefinitions()
	 * @model type="org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition" required="true"
	 * @generated
	 */
	EList getIdentityColumnDataTypeDefinitions();

	/**
	 * Returns the value of the '<em><b>Identity Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Supported</em>' attribute.
	 * @see #setIdentitySupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition_IdentitySupported()
	 * @model
	 * @generated
	 */
	boolean isIdentitySupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentitySupported <em>Identity Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity Supported</em>' attribute.
	 * @see #isIdentitySupported()
	 * @generated
	 */
	void setIdentitySupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Computed Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Computed Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Computed Supported</em>' attribute.
	 * @see #setComputedSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition_ComputedSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isComputedSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isComputedSupported <em>Computed Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Computed Supported</em>' attribute.
	 * @see #isComputedSupported()
	 * @generated
	 */
	void setComputedSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Identity Start Value Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Start Value Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Start Value Supported</em>' attribute.
	 * @see #setIdentityStartValueSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition_IdentityStartValueSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isIdentityStartValueSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityStartValueSupported <em>Identity Start Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity Start Value Supported</em>' attribute.
	 * @see #isIdentityStartValueSupported()
	 * @generated
	 */
	void setIdentityStartValueSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Identity Increment Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Increment Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Increment Supported</em>' attribute.
	 * @see #setIdentityIncrementSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition_IdentityIncrementSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isIdentityIncrementSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityIncrementSupported <em>Identity Increment Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity Increment Supported</em>' attribute.
	 * @see #isIdentityIncrementSupported()
	 * @generated
	 */
	void setIdentityIncrementSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Identity Minimum Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Minimum Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Minimum Supported</em>' attribute.
	 * @see #setIdentityMinimumSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition_IdentityMinimumSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isIdentityMinimumSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityMinimumSupported <em>Identity Minimum Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity Minimum Supported</em>' attribute.
	 * @see #isIdentityMinimumSupported()
	 * @generated
	 */
	void setIdentityMinimumSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Identity Maximum Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Maximum Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Maximum Supported</em>' attribute.
	 * @see #setIdentityMaximumSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition_IdentityMaximumSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isIdentityMaximumSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityMaximumSupported <em>Identity Maximum Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity Maximum Supported</em>' attribute.
	 * @see #isIdentityMaximumSupported()
	 * @generated
	 */
	void setIdentityMaximumSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Identity Cycle Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Cycle Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Cycle Supported</em>' attribute.
	 * @see #setIdentityCycleSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition_IdentityCycleSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isIdentityCycleSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#isIdentityCycleSupported <em>Identity Cycle Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity Cycle Supported</em>' attribute.
	 * @see #isIdentityCycleSupported()
	 * @generated
	 */
	void setIdentityCycleSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Identifier Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #setMaximumIdentifierLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getColumnDefinition_MaximumIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 */
	void setMaximumIdentifierLength(int value);

} // ColumnDefinition
