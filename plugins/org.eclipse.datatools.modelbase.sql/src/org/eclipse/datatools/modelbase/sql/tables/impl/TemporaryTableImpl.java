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
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.ReferenceType;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Temporary Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TemporaryTableImpl#isLocal <em>Local</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TemporaryTableImpl#isDeleteOnCommit <em>Delete On Commit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TemporaryTableImpl extends BaseTableImpl implements TemporaryTable {
	/**
	 * The default value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOCAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
	protected boolean local = LOCAL_EDEFAULT;

	/**
	 * The default value of the '{@link #isDeleteOnCommit() <em>Delete On Commit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeleteOnCommit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DELETE_ON_COMMIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeleteOnCommit() <em>Delete On Commit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeleteOnCommit()
	 * @generated
	 * @ordered
	 */
	protected boolean deleteOnCommit = DELETE_ON_COMMIT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemporaryTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLTablesPackage.eINSTANCE.getTemporaryTable();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLocal() {
		return local;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocal(boolean newLocal) {
		boolean oldLocal = local;
		local = newLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TEMPORARY_TABLE__LOCAL, oldLocal, local));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeleteOnCommit() {
		return deleteOnCommit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeleteOnCommit(boolean newDeleteOnCommit) {
		boolean oldDeleteOnCommit = deleteOnCommit;
		deleteOnCommit = newDeleteOnCommit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TEMPORARY_TABLE__DELETE_ON_COMMIT, oldDeleteOnCommit, deleteOnCommit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLTablesPackage.TEMPORARY_TABLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__COLUMNS:
					return ((InternalEList)getColumns()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__SUPERTABLE:
					if (supertable != null)
						msgs = ((InternalEObject)supertable).eInverseRemove(this, SQLTablesPackage.TABLE__SUBTABLES, Table.class, msgs);
					return basicSetSupertable((Table)otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__SUBTABLES:
					return ((InternalEList)getSubtables()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__SCHEMA:
					if (schema != null)
						msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__TABLES, Schema.class, msgs);
					return basicSetSchema((Schema)otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__TRIGGERS:
					return ((InternalEList)getTriggers()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__INDEX:
					return ((InternalEList)getIndex()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__CONSTRAINTS:
					return ((InternalEList)getConstraints()).basicAdd(otherEnd, msgs);
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
				case SQLTablesPackage.TEMPORARY_TABLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__COLUMNS:
					return ((InternalEList)getColumns()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__SUPERTABLE:
					return basicSetSupertable(null, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__SUBTABLES:
					return ((InternalEList)getSubtables()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__TRIGGERS:
					return ((InternalEList)getTriggers()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__INDEX:
					return ((InternalEList)getIndex()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TEMPORARY_TABLE__CONSTRAINTS:
					return ((InternalEList)getConstraints()).basicRemove(otherEnd, msgs);
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
			case SQLTablesPackage.TEMPORARY_TABLE__EANNOTATIONS:
				return getEAnnotations();
			case SQLTablesPackage.TEMPORARY_TABLE__NAME:
				return getName();
			case SQLTablesPackage.TEMPORARY_TABLE__DEPENDENCIES:
				return getDependencies();
			case SQLTablesPackage.TEMPORARY_TABLE__DESCRIPTION:
				return getDescription();
			case SQLTablesPackage.TEMPORARY_TABLE__LABEL:
				return getLabel();
			case SQLTablesPackage.TEMPORARY_TABLE__COLUMNS:
				return getColumns();
			case SQLTablesPackage.TEMPORARY_TABLE__SUPERTABLE:
				if (resolve) return getSupertable();
				return basicGetSupertable();
			case SQLTablesPackage.TEMPORARY_TABLE__SUBTABLES:
				return getSubtables();
			case SQLTablesPackage.TEMPORARY_TABLE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLTablesPackage.TEMPORARY_TABLE__UDT:
				if (resolve) return getUdt();
				return basicGetUdt();
			case SQLTablesPackage.TEMPORARY_TABLE__TRIGGERS:
				return getTriggers();
			case SQLTablesPackage.TEMPORARY_TABLE__INDEX:
				return getIndex();
			case SQLTablesPackage.TEMPORARY_TABLE__SELF_REF_COLUMN_GENERATION:
				return getSelfRefColumnGeneration();
			case SQLTablesPackage.TEMPORARY_TABLE__INSERTABLE:
				return isInsertable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.TEMPORARY_TABLE__UPDATABLE:
				return isUpdatable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.TEMPORARY_TABLE__CONSTRAINTS:
				return getConstraints();
			case SQLTablesPackage.TEMPORARY_TABLE__LOCAL:
				return isLocal() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.TEMPORARY_TABLE__DELETE_ON_COMMIT:
				return isDeleteOnCommit() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLTablesPackage.TEMPORARY_TABLE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__NAME:
				setName((String)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__SUPERTABLE:
				setSupertable((Table)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__SUBTABLES:
				getSubtables().clear();
				getSubtables().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__UDT:
				setUdt((StructuredUserDefinedType)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__TRIGGERS:
				getTriggers().clear();
				getTriggers().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__INDEX:
				getIndex().clear();
				getIndex().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration((ReferenceType)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__CONSTRAINTS:
				getConstraints().clear();
				getConstraints().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__LOCAL:
				setLocal(((Boolean)newValue).booleanValue());
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__DELETE_ON_COMMIT:
				setDeleteOnCommit(((Boolean)newValue).booleanValue());
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
			case SQLTablesPackage.TEMPORARY_TABLE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__COLUMNS:
				getColumns().clear();
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__SUPERTABLE:
				setSupertable((Table)null);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__SUBTABLES:
				getSubtables().clear();
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__UDT:
				setUdt((StructuredUserDefinedType)null);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__TRIGGERS:
				getTriggers().clear();
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__INDEX:
				getIndex().clear();
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration(SELF_REF_COLUMN_GENERATION_EDEFAULT);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__CONSTRAINTS:
				getConstraints().clear();
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__LOCAL:
				setLocal(LOCAL_EDEFAULT);
				return;
			case SQLTablesPackage.TEMPORARY_TABLE__DELETE_ON_COMMIT:
				setDeleteOnCommit(DELETE_ON_COMMIT_EDEFAULT);
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
			case SQLTablesPackage.TEMPORARY_TABLE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLTablesPackage.TEMPORARY_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLTablesPackage.TEMPORARY_TABLE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLTablesPackage.TEMPORARY_TABLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLTablesPackage.TEMPORARY_TABLE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLTablesPackage.TEMPORARY_TABLE__COLUMNS:
				return columns != null && !columns.isEmpty();
			case SQLTablesPackage.TEMPORARY_TABLE__SUPERTABLE:
				return supertable != null;
			case SQLTablesPackage.TEMPORARY_TABLE__SUBTABLES:
				return subtables != null && !subtables.isEmpty();
			case SQLTablesPackage.TEMPORARY_TABLE__SCHEMA:
				return schema != null;
			case SQLTablesPackage.TEMPORARY_TABLE__UDT:
				return udt != null;
			case SQLTablesPackage.TEMPORARY_TABLE__TRIGGERS:
				return triggers != null && !triggers.isEmpty();
			case SQLTablesPackage.TEMPORARY_TABLE__INDEX:
				return index != null && !index.isEmpty();
			case SQLTablesPackage.TEMPORARY_TABLE__SELF_REF_COLUMN_GENERATION:
				return selfRefColumnGeneration != SELF_REF_COLUMN_GENERATION_EDEFAULT;
			case SQLTablesPackage.TEMPORARY_TABLE__INSERTABLE:
				return isInsertable() != INSERTABLE_EDEFAULT;
			case SQLTablesPackage.TEMPORARY_TABLE__UPDATABLE:
				return isUpdatable() != UPDATABLE_EDEFAULT;
			case SQLTablesPackage.TEMPORARY_TABLE__CONSTRAINTS:
				return constraints != null && !constraints.isEmpty();
			case SQLTablesPackage.TEMPORARY_TABLE__LOCAL:
				return local != LOCAL_EDEFAULT;
			case SQLTablesPackage.TEMPORARY_TABLE__DELETE_ON_COMMIT:
				return deleteOnCommit != DELETE_ON_COMMIT_EDEFAULT;
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
		result.append(" (local: "); //$NON-NLS-1$
		result.append(local);
		result.append(", deleteOnCommit: "); //$NON-NLS-1$
		result.append(deleteOnCommit);
		result.append(')');
		return result.toString();
	}

} //TemporaryTableImpl
