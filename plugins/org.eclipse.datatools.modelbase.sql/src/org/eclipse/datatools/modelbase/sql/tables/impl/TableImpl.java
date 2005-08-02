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

import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.ReferenceType;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#getSupertable <em>Supertable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#getSubtables <em>Subtables</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#getUdt <em>Udt</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#getTriggers <em>Triggers</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#getSelfRefColumnGeneration <em>Self Ref Column Generation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#isInsertable <em>Insertable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TableImpl#isUpdatable <em>Updatable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TableImpl extends SQLObjectImpl implements Table {
	/**
	 * The cached value of the '{@link #getColumns() <em>Columns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumns()
	 * @generated
	 * @ordered
	 */
	protected EList columns = null;

	/**
	 * The cached value of the '{@link #getSupertable() <em>Supertable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupertable()
	 * @generated
	 * @ordered
	 */
	protected Table supertable = null;

	/**
	 * The cached value of the '{@link #getSubtables() <em>Subtables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubtables()
	 * @generated
	 * @ordered
	 */
	protected EList subtables = null;

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
	 * The cached value of the '{@link #getUdt() <em>Udt</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUdt()
	 * @generated
	 * @ordered
	 */
	protected StructuredUserDefinedType udt = null;

	/**
	 * The cached value of the '{@link #getTriggers() <em>Triggers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggers()
	 * @generated
	 * @ordered
	 */
	protected EList triggers = null;

	/**
	 * The cached value of the '{@link #getIndex() <em>Index</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndex()
	 * @generated
	 * @ordered
	 */
	protected EList index = null;

	/**
	 * The default value of the '{@link #getSelfRefColumnGeneration() <em>Self Ref Column Generation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelfRefColumnGeneration()
	 * @generated
	 * @ordered
	 */
	protected static final ReferenceType SELF_REF_COLUMN_GENERATION_EDEFAULT = ReferenceType.SYSTEM_GENERATED_LITERAL;

	/**
	 * The cached value of the '{@link #getSelfRefColumnGeneration() <em>Self Ref Column Generation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelfRefColumnGeneration()
	 * @generated
	 * @ordered
	 */
	protected ReferenceType selfRefColumnGeneration = SELF_REF_COLUMN_GENERATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isInsertable() <em>Insertable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInsertable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSERTABLE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isUpdatable() <em>Updatable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUpdatable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UPDATABLE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLTablesPackage.eINSTANCE.getTable();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getColumns() {
		if (columns == null) {
			columns = new EObjectContainmentWithInverseEList(Column.class, this, SQLTablesPackage.TABLE__COLUMNS, SQLTablesPackage.COLUMN__TABLE);
		}
		return columns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getSupertable() {
		if (supertable != null && supertable.eIsProxy()) {
			Table oldSupertable = supertable;
			supertable = (Table)eResolveProxy((InternalEObject)supertable);
			if (supertable != oldSupertable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLTablesPackage.TABLE__SUPERTABLE, oldSupertable, supertable));
			}
		}
		return supertable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetSupertable() {
		return supertable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSupertable(Table newSupertable, NotificationChain msgs) {
		Table oldSupertable = supertable;
		supertable = newSupertable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TABLE__SUPERTABLE, oldSupertable, newSupertable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupertable(Table newSupertable) {
		if (newSupertable != supertable) {
			NotificationChain msgs = null;
			if (supertable != null)
				msgs = ((InternalEObject)supertable).eInverseRemove(this, SQLTablesPackage.TABLE__SUBTABLES, Table.class, msgs);
			if (newSupertable != null)
				msgs = ((InternalEObject)newSupertable).eInverseAdd(this, SQLTablesPackage.TABLE__SUBTABLES, Table.class, msgs);
			msgs = basicSetSupertable(newSupertable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TABLE__SUPERTABLE, newSupertable, newSupertable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSubtables() {
		if (subtables == null) {
			subtables = new EObjectWithInverseResolvingEList(Table.class, this, SQLTablesPackage.TABLE__SUBTABLES, SQLTablesPackage.TABLE__SUPERTABLE);
		}
		return subtables;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLTablesPackage.TABLE__SCHEMA, oldSchema, schema));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TABLE__SCHEMA, oldSchema, newSchema);
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
				msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__TABLES, Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, SQLSchemaPackage.SCHEMA__TABLES, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TABLE__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredUserDefinedType getUdt() {
		if (udt != null && udt.eIsProxy()) {
			StructuredUserDefinedType oldUdt = udt;
			udt = (StructuredUserDefinedType)eResolveProxy((InternalEObject)udt);
			if (udt != oldUdt) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLTablesPackage.TABLE__UDT, oldUdt, udt));
			}
		}
		return udt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredUserDefinedType basicGetUdt() {
		return udt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUdt(StructuredUserDefinedType newUdt) {
		StructuredUserDefinedType oldUdt = udt;
		udt = newUdt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TABLE__UDT, oldUdt, udt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTriggers() {
		if (triggers == null) {
			triggers = new EObjectWithInverseResolvingEList(Trigger.class, this, SQLTablesPackage.TABLE__TRIGGERS, SQLTablesPackage.TRIGGER__SUBJECT_TABLE);
		}
		return triggers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIndex() {
		if (index == null) {
			index = new EObjectWithInverseResolvingEList(Index.class, this, SQLTablesPackage.TABLE__INDEX, SQLConstraintsPackage.INDEX__TABLE);
		}
		return index;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceType getSelfRefColumnGeneration() {
		return selfRefColumnGeneration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelfRefColumnGeneration(ReferenceType newSelfRefColumnGeneration) {
		ReferenceType oldSelfRefColumnGeneration = selfRefColumnGeneration;
		selfRefColumnGeneration = newSelfRefColumnGeneration == null ? SELF_REF_COLUMN_GENERATION_EDEFAULT : newSelfRefColumnGeneration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TABLE__SELF_REF_COLUMN_GENERATION, oldSelfRefColumnGeneration, selfRefColumnGeneration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInsertable() {
		// TODO: implement this method to return the 'Insertable' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUpdatable() {
		// TODO: implement this method to return the 'Updatable' attribute
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLTablesPackage.TABLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.TABLE__COLUMNS:
					return ((InternalEList)getColumns()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.TABLE__SUPERTABLE:
					if (supertable != null)
						msgs = ((InternalEObject)supertable).eInverseRemove(this, SQLTablesPackage.TABLE__SUBTABLES, Table.class, msgs);
					return basicSetSupertable((Table)otherEnd, msgs);
				case SQLTablesPackage.TABLE__SUBTABLES:
					return ((InternalEList)getSubtables()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.TABLE__SCHEMA:
					if (schema != null)
						msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__TABLES, Schema.class, msgs);
					return basicSetSchema((Schema)otherEnd, msgs);
				case SQLTablesPackage.TABLE__TRIGGERS:
					return ((InternalEList)getTriggers()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.TABLE__INDEX:
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
				case SQLTablesPackage.TABLE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TABLE__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TABLE__COLUMNS:
					return ((InternalEList)getColumns()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TABLE__SUPERTABLE:
					return basicSetSupertable(null, msgs);
				case SQLTablesPackage.TABLE__SUBTABLES:
					return ((InternalEList)getSubtables()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TABLE__SCHEMA:
					return basicSetSchema(null, msgs);
				case SQLTablesPackage.TABLE__TRIGGERS:
					return ((InternalEList)getTriggers()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.TABLE__INDEX:
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
			case SQLTablesPackage.TABLE__EANNOTATIONS:
				return getEAnnotations();
			case SQLTablesPackage.TABLE__NAME:
				return getName();
			case SQLTablesPackage.TABLE__DEPENDENCIES:
				return getDependencies();
			case SQLTablesPackage.TABLE__DESCRIPTION:
				return getDescription();
			case SQLTablesPackage.TABLE__LABEL:
				return getLabel();
			case SQLTablesPackage.TABLE__COLUMNS:
				return getColumns();
			case SQLTablesPackage.TABLE__SUPERTABLE:
				if (resolve) return getSupertable();
				return basicGetSupertable();
			case SQLTablesPackage.TABLE__SUBTABLES:
				return getSubtables();
			case SQLTablesPackage.TABLE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLTablesPackage.TABLE__UDT:
				if (resolve) return getUdt();
				return basicGetUdt();
			case SQLTablesPackage.TABLE__TRIGGERS:
				return getTriggers();
			case SQLTablesPackage.TABLE__INDEX:
				return getIndex();
			case SQLTablesPackage.TABLE__SELF_REF_COLUMN_GENERATION:
				return getSelfRefColumnGeneration();
			case SQLTablesPackage.TABLE__INSERTABLE:
				return isInsertable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.TABLE__UPDATABLE:
				return isUpdatable() ? Boolean.TRUE : Boolean.FALSE;
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
			case SQLTablesPackage.TABLE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TABLE__NAME:
				setName((String)newValue);
				return;
			case SQLTablesPackage.TABLE__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TABLE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLTablesPackage.TABLE__LABEL:
				setLabel((String)newValue);
				return;
			case SQLTablesPackage.TABLE__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TABLE__SUPERTABLE:
				setSupertable((Table)newValue);
				return;
			case SQLTablesPackage.TABLE__SUBTABLES:
				getSubtables().clear();
				getSubtables().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TABLE__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLTablesPackage.TABLE__UDT:
				setUdt((StructuredUserDefinedType)newValue);
				return;
			case SQLTablesPackage.TABLE__TRIGGERS:
				getTriggers().clear();
				getTriggers().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TABLE__INDEX:
				getIndex().clear();
				getIndex().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TABLE__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration((ReferenceType)newValue);
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
			case SQLTablesPackage.TABLE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLTablesPackage.TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLTablesPackage.TABLE__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLTablesPackage.TABLE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLTablesPackage.TABLE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLTablesPackage.TABLE__COLUMNS:
				getColumns().clear();
				return;
			case SQLTablesPackage.TABLE__SUPERTABLE:
				setSupertable((Table)null);
				return;
			case SQLTablesPackage.TABLE__SUBTABLES:
				getSubtables().clear();
				return;
			case SQLTablesPackage.TABLE__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLTablesPackage.TABLE__UDT:
				setUdt((StructuredUserDefinedType)null);
				return;
			case SQLTablesPackage.TABLE__TRIGGERS:
				getTriggers().clear();
				return;
			case SQLTablesPackage.TABLE__INDEX:
				getIndex().clear();
				return;
			case SQLTablesPackage.TABLE__SELF_REF_COLUMN_GENERATION:
				setSelfRefColumnGeneration(SELF_REF_COLUMN_GENERATION_EDEFAULT);
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
			case SQLTablesPackage.TABLE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLTablesPackage.TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLTablesPackage.TABLE__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLTablesPackage.TABLE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLTablesPackage.TABLE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLTablesPackage.TABLE__COLUMNS:
				return columns != null && !columns.isEmpty();
			case SQLTablesPackage.TABLE__SUPERTABLE:
				return supertable != null;
			case SQLTablesPackage.TABLE__SUBTABLES:
				return subtables != null && !subtables.isEmpty();
			case SQLTablesPackage.TABLE__SCHEMA:
				return schema != null;
			case SQLTablesPackage.TABLE__UDT:
				return udt != null;
			case SQLTablesPackage.TABLE__TRIGGERS:
				return triggers != null && !triggers.isEmpty();
			case SQLTablesPackage.TABLE__INDEX:
				return index != null && !index.isEmpty();
			case SQLTablesPackage.TABLE__SELF_REF_COLUMN_GENERATION:
				return selfRefColumnGeneration != SELF_REF_COLUMN_GENERATION_EDEFAULT;
			case SQLTablesPackage.TABLE__INSERTABLE:
				return isInsertable() != INSERTABLE_EDEFAULT;
			case SQLTablesPackage.TABLE__UPDATABLE:
				return isUpdatable() != UPDATABLE_EDEFAULT;
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
		result.append(" (selfRefColumnGeneration: "); //$NON-NLS-1$
		result.append(selfRefColumnGeneration);
		result.append(')');
		return result.toString();
	}

} //TableImpl
