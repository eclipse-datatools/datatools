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

import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.RoleImpl#getRoleAuthorization <em>Role Authorization</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleImpl extends AuthorizationIdentifierImpl implements Role {
	/**
	 * The cached value of the '{@link #getRoleAuthorization() <em>Role Authorization</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleAuthorization()
	 * @generated
	 * @ordered
	 */
	protected EList roleAuthorization = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLAccessControlPackage.eINSTANCE.getRole();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRoleAuthorization() {
		if (roleAuthorization == null) {
			roleAuthorization = new EObjectWithInverseResolvingEList(RoleAuthorization.class, this, SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION, SQLAccessControlPackage.ROLE_AUTHORIZATION__ROLE);
		}
		return roleAuthorization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLAccessControlPackage.ROLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__OWNED_SCHEMA:
					return ((InternalEList)getOwnedSchema()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__RECEIVED_ROLE_AUTHORIZATION:
					return ((InternalEList)getReceivedRoleAuthorization()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__GRANTED_ROLE_AUTHORIZATION:
					return ((InternalEList)getGrantedRoleAuthorization()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__GRANTED_PRIVILEGE:
					return ((InternalEList)getGrantedPrivilege()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION:
					return ((InternalEList)getRoleAuthorization()).basicAdd(otherEnd, msgs);
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
				case SQLAccessControlPackage.ROLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__OWNED_SCHEMA:
					return ((InternalEList)getOwnedSchema()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__RECEIVED_ROLE_AUTHORIZATION:
					return ((InternalEList)getReceivedRoleAuthorization()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__GRANTED_ROLE_AUTHORIZATION:
					return ((InternalEList)getGrantedRoleAuthorization()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__GRANTED_PRIVILEGE:
					return ((InternalEList)getGrantedPrivilege()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__RECEIVED_PRIVILEGE:
					return ((InternalEList)getReceivedPrivilege()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION:
					return ((InternalEList)getRoleAuthorization()).basicRemove(otherEnd, msgs);
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
			case SQLAccessControlPackage.ROLE__EANNOTATIONS:
				return getEAnnotations();
			case SQLAccessControlPackage.ROLE__NAME:
				return getName();
			case SQLAccessControlPackage.ROLE__DEPENDENCIES:
				return getDependencies();
			case SQLAccessControlPackage.ROLE__DESCRIPTION:
				return getDescription();
			case SQLAccessControlPackage.ROLE__LABEL:
				return getLabel();
			case SQLAccessControlPackage.ROLE__OWNED_SCHEMA:
				return getOwnedSchema();
			case SQLAccessControlPackage.ROLE__RECEIVED_ROLE_AUTHORIZATION:
				return getReceivedRoleAuthorization();
			case SQLAccessControlPackage.ROLE__GRANTED_ROLE_AUTHORIZATION:
				return getGrantedRoleAuthorization();
			case SQLAccessControlPackage.ROLE__GRANTED_PRIVILEGE:
				return getGrantedPrivilege();
			case SQLAccessControlPackage.ROLE__RECEIVED_PRIVILEGE:
				return getReceivedPrivilege();
			case SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION:
				return getRoleAuthorization();
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
			case SQLAccessControlPackage.ROLE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.ROLE__NAME:
				setName((String)newValue);
				return;
			case SQLAccessControlPackage.ROLE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.ROLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLAccessControlPackage.ROLE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLAccessControlPackage.ROLE__OWNED_SCHEMA:
				getOwnedSchema().clear();
				getOwnedSchema().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.ROLE__RECEIVED_ROLE_AUTHORIZATION:
				getReceivedRoleAuthorization().clear();
				getReceivedRoleAuthorization().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.ROLE__GRANTED_ROLE_AUTHORIZATION:
				getGrantedRoleAuthorization().clear();
				getGrantedRoleAuthorization().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.ROLE__GRANTED_PRIVILEGE:
				getGrantedPrivilege().clear();
				getGrantedPrivilege().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.ROLE__RECEIVED_PRIVILEGE:
				getReceivedPrivilege().clear();
				getReceivedPrivilege().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION:
				getRoleAuthorization().clear();
				getRoleAuthorization().addAll((Collection)newValue);
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
			case SQLAccessControlPackage.ROLE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLAccessControlPackage.ROLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLAccessControlPackage.ROLE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLAccessControlPackage.ROLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLAccessControlPackage.ROLE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLAccessControlPackage.ROLE__OWNED_SCHEMA:
				getOwnedSchema().clear();
				return;
			case SQLAccessControlPackage.ROLE__RECEIVED_ROLE_AUTHORIZATION:
				getReceivedRoleAuthorization().clear();
				return;
			case SQLAccessControlPackage.ROLE__GRANTED_ROLE_AUTHORIZATION:
				getGrantedRoleAuthorization().clear();
				return;
			case SQLAccessControlPackage.ROLE__GRANTED_PRIVILEGE:
				getGrantedPrivilege().clear();
				return;
			case SQLAccessControlPackage.ROLE__RECEIVED_PRIVILEGE:
				getReceivedPrivilege().clear();
				return;
			case SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION:
				getRoleAuthorization().clear();
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
			case SQLAccessControlPackage.ROLE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLAccessControlPackage.ROLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLAccessControlPackage.ROLE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLAccessControlPackage.ROLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLAccessControlPackage.ROLE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLAccessControlPackage.ROLE__OWNED_SCHEMA:
				return ownedSchema != null && !ownedSchema.isEmpty();
			case SQLAccessControlPackage.ROLE__RECEIVED_ROLE_AUTHORIZATION:
				return receivedRoleAuthorization != null && !receivedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.ROLE__GRANTED_ROLE_AUTHORIZATION:
				return grantedRoleAuthorization != null && !grantedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.ROLE__GRANTED_PRIVILEGE:
				return grantedPrivilege != null && !grantedPrivilege.isEmpty();
			case SQLAccessControlPackage.ROLE__RECEIVED_PRIVILEGE:
				return receivedPrivilege != null && !receivedPrivilege.isEmpty();
			case SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION:
				return roleAuthorization != null && !roleAuthorization.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //RoleImpl
