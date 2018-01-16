/**
 * <copyright>
 * </copyright>
 *
 * $Id: FederatedParameter.java,v 1.3 2007/10/12 23:05:35 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Federated Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Federated Parameter object capturs the remote procedure atributes that are stored as options in the DB2 Catalog. Those that could not be inherited through the Parameter object, have been modeled specifically. These attributes are
 * remoteCodePage and remoteParamTypeID.
 * The attributes & where they can be found from the Parameter object are below. 
 * RemoteParamName - Remote parameter name, from name feild of any named object
 * RemoteTypeSchema - found  through getDatatype, inherited from Parameter
 * Remote_Param_Type_Name  - found  through getDatatype, inherited from Parameter
 * Remote_Remote_Param_Scale -  found  through getDatatype, inherited from Parameter
 * Remote_Remote_Param_Length - found  through getDatatype, inherited from Parameter
 * Remote_Is_Return_Value - Found in ParameterMode enumeration, inherited from Parameter
 * 
 * 
 * Remote_Codepage
 * Remote_Param_Type_ID - the remote parameter type as an integer, attribute of Federated Paramter paramtypeid
 * 
 *  
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteCodePage <em>Remote Code Page</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteParamTypeID <em>Remote Param Type ID</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getFederatedProcedure <em>Federated Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteParameter <em>Remote Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedParameter()
 * @model
 * @generated
 */
public interface FederatedParameter extends Parameter {
	/**
	 * Returns the value of the '<em><b>Remote Code Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Code Page</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Code Page</em>' attribute.
	 * @see #setRemoteCodePage(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedParameter_RemoteCodePage()
	 * @model
	 * @generated
	 */
	int getRemoteCodePage();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteCodePage <em>Remote Code Page</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Code Page</em>' attribute.
	 * @see #getRemoteCodePage()
	 * @generated
	 */
	void setRemoteCodePage(int value);

	/**
	 * Returns the value of the '<em><b>Remote Param Type ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Param Type ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Param Type ID</em>' attribute.
	 * @see #setRemoteParamTypeID(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedParameter_RemoteParamTypeID()
	 * @model
	 * @generated
	 */
	int getRemoteParamTypeID();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteParamTypeID <em>Remote Param Type ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Param Type ID</em>' attribute.
	 * @see #getRemoteParamTypeID()
	 * @generated
	 */
	void setRemoteParamTypeID(int value);

	/**
	 * Returns the value of the '<em><b>Federated Procedure</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getFederatedParameter <em>Federated Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Federated Procedure</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Federated Procedure</em>' reference.
	 * @see #setFederatedProcedure(FederatedProcedure)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedParameter_FederatedProcedure()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getFederatedParameter
	 * @model opposite="federatedParameter" required="true"
	 * @generated
	 */
	FederatedProcedure getFederatedProcedure();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getFederatedProcedure <em>Federated Procedure</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Federated Procedure</em>' reference.
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	void setFederatedProcedure(FederatedProcedure value);

	/**
	 * Returns the value of the '<em><b>Remote Parameter</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.routines.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Parameter</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Parameter</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getFederatedParameter_RemoteParameter()
	 * @model type="org.eclipse.datatools.modelbase.sql.routines.Parameter"
	 * @generated
	 */
	EList getRemoteParameter();

} // FederatedParameter
