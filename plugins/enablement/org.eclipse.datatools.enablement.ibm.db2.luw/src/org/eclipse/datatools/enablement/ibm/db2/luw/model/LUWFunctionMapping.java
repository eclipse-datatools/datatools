/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import java.util.Date;

import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Function;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerType <em>Server Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerVersion <em>Server Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerName <em>Server Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getCreationTime <em>Creation Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getInstsPerInvoc <em>Insts Per Invoc</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getInstsPerArgByte <em>Insts Per Arg Byte</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getIosPerInvoc <em>Ios Per Invoc</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getIosPerArgByte <em>Ios Per Arg Byte</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLocalFunction <em>Local Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getRemoteFunction <em>Remote Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLUWDatabase <em>LUW Database</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping()
 * @model
 * @generated
 */
public interface LUWFunctionMapping extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Server Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Type</em>' attribute.
	 * @see #setServerType(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_ServerType()
	 * @model
	 * @generated
	 */
	String getServerType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerType <em>Server Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Type</em>' attribute.
	 * @see #getServerType()
	 * @generated
	 */
	void setServerType(String value);

	/**
	 * Returns the value of the '<em><b>Server Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Version</em>' attribute.
	 * @see #setServerVersion(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_ServerVersion()
	 * @model
	 * @generated
	 */
	String getServerVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerVersion <em>Server Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Version</em>' attribute.
	 * @see #getServerVersion()
	 * @generated
	 */
	void setServerVersion(String value);

	/**
	 * Returns the value of the '<em><b>Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Server Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Server Name</em>' attribute.
	 * @see #setServerName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_ServerName()
	 * @model
	 * @generated
	 */
	String getServerName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerName <em>Server Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Server Name</em>' attribute.
	 * @see #getServerName()
	 * @generated
	 */
	void setServerName(String value);

	/**
	 * Returns the value of the '<em><b>Creation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Creation Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Creation Time</em>' attribute.
	 * @see #setCreationTime(Date)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_CreationTime()
	 * @model dataType="org.eclipse.datatools.modelbase.sql.schema.Date"
	 * @generated
	 */
	Date getCreationTime();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getCreationTime <em>Creation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Time</em>' attribute.
	 * @see #getCreationTime()
	 * @generated
	 */
	void setCreationTime(Date value);

	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Disabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disabled</em>' attribute.
	 * @see #setDisabled(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_Disabled()
	 * @model default="false"
	 * @generated
	 */
	boolean isDisabled();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#isDisabled <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disabled</em>' attribute.
	 * @see #isDisabled()
	 * @generated
	 */
	void setDisabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Insts Per Invoc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Insts Per Invoc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Insts Per Invoc</em>' attribute.
	 * @see #setInstsPerInvoc(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_InstsPerInvoc()
	 * @model
	 * @generated
	 */
	int getInstsPerInvoc();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getInstsPerInvoc <em>Insts Per Invoc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Insts Per Invoc</em>' attribute.
	 * @see #getInstsPerInvoc()
	 * @generated
	 */
	void setInstsPerInvoc(int value);

	/**
	 * Returns the value of the '<em><b>Insts Per Arg Byte</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Insts Per Arg Byte</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Insts Per Arg Byte</em>' attribute.
	 * @see #setInstsPerArgByte(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_InstsPerArgByte()
	 * @model
	 * @generated
	 */
	int getInstsPerArgByte();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getInstsPerArgByte <em>Insts Per Arg Byte</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Insts Per Arg Byte</em>' attribute.
	 * @see #getInstsPerArgByte()
	 * @generated
	 */
	void setInstsPerArgByte(int value);

	/**
	 * Returns the value of the '<em><b>Ios Per Invoc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ios Per Invoc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ios Per Invoc</em>' attribute.
	 * @see #setIosPerInvoc(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_IosPerInvoc()
	 * @model
	 * @generated
	 */
	int getIosPerInvoc();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getIosPerInvoc <em>Ios Per Invoc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ios Per Invoc</em>' attribute.
	 * @see #getIosPerInvoc()
	 * @generated
	 */
	void setIosPerInvoc(int value);

	/**
	 * Returns the value of the '<em><b>Ios Per Arg Byte</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ios Per Arg Byte</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ios Per Arg Byte</em>' attribute.
	 * @see #setIosPerArgByte(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_IosPerArgByte()
	 * @model
	 * @generated
	 */
	int getIosPerArgByte();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getIosPerArgByte <em>Ios Per Arg Byte</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ios Per Arg Byte</em>' attribute.
	 * @see #getIosPerArgByte()
	 * @generated
	 */
	void setIosPerArgByte(int value);

	/**
	 * Returns the value of the '<em><b>Options</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Options</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Options</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_Options()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption" containment="true"
	 * @generated
	 */
	EList getOptions();

	/**
	 * Returns the value of the '<em><b>Local Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local Function</em>' reference.
	 * @see #setLocalFunction(DB2Function)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_LocalFunction()
	 * @model required="true"
	 * @generated
	 */
	DB2Function getLocalFunction();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLocalFunction <em>Local Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local Function</em>' reference.
	 * @see #getLocalFunction()
	 * @generated
	 */
	void setLocalFunction(DB2Function value);

	/**
	 * Returns the value of the '<em><b>Remote Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Function</em>' reference.
	 * @see #setRemoteFunction(Function)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_RemoteFunction()
	 * @model required="true"
	 * @generated
	 */
	Function getRemoteFunction();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getRemoteFunction <em>Remote Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Function</em>' reference.
	 * @see #getRemoteFunction()
	 * @generated
	 */
	void setRemoteFunction(Function value);

	/**
	 * Returns the value of the '<em><b>LUW Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getFunctionMappings <em>Function Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LUW Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LUW Database</em>' reference.
	 * @see #setLUWDatabase(LUWDatabase)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWFunctionMapping_LUWDatabase()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getFunctionMappings
	 * @model opposite="functionMappings" required="true"
	 * @generated
	 */
	LUWDatabase getLUWDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLUWDatabase <em>LUW Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LUW Database</em>' reference.
	 * @see #getLUWDatabase()
	 * @generated
	 */
	void setLUWDatabase(LUWDatabase value);

} // LUWFunctionMapping
