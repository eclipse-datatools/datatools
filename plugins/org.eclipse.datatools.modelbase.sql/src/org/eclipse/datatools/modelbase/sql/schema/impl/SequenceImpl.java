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

import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SequenceImpl#getIdentity <em>Identity</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.impl.SequenceImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SequenceImpl extends TypedElementImpl implements Sequence {
	/**
	 * The cached value of the '{@link #getIdentity() <em>Identity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentity()
	 * @generated
	 * @ordered
	 */
	protected IdentitySpecifier identity = null;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected Schema schema = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLSchemaPackage.eINSTANCE.getSequence();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentitySpecifier getIdentity() {
		return identity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIdentity(IdentitySpecifier newIdentity, NotificationChain msgs) {
		IdentitySpecifier oldIdentity = identity;
		identity = newIdentity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SEQUENCE__IDENTITY, oldIdentity, newIdentity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentity(IdentitySpecifier newIdentity) {
		if (newIdentity != identity) {
			NotificationChain msgs = null;
			if (identity != null)
				msgs = ((InternalEObject)identity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLSchemaPackage.SEQUENCE__IDENTITY, null, msgs);
			if (newIdentity != null)
				msgs = ((InternalEObject)newIdentity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLSchemaPackage.SEQUENCE__IDENTITY, null, msgs);
			msgs = basicSetIdentity(newIdentity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SEQUENCE__IDENTITY, newIdentity, newIdentity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			Schema oldSchema = schema;
			schema = (Schema)eResolveProxy((InternalEObject)schema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLSchemaPackage.SEQUENCE__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(Schema newSchema, NotificationChain msgs) {
		Schema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SEQUENCE__SCHEMA, oldSchema, newSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(Schema newSchema) {
		if (newSchema != schema) {
			NotificationChain msgs = null;
			if (schema != null)
				msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__SEQUENCES, Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, SQLSchemaPackage.SCHEMA__SEQUENCES, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLSchemaPackage.SEQUENCE__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLSchemaPackage.SEQUENCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLSchemaPackage.SEQUENCE__SCHEMA:
					if (schema != null)
						msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__SEQUENCES, Schema.class, msgs);
					return basicSetSchema((Schema)otherEnd, msgs);
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
				case SQLSchemaPackage.SEQUENCE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SEQUENCE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLSchemaPackage.SEQUENCE__CONTAINED_TYPE:
					return basicSetContainedType(null, msgs);
				case SQLSchemaPackage.SEQUENCE__IDENTITY:
					return basicSetIdentity(null, msgs);
				case SQLSchemaPackage.SEQUENCE__SCHEMA:
					return basicSetSchema(null, msgs);
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
			case SQLSchemaPackage.SEQUENCE__EANNOTATIONS:
				return getEAnnotations();
			case SQLSchemaPackage.SEQUENCE__NAME:
				return getName();
			case SQLSchemaPackage.SEQUENCE__DEPENDENCIES:
				return getDependencies();
			case SQLSchemaPackage.SEQUENCE__DESCRIPTION:
				return getDescription();
			case SQLSchemaPackage.SEQUENCE__LABEL:
				return getLabel();
			case SQLSchemaPackage.SEQUENCE__CONTAINED_TYPE:
				return getContainedType();
			case SQLSchemaPackage.SEQUENCE__REFERENCED_TYPE:
				if (resolve) return getReferencedType();
				return basicGetReferencedType();
			case SQLSchemaPackage.SEQUENCE__IDENTITY:
				return getIdentity();
			case SQLSchemaPackage.SEQUENCE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
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
			case SQLSchemaPackage.SEQUENCE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SEQUENCE__NAME:
				setName((String)newValue);
				return;
			case SQLSchemaPackage.SEQUENCE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLSchemaPackage.SEQUENCE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLSchemaPackage.SEQUENCE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLSchemaPackage.SEQUENCE__CONTAINED_TYPE:
				setContainedType((SQLDataType)newValue);
				return;
			case SQLSchemaPackage.SEQUENCE__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)newValue);
				return;
			case SQLSchemaPackage.SEQUENCE__IDENTITY:
				setIdentity((IdentitySpecifier)newValue);
				return;
			case SQLSchemaPackage.SEQUENCE__SCHEMA:
				setSchema((Schema)newValue);
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
			case SQLSchemaPackage.SEQUENCE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLSchemaPackage.SEQUENCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLSchemaPackage.SEQUENCE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLSchemaPackage.SEQUENCE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLSchemaPackage.SEQUENCE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLSchemaPackage.SEQUENCE__CONTAINED_TYPE:
				setContainedType((SQLDataType)null);
				return;
			case SQLSchemaPackage.SEQUENCE__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)null);
				return;
			case SQLSchemaPackage.SEQUENCE__IDENTITY:
				setIdentity((IdentitySpecifier)null);
				return;
			case SQLSchemaPackage.SEQUENCE__SCHEMA:
				setSchema((Schema)null);
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
			case SQLSchemaPackage.SEQUENCE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLSchemaPackage.SEQUENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLSchemaPackage.SEQUENCE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLSchemaPackage.SEQUENCE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLSchemaPackage.SEQUENCE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLSchemaPackage.SEQUENCE__CONTAINED_TYPE:
				return containedType != null;
			case SQLSchemaPackage.SEQUENCE__REFERENCED_TYPE:
				return referencedType != null;
			case SQLSchemaPackage.SEQUENCE__IDENTITY:
				return identity != null;
			case SQLSchemaPackage.SEQUENCE__SCHEMA:
				return schema != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SequenceImpl
