/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWDataPartitionImpl.java,v 1.9 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Partition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl#isLowInclusive <em>Low Inclusive</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl#isHighInclusive <em>High Inclusive</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl#getLOBDataTableSpace <em>LOB Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl#getRegularDataTableSpace <em>Regular Data Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl#getPartitionElements <em>Partition Elements</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl#getIndexDataTableSpace <em>Index Data Table Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWDataPartitionImpl extends SQLObjectImpl implements LUWDataPartition {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isLowInclusive() <em>Low Inclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLowInclusive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOW_INCLUSIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLowInclusive() <em>Low Inclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLowInclusive()
	 * @generated
	 * @ordered
	 */
	protected boolean lowInclusive = LOW_INCLUSIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #isHighInclusive() <em>High Inclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHighInclusive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIGH_INCLUSIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHighInclusive() <em>High Inclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHighInclusive()
	 * @generated
	 * @ordered
	 */
	protected boolean highInclusive = HIGH_INCLUSIVE_EDEFAULT;

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
	 * The cached value of the '{@link #getPartitionElements() <em>Partition Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionElements()
	 * @generated
	 * @ordered
	 */
	protected EList partitionElements;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWDataPartitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_DATA_PARTITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLowInclusive() {
		return lowInclusive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLowInclusive(boolean newLowInclusive) {
		boolean oldLowInclusive = lowInclusive;
		lowInclusive = newLowInclusive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__LOW_INCLUSIVE, oldLowInclusive, lowInclusive));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHighInclusive() {
		return highInclusive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHighInclusive(boolean newHighInclusive) {
		boolean oldHighInclusive = highInclusive;
		highInclusive = newHighInclusive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__HIGH_INCLUSIVE, oldHighInclusive, highInclusive));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE, oldLOBDataTableSpace, lobDataTableSpace));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE, oldLOBDataTableSpace, newLOBDataTableSpace);
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
				msgs = ((InternalEObject)lobDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION, LUWTableSpace.class, msgs);
			if (newLOBDataTableSpace != null)
				msgs = ((InternalEObject)newLOBDataTableSpace).eInverseAdd(this, LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION, LUWTableSpace.class, msgs);
			msgs = basicSetLOBDataTableSpace(newLOBDataTableSpace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE, newLOBDataTableSpace, newLOBDataTableSpace));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE, oldRegularDataTableSpace, regularDataTableSpace));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE, oldRegularDataTableSpace, newRegularDataTableSpace);
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
				msgs = ((InternalEObject)regularDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION, LUWTableSpace.class, msgs);
			if (newRegularDataTableSpace != null)
				msgs = ((InternalEObject)newRegularDataTableSpace).eInverseAdd(this, LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION, LUWTableSpace.class, msgs);
			msgs = basicSetRegularDataTableSpace(newRegularDataTableSpace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE, newRegularDataTableSpace, newRegularDataTableSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartitionElements() {
		if (partitionElements == null) {
			partitionElements = new EObjectWithInverseResolvingEList(LUWPartitionElement.class, this, LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS, LUWPackage.LUW_PARTITION_ELEMENT__PARTITION);
		}
		return partitionElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageTable getTable() {
		if (eContainerFeatureID() != LUWPackage.LUW_DATA_PARTITION__TABLE) return null;
		return (LUWStorageTable)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTable(LUWStorageTable newTable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTable, LUWPackage.LUW_DATA_PARTITION__TABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(LUWStorageTable newTable) {
		if (newTable != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_DATA_PARTITION__TABLE && newTable != null)) {
			if (EcoreUtil.isAncestor(this, newTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, LUWPackage.LUW_STORAGE_TABLE__DATA_PARTITIONS, LUWStorageTable.class, msgs);
			msgs = basicSetTable(newTable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__TABLE, newTable, newTable));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE, oldIndexDataTableSpace, indexDataTableSpace));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE, oldIndexDataTableSpace, newIndexDataTableSpace);
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
				msgs = ((InternalEObject)indexDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION, LUWTableSpace.class, msgs);
			if (newIndexDataTableSpace != null)
				msgs = ((InternalEObject)newIndexDataTableSpace).eInverseAdd(this, LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION, LUWTableSpace.class, msgs);
			msgs = basicSetIndexDataTableSpace(newIndexDataTableSpace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE, newIndexDataTableSpace, newIndexDataTableSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE:
				if (lobDataTableSpace != null)
					msgs = ((InternalEObject)lobDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION, LUWTableSpace.class, msgs);
				return basicSetLOBDataTableSpace((LUWTableSpace)otherEnd, msgs);
			case LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE:
				if (regularDataTableSpace != null)
					msgs = ((InternalEObject)regularDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION, LUWTableSpace.class, msgs);
				return basicSetRegularDataTableSpace((LUWTableSpace)otherEnd, msgs);
			case LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS:
				return ((InternalEList)getPartitionElements()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_DATA_PARTITION__TABLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTable((LUWStorageTable)otherEnd, msgs);
			case LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE:
				if (indexDataTableSpace != null)
					msgs = ((InternalEObject)indexDataTableSpace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION, LUWTableSpace.class, msgs);
				return basicSetIndexDataTableSpace((LUWTableSpace)otherEnd, msgs);
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
			case LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE:
				return basicSetLOBDataTableSpace(null, msgs);
			case LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE:
				return basicSetRegularDataTableSpace(null, msgs);
			case LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS:
				return ((InternalEList)getPartitionElements()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_DATA_PARTITION__TABLE:
				return basicSetTable(null, msgs);
			case LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE:
				return basicSetIndexDataTableSpace(null, msgs);
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
			case LUWPackage.LUW_DATA_PARTITION__TABLE:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_STORAGE_TABLE__DATA_PARTITIONS, LUWStorageTable.class, msgs);
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
			case LUWPackage.LUW_DATA_PARTITION__ID:
				return new Integer(getId());
			case LUWPackage.LUW_DATA_PARTITION__LOW_INCLUSIVE:
				return isLowInclusive() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_DATA_PARTITION__HIGH_INCLUSIVE:
				return isHighInclusive() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE:
				if (resolve) return getLOBDataTableSpace();
				return basicGetLOBDataTableSpace();
			case LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE:
				if (resolve) return getRegularDataTableSpace();
				return basicGetRegularDataTableSpace();
			case LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS:
				return getPartitionElements();
			case LUWPackage.LUW_DATA_PARTITION__TABLE:
				return getTable();
			case LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE:
				if (resolve) return getIndexDataTableSpace();
				return basicGetIndexDataTableSpace();
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
			case LUWPackage.LUW_DATA_PARTITION__ID:
				setId(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_DATA_PARTITION__LOW_INCLUSIVE:
				setLowInclusive(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_DATA_PARTITION__HIGH_INCLUSIVE:
				setHighInclusive(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE:
				setLOBDataTableSpace((LUWTableSpace)newValue);
				return;
			case LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE:
				setRegularDataTableSpace((LUWTableSpace)newValue);
				return;
			case LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS:
				getPartitionElements().clear();
				getPartitionElements().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_DATA_PARTITION__TABLE:
				setTable((LUWStorageTable)newValue);
				return;
			case LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE:
				setIndexDataTableSpace((LUWTableSpace)newValue);
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
			case LUWPackage.LUW_DATA_PARTITION__ID:
				setId(ID_EDEFAULT);
				return;
			case LUWPackage.LUW_DATA_PARTITION__LOW_INCLUSIVE:
				setLowInclusive(LOW_INCLUSIVE_EDEFAULT);
				return;
			case LUWPackage.LUW_DATA_PARTITION__HIGH_INCLUSIVE:
				setHighInclusive(HIGH_INCLUSIVE_EDEFAULT);
				return;
			case LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE:
				setLOBDataTableSpace((LUWTableSpace)null);
				return;
			case LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE:
				setRegularDataTableSpace((LUWTableSpace)null);
				return;
			case LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS:
				getPartitionElements().clear();
				return;
			case LUWPackage.LUW_DATA_PARTITION__TABLE:
				setTable((LUWStorageTable)null);
				return;
			case LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE:
				setIndexDataTableSpace((LUWTableSpace)null);
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
			case LUWPackage.LUW_DATA_PARTITION__ID:
				return id != ID_EDEFAULT;
			case LUWPackage.LUW_DATA_PARTITION__LOW_INCLUSIVE:
				return lowInclusive != LOW_INCLUSIVE_EDEFAULT;
			case LUWPackage.LUW_DATA_PARTITION__HIGH_INCLUSIVE:
				return highInclusive != HIGH_INCLUSIVE_EDEFAULT;
			case LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE:
				return lobDataTableSpace != null;
			case LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE:
				return regularDataTableSpace != null;
			case LUWPackage.LUW_DATA_PARTITION__PARTITION_ELEMENTS:
				return partitionElements != null && !partitionElements.isEmpty();
			case LUWPackage.LUW_DATA_PARTITION__TABLE:
				return getTable() != null;
			case LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE:
				return indexDataTableSpace != null;
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", lowInclusive: "); //$NON-NLS-1$
		result.append(lowInclusive);
		result.append(", highInclusive: "); //$NON-NLS-1$
		result.append(highInclusive);
		result.append(')');
		return result.toString();
	}

} //LUWDataPartitionImpl
