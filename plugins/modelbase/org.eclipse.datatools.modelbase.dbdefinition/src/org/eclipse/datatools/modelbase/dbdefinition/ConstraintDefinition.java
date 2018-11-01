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
 * A representation of the model object '<em><b>Constraint Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isDeferrableConstraintSupported <em>Deferrable Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isInformationalConstraintSupported <em>Informational Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isClusteredPrimaryKeySupported <em>Clustered Primary Key Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isClusteredUniqueConstraintSupported <em>Clustered Unique Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isPrimaryKeyNullable <em>Primary Key Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isUniqueKeyNullable <em>Unique Key Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumCheckExpressionLength <em>Maximum Check Expression Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getParentUpdateDRIRuleType <em>Parent Update DRI Rule Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getParentDeleteDRIRuleType <em>Parent Delete DRI Rule Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getCheckOption <em>Check Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumPrimaryKeyIdentifierLength <em>Maximum Primary Key Identifier Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumForeignKeyIdentifierLength <em>Maximum Foreign Key Identifier Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumCheckConstraintIdentifierLength <em>Maximum Check Constraint Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition()
 * @model
 * @generated
 */
public interface ConstraintDefinition extends EObject{
	/**
	 * Returns the value of the '<em><b>Deferrable Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deferrable Constraint Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deferrable Constraint Supported</em>' attribute.
	 * @see #setDeferrableConstraintSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_DeferrableConstraintSupported()
	 * @model
	 * @generated
	 */
	boolean isDeferrableConstraintSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isDeferrableConstraintSupported <em>Deferrable Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deferrable Constraint Supported</em>' attribute.
	 * @see #isDeferrableConstraintSupported()
	 * @generated
	 */
	void setDeferrableConstraintSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Informational Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Informational Constraint Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Informational Constraint Supported</em>' attribute.
	 * @see #setInformationalConstraintSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_InformationalConstraintSupported()
	 * @model
	 * @generated
	 */
	boolean isInformationalConstraintSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isInformationalConstraintSupported <em>Informational Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Informational Constraint Supported</em>' attribute.
	 * @see #isInformationalConstraintSupported()
	 * @generated
	 */
	void setInformationalConstraintSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Clustered Primary Key Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clustered Primary Key Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clustered Primary Key Supported</em>' attribute.
	 * @see #setClusteredPrimaryKeySupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_ClusteredPrimaryKeySupported()
	 * @model
	 * @generated
	 */
	boolean isClusteredPrimaryKeySupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isClusteredPrimaryKeySupported <em>Clustered Primary Key Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clustered Primary Key Supported</em>' attribute.
	 * @see #isClusteredPrimaryKeySupported()
	 * @generated
	 */
	void setClusteredPrimaryKeySupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Clustered Unique Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clustered Unique Constraint Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clustered Unique Constraint Supported</em>' attribute.
	 * @see #setClusteredUniqueConstraintSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_ClusteredUniqueConstraintSupported()
	 * @model
	 * @generated
	 */
	boolean isClusteredUniqueConstraintSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isClusteredUniqueConstraintSupported <em>Clustered Unique Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clustered Unique Constraint Supported</em>' attribute.
	 * @see #isClusteredUniqueConstraintSupported()
	 * @generated
	 */
	void setClusteredUniqueConstraintSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Primary Key Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Key Nullable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Key Nullable</em>' attribute.
	 * @see #setPrimaryKeyNullable(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_PrimaryKeyNullable()
	 * @model
	 * @generated
	 */
	boolean isPrimaryKeyNullable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isPrimaryKeyNullable <em>Primary Key Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Key Nullable</em>' attribute.
	 * @see #isPrimaryKeyNullable()
	 * @generated
	 */
	void setPrimaryKeyNullable(boolean value);

	/**
	 * Returns the value of the '<em><b>Unique Key Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique Key Nullable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique Key Nullable</em>' attribute.
	 * @see #setUniqueKeyNullable(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_UniqueKeyNullable()
	 * @model
	 * @generated
	 */
	boolean isUniqueKeyNullable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#isUniqueKeyNullable <em>Unique Key Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique Key Nullable</em>' attribute.
	 * @see #isUniqueKeyNullable()
	 * @generated
	 */
	void setUniqueKeyNullable(boolean value);

	/**
	 * Returns the value of the '<em><b>Maximum Check Expression Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Check Expression Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Check Expression Length</em>' attribute.
	 * @see #setMaximumCheckExpressionLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_MaximumCheckExpressionLength()
	 * @model
	 * @generated
	 */
	int getMaximumCheckExpressionLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumCheckExpressionLength <em>Maximum Check Expression Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Check Expression Length</em>' attribute.
	 * @see #getMaximumCheckExpressionLength()
	 * @generated
	 */
	void setMaximumCheckExpressionLength(int value);

	/**
	 * Returns the value of the '<em><b>Parent Update DRI Rule Type</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType}.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Update DRI Rule Type</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Update DRI Rule Type</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_ParentUpdateDRIRuleType()
	 * @model dataType="org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType"
	 * @generated
	 */
	EList getParentUpdateDRIRuleType();

	/**
	 * Returns the value of the '<em><b>Parent Delete DRI Rule Type</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType}.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Delete DRI Rule Type</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Delete DRI Rule Type</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_ParentDeleteDRIRuleType()
	 * @model dataType="org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType"
	 * @generated
	 */
	EList getParentDeleteDRIRuleType();

	/**
	 * Returns the value of the '<em><b>Check Option</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.CheckOption}.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.CheckOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Check Option</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Check Option</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.CheckOption
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_CheckOption()
	 * @model dataType="org.eclipse.datatools.modelbase.dbdefinition.CheckOption"
	 * @generated
	 */
	EList getCheckOption();

	/**
	 * Returns the value of the '<em><b>Maximum Primary Key Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Primary Key Identifier Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Primary Key Identifier Length</em>' attribute.
	 * @see #setMaximumPrimaryKeyIdentifierLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_MaximumPrimaryKeyIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumPrimaryKeyIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumPrimaryKeyIdentifierLength <em>Maximum Primary Key Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Primary Key Identifier Length</em>' attribute.
	 * @see #getMaximumPrimaryKeyIdentifierLength()
	 * @generated
	 */
	void setMaximumPrimaryKeyIdentifierLength(int value);

	/**
	 * Returns the value of the '<em><b>Maximum Foreign Key Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Foreign Key Identifier Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Foreign Key Identifier Length</em>' attribute.
	 * @see #setMaximumForeignKeyIdentifierLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_MaximumForeignKeyIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumForeignKeyIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumForeignKeyIdentifierLength <em>Maximum Foreign Key Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Foreign Key Identifier Length</em>' attribute.
	 * @see #getMaximumForeignKeyIdentifierLength()
	 * @generated
	 */
	void setMaximumForeignKeyIdentifierLength(int value);

	/**
	 * Returns the value of the '<em><b>Maximum Check Constraint Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Check Constraint Identifier Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Check Constraint Identifier Length</em>' attribute.
	 * @see #setMaximumCheckConstraintIdentifierLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getConstraintDefinition_MaximumCheckConstraintIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumCheckConstraintIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition#getMaximumCheckConstraintIdentifierLength <em>Maximum Check Constraint Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Check Constraint Identifier Length</em>' attribute.
	 * @see #getMaximumCheckConstraintIdentifierLength()
	 * @generated
	 */
	void setMaximumCheckConstraintIdentifierLength(int value);

} // ConstraintDefinition
