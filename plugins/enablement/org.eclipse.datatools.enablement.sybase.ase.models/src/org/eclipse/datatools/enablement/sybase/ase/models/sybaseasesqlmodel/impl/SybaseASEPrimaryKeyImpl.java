/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEPrimaryKeyImpl.java,v 1.7 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.constraints.impl.PrimaryKeyImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Primary Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrimaryKeyImpl#getAseUniqueConstraint <em>Ase Unique Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEPrimaryKeyImpl extends PrimaryKeyImpl implements SybaseASEPrimaryKey 
{
	/**
     * The cached value of the '{@link #getAseUniqueConstraint() <em>Ase Unique Constraint</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAseUniqueConstraint()
     * @generated
     * @ordered
     */
	protected SybaseASEUniqueConstraint aseUniqueConstraint;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEPrimaryKeyImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_PRIMARY_KEY;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEUniqueConstraint getAseUniqueConstraint() {
        return aseUniqueConstraint;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAseUniqueConstraint(SybaseASEUniqueConstraint newAseUniqueConstraint, NotificationChain msgs) {
        SybaseASEUniqueConstraint oldAseUniqueConstraint = aseUniqueConstraint;
        aseUniqueConstraint = newAseUniqueConstraint;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT, oldAseUniqueConstraint, newAseUniqueConstraint);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAseUniqueConstraint(SybaseASEUniqueConstraint newAseUniqueConstraint) {
        if (newAseUniqueConstraint != aseUniqueConstraint)
        {
            NotificationChain msgs = null;
            if (aseUniqueConstraint != null)
                msgs = ((InternalEObject)aseUniqueConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT, null, msgs);
            if (newAseUniqueConstraint != null)
                msgs = ((InternalEObject)newAseUniqueConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT, null, msgs);
            msgs = basicSetAseUniqueConstraint(newAseUniqueConstraint, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT, newAseUniqueConstraint, newAseUniqueConstraint));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT:
                return basicSetAseUniqueConstraint(null, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT:
                return getAseUniqueConstraint();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT:
                setAseUniqueConstraint((SybaseASEUniqueConstraint)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT:
                setAseUniqueConstraint((SybaseASEUniqueConstraint)null);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT:
                return aseUniqueConstraint != null;
        }
        return super.eIsSet(featureID);
    }

} //SybaseASEPrimaryKeyImpl
