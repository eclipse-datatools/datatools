/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Nickname</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNicknameImpl#getRemoteDataSet <em>Remote Data Set</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNicknameImpl#getServer <em>Server</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LUWNicknameImpl extends LUWTableImpl implements LUWNickname {
	/**
	 * The cached value of the '{@link #getRemoteDataSet() <em>Remote Data Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteDataSet()
	 * @generated
	 * @ordered
	 */
	protected RemoteDataSet remoteDataSet;

	/**
	 * The cached value of the '{@link #getServer() <em>Server</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServer()
	 * @generated
	 * @ordered
	 */
	protected LUWServer server;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWNicknameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_NICKNAME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoteDataSet getRemoteDataSet() {
		if (remoteDataSet != null && remoteDataSet.eIsProxy()) {
			InternalEObject oldRemoteDataSet = (InternalEObject)remoteDataSet;
			remoteDataSet = (RemoteDataSet)eResolveProxy(oldRemoteDataSet);
			if (remoteDataSet != oldRemoteDataSet) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET, oldRemoteDataSet, remoteDataSet));
			}
		}
		return remoteDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RemoteDataSet basicGetRemoteDataSet() {
		return remoteDataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRemoteDataSet(RemoteDataSet newRemoteDataSet, NotificationChain msgs) {
		RemoteDataSet oldRemoteDataSet = remoteDataSet;
		remoteDataSet = newRemoteDataSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET, oldRemoteDataSet, newRemoteDataSet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteDataSet(RemoteDataSet newRemoteDataSet) {
		if (newRemoteDataSet != remoteDataSet) {
			NotificationChain msgs = null;
			if (remoteDataSet != null)
				msgs = ((InternalEObject)remoteDataSet).eInverseRemove(this, LUWPackage.REMOTE_DATA_SET__NICKNAME, RemoteDataSet.class, msgs);
			if (newRemoteDataSet != null)
				msgs = ((InternalEObject)newRemoteDataSet).eInverseAdd(this, LUWPackage.REMOTE_DATA_SET__NICKNAME, RemoteDataSet.class, msgs);
			msgs = basicSetRemoteDataSet(newRemoteDataSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET, newRemoteDataSet, newRemoteDataSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWServer getServer() {
		if (server != null && server.eIsProxy()) {
			InternalEObject oldServer = (InternalEObject)server;
			server = (LUWServer)eResolveProxy(oldServer);
			if (server != oldServer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_NICKNAME__SERVER, oldServer, server));
			}
		}
		return server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWServer basicGetServer() {
		return server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServer(LUWServer newServer, NotificationChain msgs) {
		LUWServer oldServer = server;
		server = newServer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_NICKNAME__SERVER, oldServer, newServer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServer(LUWServer newServer) {
		if (newServer != server) {
			NotificationChain msgs = null;
			if (server != null)
				msgs = ((InternalEObject)server).eInverseRemove(this, LUWPackage.LUW_SERVER__NICKNAMES, LUWServer.class, msgs);
			if (newServer != null)
				msgs = ((InternalEObject)newServer).eInverseAdd(this, LUWPackage.LUW_SERVER__NICKNAMES, LUWServer.class, msgs);
			msgs = basicSetServer(newServer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_NICKNAME__SERVER, newServer, newServer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET:
				if (remoteDataSet != null)
					msgs = ((InternalEObject)remoteDataSet).eInverseRemove(this, LUWPackage.REMOTE_DATA_SET__NICKNAME, RemoteDataSet.class, msgs);
				return basicSetRemoteDataSet((RemoteDataSet)otherEnd, msgs);
			case LUWPackage.LUW_NICKNAME__SERVER:
				if (server != null)
					msgs = ((InternalEObject)server).eInverseRemove(this, LUWPackage.LUW_SERVER__NICKNAMES, LUWServer.class, msgs);
				return basicSetServer((LUWServer)otherEnd, msgs);
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
			case LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET:
				return basicSetRemoteDataSet(null, msgs);
			case LUWPackage.LUW_NICKNAME__SERVER:
				return basicSetServer(null, msgs);
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
			case LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET:
				if (resolve) return getRemoteDataSet();
				return basicGetRemoteDataSet();
			case LUWPackage.LUW_NICKNAME__SERVER:
				if (resolve) return getServer();
				return basicGetServer();
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
			case LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET:
				setRemoteDataSet((RemoteDataSet)newValue);
				return;
			case LUWPackage.LUW_NICKNAME__SERVER:
				setServer((LUWServer)newValue);
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
			case LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET:
				setRemoteDataSet((RemoteDataSet)null);
				return;
			case LUWPackage.LUW_NICKNAME__SERVER:
				setServer((LUWServer)null);
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
			case LUWPackage.LUW_NICKNAME__REMOTE_DATA_SET:
				return remoteDataSet != null;
			case LUWPackage.LUW_NICKNAME__SERVER:
				return server != null;
		}
		return super.eIsSet(featureID);
	}

} //LUWNicknameImpl
