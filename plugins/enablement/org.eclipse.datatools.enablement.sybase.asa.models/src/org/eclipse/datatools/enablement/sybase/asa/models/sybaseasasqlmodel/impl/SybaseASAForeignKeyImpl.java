/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASAForeignKeyImpl.java,v 1.4 2007/06/05 14:41:03 hcao Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseForeignKeyImpl;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Foreign Key</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAForeignKeyImpl#isCheckOnCommit <em>Check On Commit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAForeignKeyImpl#isNullable <em>Nullable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASAForeignKeyImpl extends SybaseASABaseForeignKeyImpl implements SybaseASAForeignKey 
{
    /**
	 * The default value of the '{@link #isCheckOnCommit() <em>Check On Commit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCheckOnCommit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CHECK_ON_COMMIT_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isCheckOnCommit() <em>Check On Commit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCheckOnCommit()
	 * @generated
	 * @ordered
	 */
	protected boolean checkOnCommit = CHECK_ON_COMMIT_EDEFAULT;

    /**
	 * The default value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NULLABLE_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullable()
	 * @generated
	 * @ordered
	 */
	protected boolean nullable = NULLABLE_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASAForeignKeyImpl()
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
		return SybaseasasqlmodelPackage.Literals.SYBASE_ASA_FOREIGN_KEY;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCheckOnCommit()
    {
		return checkOnCommit;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCheckOnCommit(boolean newCheckOnCommit)
    {
		boolean oldCheckOnCommit = checkOnCommit;
		checkOnCommit = newCheckOnCommit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT, oldCheckOnCommit, checkOnCommit));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNullable()
    {
		return nullable;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullable(boolean newNullable)
    {
		boolean oldNullable = nullable;
		nullable = newNullable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__NULLABLE, oldNullable, nullable));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT:
				return isCheckOnCommit() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__NULLABLE:
				return isNullable() ? Boolean.TRUE : Boolean.FALSE;
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
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT:
				setCheckOnCommit(((Boolean)newValue).booleanValue());
				return;
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__NULLABLE:
				setNullable(((Boolean)newValue).booleanValue());
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
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT:
				setCheckOnCommit(CHECK_ON_COMMIT_EDEFAULT);
				return;
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__NULLABLE:
				setNullable(NULLABLE_EDEFAULT);
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
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT:
				return checkOnCommit != CHECK_ON_COMMIT_EDEFAULT;
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY__NULLABLE:
				return nullable != NULLABLE_EDEFAULT;
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
		result.append(" (checkOnCommit: ");
		result.append(checkOnCommit);
		result.append(", nullable: ");
		result.append(nullable);
		result.append(')');
		return result.toString();
	}

} //SybaseASAForeignKeyImpl