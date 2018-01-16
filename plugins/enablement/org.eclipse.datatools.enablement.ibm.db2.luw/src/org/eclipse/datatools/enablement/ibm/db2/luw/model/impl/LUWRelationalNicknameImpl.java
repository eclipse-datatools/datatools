/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relational Nickname</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalNicknameImpl#getRelServer <em>Rel Server</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LUWRelationalNicknameImpl extends LUWNicknameImpl implements LUWRelationalNickname {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWRelationalNicknameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_RELATIONAL_NICKNAME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWRelationalServer getRelServer() {
		LUWRelationalServer relServer = basicGetRelServer();
		return relServer != null && relServer.eIsProxy() ? (LUWRelationalServer)eResolveProxy((InternalEObject)relServer) : relServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LUWRelationalServer basicGetRelServer() {
		return (LUWRelationalServer) server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_RELATIONAL_NICKNAME__REL_SERVER:
				if (resolve) return getRelServer();
				return basicGetRelServer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LUWPackage.LUW_RELATIONAL_NICKNAME__REL_SERVER:
				return basicGetRelServer() != null;
		}
		return super.eIsSet(featureID);
	}

} //LUWRelationalNicknameImpl
