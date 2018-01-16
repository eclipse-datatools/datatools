/**
 * <copyright>
 * </copyright>
 *
 * $Id: ListRangePartitionItem.java,v 1.7 2007/07/06 08:40:21 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Range Partition Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getPartitionName <em>Partition Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getValues <em>Values</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getSegment <em>Segment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getListRangePartitionItem()
 * @model
 * @generated
 */
public interface ListRangePartitionItem extends SQLObject {
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
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getListRangePartitionItem_PartitionName()
     * @model
     * @generated
     */
	String getPartitionName();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getPartitionName <em>Partition Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partition Name</em>' attribute.
     * @see #getPartitionName()
     * @generated
     */
	void setPartitionName(String value);

	/**
     * Returns the value of the '<em><b>Values</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Values</em>' attribute list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getListRangePartitionItem_Values()
     * @model default="" required="true"
     * @generated
     */
	EList getValues();

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
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage#getListRangePartitionItem_Segment()
     * @model
     * @generated
     */
	SybaseASESegment getSegment();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getSegment <em>Segment</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Segment</em>' reference.
     * @see #getSegment()
     * @generated
     */
	void setSegment(SybaseASESegment value);

} // ListRangePartitionItem
