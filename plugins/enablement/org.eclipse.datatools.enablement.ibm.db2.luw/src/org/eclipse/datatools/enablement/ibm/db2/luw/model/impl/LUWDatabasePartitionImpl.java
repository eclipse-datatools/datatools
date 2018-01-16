/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

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
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database Partition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl#getPortNumber <em>Port Number</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl#getHostName <em>Host Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl#getSwitchName <em>Switch Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl#isCatalogPartition <em>Catalog Partition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl#getBufferPool <em>Buffer Pool</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl#getContainers <em>Containers</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl#getSizeException <em>Size Exception</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWDatabasePartitionImpl extends SQLObjectImpl implements LUWDatabasePartition {
	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected int number = NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getPortNumber() <em>Port Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int PORT_NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPortNumber() <em>Port Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortNumber()
	 * @generated
	 * @ordered
	 */
	protected int portNumber = PORT_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getHostName() <em>Host Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostName()
	 * @generated
	 * @ordered
	 */
	protected static final String HOST_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHostName() <em>Host Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostName()
	 * @generated
	 * @ordered
	 */
	protected String hostName = HOST_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSwitchName() <em>Switch Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchName()
	 * @generated
	 * @ordered
	 */
	protected static final String SWITCH_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSwitchName() <em>Switch Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwitchName()
	 * @generated
	 * @ordered
	 */
	protected String switchName = SWITCH_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isCatalogPartition() <em>Catalog Partition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCatalogPartition()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CATALOG_PARTITION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCatalogPartition() <em>Catalog Partition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCatalogPartition()
	 * @generated
	 * @ordered
	 */
	protected boolean catalogPartition = CATALOG_PARTITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected LUWPartitionGroup group;

	/**
	 * The cached value of the '{@link #getBufferPool() <em>Buffer Pool</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBufferPool()
	 * @generated
	 * @ordered
	 */
	protected LUWBufferPool bufferPool;

	/**
	 * The cached value of the '{@link #getContainers() <em>Containers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainers()
	 * @generated
	 * @ordered
	 */
	protected EList containers;

	/**
	 * The cached value of the '{@link #getSizeException() <em>Size Exception</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeException()
	 * @generated
	 * @ordered
	 */
	protected LUWBufferPoolSizeException sizeException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWDatabasePartitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_DATABASE_PARTITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(int newNumber) {
		int oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__NUMBER, oldNumber, number));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionGroup getGroup() {
		if (group != null && group.eIsProxy()) {
			InternalEObject oldGroup = (InternalEObject)group;
			group = (LUWPartitionGroup)eResolveProxy(oldGroup);
			if (group != oldGroup) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_DATABASE_PARTITION__GROUP, oldGroup, group));
			}
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionGroup basicGetGroup() {
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroup(LUWPartitionGroup newGroup, NotificationChain msgs) {
		LUWPartitionGroup oldGroup = group;
		group = newGroup;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__GROUP, oldGroup, newGroup);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(LUWPartitionGroup newGroup) {
		if (newGroup != group) {
			NotificationChain msgs = null;
			if (group != null)
				msgs = ((InternalEObject)group).eInverseRemove(this, LUWPackage.LUW_PARTITION_GROUP__PARTITIONS, LUWPartitionGroup.class, msgs);
			if (newGroup != null)
				msgs = ((InternalEObject)newGroup).eInverseAdd(this, LUWPackage.LUW_PARTITION_GROUP__PARTITIONS, LUWPartitionGroup.class, msgs);
			msgs = basicSetGroup(newGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__GROUP, newGroup, newGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWBufferPool getBufferPool() {
		if (bufferPool != null && bufferPool.eIsProxy()) {
			InternalEObject oldBufferPool = (InternalEObject)bufferPool;
			bufferPool = (LUWBufferPool)eResolveProxy(oldBufferPool);
			if (bufferPool != oldBufferPool) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL, oldBufferPool, bufferPool));
			}
		}
		return bufferPool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWBufferPool basicGetBufferPool() {
		return bufferPool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBufferPool(LUWBufferPool newBufferPool, NotificationChain msgs) {
		LUWBufferPool oldBufferPool = bufferPool;
		bufferPool = newBufferPool;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL, oldBufferPool, newBufferPool);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBufferPool(LUWBufferPool newBufferPool) {
		if (newBufferPool != bufferPool) {
			NotificationChain msgs = null;
			if (bufferPool != null)
				msgs = ((InternalEObject)bufferPool).eInverseRemove(this, LUWPackage.LUW_BUFFER_POOL__PARTITIONS, LUWBufferPool.class, msgs);
			if (newBufferPool != null)
				msgs = ((InternalEObject)newBufferPool).eInverseAdd(this, LUWPackage.LUW_BUFFER_POOL__PARTITIONS, LUWBufferPool.class, msgs);
			msgs = basicSetBufferPool(newBufferPool, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL, newBufferPool, newBufferPool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getContainers() {
		if (containers == null) {
			containers = new EObjectWithInverseResolvingEList.ManyInverse(LUWDatabaseContainer.class, this, LUWPackage.LUW_DATABASE_PARTITION__CONTAINERS, LUWPackage.LUW_DATABASE_CONTAINER__PARTITIONS);
		}
		return containers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWBufferPoolSizeException getSizeException() {
		if (sizeException != null && sizeException.eIsProxy()) {
			InternalEObject oldSizeException = (InternalEObject)sizeException;
			sizeException = (LUWBufferPoolSizeException)eResolveProxy(oldSizeException);
			if (sizeException != oldSizeException) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION, oldSizeException, sizeException));
			}
		}
		return sizeException;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWBufferPoolSizeException basicGetSizeException() {
		return sizeException;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSizeException(LUWBufferPoolSizeException newSizeException, NotificationChain msgs) {
		LUWBufferPoolSizeException oldSizeException = sizeException;
		sizeException = newSizeException;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION, oldSizeException, newSizeException);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSizeException(LUWBufferPoolSizeException newSizeException) {
		if (newSizeException != sizeException) {
			NotificationChain msgs = null;
			if (sizeException != null)
				msgs = ((InternalEObject)sizeException).eInverseRemove(this, LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS, LUWBufferPoolSizeException.class, msgs);
			if (newSizeException != null)
				msgs = ((InternalEObject)newSizeException).eInverseAdd(this, LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS, LUWBufferPoolSizeException.class, msgs);
			msgs = basicSetSizeException(newSizeException, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION, newSizeException, newSizeException));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPortNumber() {
		return portNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortNumber(int newPortNumber) {
		int oldPortNumber = portNumber;
		portNumber = newPortNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__PORT_NUMBER, oldPortNumber, portNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHostName(String newHostName) {
		String oldHostName = hostName;
		hostName = newHostName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__HOST_NAME, oldHostName, hostName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSwitchName() {
		return switchName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwitchName(String newSwitchName) {
		String oldSwitchName = switchName;
		switchName = newSwitchName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__SWITCH_NAME, oldSwitchName, switchName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCatalogPartition() {
		return catalogPartition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCatalogPartition(boolean newCatalogPartition) {
		boolean oldCatalogPartition = catalogPartition;
		catalogPartition = newCatalogPartition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_PARTITION__CATALOG_PARTITION, oldCatalogPartition, catalogPartition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_DATABASE_PARTITION__GROUP:
				if (group != null)
					msgs = ((InternalEObject)group).eInverseRemove(this, LUWPackage.LUW_PARTITION_GROUP__PARTITIONS, LUWPartitionGroup.class, msgs);
				return basicSetGroup((LUWPartitionGroup)otherEnd, msgs);
			case LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL:
				if (bufferPool != null)
					msgs = ((InternalEObject)bufferPool).eInverseRemove(this, LUWPackage.LUW_BUFFER_POOL__PARTITIONS, LUWBufferPool.class, msgs);
				return basicSetBufferPool((LUWBufferPool)otherEnd, msgs);
			case LUWPackage.LUW_DATABASE_PARTITION__CONTAINERS:
				return ((InternalEList)getContainers()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION:
				if (sizeException != null)
					msgs = ((InternalEObject)sizeException).eInverseRemove(this, LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS, LUWBufferPoolSizeException.class, msgs);
				return basicSetSizeException((LUWBufferPoolSizeException)otherEnd, msgs);
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
			case LUWPackage.LUW_DATABASE_PARTITION__GROUP:
				return basicSetGroup(null, msgs);
			case LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL:
				return basicSetBufferPool(null, msgs);
			case LUWPackage.LUW_DATABASE_PARTITION__CONTAINERS:
				return ((InternalEList)getContainers()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION:
				return basicSetSizeException(null, msgs);
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
			case LUWPackage.LUW_DATABASE_PARTITION__NUMBER:
				return new Integer(getNumber());
			case LUWPackage.LUW_DATABASE_PARTITION__PORT_NUMBER:
				return new Integer(getPortNumber());
			case LUWPackage.LUW_DATABASE_PARTITION__HOST_NAME:
				return getHostName();
			case LUWPackage.LUW_DATABASE_PARTITION__SWITCH_NAME:
				return getSwitchName();
			case LUWPackage.LUW_DATABASE_PARTITION__CATALOG_PARTITION:
				return isCatalogPartition() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_DATABASE_PARTITION__GROUP:
				if (resolve) return getGroup();
				return basicGetGroup();
			case LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL:
				if (resolve) return getBufferPool();
				return basicGetBufferPool();
			case LUWPackage.LUW_DATABASE_PARTITION__CONTAINERS:
				return getContainers();
			case LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION:
				if (resolve) return getSizeException();
				return basicGetSizeException();
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
			case LUWPackage.LUW_DATABASE_PARTITION__NUMBER:
				setNumber(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__PORT_NUMBER:
				setPortNumber(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__HOST_NAME:
				setHostName((String)newValue);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__SWITCH_NAME:
				setSwitchName((String)newValue);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__CATALOG_PARTITION:
				setCatalogPartition(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__GROUP:
				setGroup((LUWPartitionGroup)newValue);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL:
				setBufferPool((LUWBufferPool)newValue);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__CONTAINERS:
				getContainers().clear();
				getContainers().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION:
				setSizeException((LUWBufferPoolSizeException)newValue);
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
			case LUWPackage.LUW_DATABASE_PARTITION__NUMBER:
				setNumber(NUMBER_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__PORT_NUMBER:
				setPortNumber(PORT_NUMBER_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__HOST_NAME:
				setHostName(HOST_NAME_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__SWITCH_NAME:
				setSwitchName(SWITCH_NAME_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__CATALOG_PARTITION:
				setCatalogPartition(CATALOG_PARTITION_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__GROUP:
				setGroup((LUWPartitionGroup)null);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL:
				setBufferPool((LUWBufferPool)null);
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__CONTAINERS:
				getContainers().clear();
				return;
			case LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION:
				setSizeException((LUWBufferPoolSizeException)null);
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
			case LUWPackage.LUW_DATABASE_PARTITION__NUMBER:
				return number != NUMBER_EDEFAULT;
			case LUWPackage.LUW_DATABASE_PARTITION__PORT_NUMBER:
				return portNumber != PORT_NUMBER_EDEFAULT;
			case LUWPackage.LUW_DATABASE_PARTITION__HOST_NAME:
				return HOST_NAME_EDEFAULT == null ? hostName != null : !HOST_NAME_EDEFAULT.equals(hostName);
			case LUWPackage.LUW_DATABASE_PARTITION__SWITCH_NAME:
				return SWITCH_NAME_EDEFAULT == null ? switchName != null : !SWITCH_NAME_EDEFAULT.equals(switchName);
			case LUWPackage.LUW_DATABASE_PARTITION__CATALOG_PARTITION:
				return catalogPartition != CATALOG_PARTITION_EDEFAULT;
			case LUWPackage.LUW_DATABASE_PARTITION__GROUP:
				return group != null;
			case LUWPackage.LUW_DATABASE_PARTITION__BUFFER_POOL:
				return bufferPool != null;
			case LUWPackage.LUW_DATABASE_PARTITION__CONTAINERS:
				return containers != null && !containers.isEmpty();
			case LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION:
				return sizeException != null;
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
		result.append(" (number: "); //$NON-NLS-1$
		result.append(number);
		result.append(", portNumber: "); //$NON-NLS-1$
		result.append(portNumber);
		result.append(", hostName: "); //$NON-NLS-1$
		result.append(hostName);
		result.append(", switchName: "); //$NON-NLS-1$
		result.append(switchName);
		result.append(", catalogPartition: "); //$NON-NLS-1$
		result.append(catalogPartition);
		result.append(')');
		return result.toString();
	}

} //LUWDatabasePartitionImpl
