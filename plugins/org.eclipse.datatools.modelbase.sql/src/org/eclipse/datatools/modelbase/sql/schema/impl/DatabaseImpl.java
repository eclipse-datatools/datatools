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
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
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
	protected EList schemas = null;

	/**
	 * The cached value of the '{@link #getAuthorizationIds() <em>Authorization Ids</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorizationIds()
	 * @generated
	 * @ordered
	 */
	protected EList authorizationIds = null;

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
		return SQLSchemaPackage.eINSTANCE.getDatabase();
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
	public EList getAuthorizationIds() {
		if (authorizationIds == null) {
			authorizationIds = new EObjectResolvingEList(AuthorizationIdentifier.class, this, SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLSchemaPackage.DATABASE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.DATABASE__SCHEMAS:
					return ((InternalEList)getSchemas()).basicAdd(otherEnd, msgs);
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
				case SQLSchemaPackage.DATABASE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.DATABASE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.DATABASE__SCHEMAS:
					return ((InternalEList)getSchemas()).basicRemove(otherEnd, msgs);
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
			case SQLSchemaPackage.DATABASE__EANNOTATIONS:
				return getEAnnotations();
			case SQLSchemaPackage.DATABASE__NAME:
				return getName();
			case SQLSchemaPackage.DATABASE__DEPENDENCIES:
				return getDependencies();
			case SQLSchemaPackage.DATABASE__DESCRIPTION:
				return getDescription();
			case SQLSchemaPackage.DATABASE__LABEL:
				return getLabel();
			case SQLSchemaPackage.DATABASE__VENDOR:
				return getVendor();
			case SQLSchemaPackage.DATABASE__VERSION:
				return getVersion();
			case SQLSchemaPackage.DATABASE__SCHEMAS:
				return getSchemas();
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				return getAuthorizationIds();
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
			case SQLSchemaPackage.DATABASE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.DATABASE__NAME:
				setName((String)newValue);
				return;
			case SQLSchemaPackage.DATABASE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.DATABASE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLSchemaPackage.DATABASE__LABEL:
				setLabel((String)newValue);
				return;
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
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				getAuthorizationIds().clear();
				getAuthorizationIds().addAll((Collection)newValue);
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
			case SQLSchemaPackage.DATABASE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLSchemaPackage.DATABASE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLSchemaPackage.DATABASE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLSchemaPackage.DATABASE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLSchemaPackage.DATABASE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLSchemaPackage.DATABASE__VENDOR:
				setVendor(VENDOR_EDEFAULT);
				return;
			case SQLSchemaPackage.DATABASE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case SQLSchemaPackage.DATABASE__SCHEMAS:
				getSchemas().clear();
				return;
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				getAuthorizationIds().clear();
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
			case SQLSchemaPackage.DATABASE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLSchemaPackage.DATABASE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLSchemaPackage.DATABASE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLSchemaPackage.DATABASE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLSchemaPackage.DATABASE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLSchemaPackage.DATABASE__VENDOR:
				return VENDOR_EDEFAULT == null ? vendor != null : !VENDOR_EDEFAULT.equals(vendor);
			case SQLSchemaPackage.DATABASE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case SQLSchemaPackage.DATABASE__SCHEMAS:
				return schemas != null && !schemas.isEmpty();
			case SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS:
				return authorizationIds != null && !authorizationIds.isEmpty();
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
		result.append(" (vendor: "); //$NON-NLS-1$
		result.append(vendor);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //DatabaseImpl
