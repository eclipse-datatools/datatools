/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Server</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl#getServerType <em>Server Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl#getServerVersion <em>Server Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl#getUserMappings <em>User Mappings</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl#getWrapper <em>Wrapper</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl#getNicknames <em>Nicknames</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl#getLUWDatabase <em>LUW Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl#getRemoteServer <em>Remote Server</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LUWServerImpl extends SQLObjectImpl implements LUWServer {
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
	 * The cached value of the '{@link #getUserMappings() <em>User Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserMappings()
	 * @generated
	 * @ordered
	 */
	protected EList userMappings;

	/**
	 * The cached value of the '{@link #getWrapper() <em>Wrapper</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWrapper()
	 * @generated
	 * @ordered
	 */
	protected LUWWrapper wrapper;

	/**
	 * The cached value of the '{@link #getNicknames() <em>Nicknames</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNicknames()
	 * @generated
	 * @ordered
	 */
	protected EList nicknames;

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
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected EList options;

	/**
	 * The cached value of the '{@link #getRemoteServer() <em>Remote Server</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteServer()
	 * @generated
	 * @ordered
	 */
	protected RemoteServer remoteServer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWServerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_SERVER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SERVER__SERVER_TYPE, oldServerType, serverType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SERVER__SERVER_VERSION, oldServerVersion, serverVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getUserMappings() {
		if (userMappings == null) {
			userMappings = new EObjectContainmentWithInverseEList(LUWUserMapping.class, this, LUWPackage.LUW_SERVER__USER_MAPPINGS, LUWPackage.LUW_USER_MAPPING__SERVER);
		}
		return userMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWWrapper getWrapper() {
		if (wrapper != null && wrapper.eIsProxy()) {
			InternalEObject oldWrapper = (InternalEObject)wrapper;
			wrapper = (LUWWrapper)eResolveProxy(oldWrapper);
			if (wrapper != oldWrapper) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_SERVER__WRAPPER, oldWrapper, wrapper));
			}
		}
		return wrapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWWrapper basicGetWrapper() {
		return wrapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWrapper(LUWWrapper newWrapper, NotificationChain msgs) {
		LUWWrapper oldWrapper = wrapper;
		wrapper = newWrapper;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SERVER__WRAPPER, oldWrapper, newWrapper);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWrapper(LUWWrapper newWrapper) {
		if (newWrapper != wrapper) {
			NotificationChain msgs = null;
			if (wrapper != null)
				msgs = ((InternalEObject)wrapper).eInverseRemove(this, LUWPackage.LUW_WRAPPER__SERVERS, LUWWrapper.class, msgs);
			if (newWrapper != null)
				msgs = ((InternalEObject)newWrapper).eInverseAdd(this, LUWPackage.LUW_WRAPPER__SERVERS, LUWWrapper.class, msgs);
			msgs = basicSetWrapper(newWrapper, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SERVER__WRAPPER, newWrapper, newWrapper));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNicknames() {
		if (nicknames == null) {
			nicknames = new EObjectWithInverseResolvingEList(LUWNickname.class, this, LUWPackage.LUW_SERVER__NICKNAMES, LUWPackage.LUW_NICKNAME__SERVER);
		}
		return nicknames;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_SERVER__LUW_DATABASE, oldLUWDatabase, luwDatabase));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SERVER__LUW_DATABASE, oldLUWDatabase, newLUWDatabase);
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
				msgs = ((InternalEObject)luwDatabase).eInverseRemove(this, LUWPackage.LUW_DATABASE__SERVERS, LUWDatabase.class, msgs);
			if (newLUWDatabase != null)
				msgs = ((InternalEObject)newLUWDatabase).eInverseAdd(this, LUWPackage.LUW_DATABASE__SERVERS, LUWDatabase.class, msgs);
			msgs = basicSetLUWDatabase(newLUWDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SERVER__LUW_DATABASE, newLUWDatabase, newLUWDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOptions() {
		if (options == null) {
			options = new EObjectContainmentEList(LUWOption.class, this, LUWPackage.LUW_SERVER__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoteServer getRemoteServer() {
		if (remoteServer != null && remoteServer.eIsProxy()) {
			InternalEObject oldRemoteServer = (InternalEObject)remoteServer;
			remoteServer = (RemoteServer)eResolveProxy(oldRemoteServer);
			if (remoteServer != oldRemoteServer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_SERVER__REMOTE_SERVER, oldRemoteServer, remoteServer));
			}
		}
		return remoteServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoteServer basicGetRemoteServer() {
		return remoteServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRemoteServer(RemoteServer newRemoteServer, NotificationChain msgs) {
		RemoteServer oldRemoteServer = remoteServer;
		remoteServer = newRemoteServer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SERVER__REMOTE_SERVER, oldRemoteServer, newRemoteServer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteServer(RemoteServer newRemoteServer) {
		if (newRemoteServer != remoteServer) {
			NotificationChain msgs = null;
			if (remoteServer != null)
				msgs = ((InternalEObject)remoteServer).eInverseRemove(this, LUWPackage.REMOTE_SERVER__LUW_SERVER, RemoteServer.class, msgs);
			if (newRemoteServer != null)
				msgs = ((InternalEObject)newRemoteServer).eInverseAdd(this, LUWPackage.REMOTE_SERVER__LUW_SERVER, RemoteServer.class, msgs);
			msgs = basicSetRemoteServer(newRemoteServer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_SERVER__REMOTE_SERVER, newRemoteServer, newRemoteServer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_SERVER__USER_MAPPINGS:
				return ((InternalEList)getUserMappings()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_SERVER__WRAPPER:
				if (wrapper != null)
					msgs = ((InternalEObject)wrapper).eInverseRemove(this, LUWPackage.LUW_WRAPPER__SERVERS, LUWWrapper.class, msgs);
				return basicSetWrapper((LUWWrapper)otherEnd, msgs);
			case LUWPackage.LUW_SERVER__NICKNAMES:
				return ((InternalEList)getNicknames()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_SERVER__LUW_DATABASE:
				if (luwDatabase != null)
					msgs = ((InternalEObject)luwDatabase).eInverseRemove(this, LUWPackage.LUW_DATABASE__SERVERS, LUWDatabase.class, msgs);
				return basicSetLUWDatabase((LUWDatabase)otherEnd, msgs);
			case LUWPackage.LUW_SERVER__REMOTE_SERVER:
				if (remoteServer != null)
					msgs = ((InternalEObject)remoteServer).eInverseRemove(this, LUWPackage.REMOTE_SERVER__LUW_SERVER, RemoteServer.class, msgs);
				return basicSetRemoteServer((RemoteServer)otherEnd, msgs);
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
			case LUWPackage.LUW_SERVER__USER_MAPPINGS:
				return ((InternalEList)getUserMappings()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_SERVER__WRAPPER:
				return basicSetWrapper(null, msgs);
			case LUWPackage.LUW_SERVER__NICKNAMES:
				return ((InternalEList)getNicknames()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_SERVER__LUW_DATABASE:
				return basicSetLUWDatabase(null, msgs);
			case LUWPackage.LUW_SERVER__OPTIONS:
				return ((InternalEList)getOptions()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_SERVER__REMOTE_SERVER:
				return basicSetRemoteServer(null, msgs);
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
			case LUWPackage.LUW_SERVER__SERVER_TYPE:
				return getServerType();
			case LUWPackage.LUW_SERVER__SERVER_VERSION:
				return getServerVersion();
			case LUWPackage.LUW_SERVER__USER_MAPPINGS:
				return getUserMappings();
			case LUWPackage.LUW_SERVER__WRAPPER:
				if (resolve) return getWrapper();
				return basicGetWrapper();
			case LUWPackage.LUW_SERVER__NICKNAMES:
				return getNicknames();
			case LUWPackage.LUW_SERVER__LUW_DATABASE:
				if (resolve) return getLUWDatabase();
				return basicGetLUWDatabase();
			case LUWPackage.LUW_SERVER__OPTIONS:
				return getOptions();
			case LUWPackage.LUW_SERVER__REMOTE_SERVER:
				if (resolve) return getRemoteServer();
				return basicGetRemoteServer();
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
			case LUWPackage.LUW_SERVER__SERVER_TYPE:
				setServerType((String)newValue);
				return;
			case LUWPackage.LUW_SERVER__SERVER_VERSION:
				setServerVersion((String)newValue);
				return;
			case LUWPackage.LUW_SERVER__USER_MAPPINGS:
				getUserMappings().clear();
				getUserMappings().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_SERVER__WRAPPER:
				setWrapper((LUWWrapper)newValue);
				return;
			case LUWPackage.LUW_SERVER__NICKNAMES:
				getNicknames().clear();
				getNicknames().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_SERVER__LUW_DATABASE:
				setLUWDatabase((LUWDatabase)newValue);
				return;
			case LUWPackage.LUW_SERVER__OPTIONS:
				getOptions().clear();
				getOptions().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_SERVER__REMOTE_SERVER:
				setRemoteServer((RemoteServer)newValue);
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
			case LUWPackage.LUW_SERVER__SERVER_TYPE:
				setServerType(SERVER_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_SERVER__SERVER_VERSION:
				setServerVersion(SERVER_VERSION_EDEFAULT);
				return;
			case LUWPackage.LUW_SERVER__USER_MAPPINGS:
				getUserMappings().clear();
				return;
			case LUWPackage.LUW_SERVER__WRAPPER:
				setWrapper((LUWWrapper)null);
				return;
			case LUWPackage.LUW_SERVER__NICKNAMES:
				getNicknames().clear();
				return;
			case LUWPackage.LUW_SERVER__LUW_DATABASE:
				setLUWDatabase((LUWDatabase)null);
				return;
			case LUWPackage.LUW_SERVER__OPTIONS:
				getOptions().clear();
				return;
			case LUWPackage.LUW_SERVER__REMOTE_SERVER:
				setRemoteServer((RemoteServer)null);
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
			case LUWPackage.LUW_SERVER__SERVER_TYPE:
				return SERVER_TYPE_EDEFAULT == null ? serverType != null : !SERVER_TYPE_EDEFAULT.equals(serverType);
			case LUWPackage.LUW_SERVER__SERVER_VERSION:
				return SERVER_VERSION_EDEFAULT == null ? serverVersion != null : !SERVER_VERSION_EDEFAULT.equals(serverVersion);
			case LUWPackage.LUW_SERVER__USER_MAPPINGS:
				return userMappings != null && !userMappings.isEmpty();
			case LUWPackage.LUW_SERVER__WRAPPER:
				return wrapper != null;
			case LUWPackage.LUW_SERVER__NICKNAMES:
				return nicknames != null && !nicknames.isEmpty();
			case LUWPackage.LUW_SERVER__LUW_DATABASE:
				return luwDatabase != null;
			case LUWPackage.LUW_SERVER__OPTIONS:
				return options != null && !options.isEmpty();
			case LUWPackage.LUW_SERVER__REMOTE_SERVER:
				return remoteServer != null;
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
		result.append(')');
		return result.toString();
	}

} //LUWServerImpl
