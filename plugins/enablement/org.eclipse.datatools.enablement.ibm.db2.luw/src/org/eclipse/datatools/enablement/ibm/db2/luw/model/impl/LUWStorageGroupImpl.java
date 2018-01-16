/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Storage Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl#getStoragePaths <em>Storage Paths</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl#getOverhead <em>Overhead</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl#getDeviceReadRate <em>Device Read Rate</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl#getDataTag <em>Data Tag</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl#isDefault <em>Default</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl#getTableSpaces <em>Table Spaces</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWStorageGroupImpl extends SQLObjectImpl implements LUWStorageGroup {
	/**
	 * The cached value of the '{@link #getStoragePaths() <em>Storage Paths</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStoragePaths()
	 * @generated
	 * @ordered
	 */
	protected EList storagePaths;

	/**
	 * The default value of the '{@link #getOverhead() <em>Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverhead()
	 * @generated
	 * @ordered
	 */
	protected static final double OVERHEAD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getOverhead() <em>Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverhead()
	 * @generated
	 * @ordered
	 */
	protected double overhead = OVERHEAD_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeviceReadRate() <em>Device Read Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceReadRate()
	 * @generated
	 * @ordered
	 */
	protected static final double DEVICE_READ_RATE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getDeviceReadRate() <em>Device Read Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceReadRate()
	 * @generated
	 * @ordered
	 */
	protected double deviceReadRate = DEVICE_READ_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataTag() <em>Data Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataTag()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_TAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataTag() <em>Data Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataTag()
	 * @generated
	 * @ordered
	 */
	protected String dataTag = DATA_TAG_EDEFAULT;

	/**
	 * The default value of the '{@link #isDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefault()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFAULT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefault()
	 * @generated
	 * @ordered
	 */
	protected boolean default_ = DEFAULT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected LUWDatabase database;

	/**
	 * The cached value of the '{@link #getTableSpaces() <em>Table Spaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableSpaces()
	 * @generated
	 * @ordered
	 */
	protected EList tableSpaces;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWStorageGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_STORAGE_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getStoragePaths() {
		if (storagePaths == null) {
			storagePaths = new EDataTypeUniqueEList(String.class, this, LUWPackage.LUW_STORAGE_GROUP__STORAGE_PATHS);
		}
		return storagePaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getOverhead() {
		return overhead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverhead(double newOverhead) {
		double oldOverhead = overhead;
		overhead = newOverhead;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_STORAGE_GROUP__OVERHEAD, oldOverhead, overhead));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getDeviceReadRate() {
		return deviceReadRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeviceReadRate(double newDeviceReadRate) {
		double oldDeviceReadRate = deviceReadRate;
		deviceReadRate = newDeviceReadRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_STORAGE_GROUP__DEVICE_READ_RATE, oldDeviceReadRate, deviceReadRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataTag() {
		return dataTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataTag(String newDataTag) {
		String oldDataTag = dataTag;
		dataTag = newDataTag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_STORAGE_GROUP__DATA_TAG, oldDataTag, dataTag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefault() {
		return default_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefault(boolean newDefault) {
		boolean oldDefault = default_;
		default_ = newDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_STORAGE_GROUP__DEFAULT, oldDefault, default_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabase getDatabase() {
		if (database != null && database.eIsProxy()) {
			InternalEObject oldDatabase = (InternalEObject)database;
			database = (LUWDatabase)eResolveProxy(oldDatabase);
			if (database != oldDatabase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_STORAGE_GROUP__DATABASE, oldDatabase, database));
			}
		}
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabase basicGetDatabase() {
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDatabase(LUWDatabase newDatabase, NotificationChain msgs) {
		LUWDatabase oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_STORAGE_GROUP__DATABASE, oldDatabase, newDatabase);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(LUWDatabase newDatabase) {
		if (newDatabase != database) {
			NotificationChain msgs = null;
			if (database != null)
				msgs = ((InternalEObject)database).eInverseRemove(this, LUWPackage.LUW_DATABASE__STORAGE_GROUPS, LUWDatabase.class, msgs);
			if (newDatabase != null)
				msgs = ((InternalEObject)newDatabase).eInverseAdd(this, LUWPackage.LUW_DATABASE__STORAGE_GROUPS, LUWDatabase.class, msgs);
			msgs = basicSetDatabase(newDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_STORAGE_GROUP__DATABASE, newDatabase, newDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTableSpaces() {
		if (tableSpaces == null) {
			tableSpaces = new EObjectWithInverseResolvingEList(LUWTableSpace.class, this, LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES, LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP);
		}
		return tableSpaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_STORAGE_GROUP__DATABASE:
				if (database != null)
					msgs = ((InternalEObject)database).eInverseRemove(this, LUWPackage.LUW_DATABASE__STORAGE_GROUPS, LUWDatabase.class, msgs);
				return basicSetDatabase((LUWDatabase)otherEnd, msgs);
			case LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES:
				return ((InternalEList)getTableSpaces()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.LUW_STORAGE_GROUP__DATABASE:
				return basicSetDatabase(null, msgs);
			case LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES:
				return ((InternalEList)getTableSpaces()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_STORAGE_GROUP__STORAGE_PATHS:
				return getStoragePaths();
			case LUWPackage.LUW_STORAGE_GROUP__OVERHEAD:
				return new Double(getOverhead());
			case LUWPackage.LUW_STORAGE_GROUP__DEVICE_READ_RATE:
				return new Double(getDeviceReadRate());
			case LUWPackage.LUW_STORAGE_GROUP__DATA_TAG:
				return getDataTag();
			case LUWPackage.LUW_STORAGE_GROUP__DEFAULT:
				return isDefault() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_STORAGE_GROUP__DATABASE:
				if (resolve) return getDatabase();
				return basicGetDatabase();
			case LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES:
				return getTableSpaces();
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
			case LUWPackage.LUW_STORAGE_GROUP__STORAGE_PATHS:
				getStoragePaths().clear();
				getStoragePaths().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_STORAGE_GROUP__OVERHEAD:
				setOverhead(((Double)newValue).doubleValue());
				return;
			case LUWPackage.LUW_STORAGE_GROUP__DEVICE_READ_RATE:
				setDeviceReadRate(((Double)newValue).doubleValue());
				return;
			case LUWPackage.LUW_STORAGE_GROUP__DATA_TAG:
				setDataTag((String)newValue);
				return;
			case LUWPackage.LUW_STORAGE_GROUP__DEFAULT:
				setDefault(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_STORAGE_GROUP__DATABASE:
				setDatabase((LUWDatabase)newValue);
				return;
			case LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES:
				getTableSpaces().clear();
				getTableSpaces().addAll((Collection)newValue);
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
			case LUWPackage.LUW_STORAGE_GROUP__STORAGE_PATHS:
				getStoragePaths().clear();
				return;
			case LUWPackage.LUW_STORAGE_GROUP__OVERHEAD:
				setOverhead(OVERHEAD_EDEFAULT);
				return;
			case LUWPackage.LUW_STORAGE_GROUP__DEVICE_READ_RATE:
				setDeviceReadRate(DEVICE_READ_RATE_EDEFAULT);
				return;
			case LUWPackage.LUW_STORAGE_GROUP__DATA_TAG:
				setDataTag(DATA_TAG_EDEFAULT);
				return;
			case LUWPackage.LUW_STORAGE_GROUP__DEFAULT:
				setDefault(DEFAULT_EDEFAULT);
				return;
			case LUWPackage.LUW_STORAGE_GROUP__DATABASE:
				setDatabase((LUWDatabase)null);
				return;
			case LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES:
				getTableSpaces().clear();
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
			case LUWPackage.LUW_STORAGE_GROUP__STORAGE_PATHS:
				return storagePaths != null && !storagePaths.isEmpty();
			case LUWPackage.LUW_STORAGE_GROUP__OVERHEAD:
				return overhead != OVERHEAD_EDEFAULT;
			case LUWPackage.LUW_STORAGE_GROUP__DEVICE_READ_RATE:
				return deviceReadRate != DEVICE_READ_RATE_EDEFAULT;
			case LUWPackage.LUW_STORAGE_GROUP__DATA_TAG:
				return DATA_TAG_EDEFAULT == null ? dataTag != null : !DATA_TAG_EDEFAULT.equals(dataTag);
			case LUWPackage.LUW_STORAGE_GROUP__DEFAULT:
				return default_ != DEFAULT_EDEFAULT;
			case LUWPackage.LUW_STORAGE_GROUP__DATABASE:
				return database != null;
			case LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES:
				return tableSpaces != null && !tableSpaces.isEmpty();
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
		result.append(" (storagePaths: "); //$NON-NLS-1$
		result.append(storagePaths);
		result.append(", overhead: "); //$NON-NLS-1$
		result.append(overhead);
		result.append(", deviceReadRate: "); //$NON-NLS-1$
		result.append(deviceReadRate);
		result.append(", dataTag: "); //$NON-NLS-1$
		result.append(dataTag);
		result.append(", default: "); //$NON-NLS-1$
		result.append(default_);
		result.append(')');
		return result.toString();
	}

} //LUWStorageGroupImpl
