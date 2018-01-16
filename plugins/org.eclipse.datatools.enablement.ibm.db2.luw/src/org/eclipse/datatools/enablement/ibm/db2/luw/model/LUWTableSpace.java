/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.enablement.ibm.db2.model.UnitType;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Space</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Table space design
 * 
 * A table space is a storage structure containing tables, indexes, large objects, and long data. Table spaces reside in database partition groups. They allow you to assign the location of database and table data directly onto containers. (A container can be a directory name, a device name, or a file name.) This can provide improved performance and more flexible configuration. 
 * 
 * Since table spaces reside in database partition groups, the table space selected to hold a table defines how the data for that table is distributed across the database partitions in a database partition group. A single table space can span several containers. It is possible for multiple containers (from one or more table spaces) to be created on the same physical disk (or drive). For improved performance, each container should use a different disk. Figure 14 illustrates the relationship between tables and table spaces within a database, and the containers associated with that database.
 * 
 * Creating table spaces in database partition groups
 * 
 * By placing a table space in a multiple-partition database partition group, all of the tables within the table space are divided or partitioned across each partition in the database partition group. The table space is created into a database partition group. Once in a database partition group, the table space must remain there; it cannot be changed to another database partition group. The CREATE TABLESPACE statement is used to associate a table space with a database partition group.
 * 
 * 
 * IBMR DB2 Universal Database - SQL Reference Volume 1 - Version 8
 * 
 * Table spaces and other storage structures
 * 
 * Storage structures contain database objects. The basic storage structure is the table space; it contains tables, indexes, large objects, and data defined with a LONG data type.
 * 
 * There are two types of table spaces:
 *  - Database managed space (DMS): a table space that is managed by the database manager.
 *  - System managed space (SMS): a table space that is managed by the operating system.
 * 
 * All table spaces consist of containers. A container describes where objects are stored. A subdirectory in a file system is an example of a container. When data is read from table space containers, it is placed in an area of memory called a buffer pool. A buffer pool is associated with a specific table space, thereby allowing control over which data will share the same memory areas for data buffering.
 * 
 * Creation:
 * 
 * EXTENTSIZE number-of-pages
 * Specifies the number of PAGESIZE pages that will be written to a container before skipping to the next container. The extent size value can also be specified as an integer value followed by K (for kilobytes), M (for megabytes), or G (for gigabytes). If specified in this way, the floor of the number of bytes divided by the pagesize is used to determine the number of pages value for extent size. The database manager cycles repeatedly through the containers as data is stored. The default value is provided by the DFT_EXTENT_SZ configuration parameter.
 * 
 * PREFETCHSIZE number-of-pages
 * Specifies the number of PAGESIZE pages that will be read from the tablespace when data prefetching is being performed. The prefetch size value can also be specified as an integer value followed by K (for kilobytes), M (for megabytes), or G (for gigabytes). If specified in this way, the floor of the number of bytes divided by the pagesize is used to determine the number of pages value for prefetch size. Prefetching reads in data needed by a query prior to it being referenced by the query, so that the query need not wait for I/O to be performed. The default value is provided by the DFT_PREFETCH_SZ
 * configuration parameter.
 * 
 * BUFFERPOOL bufferpool-name
 * The name of the buffer pool used for tables in this tablespace. The buffer pool must exist (SQLSTATE 42704). If not specified, the default buffer pool (IBMDEFAULTBP) is used. The page size of the bufferpool must match the page size specified (or defaulted) for the tablespace (SQLSTATE 428CB). The database partition group of the tablespace must be defined for the bufferpool (SQLSTATE 42735).
 * 
 * OVERHEAD number-of-milliseconds
 * Any numeric literal (integer, decimal, or floating point) that specifies the I/O controller overhead and disk seek and latency time, in milliseconds. The number should be an average for all containers that belong to the tablespace, if not the same for all containers. This value is used to determine the cost of I/O during query optimization.
 * 
 * TRANSFERRATE number-of-milliseconds
 * Any numeric literal (integer, decimal, or floating point) that specifies the time to read one page into memory, in milliseconds. The number should be an average for all containers that belong to the tablespace, if not the same for all containers. This value is used to determine the cost of I/O during query optimization.
 * 
 * DROPPED TABLE RECOVERY
 * Dropped tables in the specified tablespace may be recovered using the RECOVER TABLE ON option of the ROLLFORWARD command. This clause can only be specified for a REGULAR tablespace (SQLSTATE 42613).
 * 
 * 
 * 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTemporaryStorageTables <em>Temporary Storage Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTablespaceType <em>Tablespace Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getManagementType <em>Management Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getExtentSize <em>Extent Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getPreFetchSize <em>Pre Fetch Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getOverhead <em>Overhead</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTransferRate <em>Transfer Rate</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isRecoverDroppedTableOn <em>Recover Dropped Table On</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getPageSize <em>Page Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getSize <em>Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isAutoResize <em>Auto Resize</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getInitialSize <em>Initial Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreaseSize <em>Increase Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getMaximumSize <em>Maximum Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getInitialSizeUnit <em>Initial Size Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getMaximumSizeUnit <em>Maximum Size Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreaseSizeUnit <em>Increase Size Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreasePercent <em>Increase Percent</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getFileSystemCaching <em>File System Caching</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getAverageSeekTime <em>Average Seek Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRotationSpeed <em>Rotation Speed</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTransfer <em>Transfer</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getSystemType <em>System Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getAverageTableSize <em>Average Table Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getExternalContainerCount <em>External Container Count</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isInheritOverhead <em>Inherit Overhead</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isInheritTransferate <em>Inherit Transferate</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isRebalance <em>Rebalance</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDataTag <em>Data Tag</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isSuspendRebalance <em>Suspend Rebalance</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isResumeRebalance <em>Resume Rebalance</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getContainers <em>Containers</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getBufferPool <em>Buffer Pool</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataTables <em>Index Data Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataTables <em>LOB Data Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataTables <em>Regular Data Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataPartition <em>LOB Data Partition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataPartition <em>Regular Data Partition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataPartition <em>Index Data Partition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getStorageGroup <em>Storage Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace()
 * @model
 * @generated
 */
