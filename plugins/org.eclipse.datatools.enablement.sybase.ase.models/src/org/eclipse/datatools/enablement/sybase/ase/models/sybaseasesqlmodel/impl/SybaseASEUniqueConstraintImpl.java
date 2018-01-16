/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEUniqueConstraintImpl.java,v 1.7 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.constraints.impl.UniqueConstraintImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Unique Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUniqueConstraintImpl#getSystemGenedIndex <em>System Gened Index</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUniqueConstraintImpl#isSystemGenedName <em>System Gened Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEUniqueConstraintImpl extends UniqueConstraintImpl implements SybaseASEUniqueConstraint 
{
	/**
     * The cached value of the '{@link #getSystemGenedIndex() <em>System Gened Index</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSystemGenedIndex()
     * @generated
     * @ordered
     */
	protected SybaseASEIndex systemGenedIndex;

	/**
     * The default value of the '{@link #isSystemGenedName() <em>System Gened Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSystemGenedName()
     * @generated
     * @ordered
     */
	protected static final boolean SYSTEM_GENED_NAME_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isSystemGenedName() <em>System Gened Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSystemGenedName()
     * @generated
     * @ordered
     */
	protected boolean systemGenedName = SYSTEM_GENED_NAME_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEUniqueConstraintImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_UNIQUE_CONSTRAINT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEIndex getSystemGenedIndex() {
        return systemGenedIndex;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSystemGenedIndex(SybaseASEIndex newSystemGenedIndex, NotificationChain msgs) {
        SybaseASEIndex oldSystemGenedIndex = systemGenedIndex;
        systemGenedIndex = newSystemGenedIndex;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX, oldSystemGenedIndex, newSystemGenedIndex);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSystemGenedIndex(SybaseASEIndex newSystemGenedIndex) {
        if (newSystemGenedIndex != systemGenedIndex)
        {
            NotificationChain msgs = null;
            if (systemGenedIndex != null)
                msgs = ((InternalEObject)systemGenedIndex).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX, null, msgs);
            if (newSystemGenedIndex != null)
                msgs = ((InternalEObject)newSystemGenedIndex).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX, null, msgs);
            msgs = basicSetSystemGenedIndex(newSystemGenedIndex, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX, newSystemGenedIndex, newSystemGenedIndex));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isSystemGenedName() {
        return systemGenedName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSystemGenedName(boolean newSystemGenedName) {
        boolean oldSystemGenedName = systemGenedName;
        systemGenedName = newSystemGenedName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_NAME, oldSystemGenedName, systemGenedName));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX:
                return basicSetSystemGenedIndex(null, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX:
                return getSystemGenedIndex();
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_NAME:
                return isSystemGenedName() ? Boolean.TRUE : Boolean.FALSE;
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX:
                setSystemGenedIndex((SybaseASEIndex)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_NAME:
                setSystemGenedName(((Boolean)newValue).booleanValue());
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX:
                setSystemGenedIndex((SybaseASEIndex)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_NAME:
                setSystemGenedName(SYSTEM_GENED_NAME_EDEFAULT);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX:
                return systemGenedIndex != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_NAME:
                return systemGenedName != SYSTEM_GENED_NAME_EDEFAULT;
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
        result.append(" (systemGenedName: "); //$NON-NLS-1$
        result.append(systemGenedName);
        result.append(')');
        return result.toString();
    }

} //SybaseASEUniqueConstraintImpl
