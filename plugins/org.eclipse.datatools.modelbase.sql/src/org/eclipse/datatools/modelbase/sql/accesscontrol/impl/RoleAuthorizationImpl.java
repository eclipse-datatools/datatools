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
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
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
 * An implementation of the model object '<em><b>Role Authorization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleAuthorizationImpl#isGrantable <em>Grantable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleAuthorizationImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleAuthorizationImpl#getGrantee <em>Grantee</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleAuthorizationImpl#getGrantor <em>Grantor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleAuthorizationImpl extends SQLObjectImpl implements RoleAuthorization {
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
	 * The cached value of the '{@link #getRole() <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected Role role;

	/**
	 * The cached value of the '{@link #getGrantee() <em>Grantee</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrantee()
	 * @generated
	 * @ordered
	 */
	protected AuthorizationIdentifier grantee;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleAuthorizationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLAccessControlPackage.Literals.ROLE_AUTHORIZATION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTABLE, oldGrantable, grantable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role getRole() {
		if (role != null && role.eIsProxy()) {
			InternalEObject oldRole = (InternalEObject)role;
			role = (Role)eResolveProxy(oldRole);
			if (role != oldRole) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE, oldRole, role));
			}
		}
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role basicGetRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRole(Role newRole, NotificationChain msgs) {
		Role oldRole = role;
		role = newRole;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE, oldRole, newRole);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRole(Role newRole) {
		if (newRole != role) {
			NotificationChain msgs = null;
			if (role != null)
				msgs = ((InternalEObject)role).eInverseRemove(this, SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION, Role.class, msgs);
			if (newRole != null)
				msgs = ((InternalEObject)newRole).eInverseAdd(this, SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION, Role.class, msgs);
			msgs = basicSetRole(newRole, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE, newRole, newRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthorizationIdentifier getGrantee() {
		if (grantee != null && grantee.eIsProxy()) {
			InternalEObject oldGrantee = (InternalEObject)grantee;
			grantee = (AuthorizationIdentifier)eResolveProxy(oldGrantee);
			if (grantee != oldGrantee) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE, oldGrantee, grantee));
			}
		}
		return grantee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthorizationIdentifier basicGetGrantee() {
		return grantee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrantee(AuthorizationIdentifier newGrantee, NotificationChain msgs) {
		AuthorizationIdentifier oldGrantee = grantee;
		grantee = newGrantee;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE, oldGrantee, newGrantee);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrantee(AuthorizationIdentifier newGrantee) {
		if (newGrantee != grantee) {
			NotificationChain msgs = null;
			if (grantee != null)
				msgs = ((InternalEObject)grantee).eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION, AuthorizationIdentifier.class, msgs);
			if (newGrantee != null)
				msgs = ((InternalEObject)newGrantee).eInverseAdd(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION, AuthorizationIdentifier.class, msgs);
			msgs = basicSetGrantee(newGrantee, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE, newGrantee, newGrantee));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR, oldGrantor, grantor));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR, oldGrantor, newGrantor);
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
				msgs = ((InternalEObject)grantor).eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION, AuthorizationIdentifier.class, msgs);
			if (newGrantor != null)
				msgs = ((InternalEObject)newGrantor).eInverseAdd(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION, AuthorizationIdentifier.class, msgs);
			msgs = basicSetGrantor(newGrantor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR, newGrantor, newGrantor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE:
				if (role != null)
					msgs = ((InternalEObject)role).eInverseRemove(this, SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION, Role.class, msgs);
				return basicSetRole((Role)otherEnd, msgs);
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE:
				if (grantee != null)
					msgs = ((InternalEObject)grantee).eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION, AuthorizationIdentifier.class, msgs);
				return basicSetGrantee((AuthorizationIdentifier)otherEnd, msgs);
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR:
				if (grantor != null)
					msgs = ((InternalEObject)grantor).eInverseRemove(this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION, AuthorizationIdentifier.class, msgs);
				return basicSetGrantor((AuthorizationIdentifier)otherEnd, msgs);
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
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE:
				return basicSetRole(null, msgs);
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE:
				return basicSetGrantee(null, msgs);
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR:
				return basicSetGrantor(null, msgs);
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
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTABLE:
				return isGrantable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE:
				if (resolve) return getRole();
				return basicGetRole();
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE:
				if (resolve) return getGrantee();
				return basicGetGrantee();
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR:
				if (resolve) return getGrantor();
				return basicGetGrantor();
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
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTABLE:
				setGrantable(((Boolean)newValue).booleanValue());
				return;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE:
				setRole((Role)newValue);
				return;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE:
				setGrantee((AuthorizationIdentifier)newValue);
				return;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR:
				setGrantor((AuthorizationIdentifier)newValue);
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
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTABLE:
				setGrantable(GRANTABLE_EDEFAULT);
				return;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE:
				setRole((Role)null);
				return;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE:
				setGrantee((AuthorizationIdentifier)null);
				return;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR:
				setGrantor((AuthorizationIdentifier)null);
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
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTABLE:
				return grantable != GRANTABLE_EDEFAULT;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE:
				return role != null;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE:
				return grantee != null;
			case SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR:
				return grantor != null;
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
		result.append(')');
		return result.toString();
	}

} //RoleAuthorizationImpl
