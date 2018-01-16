/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASADefaultWrapperImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Default Wrapper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl#isIsLiteral <em>Is Literal</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl#getPartitionSize <em>Partition Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASADefaultWrapperImpl extends EObjectImpl implements SybaseASADefaultWrapper
{
    /**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
    protected static final String VALUE_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
    protected String value = VALUE_EDEFAULT;

    /**
	 * The default value of the '{@link #isIsLiteral() <em>Is Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isIsLiteral()
	 * @generated
	 * @ordered
	 */
    protected static final boolean IS_LITERAL_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isIsLiteral() <em>Is Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isIsLiteral()
	 * @generated
	 * @ordered
	 */
    protected boolean isLiteral = IS_LITERAL_EDEFAULT;

    /**
	 * The default value of the '{@link #getPartitionSize() <em>Partition Size</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPartitionSize()
	 * @generated
	 * @ordered
	 */
    protected static final int PARTITION_SIZE_EDEFAULT = -1;

    /**
	 * The cached value of the '{@link #getPartitionSize() <em>Partition Size</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPartitionSize()
	 * @generated
	 * @ordered
	 */
    protected int partitionSize = PARTITION_SIZE_EDEFAULT;

    /**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
    protected static final TypeOfDefault TYPE_EDEFAULT = TypeOfDefault.NO_DEFAULT_LITERAL;

    /**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
    protected TypeOfDefault type = TYPE_EDEFAULT;

    protected boolean isComputed;
    
    protected String rawValue;
    
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SybaseASADefaultWrapperImpl()
    {
		super();
	}

    public SybaseASADefaultWrapperImpl(String value, boolean isComputed)
    {
        this.value = value;
        
        // The raw string is slightly different from the value. For example, 
        this.rawValue = value;
        this.isComputed = isComputed;
        parse();
    }

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass()
    {
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_DEFAULT_WRAPPER;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getValue()
    {
		return value;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValue(String newValue)
    {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__VALUE, oldValue, value));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isIsLiteral()
    {
		return isLiteral;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setIsLiteral(boolean newIsLiteral)
    {
		boolean oldIsLiteral = isLiteral;
		isLiteral = newIsLiteral;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__IS_LITERAL, oldIsLiteral, isLiteral));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getPartitionSize()
    {
		return partitionSize;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPartitionSize(int newPartitionSize)
    {
		int oldPartitionSize = partitionSize;
		partitionSize = newPartitionSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__PARTITION_SIZE, oldPartitionSize, partitionSize));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TypeOfDefault getType()
    {
		return type;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setType(TypeOfDefault newType)
    {
		TypeOfDefault oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__TYPE, oldType, type));
	}

    /**
     * Parses the string value and assign to other database memebers accordingly
     */
    public void parse()
    {
        if(value == null || value.trim().equals(""))
        {
            value = "";
            type = TypeOfDefault.NO_DEFAULT_LITERAL;
        }
        else if(isComputed)
        {
            type = TypeOfDefault.COMPUTED_VALUE_LITERAL;
        }
        else if(isSystemDefault())
        {
            type = TypeOfDefault.SYSTEM_DEFINED_LITERAL;
            if(value.startsWith(GLOBAL_AUTOINCREMENT))
            {
                try
                {
                    int partSize = Integer.parseInt(value.substring(GLOBAL_AUTOINCREMENT.length() + 1, value.length() - 1));
                    if(partSize > 0)
                    {
                        partitionSize = partSize;
                    }
                }
                catch (Exception e)
                {
                    
                }
            }
        }
        else
        {
            type = TypeOfDefault.USER_DEFINED_LITERAL;
            if(value.charAt(0) == '\'' && value.charAt(value.length()-1) == '\'')
            {
                rawValue = unquote(value);
                isLiteral = true;
            }
            else
            {
                isLiteral = false;
            }
        }
    }

    private static String unquote(String quoted)
    {
        String content = quoted;
        if (quoted.indexOf("'") == 0 && quoted.lastIndexOf("'") == quoted.length() - 1)
        {
            content = quoted.substring(1, quoted.length() - 1).replaceAll("''", "'");
        }
        else if (quoted.indexOf("\"") == 0 && quoted.lastIndexOf("\"") == quoted.length() - 1)
        {
            content = quoted.substring(1, quoted.length() - 1).replaceAll("\"\"", "\"");
        }
        return content;
    }
    
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__VALUE:
				return getValue();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__IS_LITERAL:
				return isIsLiteral() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__PARTITION_SIZE:
				return new Integer(getPartitionSize());
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__TYPE:
				return getType();
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__VALUE:
				setValue((String)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__IS_LITERAL:
				setIsLiteral(((Boolean)newValue).booleanValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__PARTITION_SIZE:
				setPartitionSize(((Integer)newValue).intValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__TYPE:
				setType((TypeOfDefault)newValue);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__IS_LITERAL:
				setIsLiteral(IS_LITERAL_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__PARTITION_SIZE:
				setPartitionSize(PARTITION_SIZE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__TYPE:
				setType(TYPE_EDEFAULT);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__IS_LITERAL:
				return isLiteral != IS_LITERAL_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__PARTITION_SIZE:
				return partitionSize != PARTITION_SIZE_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER__TYPE:
				return type != TYPE_EDEFAULT;
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
		result.append(" (value: ");
		result.append(value);
		result.append(", isLiteral: ");
		result.append(isLiteral);
		result.append(", partitionSize: ");
		result.append(partitionSize);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

    public boolean isSystemDefault()
    {
        List systemDefaults = new ArrayList();
        for (int i = 0; i < STRING_TYPE_SYSTEM_DEFAULTS.length; i++)
        {
            systemDefaults.add(STRING_TYPE_SYSTEM_DEFAULTS[i]);
        }
        for (int i = 0; i < NUMERIC_TYPE_SYSTEM_DEFAULTS.length; i++)
        {
            systemDefaults.add(NUMERIC_TYPE_SYSTEM_DEFAULTS[i]);
        }
        for (int i = 0; i < TIME_TYPE_SYSTEM_DEFAULTS.length; i++)
        {
            systemDefaults.add(TIME_TYPE_SYSTEM_DEFAULTS[i]);
        }
        for (int i = 0; i < DATE_TYPE_SYSTEM_DEFAULTS.length; i++)
        {
            systemDefaults.add(DATE_TYPE_SYSTEM_DEFAULTS[i]);
        }
        for (int i = 0; i < TS_TYPE_SYSTEM_DEFAULTS.length; i++)
        {
            systemDefaults.add(TS_TYPE_SYSTEM_DEFAULTS[i]);
        }
        for (int i = 0; i < BINARY_TYPE_SYSTEM_DEFAULTS.length; i++)
        {
            systemDefaults.add(BINARY_TYPE_SYSTEM_DEFAULTS[i]);
        }
        if(systemDefaults.contains(value))
        {
            return true;
        }
        //TODO: use regular expression to judge
        if(value.startsWith(GLOBAL_AUTOINCREMENT))
        {
            rawValue = GLOBAL_AUTOINCREMENT;
            return true;
        }
        return false;
    }

    public String getRawValue()
    {
        return rawValue;
    }
    
} //SybaseASADefaultWrapperImpl