public interface LUWTableSpace extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Temporary Storage Tables</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getUserTemporaryTableSpace <em>User Temporary Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Temporary Storage Tables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temporary Storage Tables</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_TemporaryStorageTables()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getUserTemporaryTableSpace
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable" opposite="userTemporaryTableSpace"
	 * @generated
	 */
	EList getTemporaryStorageTables();

	/**
	 * Returns the value of the '<em><b>Tablespace Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tablespace Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tablespace Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType
	 * @see #setTablespaceType(TableSpaceType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_TablespaceType()
	 * @model
	 * @generated
	 */
	TableSpaceType getTablespaceType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTablespaceType <em>Tablespace Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tablespace Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType
	 * @see #getTablespaceType()
	 * @generated
	 */
	void setTablespaceType(TableSpaceType value);

	/**
	 * Returns the value of the '<em><b>Management Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Management Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Management Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType
	 * @see #setManagementType(ManagementType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_ManagementType()
	 * @model
	 * @generated
	 */
	ManagementType getManagementType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getManagementType <em>Management Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Management Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType
	 * @see #getManagementType()
	 * @generated
	 */
	void setManagementType(ManagementType value);

	/**
	 * Returns the value of the '<em><b>Extent Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extent Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extent Size</em>' attribute.
	 * @see #setExtentSize(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_ExtentSize()
	 * @model
	 * @generated
	 */
	int getExtentSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getExtentSize <em>Extent Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extent Size</em>' attribute.
	 * @see #getExtentSize()
	 * @generated
	 */
	void setExtentSize(int value);

	/**
	 * Returns the value of the '<em><b>Pre Fetch Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Fetch Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Fetch Size</em>' attribute.
	 * @see #setPreFetchSize(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_PreFetchSize()
	 * @model
	 * @generated
	 */
	int getPreFetchSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getPreFetchSize <em>Pre Fetch Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pre Fetch Size</em>' attribute.
	 * @see #getPreFetchSize()
	 * @generated
	 */
	void setPreFetchSize(int value);

	/**
	 * Returns the value of the '<em><b>Overhead</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overhead</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overhead</em>' attribute.
	 * @see #setOverhead(double)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_Overhead()
	 * @model
	 * @generated
	 */
	double getOverhead();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getOverhead <em>Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Overhead</em>' attribute.
	 * @see #getOverhead()
	 * @generated
	 */
	void setOverhead(double value);

	/**
	 * Returns the value of the '<em><b>Transfer Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transfer Rate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transfer Rate</em>' attribute.
	 * @see #setTransferRate(double)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_TransferRate()
	 * @model
	 * @generated
	 */
	double getTransferRate();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTransferRate <em>Transfer Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transfer Rate</em>' attribute.
	 * @see #getTransferRate()
	 * @generated
	 */
	void setTransferRate(double value);

	/**
	 * Returns the value of the '<em><b>Recover Dropped Table On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recover Dropped Table On</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recover Dropped Table On</em>' attribute.
	 * @see #setRecoverDroppedTableOn(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_RecoverDroppedTableOn()
	 * @model
	 * @generated
	 */
	boolean isRecoverDroppedTableOn();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isRecoverDroppedTableOn <em>Recover Dropped Table On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recover Dropped Table On</em>' attribute.
	 * @see #isRecoverDroppedTableOn()
	 * @generated
	 */
	void setRecoverDroppedTableOn(boolean value);

	/**
	 * Returns the value of the '<em><b>Page Size</b></em>' attribute.
	 * The default value is <code>"FOUR_K"</code>.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page Size</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType
	 * @see #setPageSize(PageSizeType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_PageSize()
	 * @model default="FOUR_K"
	 * @generated
	 */
	PageSizeType getPageSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getPageSize <em>Page Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Size</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType
	 * @see #getPageSize()
	 * @generated
	 */
	void setPageSize(PageSizeType value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(long)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_Size()
	 * @model
	 * @generated
	 */
	long getSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(long value);

	/**
	 * Returns the value of the '<em><b>Auto Resize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auto Resize</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Resize</em>' attribute.
	 * @see #setAutoResize(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_AutoResize()
	 * @model
	 * @generated
	 */
	boolean isAutoResize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isAutoResize <em>Auto Resize</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Resize</em>' attribute.
	 * @see #isAutoResize()
	 * @generated
	 */
	void setAutoResize(boolean value);

	/**
	 * Returns the value of the '<em><b>Initial Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Size</em>' attribute.
	 * @see #setInitialSize(long)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_InitialSize()
	 * @model
	 * @generated
	 */
	long getInitialSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getInitialSize <em>Initial Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Size</em>' attribute.
	 * @see #getInitialSize()
	 * @generated
	 */
	void setInitialSize(long value);

	/**
	 * Returns the value of the '<em><b>Increase Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Increase Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Increase Size</em>' attribute.
	 * @see #setIncreaseSize(long)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_IncreaseSize()
	 * @model
	 * @generated
	 */
	long getIncreaseSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreaseSize <em>Increase Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Increase Size</em>' attribute.
	 * @see #getIncreaseSize()
	 * @generated
	 */
	void setIncreaseSize(long value);

	/**
	 * Returns the value of the '<em><b>Maximum Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Size</em>' attribute.
	 * @see #setMaximumSize(long)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_MaximumSize()
	 * @model
	 * @generated
	 */
	long getMaximumSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getMaximumSize <em>Maximum Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Size</em>' attribute.
	 * @see #getMaximumSize()
	 * @generated
	 */
	void setMaximumSize(long value);

	/**
	 * Returns the value of the '<em><b>Initial Size Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.UnitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Size Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Size Unit</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @see #setInitialSizeUnit(UnitType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_InitialSizeUnit()
	 * @model
	 * @generated
	 */
	UnitType getInitialSizeUnit();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getInitialSizeUnit <em>Initial Size Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Size Unit</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @see #getInitialSizeUnit()
	 * @generated
	 */
	void setInitialSizeUnit(UnitType value);

	/**
	 * Returns the value of the '<em><b>Maximum Size Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.UnitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Size Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Size Unit</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @see #setMaximumSizeUnit(UnitType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_MaximumSizeUnit()
	 * @model
	 * @generated
	 */
	UnitType getMaximumSizeUnit();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getMaximumSizeUnit <em>Maximum Size Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Size Unit</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @see #getMaximumSizeUnit()
	 * @generated
	 */
	void setMaximumSizeUnit(UnitType value);

	/**
	 * Returns the value of the '<em><b>Increase Size Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.model.UnitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Increase Size Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Increase Size Unit</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @see #setIncreaseSizeUnit(UnitType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_IncreaseSizeUnit()
	 * @model
	 * @generated
	 */
	UnitType getIncreaseSizeUnit();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreaseSizeUnit <em>Increase Size Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Increase Size Unit</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @see #getIncreaseSizeUnit()
	 * @generated
	 */
	void setIncreaseSizeUnit(UnitType value);

	/**
	 * Returns the value of the '<em><b>Increase Percent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Increase Percent</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Increase Percent</em>' attribute.
	 * @see #setIncreasePercent(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_IncreasePercent()
	 * @model
	 * @generated
	 */
	int getIncreasePercent();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreasePercent <em>Increase Percent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Increase Percent</em>' attribute.
	 * @see #getIncreasePercent()
	 * @generated
	 */
	void setIncreasePercent(int value);

	/**
	 * Returns the value of the '<em><b>File System Caching</b></em>' attribute.
	 * The default value is <code>"NONE"</code>.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File System Caching</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File System Caching</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType
	 * @see #setFileSystemCaching(FileSystemCachingType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_FileSystemCaching()
	 * @model default="NONE"
	 * @generated
	 */
	FileSystemCachingType getFileSystemCaching();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getFileSystemCaching <em>File System Caching</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File System Caching</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType
	 * @see #getFileSystemCaching()
	 * @generated
	 */
	void setFileSystemCaching(FileSystemCachingType value);

	/**
	 * Returns the value of the '<em><b>Average Seek Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Average Seek Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Average Seek Time</em>' attribute.
	 * @see #setAverageSeekTime(double)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_AverageSeekTime()
	 * @model
	 * @generated
	 */
	double getAverageSeekTime();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getAverageSeekTime <em>Average Seek Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Average Seek Time</em>' attribute.
	 * @see #getAverageSeekTime()
	 * @generated
	 */
	void setAverageSeekTime(double value);

	/**
	 * Returns the value of the '<em><b>Rotation Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rotation Speed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rotation Speed</em>' attribute.
	 * @see #setRotationSpeed(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_RotationSpeed()
	 * @model
	 * @generated
	 */
	int getRotationSpeed();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRotationSpeed <em>Rotation Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rotation Speed</em>' attribute.
	 * @see #getRotationSpeed()
	 * @generated
	 */
	void setRotationSpeed(int value);

	/**
	 * Returns the value of the '<em><b>Transfer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transfer</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transfer</em>' attribute.
	 * @see #setTransfer(double)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_Transfer()
	 * @model
	 * @generated
	 */
	double getTransfer();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTransfer <em>Transfer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transfer</em>' attribute.
	 * @see #getTransfer()
	 * @generated
	 */
	void setTransfer(double value);

	/**
	 * Returns the value of the '<em><b>System Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType
	 * @see #setSystemType(SystemType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_SystemType()
	 * @model
	 * @generated
	 */
	SystemType getSystemType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getSystemType <em>System Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType
	 * @see #getSystemType()
	 * @generated
	 */
	void setSystemType(SystemType value);

	/**
	 * Returns the value of the '<em><b>Average Table Size</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Average Table Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Average Table Size</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType
	 * @see #setAverageTableSize(AverageTableSizeType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_AverageTableSize()
	 * @model
	 * @generated
	 */
	AverageTableSizeType getAverageTableSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getAverageTableSize <em>Average Table Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Average Table Size</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType
	 * @see #getAverageTableSize()
	 * @generated
	 */
	void setAverageTableSize(AverageTableSizeType value);

	/**
	 * Returns the value of the '<em><b>External Container Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Container Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Container Count</em>' attribute.
	 * @see #setExternalContainerCount(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_ExternalContainerCount()
	 * @model
	 * @generated
	 */
	int getExternalContainerCount();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getExternalContainerCount <em>External Container Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Container Count</em>' attribute.
	 * @see #getExternalContainerCount()
	 * @generated
	 */
	void setExternalContainerCount(int value);

	/**
	 * Returns the value of the '<em><b>Inherit Overhead</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inherit Overhead</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherit Overhead</em>' attribute.
	 * @see #setInheritOverhead(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_InheritOverhead()
	 * @model
	 * @generated
	 */
	boolean isInheritOverhead();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isInheritOverhead <em>Inherit Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherit Overhead</em>' attribute.
	 * @see #isInheritOverhead()
	 * @generated
	 */
	void setInheritOverhead(boolean value);

	/**
	 * Returns the value of the '<em><b>Inherit Transferate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inherit Transferate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherit Transferate</em>' attribute.
	 * @see #setInheritTransferate(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_InheritTransferate()
	 * @model
	 * @generated
	 */
	boolean isInheritTransferate();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isInheritTransferate <em>Inherit Transferate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inherit Transferate</em>' attribute.
	 * @see #isInheritTransferate()
	 * @generated
	 */
	void setInheritTransferate(boolean value);

	/**
	 * Returns the value of the '<em><b>Rebalance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rebalance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rebalance</em>' attribute.
	 * @see #setRebalance(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_Rebalance()
	 * @model
	 * @generated
	 */
	boolean isRebalance();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isRebalance <em>Rebalance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rebalance</em>' attribute.
	 * @see #isRebalance()
	 * @generated
	 */
	void setRebalance(boolean value);

	/**
	 * Returns the value of the '<em><b>Data Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Tag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Tag</em>' attribute.
	 * @see #setDataTag(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_DataTag()
	 * @model
	 * @generated
	 */
	String getDataTag();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDataTag <em>Data Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Tag</em>' attribute.
	 * @see #getDataTag()
	 * @generated
	 */
	void setDataTag(String value);

	/**
	 * Returns the value of the '<em><b>Suspend Rebalance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suspend Rebalance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Suspend Rebalance</em>' attribute.
	 * @see #setSuspendRebalance(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_SuspendRebalance()
	 * @model
	 * @generated
	 */
	boolean isSuspendRebalance();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isSuspendRebalance <em>Suspend Rebalance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suspend Rebalance</em>' attribute.
	 * @see #isSuspendRebalance()
	 * @generated
	 */
	void setSuspendRebalance(boolean value);

	/**
	 * Returns the value of the '<em><b>Resume Rebalance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resume Rebalance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resume Rebalance</em>' attribute.
	 * @see #setResumeRebalance(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_ResumeRebalance()
	 * @model
	 * @generated
	 */
	boolean isResumeRebalance();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isResumeRebalance <em>Resume Rebalance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resume Rebalance</em>' attribute.
	 * @see #isResumeRebalance()
	 * @generated
	 */
	void setResumeRebalance(boolean value);

	/**
	 * Returns the value of the '<em><b>Group</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getTableSpaces <em>Table Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' reference.
	 * @see #setGroup(LUWPartitionGroup)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_Group()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getTableSpaces
	 * @model opposite="tableSpaces" required="true"
	 * @generated
	 */
	LUWPartitionGroup getGroup();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getGroup <em>Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' reference.
	 * @see #getGroup()
	 * @generated
	 */
	void setGroup(LUWPartitionGroup value);

	/**
	 * Returns the value of the '<em><b>Containers</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getTableSpace <em>Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containers</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_Containers()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getTableSpace
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer" opposite="tableSpace" containment="true" required="true"
	 * @generated
	 */
	EList getContainers();

	/**
	 * Returns the value of the '<em><b>Buffer Pool</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getTableSpaces <em>Table Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Buffer Pool</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Buffer Pool</em>' reference.
	 * @see #setBufferPool(LUWBufferPool)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_BufferPool()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getTableSpaces
	 * @model opposite="tableSpaces" required="true"
	 * @generated
	 */
	LUWBufferPool getBufferPool();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getBufferPool <em>Buffer Pool</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Buffer Pool</em>' reference.
	 * @see #getBufferPool()
	 * @generated
	 */
	void setBufferPool(LUWBufferPool value);

	/**
	 * Returns the value of the '<em><b>Regular Data Tables</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getRegularDataTableSpace <em>Regular Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regular Data Tables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regular Data Tables</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_RegularDataTables()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getRegularDataTableSpace
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable" opposite="regularDataTableSpace"
	 * @generated
	 */
	EList getRegularDataTables();

	/**
	 * Returns the value of the '<em><b>Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getTablespaces <em>Tablespaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' reference.
	 * @see #setDatabase(LUWDatabase)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_Database()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getTablespaces
	 * @model opposite="tablespaces" required="true"
	 * @generated
	 */
	LUWDatabase getDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDatabase <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' reference.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(LUWDatabase value);

	/**
	 * Returns the value of the '<em><b>LOB Data Partition</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getLOBDataTableSpace <em>LOB Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LOB Data Partition</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LOB Data Partition</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_LOBDataPartition()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getLOBDataTableSpace
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition" opposite="LOBDataTableSpace"
	 * @generated
	 */
	EList getLOBDataPartition();

	/**
	 * Returns the value of the '<em><b>Regular Data Partition</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getRegularDataTableSpace <em>Regular Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regular Data Partition</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regular Data Partition</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_RegularDataPartition()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getRegularDataTableSpace
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition" opposite="regularDataTableSpace"
	 * @generated
	 */
	EList getRegularDataPartition();

	/**
	 * Returns the value of the '<em><b>Indexes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getTablespace <em>Tablespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexes</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_Indexes()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getTablespace
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex" opposite="tablespace"
	 * @generated
	 */
	EList getIndexes();

	/**
	 * Returns the value of the '<em><b>Index Data Partition</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getIndexDataTableSpace <em>Index Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Data Partition</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Data Partition</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_IndexDataPartition()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getIndexDataTableSpace
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition" opposite="indexDataTableSpace"
	 * @generated
	 */
	EList getIndexDataPartition();

	/**
	 * Returns the value of the '<em><b>Storage Group</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getTableSpaces <em>Table Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Storage Group</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Storage Group</em>' reference.
	 * @see #setStorageGroup(LUWStorageGroup)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_StorageGroup()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getTableSpaces
	 * @model opposite="tableSpaces"
	 * @generated
	 */
	LUWStorageGroup getStorageGroup();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getStorageGroup <em>Storage Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Storage Group</em>' reference.
	 * @see #getStorageGroup()
	 * @generated
	 */
	void setStorageGroup(LUWStorageGroup value);

	/**
	 * Returns the value of the '<em><b>Index Data Tables</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getIndexDataTableSpace <em>Index Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Data Tables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Data Tables</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_IndexDataTables()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getIndexDataTableSpace
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable" opposite="indexDataTableSpace"
	 * @generated
	 */
	EList getIndexDataTables();

	/**
	 * Returns the value of the '<em><b>LOB Data Tables</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getLOBDataTableSpace <em>LOB Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LOB Data Tables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LOB Data Tables</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWTableSpace_LOBDataTables()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getLOBDataTableSpace
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable" opposite="LOBDataTableSpace"
	 * @generated
	 */
	EList getLOBDataTables();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.datatools.modelbase.sql.schema.List" many="false"
	 * @generated
	 */
	List getTables();

} // LUWTableSpace
