/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionNumInSegments.java,v 1.7 2007/07/06 08:40:22 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition;

import org.eclipse.emf.common.util.EList;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Partition Num In Segments</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments#getPartitionNumb <em>Partition Numb</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments#getSegment <em>Segment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getPartitionNumInSegments()
 * @model
 * @generated
 */
public interface PartitionNumInSegments extends SQLObject {
	/**
     * Returns the value of the '<em><b>Partition Numb</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Numb</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Partition Numb</em>' attribute.
     * @see #setPartitionNumb(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getPartitionNumInSegments_PartitionNumb()
     * @model
     * @generated
     */
	int getPartitionNumb();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments#getPartitionNumb <em>Partition Numb</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partition Numb</em>' attribute.
     * @see #getPartitionNumb()
     * @generated
     */
	void setPartitionNumb(int value);

	/**
     * Returns the value of the '<em><b>Segment</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segment</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Segment</em>' reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getPartitionNumInSegments_Segment()
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment" required="true"
     * @generated
     */
	EList getSegment();

} // PartitionNumInSegments
