/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWGenericWrapperImpl.java,v 1.10 2008/01/29 00:04:54 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Wrapper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericWrapperImpl#getGenericServers <em>Generic Servers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWGenericWrapperImpl extends LUWWrapperImpl implements LUWGenericWrapper {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWGenericWrapperImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_GENERIC_WRAPPER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getGenericServers() {
		if (servers == null) {
			servers = new EObjectWithInverseResolvingEList(LUWGenericServer.class, this, LUWPackage.LUW_GENERIC_WRAPPER__GENERIC_SERVERS, LUWPackage.LUW_GENERIC_SERVER__GENERIC_WRAPPER);
		}
		return servers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_GENERIC_WRAPPER__GENERIC_SERVERS:
				return getGenericServers();
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
			case LUWPackage.LUW_GENERIC_WRAPPER__GENERIC_SERVERS:
				getGenericServers().clear();
				getGenericServers().addAll((Collection)newValue);
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
			case LUWPackage.LUW_GENERIC_WRAPPER__GENERIC_SERVERS:
				getGenericServers().clear();
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
			case LUWPackage.LUW_GENERIC_WRAPPER__GENERIC_SERVERS:
				return !getGenericServers().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LUWGenericWrapperImpl
