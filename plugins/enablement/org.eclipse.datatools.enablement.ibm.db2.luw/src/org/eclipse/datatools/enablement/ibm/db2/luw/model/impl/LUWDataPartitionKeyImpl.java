/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWDataPartitionKeyImpl.java,v 1.6 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Partition Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionKeyImpl#getPartitionMethod <em>Partition Method</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionKeyImpl#getPartitionExpressions <em>Partition Expressions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionKeyImpl#getTable <em>Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWDataPartitionKeyImpl extends EObjectImpl implements LUWDataPartitionKey {
	/**
	 * The default value of the '{@link #getPartitionMethod() <em>Partition Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionMethod()
	 * @generated
	 * @ordered
	 */
	protected static final DataPartitionMethod PARTITION_METHOD_EDEFAULT = DataPartitionMethod.RANGE_LITERAL;

	/**
	 * The cached value of the '{@link #getPartitionMethod() <em>Partition Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionMethod()
	 * @generated
	 * @ordered
	 */
	protected DataPartitionMethod partitionMethod = PARTITION_METHOD_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPartitionExpressions() <em>Partition Expressions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionExpressions()
	 * @generated
	 * @ordered
	 */
	protected EList partitionExpressions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWDataPartitionKeyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_DATA_PARTITION_KEY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataPartitionMethod getPartitionMethod() {
		return partitionMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartitionMethod(DataPartitionMethod newPartitionMethod) {
		DataPartitionMethod oldPartitionMethod = partitionMethod;
		partitionMethod = newPartitionMethod == null ? PARTITION_METHOD_EDEFAULT : newPartitionMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_METHOD, oldPartitionMethod, partitionMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartitionExpressions() {
		if (partitionExpressions == null) {
			partitionExpressions = new EObjectContainmentWithInverseEList(LUWPartitionExpression.class, this, LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS, LUWPackage.LUW_PARTITION_EXPRESSION__KEY);
		}
		return partitionExpressions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageTable getTable() {
		if (eContainerFeatureID() != LUWPackage.LUW_DATA_PARTITION_KEY__TABLE) return null;
		return (LUWStorageTable)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTable(LUWStorageTable newTable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTable, LUWPackage.LUW_DATA_PARTITION_KEY__TABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(LUWStorageTable newTable) {
		if (newTable != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_DATA_PARTITION_KEY__TABLE && newTable != null)) {
			if (EcoreUtil.isAncestor(this, newTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, LUWPackage.LUW_STORAGE_TABLE__DATA_PARTITION_KEY, LUWStorageTable.class, msgs);
			msgs = basicSetTable(newTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION_KEY__TABLE, newTable, newTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS:
				return ((InternalEList)getPartitionExpressions()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_DATA_PARTITION_KEY__TABLE:
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
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS:
				return ((InternalEList)getPartitionExpressions()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_DATA_PARTITION_KEY__TABLE:
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
			case LUWPackage.LUW_DATA_PARTITION_KEY__TABLE:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_STORAGE_TABLE__DATA_PARTITION_KEY, LUWStorageTable.class, msgs);
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
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_METHOD:
				return getPartitionMethod();
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS:
				return getPartitionExpressions();
			case LUWPackage.LUW_DATA_PARTITION_KEY__TABLE:
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
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_METHOD:
				setPartitionMethod((DataPartitionMethod)newValue);
				return;
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS:
				getPartitionExpressions().clear();
				getPartitionExpressions().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATA_PARTITION_KEY__TABLE:
				setTable((LUWStorageTable)newValue);
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
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_METHOD:
				setPartitionMethod(PARTITION_METHOD_EDEFAULT);
				return;
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS:
				getPartitionExpressions().clear();
				return;
			case LUWPackage.LUW_DATA_PARTITION_KEY__TABLE:
				setTable((LUWStorageTable)null);
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
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_METHOD:
				return partitionMethod != PARTITION_METHOD_EDEFAULT;
			case LUWPackage.LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS:
				return partitionExpressions != null && !partitionExpressions.isEmpty();
			case LUWPackage.LUW_DATA_PARTITION_KEY__TABLE:
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
		result.append(" (partitionMethod: "); //$NON-NLS-1$
		result.append(partitionMethod);
		result.append(')');
		return result.toString();
	}

} //LUWDataPartitionKeyImpl
