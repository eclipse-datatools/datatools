/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Security Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelImpl#getSecurityLabel <em>Security Label</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelImpl#getPolicy <em>Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWSecurityLabelImpl extends SQLObjectImpl implements LUWSecurityLabel {
	/**
	 * The default value of the '{@link #getSecurityLabel() <em>Security Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String SECURITY_LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecurityLabel() <em>Security Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityLabel()
	 * @generated
	 * @ordered
	 */
	protected String securityLabel = SECURITY_LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPolicy() <em>Policy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolicy()
	 * @generated
	 * @ordered
	 */
	protected LUWSecurityPolicy policy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWSecurityLabelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_SECURITY_LABEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSecurityLabel() {
		return securityLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityLabel(String newSecurityLabel) {
		String oldSecurityLabel = securityLabel;
		securityLabel = newSecurityLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SECURITY_LABEL__SECURITY_LABEL, oldSecurityLabel, securityLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityPolicy getPolicy() {
		if (policy != null && policy.eIsProxy()) {
			InternalEObject oldPolicy = (InternalEObject)policy;
			policy = (LUWSecurityPolicy)eResolveProxy(oldPolicy);
			if (policy != oldPolicy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_SECURITY_LABEL__POLICY, oldPolicy, policy));
			}
		}
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityPolicy basicGetPolicy() {
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPolicy(LUWSecurityPolicy newPolicy, NotificationChain msgs) {
		LUWSecurityPolicy oldPolicy = policy;
		policy = newPolicy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SECURITY_LABEL__POLICY, oldPolicy, newPolicy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPolicy(LUWSecurityPolicy newPolicy) {
		if (newPolicy != policy) {
			NotificationChain msgs = null;
			if (policy != null)
				msgs = ((InternalEObject)policy).eInverseRemove(this, LUWPackage.LUW_SECURITY_POLICY__LABELS, LUWSecurityPolicy.class, msgs);
			if (newPolicy != null)
				msgs = ((InternalEObject)newPolicy).eInverseAdd(this, LUWPackage.LUW_SECURITY_POLICY__LABELS, LUWSecurityPolicy.class, msgs);
			msgs = basicSetPolicy(newPolicy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SECURITY_LABEL__POLICY, newPolicy, newPolicy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_SECURITY_LABEL__POLICY:
				if (policy != null)
					msgs = ((InternalEObject)policy).eInverseRemove(this, LUWPackage.LUW_SECURITY_POLICY__LABELS, LUWSecurityPolicy.class, msgs);
				return basicSetPolicy((LUWSecurityPolicy)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_SECURITY_LABEL__POLICY:
				return basicSetPolicy(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_SECURITY_LABEL__SECURITY_LABEL:
				return getSecurityLabel();
			case LUWPackage.LUW_SECURITY_LABEL__POLICY:
				if (resolve) return getPolicy();
				return basicGetPolicy();
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
			case LUWPackage.LUW_SECURITY_LABEL__SECURITY_LABEL:
				setSecurityLabel((String)newValue);
				return;
			case LUWPackage.LUW_SECURITY_LABEL__POLICY:
				setPolicy((LUWSecurityPolicy)newValue);
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
			case LUWPackage.LUW_SECURITY_LABEL__SECURITY_LABEL:
				setSecurityLabel(SECURITY_LABEL_EDEFAULT);
				return;
			case LUWPackage.LUW_SECURITY_LABEL__POLICY:
				setPolicy((LUWSecurityPolicy)null);
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
			case LUWPackage.LUW_SECURITY_LABEL__SECURITY_LABEL:
				return SECURITY_LABEL_EDEFAULT == null ? securityLabel != null : !SECURITY_LABEL_EDEFAULT.equals(securityLabel);
			case LUWPackage.LUW_SECURITY_LABEL__POLICY:
				return policy != null;
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
		result.append(" (securityLabel: "); //$NON-NLS-1$
		result.append(securityLabel);
		result.append(')');
		return result.toString();
	}

} //LUWSecurityLabelImpl
