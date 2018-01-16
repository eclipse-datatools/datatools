/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEListPartitionImpl.java,v 1.6 2007/07/06 08:40:15 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE List Partition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEListPartitionImpl#getColumn <em>Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEListPartitionImpl#getListPartitionItems <em>List Partition Items</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEListPartitionImpl extends SybaseASEPartitionImpl implements SybaseASEListPartition 
{
	/**
     * The cached value of the '{@link #getColumn() <em>Column</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getColumn()
     * @generated
     * @ordered
     */
	protected SybaseASEColumn column;

	/**
     * The cached value of the '{@link #getListPartitionItems() <em>List Partition Items</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getListPartitionItems()
     * @generated
     * @ordered
     */
	protected EList listPartitionItems;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEListPartitionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return PartitionPackage.Literals.SYBASE_ASE_LIST_PARTITION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEColumn getColumn() {
        if (column != null && column.eIsProxy())
        {
            InternalEObject oldColumn = (InternalEObject)column;
            column = (SybaseASEColumn)eResolveProxy(oldColumn);
            if (column != oldColumn)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PartitionPackage.SYBASE_ASE_LIST_PARTITION__COLUMN, oldColumn, column));
            }
        }
        return column;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEColumn basicGetColumn() {
        return column;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setColumn(SybaseASEColumn newColumn) {
        SybaseASEColumn oldColumn = column;
        column = newColumn;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PartitionPackage.SYBASE_ASE_LIST_PARTITION__COLUMN, oldColumn, column));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getListPartitionItems() {
        if (listPartitionItems == null)
        {
            listPartitionItems = new EObjectResolvingEList(ListRangePartitionItem.class, this, PartitionPackage.SYBASE_ASE_LIST_PARTITION__LIST_PARTITION_ITEMS);
        }
        return listPartitionItems;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION__COLUMN:
                if (resolve) return getColumn();
                return basicGetColumn();
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION__LIST_PARTITION_ITEMS:
                return getListPartitionItems();
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
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION__COLUMN:
                setColumn((SybaseASEColumn)newValue);
                return;
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION__LIST_PARTITION_ITEMS:
                getListPartitionItems().clear();
                getListPartitionItems().addAll((Collection)newValue);
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
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION__COLUMN:
                setColumn((SybaseASEColumn)null);
                return;
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION__LIST_PARTITION_ITEMS:
                getListPartitionItems().clear();
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
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION__COLUMN:
                return column != null;
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION__LIST_PARTITION_ITEMS:
                return listPartitionItems != null && !listPartitionItems.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SybaseASEListPartitionImpl
