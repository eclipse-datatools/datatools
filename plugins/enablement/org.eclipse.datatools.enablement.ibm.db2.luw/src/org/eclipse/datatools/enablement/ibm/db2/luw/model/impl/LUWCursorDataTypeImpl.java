/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWCursorDataTypeImpl.java,v 1.1 2009/02/27 18:42:01 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRowDataType;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.UserDefinedTypeImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cursor Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWCursorDataTypeImpl#getRowType <em>Row Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWCursorDataTypeImpl extends UserDefinedTypeImpl implements LUWCursorDataType {
	/**
	 * The cached value of the '{@link #getRowType() <em>Row Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRowType()
	 * @generated
	 * @ordered
	 */
	protected LUWRowDataType rowType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWCursorDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_CURSOR_DATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWRowDataType getRowType() {
		if (rowType != null && rowType.eIsProxy()) {
			InternalEObject oldRowType = (InternalEObject)rowType;
			rowType = (LUWRowDataType)eResolveProxy(oldRowType);
			if (rowType != oldRowType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_CURSOR_DATA_TYPE__ROW_TYPE, oldRowType, rowType));
			}
		}
		return rowType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWRowDataType basicGetRowType() {
		return rowType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRowType(LUWRowDataType newRowType) {
		LUWRowDataType oldRowType = rowType;
		rowType = newRowType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_CURSOR_DATA_TYPE__ROW_TYPE, oldRowType, rowType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_CURSOR_DATA_TYPE__ROW_TYPE:
				if (resolve) return getRowType();
				return basicGetRowType();
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
			case LUWPackage.LUW_CURSOR_DATA_TYPE__ROW_TYPE:
				setRowType((LUWRowDataType)newValue);
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
			case LUWPackage.LUW_CURSOR_DATA_TYPE__ROW_TYPE:
				setRowType((LUWRowDataType)null);
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
			case LUWPackage.LUW_CURSOR_DATA_TYPE__ROW_TYPE:
				return rowType != null;
		}
		return super.eIsSet(featureID);
	}

} //LUWCursorDataTypeImpl
