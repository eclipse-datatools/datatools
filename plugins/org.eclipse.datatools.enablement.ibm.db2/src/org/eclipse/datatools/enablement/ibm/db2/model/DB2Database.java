/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.Database;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Relational databases (Chapter 1. Concepts 1)
 * 
 * A relational database is a database that can be perceived as a set of tables and can be manipulated in accordance with the relational model of data. The relational database contains a set of objects used to store, access, and manage data. The set of objects includes tables, views, indexes, aliases, distinct types, functions, procedures, and packages. Any number of relational databases can be created on a given physical machine.
 * 
 * A partitioned relational database is a relational database whose data is managed across multiple partitions (also called nodes). This separation of data across partitions is transparent to users of most SQL statements. However, some data definition language (DDL) statements take partition information into consideration (for example, CREATE DATABASE PARTITION GROUP). (Data definition language is the subset of SQL statements used to describe data relationships in a database.)
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Database#isPartitioned <em>Partitioned</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Database#isDefaultOrganizeByRow <em>Default Organize By Row</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Database()
 * @model
 * @generated
 */
public interface DB2Database extends Database {
	/**
	 * Returns the value of the '<em><b>Partitioned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partitioned</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partitioned</em>' attribute.
	 * @see #setPartitioned(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Database_Partitioned()
	 * @model
	 * @generated
	 */
	boolean isPartitioned();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Database#isPartitioned <em>Partitioned</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partitioned</em>' attribute.
	 * @see #isPartitioned()
	 * @generated
	 */
	void setPartitioned(boolean value);

	/**
	 * Returns the value of the '<em><b>Default Organize By Row</b></em>' attribute.
	 * The default value is <code>"True"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Organize By Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Organize By Row</em>' attribute.
	 * @see #setDefaultOrganizeByRow(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2Database_DefaultOrganizeByRow()
	 * @model default="True"
	 * @generated
	 */
	boolean isDefaultOrganizeByRow();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Database#isDefaultOrganizeByRow <em>Default Organize By Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Organize By Row</em>' attribute.
	 * @see #isDefaultOrganizeByRow()
	 * @generated
	 */
	void setDefaultOrganizeByRow(boolean value);

} // DB2Database
