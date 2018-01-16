/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionAdapterFactory.java,v 1.1 2008/03/27 07:41:14 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.util;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.*;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage
 * @generated
 */
public class PartitionAdapterFactory extends AdapterFactoryImpl 
{
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PartitionPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartitionAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PartitionPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PartitionSwitch modelSwitch =
		new PartitionSwitch() {
			public Object caseSybaseASEPartition(SybaseASEPartition object) {
				return createSybaseASEPartitionAdapter();
			}
			public Object caseSybaseASERangePartition(SybaseASERangePartition object) {
				return createSybaseASERangePartitionAdapter();
			}
			public Object caseSybaseASEHashPartition(SybaseASEHashPartition object) {
				return createSybaseASEHashPartitionAdapter();
			}
			public Object caseSybaseASEListPartition(SybaseASEListPartition object) {
				return createSybaseASEListPartitionAdapter();
			}
			public Object caseSybaseASERoundrobinPartition(SybaseASERoundrobinPartition object) {
				return createSybaseASERoundrobinPartitionAdapter();
			}
			public Object casePartitionSegmentPair(PartitionSegmentPair object) {
				return createPartitionSegmentPairAdapter();
			}
			public Object casePartitionNumInSegments(PartitionNumInSegments object) {
				return createPartitionNumInSegmentsAdapter();
			}
			public Object caseListRangePartitionItem(ListRangePartitionItem object) {
				return createListRangePartitionItemAdapter();
			}
			public Object caseEModelElement(EModelElement object) {
				return createEModelElementAdapter();
			}
			public Object caseENamedElement(ENamedElement object) {
				return createENamedElementAdapter();
			}
			public Object caseSQLObject(SQLObject object) {
				return createSQLObjectAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition <em>Sybase ASE Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition
	 * @generated
	 */
	public Adapter createSybaseASEPartitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition <em>Sybase ASE Range Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition
	 * @generated
	 */
	public Adapter createSybaseASERangePartitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition <em>Sybase ASE Hash Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition
	 * @generated
	 */
	public Adapter createSybaseASEHashPartitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition <em>Sybase ASE List Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition
	 * @generated
	 */
	public Adapter createSybaseASEListPartitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition <em>Sybase ASE Roundrobin Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition
	 * @generated
	 */
	public Adapter createSybaseASERoundrobinPartitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair <em>Segment Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair
	 * @generated
	 */
	public Adapter createPartitionSegmentPairAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments <em>Num In Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments
	 * @generated
	 */
	public Adapter createPartitionNumInSegmentsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem <em>List Range Partition Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem
	 * @generated
	 */
	public Adapter createListRangePartitionItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EModelElement
	 * @generated
	 */
	public Adapter createEModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ENamedElement <em>ENamed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.ENamedElement
	 * @generated
	 */
	public Adapter createENamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject <em>SQL Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject
	 * @generated
	 */
	public Adapter createSQLObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PartitionAdapterFactory
