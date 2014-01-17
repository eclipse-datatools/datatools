/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2JavaOptions.java,v 1.6 2007/10/12 23:05:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Java Options</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#isSqlj <em>Sqlj</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getProcedure <em>Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getJar <em>Jar</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2JavaOptions()
 * @model
 * @generated
 */
public interface DB2JavaOptions extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Java class name of the routine registered with the database, initially the same as the method name.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Name</em>' attribute.
	 * @see #setClassName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2JavaOptions_ClassName()
	 * @model
	 * @generated
	 */
   String getClassName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getClassName <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Name</em>' attribute.
	 * @see #getClassName()
	 * @generated
	 */
   void setClassName(String value);

	/**
	 * Returns the value of the '<em><b>Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Java method name (entry point), initially derived from the procedure name.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method Name</em>' attribute.
	 * @see #setMethodName(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2JavaOptions_MethodName()
	 * @model
	 * @generated
	 */
   String getMethodName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getMethodName <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Name</em>' attribute.
	 * @see #getMethodName()
	 * @generated
	 */
   void setMethodName(String value);

	/**
	 * Returns the value of the '<em><b>Sqlj</b></em>' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If <code>true</code>, then the stored procedure is SQLJ, a static Java stored procedure.  If <code>false</code>, then the java stored procedure is dynamic
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sqlj</em>' attribute.
	 * @see #setSqlj(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2JavaOptions_Sqlj()
	 * @model
	 * @generated
	 */
   boolean isSqlj();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#isSqlj <em>Sqlj</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sqlj</em>' attribute.
	 * @see #isSqlj()
	 * @generated
	 */
   void setSqlj(boolean value);

	/**
	 * Returns the value of the '<em><b>Procedure</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getJavaOptions <em>Java Options</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Procedure</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Procedure</em>' container reference.
	 * @see #setProcedure(DB2Procedure)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2JavaOptions_Procedure()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getJavaOptions
	 * @model opposite="javaOptions" required="true"
	 * @generated
	 */
   DB2Procedure getProcedure();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getProcedure <em>Procedure</em>}' container reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Procedure</em>' container reference.
	 * @see #getProcedure()
	 * @generated
	 */
   void setProcedure(DB2Procedure value);

	/**
	 * Returns the value of the '<em><b>Jar</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getProcedures <em>Procedures</em>}'.
	 * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Jar</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
	 * @return the value of the '<em>Jar</em>' reference.
	 * @see #setJar(DB2Jar)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2JavaOptions_Jar()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getProcedures
	 * @model opposite="procedures"
	 * @generated
	 */
   DB2Jar getJar();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getJar <em>Jar</em>}' reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jar</em>' reference.
	 * @see #getJar()
	 * @generated
	 */
   void setJar(DB2Jar value);

} // DB2JavaOptions
