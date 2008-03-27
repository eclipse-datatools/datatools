/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASERangePartition.java,v 1.6 2007/07/06 08:40:21 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Range Partition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition#getRangePartitionItems <em>Range Partition Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASERangePartition()
 * @model
 * @generated
 */
public interface SybaseASERangePartition extends SybaseASEPartition, SQLObject {
	/**
     * Returns the value of the '<em><b>Columns</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.Column}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Columns</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASERangePartition_Columns()
     * @model type="org.eclipse.datatools.modelbase.sql.tables.Column" required="true"
     * @generated
     */
	EList getColumns();

	/**
     * Returns the value of the '<em><b>Range Partition Items</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Range Partition Items</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Range Partition Items</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASERangePartition_RangePartitionItems()
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem"
     * @generated
     */
	EList getRangePartitionItems();

} // SybaseASERangePartition
