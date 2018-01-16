/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Function;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getServerType <em>Server Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getServerVersion <em>Server Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getServerName <em>Server Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getCreationTime <em>Creation Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getInstsPerInvoc <em>Insts Per Invoc</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getInstsPerArgByte <em>Insts Per Arg Byte</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getIosPerInvoc <em>Ios Per Invoc</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getIosPerArgByte <em>Ios Per Arg Byte</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getLocalFunction <em>Local Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getRemoteFunction <em>Remote Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl#getLUWDatabase <em>LUW Database</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWFunctionMappingImpl extends SQLObjectImpl implements LUWFunctionMapping {
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
	 * The default value of the '{@link #getServerVersion() <em>Server Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String SERVER_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getServerVersion() <em>Server Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServerVersion()
	 * @generated
	 * @ordered
	 */
	protected String serverVersion = SERVER_VERSION_EDEFAULT;

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
	 * The default value of the '{@link #isDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISABLED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisabled()
	 * @generated
	 * @ordered
	 */
	protected boolean disabled = DISABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getInstsPerInvoc() <em>Insts Per Invoc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstsPerInvoc()
	 * @generated
	 * @ordered
	 */
	protected static final int INSTS_PER_INVOC_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getInstsPerInvoc() <em>Insts Per Invoc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstsPerInvoc()
	 * @generated
	 * @ordered
	 */
	protected int instsPerInvoc = INSTS_PER_INVOC_EDEFAULT;

	/**
	 * The default value of the '{@link #getInstsPerArgByte() <em>Insts Per Arg Byte</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstsPerArgByte()
	 * @generated
	 * @ordered
	 */
	protected static final int INSTS_PER_ARG_BYTE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getInstsPerArgByte() <em>Insts Per Arg Byte</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstsPerArgByte()
	 * @generated
	 * @ordered
	 */
	protected int instsPerArgByte = INSTS_PER_ARG_BYTE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIosPerInvoc() <em>Ios Per Invoc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIosPerInvoc()
	 * @generated
	 * @ordered
	 */
	protected static final int IOS_PER_INVOC_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIosPerInvoc() <em>Ios Per Invoc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIosPerInvoc()
	 * @generated
	 * @ordered
	 */
	protected int iosPerInvoc = IOS_PER_INVOC_EDEFAULT;

	/**
	 * The default value of the '{@link #getIosPerArgByte() <em>Ios Per Arg Byte</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIosPerArgByte()
	 * @generated
	 * @ordered
	 */
	protected static final int IOS_PER_ARG_BYTE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIosPerArgByte() <em>Ios Per Arg Byte</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIosPerArgByte()
	 * @generated
	 * @ordered
	 */
	protected int iosPerArgByte = IOS_PER_ARG_BYTE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected EList options;

	/**
	 * The cached value of the '{@link #getLocalFunction() <em>Local Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalFunction()
	 * @generated
	 * @ordered
	 */
	protected DB2Function localFunction;

	/**
	 * The cached value of the '{@link #getRemoteFunction() <em>Remote Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteFunction()
	 * @generated
	 * @ordered
	 */
	protected Function remoteFunction;

	/**
	 * The cached value of the '{@link #getLUWDatabase() <em>LUW Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLUWDatabase()
	 * @generated
	 * @ordered
	 */
	protected LUWDatabase luwDatabase;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWFunctionMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_FUNCTION_MAPPING;
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__SERVER_TYPE, oldServerType, serverType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getServerVersion() {
		return serverVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServerVersion(String newServerVersion) {
		String oldServerVersion = serverVersion;
		serverVersion = newServerVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__SERVER_VERSION, oldServerVersion, serverVersion));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__SERVER_NAME, oldServerName, serverName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__CREATION_TIME, oldCreationTime, creationTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisabled(boolean newDisabled) {
		boolean oldDisabled = disabled;
		disabled = newDisabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__DISABLED, oldDisabled, disabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInstsPerInvoc() {
		return instsPerInvoc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstsPerInvoc(int newInstsPerInvoc) {
		int oldInstsPerInvoc = instsPerInvoc;
		instsPerInvoc = newInstsPerInvoc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_INVOC, oldInstsPerInvoc, instsPerInvoc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInstsPerArgByte() {
		return instsPerArgByte;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstsPerArgByte(int newInstsPerArgByte) {
		int oldInstsPerArgByte = instsPerArgByte;
		instsPerArgByte = newInstsPerArgByte;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_ARG_BYTE, oldInstsPerArgByte, instsPerArgByte));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIosPerInvoc() {
		return iosPerInvoc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIosPerInvoc(int newIosPerInvoc) {
		int oldIosPerInvoc = iosPerInvoc;
		iosPerInvoc = newIosPerInvoc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_INVOC, oldIosPerInvoc, iosPerInvoc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIosPerArgByte() {
		return iosPerArgByte;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIosPerArgByte(int newIosPerArgByte) {
		int oldIosPerArgByte = iosPerArgByte;
		iosPerArgByte = newIosPerArgByte;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_ARG_BYTE, oldIosPerArgByte, iosPerArgByte));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOptions() {
		if (options == null) {
			options = new EObjectContainmentEList(LUWOption.class, this, LUWPackage.LUW_FUNCTION_MAPPING__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Function getLocalFunction() {
		if (localFunction != null && localFunction.eIsProxy()) {
			InternalEObject oldLocalFunction = (InternalEObject)localFunction;
			localFunction = (DB2Function)eResolveProxy(oldLocalFunction);
			if (localFunction != oldLocalFunction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_FUNCTION_MAPPING__LOCAL_FUNCTION, oldLocalFunction, localFunction));
			}
		}
		return localFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Function basicGetLocalFunction() {
		return localFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalFunction(DB2Function newLocalFunction) {
		DB2Function oldLocalFunction = localFunction;
		localFunction = newLocalFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__LOCAL_FUNCTION, oldLocalFunction, localFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function getRemoteFunction() {
		if (remoteFunction != null && remoteFunction.eIsProxy()) {
			InternalEObject oldRemoteFunction = (InternalEObject)remoteFunction;
			remoteFunction = (Function)eResolveProxy(oldRemoteFunction);
			if (remoteFunction != oldRemoteFunction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_FUNCTION_MAPPING__REMOTE_FUNCTION, oldRemoteFunction, remoteFunction));
			}
		}
		return remoteFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function basicGetRemoteFunction() {
		return remoteFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteFunction(Function newRemoteFunction) {
		Function oldRemoteFunction = remoteFunction;
		remoteFunction = newRemoteFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__REMOTE_FUNCTION, oldRemoteFunction, remoteFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabase getLUWDatabase() {
		if (luwDatabase != null && luwDatabase.eIsProxy()) {
			InternalEObject oldLUWDatabase = (InternalEObject)luwDatabase;
			luwDatabase = (LUWDatabase)eResolveProxy(oldLUWDatabase);
			if (luwDatabase != oldLUWDatabase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE, oldLUWDatabase, luwDatabase));
			}
		}
		return luwDatabase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabase basicGetLUWDatabase() {
		return luwDatabase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLUWDatabase(LUWDatabase newLUWDatabase, NotificationChain msgs) {
		LUWDatabase oldLUWDatabase = luwDatabase;
		luwDatabase = newLUWDatabase;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE, oldLUWDatabase, newLUWDatabase);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLUWDatabase(LUWDatabase newLUWDatabase) {
		if (newLUWDatabase != luwDatabase) {
			NotificationChain msgs = null;
			if (luwDatabase != null)
				msgs = ((InternalEObject)luwDatabase).eInverseRemove(this, LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS, LUWDatabase.class, msgs);
			if (newLUWDatabase != null)
				msgs = ((InternalEObject)newLUWDatabase).eInverseAdd(this, LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS, LUWDatabase.class, msgs);
			msgs = basicSetLUWDatabase(newLUWDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE, newLUWDatabase, newLUWDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE:
				if (luwDatabase != null)
					msgs = ((InternalEObject)luwDatabase).eInverseRemove(this, LUWPackage.LUW_DATABASE__FUNCTION_MAPPINGS, LUWDatabase.class, msgs);
				return basicSetLUWDatabase((LUWDatabase)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_FUNCTION_MAPPING__OPTIONS:
				return ((InternalEList)getOptions()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE:
				return basicSetLUWDatabase(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_TYPE:
				return getServerType();
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_VERSION:
				return getServerVersion();
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_NAME:
				return getServerName();
			case LUWPackage.LUW_FUNCTION_MAPPING__CREATION_TIME:
				return getCreationTime();
			case LUWPackage.LUW_FUNCTION_MAPPING__DISABLED:
				return isDisabled() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_INVOC:
				return new Integer(getInstsPerInvoc());
			case LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_ARG_BYTE:
				return new Integer(getInstsPerArgByte());
			case LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_INVOC:
				return new Integer(getIosPerInvoc());
			case LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_ARG_BYTE:
				return new Integer(getIosPerArgByte());
			case LUWPackage.LUW_FUNCTION_MAPPING__OPTIONS:
				return getOptions();
			case LUWPackage.LUW_FUNCTION_MAPPING__LOCAL_FUNCTION:
				if (resolve) return getLocalFunction();
				return basicGetLocalFunction();
			case LUWPackage.LUW_FUNCTION_MAPPING__REMOTE_FUNCTION:
				if (resolve) return getRemoteFunction();
				return basicGetRemoteFunction();
			case LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE:
				if (resolve) return getLUWDatabase();
				return basicGetLUWDatabase();
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
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_TYPE:
				setServerType((String)newValue);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_VERSION:
				setServerVersion((String)newValue);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_NAME:
				setServerName((String)newValue);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__CREATION_TIME:
				setCreationTime((Date)newValue);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__DISABLED:
				setDisabled(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_INVOC:
				setInstsPerInvoc(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_ARG_BYTE:
				setInstsPerArgByte(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_INVOC:
				setIosPerInvoc(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_ARG_BYTE:
				setIosPerArgByte(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__OPTIONS:
				getOptions().clear();
				getOptions().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__LOCAL_FUNCTION:
				setLocalFunction((DB2Function)newValue);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__REMOTE_FUNCTION:
				setRemoteFunction((Function)newValue);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE:
				setLUWDatabase((LUWDatabase)newValue);
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
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_TYPE:
				setServerType(SERVER_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_VERSION:
				setServerVersion(SERVER_VERSION_EDEFAULT);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_NAME:
				setServerName(SERVER_NAME_EDEFAULT);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__CREATION_TIME:
				setCreationTime(CREATION_TIME_EDEFAULT);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__DISABLED:
				setDisabled(DISABLED_EDEFAULT);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_INVOC:
				setInstsPerInvoc(INSTS_PER_INVOC_EDEFAULT);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_ARG_BYTE:
				setInstsPerArgByte(INSTS_PER_ARG_BYTE_EDEFAULT);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_INVOC:
				setIosPerInvoc(IOS_PER_INVOC_EDEFAULT);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_ARG_BYTE:
				setIosPerArgByte(IOS_PER_ARG_BYTE_EDEFAULT);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__OPTIONS:
				getOptions().clear();
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__LOCAL_FUNCTION:
				setLocalFunction((DB2Function)null);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__REMOTE_FUNCTION:
				setRemoteFunction((Function)null);
				return;
			case LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE:
				setLUWDatabase((LUWDatabase)null);
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
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_TYPE:
				return SERVER_TYPE_EDEFAULT == null ? serverType != null : !SERVER_TYPE_EDEFAULT.equals(serverType);
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_VERSION:
				return SERVER_VERSION_EDEFAULT == null ? serverVersion != null : !SERVER_VERSION_EDEFAULT.equals(serverVersion);
			case LUWPackage.LUW_FUNCTION_MAPPING__SERVER_NAME:
				return SERVER_NAME_EDEFAULT == null ? serverName != null : !SERVER_NAME_EDEFAULT.equals(serverName);
			case LUWPackage.LUW_FUNCTION_MAPPING__CREATION_TIME:
				return CREATION_TIME_EDEFAULT == null ? creationTime != null : !CREATION_TIME_EDEFAULT.equals(creationTime);
			case LUWPackage.LUW_FUNCTION_MAPPING__DISABLED:
				return disabled != DISABLED_EDEFAULT;
			case LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_INVOC:
				return instsPerInvoc != INSTS_PER_INVOC_EDEFAULT;
			case LUWPackage.LUW_FUNCTION_MAPPING__INSTS_PER_ARG_BYTE:
				return instsPerArgByte != INSTS_PER_ARG_BYTE_EDEFAULT;
			case LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_INVOC:
				return iosPerInvoc != IOS_PER_INVOC_EDEFAULT;
			case LUWPackage.LUW_FUNCTION_MAPPING__IOS_PER_ARG_BYTE:
				return iosPerArgByte != IOS_PER_ARG_BYTE_EDEFAULT;
			case LUWPackage.LUW_FUNCTION_MAPPING__OPTIONS:
				return options != null && !options.isEmpty();
			case LUWPackage.LUW_FUNCTION_MAPPING__LOCAL_FUNCTION:
				return localFunction != null;
			case LUWPackage.LUW_FUNCTION_MAPPING__REMOTE_FUNCTION:
				return remoteFunction != null;
			case LUWPackage.LUW_FUNCTION_MAPPING__LUW_DATABASE:
				return luwDatabase != null;
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
		result.append(", serverVersion: "); //$NON-NLS-1$
		result.append(serverVersion);
		result.append(", serverName: "); //$NON-NLS-1$
		result.append(serverName);
		result.append(", creationTime: "); //$NON-NLS-1$
		result.append(creationTime);
		result.append(", disabled: "); //$NON-NLS-1$
		result.append(disabled);
		result.append(", instsPerInvoc: "); //$NON-NLS-1$
		result.append(instsPerInvoc);
		result.append(", instsPerArgByte: "); //$NON-NLS-1$
		result.append(instsPerArgByte);
		result.append(", iosPerInvoc: "); //$NON-NLS-1$
		result.append(iosPerInvoc);
		result.append(", iosPerArgByte: "); //$NON-NLS-1$
		result.append(iosPerArgByte);
		result.append(')');
		return result.toString();
	}

} //LUWFunctionMappingImpl
