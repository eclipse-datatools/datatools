/**
 * <copyright>
 * </copyright>
 *
 * $Id: DeviceItem.java,v 1.7 2007/07/06 08:40:18 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Device Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem#getDeviceName <em>Device Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getDeviceItem()
 * @model
 * @generated
 */
public interface DeviceItem extends SQLObject {
	/**
     * Returns the value of the '<em><b>Device Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Device Name</em>' attribute.
     * @see #setDeviceName(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getDeviceItem_DeviceName()
     * @model
     * @generated
     */
	String getDeviceName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem#getDeviceName <em>Device Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Device Name</em>' attribute.
     * @see #getDeviceName()
     * @generated
     */
	void setDeviceName(String value);

	/**
     * Returns the value of the '<em><b>Size</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Size</em>' attribute.
     * @see #setSize(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getDeviceItem_Size()
     * @model default="0" required="true"
     * @generated
     */
	int getSize();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem#getSize <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Size</em>' attribute.
     * @see #getSize()
     * @generated
     */
	void setSize(int value);

} // DeviceItem
