/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Database;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Relational databases
 * 
 * A relational database is a database that is treated as a set of tables and manipulated in accordance with the relational model of data. It contains a set of objects used to store, manage, and access data. Examples of such objects are tables, views, indexes, functions, triggers, and packages.
 * 
 * A partitioned relational database is a relational database whose data is managed across multiple partitions (also called nodes). This separation of data across partitions is transparent to users of most SQL statements. However, some data definition language (DDL) statements take partition information into consideration (for example, CREATE DATABASE PARTITION GROUP). (Data definition language is the subset of SQL statements used to describe data relationships in a database.)
 * 
 * A federated database is a relational database whose data is stored in multiple data sources (such as separate relational databases). The data appears as if it were all in a single large database and can be accessed through traditional SQL queries. Changes to the data can be explicitly directed to the appropriate data source.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#isFederated <em>Federated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getGroups <em>Groups</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getWrappers <em>Wrappers</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getServers <em>Servers</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getFunctionMappings <em>Function Mappings</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getTypeMappings <em>Type Mappings</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getReverseTypeMappings <em>Reverse Type Mappings</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getBufferpools <em>Bufferpools</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getTablespaces <em>Tablespaces</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getStorageGroups <em>Storage Groups</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getDefaultStorageGroup <em>Default Storage Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase()
 * @model
 * @generated
 */
public interface LUWDatabase extends DB2Database {
	/**
	 * Returns the value of the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Federated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Federated</em>' attribute.
	 * @see #setFederated(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_Federated()
	 * @model
	 * @generated
	 */
	boolean isFederated();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#isFederated <em>Federated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Federated</em>' attribute.
	 * @see #isFederated()
	 * @generated
	 */
	void setFederated(boolean value);

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_Groups()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getDatabase
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup" opposite="database"
	 * @generated
	 */
	EList getGroups();

	/**
	 * Returns the value of the '<em><b>Wrappers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLUWDatabase <em>LUW Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wrappers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wrappers</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_Wrappers()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLUWDatabase
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper" opposite="LUWDatabase"
	 * @generated
	 */
	EList getWrappers();

	/**
	 * Returns the value of the '<em><b>Servers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getLUWDatabase <em>LUW Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Servers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Servers</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_Servers()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getLUWDatabase
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer" opposite="LUWDatabase"
	 * @generated
	 */
	EList getServers();

	/**
	 * Returns the value of the '<em><b>Function Mappings</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLUWDatabase <em>LUW Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Mappings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Mappings</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_FunctionMappings()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLUWDatabase
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping" opposite="LUWDatabase"
	 * @generated
	 */
	EList getFunctionMappings();

	/**
	 * Returns the value of the '<em><b>Type Mappings</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Mappings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Mappings</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_TypeMappings()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping"
	 * @generated
	 */
	EList getTypeMappings();

	/**
	 * Returns the value of the '<em><b>Reverse Type Mappings</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reverse Type Mappings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reverse Type Mappings</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_ReverseTypeMappings()
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping"
	 * @generated
	 */
	EList getReverseTypeMappings();

	/**
	 * Returns the value of the '<em><b>Bufferpools</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bufferpools</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bufferpools</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_Bufferpools()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getDatabase
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool" opposite="database" required="true"
	 * @generated
	 */
	EList getBufferpools();

	/**
	 * Returns the value of the '<em><b>Tablespaces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tablespaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tablespaces</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_Tablespaces()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDatabase
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace" opposite="database"
	 * @generated
	 */
	EList getTablespaces();

	/**
	 * Returns the value of the '<em><b>Storage Groups</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storage Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Storage Groups</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_StorageGroups()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getDatabase
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup" opposite="database"
	 * @generated
	 */
	EList getStorageGroups();

	/**
	 * Returns the value of the '<em><b>Default Storage Group</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Storage Group</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Storage Group</em>' reference.
	 * @see #setDefaultStorageGroup(LUWStorageGroup)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWDatabase_DefaultStorageGroup()
	 * @model
	 * @generated
	 */
	LUWStorageGroup getDefaultStorageGroup();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getDefaultStorageGroup <em>Default Storage Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Storage Group</em>' reference.
	 * @see #getDefaultStorageGroup()
	 * @generated
	 */
	void setDefaultStorageGroup(LUWStorageGroup value);

} // LUWDatabase
