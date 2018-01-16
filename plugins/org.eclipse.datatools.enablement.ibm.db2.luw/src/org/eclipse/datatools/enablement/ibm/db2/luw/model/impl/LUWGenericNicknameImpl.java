/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWGenericNicknameImpl.java,v 1.14 2008/01/29 00:04:54 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Nickname</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericNicknameImpl#getGenericServer <em>Generic Server</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWGenericNicknameImpl extends LUWNicknameImpl implements LUWGenericNickname {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWGenericNicknameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_GENERIC_NICKNAME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWGenericServer getGenericServer() {
		LUWGenericServer genericServer = basicGetGenericServer();
		return genericServer != null && genericServer.eIsProxy() ? (LUWGenericServer)eResolveProxy((InternalEObject)genericServer) : genericServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LUWGenericServer basicGetGenericServer() {
		return (LUWGenericServer) server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setGenericServer(LUWGenericServer newGenericServer) {
		super.setServer(newGenericServer);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_GENERIC_NICKNAME__GENERIC_SERVER:
				if (resolve) return getGenericServer();
				return basicGetGenericServer();
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
			case LUWPackage.LUW_GENERIC_NICKNAME__GENERIC_SERVER:
				setGenericServer((LUWGenericServer)newValue);
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
			case LUWPackage.LUW_GENERIC_NICKNAME__GENERIC_SERVER:
				setGenericServer((LUWGenericServer)null);
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
			case LUWPackage.LUW_GENERIC_NICKNAME__GENERIC_SERVER:
				return basicGetGenericServer() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setServer(LUWServer newGenericServer) {
		setGenericServer((LUWGenericServer)newGenericServer);
	}

} //LUWGenericNicknameImpl
