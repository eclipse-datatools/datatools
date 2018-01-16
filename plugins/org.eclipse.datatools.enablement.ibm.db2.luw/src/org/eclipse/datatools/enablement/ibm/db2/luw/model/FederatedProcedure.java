/**
 * <copyright>
 * </copyright>
 *
 * $Id: FederatedProcedure.java,v 1.3 2007/10/12 23:05:35 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Federated Procedure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteUniqueId <em>Remote Unique Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteServer <em>Remote Server</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteSchema <em>Remote Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemotePackage <em>Remote Package</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteProcedureName <em>Remote Procedure Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getNumberOfParameters <em>Number Of Parameters</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getResultSetsToClient <em>Result Sets To Client</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getNumberOfRefCursors <em>Number Of Ref Cursors</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#isAllResultSetsToCaller <em>All Result Sets To Caller</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getFederatedProcedure <em>Federated Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteProcedure <em>Remote Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getFederatedParameter <em>Federated Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure()
 * @model
 * @generated
 */
public interface FederatedProcedure extends DB2Procedure {
	/**
	 * Returns the value of the '<em><b>Remote Unique Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Unique Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Unique Id</em>' attribute.
	 * @see #setRemoteUniqueId(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_RemoteUniqueId()
	 * @model
	 * @generated
	 */
	String getRemoteUniqueId();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteUniqueId <em>Remote Unique Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Unique Id</em>' attribute.
	 * @see #getRemoteUniqueId()
	 * @generated
	 */
	void setRemoteUniqueId(String value);

	/**
	 * Returns the value of the '<em><b>Remote Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Server</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Server</em>' attribute.
	 * @see #setRemoteServer(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_RemoteServer()
	 * @model
	 * @generated
	 */
	String getRemoteServer();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteServer <em>Remote Server</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Server</em>' attribute.
	 * @see #getRemoteServer()
	 * @generated
	 */
	void setRemoteServer(String value);

	/**
	 * Returns the value of the '<em><b>Remote Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Schema</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Schema</em>' attribute.
	 * @see #setRemoteSchema(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_RemoteSchema()
	 * @model
	 * @generated
	 */
	String getRemoteSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteSchema <em>Remote Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Schema</em>' attribute.
	 * @see #getRemoteSchema()
	 * @generated
	 */
	void setRemoteSchema(String value);

	/**
	 * Returns the value of the '<em><b>Remote Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Package</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Package</em>' attribute.
	 * @see #setRemotePackage(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_RemotePackage()
	 * @model
	 * @generated
	 */
	String getRemotePackage();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemotePackage <em>Remote Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Package</em>' attribute.
	 * @see #getRemotePackage()
	 * @generated
	 */
	void setRemotePackage(String value);

	/**
	 * Returns the value of the '<em><b>Remote Procedure Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Procedure Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Procedure Name</em>' attribute.
	 * @see #setRemoteProcedureName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_RemoteProcedureName()
	 * @model
	 * @generated
	 */
	String getRemoteProcedureName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteProcedureName <em>Remote Procedure Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Procedure Name</em>' attribute.
	 * @see #getRemoteProcedureName()
	 * @generated
	 */
	void setRemoteProcedureName(String value);

	/**
	 * Returns the value of the '<em><b>Number Of Parameters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Parameters</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Parameters</em>' attribute.
	 * @see #setNumberOfParameters(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_NumberOfParameters()
	 * @model
	 * @generated
	 */
	int getNumberOfParameters();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getNumberOfParameters <em>Number Of Parameters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Parameters</em>' attribute.
	 * @see #getNumberOfParameters()
	 * @generated
	 */
	void setNumberOfParameters(int value);

	/**
	 * Returns the value of the '<em><b>Result Sets To Client</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Sets To Client</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Sets To Client</em>' attribute.
	 * @see #setResultSetsToClient(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_ResultSetsToClient()
	 * @model
	 * @generated
	 */
	String getResultSetsToClient();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getResultSetsToClient <em>Result Sets To Client</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Sets To Client</em>' attribute.
	 * @see #getResultSetsToClient()
	 * @generated
	 */
	void setResultSetsToClient(String value);

	/**
	 * Returns the value of the '<em><b>Number Of Ref Cursors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Of Ref Cursors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Ref Cursors</em>' attribute.
	 * @see #setNumberOfRefCursors(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_NumberOfRefCursors()
	 * @model
	 * @generated
	 */
	int getNumberOfRefCursors();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getNumberOfRefCursors <em>Number Of Ref Cursors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Ref Cursors</em>' attribute.
	 * @see #getNumberOfRefCursors()
	 * @generated
	 */
	void setNumberOfRefCursors(int value);

	/**
	 * Returns the value of the '<em><b>All Result Sets To Caller</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Result Sets To Caller</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Result Sets To Caller</em>' attribute.
	 * @see #setAllResultSetsToCaller(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_AllResultSetsToCaller()
	 * @model
	 * @generated
	 */
	boolean isAllResultSetsToCaller();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#isAllResultSetsToCaller <em>All Result Sets To Caller</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Result Sets To Caller</em>' attribute.
	 * @see #isAllResultSetsToCaller()
	 * @generated
	 */
	void setAllResultSetsToCaller(boolean value);

	/**
	 * Returns the value of the '<em><b>Federated Procedure</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Federated Procedure</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Federated Procedure</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_FederatedProcedure()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure"
	 * @generated
	 */
	EList getFederatedProcedure();

	/**
	 * Returns the value of the '<em><b>Remote Procedure</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.routines.Procedure}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Procedure</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Procedure</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_RemoteProcedure()
	 * @model type="org.eclipse.datatools.modelbase.sql.routines.Procedure"
	 * @generated
	 */
	EList getRemoteProcedure();

	/**
	 * Returns the value of the '<em><b>Federated Parameter</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getFederatedProcedure <em>Federated Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Federated Parameter</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Federated Parameter</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedProcedure_FederatedParameter()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getFederatedProcedure
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter" opposite="federatedProcedure"
	 * @generated
	 */
	EList getFederatedParameter();

} // FederatedProcedure
