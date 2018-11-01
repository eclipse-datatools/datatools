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
 * A representation of the model object '<em><b>Table Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isAuditSupported <em>Audit Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isDataCaptureSupported <em>Data Capture Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isEditProcSupported <em>Edit Proc Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isEncodingSupported <em>Encoding Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isValidProcSupported <em>Valid Proc Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableDefinition()
 * @model
 * @generated
 */
public interface TableDefinition extends EObject{
	/**
	 * Returns the value of the '<em><b>Audit Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Audit Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Audit Supported</em>' attribute.
	 * @see #setAuditSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableDefinition_AuditSupported()
	 * @model
	 * @generated
	 */
	boolean isAuditSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isAuditSupported <em>Audit Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Audit Supported</em>' attribute.
	 * @see #isAuditSupported()
	 * @generated
	 */
	void setAuditSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Data Capture Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Capture Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Capture Supported</em>' attribute.
	 * @see #setDataCaptureSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableDefinition_DataCaptureSupported()
	 * @model
	 * @generated
	 */
	boolean isDataCaptureSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isDataCaptureSupported <em>Data Capture Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Capture Supported</em>' attribute.
	 * @see #isDataCaptureSupported()
	 * @generated
	 */
	void setDataCaptureSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Edit Proc Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edit Proc Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edit Proc Supported</em>' attribute.
	 * @see #setEditProcSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableDefinition_EditProcSupported()
	 * @model
	 * @generated
	 */
	boolean isEditProcSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isEditProcSupported <em>Edit Proc Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Edit Proc Supported</em>' attribute.
	 * @see #isEditProcSupported()
	 * @generated
	 */
	void setEditProcSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Encoding Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encoding Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encoding Supported</em>' attribute.
	 * @see #setEncodingSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableDefinition_EncodingSupported()
	 * @model
	 * @generated
	 */
	boolean isEncodingSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isEncodingSupported <em>Encoding Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoding Supported</em>' attribute.
	 * @see #isEncodingSupported()
	 * @generated
	 */
	void setEncodingSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Valid Proc Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid Proc Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid Proc Supported</em>' attribute.
	 * @see #setValidProcSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableDefinition_ValidProcSupported()
	 * @model
	 * @generated
	 */
	boolean isValidProcSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#isValidProcSupported <em>Valid Proc Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valid Proc Supported</em>' attribute.
	 * @see #isValidProcSupported()
	 * @generated
	 */
	void setValidProcSupported(boolean value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getTableDefinition_MaximumIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 */
	void setMaximumIdentifierLength(int value);

} // TableDefinition
