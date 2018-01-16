/**
 * <copyright>
 * </copyright>
 *
 * $Id: RelationalRemoteServerImpl.java,v 1.10 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Remote Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteServerImpl#getLUWServer <em>LUW Server</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteServerImpl#getDatabase <em>Database</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RelationalRemoteServerImpl extends SQLObjectImpl implements RelationalRemoteServer {
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
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected Database database;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RelationalRemoteServerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.RELATIONAL_REMOTE_SERVER;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER, oldLUWServer, luwServer));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER, oldLUWServer, newLUWServer);
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
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER, newLUWServer, newLUWServer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Database getDatabase() {
		if (database != null && database.eIsProxy()) {
			InternalEObject oldDatabase = (InternalEObject)database;
			database = (Database)eResolveProxy(oldDatabase);
			if (database != oldDatabase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.RELATIONAL_REMOTE_SERVER__DATABASE, oldDatabase, database));
			}
		}
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Database basicGetDatabase() {
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(Database newDatabase) {
		Database oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.RELATIONAL_REMOTE_SERVER__DATABASE, oldDatabase, database));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER:
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
			case LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER:
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
			case LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER:
				if (resolve) return getLUWServer();
				return basicGetLUWServer();
			case LUWPackage.RELATIONAL_REMOTE_SERVER__DATABASE:
				if (resolve) return getDatabase();
				return basicGetDatabase();
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
			case LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER:
				setLUWServer((LUWServer)newValue);
				return;
			case LUWPackage.RELATIONAL_REMOTE_SERVER__DATABASE:
				setDatabase((Database)newValue);
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
			case LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER:
				setLUWServer((LUWServer)null);
				return;
			case LUWPackage.RELATIONAL_REMOTE_SERVER__DATABASE:
				setDatabase((Database)null);
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
			case LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER:
				return luwServer != null;
			case LUWPackage.RELATIONAL_REMOTE_SERVER__DATABASE:
				return database != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == RemoteServer.class) {
			switch (derivedFeatureID) {
				case LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER: return LUWPackage.REMOTE_SERVER__LUW_SERVER;
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
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == RemoteServer.class) {
			switch (baseFeatureID) {
				case LUWPackage.REMOTE_SERVER__LUW_SERVER: return LUWPackage.RELATIONAL_REMOTE_SERVER__LUW_SERVER;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //RemoteDatabaseImpl
