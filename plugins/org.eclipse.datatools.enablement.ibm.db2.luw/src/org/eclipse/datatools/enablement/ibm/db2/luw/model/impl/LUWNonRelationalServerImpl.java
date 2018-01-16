/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Non Relational Server</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalServerImpl#getNonRelWrapper <em>Non Rel Wrapper</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalServerImpl#getNonRelNicknames <em>Non Rel Nicknames</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LUWNonRelationalServerImpl extends LUWServerImpl implements LUWNonRelationalServer {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWNonRelationalServerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_NON_RELATIONAL_SERVER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWNonRelationalWrapper getNonRelWrapper() {
		LUWNonRelationalWrapper nonRelWrapper = basicGetNonRelWrapper();
		return nonRelWrapper != null && nonRelWrapper.eIsProxy() ? (LUWNonRelationalWrapper)eResolveProxy((InternalEObject)nonRelWrapper) : nonRelWrapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LUWNonRelationalWrapper basicGetNonRelWrapper() {
		return (LUWNonRelationalWrapper) wrapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getNonRelNicknames() {
		// This method should always be overridden by a subclass to ensure
		// that a correctly-typed EList is generated.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_NON_RELATIONAL_SERVER__NON_REL_WRAPPER:
				if (resolve) return getNonRelWrapper();
				return basicGetNonRelWrapper();
			case LUWPackage.LUW_NON_RELATIONAL_SERVER__NON_REL_NICKNAMES:
				return getNonRelNicknames();
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
			case LUWPackage.LUW_NON_RELATIONAL_SERVER__NON_REL_WRAPPER:
				return basicGetNonRelWrapper() != null;
			case LUWPackage.LUW_NON_RELATIONAL_SERVER__NON_REL_NICKNAMES:
				return !getNonRelNicknames().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LUWNonRelationalServerImpl
