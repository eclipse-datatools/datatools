/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Debugger Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DebuggerDefinitionImpl#isConditionSupported <em>Condition Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DebuggerDefinitionImpl extends EObjectImpl implements DebuggerDefinition {
	/**
	 * The default value of the '{@link #isConditionSupported() <em>Condition Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConditionSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONDITION_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConditionSupported() <em>Condition Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConditionSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean conditionSupported = CONDITION_SUPPORTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DebuggerDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.eINSTANCE.getDebuggerDefinition();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConditionSupported() {
		return conditionSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConditionSupported(boolean newConditionSupported) {
		boolean oldConditionSupported = conditionSupported;
		conditionSupported = newConditionSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DEBUGGER_DEFINITION__CONDITION_SUPPORTED, oldConditionSupported, conditionSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.DEBUGGER_DEFINITION__CONDITION_SUPPORTED:
				return isConditionSupported() ? Boolean.TRUE : Boolean.FALSE;
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.DEBUGGER_DEFINITION__CONDITION_SUPPORTED:
				setConditionSupported(((Boolean)newValue).booleanValue());
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.DEBUGGER_DEFINITION__CONDITION_SUPPORTED:
				setConditionSupported(CONDITION_SUPPORTED_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.DEBUGGER_DEFINITION__CONDITION_SUPPORTED:
				return conditionSupported != CONDITION_SUPPORTED_EDEFAULT;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (conditionSupported: "); //$NON-NLS-1$
		result.append(conditionSupported);
		result.append(')');
		return result.toString();
	}

} //DebuggerDefinitionImpl
