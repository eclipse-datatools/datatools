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
 * A representation of the model object '<em><b>Sequence Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getPredefinedDataTypeDefinitions <em>Predefined Data Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getDefaultDataTypeDefinition <em>Default Data Type Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isTypeEnumerationSupported <em>Type Enumeration Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isCacheSupported <em>Cache Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isOrderSupported <em>Order Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoMaximumValueString <em>No Maximum Value String</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoMinimumValueString <em>No Minimum Value String</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoCacheString <em>No Cache String</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getCacheDefaultValue <em>Cache Default Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition()
 * @model
 * @generated
 */
public interface SequenceDefinition extends EObject{
	/**
	 * Returns the value of the '<em><b>Predefined Data Type Definitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predefined Data Type Definitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predefined Data Type Definitions</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition_PredefinedDataTypeDefinitions()
	 * @model type="org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition" required="true"
	 * @generated
	 */
	EList getPredefinedDataTypeDefinitions();

	/**
	 * Returns the value of the '<em><b>Type Enumeration Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Enumeration Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Enumeration Supported</em>' attribute.
	 * @see #setTypeEnumerationSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition_TypeEnumerationSupported()
	 * @model
	 * @generated
	 */
	boolean isTypeEnumerationSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isTypeEnumerationSupported <em>Type Enumeration Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Enumeration Supported</em>' attribute.
	 * @see #isTypeEnumerationSupported()
	 * @generated
	 */
	void setTypeEnumerationSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Cache Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cache Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache Supported</em>' attribute.
	 * @see #setCacheSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition_CacheSupported()
	 * @model
	 * @generated
	 */
	boolean isCacheSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isCacheSupported <em>Cache Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache Supported</em>' attribute.
	 * @see #isCacheSupported()
	 * @generated
	 */
	void setCacheSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Order Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order Supported</em>' attribute.
	 * @see #setOrderSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition_OrderSupported()
	 * @model
	 * @generated
	 */
	boolean isOrderSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#isOrderSupported <em>Order Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order Supported</em>' attribute.
	 * @see #isOrderSupported()
	 * @generated
	 */
	void setOrderSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>No Maximum Value String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>No Maximum Value String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>No Maximum Value String</em>' attribute.
	 * @see #setNoMaximumValueString(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition_NoMaximumValueString()
	 * @model
	 * @generated
	 */
	String getNoMaximumValueString();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoMaximumValueString <em>No Maximum Value String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No Maximum Value String</em>' attribute.
	 * @see #getNoMaximumValueString()
	 * @generated
	 */
	void setNoMaximumValueString(String value);

	/**
	 * Returns the value of the '<em><b>No Minimum Value String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>No Minimum Value String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>No Minimum Value String</em>' attribute.
	 * @see #setNoMinimumValueString(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition_NoMinimumValueString()
	 * @model
	 * @generated
	 */
	String getNoMinimumValueString();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoMinimumValueString <em>No Minimum Value String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No Minimum Value String</em>' attribute.
	 * @see #getNoMinimumValueString()
	 * @generated
	 */
	void setNoMinimumValueString(String value);

	/**
	 * Returns the value of the '<em><b>No Cache String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>No Cache String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>No Cache String</em>' attribute.
	 * @see #setNoCacheString(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition_NoCacheString()
	 * @model
	 * @generated
	 */
	String getNoCacheString();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getNoCacheString <em>No Cache String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>No Cache String</em>' attribute.
	 * @see #getNoCacheString()
	 * @generated
	 */
	void setNoCacheString(String value);

	/**
	 * Returns the value of the '<em><b>Cache Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cache Default Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache Default Value</em>' attribute.
	 * @see #setCacheDefaultValue(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition_CacheDefaultValue()
	 * @model
	 * @generated
	 */
	int getCacheDefaultValue();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getCacheDefaultValue <em>Cache Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache Default Value</em>' attribute.
	 * @see #getCacheDefaultValue()
	 * @generated
	 */
	void setCacheDefaultValue(int value);

	/**
	 * Returns the value of the '<em><b>Default Data Type Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Data Type Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Data Type Definition</em>' reference.
	 * @see #setDefaultDataTypeDefinition(PredefinedDataTypeDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getSequenceDefinition_DefaultDataTypeDefinition()
	 * @model required="true"
	 * @generated
	 */
	PredefinedDataTypeDefinition getDefaultDataTypeDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition#getDefaultDataTypeDefinition <em>Default Data Type Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Data Type Definition</em>' reference.
	 * @see #getDefaultDataTypeDefinition()
	 * @generated
	 */
	void setDefaultDataTypeDefinition(PredefinedDataTypeDefinition value);

} // SequenceDefinition
