/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement;

import java.util.Collection;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Security Label Component Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentElementImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentElementImpl#getParentValue <em>Parent Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentElementImpl#getLUWSecurityLabelComponent <em>LUW Security Label Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWSecurityLabelComponentElementImpl extends SQLObjectImpl implements LUWSecurityLabelComponentElement {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getParentValue() <em>Parent Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentValue()
	 * @generated
	 * @ordered
	 */
	protected static final String PARENT_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParentValue() <em>Parent Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentValue()
	 * @generated
	 * @ordered
	 */
	protected String parentValue = PARENT_VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLUWSecurityLabelComponent() <em>LUW Security Label Component</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLUWSecurityLabelComponent()
	 * @generated
	 * @ordered
	 */
	protected EList luwSecurityLabelComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWSecurityLabelComponentElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_SECURITY_LABEL_COMPONENT_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParentValue() {
		return parentValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentValue(String newParentValue) {
		String oldParentValue = parentValue;
		parentValue = newParentValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__PARENT_VALUE, oldParentValue, parentValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLUWSecurityLabelComponent() {
		if (luwSecurityLabelComponent == null) {
			luwSecurityLabelComponent = new EObjectWithInverseResolvingEList.ManyInverse(LUWSecurityLabelComponent.class, this, LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT, LUWPackage.LUW_SECURITY_LABEL_COMPONENT__ELEMENTS);
		}
		return luwSecurityLabelComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT:
				return ((InternalEList)getLUWSecurityLabelComponent()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT:
				return ((InternalEList)getLUWSecurityLabelComponent()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__VALUE:
				return getValue();
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__PARENT_VALUE:
				return getParentValue();
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT:
				return getLUWSecurityLabelComponent();
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__VALUE:
				setValue((String)newValue);
				return;
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__PARENT_VALUE:
				setParentValue((String)newValue);
				return;
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT:
				getLUWSecurityLabelComponent().clear();
				getLUWSecurityLabelComponent().addAll((Collection)newValue);
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__PARENT_VALUE:
				setParentValue(PARENT_VALUE_EDEFAULT);
				return;
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT:
				getLUWSecurityLabelComponent().clear();
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__PARENT_VALUE:
				return PARENT_VALUE_EDEFAULT == null ? parentValue != null : !PARENT_VALUE_EDEFAULT.equals(parentValue);
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT:
				return luwSecurityLabelComponent != null && !luwSecurityLabelComponent.isEmpty();
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
		result.append(" (value: "); //$NON-NLS-1$
		result.append(value);
		result.append(", parentValue: "); //$NON-NLS-1$
		result.append(parentValue);
		result.append(')');
		return result.toString();
	}

} //LUWSecurityLabelComponentElementImpl
