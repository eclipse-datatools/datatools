/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASAIndexImpl.java,v 1.4 2007/06/05 14:41:03 hcao Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseIndexImpl;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAIndexImpl#isVirtual <em>Virtual</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASAIndexImpl extends SybaseASABaseIndexImpl implements SybaseASAIndex 
{
    /**
	 * The default value of the '{@link #isVirtual() <em>Virtual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVirtual()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VIRTUAL_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isVirtual() <em>Virtual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVirtual()
	 * @generated
	 * @ordered
	 */
	protected boolean virtual = VIRTUAL_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASAIndexImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass()
    {
		return SybaseasasqlmodelPackage.Literals.SYBASE_ASA_INDEX;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVirtual()
    {
		return virtual;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVirtual(boolean newVirtual)
    {
		boolean oldVirtual = virtual;
		virtual = newVirtual;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasasqlmodelPackage.SYBASE_ASA_INDEX__VIRTUAL, oldVirtual, virtual));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasasqlmodelPackage.SYBASE_ASA_INDEX__VIRTUAL:
				return isVirtual() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case SybaseasasqlmodelPackage.SYBASE_ASA_INDEX__VIRTUAL:
				setVirtual(((Boolean)newValue).booleanValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID)
    {
		switch (featureID) {
			case SybaseasasqlmodelPackage.SYBASE_ASA_INDEX__VIRTUAL:
				setVirtual(VIRTUAL_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case SybaseasasqlmodelPackage.SYBASE_ASA_INDEX__VIRTUAL:
				return virtual != VIRTUAL_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString()
    {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (virtual: ");
		result.append(virtual);
		result.append(')');
		return result.toString();
	}

} //SybaseASAIndexImpl