/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TriggerImpl#isOperative <em>Operative</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TriggerImpl#isSecured <em>Secured</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2TriggerImpl extends TriggerImpl implements DB2Trigger {
	/**
	 * The default value of the '{@link #isOperative() <em>Operative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOperative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPERATIVE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOperative() <em>Operative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOperative()
	 * @generated
	 * @ordered
	 */
	protected boolean operative = OPERATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #isSecured() <em>Secured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSecured()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SECURED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSecured() <em>Secured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSecured()
	 * @generated
	 * @ordered
	 */
	protected boolean secured = SECURED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2TriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOperative() {
		return operative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperative(boolean newOperative) {
		boolean oldOperative = operative;
		operative = newOperative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TRIGGER__OPERATIVE, oldOperative, operative));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSecured() {
		return secured;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecured(boolean newSecured) {
		boolean oldSecured = secured;
		secured = newSecured;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_TRIGGER__SECURED, oldSecured, secured));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_TRIGGER__OPERATIVE:
				return isOperative() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_TRIGGER__SECURED:
				return isSecured() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DB2ModelPackage.DB2_TRIGGER__OPERATIVE:
				setOperative(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_TRIGGER__SECURED:
				setSecured(((Boolean)newValue).booleanValue());
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
		switch (featureID) {
			case DB2ModelPackage.DB2_TRIGGER__OPERATIVE:
				setOperative(OPERATIVE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_TRIGGER__SECURED:
				setSecured(SECURED_EDEFAULT);
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
		switch (featureID) {
			case DB2ModelPackage.DB2_TRIGGER__OPERATIVE:
				return operative != OPERATIVE_EDEFAULT;
			case DB2ModelPackage.DB2_TRIGGER__SECURED:
				return secured != SECURED_EDEFAULT;
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
		result.append(" (operative: "); //$NON-NLS-1$
		result.append(operative);
		result.append(", secured: "); //$NON-NLS-1$
		result.append(secured);
		result.append(')');
		return result.toString();
	}

} //DB2TriggerImpl
