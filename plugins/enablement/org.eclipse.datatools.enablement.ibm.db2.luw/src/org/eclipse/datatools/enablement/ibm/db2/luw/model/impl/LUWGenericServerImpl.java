/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWGenericServerImpl.java,v 1.11 2008/01/29 00:04:54 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generic Server</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericServerImpl#getGenericNicknames <em>Generic Nicknames</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericServerImpl#getGenericWrapper <em>Generic Wrapper</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWGenericServerImpl extends LUWServerImpl implements LUWGenericServer {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWGenericServerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_GENERIC_SERVER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getGenericNicknames() {
		if (nicknames == null) {
			nicknames = new EObjectWithInverseResolvingEList(LUWGenericNickname.class, this, LUWPackage.LUW_SERVER__NICKNAMES, LUWPackage.LUW_NICKNAME__SERVER);
		}
		return nicknames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWGenericWrapper getGenericWrapper() {
		LUWGenericWrapper genericWrapper = basicGetGenericWrapper();
		return genericWrapper != null && genericWrapper.eIsProxy() ? (LUWGenericWrapper)eResolveProxy((InternalEObject)genericWrapper) : genericWrapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LUWGenericWrapper basicGetGenericWrapper() {
		return (LUWGenericWrapper) wrapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setGenericWrapper(LUWGenericWrapper newGenericWrapper) {
		super.setWrapper(newGenericWrapper);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_GENERIC_SERVER__GENERIC_NICKNAMES:
				return getGenericNicknames();
			case LUWPackage.LUW_GENERIC_SERVER__GENERIC_WRAPPER:
				if (resolve) return getGenericWrapper();
				return basicGetGenericWrapper();
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
			case LUWPackage.LUW_GENERIC_SERVER__GENERIC_NICKNAMES:
				getGenericNicknames().clear();
				getGenericNicknames().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_GENERIC_SERVER__GENERIC_WRAPPER:
				setGenericWrapper((LUWGenericWrapper)newValue);
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
			case LUWPackage.LUW_GENERIC_SERVER__GENERIC_NICKNAMES:
				getGenericNicknames().clear();
				return;
			case LUWPackage.LUW_GENERIC_SERVER__GENERIC_WRAPPER:
				setGenericWrapper((LUWGenericWrapper)null);
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
			case LUWPackage.LUW_GENERIC_SERVER__GENERIC_NICKNAMES:
				return !getGenericNicknames().isEmpty();
			case LUWPackage.LUW_GENERIC_SERVER__GENERIC_WRAPPER:
				return basicGetGenericWrapper() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setWrapper(LUWWrapper newGenericWrapper) {
		setGenericWrapper((LUWGenericWrapper) newGenericWrapper);
	}

} //LUWGenericServerImpl
