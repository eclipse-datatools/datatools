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

import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class UserImpl extends AuthorizationIdentifierImpl implements User {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLAccessControlPackage.eINSTANCE.getUser();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLAccessControlPackage.USER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.USER__OWNED_SCHEMA:
					return ((InternalEList)getOwnedSchema()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION:
					return ((InternalEList)getReceivedRoleAuthorization()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.USER__GRANTED_ROLE_AUTHORIZATION:
					return ((InternalEList)getGrantedRoleAuthorization()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.USER__GRANTED_PRIVILEGE:
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
				case SQLAccessControlPackage.USER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.USER__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.USER__OWNED_SCHEMA:
					return ((InternalEList)getOwnedSchema()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION:
					return ((InternalEList)getReceivedRoleAuthorization()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.USER__GRANTED_ROLE_AUTHORIZATION:
					return ((InternalEList)getGrantedRoleAuthorization()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.USER__GRANTED_PRIVILEGE:
					return ((InternalEList)getGrantedPrivilege()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE:
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
			case SQLAccessControlPackage.USER__EANNOTATIONS:
				return getEAnnotations();
			case SQLAccessControlPackage.USER__NAME:
				return getName();
			case SQLAccessControlPackage.USER__DEPENDENCIES:
				return getDependencies();
			case SQLAccessControlPackage.USER__DESCRIPTION:
				return getDescription();
			case SQLAccessControlPackage.USER__LABEL:
				return getLabel();
			case SQLAccessControlPackage.USER__OWNED_SCHEMA:
				return getOwnedSchema();
			case SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION:
				return getReceivedRoleAuthorization();
			case SQLAccessControlPackage.USER__GRANTED_ROLE_AUTHORIZATION:
				return getGrantedRoleAuthorization();
			case SQLAccessControlPackage.USER__GRANTED_PRIVILEGE:
				return getGrantedPrivilege();
			case SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE:
				return getReceivedPrivilege();
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
			case SQLAccessControlPackage.USER__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.USER__NAME:
				setName((String)newValue);
				return;
			case SQLAccessControlPackage.USER__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.USER__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLAccessControlPackage.USER__LABEL:
				setLabel((String)newValue);
				return;
			case SQLAccessControlPackage.USER__OWNED_SCHEMA:
				getOwnedSchema().clear();
				getOwnedSchema().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION:
				getReceivedRoleAuthorization().clear();
				getReceivedRoleAuthorization().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.USER__GRANTED_ROLE_AUTHORIZATION:
				getGrantedRoleAuthorization().clear();
				getGrantedRoleAuthorization().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.USER__GRANTED_PRIVILEGE:
				getGrantedPrivilege().clear();
				getGrantedPrivilege().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE:
				getReceivedPrivilege().clear();
				getReceivedPrivilege().addAll((Collection)newValue);
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
			case SQLAccessControlPackage.USER__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLAccessControlPackage.USER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLAccessControlPackage.USER__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLAccessControlPackage.USER__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLAccessControlPackage.USER__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLAccessControlPackage.USER__OWNED_SCHEMA:
				getOwnedSchema().clear();
				return;
			case SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION:
				getReceivedRoleAuthorization().clear();
				return;
			case SQLAccessControlPackage.USER__GRANTED_ROLE_AUTHORIZATION:
				getGrantedRoleAuthorization().clear();
				return;
			case SQLAccessControlPackage.USER__GRANTED_PRIVILEGE:
				getGrantedPrivilege().clear();
				return;
			case SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE:
				getReceivedPrivilege().clear();
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
			case SQLAccessControlPackage.USER__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLAccessControlPackage.USER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLAccessControlPackage.USER__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLAccessControlPackage.USER__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLAccessControlPackage.USER__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLAccessControlPackage.USER__OWNED_SCHEMA:
				return ownedSchema != null && !ownedSchema.isEmpty();
			case SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION:
				return receivedRoleAuthorization != null && !receivedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.USER__GRANTED_ROLE_AUTHORIZATION:
				return grantedRoleAuthorization != null && !grantedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.USER__GRANTED_PRIVILEGE:
				return grantedPrivilege != null && !grantedPrivilege.isEmpty();
			case SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE:
				return receivedPrivilege != null && !receivedPrivilege.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //UserImpl
