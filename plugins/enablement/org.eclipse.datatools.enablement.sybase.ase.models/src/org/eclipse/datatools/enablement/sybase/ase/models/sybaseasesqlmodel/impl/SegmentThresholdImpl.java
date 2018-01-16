/**
 * <copyright>
 * </copyright>
 *
 * $Id: SegmentThresholdImpl.java,v 1.7 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Segment Threshold</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SegmentThresholdImpl#getProcedureName <em>Procedure Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SegmentThresholdImpl#getFreeSpace <em>Free Space</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SegmentThresholdImpl extends SQLObjectImpl implements SegmentThreshold 
{
	/**
     * The default value of the '{@link #getProcedureName() <em>Procedure Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getProcedureName()
     * @generated
     * @ordered
     */
	protected static final String PROCEDURE_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getProcedureName() <em>Procedure Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getProcedureName()
     * @generated
     * @ordered
     */
	protected String procedureName = PROCEDURE_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getFreeSpace() <em>Free Space</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFreeSpace()
     * @generated
     * @ordered
     */
	protected static final int FREE_SPACE_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getFreeSpace() <em>Free Space</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFreeSpace()
     * @generated
     * @ordered
     */
	protected int freeSpace = FREE_SPACE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SegmentThresholdImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SEGMENT_THRESHOLD;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getProcedureName() {
        return procedureName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setProcedureName(String newProcedureName) {
        String oldProcedureName = procedureName;
        procedureName = newProcedureName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__PROCEDURE_NAME, oldProcedureName, procedureName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getFreeSpace() {
        return freeSpace;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setFreeSpace(int newFreeSpace) {
        int oldFreeSpace = freeSpace;
        freeSpace = newFreeSpace;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__FREE_SPACE, oldFreeSpace, freeSpace));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__PROCEDURE_NAME:
                return getProcedureName();
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__FREE_SPACE:
                return new Integer(getFreeSpace());
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
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__PROCEDURE_NAME:
                setProcedureName((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__FREE_SPACE:
                setFreeSpace(((Integer)newValue).intValue());
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
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__PROCEDURE_NAME:
                setProcedureName(PROCEDURE_NAME_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__FREE_SPACE:
                setFreeSpace(FREE_SPACE_EDEFAULT);
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
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__PROCEDURE_NAME:
                return PROCEDURE_NAME_EDEFAULT == null ? procedureName != null : !PROCEDURE_NAME_EDEFAULT.equals(procedureName);
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD__FREE_SPACE:
                return freeSpace != FREE_SPACE_EDEFAULT;
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
        result.append(" (procedureName: "); //$NON-NLS-1$
        result.append(procedureName);
        result.append(", freeSpace: "); //$NON-NLS-1$
        result.append(freeSpace);
        result.append(')');
        return result.toString();
    }

} //SegmentThresholdImpl
