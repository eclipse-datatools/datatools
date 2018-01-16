/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.routines.Method;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Inherits both DB2Model::DB2Function and SQLModel::Method and 
 * used in SQLModel::StructuredUserDefinedType
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Method#isReturnsSelfAsResult <em>Returns Self As Result</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Method#isImplemented <em>Implemented</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Method()
 * @model
 * @generated
 */
public interface DB2Method extends Method, DB2Function {
	/**
	 * Returns the value of the '<em><b>Returns Self As Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * SELF AS RESULT
	 * Identifies this method as a type-preserving method, which means the
	 * following:
	 * - The declared return type must be the same as the declared subject-type
	 *   (SQLSTATE 428EQ).
	 * - When an SQL statement is compiled and resolves to a type preserving
	 *   method, the static type of the result of the method is the same as the
	 *   static type of the subject argument.
	 * - The method must be implemented in such a way that the dynamic type
	 *   of the result is the same as the dynamic type of the subject argument
	 *   (SQLSTATE 2200G), and the result cannot be NULL (SQLSTATE 22004).
	 * 
	 * If the method being defined overrides another method, this clause cannot
	 * be specified (SQLSTATE 428FV).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Returns Self As Result</em>' attribute.
	 * @see #setReturnsSelfAsResult(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Method_ReturnsSelfAsResult()
	 * @model
	 * @generated
	 */
	boolean isReturnsSelfAsResult();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Method#isReturnsSelfAsResult <em>Returns Self As Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Returns Self As Result</em>' attribute.
	 * @see #isReturnsSelfAsResult()
	 * @generated
	 */
	void setReturnsSelfAsResult(boolean value);

	/**
	 * Returns the value of the '<em><b>Implemented</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implemented</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implemented</em>' attribute.
	 * @see #setImplemented(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Method_Implemented()
	 * @model
	 * @generated
	 */
	boolean isImplemented();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Method#isImplemented <em>Implemented</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implemented</em>' attribute.
	 * @see #isImplemented()
	 * @generated
	 */
	void setImplemented(boolean value);

} // DB2Method
