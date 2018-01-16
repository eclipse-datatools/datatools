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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Buffer Pool</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getCreateType <em>Create Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getPageSize <em>Page Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getBlockSize <em>Block Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getNumBlockPages <em>Num Block Pages</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#isExtendedStorage <em>Extended Storage</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#isAutomatic <em>Automatic</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getTableSpaces <em>Table Spaces</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getPartitions <em>Partitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getPartitionGroup <em>Partition Group</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl#getSizeException <em>Size Exception</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWBufferPoolImpl extends SQLObjectImpl implements LUWBufferPool {
	/**
	 * The default value of the '{@link #getCreateType() <em>Create Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateType()
	 * @generated
	 * @ordered
	 */
	protected static final BufferPoolType CREATE_TYPE_EDEFAULT = BufferPoolType.IMMEDIATE_LITERAL;

	/**
	 * The cached value of the '{@link #getCreateType() <em>Create Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreateType()
	 * @generated
	 * @ordered
	 */
	protected BufferPoolType createType = CREATE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final int SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected int size = SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPageSize() <em>Page Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageSize()
	 * @generated
	 * @ordered
	 */
	protected static final PageSizeType PAGE_SIZE_EDEFAULT= PageSizeType.FOUR_K_LITERAL;

	/**
	 * The cached value of the '{@link #getPageSize() <em>Page Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageSize()
	 * @generated
	 * @ordered
	 */
	protected PageSizeType pageSize = PAGE_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBlockSize() <em>Block Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockSize()
	 * @generated
	 * @ordered
	 */
	protected static final int BLOCK_SIZE_EDEFAULT = 32;

	/**
	 * The cached value of the '{@link #getBlockSize() <em>Block Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockSize()
	 * @generated
	 * @ordered
	 */
	protected int blockSize = BLOCK_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumBlockPages() <em>Num Block Pages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumBlockPages()
	 * @generated
	 * @ordered
	 */
	protected static final int NUM_BLOCK_PAGES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumBlockPages() <em>Num Block Pages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumBlockPages()
	 * @generated
	 * @ordered
	 */
	protected int numBlockPages = NUM_BLOCK_PAGES_EDEFAULT;

	/**
	 * The default value of the '{@link #isExtendedStorage() <em>Extended Storage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtendedStorage()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXTENDED_STORAGE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExtendedStorage() <em>Extended Storage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExtendedStorage()
	 * @generated
	 * @ordered
	 */
	protected boolean extendedStorage = EXTENDED_STORAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAutomatic() <em>Automatic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutomatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTOMATIC_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAutomatic() <em>Automatic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutomatic()
	 * @generated
	 * @ordered
	 */
	protected boolean automatic = AUTOMATIC_EDEFAULT;

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
	 * The cached value of the '{@link #getPartitions() <em>Partitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitions()
	 * @generated
	 * @ordered
	 */
	protected EList partitions;

	/**
	 * The cached value of the '{@link #getPartitionGroup() <em>Partition Group</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionGroup()
	 * @generated
	 * @ordered
	 */
	protected EList partitionGroup;

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
	 * The cached value of the '{@link #getSizeException() <em>Size Exception</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeException()
	 * @generated
	 * @ordered
	 */
	protected EList sizeException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWBufferPoolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_BUFFER_POOL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BufferPoolType getCreateType() {
		return createType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreateType(BufferPoolType newCreateType) {
		BufferPoolType oldCreateType = createType;
		createType = newCreateType == null ? CREATE_TYPE_EDEFAULT : newCreateType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL__CREATE_TYPE, oldCreateType, createType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(int newSize) {
		int oldSize = size;
		size = newSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PageSizeType getPageSize() {
		return pageSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageSize(PageSizeType newPageSize) {
		PageSizeType oldPageSize = pageSize;
		pageSize = newPageSize == null ? PAGE_SIZE_EDEFAULT : newPageSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL__PAGE_SIZE, oldPageSize, pageSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlockSize(int newBlockSize) {
		int oldBlockSize = blockSize;
		blockSize = newBlockSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL__BLOCK_SIZE, oldBlockSize, blockSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumBlockPages() {
		return numBlockPages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumBlockPages(int newNumBlockPages) {
		int oldNumBlockPages = numBlockPages;
		numBlockPages = newNumBlockPages;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL__NUM_BLOCK_PAGES, oldNumBlockPages, numBlockPages));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExtendedStorage() {
		return extendedStorage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedStorage(boolean newExtendedStorage) {
		boolean oldExtendedStorage = extendedStorage;
		extendedStorage = newExtendedStorage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL__EXTENDED_STORAGE, oldExtendedStorage, extendedStorage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutomatic() {
		return automatic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutomatic(boolean newAutomatic) {
		boolean oldAutomatic = automatic;
		automatic = newAutomatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL__AUTOMATIC, oldAutomatic, automatic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTableSpaces() {
		if (tableSpaces == null) {
			tableSpaces = new EObjectWithInverseResolvingEList(LUWTableSpace.class, this, LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES, LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL);
		}
		return tableSpaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartitions() {
		if (partitions == null) {
			partitions = new EObjectWithInverseResolvingEList(LUWDatabasePartition.class, this, LUWPackage.LUW_BUFFER_POOL__PARTITIONS, LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL);
		}
		return partitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartitionGroup() {
		if (partitionGroup == null) {
			partitionGroup = new EObjectWithInverseResolvingEList.ManyInverse(LUWPartitionGroup.class, this, LUWPackage.LUW_BUFFER_POOL__PARTITION_GROUP, LUWPackage.LUW_PARTITION_GROUP__BUFFER_POOL);
		}
		return partitionGroup;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_BUFFER_POOL__DATABASE, oldDatabase, database));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL__DATABASE, oldDatabase, newDatabase);
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
				msgs = ((InternalEObject)database).eInverseRemove(this, LUWPackage.LUW_DATABASE__BUFFERPOOLS, LUWDatabase.class, msgs);
			if (newDatabase != null)
				msgs = ((InternalEObject)newDatabase).eInverseAdd(this, LUWPackage.LUW_DATABASE__BUFFERPOOLS, LUWDatabase.class, msgs);
			msgs = basicSetDatabase(newDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL__DATABASE, newDatabase, newDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSizeException() {
		if (sizeException == null) {
			sizeException = new EObjectContainmentWithInverseEList(LUWBufferPoolSizeException.class, this, LUWPackage.LUW_BUFFER_POOL__SIZE_EXCEPTION, LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL);
		}
		return sizeException;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES:
				return ((InternalEList)getTableSpaces()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_BUFFER_POOL__PARTITIONS:
				return ((InternalEList)getPartitions()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_BUFFER_POOL__PARTITION_GROUP:
				return ((InternalEList)getPartitionGroup()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_BUFFER_POOL__DATABASE:
				if (database != null)
					msgs = ((InternalEObject)database).eInverseRemove(this, LUWPackage.LUW_DATABASE__BUFFERPOOLS, LUWDatabase.class, msgs);
				return basicSetDatabase((LUWDatabase)otherEnd, msgs);
			case LUWPackage.LUW_BUFFER_POOL__SIZE_EXCEPTION:
				return ((InternalEList)getSizeException()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES:
				return ((InternalEList)getTableSpaces()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_BUFFER_POOL__PARTITIONS:
				return ((InternalEList)getPartitions()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_BUFFER_POOL__PARTITION_GROUP:
				return ((InternalEList)getPartitionGroup()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_BUFFER_POOL__DATABASE:
				return basicSetDatabase(null, msgs);
			case LUWPackage.LUW_BUFFER_POOL__SIZE_EXCEPTION:
				return ((InternalEList)getSizeException()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_BUFFER_POOL__CREATE_TYPE:
				return getCreateType();
			case LUWPackage.LUW_BUFFER_POOL__SIZE:
				return new Integer(getSize());
			case LUWPackage.LUW_BUFFER_POOL__PAGE_SIZE:
				return getPageSize();
			case LUWPackage.LUW_BUFFER_POOL__BLOCK_SIZE:
				return new Integer(getBlockSize());
			case LUWPackage.LUW_BUFFER_POOL__NUM_BLOCK_PAGES:
				return new Integer(getNumBlockPages());
			case LUWPackage.LUW_BUFFER_POOL__EXTENDED_STORAGE:
				return isExtendedStorage() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_BUFFER_POOL__AUTOMATIC:
				return isAutomatic() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES:
				return getTableSpaces();
			case LUWPackage.LUW_BUFFER_POOL__PARTITIONS:
				return getPartitions();
			case LUWPackage.LUW_BUFFER_POOL__PARTITION_GROUP:
				return getPartitionGroup();
			case LUWPackage.LUW_BUFFER_POOL__DATABASE:
				if (resolve) return getDatabase();
				return basicGetDatabase();
			case LUWPackage.LUW_BUFFER_POOL__SIZE_EXCEPTION:
				return getSizeException();
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
			case LUWPackage.LUW_BUFFER_POOL__CREATE_TYPE:
				setCreateType((BufferPoolType)newValue);
				return;
			case LUWPackage.LUW_BUFFER_POOL__SIZE:
				setSize(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_BUFFER_POOL__PAGE_SIZE:
				setPageSize((PageSizeType)newValue);
				return;
			case LUWPackage.LUW_BUFFER_POOL__BLOCK_SIZE:
				setBlockSize(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_BUFFER_POOL__NUM_BLOCK_PAGES:
				setNumBlockPages(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_BUFFER_POOL__EXTENDED_STORAGE:
				setExtendedStorage(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_BUFFER_POOL__AUTOMATIC:
				setAutomatic(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES:
				getTableSpaces().clear();
				getTableSpaces().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_BUFFER_POOL__PARTITIONS:
				getPartitions().clear();
				getPartitions().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_BUFFER_POOL__PARTITION_GROUP:
				getPartitionGroup().clear();
				getPartitionGroup().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_BUFFER_POOL__DATABASE:
				setDatabase((LUWDatabase)newValue);
				return;
			case LUWPackage.LUW_BUFFER_POOL__SIZE_EXCEPTION:
				getSizeException().clear();
				getSizeException().addAll((Collection)newValue);
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
			case LUWPackage.LUW_BUFFER_POOL__CREATE_TYPE:
				setCreateType(CREATE_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_BUFFER_POOL__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_BUFFER_POOL__PAGE_SIZE:
				setPageSize(PAGE_SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_BUFFER_POOL__BLOCK_SIZE:
				setBlockSize(BLOCK_SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_BUFFER_POOL__NUM_BLOCK_PAGES:
				setNumBlockPages(NUM_BLOCK_PAGES_EDEFAULT);
				return;
			case LUWPackage.LUW_BUFFER_POOL__EXTENDED_STORAGE:
				setExtendedStorage(EXTENDED_STORAGE_EDEFAULT);
				return;
			case LUWPackage.LUW_BUFFER_POOL__AUTOMATIC:
				setAutomatic(AUTOMATIC_EDEFAULT);
				return;
			case LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES:
				getTableSpaces().clear();
				return;
			case LUWPackage.LUW_BUFFER_POOL__PARTITIONS:
				getPartitions().clear();
				return;
			case LUWPackage.LUW_BUFFER_POOL__PARTITION_GROUP:
				getPartitionGroup().clear();
				return;
			case LUWPackage.LUW_BUFFER_POOL__DATABASE:
				setDatabase((LUWDatabase)null);
				return;
			case LUWPackage.LUW_BUFFER_POOL__SIZE_EXCEPTION:
				getSizeException().clear();
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
			case LUWPackage.LUW_BUFFER_POOL__CREATE_TYPE:
				return createType != CREATE_TYPE_EDEFAULT;
			case LUWPackage.LUW_BUFFER_POOL__SIZE:
				return size != SIZE_EDEFAULT;
			case LUWPackage.LUW_BUFFER_POOL__PAGE_SIZE:
				return pageSize != PAGE_SIZE_EDEFAULT;
			case LUWPackage.LUW_BUFFER_POOL__BLOCK_SIZE:
				return blockSize != BLOCK_SIZE_EDEFAULT;
			case LUWPackage.LUW_BUFFER_POOL__NUM_BLOCK_PAGES:
				return numBlockPages != NUM_BLOCK_PAGES_EDEFAULT;
			case LUWPackage.LUW_BUFFER_POOL__EXTENDED_STORAGE:
				return extendedStorage != EXTENDED_STORAGE_EDEFAULT;
			case LUWPackage.LUW_BUFFER_POOL__AUTOMATIC:
				return automatic != AUTOMATIC_EDEFAULT;
			case LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES:
				return tableSpaces != null && !tableSpaces.isEmpty();
			case LUWPackage.LUW_BUFFER_POOL__PARTITIONS:
				return partitions != null && !partitions.isEmpty();
			case LUWPackage.LUW_BUFFER_POOL__PARTITION_GROUP:
				return partitionGroup != null && !partitionGroup.isEmpty();
			case LUWPackage.LUW_BUFFER_POOL__DATABASE:
				return database != null;
			case LUWPackage.LUW_BUFFER_POOL__SIZE_EXCEPTION:
				return sizeException != null && !sizeException.isEmpty();
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
		result.append(" (createType: "); //$NON-NLS-1$
		result.append(createType);
		result.append(", size: "); //$NON-NLS-1$
		result.append(size);
		result.append(", pageSize: "); //$NON-NLS-1$
		result.append(pageSize);
		result.append(", blockSize: "); //$NON-NLS-1$
		result.append(blockSize);
		result.append(", numBlockPages: "); //$NON-NLS-1$
		result.append(numBlockPages);
		result.append(", extendedStorage: "); //$NON-NLS-1$
		result.append(extendedStorage);
		result.append(", automatic: "); //$NON-NLS-1$
		result.append(automatic);
		result.append(')');
		return result.toString();
	}

} //LUWBufferPoolImpl
