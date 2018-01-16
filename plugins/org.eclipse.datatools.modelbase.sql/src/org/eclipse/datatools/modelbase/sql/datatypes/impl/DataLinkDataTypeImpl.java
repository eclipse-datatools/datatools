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
package org.eclipse.datatools.modelbase.sql.datatypes.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption;
import org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption;
import org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Link Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl#getLength <em>Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl#getLinkControl <em>Link Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl#getIntegrityControl <em>Integrity Control</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl#getReadPermission <em>Read Permission</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl#getWritePermission <em>Write Permission</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl#isRecovery <em>Recovery</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.DataLinkDataTypeImpl#getUnlink <em>Unlink</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataLinkDataTypeImpl extends PredefinedDataTypeImpl implements DataLinkDataType {
	/**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final int LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected int length = LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getLinkControl() <em>Link Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkControl()
	 * @generated
	 * @ordered
	 */
	protected static final LinkControlOption LINK_CONTROL_EDEFAULT = LinkControlOption.FILE_LINK_CONTROL_LITERAL;

	/**
	 * The cached value of the '{@link #getLinkControl() <em>Link Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinkControl()
	 * @generated
	 * @ordered
	 */
	protected LinkControlOption linkControl = LINK_CONTROL_EDEFAULT;

	/**
	 * The default value of the '{@link #getIntegrityControl() <em>Integrity Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegrityControl()
	 * @generated
	 * @ordered
	 */
	protected static final IntegrityControlOption INTEGRITY_CONTROL_EDEFAULT = IntegrityControlOption.ALL_LITERAL;

	/**
	 * The cached value of the '{@link #getIntegrityControl() <em>Integrity Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegrityControl()
	 * @generated
	 * @ordered
	 */
	protected IntegrityControlOption integrityControl = INTEGRITY_CONTROL_EDEFAULT;

	/**
	 * The default value of the '{@link #getReadPermission() <em>Read Permission</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReadPermission()
	 * @generated
	 * @ordered
	 */
	protected static final ReadPermissionOption READ_PERMISSION_EDEFAULT = ReadPermissionOption.FS_LITERAL;

	/**
	 * The cached value of the '{@link #getReadPermission() <em>Read Permission</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReadPermission()
	 * @generated
	 * @ordered
	 */
	protected ReadPermissionOption readPermission = READ_PERMISSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getWritePermission() <em>Write Permission</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWritePermission()
	 * @generated
	 * @ordered
	 */
	protected static final WritePermissionOption WRITE_PERMISSION_EDEFAULT = WritePermissionOption.FS_LITERAL;

	/**
	 * The cached value of the '{@link #getWritePermission() <em>Write Permission</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWritePermission()
	 * @generated
	 * @ordered
	 */
	protected WritePermissionOption writePermission = WRITE_PERMISSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isRecovery() <em>Recovery</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecovery()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RECOVERY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRecovery() <em>Recovery</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecovery()
	 * @generated
	 * @ordered
	 */
	protected boolean recovery = RECOVERY_EDEFAULT;

	/**
	 * The default value of the '{@link #getUnlink() <em>Unlink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnlink()
	 * @generated
	 * @ordered
	 */
	protected static final UnlinkOption UNLINK_EDEFAULT = UnlinkOption.RESTORE_LITERAL;

	/**
	 * The cached value of the '{@link #getUnlink() <em>Unlink</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnlink()
	 * @generated
	 * @ordered
	 */
	protected UnlinkOption unlink = UNLINK_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataLinkDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.Literals.DATA_LINK_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLength() {
		return length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(int newLength) {
		int oldLength = length;
		length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LENGTH, oldLength, length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkControlOption getLinkControl() {
		return linkControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinkControl(LinkControlOption newLinkControl) {
		LinkControlOption oldLinkControl = linkControl;
		linkControl = newLinkControl == null ? LINK_CONTROL_EDEFAULT : newLinkControl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LINK_CONTROL, oldLinkControl, linkControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegrityControlOption getIntegrityControl() {
		return integrityControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntegrityControl(IntegrityControlOption newIntegrityControl) {
		IntegrityControlOption oldIntegrityControl = integrityControl;
		integrityControl = newIntegrityControl == null ? INTEGRITY_CONTROL_EDEFAULT : newIntegrityControl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DATA_LINK_DATA_TYPE__INTEGRITY_CONTROL, oldIntegrityControl, integrityControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadPermissionOption getReadPermission() {
		return readPermission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReadPermission(ReadPermissionOption newReadPermission) {
		ReadPermissionOption oldReadPermission = readPermission;
		readPermission = newReadPermission == null ? READ_PERMISSION_EDEFAULT : newReadPermission;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DATA_LINK_DATA_TYPE__READ_PERMISSION, oldReadPermission, readPermission));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WritePermissionOption getWritePermission() {
		return writePermission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWritePermission(WritePermissionOption newWritePermission) {
		WritePermissionOption oldWritePermission = writePermission;
		writePermission = newWritePermission == null ? WRITE_PERMISSION_EDEFAULT : newWritePermission;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DATA_LINK_DATA_TYPE__WRITE_PERMISSION, oldWritePermission, writePermission));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRecovery() {
		return recovery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecovery(boolean newRecovery) {
		boolean oldRecovery = recovery;
		recovery = newRecovery;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DATA_LINK_DATA_TYPE__RECOVERY, oldRecovery, recovery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnlinkOption getUnlink() {
		return unlink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnlink(UnlinkOption newUnlink) {
		UnlinkOption oldUnlink = unlink;
		unlink = newUnlink == null ? UNLINK_EDEFAULT : newUnlink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.DATA_LINK_DATA_TYPE__UNLINK, oldUnlink, unlink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LENGTH:
				return new Integer(getLength());
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LINK_CONTROL:
				return getLinkControl();
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__INTEGRITY_CONTROL:
				return getIntegrityControl();
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__READ_PERMISSION:
				return getReadPermission();
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__WRITE_PERMISSION:
				return getWritePermission();
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__RECOVERY:
				return isRecovery() ? Boolean.TRUE : Boolean.FALSE;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__UNLINK:
				return getUnlink();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LENGTH:
				setLength(((Integer)newValue).intValue());
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LINK_CONTROL:
				setLinkControl((LinkControlOption)newValue);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__INTEGRITY_CONTROL:
				setIntegrityControl((IntegrityControlOption)newValue);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__READ_PERMISSION:
				setReadPermission((ReadPermissionOption)newValue);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__WRITE_PERMISSION:
				setWritePermission((WritePermissionOption)newValue);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__RECOVERY:
				setRecovery(((Boolean)newValue).booleanValue());
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__UNLINK:
				setUnlink((UnlinkOption)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LENGTH:
				setLength(LENGTH_EDEFAULT);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LINK_CONTROL:
				setLinkControl(LINK_CONTROL_EDEFAULT);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__INTEGRITY_CONTROL:
				setIntegrityControl(INTEGRITY_CONTROL_EDEFAULT);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__READ_PERMISSION:
				setReadPermission(READ_PERMISSION_EDEFAULT);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__WRITE_PERMISSION:
				setWritePermission(WRITE_PERMISSION_EDEFAULT);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__RECOVERY:
				setRecovery(RECOVERY_EDEFAULT);
				return;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__UNLINK:
				setUnlink(UNLINK_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LENGTH:
				return length != LENGTH_EDEFAULT;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__LINK_CONTROL:
				return linkControl != LINK_CONTROL_EDEFAULT;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__INTEGRITY_CONTROL:
				return integrityControl != INTEGRITY_CONTROL_EDEFAULT;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__READ_PERMISSION:
				return readPermission != READ_PERMISSION_EDEFAULT;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__WRITE_PERMISSION:
				return writePermission != WRITE_PERMISSION_EDEFAULT;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__RECOVERY:
				return recovery != RECOVERY_EDEFAULT;
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE__UNLINK:
				return unlink != UNLINK_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (length: "); //$NON-NLS-1$
		result.append(length);
		result.append(", linkControl: "); //$NON-NLS-1$
		result.append(linkControl);
		result.append(", integrityControl: "); //$NON-NLS-1$
		result.append(integrityControl);
		result.append(", readPermission: "); //$NON-NLS-1$
		result.append(readPermission);
		result.append(", writePermission: "); //$NON-NLS-1$
		result.append(writePermission);
		result.append(", recovery: "); //$NON-NLS-1$
		result.append(recovery);
		result.append(", unlink: "); //$NON-NLS-1$
		result.append(unlink);
		result.append(')');
		return result.toString();
	}

} //DataLinkDataTypeImpl
