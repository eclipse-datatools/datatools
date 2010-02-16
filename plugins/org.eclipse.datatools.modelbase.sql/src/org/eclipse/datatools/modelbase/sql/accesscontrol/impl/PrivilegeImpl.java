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
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

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
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl#isWithHierarchy <em>With Hierarchy</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl#getGrantor <em>Grantor</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl#getGrantee <em>Grantee</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.PrivilegeImpl#getActionObjects <em>Action Objects</em>}</li>
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
	 * The default value of the '{@link #isWithHierarchy() <em>With Hierarchy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWithHierarchy()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WITH_HIERARCHY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isWithHierarchy() <em>With Hierarchy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWithHierarchy()
	 * @generated
	 * @ordered
	 */
	protected boolean withHierarchy = WITH_HIERARCHY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGrantor() <em>Grantor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrantor()
	 * @generated
	 * @ordered
	 */
	protected AuthorizationIdentifier grantor;

	/**
	 * The cached value of the '{@link #getActionObjects() <em>Action Objects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionObjects()
	 * @generated
	 * @ordered
	 */
	protected EList actionObjects;

	/**
	 * The cached value of the '{@link #getObject() <em>Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObject()
	 * @generated
	 * @ordered
	 */
	protected SQLObject object;

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
		return SQLAccessControlPackage.Literals.PRIVILEGE;
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
	public boolean isWithHierarchy() {
		return withHierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWithHierarchy(boolean newWithHierarchy) {
		boolean oldWithHierarchy = withHierarchy;
		withHierarchy = newWithHierarchy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.PRIVILEGE__WITH_HIERARCHY, oldWithHierarchy, withHierarchy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthorizationIdentifier getGrantor() {
		if (grantor != null && grantor.eIsProxy()) {
			InternalEObject oldGrantor = (InternalEObject)grantor;
			grantor = (AuthorizationIdentifier)eResolveProxy(oldGrantor);
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
	public AuthorizationIdentifier getGrantee() {
		if (eContainerFeatureID != SQLAccessControlPackage.PRIVILEGE__GRANTEE) return null;
		return (AuthorizationIdentifier)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrantee(AuthorizationIdentifier newGrantee, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrantee, SQLAccessControlPackage.PRIVILEGE__GRANTEE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrantee(AuthorizationIdentifier newGrantee) {
		if (newGrantee != eInternalContainer() || (eContainerFeatureID != SQLAccessControlPackage.PRIVILEGE__GRANTEE && newGrantee != null)) {
			if (EcoreUtil.isAncestor(this, newGrantee))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrantee != null)
				msgs = ((InternalEObject)newGrantee).eInverseAdd(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE, AuthorizationIdentifier.class, msgs);
			msgs = basicSetGrantee(newGrantee, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.PRIVILEGE__GRANTEE, newGrantee, newGrantee));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getActionObjects() {
		if (actionObjects == null) {
			actionObjects = new EObjectResolvingEList(SQLObject.class, this, SQLAccessControlPackage.PRIVILEGE__ACTION_OBJECTS);
		}
		return actionObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLObject getObject() {
		if (object != null && object.eIsProxy()) {
			InternalEObject oldObject = (InternalEObject)object;
			object = (SQLObject)eResolveProxy(oldObject);
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
	public NotificationChain basicSetObject(SQLObject newObject, NotificationChain msgs) {
		SQLObject oldObject = object;
		object = newObject;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.PRIVILEGE__OBJECT, oldObject, newObject);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObject(SQLObject newObject) {
		if (newObject != object) {
			NotificationChain msgs = null;
			if (object != null)
				msgs = ((InternalEObject)object).eInverseRemove(this, SQLSchemaPackage.SQL_OBJECT__PRIVILEGES, SQLObject.class, msgs);
			if (newObject != null)
				msgs = ((InternalEObject)newObject).eInverseAdd(this, SQLSchemaPackage.SQL_OBJECT__PRIVILEGES, SQLObject.class, msgs);
			msgs = basicSetObject(newObject, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.PRIVILEGE__OBJECT, newObject, newObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				if (grantor != null)
					msgs = ((InternalEObject)grantor).eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE, AuthorizationIdentifier.class, msgs);
				return basicSetGrantor((AuthorizationIdentifier)otherEnd, msgs);
			case SQLAccessControlPackage.PRIVILEGE__GRANTEE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrantee((AuthorizationIdentifier)otherEnd, msgs);
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				if (object != null)
					msgs = ((InternalEObject)object).eInverseRemove(this, SQLSchemaPackage.SQL_OBJECT__PRIVILEGES, SQLObject.class, msgs);
				return basicSetObject((SQLObject)otherEnd, msgs);
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
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				return basicSetGrantor(null, msgs);
			case SQLAccessControlPackage.PRIVILEGE__GRANTEE:
				return basicSetGrantee(null, msgs);
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				return basicSetObject(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case SQLAccessControlPackage.PRIVILEGE__GRANTEE:
				return eInternalContainer().eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE, AuthorizationIdentifier.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLAccessControlPackage.PRIVILEGE__GRANTABLE:
				return isGrantable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLAccessControlPackage.PRIVILEGE__ACTION:
				return getAction();
			case SQLAccessControlPackage.PRIVILEGE__WITH_HIERARCHY:
				return isWithHierarchy() ? Boolean.TRUE : Boolean.FALSE;
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				if (resolve) return getGrantor();
				return basicGetGrantor();
			case SQLAccessControlPackage.PRIVILEGE__GRANTEE:
				return getGrantee();
			case SQLAccessControlPackage.PRIVILEGE__ACTION_OBJECTS:
				return getActionObjects();
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				if (resolve) return getObject();
				return basicGetObject();
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
			case SQLAccessControlPackage.PRIVILEGE__GRANTABLE:
				setGrantable(((Boolean)newValue).booleanValue());
				return;
			case SQLAccessControlPackage.PRIVILEGE__ACTION:
				setAction((String)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__WITH_HIERARCHY:
				setWithHierarchy(((Boolean)newValue).booleanValue());
				return;
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				setGrantor((AuthorizationIdentifier)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__GRANTEE:
				setGrantee((AuthorizationIdentifier)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__ACTION_OBJECTS:
				getActionObjects().clear();
				getActionObjects().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				setObject((SQLObject)newValue);
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
			case SQLAccessControlPackage.PRIVILEGE__GRANTABLE:
				setGrantable(GRANTABLE_EDEFAULT);
				return;
			case SQLAccessControlPackage.PRIVILEGE__ACTION:
				setAction(ACTION_EDEFAULT);
				return;
			case SQLAccessControlPackage.PRIVILEGE__WITH_HIERARCHY:
				setWithHierarchy(WITH_HIERARCHY_EDEFAULT);
				return;
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				setGrantor((AuthorizationIdentifier)null);
				return;
			case SQLAccessControlPackage.PRIVILEGE__GRANTEE:
				setGrantee((AuthorizationIdentifier)null);
				return;
			case SQLAccessControlPackage.PRIVILEGE__ACTION_OBJECTS:
				getActionObjects().clear();
				return;
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				setObject((SQLObject)null);
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
			case SQLAccessControlPackage.PRIVILEGE__GRANTABLE:
				return grantable != GRANTABLE_EDEFAULT;
			case SQLAccessControlPackage.PRIVILEGE__ACTION:
				return ACTION_EDEFAULT == null ? action != null : !ACTION_EDEFAULT.equals(action);
			case SQLAccessControlPackage.PRIVILEGE__WITH_HIERARCHY:
				return withHierarchy != WITH_HIERARCHY_EDEFAULT;
			case SQLAccessControlPackage.PRIVILEGE__GRANTOR:
				return grantor != null;
			case SQLAccessControlPackage.PRIVILEGE__GRANTEE:
				return getGrantee() != null;
			case SQLAccessControlPackage.PRIVILEGE__ACTION_OBJECTS:
				return actionObjects != null && !actionObjects.isEmpty();
			case SQLAccessControlPackage.PRIVILEGE__OBJECT:
				return object != null;
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
		result.append(" (grantable: "); //$NON-NLS-1$
		result.append(grantable);
		result.append(", action: "); //$NON-NLS-1$
		result.append(action);
		result.append(", withHierarchy: "); //$NON-NLS-1$
		result.append(withHierarchy);
		result.append(')');
		return result.toString();
	}

} //PrivilegeImpl
