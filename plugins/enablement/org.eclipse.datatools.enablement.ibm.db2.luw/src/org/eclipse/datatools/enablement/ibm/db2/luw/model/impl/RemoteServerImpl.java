/**
 * <copyright>
 * </copyright>
 *
 * $Id: RemoteServerImpl.java,v 1.10 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Remote Server</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RemoteServerImpl#getLUWServer <em>LUW Server</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RemoteServerImpl extends EObjectImpl implements RemoteServer {
	/**
	 * The cached value of the '{@link #getLUWServer() <em>LUW Server</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLUWServer()
	 * @generated
	 * @ordered
	 */
	protected LUWServer luwServer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RemoteServerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.REMOTE_SERVER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWServer getLUWServer() {
		if (luwServer != null && luwServer.eIsProxy()) {
			InternalEObject oldLUWServer = (InternalEObject)luwServer;
			luwServer = (LUWServer)eResolveProxy(oldLUWServer);
			if (luwServer != oldLUWServer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.REMOTE_SERVER__LUW_SERVER, oldLUWServer, luwServer));
			}
		}
		return luwServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWServer basicGetLUWServer() {
		return luwServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLUWServer(LUWServer newLUWServer, NotificationChain msgs) {
		LUWServer oldLUWServer = luwServer;
		luwServer = newLUWServer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.REMOTE_SERVER__LUW_SERVER, oldLUWServer, newLUWServer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLUWServer(LUWServer newLUWServer) {
		if (newLUWServer != luwServer) {
			NotificationChain msgs = null;
			if (luwServer != null)
				msgs = ((InternalEObject)luwServer).eInverseRemove(this, LUWPackage.LUW_SERVER__REMOTE_SERVER, LUWServer.class, msgs);
			if (newLUWServer != null)
				msgs = ((InternalEObject)newLUWServer).eInverseAdd(this, LUWPackage.LUW_SERVER__REMOTE_SERVER, LUWServer.class, msgs);
			msgs = basicSetLUWServer(newLUWServer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.REMOTE_SERVER__LUW_SERVER, newLUWServer, newLUWServer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.REMOTE_SERVER__LUW_SERVER:
				if (luwServer != null)
					msgs = ((InternalEObject)luwServer).eInverseRemove(this, LUWPackage.LUW_SERVER__REMOTE_SERVER, LUWServer.class, msgs);
				return basicSetLUWServer((LUWServer)otherEnd, msgs);
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
			case LUWPackage.REMOTE_SERVER__LUW_SERVER:
				return basicSetLUWServer(null, msgs);
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
			case LUWPackage.REMOTE_SERVER__LUW_SERVER:
				if (resolve) return getLUWServer();
				return basicGetLUWServer();
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
			case LUWPackage.REMOTE_SERVER__LUW_SERVER:
				setLUWServer((LUWServer)newValue);
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
			case LUWPackage.REMOTE_SERVER__LUW_SERVER:
				setLUWServer((LUWServer)null);
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
			case LUWPackage.REMOTE_SERVER__LUW_SERVER:
				return luwServer != null;
		}
		return super.eIsSet(featureID);
	}

} //RemoteServerImpl
