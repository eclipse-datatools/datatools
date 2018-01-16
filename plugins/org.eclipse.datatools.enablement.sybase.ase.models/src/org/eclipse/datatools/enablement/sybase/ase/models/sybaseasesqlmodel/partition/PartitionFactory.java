/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionFactory.java,v 1.6 2007/07/06 08:40:21 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage
 * @generated
 */
public interface PartitionFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	PartitionFactory eINSTANCE = org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Sybase ASE Partition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Sybase ASE Partition</em>'.
     * @generated
     */
	SybaseASEPartition createSybaseASEPartition();

	/**
     * Returns a new object of class '<em>Sybase ASE Range Partition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Sybase ASE Range Partition</em>'.
     * @generated
     */
	SybaseASERangePartition createSybaseASERangePartition();

	/**
     * Returns a new object of class '<em>Sybase ASE Hash Partition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Sybase ASE Hash Partition</em>'.
     * @generated
     */
	SybaseASEHashPartition createSybaseASEHashPartition();

	/**
     * Returns a new object of class '<em>Sybase ASE List Partition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Sybase ASE List Partition</em>'.
     * @generated
     */
	SybaseASEListPartition createSybaseASEListPartition();

	/**
     * Returns a new object of class '<em>Sybase ASE Roundrobin Partition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Sybase ASE Roundrobin Partition</em>'.
     * @generated
     */
	SybaseASERoundrobinPartition createSybaseASERoundrobinPartition();

	/**
     * Returns a new object of class '<em>Segment Pair</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Segment Pair</em>'.
     * @generated
     */
	PartitionSegmentPair createPartitionSegmentPair();

	/**
     * Returns a new object of class '<em>Num In Segments</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Num In Segments</em>'.
     * @generated
     */
	PartitionNumInSegments createPartitionNumInSegments();

	/**
     * Returns a new object of class '<em>List Range Partition Item</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>List Range Partition Item</em>'.
     * @generated
     */
	ListRangePartitionItem createListRangePartitionItem();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	PartitionPackage getPartitionPackage();

} //PartitionFactory
