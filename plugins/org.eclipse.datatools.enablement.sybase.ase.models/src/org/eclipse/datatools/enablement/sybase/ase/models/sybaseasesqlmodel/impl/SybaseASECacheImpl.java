/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASECacheImpl.java,v 1.7 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Cache</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECacheImpl#getDatabase <em>Database</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASECacheImpl extends SQLObjectImpl implements SybaseASECache 
{
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASECacheImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_CACHE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEDatabase getDatabase() {
        if (eContainerFeatureID != SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE) return null;
        return (SybaseASEDatabase)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDatabase(SybaseASEDatabase newDatabase, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newDatabase, SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDatabase(SybaseASEDatabase newDatabase) {
        if (newDatabase != eInternalContainer() || (eContainerFeatureID != SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE && newDatabase != null))
        {
            if (EcoreUtil.isAncestor(this, newDatabase))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newDatabase != null)
                msgs = ((InternalEObject)newDatabase).eInverseAdd(this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES, SybaseASEDatabase.class, msgs);
            msgs = basicSetDatabase(newDatabase, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE, newDatabase, newDatabase));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetDatabase((SybaseASEDatabase)otherEnd, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE:
                return basicSetDatabase(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE:
                return eInternalContainer().eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__CACHES, SybaseASEDatabase.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE:
                return getDatabase();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE:
                setDatabase((SybaseASEDatabase)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE:
                setDatabase((SybaseASEDatabase)null);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CACHE__DATABASE:
                return getDatabase() != null;
        }
        return super.eIsSet(featureID);
    }

} //SybaseASECacheImpl
