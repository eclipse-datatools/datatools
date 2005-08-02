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
package org.eclipse.datatools.modelbase.sql.tables.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.DerivedTable;
import org.eclipse.datatools.modelbase.sql.tables.ReferenceType;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Derived Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.DerivedTableImpl#getQueryExpression <em>Query Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DerivedTableImpl extends TableImpl implements DerivedTable {
	/**
	 * The cached value of the '{@link #getQueryExpression() <em>Query Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryExpression()
	 * @generated
	 * @ordered
	 */
	protected QueryExpression queryExpression = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DerivedTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLTablesPackage.eINSTANCE.getDerivedTable();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryExpression getQueryExpression() {
		return queryExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQueryExpression(QueryExpression newQueryExpression, NotificationChain msgs) {
		QueryExpression oldQueryExpression = queryExpression;
		queryExpression = newQueryExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION, oldQueryExpression, newQueryExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQueryExpression(QueryExpression newQueryExpression) {
		if (newQueryExpression != queryExpression) {
			NotificationChain msgs = null;
			if (queryExpression != null)
				msgs = ((InternalEObject)queryExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION, null, msgs);
			if (newQueryExpression != null)
				msgs = ((InternalEObject)newQueryExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION, null, msgs);
			msgs = basicSetQueryExpression(newQueryExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION, newQueryExpression, newQueryExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLTablesPackage.DERIVED_TABLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__COLUMNS:
					return ((InternalEList)getColumns()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__SUPERTABLE:
					if (supertable != null)
						msgs = ((InternalEObject)supertable).eInverseRemove(this, SQLTablesPackage.TABLE__SUBTABLES, Table.class, msgs);
					return basicSetSupertable((Table)otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__SUBTABLES:
					return ((InternalEList)getSubtables()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__SCHEMA:
					if (schema != null)
						msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__TABLES, Schema.class, msgs);
					return basicSetSchema((Schema)otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__TRIGGERS:
					return ((InternalEList)getTriggers()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__INDEX:
					return ((InternalEList)getIndex()).basicAdd(otherEnd, msgs);
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
				case SQLTablesPackage.DERIVED_TABLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__COLUMNS:
					return ((InternalEList)getColumns()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__SUPERTABLE:
					return basicSetSupertable(null, msgs);
				case SQLTablesPackage.DERIVED_TABLE__SUBTABLES:
					return ((InternalEList)getSubtables()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLTablesPackage.DERIVED_TABLE__TRIGGERS:
					return ((InternalEList)getTriggers()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__INDEX:
					return ((InternalEList)getIndex()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION:
					return basicSetQueryExpression(null, msgs);
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
			case SQLTablesPackage.DERIVED_TABLE__EANNOTATIONS:
				return getEAnnotations();
			case SQLTablesPackage.DERIVED_TABLE__NAME:
				return getName();
			case SQLTablesPackage.DERIVED_TABLE__DEPENDENCIES:
				return getDependencies();
			case SQLTablesPackage.DERIVED_TABLE__DESCRIPTION:
				return getDescription();
			case SQLTablesPackage.DERIVED_TABLE__LABEL:
				return getLabel();
			case SQLTablesPackage.DERIVED_TABLE__COLUMNS:
				return getColumns();
			case SQLTablesPackage.DERIVED_TABLE__SUPERTABLE:
				if (resolve) return getSupertable();
				return basicGetSupertable();
			case SQLTablesPackage.DERIVED_TABLE__SUBTABLES:
				return getSubtables();
			case SQLTablesPackage.DERIVED_TABLE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLTablesPackage.DERIVED_TABLE__UDT:
				if (resolve) return getUdt();
				return basicGetUdt();
			case SQLTablesPackage.DERIVED_TABLE__TRIGGERS:
				return getTriggers();
			case SQLTablesPackage.DERIVED_TABLE__INDEX:
				return getIndex();
			case SQLTablesPackage.DERIVED_TABLE__SELF_REF_COLUMN_GENERATION:
				return getSelfRefColumnGeneration();
			case SQLTablesPackage.DERIVED_TABLE__INSERTABLE:
				return isInsertable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.DERIVED_TABLE__UPDATABLE:
				return isUpdatable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION:
				return getQueryExpression();
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
			case SQLTablesPackage.DERIVED_TABLE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__NAME:
				setName((String)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__SUPERTABLE:
				setSupertable((Table)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__SUBTABLES:
				getSubtables().clear();
				getSubtables().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__UDT:
				setUdt((StructuredUserDefinedType)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__TRIGGERS:
				getTriggers().clear();
				getTriggers().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__INDEX:
				getIndex().clear();
				getIndex().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration((ReferenceType)newValue);
				return;
			case SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION:
				setQueryExpression((QueryExpression)newValue);
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
			case SQLTablesPackage.DERIVED_TABLE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLTablesPackage.DERIVED_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLTablesPackage.DERIVED_TABLE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLTablesPackage.DERIVED_TABLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLTablesPackage.DERIVED_TABLE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLTablesPackage.DERIVED_TABLE__COLUMNS:
				getColumns().clear();
				return;
			case SQLTablesPackage.DERIVED_TABLE__SUPERTABLE:
				setSupertable((Table)null);
				return;
			case SQLTablesPackage.DERIVED_TABLE__SUBTABLES:
				getSubtables().clear();
				return;
			case SQLTablesPackage.DERIVED_TABLE__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLTablesPackage.DERIVED_TABLE__UDT:
				setUdt((StructuredUserDefinedType)null);
				return;
			case SQLTablesPackage.DERIVED_TABLE__TRIGGERS:
				getTriggers().clear();
				return;
			case SQLTablesPackage.DERIVED_TABLE__INDEX:
				getIndex().clear();
				return;
			case SQLTablesPackage.DERIVED_TABLE__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration(SELF_REF_COLUMN_GENERATION_EDEFAULT);
				return;
			case SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION:
				setQueryExpression((QueryExpression)null);
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
			case SQLTablesPackage.DERIVED_TABLE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLTablesPackage.DERIVED_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLTablesPackage.DERIVED_TABLE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLTablesPackage.DERIVED_TABLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLTablesPackage.DERIVED_TABLE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLTablesPackage.DERIVED_TABLE__COLUMNS:
				return columns != null && !columns.isEmpty();
			case SQLTablesPackage.DERIVED_TABLE__SUPERTABLE:
				return supertable != null;
			case SQLTablesPackage.DERIVED_TABLE__SUBTABLES:
				return subtables != null && !subtables.isEmpty();
			case SQLTablesPackage.DERIVED_TABLE__SCHEMA:
				return schema != null;
			case SQLTablesPackage.DERIVED_TABLE__UDT:
				return udt != null;
			case SQLTablesPackage.DERIVED_TABLE__TRIGGERS:
				return triggers != null && !triggers.isEmpty();
			case SQLTablesPackage.DERIVED_TABLE__INDEX:
				return index != null && !index.isEmpty();
			case SQLTablesPackage.DERIVED_TABLE__SELF_REF_COLUMN_GENERATION:
				return selfRefColumnGeneration != SELF_REF_COLUMN_GENERATION_EDEFAULT;
			case SQLTablesPackage.DERIVED_TABLE__INSERTABLE:
				return isInsertable() != INSERTABLE_EDEFAULT;
			case SQLTablesPackage.DERIVED_TABLE__UPDATABLE:
				return isUpdatable() != UPDATABLE_EDEFAULT;
			case SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION:
				return queryExpression != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //DerivedTableImpl
