/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEViewTableImpl.java,v 1.6 2007/07/06 08:40:20 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseViewTableImpl;

import org.eclipse.datatools.modelbase.sql.tables.impl.ViewTableImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE View Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEViewTableImpl#isWithCheckOption <em>With Check Option</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEViewTableImpl extends SybaseViewTableImpl implements SybaseASEViewTable 
{
	/**
     * The default value of the '{@link #isWithCheckOption() <em>With Check Option</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isWithCheckOption()
     * @generated
     * @ordered
     */
	protected static final boolean WITH_CHECK_OPTION_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isWithCheckOption() <em>With Check Option</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isWithCheckOption()
     * @generated
     * @ordered
     */
	protected boolean withCheckOption = WITH_CHECK_OPTION_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEViewTableImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_VIEW_TABLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isWithCheckOption() {
        return withCheckOption;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setWithCheckOption(boolean newWithCheckOption) {
        boolean oldWithCheckOption = withCheckOption;
        withCheckOption = newWithCheckOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_VIEW_TABLE__WITH_CHECK_OPTION, oldWithCheckOption, withCheckOption));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_VIEW_TABLE__WITH_CHECK_OPTION:
                return isWithCheckOption() ? Boolean.TRUE : Boolean.FALSE;
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_VIEW_TABLE__WITH_CHECK_OPTION:
                setWithCheckOption(((Boolean)newValue).booleanValue());
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_VIEW_TABLE__WITH_CHECK_OPTION:
                setWithCheckOption(WITH_CHECK_OPTION_EDEFAULT);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_VIEW_TABLE__WITH_CHECK_OPTION:
                return withCheckOption != WITH_CHECK_OPTION_EDEFAULT;
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
        result.append(" (withCheckOption: "); //$NON-NLS-1$
        result.append(withCheckOption);
        result.append(')');
        return result.toString();
    }

} //SybaseASEViewTableImpl
