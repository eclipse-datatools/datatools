/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.UnitType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Space</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getTemporaryStorageTables <em>Temporary Storage Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getTablespaceType <em>Tablespace Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getManagementType <em>Management Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getExtentSize <em>Extent Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getPreFetchSize <em>Pre Fetch Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getOverhead <em>Overhead</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getTransferRate <em>Transfer Rate</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#isRecoverDroppedTableOn <em>Recover Dropped Table On</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getPageSize <em>Page Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#isAutoResize <em>Auto Resize</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getInitialSize <em>Initial Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getIncreaseSize <em>Increase Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getMaximumSize <em>Maximum Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getInitialSizeUnit <em>Initial Size Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getMaximumSizeUnit <em>Maximum Size Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getIncreaseSizeUnit <em>Increase Size Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getIncreasePercent <em>Increase Percent</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getFileSystemCaching <em>File System Caching</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getAverageSeekTime <em>Average Seek Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getRotationSpeed <em>Rotation Speed</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getTransfer <em>Transfer</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getSystemType <em>System Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getAverageTableSize <em>Average Table Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getExternalContainerCount <em>External Container Count</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#isInheritOverhead <em>Inherit Overhead</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#isInheritTransferate <em>Inherit Transferate</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#isRebalance <em>Rebalance</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getDataTag <em>Data Tag</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#isSuspendRebalance <em>Suspend Rebalance</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#isResumeRebalance <em>Resume Rebalance</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getContainers <em>Containers</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getBufferPool <em>Buffer Pool</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getIndexDataTables <em>Index Data Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getLOBDataTables <em>LOB Data Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getRegularDataTables <em>Regular Data Tables</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getDatabase <em>Database</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getLOBDataPartition <em>LOB Data Partition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getRegularDataPartition <em>Regular Data Partition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getIndexDataPartition <em>Index Data Partition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl#getStorageGroup <em>Storage Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWTableSpaceImpl extends SQLObjectImpl implements LUWTableSpace {
	/**
	 * The cached value of the '{@link #getTemporaryStorageTables() <em>Temporary Storage Tables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporaryStorageTables()
	 * @generated
	 * @ordered
	 */
	protected EList temporaryStorageTables;

	/**
	 * The default value of the '{@link #getTablespaceType() <em>Tablespace Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTablespaceType()
	 * @generated
	 * @ordered
	 */
	protected static final TableSpaceType TABLESPACE_TYPE_EDEFAULT = TableSpaceType.REGULAR_LITERAL;

	/**
	 * The cached value of the '{@link #getTablespaceType() <em>Tablespace Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTablespaceType()
	 * @generated
	 * @ordered
	 */
	protected TableSpaceType tablespaceType = TABLESPACE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getManagementType() <em>Management Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagementType()
	 * @generated
	 * @ordered
	 */
	protected static final ManagementType MANAGEMENT_TYPE_EDEFAULT = ManagementType.SYSTEM_MANAGED_LITERAL;

	/**
	 * The cached value of the '{@link #getManagementType() <em>Management Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManagementType()
	 * @generated
	 * @ordered
	 */
	protected ManagementType managementType = MANAGEMENT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getExtentSize() <em>Extent Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtentSize()
	 * @generated
	 * @ordered
	 */
	protected static final int EXTENT_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getExtentSize() <em>Extent Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtentSize()
	 * @generated
	 * @ordered
	 */
	protected int extentSize = EXTENT_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreFetchSize() <em>Pre Fetch Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreFetchSize()
	 * @generated
	 * @ordered
	 */
	protected static final int PRE_FETCH_SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPreFetchSize() <em>Pre Fetch Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreFetchSize()
	 * @generated
	 * @ordered
	 */
	protected int preFetchSize = PRE_FETCH_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOverhead() <em>Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverhead()
	 * @generated
	 * @ordered
	 */
	protected static final double OVERHEAD_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getOverhead() <em>Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOverhead()
	 * @generated
	 * @ordered
	 */
	protected double overhead = OVERHEAD_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransferRate() <em>Transfer Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransferRate()
	 * @generated
	 * @ordered
	 */
	protected static final double TRANSFER_RATE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTransferRate() <em>Transfer Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransferRate()
	 * @generated
	 * @ordered
	 */
	protected double transferRate = TRANSFER_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isRecoverDroppedTableOn() <em>Recover Dropped Table On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecoverDroppedTableOn()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RECOVER_DROPPED_TABLE_ON_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRecoverDroppedTableOn() <em>Recover Dropped Table On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecoverDroppedTableOn()
	 * @generated
	 * @ordered
	 */
	protected boolean recoverDroppedTableOn = RECOVER_DROPPED_TABLE_ON_EDEFAULT;

	/**
	 * The default value of the '{@link #getPageSize() <em>Page Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageSize()
	 * @generated
	 * @ordered
	 */
	protected static final PageSizeType PAGE_SIZE_EDEFAULT = PageSizeType.FOUR_K_LITERAL;

	/**
	 * The cached value of the '{@link #getPageSize() <em>Page Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageSize()
	 * @generated
	 * @ordered
	 */
	protected PageSizeType pageSize = PAGE_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final long SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected long size = SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAutoResize() <em>Auto Resize</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoResize()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTO_RESIZE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAutoResize() <em>Auto Resize</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoResize()
	 * @generated
	 * @ordered
	 */
	protected boolean autoResize = AUTO_RESIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getInitialSize() <em>Initial Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialSize()
	 * @generated
	 * @ordered
	 */
	protected static final long INITIAL_SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getInitialSize() <em>Initial Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialSize()
	 * @generated
	 * @ordered
	 */
	protected long initialSize = INITIAL_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncreaseSize() <em>Increase Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncreaseSize()
	 * @generated
	 * @ordered
	 */
	protected static final long INCREASE_SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getIncreaseSize() <em>Increase Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncreaseSize()
	 * @generated
	 * @ordered
	 */
	protected long increaseSize = INCREASE_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumSize() <em>Maximum Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumSize()
	 * @generated
	 * @ordered
	 */
	protected static final long MAXIMUM_SIZE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMaximumSize() <em>Maximum Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumSize()
	 * @generated
	 * @ordered
	 */
	protected long maximumSize = MAXIMUM_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getInitialSizeUnit() <em>Initial Size Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialSizeUnit()
	 * @generated
	 * @ordered
	 */
	protected static final UnitType INITIAL_SIZE_UNIT_EDEFAULT = UnitType.K_LITERAL;

	/**
	 * The cached value of the '{@link #getInitialSizeUnit() <em>Initial Size Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialSizeUnit()
	 * @generated
	 * @ordered
	 */
	protected UnitType initialSizeUnit = INITIAL_SIZE_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumSizeUnit() <em>Maximum Size Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumSizeUnit()
	 * @generated
	 * @ordered
	 */
	protected static final UnitType MAXIMUM_SIZE_UNIT_EDEFAULT = UnitType.K_LITERAL;

	/**
	 * The cached value of the '{@link #getMaximumSizeUnit() <em>Maximum Size Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumSizeUnit()
	 * @generated
	 * @ordered
	 */
	protected UnitType maximumSizeUnit = MAXIMUM_SIZE_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncreaseSizeUnit() <em>Increase Size Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncreaseSizeUnit()
	 * @generated
	 * @ordered
	 */
	protected static final UnitType INCREASE_SIZE_UNIT_EDEFAULT = UnitType.K_LITERAL;

	/**
	 * The cached value of the '{@link #getIncreaseSizeUnit() <em>Increase Size Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncreaseSizeUnit()
	 * @generated
	 * @ordered
	 */
	protected UnitType increaseSizeUnit = INCREASE_SIZE_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncreasePercent() <em>Increase Percent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncreasePercent()
	 * @generated
	 * @ordered
	 */
	protected static final int INCREASE_PERCENT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIncreasePercent() <em>Increase Percent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncreasePercent()
	 * @generated
	 * @ordered
	 */
	protected int increasePercent = INCREASE_PERCENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileSystemCaching() <em>File System Caching</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSystemCaching()
	 * @generated
	 * @ordered
	 */
	protected static final FileSystemCachingType FILE_SYSTEM_CACHING_EDEFAULT = FileSystemCachingType.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getFileSystemCaching() <em>File System Caching</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSystemCaching()
	 * @generated
	 * @ordered
	 */
	protected FileSystemCachingType fileSystemCaching = FILE_SYSTEM_CACHING_EDEFAULT;

	/**
	 * The default value of the '{@link #getAverageSeekTime() <em>Average Seek Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAverageSeekTime()
	 * @generated
	 * @ordered
	 */
	protected static final double AVERAGE_SEEK_TIME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getAverageSeekTime() <em>Average Seek Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAverageSeekTime()
	 * @generated
	 * @ordered
	 */
	protected double averageSeekTime = AVERAGE_SEEK_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getRotationSpeed() <em>Rotation Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRotationSpeed()
	 * @generated
	 * @ordered
	 */
	protected static final int ROTATION_SPEED_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRotationSpeed() <em>Rotation Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRotationSpeed()
	 * @generated
	 * @ordered
	 */
	protected int rotationSpeed = ROTATION_SPEED_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransfer() <em>Transfer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransfer()
	 * @generated
	 * @ordered
	 */
	protected static final double TRANSFER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTransfer() <em>Transfer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransfer()
	 * @generated
	 * @ordered
	 */
	protected double transfer = TRANSFER_EDEFAULT;

	/**
	 * The default value of the '{@link #getSystemType() <em>System Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemType()
	 * @generated
	 * @ordered
	 */
	protected static final SystemType SYSTEM_TYPE_EDEFAULT = SystemType.SATA_LITERAL;

	/**
	 * The cached value of the '{@link #getSystemType() <em>System Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemType()
	 * @generated
	 * @ordered
	 */
	protected SystemType systemType = SYSTEM_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getAverageTableSize() <em>Average Table Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAverageTableSize()
	 * @generated
	 * @ordered
	 */
	protected static final AverageTableSizeType AVERAGE_TABLE_SIZE_EDEFAULT = AverageTableSizeType.LESS_THAN_50MB_LITERAL;

	/**
	 * The cached value of the '{@link #getAverageTableSize() <em>Average Table Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAverageTableSize()
	 * @generated
	 * @ordered
	 */
	protected AverageTableSizeType averageTableSize = AVERAGE_TABLE_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getExternalContainerCount() <em>External Container Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalContainerCount()
	 * @generated
	 * @ordered
	 */
	protected static final int EXTERNAL_CONTAINER_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getExternalContainerCount() <em>External Container Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalContainerCount()
	 * @generated
	 * @ordered
	 */
	protected int externalContainerCount = EXTERNAL_CONTAINER_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #isInheritOverhead() <em>Inherit Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInheritOverhead()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INHERIT_OVERHEAD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInheritOverhead() <em>Inherit Overhead</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInheritOverhead()
	 * @generated
	 * @ordered
	 */
	protected boolean inheritOverhead = INHERIT_OVERHEAD_EDEFAULT;

	/**
	 * The default value of the '{@link #isInheritTransferate() <em>Inherit Transferate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInheritTransferate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INHERIT_TRANSFERATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInheritTransferate() <em>Inherit Transferate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInheritTransferate()
	 * @generated
	 * @ordered
	 */
	protected boolean inheritTransferate = INHERIT_TRANSFERATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isRebalance() <em>Rebalance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRebalance()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REBALANCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRebalance() <em>Rebalance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRebalance()
	 * @generated
	 * @ordered
	 */
	protected boolean rebalance = REBALANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataTag() <em>Data Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataTag()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_TAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDataTag() <em>Data Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataTag()
	 * @generated
	 * @ordered
	 */
	protected String dataTag = DATA_TAG_EDEFAULT;

	/**
	 * The default value of the '{@link #isSuspendRebalance() <em>Suspend Rebalance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSuspendRebalance()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUSPEND_REBALANCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSuspendRebalance() <em>Suspend Rebalance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSuspendRebalance()
	 * @generated
	 * @ordered
	 */
	protected boolean suspendRebalance = SUSPEND_REBALANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #isResumeRebalance() <em>Resume Rebalance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResumeRebalance()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RESUME_REBALANCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isResumeRebalance() <em>Resume Rebalance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isResumeRebalance()
	 * @generated
	 * @ordered
	 */
	protected boolean resumeRebalance = RESUME_REBALANCE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected LUWPartitionGroup group;

	/**
	 * The cached value of the '{@link #getContainers() <em>Containers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainers()
	 * @generated
	 * @ordered
	 */
	protected EList containers;

	/**
	 * The cached value of the '{@link #getBufferPool() <em>Buffer Pool</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBufferPool()
	 * @generated
	 * @ordered
	 */
	protected LUWBufferPool bufferPool;

	/**
	 * The cached value of the '{@link #getIndexDataTables() <em>Index Data Tables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexDataTables()
	 * @generated
	 * @ordered
	 */
	protected EList indexDataTables;

	/**
	 * The cached value of the '{@link #getLOBDataTables() <em>LOB Data Tables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLOBDataTables()
	 * @generated
	 * @ordered
	 */
	protected EList lobDataTables;

	/**
	 * The cached value of the '{@link #getRegularDataTables() <em>Regular Data Tables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegularDataTables()
	 * @generated
	 * @ordered
	 */
	protected EList regularDataTables;

	/**
	 * The cached value of the '{@link #getDatabase() <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabase()
	 * @generated
	 * @ordered
	 */
	protected LUWDatabase database;

	/**
	 * The cached value of the '{@link #getLOBDataPartition() <em>LOB Data Partition</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLOBDataPartition()
	 * @generated
	 * @ordered
	 */
	protected EList lobDataPartition;

	/**
	 * The cached value of the '{@link #getRegularDataPartition() <em>Regular Data Partition</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegularDataPartition()
	 * @generated
	 * @ordered
	 */
	protected EList regularDataPartition;

	/**
	 * The cached value of the '{@link #getIndexes() <em>Indexes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexes()
	 * @generated
	 * @ordered
	 */
	protected EList indexes;

	/**
	 * The cached value of the '{@link #getIndexDataPartition() <em>Index Data Partition</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexDataPartition()
	 * @generated
	 * @ordered
	 */
	protected EList indexDataPartition;

	/**
	 * The cached value of the '{@link #getStorageGroup() <em>Storage Group</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStorageGroup()
	 * @generated
	 * @ordered
	 */
	protected LUWStorageGroup storageGroup;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWTableSpaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_TABLE_SPACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTemporaryStorageTables() {
		if (temporaryStorageTables == null) {
			temporaryStorageTables = new EObjectWithInverseResolvingEList(LUWTemporaryStorageTable.class, this, LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES, LUWPackage.LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE);
		}
		return temporaryStorageTables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpaceType getTablespaceType() {
		return tablespaceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTablespaceType(TableSpaceType newTablespaceType) {
		TableSpaceType oldTablespaceType = tablespaceType;
		tablespaceType = newTablespaceType == null ? TABLESPACE_TYPE_EDEFAULT : newTablespaceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__TABLESPACE_TYPE, oldTablespaceType, tablespaceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManagementType getManagementType() {
		return managementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setManagementType(ManagementType newManagementType) {
		ManagementType oldManagementType = managementType;
		managementType = newManagementType == null ? MANAGEMENT_TYPE_EDEFAULT : newManagementType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__MANAGEMENT_TYPE, oldManagementType, managementType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getExtentSize() {
		return extentSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtentSize(int newExtentSize) {
		int oldExtentSize = extentSize;
		extentSize = newExtentSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__EXTENT_SIZE, oldExtentSize, extentSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPreFetchSize() {
		return preFetchSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreFetchSize(int newPreFetchSize) {
		int oldPreFetchSize = preFetchSize;
		preFetchSize = newPreFetchSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__PRE_FETCH_SIZE, oldPreFetchSize, preFetchSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getOverhead() {
		return overhead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOverhead(double newOverhead) {
		double oldOverhead = overhead;
		overhead = newOverhead;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__OVERHEAD, oldOverhead, overhead));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTransferRate() {
		return transferRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransferRate(double newTransferRate) {
		double oldTransferRate = transferRate;
		transferRate = newTransferRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__TRANSFER_RATE, oldTransferRate, transferRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRecoverDroppedTableOn() {
		return recoverDroppedTableOn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecoverDroppedTableOn(boolean newRecoverDroppedTableOn) {
		boolean oldRecoverDroppedTableOn = recoverDroppedTableOn;
		recoverDroppedTableOn = newRecoverDroppedTableOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__RECOVER_DROPPED_TABLE_ON, oldRecoverDroppedTableOn, recoverDroppedTableOn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PageSizeType getPageSize() {
		return pageSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageSize(PageSizeType newPageSize) {
		PageSizeType oldPageSize = pageSize;
		pageSize = newPageSize == null ? PAGE_SIZE_EDEFAULT : newPageSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__PAGE_SIZE, oldPageSize, pageSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(long newSize) {
		long oldSize = size;
		size = newSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutoResize() {
		return autoResize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutoResize(boolean newAutoResize) {
		boolean oldAutoResize = autoResize;
		autoResize = newAutoResize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__AUTO_RESIZE, oldAutoResize, autoResize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getInitialSize() {
		return initialSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialSize(long newInitialSize) {
		long oldInitialSize = initialSize;
		initialSize = newInitialSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE, oldInitialSize, initialSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getIncreaseSize() {
		return increaseSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncreaseSize(long newIncreaseSize) {
		long oldIncreaseSize = increaseSize;
		increaseSize = newIncreaseSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE, oldIncreaseSize, increaseSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getMaximumSize() {
		return maximumSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumSize(long newMaximumSize) {
		long oldMaximumSize = maximumSize;
		maximumSize = newMaximumSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE, oldMaximumSize, maximumSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitType getInitialSizeUnit() {
		return initialSizeUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialSizeUnit(UnitType newInitialSizeUnit) {
		UnitType oldInitialSizeUnit = initialSizeUnit;
		initialSizeUnit = newInitialSizeUnit == null ? INITIAL_SIZE_UNIT_EDEFAULT : newInitialSizeUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE_UNIT, oldInitialSizeUnit, initialSizeUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitType getMaximumSizeUnit() {
		return maximumSizeUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumSizeUnit(UnitType newMaximumSizeUnit) {
		UnitType oldMaximumSizeUnit = maximumSizeUnit;
		maximumSizeUnit = newMaximumSizeUnit == null ? MAXIMUM_SIZE_UNIT_EDEFAULT : newMaximumSizeUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE_UNIT, oldMaximumSizeUnit, maximumSizeUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnitType getIncreaseSizeUnit() {
		return increaseSizeUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncreaseSizeUnit(UnitType newIncreaseSizeUnit) {
		UnitType oldIncreaseSizeUnit = increaseSizeUnit;
		increaseSizeUnit = newIncreaseSizeUnit == null ? INCREASE_SIZE_UNIT_EDEFAULT : newIncreaseSizeUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE_UNIT, oldIncreaseSizeUnit, increaseSizeUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIncreasePercent() {
		return increasePercent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncreasePercent(int newIncreasePercent) {
		int oldIncreasePercent = increasePercent;
		increasePercent = newIncreasePercent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__INCREASE_PERCENT, oldIncreasePercent, increasePercent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileSystemCachingType getFileSystemCaching() {
		return fileSystemCaching;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileSystemCaching(FileSystemCachingType newFileSystemCaching) {
		FileSystemCachingType oldFileSystemCaching = fileSystemCaching;
		fileSystemCaching = newFileSystemCaching == null ? FILE_SYSTEM_CACHING_EDEFAULT : newFileSystemCaching;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__FILE_SYSTEM_CACHING, oldFileSystemCaching, fileSystemCaching));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getAverageSeekTime() {
		return averageSeekTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAverageSeekTime(double newAverageSeekTime) {
		double oldAverageSeekTime = averageSeekTime;
		averageSeekTime = newAverageSeekTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__AVERAGE_SEEK_TIME, oldAverageSeekTime, averageSeekTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRotationSpeed() {
		return rotationSpeed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRotationSpeed(int newRotationSpeed) {
		int oldRotationSpeed = rotationSpeed;
		rotationSpeed = newRotationSpeed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__ROTATION_SPEED, oldRotationSpeed, rotationSpeed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTransfer() {
		return transfer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransfer(double newTransfer) {
		double oldTransfer = transfer;
		transfer = newTransfer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__TRANSFER, oldTransfer, transfer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemType getSystemType() {
		return systemType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystemType(SystemType newSystemType) {
		SystemType oldSystemType = systemType;
		systemType = newSystemType == null ? SYSTEM_TYPE_EDEFAULT : newSystemType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__SYSTEM_TYPE, oldSystemType, systemType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AverageTableSizeType getAverageTableSize() {
		return averageTableSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAverageTableSize(AverageTableSizeType newAverageTableSize) {
		AverageTableSizeType oldAverageTableSize = averageTableSize;
		averageTableSize = newAverageTableSize == null ? AVERAGE_TABLE_SIZE_EDEFAULT : newAverageTableSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__AVERAGE_TABLE_SIZE, oldAverageTableSize, averageTableSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getExternalContainerCount() {
		return externalContainerCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalContainerCount(int newExternalContainerCount) {
		int oldExternalContainerCount = externalContainerCount;
		externalContainerCount = newExternalContainerCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__EXTERNAL_CONTAINER_COUNT, oldExternalContainerCount, externalContainerCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInheritOverhead() {
		return inheritOverhead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInheritOverhead(boolean newInheritOverhead) {
		boolean oldInheritOverhead = inheritOverhead;
		inheritOverhead = newInheritOverhead;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__INHERIT_OVERHEAD, oldInheritOverhead, inheritOverhead));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInheritTransferate() {
		return inheritTransferate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInheritTransferate(boolean newInheritTransferate) {
		boolean oldInheritTransferate = inheritTransferate;
		inheritTransferate = newInheritTransferate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__INHERIT_TRANSFERATE, oldInheritTransferate, inheritTransferate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRebalance() {
		return rebalance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRebalance(boolean newRebalance) {
		boolean oldRebalance = rebalance;
		rebalance = newRebalance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__REBALANCE, oldRebalance, rebalance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataTag() {
		return dataTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataTag(String newDataTag) {
		String oldDataTag = dataTag;
		dataTag = newDataTag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__DATA_TAG, oldDataTag, dataTag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSuspendRebalance() {
		return suspendRebalance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuspendRebalance(boolean newSuspendRebalance) {
		boolean oldSuspendRebalance = suspendRebalance;
		suspendRebalance = newSuspendRebalance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__SUSPEND_REBALANCE, oldSuspendRebalance, suspendRebalance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isResumeRebalance() {
		return resumeRebalance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResumeRebalance(boolean newResumeRebalance) {
		boolean oldResumeRebalance = resumeRebalance;
		resumeRebalance = newResumeRebalance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__RESUME_REBALANCE, oldResumeRebalance, resumeRebalance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionGroup getGroup() {
		if (group != null && group.eIsProxy()) {
			InternalEObject oldGroup = (InternalEObject)group;
			group = (LUWPartitionGroup)eResolveProxy(oldGroup);
			if (group != oldGroup) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_TABLE_SPACE__GROUP, oldGroup, group));
			}
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWPartitionGroup basicGetGroup() {
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroup(LUWPartitionGroup newGroup, NotificationChain msgs) {
		LUWPartitionGroup oldGroup = group;
		group = newGroup;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__GROUP, oldGroup, newGroup);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(LUWPartitionGroup newGroup) {
		if (newGroup != group) {
			NotificationChain msgs = null;
			if (group != null)
				msgs = ((InternalEObject)group).eInverseRemove(this, LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES, LUWPartitionGroup.class, msgs);
			if (newGroup != null)
				msgs = ((InternalEObject)newGroup).eInverseAdd(this, LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES, LUWPartitionGroup.class, msgs);
			msgs = basicSetGroup(newGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__GROUP, newGroup, newGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getContainers() {
		if (containers == null) {
			containers = new EObjectContainmentWithInverseEList(LUWDatabaseContainer.class, this, LUWPackage.LUW_TABLE_SPACE__CONTAINERS, LUWPackage.LUW_DATABASE_CONTAINER__TABLE_SPACE);
		}
		return containers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWBufferPool getBufferPool() {
		if (bufferPool != null && bufferPool.eIsProxy()) {
			InternalEObject oldBufferPool = (InternalEObject)bufferPool;
			bufferPool = (LUWBufferPool)eResolveProxy(oldBufferPool);
			if (bufferPool != oldBufferPool) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL, oldBufferPool, bufferPool));
			}
		}
		return bufferPool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWBufferPool basicGetBufferPool() {
		return bufferPool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBufferPool(LUWBufferPool newBufferPool, NotificationChain msgs) {
		LUWBufferPool oldBufferPool = bufferPool;
		bufferPool = newBufferPool;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL, oldBufferPool, newBufferPool);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBufferPool(LUWBufferPool newBufferPool) {
		if (newBufferPool != bufferPool) {
			NotificationChain msgs = null;
			if (bufferPool != null)
				msgs = ((InternalEObject)bufferPool).eInverseRemove(this, LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES, LUWBufferPool.class, msgs);
			if (newBufferPool != null)
				msgs = ((InternalEObject)newBufferPool).eInverseAdd(this, LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES, LUWBufferPool.class, msgs);
			msgs = basicSetBufferPool(newBufferPool, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL, newBufferPool, newBufferPool));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRegularDataTables() {
		if (regularDataTables == null) {
			regularDataTables = new EObjectWithInverseResolvingEList(LUWStorageTable.class, this, LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES, LUWPackage.LUW_STORAGE_TABLE__REGULAR_DATA_TABLE_SPACE);
		}
		return regularDataTables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabase getDatabase() {
		if (database != null && database.eIsProxy()) {
			InternalEObject oldDatabase = (InternalEObject)database;
			database = (LUWDatabase)eResolveProxy(oldDatabase);
			if (database != oldDatabase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_TABLE_SPACE__DATABASE, oldDatabase, database));
			}
		}
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWDatabase basicGetDatabase() {
		return database;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDatabase(LUWDatabase newDatabase, NotificationChain msgs) {
		LUWDatabase oldDatabase = database;
		database = newDatabase;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__DATABASE, oldDatabase, newDatabase);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabase(LUWDatabase newDatabase) {
		if (newDatabase != database) {
			NotificationChain msgs = null;
			if (database != null)
				msgs = ((InternalEObject)database).eInverseRemove(this, LUWPackage.LUW_DATABASE__TABLESPACES, LUWDatabase.class, msgs);
			if (newDatabase != null)
				msgs = ((InternalEObject)newDatabase).eInverseAdd(this, LUWPackage.LUW_DATABASE__TABLESPACES, LUWDatabase.class, msgs);
			msgs = basicSetDatabase(newDatabase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__DATABASE, newDatabase, newDatabase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLOBDataPartition() {
		if (lobDataPartition == null) {
			lobDataPartition = new EObjectWithInverseResolvingEList(LUWDataPartition.class, this, LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION, LUWPackage.LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE);
		}
		return lobDataPartition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRegularDataPartition() {
		if (regularDataPartition == null) {
			regularDataPartition = new EObjectWithInverseResolvingEList(LUWDataPartition.class, this, LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION, LUWPackage.LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE);
		}
		return regularDataPartition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIndexes() {
		if (indexes == null) {
			indexes = new EObjectWithInverseResolvingEList(LUWIndex.class, this, LUWPackage.LUW_TABLE_SPACE__INDEXES, LUWPackage.LUW_INDEX__TABLESPACE);
		}
		return indexes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIndexDataPartition() {
		if (indexDataPartition == null) {
			indexDataPartition = new EObjectWithInverseResolvingEList(LUWDataPartition.class, this, LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION, LUWPackage.LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE);
		}
		return indexDataPartition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageGroup getStorageGroup() {
		if (storageGroup != null && storageGroup.eIsProxy()) {
			InternalEObject oldStorageGroup = (InternalEObject)storageGroup;
			storageGroup = (LUWStorageGroup)eResolveProxy(oldStorageGroup);
			if (storageGroup != oldStorageGroup) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP, oldStorageGroup, storageGroup));
			}
		}
		return storageGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWStorageGroup basicGetStorageGroup() {
		return storageGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStorageGroup(LUWStorageGroup newStorageGroup, NotificationChain msgs) {
		LUWStorageGroup oldStorageGroup = storageGroup;
		storageGroup = newStorageGroup;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP, oldStorageGroup, newStorageGroup);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStorageGroup(LUWStorageGroup newStorageGroup) {
		if (newStorageGroup != storageGroup) {
			NotificationChain msgs = null;
			if (storageGroup != null)
				msgs = ((InternalEObject)storageGroup).eInverseRemove(this, LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES, LUWStorageGroup.class, msgs);
			if (newStorageGroup != null)
				msgs = ((InternalEObject)newStorageGroup).eInverseAdd(this, LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES, LUWStorageGroup.class, msgs);
			msgs = basicSetStorageGroup(newStorageGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP, newStorageGroup, newStorageGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIndexDataTables() {
		if (indexDataTables == null) {
			indexDataTables = new EObjectWithInverseResolvingEList(LUWStorageTable.class, this, LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES, LUWPackage.LUW_STORAGE_TABLE__INDEX_DATA_TABLE_SPACE);
		}
		return indexDataTables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLOBDataTables() {
		if (lobDataTables == null) {
			lobDataTables = new EObjectWithInverseResolvingEList(LUWStorageTable.class, this, LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES, LUWPackage.LUW_STORAGE_TABLE__LOB_DATA_TABLE_SPACE);
		}
		return lobDataTables;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getTables() 
	{
		List tableList = new ArrayList();
		tableList.addAll( getRegularDataTables() );
		tableList.addAll( getIndexDataTables() );
		tableList.addAll( getLOBDataTables() );
		return tableList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES:
				return ((InternalEList)getTemporaryStorageTables()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__GROUP:
				if (group != null)
					msgs = ((InternalEObject)group).eInverseRemove(this, LUWPackage.LUW_PARTITION_GROUP__TABLE_SPACES, LUWPartitionGroup.class, msgs);
				return basicSetGroup((LUWPartitionGroup)otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__CONTAINERS:
				return ((InternalEList)getContainers()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL:
				if (bufferPool != null)
					msgs = ((InternalEObject)bufferPool).eInverseRemove(this, LUWPackage.LUW_BUFFER_POOL__TABLE_SPACES, LUWBufferPool.class, msgs);
				return basicSetBufferPool((LUWBufferPool)otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES:
				return ((InternalEList)getIndexDataTables()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES:
				return ((InternalEList)getLOBDataTables()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES:
				return ((InternalEList)getRegularDataTables()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__DATABASE:
				if (database != null)
					msgs = ((InternalEObject)database).eInverseRemove(this, LUWPackage.LUW_DATABASE__TABLESPACES, LUWDatabase.class, msgs);
				return basicSetDatabase((LUWDatabase)otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION:
				return ((InternalEList)getLOBDataPartition()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION:
				return ((InternalEList)getRegularDataPartition()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__INDEXES:
				return ((InternalEList)getIndexes()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION:
				return ((InternalEList)getIndexDataPartition()).basicAdd(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP:
				if (storageGroup != null)
					msgs = ((InternalEObject)storageGroup).eInverseRemove(this, LUWPackage.LUW_STORAGE_GROUP__TABLE_SPACES, LUWStorageGroup.class, msgs);
				return basicSetStorageGroup((LUWStorageGroup)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES:
				return ((InternalEList)getTemporaryStorageTables()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__GROUP:
				return basicSetGroup(null, msgs);
			case LUWPackage.LUW_TABLE_SPACE__CONTAINERS:
				return ((InternalEList)getContainers()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL:
				return basicSetBufferPool(null, msgs);
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES:
				return ((InternalEList)getIndexDataTables()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES:
				return ((InternalEList)getLOBDataTables()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES:
				return ((InternalEList)getRegularDataTables()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__DATABASE:
				return basicSetDatabase(null, msgs);
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION:
				return ((InternalEList)getLOBDataPartition()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION:
				return ((InternalEList)getRegularDataPartition()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__INDEXES:
				return ((InternalEList)getIndexes()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION:
				return ((InternalEList)getIndexDataPartition()).basicRemove(otherEnd, msgs);
			case LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP:
				return basicSetStorageGroup(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES:
				return getTemporaryStorageTables();
			case LUWPackage.LUW_TABLE_SPACE__TABLESPACE_TYPE:
				return getTablespaceType();
			case LUWPackage.LUW_TABLE_SPACE__MANAGEMENT_TYPE:
				return getManagementType();
			case LUWPackage.LUW_TABLE_SPACE__EXTENT_SIZE:
				return new Integer(getExtentSize());
			case LUWPackage.LUW_TABLE_SPACE__PRE_FETCH_SIZE:
				return new Integer(getPreFetchSize());
			case LUWPackage.LUW_TABLE_SPACE__OVERHEAD:
				return new Double(getOverhead());
			case LUWPackage.LUW_TABLE_SPACE__TRANSFER_RATE:
				return new Double(getTransferRate());
			case LUWPackage.LUW_TABLE_SPACE__RECOVER_DROPPED_TABLE_ON:
				return isRecoverDroppedTableOn() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_TABLE_SPACE__PAGE_SIZE:
				return getPageSize();
			case LUWPackage.LUW_TABLE_SPACE__SIZE:
				return new Long(getSize());
			case LUWPackage.LUW_TABLE_SPACE__AUTO_RESIZE:
				return isAutoResize() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE:
				return new Long(getInitialSize());
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE:
				return new Long(getIncreaseSize());
			case LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE:
				return new Long(getMaximumSize());
			case LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE_UNIT:
				return getInitialSizeUnit();
			case LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE_UNIT:
				return getMaximumSizeUnit();
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE_UNIT:
				return getIncreaseSizeUnit();
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_PERCENT:
				return new Integer(getIncreasePercent());
			case LUWPackage.LUW_TABLE_SPACE__FILE_SYSTEM_CACHING:
				return getFileSystemCaching();
			case LUWPackage.LUW_TABLE_SPACE__AVERAGE_SEEK_TIME:
				return new Double(getAverageSeekTime());
			case LUWPackage.LUW_TABLE_SPACE__ROTATION_SPEED:
				return new Integer(getRotationSpeed());
			case LUWPackage.LUW_TABLE_SPACE__TRANSFER:
				return new Double(getTransfer());
			case LUWPackage.LUW_TABLE_SPACE__SYSTEM_TYPE:
				return getSystemType();
			case LUWPackage.LUW_TABLE_SPACE__AVERAGE_TABLE_SIZE:
				return getAverageTableSize();
			case LUWPackage.LUW_TABLE_SPACE__EXTERNAL_CONTAINER_COUNT:
				return new Integer(getExternalContainerCount());
			case LUWPackage.LUW_TABLE_SPACE__INHERIT_OVERHEAD:
				return isInheritOverhead() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_TABLE_SPACE__INHERIT_TRANSFERATE:
				return isInheritTransferate() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_TABLE_SPACE__REBALANCE:
				return isRebalance() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_TABLE_SPACE__DATA_TAG:
				return getDataTag();
			case LUWPackage.LUW_TABLE_SPACE__SUSPEND_REBALANCE:
				return isSuspendRebalance() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_TABLE_SPACE__RESUME_REBALANCE:
				return isResumeRebalance() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_TABLE_SPACE__GROUP:
				if (resolve) return getGroup();
				return basicGetGroup();
			case LUWPackage.LUW_TABLE_SPACE__CONTAINERS:
				return getContainers();
			case LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL:
				if (resolve) return getBufferPool();
				return basicGetBufferPool();
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES:
				return getIndexDataTables();
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES:
				return getLOBDataTables();
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES:
				return getRegularDataTables();
			case LUWPackage.LUW_TABLE_SPACE__DATABASE:
				if (resolve) return getDatabase();
				return basicGetDatabase();
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION:
				return getLOBDataPartition();
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION:
				return getRegularDataPartition();
			case LUWPackage.LUW_TABLE_SPACE__INDEXES:
				return getIndexes();
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION:
				return getIndexDataPartition();
			case LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP:
				if (resolve) return getStorageGroup();
				return basicGetStorageGroup();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES:
				getTemporaryStorageTables().clear();
				getTemporaryStorageTables().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__TABLESPACE_TYPE:
				setTablespaceType((TableSpaceType)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__MANAGEMENT_TYPE:
				setManagementType((ManagementType)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__EXTENT_SIZE:
				setExtentSize(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__PRE_FETCH_SIZE:
				setPreFetchSize(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__OVERHEAD:
				setOverhead(((Double)newValue).doubleValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__TRANSFER_RATE:
				setTransferRate(((Double)newValue).doubleValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__RECOVER_DROPPED_TABLE_ON:
				setRecoverDroppedTableOn(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__PAGE_SIZE:
				setPageSize((PageSizeType)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__SIZE:
				setSize(((Long)newValue).longValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__AUTO_RESIZE:
				setAutoResize(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE:
				setInitialSize(((Long)newValue).longValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE:
				setIncreaseSize(((Long)newValue).longValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE:
				setMaximumSize(((Long)newValue).longValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE_UNIT:
				setInitialSizeUnit((UnitType)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE_UNIT:
				setMaximumSizeUnit((UnitType)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE_UNIT:
				setIncreaseSizeUnit((UnitType)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_PERCENT:
				setIncreasePercent(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__FILE_SYSTEM_CACHING:
				setFileSystemCaching((FileSystemCachingType)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__AVERAGE_SEEK_TIME:
				setAverageSeekTime(((Double)newValue).doubleValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__ROTATION_SPEED:
				setRotationSpeed(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__TRANSFER:
				setTransfer(((Double)newValue).doubleValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__SYSTEM_TYPE:
				setSystemType((SystemType)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__AVERAGE_TABLE_SIZE:
				setAverageTableSize((AverageTableSizeType)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__EXTERNAL_CONTAINER_COUNT:
				setExternalContainerCount(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__INHERIT_OVERHEAD:
				setInheritOverhead(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__INHERIT_TRANSFERATE:
				setInheritTransferate(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__REBALANCE:
				setRebalance(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__DATA_TAG:
				setDataTag((String)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__SUSPEND_REBALANCE:
				setSuspendRebalance(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__RESUME_REBALANCE:
				setResumeRebalance(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_TABLE_SPACE__GROUP:
				setGroup((LUWPartitionGroup)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__CONTAINERS:
				getContainers().clear();
				getContainers().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL:
				setBufferPool((LUWBufferPool)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES:
				getIndexDataTables().clear();
				getIndexDataTables().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES:
				getLOBDataTables().clear();
				getLOBDataTables().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES:
				getRegularDataTables().clear();
				getRegularDataTables().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__DATABASE:
				setDatabase((LUWDatabase)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION:
				getLOBDataPartition().clear();
				getLOBDataPartition().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION:
				getRegularDataPartition().clear();
				getRegularDataPartition().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INDEXES:
				getIndexes().clear();
				getIndexes().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION:
				getIndexDataPartition().clear();
				getIndexDataPartition().addAll((Collection)newValue);
				return;
			case LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP:
				setStorageGroup((LUWStorageGroup)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES:
				getTemporaryStorageTables().clear();
				return;
			case LUWPackage.LUW_TABLE_SPACE__TABLESPACE_TYPE:
				setTablespaceType(TABLESPACE_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__MANAGEMENT_TYPE:
				setManagementType(MANAGEMENT_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__EXTENT_SIZE:
				setExtentSize(EXTENT_SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__PRE_FETCH_SIZE:
				setPreFetchSize(PRE_FETCH_SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__OVERHEAD:
				setOverhead(OVERHEAD_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__TRANSFER_RATE:
				setTransferRate(TRANSFER_RATE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__RECOVER_DROPPED_TABLE_ON:
				setRecoverDroppedTableOn(RECOVER_DROPPED_TABLE_ON_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__PAGE_SIZE:
				setPageSize(PAGE_SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__AUTO_RESIZE:
				setAutoResize(AUTO_RESIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE:
				setInitialSize(INITIAL_SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE:
				setIncreaseSize(INCREASE_SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE:
				setMaximumSize(MAXIMUM_SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE_UNIT:
				setInitialSizeUnit(INITIAL_SIZE_UNIT_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE_UNIT:
				setMaximumSizeUnit(MAXIMUM_SIZE_UNIT_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE_UNIT:
				setIncreaseSizeUnit(INCREASE_SIZE_UNIT_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_PERCENT:
				setIncreasePercent(INCREASE_PERCENT_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__FILE_SYSTEM_CACHING:
				setFileSystemCaching(FILE_SYSTEM_CACHING_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__AVERAGE_SEEK_TIME:
				setAverageSeekTime(AVERAGE_SEEK_TIME_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__ROTATION_SPEED:
				setRotationSpeed(ROTATION_SPEED_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__TRANSFER:
				setTransfer(TRANSFER_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__SYSTEM_TYPE:
				setSystemType(SYSTEM_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__AVERAGE_TABLE_SIZE:
				setAverageTableSize(AVERAGE_TABLE_SIZE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__EXTERNAL_CONTAINER_COUNT:
				setExternalContainerCount(EXTERNAL_CONTAINER_COUNT_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INHERIT_OVERHEAD:
				setInheritOverhead(INHERIT_OVERHEAD_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INHERIT_TRANSFERATE:
				setInheritTransferate(INHERIT_TRANSFERATE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__REBALANCE:
				setRebalance(REBALANCE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__DATA_TAG:
				setDataTag(DATA_TAG_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__SUSPEND_REBALANCE:
				setSuspendRebalance(SUSPEND_REBALANCE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__RESUME_REBALANCE:
				setResumeRebalance(RESUME_REBALANCE_EDEFAULT);
				return;
			case LUWPackage.LUW_TABLE_SPACE__GROUP:
				setGroup((LUWPartitionGroup)null);
				return;
			case LUWPackage.LUW_TABLE_SPACE__CONTAINERS:
				getContainers().clear();
				return;
			case LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL:
				setBufferPool((LUWBufferPool)null);
				return;
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES:
				getIndexDataTables().clear();
				return;
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES:
				getLOBDataTables().clear();
				return;
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES:
				getRegularDataTables().clear();
				return;
			case LUWPackage.LUW_TABLE_SPACE__DATABASE:
				setDatabase((LUWDatabase)null);
				return;
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION:
				getLOBDataPartition().clear();
				return;
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION:
				getRegularDataPartition().clear();
				return;
			case LUWPackage.LUW_TABLE_SPACE__INDEXES:
				getIndexes().clear();
				return;
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION:
				getIndexDataPartition().clear();
				return;
			case LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP:
				setStorageGroup((LUWStorageGroup)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LUWPackage.LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES:
				return temporaryStorageTables != null && !temporaryStorageTables.isEmpty();
			case LUWPackage.LUW_TABLE_SPACE__TABLESPACE_TYPE:
				return tablespaceType != TABLESPACE_TYPE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__MANAGEMENT_TYPE:
				return managementType != MANAGEMENT_TYPE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__EXTENT_SIZE:
				return extentSize != EXTENT_SIZE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__PRE_FETCH_SIZE:
				return preFetchSize != PRE_FETCH_SIZE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__OVERHEAD:
				return overhead != OVERHEAD_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__TRANSFER_RATE:
				return transferRate != TRANSFER_RATE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__RECOVER_DROPPED_TABLE_ON:
				return recoverDroppedTableOn != RECOVER_DROPPED_TABLE_ON_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__PAGE_SIZE:
				return pageSize != PAGE_SIZE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__SIZE:
				return size != SIZE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__AUTO_RESIZE:
				return autoResize != AUTO_RESIZE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE:
				return initialSize != INITIAL_SIZE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE:
				return increaseSize != INCREASE_SIZE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE:
				return maximumSize != MAXIMUM_SIZE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__INITIAL_SIZE_UNIT:
				return initialSizeUnit != INITIAL_SIZE_UNIT_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__MAXIMUM_SIZE_UNIT:
				return maximumSizeUnit != MAXIMUM_SIZE_UNIT_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_SIZE_UNIT:
				return increaseSizeUnit != INCREASE_SIZE_UNIT_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__INCREASE_PERCENT:
				return increasePercent != INCREASE_PERCENT_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__FILE_SYSTEM_CACHING:
				return fileSystemCaching != FILE_SYSTEM_CACHING_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__AVERAGE_SEEK_TIME:
				return averageSeekTime != AVERAGE_SEEK_TIME_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__ROTATION_SPEED:
				return rotationSpeed != ROTATION_SPEED_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__TRANSFER:
				return transfer != TRANSFER_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__SYSTEM_TYPE:
				return systemType != SYSTEM_TYPE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__AVERAGE_TABLE_SIZE:
				return averageTableSize != AVERAGE_TABLE_SIZE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__EXTERNAL_CONTAINER_COUNT:
				return externalContainerCount != EXTERNAL_CONTAINER_COUNT_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__INHERIT_OVERHEAD:
				return inheritOverhead != INHERIT_OVERHEAD_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__INHERIT_TRANSFERATE:
				return inheritTransferate != INHERIT_TRANSFERATE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__REBALANCE:
				return rebalance != REBALANCE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__DATA_TAG:
				return DATA_TAG_EDEFAULT == null ? dataTag != null : !DATA_TAG_EDEFAULT.equals(dataTag);
			case LUWPackage.LUW_TABLE_SPACE__SUSPEND_REBALANCE:
				return suspendRebalance != SUSPEND_REBALANCE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__RESUME_REBALANCE:
				return resumeRebalance != RESUME_REBALANCE_EDEFAULT;
			case LUWPackage.LUW_TABLE_SPACE__GROUP:
				return group != null;
			case LUWPackage.LUW_TABLE_SPACE__CONTAINERS:
				return containers != null && !containers.isEmpty();
			case LUWPackage.LUW_TABLE_SPACE__BUFFER_POOL:
				return bufferPool != null;
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_TABLES:
				return indexDataTables != null && !indexDataTables.isEmpty();
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_TABLES:
				return lobDataTables != null && !lobDataTables.isEmpty();
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_TABLES:
				return regularDataTables != null && !regularDataTables.isEmpty();
			case LUWPackage.LUW_TABLE_SPACE__DATABASE:
				return database != null;
			case LUWPackage.LUW_TABLE_SPACE__LOB_DATA_PARTITION:
				return lobDataPartition != null && !lobDataPartition.isEmpty();
			case LUWPackage.LUW_TABLE_SPACE__REGULAR_DATA_PARTITION:
				return regularDataPartition != null && !regularDataPartition.isEmpty();
			case LUWPackage.LUW_TABLE_SPACE__INDEXES:
				return indexes != null && !indexes.isEmpty();
			case LUWPackage.LUW_TABLE_SPACE__INDEX_DATA_PARTITION:
				return indexDataPartition != null && !indexDataPartition.isEmpty();
			case LUWPackage.LUW_TABLE_SPACE__STORAGE_GROUP:
				return storageGroup != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (tablespaceType: "); //$NON-NLS-1$
		result.append(tablespaceType);
		result.append(", managementType: "); //$NON-NLS-1$
		result.append(managementType);
		result.append(", extentSize: "); //$NON-NLS-1$
		result.append(extentSize);
		result.append(", preFetchSize: "); //$NON-NLS-1$
		result.append(preFetchSize);
		result.append(", overhead: "); //$NON-NLS-1$
		result.append(overhead);
		result.append(", transferRate: "); //$NON-NLS-1$
		result.append(transferRate);
		result.append(", recoverDroppedTableOn: "); //$NON-NLS-1$
		result.append(recoverDroppedTableOn);
		result.append(", pageSize: "); //$NON-NLS-1$
		result.append(pageSize);
		result.append(", size: "); //$NON-NLS-1$
		result.append(size);
		result.append(", autoResize: "); //$NON-NLS-1$
		result.append(autoResize);
		result.append(", initialSize: "); //$NON-NLS-1$
		result.append(initialSize);
		result.append(", increaseSize: "); //$NON-NLS-1$
		result.append(increaseSize);
		result.append(", maximumSize: "); //$NON-NLS-1$
		result.append(maximumSize);
		result.append(", initialSizeUnit: "); //$NON-NLS-1$
		result.append(initialSizeUnit);
		result.append(", maximumSizeUnit: "); //$NON-NLS-1$
		result.append(maximumSizeUnit);
		result.append(", increaseSizeUnit: "); //$NON-NLS-1$
		result.append(increaseSizeUnit);
		result.append(", increasePercent: "); //$NON-NLS-1$
		result.append(increasePercent);
		result.append(", fileSystemCaching: "); //$NON-NLS-1$
		result.append(fileSystemCaching);
		result.append(", averageSeekTime: "); //$NON-NLS-1$
		result.append(averageSeekTime);
		result.append(", rotationSpeed: "); //$NON-NLS-1$
		result.append(rotationSpeed);
		result.append(", transfer: "); //$NON-NLS-1$
		result.append(transfer);
		result.append(", systemType: "); //$NON-NLS-1$
		result.append(systemType);
		result.append(", averageTableSize: "); //$NON-NLS-1$
		result.append(averageTableSize);
		result.append(", externalContainerCount: "); //$NON-NLS-1$
		result.append(externalContainerCount);
		result.append(", inheritOverhead: "); //$NON-NLS-1$
		result.append(inheritOverhead);
		result.append(", inheritTransferate: "); //$NON-NLS-1$
		result.append(inheritTransferate);
		result.append(", rebalance: "); //$NON-NLS-1$
		result.append(rebalance);
		result.append(", dataTag: "); //$NON-NLS-1$
		result.append(dataTag);
		result.append(", suspendRebalance: "); //$NON-NLS-1$
		result.append(suspendRebalance);
		result.append(", resumeRebalance: "); //$NON-NLS-1$
		result.append(resumeRebalance);
		result.append(')');
		return result.toString();
	}

} //LUWTableSpaceImpl
