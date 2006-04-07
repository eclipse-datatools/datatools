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
package org.eclipse.datatools.modelbase.derby.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.derby.DerbyModelPackage;
import org.eclipse.datatools.modelbase.derby.Synonym;

import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

import org.eclipse.datatools.modelbase.sql.tables.ReferenceType;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;

import org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Synonym</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.derby.impl.SynonymImpl#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SynonymImpl extends TableImpl implements Synonym {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The cached value of the '{@link #getTable() <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTable()
	 * @generated
	 * @ordered
	 */
	protected Table table = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SynonymImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DerbyModelPackage.eINSTANCE.getSynonym();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getTable() {
		if (table != null && table.eIsProxy()) {
			Table oldTable = table;
			table = (Table)eResolveProxy((InternalEObject)table);
			if (table != oldTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DerbyModelPackage.SYNONYM__TABLE, oldTable, table));
			}
		}
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetTable() {
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(Table newTable) {
		Table oldTable = table;
		table = newTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DerbyModelPackage.SYNONYM__TABLE, oldTable, table));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case DerbyModelPackage.SYNONYM__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__COLUMNS:
					return ((InternalEList)getColumns()).basicAdd(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__SUPERTABLE:
					if (supertable != null)
						msgs = ((InternalEObject)supertable).eInverseRemove(this, SQLTablesPackage.TABLE__SUBTABLES, Table.class, msgs);
					return basicSetSupertable((Table)otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__SUBTABLES:
					return ((InternalEList)getSubtables()).basicAdd(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__SCHEMA:
					if (schema != null)
						msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__TABLES, Schema.class, msgs);
					return basicSetSchema((Schema)otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__TRIGGERS:
					return ((InternalEList)getTriggers()).basicAdd(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__INDEX:
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
				case DerbyModelPackage.SYNONYM__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__COLUMNS:
					return ((InternalEList)getColumns()).basicRemove(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__SUPERTABLE:
					return basicSetSupertable(null, msgs);
				case DerbyModelPackage.SYNONYM__SUBTABLES:
					return ((InternalEList)getSubtables()).basicRemove(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__SCHEMA:
					return basicSetSchema(null, msgs);
				case DerbyModelPackage.SYNONYM__TRIGGERS:
					return ((InternalEList)getTriggers()).basicRemove(otherEnd, msgs);
				case DerbyModelPackage.SYNONYM__INDEX:
					return ((InternalEList)getIndex()).basicRemove(otherEnd, msgs);
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
			case DerbyModelPackage.SYNONYM__EANNOTATIONS:
				return getEAnnotations();
			case DerbyModelPackage.SYNONYM__NAME:
				return getName();
			case DerbyModelPackage.SYNONYM__DEPENDENCIES:
				return getDependencies();
			case DerbyModelPackage.SYNONYM__DESCRIPTION:
				return getDescription();
			case DerbyModelPackage.SYNONYM__LABEL:
				return getLabel();
			case DerbyModelPackage.SYNONYM__COLUMNS:
				return getColumns();
			case DerbyModelPackage.SYNONYM__SUPERTABLE:
				if (resolve) return getSupertable();
				return basicGetSupertable();
			case DerbyModelPackage.SYNONYM__SUBTABLES:
				return getSubtables();
			case DerbyModelPackage.SYNONYM__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case DerbyModelPackage.SYNONYM__UDT:
				if (resolve) return getUdt();
				return basicGetUdt();
			case DerbyModelPackage.SYNONYM__TRIGGERS:
				return getTriggers();
			case DerbyModelPackage.SYNONYM__INDEX:
				return getIndex();
			case DerbyModelPackage.SYNONYM__SELF_REF_COLUMN_GENERATION:
				return getSelfRefColumnGeneration();
			case DerbyModelPackage.SYNONYM__INSERTABLE:
				return isInsertable() ? Boolean.TRUE : Boolean.FALSE;
			case DerbyModelPackage.SYNONYM__UPDATABLE:
				return isUpdatable() ? Boolean.TRUE : Boolean.FALSE;
			case DerbyModelPackage.SYNONYM__TABLE:
				if (resolve) return getTable();
				return basicGetTable();
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
			case DerbyModelPackage.SYNONYM__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case DerbyModelPackage.SYNONYM__NAME:
				setName((String)newValue);
				return;
			case DerbyModelPackage.SYNONYM__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case DerbyModelPackage.SYNONYM__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case DerbyModelPackage.SYNONYM__LABEL:
				setLabel((String)newValue);
				return;
			case DerbyModelPackage.SYNONYM__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection)newValue);
				return;
			case DerbyModelPackage.SYNONYM__SUPERTABLE:
				setSupertable((Table)newValue);
				return;
			case DerbyModelPackage.SYNONYM__SUBTABLES:
				getSubtables().clear();
				getSubtables().addAll((Collection)newValue);
				return;
			case DerbyModelPackage.SYNONYM__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case DerbyModelPackage.SYNONYM__UDT:
				setUdt((StructuredUserDefinedType)newValue);
				return;
			case DerbyModelPackage.SYNONYM__TRIGGERS:
				getTriggers().clear();
				getTriggers().addAll((Collection)newValue);
				return;
			case DerbyModelPackage.SYNONYM__INDEX:
				getIndex().clear();
				getIndex().addAll((Collection)newValue);
				return;
			case DerbyModelPackage.SYNONYM__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration((ReferenceType)newValue);
				return;
			case DerbyModelPackage.SYNONYM__TABLE:
				setTable((Table)newValue);
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
			case DerbyModelPackage.SYNONYM__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case DerbyModelPackage.SYNONYM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DerbyModelPackage.SYNONYM__DEPENDENCIES:
				getDependencies().clear();
				return;
			case DerbyModelPackage.SYNONYM__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case DerbyModelPackage.SYNONYM__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case DerbyModelPackage.SYNONYM__COLUMNS:
				getColumns().clear();
				return;
			case DerbyModelPackage.SYNONYM__SUPERTABLE:
				setSupertable((Table)null);
				return;
			case DerbyModelPackage.SYNONYM__SUBTABLES:
				getSubtables().clear();
				return;
			case DerbyModelPackage.SYNONYM__SCHEMA:
				setSchema((Schema)null);
				return;
			case DerbyModelPackage.SYNONYM__UDT:
				setUdt((StructuredUserDefinedType)null);
				return;
			case DerbyModelPackage.SYNONYM__TRIGGERS:
				getTriggers().clear();
				return;
			case DerbyModelPackage.SYNONYM__INDEX:
				getIndex().clear();
				return;
			case DerbyModelPackage.SYNONYM__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration(SELF_REF_COLUMN_GENERATION_EDEFAULT);
				return;
			case DerbyModelPackage.SYNONYM__TABLE:
				setTable((Table)null);
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
			case DerbyModelPackage.SYNONYM__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case DerbyModelPackage.SYNONYM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DerbyModelPackage.SYNONYM__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case DerbyModelPackage.SYNONYM__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case DerbyModelPackage.SYNONYM__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case DerbyModelPackage.SYNONYM__COLUMNS:
				return columns != null && !columns.isEmpty();
			case DerbyModelPackage.SYNONYM__SUPERTABLE:
				return supertable != null;
			case DerbyModelPackage.SYNONYM__SUBTABLES:
				return subtables != null && !subtables.isEmpty();
			case DerbyModelPackage.SYNONYM__SCHEMA:
				return schema != null;
			case DerbyModelPackage.SYNONYM__UDT:
				return udt != null;
			case DerbyModelPackage.SYNONYM__TRIGGERS:
				return triggers != null && !triggers.isEmpty();
			case DerbyModelPackage.SYNONYM__INDEX:
				return index != null && !index.isEmpty();
			case DerbyModelPackage.SYNONYM__SELF_REF_COLUMN_GENERATION:
				return selfRefColumnGeneration != SELF_REF_COLUMN_GENERATION_EDEFAULT;
			case DerbyModelPackage.SYNONYM__INSERTABLE:
				return isInsertable() != INSERTABLE_EDEFAULT;
			case DerbyModelPackage.SYNONYM__UPDATABLE:
				return isUpdatable() != UPDATABLE_EDEFAULT;
			case DerbyModelPackage.SYNONYM__TABLE:
				return table != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //SynonymImpl
