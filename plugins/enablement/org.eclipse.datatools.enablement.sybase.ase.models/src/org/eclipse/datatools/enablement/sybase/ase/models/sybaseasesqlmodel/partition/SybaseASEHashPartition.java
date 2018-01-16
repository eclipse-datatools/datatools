/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEHashPartition.java,v 1.6 2007/07/06 08:40:21 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Hash Partition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getPartitionSegmentPairs <em>Partition Segment Pairs</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getPartitionNumInSegments <em>Partition Num In Segments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASEHashPartition()
 * @model
 * @generated
 */
public interface SybaseASEHashPartition extends SybaseASEPartition, SQLObject {
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
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASEHashPartition_Columns()
     * @model type="org.eclipse.datatools.modelbase.sql.tables.Column" required="true"
     * @generated
     */
	EList getColumns();

	/**
     * Returns the value of the '<em><b>Partition Segment Pairs</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Segment Pairs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Partition Segment Pairs</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASEHashPartition_PartitionSegmentPairs()
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair"
     * @generated
     */
	EList getPartitionSegmentPairs();

	/**
     * Returns the value of the '<em><b>Partition Num In Segments</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Num In Segments</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Partition Num In Segments</em>' reference.
     * @see #setPartitionNumInSegments(PartitionNumInSegments)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getSybaseASEHashPartition_PartitionNumInSegments()
     * @model
     * @generated
     */
	PartitionNumInSegments getPartitionNumInSegments();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getPartitionNumInSegments <em>Partition Num In Segments</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partition Num In Segments</em>' reference.
     * @see #getPartitionNumInSegments()
     * @generated
     */
	void setPartitionNumInSegments(PartitionNumInSegments value);

} // SybaseASEHashPartition
