/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;


import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Procedure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Procedures (Chapter 1. Concepts 9)
 * 
 * A procedure (sometimes called a stored procedure) is a routine that can be called to perform operations that can include both host language statements and SQL statements.
 * 
 * Procedures are classified as either SQL procedures or external procedures. SQL procedures contain only SQL statements. External procedures reference a host language program which may or may not contain SQL statements. A procedure is created with the CREATE PROCEDURE statement. For more information about creating procedures, see "CREATE PROCEDURE" on page 366.
 * 
 * Procedures in SQL provide the same benefits as procedures in a host language. That is, a common piece of code need only be written and maintained once and can be called from several programs. Host languages can easily call procedures that exist on the local system. SQL can also easily call a procedure that exists on a remote system. In fact, the major benefit of procedures in SQL is that they can be used to enhance the performance characteristics of distributed applications.
 * 
 * Assume several SQL statements must be executed at a remote system. There are two ways this can be done. Without procedures, when the first SQL statement is executed, the application requester will send a request to an application server to perform the operation. It then waits for a reply that indicates whether the statement executed successfully or not and optionally returns results. When the second and each subsequent SQL statement is executed, the application requester will send another request and wait for another reply. If the same SQL statements are stored in a procedure at an application server, a CALL statement can be executed that references the remote procedure. When the CALL statement is executed, the application requester will send a single request to the current server to call the procedure. It then waits for a single reply that indicates whether the CALL statement executed successfully or not and optionally returns results.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isModelResultSets <em>Model Result Sets</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isNullInput <em>Null Input</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getDialect <em>Dialect</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isExternalAction <em>External Action</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getReturn <em>Return</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getJavaOptions <em>Java Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getDeploy <em>Deploy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Procedure()
 * @model
 * @generated
 */
public interface DB2Procedure extends Procedure, DB2Routine {
	/**
	 * Returns the value of the '<em><b>Model Result Sets</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <code>True</code> means that the stored procedure should have its result sets modeled. 
	 * <code>False</code> means that even if the stored procedure returned result sets, they are not modeled. 
	 * Most users will not be wanting to model the stored procedure return result sets metadata as this takes 
	 * considerable time, and the developer may not want to even run the stored procedure (yes, even though 
	 * no data is returned, it needs to be done to learn the metadata) at the time of the build. 
	 * 
	 * This was added so that the stored procedure could be completely described in a DADX document. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Result Sets</em>' attribute.
	 * @see #setModelResultSets(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Procedure_ModelResultSets()
	 * @model default="false"
	 * @generated
	 */
	boolean isModelResultSets();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isModelResultSets <em>Model Result Sets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Result Sets</em>' attribute.
	 * @see #isModelResultSets()
	 * @generated
	 */
	void setModelResultSets(boolean value);

	/**
	 * Returns the value of the '<em><b>Null Input</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * CALLED ON NULL INPUT 
	 * <p> 
	 * CALLED ON NULL INPUT always applies to stored procedures. This 
	 * means that regardless if any arguments are null, the stored procedure is 
	 * called. It can return a null value or a normal (non-null) value. Responsibility 
	 * for testing for null argument values lies with the stored procedure. 
	 * <p> 
	 * The value NULL CALL may be used as a synonym for CALLED ON 
	 * NULL INPUT for backwards and family compatibility.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Null Input</em>' attribute.
	 * @see #setNullInput(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Procedure_NullInput()
	 * @model
	 * @generated
	 */
   boolean isNullInput();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isNullInput <em>Null Input</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Null Input</em>' attribute.
	 * @see #isNullInput()
	 * @generated
	 */
   void setNullInput(boolean value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Initially for DB2 os390 v9 where SQL stored procedures can be versioned (New 
	 * Function mode).  LUW could follow suit, though it is not in plan for v9.1
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Procedure_Version()
	 * @model
	 * @generated
	 */
   String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
   void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Dialect</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dialect</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dialect</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect
	 * @see #setDialect(SourceDialect)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Procedure_Dialect()
	 * @model
	 * @generated
	 */
	SourceDialect getDialect();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getDialect <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dialect</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect
	 * @see #getDialect()
	 * @generated
	 */
	void setDialect(SourceDialect value);

	/**
	 * Returns the value of the '<em><b>External Action</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Action</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Action</em>' attribute.
	 * @see #setExternalAction(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Procedure_ExternalAction()
	 * @model default="true"
	 * @generated
	 */
	boolean isExternalAction();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isExternalAction <em>External Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Action</em>' attribute.
	 * @see #isExternalAction()
	 * @generated
	 */
	void setExternalAction(boolean value);

	/**
	 * Returns the value of the '<em><b>Return</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
    * The stored procedure can only return an integer data type, if a return type is specified.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return</em>' containment reference.
	 * @see #setReturn(IntegerDataType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Procedure_Return()
	 * @model containment="true"
	 * @generated
	 */
	IntegerDataType getReturn();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getReturn <em>Return</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
    * The stored procedure can only return an integer data type, if a return type is specified.
	 * </p>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return</em>' containment reference.
	 * @see #getReturn()
	 * @generated
	 */
	void setReturn(IntegerDataType value);

	/**
	 * Returns the value of the '<em><b>Java Options</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getProcedure <em>Procedure</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Java Options</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Options</em>' containment reference.
	 * @see #setJavaOptions(DB2JavaOptions)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Procedure_JavaOptions()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getProcedure
	 * @model opposite="procedure" containment="true"
	 * @generated
	 */
   DB2JavaOptions getJavaOptions();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getJavaOptions <em>Java Options</em>}' containment reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Options</em>' containment reference.
	 * @see #getJavaOptions()
	 * @generated
	 */
   void setJavaOptions(DB2JavaOptions value);

	/**
	 * Returns the value of the '<em><b>Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deploy</em>' containment reference.
	 * @see #setDeploy(DB2ProcedureDeploy)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Procedure_Deploy()
	 * @model containment="true"
	 * @generated
	 */
	DB2ProcedureDeploy getDeploy();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getDeploy <em>Deploy</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deploy</em>' containment reference.
	 * @see #getDeploy()
	 * @generated
	 */
	void setDeploy(DB2ProcedureDeploy value);

} // DB2Procedure
