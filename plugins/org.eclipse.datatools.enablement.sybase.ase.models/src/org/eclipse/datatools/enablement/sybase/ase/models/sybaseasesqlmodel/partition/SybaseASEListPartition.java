/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEListPartition.java,v 1.6 2007/07/06 08:40:22 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE List Partition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition#getColumn <em>Column</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition#getListPartitionItems <em>List Partition Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASEListPartition()
 * @model
 * @generated
 */
public interface SybaseASEListPartition extends SybaseASEPartition, SQLObject {
	/**
     * Returns the value of the '<em><b>Column</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Column</em>' reference.
     * @see #setColumn(SybaseASEColumn)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASEListPartition_Column()
     * @model required="true"
     * @generated
     */
	SybaseASEColumn getColumn();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition#getColumn <em>Column</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column</em>' reference.
     * @see #getColumn()
     * @generated
     */
	void setColumn(SybaseASEColumn value);

	/**
     * Returns the value of the '<em><b>List Partition Items</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List Partition Items</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>List Partition Items</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASEListPartition_ListPartitionItems()
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem" required="true"
     * @generated
     */
	EList getListPartitionItems();

} // SybaseASEListPartition
