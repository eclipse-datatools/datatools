/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWBufferPoolSizeExceptionImpl.java,v 1.1 2009/02/27 23:12:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Buffer Pool Size Exception</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolSizeExceptionImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolSizeExceptionImpl#getBufferPool <em>Buffer Pool</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolSizeExceptionImpl#getPartitions <em>Partitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWBufferPoolSizeExceptionImpl extends SQLObjectImpl implements LUWBufferPoolSizeException {
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
	 * The cached value of the '{@link #getPartitions() <em>Partitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitions()
	 * @generated
	 * @ordered
	 */
	protected EList partitions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWBufferPoolSizeExceptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_BUFFER_POOL_SIZE_EXCEPTION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWBufferPool getBufferPool() {
		if (eContainerFeatureID() != LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL) return null;
		return (LUWBufferPool)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBufferPool(LUWBufferPool newBufferPool, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBufferPool, LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBufferPool(LUWBufferPool newBufferPool) {
		if (newBufferPool != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL && newBufferPool != null)) {
			if (EcoreUtil.isAncestor(this, newBufferPool))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBufferPool != null)
				msgs = ((InternalEObject)newBufferPool).eInverseAdd(this, LUWPackage.LUW_BUFFER_POOL__SIZE_EXCEPTION, LUWBufferPool.class, msgs);
			msgs = basicSetBufferPool(newBufferPool, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL, newBufferPool, newBufferPool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartitions() {
		if (partitions == null) {
			partitions = new EObjectWithInverseResolvingEList(LUWDatabasePartition.class, this, LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS, LUWPackage.LUW_DATABASE_PARTITION__SIZE_EXCEPTION);
		}
		return partitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBufferPool((LUWBufferPool)otherEnd, msgs);
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS:
				return ((InternalEList)getPartitions()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL:
				return basicSetBufferPool(null, msgs);
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS:
				return ((InternalEList)getPartitions()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_BUFFER_POOL__SIZE_EXCEPTION, LUWBufferPool.class, msgs);
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
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__SIZE:
				return new Integer(getSize());
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL:
				return getBufferPool();
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS:
				return getPartitions();
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
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__SIZE:
				setSize(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL:
				setBufferPool((LUWBufferPool)newValue);
				return;
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS:
				getPartitions().clear();
				getPartitions().addAll((Collection)newValue);
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
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL:
				setBufferPool((LUWBufferPool)null);
				return;
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS:
				getPartitions().clear();
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
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__SIZE:
				return size != SIZE_EDEFAULT;
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL:
				return getBufferPool() != null;
			case LUWPackage.LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS:
				return partitions != null && !partitions.isEmpty();
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
		result.append(" (size: "); //$NON-NLS-1$
		result.append(size);
		result.append(')');
		return result.toString();
	}

} //LUWBufferPoolSizeExceptionImpl
