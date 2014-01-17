/**
 * <copyright>
 * </copyright>
 *
 * $Id: FederatedProcedureImpl.java,v 1.6 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Federated Procedure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getRemoteUniqueId <em>Remote Unique Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getRemoteServer <em>Remote Server</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getRemoteSchema <em>Remote Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getRemotePackage <em>Remote Package</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getRemoteProcedureName <em>Remote Procedure Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getNumberOfParameters <em>Number Of Parameters</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getResultSetsToClient <em>Result Sets To Client</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getNumberOfRefCursors <em>Number Of Ref Cursors</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#isAllResultSetsToCaller <em>All Result Sets To Caller</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getFederatedProcedure <em>Federated Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getRemoteProcedure <em>Remote Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl#getFederatedParameter <em>Federated Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FederatedProcedureImpl extends DB2ProcedureImpl implements FederatedProcedure {
	/**
	 * The default value of the '{@link #getRemoteUniqueId() <em>Remote Unique Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteUniqueId()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_UNIQUE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemoteUniqueId() <em>Remote Unique Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteUniqueId()
	 * @generated
	 * @ordered
	 */
	protected String remoteUniqueId = REMOTE_UNIQUE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemoteServer() <em>Remote Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteServer()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_SERVER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemoteServer() <em>Remote Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteServer()
	 * @generated
	 * @ordered
	 */
	protected String remoteServer = REMOTE_SERVER_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemoteSchema() <em>Remote Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteSchema()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_SCHEMA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemoteSchema() <em>Remote Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteSchema()
	 * @generated
	 * @ordered
	 */
	protected String remoteSchema = REMOTE_SCHEMA_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemotePackage() <em>Remote Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemotePackage()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_PACKAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemotePackage() <em>Remote Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemotePackage()
	 * @generated
	 * @ordered
	 */
	protected String remotePackage = REMOTE_PACKAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRemoteProcedureName() <em>Remote Procedure Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteProcedureName()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_PROCEDURE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRemoteProcedureName() <em>Remote Procedure Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteProcedureName()
	 * @generated
	 * @ordered
	 */
	protected String remoteProcedureName = REMOTE_PROCEDURE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumberOfParameters() <em>Number Of Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfParameters()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_OF_PARAMETERS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumberOfParameters() <em>Number Of Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfParameters()
	 * @generated
	 * @ordered
	 */
	protected int numberOfParameters = NUMBER_OF_PARAMETERS_EDEFAULT;

	/**
	 * The default value of the '{@link #getResultSetsToClient() <em>Result Sets To Client</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultSetsToClient()
	 * @generated
	 * @ordered
	 */
	protected static final String RESULT_SETS_TO_CLIENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResultSetsToClient() <em>Result Sets To Client</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultSetsToClient()
	 * @generated
	 * @ordered
	 */
	protected String resultSetsToClient = RESULT_SETS_TO_CLIENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumberOfRefCursors() <em>Number Of Ref Cursors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfRefCursors()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_OF_REF_CURSORS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumberOfRefCursors() <em>Number Of Ref Cursors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfRefCursors()
	 * @generated
	 * @ordered
	 */
	protected int numberOfRefCursors = NUMBER_OF_REF_CURSORS_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllResultSetsToCaller() <em>All Result Sets To Caller</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllResultSetsToCaller()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALL_RESULT_SETS_TO_CALLER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllResultSetsToCaller() <em>All Result Sets To Caller</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllResultSetsToCaller()
	 * @generated
	 * @ordered
	 */
	protected boolean allResultSetsToCaller = ALL_RESULT_SETS_TO_CALLER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFederatedProcedure() <em>Federated Procedure</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFederatedProcedure()
	 * @generated
	 * @ordered
	 */
	protected EList federatedProcedure;

	/**
	 * The cached value of the '{@link #getRemoteProcedure() <em>Remote Procedure</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemoteProcedure()
	 * @generated
	 * @ordered
	 */
	protected EList remoteProcedure;

	/**
	 * The cached value of the '{@link #getFederatedParameter() <em>Federated Parameter</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFederatedParameter()
	 * @generated
	 * @ordered
	 */
	protected EList federatedParameter;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FederatedProcedureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.FEDERATED_PROCEDURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemoteUniqueId() {
		return remoteUniqueId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteUniqueId(String newRemoteUniqueId) {
		String oldRemoteUniqueId = remoteUniqueId;
		remoteUniqueId = newRemoteUniqueId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PROCEDURE__REMOTE_UNIQUE_ID, oldRemoteUniqueId, remoteUniqueId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemoteServer() {
		return remoteServer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteServer(String newRemoteServer) {
		String oldRemoteServer = remoteServer;
		remoteServer = newRemoteServer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PROCEDURE__REMOTE_SERVER, oldRemoteServer, remoteServer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemoteSchema() {
		return remoteSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteSchema(String newRemoteSchema) {
		String oldRemoteSchema = remoteSchema;
		remoteSchema = newRemoteSchema;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PROCEDURE__REMOTE_SCHEMA, oldRemoteSchema, remoteSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemotePackage() {
		return remotePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemotePackage(String newRemotePackage) {
		String oldRemotePackage = remotePackage;
		remotePackage = newRemotePackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PROCEDURE__REMOTE_PACKAGE, oldRemotePackage, remotePackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRemoteProcedureName() {
		return remoteProcedureName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRemoteProcedureName(String newRemoteProcedureName) {
		String oldRemoteProcedureName = remoteProcedureName;
		remoteProcedureName = newRemoteProcedureName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE_NAME, oldRemoteProcedureName, remoteProcedureName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumberOfParameters() {
		return numberOfParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfParameters(int newNumberOfParameters) {
		int oldNumberOfParameters = numberOfParameters;
		numberOfParameters = newNumberOfParameters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_PARAMETERS, oldNumberOfParameters, numberOfParameters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResultSetsToClient() {
		return resultSetsToClient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultSetsToClient(String newResultSetsToClient) {
		String oldResultSetsToClient = resultSetsToClient;
		resultSetsToClient = newResultSetsToClient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PROCEDURE__RESULT_SETS_TO_CLIENT, oldResultSetsToClient, resultSetsToClient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumberOfRefCursors() {
		return numberOfRefCursors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfRefCursors(int newNumberOfRefCursors) {
		int oldNumberOfRefCursors = numberOfRefCursors;
		numberOfRefCursors = newNumberOfRefCursors;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_REF_CURSORS, oldNumberOfRefCursors, numberOfRefCursors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAllResultSetsToCaller() {
		return allResultSetsToCaller;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllResultSetsToCaller(boolean newAllResultSetsToCaller) {
		boolean oldAllResultSetsToCaller = allResultSetsToCaller;
		allResultSetsToCaller = newAllResultSetsToCaller;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.FEDERATED_PROCEDURE__ALL_RESULT_SETS_TO_CALLER, oldAllResultSetsToCaller, allResultSetsToCaller));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFederatedProcedure() {
		if (federatedProcedure == null) {
			federatedProcedure = new EObjectResolvingEList(FederatedProcedure.class, this, LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PROCEDURE);
		}
		return federatedProcedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRemoteProcedure() {
		if (remoteProcedure == null) {
			remoteProcedure = new EObjectResolvingEList(Procedure.class, this, LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE);
		}
		return remoteProcedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFederatedParameter() {
		if (federatedParameter == null) {
			federatedParameter = new EObjectWithInverseResolvingEList(FederatedParameter.class, this, LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER, LUWPackage.FEDERATED_PARAMETER__FEDERATED_PROCEDURE);
		}
		return federatedParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER:
				return ((InternalEList)getFederatedParameter()).basicAdd(otherEnd, msgs);
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
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER:
				return ((InternalEList)getFederatedParameter()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_UNIQUE_ID:
				return getRemoteUniqueId();
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_SERVER:
				return getRemoteServer();
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_SCHEMA:
				return getRemoteSchema();
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PACKAGE:
				return getRemotePackage();
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE_NAME:
				return getRemoteProcedureName();
			case LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_PARAMETERS:
				return new Integer(getNumberOfParameters());
			case LUWPackage.FEDERATED_PROCEDURE__RESULT_SETS_TO_CLIENT:
				return getResultSetsToClient();
			case LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_REF_CURSORS:
				return new Integer(getNumberOfRefCursors());
			case LUWPackage.FEDERATED_PROCEDURE__ALL_RESULT_SETS_TO_CALLER:
				return isAllResultSetsToCaller() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PROCEDURE:
				return getFederatedProcedure();
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE:
				return getRemoteProcedure();
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER:
				return getFederatedParameter();
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
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_UNIQUE_ID:
				setRemoteUniqueId((String)newValue);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_SERVER:
				setRemoteServer((String)newValue);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_SCHEMA:
				setRemoteSchema((String)newValue);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PACKAGE:
				setRemotePackage((String)newValue);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE_NAME:
				setRemoteProcedureName((String)newValue);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_PARAMETERS:
				setNumberOfParameters(((Integer)newValue).intValue());
				return;
			case LUWPackage.FEDERATED_PROCEDURE__RESULT_SETS_TO_CLIENT:
				setResultSetsToClient((String)newValue);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_REF_CURSORS:
				setNumberOfRefCursors(((Integer)newValue).intValue());
				return;
			case LUWPackage.FEDERATED_PROCEDURE__ALL_RESULT_SETS_TO_CALLER:
				setAllResultSetsToCaller(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PROCEDURE:
				getFederatedProcedure().clear();
				getFederatedProcedure().addAll((Collection)newValue);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE:
				getRemoteProcedure().clear();
				getRemoteProcedure().addAll((Collection)newValue);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER:
				getFederatedParameter().clear();
				getFederatedParameter().addAll((Collection)newValue);
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
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_UNIQUE_ID:
				setRemoteUniqueId(REMOTE_UNIQUE_ID_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_SERVER:
				setRemoteServer(REMOTE_SERVER_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_SCHEMA:
				setRemoteSchema(REMOTE_SCHEMA_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PACKAGE:
				setRemotePackage(REMOTE_PACKAGE_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE_NAME:
				setRemoteProcedureName(REMOTE_PROCEDURE_NAME_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_PARAMETERS:
				setNumberOfParameters(NUMBER_OF_PARAMETERS_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__RESULT_SETS_TO_CLIENT:
				setResultSetsToClient(RESULT_SETS_TO_CLIENT_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_REF_CURSORS:
				setNumberOfRefCursors(NUMBER_OF_REF_CURSORS_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__ALL_RESULT_SETS_TO_CALLER:
				setAllResultSetsToCaller(ALL_RESULT_SETS_TO_CALLER_EDEFAULT);
				return;
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PROCEDURE:
				getFederatedProcedure().clear();
				return;
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE:
				getRemoteProcedure().clear();
				return;
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER:
				getFederatedParameter().clear();
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
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_UNIQUE_ID:
				return REMOTE_UNIQUE_ID_EDEFAULT == null ? remoteUniqueId != null : !REMOTE_UNIQUE_ID_EDEFAULT.equals(remoteUniqueId);
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_SERVER:
				return REMOTE_SERVER_EDEFAULT == null ? remoteServer != null : !REMOTE_SERVER_EDEFAULT.equals(remoteServer);
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_SCHEMA:
				return REMOTE_SCHEMA_EDEFAULT == null ? remoteSchema != null : !REMOTE_SCHEMA_EDEFAULT.equals(remoteSchema);
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PACKAGE:
				return REMOTE_PACKAGE_EDEFAULT == null ? remotePackage != null : !REMOTE_PACKAGE_EDEFAULT.equals(remotePackage);
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE_NAME:
				return REMOTE_PROCEDURE_NAME_EDEFAULT == null ? remoteProcedureName != null : !REMOTE_PROCEDURE_NAME_EDEFAULT.equals(remoteProcedureName);
			case LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_PARAMETERS:
				return numberOfParameters != NUMBER_OF_PARAMETERS_EDEFAULT;
			case LUWPackage.FEDERATED_PROCEDURE__RESULT_SETS_TO_CLIENT:
				return RESULT_SETS_TO_CLIENT_EDEFAULT == null ? resultSetsToClient != null : !RESULT_SETS_TO_CLIENT_EDEFAULT.equals(resultSetsToClient);
			case LUWPackage.FEDERATED_PROCEDURE__NUMBER_OF_REF_CURSORS:
				return numberOfRefCursors != NUMBER_OF_REF_CURSORS_EDEFAULT;
			case LUWPackage.FEDERATED_PROCEDURE__ALL_RESULT_SETS_TO_CALLER:
				return allResultSetsToCaller != ALL_RESULT_SETS_TO_CALLER_EDEFAULT;
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PROCEDURE:
				return federatedProcedure != null && !federatedProcedure.isEmpty();
			case LUWPackage.FEDERATED_PROCEDURE__REMOTE_PROCEDURE:
				return remoteProcedure != null && !remoteProcedure.isEmpty();
			case LUWPackage.FEDERATED_PROCEDURE__FEDERATED_PARAMETER:
				return federatedParameter != null && !federatedParameter.isEmpty();
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
		result.append(" (remoteUniqueId: "); //$NON-NLS-1$
		result.append(remoteUniqueId);
		result.append(", remoteServer: "); //$NON-NLS-1$
		result.append(remoteServer);
		result.append(", remoteSchema: "); //$NON-NLS-1$
		result.append(remoteSchema);
		result.append(", remotePackage: "); //$NON-NLS-1$
		result.append(remotePackage);
		result.append(", remoteProcedureName: "); //$NON-NLS-1$
		result.append(remoteProcedureName);
		result.append(", numberOfParameters: "); //$NON-NLS-1$
		result.append(numberOfParameters);
		result.append(", resultSetsToClient: "); //$NON-NLS-1$
		result.append(resultSetsToClient);
		result.append(", numberOfRefCursors: "); //$NON-NLS-1$
		result.append(numberOfRefCursors);
		result.append(", allResultSetsToCaller: "); //$NON-NLS-1$
		result.append(allResultSetsToCaller);
		result.append(')');
		return result.toString();
	}

} //FederatedProcedureImpl
