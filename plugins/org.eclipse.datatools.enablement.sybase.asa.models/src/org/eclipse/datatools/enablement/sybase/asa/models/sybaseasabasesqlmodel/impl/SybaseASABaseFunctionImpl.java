/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseFunctionImpl.java,v 1.5 2008/04/28 17:14:18 bfitzpatrick Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseFunction;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.util.SybaseRoutineUtil;

import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;

import org.eclipse.datatools.modelbase.sql.routines.impl.UserDefinedFunctionImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseFunctionImpl#isOnExceptionResume <em>On Exception Resume</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseFunctionImpl extends UserDefinedFunctionImpl implements SybaseASABaseFunction 
{
    /**
	 * The default value of the '{@link #isOnExceptionResume() <em>On Exception Resume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnExceptionResume()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ON_EXCEPTION_RESUME_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isOnExceptionResume() <em>On Exception Resume</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOnExceptionResume()
	 * @generated
	 * @ordered
	 */
	protected boolean onExceptionResume = ON_EXCEPTION_RESUME_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseFunctionImpl()
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
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_FUNCTION;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSystem() {
		String owner = this.getSchema().getName();
		return owner.equals("SYS") || owner.equals("rs_systabgroup");
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOnExceptionResume()
    {
		return onExceptionResume;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnExceptionResume(boolean newOnExceptionResume)
    {
		boolean oldOnExceptionResume = onExceptionResume;
		onExceptionResume = newOnExceptionResume;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION__ON_EXCEPTION_RESUME, oldOnExceptionResume, onExceptionResume));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void parseParameterDefaultValues()
    {
        SybaseRoutineUtil.parseParameterDefaultValues(this, parameters);
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION__ON_EXCEPTION_RESUME:
				return isOnExceptionResume() ? Boolean.TRUE : Boolean.FALSE;
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION__ON_EXCEPTION_RESUME:
				setOnExceptionResume(((Boolean)newValue).booleanValue());
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION__ON_EXCEPTION_RESUME:
				setOnExceptionResume(ON_EXCEPTION_RESUME_EDEFAULT);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION__ON_EXCEPTION_RESUME:
				return onExceptionResume != ON_EXCEPTION_RESUME_EDEFAULT;
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
		result.append(" (onExceptionResume: ");
		result.append(onExceptionResume);
		result.append(')');
		return result.toString();
	}

} //SybaseASABaseFunctionImpl