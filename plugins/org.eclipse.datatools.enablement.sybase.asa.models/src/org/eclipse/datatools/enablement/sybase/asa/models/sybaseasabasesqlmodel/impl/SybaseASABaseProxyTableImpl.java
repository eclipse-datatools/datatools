/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseProxyTableImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
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
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProxyTableImpl#isExisting <em>Existing</em>}</li>
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
	protected static final String REMOTE_OBJECT_LOCATION_EDEFAULT = null;

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
	 * The default value of the '{@link #isExisting() <em>Existing</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isExisting()
	 * @generated
	 * @ordered
	 */
    protected static final boolean EXISTING_EDEFAULT =
    false; // TODO The default value literal "" is not valid.

    /**
	 * The cached value of the '{@link #isExisting() <em>Existing</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isExisting()
	 * @generated
	 * @ordered
	 */
    protected boolean existing = EXISTING_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseProxyTableImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass()
    {
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_PROXY_TABLE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemoteObjectLocation()
    {
		return remoteObjectLocation;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteObjectLocation(String newRemoteObjectLocation)
    {
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
    public boolean isExisting()
    {
		return existing;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setExisting(boolean newExisting)
    {
		boolean oldExisting = existing;
		existing = newExisting;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__EXISTING, oldExisting, existing));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION:
				return getRemoteObjectLocation();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__EXISTING:
				return isExisting() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION:
				setRemoteObjectLocation((String)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__EXISTING:
				setExisting(((Boolean)newValue).booleanValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION:
				setRemoteObjectLocation(REMOTE_OBJECT_LOCATION_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__EXISTING:
				setExisting(EXISTING_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION:
				return REMOTE_OBJECT_LOCATION_EDEFAULT == null ? remoteObjectLocation != null : !REMOTE_OBJECT_LOCATION_EDEFAULT.equals(remoteObjectLocation);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE__EXISTING:
				return existing != EXISTING_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString()
    {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (remoteObjectLocation: ");
		result.append(remoteObjectLocation);
		result.append(", existing: ");
		result.append(existing);
		result.append(')');
		return result.toString();
	}

} //SybaseASABaseProxyTableImpl