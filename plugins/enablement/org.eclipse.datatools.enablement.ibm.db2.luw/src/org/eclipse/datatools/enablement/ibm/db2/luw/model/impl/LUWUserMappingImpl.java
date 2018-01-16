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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWUserMappingImpl#getLocalAuthId <em>Local Auth Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWUserMappingImpl#getServer <em>Server</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWUserMappingImpl#getOptions <em>Options</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWUserMappingImpl extends SQLObjectImpl implements LUWUserMapping {
	/**
	 * The default value of the '{@link #getLocalAuthId() <em>Local Auth Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalAuthId()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_AUTH_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalAuthId() <em>Local Auth Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalAuthId()
	 * @generated
	 * @ordered
	 */
	protected String localAuthId = LOCAL_AUTH_ID_EDEFAULT;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWUserMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_USER_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocalAuthId() {
		return localAuthId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalAuthId(String newLocalAuthId) {
		String oldLocalAuthId = localAuthId;
		localAuthId = newLocalAuthId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_USER_MAPPING__LOCAL_AUTH_ID, oldLocalAuthId, localAuthId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWServer getServer() {
		if (eContainerFeatureID() != LUWPackage.LUW_USER_MAPPING__SERVER) return null;
		return (LUWServer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetServer(LUWServer newServer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newServer, LUWPackage.LUW_USER_MAPPING__SERVER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setServer(LUWServer newServer) {
		if (newServer != eInternalContainer() || (eContainerFeatureID() != LUWPackage.LUW_USER_MAPPING__SERVER && newServer != null)) {
			if (EcoreUtil.isAncestor(this, newServer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newServer != null)
				msgs = ((InternalEObject)newServer).eInverseAdd(this, LUWPackage.LUW_SERVER__USER_MAPPINGS, LUWServer.class, msgs);
			msgs = basicSetServer(newServer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_USER_MAPPING__SERVER, newServer, newServer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOptions() {
		if (options == null) {
			options = new EObjectContainmentEList(LUWOption.class, this, LUWPackage.LUW_USER_MAPPING__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_USER_MAPPING__SERVER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
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
			case LUWPackage.LUW_USER_MAPPING__SERVER:
				return basicSetServer(null, msgs);
			case LUWPackage.LUW_USER_MAPPING__OPTIONS:
				return ((InternalEList)getOptions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case LUWPackage.LUW_USER_MAPPING__SERVER:
				return eInternalContainer().eInverseRemove(this, LUWPackage.LUW_SERVER__USER_MAPPINGS, LUWServer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_USER_MAPPING__LOCAL_AUTH_ID:
				return getLocalAuthId();
			case LUWPackage.LUW_USER_MAPPING__SERVER:
				return getServer();
			case LUWPackage.LUW_USER_MAPPING__OPTIONS:
				return getOptions();
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
			case LUWPackage.LUW_USER_MAPPING__LOCAL_AUTH_ID:
				setLocalAuthId((String)newValue);
				return;
			case LUWPackage.LUW_USER_MAPPING__SERVER:
				setServer((LUWServer)newValue);
				return;
			case LUWPackage.LUW_USER_MAPPING__OPTIONS:
				getOptions().clear();
				getOptions().addAll((Collection)newValue);
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
			case LUWPackage.LUW_USER_MAPPING__LOCAL_AUTH_ID:
				setLocalAuthId(LOCAL_AUTH_ID_EDEFAULT);
				return;
			case LUWPackage.LUW_USER_MAPPING__SERVER:
				setServer((LUWServer)null);
				return;
			case LUWPackage.LUW_USER_MAPPING__OPTIONS:
				getOptions().clear();
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
			case LUWPackage.LUW_USER_MAPPING__LOCAL_AUTH_ID:
				return LOCAL_AUTH_ID_EDEFAULT == null ? localAuthId != null : !LOCAL_AUTH_ID_EDEFAULT.equals(localAuthId);
			case LUWPackage.LUW_USER_MAPPING__SERVER:
				return getServer() != null;
			case LUWPackage.LUW_USER_MAPPING__OPTIONS:
				return options != null && !options.isEmpty();
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
		result.append(" (localAuthId: "); //$NON-NLS-1$
		result.append(localAuthId);
		result.append(')');
		return result.toString();
	}

} //LUWUserMappingImpl
