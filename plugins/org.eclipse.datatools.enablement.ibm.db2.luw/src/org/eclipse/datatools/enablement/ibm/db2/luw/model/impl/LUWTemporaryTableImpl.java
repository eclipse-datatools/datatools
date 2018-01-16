/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption;

import org.eclipse.datatools.modelbase.sql.tables.Table;

import org.eclipse.datatools.modelbase.sql.tables.impl.TemporaryTableImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Temporary Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryTableImpl#getPartitionKey <em>Partition Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryTableImpl#getUserTemporaryTableSpace <em>User Temporary Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryTableImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryTableImpl#getLogOption <em>Log Option</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWTemporaryTableImpl extends TemporaryTableImpl implements LUWTemporaryTable {
	/**
	 * The cached value of the '{@link #getPartitionKey() <em>Partition Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionKey()
	 * @generated
	 * @ordered
	 */
	protected LUWPartitionKey partitionKey;

	/**
	 * The cached value of the '{@link #getUserTemporaryTableSpace() <em>User Temporary Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserTemporaryTableSpace()
	 * @generated
	 * @ordered
	 */
	protected LUWTableSpace userTemporaryTableSpace;

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
	 * The default value of the '{@link #getLogOption() <em>Log Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogOption()
	 * @generated
	 * @ordered
	 */
	protected static final LUWTemporaryTableLoggingOption LOG_OPTION_EDEFAULT = LUWTemporaryTableLoggingOption.NOT_LOGGED_DELETE_ROWS_LITERAL;

	/**
	 * The cached value of the '{@link #getLogOption() <em>Log Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogOption()
	 * @generated
	 * @ordered
	 */
	protected LUWTemporaryTableLoggingOption logOption = LOG_OPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWTemporaryTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_TEMPORARY_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionKey getPartitionKey() {
		return partitionKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPartitionKey(LUWPartitionKey newPartitionKey, NotificationChain msgs) {
		LUWPartitionKey oldPartitionKey = partitionKey;
		partitionKey = newPartitionKey;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY, oldPartitionKey, newPartitionKey);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartitionKey(LUWPartitionKey newPartitionKey) {
		if (newPartitionKey != partitionKey) {
			NotificationChain msgs = null;
			if (partitionKey != null)
				msgs = ((InternalEObject)partitionKey).eInverseRemove(this, LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE, LUWPartitionKey.class, msgs);
			if (newPartitionKey != null)
				msgs = ((InternalEObject)newPartitionKey).eInverseAdd(this, LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE, LUWPartitionKey.class, msgs);
			msgs = basicSetPartitionKey(newPartitionKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY, newPartitionKey, newPartitionKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace getUserTemporaryTableSpace() {
		if (userTemporaryTableSpace != null && userTemporaryTableSpace.eIsProxy()) {
			InternalEObject oldUserTemporaryTableSpace = (InternalEObject)userTemporaryTableSpace;
			userTemporaryTableSpace = (LUWTableSpace)eResolveProxy(oldUserTemporaryTableSpace);
			if (userTemporaryTableSpace != oldUserTemporaryTableSpace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE, oldUserTemporaryTableSpace, userTemporaryTableSpace));
			}
		}
		return userTemporaryTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace basicGetUserTemporaryTableSpace() {
		return userTemporaryTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUserTemporaryTableSpace(LUWTableSpace newUserTemporaryTableSpace, NotificationChain msgs) {
		LUWTableSpace oldUserTemporaryTableSpace = userTemporaryTableSpace;
		userTemporaryTableSpace = newUserTemporaryTableSpace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE, oldUserTemporaryTableSpace, newUserTemporaryTableSpace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserTemporaryTableSpace(LUWTableSpace newUserTemporaryTableSpace) {
		if (newUserTemporaryTableSpace != userTemporaryTableSpace) {
			NotificationChain msgs = null;
			if (userTemporaryTableSpace != null)
				msgs = ((InternalEObject)userTemporaryTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES, LUWTableSpace.class, msgs);
			if (newUserTemporaryTableSpace != null)
				msgs = ((InternalEObject)newUserTemporaryTableSpace).eInverseAdd(this, LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES, LUWTableSpace.class, msgs);
			msgs = basicSetUserTemporaryTableSpace(newUserTemporaryTableSpace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE, newUserTemporaryTableSpace, newUserTemporaryTableSpace));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_TEMPORARY_TABLE__TABLE, oldTable, table));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_TABLE__TABLE, oldTable, table));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTemporaryTableLoggingOption getLogOption() {
		return logOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLogOption(LUWTemporaryTableLoggingOption newLogOption) {
		LUWTemporaryTableLoggingOption oldLogOption = logOption;
		logOption = newLogOption == null ? LOG_OPTION_EDEFAULT : newLogOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_TABLE__LOG_OPTION, oldLogOption, logOption));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY:
				if (partitionKey != null)
					msgs = ((InternalEObject)partitionKey).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY, null, msgs);
				return basicSetPartitionKey((LUWPartitionKey)otherEnd, msgs);
			case LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE:
				if (userTemporaryTableSpace != null)
					msgs = ((InternalEObject)userTemporaryTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES, LUWTableSpace.class, msgs);
				return basicSetUserTemporaryTableSpace((LUWTableSpace)otherEnd, msgs);
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
			case LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY:
				return basicSetPartitionKey(null, msgs);
			case LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE:
				return basicSetUserTemporaryTableSpace(null, msgs);
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
			case LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY:
				return getPartitionKey();
			case LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE:
				if (resolve) return getUserTemporaryTableSpace();
				return basicGetUserTemporaryTableSpace();
			case LUWPackage.LUW_TEMPORARY_TABLE__TABLE:
				if (resolve) return getTable();
				return basicGetTable();
			case LUWPackage.LUW_TEMPORARY_TABLE__LOG_OPTION:
				return getLogOption();
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
			case LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY:
				setPartitionKey((LUWPartitionKey)newValue);
				return;
			case LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE:
				setUserTemporaryTableSpace((LUWTableSpace)newValue);
				return;
			case LUWPackage.LUW_TEMPORARY_TABLE__TABLE:
				setTable((Table)newValue);
				return;
			case LUWPackage.LUW_TEMPORARY_TABLE__LOG_OPTION:
				setLogOption((LUWTemporaryTableLoggingOption)newValue);
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
			case LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY:
				setPartitionKey((LUWPartitionKey)null);
				return;
			case LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE:
				setUserTemporaryTableSpace((LUWTableSpace)null);
				return;
			case LUWPackage.LUW_TEMPORARY_TABLE__TABLE:
				setTable((Table)null);
				return;
			case LUWPackage.LUW_TEMPORARY_TABLE__LOG_OPTION:
				setLogOption(LOG_OPTION_EDEFAULT);
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
			case LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY:
				return partitionKey != null;
			case LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE:
				return userTemporaryTableSpace != null;
			case LUWPackage.LUW_TEMPORARY_TABLE__TABLE:
				return table != null;
			case LUWPackage.LUW_TEMPORARY_TABLE__LOG_OPTION:
				return logOption != LOG_OPTION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == LUWTemporaryStorageTable.class) {
			switch (derivedFeatureID) {
				case LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY: return LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY;
				case LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE: return LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == LUWTemporaryStorageTable.class) {
			switch (baseFeatureID) {
				case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY: return LUWPackage.LUW_TEMPORARY_TABLE__PARTITION_KEY;
				case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE: return LUWPackage.LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (logOption: "); //$NON-NLS-1$
		result.append(logOption);
		result.append(')');
		return result.toString();
	}

} //LUWTemporaryTableImpl
