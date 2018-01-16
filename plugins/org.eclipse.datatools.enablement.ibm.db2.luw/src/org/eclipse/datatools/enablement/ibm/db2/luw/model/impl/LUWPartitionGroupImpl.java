/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Partition Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionGroupImpl#getPartitions <em>Partitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionGroupImpl#getTableSpaces <em>Table Spaces</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionGroupImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionGroupImpl#getBufferPool <em>Buffer Pool</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWPartitionGroupImpl extends SQLObjectImpl implements LUWPartitionGroup {
	/**
	 * The cached value of the '{@link #getPartitions() <em>Partitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitions()
	 * @generated
	 * @ordered
	 */
	protected EList partitions;

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
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected LUWDatabase database;

	/**
	 * The cached value of the '{@link #getBufferPool() <em>Buffer Pool</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBufferPool()
	 * @generated
	 * @ordered
	 */
	protected EList bufferPool;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWPartitionGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_PARTITION_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartitions() {
		if (partitions == null) {
			partitions = new EObjectWithInverseResolvingEList(LUWDatabasePartition.class, this, LUWPackage.LUW_PARTITION_GROUP__PARTITIONS, LUWPackage.LUW_DATABASE_PARTITION__GROUP);
		}
		return partitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTableSpaces() {
		if (tableSpaces == null) {
			tableSpaces = new EObjectWithInverseResolvingEList(LUWTableSpace.class, this, LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES, LUWPackage.LUW_TABLE_SPACE__GROUP);
		}
		return tableSpaces;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_PARTITION_GROUP__DATABASE, oldDatabase, database));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_GROUP__DATABASE, oldDatabase, newDatabase);
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
				msgs = ((InternalEObject)database).eInverseRemove(this, LUWPackage.LUW_DATABASE__GROUPS, LUWDatabase.class, msgs);
			if (newDatabase != null)
				msgs = ((InternalEObject)newDatabase).eInverseAdd(this, LUWPackage.LUW_DATABASE__GROUPS, LUWDatabase.class, msgs);
			msgs = basicSetDatabase(newDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_GROUP__DATABASE, newDatabase, newDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getBufferPool() {
		if (bufferPool == null) {
			bufferPool = new EObjectWithInverseResolvingEList.ManyInverse(LUWBufferPool.class, this, LUWPackage.LUW_PARTITION_GROUP__BUFFER_POOL, LUWPackage.LUW_BUFFER_POOL__PARTITION_GROUP);
		}
		return bufferPool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_PARTITION_GROUP__PARTITIONS:
				return ((InternalEList)getPartitions()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES:
				return ((InternalEList)getTableSpaces()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_PARTITION_GROUP__DATABASE:
				if (database != null)
					msgs = ((InternalEObject)database).eInverseRemove(this, LUWPackage.LUW_DATABASE__GROUPS, LUWDatabase.class, msgs);
				return basicSetDatabase((LUWDatabase)otherEnd, msgs);
			case LUWPackage.LUW_PARTITION_GROUP__BUFFER_POOL:
				return ((InternalEList)getBufferPool()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.LUW_PARTITION_GROUP__PARTITIONS:
				return ((InternalEList)getPartitions()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES:
				return ((InternalEList)getTableSpaces()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_PARTITION_GROUP__DATABASE:
				return basicSetDatabase(null, msgs);
			case LUWPackage.LUW_PARTITION_GROUP__BUFFER_POOL:
				return ((InternalEList)getBufferPool()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_PARTITION_GROUP__PARTITIONS:
				return getPartitions();
			case LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES:
				return getTableSpaces();
			case LUWPackage.LUW_PARTITION_GROUP__DATABASE:
				if (resolve) return getDatabase();
				return basicGetDatabase();
			case LUWPackage.LUW_PARTITION_GROUP__BUFFER_POOL:
				return getBufferPool();
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
			case LUWPackage.LUW_PARTITION_GROUP__PARTITIONS:
				getPartitions().clear();
				getPartitions().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES:
				getTableSpaces().clear();
				getTableSpaces().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_PARTITION_GROUP__DATABASE:
				setDatabase((LUWDatabase)newValue);
				return;
			case LUWPackage.LUW_PARTITION_GROUP__BUFFER_POOL:
				getBufferPool().clear();
				getBufferPool().addAll((Collection)newValue);
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
			case LUWPackage.LUW_PARTITION_GROUP__PARTITIONS:
				getPartitions().clear();
				return;
			case LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES:
				getTableSpaces().clear();
				return;
			case LUWPackage.LUW_PARTITION_GROUP__DATABASE:
				setDatabase((LUWDatabase)null);
				return;
			case LUWPackage.LUW_PARTITION_GROUP__BUFFER_POOL:
				getBufferPool().clear();
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
			case LUWPackage.LUW_PARTITION_GROUP__PARTITIONS:
				return partitions != null && !partitions.isEmpty();
			case LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES:
				return tableSpaces != null && !tableSpaces.isEmpty();
			case LUWPackage.LUW_PARTITION_GROUP__DATABASE:
				return database != null;
			case LUWPackage.LUW_PARTITION_GROUP__BUFFER_POOL:
				return bufferPool != null && !bufferPool.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LUWPartitionGroupImpl
