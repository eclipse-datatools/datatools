/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseTempTableImpl.java,v 1.1 2007/03/05 15:52:15 jgraham Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption;
import org.eclipse.datatools.modelbase.sql.tables.impl.TemporaryTableImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Temp Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTempTableImpl#getTransactionOption <em>Transaction Option</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseTempTableImpl extends TemporaryTableImpl implements SybaseASABaseTempTable 
{
    /**
     * The default value of the '{@link #getTransactionOption() <em>Transaction Option</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTransactionOption()
     * @generated
     * @ordered
     */
	protected static final TransactionOption TRANSACTION_OPTION_EDEFAULT = TransactionOption.DELETE_LITERAL;

    /**
     * The cached value of the '{@link #getTransactionOption() <em>Transaction Option</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTransactionOption()
     * @generated
     * @ordered
     */
	protected TransactionOption transactionOption = TRANSACTION_OPTION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASABaseTempTableImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass()
    {
        return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_TEMP_TABLE;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TransactionOption getTransactionOption()
    {
        return transactionOption;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTransactionOption(TransactionOption newTransactionOption)
    {
        TransactionOption oldTransactionOption = transactionOption;
        transactionOption = newTransactionOption == null ? TRANSACTION_OPTION_EDEFAULT : newTransactionOption;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__TRANSACTION_OPTION, oldTransactionOption, transactionOption));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__TRANSACTION_OPTION:
                return getTransactionOption();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__TRANSACTION_OPTION:
                setTransactionOption((TransactionOption)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__TRANSACTION_OPTION:
                setTransactionOption(TRANSACTION_OPTION_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__TRANSACTION_OPTION:
                return transactionOption != TRANSACTION_OPTION_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String toString()
    {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (transactionOption: "); //$NON-NLS-1$
        result.append(transactionOption);
        result.append(')');
        return result.toString();
    }

} //SybaseASABaseTempTableImpl