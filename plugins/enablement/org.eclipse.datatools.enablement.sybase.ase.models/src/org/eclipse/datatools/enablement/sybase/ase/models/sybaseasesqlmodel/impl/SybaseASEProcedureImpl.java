/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEProcedureImpl.java,v 1.1 2008/03/27 07:41:14 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType;
import org.eclipse.datatools.enablement.sybase.util.SybaseRoutineUtil;
import org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Procedure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProcedureImpl#getGroupNumber <em>Group Number</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProcedureImpl#getTransactionMode <em>Transaction Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProcedureImpl#isSystemProcedure <em>System Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProcedureImpl#isWithRecompile <em>With Recompile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEProcedureImpl extends ProcedureImpl implements SybaseASEProcedure 
{
	/**
     * The default value of the '{@link #getGroupNumber() <em>Group Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGroupNumber()
     * @generated
     * @ordered
     */
	protected static final int GROUP_NUMBER_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getGroupNumber() <em>Group Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGroupNumber()
     * @generated
     * @ordered
     */
	protected int groupNumber = GROUP_NUMBER_EDEFAULT;

	/**
     * The default value of the '{@link #getTransactionMode() <em>Transaction Mode</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTransactionMode()
     * @generated
     * @ordered
     */
	protected static final TransactionModeType TRANSACTION_MODE_EDEFAULT = TransactionModeType.CHAINED_LITERAL;

	/**
     * The cached value of the '{@link #getTransactionMode() <em>Transaction Mode</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTransactionMode()
     * @generated
     * @ordered
     */
	protected TransactionModeType transactionMode = TRANSACTION_MODE_EDEFAULT;

	/**
     * The default value of the '{@link #isSystemProcedure() <em>System Procedure</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSystemProcedure()
     * @generated
     * @ordered
     */
	protected static final boolean SYSTEM_PROCEDURE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isSystemProcedure() <em>System Procedure</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSystemProcedure()
     * @generated
     * @ordered
     */
	protected boolean systemProcedure = SYSTEM_PROCEDURE_EDEFAULT;

	/**
     * The default value of the '{@link #isWithRecompile() <em>With Recompile</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isWithRecompile()
     * @generated
     * @ordered
     */
	protected static final boolean WITH_RECOMPILE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isWithRecompile() <em>With Recompile</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isWithRecompile()
     * @generated
     * @ordered
     */
	protected boolean withRecompile = WITH_RECOMPILE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEProcedureImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_PROCEDURE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getGroupNumber() {
        return groupNumber;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setGroupNumber(int newGroupNumber) {
        int oldGroupNumber = groupNumber;
        groupNumber = newGroupNumber;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__GROUP_NUMBER, oldGroupNumber, groupNumber));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TransactionModeType getTransactionMode() {
        return transactionMode;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTransactionMode(TransactionModeType newTransactionMode) {
        TransactionModeType oldTransactionMode = transactionMode;
        transactionMode = newTransactionMode == null ? TRANSACTION_MODE_EDEFAULT : newTransactionMode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__TRANSACTION_MODE, oldTransactionMode, transactionMode));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isSystemProcedure() {
        return systemProcedure;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSystemProcedure(boolean newSystemProcedure) {
        boolean oldSystemProcedure = systemProcedure;
        systemProcedure = newSystemProcedure;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__SYSTEM_PROCEDURE, oldSystemProcedure, systemProcedure));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isWithRecompile() {
        return withRecompile;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setWithRecompile(boolean newWithRecompile) {
        boolean oldWithRecompile = withRecompile;
        withRecompile = newWithRecompile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__WITH_RECOMPILE, oldWithRecompile, withRecompile));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__GROUP_NUMBER:
                return new Integer(getGroupNumber());
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__TRANSACTION_MODE:
                return getTransactionMode();
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__SYSTEM_PROCEDURE:
                return isSystemProcedure() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__WITH_RECOMPILE:
                return isWithRecompile() ? Boolean.TRUE : Boolean.FALSE;
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__GROUP_NUMBER:
                setGroupNumber(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__TRANSACTION_MODE:
                setTransactionMode((TransactionModeType)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__SYSTEM_PROCEDURE:
                setSystemProcedure(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__WITH_RECOMPILE:
                setWithRecompile(((Boolean)newValue).booleanValue());
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__GROUP_NUMBER:
                setGroupNumber(GROUP_NUMBER_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__TRANSACTION_MODE:
                setTransactionMode(TRANSACTION_MODE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__SYSTEM_PROCEDURE:
                setSystemProcedure(SYSTEM_PROCEDURE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__WITH_RECOMPILE:
                setWithRecompile(WITH_RECOMPILE_EDEFAULT);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__GROUP_NUMBER:
                return groupNumber != GROUP_NUMBER_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__TRANSACTION_MODE:
                return transactionMode != TRANSACTION_MODE_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__SYSTEM_PROCEDURE:
                return systemProcedure != SYSTEM_PROCEDURE_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE__WITH_RECOMPILE:
                return withRecompile != WITH_RECOMPILE_EDEFAULT;
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
        result.append(" (groupNumber: "); //$NON-NLS-1$
        result.append(groupNumber);
        result.append(", transactionMode: "); //$NON-NLS-1$
        result.append(transactionMode);
        result.append(", systemProcedure: "); //$NON-NLS-1$
        result.append(systemProcedure);
        result.append(", withRecompile: "); //$NON-NLS-1$
        result.append(withRecompile);
        result.append(')');
        return result.toString();
    }

    public  void parseParameterDefaultValues()
    {
        SybaseRoutineUtil.parseParameterDefaultValues(this, parameters);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean isSystem()
    {
        return isSystemProcedure();
    }
} //SybaseASEProcedureImpl
