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
import org.eclipse.datatools.modelbase.sql.tables.CheckType;
import org.eclipse.datatools.modelbase.sql.tables.ReferenceType;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.ViewTableImpl#getCheckType <em>Check Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewTableImpl extends DerivedTableImpl implements ViewTable {
	/**
	 * The default value of the '{@link #getCheckType() <em>Check Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckType()
	 * @generated
	 * @ordered
	 */
	protected static final CheckType CHECK_TYPE_EDEFAULT = CheckType.CASCADED_LITERAL;

	/**
	 * The cached value of the '{@link #getCheckType() <em>Check Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckType()
	 * @generated
	 * @ordered
	 */
	protected CheckType checkType = CHECK_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLTablesPackage.eINSTANCE.getViewTable();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckType getCheckType() {
		return checkType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCheckType(CheckType newCheckType) {
		CheckType oldCheckType = checkType;
		checkType = newCheckType == null ? CHECK_TYPE_EDEFAULT : newCheckType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.VIEW_TABLE__CHECK_TYPE, oldCheckType, checkType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLTablesPackage.VIEW_TABLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__COLUMNS:
					return ((InternalEList)getColumns()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__SUPERTABLE:
					if (supertable != null)
						msgs = ((InternalEObject)supertable).eInverseRemove(this, SQLTablesPackage.TABLE__SUBTABLES, Table.class, msgs);
					return basicSetSupertable((Table)otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__SUBTABLES:
					return ((InternalEList)getSubtables()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__SCHEMA:
					if (schema != null)
						msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__TABLES, Schema.class, msgs);
					return basicSetSchema((Schema)otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__TRIGGERS:
					return ((InternalEList)getTriggers()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__INDEX:
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
				case SQLTablesPackage.VIEW_TABLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__COLUMNS:
					return ((InternalEList)getColumns()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__SUPERTABLE:
					return basicSetSupertable(null, msgs);
				case SQLTablesPackage.VIEW_TABLE__SUBTABLES:
					return ((InternalEList)getSubtables()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLTablesPackage.VIEW_TABLE__TRIGGERS:
					return ((InternalEList)getTriggers()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__INDEX:
					return ((InternalEList)getIndex()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION:
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
			case SQLTablesPackage.VIEW_TABLE__EANNOTATIONS:
				return getEAnnotations();
			case SQLTablesPackage.VIEW_TABLE__NAME:
				return getName();
			case SQLTablesPackage.VIEW_TABLE__DEPENDENCIES:
				return getDependencies();
			case SQLTablesPackage.VIEW_TABLE__DESCRIPTION:
				return getDescription();
			case SQLTablesPackage.VIEW_TABLE__LABEL:
				return getLabel();
			case SQLTablesPackage.VIEW_TABLE__COLUMNS:
				return getColumns();
			case SQLTablesPackage.VIEW_TABLE__SUPERTABLE:
				if (resolve) return getSupertable();
				return basicGetSupertable();
			case SQLTablesPackage.VIEW_TABLE__SUBTABLES:
				return getSubtables();
			case SQLTablesPackage.VIEW_TABLE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLTablesPackage.VIEW_TABLE__UDT:
				if (resolve) return getUdt();
				return basicGetUdt();
			case SQLTablesPackage.VIEW_TABLE__TRIGGERS:
				return getTriggers();
			case SQLTablesPackage.VIEW_TABLE__INDEX:
				return getIndex();
			case SQLTablesPackage.VIEW_TABLE__SELF_REF_COLUMN_GENERATION:
				return getSelfRefColumnGeneration();
			case SQLTablesPackage.VIEW_TABLE__INSERTABLE:
				return isInsertable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.VIEW_TABLE__UPDATABLE:
				return isUpdatable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION:
				return getQueryExpression();
			case SQLTablesPackage.VIEW_TABLE__CHECK_TYPE:
				return getCheckType();
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
			case SQLTablesPackage.VIEW_TABLE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__NAME:
				setName((String)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__SUPERTABLE:
				setSupertable((Table)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__SUBTABLES:
				getSubtables().clear();
				getSubtables().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__UDT:
				setUdt((StructuredUserDefinedType)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__TRIGGERS:
				getTriggers().clear();
				getTriggers().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__INDEX:
				getIndex().clear();
				getIndex().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration((ReferenceType)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION:
				setQueryExpression((QueryExpression)newValue);
				return;
			case SQLTablesPackage.VIEW_TABLE__CHECK_TYPE:
				setCheckType((CheckType)newValue);
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
			case SQLTablesPackage.VIEW_TABLE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLTablesPackage.VIEW_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLTablesPackage.VIEW_TABLE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLTablesPackage.VIEW_TABLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLTablesPackage.VIEW_TABLE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLTablesPackage.VIEW_TABLE__COLUMNS:
				getColumns().clear();
				return;
			case SQLTablesPackage.VIEW_TABLE__SUPERTABLE:
				setSupertable((Table)null);
				return;
			case SQLTablesPackage.VIEW_TABLE__SUBTABLES:
				getSubtables().clear();
				return;
			case SQLTablesPackage.VIEW_TABLE__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLTablesPackage.VIEW_TABLE__UDT:
				setUdt((StructuredUserDefinedType)null);
				return;
			case SQLTablesPackage.VIEW_TABLE__TRIGGERS:
				getTriggers().clear();
				return;
			case SQLTablesPackage.VIEW_TABLE__INDEX:
				getIndex().clear();
				return;
			case SQLTablesPackage.VIEW_TABLE__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration(SELF_REF_COLUMN_GENERATION_EDEFAULT);
				return;
			case SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION:
				setQueryExpression((QueryExpression)null);
				return;
			case SQLTablesPackage.VIEW_TABLE__CHECK_TYPE:
				setCheckType(CHECK_TYPE_EDEFAULT);
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
			case SQLTablesPackage.VIEW_TABLE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLTablesPackage.VIEW_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLTablesPackage.VIEW_TABLE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLTablesPackage.VIEW_TABLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLTablesPackage.VIEW_TABLE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLTablesPackage.VIEW_TABLE__COLUMNS:
				return columns != null && !columns.isEmpty();
			case SQLTablesPackage.VIEW_TABLE__SUPERTABLE:
				return supertable != null;
			case SQLTablesPackage.VIEW_TABLE__SUBTABLES:
				return subtables != null && !subtables.isEmpty();
			case SQLTablesPackage.VIEW_TABLE__SCHEMA:
				return schema != null;
			case SQLTablesPackage.VIEW_TABLE__UDT:
				return udt != null;
			case SQLTablesPackage.VIEW_TABLE__TRIGGERS:
				return triggers != null && !triggers.isEmpty();
			case SQLTablesPackage.VIEW_TABLE__INDEX:
				return index != null && !index.isEmpty();
			case SQLTablesPackage.VIEW_TABLE__SELF_REF_COLUMN_GENERATION:
				return selfRefColumnGeneration != SELF_REF_COLUMN_GENERATION_EDEFAULT;
			case SQLTablesPackage.VIEW_TABLE__INSERTABLE:
				return isInsertable() != INSERTABLE_EDEFAULT;
			case SQLTablesPackage.VIEW_TABLE__UPDATABLE:
				return isUpdatable() != UPDATABLE_EDEFAULT;
			case SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION:
				return queryExpression != null;
			case SQLTablesPackage.VIEW_TABLE__CHECK_TYPE:
				return checkType != CHECK_TYPE_EDEFAULT;
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
		result.append(" (checkType: "); //$NON-NLS-1$
		result.append(checkType);
		result.append(')');
		return result.toString();
	}

} //ViewTableImpl
