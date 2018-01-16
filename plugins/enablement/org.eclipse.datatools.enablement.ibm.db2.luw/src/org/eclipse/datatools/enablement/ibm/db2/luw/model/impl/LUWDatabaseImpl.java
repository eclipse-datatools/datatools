/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#isFederated <em>Federated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getWrappers <em>Wrappers</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getServers <em>Servers</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getFunctionMappings <em>Function Mappings</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getTypeMappings <em>Type Mappings</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getReverseTypeMappings <em>Reverse Type Mappings</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getBufferpools <em>Bufferpools</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getTablespaces <em>Tablespaces</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getStorageGroups <em>Storage Groups</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl#getDefaultStorageGroup <em>Default Storage Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWDatabaseImpl extends DB2DatabaseImpl implements LUWDatabase {
	/**
	 * The default value of the '{@link #isFederated() <em>Federated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFederated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FEDERATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFederated() <em>Federated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFederated()
	 * @generated
	 * @ordered
	 */
	protected boolean federated = FEDERATED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGroups() <em>Groups</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroups()
	 * @generated
	 * @ordered
	 */
	protected EList groups;

	/**
	 * The cached value of the '{@link #getWrappers() <em>Wrappers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWrappers()
	 * @generated
	 * @ordered
	 */
	protected EList wrappers;

	/**
	 * The cached value of the '{@link #getServers() <em>Servers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServers()
	 * @generated
	 * @ordered
	 */
	protected EList servers;

	/**
	 * The cached value of the '{@link #getFunctionMappings() <em>Function Mappings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionMappings()
	 * @generated
	 * @ordered
	 */
	protected EList functionMappings;

	/**
	 * The cached value of the '{@link #getTypeMappings() <em>Type Mappings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeMappings()
	 * @generated
	 * @ordered
	 */
	protected EList typeMappings;

	/**
	 * The cached value of the '{@link #getReverseTypeMappings() <em>Reverse Type Mappings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReverseTypeMappings()
	 * @generated
	 * @ordered
	 */
	protected EList reverseTypeMappings;

	/**
	 * The cached value of the '{@link #getBufferpools() <em>Bufferpools</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBufferpools()
	 * @generated
	 * @ordered
	 */
	protected EList bufferpools;

	/**
	 * The cached value of the '{@link #getTablespaces() <em>Tablespaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTablespaces()
	 * @generated
	 * @ordered
	 */
	protected EList tablespaces;

	/**
	 * The cached value of the '{@link #getStorageGroups() <em>Storage Groups</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStorageGroups()
	 * @generated
	 * @ordered
	 */
	protected EList storageGroups;

	/**
	 * The cached value of the '{@link #getDefaultStorageGroup() <em>Default Storage Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultStorageGroup()
	 * @generated
	 * @ordered
	 */
	protected LUWStorageGroup defaultStorageGroup;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWDatabaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_DATABASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFederated() {
		return federated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFederated(boolean newFederated) {
		boolean oldFederated = federated;
		federated = newFederated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE__FEDERATED, oldFederated, federated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getGroups() {
		if (groups == null) {
			groups = new EObjectWithInverseResolvingEList(LUWPartitionGroup.class, this, LUWPackage.LUW_DATABASE__GROUPS, LUWPackage.LUW_PARTITION_GROUP__DATABASE);
		}
		return groups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getWrappers() {
		if (wrappers == null) {
			wrappers = new EObjectWithInverseResolvingEList(LUWWrapper.class, this, LUWPackage.LUW_DATABASE__WRAPPERS, LUWPackage.LUW_WRAPPER__LUW_DATABASE);
		}
		return wrappers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getServers() {
		if (servers == null) {
			servers = new EObjectWithInverseResolvingEList(LUWServer.class, this, LUWPackage.LUW_DATABASE__SERVERS, LUWPackage.LUW_SERVER__LUW_DATABASE);
		}
		return servers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFunctionMappings() {
		if (functionMappings == null) {
			functionMappings = new EObjectWithInverseResolvingEList(LUWFunctionMapping.class, this, LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS, LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE);
		}
		return functionMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTypeMappings() {
		if (typeMappings == null) {
			typeMappings = new EObjectResolvingEList(LUWTypeMapping.class, this, LUWPackage.LUW_DATABASE__TYPE_MAPPINGS);
		}
		return typeMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReverseTypeMappings() {
		if (reverseTypeMappings == null) {
			reverseTypeMappings = new EObjectResolvingEList(LUWTypeMapping.class, this, LUWPackage.LUW_DATABASE__REVERSE_TYPE_MAPPINGS);
		}
		return reverseTypeMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getBufferpools() {
		if (bufferpools == null) {
			bufferpools = new EObjectWithInverseResolvingEList(LUWBufferPool.class, this, LUWPackage.LUW_DATABASE__BUFFERPOOLS, LUWPackage.LUW_BUFFER_POOL__DATABASE);
		}
		return bufferpools;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTablespaces() {
		if (tablespaces == null) {
			tablespaces = new EObjectWithInverseResolvingEList(LUWTableSpace.class, this, LUWPackage.LUW_DATABASE__TABLESPACES, LUWPackage.LUW_TABLE_SPACE__DATABASE);
		}
		return tablespaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getStorageGroups() {
		if (storageGroups == null) {
			storageGroups = new EObjectWithInverseResolvingEList(LUWStorageGroup.class, this, LUWPackage.LUW_DATABASE__STORAGE_GROUPS, LUWPackage.LUW_STORAGE_GROUP__DATABASE);
		}
		return storageGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageGroup getDefaultStorageGroup() {
		if (defaultStorageGroup != null && defaultStorageGroup.eIsProxy()) {
			InternalEObject oldDefaultStorageGroup = (InternalEObject)defaultStorageGroup;
			defaultStorageGroup = (LUWStorageGroup)eResolveProxy(oldDefaultStorageGroup);
			if (defaultStorageGroup != oldDefaultStorageGroup) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_DATABASE__DEFAULT_STORAGE_GROUP, oldDefaultStorageGroup, defaultStorageGroup));
			}
		}
		return defaultStorageGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageGroup basicGetDefaultStorageGroup() {
		return defaultStorageGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultStorageGroup(LUWStorageGroup newDefaultStorageGroup) {
		LUWStorageGroup oldDefaultStorageGroup = defaultStorageGroup;
		defaultStorageGroup = newDefaultStorageGroup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE__DEFAULT_STORAGE_GROUP, oldDefaultStorageGroup, defaultStorageGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_DATABASE__GROUPS:
				return ((InternalEList)getGroups()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__WRAPPERS:
				return ((InternalEList)getWrappers()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__SERVERS:
				return ((InternalEList)getServers()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS:
				return ((InternalEList)getFunctionMappings()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__BUFFERPOOLS:
				return ((InternalEList)getBufferpools()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__TABLESPACES:
				return ((InternalEList)getTablespaces()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__STORAGE_GROUPS:
				return ((InternalEList)getStorageGroups()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.LUW_DATABASE__GROUPS:
				return ((InternalEList)getGroups()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__WRAPPERS:
				return ((InternalEList)getWrappers()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__SERVERS:
				return ((InternalEList)getServers()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS:
				return ((InternalEList)getFunctionMappings()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__BUFFERPOOLS:
				return ((InternalEList)getBufferpools()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__TABLESPACES:
				return ((InternalEList)getTablespaces()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_DATABASE__STORAGE_GROUPS:
				return ((InternalEList)getStorageGroups()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_DATABASE__FEDERATED:
				return isFederated() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_DATABASE__GROUPS:
				return getGroups();
			case LUWPackage.LUW_DATABASE__WRAPPERS:
				return getWrappers();
			case LUWPackage.LUW_DATABASE__SERVERS:
				return getServers();
			case LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS:
				return getFunctionMappings();
			case LUWPackage.LUW_DATABASE__TYPE_MAPPINGS:
				return getTypeMappings();
			case LUWPackage.LUW_DATABASE__REVERSE_TYPE_MAPPINGS:
				return getReverseTypeMappings();
			case LUWPackage.LUW_DATABASE__BUFFERPOOLS:
				return getBufferpools();
			case LUWPackage.LUW_DATABASE__TABLESPACES:
				return getTablespaces();
			case LUWPackage.LUW_DATABASE__STORAGE_GROUPS:
				return getStorageGroups();
			case LUWPackage.LUW_DATABASE__DEFAULT_STORAGE_GROUP:
				if (resolve) return getDefaultStorageGroup();
				return basicGetDefaultStorageGroup();
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
			case LUWPackage.LUW_DATABASE__FEDERATED:
				setFederated(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_DATABASE__GROUPS:
				getGroups().clear();
				getGroups().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE__WRAPPERS:
				getWrappers().clear();
				getWrappers().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE__SERVERS:
				getServers().clear();
				getServers().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS:
				getFunctionMappings().clear();
				getFunctionMappings().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE__TYPE_MAPPINGS:
				getTypeMappings().clear();
				getTypeMappings().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE__REVERSE_TYPE_MAPPINGS:
				getReverseTypeMappings().clear();
				getReverseTypeMappings().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE__BUFFERPOOLS:
				getBufferpools().clear();
				getBufferpools().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE__TABLESPACES:
				getTablespaces().clear();
				getTablespaces().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE__STORAGE_GROUPS:
				getStorageGroups().clear();
				getStorageGroups().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATABASE__DEFAULT_STORAGE_GROUP:
				setDefaultStorageGroup((LUWStorageGroup)newValue);
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
			case LUWPackage.LUW_DATABASE__FEDERATED:
				setFederated(FEDERATED_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE__GROUPS:
				getGroups().clear();
				return;
			case LUWPackage.LUW_DATABASE__WRAPPERS:
				getWrappers().clear();
				return;
			case LUWPackage.LUW_DATABASE__SERVERS:
				getServers().clear();
				return;
			case LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS:
				getFunctionMappings().clear();
				return;
			case LUWPackage.LUW_DATABASE__TYPE_MAPPINGS:
				getTypeMappings().clear();
				return;
			case LUWPackage.LUW_DATABASE__REVERSE_TYPE_MAPPINGS:
				getReverseTypeMappings().clear();
				return;
			case LUWPackage.LUW_DATABASE__BUFFERPOOLS:
				getBufferpools().clear();
				return;
			case LUWPackage.LUW_DATABASE__TABLESPACES:
				getTablespaces().clear();
				return;
			case LUWPackage.LUW_DATABASE__STORAGE_GROUPS:
				getStorageGroups().clear();
				return;
			case LUWPackage.LUW_DATABASE__DEFAULT_STORAGE_GROUP:
				setDefaultStorageGroup((LUWStorageGroup)null);
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
			case LUWPackage.LUW_DATABASE__FEDERATED:
				return federated != FEDERATED_EDEFAULT;
			case LUWPackage.LUW_DATABASE__GROUPS:
				return groups != null && !groups.isEmpty();
			case LUWPackage.LUW_DATABASE__WRAPPERS:
				return wrappers != null && !wrappers.isEmpty();
			case LUWPackage.LUW_DATABASE__SERVERS:
				return servers != null && !servers.isEmpty();
			case LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS:
				return functionMappings != null && !functionMappings.isEmpty();
			case LUWPackage.LUW_DATABASE__TYPE_MAPPINGS:
				return typeMappings != null && !typeMappings.isEmpty();
			case LUWPackage.LUW_DATABASE__REVERSE_TYPE_MAPPINGS:
				return reverseTypeMappings != null && !reverseTypeMappings.isEmpty();
			case LUWPackage.LUW_DATABASE__BUFFERPOOLS:
				return bufferpools != null && !bufferpools.isEmpty();
			case LUWPackage.LUW_DATABASE__TABLESPACES:
				return tablespaces != null && !tablespaces.isEmpty();
			case LUWPackage.LUW_DATABASE__STORAGE_GROUPS:
				return storageGroups != null && !storageGroups.isEmpty();
			case LUWPackage.LUW_DATABASE__DEFAULT_STORAGE_GROUP:
				return defaultStorageGroup != null;
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
		result.append(" (federated: "); //$NON-NLS-1$
		result.append(federated);
		result.append(')');
		return result.toString();
	}

} //LUWDatabaseImpl
