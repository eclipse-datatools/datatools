package org.eclipse.datatools.enablement.ibm.db2.luw.model;


import org.eclipse.datatools.enablement.ibm.db2.model.DB2Member;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Member</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getAlert <em>Alert</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getDbPartitionNum <em>Db Partition Num</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getLogicalPort <em>Logical Port</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getNetName <em>Net Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWMember()
 * @model
 * @generated
 */
public interface LUWMember extends DB2Member {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType
	 * @see #setType(LUWMemberType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWMember_Type()
	 * @model
	 * @generated
	 */
	LUWMemberType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType
	 * @see #getType()
	 * @generated
	 */
	void setType(LUWMemberType value);

	/**
	 * Returns the value of the '<em><b>Alert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alert</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alert</em>' attribute.
	 * @see #setAlert(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWMember_Alert()
	 * @model
	 * @generated
	 */
	String getAlert();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getAlert <em>Alert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alert</em>' attribute.
	 * @see #getAlert()
	 * @generated
	 */
	void setAlert(String value);

	/**
	 * Returns the value of the '<em><b>Db Partition Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Db Partition Num</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Db Partition Num</em>' attribute.
	 * @see #setDbPartitionNum(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWMember_DbPartitionNum()
	 * @model
	 * @generated
	 */
	int getDbPartitionNum();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getDbPartitionNum <em>Db Partition Num</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Db Partition Num</em>' attribute.
	 * @see #getDbPartitionNum()
	 * @generated
	 */
	void setDbPartitionNum(int value);

	/**
	 * Returns the value of the '<em><b>Logical Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Port</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Port</em>' attribute.
	 * @see #setLogicalPort(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWMember_LogicalPort()
	 * @model
	 * @generated
	 */
	int getLogicalPort();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getLogicalPort <em>Logical Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Logical Port</em>' attribute.
	 * @see #getLogicalPort()
	 * @generated
	 */
	void setLogicalPort(int value);

	/**
	 * Returns the value of the '<em><b>Net Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Net Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Net Name</em>' attribute.
	 * @see #setNetName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWMember_NetName()
	 * @model
	 * @generated
	 */
	String getNetName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getNetName <em>Net Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Net Name</em>' attribute.
	 * @see #getNetName()
	 * @generated
	 */
	void setNetName(String value);

} // LUWMember
