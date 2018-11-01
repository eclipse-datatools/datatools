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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trigger Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumReferencePartLength <em>Maximum Reference Part Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumActionBodyLength <em>Maximum Action Body Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isTypeSupported <em>Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isWhenClauseSupported <em>When Clause Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isGranularitySupported <em>Granularity Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isReferencesClauseSupported <em>References Clause Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isPerColumnUpdateTriggerSupported <em>Per Column Update Trigger Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isInsteadOfTriggerSupported <em>Instead Of Trigger Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isRowTriggerReferenceSupported <em>Row Trigger Reference Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isTableTriggerReferenceSupported <em>Table Trigger Reference Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition()
 * @model
 * @generated
 */
public interface TriggerDefinition extends EObject{
	/**
	 * Returns the value of the '<em><b>Maximum Reference Part Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Reference Part Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Reference Part Length</em>' attribute.
	 * @see #setMaximumReferencePartLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_MaximumReferencePartLength()
	 * @model
	 * @generated
	 */
	int getMaximumReferencePartLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumReferencePartLength <em>Maximum Reference Part Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Reference Part Length</em>' attribute.
	 * @see #getMaximumReferencePartLength()
	 * @generated
	 */
	void setMaximumReferencePartLength(int value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_MaximumActionBodyLength()
	 * @model
	 * @generated
	 */
	int getMaximumActionBodyLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumActionBodyLength <em>Maximum Action Body Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Action Body Length</em>' attribute.
	 * @see #getMaximumActionBodyLength()
	 * @generated
	 */
	void setMaximumActionBodyLength(int value);

	/**
	 * Returns the value of the '<em><b>Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Supported</em>' attribute.
	 * @see #setTypeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_TypeSupported()
	 * @model
	 * @generated
	 */
	boolean isTypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isTypeSupported <em>Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Supported</em>' attribute.
	 * @see #isTypeSupported()
	 * @generated
	 */
	void setTypeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>When Clause Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>When Clause Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When Clause Supported</em>' attribute.
	 * @see #setWhenClauseSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_WhenClauseSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isWhenClauseSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isWhenClauseSupported <em>When Clause Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When Clause Supported</em>' attribute.
	 * @see #isWhenClauseSupported()
	 * @generated
	 */
	void setWhenClauseSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Granularity Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Granularity Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Granularity Supported</em>' attribute.
	 * @see #setGranularitySupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_GranularitySupported()
	 * @model
	 * @generated
	 */
	boolean isGranularitySupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isGranularitySupported <em>Granularity Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Granularity Supported</em>' attribute.
	 * @see #isGranularitySupported()
	 * @generated
	 */
	void setGranularitySupported(boolean value);

	/**
	 * Returns the value of the '<em><b>References Clause Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>References Clause Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>References Clause Supported</em>' attribute.
	 * @see #setReferencesClauseSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_ReferencesClauseSupported()
	 * @model
	 * @generated
	 */
	boolean isReferencesClauseSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isReferencesClauseSupported <em>References Clause Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>References Clause Supported</em>' attribute.
	 * @see #isReferencesClauseSupported()
	 * @generated
	 */
	void setReferencesClauseSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Per Column Update Trigger Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Per Column Update Trigger Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Per Column Update Trigger Supported</em>' attribute.
	 * @see #setPerColumnUpdateTriggerSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_PerColumnUpdateTriggerSupported()
	 * @model
	 * @generated
	 */
	boolean isPerColumnUpdateTriggerSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isPerColumnUpdateTriggerSupported <em>Per Column Update Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Per Column Update Trigger Supported</em>' attribute.
	 * @see #isPerColumnUpdateTriggerSupported()
	 * @generated
	 */
	void setPerColumnUpdateTriggerSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Instead Of Trigger Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instead Of Trigger Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instead Of Trigger Supported</em>' attribute.
	 * @see #setInsteadOfTriggerSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_InsteadOfTriggerSupported()
	 * @model
	 * @generated
	 */
	boolean isInsteadOfTriggerSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isInsteadOfTriggerSupported <em>Instead Of Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instead Of Trigger Supported</em>' attribute.
	 * @see #isInsteadOfTriggerSupported()
	 * @generated
	 */
	void setInsteadOfTriggerSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Row Trigger Reference Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Trigger Reference Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Trigger Reference Supported</em>' attribute.
	 * @see #setRowTriggerReferenceSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_RowTriggerReferenceSupported()
	 * @model
	 * @generated
	 */
	boolean isRowTriggerReferenceSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isRowTriggerReferenceSupported <em>Row Trigger Reference Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Trigger Reference Supported</em>' attribute.
	 * @see #isRowTriggerReferenceSupported()
	 * @generated
	 */
	void setRowTriggerReferenceSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Table Trigger Reference Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Trigger Reference Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Trigger Reference Supported</em>' attribute.
	 * @see #setTableTriggerReferenceSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_TableTriggerReferenceSupported()
	 * @model
	 * @generated
	 */
	boolean isTableTriggerReferenceSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#isTableTriggerReferenceSupported <em>Table Trigger Reference Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Trigger Reference Supported</em>' attribute.
	 * @see #isTableTriggerReferenceSupported()
	 * @generated
	 */
	void setTableTriggerReferenceSupported(boolean value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTriggerDefinition_MaximumIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 */
	void setMaximumIdentifierLength(int value);

} // TriggerDefinition
