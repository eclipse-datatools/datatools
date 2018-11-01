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
 * A representation of the model object '<em><b>Table Space Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isTypeSupported <em>Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isExtentSizeSupported <em>Extent Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isPrefetchSizeSupported <em>Prefetch Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isManagedBySupported <em>Managed By Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isPageSizeSupported <em>Page Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isBufferPoolSupported <em>Buffer Pool Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isDefaultSupported <em>Default Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerMaximumSizeSupported <em>Container Maximum Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerInitialSizeSupported <em>Container Initial Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerExtentSizeSupported <em>Container Extent Size Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#getTableSpaceType <em>Table Space Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition()
 * @model
 * @generated
 */
public interface TableSpaceDefinition extends EObject{
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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_TypeSupported()
	 * @model
	 * @generated
	 */
	boolean isTypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isTypeSupported <em>Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Supported</em>' attribute.
	 * @see #isTypeSupported()
	 * @generated
	 */
	void setTypeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Extent Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extent Size Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extent Size Supported</em>' attribute.
	 * @see #setExtentSizeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_ExtentSizeSupported()
	 * @model
	 * @generated
	 */
	boolean isExtentSizeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isExtentSizeSupported <em>Extent Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extent Size Supported</em>' attribute.
	 * @see #isExtentSizeSupported()
	 * @generated
	 */
	void setExtentSizeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Prefetch Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prefetch Size Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prefetch Size Supported</em>' attribute.
	 * @see #setPrefetchSizeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_PrefetchSizeSupported()
	 * @model
	 * @generated
	 */
	boolean isPrefetchSizeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isPrefetchSizeSupported <em>Prefetch Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prefetch Size Supported</em>' attribute.
	 * @see #isPrefetchSizeSupported()
	 * @generated
	 */
	void setPrefetchSizeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Managed By Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed By Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Managed By Supported</em>' attribute.
	 * @see #setManagedBySupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_ManagedBySupported()
	 * @model
	 * @generated
	 */
	boolean isManagedBySupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isManagedBySupported <em>Managed By Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managed By Supported</em>' attribute.
	 * @see #isManagedBySupported()
	 * @generated
	 */
	void setManagedBySupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Page Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page Size Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page Size Supported</em>' attribute.
	 * @see #setPageSizeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_PageSizeSupported()
	 * @model
	 * @generated
	 */
	boolean isPageSizeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isPageSizeSupported <em>Page Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Size Supported</em>' attribute.
	 * @see #isPageSizeSupported()
	 * @generated
	 */
	void setPageSizeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Buffer Pool Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Buffer Pool Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Buffer Pool Supported</em>' attribute.
	 * @see #setBufferPoolSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_BufferPoolSupported()
	 * @model
	 * @generated
	 */
	boolean isBufferPoolSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isBufferPoolSupported <em>Buffer Pool Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Buffer Pool Supported</em>' attribute.
	 * @see #isBufferPoolSupported()
	 * @generated
	 */
	void setBufferPoolSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Default Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Supported</em>' attribute.
	 * @see #setDefaultSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_DefaultSupported()
	 * @model
	 * @generated
	 */
	boolean isDefaultSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isDefaultSupported <em>Default Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Supported</em>' attribute.
	 * @see #isDefaultSupported()
	 * @generated
	 */
	void setDefaultSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Container Maximum Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container Maximum Size Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container Maximum Size Supported</em>' attribute.
	 * @see #setContainerMaximumSizeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_ContainerMaximumSizeSupported()
	 * @model
	 * @generated
	 */
	boolean isContainerMaximumSizeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerMaximumSizeSupported <em>Container Maximum Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container Maximum Size Supported</em>' attribute.
	 * @see #isContainerMaximumSizeSupported()
	 * @generated
	 */
	void setContainerMaximumSizeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Container Initial Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container Initial Size Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container Initial Size Supported</em>' attribute.
	 * @see #setContainerInitialSizeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_ContainerInitialSizeSupported()
	 * @model
	 * @generated
	 */
	boolean isContainerInitialSizeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerInitialSizeSupported <em>Container Initial Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container Initial Size Supported</em>' attribute.
	 * @see #isContainerInitialSizeSupported()
	 * @generated
	 */
	void setContainerInitialSizeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Container Extent Size Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container Extent Size Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container Extent Size Supported</em>' attribute.
	 * @see #setContainerExtentSizeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_ContainerExtentSizeSupported()
	 * @model
	 * @generated
	 */
	boolean isContainerExtentSizeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#isContainerExtentSizeSupported <em>Container Extent Size Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container Extent Size Supported</em>' attribute.
	 * @see #isContainerExtentSizeSupported()
	 * @generated
	 */
	void setContainerExtentSizeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Table Space Type</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType}.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Space Type</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Space Type</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_TableSpaceType()
	 * @model dataType="org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType"
	 * @generated
	 */
	EList getTableSpaceType();

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableSpaceDefinition_MaximumIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 */
	void setMaximumIdentifierLength(int value);

} // TableSpaceDefinition
