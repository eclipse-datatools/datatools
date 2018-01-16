/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASECatalog.java,v 1.9 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.Catalog;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Catalog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getSegments <em>Segments</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getDataDevices <em>Data Devices</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getLogDevices <em>Log Devices</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isOverride <em>Override</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getDefaultLocation <em>Default Location</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isForLoad <em>For Load</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isForProxyUpdate <em>For Proxy Update</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getLogIOSize <em>Log IO Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getRecoveryOrder <em>Recovery Order</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getAuthorizationIds <em>Authorization Ids</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getCache <em>Cache</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getCatalogType <em>Catalog Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog()
 * @model
 * @generated
 */
public interface SybaseASECatalog extends Catalog, SQLObject {
	/**
     * Returns the value of the '<em><b>Segments</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getCatalog <em>Catalog</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Segments</em>' containment reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_Segments()
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getCatalog
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment" opposite="catalog" containment="true"
     * @generated
     */
	EList getSegments();

	/**
     * Returns the value of the '<em><b>Data Devices</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Devices</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Data Devices</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_DataDevices()
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem"
     * @generated
     */
	EList getDataDevices();

	/**
     * Returns the value of the '<em><b>Log Devices</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Log Devices</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Log Devices</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_LogDevices()
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem"
     * @generated
     */
	EList getLogDevices();

	/**
     * Returns the value of the '<em><b>Override</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Override</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Override</em>' attribute.
     * @see #setOverride(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_Override()
     * @model
     * @generated
     */
	boolean isOverride();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isOverride <em>Override</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Override</em>' attribute.
     * @see #isOverride()
     * @generated
     */
	void setOverride(boolean value);

	/**
     * Returns the value of the '<em><b>Default Location</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default Location</em>' attribute.
     * @see #setDefaultLocation(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_DefaultLocation()
     * @model
     * @generated
     */
	String getDefaultLocation();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getDefaultLocation <em>Default Location</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Location</em>' attribute.
     * @see #getDefaultLocation()
     * @generated
     */
	void setDefaultLocation(String value);

	/**
     * Returns the value of the '<em><b>For Load</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For Load</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>For Load</em>' attribute.
     * @see #setForLoad(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_ForLoad()
     * @model
     * @generated
     */
	boolean isForLoad();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isForLoad <em>For Load</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>For Load</em>' attribute.
     * @see #isForLoad()
     * @generated
     */
	void setForLoad(boolean value);

	/**
     * Returns the value of the '<em><b>For Proxy Update</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For Proxy Update</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>For Proxy Update</em>' attribute.
     * @see #setForProxyUpdate(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_ForProxyUpdate()
     * @model
     * @generated
     */
	boolean isForProxyUpdate();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isForProxyUpdate <em>For Proxy Update</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>For Proxy Update</em>' attribute.
     * @see #isForProxyUpdate()
     * @generated
     */
	void setForProxyUpdate(boolean value);

	/**
     * Returns the value of the '<em><b>Cache</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cache</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Cache</em>' reference.
     * @see #setCache(SybaseASECache)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_Cache()
     * @model
     * @generated
     */
	SybaseASECache getCache();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getCache <em>Cache</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cache</em>' reference.
     * @see #getCache()
     * @generated
     */
	void setCache(SybaseASECache value);

	/**
     * Returns the value of the '<em><b>Catalog Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Catalog Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Catalog Type</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType
     * @see #setCatalogType(SybaseASECatalogType)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_CatalogType()
     * @model
     * @generated
     */
	SybaseASECatalogType getCatalogType();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getCatalogType <em>Catalog Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Catalog Type</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType
     * @see #getCatalogType()
     * @generated
     */
	void setCatalogType(SybaseASECatalogType value);

	/**
     * Returns the value of the '<em><b>Log IO Size</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Log IO Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Log IO Size</em>' attribute.
     * @see #setLogIOSize(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_LogIOSize()
     * @model
     * @generated
     */
	int getLogIOSize();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getLogIOSize <em>Log IO Size</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Log IO Size</em>' attribute.
     * @see #getLogIOSize()
     * @generated
     */
	void setLogIOSize(int value);

	/**
     * Returns the value of the '<em><b>Recovery Order</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recovery Order</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * 0 as default value
     * <!-- end-model-doc -->
     * @return the value of the '<em>Recovery Order</em>' attribute.
     * @see #setRecoveryOrder(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_RecoveryOrder()
     * @model
     * @generated
     */
	int getRecoveryOrder();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getRecoveryOrder <em>Recovery Order</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Recovery Order</em>' attribute.
     * @see #getRecoveryOrder()
     * @generated
     */
	void setRecoveryOrder(int value);

	/**
     * Returns the value of the '<em><b>Authorization Ids</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authorization Ids</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * List of users and groups
     * <!-- end-model-doc -->
     * @return the value of the '<em>Authorization Ids</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASECatalog_AuthorizationIds()
     * @model type="org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier"
     * @generated
     */
	EList getAuthorizationIds();

} // SybaseASECatalog
