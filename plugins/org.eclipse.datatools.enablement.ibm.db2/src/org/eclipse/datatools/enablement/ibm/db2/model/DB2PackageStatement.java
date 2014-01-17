/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2PackageStatement.java,v 1.1 2008/06/10 20:19:40 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Package Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getStatementNumber <em>Statement Number</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getSectionNumber <em>Section Number</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getSqlStatement <em>Sql Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2PackageStatement()
 * @model
 * @generated
 */
public interface DB2PackageStatement extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Statement Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statement Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statement Number</em>' attribute.
	 * @see #setStatementNumber(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2PackageStatement_StatementNumber()
	 * @model
	 * @generated
	 */
	int getStatementNumber();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getStatementNumber <em>Statement Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statement Number</em>' attribute.
	 * @see #getStatementNumber()
	 * @generated
	 */
	void setStatementNumber(int value);

	/**
	 * Returns the value of the '<em><b>Section Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Section Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Section Number</em>' attribute.
	 * @see #setSectionNumber(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2PackageStatement_SectionNumber()
	 * @model
	 * @generated
	 */
	int getSectionNumber();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getSectionNumber <em>Section Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Number</em>' attribute.
	 * @see #getSectionNumber()
	 * @generated
	 */
	void setSectionNumber(int value);

	/**
	 * Returns the value of the '<em><b>Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getStatements <em>Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' container reference.
	 * @see #setPackage(DB2Package)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2PackageStatement_Package()
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getStatements
	 * @model opposite="statements" required="true"
	 * @generated
	 */
	DB2Package getPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getPackage <em>Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' container reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(DB2Package value);

	/**
	 * Returns the value of the '<em><b>Sql Statement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sql Statement</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sql Statement</em>' containment reference.
	 * @see #setSqlStatement(SQLStatement)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2PackageStatement_SqlStatement()
	 * @model containment="true"
	 * @generated
	 */
	SQLStatement getSqlStatement();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getSqlStatement <em>Sql Statement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sql Statement</em>' containment reference.
	 * @see #getSqlStatement()
	 * @generated
	 */
	void setSqlStatement(SQLStatement value);

} // DB2PackageStatement
