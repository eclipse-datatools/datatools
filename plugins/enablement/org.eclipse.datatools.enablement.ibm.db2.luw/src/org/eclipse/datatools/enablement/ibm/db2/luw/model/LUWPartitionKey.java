/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Partition Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Data partitioning across multiple partitions
 * 
 * DB2 allows great flexibility in spreading data across multiple partitions (nodes) of a partitioned database. Users can choose how to partition their data by declaring partitioning keys, and can determine which and how many partitions their table data can be spread across by selecting the database partition group and table space in which the data should be stored. In addition, a partitioning map (which is updatable) specifies the mapping of partitioning key values to partitions. This makes it possible for flexible workload parallelization across a partitioned database for large tables, while allowing smaller tables to be stored on one or a small number of partitions if the application designer so chooses. Each local partition may have local indexes on the data it stores to provide high performance local data access.
 * 
 * A partitioned database supports a partitioned storage model, in which the partitioning key is used to partition table data across a set of database partitions. Index data is also partitioned with its corresponding tables, and stored locally at each partition. Before partitions can be used to store database data, they must be defined to the database manager. Partitions are defined in a file called db2nodes.cfg.
 * 
 * The partitioning key for a table in a table space on a partitioned database partition group is specified in the CREATE TABLE statement or the ALTER TABLE statement. If not specified, a partitioning key for a table is created by default from the first column of the primary key. If no primary key is defined, the default partitioning key is the first column defined in that table that has a data type other than a long or a LOB data type. Partitioned tables must have at least one column that is neither a long nor a LOB data type. A table in a table space that is in a single partition database partition group will have a partitioning key only if it is explicitly specified.
 * 
 * Hash partitioning is used to place a row in a partition as follows:
 * 1. A hashing algorithm (partitioning function) is applied to all of the columns of the partitioning key, which results in the generation of a partitioning map index value.
 * 2. The partition number at that index value in the partitioning map identifies the partition in which the row is to be stored.
 * 
 * DB2 supports partial declustering, which means that a table can be partitioned across a subset of partitions in the system (that is, a database partition group). Tables do not have to be partitioned across all of the partitions in the system. DB2 has the capability of recognizing when data being accessed for a join or a subquery is located at the same partition in the same database partition group. This is known as table collocation. Rows in collocated tables with the same partitioning key values are located on the same partition. DB2 can choose to perform join or subquery processing at the partition in which the data is stored. This can have significant performance advantages.
 * 
 * Collocated tables must:
 *  - Be in the same database partition group, one that is not being redistributed. (During redistribution, tables in the database partition group may be using different partitioning maps - they are not collocated.)
 *  - Have partitioning keys with the same number of columns.
 *  - Have the corresponding columns of the partitioning key be partition compatible.
 *  - Be in a single partition database partition group defined on the same partition.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTemporaryStorageTable <em>Temporary Storage Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getPartitionMethod <em>Partition Method</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getColumns <em>Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionKey()
 * @model
 * @generated
 */
public interface LUWPartitionKey extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Temporary Storage Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getPartitionKey <em>Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Temporary Storage Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temporary Storage Table</em>' container reference.
	 * @see #setTemporaryStorageTable(LUWTemporaryStorageTable)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionKey_TemporaryStorageTable()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getPartitionKey
	 * @model opposite="partitionKey" required="true"
	 * @generated
	 */
	LUWTemporaryStorageTable getTemporaryStorageTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTemporaryStorageTable <em>Temporary Storage Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temporary Storage Table</em>' container reference.
	 * @see #getTemporaryStorageTable()
	 * @generated
	 */
	void setTemporaryStorageTable(LUWTemporaryStorageTable value);

	/**
	 * Returns the value of the '<em><b>Partition Method</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Method</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod
	 * @see #setPartitionMethod(PartitionMethod)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionKey_PartitionMethod()
	 * @model
	 * @generated
	 */
	PartitionMethod getPartitionMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getPartitionMethod <em>Partition Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition Method</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod
	 * @see #getPartitionMethod()
	 * @generated
	 */
	void setPartitionMethod(PartitionMethod value);

	/**
	 * Returns the value of the '<em><b>Table</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getPartitionKey <em>Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table</em>' container reference.
	 * @see #setTable(LUWStorageTable)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionKey_Table()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getPartitionKey
	 * @model opposite="partitionKey" required="true"
	 * @generated
	 */
	LUWStorageTable getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTable <em>Table</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' container reference.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(LUWStorageTable value);

	/**
	 * Returns the value of the '<em><b>Columns</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.tables.Column}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionKey_Columns()
	 * @model type="org.eclipse.datatools.modelbase.sql.tables.Column" required="true"
	 * @generated
	 */
	EList getColumns();

} // LUWPartitionKey
