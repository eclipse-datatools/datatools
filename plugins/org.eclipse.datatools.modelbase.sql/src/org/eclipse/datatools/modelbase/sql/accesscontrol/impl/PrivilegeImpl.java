/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.accesscontrol.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Privilege</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl#isGrantable <em>Grantable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl#getAction <em>Action</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl#getGrantor <em>Grantor</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl#getObject <em>Object</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PrivilegeImpl extends SQLObjectImpl implements Privilege {
	/**
	 * The default value of the '{@link #isGrantable() <em>Grantable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGrantable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GRANTABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isGrantable() <em>Grantable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGrantable()
	 * @generated
	 * @ordered
	 */
	protected boolean grantable = GRANTABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected String action = ACTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGrantor() <em>Grantor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrantor()
	 * @generated
	 * @ordered
	 */
	protected AuthorizationIdentifier grantor = null;

	/**
	 * The cached value of the '{@link #getObject() <em>Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObject()
	 * @generated
	 * @ordered
	 */
	protected SQLObject object = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrivilegeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLAccessControlPackage.eINSTANCE.getPrivilege();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isGrantable() {
		return grantable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrantable(boolean newGrantable) {
		boolean oldGrantable = grantable;
		grantable = newGrantable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.PRIVILEGE__GRANTABLE, oldGrantable, grantable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAction() {
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction(String newAction) {
		String oldAction = action;
		action = newAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.PRIVILEGE__ACTION, oldAction, action));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthorizationIdentifier getGrantor() {
		if (grantor != null && grantor.eIsProxy()) {
			AuthorizationIdentifier oldGrantor = grantor;
			grantor = (AuthorizationIdentifier)eResolveProxy((InternalEObject)grantor);
			if (grantor != oldGrantor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLAccessControlPackage.PRIVILEGE__GRANTOR, oldGrantor, grantor));
			}
		}
		return grantor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthorizationIdentifier basicGetGrantor() {
		return grantor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrantor(AuthorizationIdentifier newGrantor, NotificationChain msgs) {
		AuthorizationIdentifier oldGrantor = grantor;
		grantor = newGrantor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.PRIVILEGE__GRANTOR, oldGrantor, newGrantor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrantor(AuthorizationIdentifier newGrantor) {
		if (newGrantor != grantor) {
			NotificationChain msgs = null;
			if (grantor != null)
				msgs = ((InternalEObject)grantor).eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE, AuthorizationIdentifier.class, msgs);
			if (newGrantor != null)
				msgs = ((InternalEObject)newGrantor).eInverseAdd(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE, AuthorizationIdentifier.class, msgs);
			msgs = basicSetGrantor(newGrantor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.PRIVILEGE__GRANTOR, newGrantor, newGrantor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLObject getObject() {
		if (object != null && object.eIsProxy()) {
			SQLObject oldObject = object;
			object = (SQLObject)eResolveProxy((InternalEObject)object);
			if (object != oldObject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLAccessControlPackage.PRIVILEGE__OBJECT, oldObject, object));
			}
		}
		return object;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLObject basicGetObject() {
		return object;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObject(SQLObject newObject) {
		SQLObject oldObject = object;
		object = newObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.PRIVILEGE__OBJECT, oldObject, object));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLAccessControlPackage.PRIVILEGE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
					if (grantor != null)
						msgs = ((InternalEObject)grantor).eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE, AuthorizationIdentifier.class, msgs);
					return basicSetGrantor((AuthorizationIdentifier)otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLAccessControlPackage.PRIVILEGE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.PRIVILEGE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
					return basicSetGrantor(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLAccessControlPackage.PRIVILEGE__EANNOTATIONS:
				return getEAnnotations();
			case SQLAccessControlPackage.PRIVILEGE__NAME:
				return getName();
			case SQLAccessControlPackage.PRIVILEGE__DEPENDENCIES:
				return getDependencies();
			case SQLAccessControlPackage.PRIVILEGE__DESCRIPTION:
				return getDescription();
			case SQLAccessControlPackage.PRIVILEGE__LABEL:
				return getLabel();
			case SQLAccessControlPackage.PRIVILEGE__GRANTABLE:
				return isGrantable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLAccessControlPackage.PRIVILEGE__ACTION:
				return getAction();
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				if (resolve) return getGrantor();
				return basicGetGrantor();
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				if (resolve) return getObject();
				return basicGetObject();
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
			case SQLAccessControlPackage.PRIVILEGE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__NAME:
				setName((String)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__GRANTABLE:
				setGrantable(((Boolean)newValue).booleanValue());
				return;
			case SQLAccessControlPackage.PRIVILEGE__ACTION:
				setAction((String)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				setGrantor((AuthorizationIdentifier)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				setObject((SQLObject)newValue);
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
			case SQLAccessControlPackage.PRIVILEGE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLAccessControlPackage.PRIVILEGE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLAccessControlPackage.PRIVILEGE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLAccessControlPackage.PRIVILEGE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLAccessControlPackage.PRIVILEGE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLAccessControlPackage.PRIVILEGE__GRANTABLE:
				setGrantable(GRANTABLE_EDEFAULT);
				return;
			case SQLAccessControlPackage.PRIVILEGE__ACTION:
				setAction(ACTION_EDEFAULT);
				return;
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				setGrantor((AuthorizationIdentifier)null);
				return;
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				setObject((SQLObject)null);
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
			case SQLAccessControlPackage.PRIVILEGE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLAccessControlPackage.PRIVILEGE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLAccessControlPackage.PRIVILEGE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLAccessControlPackage.PRIVILEGE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLAccessControlPackage.PRIVILEGE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLAccessControlPackage.PRIVILEGE__GRANTABLE:
				return grantable != GRANTABLE_EDEFAULT;
			case SQLAccessControlPackage.PRIVILEGE__ACTION:
				return ACTION_EDEFAULT == null ? action != null : !ACTION_EDEFAULT.equals(action);
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				return grantor != null;
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				return object != null;
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
		result.append(" (grantable: "); //$NON-NLS-1$
		result.append(grantable);
		result.append(", action: "); //$NON-NLS-1$
		result.append(action);
		result.append(')');
		return result.toString();
	}

} //PrivilegeImpl
