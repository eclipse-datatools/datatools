/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase Privilege</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasePrivilegeImpl#isRevoked <em>Revoked</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybasePrivilegeImpl extends PrivilegeImpl implements SybasePrivilege {
	/**
	 * The default value of the '{@link #isRevoked() <em>Revoked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRevoked()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REVOKED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRevoked() <em>Revoked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRevoked()
	 * @generated
	 * @ordered
	 */
	protected boolean revoked = REVOKED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybasePrivilegeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SybasesqlmodelPackage.Literals.SYBASE_PRIVILEGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRevoked() {
		return revoked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRevoked(boolean newRevoked) {
		boolean oldRevoked = revoked;
		revoked = newRevoked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybasesqlmodelPackage.SYBASE_PRIVILEGE__REVOKED, oldRevoked, revoked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_PRIVILEGE__REVOKED:
				return isRevoked() ? Boolean.TRUE : Boolean.FALSE;
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
			case SybasesqlmodelPackage.SYBASE_PRIVILEGE__REVOKED:
				setRevoked(((Boolean)newValue).booleanValue());
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
			case SybasesqlmodelPackage.SYBASE_PRIVILEGE__REVOKED:
				setRevoked(REVOKED_EDEFAULT);
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
			case SybasesqlmodelPackage.SYBASE_PRIVILEGE__REVOKED:
				return revoked != REVOKED_EDEFAULT;
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
		result.append(" (revoked: ");
		result.append(revoked);
		result.append(')');
		return result.toString();
	}

} //SybasePrivilegeImpl
