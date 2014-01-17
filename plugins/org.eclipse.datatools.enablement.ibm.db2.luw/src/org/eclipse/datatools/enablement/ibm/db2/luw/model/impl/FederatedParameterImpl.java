/**
 * <copyright>
 * </copyright>
 *
 * $Id: FederatedParameterImpl.java,v 1.6 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Federated Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedParameterImpl#getRemoteCodePage <em>Remote Code Page</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedParameterImpl#getRemoteParamTypeID <em>Remote Param Type ID</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedParameterImpl#getFederatedProcedure <em>Federated Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedParameterImpl#getRemoteParameter <em>Remote Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FederatedParameterImpl extends ParameterImpl implements FederatedParameter {
	/**
	 * The default value of the '{@link #getRemoteCodePage() <em>Remote Code Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteCodePage()
	 * @generated
	 * @ordered
	 */
	protected static final int REMOTE_CODE_PAGE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRemoteCodePage() <em>Remote Code Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteCodePage()
	 * @generated
	 * @ordered
	 */
	protected int remoteCodePage = REMOTE_CODE_PAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemoteParamTypeID() <em>Remote Param Type ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteParamTypeID()
	 * @generated
	 * @ordered
	 */
	protected static final int REMOTE_PARAM_TYPE_ID_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRemoteParamTypeID() <em>Remote Param Type ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteParamTypeID()
	 * @generated
	 * @ordered
	 */
	protected int remoteParamTypeID = REMOTE_PARAM_TYPE_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFederatedProcedure() <em>Federated Procedure</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFederatedProcedure()
	 * @generated
	 * @ordered
	 */
	protected FederatedProcedure federatedProcedure;

	/**
	 * The cached value of the '{@link #getRemoteParameter() <em>Remote Parameter</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteParameter()
	 * @generated
	 * @ordered
	 */
	protected EList remoteParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FederatedParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.FEDERATED_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRemoteCodePage() {
		return remoteCodePage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteCodePage(int newRemoteCodePage) {
		int oldRemoteCodePage = remoteCodePage;
		remoteCodePage = newRemoteCodePage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PARAMETER__REMOTE_CODE_PAGE, oldRemoteCodePage, remoteCodePage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRemoteParamTypeID() {
		return remoteParamTypeID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteParamTypeID(int newRemoteParamTypeID) {
		int oldRemoteParamTypeID = remoteParamTypeID;
		remoteParamTypeID = newRemoteParamTypeID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAM_TYPE_ID, oldRemoteParamTypeID, remoteParamTypeID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FederatedProcedure getFederatedProcedure() {
		if (federatedProcedure != null && federatedProcedure.eIsProxy()) {
			InternalEObject oldFederatedProcedure = (InternalEObject)federatedProcedure;
			federatedProcedure = (FederatedProcedure)eResolveProxy(oldFederatedProcedure);
			if (federatedProcedure != oldFederatedProcedure) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE, oldFederatedProcedure, federatedProcedure));
			}
		}
		return federatedProcedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FederatedProcedure basicGetFederatedProcedure() {
		return federatedProcedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFederatedProcedure(FederatedProcedure newFederatedProcedure, NotificationChain msgs) {
		FederatedProcedure oldFederatedProcedure = federatedProcedure;
		federatedProcedure = newFederatedProcedure;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE, oldFederatedProcedure, newFederatedProcedure);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFederatedProcedure(FederatedProcedure newFederatedProcedure) {
		if (newFederatedProcedure != federatedProcedure) {
			NotificationChain msgs = null;
			if (federatedProcedure != null)
				msgs = ((InternalEObject)federatedProcedure).eInverseRemove(this, LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER, FederatedProcedure.class, msgs);
			if (newFederatedProcedure != null)
				msgs = ((InternalEObject)newFederatedProcedure).eInverseAdd(this, LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER, FederatedProcedure.class, msgs);
			msgs = basicSetFederatedProcedure(newFederatedProcedure, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE, newFederatedProcedure, newFederatedProcedure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRemoteParameter() {
		if (remoteParameter == null) {
			remoteParameter = new EObjectResolvingEList(Parameter.class, this, LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAMETER);
		}
		return remoteParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE:
				if (federatedProcedure != null)
					msgs = ((InternalEObject)federatedProcedure).eInverseRemove(this, LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER, FederatedProcedure.class, msgs);
				return basicSetFederatedProcedure((FederatedProcedure)otherEnd, msgs);
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
			case LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE:
				return basicSetFederatedProcedure(null, msgs);
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
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_CODE_PAGE:
				return new Integer(getRemoteCodePage());
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAM_TYPE_ID:
				return new Integer(getRemoteParamTypeID());
			case LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE:
				if (resolve) return getFederatedProcedure();
				return basicGetFederatedProcedure();
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAMETER:
				return getRemoteParameter();
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
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_CODE_PAGE:
				setRemoteCodePage(((Integer)newValue).intValue());
				return;
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAM_TYPE_ID:
				setRemoteParamTypeID(((Integer)newValue).intValue());
				return;
			case LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE:
				setFederatedProcedure((FederatedProcedure)newValue);
				return;
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAMETER:
				getRemoteParameter().clear();
				getRemoteParameter().addAll((Collection)newValue);
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
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_CODE_PAGE:
				setRemoteCodePage(REMOTE_CODE_PAGE_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAM_TYPE_ID:
				setRemoteParamTypeID(REMOTE_PARAM_TYPE_ID_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE:
				setFederatedProcedure((FederatedProcedure)null);
				return;
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAMETER:
				getRemoteParameter().clear();
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
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_CODE_PAGE:
				return remoteCodePage != REMOTE_CODE_PAGE_EDEFAULT;
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAM_TYPE_ID:
				return remoteParamTypeID != REMOTE_PARAM_TYPE_ID_EDEFAULT;
			case LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE:
				return federatedProcedure != null;
			case LUWPackage.FEDERATED_PARAMETER__REMOTE_PARAMETER:
				return remoteParameter != null && !remoteParameter.isEmpty();
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
		result.append(" (remoteCodePage: "); //$NON-NLS-1$
		result.append(remoteCodePage);
		result.append(", remoteParamTypeID: "); //$NON-NLS-1$
		result.append(remoteParamTypeID);
		result.append(')');
		return result.toString();
	}

} //FederatedParameterImpl
