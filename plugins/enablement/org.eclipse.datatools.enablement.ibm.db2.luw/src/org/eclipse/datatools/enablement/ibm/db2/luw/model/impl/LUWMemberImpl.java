package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;


import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Member</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMemberImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMemberImpl#getAlert <em>Alert</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMemberImpl#getDbPartitionNum <em>Db Partition Num</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMemberImpl#getLogicalPort <em>Logical Port</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMemberImpl#getNetName <em>Net Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWMemberImpl extends DB2MemberImpl implements LUWMember {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final LUWMemberType TYPE_EDEFAULT = LUWMemberType.MEMBER_LITERAL;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected LUWMemberType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlert() <em>Alert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlert()
	 * @generated
	 * @ordered
	 */
	protected static final String ALERT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlert() <em>Alert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlert()
	 * @generated
	 * @ordered
	 */
	protected String alert = ALERT_EDEFAULT;

	/**
	 * The default value of the '{@link #getDbPartitionNum() <em>Db Partition Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbPartitionNum()
	 * @generated
	 * @ordered
	 */
	protected static final int DB_PARTITION_NUM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDbPartitionNum() <em>Db Partition Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbPartitionNum()
	 * @generated
	 * @ordered
	 */
	protected int dbPartitionNum = DB_PARTITION_NUM_EDEFAULT;

	/**
	 * The default value of the '{@link #getLogicalPort() <em>Logical Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogicalPort()
	 * @generated
	 * @ordered
	 */
	protected static final int LOGICAL_PORT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLogicalPort() <em>Logical Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogicalPort()
	 * @generated
	 * @ordered
	 */
	protected int logicalPort = LOGICAL_PORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getNetName() <em>Net Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetName()
	 * @generated
	 * @ordered
	 */
	protected static final String NET_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNetName() <em>Net Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetName()
	 * @generated
	 * @ordered
	 */
	protected String netName = NET_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWMemberImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_MEMBER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWMemberType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(LUWMemberType newType) {
		LUWMemberType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MEMBER__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAlert() {
		return alert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlert(String newAlert) {
		String oldAlert = alert;
		alert = newAlert;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MEMBER__ALERT, oldAlert, alert));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDbPartitionNum() {
		return dbPartitionNum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDbPartitionNum(int newDbPartitionNum) {
		int oldDbPartitionNum = dbPartitionNum;
		dbPartitionNum = newDbPartitionNum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MEMBER__DB_PARTITION_NUM, oldDbPartitionNum, dbPartitionNum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLogicalPort() {
		return logicalPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLogicalPort(int newLogicalPort) {
		int oldLogicalPort = logicalPort;
		logicalPort = newLogicalPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MEMBER__LOGICAL_PORT, oldLogicalPort, logicalPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNetName() {
		return netName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNetName(String newNetName) {
		String oldNetName = netName;
		netName = newNetName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_MEMBER__NET_NAME, oldNetName, netName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_MEMBER__TYPE:
				return getType();
			case LUWPackage.LUW_MEMBER__ALERT:
				return getAlert();
			case LUWPackage.LUW_MEMBER__DB_PARTITION_NUM:
				return new Integer(getDbPartitionNum());
			case LUWPackage.LUW_MEMBER__LOGICAL_PORT:
				return new Integer(getLogicalPort());
			case LUWPackage.LUW_MEMBER__NET_NAME:
				return getNetName();
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
			case LUWPackage.LUW_MEMBER__TYPE:
				setType((LUWMemberType)newValue);
				return;
			case LUWPackage.LUW_MEMBER__ALERT:
				setAlert((String)newValue);
				return;
			case LUWPackage.LUW_MEMBER__DB_PARTITION_NUM:
				setDbPartitionNum(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_MEMBER__LOGICAL_PORT:
				setLogicalPort(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_MEMBER__NET_NAME:
				setNetName((String)newValue);
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
			case LUWPackage.LUW_MEMBER__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_MEMBER__ALERT:
				setAlert(ALERT_EDEFAULT);
				return;
			case LUWPackage.LUW_MEMBER__DB_PARTITION_NUM:
				setDbPartitionNum(DB_PARTITION_NUM_EDEFAULT);
				return;
			case LUWPackage.LUW_MEMBER__LOGICAL_PORT:
				setLogicalPort(LOGICAL_PORT_EDEFAULT);
				return;
			case LUWPackage.LUW_MEMBER__NET_NAME:
				setNetName(NET_NAME_EDEFAULT);
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
			case LUWPackage.LUW_MEMBER__TYPE:
				return type != TYPE_EDEFAULT;
			case LUWPackage.LUW_MEMBER__ALERT:
				return ALERT_EDEFAULT == null ? alert != null : !ALERT_EDEFAULT.equals(alert);
			case LUWPackage.LUW_MEMBER__DB_PARTITION_NUM:
				return dbPartitionNum != DB_PARTITION_NUM_EDEFAULT;
			case LUWPackage.LUW_MEMBER__LOGICAL_PORT:
				return logicalPort != LOGICAL_PORT_EDEFAULT;
			case LUWPackage.LUW_MEMBER__NET_NAME:
				return NET_NAME_EDEFAULT == null ? netName != null : !NET_NAME_EDEFAULT.equals(netName);
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
		result.append(" (type: "); //$NON-NLS-1$
		result.append(type);
		result.append(", alert: "); //$NON-NLS-1$
		result.append(alert);
		result.append(", dbPartitionNum: "); //$NON-NLS-1$
		result.append(dbPartitionNum);
		result.append(", logicalPort: "); //$NON-NLS-1$
		result.append(logicalPort);
		result.append(", netName: "); //$NON-NLS-1$
		result.append(netName);
		result.append(')');
		return result.toString();
	}

} //LUWMemberImpl
