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
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Partition Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionKeyImpl#getTemporaryStorageTable <em>Temporary Storage Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionKeyImpl#getPartitionMethod <em>Partition Method</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionKeyImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionKeyImpl#getColumns <em>Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWPartitionKeyImpl extends SQLObjectImpl implements LUWPartitionKey {
	/**
	 * The default value of the '{@link #getPartitionMethod() <em>Partition Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionMethod()
	 * @generated
	 * @ordered
	 */
	protected static final PartitionMethod PARTITION_METHOD_EDEFAULT = PartitionMethod.HASHING_LITERAL;

	/**
	 * The cached value of the '{@link #getPartitionMethod() <em>Partition Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionMethod()
	 * @generated
	 * @ordered
	 */
	protected PartitionMethod partitionMethod = PARTITION_METHOD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getColumns() <em>Columns</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumns()
	 * @generated
	 * @ordered
	 */
	protected EList columns;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWPartitionKeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_PARTITION_KEY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTemporaryStorageTable getTemporaryStorageTable() {
		if (eContainerFeatureID() != LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE) return null;
		return (LUWTemporaryStorageTable)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTemporaryStorageTable(LUWTemporaryStorageTable newTemporaryStorageTable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTemporaryStorageTable, LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemporaryStorageTable(LUWTemporaryStorageTable newTemporaryStorageTable) {
		if (newTemporaryStorageTable != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE && newTemporaryStorageTable != null)) {
			if (EcoreUtil.isAncestor(this, newTemporaryStorageTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTemporaryStorageTable != null)
				msgs = ((InternalEObject)newTemporaryStorageTable).eInverseAdd(this, LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY, LUWTemporaryStorageTable.class, msgs);
			msgs = basicSetTemporaryStorageTable(newTemporaryStorageTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE, newTemporaryStorageTable, newTemporaryStorageTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartitionMethod getPartitionMethod() {
		return partitionMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartitionMethod(PartitionMethod newPartitionMethod) {
		PartitionMethod oldPartitionMethod = partitionMethod;
		partitionMethod = newPartitionMethod == null ? PARTITION_METHOD_EDEFAULT : newPartitionMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_KEY__PARTITION_METHOD, oldPartitionMethod, partitionMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageTable getTable() {
		if (eContainerFeatureID() != LUWPackage.LUW_PARTITION_KEY__TABLE) return null;
		return (LUWStorageTable)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTable(LUWStorageTable newTable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTable, LUWPackage.LUW_PARTITION_KEY__TABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(LUWStorageTable newTable) {
		if (newTable != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_PARTITION_KEY__TABLE && newTable != null)) {
			if (EcoreUtil.isAncestor(this, newTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, LUWPackage.LUW_STORAGE_TABLE__PARTITION_KEY, LUWStorageTable.class, msgs);
			msgs = basicSetTable(newTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_PARTITION_KEY__TABLE, newTable, newTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getColumns() {
		if (columns == null) {
			columns = new EObjectResolvingEList(Column.class, this, LUWPackage.LUW_PARTITION_KEY__COLUMNS);
		}
		return columns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTemporaryStorageTable((LUWTemporaryStorageTable)otherEnd, msgs);
			case LUWPackage.LUW_PARTITION_KEY__TABLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTable((LUWStorageTable)otherEnd, msgs);
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
			case LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE:
				return basicSetTemporaryStorageTable(null, msgs);
			case LUWPackage.LUW_PARTITION_KEY__TABLE:
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
			case LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY, LUWTemporaryStorageTable.class, msgs);
			case LUWPackage.LUW_PARTITION_KEY__TABLE:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_STORAGE_TABLE__PARTITION_KEY, LUWStorageTable.class, msgs);
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
			case LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE:
				return getTemporaryStorageTable();
			case LUWPackage.LUW_PARTITION_KEY__PARTITION_METHOD:
				return getPartitionMethod();
			case LUWPackage.LUW_PARTITION_KEY__TABLE:
				return getTable();
			case LUWPackage.LUW_PARTITION_KEY__COLUMNS:
				return getColumns();
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
			case LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE:
				setTemporaryStorageTable((LUWTemporaryStorageTable)newValue);
				return;
			case LUWPackage.LUW_PARTITION_KEY__PARTITION_METHOD:
				setPartitionMethod((PartitionMethod)newValue);
				return;
			case LUWPackage.LUW_PARTITION_KEY__TABLE:
				setTable((LUWStorageTable)newValue);
				return;
			case LUWPackage.LUW_PARTITION_KEY__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection)newValue);
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
			case LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE:
				setTemporaryStorageTable((LUWTemporaryStorageTable)null);
				return;
			case LUWPackage.LUW_PARTITION_KEY__PARTITION_METHOD:
				setPartitionMethod(PARTITION_METHOD_EDEFAULT);
				return;
			case LUWPackage.LUW_PARTITION_KEY__TABLE:
				setTable((LUWStorageTable)null);
				return;
			case LUWPackage.LUW_PARTITION_KEY__COLUMNS:
				getColumns().clear();
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
			case LUWPackage.LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE:
				return getTemporaryStorageTable() != null;
			case LUWPackage.LUW_PARTITION_KEY__PARTITION_METHOD:
				return partitionMethod != PARTITION_METHOD_EDEFAULT;
			case LUWPackage.LUW_PARTITION_KEY__TABLE:
				return getTable() != null;
			case LUWPackage.LUW_PARTITION_KEY__COLUMNS:
				return columns != null && !columns.isEmpty();
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
		result.append(" (partitionMethod: "); //$NON-NLS-1$
		result.append(partitionMethod);
		result.append(')');
		return result.toString();
	}

} //LUWPartitionKeyImpl
