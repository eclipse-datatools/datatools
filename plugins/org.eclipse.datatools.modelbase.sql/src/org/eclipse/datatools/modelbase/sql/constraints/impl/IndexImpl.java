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
package org.eclipse.datatools.modelbase.sql.constraints.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl#isClustered <em>Clustered</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl#getFillFactor <em>Fill Factor</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl#isSystemGenerated <em>System Generated</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl#getMembers <em>Members</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl#getForeignKey <em>Foreign Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl#getIncludedMembers <em>Included Members</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndexImpl extends SQLObjectImpl implements Index {
	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected Schema schema;

	/**
	 * The default value of the '{@link #isClustered() <em>Clustered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClustered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLUSTERED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isClustered() <em>Clustered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClustered()
	 * @generated
	 * @ordered
	 */
	protected boolean clustered = CLUSTERED_EDEFAULT;

	/**
	 * The default value of the '{@link #getFillFactor() <em>Fill Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillFactor()
	 * @generated
	 * @ordered
	 */
	protected static final int FILL_FACTOR_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getFillFactor() <em>Fill Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillFactor()
	 * @generated
	 * @ordered
	 */
	protected int fillFactor = FILL_FACTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNIQUE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean unique = UNIQUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isSystemGenerated() <em>System Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSystemGenerated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYSTEM_GENERATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSystemGenerated() <em>System Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSystemGenerated()
	 * @generated
	 * @ordered
	 */
	protected boolean systemGenerated = SYSTEM_GENERATED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMembers() <em>Members</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMembers()
	 * @generated
	 * @ordered
	 */
	protected EList members;

	/**
	 * The cached value of the '{@link #getTable() <em>Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTable()
	 * @generated
	 * @ordered
	 */
	protected Table table;

	/**
	 * The cached value of the '{@link #getForeignKey() <em>Foreign Key</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeignKey()
	 * @generated
	 * @ordered
	 */
	protected EList foreignKey;

	/**
	 * The cached value of the '{@link #getIncludedMembers() <em>Included Members</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedMembers()
	 * @generated
	 * @ordered
	 */
	protected EList includedMembers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndexImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLConstraintsPackage.Literals.INDEX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (Schema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLConstraintsPackage.INDEX__SCHEMA, oldSchema, schema));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.INDEX__SCHEMA, oldSchema, newSchema);
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
				msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__INDICES, Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, SQLSchemaPackage.SCHEMA__INDICES, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.INDEX__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isClustered() {
		return clustered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClustered(boolean newClustered) {
		boolean oldClustered = clustered;
		clustered = newClustered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.INDEX__CLUSTERED, oldClustered, clustered));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getFillFactor() {
		return fillFactor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillFactor(int newFillFactor) {
		int oldFillFactor = fillFactor;
		fillFactor = newFillFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.INDEX__FILL_FACTOR, oldFillFactor, fillFactor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnique() {
		return unique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnique(boolean newUnique) {
		boolean oldUnique = unique;
		unique = newUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.INDEX__UNIQUE, oldUnique, unique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSystemGenerated() {
		return systemGenerated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystemGenerated(boolean newSystemGenerated) {
		boolean oldSystemGenerated = systemGenerated;
		systemGenerated = newSystemGenerated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.INDEX__SYSTEM_GENERATED, oldSystemGenerated, systemGenerated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMembers() {
		if (members == null) {
			members = new EObjectContainmentEList(IndexMember.class, this, SQLConstraintsPackage.INDEX__MEMBERS);
		}
		return members;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getTable() {
		if (table != null && table.eIsProxy()) {
			InternalEObject oldTable = (InternalEObject)table;
			table = (Table)eResolveProxy(oldTable);
			if (table != oldTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLConstraintsPackage.INDEX__TABLE, oldTable, table));
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
	public NotificationChain basicSetTable(Table newTable, NotificationChain msgs) {
		Table oldTable = table;
		table = newTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.INDEX__TABLE, oldTable, newTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(Table newTable) {
		if (newTable != table) {
			NotificationChain msgs = null;
			if (table != null)
				msgs = ((InternalEObject)table).eInverseRemove(this, SQLTablesPackage.TABLE__INDEX, Table.class, msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, SQLTablesPackage.TABLE__INDEX, Table.class, msgs);
			msgs = basicSetTable(newTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.INDEX__TABLE, newTable, newTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getForeignKey() {
		if (foreignKey == null) {
			foreignKey = new EObjectWithInverseResolvingEList(ForeignKey.class, this, SQLConstraintsPackage.INDEX__FOREIGN_KEY, SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX);
		}
		return foreignKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncludedMembers() {
		if (includedMembers == null) {
			includedMembers = new EObjectContainmentEList(IndexMember.class, this, SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS);
		}
		return includedMembers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLConstraintsPackage.INDEX__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__INDICES, Schema.class, msgs);
				return basicSetSchema((Schema)otherEnd, msgs);
			case SQLConstraintsPackage.INDEX__TABLE:
				if (table != null)
					msgs = ((InternalEObject)table).eInverseRemove(this, SQLTablesPackage.TABLE__INDEX, Table.class, msgs);
				return basicSetTable((Table)otherEnd, msgs);
			case SQLConstraintsPackage.INDEX__FOREIGN_KEY:
				return ((InternalEList)getForeignKey()).basicAdd(otherEnd, msgs);
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
			case SQLConstraintsPackage.INDEX__SCHEMA:
				return basicSetSchema(null, msgs);
			case SQLConstraintsPackage.INDEX__MEMBERS:
				return ((InternalEList)getMembers()).basicRemove(otherEnd, msgs);
			case SQLConstraintsPackage.INDEX__TABLE:
				return basicSetTable(null, msgs);
			case SQLConstraintsPackage.INDEX__FOREIGN_KEY:
				return ((InternalEList)getForeignKey()).basicRemove(otherEnd, msgs);
			case SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS:
				return ((InternalEList)getIncludedMembers()).basicRemove(otherEnd, msgs);
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
			case SQLConstraintsPackage.INDEX__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLConstraintsPackage.INDEX__CLUSTERED:
				return isClustered() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.INDEX__FILL_FACTOR:
				return new Integer(getFillFactor());
			case SQLConstraintsPackage.INDEX__UNIQUE:
				return isUnique() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.INDEX__SYSTEM_GENERATED:
				return isSystemGenerated() ? Boolean.TRUE : Boolean.FALSE;
			case SQLConstraintsPackage.INDEX__MEMBERS:
				return getMembers();
			case SQLConstraintsPackage.INDEX__TABLE:
				if (resolve) return getTable();
				return basicGetTable();
			case SQLConstraintsPackage.INDEX__FOREIGN_KEY:
				return getForeignKey();
			case SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS:
				return getIncludedMembers();
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
			case SQLConstraintsPackage.INDEX__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLConstraintsPackage.INDEX__CLUSTERED:
				setClustered(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.INDEX__FILL_FACTOR:
				setFillFactor(((Integer)newValue).intValue());
				return;
			case SQLConstraintsPackage.INDEX__UNIQUE:
				setUnique(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.INDEX__SYSTEM_GENERATED:
				setSystemGenerated(((Boolean)newValue).booleanValue());
				return;
			case SQLConstraintsPackage.INDEX__MEMBERS:
				getMembers().clear();
				getMembers().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.INDEX__TABLE:
				setTable((Table)newValue);
				return;
			case SQLConstraintsPackage.INDEX__FOREIGN_KEY:
				getForeignKey().clear();
				getForeignKey().addAll((Collection)newValue);
				return;
			case SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS:
				getIncludedMembers().clear();
				getIncludedMembers().addAll((Collection)newValue);
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
			case SQLConstraintsPackage.INDEX__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLConstraintsPackage.INDEX__CLUSTERED:
				setClustered(CLUSTERED_EDEFAULT);
				return;
			case SQLConstraintsPackage.INDEX__FILL_FACTOR:
				setFillFactor(FILL_FACTOR_EDEFAULT);
				return;
			case SQLConstraintsPackage.INDEX__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case SQLConstraintsPackage.INDEX__SYSTEM_GENERATED:
				setSystemGenerated(SYSTEM_GENERATED_EDEFAULT);
				return;
			case SQLConstraintsPackage.INDEX__MEMBERS:
				getMembers().clear();
				return;
			case SQLConstraintsPackage.INDEX__TABLE:
				setTable((Table)null);
				return;
			case SQLConstraintsPackage.INDEX__FOREIGN_KEY:
				getForeignKey().clear();
				return;
			case SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS:
				getIncludedMembers().clear();
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
			case SQLConstraintsPackage.INDEX__SCHEMA:
				return schema != null;
			case SQLConstraintsPackage.INDEX__CLUSTERED:
				return clustered != CLUSTERED_EDEFAULT;
			case SQLConstraintsPackage.INDEX__FILL_FACTOR:
				return fillFactor != FILL_FACTOR_EDEFAULT;
			case SQLConstraintsPackage.INDEX__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case SQLConstraintsPackage.INDEX__SYSTEM_GENERATED:
				return systemGenerated != SYSTEM_GENERATED_EDEFAULT;
			case SQLConstraintsPackage.INDEX__MEMBERS:
				return members != null && !members.isEmpty();
			case SQLConstraintsPackage.INDEX__TABLE:
				return table != null;
			case SQLConstraintsPackage.INDEX__FOREIGN_KEY:
				return foreignKey != null && !foreignKey.isEmpty();
			case SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS:
				return includedMembers != null && !includedMembers.isEmpty();
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
		result.append(" (clustered: "); //$NON-NLS-1$
		result.append(clustered);
		result.append(", fillFactor: "); //$NON-NLS-1$
		result.append(fillFactor);
		result.append(", unique: "); //$NON-NLS-1$
		result.append(unique);
		result.append(", systemGenerated: "); //$NON-NLS-1$
		result.append(systemGenerated);
		result.append(')');
		return result.toString();
	}

} //IndexImpl
