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
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Authorization Identifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl#getOwnedSchema <em>Owned Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl#getReceivedRoleAuthorization <em>Received Role Authorization</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl#getGrantedRoleAuthorization <em>Granted Role Authorization</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl#getGrantedPrivilege <em>Granted Privilege</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl#getReceivedPrivilege <em>Received Privilege</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AuthorizationIdentifierImpl extends SQLObjectImpl implements AuthorizationIdentifier {
	/**
	 * The cached value of the '{@link #getOwnedSchema() <em>Owned Schema</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSchema()
	 * @generated
	 * @ordered
	 */
	protected EList ownedSchema = null;

	/**
	 * The cached value of the '{@link #getReceivedRoleAuthorization() <em>Received Role Authorization</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceivedRoleAuthorization()
	 * @generated
	 * @ordered
	 */
	protected EList receivedRoleAuthorization = null;

	/**
	 * The cached value of the '{@link #getGrantedRoleAuthorization() <em>Granted Role Authorization</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrantedRoleAuthorization()
	 * @generated
	 * @ordered
	 */
	protected EList grantedRoleAuthorization = null;

	/**
	 * The cached value of the '{@link #getGrantedPrivilege() <em>Granted Privilege</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrantedPrivilege()
	 * @generated
	 * @ordered
	 */
	protected EList grantedPrivilege = null;

	/**
	 * The cached value of the '{@link #getReceivedPrivilege() <em>Received Privilege</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceivedPrivilege()
	 * @generated
	 * @ordered
	 */
	protected EList receivedPrivilege = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AuthorizationIdentifierImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLAccessControlPackage.eINSTANCE.getAuthorizationIdentifier();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOwnedSchema() {
		if (ownedSchema == null) {
			ownedSchema = new EObjectWithInverseResolvingEList(Schema.class, this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA, SQLSchemaPackage.SCHEMA__OWNER);
		}
		return ownedSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReceivedRoleAuthorization() {
		if (receivedRoleAuthorization == null) {
			receivedRoleAuthorization = new EObjectWithInverseResolvingEList(RoleAuthorization.class, this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION, SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTEE);
		}
		return receivedRoleAuthorization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getGrantedRoleAuthorization() {
		if (grantedRoleAuthorization == null) {
			grantedRoleAuthorization = new EObjectWithInverseResolvingEList(RoleAuthorization.class, this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION, SQLAccessControlPackage.ROLE_AUTHORIZATION__GRANTOR);
		}
		return grantedRoleAuthorization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getGrantedPrivilege() {
		if (grantedPrivilege == null) {
			grantedPrivilege = new EObjectWithInverseResolvingEList(Privilege.class, this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE, SQLAccessControlPackage.PRIVILEGE__GRANTOR);
		}
		return grantedPrivilege;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReceivedPrivilege() {
		if (receivedPrivilege == null) {
			receivedPrivilege = new EObjectContainmentEList(Privilege.class, this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE);
		}
		return receivedPrivilege;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
					return ((InternalEList)getOwnedSchema()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
					return ((InternalEList)getReceivedRoleAuthorization()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
					return ((InternalEList)getGrantedRoleAuthorization()).basicAdd(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
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
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
					return ((InternalEList)getOwnedSchema()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
					return ((InternalEList)getReceivedRoleAuthorization()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
					return ((InternalEList)getGrantedRoleAuthorization()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
					return ((InternalEList)getGrantedPrivilege()).basicRemove(otherEnd, msgs);
				case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
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
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__EANNOTATIONS:
				return getEAnnotations();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__NAME:
				return getName();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DEPENDENCIES:
				return getDependencies();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DESCRIPTION:
				return getDescription();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__LABEL:
				return getLabel();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				return getOwnedSchema();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
				return getReceivedRoleAuthorization();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
				return getGrantedRoleAuthorization();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
				return getGrantedPrivilege();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
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
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__NAME:
				setName((String)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__LABEL:
				setLabel((String)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				getOwnedSchema().clear();
				getOwnedSchema().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
				getReceivedRoleAuthorization().clear();
				getReceivedRoleAuthorization().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
				getGrantedRoleAuthorization().clear();
				getGrantedRoleAuthorization().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
				getGrantedPrivilege().clear();
				getGrantedPrivilege().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
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
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				getOwnedSchema().clear();
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
				getReceivedRoleAuthorization().clear();
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
				getGrantedRoleAuthorization().clear();
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
				getGrantedPrivilege().clear();
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
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
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				return ownedSchema != null && !ownedSchema.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
				return receivedRoleAuthorization != null && !receivedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
				return grantedRoleAuthorization != null && !grantedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
				return grantedPrivilege != null && !grantedPrivilege.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
				return receivedPrivilege != null && !receivedPrivilege.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //AuthorizationIdentifierImpl
