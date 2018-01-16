/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEHashPartitionImpl.java,v 1.1 2008/03/27 07:41:12 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition;

import org.eclipse.datatools.modelbase.sql.tables.Column;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Hash Partition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEHashPartitionImpl#getColumns <em>Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEHashPartitionImpl#getPartitionSegmentPairs <em>Partition Segment Pairs</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEHashPartitionImpl#getPartitionNumInSegments <em>Partition Num In Segments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEHashPartitionImpl extends SybaseASEPartitionImpl implements SybaseASEHashPartition 
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
	 * The cached value of the '{@link #getPartitionSegmentPairs() <em>Partition Segment Pairs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionSegmentPairs()
	 * @generated
	 * @ordered
	 */
	protected EList partitionSegmentPairs;

	/**
	 * The cached value of the '{@link #getPartitionNumInSegments() <em>Partition Num In Segments</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartitionNumInSegments()
	 * @generated
	 * @ordered
	 */
	protected PartitionNumInSegments partitionNumInSegments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASEHashPartitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PartitionPackage.Literals.SYBASE_ASE_HASH_PARTITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getColumns() {
		if (columns == null) {
			columns = new EObjectResolvingEList(Column.class, this, PartitionPackage.SYBASE_ASE_HASH_PARTITION__COLUMNS);
		}
		return columns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPartitionSegmentPairs() {
		if (partitionSegmentPairs == null) {
			partitionSegmentPairs = new EObjectResolvingEList(PartitionSegmentPair.class, this, PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_SEGMENT_PAIRS);
		}
		return partitionSegmentPairs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartitionNumInSegments getPartitionNumInSegments() {
		if (partitionNumInSegments != null && partitionNumInSegments.eIsProxy()) {
			InternalEObject oldPartitionNumInSegments = (InternalEObject)partitionNumInSegments;
			partitionNumInSegments = (PartitionNumInSegments)eResolveProxy(oldPartitionNumInSegments);
			if (partitionNumInSegments != oldPartitionNumInSegments) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_NUM_IN_SEGMENTS, oldPartitionNumInSegments, partitionNumInSegments));
			}
		}
		return partitionNumInSegments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PartitionNumInSegments basicGetPartitionNumInSegments() {
		return partitionNumInSegments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPartitionNumInSegments(PartitionNumInSegments newPartitionNumInSegments) {
		PartitionNumInSegments oldPartitionNumInSegments = partitionNumInSegments;
		partitionNumInSegments = newPartitionNumInSegments;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_NUM_IN_SEGMENTS, oldPartitionNumInSegments, partitionNumInSegments));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__COLUMNS:
				return getColumns();
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_SEGMENT_PAIRS:
				return getPartitionSegmentPairs();
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_NUM_IN_SEGMENTS:
				if (resolve) return getPartitionNumInSegments();
				return basicGetPartitionNumInSegments();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__COLUMNS:
				getColumns().clear();
				getColumns().addAll((Collection)newValue);
				return;
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_SEGMENT_PAIRS:
				getPartitionSegmentPairs().clear();
				getPartitionSegmentPairs().addAll((Collection)newValue);
				return;
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_NUM_IN_SEGMENTS:
				setPartitionNumInSegments((PartitionNumInSegments)newValue);
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
		switch (featureID) {
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__COLUMNS:
				getColumns().clear();
				return;
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_SEGMENT_PAIRS:
				getPartitionSegmentPairs().clear();
				return;
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_NUM_IN_SEGMENTS:
				setPartitionNumInSegments((PartitionNumInSegments)null);
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
		switch (featureID) {
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__COLUMNS:
				return columns != null && !columns.isEmpty();
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_SEGMENT_PAIRS:
				return partitionSegmentPairs != null && !partitionSegmentPairs.isEmpty();
			case PartitionPackage.SYBASE_ASE_HASH_PARTITION__PARTITION_NUM_IN_SEGMENTS:
				return partitionNumInSegments != null;
		}
		return super.eIsSet(featureID);
	}

} //SybaseASEHashPartitionImpl
