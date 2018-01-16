/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASECatalogImpl.java,v 1.9 2007/07/06 08:40:18 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;

import org.eclipse.datatools.modelbase.sql.schema.impl.CatalogImpl;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Catalog</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#getSegments <em>Segments</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#getDataDevices <em>Data Devices</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#getLogDevices <em>Log Devices</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#isOverride <em>Override</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#getDefaultLocation <em>Default Location</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#isForLoad <em>For Load</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#isForProxyUpdate <em>For Proxy Update</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#getLogIOSize <em>Log IO Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#getRecoveryOrder <em>Recovery Order</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#getAuthorizationIds <em>Authorization Ids</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#getCache <em>Cache</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl#getCatalogType <em>Catalog Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASECatalogImpl extends CatalogImpl implements SybaseASECatalog 
{
	/**
     * The cached value of the '{@link #getSegments() <em>Segments</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSegments()
     * @generated
     * @ordered
     */
	protected EList segments;

	/**
     * The cached value of the '{@link #getDataDevices() <em>Data Devices</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDataDevices()
     * @generated
     * @ordered
     */
	protected EList dataDevices;

	/**
     * The cached value of the '{@link #getLogDevices() <em>Log Devices</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLogDevices()
     * @generated
     * @ordered
     */
	protected EList logDevices;

	/**
     * The default value of the '{@link #isOverride() <em>Override</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isOverride()
     * @generated
     * @ordered
     */
	protected static final boolean OVERRIDE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isOverride() <em>Override</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isOverride()
     * @generated
     * @ordered
     */
	protected boolean override = OVERRIDE_EDEFAULT;

	/**
     * The default value of the '{@link #getDefaultLocation() <em>Default Location</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultLocation()
     * @generated
     * @ordered
     */
	protected static final String DEFAULT_LOCATION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getDefaultLocation() <em>Default Location</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefaultLocation()
     * @generated
     * @ordered
     */
	protected String defaultLocation = DEFAULT_LOCATION_EDEFAULT;

	/**
     * The default value of the '{@link #isForLoad() <em>For Load</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isForLoad()
     * @generated
     * @ordered
     */
	protected static final boolean FOR_LOAD_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isForLoad() <em>For Load</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isForLoad()
     * @generated
     * @ordered
     */
	protected boolean forLoad = FOR_LOAD_EDEFAULT;

	/**
     * The default value of the '{@link #isForProxyUpdate() <em>For Proxy Update</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isForProxyUpdate()
     * @generated
     * @ordered
     */
	protected static final boolean FOR_PROXY_UPDATE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isForProxyUpdate() <em>For Proxy Update</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isForProxyUpdate()
     * @generated
     * @ordered
     */
	protected boolean forProxyUpdate = FOR_PROXY_UPDATE_EDEFAULT;

	/**
     * The default value of the '{@link #getLogIOSize() <em>Log IO Size</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLogIOSize()
     * @generated
     * @ordered
     */
	protected static final int LOG_IO_SIZE_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getLogIOSize() <em>Log IO Size</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLogIOSize()
     * @generated
     * @ordered
     */
	protected int logIOSize = LOG_IO_SIZE_EDEFAULT;

	/**
     * The default value of the '{@link #getRecoveryOrder() <em>Recovery Order</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRecoveryOrder()
     * @generated
     * @ordered
     */
	protected static final int RECOVERY_ORDER_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getRecoveryOrder() <em>Recovery Order</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRecoveryOrder()
     * @generated
     * @ordered
     */
	protected int recoveryOrder = RECOVERY_ORDER_EDEFAULT;

	/**
     * The cached value of the '{@link #getAuthorizationIds() <em>Authorization Ids</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAuthorizationIds()
     * @generated
     * @ordered
     */
	protected EList authorizationIds;

	/**
     * The cached value of the '{@link #getCache() <em>Cache</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCache()
     * @generated
     * @ordered
     */
	protected SybaseASECache cache;

	/**
     * The default value of the '{@link #getCatalogType() <em>Catalog Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCatalogType()
     * @generated
     * @ordered
     */
	protected static final SybaseASECatalogType CATALOG_TYPE_EDEFAULT = SybaseASECatalogType.PROXYCATALOG_LITERAL;

	/**
     * The cached value of the '{@link #getCatalogType() <em>Catalog Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCatalogType()
     * @generated
     * @ordered
     */
	protected SybaseASECatalogType catalogType = CATALOG_TYPE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASECatalogImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_CATALOG;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getSegments() {
        if (segments == null)
        {
            segments = new EObjectContainmentWithInverseEList(SybaseASESegment.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS, SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG);
        }
        return segments;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDataDevices() {
        if (dataDevices == null)
        {
            dataDevices = new EObjectResolvingEList(DeviceItem.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DATA_DEVICES);
        }
        return dataDevices;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getLogDevices() {
        if (logDevices == null)
        {
            logDevices = new EObjectResolvingEList(DeviceItem.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_DEVICES);
        }
        return logDevices;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isOverride() {
        return override;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOverride(boolean newOverride) {
        boolean oldOverride = override;
        override = newOverride;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__OVERRIDE, oldOverride, override));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getDefaultLocation() {
        return defaultLocation;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDefaultLocation(String newDefaultLocation) {
        String oldDefaultLocation = defaultLocation;
        defaultLocation = newDefaultLocation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DEFAULT_LOCATION, oldDefaultLocation, defaultLocation));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isForLoad() {
        return forLoad;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setForLoad(boolean newForLoad) {
        boolean oldForLoad = forLoad;
        forLoad = newForLoad;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_LOAD, oldForLoad, forLoad));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isForProxyUpdate() {
        return forProxyUpdate;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setForProxyUpdate(boolean newForProxyUpdate) {
        boolean oldForProxyUpdate = forProxyUpdate;
        forProxyUpdate = newForProxyUpdate;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_PROXY_UPDATE, oldForProxyUpdate, forProxyUpdate));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASECache getCache() {
        if (cache != null && cache.eIsProxy())
        {
            InternalEObject oldCache = (InternalEObject)cache;
            cache = (SybaseASECache)eResolveProxy(oldCache);
            if (cache != oldCache)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CACHE, oldCache, cache));
            }
        }
        return cache;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASECache basicGetCache() {
        return cache;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setCache(SybaseASECache newCache) {
        SybaseASECache oldCache = cache;
        cache = newCache;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CACHE, oldCache, cache));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASECatalogType getCatalogType() {
        return catalogType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setCatalogType(SybaseASECatalogType newCatalogType) {
        SybaseASECatalogType oldCatalogType = catalogType;
        catalogType = newCatalogType == null ? CATALOG_TYPE_EDEFAULT : newCatalogType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CATALOG_TYPE, oldCatalogType, catalogType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getLogIOSize() {
        return logIOSize;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setLogIOSize(int newLogIOSize) {
        int oldLogIOSize = logIOSize;
        logIOSize = newLogIOSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_IO_SIZE, oldLogIOSize, logIOSize));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getRecoveryOrder() {
        return recoveryOrder;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRecoveryOrder(int newRecoveryOrder) {
        int oldRecoveryOrder = recoveryOrder;
        recoveryOrder = newRecoveryOrder;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__RECOVERY_ORDER, oldRecoveryOrder, recoveryOrder));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getAuthorizationIds() {
        if (authorizationIds == null)
        {
            authorizationIds = new EObjectResolvingEList(AuthorizationIdentifier.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__AUTHORIZATION_IDS);
        }
        return authorizationIds;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS:
                return ((InternalEList)getSegments()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS:
                return ((InternalEList)getSegments()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS:
                return getSegments();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DATA_DEVICES:
                return getDataDevices();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_DEVICES:
                return getLogDevices();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__OVERRIDE:
                return isOverride() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DEFAULT_LOCATION:
                return getDefaultLocation();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_LOAD:
                return isForLoad() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_PROXY_UPDATE:
                return isForProxyUpdate() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_IO_SIZE:
                return new Integer(getLogIOSize());
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__RECOVERY_ORDER:
                return new Integer(getRecoveryOrder());
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__AUTHORIZATION_IDS:
                return getAuthorizationIds();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CACHE:
                if (resolve) return getCache();
                return basicGetCache();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CATALOG_TYPE:
                return getCatalogType();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS:
                getSegments().clear();
                getSegments().addAll((Collection)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DATA_DEVICES:
                getDataDevices().clear();
                getDataDevices().addAll((Collection)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_DEVICES:
                getLogDevices().clear();
                getLogDevices().addAll((Collection)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__OVERRIDE:
                setOverride(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DEFAULT_LOCATION:
                setDefaultLocation((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_LOAD:
                setForLoad(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_PROXY_UPDATE:
                setForProxyUpdate(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_IO_SIZE:
                setLogIOSize(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__RECOVERY_ORDER:
                setRecoveryOrder(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__AUTHORIZATION_IDS:
                getAuthorizationIds().clear();
                getAuthorizationIds().addAll((Collection)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CACHE:
                setCache((SybaseASECache)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CATALOG_TYPE:
                setCatalogType((SybaseASECatalogType)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS:
                getSegments().clear();
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DATA_DEVICES:
                getDataDevices().clear();
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_DEVICES:
                getLogDevices().clear();
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__OVERRIDE:
                setOverride(OVERRIDE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DEFAULT_LOCATION:
                setDefaultLocation(DEFAULT_LOCATION_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_LOAD:
                setForLoad(FOR_LOAD_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_PROXY_UPDATE:
                setForProxyUpdate(FOR_PROXY_UPDATE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_IO_SIZE:
                setLogIOSize(LOG_IO_SIZE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__RECOVERY_ORDER:
                setRecoveryOrder(RECOVERY_ORDER_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__AUTHORIZATION_IDS:
                getAuthorizationIds().clear();
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CACHE:
                setCache((SybaseASECache)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CATALOG_TYPE:
                setCatalogType(CATALOG_TYPE_EDEFAULT);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS:
                return segments != null && !segments.isEmpty();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DATA_DEVICES:
                return dataDevices != null && !dataDevices.isEmpty();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_DEVICES:
                return logDevices != null && !logDevices.isEmpty();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__OVERRIDE:
                return override != OVERRIDE_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__DEFAULT_LOCATION:
                return DEFAULT_LOCATION_EDEFAULT == null ? defaultLocation != null : !DEFAULT_LOCATION_EDEFAULT.equals(defaultLocation);
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_LOAD:
                return forLoad != FOR_LOAD_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__FOR_PROXY_UPDATE:
                return forProxyUpdate != FOR_PROXY_UPDATE_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__LOG_IO_SIZE:
                return logIOSize != LOG_IO_SIZE_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__RECOVERY_ORDER:
                return recoveryOrder != RECOVERY_ORDER_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__AUTHORIZATION_IDS:
                return authorizationIds != null && !authorizationIds.isEmpty();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CACHE:
                return cache != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__CATALOG_TYPE:
                return catalogType != CATALOG_TYPE_EDEFAULT;
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
        result.append(" (override: "); //$NON-NLS-1$
        result.append(override);
        result.append(", defaultLocation: "); //$NON-NLS-1$
        result.append(defaultLocation);
        result.append(", forLoad: "); //$NON-NLS-1$
        result.append(forLoad);
        result.append(", forProxyUpdate: "); //$NON-NLS-1$
        result.append(forProxyUpdate);
        result.append(", logIOSize: "); //$NON-NLS-1$
        result.append(logIOSize);
        result.append(", recoveryOrder: "); //$NON-NLS-1$
        result.append(recoveryOrder);
        result.append(", catalogType: "); //$NON-NLS-1$
        result.append(catalogType);
        result.append(')');
        return result.toString();
    }

} //SybaseASECatalogImpl
