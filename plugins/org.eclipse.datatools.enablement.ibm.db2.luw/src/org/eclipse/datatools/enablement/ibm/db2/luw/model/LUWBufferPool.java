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
 * A representation of the model object '<em><b>Buffer Pool</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * DB2 Universal Database SQL Reference Version 8.1 (Vol.1 and 2)
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * Table spaces and other storage structures
 * 
 * Storage structures contain database objects. The basic storage structure is the table space; it contains tables, indexes, large objects, and data defined with a LONG data type.
 * 
 * There are two types of table spaces:
 *  - Database managed space (DMS): a table space that is managed by the database manager.
 *  - System managed space (SMS): a table space that is managed by the operating system.
 * 
 * All table spaces consist of containers. A container describes where objects are stored. A subdirectory in a file system is an example of a container.
 * 
 * When data is read from table space containers, it is placed in an area of memory called a buffer pool. A buffer pool is associated with a specific table space, thereby allowing control over which data will share the same memory areas for data buffering.
 * 
 * 
 * NUMBLOCKPAGES number-of-pages:
 * Specifies the number of pages that should exist in the block-based area. The number of pages must not be greater than 98 percent of the number of pages for the buffer pool (SQLSTATE 54052). Specifying the value 0 disables block I/O. The actual value of NUMBLOCKPAGES used will be a multiple of BLOCKSIZE.
 * 
 * BLOCKSIZE number-of-pages:
 * Specifies the number of pages in a block. The block size must be a value between 2 and 256 (SQLSTATE 54053). The default value is 32.
 * 
 * PAGESIZE integer [K]:
 * Defines the size of pages used for the bufferpool. The valid values for integer without the suffix K are 4 096, 8 192, 16 384 or 32 768. The valid values for integer with the suffix K are 4, 8, 16 or 32. An error occurs if the page size is not one of these values (SQLSTATE 428DE). The default is 4 096 byte (4K) pages. Any number of spaces is allowed between integer and K, including no space.
 * 
 * EXTENDED STORAGE
 * If extended storage is enabled, pages that are being evicted from this buffer pool will be cached in extended storage. (Extended storage is enabled by setting the database configuration parameters NUM_ESTORE_SEGS and ESTORE_SEG_SIZE to non-zero values.)
 * 
 * NOT EXTENDED STORAGE
 * Even if extended storage is enabled, pages that are being evicted from this buffer pool are not cached in extended storage.
 * 
 * Notes:
 * Compatibilities: for compatibility with previous versions of DB2:
 * - NODE can be specified in place of DBPARTITIONNUM
 * - NODES can be specified in place of DBPARTITIONNUMS
 * - NODEGROUP can be specified in place of DATABASE PARTITION GROUP
 * 
 * -  If the buffer pool is created using the DEFERRED option, any table space created in this buffer pool will use a small system buffer pool of the same page size, until next database activation. The database has to be restarted for the buffer pool to become active and for table space assignments to the new buffer pool to take effect. The default option is IMMEDIATE.
 *  - A buffer pool cannot be created with both extended storage and block-based support.
 *  - There should be enough real memory on the machine for the total of all the buffer pools, as well as for the rest of the database manager and application requirements. If DB2 is unable to obtain memory for the regular buffer pools, it will attempt to start up with small system buffer pools, one for each page size (4K, 8K, 16K and 32K). In this situation, a warning will be returned to the user (SQLSTATE 01626), and the pages from all table spaces will use the system buffer pools.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getCreateType <em>Create Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getSize <em>Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPageSize <em>Page Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getBlockSize <em>Block Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getNumBlockPages <em>Num Block Pages</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#isExtendedStorage <em>Extended Storage</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#isAutomatic <em>Automatic</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getTableSpaces <em>Table Spaces</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPartitions <em>Partitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPartitionGroup <em>Partition Group</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getSizeException <em>Size Exception</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool()
 * @model
 * @generated
 */
public interface LUWBufferPool extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Create Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Create Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Create Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType
	 * @see #setCreateType(BufferPoolType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_CreateType()
	 * @model
	 * @generated
	 */
	BufferPoolType getCreateType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getCreateType <em>Create Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Create Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType
	 * @see #getCreateType()
	 * @generated
	 */
	void setCreateType(BufferPoolType value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_Size()
	 * @model
	 * @generated
	 */
	int getSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(int value);

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
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_PageSize()
	 * @model default="FOUR_K"
	 * @generated
	 */
	PageSizeType getPageSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPageSize <em>Page Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Size</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType
	 * @see #getPageSize()
	 * @generated
	 */
	void setPageSize(PageSizeType value);

	/**
	 * Returns the value of the '<em><b>Block Size</b></em>' attribute.
	 * The default value is <code>"32"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Block Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block Size</em>' attribute.
	 * @see #setBlockSize(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_BlockSize()
	 * @model default="32"
	 * @generated
	 */
	int getBlockSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getBlockSize <em>Block Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block Size</em>' attribute.
	 * @see #getBlockSize()
	 * @generated
	 */
	void setBlockSize(int value);

	/**
	 * Returns the value of the '<em><b>Num Block Pages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Num Block Pages</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Num Block Pages</em>' attribute.
	 * @see #setNumBlockPages(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_NumBlockPages()
	 * @model
	 * @generated
	 */
	int getNumBlockPages();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getNumBlockPages <em>Num Block Pages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Num Block Pages</em>' attribute.
	 * @see #getNumBlockPages()
	 * @generated
	 */
	void setNumBlockPages(int value);

	/**
	 * Returns the value of the '<em><b>Extended Storage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Storage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Storage</em>' attribute.
	 * @see #setExtendedStorage(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_ExtendedStorage()
	 * @model
	 * @generated
	 */
	boolean isExtendedStorage();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#isExtendedStorage <em>Extended Storage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Storage</em>' attribute.
	 * @see #isExtendedStorage()
	 * @generated
	 */
	void setExtendedStorage(boolean value);

	/**
	 * Returns the value of the '<em><b>Automatic</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Automatic</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Automatic</em>' attribute.
	 * @see #setAutomatic(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_Automatic()
	 * @model default="true"
	 * @generated
	 */
	boolean isAutomatic();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#isAutomatic <em>Automatic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Automatic</em>' attribute.
	 * @see #isAutomatic()
	 * @generated
	 */
	void setAutomatic(boolean value);

	/**
	 * Returns the value of the '<em><b>Table Spaces</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Spaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Spaces</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_TableSpaces()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getBufferPool
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace" opposite="bufferPool"
	 * @generated
	 */
	EList getTableSpaces();

	/**
	 * Returns the value of the '<em><b>Partitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partitions</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_Partitions()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getBufferPool
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition" opposite="bufferPool"
	 * @generated
	 */
	EList getPartitions();

	/**
	 * Returns the value of the '<em><b>Partition Group</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Group</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Group</em>' reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_PartitionGroup()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getBufferPool
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup" opposite="bufferPool"
	 * @generated
	 */
	EList getPartitionGroup();

	/**
	 * Returns the value of the '<em><b>Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getBufferpools <em>Bufferpools</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' reference.
	 * @see #setDatabase(LUWDatabase)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_Database()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getBufferpools
	 * @model opposite="bufferpools" required="true"
	 * @generated
	 */
	LUWDatabase getDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getDatabase <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' reference.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(LUWDatabase value);

	/**
	 * Returns the value of the '<em><b>Size Exception</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size Exception</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size Exception</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWBufferPool_SizeException()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getBufferPool
	 * @model type="org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException" opposite="bufferPool" containment="true"
	 * @generated
	 */
	EList getSizeException();

} // LUWBufferPool
