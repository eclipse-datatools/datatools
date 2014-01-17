/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Column;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Period;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2PeriodType;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Period</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PeriodImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PeriodImpl#getBeginColumn <em>Begin Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PeriodImpl#getEndColumn <em>End Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PeriodImpl#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2PeriodImpl extends EObjectImpl implements DB2Period {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final DB2PeriodType TYPE_EDEFAULT = DB2PeriodType.SYSTEM_TIME_LITERAL;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected DB2PeriodType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBeginColumn() <em>Begin Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBeginColumn()
	 * @generated
	 * @ordered
	 */
	protected DB2Column beginColumn;

	/**
	 * The cached value of the '{@link #getEndColumn() <em>End Column</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndColumn()
	 * @generated
	 * @ordered
	 */
	protected DB2Column endColumn;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2PeriodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_PERIOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2PeriodType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(DB2PeriodType newType) {
		DB2PeriodType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PERIOD__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Column getBeginColumn() {
		if (beginColumn != null && beginColumn.eIsProxy()) {
			InternalEObject oldBeginColumn = (InternalEObject)beginColumn;
			beginColumn = (DB2Column)eResolveProxy(oldBeginColumn);
			if (beginColumn != oldBeginColumn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN, oldBeginColumn, beginColumn));
			}
		}
		return beginColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Column basicGetBeginColumn() {
		return beginColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBeginColumn(DB2Column newBeginColumn, NotificationChain msgs) {
		DB2Column oldBeginColumn = beginColumn;
		beginColumn = newBeginColumn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN, oldBeginColumn, newBeginColumn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBeginColumn(DB2Column newBeginColumn) {
		if (newBeginColumn != beginColumn) {
			NotificationChain msgs = null;
			if (beginColumn != null)
				msgs = ((InternalEObject)beginColumn).eInverseRemove(this, DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD, DB2Column.class, msgs);
			if (newBeginColumn != null)
				msgs = ((InternalEObject)newBeginColumn).eInverseAdd(this, DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD, DB2Column.class, msgs);
			msgs = basicSetBeginColumn(newBeginColumn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN, newBeginColumn, newBeginColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Column getEndColumn() {
		if (endColumn != null && endColumn.eIsProxy()) {
			InternalEObject oldEndColumn = (InternalEObject)endColumn;
			endColumn = (DB2Column)eResolveProxy(oldEndColumn);
			if (endColumn != oldEndColumn) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_PERIOD__END_COLUMN, oldEndColumn, endColumn));
			}
		}
		return endColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Column basicGetEndColumn() {
		return endColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndColumn(DB2Column newEndColumn, NotificationChain msgs) {
		DB2Column oldEndColumn = endColumn;
		endColumn = newEndColumn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PERIOD__END_COLUMN, oldEndColumn, newEndColumn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndColumn(DB2Column newEndColumn) {
		if (newEndColumn != endColumn) {
			NotificationChain msgs = null;
			if (endColumn != null)
				msgs = ((InternalEObject)endColumn).eInverseRemove(this, DB2ModelPackage.DB2_COLUMN__END_PERIOD, DB2Column.class, msgs);
			if (newEndColumn != null)
				msgs = ((InternalEObject)newEndColumn).eInverseAdd(this, DB2ModelPackage.DB2_COLUMN__END_PERIOD, DB2Column.class, msgs);
			msgs = basicSetEndColumn(newEndColumn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PERIOD__END_COLUMN, newEndColumn, newEndColumn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Table getTable() {
		if (eContainerFeatureID() != DB2ModelPackage.DB2_PERIOD__TABLE) return null;
		return (DB2Table)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTable(DB2Table newTable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTable, DB2ModelPackage.DB2_PERIOD__TABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(DB2Table newTable) {
		if (newTable != eInternalContainer() || (eContainerFeatureID() != DB2ModelPackage.DB2_PERIOD__TABLE && newTable != null)) {
			if (EcoreUtil.isAncestor(this, newTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, DB2ModelPackage.DB2_TABLE__PERIODS, DB2Table.class, msgs);
			msgs = basicSetTable(newTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PERIOD__TABLE, newTable, newTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN:
				if (beginColumn != null)
					msgs = ((InternalEObject)beginColumn).eInverseRemove(this, DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD, DB2Column.class, msgs);
				return basicSetBeginColumn((DB2Column)otherEnd, msgs);
			case DB2ModelPackage.DB2_PERIOD__END_COLUMN:
				if (endColumn != null)
					msgs = ((InternalEObject)endColumn).eInverseRemove(this, DB2ModelPackage.DB2_COLUMN__END_PERIOD, DB2Column.class, msgs);
				return basicSetEndColumn((DB2Column)otherEnd, msgs);
			case DB2ModelPackage.DB2_PERIOD__TABLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTable((DB2Table)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN:
				return basicSetBeginColumn(null, msgs);
			case DB2ModelPackage.DB2_PERIOD__END_COLUMN:
				return basicSetEndColumn(null, msgs);
			case DB2ModelPackage.DB2_PERIOD__TABLE:
				return basicSetTable(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case DB2ModelPackage.DB2_PERIOD__TABLE:
				return eInternalContainer().eInverseRemove(this, DB2ModelPackage.DB2_TABLE__PERIODS, DB2Table.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_PERIOD__TYPE:
				return getType();
			case DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN:
				if (resolve) return getBeginColumn();
				return basicGetBeginColumn();
			case DB2ModelPackage.DB2_PERIOD__END_COLUMN:
				if (resolve) return getEndColumn();
				return basicGetEndColumn();
			case DB2ModelPackage.DB2_PERIOD__TABLE:
				return getTable();
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
			case DB2ModelPackage.DB2_PERIOD__TYPE:
				setType((DB2PeriodType)newValue);
				return;
			case DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN:
				setBeginColumn((DB2Column)newValue);
				return;
			case DB2ModelPackage.DB2_PERIOD__END_COLUMN:
				setEndColumn((DB2Column)newValue);
				return;
			case DB2ModelPackage.DB2_PERIOD__TABLE:
				setTable((DB2Table)newValue);
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
			case DB2ModelPackage.DB2_PERIOD__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN:
				setBeginColumn((DB2Column)null);
				return;
			case DB2ModelPackage.DB2_PERIOD__END_COLUMN:
				setEndColumn((DB2Column)null);
				return;
			case DB2ModelPackage.DB2_PERIOD__TABLE:
				setTable((DB2Table)null);
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
			case DB2ModelPackage.DB2_PERIOD__TYPE:
				return type != TYPE_EDEFAULT;
			case DB2ModelPackage.DB2_PERIOD__BEGIN_COLUMN:
				return beginColumn != null;
			case DB2ModelPackage.DB2_PERIOD__END_COLUMN:
				return endColumn != null;
			case DB2ModelPackage.DB2_PERIOD__TABLE:
				return getTable() != null;
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
		result.append(" (type: "); //$NON-NLS-1$
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //DB2PeriodImpl
