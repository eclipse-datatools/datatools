/**
 * <copyright>
 * </copyright>
 *
 * $Id: DeviceItemImpl.java,v 1.7 2007/07/06 08:40:18 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Device Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.DeviceItemImpl#getDeviceName <em>Device Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.DeviceItemImpl#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeviceItemImpl extends SQLObjectImpl implements DeviceItem 
{
	/**
     * The default value of the '{@link #getDeviceName() <em>Device Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDeviceName()
     * @generated
     * @ordered
     */
	protected static final String DEVICE_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDeviceName() <em>Device Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDeviceName()
     * @generated
     * @ordered
     */
	protected String deviceName = DEVICE_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
	protected static final int SIZE_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSize()
     * @generated
     * @ordered
     */
	protected int size = SIZE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected DeviceItemImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.DEVICE_ITEM;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getDeviceName() {
        return deviceName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDeviceName(String newDeviceName) {
        String oldDeviceName = deviceName;
        deviceName = newDeviceName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.DEVICE_ITEM__DEVICE_NAME, oldDeviceName, deviceName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getSize() {
        return size;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSize(int newSize) {
        int oldSize = size;
        size = newSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.DEVICE_ITEM__SIZE, oldSize, size));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.DEVICE_ITEM__DEVICE_NAME:
                return getDeviceName();
            case SybaseasesqlmodelPackage.DEVICE_ITEM__SIZE:
                return new Integer(getSize());
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.DEVICE_ITEM__DEVICE_NAME:
                setDeviceName((String)newValue);
                return;
            case SybaseasesqlmodelPackage.DEVICE_ITEM__SIZE:
                setSize(((Integer)newValue).intValue());
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.DEVICE_ITEM__DEVICE_NAME:
                setDeviceName(DEVICE_NAME_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.DEVICE_ITEM__SIZE:
                setSize(SIZE_EDEFAULT);
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.DEVICE_ITEM__DEVICE_NAME:
                return DEVICE_NAME_EDEFAULT == null ? deviceName != null : !DEVICE_NAME_EDEFAULT.equals(deviceName);
            case SybaseasesqlmodelPackage.DEVICE_ITEM__SIZE:
                return size != SIZE_EDEFAULT;
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
        result.append(" (deviceName: "); //$NON-NLS-1$
        result.append(deviceName);
        result.append(", size: "); //$NON-NLS-1$
        result.append(size);
        result.append(')');
        return result.toString();
    }

} //DeviceItemImpl
