/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Column;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;

import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Mask</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl#getCorrelationName <em>Correlation Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl#getCaseExpression <em>Case Expression</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl#isEnable <em>Enable</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl#getSubjectTable <em>Subject Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl#getSubjectMQT <em>Subject MQT</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl#getSubjectColumn <em>Subject Column</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2MaskImpl extends SQLObjectImpl implements DB2Mask {
	/**
	 * The default value of the '{@link #getCorrelationName() <em>Correlation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationName()
	 * @generated
	 * @ordered
	 */
	protected static final String CORRELATION_NAME_EDEFAULT = null; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getCorrelationName() <em>Correlation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationName()
	 * @generated
	 * @ordered
	 */
	protected String correlationName = CORRELATION_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCaseExpression() <em>Case Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCaseExpression()
	 * @generated
	 * @ordered
	 */
	protected QueryExpression caseExpression;

	/**
	 * The default value of the '{@link #isEnable() <em>Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnable() <em>Enable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnable()
	 * @generated
	 * @ordered
	 */
	protected boolean enable = ENABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected DB2Schema schema;

	/**
	 * The cached value of the '{@link #getSubjectTable() <em>Subject Table</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjectTable()
	 * @generated
	 * @ordered
	 */
	protected DB2Table subjectTable;

	/**
	 * The cached value of the '{@link #getSubjectMQT() <em>Subject MQT</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjectMQT()
	 * @generated
	 * @ordered
	 */
	protected DB2MaterializedQueryTable subjectMQT;

	/**
	 * The cached value of the '{@link #getSubjectColumn() <em>Subject Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjectColumn()
	 * @generated
	 * @ordered
	 */
	protected DB2Column subjectColumn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2MaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_MASK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCorrelationName() {
		return correlationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorrelationName(String newCorrelationName) {
		String oldCorrelationName = correlationName;
		correlationName = newCorrelationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__CORRELATION_NAME, oldCorrelationName, correlationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryExpression getCaseExpression() {
		return caseExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCaseExpression(QueryExpression newCaseExpression, NotificationChain msgs) {
		QueryExpression oldCaseExpression = caseExpression;
		caseExpression = newCaseExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__CASE_EXPRESSION, oldCaseExpression, newCaseExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCaseExpression(QueryExpression newCaseExpression) {
		if (newCaseExpression != caseExpression) {
			NotificationChain msgs = null;
			if (caseExpression != null)
				msgs = ((InternalEObject)caseExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_MASK__CASE_EXPRESSION, null, msgs);
			if (newCaseExpression != null)
				msgs = ((InternalEObject)newCaseExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_MASK__CASE_EXPRESSION, null, msgs);
			msgs = basicSetCaseExpression(newCaseExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__CASE_EXPRESSION, newCaseExpression, newCaseExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnable(boolean newEnable) {
		boolean oldEnable = enable;
		enable = newEnable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__ENABLE, oldEnable, enable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (DB2Schema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_MASK__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Schema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(DB2Schema newSchema, NotificationChain msgs) {
		DB2Schema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__SCHEMA, oldSchema, newSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(DB2Schema newSchema) {
		if (newSchema != schema) {
			NotificationChain msgs = null;
			if (schema != null)
				msgs = ((InternalEObject)schema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__MASKS, DB2Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, DB2ModelPackage.DB2_SCHEMA__MASKS, DB2Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Table getSubjectTable() {
		if (subjectTable != null && subjectTable.eIsProxy()) {
			InternalEObject oldSubjectTable = (InternalEObject)subjectTable;
			subjectTable = (DB2Table)eResolveProxy(oldSubjectTable);
			if (subjectTable != oldSubjectTable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_MASK__SUBJECT_TABLE, oldSubjectTable, subjectTable));
			}
		}
		return subjectTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Table basicGetSubjectTable() {
		return subjectTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubjectTable(DB2Table newSubjectTable, NotificationChain msgs) {
		DB2Table oldSubjectTable = subjectTable;
		subjectTable = newSubjectTable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__SUBJECT_TABLE, oldSubjectTable, newSubjectTable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubjectTable(DB2Table newSubjectTable) {
		if (newSubjectTable != subjectTable) {
			NotificationChain msgs = null;
			if (subjectTable != null)
				msgs = ((InternalEObject)subjectTable).eInverseRemove(this, DB2ModelPackage.DB2_TABLE__MASKS, DB2Table.class, msgs);
			if (newSubjectTable != null)
				msgs = ((InternalEObject)newSubjectTable).eInverseAdd(this, DB2ModelPackage.DB2_TABLE__MASKS, DB2Table.class, msgs);
			msgs = basicSetSubjectTable(newSubjectTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__SUBJECT_TABLE, newSubjectTable, newSubjectTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2MaterializedQueryTable getSubjectMQT() {
		if (subjectMQT != null && subjectMQT.eIsProxy()) {
			InternalEObject oldSubjectMQT = (InternalEObject)subjectMQT;
			subjectMQT = (DB2MaterializedQueryTable)eResolveProxy(oldSubjectMQT);
			if (subjectMQT != oldSubjectMQT) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_MASK__SUBJECT_MQT, oldSubjectMQT, subjectMQT));
			}
		}
		return subjectMQT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2MaterializedQueryTable basicGetSubjectMQT() {
		return subjectMQT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSubjectMQT(DB2MaterializedQueryTable newSubjectMQT, NotificationChain msgs) {
		DB2MaterializedQueryTable oldSubjectMQT = subjectMQT;
		subjectMQT = newSubjectMQT;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__SUBJECT_MQT, oldSubjectMQT, newSubjectMQT);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubjectMQT(DB2MaterializedQueryTable newSubjectMQT) {
		if (newSubjectMQT != subjectMQT) {
			NotificationChain msgs = null;
			if (subjectMQT != null)
				msgs = ((InternalEObject)subjectMQT).eInverseRemove(this, DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS, DB2MaterializedQueryTable.class, msgs);
			if (newSubjectMQT != null)
				msgs = ((InternalEObject)newSubjectMQT).eInverseAdd(this, DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS, DB2MaterializedQueryTable.class, msgs);
			msgs = basicSetSubjectMQT(newSubjectMQT, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__SUBJECT_MQT, newSubjectMQT, newSubjectMQT));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Column getSubjectColumn() {
		if (subjectColumn != null && subjectColumn.eIsProxy()) {
			InternalEObject oldSubjectColumn = (InternalEObject)subjectColumn;
			subjectColumn = (DB2Column)eResolveProxy(oldSubjectColumn);
			if (subjectColumn != oldSubjectColumn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_MASK__SUBJECT_COLUMN, oldSubjectColumn, subjectColumn));
			}
		}
		return subjectColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Column basicGetSubjectColumn() {
		return subjectColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubjectColumn(DB2Column newSubjectColumn) {
		DB2Column oldSubjectColumn = subjectColumn;
		subjectColumn = newSubjectColumn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MASK__SUBJECT_COLUMN, oldSubjectColumn, subjectColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_MASK__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__MASKS, DB2Schema.class, msgs);
				return basicSetSchema((DB2Schema)otherEnd, msgs);
			case DB2ModelPackage.DB2_MASK__SUBJECT_TABLE:
				if (subjectTable != null)
					msgs = ((InternalEObject)subjectTable).eInverseRemove(this, DB2ModelPackage.DB2_TABLE__MASKS, DB2Table.class, msgs);
				return basicSetSubjectTable((DB2Table)otherEnd, msgs);
			case DB2ModelPackage.DB2_MASK__SUBJECT_MQT:
				if (subjectMQT != null)
					msgs = ((InternalEObject)subjectMQT).eInverseRemove(this, DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS, DB2MaterializedQueryTable.class, msgs);
				return basicSetSubjectMQT((DB2MaterializedQueryTable)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_MASK__CASE_EXPRESSION:
				return basicSetCaseExpression(null, msgs);
			case DB2ModelPackage.DB2_MASK__SCHEMA:
				return basicSetSchema(null, msgs);
			case DB2ModelPackage.DB2_MASK__SUBJECT_TABLE:
				return basicSetSubjectTable(null, msgs);
			case DB2ModelPackage.DB2_MASK__SUBJECT_MQT:
				return basicSetSubjectMQT(null, msgs);
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
			case DB2ModelPackage.DB2_MASK__CORRELATION_NAME:
				return getCorrelationName();
			case DB2ModelPackage.DB2_MASK__CASE_EXPRESSION:
				return getCaseExpression();
			case DB2ModelPackage.DB2_MASK__ENABLE:
				return isEnable() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_MASK__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case DB2ModelPackage.DB2_MASK__SUBJECT_TABLE:
				if (resolve) return getSubjectTable();
				return basicGetSubjectTable();
			case DB2ModelPackage.DB2_MASK__SUBJECT_MQT:
				if (resolve) return getSubjectMQT();
				return basicGetSubjectMQT();
			case DB2ModelPackage.DB2_MASK__SUBJECT_COLUMN:
				if (resolve) return getSubjectColumn();
				return basicGetSubjectColumn();
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
			case DB2ModelPackage.DB2_MASK__CORRELATION_NAME:
				setCorrelationName((String)newValue);
				return;
			case DB2ModelPackage.DB2_MASK__CASE_EXPRESSION:
				setCaseExpression((QueryExpression)newValue);
				return;
			case DB2ModelPackage.DB2_MASK__ENABLE:
				setEnable(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_MASK__SCHEMA:
				setSchema((DB2Schema)newValue);
				return;
			case DB2ModelPackage.DB2_MASK__SUBJECT_TABLE:
				setSubjectTable((DB2Table)newValue);
				return;
			case DB2ModelPackage.DB2_MASK__SUBJECT_MQT:
				setSubjectMQT((DB2MaterializedQueryTable)newValue);
				return;
			case DB2ModelPackage.DB2_MASK__SUBJECT_COLUMN:
				setSubjectColumn((DB2Column)newValue);
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
			case DB2ModelPackage.DB2_MASK__CORRELATION_NAME:
				setCorrelationName(CORRELATION_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_MASK__CASE_EXPRESSION:
				setCaseExpression((QueryExpression)null);
				return;
			case DB2ModelPackage.DB2_MASK__ENABLE:
				setEnable(ENABLE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_MASK__SCHEMA:
				setSchema((DB2Schema)null);
				return;
			case DB2ModelPackage.DB2_MASK__SUBJECT_TABLE:
				setSubjectTable((DB2Table)null);
				return;
			case DB2ModelPackage.DB2_MASK__SUBJECT_MQT:
				setSubjectMQT((DB2MaterializedQueryTable)null);
				return;
			case DB2ModelPackage.DB2_MASK__SUBJECT_COLUMN:
				setSubjectColumn((DB2Column)null);
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
			case DB2ModelPackage.DB2_MASK__CORRELATION_NAME:
				return CORRELATION_NAME_EDEFAULT == null ? correlationName != null : !CORRELATION_NAME_EDEFAULT.equals(correlationName);
			case DB2ModelPackage.DB2_MASK__CASE_EXPRESSION:
				return caseExpression != null;
			case DB2ModelPackage.DB2_MASK__ENABLE:
				return enable != ENABLE_EDEFAULT;
			case DB2ModelPackage.DB2_MASK__SCHEMA:
				return schema != null;
			case DB2ModelPackage.DB2_MASK__SUBJECT_TABLE:
				return subjectTable != null;
			case DB2ModelPackage.DB2_MASK__SUBJECT_MQT:
				return subjectMQT != null;
			case DB2ModelPackage.DB2_MASK__SUBJECT_COLUMN:
				return subjectColumn != null;
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
		result.append(" (correlationName: "); //$NON-NLS-1$
		result.append(correlationName);
		result.append(", enable: "); //$NON-NLS-1$
		result.append(enable);
		result.append(')');
		return result.toString();
	}

} //DB2MaskImpl
