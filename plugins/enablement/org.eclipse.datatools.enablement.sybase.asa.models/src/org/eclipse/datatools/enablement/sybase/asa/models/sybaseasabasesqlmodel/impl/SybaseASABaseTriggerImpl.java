/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseTriggerImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.impl.TriggerImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTriggerImpl#getOrder <em>Order</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTriggerImpl#getSybaseASABaseActionTime <em>Sybase ASA Base Action Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTriggerImpl#getRemoteName <em>Remote Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTriggerImpl#isUpdateColumnType <em>Update Column Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseTriggerImpl extends TriggerImpl implements SybaseASABaseTrigger 
{
    /**
	 * The default value of the '{@link #getOrder() <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrder()
	 * @generated
	 * @ordered
	 */
	protected static final int ORDER_EDEFAULT = 1;

    /**
	 * The cached value of the '{@link #getOrder() <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrder()
	 * @generated
	 * @ordered
	 */
	protected int order = ORDER_EDEFAULT;

    /**
	 * The default value of the '{@link #getSybaseASABaseActionTime() <em>Sybase ASA Base Action Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSybaseASABaseActionTime()
	 * @generated
	 * @ordered
	 */
	protected static final SybaseASABaseActionTime SYBASE_ASA_BASE_ACTION_TIME_EDEFAULT = SybaseASABaseActionTime.BEFORE_LITERAL;

    /**
	 * The cached value of the '{@link #getSybaseASABaseActionTime() <em>Sybase ASA Base Action Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSybaseASABaseActionTime()
	 * @generated
	 * @ordered
	 */
	protected SybaseASABaseActionTime sybaseASABaseActionTime = SYBASE_ASA_BASE_ACTION_TIME_EDEFAULT;

    /**
	 * The default value of the '{@link #getRemoteName() <em>Remote Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteName()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getRemoteName() <em>Remote Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteName()
	 * @generated
	 * @ordered
	 */
	protected String remoteName = REMOTE_NAME_EDEFAULT;

    /**
	 * The default value of the '{@link #isUpdateColumnType() <em>Update Column Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isUpdateColumnType()
	 * @generated
	 * @ordered
	 */
    protected static final boolean UPDATE_COLUMN_TYPE_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isUpdateColumnType() <em>Update Column Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isUpdateColumnType()
	 * @generated
	 * @ordered
	 */
    protected boolean updateColumnType = UPDATE_COLUMN_TYPE_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseTriggerImpl()
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
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_TRIGGER;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOrder()
    {
		return order;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrder(int newOrder)
    {
		int oldOrder = order;
		order = newOrder;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ORDER, oldOrder, order));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASABaseActionTime getSybaseASABaseActionTime()
    {
		return sybaseASABaseActionTime;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSybaseASABaseActionTime(SybaseASABaseActionTime newSybaseASABaseActionTime)
    {
		SybaseASABaseActionTime oldSybaseASABaseActionTime = sybaseASABaseActionTime;
		sybaseASABaseActionTime = newSybaseASABaseActionTime == null ? SYBASE_ASA_BASE_ACTION_TIME_EDEFAULT : newSybaseASABaseActionTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__SYBASE_ASA_BASE_ACTION_TIME, oldSybaseASABaseActionTime, sybaseASABaseActionTime));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemoteName()
    {
		return remoteName;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteName(String newRemoteName)
    {
		String oldRemoteName = remoteName;
		remoteName = newRemoteName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME, oldRemoteName, remoteName));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isUpdateColumnType()
    {
		return updateColumnType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUpdateColumnType(boolean newUpdateColumnType)
    {
		boolean oldUpdateColumnType = updateColumnType;
		updateColumnType = newUpdateColumnType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE, oldUpdateColumnType, updateColumnType));
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getOldName()
    {
        if (getActionGranularity().getValue() == ActionGranularityType.ROW)
        {
            return getOldRow();
        }
        else
        {
            return getOldTable();
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public String getNewName()
    {
        if (getActionGranularity().getValue() == ActionGranularityType.ROW)
        {
            return getNewRow();
        }
        else
        {
            return getNewTable();
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setOldName(String oldName)
    {
        if (getActionGranularity().getValue() == ActionGranularityType.ROW)
        {
            setOldRow(oldName);
        }
        else
        {
            setOldTable(oldName);
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    public void setNewName(String newName)
    {
        if (getActionGranularity().getValue() == ActionGranularityType.ROW)
        {
            setNewRow(newName);
        }
        else
        {
            setNewTable(newName);
        }
    }

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ORDER:
				return new Integer(getOrder());
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__SYBASE_ASA_BASE_ACTION_TIME:
				return getSybaseASABaseActionTime();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME:
				return getRemoteName();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE:
				return isUpdateColumnType() ? Boolean.TRUE : Boolean.FALSE;
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ORDER:
				setOrder(((Integer)newValue).intValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__SYBASE_ASA_BASE_ACTION_TIME:
				setSybaseASABaseActionTime((SybaseASABaseActionTime)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME:
				setRemoteName((String)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE:
				setUpdateColumnType(((Boolean)newValue).booleanValue());
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ORDER:
				setOrder(ORDER_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__SYBASE_ASA_BASE_ACTION_TIME:
				setSybaseASABaseActionTime(SYBASE_ASA_BASE_ACTION_TIME_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME:
				setRemoteName(REMOTE_NAME_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE:
				setUpdateColumnType(UPDATE_COLUMN_TYPE_EDEFAULT);
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
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__ORDER:
				return order != ORDER_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__SYBASE_ASA_BASE_ACTION_TIME:
				return sybaseASABaseActionTime != SYBASE_ASA_BASE_ACTION_TIME_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME:
				return REMOTE_NAME_EDEFAULT == null ? remoteName != null : !REMOTE_NAME_EDEFAULT.equals(remoteName);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE:
				return updateColumnType != UPDATE_COLUMN_TYPE_EDEFAULT;
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
		result.append(" (order: ");
		result.append(order);
		result.append(", sybaseASABaseActionTime: ");
		result.append(sybaseASABaseActionTime);
		result.append(", remoteName: ");
		result.append(remoteName);
		result.append(", updateColumnType: ");
		result.append(updateColumnType);
		result.append(')');
		return result.toString();
	}

} //SybaseASABaseTriggerImpl