/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseParameterImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseParameterImpl#isNullable <em>Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseParameterImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseParameterImpl#getJDBCParameterType <em>JDBC Parameter Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseParameterImpl#getParmType <em>Parm Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseParameterImpl extends ParameterImpl implements SybaseASABaseParameter 
{
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
	 * The default value of the '{@link #getParmType() <em>Parm Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParmType()
	 * @generated
	 * @ordered
	 */
	protected static final ParameterType PARM_TYPE_EDEFAULT = ParameterType.VARIABLE_LITERAL;

    /**
	 * The cached value of the '{@link #getParmType() <em>Parm Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParmType()
	 * @generated
	 * @ordered
	 */
	protected ParameterType parmType = PARM_TYPE_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseParameterImpl()
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
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_PARAMETER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__NULLABLE, oldNullable, nullable));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getDefaultValue()
    {
		return defaultValue;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDefaultValue(String newDefaultValue)
    {
		String oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__DEFAULT_VALUE, oldDefaultValue, defaultValue));
	}

    /**
     * <!-- begin-user-doc -->
     * For ASA Parameter, the JDBCParameterType is mapped from ParameterType 
     * <!-- end-user-doc -->
     */
    public JDBCParameterType getJDBCParameterType()
    {
        if (eIsSet(SybasesqlmodelPackage.SYBASE_PARAMETER__JDBC_PARAMETER_TYPE))
        {
            return jdbcParameterType; 
        }
            
        switch (parmType.getValue())
        {
            case ParameterType.RESULT:
                return JDBCParameterType.RESULT_LITERAL;
            case ParameterType.RETURN:
                return JDBCParameterType.RETURN_LITERAL;
            case ParameterType.VARIABLE:
                switch (getMode().getValue())
                {
                    case ParameterMode.IN:
                        return JDBCParameterType.IN_LITERAL;
                    case ParameterMode.INOUT:
                        return JDBCParameterType.IN_OUT_LITERAL;
                    case ParameterMode.OUT:
                        return JDBCParameterType.OUT_LITERAL;
                }
            case ParameterType.SQLCODE:
            case ParameterType.SQLSTATE:
            default:
                return JDBCParameterType.UNKNOWN_LITERAL;
        }
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setJDBCParameterType(JDBCParameterType newJDBCParameterType)
    {
		JDBCParameterType oldJDBCParameterType = jdbcParameterType;
		jdbcParameterType = newJDBCParameterType == null ? JDBC_PARAMETER_TYPE_EDEFAULT : newJDBCParameterType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__JDBC_PARAMETER_TYPE, oldJDBCParameterType, jdbcParameterType));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterType getParmType()
    {
		return parmType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setParmType(ParameterType newParmType)
    {
		ParameterType oldParmType = parmType;
		parmType = newParmType == null ? PARM_TYPE_EDEFAULT : newParmType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__PARM_TYPE, oldParmType, parmType));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__NULLABLE:
				return isNullable() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__DEFAULT_VALUE:
				return getDefaultValue();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__JDBC_PARAMETER_TYPE:
				return getJDBCParameterType();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__PARM_TYPE:
				return getParmType();
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__NULLABLE:
				setNullable(((Boolean)newValue).booleanValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__DEFAULT_VALUE:
				setDefaultValue((String)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__JDBC_PARAMETER_TYPE:
				setJDBCParameterType((JDBCParameterType)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__PARM_TYPE:
				setParmType((ParameterType)newValue);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__NULLABLE:
				setNullable(NULLABLE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__DEFAULT_VALUE:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__JDBC_PARAMETER_TYPE:
				setJDBCParameterType(JDBC_PARAMETER_TYPE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__PARM_TYPE:
				setParmType(PARM_TYPE_EDEFAULT);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__NULLABLE:
				return nullable != NULLABLE_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__JDBC_PARAMETER_TYPE:
				return jdbcParameterType != JDBC_PARAMETER_TYPE_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__PARM_TYPE:
				return parmType != PARM_TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)
    {
		if (baseClass == SybaseParameter.class) {
			switch (derivedFeatureID) {
				case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__NULLABLE: return SybasesqlmodelPackage.SYBASE_PARAMETER__NULLABLE;
				case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__DEFAULT_VALUE: return SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE;
				case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__JDBC_PARAMETER_TYPE: return SybasesqlmodelPackage.SYBASE_PARAMETER__JDBC_PARAMETER_TYPE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)
    {
		if (baseClass == SybaseParameter.class) {
			switch (baseFeatureID) {
				case SybasesqlmodelPackage.SYBASE_PARAMETER__NULLABLE: return SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__NULLABLE;
				case SybasesqlmodelPackage.SYBASE_PARAMETER__DEFAULT_VALUE: return SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__DEFAULT_VALUE;
				case SybasesqlmodelPackage.SYBASE_PARAMETER__JDBC_PARAMETER_TYPE: return SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER__JDBC_PARAMETER_TYPE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", defaultValue: ");
		result.append(defaultValue);
		result.append(", JDBCParameterType: ");
		result.append(jdbcParameterType);
		result.append(", parmType: ");
		result.append(parmType);
		result.append(')');
		return result.toString();
	}

	public Object getAdapter(Class adapterClass) {
		Object adapter = Platform.getAdapterManager().getAdapter(this, adapterClass);
		if(adapter == null){
			adapter = Platform.getAdapterManager().loadAdapter(this, adapterClass.getName());
		}
		return adapter;
	}

} //SybaseASABaseParameterImpl