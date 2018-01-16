/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASECheckConstraintImpl.java,v 1.2 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl;

import org.eclipse.datatools.modelbase.sql.schema.Schema;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Check Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECheckConstraintImpl#getCreator <em>Creator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASECheckConstraintImpl extends CheckConstraintImpl implements SybaseASECheckConstraint {
	/**
     * The cached value of the '{@link #getCreator() <em>Creator</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCreator()
     * @generated
     * @ordered
     */
	protected Schema creator;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASECheckConstraintImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_CHECK_CONSTRAINT;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Schema getCreator() {
        if (creator != null && creator.eIsProxy())
        {
            InternalEObject oldCreator = (InternalEObject)creator;
            creator = (Schema)eResolveProxy(oldCreator);
            if (creator != oldCreator)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_CHECK_CONSTRAINT__CREATOR, oldCreator, creator));
            }
        }
        return creator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Schema basicGetCreator() {
        return creator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setCreator(Schema newCreator) {
        Schema oldCreator = creator;
        creator = newCreator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_CHECK_CONSTRAINT__CREATOR, oldCreator, creator));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_CHECK_CONSTRAINT__CREATOR:
                if (resolve) return getCreator();
                return basicGetCreator();
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CHECK_CONSTRAINT__CREATOR:
                setCreator((Schema)newValue);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CHECK_CONSTRAINT__CREATOR:
                setCreator((Schema)null);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_CHECK_CONSTRAINT__CREATOR:
                return creator != null;
        }
        return super.eIsSet(featureID);
    }

} //SybaseASECheckConstraintImpl
