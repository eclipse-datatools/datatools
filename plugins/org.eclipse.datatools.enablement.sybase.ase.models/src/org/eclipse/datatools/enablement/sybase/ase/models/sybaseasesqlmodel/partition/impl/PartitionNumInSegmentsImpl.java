/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionNumInSegmentsImpl.java,v 1.7 2007/07/06 08:40:15 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Partition Num In Segments</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionNumInSegmentsImpl#getPartitionNumb <em>Partition Numb</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionNumInSegmentsImpl#getSegment <em>Segment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PartitionNumInSegmentsImpl extends SQLObjectImpl implements PartitionNumInSegments 
{
	/**
     * The default value of the '{@link #getPartitionNumb() <em>Partition Numb</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPartitionNumb()
     * @generated
     * @ordered
     */
	protected static final int PARTITION_NUMB_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getPartitionNumb() <em>Partition Numb</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPartitionNumb()
     * @generated
     * @ordered
     */
	protected int partitionNumb = PARTITION_NUMB_EDEFAULT;

	/**
     * The cached value of the '{@link #getSegment() <em>Segment</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSegment()
     * @generated
     * @ordered
     */
	protected EList segment;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected PartitionNumInSegmentsImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return PartitionPackage.Literals.PARTITION_NUM_IN_SEGMENTS;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getPartitionNumb() {
        return partitionNumb;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPartitionNumb(int newPartitionNumb) {
        int oldPartitionNumb = partitionNumb;
        partitionNumb = newPartitionNumb;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PartitionPackage.PARTITION_NUM_IN_SEGMENTS__PARTITION_NUMB, oldPartitionNumb, partitionNumb));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getSegment() {
        if (segment == null)
        {
            segment = new EObjectResolvingEList(SybaseASESegment.class, this, PartitionPackage.PARTITION_NUM_IN_SEGMENTS__SEGMENT);
        }
        return segment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS__PARTITION_NUMB:
                return new Integer(getPartitionNumb());
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS__SEGMENT:
                return getSegment();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID)
        {
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS__PARTITION_NUMB:
                setPartitionNumb(((Integer)newValue).intValue());
                return;
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS__SEGMENT:
                getSegment().clear();
                getSegment().addAll((Collection)newValue);
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
        switch (featureID)
        {
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS__PARTITION_NUMB:
                setPartitionNumb(PARTITION_NUMB_EDEFAULT);
                return;
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS__SEGMENT:
                getSegment().clear();
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
        switch (featureID)
        {
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS__PARTITION_NUMB:
                return partitionNumb != PARTITION_NUMB_EDEFAULT;
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS__SEGMENT:
                return segment != null && !segment.isEmpty();
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (partitionNumb: "); //$NON-NLS-1$
        result.append(partitionNumb);
        result.append(')');
        return result.toString();
    }

} //PartitionNumInSegmentsImpl
