/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWGenericUserMappingImpl.java,v 1.11 2008/01/29 00:04:54 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic User Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericUserMappingImpl#getRemoteUser <em>Remote User</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericUserMappingImpl#getRemotePassword <em>Remote Password</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWGenericUserMappingImpl extends LUWUserMappingImpl implements LUWGenericUserMapping {
	/**
	 * The default value of the '{@link #getRemoteUser() <em>Remote User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteUser()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_USER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemoteUser() <em>Remote User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteUser()
	 * @generated
	 * @ordered
	 */
	protected String remoteUser = REMOTE_USER_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemotePassword() <em>Remote Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemotePassword()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemotePassword() <em>Remote Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemotePassword()
	 * @generated
	 * @ordered
	 */
	protected String remotePassword = REMOTE_PASSWORD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWGenericUserMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_GENERIC_USER_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemoteUser() {
		return remoteUser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteUser(String newRemoteUser) {
		String oldRemoteUser = remoteUser;
		remoteUser = newRemoteUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_USER, oldRemoteUser, remoteUser));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemotePassword() {
		return remotePassword;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemotePassword(String newRemotePassword) {
		String oldRemotePassword = remotePassword;
		remotePassword = newRemotePassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_PASSWORD, oldRemotePassword, remotePassword));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_USER:
				return getRemoteUser();
			case LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_PASSWORD:
				return getRemotePassword();
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
			case LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_USER:
				setRemoteUser((String)newValue);
				return;
			case LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_PASSWORD:
				setRemotePassword((String)newValue);
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
			case LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_USER:
				setRemoteUser(REMOTE_USER_EDEFAULT);
				return;
			case LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_PASSWORD:
				setRemotePassword(REMOTE_PASSWORD_EDEFAULT);
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
			case LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_USER:
				return REMOTE_USER_EDEFAULT == null ? remoteUser != null : !REMOTE_USER_EDEFAULT.equals(remoteUser);
			case LUWPackage.LUW_GENERIC_USER_MAPPING__REMOTE_PASSWORD:
				return REMOTE_PASSWORD_EDEFAULT == null ? remotePassword != null : !REMOTE_PASSWORD_EDEFAULT.equals(remotePassword);
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
		result.append(" (remoteUser: "); //$NON-NLS-1$
		result.append(remoteUser);
		result.append(", remotePassword: "); //$NON-NLS-1$
		result.append(remotePassword);
		result.append(')');
		return result.toString();
	}

} //LUWGenericUserMappingImpl
