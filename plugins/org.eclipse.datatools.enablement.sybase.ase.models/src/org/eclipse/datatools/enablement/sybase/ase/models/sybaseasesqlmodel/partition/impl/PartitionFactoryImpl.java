/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionFactoryImpl.java,v 1.7 2007/07/06 08:40:15 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PartitionFactoryImpl extends EFactoryImpl implements PartitionFactory 
{
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static PartitionFactory init() {
        try
        {
            PartitionFactory thePartitionFactory = (PartitionFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/connectivity/sqm/sybase/ase/sybasease../../org.eclipse.datatools.modelbase.sql/model/../../org.eclipse.datatools.modelbase.sql/model/sqlmodel.ecore/partition.ecore"); //$NON-NLS-1$ 
            if (thePartitionFactory != null)
            {
                return thePartitionFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PartitionFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PartitionFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID())
        {
            case PartitionPackage.SYBASE_ASE_PARTITION: return createSybaseASEPartition();
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION: return createSybaseASERangePartition();
            case PartitionPackage.SYBASE_ASE_HASH_PARTITION: return createSybaseASEHashPartition();
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION: return createSybaseASEListPartition();
            case PartitionPackage.SYBASE_ASE_ROUNDROBIN_PARTITION: return createSybaseASERoundrobinPartition();
            case PartitionPackage.PARTITION_SEGMENT_PAIR: return createPartitionSegmentPair();
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS: return createPartitionNumInSegments();
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM: return createListRangePartitionItem();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEPartition createSybaseASEPartition() {
        SybaseASEPartitionImpl sybaseASEPartition = new SybaseASEPartitionImpl();
        return sybaseASEPartition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASERangePartition createSybaseASERangePartition() {
        SybaseASERangePartitionImpl sybaseASERangePartition = new SybaseASERangePartitionImpl();
        return sybaseASERangePartition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEHashPartition createSybaseASEHashPartition() {
        SybaseASEHashPartitionImpl sybaseASEHashPartition = new SybaseASEHashPartitionImpl();
        return sybaseASEHashPartition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEListPartition createSybaseASEListPartition() {
        SybaseASEListPartitionImpl sybaseASEListPartition = new SybaseASEListPartitionImpl();
        return sybaseASEListPartition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASERoundrobinPartition createSybaseASERoundrobinPartition() {
        SybaseASERoundrobinPartitionImpl sybaseASERoundrobinPartition = new SybaseASERoundrobinPartitionImpl();
        return sybaseASERoundrobinPartition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PartitionSegmentPair createPartitionSegmentPair() {
        PartitionSegmentPairImpl partitionSegmentPair = new PartitionSegmentPairImpl();
        return partitionSegmentPair;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PartitionNumInSegments createPartitionNumInSegments() {
        PartitionNumInSegmentsImpl partitionNumInSegments = new PartitionNumInSegmentsImpl();
        return partitionNumInSegments;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ListRangePartitionItem createListRangePartitionItem() {
        ListRangePartitionItemImpl listRangePartitionItem = new ListRangePartitionItemImpl();
        return listRangePartitionItem;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PartitionPackage getPartitionPackage() {
        return (PartitionPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	public static PartitionPackage getPackage() {
        return PartitionPackage.eINSTANCE;
    }

} //PartitionFactoryImpl
