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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Link Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a link to a file/location outside the system. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getLength <em>Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getLinkControl <em>Link Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getIntegrityControl <em>Integrity Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getReadPermission <em>Read Permission</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getWritePermission <em>Write Permission</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#isRecovery <em>Recovery</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getUnlink <em>Unlink</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataLinkDataType()
 * @model
 * @generated
 */
public interface DataLinkDataType extends PredefinedDataType {
	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(int)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataLinkDataType_Length()
	 * @model
	 * @generated
	 */
	int getLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(int value);

	/**
	 * Returns the value of the '<em><b>Link Control</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link Control</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link Control</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption
	 * @see #setLinkControl(LinkControlOption)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataLinkDataType_LinkControl()
	 * @model
	 * @generated
	 */
	LinkControlOption getLinkControl();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getLinkControl <em>Link Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Control</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption
	 * @see #getLinkControl()
	 * @generated
	 */
	void setLinkControl(LinkControlOption value);

	/**
	 * Returns the value of the '<em><b>Integrity Control</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Integrity Control</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Integrity Control</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption
	 * @see #setIntegrityControl(IntegrityControlOption)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataLinkDataType_IntegrityControl()
	 * @model
	 * @generated
	 */
	IntegrityControlOption getIntegrityControl();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getIntegrityControl <em>Integrity Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integrity Control</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption
	 * @see #getIntegrityControl()
	 * @generated
	 */
	void setIntegrityControl(IntegrityControlOption value);

	/**
	 * Returns the value of the '<em><b>Read Permission</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Read Permission</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Read Permission</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption
	 * @see #setReadPermission(ReadPermissionOption)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataLinkDataType_ReadPermission()
	 * @model
	 * @generated
	 */
	ReadPermissionOption getReadPermission();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getReadPermission <em>Read Permission</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Read Permission</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption
	 * @see #getReadPermission()
	 * @generated
	 */
	void setReadPermission(ReadPermissionOption value);

	/**
	 * Returns the value of the '<em><b>Write Permission</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Write Permission</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Write Permission</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption
	 * @see #setWritePermission(WritePermissionOption)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataLinkDataType_WritePermission()
	 * @model
	 * @generated
	 */
	WritePermissionOption getWritePermission();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getWritePermission <em>Write Permission</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Write Permission</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption
	 * @see #getWritePermission()
	 * @generated
	 */
	void setWritePermission(WritePermissionOption value);

	/**
	 * Returns the value of the '<em><b>Recovery</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recovery</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recovery</em>' attribute.
	 * @see #setRecovery(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataLinkDataType_Recovery()
	 * @model
	 * @generated
	 */
	boolean isRecovery();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#isRecovery <em>Recovery</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recovery</em>' attribute.
	 * @see #isRecovery()
	 * @generated
	 */
	void setRecovery(boolean value);

	/**
	 * Returns the value of the '<em><b>Unlink</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unlink</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unlink</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption
	 * @see #setUnlink(UnlinkOption)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getDataLinkDataType_Unlink()
	 * @model
	 * @generated
	 */
	UnlinkOption getUnlink();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType#getUnlink <em>Unlink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unlink</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption
	 * @see #getUnlink()
	 * @generated
	 */
	void setUnlink(UnlinkOption value);

} // DataLinkDataType
