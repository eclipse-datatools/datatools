/**
 * <copyright>
 * </copyright>
 *
 * $Id: LockPromotionInfo.java,v 1.7 2007/07/06 08:40:17 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lock Promotion Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#isRowLockPromotion <em>Row Lock Promotion</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getLWM <em>LWM</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getHWM <em>HWM</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getPCT <em>PCT</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getLockPromotionInfo()
 * @model
 * @generated
 */
public interface LockPromotionInfo extends SQLObject {
	/**
     * Returns the value of the '<em><b>Row Lock Promotion</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Row Lock Promotion</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Row Lock Promotion</em>' attribute.
     * @see #setRowLockPromotion(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getLockPromotionInfo_RowLockPromotion()
     * @model
     * @generated
     */
	boolean isRowLockPromotion();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#isRowLockPromotion <em>Row Lock Promotion</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Row Lock Promotion</em>' attribute.
     * @see #isRowLockPromotion()
     * @generated
     */
	void setRowLockPromotion(boolean value);

	/**
     * Returns the value of the '<em><b>LWM</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LWM</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>LWM</em>' attribute.
     * @see #setLWM(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getLockPromotionInfo_LWM()
     * @model
     * @generated
     */
	int getLWM();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getLWM <em>LWM</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>LWM</em>' attribute.
     * @see #getLWM()
     * @generated
     */
	void setLWM(int value);

	/**
     * Returns the value of the '<em><b>HWM</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>HWM</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>HWM</em>' attribute.
     * @see #setHWM(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getLockPromotionInfo_HWM()
     * @model
     * @generated
     */
	int getHWM();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getHWM <em>HWM</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>HWM</em>' attribute.
     * @see #getHWM()
     * @generated
     */
	void setHWM(int value);

	/**
     * Returns the value of the '<em><b>PCT</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>PCT</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>PCT</em>' attribute.
     * @see #setPCT(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getLockPromotionInfo_PCT()
     * @model
     * @generated
     */
	int getPCT();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getPCT <em>PCT</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>PCT</em>' attribute.
     * @see #getPCT()
     * @generated
     */
	void setPCT(int value);

} // LockPromotionInfo
