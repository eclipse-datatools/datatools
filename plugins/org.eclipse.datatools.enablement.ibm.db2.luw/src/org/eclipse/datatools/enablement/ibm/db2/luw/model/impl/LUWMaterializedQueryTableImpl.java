/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWMaterializedQueryTableImpl.java,v 1.13 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMaterializedQueryTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Materialized Query Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#isValueCompression <em>Value Compression</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#isRowCompression <em>Row Compression</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#isRowCompressionEmpty <em>Row Compression Empty</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#getCompressionMode <em>Compression Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#getPartitionKey <em>Partition Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#getIndexDataTableSpace <em>Index Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#getLOBDataTableSpace <em>LOB Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#getRegularDataTableSpace <em>Regular Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#getDataPartitions <em>Data Partitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl#getDataPartitionKey <em>Data Partition Key</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWMaterializedQueryTableImpl extends DB2MaterializedQueryTableImpl implements LUWMaterializedQueryTable {
	/**
	 * The default value of the '{@link #isValueCompression() <em>Value Compression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValueCompression()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VALUE_COMPRESSION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isValueCompression() <em>Value Compression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValueCompression()
	 * @generated
	 * @ordered
	 */
	protected boolean valueCompression = VALUE_COMPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isRowCompression() <em>Row Compression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowCompression()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROW_COMPRESSION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRowCompression() <em>Row Compression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowCompression()
	 * @generated
	 * @ordered
	 */
	protected boolean rowCompression = ROW_COMPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isRowCompressionEmpty() <em>Row Compression Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowCompressionEmpty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROW_COMPRESSION_EMPTY_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isRowCompressionEmpty() <em>Row Compression Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowCompressionEmpty()
	 * @generated
	 * @ordered
	 */
	protected boolean rowCompressionEmpty = ROW_COMPRESSION_EMPTY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompressionMode() <em>Compression Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompressionMode()
	 * @generated
	 * @ordered
	 */
	protected static final LUWStorageTableCompressionMode COMPRESSION_MODE_EDEFAULT = LUWStorageTableCompressionMode.NO_SELECTION_LITERAL;

	/**
	 * The cached value of the '{@link #getCompressionMode() <em>Compression Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompressionMode()
	 * @generated
	 * @ordered
	 */
	protected LUWStorageTableCompressionMode compressionMode = COMPRESSION_MODE_EDEFAULT;

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
	 * The cached value of the '{@link #getIndexDataTableSpace() <em>Index Data Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexDataTableSpace()
	 * @generated
	 * @ordered
	 */
	protected LUWTableSpace indexDataTableSpace;

	/**
	 * The cached value of the '{@link #getLOBDataTableSpace() <em>LOB Data Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLOBDataTableSpace()
	 * @generated
	 * @ordered
	 */
	protected LUWTableSpace lobDataTableSpace;

	/**
	 * The cached value of the '{@link #getRegularDataTableSpace() <em>Regular Data Table Space</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegularDataTableSpace()
	 * @generated
	 * @ordered
	 */
	protected LUWTableSpace regularDataTableSpace;

	/**
	 * The cached value of the '{@link #getDataPartitions() <em>Data Partitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataPartitions()
	 * @generated
	 * @ordered
	 */
	protected EList dataPartitions;

	/**
	 * The cached value of the '{@link #getDataPartitionKey() <em>Data Partition Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataPartitionKey()
	 * @generated
	 * @ordered
	 */
	protected LUWDataPartitionKey dataPartitionKey;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWMaterializedQueryTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_MATERIALIZED_QUERY_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isValueCompression() {
		return valueCompression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueCompression(boolean newValueCompression) {
		boolean oldValueCompression = valueCompression;
		valueCompression = newValueCompression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__VALUE_COMPRESSION, oldValueCompression, valueCompression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRowCompression() {
		return rowCompression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRowCompression(boolean newRowCompression) {
		boolean oldRowCompression = rowCompression;
		rowCompression = newRowCompression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION, oldRowCompression, rowCompression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRowCompressionEmpty() {
		return rowCompressionEmpty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRowCompressionEmpty(boolean newRowCompressionEmpty) {
		boolean oldRowCompressionEmpty = rowCompressionEmpty;
		rowCompressionEmpty = newRowCompressionEmpty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION_EMPTY, oldRowCompressionEmpty, rowCompressionEmpty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageTableCompressionMode getCompressionMode() {
		return compressionMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompressionMode(LUWStorageTableCompressionMode newCompressionMode) {
		LUWStorageTableCompressionMode oldCompressionMode = compressionMode;
		compressionMode = newCompressionMode == null ? COMPRESSION_MODE_EDEFAULT : newCompressionMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__COMPRESSION_MODE, oldCompressionMode, compressionMode));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY, oldPartitionKey, newPartitionKey);
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
				msgs = ((InternalEObject)partitionKey).eInverseRemove(this, LUWPackage.LUW_PARTITION_KEY__TABLE, LUWPartitionKey.class, msgs);
			if (newPartitionKey != null)
				msgs = ((InternalEObject)newPartitionKey).eInverseAdd(this, LUWPackage.LUW_PARTITION_KEY__TABLE, LUWPartitionKey.class, msgs);
			msgs = basicSetPartitionKey(newPartitionKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY, newPartitionKey, newPartitionKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace getIndexDataTableSpace() {
		if (indexDataTableSpace != null && indexDataTableSpace.eIsProxy()) {
			InternalEObject oldIndexDataTableSpace = (InternalEObject)indexDataTableSpace;
			indexDataTableSpace = (LUWTableSpace)eResolveProxy(oldIndexDataTableSpace);
			if (indexDataTableSpace != oldIndexDataTableSpace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE, oldIndexDataTableSpace, indexDataTableSpace));
			}
		}
		return indexDataTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace basicGetIndexDataTableSpace() {
		return indexDataTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIndexDataTableSpace(LUWTableSpace newIndexDataTableSpace, NotificationChain msgs) {
		LUWTableSpace oldIndexDataTableSpace = indexDataTableSpace;
		indexDataTableSpace = newIndexDataTableSpace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE, oldIndexDataTableSpace, newIndexDataTableSpace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexDataTableSpace(LUWTableSpace newIndexDataTableSpace) {
		if (newIndexDataTableSpace != indexDataTableSpace) {
			NotificationChain msgs = null;
			if (indexDataTableSpace != null)
				msgs = ((InternalEObject)indexDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES, LUWTableSpace.class, msgs);
			if (newIndexDataTableSpace != null)
				msgs = ((InternalEObject)newIndexDataTableSpace).eInverseAdd(this, LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES, LUWTableSpace.class, msgs);
			msgs = basicSetIndexDataTableSpace(newIndexDataTableSpace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE, newIndexDataTableSpace, newIndexDataTableSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace getLOBDataTableSpace() {
		if (lobDataTableSpace != null && lobDataTableSpace.eIsProxy()) {
			InternalEObject oldLOBDataTableSpace = (InternalEObject)lobDataTableSpace;
			lobDataTableSpace = (LUWTableSpace)eResolveProxy(oldLOBDataTableSpace);
			if (lobDataTableSpace != oldLOBDataTableSpace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE, oldLOBDataTableSpace, lobDataTableSpace));
			}
		}
		return lobDataTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace basicGetLOBDataTableSpace() {
		return lobDataTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLOBDataTableSpace(LUWTableSpace newLOBDataTableSpace, NotificationChain msgs) {
		LUWTableSpace oldLOBDataTableSpace = lobDataTableSpace;
		lobDataTableSpace = newLOBDataTableSpace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE, oldLOBDataTableSpace, newLOBDataTableSpace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLOBDataTableSpace(LUWTableSpace newLOBDataTableSpace) {
		if (newLOBDataTableSpace != lobDataTableSpace) {
			NotificationChain msgs = null;
			if (lobDataTableSpace != null)
				msgs = ((InternalEObject)lobDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES, LUWTableSpace.class, msgs);
			if (newLOBDataTableSpace != null)
				msgs = ((InternalEObject)newLOBDataTableSpace).eInverseAdd(this, LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES, LUWTableSpace.class, msgs);
			msgs = basicSetLOBDataTableSpace(newLOBDataTableSpace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE, newLOBDataTableSpace, newLOBDataTableSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace getRegularDataTableSpace() {
		if (regularDataTableSpace != null && regularDataTableSpace.eIsProxy()) {
			InternalEObject oldRegularDataTableSpace = (InternalEObject)regularDataTableSpace;
			regularDataTableSpace = (LUWTableSpace)eResolveProxy(oldRegularDataTableSpace);
			if (regularDataTableSpace != oldRegularDataTableSpace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE, oldRegularDataTableSpace, regularDataTableSpace));
			}
		}
		return regularDataTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace basicGetRegularDataTableSpace() {
		return regularDataTableSpace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRegularDataTableSpace(LUWTableSpace newRegularDataTableSpace, NotificationChain msgs) {
		LUWTableSpace oldRegularDataTableSpace = regularDataTableSpace;
		regularDataTableSpace = newRegularDataTableSpace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE, oldRegularDataTableSpace, newRegularDataTableSpace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRegularDataTableSpace(LUWTableSpace newRegularDataTableSpace) {
		if (newRegularDataTableSpace != regularDataTableSpace) {
			NotificationChain msgs = null;
			if (regularDataTableSpace != null)
				msgs = ((InternalEObject)regularDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES, LUWTableSpace.class, msgs);
			if (newRegularDataTableSpace != null)
				msgs = ((InternalEObject)newRegularDataTableSpace).eInverseAdd(this, LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES, LUWTableSpace.class, msgs);
			msgs = basicSetRegularDataTableSpace(newRegularDataTableSpace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE, newRegularDataTableSpace, newRegularDataTableSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDataPartitions() {
		if (dataPartitions == null) {
			dataPartitions = new EObjectContainmentWithInverseEList(LUWDataPartition.class, this, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS, LUWPackage.LUW_DATA_PARTITION__TABLE);
		}
		return dataPartitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDataPartitionKey getDataPartitionKey() {
		return dataPartitionKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataPartitionKey(LUWDataPartitionKey newDataPartitionKey, NotificationChain msgs) {
		LUWDataPartitionKey oldDataPartitionKey = dataPartitionKey;
		dataPartitionKey = newDataPartitionKey;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY, oldDataPartitionKey, newDataPartitionKey);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataPartitionKey(LUWDataPartitionKey newDataPartitionKey) {
		if (newDataPartitionKey != dataPartitionKey) {
			NotificationChain msgs = null;
			if (dataPartitionKey != null)
				msgs = ((InternalEObject)dataPartitionKey).eInverseRemove(this, LUWPackage.LUW_DATA_PARTITION_KEY__TABLE, LUWDataPartitionKey.class, msgs);
			if (newDataPartitionKey != null)
				msgs = ((InternalEObject)newDataPartitionKey).eInverseAdd(this, LUWPackage.LUW_DATA_PARTITION_KEY__TABLE, LUWDataPartitionKey.class, msgs);
			msgs = basicSetDataPartitionKey(newDataPartitionKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY, newDataPartitionKey, newDataPartitionKey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getTableSpaces() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY:
				if (partitionKey != null)
					msgs = ((InternalEObject)partitionKey).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY, null, msgs);
				return basicSetPartitionKey((LUWPartitionKey)otherEnd, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE:
				if (indexDataTableSpace != null)
					msgs = ((InternalEObject)indexDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES, LUWTableSpace.class, msgs);
				return basicSetIndexDataTableSpace((LUWTableSpace)otherEnd, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE:
				if (lobDataTableSpace != null)
					msgs = ((InternalEObject)lobDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES, LUWTableSpace.class, msgs);
				return basicSetLOBDataTableSpace((LUWTableSpace)otherEnd, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE:
				if (regularDataTableSpace != null)
					msgs = ((InternalEObject)regularDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES, LUWTableSpace.class, msgs);
				return basicSetRegularDataTableSpace((LUWTableSpace)otherEnd, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS:
				return ((InternalEList)getDataPartitions()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY:
				if (dataPartitionKey != null)
					msgs = ((InternalEObject)dataPartitionKey).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY, null, msgs);
				return basicSetDataPartitionKey((LUWDataPartitionKey)otherEnd, msgs);
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
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY:
				return basicSetPartitionKey(null, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE:
				return basicSetIndexDataTableSpace(null, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE:
				return basicSetLOBDataTableSpace(null, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE:
				return basicSetRegularDataTableSpace(null, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS:
				return ((InternalEList)getDataPartitions()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY:
				return basicSetDataPartitionKey(null, msgs);
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
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__VALUE_COMPRESSION:
				return isValueCompression() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION:
				return isRowCompression() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION_EMPTY:
				return isRowCompressionEmpty() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__COMPRESSION_MODE:
				return getCompressionMode();
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY:
				return getPartitionKey();
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE:
				if (resolve) return getIndexDataTableSpace();
				return basicGetIndexDataTableSpace();
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE:
				if (resolve) return getLOBDataTableSpace();
				return basicGetLOBDataTableSpace();
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE:
				if (resolve) return getRegularDataTableSpace();
				return basicGetRegularDataTableSpace();
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS:
				return getDataPartitions();
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY:
				return getDataPartitionKey();
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
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__VALUE_COMPRESSION:
				setValueCompression(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION:
				setRowCompression(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION_EMPTY:
				setRowCompressionEmpty(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__COMPRESSION_MODE:
				setCompressionMode((LUWStorageTableCompressionMode)newValue);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY:
				setPartitionKey((LUWPartitionKey)newValue);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE:
				setIndexDataTableSpace((LUWTableSpace)newValue);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE:
				setLOBDataTableSpace((LUWTableSpace)newValue);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE:
				setRegularDataTableSpace((LUWTableSpace)newValue);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS:
				getDataPartitions().clear();
				getDataPartitions().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY:
				setDataPartitionKey((LUWDataPartitionKey)newValue);
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
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__VALUE_COMPRESSION:
				setValueCompression(VALUE_COMPRESSION_EDEFAULT);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION:
				setRowCompression(ROW_COMPRESSION_EDEFAULT);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION_EMPTY:
				setRowCompressionEmpty(ROW_COMPRESSION_EMPTY_EDEFAULT);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__COMPRESSION_MODE:
				setCompressionMode(COMPRESSION_MODE_EDEFAULT);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY:
				setPartitionKey((LUWPartitionKey)null);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE:
				setIndexDataTableSpace((LUWTableSpace)null);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE:
				setLOBDataTableSpace((LUWTableSpace)null);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE:
				setRegularDataTableSpace((LUWTableSpace)null);
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS:
				getDataPartitions().clear();
				return;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY:
				setDataPartitionKey((LUWDataPartitionKey)null);
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
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__VALUE_COMPRESSION:
				return valueCompression != VALUE_COMPRESSION_EDEFAULT;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION:
				return rowCompression != ROW_COMPRESSION_EDEFAULT;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION_EMPTY:
				return rowCompressionEmpty != ROW_COMPRESSION_EMPTY_EDEFAULT;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__COMPRESSION_MODE:
				return compressionMode != COMPRESSION_MODE_EDEFAULT;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY:
				return partitionKey != null;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE:
				return indexDataTableSpace != null;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE:
				return lobDataTableSpace != null;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE:
				return regularDataTableSpace != null;
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS:
				return dataPartitions != null && !dataPartitions.isEmpty();
			case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY:
				return dataPartitionKey != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == LUWStorageTable.class) {
			switch (derivedFeatureID) {
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__VALUE_COMPRESSION: return LUWPackage.LUW_STORAGE_TABLE__VALUE_COMPRESSION;
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION: return LUWPackage.LUW_STORAGE_TABLE__ROW_COMPRESSION;
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION_EMPTY: return LUWPackage.LUW_STORAGE_TABLE__ROW_COMPRESSION_EMPTY;
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__COMPRESSION_MODE: return LUWPackage.LUW_STORAGE_TABLE__COMPRESSION_MODE;
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY: return LUWPackage.LUW_STORAGE_TABLE__PARTITION_KEY;
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE: return LUWPackage.LUW_STORAGE_TABLE__INDEX_DATA_TABLE_SPACE;
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE: return LUWPackage.LUW_STORAGE_TABLE__LOB_DATA_TABLE_SPACE;
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE: return LUWPackage.LUW_STORAGE_TABLE__REGULAR_DATA_TABLE_SPACE;
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS: return LUWPackage.LUW_STORAGE_TABLE__DATA_PARTITIONS;
				case LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY: return LUWPackage.LUW_STORAGE_TABLE__DATA_PARTITION_KEY;
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
		if (baseClass == LUWStorageTable.class) {
			switch (baseFeatureID) {
				case LUWPackage.LUW_STORAGE_TABLE__VALUE_COMPRESSION: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__VALUE_COMPRESSION;
				case LUWPackage.LUW_STORAGE_TABLE__ROW_COMPRESSION: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION;
				case LUWPackage.LUW_STORAGE_TABLE__ROW_COMPRESSION_EMPTY: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION_EMPTY;
				case LUWPackage.LUW_STORAGE_TABLE__COMPRESSION_MODE: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__COMPRESSION_MODE;
				case LUWPackage.LUW_STORAGE_TABLE__PARTITION_KEY: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY;
				case LUWPackage.LUW_STORAGE_TABLE__INDEX_DATA_TABLE_SPACE: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE;
				case LUWPackage.LUW_STORAGE_TABLE__LOB_DATA_TABLE_SPACE: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE;
				case LUWPackage.LUW_STORAGE_TABLE__REGULAR_DATA_TABLE_SPACE: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE;
				case LUWPackage.LUW_STORAGE_TABLE__DATA_PARTITIONS: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS;
				case LUWPackage.LUW_STORAGE_TABLE__DATA_PARTITION_KEY: return LUWPackage.LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY;
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
		result.append(" (valueCompression: "); //$NON-NLS-1$
		result.append(valueCompression);
		result.append(", rowCompression: "); //$NON-NLS-1$
		result.append(rowCompression);
		result.append(", rowCompressionEmpty: "); //$NON-NLS-1$
		result.append(rowCompressionEmpty);
		result.append(", compressionMode: "); //$NON-NLS-1$
		result.append(compressionMode);
		result.append(')');
		return result.toString();
	}

} //LUWMaterializedQueryTableImpl
