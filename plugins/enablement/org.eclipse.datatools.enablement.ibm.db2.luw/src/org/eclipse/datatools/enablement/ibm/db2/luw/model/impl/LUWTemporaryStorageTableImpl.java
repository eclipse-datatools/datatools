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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Temporary Storage Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryStorageTableImpl#getPartitionKey <em>Partition Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryStorageTableImpl#getUserTemporaryTableSpace <em>User Temporary Table Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWTemporaryStorageTableImpl extends EObjectImpl implements LUWTemporaryStorageTable {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWTemporaryStorageTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_TEMPORARY_STORAGE_TABLE;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY, oldPartitionKey, newPartitionKey);
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY, newPartitionKey, newPartitionKey));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE, oldUserTemporaryTableSpace, userTemporaryTableSpace));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE, oldUserTemporaryTableSpace, newUserTemporaryTableSpace);
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE, newUserTemporaryTableSpace, newUserTemporaryTableSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY:
				if (partitionKey != null)
					msgs = ((InternalEObject)partitionKey).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY, null, msgs);
				return basicSetPartitionKey((LUWPartitionKey)otherEnd, msgs);
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE:
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
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY:
				return basicSetPartitionKey(null, msgs);
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE:
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
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY:
				return getPartitionKey();
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE:
				if (resolve) return getUserTemporaryTableSpace();
				return basicGetUserTemporaryTableSpace();
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
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY:
				setPartitionKey((LUWPartitionKey)newValue);
				return;
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE:
				setUserTemporaryTableSpace((LUWTableSpace)newValue);
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
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY:
				setPartitionKey((LUWPartitionKey)null);
				return;
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE:
				setUserTemporaryTableSpace((LUWTableSpace)null);
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
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY:
				return partitionKey != null;
			case LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE:
				return userTemporaryTableSpace != null;
		}
		return super.eIsSet(featureID);
	}

} //LUWTemporaryStorageTableImpl
