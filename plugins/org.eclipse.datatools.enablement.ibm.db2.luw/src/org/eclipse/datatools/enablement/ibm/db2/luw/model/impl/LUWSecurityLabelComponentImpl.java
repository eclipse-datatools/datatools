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
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Security Label Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentImpl#getLUWSecurityPolicy <em>LUW Security Policy</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentImpl#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWSecurityLabelComponentImpl extends SQLObjectImpl implements LUWSecurityLabelComponent {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final LUWSecurityLabelComponentType TYPE_EDEFAULT = LUWSecurityLabelComponentType.SET_LITERAL;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected LUWSecurityLabelComponentType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLUWSecurityPolicy() <em>LUW Security Policy</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLUWSecurityPolicy()
	 * @generated
	 * @ordered
	 */
	protected EList luwSecurityPolicy;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList elements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWSecurityLabelComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_SECURITY_LABEL_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWSecurityLabelComponentType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(LUWSecurityLabelComponentType newType) {
		LUWSecurityLabelComponentType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SECURITY_LABEL_COMPONENT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLUWSecurityPolicy() {
		if (luwSecurityPolicy == null) {
			luwSecurityPolicy = new EObjectWithInverseResolvingEList.ManyInverse(LUWSecurityPolicy.class, this, LUWPackage.LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY, LUWPackage.LUW_SECURITY_POLICY__COMPONENTS);
		}
		return luwSecurityPolicy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getElements() {
		if (elements == null) {
			elements = new EObjectWithInverseResolvingEList.ManyInverse(LUWSecurityLabelComponentElement.class, this, LUWPackage.LUW_SECURITY_LABEL_COMPONENT__ELEMENTS, LUWPackage.LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY:
				return ((InternalEList)getLUWSecurityPolicy()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__ELEMENTS:
				return ((InternalEList)getElements()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY:
				return ((InternalEList)getLUWSecurityPolicy()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__ELEMENTS:
				return ((InternalEList)getElements()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__TYPE:
				return getType();
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY:
				return getLUWSecurityPolicy();
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__ELEMENTS:
				return getElements();
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__TYPE:
				setType((LUWSecurityLabelComponentType)newValue);
				return;
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY:
				getLUWSecurityPolicy().clear();
				getLUWSecurityPolicy().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection)newValue);
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY:
				getLUWSecurityPolicy().clear();
				return;
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__ELEMENTS:
				getElements().clear();
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
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__TYPE:
				return type != TYPE_EDEFAULT;
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY:
				return luwSecurityPolicy != null && !luwSecurityPolicy.isEmpty();
			case LUWPackage.LUW_SECURITY_LABEL_COMPONENT__ELEMENTS:
				return elements != null && !elements.isEmpty();
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
		result.append(" (type: "); //$NON-NLS-1$
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //LUWSecurityLabelComponentImpl
