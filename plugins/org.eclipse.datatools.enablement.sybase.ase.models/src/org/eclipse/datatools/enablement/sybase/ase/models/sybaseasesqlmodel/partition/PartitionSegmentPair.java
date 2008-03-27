/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionSegmentPair.java,v 1.7 2007/07/06 08:40:21 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Partition Segment Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair#getPartitionName <em>Partition Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair#getSegment <em>Segment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getPartitionSegmentPair()
 * @model
 * @generated
 */
public interface PartitionSegmentPair extends SQLObject {
	/**
     * Returns the value of the '<em><b>Partition Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Partition Name</em>' attribute.
     * @see #setPartitionName(String)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getPartitionSegmentPair_PartitionName()
     * @model
     * @generated
     */
	String getPartitionName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair#getPartitionName <em>Partition Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partition Name</em>' attribute.
     * @see #getPartitionName()
     * @generated
     */
	void setPartitionName(String value);

	/**
     * Returns the value of the '<em><b>Segment</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segment</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Segment</em>' reference.
     * @see #setSegment(SybaseASESegment)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getPartitionSegmentPair_Segment()
     * @model required="true"
     * @generated
     */
	SybaseASESegment getSegment();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair#getSegment <em>Segment</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Segment</em>' reference.
     * @see #getSegment()
     * @generated
     */
	void setSegment(SybaseASESegment value);

} // PartitionSegmentPair
