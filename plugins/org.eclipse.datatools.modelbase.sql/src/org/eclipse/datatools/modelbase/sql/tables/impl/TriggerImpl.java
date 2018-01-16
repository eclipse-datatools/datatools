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
import java.util.Date;

import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getSubjectTable <em>Subject Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getActionStatement <em>Action Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getTriggerColumn <em>Trigger Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getActionGranularity <em>Action Granularity</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getWhen <em>When</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getTimeStamp <em>Time Stamp</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getActionTime <em>Action Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#isUpdateType <em>Update Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#isInsertType <em>Insert Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#isDeleteType <em>Delete Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getOldRow <em>Old Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getNewRow <em>New Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getOldTable <em>Old Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl#getNewTable <em>New Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TriggerImpl extends SQLObjectImpl implements Trigger {
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
	 * The cached value of the '{@link #getSubjectTable() <em>Subject Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjectTable()
	 * @generated
	 * @ordered
	 */
	protected Table subjectTable;

	/**
	 * The cached value of the '{@link #getActionStatement() <em>Action Statement</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionStatement()
	 * @generated
	 * @ordered
	 */
	protected EList actionStatement;

	/**
	 * The cached value of the '{@link #getTriggerColumn() <em>Trigger Column</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggerColumn()
	 * @generated
	 * @ordered
	 */
	protected EList triggerColumn;

	/**
	 * The default value of the '{@link #getActionGranularity() <em>Action Granularity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionGranularity()
	 * @generated
	 * @ordered
	 */
	protected static final ActionGranularityType ACTION_GRANULARITY_EDEFAULT = ActionGranularityType.STATEMENT_LITERAL;

	/**
	 * The cached value of the '{@link #getActionGranularity() <em>Action Granularity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionGranularity()
	 * @generated
	 * @ordered
	 */
	protected ActionGranularityType actionGranularity = ACTION_GRANULARITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWhen() <em>When</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhen()
	 * @generated
	 * @ordered
	 */
	protected SearchCondition when;

	/**
	 * The default value of the '{@link #getTimeStamp() <em>Time Stamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeStamp()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIME_STAMP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimeStamp() <em>Time Stamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeStamp()
	 * @generated
	 * @ordered
	 */
	protected Date timeStamp = TIME_STAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #getActionTime() <em>Action Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionTime()
	 * @generated
	 * @ordered
	 */
	protected static final ActionTimeType ACTION_TIME_EDEFAULT = ActionTimeType.AFTER_LITERAL;

	/**
	 * The cached value of the '{@link #getActionTime() <em>Action Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionTime()
	 * @generated
	 * @ordered
	 */
	protected ActionTimeType actionTime = ACTION_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #isUpdateType() <em>Update Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUpdateType()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UPDATE_TYPE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUpdateType() <em>Update Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUpdateType()
	 * @generated
	 * @ordered
	 */
	protected boolean updateType = UPDATE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isInsertType() <em>Insert Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInsertType()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSERT_TYPE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInsertType() <em>Insert Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInsertType()
	 * @generated
	 * @ordered
	 */
	protected boolean insertType = INSERT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isDeleteType() <em>Delete Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeleteType()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DELETE_TYPE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeleteType() <em>Delete Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeleteType()
	 * @generated
	 * @ordered
	 */
	protected boolean deleteType = DELETE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOldRow() <em>Old Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldRow()
	 * @generated
	 * @ordered
	 */
	protected static final String OLD_ROW_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOldRow() <em>Old Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldRow()
	 * @generated
	 * @ordered
	 */
	protected String oldRow = OLD_ROW_EDEFAULT;

	/**
	 * The default value of the '{@link #getNewRow() <em>New Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewRow()
	 * @generated
	 * @ordered
	 */
	protected static final String NEW_ROW_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNewRow() <em>New Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewRow()
	 * @generated
	 * @ordered
	 */
	protected String newRow = NEW_ROW_EDEFAULT;

	/**
	 * The default value of the '{@link #getOldTable() <em>Old Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldTable()
	 * @generated
	 * @ordered
	 */
	protected static final String OLD_TABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOldTable() <em>Old Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOldTable()
	 * @generated
	 * @ordered
	 */
	protected String oldTable = OLD_TABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNewTable() <em>New Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewTable()
	 * @generated
	 * @ordered
	 */
	protected static final String NEW_TABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNewTable() <em>New Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewTable()
	 * @generated
	 * @ordered
	 */
	protected String newTable = NEW_TABLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLTablesPackage.Literals.TRIGGER;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLTablesPackage.TRIGGER__SCHEMA, oldSchema, schema));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__SCHEMA, oldSchema, newSchema);
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
				msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__TRIGGERS, Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, SQLSchemaPackage.SCHEMA__TRIGGERS, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getSubjectTable() {
		if (subjectTable != null && subjectTable.eIsProxy()) {
			InternalEObject oldSubjectTable = (InternalEObject)subjectTable;
			subjectTable = (Table)eResolveProxy(oldSubjectTable);
			if (subjectTable != oldSubjectTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLTablesPackage.TRIGGER__SUBJECT_TABLE, oldSubjectTable, subjectTable));
			}
		}
		return subjectTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table basicGetSubjectTable() {
		return subjectTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubjectTable(Table newSubjectTable, NotificationChain msgs) {
		Table oldSubjectTable = subjectTable;
		subjectTable = newSubjectTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__SUBJECT_TABLE, oldSubjectTable, newSubjectTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubjectTable(Table newSubjectTable) {
		if (newSubjectTable != subjectTable) {
			NotificationChain msgs = null;
			if (subjectTable != null)
				msgs = ((InternalEObject)subjectTable).eInverseRemove(this, SQLTablesPackage.TABLE__TRIGGERS, Table.class, msgs);
			if (newSubjectTable != null)
				msgs = ((InternalEObject)newSubjectTable).eInverseAdd(this, SQLTablesPackage.TABLE__TRIGGERS, Table.class, msgs);
			msgs = basicSetSubjectTable(newSubjectTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__SUBJECT_TABLE, newSubjectTable, newSubjectTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getActionStatement() {
		if (actionStatement == null) {
			actionStatement = new EObjectContainmentEList(SQLStatement.class, this, SQLTablesPackage.TRIGGER__ACTION_STATEMENT);
		}
		return actionStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTriggerColumn() {
		if (triggerColumn == null) {
			triggerColumn = new EObjectResolvingEList(Column.class, this, SQLTablesPackage.TRIGGER__TRIGGER_COLUMN);
		}
		return triggerColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionGranularityType getActionGranularity() {
		return actionGranularity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionGranularity(ActionGranularityType newActionGranularity) {
		ActionGranularityType oldActionGranularity = actionGranularity;
		actionGranularity = newActionGranularity == null ? ACTION_GRANULARITY_EDEFAULT : newActionGranularity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__ACTION_GRANULARITY, oldActionGranularity, actionGranularity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchCondition getWhen() {
		return when;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWhen(SearchCondition newWhen, NotificationChain msgs) {
		SearchCondition oldWhen = when;
		when = newWhen;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__WHEN, oldWhen, newWhen);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWhen(SearchCondition newWhen) {
		if (newWhen != when) {
			NotificationChain msgs = null;
			if (when != null)
				msgs = ((InternalEObject)when).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLTablesPackage.TRIGGER__WHEN, null, msgs);
			if (newWhen != null)
				msgs = ((InternalEObject)newWhen).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLTablesPackage.TRIGGER__WHEN, null, msgs);
			msgs = basicSetWhen(newWhen, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__WHEN, newWhen, newWhen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionTimeType getActionTime() {
		return actionTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionTime(ActionTimeType newActionTime) {
		ActionTimeType oldActionTime = actionTime;
		actionTime = newActionTime == null ? ACTION_TIME_EDEFAULT : newActionTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__ACTION_TIME, oldActionTime, actionTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUpdateType() {
		return updateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpdateType(boolean newUpdateType) {
		boolean oldUpdateType = updateType;
		updateType = newUpdateType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__UPDATE_TYPE, oldUpdateType, updateType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInsertType() {
		return insertType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInsertType(boolean newInsertType) {
		boolean oldInsertType = insertType;
		insertType = newInsertType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__INSERT_TYPE, oldInsertType, insertType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeleteType() {
		return deleteType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeleteType(boolean newDeleteType) {
		boolean oldDeleteType = deleteType;
		deleteType = newDeleteType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__DELETE_TYPE, oldDeleteType, deleteType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOldRow() {
		return oldRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOldRow(String newOldRow) {
		String oldOldRow = oldRow;
		oldRow = newOldRow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__OLD_ROW, oldOldRow, oldRow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNewRow() {
		return newRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewRow(String newNewRow) {
		String oldNewRow = newRow;
		newRow = newNewRow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__NEW_ROW, oldNewRow, newRow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOldTable() {
		return oldTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOldTable(String newOldTable) {
		String oldOldTable = oldTable;
		oldTable = newOldTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__OLD_TABLE, oldOldTable, oldTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNewTable() {
		return newTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewTable(String newNewTable) {
		String oldNewTable = newTable;
		newTable = newNewTable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.TRIGGER__NEW_TABLE, oldNewTable, newTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLTablesPackage.TRIGGER__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__TRIGGERS, Schema.class, msgs);
				return basicSetSchema((Schema)otherEnd, msgs);
			case SQLTablesPackage.TRIGGER__SUBJECT_TABLE:
				if (subjectTable != null)
					msgs = ((InternalEObject)subjectTable).eInverseRemove(this, SQLTablesPackage.TABLE__TRIGGERS, Table.class, msgs);
				return basicSetSubjectTable((Table)otherEnd, msgs);
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
			case SQLTablesPackage.TRIGGER__SCHEMA:
				return basicSetSchema(null, msgs);
			case SQLTablesPackage.TRIGGER__SUBJECT_TABLE:
				return basicSetSubjectTable(null, msgs);
			case SQLTablesPackage.TRIGGER__ACTION_STATEMENT:
				return ((InternalEList)getActionStatement()).basicRemove(otherEnd, msgs);
			case SQLTablesPackage.TRIGGER__WHEN:
				return basicSetWhen(null, msgs);
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
			case SQLTablesPackage.TRIGGER__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLTablesPackage.TRIGGER__SUBJECT_TABLE:
				if (resolve) return getSubjectTable();
				return basicGetSubjectTable();
			case SQLTablesPackage.TRIGGER__ACTION_STATEMENT:
				return getActionStatement();
			case SQLTablesPackage.TRIGGER__TRIGGER_COLUMN:
				return getTriggerColumn();
			case SQLTablesPackage.TRIGGER__ACTION_GRANULARITY:
				return getActionGranularity();
			case SQLTablesPackage.TRIGGER__WHEN:
				return getWhen();
			case SQLTablesPackage.TRIGGER__TIME_STAMP:
				return getTimeStamp();
			case SQLTablesPackage.TRIGGER__ACTION_TIME:
				return getActionTime();
			case SQLTablesPackage.TRIGGER__UPDATE_TYPE:
				return isUpdateType() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.TRIGGER__INSERT_TYPE:
				return isInsertType() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.TRIGGER__DELETE_TYPE:
				return isDeleteType() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.TRIGGER__OLD_ROW:
				return getOldRow();
			case SQLTablesPackage.TRIGGER__NEW_ROW:
				return getNewRow();
			case SQLTablesPackage.TRIGGER__OLD_TABLE:
				return getOldTable();
			case SQLTablesPackage.TRIGGER__NEW_TABLE:
				return getNewTable();
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
			case SQLTablesPackage.TRIGGER__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLTablesPackage.TRIGGER__SUBJECT_TABLE:
				setSubjectTable((Table)newValue);
				return;
			case SQLTablesPackage.TRIGGER__ACTION_STATEMENT:
				getActionStatement().clear();
				getActionStatement().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TRIGGER__TRIGGER_COLUMN:
				getTriggerColumn().clear();
				getTriggerColumn().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.TRIGGER__ACTION_GRANULARITY:
				setActionGranularity((ActionGranularityType)newValue);
				return;
			case SQLTablesPackage.TRIGGER__WHEN:
				setWhen((SearchCondition)newValue);
				return;
			case SQLTablesPackage.TRIGGER__ACTION_TIME:
				setActionTime((ActionTimeType)newValue);
				return;
			case SQLTablesPackage.TRIGGER__UPDATE_TYPE:
				setUpdateType(((Boolean)newValue).booleanValue());
				return;
			case SQLTablesPackage.TRIGGER__INSERT_TYPE:
				setInsertType(((Boolean)newValue).booleanValue());
				return;
			case SQLTablesPackage.TRIGGER__DELETE_TYPE:
				setDeleteType(((Boolean)newValue).booleanValue());
				return;
			case SQLTablesPackage.TRIGGER__OLD_ROW:
				setOldRow((String)newValue);
				return;
			case SQLTablesPackage.TRIGGER__NEW_ROW:
				setNewRow((String)newValue);
				return;
			case SQLTablesPackage.TRIGGER__OLD_TABLE:
				setOldTable((String)newValue);
				return;
			case SQLTablesPackage.TRIGGER__NEW_TABLE:
				setNewTable((String)newValue);
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
			case SQLTablesPackage.TRIGGER__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLTablesPackage.TRIGGER__SUBJECT_TABLE:
				setSubjectTable((Table)null);
				return;
			case SQLTablesPackage.TRIGGER__ACTION_STATEMENT:
				getActionStatement().clear();
				return;
			case SQLTablesPackage.TRIGGER__TRIGGER_COLUMN:
				getTriggerColumn().clear();
				return;
			case SQLTablesPackage.TRIGGER__ACTION_GRANULARITY:
				setActionGranularity(ACTION_GRANULARITY_EDEFAULT);
				return;
			case SQLTablesPackage.TRIGGER__WHEN:
				setWhen((SearchCondition)null);
				return;
			case SQLTablesPackage.TRIGGER__ACTION_TIME:
				setActionTime(ACTION_TIME_EDEFAULT);
				return;
			case SQLTablesPackage.TRIGGER__UPDATE_TYPE:
				setUpdateType(UPDATE_TYPE_EDEFAULT);
				return;
			case SQLTablesPackage.TRIGGER__INSERT_TYPE:
				setInsertType(INSERT_TYPE_EDEFAULT);
				return;
			case SQLTablesPackage.TRIGGER__DELETE_TYPE:
				setDeleteType(DELETE_TYPE_EDEFAULT);
				return;
			case SQLTablesPackage.TRIGGER__OLD_ROW:
				setOldRow(OLD_ROW_EDEFAULT);
				return;
			case SQLTablesPackage.TRIGGER__NEW_ROW:
				setNewRow(NEW_ROW_EDEFAULT);
				return;
			case SQLTablesPackage.TRIGGER__OLD_TABLE:
				setOldTable(OLD_TABLE_EDEFAULT);
				return;
			case SQLTablesPackage.TRIGGER__NEW_TABLE:
				setNewTable(NEW_TABLE_EDEFAULT);
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
			case SQLTablesPackage.TRIGGER__SCHEMA:
				return schema != null;
			case SQLTablesPackage.TRIGGER__SUBJECT_TABLE:
				return subjectTable != null;
			case SQLTablesPackage.TRIGGER__ACTION_STATEMENT:
				return actionStatement != null && !actionStatement.isEmpty();
			case SQLTablesPackage.TRIGGER__TRIGGER_COLUMN:
				return triggerColumn != null && !triggerColumn.isEmpty();
			case SQLTablesPackage.TRIGGER__ACTION_GRANULARITY:
				return actionGranularity != ACTION_GRANULARITY_EDEFAULT;
			case SQLTablesPackage.TRIGGER__WHEN:
				return when != null;
			case SQLTablesPackage.TRIGGER__TIME_STAMP:
				return TIME_STAMP_EDEFAULT == null ? timeStamp != null : !TIME_STAMP_EDEFAULT.equals(timeStamp);
			case SQLTablesPackage.TRIGGER__ACTION_TIME:
				return actionTime != ACTION_TIME_EDEFAULT;
			case SQLTablesPackage.TRIGGER__UPDATE_TYPE:
				return updateType != UPDATE_TYPE_EDEFAULT;
			case SQLTablesPackage.TRIGGER__INSERT_TYPE:
				return insertType != INSERT_TYPE_EDEFAULT;
			case SQLTablesPackage.TRIGGER__DELETE_TYPE:
				return deleteType != DELETE_TYPE_EDEFAULT;
			case SQLTablesPackage.TRIGGER__OLD_ROW:
				return OLD_ROW_EDEFAULT == null ? oldRow != null : !OLD_ROW_EDEFAULT.equals(oldRow);
			case SQLTablesPackage.TRIGGER__NEW_ROW:
				return NEW_ROW_EDEFAULT == null ? newRow != null : !NEW_ROW_EDEFAULT.equals(newRow);
			case SQLTablesPackage.TRIGGER__OLD_TABLE:
				return OLD_TABLE_EDEFAULT == null ? oldTable != null : !OLD_TABLE_EDEFAULT.equals(oldTable);
			case SQLTablesPackage.TRIGGER__NEW_TABLE:
				return NEW_TABLE_EDEFAULT == null ? newTable != null : !NEW_TABLE_EDEFAULT.equals(newTable);
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
		result.append(" (actionGranularity: "); //$NON-NLS-1$
		result.append(actionGranularity);
		result.append(", timeStamp: "); //$NON-NLS-1$
		result.append(timeStamp);
		result.append(", actionTime: "); //$NON-NLS-1$
		result.append(actionTime);
		result.append(", updateType: "); //$NON-NLS-1$
		result.append(updateType);
		result.append(", insertType: "); //$NON-NLS-1$
		result.append(insertType);
		result.append(", deleteType: "); //$NON-NLS-1$
		result.append(deleteType);
		result.append(", oldRow: "); //$NON-NLS-1$
		result.append(oldRow);
		result.append(", newRow: "); //$NON-NLS-1$
		result.append(newRow);
		result.append(", oldTable: "); //$NON-NLS-1$
		result.append(oldTable);
		result.append(", newTable: "); //$NON-NLS-1$
		result.append(newTable);
		result.append(')');
		return result.toString();
	}

} //TriggerImpl
