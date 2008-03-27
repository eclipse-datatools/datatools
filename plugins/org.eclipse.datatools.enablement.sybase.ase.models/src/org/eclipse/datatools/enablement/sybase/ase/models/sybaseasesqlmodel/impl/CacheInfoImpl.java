/**
 * <copyright>
 * </copyright>
 *
 * $Id: CacheInfoImpl.java,v 1.8 2007/07/06 08:40:20 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cache Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.CacheInfoImpl#getCacheStrategy <em>Cache Strategy</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.CacheInfoImpl#getCache <em>Cache</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CacheInfoImpl extends SQLObjectImpl implements CacheInfo 
{
	/**
     * The default value of the '{@link #getCacheStrategy() <em>Cache Strategy</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCacheStrategy()
     * @generated
     * @ordered
     */
	protected static final int CACHE_STRATEGY_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getCacheStrategy() <em>Cache Strategy</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCacheStrategy()
     * @generated
     * @ordered
     */
	protected int cacheStrategy = CACHE_STRATEGY_EDEFAULT;

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
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected CacheInfoImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.CACHE_INFO;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getCacheStrategy() {
        return cacheStrategy;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setCacheStrategy(int newCacheStrategy) {
        int oldCacheStrategy = cacheStrategy;
        cacheStrategy = newCacheStrategy;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.CACHE_INFO__CACHE_STRATEGY, oldCacheStrategy, cacheStrategy));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.CACHE_INFO__CACHE, oldCache, cache));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.CACHE_INFO__CACHE, oldCache, cache));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.CACHE_INFO__CACHE_STRATEGY:
                return new Integer(getCacheStrategy());
            case SybaseasesqlmodelPackage.CACHE_INFO__CACHE:
                if (resolve) return getCache();
                return basicGetCache();
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
            case SybaseasesqlmodelPackage.CACHE_INFO__CACHE_STRATEGY:
                setCacheStrategy(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.CACHE_INFO__CACHE:
                setCache((SybaseASECache)newValue);
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
            case SybaseasesqlmodelPackage.CACHE_INFO__CACHE_STRATEGY:
                setCacheStrategy(CACHE_STRATEGY_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.CACHE_INFO__CACHE:
                setCache((SybaseASECache)null);
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
            case SybaseasesqlmodelPackage.CACHE_INFO__CACHE_STRATEGY:
                return cacheStrategy != CACHE_STRATEGY_EDEFAULT;
            case SybaseasesqlmodelPackage.CACHE_INFO__CACHE:
                return cache != null;
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
        result.append(" (cacheStrategy: "); //$NON-NLS-1$
        result.append(cacheStrategy);
        result.append(')');
        return result.toString();
    }

} //CacheInfoImpl
