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

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Non Relational Wrapper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalWrapperImpl#getNonRelServers <em>Non Rel Servers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LUWNonRelationalWrapperImpl extends LUWWrapperImpl implements LUWNonRelationalWrapper {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWNonRelationalWrapperImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_NON_RELATIONAL_WRAPPER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getNonRelServers() {
		// This method should always be overridden by a subclass to ensure
		// that a correctly-type EList is returned.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_NON_RELATIONAL_WRAPPER__NON_REL_SERVERS:
				return getNonRelServers();
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
			case LUWPackage.LUW_NON_RELATIONAL_WRAPPER__NON_REL_SERVERS:
				return !getNonRelServers().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LUWNonRelationalWrapperImpl
