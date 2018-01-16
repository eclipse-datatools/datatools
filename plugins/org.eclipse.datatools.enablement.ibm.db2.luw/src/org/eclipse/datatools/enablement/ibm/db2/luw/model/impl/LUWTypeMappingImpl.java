/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Date;

import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping;
/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl#getServerType <em>Server Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl#getServerVesion <em>Server Vesion</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl#getServerName <em>Server Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl#getCreationTime <em>Creation Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl#getLocalType <em>Local Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl#getRemoteType <em>Remote Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWTypeMappingImpl extends SQLObjectImpl implements LUWTypeMapping {
	/**
	 * The default value of the '{@link #getServerType() <em>Server Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerType()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerType() <em>Server Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerType()
	 * @generated
	 * @ordered
	 */
	protected String serverType = SERVER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getServerVesion() <em>Server Vesion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerVesion()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_VESION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerVesion() <em>Server Vesion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerVesion()
	 * @generated
	 * @ordered
	 */
	protected String serverVesion = SERVER_VESION_EDEFAULT;

	/**
	 * The default value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerName()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerName() <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerName()
	 * @generated
	 * @ordered
	 */
	protected String serverName = SERVER_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationTime() <em>Creation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date CREATION_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationTime() <em>Creation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTime()
	 * @generated
	 * @ordered
	 */
	protected Date creationTime = CREATION_TIME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLocalType() <em>Local Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalType()
	 * @generated
	 * @ordered
	 */
	protected PredefinedDataType localType;

	/**
	 * The cached value of the '{@link #getRemoteType() <em>Remote Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteType()
	 * @generated
	 * @ordered
	 */
	protected PredefinedDataType remoteType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWTypeMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_TYPE_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServerType() {
		return serverType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerType(String newServerType) {
		String oldServerType = serverType;
		serverType = newServerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TYPE_MAPPING__SERVER_TYPE, oldServerType, serverType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServerVesion() {
		return serverVesion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerVesion(String newServerVesion) {
		String oldServerVesion = serverVesion;
		serverVesion = newServerVesion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TYPE_MAPPING__SERVER_VESION, oldServerVesion, serverVesion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerName(String newServerName) {
		String oldServerName = serverName;
		serverName = newServerName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TYPE_MAPPING__SERVER_NAME, oldServerName, serverName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationTime(Date newCreationTime) {
		Date oldCreationTime = creationTime;
		creationTime = newCreationTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TYPE_MAPPING__CREATION_TIME, oldCreationTime, creationTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredefinedDataType getLocalType() {
		if (localType != null && localType.eIsProxy()) {
			InternalEObject oldLocalType = (InternalEObject)localType;
			localType = (PredefinedDataType)eResolveProxy(oldLocalType);
			if (localType != oldLocalType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_TYPE_MAPPING__LOCAL_TYPE, oldLocalType, localType));
			}
		}
		return localType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredefinedDataType basicGetLocalType() {
		return localType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalType(PredefinedDataType newLocalType) {
		PredefinedDataType oldLocalType = localType;
		localType = newLocalType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TYPE_MAPPING__LOCAL_TYPE, oldLocalType, localType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredefinedDataType getRemoteType() {
		if (remoteType != null && remoteType.eIsProxy()) {
			InternalEObject oldRemoteType = (InternalEObject)remoteType;
			remoteType = (PredefinedDataType)eResolveProxy(oldRemoteType);
			if (remoteType != oldRemoteType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_TYPE_MAPPING__REMOTE_TYPE, oldRemoteType, remoteType));
			}
		}
		return remoteType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredefinedDataType basicGetRemoteType() {
		return remoteType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteType(PredefinedDataType newRemoteType) {
		PredefinedDataType oldRemoteType = remoteType;
		remoteType = newRemoteType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TYPE_MAPPING__REMOTE_TYPE, oldRemoteType, remoteType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_TYPE:
				return getServerType();
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_VESION:
				return getServerVesion();
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_NAME:
				return getServerName();
			case LUWPackage.LUW_TYPE_MAPPING__CREATION_TIME:
				return getCreationTime();
			case LUWPackage.LUW_TYPE_MAPPING__LOCAL_TYPE:
				if (resolve) return getLocalType();
				return basicGetLocalType();
			case LUWPackage.LUW_TYPE_MAPPING__REMOTE_TYPE:
				if (resolve) return getRemoteType();
				return basicGetRemoteType();
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
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_TYPE:
				setServerType((String)newValue);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_VESION:
				setServerVesion((String)newValue);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_NAME:
				setServerName((String)newValue);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__CREATION_TIME:
				setCreationTime((Date)newValue);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__LOCAL_TYPE:
				setLocalType((PredefinedDataType)newValue);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__REMOTE_TYPE:
				setRemoteType((PredefinedDataType)newValue);
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
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_TYPE:
				setServerType(SERVER_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_VESION:
				setServerVesion(SERVER_VESION_EDEFAULT);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_NAME:
				setServerName(SERVER_NAME_EDEFAULT);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__CREATION_TIME:
				setCreationTime(CREATION_TIME_EDEFAULT);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__LOCAL_TYPE:
				setLocalType((PredefinedDataType)null);
				return;
			case LUWPackage.LUW_TYPE_MAPPING__REMOTE_TYPE:
				setRemoteType((PredefinedDataType)null);
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
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_TYPE:
				return SERVER_TYPE_EDEFAULT == null ? serverType != null : !SERVER_TYPE_EDEFAULT.equals(serverType);
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_VESION:
				return SERVER_VESION_EDEFAULT == null ? serverVesion != null : !SERVER_VESION_EDEFAULT.equals(serverVesion);
			case LUWPackage.LUW_TYPE_MAPPING__SERVER_NAME:
				return SERVER_NAME_EDEFAULT == null ? serverName != null : !SERVER_NAME_EDEFAULT.equals(serverName);
			case LUWPackage.LUW_TYPE_MAPPING__CREATION_TIME:
				return CREATION_TIME_EDEFAULT == null ? creationTime != null : !CREATION_TIME_EDEFAULT.equals(creationTime);
			case LUWPackage.LUW_TYPE_MAPPING__LOCAL_TYPE:
				return localType != null;
			case LUWPackage.LUW_TYPE_MAPPING__REMOTE_TYPE:
				return remoteType != null;
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
		result.append(" (serverType: "); //$NON-NLS-1$
		result.append(serverType);
		result.append(", serverVesion: "); //$NON-NLS-1$
		result.append(serverVesion);
		result.append(", serverName: "); //$NON-NLS-1$
		result.append(serverName);
		result.append(", creationTime: "); //$NON-NLS-1$
		result.append(creationTime);
		result.append(')');
		return result.toString();
	}

} //LUWTypeMappingImpl
