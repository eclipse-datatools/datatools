package org.eclipse.datatools.enablement.ibm.db2.model.impl;


import org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Member;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Member</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl#getHomeHost <em>Home Host</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl#getCurrentHost <em>Current Host</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl#getState <em>State</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl#getCluster <em>Cluster</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2MemberImpl extends SQLObjectImpl implements DB2Member {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected int id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getHomeHost() <em>Home Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHomeHost()
	 * @generated
	 * @ordered
	 */
	protected static final String HOME_HOST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHomeHost() <em>Home Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHomeHost()
	 * @generated
	 * @ordered
	 */
	protected String homeHost = HOME_HOST_EDEFAULT;

	/**
	 * The default value of the '{@link #getCurrentHost() <em>Current Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentHost()
	 * @generated
	 * @ordered
	 */
	protected static final String CURRENT_HOST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCurrentHost() <em>Current Host</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrentHost()
	 * @generated
	 * @ordered
	 */
	protected String currentHost = CURRENT_HOST_EDEFAULT;

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
	protected DB2MemberImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_MEMBER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(int newId) {
		int oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MEMBER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getHomeHost() {
		return homeHost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHomeHost(String newHomeHost) {
		String oldHomeHost = homeHost;
		homeHost = newHomeHost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MEMBER__HOME_HOST, oldHomeHost, homeHost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCurrentHost() {
		return currentHost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrentHost(String newCurrentHost) {
		String oldCurrentHost = currentHost;
		currentHost = newCurrentHost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MEMBER__CURRENT_HOST, oldCurrentHost, currentHost));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_MEMBER__CLUSTER, oldCluster, cluster));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MEMBER__CLUSTER, oldCluster, newCluster);
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
				msgs = ((InternalEObject)cluster).eInverseRemove(this, DB2ModelPackage.DB2_CLUSTER__MEMBERS, DB2Cluster.class, msgs);
			if (newCluster != null)
				msgs = ((InternalEObject)newCluster).eInverseAdd(this, DB2ModelPackage.DB2_CLUSTER__MEMBERS, DB2Cluster.class, msgs);
			msgs = basicSetCluster(newCluster, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_MEMBER__CLUSTER, newCluster, newCluster));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_MEMBER__CLUSTER:
				if (cluster != null)
					msgs = ((InternalEObject)cluster).eInverseRemove(this, DB2ModelPackage.DB2_CLUSTER__MEMBERS, DB2Cluster.class, msgs);
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
			case DB2ModelPackage.DB2_MEMBER__CLUSTER:
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
			case DB2ModelPackage.DB2_MEMBER__ID:
				return new Integer(getId());
			case DB2ModelPackage.DB2_MEMBER__HOME_HOST:
				return getHomeHost();
			case DB2ModelPackage.DB2_MEMBER__CURRENT_HOST:
				return getCurrentHost();
			case DB2ModelPackage.DB2_MEMBER__CLUSTER:
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
			case DB2ModelPackage.DB2_MEMBER__ID:
				setId(((Integer)newValue).intValue());
				return;
			case DB2ModelPackage.DB2_MEMBER__HOME_HOST:
				setHomeHost((String)newValue);
				return;
			case DB2ModelPackage.DB2_MEMBER__CURRENT_HOST:
				setCurrentHost((String)newValue);
				return;
			case DB2ModelPackage.DB2_MEMBER__CLUSTER:
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
			case DB2ModelPackage.DB2_MEMBER__ID:
				setId(ID_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_MEMBER__HOME_HOST:
				setHomeHost(HOME_HOST_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_MEMBER__CURRENT_HOST:
				setCurrentHost(CURRENT_HOST_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_MEMBER__CLUSTER:
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
			case DB2ModelPackage.DB2_MEMBER__ID:
				return id != ID_EDEFAULT;
			case DB2ModelPackage.DB2_MEMBER__HOME_HOST:
				return HOME_HOST_EDEFAULT == null ? homeHost != null : !HOME_HOST_EDEFAULT.equals(homeHost);
			case DB2ModelPackage.DB2_MEMBER__CURRENT_HOST:
				return CURRENT_HOST_EDEFAULT == null ? currentHost != null : !CURRENT_HOST_EDEFAULT.equals(currentHost);
			case DB2ModelPackage.DB2_MEMBER__CLUSTER:
				return cluster != null;
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", homeHost: "); //$NON-NLS-1$
		result.append(homeHost);
		result.append(", currentHost: "); //$NON-NLS-1$
		result.append(currentHost);
		result.append(')');
		return result.toString();
	}

} //DB2MemberImpl
