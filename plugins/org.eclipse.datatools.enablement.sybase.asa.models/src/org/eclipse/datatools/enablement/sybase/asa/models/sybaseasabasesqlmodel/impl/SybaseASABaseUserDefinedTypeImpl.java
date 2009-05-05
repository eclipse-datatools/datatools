/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseUserDefinedTypeImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.util.Utils;
import org.eclipse.datatools.modelbase.sql.datatypes.impl.DomainImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base User Defined Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserDefinedTypeImpl#getNullable <em>Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserDefinedTypeImpl#getDefaultType <em>Default Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseUserDefinedTypeImpl extends DomainImpl implements SybaseASABaseUserDefinedType 
{
    /**
	 * The default value of the '{@link #getNullable() <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNullable()
	 * @generated
	 * @ordered
	 */
	protected static final AllowNullType NULLABLE_EDEFAULT = AllowNullType.NULLABLE_LITERAL;

    /**
	 * The cached value of the '{@link #getNullable() <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNullable()
	 * @generated
	 * @ordered
	 */
	protected AllowNullType nullable = NULLABLE_EDEFAULT;

    /**
	 * The default value of the '{@link #getDefaultType() <em>Default Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultType()
	 * @generated
	 * @ordered
	 */
	protected static final TypeOfDefault DEFAULT_TYPE_EDEFAULT = TypeOfDefault.NO_DEFAULT_LITERAL;

    /**
	 * The cached value of the '{@link #getDefaultType() <em>Default Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultType()
	 * @generated
	 * @ordered
	 */
	protected TypeOfDefault defaultType = DEFAULT_TYPE_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseUserDefinedTypeImpl()
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
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_USER_DEFINED_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllowNullType getNullable()
    {
		return nullable;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullable(AllowNullType newNullable)
    {
		AllowNullType oldNullable = nullable;
		nullable = newNullable == null ? NULLABLE_EDEFAULT : newNullable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NULLABLE, oldNullable, nullable));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeOfDefault getDefaultType()
    {
		return defaultType;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultType(TypeOfDefault newDefaultType)
    {
		TypeOfDefault oldDefaultType = defaultType;
		defaultType = newDefaultType == null ? DEFAULT_TYPE_EDEFAULT : newDefaultType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_TYPE, oldDefaultType, defaultType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isLiteralDefault() {
		return Utils.isLiteralDefault(this.getDefaultType(), this.getDefaultValue());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getGlobalIncrementPartitionSize() {
		return Utils.getDefaultGlobalIncrementPartitionSize(this.getDefaultType(), this.getDefaultValue());
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NULLABLE:
				return getNullable();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_TYPE:
				return getDefaultType();
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NULLABLE:
				setNullable((AllowNullType)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_TYPE:
				setDefaultType((TypeOfDefault)newValue);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NULLABLE:
				setNullable(NULLABLE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_TYPE:
				setDefaultType(DEFAULT_TYPE_EDEFAULT);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__NULLABLE:
				return nullable != NULLABLE_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_TYPE:
				return defaultType != DEFAULT_TYPE_EDEFAULT;
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
		result.append(" (nullable: ");
		result.append(nullable);
		result.append(", defaultType: ");
		result.append(defaultType);
		result.append(')');
		return result.toString();
	}

} //SybaseASABaseUserDefinedTypeImpl