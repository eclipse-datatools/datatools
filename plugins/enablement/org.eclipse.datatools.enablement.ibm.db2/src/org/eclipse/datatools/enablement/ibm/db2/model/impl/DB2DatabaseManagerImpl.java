package org.eclipse.datatools.enablement.ibm.db2.model.impl;


import org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Database;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import java.util.Collection;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Database Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseManagerImpl#getDatabases <em>Databases</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseManagerImpl#getDb2Process <em>Db2 Process</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseManagerImpl#getServer <em>Server</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseManagerImpl#getCluster <em>Cluster</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2DatabaseManagerImpl extends SQLObjectImpl implements DB2DatabaseManager {
	/**
	 * The cached value of the '{@link #getDatabases() <em>Databases</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabases()
	 * @generated
	 * @ordered
	 */
	protected EList databases;

	/**
	 * The cached value of the '{@link #getDb2Process() <em>Db2 Process</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDb2Process()
	 * @generated
	 * @ordered
	 */
	protected EList db2Process;

	/**
	 * The cached value of the '{@link #getServer() <em>Server</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServer()
	 * @generated
	 * @ordered
	 */
	protected EList server;

	/**
	 * The cached value of the '{@link #getCluster() <em>Cluster</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCluster()
	 * @generated
	 * @ordered
	 */
	protected DB2Cluster cluster;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2DatabaseManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_DATABASE_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDatabases() {
		if (databases == null) {
			databases = new EObjectResolvingEList(DB2Database.class, this, DB2ModelPackage.DB2_DATABASE_MANAGER__DATABASES);
		}
		return databases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDb2Process() {
		if (db2Process == null) {
			db2Process = new EObjectResolvingEList(DB2ApplicationProcess.class, this, DB2ModelPackage.DB2_DATABASE_MANAGER__DB2_PROCESS);
		}
		return db2Process;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getServer() {
//		if (server == null) {
//			server = new EObjectWithInverseResolvingEList.ManyInverse(LUWAdminServer.class, this, DB2ModelPackage.DB2_DATABASE_MANAGER__SERVER, LUWPackage.LUW_ADMIN_SERVER__INSTANCES);
//		}
		return server;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Cluster getCluster() {
		if (cluster != null && cluster.eIsProxy()) {
			InternalEObject oldCluster = (InternalEObject)cluster;
			cluster = (DB2Cluster)eResolveProxy(oldCluster);
			if (cluster != oldCluster) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_DATABASE_MANAGER__CLUSTER, oldCluster, cluster));
			}
		}
		return cluster;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Cluster basicGetCluster() {
		return cluster;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCluster(DB2Cluster newCluster, NotificationChain msgs) {
		DB2Cluster oldCluster = cluster;
		cluster = newCluster;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_DATABASE_MANAGER__CLUSTER, oldCluster, newCluster);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCluster(DB2Cluster newCluster) {
		if (newCluster != cluster) {
			NotificationChain msgs = null;
			if (cluster != null)
				msgs = ((InternalEObject)cluster).eInverseRemove(this, DB2ModelPackage.DB2_CLUSTER__INSTANCE, DB2Cluster.class, msgs);
			if (newCluster != null)
				msgs = ((InternalEObject)newCluster).eInverseAdd(this, DB2ModelPackage.DB2_CLUSTER__INSTANCE, DB2Cluster.class, msgs);
			msgs = basicSetCluster(newCluster, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_DATABASE_MANAGER__CLUSTER, newCluster, newCluster));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_DATABASE_MANAGER__SERVER:
				return ((InternalEList)getServer()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_DATABASE_MANAGER__CLUSTER:
				if (cluster != null)
					msgs = ((InternalEObject)cluster).eInverseRemove(this, DB2ModelPackage.DB2_CLUSTER__INSTANCE, DB2Cluster.class, msgs);
				return basicSetCluster((DB2Cluster)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_DATABASE_MANAGER__SERVER:
				return ((InternalEList)getServer()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_DATABASE_MANAGER__CLUSTER:
				return basicSetCluster(null, msgs);
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
			case DB2ModelPackage.DB2_DATABASE_MANAGER__DATABASES:
				return getDatabases();
			case DB2ModelPackage.DB2_DATABASE_MANAGER__DB2_PROCESS:
				return getDb2Process();
			case DB2ModelPackage.DB2_DATABASE_MANAGER__SERVER:
				return getServer();
			case DB2ModelPackage.DB2_DATABASE_MANAGER__CLUSTER:
				if (resolve) return getCluster();
				return basicGetCluster();
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
			case DB2ModelPackage.DB2_DATABASE_MANAGER__DATABASES:
				getDatabases().clear();
				getDatabases().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_DATABASE_MANAGER__DB2_PROCESS:
				getDb2Process().clear();
				getDb2Process().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_DATABASE_MANAGER__SERVER:
				getServer().clear();
				getServer().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_DATABASE_MANAGER__CLUSTER:
				setCluster((DB2Cluster)newValue);
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
			case DB2ModelPackage.DB2_DATABASE_MANAGER__DATABASES:
				getDatabases().clear();
				return;
			case DB2ModelPackage.DB2_DATABASE_MANAGER__DB2_PROCESS:
				getDb2Process().clear();
				return;
			case DB2ModelPackage.DB2_DATABASE_MANAGER__SERVER:
				getServer().clear();
				return;
			case DB2ModelPackage.DB2_DATABASE_MANAGER__CLUSTER:
				setCluster((DB2Cluster)null);
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
			case DB2ModelPackage.DB2_DATABASE_MANAGER__DATABASES:
				return databases != null && !databases.isEmpty();
			case DB2ModelPackage.DB2_DATABASE_MANAGER__DB2_PROCESS:
				return db2Process != null && !db2Process.isEmpty();
			case DB2ModelPackage.DB2_DATABASE_MANAGER__SERVER:
				return server != null && !server.isEmpty();
			case DB2ModelPackage.DB2_DATABASE_MANAGER__CLUSTER:
				return cluster != null;
		}
		return super.eIsSet(featureID);
	}

} //DB2DatabaseManagerImpl
