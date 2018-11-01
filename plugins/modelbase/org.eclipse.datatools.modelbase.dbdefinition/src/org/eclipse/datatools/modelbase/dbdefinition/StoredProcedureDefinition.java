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
 * A representation of the model object '<em><b>Stored Procedure Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getPredefinedDataTypeDefinitions <em>Predefined Data Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isNullInputActionSupported <em>Null Input Action Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isPackageGenerationSupported <em>Package Generation Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isDetermininsticSupported <em>Determininstic Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnedNullSupported <em>Returned Null Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnedTypeDeclarationConstraintSupported <em>Returned Type Declaration Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterInitValueSupported <em>Parameter Init Value Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterStyleSupported <em>Parameter Style Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnTypeSupported <em>Return Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterDeclarationConstraintSupported <em>Parameter Declaration Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getMaximumActionBodyLength <em>Maximum Action Body Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getParameterStyle <em>Parameter Style</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getLanguageType <em>Language Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getFunctionLanguageType <em>Function Language Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getProcedureType <em>Procedure Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition()
 * @model
 * @generated
 */
public interface StoredProcedureDefinition extends EObject{
	/**
	 * Returns the value of the '<em><b>Predefined Data Type Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predefined Data Type Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predefined Data Type Definitions</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_PredefinedDataTypeDefinitions()
	 * @model type="org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition" containment="true"
	 * @generated
	 */
	EList getPredefinedDataTypeDefinitions();

	/**
	 * Returns the value of the '<em><b>Null Input Action Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Null Input Action Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Null Input Action Supported</em>' attribute.
	 * @see #setNullInputActionSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_NullInputActionSupported()
	 * @model
	 * @generated
	 */
	boolean isNullInputActionSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isNullInputActionSupported <em>Null Input Action Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Null Input Action Supported</em>' attribute.
	 * @see #isNullInputActionSupported()
	 * @generated
	 */
	void setNullInputActionSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Package Generation Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Generation Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Generation Supported</em>' attribute.
	 * @see #setPackageGenerationSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_PackageGenerationSupported()
	 * @model
	 * @generated
	 */
	boolean isPackageGenerationSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isPackageGenerationSupported <em>Package Generation Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Generation Supported</em>' attribute.
	 * @see #isPackageGenerationSupported()
	 * @generated
	 */
	void setPackageGenerationSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Determininstic Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Determininstic Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Determininstic Supported</em>' attribute.
	 * @see #setDetermininsticSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_DetermininsticSupported()
	 * @model
	 * @generated
	 */
	boolean isDetermininsticSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isDetermininsticSupported <em>Determininstic Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Determininstic Supported</em>' attribute.
	 * @see #isDetermininsticSupported()
	 * @generated
	 */
	void setDetermininsticSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Returned Null Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Returned Null Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Returned Null Supported</em>' attribute.
	 * @see #setReturnedNullSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_ReturnedNullSupported()
	 * @model
	 * @generated
	 */
	boolean isReturnedNullSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnedNullSupported <em>Returned Null Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Returned Null Supported</em>' attribute.
	 * @see #isReturnedNullSupported()
	 * @generated
	 */
	void setReturnedNullSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Returned Type Declaration Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Returned Type Declaration Constraint Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Returned Type Declaration Constraint Supported</em>' attribute.
	 * @see #setReturnedTypeDeclarationConstraintSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_ReturnedTypeDeclarationConstraintSupported()
	 * @model
	 * @generated
	 */
	boolean isReturnedTypeDeclarationConstraintSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnedTypeDeclarationConstraintSupported <em>Returned Type Declaration Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Returned Type Declaration Constraint Supported</em>' attribute.
	 * @see #isReturnedTypeDeclarationConstraintSupported()
	 * @generated
	 */
	void setReturnedTypeDeclarationConstraintSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Parameter Init Value Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Init Value Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Init Value Supported</em>' attribute.
	 * @see #setParameterInitValueSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_ParameterInitValueSupported()
	 * @model
	 * @generated
	 */
	boolean isParameterInitValueSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterInitValueSupported <em>Parameter Init Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Init Value Supported</em>' attribute.
	 * @see #isParameterInitValueSupported()
	 * @generated
	 */
	void setParameterInitValueSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Parameter Style Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Style Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Style Supported</em>' attribute.
	 * @see #setParameterStyleSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_ParameterStyleSupported()
	 * @model
	 * @generated
	 */
	boolean isParameterStyleSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterStyleSupported <em>Parameter Style Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Style Supported</em>' attribute.
	 * @see #isParameterStyleSupported()
	 * @generated
	 */
	void setParameterStyleSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Return Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type Supported</em>' attribute.
	 * @see #setReturnTypeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_ReturnTypeSupported()
	 * @model
	 * @generated
	 */
	boolean isReturnTypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isReturnTypeSupported <em>Return Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type Supported</em>' attribute.
	 * @see #isReturnTypeSupported()
	 * @generated
	 */
	void setReturnTypeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Parameter Declaration Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Declaration Constraint Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Declaration Constraint Supported</em>' attribute.
	 * @see #setParameterDeclarationConstraintSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_ParameterDeclarationConstraintSupported()
	 * @model
	 * @generated
	 */
	boolean isParameterDeclarationConstraintSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#isParameterDeclarationConstraintSupported <em>Parameter Declaration Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Declaration Constraint Supported</em>' attribute.
	 * @see #isParameterDeclarationConstraintSupported()
	 * @generated
	 */
	void setParameterDeclarationConstraintSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Maximum Action Body Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Action Body Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Action Body Length</em>' attribute.
	 * @see #setMaximumActionBodyLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_MaximumActionBodyLength()
	 * @model
	 * @generated
	 */
	int getMaximumActionBodyLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getMaximumActionBodyLength <em>Maximum Action Body Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Action Body Length</em>' attribute.
	 * @see #getMaximumActionBodyLength()
	 * @generated
	 */
	void setMaximumActionBodyLength(int value);

	/**
	 * Returns the value of the '<em><b>Parameter Style</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle}.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Style</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Style</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_ParameterStyle()
	 * @model dataType="org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle"
	 * @generated
	 */
	EList getParameterStyle();

	/**
	 * Returns the value of the '<em><b>Language Type</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.LanguageType}.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.LanguageType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language Type</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language Type</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.LanguageType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_LanguageType()
	 * @model dataType="org.eclipse.datatools.modelbase.dbdefinition.LanguageType"
	 * @generated
	 */
	EList getLanguageType();

	/**
	 * Returns the value of the '<em><b>Function Language Type</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.LanguageType}.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.LanguageType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Language Type</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Language Type</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.LanguageType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_FunctionLanguageType()
	 * @model dataType="org.eclipse.datatools.modelbase.dbdefinition.LanguageType"
	 * @generated
	 */
	EList getFunctionLanguageType();

	/**
	 * Returns the value of the '<em><b>Procedure Type</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.ProcedureType}.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.ProcedureType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Procedure Type</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Procedure Type</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ProcedureType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_ProcedureType()
	 * @model dataType="org.eclipse.datatools.modelbase.dbdefinition.ProcedureType"
	 * @generated
	 */
	EList getProcedureType();

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getStoredProcedureDefinition_MaximumIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 */
	void setMaximumIdentifierLength(int value);

} // StoredProcedureDefinition
