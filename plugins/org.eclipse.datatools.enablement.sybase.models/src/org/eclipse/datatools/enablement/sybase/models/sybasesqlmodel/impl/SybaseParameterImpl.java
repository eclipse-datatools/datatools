/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseParameterImpl#isNullable <em>Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseParameterImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseParameterImpl#getJDBCParameterType <em>JDBC Parameter Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseParameterImpl extends ParameterImpl implements SybaseParameter {
	/**
	 * The default value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NULLABLE_EDEFAULT = true;

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
	 * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected String defaultValue = DEFAULT_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getJDBCParameterType() <em>JDBC Parameter Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJDBCParameterType()
	 * @generated
	 * @ordered
	 */
	protected static final JDBCParameterType JDBC_PARAMETER_TYPE_EDEFAULT = JDBCParameterType.UNKNOWN_LITERAL;

	/**
	 * The cached value of the '{@link #getJDBCParameterType() <em>JDBC Parameter Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJDBCParameterType()
	 * @generated
	 * @ordered
	 */
	protected JDBCParameterType jdbcParameterType = JDBC_PARAMETER_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SybasesqlmodelPackage.Literals.SYBASE_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullable(boolean newNullable) {
		boolean oldNullable = nullable;
		nullable = newNullable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybasesqlmodelPackage.SYBASE_PARAMETER__NULLABLE, oldNullable, nullable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(String newDefaultValue) {
		String oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE, oldDefaultValue, defaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JDBCParameterType getJDBCParameterType() {
		return jdbcParameterType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJDBCParameterType(JDBCParameterType newJDBCParameterType) {
		JDBCParameterType oldJDBCParameterType = jdbcParameterType;
		jdbcParameterType = newJDBCParameterType == null ? JDBC_PARAMETER_TYPE_EDEFAULT : newJDBCParameterType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybasesqlmodelPackage.SYBASE_PARAMETER__JDBC_PARAMETER_TYPE, oldJDBCParameterType, jdbcParameterType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_PARAMETER__NULLABLE:
				return isNullable() ? Boolean.TRUE : Boolean.FALSE;
			case SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE:
				return getDefaultValue();
			case SybasesqlmodelPackage.SYBASE_PARAMETER__JDBC_PARAMETER_TYPE:
				return getJDBCParameterType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_PARAMETER__NULLABLE:
				setNullable(((Boolean)newValue).booleanValue());
				return;
			case SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE:
				setDefaultValue((String)newValue);
				return;
			case SybasesqlmodelPackage.SYBASE_PARAMETER__JDBC_PARAMETER_TYPE:
				setJDBCParameterType((JDBCParameterType)newValue);
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
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_PARAMETER__NULLABLE:
				setNullable(NULLABLE_EDEFAULT);
				return;
			case SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
				return;
			case SybasesqlmodelPackage.SYBASE_PARAMETER__JDBC_PARAMETER_TYPE:
				setJDBCParameterType(JDBC_PARAMETER_TYPE_EDEFAULT);
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
		switch (featureID) {
			case SybasesqlmodelPackage.SYBASE_PARAMETER__NULLABLE:
				return nullable != NULLABLE_EDEFAULT;
			case SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
			case SybasesqlmodelPackage.SYBASE_PARAMETER__JDBC_PARAMETER_TYPE:
				return jdbcParameterType != JDBC_PARAMETER_TYPE_EDEFAULT;
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
		result.append(" (nullable: ");
		result.append(nullable);
		result.append(", defaultValue: ");
		result.append(defaultValue);
		result.append(", JDBCParameterType: ");
		result.append(jdbcParameterType);
		result.append(')');
		return result.toString();
	}

} //SybaseParameterImpl
