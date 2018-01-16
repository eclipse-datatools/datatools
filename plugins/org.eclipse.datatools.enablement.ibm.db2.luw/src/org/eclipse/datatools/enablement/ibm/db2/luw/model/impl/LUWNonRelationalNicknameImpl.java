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

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Non Relational Nickname</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalNicknameImpl#getNonRelServer <em>Non Rel Server</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LUWNonRelationalNicknameImpl extends LUWNicknameImpl implements LUWNonRelationalNickname {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWNonRelationalNicknameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_NON_RELATIONAL_NICKNAME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWNonRelationalServer getNonRelServer() {
		LUWNonRelationalServer nonRelServer = basicGetNonRelServer();
		return nonRelServer != null && nonRelServer.eIsProxy() ? (LUWNonRelationalServer)eResolveProxy((InternalEObject)nonRelServer) : nonRelServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LUWNonRelationalServer basicGetNonRelServer() {
			return (LUWNonRelationalServer) server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_NON_RELATIONAL_NICKNAME__NON_REL_SERVER:
				if (resolve) return getNonRelServer();
				return basicGetNonRelServer();
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
			case LUWPackage.LUW_NON_RELATIONAL_NICKNAME__NON_REL_SERVER:
				return basicGetNonRelServer() != null;
		}
		return super.eIsSet(featureID);
	}

} //LUWNonRelationalNicknameImpl
