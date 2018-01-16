/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASESegmentImpl.java,v 1.7 2007/07/06 08:40:21 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Segment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESegmentImpl#getCatalog <em>Catalog</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESegmentImpl#getDeviceNames <em>Device Names</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESegmentImpl#getThresholds <em>Thresholds</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASESegmentImpl extends SQLObjectImpl implements SybaseASESegment 
{
	/**
     * The cached value of the '{@link #getDeviceNames() <em>Device Names</em>}' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDeviceNames()
     * @generated
     * @ordered
     */
	protected EList deviceNames;

	/**
     * The cached value of the '{@link #getThresholds() <em>Thresholds</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getThresholds()
     * @generated
     * @ordered
     */
	protected EList thresholds;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASESegmentImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_SEGMENT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASECatalog getCatalog() {
        if (eContainerFeatureID != SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG) return null;
        return (SybaseASECatalog)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetCatalog(SybaseASECatalog newCatalog, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newCatalog, SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setCatalog(SybaseASECatalog newCatalog) {
        if (newCatalog != eInternalContainer() || (eContainerFeatureID != SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG && newCatalog != null))
        {
            if (EcoreUtil.isAncestor(this, newCatalog))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newCatalog != null)
                msgs = ((InternalEObject)newCatalog).eInverseAdd(this, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS, SybaseASECatalog.class, msgs);
            msgs = basicSetCatalog(newCatalog, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG, newCatalog, newCatalog));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDeviceNames() {
        if (deviceNames == null)
        {
            deviceNames = new EDataTypeUniqueEList(String.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__DEVICE_NAMES);
        }
        return deviceNames;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getThresholds() {
        if (thresholds == null)
        {
            thresholds = new EObjectResolvingEList(SegmentThreshold.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__THRESHOLDS);
        }
        return thresholds;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetCatalog((SybaseASECatalog)otherEnd, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG:
                return basicSetCatalog(null, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG:
                return eInternalContainer().eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG__SEGMENTS, SybaseASECatalog.class, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG:
                return getCatalog();
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__DEVICE_NAMES:
                return getDeviceNames();
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__THRESHOLDS:
                return getThresholds();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG:
                setCatalog((SybaseASECatalog)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__DEVICE_NAMES:
                getDeviceNames().clear();
                getDeviceNames().addAll((Collection)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__THRESHOLDS:
                getThresholds().clear();
                getThresholds().addAll((Collection)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG:
                setCatalog((SybaseASECatalog)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__DEVICE_NAMES:
                getDeviceNames().clear();
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__THRESHOLDS:
                getThresholds().clear();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__CATALOG:
                return getCatalog() != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__DEVICE_NAMES:
                return deviceNames != null && !deviceNames.isEmpty();
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__THRESHOLDS:
                return thresholds != null && !thresholds.isEmpty();
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
        result.append(" (deviceNames: "); //$NON-NLS-1$
        result.append(deviceNames);
        result.append(')');
        return result.toString();
    }

} //SybaseASESegmentImpl
