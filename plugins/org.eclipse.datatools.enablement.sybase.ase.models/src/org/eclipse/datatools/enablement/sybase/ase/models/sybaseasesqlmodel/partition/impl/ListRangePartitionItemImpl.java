/**
 * <copyright>
 * </copyright>
 *
 * $Id: ListRangePartitionItemImpl.java,v 1.7 2007/07/06 08:40:15 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Range Partition Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.ListRangePartitionItemImpl#getPartitionName <em>Partition Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.ListRangePartitionItemImpl#getValues <em>Values</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.ListRangePartitionItemImpl#getSegment <em>Segment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ListRangePartitionItemImpl extends SQLObjectImpl implements ListRangePartitionItem 
{
	/**
     * The default value of the '{@link #getPartitionName() <em>Partition Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPartitionName()
     * @generated
     * @ordered
     */
	protected static final String PARTITION_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getPartitionName() <em>Partition Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPartitionName()
     * @generated
     * @ordered
     */
	protected String partitionName = PARTITION_NAME_EDEFAULT;

	/**
     * The cached value of the '{@link #getValues() <em>Values</em>}' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValues()
     * @generated
     * @ordered
     */
	protected EList values;

	/**
     * The cached value of the '{@link #getSegment() <em>Segment</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSegment()
     * @generated
     * @ordered
     */
	protected SybaseASESegment segment;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ListRangePartitionItemImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return PartitionPackage.Literals.LIST_RANGE_PARTITION_ITEM;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getPartitionName() {
        return partitionName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPartitionName(String newPartitionName) {
        String oldPartitionName = partitionName;
        partitionName = newPartitionName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PartitionPackage.LIST_RANGE_PARTITION_ITEM__PARTITION_NAME, oldPartitionName, partitionName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getValues() {
        if (values == null)
        {
            values = new EDataTypeUniqueEList(String.class, this, PartitionPackage.LIST_RANGE_PARTITION_ITEM__VALUES);
        }
        return values;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASESegment getSegment() {
        if (segment != null && segment.eIsProxy())
        {
            InternalEObject oldSegment = (InternalEObject)segment;
            segment = (SybaseASESegment)eResolveProxy(oldSegment);
            if (segment != oldSegment)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PartitionPackage.LIST_RANGE_PARTITION_ITEM__SEGMENT, oldSegment, segment));
            }
        }
        return segment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASESegment basicGetSegment() {
        return segment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSegment(SybaseASESegment newSegment) {
        SybaseASESegment oldSegment = segment;
        segment = newSegment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PartitionPackage.LIST_RANGE_PARTITION_ITEM__SEGMENT, oldSegment, segment));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__PARTITION_NAME:
                return getPartitionName();
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__VALUES:
                return getValues();
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__SEGMENT:
                if (resolve) return getSegment();
                return basicGetSegment();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID)
        {
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__PARTITION_NAME:
                setPartitionName((String)newValue);
                return;
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__VALUES:
                getValues().clear();
                getValues().addAll((Collection)newValue);
                return;
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__SEGMENT:
                setSegment((SybaseASESegment)newValue);
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
        switch (featureID)
        {
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__PARTITION_NAME:
                setPartitionName(PARTITION_NAME_EDEFAULT);
                return;
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__VALUES:
                getValues().clear();
                return;
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__SEGMENT:
                setSegment((SybaseASESegment)null);
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
        switch (featureID)
        {
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__PARTITION_NAME:
                return PARTITION_NAME_EDEFAULT == null ? partitionName != null : !PARTITION_NAME_EDEFAULT.equals(partitionName);
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__VALUES:
                return values != null && !values.isEmpty();
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM__SEGMENT:
                return segment != null;
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
        result.append(" (partitionName: "); //$NON-NLS-1$
        result.append(partitionName);
        result.append(", values: "); //$NON-NLS-1$
        result.append(values);
        result.append(')');
        return result.toString();
    }

} //ListRangePartitionItemImpl
