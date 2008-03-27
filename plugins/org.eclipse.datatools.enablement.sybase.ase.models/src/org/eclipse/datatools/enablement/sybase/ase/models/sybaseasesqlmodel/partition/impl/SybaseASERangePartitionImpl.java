/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASERangePartitionImpl.java,v 1.6 2007/07/06 08:40:15 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition;

import org.eclipse.datatools.modelbase.sql.tables.Column;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Range Partition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERangePartitionImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERangePartitionImpl#getRangePartitionItems <em>Range Partition Items</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASERangePartitionImpl extends SybaseASEPartitionImpl implements SybaseASERangePartition 
{
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
     * The cached value of the '{@link #getRangePartitionItems() <em>Range Partition Items</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRangePartitionItems()
     * @generated
     * @ordered
     */
	protected EList rangePartitionItems;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASERangePartitionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return PartitionPackage.Literals.SYBASE_ASE_RANGE_PARTITION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getColumns() {
        if (columns == null)
        {
            columns = new EObjectResolvingEList(Column.class, this, PartitionPackage.SYBASE_ASE_RANGE_PARTITION__COLUMNS);
        }
        return columns;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getRangePartitionItems() {
        if (rangePartitionItems == null)
        {
            rangePartitionItems = new EObjectResolvingEList(ListRangePartitionItem.class, this, PartitionPackage.SYBASE_ASE_RANGE_PARTITION__RANGE_PARTITION_ITEMS);
        }
        return rangePartitionItems;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION__COLUMNS:
                return getColumns();
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION__RANGE_PARTITION_ITEMS:
                return getRangePartitionItems();
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
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION__COLUMNS:
                getColumns().clear();
                getColumns().addAll((Collection)newValue);
                return;
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION__RANGE_PARTITION_ITEMS:
                getRangePartitionItems().clear();
                getRangePartitionItems().addAll((Collection)newValue);
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
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION__COLUMNS:
                getColumns().clear();
                return;
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION__RANGE_PARTITION_ITEMS:
                getRangePartitionItems().clear();
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
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION__COLUMNS:
                return columns != null && !columns.isEmpty();
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION__RANGE_PARTITION_ITEMS:
                return rangePartitionItems != null && !rangePartitionItems.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //SybaseASERangePartitionImpl
