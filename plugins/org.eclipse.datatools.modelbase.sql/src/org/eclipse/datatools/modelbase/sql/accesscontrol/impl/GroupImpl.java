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

import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.GroupImpl#getUser <em>User</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupImpl extends AuthorizationIdentifierImpl implements Group {
	/**
	 * The cached value of the '{@link #getUser() <em>User</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected EList user = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLAccessControlPackage.eINSTANCE.getGroup();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getUser() {
		if (user == null) {
			user = new EObjectResolvingEList(User.class, this, SQLAccessControlPackage.GROUP__USER);
		}
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLAccessControlPackage.GROUP__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__OWNED_SCHEMA:
					return ((InternalEList)getOwnedSchema()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__RECEIVED_ROLE_AUTHORIZATION:
					return ((InternalEList)getReceivedRoleAuthorization()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__GRANTED_ROLE_AUTHORIZATION:
					return ((InternalEList)getGrantedRoleAuthorization()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__GRANTED_PRIVILEGE:
					return ((InternalEList)getGrantedPrivilege()).basicAdd(otherEnd, msgs);
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
				case SQLAccessControlPackage.GROUP__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__OWNED_SCHEMA:
					return ((InternalEList)getOwnedSchema()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__RECEIVED_ROLE_AUTHORIZATION:
					return ((InternalEList)getReceivedRoleAuthorization()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__GRANTED_ROLE_AUTHORIZATION:
					return ((InternalEList)getGrantedRoleAuthorization()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__GRANTED_PRIVILEGE:
					return ((InternalEList)getGrantedPrivilege()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.GROUP__RECEIVED_PRIVILEGE:
					return ((InternalEList)getReceivedPrivilege()).basicRemove(otherEnd, msgs);
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
			case SQLAccessControlPackage.GROUP__EANNOTATIONS:
				return getEAnnotations();
			case SQLAccessControlPackage.GROUP__NAME:
				return getName();
			case SQLAccessControlPackage.GROUP__DEPENDENCIES:
				return getDependencies();
			case SQLAccessControlPackage.GROUP__DESCRIPTION:
				return getDescription();
			case SQLAccessControlPackage.GROUP__LABEL:
				return getLabel();
			case SQLAccessControlPackage.GROUP__OWNED_SCHEMA:
				return getOwnedSchema();
			case SQLAccessControlPackage.GROUP__RECEIVED_ROLE_AUTHORIZATION:
				return getReceivedRoleAuthorization();
			case SQLAccessControlPackage.GROUP__GRANTED_ROLE_AUTHORIZATION:
				return getGrantedRoleAuthorization();
			case SQLAccessControlPackage.GROUP__GRANTED_PRIVILEGE:
				return getGrantedPrivilege();
			case SQLAccessControlPackage.GROUP__RECEIVED_PRIVILEGE:
				return getReceivedPrivilege();
			case SQLAccessControlPackage.GROUP__USER:
				return getUser();
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
			case SQLAccessControlPackage.GROUP__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.GROUP__NAME:
				setName((String)newValue);
				return;
			case SQLAccessControlPackage.GROUP__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.GROUP__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLAccessControlPackage.GROUP__LABEL:
				setLabel((String)newValue);
				return;
			case SQLAccessControlPackage.GROUP__OWNED_SCHEMA:
				getOwnedSchema().clear();
				getOwnedSchema().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.GROUP__RECEIVED_ROLE_AUTHORIZATION:
				getReceivedRoleAuthorization().clear();
				getReceivedRoleAuthorization().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.GROUP__GRANTED_ROLE_AUTHORIZATION:
				getGrantedRoleAuthorization().clear();
				getGrantedRoleAuthorization().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.GROUP__GRANTED_PRIVILEGE:
				getGrantedPrivilege().clear();
				getGrantedPrivilege().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.GROUP__RECEIVED_PRIVILEGE:
				getReceivedPrivilege().clear();
				getReceivedPrivilege().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.GROUP__USER:
				getUser().clear();
				getUser().addAll((Collection)newValue);
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
			case SQLAccessControlPackage.GROUP__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLAccessControlPackage.GROUP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLAccessControlPackage.GROUP__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLAccessControlPackage.GROUP__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLAccessControlPackage.GROUP__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLAccessControlPackage.GROUP__OWNED_SCHEMA:
				getOwnedSchema().clear();
				return;
			case SQLAccessControlPackage.GROUP__RECEIVED_ROLE_AUTHORIZATION:
				getReceivedRoleAuthorization().clear();
				return;
			case SQLAccessControlPackage.GROUP__GRANTED_ROLE_AUTHORIZATION:
				getGrantedRoleAuthorization().clear();
				return;
			case SQLAccessControlPackage.GROUP__GRANTED_PRIVILEGE:
				getGrantedPrivilege().clear();
				return;
			case SQLAccessControlPackage.GROUP__RECEIVED_PRIVILEGE:
				getReceivedPrivilege().clear();
				return;
			case SQLAccessControlPackage.GROUP__USER:
				getUser().clear();
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
			case SQLAccessControlPackage.GROUP__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLAccessControlPackage.GROUP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLAccessControlPackage.GROUP__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLAccessControlPackage.GROUP__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLAccessControlPackage.GROUP__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLAccessControlPackage.GROUP__OWNED_SCHEMA:
				return ownedSchema != null && !ownedSchema.isEmpty();
			case SQLAccessControlPackage.GROUP__RECEIVED_ROLE_AUTHORIZATION:
				return receivedRoleAuthorization != null && !receivedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.GROUP__GRANTED_ROLE_AUTHORIZATION:
				return grantedRoleAuthorization != null && !grantedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.GROUP__GRANTED_PRIVILEGE:
				return grantedPrivilege != null && !grantedPrivilege.isEmpty();
			case SQLAccessControlPackage.GROUP__RECEIVED_PRIVILEGE:
				return receivedPrivilege != null && !receivedPrivilege.isEmpty();
			case SQLAccessControlPackage.GROUP__USER:
				return user != null && !user.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //GroupImpl
