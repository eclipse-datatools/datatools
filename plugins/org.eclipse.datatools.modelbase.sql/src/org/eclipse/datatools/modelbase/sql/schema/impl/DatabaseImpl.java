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
package org.eclipse.datatools.modelbase.sql.schema.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;

import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl#getVendor <em>Vendor</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl#getSchemas <em>Schemas</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl#getCatalogs <em>Catalogs</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl#getAuthorizationIds <em>Authorization Ids</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DatabaseImpl extends SQLObjectImpl implements Database {
	/**
	 * The default value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected String vendor = VENDOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSchemas() <em>Schemas</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchemas()
	 * @generated
	 * @ordered
	 */
	protected EList schemas;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList events;

	/**
	 * The cached value of the '{@link #getCatalogs() <em>Catalogs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCatalogs()
	 * @generated
	 * @ordered
	 */
	protected EList catalogs;

	/**
	 * The cached value of the '{@link #getAuthorizationIds() <em>Authorization Ids</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorizationIds()
	 * @generated
	 * @ordered
	 */
	protected EList authorizationIds;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DatabaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLSchemaPackage.Literals.DATABASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVendor(String newVendor) {
		String oldVendor = vendor;
		vendor = newVendor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.DATABASE__VENDOR, oldVendor, vendor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.DATABASE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSchemas() {
		if (schemas == null) {
			schemas = new EObjectWithInverseResolvingEList(Schema.class, this, SQLSchemaPackage.DATABASE__SCHEMAS, SQLSchemaPackage.SCHEMA__DATABASE);
		}
		return schemas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEvents() {
		if (events == null) {
			events = new EObjectWithInverseResolvingEList(Event.class, this, SQLSchemaPackage.DATABASE__EVENTS, SQLSchemaPackage.EVENT__DATABASE);
		}
		return events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCatalogs() {
		if (catalogs == null) {
			catalogs = new EObjectWithInverseResolvingEList(Catalog.class, this, SQLSchemaPackage.DATABASE__CATALOGS, SQLSchemaPackage.CATALOG__DATABASE);
		}
		return catalogs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAuthorizationIds() {
		if (authorizationIds == null) {
			authorizationIds = new EObjectWithInverseResolvingEList(AuthorizationIdentifier.class, this, SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS, SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE);
		}
		return authorizationIds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getUserDefinedTypes() {
		Vector udtList = new Vector();
		EList schemas = this.getSchemas();
		if (schemas != null) {
			Iterator schemaIterator = schemas.iterator();
			while(schemaIterator.hasNext()) {
				Schema currentSchema = (Schema)schemaIterator.next();
				EList udts = currentSchema.getUserDefinedTypes();
				if (udts != null) {
					Iterator udtIterator = udts.iterator();
					while(udtIterator.hasNext()) {
						UserDefinedType currentUDT = (UserDefinedType)udtIterator.next();
						udtList.add(currentUDT);
					}
				}
			}
		}
		return (List)udtList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLSchemaPackage.DATABASE__SCHEMAS:
				return ((InternalEList)getSchemas()).basicAdd(otherEnd, msgs);
			case SQLSchemaPackage.DATABASE__EVENTS:
				return ((InternalEList)getEvents()).basicAdd(otherEnd, msgs);
			case SQLSchemaPackage.DATABASE__CATALOGS:
				return ((InternalEList)getCatalogs()).basicAdd(otherEnd, msgs);
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				return ((InternalEList)getAuthorizationIds()).basicAdd(otherEnd, msgs);
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
			case SQLSchemaPackage.DATABASE__SCHEMAS:
				return ((InternalEList)getSchemas()).basicRemove(otherEnd, msgs);
			case SQLSchemaPackage.DATABASE__EVENTS:
				return ((InternalEList)getEvents()).basicRemove(otherEnd, msgs);
			case SQLSchemaPackage.DATABASE__CATALOGS:
				return ((InternalEList)getCatalogs()).basicRemove(otherEnd, msgs);
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				return ((InternalEList)getAuthorizationIds()).basicRemove(otherEnd, msgs);
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
			case SQLSchemaPackage.DATABASE__VENDOR:
				return getVendor();
			case SQLSchemaPackage.DATABASE__VERSION:
				return getVersion();
			case SQLSchemaPackage.DATABASE__SCHEMAS:
				return getSchemas();
			case SQLSchemaPackage.DATABASE__EVENTS:
				return getEvents();
			case SQLSchemaPackage.DATABASE__CATALOGS:
				return getCatalogs();
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				return getAuthorizationIds();
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
			case SQLSchemaPackage.DATABASE__VENDOR:
				setVendor((String)newValue);
				return;
			case SQLSchemaPackage.DATABASE__VERSION:
				setVersion((String)newValue);
				return;
			case SQLSchemaPackage.DATABASE__SCHEMAS:
				getSchemas().clear();
				getSchemas().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.DATABASE__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.DATABASE__CATALOGS:
				getCatalogs().clear();
				getCatalogs().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				getAuthorizationIds().clear();
				getAuthorizationIds().addAll((Collection)newValue);
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
			case SQLSchemaPackage.DATABASE__VENDOR:
				setVendor(VENDOR_EDEFAULT);
				return;
			case SQLSchemaPackage.DATABASE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case SQLSchemaPackage.DATABASE__SCHEMAS:
				getSchemas().clear();
				return;
			case SQLSchemaPackage.DATABASE__EVENTS:
				getEvents().clear();
				return;
			case SQLSchemaPackage.DATABASE__CATALOGS:
				getCatalogs().clear();
				return;
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				getAuthorizationIds().clear();
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
			case SQLSchemaPackage.DATABASE__VENDOR:
				return VENDOR_EDEFAULT == null ? vendor != null : !VENDOR_EDEFAULT.equals(vendor);
			case SQLSchemaPackage.DATABASE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case SQLSchemaPackage.DATABASE__SCHEMAS:
				return schemas != null && !schemas.isEmpty();
			case SQLSchemaPackage.DATABASE__EVENTS:
				return events != null && !events.isEmpty();
			case SQLSchemaPackage.DATABASE__CATALOGS:
				return catalogs != null && !catalogs.isEmpty();
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				return authorizationIds != null && !authorizationIds.isEmpty();
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
		result.append(" (vendor: "); //$NON-NLS-1$
		result.append(vendor);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //DatabaseImpl
