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
package org.eclipse.datatools.modelbase.sql.datatypes.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
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
 * An implementation of the model object '<em><b>Character Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterSetImpl#getRepertoire <em>Repertoire</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterSetImpl#getDefaultCollation <em>Default Collation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterSetImpl#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.impl.CharacterSetImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CharacterSetImpl extends SQLObjectImpl implements CharacterSet {
	/**
	 * The default value of the '{@link #getRepertoire() <em>Repertoire</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepertoire()
	 * @generated
	 * @ordered
	 */
	protected static final String REPERTOIRE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRepertoire() <em>Repertoire</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepertoire()
	 * @generated
	 * @ordered
	 */
	protected String repertoire = REPERTOIRE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultCollation() <em>Default Collation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultCollation()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_COLLATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultCollation() <em>Default Collation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultCollation()
	 * @generated
	 * @ordered
	 */
	protected String defaultCollation = DEFAULT_COLLATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getEncoding() <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncoding()
	 * @generated
	 * @ordered
	 */
	protected static final String ENCODING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEncoding() <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncoding()
	 * @generated
	 * @ordered
	 */
	protected String encoding = ENCODING_EDEFAULT;

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
	protected CharacterSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLDataTypesPackage.eINSTANCE.getCharacterSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRepertoire() {
		return repertoire;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepertoire(String newRepertoire) {
		String oldRepertoire = repertoire;
		repertoire = newRepertoire;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_SET__REPERTOIRE, oldRepertoire, repertoire));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultCollation() {
		return defaultCollation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultCollation(String newDefaultCollation) {
		String oldDefaultCollation = defaultCollation;
		defaultCollation = newDefaultCollation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_SET__DEFAULT_COLLATION, oldDefaultCollation, defaultCollation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncoding(String newEncoding) {
		String oldEncoding = encoding;
		encoding = newEncoding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_SET__ENCODING, oldEncoding, encoding));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLDataTypesPackage.CHARACTER_SET__SCHEMA, oldSchema, schema));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_SET__SCHEMA, oldSchema, newSchema);
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
				msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__CHAR_SETS, Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, SQLSchemaPackage.SCHEMA__CHAR_SETS, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLDataTypesPackage.CHARACTER_SET__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLDataTypesPackage.CHARACTER_SET__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLDataTypesPackage.CHARACTER_SET__SCHEMA:
					if (schema != null)
						msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__CHAR_SETS, Schema.class, msgs);
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
				case SQLDataTypesPackage.CHARACTER_SET__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.CHARACTER_SET__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLDataTypesPackage.CHARACTER_SET__SCHEMA:
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
			case SQLDataTypesPackage.CHARACTER_SET__EANNOTATIONS:
				return getEAnnotations();
			case SQLDataTypesPackage.CHARACTER_SET__NAME:
				return getName();
			case SQLDataTypesPackage.CHARACTER_SET__DEPENDENCIES:
				return getDependencies();
			case SQLDataTypesPackage.CHARACTER_SET__DESCRIPTION:
				return getDescription();
			case SQLDataTypesPackage.CHARACTER_SET__LABEL:
				return getLabel();
			case SQLDataTypesPackage.CHARACTER_SET__REPERTOIRE:
				return getRepertoire();
			case SQLDataTypesPackage.CHARACTER_SET__DEFAULT_COLLATION:
				return getDefaultCollation();
			case SQLDataTypesPackage.CHARACTER_SET__ENCODING:
				return getEncoding();
			case SQLDataTypesPackage.CHARACTER_SET__SCHEMA:
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
			case SQLDataTypesPackage.CHARACTER_SET__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__NAME:
				setName((String)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__LABEL:
				setLabel((String)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__REPERTOIRE:
				setRepertoire((String)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__DEFAULT_COLLATION:
				setDefaultCollation((String)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__ENCODING:
				setEncoding((String)newValue);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__SCHEMA:
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
			case SQLDataTypesPackage.CHARACTER_SET__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLDataTypesPackage.CHARACTER_SET__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLDataTypesPackage.CHARACTER_SET__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__REPERTOIRE:
				setRepertoire(REPERTOIRE_EDEFAULT);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__DEFAULT_COLLATION:
				setDefaultCollation(DEFAULT_COLLATION_EDEFAULT);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__ENCODING:
				setEncoding(ENCODING_EDEFAULT);
				return;
			case SQLDataTypesPackage.CHARACTER_SET__SCHEMA:
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
			case SQLDataTypesPackage.CHARACTER_SET__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLDataTypesPackage.CHARACTER_SET__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLDataTypesPackage.CHARACTER_SET__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLDataTypesPackage.CHARACTER_SET__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLDataTypesPackage.CHARACTER_SET__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLDataTypesPackage.CHARACTER_SET__REPERTOIRE:
				return REPERTOIRE_EDEFAULT == null ? repertoire != null : !REPERTOIRE_EDEFAULT.equals(repertoire);
			case SQLDataTypesPackage.CHARACTER_SET__DEFAULT_COLLATION:
				return DEFAULT_COLLATION_EDEFAULT == null ? defaultCollation != null : !DEFAULT_COLLATION_EDEFAULT.equals(defaultCollation);
			case SQLDataTypesPackage.CHARACTER_SET__ENCODING:
				return ENCODING_EDEFAULT == null ? encoding != null : !ENCODING_EDEFAULT.equals(encoding);
			case SQLDataTypesPackage.CHARACTER_SET__SCHEMA:
				return schema != null;
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
		result.append(" (repertoire: "); //$NON-NLS-1$
		result.append(repertoire);
		result.append(", defaultCollation: "); //$NON-NLS-1$
		result.append(defaultCollation);
		result.append(", encoding: "); //$NON-NLS-1$
		result.append(encoding);
		result.append(')');
		return result.toString();
	}

} //CharacterSetImpl
