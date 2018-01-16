/**
 * <copyright>
 * </copyright>
 *
 * $Id: LockPromotionInfoImpl.java,v 1.7 2007/07/06 08:40:19 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lock Promotion Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.LockPromotionInfoImpl#isRowLockPromotion <em>Row Lock Promotion</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.LockPromotionInfoImpl#getLWM <em>LWM</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.LockPromotionInfoImpl#getHWM <em>HWM</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.LockPromotionInfoImpl#getPCT <em>PCT</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LockPromotionInfoImpl extends SQLObjectImpl implements LockPromotionInfo 
{
	/**
     * The default value of the '{@link #isRowLockPromotion() <em>Row Lock Promotion</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isRowLockPromotion()
     * @generated
     * @ordered
     */
	protected static final boolean ROW_LOCK_PROMOTION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isRowLockPromotion() <em>Row Lock Promotion</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isRowLockPromotion()
     * @generated
     * @ordered
     */
	protected boolean rowLockPromotion = ROW_LOCK_PROMOTION_EDEFAULT;

	/**
     * The default value of the '{@link #getLWM() <em>LWM</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLWM()
     * @generated
     * @ordered
     */
	protected static final int LWM_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getLWM() <em>LWM</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLWM()
     * @generated
     * @ordered
     */
	protected int lwm = LWM_EDEFAULT;

	/**
     * The default value of the '{@link #getHWM() <em>HWM</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHWM()
     * @generated
     * @ordered
     */
	protected static final int HWM_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getHWM() <em>HWM</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHWM()
     * @generated
     * @ordered
     */
	protected int hwm = HWM_EDEFAULT;

	/**
     * The default value of the '{@link #getPCT() <em>PCT</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPCT()
     * @generated
     * @ordered
     */
	protected static final int PCT_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getPCT() <em>PCT</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPCT()
     * @generated
     * @ordered
     */
	protected int pct = PCT_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected LockPromotionInfoImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.LOCK_PROMOTION_INFO;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isRowLockPromotion() {
        return rowLockPromotion;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRowLockPromotion(boolean newRowLockPromotion) {
        boolean oldRowLockPromotion = rowLockPromotion;
        rowLockPromotion = newRowLockPromotion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__ROW_LOCK_PROMOTION, oldRowLockPromotion, rowLockPromotion));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getLWM() {
        return lwm;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setLWM(int newLWM) {
        int oldLWM = lwm;
        lwm = newLWM;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__LWM, oldLWM, lwm));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getHWM() {
        return hwm;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setHWM(int newHWM) {
        int oldHWM = hwm;
        hwm = newHWM;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__HWM, oldHWM, hwm));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getPCT() {
        return pct;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPCT(int newPCT) {
        int oldPCT = pct;
        pct = newPCT;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__PCT, oldPCT, pct));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__ROW_LOCK_PROMOTION:
                return isRowLockPromotion() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__LWM:
                return new Integer(getLWM());
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__HWM:
                return new Integer(getHWM());
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__PCT:
                return new Integer(getPCT());
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
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__ROW_LOCK_PROMOTION:
                setRowLockPromotion(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__LWM:
                setLWM(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__HWM:
                setHWM(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__PCT:
                setPCT(((Integer)newValue).intValue());
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
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__ROW_LOCK_PROMOTION:
                setRowLockPromotion(ROW_LOCK_PROMOTION_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__LWM:
                setLWM(LWM_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__HWM:
                setHWM(HWM_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__PCT:
                setPCT(PCT_EDEFAULT);
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
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__ROW_LOCK_PROMOTION:
                return rowLockPromotion != ROW_LOCK_PROMOTION_EDEFAULT;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__LWM:
                return lwm != LWM_EDEFAULT;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__HWM:
                return hwm != HWM_EDEFAULT;
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO__PCT:
                return pct != PCT_EDEFAULT;
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
        result.append(" (rowLockPromotion: "); //$NON-NLS-1$
        result.append(rowLockPromotion);
        result.append(", LWM: "); //$NON-NLS-1$
        result.append(lwm);
        result.append(", HWM: "); //$NON-NLS-1$
        result.append(hwm);
        result.append(", PCT: "); //$NON-NLS-1$
        result.append(pct);
        result.append(')');
        return result.toString();
    }

} //LockPromotionInfoImpl
