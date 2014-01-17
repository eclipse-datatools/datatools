/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ViewImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWViewImpl#isFederated <em>Federated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWViewImpl#isOptimizeQuery <em>Optimize Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWViewImpl extends DB2ViewImpl implements LUWView {
	/**
	 * The default value of the '{@link #isFederated() <em>Federated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFederated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FEDERATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFederated() <em>Federated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFederated()
	 * @generated
	 * @ordered
	 */
	protected boolean federated = FEDERATED_EDEFAULT;

	/**
	 * The default value of the '{@link #isOptimizeQuery() <em>Optimize Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptimizeQuery()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIMIZE_QUERY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptimizeQuery() <em>Optimize Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptimizeQuery()
	 * @generated
	 * @ordered
	 */
	protected boolean optimizeQuery = OPTIMIZE_QUERY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWViewImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_VIEW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFederated() {
		return federated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFederated(boolean newFederated) {
		boolean oldFederated = federated;
		federated = newFederated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_VIEW__FEDERATED, oldFederated, federated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOptimizeQuery() {
		return optimizeQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptimizeQuery(boolean newOptimizeQuery) {
		boolean oldOptimizeQuery = optimizeQuery;
		optimizeQuery = newOptimizeQuery;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_VIEW__OPTIMIZE_QUERY, oldOptimizeQuery, optimizeQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_VIEW__FEDERATED:
				return isFederated() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_VIEW__OPTIMIZE_QUERY:
				return isOptimizeQuery() ? Boolean.TRUE : Boolean.FALSE;
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
			case LUWPackage.LUW_VIEW__FEDERATED:
				setFederated(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_VIEW__OPTIMIZE_QUERY:
				setOptimizeQuery(((Boolean)newValue).booleanValue());
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
			case LUWPackage.LUW_VIEW__FEDERATED:
				setFederated(FEDERATED_EDEFAULT);
				return;
			case LUWPackage.LUW_VIEW__OPTIMIZE_QUERY:
				setOptimizeQuery(OPTIMIZE_QUERY_EDEFAULT);
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
			case LUWPackage.LUW_VIEW__FEDERATED:
				return federated != FEDERATED_EDEFAULT;
			case LUWPackage.LUW_VIEW__OPTIMIZE_QUERY:
				return optimizeQuery != OPTIMIZE_QUERY_EDEFAULT;
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
		result.append(" (federated: "); //$NON-NLS-1$
		result.append(federated);
		result.append(", optimizeQuery: "); //$NON-NLS-1$
		result.append(optimizeQuery);
		result.append(')');
		return result.toString();
	}

} //LUWViewImpl
