/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseViewTableImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseViewTableImpl;

import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.tables.impl.ViewTableImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base View Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseViewTableImpl#isWithCheckOption <em>With Check Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseViewTableImpl#getStatement <em>Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseViewTableImpl extends SybaseViewTableImpl implements SybaseASABaseViewTable 
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
	 * The cached value of the '{@link #getStatement() <em>Statement</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatement()
	 * @generated
	 * @ordered
	 */
	protected SQLStatement statement;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseViewTableImpl()
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
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_VIEW_TABLE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWithCheckOption()
    {
		return withCheckOption;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWithCheckOption(boolean newWithCheckOption)
    {
		boolean oldWithCheckOption = withCheckOption;
		withCheckOption = newWithCheckOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__WITH_CHECK_OPTION, oldWithCheckOption, withCheckOption));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLStatement getStatement()
    {
		if (statement != null && statement.eIsProxy()) {
			InternalEObject oldStatement = (InternalEObject)statement;
			statement = (SQLStatement)eResolveProxy(oldStatement);
			if (statement != oldStatement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT, oldStatement, statement));
			}
		}
		return statement;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLStatement basicGetStatement()
    {
		return statement;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatement(SQLStatement newStatement)
    {
		SQLStatement oldStatement = statement;
		statement = newStatement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT, oldStatement, statement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSystem() {
		String owner = this.getSchema().getName();
		return owner.equals("SYS") || owner.equals("dbo") || owner.equals("rs_systabgroup");
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__WITH_CHECK_OPTION:
				return isWithCheckOption() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT:
				if (resolve) return getStatement();
				return basicGetStatement();
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
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__WITH_CHECK_OPTION:
				setWithCheckOption(((Boolean)newValue).booleanValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT:
				setStatement((SQLStatement)newValue);
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
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__WITH_CHECK_OPTION:
				setWithCheckOption(WITH_CHECK_OPTION_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT:
				setStatement((SQLStatement)null);
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
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__WITH_CHECK_OPTION:
				return withCheckOption != WITH_CHECK_OPTION_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT:
				return statement != null;
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
		result.append(" (withCheckOption: ");
		result.append(withCheckOption);
		result.append(')');
		return result.toString();
	}

} //SybaseASABaseViewTableImpl