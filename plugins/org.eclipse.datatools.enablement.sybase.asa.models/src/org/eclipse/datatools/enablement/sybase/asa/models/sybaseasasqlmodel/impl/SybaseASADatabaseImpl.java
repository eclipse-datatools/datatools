/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASADatabaseImpl.java,v 1.8 2007/06/05 14:41:03 hcao Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASADatabaseImpl#isASECompatible <em>ASE Compatible</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASADatabaseImpl extends SybaseASABaseDatabaseImpl implements SybaseASADatabase 
{
    /**
	 * The default value of the '{@link #isASECompatible() <em>ASE Compatible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isASECompatible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ASE_COMPATIBLE_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isASECompatible() <em>ASE Compatible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isASECompatible()
	 * @generated
	 * @ordered
	 */
	protected boolean aseCompatible = ASE_COMPATIBLE_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASADatabaseImpl()
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
		return SybaseasasqlmodelPackage.Literals.SYBASE_ASA_DATABASE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isASECompatible()
    {
		return aseCompatible;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setASECompatible(boolean newASECompatible)
    {
		boolean oldASECompatible = aseCompatible;
		aseCompatible = newASECompatible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasasqlmodelPackage.SYBASE_ASA_DATABASE__ASE_COMPATIBLE, oldASECompatible, aseCompatible));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasasqlmodelPackage.SYBASE_ASA_DATABASE__ASE_COMPATIBLE:
				return isASECompatible() ? Boolean.TRUE : Boolean.FALSE;
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
			case SybaseasasqlmodelPackage.SYBASE_ASA_DATABASE__ASE_COMPATIBLE:
				setASECompatible(((Boolean)newValue).booleanValue());
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
			case SybaseasasqlmodelPackage.SYBASE_ASA_DATABASE__ASE_COMPATIBLE:
				setASECompatible(ASE_COMPATIBLE_EDEFAULT);
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
			case SybaseasasqlmodelPackage.SYBASE_ASA_DATABASE__ASE_COMPATIBLE:
				return aseCompatible != ASE_COMPATIBLE_EDEFAULT;
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
		result.append(" (ASECompatible: ");
		result.append(aseCompatible);
		result.append(')');
		return result.toString();
	}

} //SybaseASADatabaseImpl