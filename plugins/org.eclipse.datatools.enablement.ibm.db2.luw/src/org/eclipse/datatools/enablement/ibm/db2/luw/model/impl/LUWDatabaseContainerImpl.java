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
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.datatools.enablement.ibm.db2.model.UnitType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl#getContainerType <em>Container Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl#getSizeInPages <em>Size In Pages</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl#getSizeUnits <em>Size Units</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl#getTableSpace <em>Table Space</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl#getPartitions <em>Partitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWDatabaseContainerImpl extends SQLObjectImpl implements LUWDatabaseContainer {
	/**
	 * The default value of the '{@link #getContainerType() <em>Container Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainerType()
	 * @generated
	 * @ordered
	 */
	protected static final LUWContainerType CONTAINER_TYPE_EDEFAULT = LUWContainerType.DEVICE_LITERAL;

	/**
	 * The cached value of the '{@link #getContainerType() <em>Container Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainerType()
	 * @generated
	 * @ordered
	 */
	protected LUWContainerType containerType = CONTAINER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSizeInPages() <em>Size In Pages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeInPages()
	 * @generated
	 * @ordered
	 */
	protected static final int SIZE_IN_PAGES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSizeInPages() <em>Size In Pages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeInPages()
	 * @generated
	 * @ordered
	 */
	protected int sizeInPages = SIZE_IN_PAGES_EDEFAULT;

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
	 * The default value of the '{@link #getSizeUnits() <em>Size Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeUnits()
	 * @generated
	 * @ordered
	 */
	protected static final UnitType SIZE_UNITS_EDEFAULT = UnitType.K_LITERAL;

	/**
	 * The cached value of the '{@link #getSizeUnits() <em>Size Units</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSizeUnits()
	 * @generated
	 * @ordered
	 */
	protected UnitType sizeUnits = SIZE_UNITS_EDEFAULT;

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
	protected LUWDatabaseContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_DATABASE_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWContainerType getContainerType() {
		return containerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainerType(LUWContainerType newContainerType) {
		LUWContainerType oldContainerType = containerType;
		containerType = newContainerType == null ? CONTAINER_TYPE_EDEFAULT : newContainerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_CONTAINER__CONTAINER_TYPE, oldContainerType, containerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSizeInPages() {
		return sizeInPages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSizeInPages(int newSizeInPages) {
		int oldSizeInPages = sizeInPages;
		sizeInPages = newSizeInPages;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_CONTAINER__SIZE_IN_PAGES, oldSizeInPages, sizeInPages));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_CONTAINER__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitType getSizeUnits() {
		return sizeUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSizeUnits(UnitType newSizeUnits) {
		UnitType oldSizeUnits = sizeUnits;
		sizeUnits = newSizeUnits == null ? SIZE_UNITS_EDEFAULT : newSizeUnits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_CONTAINER__SIZE_UNITS, oldSizeUnits, sizeUnits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace getTableSpace() {
		if (eContainerFeatureID() != LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE) return null;
		return (LUWTableSpace)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTableSpace(LUWTableSpace newTableSpace, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTableSpace, LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTableSpace(LUWTableSpace newTableSpace) {
		if (newTableSpace != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE && newTableSpace != null)) {
			if (EcoreUtil.isAncestor(this, newTableSpace))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTableSpace != null)
				msgs = ((InternalEObject)newTableSpace).eInverseAdd(this, LUWPackage.LUW_TABLE_SPACE__CONTAINERS, LUWTableSpace.class, msgs);
			msgs = basicSetTableSpace(newTableSpace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE, newTableSpace, newTableSpace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartitions() {
		if (partitions == null) {
			partitions = new EObjectWithInverseResolvingEList.ManyInverse(LUWDatabasePartition.class, this, LUWPackage.LUW_DATABASE_CONTAINER__PARTITIONS, LUWPackage.LUW_DATABASE_PARTITION__CONTAINERS);
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
			case LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTableSpace((LUWTableSpace)otherEnd, msgs);
			case LUWPackage.LUW_DATABASE_CONTAINER__PARTITIONS:
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
			case LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE:
				return basicSetTableSpace(null, msgs);
			case LUWPackage.LUW_DATABASE_CONTAINER__PARTITIONS:
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
			case LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__CONTAINERS, LUWTableSpace.class, msgs);
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
			case LUWPackage.LUW_DATABASE_CONTAINER__CONTAINER_TYPE:
				return getContainerType();
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE_IN_PAGES:
				return new Integer(getSizeInPages());
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE:
				return new Integer(getSize());
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE_UNITS:
				return getSizeUnits();
			case LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE:
				return getTableSpace();
			case LUWPackage.LUW_DATABASE_CONTAINER__PARTITIONS:
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
			case LUWPackage.LUW_DATABASE_CONTAINER__CONTAINER_TYPE:
				setContainerType((LUWContainerType)newValue);
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE_IN_PAGES:
				setSizeInPages(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE:
				setSize(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE_UNITS:
				setSizeUnits((UnitType)newValue);
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE:
				setTableSpace((LUWTableSpace)newValue);
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__PARTITIONS:
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
			case LUWPackage.LUW_DATABASE_CONTAINER__CONTAINER_TYPE:
				setContainerType(CONTAINER_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE_IN_PAGES:
				setSizeInPages(SIZE_IN_PAGES_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE_UNITS:
				setSizeUnits(SIZE_UNITS_EDEFAULT);
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE:
				setTableSpace((LUWTableSpace)null);
				return;
			case LUWPackage.LUW_DATABASE_CONTAINER__PARTITIONS:
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
			case LUWPackage.LUW_DATABASE_CONTAINER__CONTAINER_TYPE:
				return containerType != CONTAINER_TYPE_EDEFAULT;
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE_IN_PAGES:
				return sizeInPages != SIZE_IN_PAGES_EDEFAULT;
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE:
				return size != SIZE_EDEFAULT;
			case LUWPackage.LUW_DATABASE_CONTAINER__SIZE_UNITS:
				return sizeUnits != SIZE_UNITS_EDEFAULT;
			case LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE:
				return getTableSpace() != null;
			case LUWPackage.LUW_DATABASE_CONTAINER__PARTITIONS:
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
		result.append(" (containerType: "); //$NON-NLS-1$
		result.append(containerType);
		result.append(", sizeInPages: "); //$NON-NLS-1$
		result.append(sizeInPages);
		result.append(", size: "); //$NON-NLS-1$
		result.append(size);
		result.append(", sizeUnits: "); //$NON-NLS-1$
		result.append(sizeUnits);
		result.append(')');
		return result.toString();
	}

} //LUWDatabaseContainerImpl
