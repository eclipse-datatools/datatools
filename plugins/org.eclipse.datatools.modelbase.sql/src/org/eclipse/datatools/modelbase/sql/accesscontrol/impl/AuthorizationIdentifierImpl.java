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
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
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
 *   <li>{@link org.eclipse.datatools.modelbase.sql.accesscontrol.impl.AuthorizationIdentifierImpl#getDatabase <em>Database</em>}</li>
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
	protected EList ownedSchema;

	/**
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected Database database;

	/**
	 * The cached value of the '{@link #getReceivedRoleAuthorization() <em>Received Role Authorization</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceivedRoleAuthorization()
	 * @generated
	 * @ordered
	 */
	protected EList receivedRoleAuthorization;

	/**
	 * The cached value of the '{@link #getGrantedRoleAuthorization() <em>Granted Role Authorization</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrantedRoleAuthorization()
	 * @generated
	 * @ordered
	 */
	protected EList grantedRoleAuthorization;

	/**
	 * The cached value of the '{@link #getGrantedPrivilege() <em>Granted Privilege</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrantedPrivilege()
	 * @generated
	 * @ordered
	 */
	protected EList grantedPrivilege;

	/**
	 * The cached value of the '{@link #getReceivedPrivilege() <em>Received Privilege</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceivedPrivilege()
	 * @generated
	 * @ordered
	 */
	protected EList receivedPrivilege;

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
		return SQLAccessControlPackage.Literals.AUTHORIZATION_IDENTIFIER;
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
	public Database getDatabase() {
		if (database != null && database.eIsProxy()) {
			InternalEObject oldDatabase = (InternalEObject)database;
			database = (Database)eResolveProxy(oldDatabase);
			if (database != oldDatabase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE, oldDatabase, database));
			}
		}
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Database basicGetDatabase() {
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDatabase(Database newDatabase, NotificationChain msgs) {
		Database oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE, oldDatabase, newDatabase);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(Database newDatabase) {
		if (newDatabase != database) {
			NotificationChain msgs = null;
			if (database != null)
				msgs = ((InternalEObject)database).eInverseRemove(this, SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS, Database.class, msgs);
			if (newDatabase != null)
				msgs = ((InternalEObject)newDatabase).eInverseAdd(this, SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS, Database.class, msgs);
			msgs = basicSetDatabase(newDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE, newDatabase, newDatabase));
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
			receivedPrivilege = new EObjectContainmentWithInverseEList(Privilege.class, this, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE, SQLAccessControlPackage.PRIVILEGE__GRANTEE);
		}
		return receivedPrivilege;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				return ((InternalEList)getOwnedSchema()).basicAdd(otherEnd, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE:
				if (database != null)
					msgs = ((InternalEObject)database).eInverseRemove(this, SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS, Database.class, msgs);
				return basicSetDatabase((Database)otherEnd, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
				return ((InternalEList)getReceivedRoleAuthorization()).basicAdd(otherEnd, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
				return ((InternalEList)getGrantedRoleAuthorization()).basicAdd(otherEnd, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
				return ((InternalEList)getGrantedPrivilege()).basicAdd(otherEnd, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
				return ((InternalEList)getReceivedPrivilege()).basicAdd(otherEnd, msgs);
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
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				return ((InternalEList)getOwnedSchema()).basicRemove(otherEnd, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE:
				return basicSetDatabase(null, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
				return ((InternalEList)getReceivedRoleAuthorization()).basicRemove(otherEnd, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
				return ((InternalEList)getGrantedRoleAuthorization()).basicRemove(otherEnd, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
				return ((InternalEList)getGrantedPrivilege()).basicRemove(otherEnd, msgs);
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
				return ((InternalEList)getReceivedPrivilege()).basicRemove(otherEnd, msgs);
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
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				return getOwnedSchema();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE:
				if (resolve) return getDatabase();
				return basicGetDatabase();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
				return getReceivedRoleAuthorization();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
				return getGrantedRoleAuthorization();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
				return getGrantedPrivilege();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
				return getReceivedPrivilege();
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
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				getOwnedSchema().clear();
				getOwnedSchema().addAll((Collection)newValue);
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE:
				setDatabase((Database)newValue);
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
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				getOwnedSchema().clear();
				return;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE:
				setDatabase((Database)null);
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
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
				return ownedSchema != null && !ownedSchema.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE:
				return database != null;
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION:
				return receivedRoleAuthorization != null && !receivedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION:
				return grantedRoleAuthorization != null && !grantedRoleAuthorization.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE:
				return grantedPrivilege != null && !grantedPrivilege.isEmpty();
			case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
				return receivedPrivilege != null && !receivedPrivilege.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AuthorizationIdentifierImpl
