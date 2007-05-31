/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseProxyTableImpl.java,v 1.2 2007/03/19 16:37:08 jgraham Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Proxy Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProxyTableImpl#getRemoteObjectLocation <em>Remote Object Location</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseProxyTableImpl extends SybaseASABaseTableImpl implements SybaseASABaseProxyTable 
{
	/**
	 * The default value of the '{@link #getRemoteObjectLocation() <em>Remote Object Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteObjectLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_OBJECT_LOCATION_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getRemoteObjectLocation() <em>Remote Object Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteObjectLocation()
	 * @generated
	 * @ordered
	 */
	protected String remoteObjectLocation = REMOTE_OBJECT_LOCATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseProxyTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_PROXY_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemoteObjectLocation() {
		return remoteObjectLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteObjectLocation(String newRemoteObjectLocation) {
		String oldRemoteObjectLocation = remoteObjectLocation;
		remoteObjectLocation = newRemoteObjectLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION, oldRemoteObjectLocation, remoteObjectLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION:
				return getRemoteObjectLocation();
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION:
				setRemoteObjectLocation((String)newValue);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION:
				setRemoteObjectLocation(REMOTE_OBJECT_LOCATION_EDEFAULT);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION:
				return REMOTE_OBJECT_LOCATION_EDEFAULT == null ? remoteObjectLocation != null : !REMOTE_OBJECT_LOCATION_EDEFAULT.equals(remoteObjectLocation);
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
		result.append(" (remoteObjectLocation: ");
		result.append(remoteObjectLocation);
		result.append(')');
		return result.toString();
	}

} //SybaseASABaseProxyTableImpl