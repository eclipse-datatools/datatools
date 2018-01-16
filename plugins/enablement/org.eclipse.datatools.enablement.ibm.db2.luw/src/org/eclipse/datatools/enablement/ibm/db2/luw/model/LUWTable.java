/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Table;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Tables
 * 
 * Tables are logical structures maintained by the database manager. Tables are made up of columns and rows. The rows are not necessarily ordered within a table (order is determined by the application program). At the intersection of every column and row is a specific data item called a value. A column is a set of values of the same type or one of its subtypes. A row is a sequence of values arranged so that the nth value is a value of the nth column of the table.
 * 
 * A base table is created with the CREATE TABLE statement and is used to hold persistent user data. A result table is a set of rows that the database manager selects or generates from one or more base tables to satisfy a query.
 * 
 * A summary table is a table defined by a query that is also used to determine the data in the table. Summary tables can be used to improve the performance of queries. If the database manager determines that a portion of a query can be resolved using a summary table, the database manager can rewrite the query to use the summary table. This decision is based on database configuration settings, such as the CURRENT REFRESH AGE and the CURRENT QUERY OPTIMIZATION special registers.
 * 
 * A table can define the data type of each column separately, or base the types on the attributes of a user-defined structured type. This is called a typed table. A user-defined structured type may be part of a type hierarchy. A subtype inherits attributes from its supertype. Similarly, a typed table can be part of a table hierarchy. A subtable inherits columns from its supertable. Note that the term subtype applies to a user-defined structured type and all user-defined structured types that are below it in the type hierarchy. A proper subtype of a structured type T is a structured type below T in the type hierarchy. Similarly, the term subtable applies to a typed table and all typed tables that are below it in the table hierarchy. A proper subtable of a table T is a table below T in the table hierarchy.
 * 
 * A declared temporary table is created with a DECLARE GLOBAL TEMPORARY TABLE statement and is used to hold temporary data on behalf of a single application. This table is dropped implicitly when the application disconnects from the database.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getPCTFree <em>PCT Free</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isRestrictOnDrop <em>Restrict On Drop</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getPartitionMode <em>Partition Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isAppendMode <em>Append Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getLogMode <em>Log Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isLockSizeRow <em>Lock Size Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isVolatile <em>Volatile</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getSecurityPolicy <em>Security Policy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable()
 * @model
 * @generated
 */
public interface LUWTable extends DB2Table, LUWStorageTable {
	/**
	 * Returns the value of the '<em><b>PCT Free</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>PCT Free</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>PCT Free</em>' attribute.
	 * @see #setPCTFree(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable_PCTFree()
	 * @model default="-1"
	 * @generated
	 */
	int getPCTFree();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getPCTFree <em>PCT Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PCT Free</em>' attribute.
	 * @see #getPCTFree()
	 * @generated
	 */
	void setPCTFree(int value);

	/**
	 * Returns the value of the '<em><b>Restrict On Drop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Restrict On Drop</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Restrict On Drop</em>' attribute.
	 * @see #setRestrictOnDrop(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable_RestrictOnDrop()
	 * @model
	 * @generated
	 */
	boolean isRestrictOnDrop();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isRestrictOnDrop <em>Restrict On Drop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Restrict On Drop</em>' attribute.
	 * @see #isRestrictOnDrop()
	 * @generated
	 */
	void setRestrictOnDrop(boolean value);

	/**
	 * Returns the value of the '<em><b>Partition Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Possible values: "USING HASHING", :REPLICATED", "" (w/o quotes)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Partition Mode</em>' attribute.
	 * @see #setPartitionMode(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable_PartitionMode()
	 * @model
	 * @generated
	 */
	String getPartitionMode();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getPartitionMode <em>Partition Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition Mode</em>' attribute.
	 * @see #getPartitionMode()
	 * @generated
	 */
	void setPartitionMode(String value);

	/**
	 * Returns the value of the '<em><b>Append Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Append Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Append Mode</em>' attribute.
	 * @see #setAppendMode(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable_AppendMode()
	 * @model
	 * @generated
	 */
	boolean isAppendMode();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isAppendMode <em>Append Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Append Mode</em>' attribute.
	 * @see #isAppendMode()
	 * @generated
	 */
	void setAppendMode(boolean value);

	/**
	 * Returns the value of the '<em><b>Log Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Possible values: "NOT LOGGED INITIALLY", "LOGGED INITIALLY", or "LOGGED INITIALLY WITH EMPTY TABLE" (w/o quotes)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Log Mode</em>' attribute.
	 * @see #setLogMode(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable_LogMode()
	 * @model
	 * @generated
	 */
	String getLogMode();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getLogMode <em>Log Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Log Mode</em>' attribute.
	 * @see #getLogMode()
	 * @generated
	 */
	void setLogMode(String value);

	/**
	 * Returns the value of the '<em><b>Lock Size Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Row lock if True else Table lock
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lock Size Row</em>' attribute.
	 * @see #setLockSizeRow(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable_LockSizeRow()
	 * @model
	 * @generated
	 */
	boolean isLockSizeRow();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isLockSizeRow <em>Lock Size Row</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lock Size Row</em>' attribute.
	 * @see #isLockSizeRow()
	 * @generated
	 */
	void setLockSizeRow(boolean value);

	/**
	 * Returns the value of the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Volatile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volatile</em>' attribute.
	 * @see #setVolatile(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable_Volatile()
	 * @model
	 * @generated
	 */
	boolean isVolatile();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isVolatile <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Volatile</em>' attribute.
	 * @see #isVolatile()
	 * @generated
	 */
	void setVolatile(boolean value);

	/**
	 * Returns the value of the '<em><b>Security Policy</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Policy</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Policy</em>' reference.
	 * @see #setSecurityPolicy(LUWSecurityPolicy)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable_SecurityPolicy()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getTable
	 * @model opposite="table" required="true"
	 * @generated
	 */
	LUWSecurityPolicy getSecurityPolicy();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getSecurityPolicy <em>Security Policy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Security Policy</em>' reference.
	 * @see #getSecurityPolicy()
	 * @generated
	 */
	void setSecurityPolicy(LUWSecurityPolicy value);

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
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTable_Options()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption" containment="true"
	 * @generated
	 */
	EList getOptions();

} // LUWTable
