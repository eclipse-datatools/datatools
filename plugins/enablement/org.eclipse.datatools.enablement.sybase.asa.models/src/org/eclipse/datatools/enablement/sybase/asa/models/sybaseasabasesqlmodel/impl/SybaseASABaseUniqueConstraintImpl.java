/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseUniqueConstraintImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.constraints.impl.UniqueConstraintImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Unique Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUniqueConstraintImpl#getSystemGenIndex <em>System Gen Index</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SybaseASABaseUniqueConstraintImpl extends UniqueConstraintImpl implements SybaseASABaseUniqueConstraint
{
    /**
	 * The cached value of the '{@link #getSystemGenIndex() <em>System Gen Index</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSystemGenIndex()
	 * @generated
	 * @ordered
	 */
    protected SybaseASABaseIndex systemGenIndex;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseUniqueConstraintImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
    {
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_UNIQUE_CONSTRAINT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public SybaseASABaseIndex getSystemGenIndex()
    {
		if (systemGenIndex != null && systemGenIndex.eIsProxy()) {
			InternalEObject oldSystemGenIndex = (InternalEObject)systemGenIndex;
			systemGenIndex = (SybaseASABaseIndex)eResolveProxy(oldSystemGenIndex);
			if (systemGenIndex != oldSystemGenIndex) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX, oldSystemGenIndex, systemGenIndex));
				}
			}
		}
		return systemGenIndex;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseIndex basicGetSystemGenIndex()
    {
		return systemGenIndex;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
	public void setSystemGenIndex(SybaseASABaseIndex newSystemGenIndex)
    {
		SybaseASABaseIndex oldSystemGenIndex = systemGenIndex;
		systemGenIndex = newSystemGenIndex;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX, oldSystemGenIndex, systemGenIndex));
		}
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX:
				if (resolve) {
					return getSystemGenIndex();
				}
				return basicGetSystemGenIndex();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX:
				setSystemGenIndex((SybaseASABaseIndex)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX:
				setSystemGenIndex((SybaseASABaseIndex)null);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX:
				return systemGenIndex != null;
		}
		return super.eIsSet(featureID);
	}

} //SybaseASABaseUniqueConstraintImpl