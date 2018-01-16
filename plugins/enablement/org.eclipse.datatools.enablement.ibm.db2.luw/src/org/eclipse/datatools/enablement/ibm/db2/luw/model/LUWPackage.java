package org.eclipse.datatools.enablement.ibm.db2.luw.model;


import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * DB2 LUW Model is the OO representation of the concepts defined in the: DB2 UDB for Linux, UNIX, Windows version 8.1 - SQL Reference
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFactory
 * @model kind="package"
 * @generated
 */
public interface LUWPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "luw"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org.eclipse.datatools.enablement.ibm.db2.luw.model.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "LUW"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LUWPackage eINSTANCE = org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionGroupImpl <em>Partition Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionGroupImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionGroup()
	 * @generated
	 */
	int LUW_PARTITION_GROUP = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__PARTITIONS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Table Spaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__TABLE_SPACES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Buffer Pool</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP__BUFFER_POOL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Partition Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_GROUP_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl <em>Table Space</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTableSpace()
	 * @generated
	 */
	int LUW_TABLE_SPACE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Temporary Storage Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tablespace Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__TABLESPACE_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Management Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__MANAGEMENT_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Extent Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__EXTENT_SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Pre Fetch Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__PRE_FETCH_SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Overhead</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__OVERHEAD = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Transfer Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__TRANSFER_RATE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Recover Dropped Table On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__RECOVER_DROPPED_TABLE_ON = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Page Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__PAGE_SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Auto Resize</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__AUTO_RESIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Initial Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INITIAL_SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Increase Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INCREASE_SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Maximum Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__MAXIMUM_SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Initial Size Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INITIAL_SIZE_UNIT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Maximum Size Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__MAXIMUM_SIZE_UNIT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Increase Size Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INCREASE_SIZE_UNIT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Increase Percent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INCREASE_PERCENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>File System Caching</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__FILE_SYSTEM_CACHING = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Average Seek Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__AVERAGE_SEEK_TIME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Rotation Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__ROTATION_SPEED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Transfer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__TRANSFER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>System Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__SYSTEM_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Average Table Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__AVERAGE_TABLE_SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>External Container Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__EXTERNAL_CONTAINER_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Inherit Overhead</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INHERIT_OVERHEAD = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Inherit Transferate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INHERIT_TRANSFERATE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Rebalance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__REBALANCE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Data Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__DATA_TAG = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 28;

	/**
	 * The feature id for the '<em><b>Suspend Rebalance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__SUSPEND_REBALANCE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 29;

	/**
	 * The feature id for the '<em><b>Resume Rebalance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__RESUME_REBALANCE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 30;

	/**
	 * The feature id for the '<em><b>Group</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__GROUP = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 31;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__CONTAINERS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 32;

	/**
	 * The feature id for the '<em><b>Buffer Pool</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__BUFFER_POOL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 33;

	/**
	 * The feature id for the '<em><b>Index Data Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INDEX_DATA_TABLES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 34;

	/**
	 * The feature id for the '<em><b>LOB Data Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__LOB_DATA_TABLES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 35;

	/**
	 * The feature id for the '<em><b>Regular Data Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__REGULAR_DATA_TABLES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 36;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 37;

	/**
	 * The feature id for the '<em><b>LOB Data Partition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__LOB_DATA_PARTITION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 38;

	/**
	 * The feature id for the '<em><b>Regular Data Partition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__REGULAR_DATA_PARTITION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 39;

	/**
	 * The feature id for the '<em><b>Indexes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INDEXES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 40;

	/**
	 * The feature id for the '<em><b>Index Data Partition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__INDEX_DATA_PARTITION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 41;

	/**
	 * The feature id for the '<em><b>Storage Group</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE__STORAGE_GROUP = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 42;

	/**
	 * The number of structural features of the '<em>Table Space</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_SPACE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 43;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl <em>Database Partition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDatabasePartition()
	 * @generated
	 */
	int LUW_DATABASE_PARTITION = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__NUMBER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Port Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__PORT_NUMBER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Host Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__HOST_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Switch Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__SWITCH_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Catalog Partition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__CATALOG_PARTITION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Group</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__GROUP = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Buffer Pool</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__BUFFER_POOL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__CONTAINERS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Size Exception</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION__SIZE_EXCEPTION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Database Partition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PARTITION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl <em>Database Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDatabaseContainer()
	 * @generated
	 */
	int LUW_DATABASE_CONTAINER = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Container Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__CONTAINER_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Size In Pages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__SIZE_IN_PAGES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Size Units</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__SIZE_UNITS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Table Space</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__TABLE_SPACE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER__PARTITIONS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Database Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_CONTAINER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAdminServerImpl <em>Admin Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAdminServerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWAdminServer()
	 * @generated
	 */
	int LUW_ADMIN_SERVER = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER__INSTANCES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Admin Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ADMIN_SERVER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl <em>Buffer Pool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWBufferPool()
	 * @generated
	 */
	int LUW_BUFFER_POOL = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Create Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__CREATE_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Page Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__PAGE_SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Block Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__BLOCK_SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Num Block Pages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__NUM_BLOCK_PAGES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Extended Storage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__EXTENDED_STORAGE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Automatic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__AUTOMATIC = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Table Spaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__TABLE_SPACES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__PARTITIONS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Partition Group</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__PARTITION_GROUP = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Size Exception</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL__SIZE_EXCEPTION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Buffer Pool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableImpl <em>Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTable()
	 * @generated
	 */
	int LUW_TABLE = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__EANNOTATIONS = DB2ModelPackage.DB2_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__NAME = DB2ModelPackage.DB2_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__DEPENDENCIES = DB2ModelPackage.DB2_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__DESCRIPTION = DB2ModelPackage.DB2_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__LABEL = DB2ModelPackage.DB2_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__COMMENTS = DB2ModelPackage.DB2_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__EXTENSIONS = DB2ModelPackage.DB2_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__PRIVILEGES = DB2ModelPackage.DB2_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__COLUMNS = DB2ModelPackage.DB2_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__SUPERTABLE = DB2ModelPackage.DB2_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__SUBTABLES = DB2ModelPackage.DB2_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__SCHEMA = DB2ModelPackage.DB2_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__UDT = DB2ModelPackage.DB2_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__TRIGGERS = DB2ModelPackage.DB2_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__INDEX = DB2ModelPackage.DB2_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__SELF_REF_COLUMN_GENERATION = DB2ModelPackage.DB2_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__INSERTABLE = DB2ModelPackage.DB2_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__UPDATABLE = DB2ModelPackage.DB2_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__CONSTRAINTS = DB2ModelPackage.DB2_TABLE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__REFERENCING_FOREIGN_KEYS = DB2ModelPackage.DB2_TABLE__REFERENCING_FOREIGN_KEYS;

	/**
	 * The feature id for the '<em><b>Data Capture</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__DATA_CAPTURE = DB2ModelPackage.DB2_TABLE__DATA_CAPTURE;

	/**
	 * The feature id for the '<em><b>Activate Row Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__ACTIVATE_ROW_ACCESS_CONTROL = DB2ModelPackage.DB2_TABLE__ACTIVATE_ROW_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Activate Column Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL = DB2ModelPackage.DB2_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Organize By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__ORGANIZE_BY = DB2ModelPackage.DB2_TABLE__ORGANIZE_BY;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__PACKAGES = DB2ModelPackage.DB2_TABLE__PACKAGES;

	/**
	 * The feature id for the '<em><b>Periods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__PERIODS = DB2ModelPackage.DB2_TABLE__PERIODS;

	/**
	 * The feature id for the '<em><b>History Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__HISTORY_TABLE = DB2ModelPackage.DB2_TABLE__HISTORY_TABLE;

	/**
	 * The feature id for the '<em><b>Temporal Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__TEMPORAL_TABLE = DB2ModelPackage.DB2_TABLE__TEMPORAL_TABLE;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__MASKS = DB2ModelPackage.DB2_TABLE__MASKS;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__PERMISSIONS = DB2ModelPackage.DB2_TABLE__PERMISSIONS;

	/**
	 * The feature id for the '<em><b>Value Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__VALUE_COMPRESSION = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Row Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__ROW_COMPRESSION = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Row Compression Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__ROW_COMPRESSION_EMPTY = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Compression Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__COMPRESSION_MODE = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__PARTITION_KEY = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Index Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__INDEX_DATA_TABLE_SPACE = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>LOB Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__LOB_DATA_TABLE_SPACE = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Regular Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__REGULAR_DATA_TABLE_SPACE = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Data Partitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__DATA_PARTITIONS = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Data Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__DATA_PARTITION_KEY = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>PCT Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__PCT_FREE = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Restrict On Drop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__RESTRICT_ON_DROP = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Partition Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__PARTITION_MODE = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Append Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__APPEND_MODE = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Log Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__LOG_MODE = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Lock Size Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__LOCK_SIZE_ROW = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__VOLATILE = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__OPTIONS = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Security Policy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE__SECURITY_POLICY = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 18;

	/**
	 * The number of structural features of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TABLE_FEATURE_COUNT = DB2ModelPackage.DB2_TABLE_FEATURE_COUNT + 19;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWViewImpl <em>View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWViewImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWView()
	 * @generated
	 */
	int LUW_VIEW = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__EANNOTATIONS = DB2ModelPackage.DB2_VIEW__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__NAME = DB2ModelPackage.DB2_VIEW__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__DEPENDENCIES = DB2ModelPackage.DB2_VIEW__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__DESCRIPTION = DB2ModelPackage.DB2_VIEW__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__LABEL = DB2ModelPackage.DB2_VIEW__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__COMMENTS = DB2ModelPackage.DB2_VIEW__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__EXTENSIONS = DB2ModelPackage.DB2_VIEW__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__PRIVILEGES = DB2ModelPackage.DB2_VIEW__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__COLUMNS = DB2ModelPackage.DB2_VIEW__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__SUPERTABLE = DB2ModelPackage.DB2_VIEW__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__SUBTABLES = DB2ModelPackage.DB2_VIEW__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__SCHEMA = DB2ModelPackage.DB2_VIEW__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__UDT = DB2ModelPackage.DB2_VIEW__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__TRIGGERS = DB2ModelPackage.DB2_VIEW__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__INDEX = DB2ModelPackage.DB2_VIEW__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__SELF_REF_COLUMN_GENERATION = DB2ModelPackage.DB2_VIEW__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__INSERTABLE = DB2ModelPackage.DB2_VIEW__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__UPDATABLE = DB2ModelPackage.DB2_VIEW__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__QUERY_EXPRESSION = DB2ModelPackage.DB2_VIEW__QUERY_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Check Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__CHECK_TYPE = DB2ModelPackage.DB2_VIEW__CHECK_TYPE;

	/**
	 * The feature id for the '<em><b>Operative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__OPERATIVE = DB2ModelPackage.DB2_VIEW__OPERATIVE;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__FEDERATED = DB2ModelPackage.DB2_VIEW_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Optimize Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW__OPTIMIZE_QUERY = DB2ModelPackage.DB2_VIEW_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_VIEW_FEATURE_COUNT = DB2ModelPackage.DB2_VIEW_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionKeyImpl <em>Partition Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionKeyImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionKey()
	 * @generated
	 */
	int LUW_PARTITION_KEY = 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Temporary Storage Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Partition Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__PARTITION_METHOD = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY__COLUMNS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Partition Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_KEY_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNicknameImpl <em>Nickname</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNicknameImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWNickname()
	 * @generated
	 */
	int LUW_NICKNAME = 9;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__EANNOTATIONS = LUW_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__NAME = LUW_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__DEPENDENCIES = LUW_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__DESCRIPTION = LUW_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__LABEL = LUW_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__COMMENTS = LUW_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__EXTENSIONS = LUW_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__PRIVILEGES = LUW_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__COLUMNS = LUW_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__SUPERTABLE = LUW_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__SUBTABLES = LUW_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__SCHEMA = LUW_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__UDT = LUW_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__TRIGGERS = LUW_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__INDEX = LUW_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__SELF_REF_COLUMN_GENERATION = LUW_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__INSERTABLE = LUW_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__UPDATABLE = LUW_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__CONSTRAINTS = LUW_TABLE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__REFERENCING_FOREIGN_KEYS = LUW_TABLE__REFERENCING_FOREIGN_KEYS;

	/**
	 * The feature id for the '<em><b>Data Capture</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__DATA_CAPTURE = LUW_TABLE__DATA_CAPTURE;

	/**
	 * The feature id for the '<em><b>Activate Row Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__ACTIVATE_ROW_ACCESS_CONTROL = LUW_TABLE__ACTIVATE_ROW_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Activate Column Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__ACTIVATE_COLUMN_ACCESS_CONTROL = LUW_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Organize By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__ORGANIZE_BY = LUW_TABLE__ORGANIZE_BY;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__PACKAGES = LUW_TABLE__PACKAGES;

	/**
	 * The feature id for the '<em><b>Periods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__PERIODS = LUW_TABLE__PERIODS;

	/**
	 * The feature id for the '<em><b>History Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__HISTORY_TABLE = LUW_TABLE__HISTORY_TABLE;

	/**
	 * The feature id for the '<em><b>Temporal Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__TEMPORAL_TABLE = LUW_TABLE__TEMPORAL_TABLE;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__MASKS = LUW_TABLE__MASKS;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__PERMISSIONS = LUW_TABLE__PERMISSIONS;

	/**
	 * The feature id for the '<em><b>Value Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__VALUE_COMPRESSION = LUW_TABLE__VALUE_COMPRESSION;

	/**
	 * The feature id for the '<em><b>Row Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__ROW_COMPRESSION = LUW_TABLE__ROW_COMPRESSION;

	/**
	 * The feature id for the '<em><b>Row Compression Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__ROW_COMPRESSION_EMPTY = LUW_TABLE__ROW_COMPRESSION_EMPTY;

	/**
	 * The feature id for the '<em><b>Compression Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__COMPRESSION_MODE = LUW_TABLE__COMPRESSION_MODE;

	/**
	 * The feature id for the '<em><b>Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__PARTITION_KEY = LUW_TABLE__PARTITION_KEY;

	/**
	 * The feature id for the '<em><b>Index Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__INDEX_DATA_TABLE_SPACE = LUW_TABLE__INDEX_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>LOB Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__LOB_DATA_TABLE_SPACE = LUW_TABLE__LOB_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>Regular Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__REGULAR_DATA_TABLE_SPACE = LUW_TABLE__REGULAR_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>Data Partitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__DATA_PARTITIONS = LUW_TABLE__DATA_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Data Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__DATA_PARTITION_KEY = LUW_TABLE__DATA_PARTITION_KEY;

	/**
	 * The feature id for the '<em><b>PCT Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__PCT_FREE = LUW_TABLE__PCT_FREE;

	/**
	 * The feature id for the '<em><b>Restrict On Drop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__RESTRICT_ON_DROP = LUW_TABLE__RESTRICT_ON_DROP;

	/**
	 * The feature id for the '<em><b>Partition Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__PARTITION_MODE = LUW_TABLE__PARTITION_MODE;

	/**
	 * The feature id for the '<em><b>Append Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__APPEND_MODE = LUW_TABLE__APPEND_MODE;

	/**
	 * The feature id for the '<em><b>Log Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__LOG_MODE = LUW_TABLE__LOG_MODE;

	/**
	 * The feature id for the '<em><b>Lock Size Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__LOCK_SIZE_ROW = LUW_TABLE__LOCK_SIZE_ROW;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__VOLATILE = LUW_TABLE__VOLATILE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__OPTIONS = LUW_TABLE__OPTIONS;

	/**
	 * The feature id for the '<em><b>Security Policy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__SECURITY_POLICY = LUW_TABLE__SECURITY_POLICY;

	/**
	 * The feature id for the '<em><b>Remote Data Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__REMOTE_DATA_SET = LUW_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME__SERVER = LUW_TABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Nickname</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NICKNAME_FEATURE_COUNT = LUW_TABLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl <em>Function Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWFunctionMapping()
	 * @generated
	 */
	int LUW_FUNCTION_MAPPING = 10;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Server Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__SERVER_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Server Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__SERVER_VERSION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__SERVER_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Creation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__CREATION_TIME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__DISABLED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Insts Per Invoc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__INSTS_PER_INVOC = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Insts Per Arg Byte</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__INSTS_PER_ARG_BYTE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Ios Per Invoc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__IOS_PER_INVOC = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Ios Per Arg Byte</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__IOS_PER_ARG_BYTE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__OPTIONS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Local Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__LOCAL_FUNCTION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Remote Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__REMOTE_FUNCTION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>LUW Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING__LUW_DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Function Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_FUNCTION_MAPPING_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl <em>Wrapper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWWrapper()
	 * @generated
	 */
	int LUW_WRAPPER = 11;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__VERSION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Library</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__LIBRARY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__FENCED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Wrapper Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__WRAPPER_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__DATA_SOURCE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Discovered Libraries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__DISCOVERED_LIBRARIES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__SERVERS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>LUW Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__LUW_DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER__OPTIONS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_WRAPPER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalNicknameImpl <em>Non Relational Nickname</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalNicknameImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWNonRelationalNickname()
	 * @generated
	 */
	int LUW_NON_RELATIONAL_NICKNAME = 12;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__EANNOTATIONS = LUW_NICKNAME__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__NAME = LUW_NICKNAME__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__DEPENDENCIES = LUW_NICKNAME__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__DESCRIPTION = LUW_NICKNAME__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__LABEL = LUW_NICKNAME__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__COMMENTS = LUW_NICKNAME__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__EXTENSIONS = LUW_NICKNAME__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__PRIVILEGES = LUW_NICKNAME__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__COLUMNS = LUW_NICKNAME__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__SUPERTABLE = LUW_NICKNAME__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__SUBTABLES = LUW_NICKNAME__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__SCHEMA = LUW_NICKNAME__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__UDT = LUW_NICKNAME__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__TRIGGERS = LUW_NICKNAME__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__INDEX = LUW_NICKNAME__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__SELF_REF_COLUMN_GENERATION = LUW_NICKNAME__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__INSERTABLE = LUW_NICKNAME__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__UPDATABLE = LUW_NICKNAME__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__CONSTRAINTS = LUW_NICKNAME__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__REFERENCING_FOREIGN_KEYS = LUW_NICKNAME__REFERENCING_FOREIGN_KEYS;

	/**
	 * The feature id for the '<em><b>Data Capture</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__DATA_CAPTURE = LUW_NICKNAME__DATA_CAPTURE;

	/**
	 * The feature id for the '<em><b>Activate Row Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__ACTIVATE_ROW_ACCESS_CONTROL = LUW_NICKNAME__ACTIVATE_ROW_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Activate Column Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__ACTIVATE_COLUMN_ACCESS_CONTROL = LUW_NICKNAME__ACTIVATE_COLUMN_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Organize By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__ORGANIZE_BY = LUW_NICKNAME__ORGANIZE_BY;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__PACKAGES = LUW_NICKNAME__PACKAGES;

	/**
	 * The feature id for the '<em><b>Periods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__PERIODS = LUW_NICKNAME__PERIODS;

	/**
	 * The feature id for the '<em><b>History Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__HISTORY_TABLE = LUW_NICKNAME__HISTORY_TABLE;

	/**
	 * The feature id for the '<em><b>Temporal Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__TEMPORAL_TABLE = LUW_NICKNAME__TEMPORAL_TABLE;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__MASKS = LUW_NICKNAME__MASKS;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__PERMISSIONS = LUW_NICKNAME__PERMISSIONS;

	/**
	 * The feature id for the '<em><b>Value Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__VALUE_COMPRESSION = LUW_NICKNAME__VALUE_COMPRESSION;

	/**
	 * The feature id for the '<em><b>Row Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__ROW_COMPRESSION = LUW_NICKNAME__ROW_COMPRESSION;

	/**
	 * The feature id for the '<em><b>Row Compression Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__ROW_COMPRESSION_EMPTY = LUW_NICKNAME__ROW_COMPRESSION_EMPTY;

	/**
	 * The feature id for the '<em><b>Compression Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__COMPRESSION_MODE = LUW_NICKNAME__COMPRESSION_MODE;

	/**
	 * The feature id for the '<em><b>Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__PARTITION_KEY = LUW_NICKNAME__PARTITION_KEY;

	/**
	 * The feature id for the '<em><b>Index Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__INDEX_DATA_TABLE_SPACE = LUW_NICKNAME__INDEX_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>LOB Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__LOB_DATA_TABLE_SPACE = LUW_NICKNAME__LOB_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>Regular Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__REGULAR_DATA_TABLE_SPACE = LUW_NICKNAME__REGULAR_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>Data Partitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__DATA_PARTITIONS = LUW_NICKNAME__DATA_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Data Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__DATA_PARTITION_KEY = LUW_NICKNAME__DATA_PARTITION_KEY;

	/**
	 * The feature id for the '<em><b>PCT Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__PCT_FREE = LUW_NICKNAME__PCT_FREE;

	/**
	 * The feature id for the '<em><b>Restrict On Drop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__RESTRICT_ON_DROP = LUW_NICKNAME__RESTRICT_ON_DROP;

	/**
	 * The feature id for the '<em><b>Partition Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__PARTITION_MODE = LUW_NICKNAME__PARTITION_MODE;

	/**
	 * The feature id for the '<em><b>Append Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__APPEND_MODE = LUW_NICKNAME__APPEND_MODE;

	/**
	 * The feature id for the '<em><b>Log Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__LOG_MODE = LUW_NICKNAME__LOG_MODE;

	/**
	 * The feature id for the '<em><b>Lock Size Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__LOCK_SIZE_ROW = LUW_NICKNAME__LOCK_SIZE_ROW;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__VOLATILE = LUW_NICKNAME__VOLATILE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__OPTIONS = LUW_NICKNAME__OPTIONS;

	/**
	 * The feature id for the '<em><b>Security Policy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__SECURITY_POLICY = LUW_NICKNAME__SECURITY_POLICY;

	/**
	 * The feature id for the '<em><b>Remote Data Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__REMOTE_DATA_SET = LUW_NICKNAME__REMOTE_DATA_SET;

	/**
	 * The feature id for the '<em><b>Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__SERVER = LUW_NICKNAME__SERVER;

	/**
	 * The feature id for the '<em><b>Non Rel Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME__NON_REL_SERVER = LUW_NICKNAME_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Non Relational Nickname</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_NICKNAME_FEATURE_COUNT = LUW_NICKNAME_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl <em>Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWServer()
	 * @generated
	 */
	int LUW_SERVER = 18;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Server Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__SERVER_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Server Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__SERVER_VERSION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>User Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__USER_MAPPINGS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Wrapper</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__WRAPPER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Nicknames</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__NICKNAMES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>LUW Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__LUW_DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__OPTIONS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Remote Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER__REMOTE_SERVER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SERVER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalServerImpl <em>Non Relational Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalServerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWNonRelationalServer()
	 * @generated
	 */
	int LUW_NON_RELATIONAL_SERVER = 13;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__EANNOTATIONS = LUW_SERVER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__NAME = LUW_SERVER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__DEPENDENCIES = LUW_SERVER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__DESCRIPTION = LUW_SERVER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__LABEL = LUW_SERVER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__COMMENTS = LUW_SERVER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__EXTENSIONS = LUW_SERVER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__PRIVILEGES = LUW_SERVER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Server Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__SERVER_TYPE = LUW_SERVER__SERVER_TYPE;

	/**
	 * The feature id for the '<em><b>Server Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__SERVER_VERSION = LUW_SERVER__SERVER_VERSION;

	/**
	 * The feature id for the '<em><b>User Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__USER_MAPPINGS = LUW_SERVER__USER_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Wrapper</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__WRAPPER = LUW_SERVER__WRAPPER;

	/**
	 * The feature id for the '<em><b>Nicknames</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__NICKNAMES = LUW_SERVER__NICKNAMES;

	/**
	 * The feature id for the '<em><b>LUW Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__LUW_DATABASE = LUW_SERVER__LUW_DATABASE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__OPTIONS = LUW_SERVER__OPTIONS;

	/**
	 * The feature id for the '<em><b>Remote Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__REMOTE_SERVER = LUW_SERVER__REMOTE_SERVER;

	/**
	 * The feature id for the '<em><b>Non Rel Wrapper</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__NON_REL_WRAPPER = LUW_SERVER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Non Rel Nicknames</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER__NON_REL_NICKNAMES = LUW_SERVER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Non Relational Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_SERVER_FEATURE_COUNT = LUW_SERVER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalWrapperImpl <em>Non Relational Wrapper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalWrapperImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWNonRelationalWrapper()
	 * @generated
	 */
	int LUW_NON_RELATIONAL_WRAPPER = 14;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__EANNOTATIONS = LUW_WRAPPER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__NAME = LUW_WRAPPER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__DEPENDENCIES = LUW_WRAPPER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__DESCRIPTION = LUW_WRAPPER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__LABEL = LUW_WRAPPER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__COMMENTS = LUW_WRAPPER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__EXTENSIONS = LUW_WRAPPER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__PRIVILEGES = LUW_WRAPPER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__VERSION = LUW_WRAPPER__VERSION;

	/**
	 * The feature id for the '<em><b>Library</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__LIBRARY = LUW_WRAPPER__LIBRARY;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__FENCED = LUW_WRAPPER__FENCED;

	/**
	 * The feature id for the '<em><b>Wrapper Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__WRAPPER_TYPE = LUW_WRAPPER__WRAPPER_TYPE;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__DATA_SOURCE = LUW_WRAPPER__DATA_SOURCE;

	/**
	 * The feature id for the '<em><b>Discovered Libraries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__DISCOVERED_LIBRARIES = LUW_WRAPPER__DISCOVERED_LIBRARIES;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__SERVERS = LUW_WRAPPER__SERVERS;

	/**
	 * The feature id for the '<em><b>LUW Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__LUW_DATABASE = LUW_WRAPPER__LUW_DATABASE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__OPTIONS = LUW_WRAPPER__OPTIONS;

	/**
	 * The feature id for the '<em><b>Non Rel Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER__NON_REL_SERVERS = LUW_WRAPPER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Non Relational Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_NON_RELATIONAL_WRAPPER_FEATURE_COUNT = LUW_WRAPPER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalNicknameImpl <em>Relational Nickname</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalNicknameImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWRelationalNickname()
	 * @generated
	 */
	int LUW_RELATIONAL_NICKNAME = 15;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__EANNOTATIONS = LUW_NICKNAME__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__NAME = LUW_NICKNAME__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__DEPENDENCIES = LUW_NICKNAME__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__DESCRIPTION = LUW_NICKNAME__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__LABEL = LUW_NICKNAME__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__COMMENTS = LUW_NICKNAME__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__EXTENSIONS = LUW_NICKNAME__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__PRIVILEGES = LUW_NICKNAME__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__COLUMNS = LUW_NICKNAME__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__SUPERTABLE = LUW_NICKNAME__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__SUBTABLES = LUW_NICKNAME__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__SCHEMA = LUW_NICKNAME__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__UDT = LUW_NICKNAME__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__TRIGGERS = LUW_NICKNAME__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__INDEX = LUW_NICKNAME__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__SELF_REF_COLUMN_GENERATION = LUW_NICKNAME__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__INSERTABLE = LUW_NICKNAME__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__UPDATABLE = LUW_NICKNAME__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__CONSTRAINTS = LUW_NICKNAME__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__REFERENCING_FOREIGN_KEYS = LUW_NICKNAME__REFERENCING_FOREIGN_KEYS;

	/**
	 * The feature id for the '<em><b>Data Capture</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__DATA_CAPTURE = LUW_NICKNAME__DATA_CAPTURE;

	/**
	 * The feature id for the '<em><b>Activate Row Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__ACTIVATE_ROW_ACCESS_CONTROL = LUW_NICKNAME__ACTIVATE_ROW_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Activate Column Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__ACTIVATE_COLUMN_ACCESS_CONTROL = LUW_NICKNAME__ACTIVATE_COLUMN_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Organize By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__ORGANIZE_BY = LUW_NICKNAME__ORGANIZE_BY;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__PACKAGES = LUW_NICKNAME__PACKAGES;

	/**
	 * The feature id for the '<em><b>Periods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__PERIODS = LUW_NICKNAME__PERIODS;

	/**
	 * The feature id for the '<em><b>History Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__HISTORY_TABLE = LUW_NICKNAME__HISTORY_TABLE;

	/**
	 * The feature id for the '<em><b>Temporal Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__TEMPORAL_TABLE = LUW_NICKNAME__TEMPORAL_TABLE;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__MASKS = LUW_NICKNAME__MASKS;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__PERMISSIONS = LUW_NICKNAME__PERMISSIONS;

	/**
	 * The feature id for the '<em><b>Value Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__VALUE_COMPRESSION = LUW_NICKNAME__VALUE_COMPRESSION;

	/**
	 * The feature id for the '<em><b>Row Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__ROW_COMPRESSION = LUW_NICKNAME__ROW_COMPRESSION;

	/**
	 * The feature id for the '<em><b>Row Compression Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__ROW_COMPRESSION_EMPTY = LUW_NICKNAME__ROW_COMPRESSION_EMPTY;

	/**
	 * The feature id for the '<em><b>Compression Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__COMPRESSION_MODE = LUW_NICKNAME__COMPRESSION_MODE;

	/**
	 * The feature id for the '<em><b>Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__PARTITION_KEY = LUW_NICKNAME__PARTITION_KEY;

	/**
	 * The feature id for the '<em><b>Index Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__INDEX_DATA_TABLE_SPACE = LUW_NICKNAME__INDEX_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>LOB Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__LOB_DATA_TABLE_SPACE = LUW_NICKNAME__LOB_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>Regular Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__REGULAR_DATA_TABLE_SPACE = LUW_NICKNAME__REGULAR_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>Data Partitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__DATA_PARTITIONS = LUW_NICKNAME__DATA_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Data Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__DATA_PARTITION_KEY = LUW_NICKNAME__DATA_PARTITION_KEY;

	/**
	 * The feature id for the '<em><b>PCT Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__PCT_FREE = LUW_NICKNAME__PCT_FREE;

	/**
	 * The feature id for the '<em><b>Restrict On Drop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__RESTRICT_ON_DROP = LUW_NICKNAME__RESTRICT_ON_DROP;

	/**
	 * The feature id for the '<em><b>Partition Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__PARTITION_MODE = LUW_NICKNAME__PARTITION_MODE;

	/**
	 * The feature id for the '<em><b>Append Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__APPEND_MODE = LUW_NICKNAME__APPEND_MODE;

	/**
	 * The feature id for the '<em><b>Log Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__LOG_MODE = LUW_NICKNAME__LOG_MODE;

	/**
	 * The feature id for the '<em><b>Lock Size Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__LOCK_SIZE_ROW = LUW_NICKNAME__LOCK_SIZE_ROW;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__VOLATILE = LUW_NICKNAME__VOLATILE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__OPTIONS = LUW_NICKNAME__OPTIONS;

	/**
	 * The feature id for the '<em><b>Security Policy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__SECURITY_POLICY = LUW_NICKNAME__SECURITY_POLICY;

	/**
	 * The feature id for the '<em><b>Remote Data Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__REMOTE_DATA_SET = LUW_NICKNAME__REMOTE_DATA_SET;

	/**
	 * The feature id for the '<em><b>Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__SERVER = LUW_NICKNAME__SERVER;

	/**
	 * The feature id for the '<em><b>Rel Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME__REL_SERVER = LUW_NICKNAME_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Relational Nickname</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_NICKNAME_FEATURE_COUNT = LUW_NICKNAME_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWUserMappingImpl <em>User Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWUserMappingImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWUserMapping()
	 * @generated
	 */
	int LUW_USER_MAPPING = 20;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Local Auth Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__LOCAL_AUTH_ID = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Server</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__SERVER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING__OPTIONS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>User Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_USER_MAPPING_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericUserMappingImpl <em>Generic User Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericUserMappingImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGenericUserMapping()
	 * @generated
	 */
	int LUW_GENERIC_USER_MAPPING = 16;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__EANNOTATIONS = LUW_USER_MAPPING__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__NAME = LUW_USER_MAPPING__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__DEPENDENCIES = LUW_USER_MAPPING__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__DESCRIPTION = LUW_USER_MAPPING__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__LABEL = LUW_USER_MAPPING__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__COMMENTS = LUW_USER_MAPPING__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__EXTENSIONS = LUW_USER_MAPPING__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__PRIVILEGES = LUW_USER_MAPPING__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Local Auth Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__LOCAL_AUTH_ID = LUW_USER_MAPPING__LOCAL_AUTH_ID;

	/**
	 * The feature id for the '<em><b>Server</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__SERVER = LUW_USER_MAPPING__SERVER;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__OPTIONS = LUW_USER_MAPPING__OPTIONS;

	/**
	 * The feature id for the '<em><b>Remote User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__REMOTE_USER = LUW_USER_MAPPING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Remote Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING__REMOTE_PASSWORD = LUW_USER_MAPPING_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic User Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_USER_MAPPING_FEATURE_COUNT = LUW_USER_MAPPING_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalWrapperImpl <em>Relational Wrapper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalWrapperImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWRelationalWrapper()
	 * @generated
	 */
	int LUW_RELATIONAL_WRAPPER = 17;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__EANNOTATIONS = LUW_WRAPPER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__NAME = LUW_WRAPPER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__DEPENDENCIES = LUW_WRAPPER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__DESCRIPTION = LUW_WRAPPER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__LABEL = LUW_WRAPPER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__COMMENTS = LUW_WRAPPER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__EXTENSIONS = LUW_WRAPPER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__PRIVILEGES = LUW_WRAPPER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__VERSION = LUW_WRAPPER__VERSION;

	/**
	 * The feature id for the '<em><b>Library</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__LIBRARY = LUW_WRAPPER__LIBRARY;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__FENCED = LUW_WRAPPER__FENCED;

	/**
	 * The feature id for the '<em><b>Wrapper Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__WRAPPER_TYPE = LUW_WRAPPER__WRAPPER_TYPE;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__DATA_SOURCE = LUW_WRAPPER__DATA_SOURCE;

	/**
	 * The feature id for the '<em><b>Discovered Libraries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__DISCOVERED_LIBRARIES = LUW_WRAPPER__DISCOVERED_LIBRARIES;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__SERVERS = LUW_WRAPPER__SERVERS;

	/**
	 * The feature id for the '<em><b>LUW Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__LUW_DATABASE = LUW_WRAPPER__LUW_DATABASE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__OPTIONS = LUW_WRAPPER__OPTIONS;

	/**
	 * The feature id for the '<em><b>Rel Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER__REL_SERVERS = LUW_WRAPPER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Relational Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_WRAPPER_FEATURE_COUNT = LUW_WRAPPER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl <em>Type Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTypeMapping()
	 * @generated
	 */
	int LUW_TYPE_MAPPING = 19;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Server Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__SERVER_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Server Vesion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__SERVER_VESION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Server Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__SERVER_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Creation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__CREATION_TIME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Local Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__LOCAL_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Remote Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING__REMOTE_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Type Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TYPE_MAPPING_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWOptionImpl <em>Option</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWOptionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWOption()
	 * @generated
	 */
	int LUW_OPTION = 21;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION__VALUE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Option</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_OPTION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl <em>Relational Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWRelationalServer()
	 * @generated
	 */
	int LUW_RELATIONAL_SERVER = 22;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__EANNOTATIONS = LUW_SERVER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__NAME = LUW_SERVER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__DEPENDENCIES = LUW_SERVER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__DESCRIPTION = LUW_SERVER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__LABEL = LUW_SERVER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__COMMENTS = LUW_SERVER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__EXTENSIONS = LUW_SERVER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__PRIVILEGES = LUW_SERVER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Server Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__SERVER_TYPE = LUW_SERVER__SERVER_TYPE;

	/**
	 * The feature id for the '<em><b>Server Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__SERVER_VERSION = LUW_SERVER__SERVER_VERSION;

	/**
	 * The feature id for the '<em><b>User Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__USER_MAPPINGS = LUW_SERVER__USER_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Wrapper</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__WRAPPER = LUW_SERVER__WRAPPER;

	/**
	 * The feature id for the '<em><b>Nicknames</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__NICKNAMES = LUW_SERVER__NICKNAMES;

	/**
	 * The feature id for the '<em><b>LUW Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__LUW_DATABASE = LUW_SERVER__LUW_DATABASE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__OPTIONS = LUW_SERVER__OPTIONS;

	/**
	 * The feature id for the '<em><b>Remote Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__REMOTE_SERVER = LUW_SERVER__REMOTE_SERVER;

	/**
	 * The feature id for the '<em><b>Cpu Ratio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__CPU_RATIO = LUW_SERVER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Io Ratio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__IO_RATIO = LUW_SERVER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Comm Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__COMM_RATE = LUW_SERVER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fold Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__FOLD_ID = LUW_SERVER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Fold PW</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__FOLD_PW = LUW_SERVER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Collating Sequence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__COLLATING_SEQUENCE = LUW_SERVER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Pushdown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__PUSHDOWN = LUW_SERVER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Node</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__NODE = LUW_SERVER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Db Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__DB_NAME = LUW_SERVER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Iud App Svpt Enforce</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__IUD_APP_SVPT_ENFORCE = LUW_SERVER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Password</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__PASSWORD = LUW_SERVER_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Rel Nicknames</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__REL_NICKNAMES = LUW_SERVER_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Rel Wrapper</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER__REL_WRAPPER = LUW_SERVER_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Relational Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_RELATIONAL_SERVER_FEATURE_COUNT = LUW_SERVER_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl <em>Database</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDatabase()
	 * @generated
	 */
	int LUW_DATABASE = 23;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__EANNOTATIONS = DB2ModelPackage.DB2_DATABASE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__NAME = DB2ModelPackage.DB2_DATABASE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__DEPENDENCIES = DB2ModelPackage.DB2_DATABASE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__DESCRIPTION = DB2ModelPackage.DB2_DATABASE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__LABEL = DB2ModelPackage.DB2_DATABASE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__COMMENTS = DB2ModelPackage.DB2_DATABASE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__EXTENSIONS = DB2ModelPackage.DB2_DATABASE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__PRIVILEGES = DB2ModelPackage.DB2_DATABASE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__VENDOR = DB2ModelPackage.DB2_DATABASE__VENDOR;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__VERSION = DB2ModelPackage.DB2_DATABASE__VERSION;

	/**
	 * The feature id for the '<em><b>Schemas</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__SCHEMAS = DB2ModelPackage.DB2_DATABASE__SCHEMAS;

	/**
	 * The feature id for the '<em><b>Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__EVENTS = DB2ModelPackage.DB2_DATABASE__EVENTS;

	/**
	 * The feature id for the '<em><b>Catalogs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__CATALOGS = DB2ModelPackage.DB2_DATABASE__CATALOGS;

	/**
	 * The feature id for the '<em><b>Authorization Ids</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__AUTHORIZATION_IDS = DB2ModelPackage.DB2_DATABASE__AUTHORIZATION_IDS;

	/**
	 * The feature id for the '<em><b>Partitioned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__PARTITIONED = DB2ModelPackage.DB2_DATABASE__PARTITIONED;

	/**
	 * The feature id for the '<em><b>Default Organize By Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__DEFAULT_ORGANIZE_BY_ROW = DB2ModelPackage.DB2_DATABASE__DEFAULT_ORGANIZE_BY_ROW;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__FEDERATED = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__GROUPS = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Wrappers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__WRAPPERS = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__SERVERS = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Function Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__FUNCTION_MAPPINGS = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Type Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__TYPE_MAPPINGS = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Reverse Type Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__REVERSE_TYPE_MAPPINGS = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Bufferpools</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__BUFFERPOOLS = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Tablespaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__TABLESPACES = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Storage Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__STORAGE_GROUPS = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Default Storage Group</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE__DEFAULT_STORAGE_GROUP = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_FEATURE_COUNT = DB2ModelPackage.DB2_DATABASE_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl <em>Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWColumn()
	 * @generated
	 */
	int LUW_COLUMN = 24;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__EANNOTATIONS = DB2ModelPackage.DB2_COLUMN__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__NAME = DB2ModelPackage.DB2_COLUMN__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__DEPENDENCIES = DB2ModelPackage.DB2_COLUMN__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__DESCRIPTION = DB2ModelPackage.DB2_COLUMN__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__LABEL = DB2ModelPackage.DB2_COLUMN__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__COMMENTS = DB2ModelPackage.DB2_COLUMN__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__EXTENSIONS = DB2ModelPackage.DB2_COLUMN__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__PRIVILEGES = DB2ModelPackage.DB2_COLUMN__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__CONTAINED_TYPE = DB2ModelPackage.DB2_COLUMN__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__REFERENCED_TYPE = DB2ModelPackage.DB2_COLUMN__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__TABLE = DB2ModelPackage.DB2_COLUMN__TABLE;

	/**
	 * The feature id for the '<em><b>Identity Specifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__IDENTITY_SPECIFIER = DB2ModelPackage.DB2_COLUMN__IDENTITY_SPECIFIER;

	/**
	 * The feature id for the '<em><b>Generate Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__GENERATE_EXPRESSION = DB2ModelPackage.DB2_COLUMN__GENERATE_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Implementation Dependent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__IMPLEMENTATION_DEPENDENT = DB2ModelPackage.DB2_COLUMN__IMPLEMENTATION_DEPENDENT;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__NULLABLE = DB2ModelPackage.DB2_COLUMN__NULLABLE;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__DEFAULT_VALUE = DB2ModelPackage.DB2_COLUMN__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Scope Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__SCOPE_CHECK = DB2ModelPackage.DB2_COLUMN__SCOPE_CHECK;

	/**
	 * The feature id for the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__SCOPE_CHECKED = DB2ModelPackage.DB2_COLUMN__SCOPE_CHECKED;

	/**
	 * The feature id for the '<em><b>Generation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__GENERATION_TYPE = DB2ModelPackage.DB2_COLUMN__GENERATION_TYPE;

	/**
	 * The feature id for the '<em><b>Row Change Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__ROW_CHANGE_TIMESTAMP = DB2ModelPackage.DB2_COLUMN__ROW_CHANGE_TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Row Begin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__ROW_BEGIN = DB2ModelPackage.DB2_COLUMN__ROW_BEGIN;

	/**
	 * The feature id for the '<em><b>Row End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__ROW_END = DB2ModelPackage.DB2_COLUMN__ROW_END;

	/**
	 * The feature id for the '<em><b>Trans Start ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__TRANS_START_ID = DB2ModelPackage.DB2_COLUMN__TRANS_START_ID;

	/**
	 * The feature id for the '<em><b>Begin Period</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__BEGIN_PERIOD = DB2ModelPackage.DB2_COLUMN__BEGIN_PERIOD;

	/**
	 * The feature id for the '<em><b>End Period</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__END_PERIOD = DB2ModelPackage.DB2_COLUMN__END_PERIOD;

	/**
	 * The feature id for the '<em><b>Lob Logged</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__LOB_LOGGED = DB2ModelPackage.DB2_COLUMN_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lob Compacted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__LOB_COMPACTED = DB2ModelPackage.DB2_COLUMN_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__COMPRESSION = DB2ModelPackage.DB2_COLUMN_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Inline Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__INLINE_LENGTH = DB2ModelPackage.DB2_COLUMN_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__HIDDEN = DB2ModelPackage.DB2_COLUMN_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Security Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__SECURITY_LABEL = DB2ModelPackage.DB2_COLUMN_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN__OPTIONS = DB2ModelPackage.DB2_COLUMN_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_COLUMN_FEATURE_COUNT = DB2ModelPackage.DB2_COLUMN_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericNicknameImpl <em>Generic Nickname</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericNicknameImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGenericNickname()
	 * @generated
	 */
	int LUW_GENERIC_NICKNAME = 25;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__EANNOTATIONS = LUW_NICKNAME__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__NAME = LUW_NICKNAME__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__DEPENDENCIES = LUW_NICKNAME__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__DESCRIPTION = LUW_NICKNAME__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__LABEL = LUW_NICKNAME__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__COMMENTS = LUW_NICKNAME__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__EXTENSIONS = LUW_NICKNAME__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__PRIVILEGES = LUW_NICKNAME__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__COLUMNS = LUW_NICKNAME__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__SUPERTABLE = LUW_NICKNAME__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__SUBTABLES = LUW_NICKNAME__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__SCHEMA = LUW_NICKNAME__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__UDT = LUW_NICKNAME__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__TRIGGERS = LUW_NICKNAME__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__INDEX = LUW_NICKNAME__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__SELF_REF_COLUMN_GENERATION = LUW_NICKNAME__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__INSERTABLE = LUW_NICKNAME__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__UPDATABLE = LUW_NICKNAME__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__CONSTRAINTS = LUW_NICKNAME__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__REFERENCING_FOREIGN_KEYS = LUW_NICKNAME__REFERENCING_FOREIGN_KEYS;

	/**
	 * The feature id for the '<em><b>Data Capture</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__DATA_CAPTURE = LUW_NICKNAME__DATA_CAPTURE;

	/**
	 * The feature id for the '<em><b>Activate Row Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__ACTIVATE_ROW_ACCESS_CONTROL = LUW_NICKNAME__ACTIVATE_ROW_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Activate Column Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__ACTIVATE_COLUMN_ACCESS_CONTROL = LUW_NICKNAME__ACTIVATE_COLUMN_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Organize By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__ORGANIZE_BY = LUW_NICKNAME__ORGANIZE_BY;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__PACKAGES = LUW_NICKNAME__PACKAGES;

	/**
	 * The feature id for the '<em><b>Periods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__PERIODS = LUW_NICKNAME__PERIODS;

	/**
	 * The feature id for the '<em><b>History Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__HISTORY_TABLE = LUW_NICKNAME__HISTORY_TABLE;

	/**
	 * The feature id for the '<em><b>Temporal Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__TEMPORAL_TABLE = LUW_NICKNAME__TEMPORAL_TABLE;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__MASKS = LUW_NICKNAME__MASKS;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__PERMISSIONS = LUW_NICKNAME__PERMISSIONS;

	/**
	 * The feature id for the '<em><b>Value Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__VALUE_COMPRESSION = LUW_NICKNAME__VALUE_COMPRESSION;

	/**
	 * The feature id for the '<em><b>Row Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__ROW_COMPRESSION = LUW_NICKNAME__ROW_COMPRESSION;

	/**
	 * The feature id for the '<em><b>Row Compression Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__ROW_COMPRESSION_EMPTY = LUW_NICKNAME__ROW_COMPRESSION_EMPTY;

	/**
	 * The feature id for the '<em><b>Compression Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__COMPRESSION_MODE = LUW_NICKNAME__COMPRESSION_MODE;

	/**
	 * The feature id for the '<em><b>Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__PARTITION_KEY = LUW_NICKNAME__PARTITION_KEY;

	/**
	 * The feature id for the '<em><b>Index Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__INDEX_DATA_TABLE_SPACE = LUW_NICKNAME__INDEX_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>LOB Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__LOB_DATA_TABLE_SPACE = LUW_NICKNAME__LOB_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>Regular Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__REGULAR_DATA_TABLE_SPACE = LUW_NICKNAME__REGULAR_DATA_TABLE_SPACE;

	/**
	 * The feature id for the '<em><b>Data Partitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__DATA_PARTITIONS = LUW_NICKNAME__DATA_PARTITIONS;

	/**
	 * The feature id for the '<em><b>Data Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__DATA_PARTITION_KEY = LUW_NICKNAME__DATA_PARTITION_KEY;

	/**
	 * The feature id for the '<em><b>PCT Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__PCT_FREE = LUW_NICKNAME__PCT_FREE;

	/**
	 * The feature id for the '<em><b>Restrict On Drop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__RESTRICT_ON_DROP = LUW_NICKNAME__RESTRICT_ON_DROP;

	/**
	 * The feature id for the '<em><b>Partition Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__PARTITION_MODE = LUW_NICKNAME__PARTITION_MODE;

	/**
	 * The feature id for the '<em><b>Append Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__APPEND_MODE = LUW_NICKNAME__APPEND_MODE;

	/**
	 * The feature id for the '<em><b>Log Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__LOG_MODE = LUW_NICKNAME__LOG_MODE;

	/**
	 * The feature id for the '<em><b>Lock Size Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__LOCK_SIZE_ROW = LUW_NICKNAME__LOCK_SIZE_ROW;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__VOLATILE = LUW_NICKNAME__VOLATILE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__OPTIONS = LUW_NICKNAME__OPTIONS;

	/**
	 * The feature id for the '<em><b>Security Policy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__SECURITY_POLICY = LUW_NICKNAME__SECURITY_POLICY;

	/**
	 * The feature id for the '<em><b>Remote Data Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__REMOTE_DATA_SET = LUW_NICKNAME__REMOTE_DATA_SET;

	/**
	 * The feature id for the '<em><b>Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__SERVER = LUW_NICKNAME__SERVER;

	/**
	 * The feature id for the '<em><b>Generic Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME__GENERIC_SERVER = LUW_NICKNAME_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Nickname</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_NICKNAME_FEATURE_COUNT = LUW_NICKNAME_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericServerImpl <em>Generic Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericServerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGenericServer()
	 * @generated
	 */
	int LUW_GENERIC_SERVER = 26;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__EANNOTATIONS = LUW_SERVER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__NAME = LUW_SERVER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__DEPENDENCIES = LUW_SERVER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__DESCRIPTION = LUW_SERVER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__LABEL = LUW_SERVER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__COMMENTS = LUW_SERVER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__EXTENSIONS = LUW_SERVER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__PRIVILEGES = LUW_SERVER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Server Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__SERVER_TYPE = LUW_SERVER__SERVER_TYPE;

	/**
	 * The feature id for the '<em><b>Server Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__SERVER_VERSION = LUW_SERVER__SERVER_VERSION;

	/**
	 * The feature id for the '<em><b>User Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__USER_MAPPINGS = LUW_SERVER__USER_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Wrapper</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__WRAPPER = LUW_SERVER__WRAPPER;

	/**
	 * The feature id for the '<em><b>Nicknames</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__NICKNAMES = LUW_SERVER__NICKNAMES;

	/**
	 * The feature id for the '<em><b>LUW Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__LUW_DATABASE = LUW_SERVER__LUW_DATABASE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__OPTIONS = LUW_SERVER__OPTIONS;

	/**
	 * The feature id for the '<em><b>Remote Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__REMOTE_SERVER = LUW_SERVER__REMOTE_SERVER;

	/**
	 * The feature id for the '<em><b>Generic Nicknames</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__GENERIC_NICKNAMES = LUW_SERVER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Generic Wrapper</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER__GENERIC_WRAPPER = LUW_SERVER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generic Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_SERVER_FEATURE_COUNT = LUW_SERVER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl <em>Materialized Query Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWMaterializedQueryTable()
	 * @generated
	 */
	int LUW_MATERIALIZED_QUERY_TABLE = 27;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__EANNOTATIONS = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__NAME = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__DEPENDENCIES = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__DESCRIPTION = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__LABEL = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__COMMENTS = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__EXTENSIONS = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__PRIVILEGES = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__COLUMNS = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__SUPERTABLE = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__SUBTABLES = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__SCHEMA = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__UDT = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__TRIGGERS = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__INDEX = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__SELF_REF_COLUMN_GENERATION = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__INSERTABLE = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__UPDATABLE = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__QUERY_EXPRESSION = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__QUERY_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Refresh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__REFRESH = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__REFRESH;

	/**
	 * The feature id for the '<em><b>Optimize Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY;

	/**
	 * The feature id for the '<em><b>Maintained By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__MAINTAINED_BY = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MAINTAINED_BY;

	/**
	 * The feature id for the '<em><b>Activate Row Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Activate Column Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__MASKS = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__MASKS;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__PERMISSIONS = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS;

	/**
	 * The feature id for the '<em><b>Value Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__VALUE_COMPRESSION = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Row Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Row Compression Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__ROW_COMPRESSION_EMPTY = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Compression Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__COMPRESSION_MODE = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__PARTITION_KEY = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Index Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__INDEX_DATA_TABLE_SPACE = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>LOB Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__LOB_DATA_TABLE_SPACE = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Regular Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__REGULAR_DATA_TABLE_SPACE = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Data Partitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITIONS = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Data Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE__DATA_PARTITION_KEY = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Materialized Query Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT = DB2ModelPackage.DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericWrapperImpl <em>Generic Wrapper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericWrapperImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGenericWrapper()
	 * @generated
	 */
	int LUW_GENERIC_WRAPPER = 28;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__EANNOTATIONS = LUW_WRAPPER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__NAME = LUW_WRAPPER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__DEPENDENCIES = LUW_WRAPPER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__DESCRIPTION = LUW_WRAPPER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__LABEL = LUW_WRAPPER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__COMMENTS = LUW_WRAPPER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__EXTENSIONS = LUW_WRAPPER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__PRIVILEGES = LUW_WRAPPER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__VERSION = LUW_WRAPPER__VERSION;

	/**
	 * The feature id for the '<em><b>Library</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__LIBRARY = LUW_WRAPPER__LIBRARY;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__FENCED = LUW_WRAPPER__FENCED;

	/**
	 * The feature id for the '<em><b>Wrapper Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__WRAPPER_TYPE = LUW_WRAPPER__WRAPPER_TYPE;

	/**
	 * The feature id for the '<em><b>Data Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__DATA_SOURCE = LUW_WRAPPER__DATA_SOURCE;

	/**
	 * The feature id for the '<em><b>Discovered Libraries</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__DISCOVERED_LIBRARIES = LUW_WRAPPER__DISCOVERED_LIBRARIES;

	/**
	 * The feature id for the '<em><b>Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__SERVERS = LUW_WRAPPER__SERVERS;

	/**
	 * The feature id for the '<em><b>LUW Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__LUW_DATABASE = LUW_WRAPPER__LUW_DATABASE;

	/**
	 * The feature id for the '<em><b>Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__OPTIONS = LUW_WRAPPER__OPTIONS;

	/**
	 * The feature id for the '<em><b>Generic Servers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER__GENERIC_SERVERS = LUW_WRAPPER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generic Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GENERIC_WRAPPER_FEATURE_COUNT = LUW_WRAPPER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable <em>Storage Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWStorageTable()
	 * @generated
	 */
	int LUW_STORAGE_TABLE = 29;

	/**
	 * The feature id for the '<em><b>Value Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__VALUE_COMPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Row Compression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__ROW_COMPRESSION = 1;

	/**
	 * The feature id for the '<em><b>Row Compression Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__ROW_COMPRESSION_EMPTY = 2;

	/**
	 * The feature id for the '<em><b>Compression Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__COMPRESSION_MODE = 3;

	/**
	 * The feature id for the '<em><b>Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__PARTITION_KEY = 4;

	/**
	 * The feature id for the '<em><b>Index Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__INDEX_DATA_TABLE_SPACE = 5;

	/**
	 * The feature id for the '<em><b>LOB Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__LOB_DATA_TABLE_SPACE = 6;

	/**
	 * The feature id for the '<em><b>Regular Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__REGULAR_DATA_TABLE_SPACE = 7;

	/**
	 * The feature id for the '<em><b>Data Partitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__DATA_PARTITIONS = 8;

	/**
	 * The feature id for the '<em><b>Data Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE__DATA_PARTITION_KEY = 9;

	/**
	 * The number of structural features of the '<em>Storage Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_TABLE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteServerImpl <em>Relational Remote Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteServerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRelationalRemoteServer()
	 * @generated
	 */
	int RELATIONAL_REMOTE_SERVER = 30;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>LUW Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__LUW_SERVER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Relational Remote Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_SERVER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteDataSetImpl <em>Relational Remote Data Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteDataSetImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRelationalRemoteDataSet()
	 * @generated
	 */
	int RELATIONAL_REMOTE_DATA_SET = 31;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Nickname</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__NICKNAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET__TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Relational Remote Data Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONAL_REMOTE_DATA_SET_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RemoteServerImpl <em>Remote Server</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RemoteServerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRemoteServer()
	 * @generated
	 */
	int REMOTE_SERVER = 32;

	/**
	 * The feature id for the '<em><b>LUW Server</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_SERVER__LUW_SERVER = 0;

	/**
	 * The number of structural features of the '<em>Remote Server</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_SERVER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RemoteDataSetImpl <em>Remote Data Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RemoteDataSetImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRemoteDataSet()
	 * @generated
	 */
	int REMOTE_DATA_SET = 33;

	/**
	 * The feature id for the '<em><b>Nickname</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_DATA_SET__NICKNAME = 0;

	/**
	 * The number of structural features of the '<em>Remote Data Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REMOTE_DATA_SET_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl <em>Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWIndex()
	 * @generated
	 */
	int LUW_INDEX = 34;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__EANNOTATIONS = DB2ModelPackage.DB2_INDEX__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__NAME = DB2ModelPackage.DB2_INDEX__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__DEPENDENCIES = DB2ModelPackage.DB2_INDEX__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__DESCRIPTION = DB2ModelPackage.DB2_INDEX__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__LABEL = DB2ModelPackage.DB2_INDEX__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__COMMENTS = DB2ModelPackage.DB2_INDEX__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__EXTENSIONS = DB2ModelPackage.DB2_INDEX__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__PRIVILEGES = DB2ModelPackage.DB2_INDEX__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__SCHEMA = DB2ModelPackage.DB2_INDEX__SCHEMA;

	/**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__CLUSTERED = DB2ModelPackage.DB2_INDEX__CLUSTERED;

	/**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__FILL_FACTOR = DB2ModelPackage.DB2_INDEX__FILL_FACTOR;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__UNIQUE = DB2ModelPackage.DB2_INDEX__UNIQUE;

	/**
	 * The feature id for the '<em><b>System Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__SYSTEM_GENERATED = DB2ModelPackage.DB2_INDEX__SYSTEM_GENERATED;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__MEMBERS = DB2ModelPackage.DB2_INDEX__MEMBERS;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__TABLE = DB2ModelPackage.DB2_INDEX__TABLE;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__FOREIGN_KEY = DB2ModelPackage.DB2_INDEX__FOREIGN_KEY;

	/**
	 * The feature id for the '<em><b>Included Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__INCLUDED_MEMBERS = DB2ModelPackage.DB2_INDEX__INCLUDED_MEMBERS;

	/**
	 * The feature id for the '<em><b>Index Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__INDEX_TYPE = DB2ModelPackage.DB2_INDEX__INDEX_TYPE;

	/**
	 * The feature id for the '<em><b>Bus Period Without Overlap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__BUS_PERIOD_WITHOUT_OVERLAP = DB2ModelPackage.DB2_INDEX__BUS_PERIOD_WITHOUT_OVERLAP;

	/**
	 * The feature id for the '<em><b>Encoded Vector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__ENCODED_VECTOR = DB2ModelPackage.DB2_INDEX__ENCODED_VECTOR;

	/**
	 * The feature id for the '<em><b>DB2 Multidimensional Index</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__DB2_MULTIDIMENSIONAL_INDEX = DB2ModelPackage.DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX;

	/**
	 * The feature id for the '<em><b>PCT Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__PCT_FREE = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Min PCT Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__MIN_PCT_FREE = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Reverse Scan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__REVERSE_SCAN = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Not Partitioned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__NOT_PARTITIONED = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Xml Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__XML_PATTERN = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>As SQL Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__AS_SQL_DATA_TYPE = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>As SQL Data Type Hashed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__AS_SQL_DATA_TYPE_HASHED = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>System Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__SYSTEM_REQUIRED = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Page Split Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__PAGE_SPLIT_TYPE = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Level2 Pct Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__LEVEL2_PCT_FREE = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Min Pct Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__MIN_PCT_USED = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Compress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__COMPRESS = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Collect Stats</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__COLLECT_STATS = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Sampled Stats</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__SAMPLED_STATS = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Detailed Stats</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__DETAILED_STATS = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Ignore Invalid Values</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__IGNORE_INVALID_VALUES = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Exclude Null Keys</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__EXCLUDE_NULL_KEYS = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Tablespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX__TABLESPACE = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 17;

	/**
	 * The number of structural features of the '<em>Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_INDEX_FEATURE_COUNT = DB2ModelPackage.DB2_INDEX_FEATURE_COUNT + 18;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAttributeDefinitionImpl <em>Attribute Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAttributeDefinitionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWAttributeDefinition()
	 * @generated
	 */
	int LUW_ATTRIBUTE_DEFINITION = 35;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__EANNOTATIONS = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__NAME = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__DEPENDENCIES = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__DESCRIPTION = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__LABEL = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__COMMENTS = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__EXTENSIONS = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__PRIVILEGES = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__CONTAINED_TYPE = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__REFERENCED_TYPE = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Scope Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__SCOPE_CHECK = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECK;

	/**
	 * The feature id for the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__SCOPE_CHECKED = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__SCOPE_CHECKED;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__DEFAULT_VALUE = SQLDataTypesPackage.ATTRIBUTE_DEFINITION__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>LOB Logged</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__LOB_LOGGED = SQLDataTypesPackage.ATTRIBUTE_DEFINITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>LOB Compacted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION__LOB_COMPACTED = SQLDataTypesPackage.ATTRIBUTE_DEFINITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Attribute Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ATTRIBUTE_DEFINITION_FEATURE_COUNT = SQLDataTypesPackage.ATTRIBUTE_DEFINITION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl <em>Federated Procedure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getFederatedProcedure()
	 * @generated
	 */
	int FEDERATED_PROCEDURE = 36;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__EANNOTATIONS = DB2ModelPackage.DB2_PROCEDURE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__NAME = DB2ModelPackage.DB2_PROCEDURE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__DEPENDENCIES = DB2ModelPackage.DB2_PROCEDURE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__DESCRIPTION = DB2ModelPackage.DB2_PROCEDURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__LABEL = DB2ModelPackage.DB2_PROCEDURE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__COMMENTS = DB2ModelPackage.DB2_PROCEDURE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__EXTENSIONS = DB2ModelPackage.DB2_PROCEDURE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__PRIVILEGES = DB2ModelPackage.DB2_PROCEDURE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__SPECIFIC_NAME = DB2ModelPackage.DB2_PROCEDURE__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__LANGUAGE = DB2ModelPackage.DB2_PROCEDURE__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__PARAMETER_STYLE = DB2ModelPackage.DB2_PROCEDURE__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__DETERMINISTIC = DB2ModelPackage.DB2_PROCEDURE__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__SQL_DATA_ACCESS = DB2ModelPackage.DB2_PROCEDURE__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__CREATION_TS = DB2ModelPackage.DB2_PROCEDURE__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__LAST_ALTERED_TS = DB2ModelPackage.DB2_PROCEDURE__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__AUTHORIZATION_ID = DB2ModelPackage.DB2_PROCEDURE__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__SECURITY = DB2ModelPackage.DB2_PROCEDURE__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__EXTERNAL_NAME = DB2ModelPackage.DB2_PROCEDURE__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__PARAMETERS = DB2ModelPackage.DB2_PROCEDURE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__SOURCE = DB2ModelPackage.DB2_PROCEDURE__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__SCHEMA = DB2ModelPackage.DB2_PROCEDURE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Max Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__MAX_RESULT_SETS = DB2ModelPackage.DB2_PROCEDURE__MAX_RESULT_SETS;

	/**
	 * The feature id for the '<em><b>Old Save Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__OLD_SAVE_POINT = DB2ModelPackage.DB2_PROCEDURE__OLD_SAVE_POINT;

	/**
	 * The feature id for the '<em><b>Result Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__RESULT_SET = DB2ModelPackage.DB2_PROCEDURE__RESULT_SET;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__FENCED = DB2ModelPackage.DB2_PROCEDURE__FENCED;

	/**
	 * The feature id for the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__THREADSAFE = DB2ModelPackage.DB2_PROCEDURE__THREADSAFE;

	/**
	 * The feature id for the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__DB_INFO = DB2ModelPackage.DB2_PROCEDURE__DB_INFO;

	/**
	 * The feature id for the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__IMPLICIT_SCHEMA = DB2ModelPackage.DB2_PROCEDURE__IMPLICIT_SCHEMA;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__FEDERATED = DB2ModelPackage.DB2_PROCEDURE__FEDERATED;

	/**
	 * The feature id for the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__PARM_CCSID = DB2ModelPackage.DB2_PROCEDURE__PARM_CCSID;

	/**
	 * The feature id for the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__SPECIAL_REGISTER = DB2ModelPackage.DB2_PROCEDURE__SPECIAL_REGISTER;

	/**
	 * The feature id for the '<em><b>Change State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__CHANGE_STATE = DB2ModelPackage.DB2_PROCEDURE__CHANGE_STATE;

	/**
	 * The feature id for the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__DEBUG_ID = DB2ModelPackage.DB2_PROCEDURE__DEBUG_ID;

	/**
	 * The feature id for the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__PROGRAM_TYPE = DB2ModelPackage.DB2_PROCEDURE__PROGRAM_TYPE;

	/**
	 * The feature id for the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__ORIG_SCHEMA_NAME = DB2ModelPackage.DB2_PROCEDURE__ORIG_SCHEMA_NAME;

	/**
	 * The feature id for the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__ORIG_PARM_SIG = DB2ModelPackage.DB2_PROCEDURE__ORIG_PARM_SIG;

	/**
	 * The feature id for the '<em><b>Extended Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__EXTENDED_OPTIONS = DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS;

	/**
	 * The feature id for the '<em><b>Routine Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__ROUTINE_EXTENSIONS = DB2ModelPackage.DB2_PROCEDURE__ROUTINE_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Model Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__MODEL_RESULT_SETS = DB2ModelPackage.DB2_PROCEDURE__MODEL_RESULT_SETS;

	/**
	 * The feature id for the '<em><b>Null Input</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__NULL_INPUT = DB2ModelPackage.DB2_PROCEDURE__NULL_INPUT;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__VERSION = DB2ModelPackage.DB2_PROCEDURE__VERSION;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__DIALECT = DB2ModelPackage.DB2_PROCEDURE__DIALECT;

	/**
	 * The feature id for the '<em><b>External Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__EXTERNAL_ACTION = DB2ModelPackage.DB2_PROCEDURE__EXTERNAL_ACTION;

	/**
	 * The feature id for the '<em><b>Return</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__RETURN = DB2ModelPackage.DB2_PROCEDURE__RETURN;

	/**
	 * The feature id for the '<em><b>Java Options</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__JAVA_OPTIONS = DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS;

	/**
	 * The feature id for the '<em><b>Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__DEPLOY = DB2ModelPackage.DB2_PROCEDURE__DEPLOY;

	/**
	 * The feature id for the '<em><b>Remote Unique Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__REMOTE_UNIQUE_ID = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Remote Server</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__REMOTE_SERVER = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Remote Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__REMOTE_SCHEMA = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Remote Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__REMOTE_PACKAGE = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Remote Procedure Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__REMOTE_PROCEDURE_NAME = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Number Of Parameters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__NUMBER_OF_PARAMETERS = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Result Sets To Client</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__RESULT_SETS_TO_CLIENT = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Number Of Ref Cursors</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__NUMBER_OF_REF_CURSORS = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>All Result Sets To Caller</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__ALL_RESULT_SETS_TO_CALLER = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Federated Procedure</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__FEDERATED_PROCEDURE = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Remote Procedure</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__REMOTE_PROCEDURE = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Federated Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE__FEDERATED_PARAMETER = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Federated Procedure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PROCEDURE_FEATURE_COUNT = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedParameterImpl <em>Federated Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedParameterImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getFederatedParameter()
	 * @generated
	 */
	int FEDERATED_PARAMETER = 37;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__EANNOTATIONS = SQLRoutinesPackage.PARAMETER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__NAME = SQLRoutinesPackage.PARAMETER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__DEPENDENCIES = SQLRoutinesPackage.PARAMETER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__DESCRIPTION = SQLRoutinesPackage.PARAMETER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__LABEL = SQLRoutinesPackage.PARAMETER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__COMMENTS = SQLRoutinesPackage.PARAMETER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__EXTENSIONS = SQLRoutinesPackage.PARAMETER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__PRIVILEGES = SQLRoutinesPackage.PARAMETER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__CONTAINED_TYPE = SQLRoutinesPackage.PARAMETER__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__REFERENCED_TYPE = SQLRoutinesPackage.PARAMETER__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__MODE = SQLRoutinesPackage.PARAMETER__MODE;

	/**
	 * The feature id for the '<em><b>Locator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__LOCATOR = SQLRoutinesPackage.PARAMETER__LOCATOR;

	/**
	 * The feature id for the '<em><b>Routine</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__ROUTINE = SQLRoutinesPackage.PARAMETER__ROUTINE;

	/**
	 * The feature id for the '<em><b>String Type Option</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__STRING_TYPE_OPTION = SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION;

	/**
	 * The feature id for the '<em><b>Remote Code Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__REMOTE_CODE_PAGE = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Remote Param Type ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__REMOTE_PARAM_TYPE_ID = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Federated Procedure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__FEDERATED_PROCEDURE = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Remote Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER__REMOTE_PARAMETER = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Federated Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEDERATED_PARAMETER_FEATURE_COUNT = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionExpressionImpl <em>Partition Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionExpressionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionExpression()
	 * @generated
	 */
	int LUW_PARTITION_EXPRESSION = 38;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Nulls Last</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__NULLS_LAST = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__KEY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__COLUMN = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Partition Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Partition Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EXPRESSION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionElementImpl <em>Partition Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionElementImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionElement()
	 * @generated
	 */
	int LUW_PARTITION_ELEMENT = 39;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Starting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__STARTING = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ending</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__ENDING = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>LUW Partition Expression</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Partition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__PARTITION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Every Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT__EVERY_CLAUSE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Partition Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_ELEMENT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl <em>Data Partition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDataPartition()
	 * @generated
	 */
	int LUW_DATA_PARTITION = 40;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__ID = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Low Inclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__LOW_INCLUSIVE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>High Inclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__HIGH_INCLUSIVE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>LOB Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Regular Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Partition Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__PARTITION_ELEMENTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Index Data Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Data Partition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionKeyImpl <em>Data Partition Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionKeyImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDataPartitionKey()
	 * @generated
	 */
	int LUW_DATA_PARTITION_KEY = 41;

	/**
	 * The feature id for the '<em><b>Partition Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION_KEY__PARTITION_METHOD = 0;

	/**
	 * The feature id for the '<em><b>Partition Expressions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS = 1;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION_KEY__TABLE = 2;

	/**
	 * The number of structural features of the '<em>Data Partition Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATA_PARTITION_KEY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl <em>Database Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDatabasePackage()
	 * @generated
	 */
	int LUW_DATABASE_PACKAGE = 42;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__EANNOTATIONS = DB2ModelPackage.DB2_PACKAGE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__NAME = DB2ModelPackage.DB2_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__DEPENDENCIES = DB2ModelPackage.DB2_PACKAGE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__DESCRIPTION = DB2ModelPackage.DB2_PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__LABEL = DB2ModelPackage.DB2_PACKAGE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__COMMENTS = DB2ModelPackage.DB2_PACKAGE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__EXTENSIONS = DB2ModelPackage.DB2_PACKAGE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__PRIVILEGES = DB2ModelPackage.DB2_PACKAGE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Operative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__OPERATIVE = DB2ModelPackage.DB2_PACKAGE__OPERATIVE;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__VALID = DB2ModelPackage.DB2_PACKAGE__VALID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__VERSION = DB2ModelPackage.DB2_PACKAGE__VERSION;

	/**
	 * The feature id for the '<em><b>Default Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__DEFAULT_SCHEMA = DB2ModelPackage.DB2_PACKAGE__DEFAULT_SCHEMA;

	/**
	 * The feature id for the '<em><b>Sql Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__SQL_PATH = DB2ModelPackage.DB2_PACKAGE__SQL_PATH;

	/**
	 * The feature id for the '<em><b>Reopt Var</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__REOPT_VAR = DB2ModelPackage.DB2_PACKAGE__REOPT_VAR;

	/**
	 * The feature id for the '<em><b>Isolation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__ISOLATION = DB2ModelPackage.DB2_PACKAGE__ISOLATION;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__UNIQUE_ID = DB2ModelPackage.DB2_PACKAGE__UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Last Bind TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__LAST_BIND_TS = DB2ModelPackage.DB2_PACKAGE__LAST_BIND_TS;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__SCHEMA = DB2ModelPackage.DB2_PACKAGE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__STATEMENTS = DB2ModelPackage.DB2_PACKAGE__STATEMENTS;

	/**
	 * The feature id for the '<em><b>Creator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__CREATOR = DB2ModelPackage.DB2_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Binder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__BINDER = DB2ModelPackage.DB2_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cursor Block</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__CURSOR_BLOCK = DB2ModelPackage.DB2_PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Number Of Sections</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__NUMBER_OF_SECTIONS = DB2ModelPackage.DB2_PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Optimization Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__OPTIMIZATION_CLASS = DB2ModelPackage.DB2_PACKAGE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Explain Snapshot</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE__EXPLAIN_SNAPSHOT = DB2ModelPackage.DB2_PACKAGE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Database Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_DATABASE_PACKAGE_FEATURE_COUNT = DB2ModelPackage.DB2_PACKAGE_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleImpl <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModule()
	 * @generated
	 */
	int LUW_MODULE = 43;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__DIALECT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owning Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__OWNING_SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Module Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE__MODULE_OBJECTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject <em>Module Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleObject()
	 * @generated
	 */
	int LUW_MODULE_OBJECT = 44;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_OBJECT__PUBLISHED = 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_OBJECT__MODULE = 1;

	/**
	 * The number of structural features of the '<em>Module Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_OBJECT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleFunctionImpl <em>Module Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleFunctionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleFunction()
	 * @generated
	 */
	int LUW_MODULE_FUNCTION = 45;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__EANNOTATIONS = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__NAME = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__DEPENDENCIES = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__DESCRIPTION = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__LABEL = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__COMMENTS = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__EXTENSIONS = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__PRIVILEGES = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__SPECIFIC_NAME = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__LANGUAGE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__PARAMETER_STYLE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__DETERMINISTIC = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__SQL_DATA_ACCESS = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__CREATION_TS = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__LAST_ALTERED_TS = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__AUTHORIZATION_ID = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__SECURITY = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__EXTERNAL_NAME = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__PARAMETERS = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__SOURCE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__SCHEMA = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__SCHEMA;

	/**
	 * The feature id for the '<em><b>Null Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__NULL_CALL = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__NULL_CALL;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__STATIC = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__STATIC;

	/**
	 * The feature id for the '<em><b>Transform Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__TRANSFORM_GROUP = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__TRANSFORM_GROUP;

	/**
	 * The feature id for the '<em><b>Type Preserving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__TYPE_PRESERVING = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__TYPE_PRESERVING;

	/**
	 * The feature id for the '<em><b>Mutator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__MUTATOR = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__MUTATOR;

	/**
	 * The feature id for the '<em><b>Return Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__RETURN_TABLE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__RETURN_TABLE;

	/**
	 * The feature id for the '<em><b>Return Scalar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__RETURN_SCALAR = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__RETURN_SCALAR;

	/**
	 * The feature id for the '<em><b>Return Cast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__RETURN_CAST = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__RETURN_CAST;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__FENCED = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__FENCED;

	/**
	 * The feature id for the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__THREADSAFE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__THREADSAFE;

	/**
	 * The feature id for the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__DB_INFO = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__DB_INFO;

	/**
	 * The feature id for the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__IMPLICIT_SCHEMA = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__IMPLICIT_SCHEMA;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__FEDERATED = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__FEDERATED;

	/**
	 * The feature id for the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__PARM_CCSID = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__PARM_CCSID;

	/**
	 * The feature id for the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__SPECIAL_REGISTER = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__SPECIAL_REGISTER;

	/**
	 * The feature id for the '<em><b>Change State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__CHANGE_STATE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__CHANGE_STATE;

	/**
	 * The feature id for the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__DEBUG_ID = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__DEBUG_ID;

	/**
	 * The feature id for the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__PROGRAM_TYPE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__PROGRAM_TYPE;

	/**
	 * The feature id for the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__ORIG_SCHEMA_NAME = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__ORIG_SCHEMA_NAME;

	/**
	 * The feature id for the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__ORIG_PARM_SIG = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__ORIG_PARM_SIG;

	/**
	 * The feature id for the '<em><b>Extended Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__EXTENDED_OPTIONS = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__EXTENDED_OPTIONS;

	/**
	 * The feature id for the '<em><b>Routine Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__ROUTINE_EXTENSIONS = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__ROUTINE_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Final Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__FINAL_CALL = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__FINAL_CALL;

	/**
	 * The feature id for the '<em><b>Scratch Pad</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__SCRATCH_PAD = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__SCRATCH_PAD;

	/**
	 * The feature id for the '<em><b>Scratch Pad Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__SCRATCH_PAD_LENGTH = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__SCRATCH_PAD_LENGTH;

	/**
	 * The feature id for the '<em><b>Function Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__FUNCTION_TYPE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__FUNCTION_TYPE;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__PREDICATE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__PREDICATE;

	/**
	 * The feature id for the '<em><b>External Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__EXTERNAL_ACTION = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__EXTERNAL_ACTION;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__CARDINALITY = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__CARDINALITY;

	/**
	 * The feature id for the '<em><b>Allow Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__ALLOW_PARALLEL = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__ALLOW_PARALLEL;

	/**
	 * The feature id for the '<em><b>Return Clause</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__RETURN_CLAUSE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__RETURN_CLAUSE;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__ORIGIN = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__ORIGIN;

	/**
	 * The feature id for the '<em><b>Inherit Lock Request</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__INHERIT_LOCK_REQUEST = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__INHERIT_LOCK_REQUEST;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__DIALECT = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__DIALECT;

	/**
	 * The feature id for the '<em><b>Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__INLINE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__INLINE;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__VERSION = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__VERSION;

	/**
	 * The feature id for the '<em><b>Secured</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__SECURED = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION__SECURED;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__PUBLISHED = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__MODULE = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Implemented</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION__IMPLEMENTED = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Module Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_FUNCTION_FEATURE_COUNT = DB2ModelPackage.DB2_USER_DEFINED_FUNCTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleProcedureImpl <em>Module Procedure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleProcedureImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleProcedure()
	 * @generated
	 */
	int LUW_MODULE_PROCEDURE = 46;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__EANNOTATIONS = DB2ModelPackage.DB2_PROCEDURE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__NAME = DB2ModelPackage.DB2_PROCEDURE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__DEPENDENCIES = DB2ModelPackage.DB2_PROCEDURE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__DESCRIPTION = DB2ModelPackage.DB2_PROCEDURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__LABEL = DB2ModelPackage.DB2_PROCEDURE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__COMMENTS = DB2ModelPackage.DB2_PROCEDURE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__EXTENSIONS = DB2ModelPackage.DB2_PROCEDURE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__PRIVILEGES = DB2ModelPackage.DB2_PROCEDURE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__SPECIFIC_NAME = DB2ModelPackage.DB2_PROCEDURE__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__LANGUAGE = DB2ModelPackage.DB2_PROCEDURE__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__PARAMETER_STYLE = DB2ModelPackage.DB2_PROCEDURE__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__DETERMINISTIC = DB2ModelPackage.DB2_PROCEDURE__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__SQL_DATA_ACCESS = DB2ModelPackage.DB2_PROCEDURE__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__CREATION_TS = DB2ModelPackage.DB2_PROCEDURE__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__LAST_ALTERED_TS = DB2ModelPackage.DB2_PROCEDURE__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__AUTHORIZATION_ID = DB2ModelPackage.DB2_PROCEDURE__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__SECURITY = DB2ModelPackage.DB2_PROCEDURE__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__EXTERNAL_NAME = DB2ModelPackage.DB2_PROCEDURE__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__PARAMETERS = DB2ModelPackage.DB2_PROCEDURE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__SOURCE = DB2ModelPackage.DB2_PROCEDURE__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__SCHEMA = DB2ModelPackage.DB2_PROCEDURE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Max Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__MAX_RESULT_SETS = DB2ModelPackage.DB2_PROCEDURE__MAX_RESULT_SETS;

	/**
	 * The feature id for the '<em><b>Old Save Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__OLD_SAVE_POINT = DB2ModelPackage.DB2_PROCEDURE__OLD_SAVE_POINT;

	/**
	 * The feature id for the '<em><b>Result Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__RESULT_SET = DB2ModelPackage.DB2_PROCEDURE__RESULT_SET;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__FENCED = DB2ModelPackage.DB2_PROCEDURE__FENCED;

	/**
	 * The feature id for the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__THREADSAFE = DB2ModelPackage.DB2_PROCEDURE__THREADSAFE;

	/**
	 * The feature id for the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__DB_INFO = DB2ModelPackage.DB2_PROCEDURE__DB_INFO;

	/**
	 * The feature id for the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__IMPLICIT_SCHEMA = DB2ModelPackage.DB2_PROCEDURE__IMPLICIT_SCHEMA;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__FEDERATED = DB2ModelPackage.DB2_PROCEDURE__FEDERATED;

	/**
	 * The feature id for the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__PARM_CCSID = DB2ModelPackage.DB2_PROCEDURE__PARM_CCSID;

	/**
	 * The feature id for the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__SPECIAL_REGISTER = DB2ModelPackage.DB2_PROCEDURE__SPECIAL_REGISTER;

	/**
	 * The feature id for the '<em><b>Change State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__CHANGE_STATE = DB2ModelPackage.DB2_PROCEDURE__CHANGE_STATE;

	/**
	 * The feature id for the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__DEBUG_ID = DB2ModelPackage.DB2_PROCEDURE__DEBUG_ID;

	/**
	 * The feature id for the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__PROGRAM_TYPE = DB2ModelPackage.DB2_PROCEDURE__PROGRAM_TYPE;

	/**
	 * The feature id for the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__ORIG_SCHEMA_NAME = DB2ModelPackage.DB2_PROCEDURE__ORIG_SCHEMA_NAME;

	/**
	 * The feature id for the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__ORIG_PARM_SIG = DB2ModelPackage.DB2_PROCEDURE__ORIG_PARM_SIG;

	/**
	 * The feature id for the '<em><b>Extended Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__EXTENDED_OPTIONS = DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS;

	/**
	 * The feature id for the '<em><b>Routine Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__ROUTINE_EXTENSIONS = DB2ModelPackage.DB2_PROCEDURE__ROUTINE_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Model Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__MODEL_RESULT_SETS = DB2ModelPackage.DB2_PROCEDURE__MODEL_RESULT_SETS;

	/**
	 * The feature id for the '<em><b>Null Input</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__NULL_INPUT = DB2ModelPackage.DB2_PROCEDURE__NULL_INPUT;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__VERSION = DB2ModelPackage.DB2_PROCEDURE__VERSION;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__DIALECT = DB2ModelPackage.DB2_PROCEDURE__DIALECT;

	/**
	 * The feature id for the '<em><b>External Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__EXTERNAL_ACTION = DB2ModelPackage.DB2_PROCEDURE__EXTERNAL_ACTION;

	/**
	 * The feature id for the '<em><b>Return</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__RETURN = DB2ModelPackage.DB2_PROCEDURE__RETURN;

	/**
	 * The feature id for the '<em><b>Java Options</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__JAVA_OPTIONS = DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS;

	/**
	 * The feature id for the '<em><b>Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__DEPLOY = DB2ModelPackage.DB2_PROCEDURE__DEPLOY;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__PUBLISHED = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__MODULE = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Implemented</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE__IMPLEMENTED = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Module Procedure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_PROCEDURE_FEATURE_COUNT = DB2ModelPackage.DB2_PROCEDURE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleConditionImpl <em>Module Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleConditionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleCondition()
	 * @generated
	 */
	int LUW_MODULE_CONDITION = 47;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__PUBLISHED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__MODULE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sqlstate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION__SQLSTATE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Module Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CONDITION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGlobalVariableImpl <em>Global Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGlobalVariableImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGlobalVariable()
	 * @generated
	 */
	int LUW_GLOBAL_VARIABLE = 48;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__EANNOTATIONS = SQLSchemaPackage.TYPED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__NAME = SQLSchemaPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__DEPENDENCIES = SQLSchemaPackage.TYPED_ELEMENT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__DESCRIPTION = SQLSchemaPackage.TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__LABEL = SQLSchemaPackage.TYPED_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__COMMENTS = SQLSchemaPackage.TYPED_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__EXTENSIONS = SQLSchemaPackage.TYPED_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__PRIVILEGES = SQLSchemaPackage.TYPED_ELEMENT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__CONTAINED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__REFERENCED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__DEFAULT_VALUE = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__IS_CONSTANT = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE__SCHEMA = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Global Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_GLOBAL_VARIABLE_FEATURE_COUNT = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleType <em>Module Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleType()
	 * @generated
	 */
	int LUW_MODULE_TYPE = 49;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_TYPE__PUBLISHED = LUW_MODULE_OBJECT__PUBLISHED;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_TYPE__MODULE = LUW_MODULE_OBJECT__MODULE;

	/**
	 * The number of structural features of the '<em>Module Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_TYPE_FEATURE_COUNT = LUW_MODULE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRowDataTypeImpl <em>Row Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRowDataTypeImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWRowDataType()
	 * @generated
	 */
	int LUW_ROW_DATA_TYPE = 55;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__EANNOTATIONS = SQLDataTypesPackage.USER_DEFINED_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__NAME = SQLDataTypesPackage.USER_DEFINED_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__DEPENDENCIES = SQLDataTypesPackage.USER_DEFINED_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__DESCRIPTION = SQLDataTypesPackage.USER_DEFINED_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__LABEL = SQLDataTypesPackage.USER_DEFINED_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__COMMENTS = SQLDataTypesPackage.USER_DEFINED_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__EXTENSIONS = SQLDataTypesPackage.USER_DEFINED_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__PRIVILEGES = SQLDataTypesPackage.USER_DEFINED_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__SCHEMA = SQLDataTypesPackage.USER_DEFINED_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__ORDERING = SQLDataTypesPackage.USER_DEFINED_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE__FIELDS = SQLDataTypesPackage.USER_DEFINED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Row Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ROW_DATA_TYPE_FEATURE_COUNT = SQLDataTypesPackage.USER_DEFINED_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleRowDataTypeImpl <em>Module Row Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleRowDataTypeImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleRowDataType()
	 * @generated
	 */
	int LUW_MODULE_ROW_DATA_TYPE = 50;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__EANNOTATIONS = LUW_ROW_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__NAME = LUW_ROW_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__DEPENDENCIES = LUW_ROW_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__DESCRIPTION = LUW_ROW_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__LABEL = LUW_ROW_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__COMMENTS = LUW_ROW_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__EXTENSIONS = LUW_ROW_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__PRIVILEGES = LUW_ROW_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__SCHEMA = LUW_ROW_DATA_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__ORDERING = LUW_ROW_DATA_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__FIELDS = LUW_ROW_DATA_TYPE__FIELDS;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__PUBLISHED = LUW_ROW_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE__MODULE = LUW_ROW_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Module Row Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ROW_DATA_TYPE_FEATURE_COUNT = LUW_ROW_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWArrayDataTypeImpl <em>Array Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWArrayDataTypeImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWArrayDataType()
	 * @generated
	 */
	int LUW_ARRAY_DATA_TYPE = 54;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__EANNOTATIONS = SQLDataTypesPackage.ARRAY_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__NAME = SQLDataTypesPackage.ARRAY_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__DEPENDENCIES = SQLDataTypesPackage.ARRAY_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__DESCRIPTION = SQLDataTypesPackage.ARRAY_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__LABEL = SQLDataTypesPackage.ARRAY_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__COMMENTS = SQLDataTypesPackage.ARRAY_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__EXTENSIONS = SQLDataTypesPackage.ARRAY_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__PRIVILEGES = SQLDataTypesPackage.ARRAY_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__ELEMENT_TYPE = SQLDataTypesPackage.ARRAY_DATA_TYPE__ELEMENT_TYPE;

	/**
	 * The feature id for the '<em><b>Max Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__MAX_CARDINALITY = SQLDataTypesPackage.ARRAY_DATA_TYPE__MAX_CARDINALITY;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__SCHEMA = SQLDataTypesPackage.ARRAY_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__ORDERING = SQLDataTypesPackage.ARRAY_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Array Index Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE = SQLDataTypesPackage.ARRAY_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Array Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_ARRAY_DATA_TYPE_FEATURE_COUNT = SQLDataTypesPackage.ARRAY_DATA_TYPE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleArrayDataTypeImpl <em>Module Array Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleArrayDataTypeImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleArrayDataType()
	 * @generated
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE = 51;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__EANNOTATIONS = LUW_ARRAY_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__NAME = LUW_ARRAY_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__DEPENDENCIES = LUW_ARRAY_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__DESCRIPTION = LUW_ARRAY_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__LABEL = LUW_ARRAY_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__COMMENTS = LUW_ARRAY_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__EXTENSIONS = LUW_ARRAY_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__PRIVILEGES = LUW_ARRAY_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__ELEMENT_TYPE = LUW_ARRAY_DATA_TYPE__ELEMENT_TYPE;

	/**
	 * The feature id for the '<em><b>Max Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__MAX_CARDINALITY = LUW_ARRAY_DATA_TYPE__MAX_CARDINALITY;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__SCHEMA = LUW_ARRAY_DATA_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__ORDERING = LUW_ARRAY_DATA_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Array Index Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE = LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__PUBLISHED = LUW_ARRAY_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE__MODULE = LUW_ARRAY_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Module Array Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_ARRAY_DATA_TYPE_FEATURE_COUNT = LUW_ARRAY_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleDistinctTypeImpl <em>Module Distinct Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleDistinctTypeImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleDistinctType()
	 * @generated
	 */
	int LUW_MODULE_DISTINCT_TYPE = 52;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__EANNOTATIONS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__NAME = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__DEPENDENCIES = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__DESCRIPTION = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__LABEL = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__COMMENTS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__EXTENSIONS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__PRIVILEGES = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__SCHEMA = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__ORDERING = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Predefined Representation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__PREDEFINED_REPRESENTATION = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__PUBLISHED = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE__MODULE = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Module Distinct Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_DISTINCT_TYPE_FEATURE_COUNT = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleGlobalVariableImpl <em>Module Global Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleGlobalVariableImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleGlobalVariable()
	 * @generated
	 */
	int LUW_MODULE_GLOBAL_VARIABLE = 53;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__EANNOTATIONS = LUW_GLOBAL_VARIABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__NAME = LUW_GLOBAL_VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__DEPENDENCIES = LUW_GLOBAL_VARIABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__DESCRIPTION = LUW_GLOBAL_VARIABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__LABEL = LUW_GLOBAL_VARIABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__COMMENTS = LUW_GLOBAL_VARIABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__EXTENSIONS = LUW_GLOBAL_VARIABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__PRIVILEGES = LUW_GLOBAL_VARIABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__CONTAINED_TYPE = LUW_GLOBAL_VARIABLE__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__REFERENCED_TYPE = LUW_GLOBAL_VARIABLE__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__DEFAULT_VALUE = LUW_GLOBAL_VARIABLE__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Is Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__IS_CONSTANT = LUW_GLOBAL_VARIABLE__IS_CONSTANT;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__SCHEMA = LUW_GLOBAL_VARIABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__PUBLISHED = LUW_GLOBAL_VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE__MODULE = LUW_GLOBAL_VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Module Global Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_GLOBAL_VARIABLE_FEATURE_COUNT = LUW_GLOBAL_VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl <em>PLSQL Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getPLSQLPackage()
	 * @generated
	 */
	int PLSQL_PACKAGE = 56;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__EANNOTATIONS = LUW_MODULE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__NAME = LUW_MODULE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__DEPENDENCIES = LUW_MODULE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__DESCRIPTION = LUW_MODULE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__LABEL = LUW_MODULE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__COMMENTS = LUW_MODULE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__EXTENSIONS = LUW_MODULE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__PRIVILEGES = LUW_MODULE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__DIALECT = LUW_MODULE__DIALECT;

	/**
	 * The feature id for the '<em><b>Owning Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__OWNING_SCHEMA = LUW_MODULE__OWNING_SCHEMA;

	/**
	 * The feature id for the '<em><b>Module Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__MODULE_OBJECTS = LUW_MODULE__MODULE_OBJECTS;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__SPECIFIC_NAME = LUW_MODULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__LANGUAGE = LUW_MODULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__PARAMETER_STYLE = LUW_MODULE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__DETERMINISTIC = LUW_MODULE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__SQL_DATA_ACCESS = LUW_MODULE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__CREATION_TS = LUW_MODULE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__LAST_ALTERED_TS = LUW_MODULE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__AUTHORIZATION_ID = LUW_MODULE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__SECURITY = LUW_MODULE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__EXTERNAL_NAME = LUW_MODULE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__PARAMETERS = LUW_MODULE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__SOURCE = LUW_MODULE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__SCHEMA = LUW_MODULE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__FENCED = LUW_MODULE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__THREADSAFE = LUW_MODULE_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__DB_INFO = LUW_MODULE_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__IMPLICIT_SCHEMA = LUW_MODULE_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__FEDERATED = LUW_MODULE_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__PARM_CCSID = LUW_MODULE_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__SPECIAL_REGISTER = LUW_MODULE_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Change State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__CHANGE_STATE = LUW_MODULE_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__DEBUG_ID = LUW_MODULE_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__PROGRAM_TYPE = LUW_MODULE_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__ORIG_SCHEMA_NAME = LUW_MODULE_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__ORIG_PARM_SIG = LUW_MODULE_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Extended Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__EXTENDED_OPTIONS = LUW_MODULE_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Routine Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__ROUTINE_EXTENSIONS = LUW_MODULE_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Package Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE__PACKAGE_BODY = LUW_MODULE_FEATURE_COUNT + 27;

	/**
	 * The number of structural features of the '<em>PLSQL Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_FEATURE_COUNT = LUW_MODULE_FEATURE_COUNT + 28;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageBodyImpl <em>PLSQL Package Body</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageBodyImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getPLSQLPackageBody()
	 * @generated
	 */
	int PLSQL_PACKAGE_BODY = 57;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__EANNOTATIONS = SQLRoutinesPackage.SOURCE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__NAME = SQLRoutinesPackage.SOURCE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__DEPENDENCIES = SQLRoutinesPackage.SOURCE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__DESCRIPTION = SQLRoutinesPackage.SOURCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__LABEL = SQLRoutinesPackage.SOURCE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__COMMENTS = SQLRoutinesPackage.SOURCE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__EXTENSIONS = SQLRoutinesPackage.SOURCE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__PRIVILEGES = SQLRoutinesPackage.SOURCE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__BODY = SQLRoutinesPackage.SOURCE__BODY;

	/**
	 * The feature id for the '<em><b>Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY__PACKAGE = SQLRoutinesPackage.SOURCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>PLSQL Package Body</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLSQL_PACKAGE_BODY_FEATURE_COUNT = SQLRoutinesPackage.SOURCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWCursorDataTypeImpl <em>Cursor Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWCursorDataTypeImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWCursorDataType()
	 * @generated
	 */
	int LUW_CURSOR_DATA_TYPE = 58;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__EANNOTATIONS = SQLDataTypesPackage.USER_DEFINED_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__NAME = SQLDataTypesPackage.USER_DEFINED_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__DEPENDENCIES = SQLDataTypesPackage.USER_DEFINED_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__DESCRIPTION = SQLDataTypesPackage.USER_DEFINED_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__LABEL = SQLDataTypesPackage.USER_DEFINED_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__COMMENTS = SQLDataTypesPackage.USER_DEFINED_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__EXTENSIONS = SQLDataTypesPackage.USER_DEFINED_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__PRIVILEGES = SQLDataTypesPackage.USER_DEFINED_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__SCHEMA = SQLDataTypesPackage.USER_DEFINED_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__ORDERING = SQLDataTypesPackage.USER_DEFINED_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Row Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE__ROW_TYPE = SQLDataTypesPackage.USER_DEFINED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Cursor Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_CURSOR_DATA_TYPE_FEATURE_COUNT = SQLDataTypesPackage.USER_DEFINED_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleCursorDataTypeImpl <em>Module Cursor Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleCursorDataTypeImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleCursorDataType()
	 * @generated
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE = 59;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__EANNOTATIONS = LUW_CURSOR_DATA_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__NAME = LUW_CURSOR_DATA_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__DEPENDENCIES = LUW_CURSOR_DATA_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__DESCRIPTION = LUW_CURSOR_DATA_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__LABEL = LUW_CURSOR_DATA_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__COMMENTS = LUW_CURSOR_DATA_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__EXTENSIONS = LUW_CURSOR_DATA_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__PRIVILEGES = LUW_CURSOR_DATA_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__SCHEMA = LUW_CURSOR_DATA_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__ORDERING = LUW_CURSOR_DATA_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Row Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__ROW_TYPE = LUW_CURSOR_DATA_TYPE__ROW_TYPE;

	/**
	 * The feature id for the '<em><b>Published</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__PUBLISHED = LUW_CURSOR_DATA_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE__MODULE = LUW_CURSOR_DATA_TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Module Cursor Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MODULE_CURSOR_DATA_TYPE_FEATURE_COUNT = LUW_CURSOR_DATA_TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolSizeExceptionImpl <em>Buffer Pool Size Exception</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolSizeExceptionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWBufferPoolSizeException()
	 * @generated
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION = 60;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Buffer Pool</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Partitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Buffer Pool Size Exception</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_BUFFER_POOL_SIZE_EXCEPTION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMemberImpl <em>Member</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMemberImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWMember()
	 * @generated
	 */
	int LUW_MEMBER = 61;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__EANNOTATIONS = DB2ModelPackage.DB2_MEMBER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__NAME = DB2ModelPackage.DB2_MEMBER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__DEPENDENCIES = DB2ModelPackage.DB2_MEMBER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__DESCRIPTION = DB2ModelPackage.DB2_MEMBER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__LABEL = DB2ModelPackage.DB2_MEMBER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__COMMENTS = DB2ModelPackage.DB2_MEMBER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__EXTENSIONS = DB2ModelPackage.DB2_MEMBER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__PRIVILEGES = DB2ModelPackage.DB2_MEMBER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__ID = DB2ModelPackage.DB2_MEMBER__ID;

	/**
	 * The feature id for the '<em><b>Home Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__HOME_HOST = DB2ModelPackage.DB2_MEMBER__HOME_HOST;

	/**
	 * The feature id for the '<em><b>Current Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__CURRENT_HOST = DB2ModelPackage.DB2_MEMBER__CURRENT_HOST;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__STATE = DB2ModelPackage.DB2_MEMBER__STATE;

	/**
	 * The feature id for the '<em><b>Cluster</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__CLUSTER = DB2ModelPackage.DB2_MEMBER__CLUSTER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__TYPE = DB2ModelPackage.DB2_MEMBER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Alert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__ALERT = DB2ModelPackage.DB2_MEMBER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Db Partition Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__DB_PARTITION_NUM = DB2ModelPackage.DB2_MEMBER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Logical Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__LOGICAL_PORT = DB2ModelPackage.DB2_MEMBER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Net Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER__NET_NAME = DB2ModelPackage.DB2_MEMBER_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Member</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_MEMBER_FEATURE_COUNT = DB2ModelPackage.DB2_MEMBER_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityPolicyImpl <em>Security Policy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityPolicyImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityPolicy()
	 * @generated
	 */
	int LUW_SECURITY_POLICY = 62;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Not Authorized Write</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__NOT_AUTHORIZED_WRITE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Components</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__COMPONENTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Labels</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__LABELS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY__TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Security Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_POLICY_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentImpl <em>Security Label Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabelComponent()
	 * @generated
	 */
	int LUW_SECURITY_LABEL_COMPONENT = 63;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>LUW Security Policy</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT__ELEMENTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Security Label Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelImpl <em>Security Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabel()
	 * @generated
	 */
	int LUW_SECURITY_LABEL = 64;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Security Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__SECURITY_LABEL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Policy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL__POLICY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Security Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentElementImpl <em>Security Label Component Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentElementImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabelComponentElement()
	 * @generated
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT = 65;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__VALUE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__PARENT_VALUE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>LUW Security Label Component</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Security Label Component Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_SECURITY_LABEL_COMPONENT_ELEMENT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl <em>Storage Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWStorageGroup()
	 * @generated
	 */
	int LUW_STORAGE_GROUP = 66;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Storage Paths</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__STORAGE_PATHS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Overhead</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__OVERHEAD = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Device Read Rate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__DEVICE_READ_RATE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Data Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__DATA_TAG = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__DEFAULT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Table Spaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP__TABLE_SPACES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Storage Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_STORAGE_GROUP_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryStorageTableImpl <em>Temporary Storage Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryStorageTableImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTemporaryStorageTable()
	 * @generated
	 */
	int LUW_TEMPORARY_STORAGE_TABLE = 67;

	/**
	 * The feature id for the '<em><b>Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY = 0;

	/**
	 * The feature id for the '<em><b>User Temporary Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE = 1;

	/**
	 * The number of structural features of the '<em>Temporary Storage Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_STORAGE_TABLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryTableImpl <em>Temporary Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryTableImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTemporaryTable()
	 * @generated
	 */
	int LUW_TEMPORARY_TABLE = 68;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__EANNOTATIONS = SQLTablesPackage.TEMPORARY_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__NAME = SQLTablesPackage.TEMPORARY_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__DEPENDENCIES = SQLTablesPackage.TEMPORARY_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__DESCRIPTION = SQLTablesPackage.TEMPORARY_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__LABEL = SQLTablesPackage.TEMPORARY_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__COMMENTS = SQLTablesPackage.TEMPORARY_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__EXTENSIONS = SQLTablesPackage.TEMPORARY_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__PRIVILEGES = SQLTablesPackage.TEMPORARY_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__COLUMNS = SQLTablesPackage.TEMPORARY_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__SUPERTABLE = SQLTablesPackage.TEMPORARY_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__SUBTABLES = SQLTablesPackage.TEMPORARY_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__SCHEMA = SQLTablesPackage.TEMPORARY_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__UDT = SQLTablesPackage.TEMPORARY_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__TRIGGERS = SQLTablesPackage.TEMPORARY_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__INDEX = SQLTablesPackage.TEMPORARY_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.TEMPORARY_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__INSERTABLE = SQLTablesPackage.TEMPORARY_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__UPDATABLE = SQLTablesPackage.TEMPORARY_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__CONSTRAINTS = SQLTablesPackage.TEMPORARY_TABLE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__REFERENCING_FOREIGN_KEYS = SQLTablesPackage.TEMPORARY_TABLE__REFERENCING_FOREIGN_KEYS;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__LOCAL = SQLTablesPackage.TEMPORARY_TABLE__LOCAL;

	/**
	 * The feature id for the '<em><b>Delete On Commit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__DELETE_ON_COMMIT = SQLTablesPackage.TEMPORARY_TABLE__DELETE_ON_COMMIT;

	/**
	 * The feature id for the '<em><b>Partition Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__PARTITION_KEY = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>User Temporary Table Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__USER_TEMPORARY_TABLE_SPACE = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__TABLE = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Log Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE__LOG_OPTION = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Temporary Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_TEMPORARY_TABLE_FEATURE_COUNT = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.ArrayIndexElementTypeImpl <em>Array Index Element Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.ArrayIndexElementTypeImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getArrayIndexElementType()
	 * @generated
	 */
	int ARRAY_INDEX_ELEMENT_TYPE = 69;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__EANNOTATIONS = SQLDataTypesPackage.ELEMENT_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__NAME = SQLDataTypesPackage.ELEMENT_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__DEPENDENCIES = SQLDataTypesPackage.ELEMENT_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__DESCRIPTION = SQLDataTypesPackage.ELEMENT_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__LABEL = SQLDataTypesPackage.ELEMENT_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__COMMENTS = SQLDataTypesPackage.ELEMENT_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__EXTENSIONS = SQLDataTypesPackage.ELEMENT_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__PRIVILEGES = SQLDataTypesPackage.ELEMENT_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__CONTAINED_TYPE = SQLDataTypesPackage.ELEMENT_TYPE__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__REFERENCED_TYPE = SQLDataTypesPackage.ELEMENT_TYPE__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Collection Data Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__COLLECTION_DATA_TYPE = SQLDataTypesPackage.ELEMENT_TYPE__COLLECTION_DATA_TYPE;

	/**
	 * The feature id for the '<em><b>LUW Array Data Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE = SQLDataTypesPackage.ELEMENT_TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array Index Element Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INDEX_ELEMENT_TYPE_FEATURE_COUNT = SQLDataTypesPackage.ELEMENT_TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionEveryClauseElementImpl <em>Partition Every Clause Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionEveryClauseElementImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionEveryClauseElement()
	 * @generated
	 */
	int LUW_PARTITION_EVERY_CLAUSE_ELEMENT = 70;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EVERY_CLAUSE_ELEMENT__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EVERY_CLAUSE_ELEMENT__DURATION = 1;

	/**
	 * The feature id for the '<em><b>LUW Partition Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT = 2;

	/**
	 * The number of structural features of the '<em>Partition Every Clause Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUW_PARTITION_EVERY_CLAUSE_ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType <em>Container Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWContainerType()
	 * @generated
	 */
	int LUW_CONTAINER_TYPE = 71;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType <em>Page Size Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getPageSizeType()
	 * @generated
	 */
	int PAGE_SIZE_TYPE = 72;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType <em>Buffer Pool Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getBufferPoolType()
	 * @generated
	 */
	int BUFFER_POOL_TYPE = 73;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType <em>Table Space Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getTableSpaceType()
	 * @generated
	 */
	int TABLE_SPACE_TYPE = 74;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType <em>Management Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getManagementType()
	 * @generated
	 */
	int MANAGEMENT_TYPE = 75;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.CheckOptionType <em>Check Option Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.CheckOptionType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getCheckOptionType()
	 * @generated
	 */
	int CHECK_OPTION_TYPE = 76;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod <em>Partition Method</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getPartitionMethod()
	 * @generated
	 */
	int PARTITION_METHOD = 77;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.MaintenanceType <em>Maintenance Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.MaintenanceType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getMaintenanceType()
	 * @generated
	 */
	int MAINTENANCE_TYPE = 78;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RefreshType <em>Refresh Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RefreshType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRefreshType()
	 * @generated
	 */
	int REFRESH_TYPE = 79;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType <em>Wrapper Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getWrapperType()
	 * @generated
	 */
	int WRAPPER_TYPE = 80;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod <em>Data Partition Method</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getDataPartitionMethod()
	 * @generated
	 */
	int DATA_PARTITION_METHOD = 81;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType <em>Cursor Block Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getCursorBlockType()
	 * @generated
	 */
	int CURSOR_BLOCK_TYPE = 82;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType <em>Explain Snaphot Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getExplainSnaphotType()
	 * @generated
	 */
	int EXPLAIN_SNAPHOT_TYPE = 83;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType <em>File System Caching Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getFileSystemCachingType()
	 * @generated
	 */
	int FILE_SYSTEM_CACHING_TYPE = 84;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType <em>Index Page Split Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWIndexPageSplitType()
	 * @generated
	 */
	int LUW_INDEX_PAGE_SPLIT_TYPE = 85;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType <em>Index Compress Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWIndexCompressType()
	 * @generated
	 */
	int LUW_INDEX_COMPRESS_TYPE = 86;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType <em>System Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getSystemType()
	 * @generated
	 */
	int SYSTEM_TYPE = 87;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType <em>Average Table Size Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getAverageTableSizeType()
	 * @generated
	 */
	int AVERAGE_TABLE_SIZE_TYPE = 88;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode <em>Storage Table Compression Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWStorageTableCompressionMode()
	 * @generated
	 */
	int LUW_STORAGE_TABLE_COMPRESSION_MODE = 89;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType <em>Member Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWMemberType()
	 * @generated
	 */
	int LUW_MEMBER_TYPE = 90;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.MemberStateType <em>Member State Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.MemberStateType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getMemberStateType()
	 * @generated
	 */
	int MEMBER_STATE_TYPE = 91;


	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType <em>Security Label Component Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabelComponentType()
	 * @generated
	 */
	int LUW_SECURITY_LABEL_COMPONENT_TYPE = 92;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction <em>Security Label Not Authorized Write Action</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabelNotAuthorizedWriteAction()
	 * @generated
	 */
	int LUW_SECURITY_LABEL_NOT_AUTHORIZED_WRITE_ACTION = 93;


	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource <em>Federated Data Source</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWFederatedDataSource()
	 * @generated
	 */
	int LUW_FEDERATED_DATA_SOURCE = 94;


	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption <em>Temporary Table Logging Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTemporaryTableLoggingOption()
	 * @generated
	 */
	int LUW_TEMPORARY_TABLE_LOGGING_OPTION = 95;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup <em>Partition Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Partition Group</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup
	 * @generated
	 */
	EClass getLUWPartitionGroup();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getPartitions <em>Partitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partitions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getPartitions()
	 * @see #getLUWPartitionGroup()
	 * @generated
	 */
	EReference getLUWPartitionGroup_Partitions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getTableSpaces <em>Table Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Table Spaces</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getTableSpaces()
	 * @see #getLUWPartitionGroup()
	 * @generated
	 */
	EReference getLUWPartitionGroup_TableSpaces();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getDatabase()
	 * @see #getLUWPartitionGroup()
	 * @generated
	 */
	EReference getLUWPartitionGroup_Database();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Buffer Pool</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup#getBufferPool()
	 * @see #getLUWPartitionGroup()
	 * @generated
	 */
	EReference getLUWPartitionGroup_BufferPool();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace <em>Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Space</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace
	 * @generated
	 */
	EClass getLUWTableSpace();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTemporaryStorageTables <em>Temporary Storage Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Temporary Storage Tables</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTemporaryStorageTables()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_TemporaryStorageTables();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTablespaceType <em>Tablespace Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tablespace Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTablespaceType()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_TablespaceType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getManagementType <em>Management Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Management Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getManagementType()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_ManagementType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getExtentSize <em>Extent Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extent Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getExtentSize()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_ExtentSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getPreFetchSize <em>Pre Fetch Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pre Fetch Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getPreFetchSize()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_PreFetchSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getOverhead <em>Overhead</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overhead</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getOverhead()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_Overhead();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTransferRate <em>Transfer Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transfer Rate</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTransferRate()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_TransferRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isRecoverDroppedTableOn <em>Recover Dropped Table On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recover Dropped Table On</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isRecoverDroppedTableOn()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_RecoverDroppedTableOn();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getPageSize <em>Page Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getPageSize()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_PageSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getSize()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_Size();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isAutoResize <em>Auto Resize</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auto Resize</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isAutoResize()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_AutoResize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getInitialSize <em>Initial Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getInitialSize()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_InitialSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreaseSize <em>Increase Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Increase Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreaseSize()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_IncreaseSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getMaximumSize <em>Maximum Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getMaximumSize()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_MaximumSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getInitialSizeUnit <em>Initial Size Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Size Unit</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getInitialSizeUnit()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_InitialSizeUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getMaximumSizeUnit <em>Maximum Size Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Size Unit</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getMaximumSizeUnit()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_MaximumSizeUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreaseSizeUnit <em>Increase Size Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Increase Size Unit</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreaseSizeUnit()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_IncreaseSizeUnit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreasePercent <em>Increase Percent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Increase Percent</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIncreasePercent()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_IncreasePercent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getFileSystemCaching <em>File System Caching</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File System Caching</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getFileSystemCaching()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_FileSystemCaching();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getAverageSeekTime <em>Average Seek Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Average Seek Time</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getAverageSeekTime()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_AverageSeekTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRotationSpeed <em>Rotation Speed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rotation Speed</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRotationSpeed()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_RotationSpeed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTransfer <em>Transfer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transfer</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getTransfer()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_Transfer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getSystemType <em>System Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getSystemType()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_SystemType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getAverageTableSize <em>Average Table Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Average Table Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getAverageTableSize()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_AverageTableSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getExternalContainerCount <em>External Container Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Container Count</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getExternalContainerCount()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_ExternalContainerCount();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isInheritOverhead <em>Inherit Overhead</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inherit Overhead</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isInheritOverhead()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_InheritOverhead();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isInheritTransferate <em>Inherit Transferate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inherit Transferate</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isInheritTransferate()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_InheritTransferate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isRebalance <em>Rebalance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rebalance</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isRebalance()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_Rebalance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDataTag <em>Data Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Tag</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDataTag()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_DataTag();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isSuspendRebalance <em>Suspend Rebalance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suspend Rebalance</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isSuspendRebalance()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_SuspendRebalance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isResumeRebalance <em>Resume Rebalance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resume Rebalance</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#isResumeRebalance()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EAttribute getLUWTableSpace_ResumeRebalance();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Group</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getGroup()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Containers</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getContainers()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_Containers();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Buffer Pool</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getBufferPool()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_BufferPool();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataTables <em>Index Data Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Index Data Tables</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataTables()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_IndexDataTables();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataTables <em>LOB Data Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>LOB Data Tables</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataTables()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_LOBDataTables();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataTables <em>Regular Data Tables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Regular Data Tables</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataTables()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_RegularDataTables();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getDatabase()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_Database();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataPartition <em>LOB Data Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>LOB Data Partition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getLOBDataPartition()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_LOBDataPartition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataPartition <em>Regular Data Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Regular Data Partition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getRegularDataPartition()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_RegularDataPartition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Indexes</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexes()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_Indexes();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataPartition <em>Index Data Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Index Data Partition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexDataPartition()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_IndexDataPartition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getStorageGroup <em>Storage Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Storage Group</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getStorageGroup()
	 * @see #getLUWTableSpace()
	 * @generated
	 */
	EReference getLUWTableSpace_StorageGroup();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition <em>Database Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database Partition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition
	 * @generated
	 */
	EClass getLUWDatabasePartition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getNumber()
	 * @see #getLUWDatabasePartition()
	 * @generated
	 */
	EAttribute getLUWDatabasePartition_Number();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getPortNumber <em>Port Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port Number</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getPortNumber()
	 * @see #getLUWDatabasePartition()
	 * @generated
	 */
	EAttribute getLUWDatabasePartition_PortNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getHostName <em>Host Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getHostName()
	 * @see #getLUWDatabasePartition()
	 * @generated
	 */
	EAttribute getLUWDatabasePartition_HostName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getSwitchName <em>Switch Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Switch Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getSwitchName()
	 * @see #getLUWDatabasePartition()
	 * @generated
	 */
	EAttribute getLUWDatabasePartition_SwitchName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#isCatalogPartition <em>Catalog Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Catalog Partition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#isCatalogPartition()
	 * @see #getLUWDatabasePartition()
	 * @generated
	 */
	EAttribute getLUWDatabasePartition_CatalogPartition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Group</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getGroup()
	 * @see #getLUWDatabasePartition()
	 * @generated
	 */
	EReference getLUWDatabasePartition_Group();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Buffer Pool</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getBufferPool()
	 * @see #getLUWDatabasePartition()
	 * @generated
	 */
	EReference getLUWDatabasePartition_BufferPool();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Containers</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getContainers()
	 * @see #getLUWDatabasePartition()
	 * @generated
	 */
	EReference getLUWDatabasePartition_Containers();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getSizeException <em>Size Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Size Exception</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePartition#getSizeException()
	 * @see #getLUWDatabasePartition()
	 * @generated
	 */
	EReference getLUWDatabasePartition_SizeException();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer <em>Database Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database Container</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer
	 * @generated
	 */
	EClass getLUWDatabaseContainer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getContainerType <em>Container Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getContainerType()
	 * @see #getLUWDatabaseContainer()
	 * @generated
	 */
	EAttribute getLUWDatabaseContainer_ContainerType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSizeInPages <em>Size In Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size In Pages</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSizeInPages()
	 * @see #getLUWDatabaseContainer()
	 * @generated
	 */
	EAttribute getLUWDatabaseContainer_SizeInPages();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSize()
	 * @see #getLUWDatabaseContainer()
	 * @generated
	 */
	EAttribute getLUWDatabaseContainer_Size();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSizeUnits <em>Size Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size Units</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getSizeUnits()
	 * @see #getLUWDatabaseContainer()
	 * @generated
	 */
	EAttribute getLUWDatabaseContainer_SizeUnits();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getTableSpace <em>Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Table Space</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getTableSpace()
	 * @see #getLUWDatabaseContainer()
	 * @generated
	 */
	EReference getLUWDatabaseContainer_TableSpace();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getPartitions <em>Partitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partitions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabaseContainer#getPartitions()
	 * @see #getLUWDatabaseContainer()
	 * @generated
	 */
	EReference getLUWDatabaseContainer_Partitions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAdminServer <em>Admin Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Admin Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAdminServer
	 * @generated
	 */
	EClass getLUWAdminServer();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAdminServer#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Instances</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAdminServer#getInstances()
	 * @see #getLUWAdminServer()
	 * @generated
	 */
	EReference getLUWAdminServer_Instances();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Buffer Pool</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool
	 * @generated
	 */
	EClass getLUWBufferPool();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getCreateType <em>Create Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Create Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getCreateType()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EAttribute getLUWBufferPool_CreateType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getSize()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EAttribute getLUWBufferPool_Size();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPageSize <em>Page Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPageSize()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EAttribute getLUWBufferPool_PageSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getBlockSize <em>Block Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Block Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getBlockSize()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EAttribute getLUWBufferPool_BlockSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getNumBlockPages <em>Num Block Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Num Block Pages</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getNumBlockPages()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EAttribute getLUWBufferPool_NumBlockPages();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#isExtendedStorage <em>Extended Storage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extended Storage</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#isExtendedStorage()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EAttribute getLUWBufferPool_ExtendedStorage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#isAutomatic <em>Automatic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Automatic</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#isAutomatic()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EAttribute getLUWBufferPool_Automatic();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getTableSpaces <em>Table Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Table Spaces</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getTableSpaces()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EReference getLUWBufferPool_TableSpaces();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPartitions <em>Partitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partitions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPartitions()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EReference getLUWBufferPool_Partitions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPartitionGroup <em>Partition Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partition Group</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getPartitionGroup()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EReference getLUWBufferPool_PartitionGroup();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getDatabase()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EReference getLUWBufferPool_Database();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getSizeException <em>Size Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Size Exception</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool#getSizeException()
	 * @see #getLUWBufferPool()
	 * @generated
	 */
	EReference getLUWBufferPool_SizeException();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable
	 * @generated
	 */
	EClass getLUWTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getPCTFree <em>PCT Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PCT Free</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getPCTFree()
	 * @see #getLUWTable()
	 * @generated
	 */
	EAttribute getLUWTable_PCTFree();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isRestrictOnDrop <em>Restrict On Drop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Restrict On Drop</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isRestrictOnDrop()
	 * @see #getLUWTable()
	 * @generated
	 */
	EAttribute getLUWTable_RestrictOnDrop();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getPartitionMode <em>Partition Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition Mode</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getPartitionMode()
	 * @see #getLUWTable()
	 * @generated
	 */
	EAttribute getLUWTable_PartitionMode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isAppendMode <em>Append Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Append Mode</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isAppendMode()
	 * @see #getLUWTable()
	 * @generated
	 */
	EAttribute getLUWTable_AppendMode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getLogMode <em>Log Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Log Mode</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getLogMode()
	 * @see #getLUWTable()
	 * @generated
	 */
	EAttribute getLUWTable_LogMode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isLockSizeRow <em>Lock Size Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock Size Row</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isLockSizeRow()
	 * @see #getLUWTable()
	 * @generated
	 */
	EAttribute getLUWTable_LockSizeRow();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isVolatile <em>Volatile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Volatile</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#isVolatile()
	 * @see #getLUWTable()
	 * @generated
	 */
	EAttribute getLUWTable_Volatile();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getSecurityPolicy <em>Security Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Security Policy</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getSecurityPolicy()
	 * @see #getLUWTable()
	 * @generated
	 */
	EReference getLUWTable_SecurityPolicy();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable#getOptions()
	 * @see #getLUWTable()
	 * @generated
	 */
	EReference getLUWTable_Options();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView
	 * @generated
	 */
	EClass getLUWView();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView#isFederated <em>Federated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Federated</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView#isFederated()
	 * @see #getLUWView()
	 * @generated
	 */
	EAttribute getLUWView_Federated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView#isOptimizeQuery <em>Optimize Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optimize Query</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWView#isOptimizeQuery()
	 * @see #getLUWView()
	 * @generated
	 */
	EAttribute getLUWView_OptimizeQuery();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey <em>Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Partition Key</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey
	 * @generated
	 */
	EClass getLUWPartitionKey();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTemporaryStorageTable <em>Temporary Storage Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Temporary Storage Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTemporaryStorageTable()
	 * @see #getLUWPartitionKey()
	 * @generated
	 */
	EReference getLUWPartitionKey_TemporaryStorageTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getPartitionMethod <em>Partition Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition Method</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getPartitionMethod()
	 * @see #getLUWPartitionKey()
	 * @generated
	 */
	EAttribute getLUWPartitionKey_PartitionMethod();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getTable()
	 * @see #getLUWPartitionKey()
	 * @generated
	 */
	EReference getLUWPartitionKey_Table();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Columns</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionKey#getColumns()
	 * @see #getLUWPartitionKey()
	 * @generated
	 */
	EReference getLUWPartitionKey_Columns();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname <em>Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nickname</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname
	 * @generated
	 */
	EClass getLUWNickname();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getRemoteDataSet <em>Remote Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Remote Data Set</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getRemoteDataSet()
	 * @see #getLUWNickname()
	 * @generated
	 */
	EReference getLUWNickname_RemoteDataSet();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname#getServer()
	 * @see #getLUWNickname()
	 * @generated
	 */
	EReference getLUWNickname_Server();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping <em>Function Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Mapping</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping
	 * @generated
	 */
	EClass getLUWFunctionMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerType <em>Server Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerType()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EAttribute getLUWFunctionMapping_ServerType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerVersion <em>Server Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Version</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerVersion()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EAttribute getLUWFunctionMapping_ServerVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerName <em>Server Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getServerName()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EAttribute getLUWFunctionMapping_ServerName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getCreationTime <em>Creation Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Time</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getCreationTime()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EAttribute getLUWFunctionMapping_CreationTime();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#isDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disabled</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#isDisabled()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EAttribute getLUWFunctionMapping_Disabled();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getInstsPerInvoc <em>Insts Per Invoc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Insts Per Invoc</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getInstsPerInvoc()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EAttribute getLUWFunctionMapping_InstsPerInvoc();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getInstsPerArgByte <em>Insts Per Arg Byte</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Insts Per Arg Byte</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getInstsPerArgByte()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EAttribute getLUWFunctionMapping_InstsPerArgByte();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getIosPerInvoc <em>Ios Per Invoc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ios Per Invoc</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getIosPerInvoc()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EAttribute getLUWFunctionMapping_IosPerInvoc();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getIosPerArgByte <em>Ios Per Arg Byte</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ios Per Arg Byte</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getIosPerArgByte()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EAttribute getLUWFunctionMapping_IosPerArgByte();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getOptions()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EReference getLUWFunctionMapping_Options();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLocalFunction <em>Local Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Local Function</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLocalFunction()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EReference getLUWFunctionMapping_LocalFunction();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getRemoteFunction <em>Remote Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Remote Function</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getRemoteFunction()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EReference getLUWFunctionMapping_RemoteFunction();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLUWDatabase <em>LUW Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>LUW Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFunctionMapping#getLUWDatabase()
	 * @see #getLUWFunctionMapping()
	 * @generated
	 */
	EReference getLUWFunctionMapping_LUWDatabase();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper <em>Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wrapper</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper
	 * @generated
	 */
	EClass getLUWWrapper();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getVersion()
	 * @see #getLUWWrapper()
	 * @generated
	 */
	EAttribute getLUWWrapper_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLibrary <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Library</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLibrary()
	 * @see #getLUWWrapper()
	 * @generated
	 */
	EAttribute getLUWWrapper_Library();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#isFenced <em>Fenced</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fenced</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#isFenced()
	 * @see #getLUWWrapper()
	 * @generated
	 */
	EAttribute getLUWWrapper_Fenced();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getWrapperType <em>Wrapper Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wrapper Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getWrapperType()
	 * @see #getLUWWrapper()
	 * @generated
	 */
	EAttribute getLUWWrapper_WrapperType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getDataSource <em>Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Source</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getDataSource()
	 * @see #getLUWWrapper()
	 * @generated
	 */
	EAttribute getLUWWrapper_DataSource();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getDiscoveredLibraries <em>Discovered Libraries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Discovered Libraries</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getDiscoveredLibraries()
	 * @see #getLUWWrapper()
	 * @generated
	 */
	EAttribute getLUWWrapper_DiscoveredLibraries();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getServers <em>Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Servers</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getServers()
	 * @see #getLUWWrapper()
	 * @generated
	 */
	EReference getLUWWrapper_Servers();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLUWDatabase <em>LUW Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>LUW Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getLUWDatabase()
	 * @see #getLUWWrapper()
	 * @generated
	 */
	EReference getLUWWrapper_LUWDatabase();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWWrapper#getOptions()
	 * @see #getLUWWrapper()
	 * @generated
	 */
	EReference getLUWWrapper_Options();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname <em>Non Relational Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Non Relational Nickname</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname
	 * @generated
	 */
	EClass getLUWNonRelationalNickname();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname#getNonRelServer <em>Non Rel Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Non Rel Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalNickname#getNonRelServer()
	 * @see #getLUWNonRelationalNickname()
	 * @generated
	 */
	EReference getLUWNonRelationalNickname_NonRelServer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer <em>Non Relational Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Non Relational Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer
	 * @generated
	 */
	EClass getLUWNonRelationalServer();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelWrapper <em>Non Rel Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Non Rel Wrapper</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelWrapper()
	 * @see #getLUWNonRelationalServer()
	 * @generated
	 */
	EReference getLUWNonRelationalServer_NonRelWrapper();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelNicknames <em>Non Rel Nicknames</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Non Rel Nicknames</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalServer#getNonRelNicknames()
	 * @see #getLUWNonRelationalServer()
	 * @generated
	 */
	EReference getLUWNonRelationalServer_NonRelNicknames();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper <em>Non Relational Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Non Relational Wrapper</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper
	 * @generated
	 */
	EClass getLUWNonRelationalWrapper();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper#getNonRelServers <em>Non Rel Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Non Rel Servers</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNonRelationalWrapper#getNonRelServers()
	 * @see #getLUWNonRelationalWrapper()
	 * @generated
	 */
	EReference getLUWNonRelationalWrapper_NonRelServers();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalNickname <em>Relational Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relational Nickname</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalNickname
	 * @generated
	 */
	EClass getLUWRelationalNickname();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalNickname#getRelServer <em>Rel Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rel Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalNickname#getRelServer()
	 * @see #getLUWRelationalNickname()
	 * @generated
	 */
	EReference getLUWRelationalNickname_RelServer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping <em>Generic User Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic User Mapping</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping
	 * @generated
	 */
	EClass getLUWGenericUserMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping#getRemoteUser <em>Remote User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote User</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping#getRemoteUser()
	 * @see #getLUWGenericUserMapping()
	 * @generated
	 */
	EAttribute getLUWGenericUserMapping_RemoteUser();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping#getRemotePassword <em>Remote Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Password</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericUserMapping#getRemotePassword()
	 * @see #getLUWGenericUserMapping()
	 * @generated
	 */
	EAttribute getLUWGenericUserMapping_RemotePassword();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalWrapper <em>Relational Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relational Wrapper</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalWrapper
	 * @generated
	 */
	EClass getLUWRelationalWrapper();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalWrapper#getRelServers <em>Rel Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Rel Servers</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalWrapper#getRelServers()
	 * @see #getLUWRelationalWrapper()
	 * @generated
	 */
	EReference getLUWRelationalWrapper_RelServers();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer
	 * @generated
	 */
	EClass getLUWServer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getServerType <em>Server Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getServerType()
	 * @see #getLUWServer()
	 * @generated
	 */
	EAttribute getLUWServer_ServerType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getServerVersion <em>Server Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Version</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getServerVersion()
	 * @see #getLUWServer()
	 * @generated
	 */
	EAttribute getLUWServer_ServerVersion();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getUserMappings <em>User Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>User Mappings</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getUserMappings()
	 * @see #getLUWServer()
	 * @generated
	 */
	EReference getLUWServer_UserMappings();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getWrapper <em>Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Wrapper</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getWrapper()
	 * @see #getLUWServer()
	 * @generated
	 */
	EReference getLUWServer_Wrapper();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getNicknames <em>Nicknames</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Nicknames</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getNicknames()
	 * @see #getLUWServer()
	 * @generated
	 */
	EReference getLUWServer_Nicknames();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getLUWDatabase <em>LUW Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>LUW Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getLUWDatabase()
	 * @see #getLUWServer()
	 * @generated
	 */
	EReference getLUWServer_LUWDatabase();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getOptions()
	 * @see #getLUWServer()
	 * @generated
	 */
	EReference getLUWServer_Options();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getRemoteServer <em>Remote Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Remote Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWServer#getRemoteServer()
	 * @see #getLUWServer()
	 * @generated
	 */
	EReference getLUWServer_RemoteServer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping <em>Type Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Mapping</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping
	 * @generated
	 */
	EClass getLUWTypeMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getServerType <em>Server Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getServerType()
	 * @see #getLUWTypeMapping()
	 * @generated
	 */
	EAttribute getLUWTypeMapping_ServerType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getServerVesion <em>Server Vesion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Vesion</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getServerVesion()
	 * @see #getLUWTypeMapping()
	 * @generated
	 */
	EAttribute getLUWTypeMapping_ServerVesion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getServerName <em>Server Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Server Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getServerName()
	 * @see #getLUWTypeMapping()
	 * @generated
	 */
	EAttribute getLUWTypeMapping_ServerName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getCreationTime <em>Creation Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Time</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getCreationTime()
	 * @see #getLUWTypeMapping()
	 * @generated
	 */
	EAttribute getLUWTypeMapping_CreationTime();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getLocalType <em>Local Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Local Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getLocalType()
	 * @see #getLUWTypeMapping()
	 * @generated
	 */
	EReference getLUWTypeMapping_LocalType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getRemoteType <em>Remote Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Remote Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTypeMapping#getRemoteType()
	 * @see #getLUWTypeMapping()
	 * @generated
	 */
	EReference getLUWTypeMapping_RemoteType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping <em>User Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Mapping</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping
	 * @generated
	 */
	EClass getLUWUserMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping#getLocalAuthId <em>Local Auth Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local Auth Id</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping#getLocalAuthId()
	 * @see #getLUWUserMapping()
	 * @generated
	 */
	EAttribute getLUWUserMapping_LocalAuthId();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping#getServer()
	 * @see #getLUWUserMapping()
	 * @generated
	 */
	EReference getLUWUserMapping_Server();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWUserMapping#getOptions()
	 * @see #getLUWUserMapping()
	 * @generated
	 */
	EReference getLUWUserMapping_Options();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption <em>Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Option</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption
	 * @generated
	 */
	EClass getLUWOption();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption#getValue()
	 * @see #getLUWOption()
	 * @generated
	 */
	EAttribute getLUWOption_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer <em>Relational Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relational Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer
	 * @generated
	 */
	EClass getLUWRelationalServer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getCpuRatio <em>Cpu Ratio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cpu Ratio</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getCpuRatio()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_CpuRatio();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getIoRatio <em>Io Ratio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Io Ratio</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getIoRatio()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_IoRatio();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getCommRate <em>Comm Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comm Rate</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getCommRate()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_CommRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isFoldId <em>Fold Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fold Id</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isFoldId()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_FoldId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isFoldPW <em>Fold PW</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fold PW</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isFoldPW()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_FoldPW();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isCollatingSequence <em>Collating Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collating Sequence</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isCollatingSequence()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_CollatingSequence();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isPushdown <em>Pushdown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pushdown</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isPushdown()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_Pushdown();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getNode()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_Node();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getDbName <em>Db Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Db Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getDbName()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_DbName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isIudAppSvptEnforce <em>Iud App Svpt Enforce</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Iud App Svpt Enforce</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#isIudAppSvptEnforce()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_IudAppSvptEnforce();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getPassword <em>Password</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getPassword()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EAttribute getLUWRelationalServer_Password();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getRelNicknames <em>Rel Nicknames</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Rel Nicknames</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getRelNicknames()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EReference getLUWRelationalServer_RelNicknames();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getRelWrapper <em>Rel Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Rel Wrapper</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer#getRelWrapper()
	 * @see #getLUWRelationalServer()
	 * @generated
	 */
	EReference getLUWRelationalServer_RelWrapper();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase
	 * @generated
	 */
	EClass getLUWDatabase();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#isFederated <em>Federated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Federated</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#isFederated()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EAttribute getLUWDatabase_Federated();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Groups</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getGroups()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_Groups();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getWrappers <em>Wrappers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Wrappers</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getWrappers()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_Wrappers();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getServers <em>Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Servers</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getServers()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_Servers();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getFunctionMappings <em>Function Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Function Mappings</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getFunctionMappings()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_FunctionMappings();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getTypeMappings <em>Type Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Type Mappings</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getTypeMappings()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_TypeMappings();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getReverseTypeMappings <em>Reverse Type Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reverse Type Mappings</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getReverseTypeMappings()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_ReverseTypeMappings();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getBufferpools <em>Bufferpools</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Bufferpools</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getBufferpools()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_Bufferpools();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getTablespaces <em>Tablespaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tablespaces</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getTablespaces()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_Tablespaces();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getStorageGroups <em>Storage Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Storage Groups</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getStorageGroups()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_StorageGroups();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getDefaultStorageGroup <em>Default Storage Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Storage Group</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabase#getDefaultStorageGroup()
	 * @see #getLUWDatabase()
	 * @generated
	 */
	EReference getLUWDatabase_DefaultStorageGroup();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn
	 * @generated
	 */
	EClass getLUWColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#isLobLogged <em>Lob Logged</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lob Logged</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#isLobLogged()
	 * @see #getLUWColumn()
	 * @generated
	 */
	EAttribute getLUWColumn_LobLogged();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#isLobCompacted <em>Lob Compacted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lob Compacted</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#isLobCompacted()
	 * @see #getLUWColumn()
	 * @generated
	 */
	EAttribute getLUWColumn_LobCompacted();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#getCompression <em>Compression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Compression</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#getCompression()
	 * @see #getLUWColumn()
	 * @generated
	 */
	EAttribute getLUWColumn_Compression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#getInlineLength <em>Inline Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inline Length</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#getInlineLength()
	 * @see #getLUWColumn()
	 * @generated
	 */
	EAttribute getLUWColumn_InlineLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#isHidden()
	 * @see #getLUWColumn()
	 * @generated
	 */
	EAttribute getLUWColumn_Hidden();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#getSecurityLabel <em>Security Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Security Label</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#getSecurityLabel()
	 * @see #getLUWColumn()
	 * @generated
	 */
	EAttribute getLUWColumn_SecurityLabel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#getOptions <em>Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn#getOptions()
	 * @see #getLUWColumn()
	 * @generated
	 */
	EReference getLUWColumn_Options();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname <em>Generic Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Nickname</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname
	 * @generated
	 */
	EClass getLUWGenericNickname();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname#getGenericServer <em>Generic Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Generic Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericNickname#getGenericServer()
	 * @see #getLUWGenericNickname()
	 * @generated
	 */
	EReference getLUWGenericNickname_GenericServer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer <em>Generic Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer
	 * @generated
	 */
	EClass getLUWGenericServer();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericNicknames <em>Generic Nicknames</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Generic Nicknames</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericNicknames()
	 * @see #getLUWGenericServer()
	 * @generated
	 */
	EReference getLUWGenericServer_GenericNicknames();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericWrapper <em>Generic Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Generic Wrapper</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericServer#getGenericWrapper()
	 * @see #getLUWGenericServer()
	 * @generated
	 */
	EReference getLUWGenericServer_GenericWrapper();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMaterializedQueryTable <em>Materialized Query Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Materialized Query Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMaterializedQueryTable
	 * @generated
	 */
	EClass getLUWMaterializedQueryTable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper <em>Generic Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Wrapper</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper
	 * @generated
	 */
	EClass getLUWGenericWrapper();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper#getGenericServers <em>Generic Servers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Generic Servers</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGenericWrapper#getGenericServers()
	 * @see #getLUWGenericWrapper()
	 * @generated
	 */
	EReference getLUWGenericWrapper_GenericServers();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable <em>Storage Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Storage Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable
	 * @generated
	 */
	EClass getLUWStorageTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isValueCompression <em>Value Compression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value Compression</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isValueCompression()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EAttribute getLUWStorageTable_ValueCompression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isRowCompression <em>Row Compression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Compression</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isRowCompression()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EAttribute getLUWStorageTable_RowCompression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isRowCompressionEmpty <em>Row Compression Empty</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Compression Empty</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#isRowCompressionEmpty()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EAttribute getLUWStorageTable_RowCompressionEmpty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getCompressionMode <em>Compression Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Compression Mode</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getCompressionMode()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EAttribute getLUWStorageTable_CompressionMode();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getPartitionKey <em>Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Partition Key</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getPartitionKey()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EReference getLUWStorageTable_PartitionKey();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getIndexDataTableSpace <em>Index Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Index Data Table Space</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getIndexDataTableSpace()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EReference getLUWStorageTable_IndexDataTableSpace();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getLOBDataTableSpace <em>LOB Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>LOB Data Table Space</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getLOBDataTableSpace()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EReference getLUWStorageTable_LOBDataTableSpace();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getRegularDataTableSpace <em>Regular Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Regular Data Table Space</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getRegularDataTableSpace()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EReference getLUWStorageTable_RegularDataTableSpace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitions <em>Data Partitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Partitions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitions()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EReference getLUWStorageTable_DataPartitions();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitionKey <em>Data Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Data Partition Key</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable#getDataPartitionKey()
	 * @see #getLUWStorageTable()
	 * @generated
	 */
	EReference getLUWStorageTable_DataPartitionKey();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer <em>Relational Remote Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relational Remote Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer
	 * @generated
	 */
	EClass getRelationalRemoteServer();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer#getDatabase()
	 * @see #getRelationalRemoteServer()
	 * @generated
	 */
	EReference getRelationalRemoteServer_Database();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet <em>Relational Remote Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relational Remote Data Set</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet
	 * @generated
	 */
	EClass getRelationalRemoteDataSet();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet#getTable()
	 * @see #getRelationalRemoteDataSet()
	 * @generated
	 */
	EReference getRelationalRemoteDataSet_Table();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer <em>Remote Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remote Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer
	 * @generated
	 */
	EClass getRemoteServer();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer#getLUWServer <em>LUW Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>LUW Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteServer#getLUWServer()
	 * @see #getRemoteServer()
	 * @generated
	 */
	EReference getRemoteServer_LUWServer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet <em>Remote Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Remote Data Set</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet
	 * @generated
	 */
	EClass getRemoteDataSet();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet#getNickname <em>Nickname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Nickname</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RemoteDataSet#getNickname()
	 * @see #getRemoteDataSet()
	 * @generated
	 */
	EReference getRemoteDataSet_Nickname();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex
	 * @generated
	 */
	EClass getLUWIndex();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getPCTFree <em>PCT Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PCT Free</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getPCTFree()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_PCTFree();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getMinPCTFree <em>Min PCT Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min PCT Free</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getMinPCTFree()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_MinPCTFree();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isReverseScan <em>Reverse Scan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reverse Scan</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isReverseScan()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_ReverseScan();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isNotPartitioned <em>Not Partitioned</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Not Partitioned</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isNotPartitioned()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_NotPartitioned();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getXmlPattern <em>Xml Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xml Pattern</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getXmlPattern()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_XmlPattern();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getAsSQLDataType <em>As SQL Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>As SQL Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getAsSQLDataType()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EReference getLUWIndex_AsSQLDataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isAsSQLDataTypeHashed <em>As SQL Data Type Hashed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>As SQL Data Type Hashed</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isAsSQLDataTypeHashed()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_AsSQLDataTypeHashed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isSystemRequired <em>System Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Required</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isSystemRequired()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_SystemRequired();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getPageSplitType <em>Page Split Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Split Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getPageSplitType()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_PageSplitType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getLevel2PctFree <em>Level2 Pct Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level2 Pct Free</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getLevel2PctFree()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_Level2PctFree();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getMinPctUsed <em>Min Pct Used</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Pct Used</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getMinPctUsed()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_MinPctUsed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getCompress <em>Compress</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Compress</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getCompress()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_Compress();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isCollectStats <em>Collect Stats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collect Stats</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isCollectStats()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_CollectStats();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isSampledStats <em>Sampled Stats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sampled Stats</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isSampledStats()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_SampledStats();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isDetailedStats <em>Detailed Stats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Detailed Stats</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isDetailedStats()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_DetailedStats();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isIgnoreInvalidValues <em>Ignore Invalid Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore Invalid Values</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isIgnoreInvalidValues()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_IgnoreInvalidValues();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isExcludeNullKeys <em>Exclude Null Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exclude Null Keys</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isExcludeNullKeys()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EAttribute getLUWIndex_ExcludeNullKeys();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getTablespace <em>Tablespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Tablespace</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getTablespace()
	 * @see #getLUWIndex()
	 * @generated
	 */
	EReference getLUWIndex_Tablespace();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition <em>Attribute Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Definition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition
	 * @generated
	 */
	EClass getLUWAttributeDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition#isLOBLogged <em>LOB Logged</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>LOB Logged</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition#isLOBLogged()
	 * @see #getLUWAttributeDefinition()
	 * @generated
	 */
	EAttribute getLUWAttributeDefinition_LOBLogged();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition#isLOBCompacted <em>LOB Compacted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>LOB Compacted</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition#isLOBCompacted()
	 * @see #getLUWAttributeDefinition()
	 * @generated
	 */
	EAttribute getLUWAttributeDefinition_LOBCompacted();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure <em>Federated Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Federated Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure
	 * @generated
	 */
	EClass getFederatedProcedure();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteUniqueId <em>Remote Unique Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Unique Id</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteUniqueId()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EAttribute getFederatedProcedure_RemoteUniqueId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteServer <em>Remote Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteServer()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EAttribute getFederatedProcedure_RemoteServer();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteSchema <em>Remote Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteSchema()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EAttribute getFederatedProcedure_RemoteSchema();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemotePackage <em>Remote Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Package</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemotePackage()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EAttribute getFederatedProcedure_RemotePackage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteProcedureName <em>Remote Procedure Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Procedure Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteProcedureName()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EAttribute getFederatedProcedure_RemoteProcedureName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getNumberOfParameters <em>Number Of Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Parameters</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getNumberOfParameters()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EAttribute getFederatedProcedure_NumberOfParameters();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getResultSetsToClient <em>Result Sets To Client</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result Sets To Client</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getResultSetsToClient()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EAttribute getFederatedProcedure_ResultSetsToClient();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getNumberOfRefCursors <em>Number Of Ref Cursors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Ref Cursors</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getNumberOfRefCursors()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EAttribute getFederatedProcedure_NumberOfRefCursors();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#isAllResultSetsToCaller <em>All Result Sets To Caller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>All Result Sets To Caller</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#isAllResultSetsToCaller()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EAttribute getFederatedProcedure_AllResultSetsToCaller();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getFederatedProcedure <em>Federated Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Federated Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getFederatedProcedure()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EReference getFederatedProcedure_FederatedProcedure();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteProcedure <em>Remote Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Remote Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getRemoteProcedure()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EReference getFederatedProcedure_RemoteProcedure();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getFederatedParameter <em>Federated Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Federated Parameter</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedProcedure#getFederatedParameter()
	 * @see #getFederatedProcedure()
	 * @generated
	 */
	EReference getFederatedProcedure_FederatedParameter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter <em>Federated Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Federated Parameter</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter
	 * @generated
	 */
	EClass getFederatedParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteCodePage <em>Remote Code Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Code Page</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteCodePage()
	 * @see #getFederatedParameter()
	 * @generated
	 */
	EAttribute getFederatedParameter_RemoteCodePage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteParamTypeID <em>Remote Param Type ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Param Type ID</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteParamTypeID()
	 * @see #getFederatedParameter()
	 * @generated
	 */
	EAttribute getFederatedParameter_RemoteParamTypeID();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getFederatedProcedure <em>Federated Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Federated Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getFederatedProcedure()
	 * @see #getFederatedParameter()
	 * @generated
	 */
	EReference getFederatedParameter_FederatedProcedure();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteParameter <em>Remote Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Remote Parameter</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FederatedParameter#getRemoteParameter()
	 * @see #getFederatedParameter()
	 * @generated
	 */
	EReference getFederatedParameter_RemoteParameter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression <em>Partition Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Partition Expression</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression
	 * @generated
	 */
	EClass getLUWPartitionExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#isNullsLast <em>Nulls Last</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nulls Last</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#isNullsLast()
	 * @see #getLUWPartitionExpression()
	 * @generated
	 */
	EAttribute getLUWPartitionExpression_NullsLast();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Key</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getKey()
	 * @see #getLUWPartitionExpression()
	 * @generated
	 */
	EReference getLUWPartitionExpression_Key();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Column</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getColumn()
	 * @see #getLUWPartitionExpression()
	 * @generated
	 */
	EReference getLUWPartitionExpression_Column();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getPartitionElements <em>Partition Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Partition Elements</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getPartitionElements()
	 * @see #getLUWPartitionExpression()
	 * @generated
	 */
	EReference getLUWPartitionExpression_PartitionElements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement <em>Partition Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Partition Element</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement
	 * @generated
	 */
	EClass getLUWPartitionElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getStarting <em>Starting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Starting</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getStarting()
	 * @see #getLUWPartitionElement()
	 * @generated
	 */
	EAttribute getLUWPartitionElement_Starting();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getEnding <em>Ending</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ending</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getEnding()
	 * @see #getLUWPartitionElement()
	 * @generated
	 */
	EAttribute getLUWPartitionElement_Ending();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getLUWPartitionExpression <em>LUW Partition Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>LUW Partition Expression</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getLUWPartitionExpression()
	 * @see #getLUWPartitionElement()
	 * @generated
	 */
	EReference getLUWPartitionElement_LUWPartitionExpression();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getPartition <em>Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Partition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getPartition()
	 * @see #getLUWPartitionElement()
	 * @generated
	 */
	EReference getLUWPartitionElement_Partition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getEveryClause <em>Every Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Every Clause</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getEveryClause()
	 * @see #getLUWPartitionElement()
	 * @generated
	 */
	EReference getLUWPartitionElement_EveryClause();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition <em>Data Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Partition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition
	 * @generated
	 */
	EClass getLUWDataPartition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getId()
	 * @see #getLUWDataPartition()
	 * @generated
	 */
	EAttribute getLUWDataPartition_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#isLowInclusive <em>Low Inclusive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Low Inclusive</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#isLowInclusive()
	 * @see #getLUWDataPartition()
	 * @generated
	 */
	EAttribute getLUWDataPartition_LowInclusive();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#isHighInclusive <em>High Inclusive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>High Inclusive</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#isHighInclusive()
	 * @see #getLUWDataPartition()
	 * @generated
	 */
	EAttribute getLUWDataPartition_HighInclusive();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getLOBDataTableSpace <em>LOB Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>LOB Data Table Space</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getLOBDataTableSpace()
	 * @see #getLUWDataPartition()
	 * @generated
	 */
	EReference getLUWDataPartition_LOBDataTableSpace();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getRegularDataTableSpace <em>Regular Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Regular Data Table Space</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getRegularDataTableSpace()
	 * @see #getLUWDataPartition()
	 * @generated
	 */
	EReference getLUWDataPartition_RegularDataTableSpace();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getPartitionElements <em>Partition Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partition Elements</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getPartitionElements()
	 * @see #getLUWDataPartition()
	 * @generated
	 */
	EReference getLUWDataPartition_PartitionElements();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getTable()
	 * @see #getLUWDataPartition()
	 * @generated
	 */
	EReference getLUWDataPartition_Table();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getIndexDataTableSpace <em>Index Data Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Index Data Table Space</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getIndexDataTableSpace()
	 * @see #getLUWDataPartition()
	 * @generated
	 */
	EReference getLUWDataPartition_IndexDataTableSpace();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey <em>Data Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Partition Key</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey
	 * @generated
	 */
	EClass getLUWDataPartitionKey();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getPartitionMethod <em>Partition Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition Method</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getPartitionMethod()
	 * @see #getLUWDataPartitionKey()
	 * @generated
	 */
	EAttribute getLUWDataPartitionKey_PartitionMethod();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getPartitionExpressions <em>Partition Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Partition Expressions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getPartitionExpressions()
	 * @see #getLUWDataPartitionKey()
	 * @generated
	 */
	EReference getLUWDataPartitionKey_PartitionExpressions();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartitionKey#getTable()
	 * @see #getLUWDataPartitionKey()
	 * @generated
	 */
	EReference getLUWDataPartitionKey_Table();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage <em>Database Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Database Package</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage
	 * @generated
	 */
	EClass getLUWDatabasePackage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getCreator <em>Creator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creator</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getCreator()
	 * @see #getLUWDatabasePackage()
	 * @generated
	 */
	EAttribute getLUWDatabasePackage_Creator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getBinder <em>Binder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binder</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getBinder()
	 * @see #getLUWDatabasePackage()
	 * @generated
	 */
	EAttribute getLUWDatabasePackage_Binder();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getCursorBlock <em>Cursor Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cursor Block</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getCursorBlock()
	 * @see #getLUWDatabasePackage()
	 * @generated
	 */
	EAttribute getLUWDatabasePackage_CursorBlock();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getNumberOfSections <em>Number Of Sections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Sections</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getNumberOfSections()
	 * @see #getLUWDatabasePackage()
	 * @generated
	 */
	EAttribute getLUWDatabasePackage_NumberOfSections();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getOptimizationClass <em>Optimization Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optimization Class</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getOptimizationClass()
	 * @see #getLUWDatabasePackage()
	 * @generated
	 */
	EAttribute getLUWDatabasePackage_OptimizationClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getExplainSnapshot <em>Explain Snapshot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Explain Snapshot</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDatabasePackage#getExplainSnapshot()
	 * @see #getLUWDatabasePackage()
	 * @generated
	 */
	EAttribute getLUWDatabasePackage_ExplainSnapshot();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule
	 * @generated
	 */
	EClass getLUWModule();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getDialect <em>Dialect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dialect</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getDialect()
	 * @see #getLUWModule()
	 * @generated
	 */
	EAttribute getLUWModule_Dialect();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getOwningSchema <em>Owning Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Owning Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getOwningSchema()
	 * @see #getLUWModule()
	 * @generated
	 */
	EReference getLUWModule_OwningSchema();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getModuleObjects <em>Module Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Module Objects</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModule#getModuleObjects()
	 * @see #getLUWModule()
	 * @generated
	 */
	EReference getLUWModule_ModuleObjects();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject <em>Module Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Object</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject
	 * @generated
	 */
	EClass getLUWModuleObject();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject#isPublished <em>Published</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Published</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject#isPublished()
	 * @see #getLUWModuleObject()
	 * @generated
	 */
	EAttribute getLUWModuleObject_Published();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Module</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject#getModule()
	 * @see #getLUWModuleObject()
	 * @generated
	 */
	EReference getLUWModuleObject_Module();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleFunction <em>Module Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Function</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleFunction
	 * @generated
	 */
	EClass getLUWModuleFunction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleFunction#isImplemented <em>Implemented</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implemented</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleFunction#isImplemented()
	 * @see #getLUWModuleFunction()
	 * @generated
	 */
	EAttribute getLUWModuleFunction_Implemented();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleProcedure <em>Module Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleProcedure
	 * @generated
	 */
	EClass getLUWModuleProcedure();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleProcedure#isImplemented <em>Implemented</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implemented</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleProcedure#isImplemented()
	 * @see #getLUWModuleProcedure()
	 * @generated
	 */
	EAttribute getLUWModuleProcedure_Implemented();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition <em>Module Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Condition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition
	 * @generated
	 */
	EClass getLUWModuleCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition#getSqlstate <em>Sqlstate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sqlstate</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCondition#getSqlstate()
	 * @see #getLUWModuleCondition()
	 * @generated
	 */
	EAttribute getLUWModuleCondition_Sqlstate();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable <em>Global Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Global Variable</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable
	 * @generated
	 */
	EClass getLUWGlobalVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable#getDefaultValue()
	 * @see #getLUWGlobalVariable()
	 * @generated
	 */
	EAttribute getLUWGlobalVariable_DefaultValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable#isIsConstant <em>Is Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Constant</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable#isIsConstant()
	 * @see #getLUWGlobalVariable()
	 * @generated
	 */
	EAttribute getLUWGlobalVariable_IsConstant();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWGlobalVariable#getSchema()
	 * @see #getLUWGlobalVariable()
	 * @generated
	 */
	EReference getLUWGlobalVariable_Schema();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleType <em>Module Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleType
	 * @generated
	 */
	EClass getLUWModuleType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleRowDataType <em>Module Row Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Row Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleRowDataType
	 * @generated
	 */
	EClass getLUWModuleRowDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleArrayDataType <em>Module Array Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Array Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleArrayDataType
	 * @generated
	 */
	EClass getLUWModuleArrayDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleDistinctType <em>Module Distinct Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Distinct Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleDistinctType
	 * @generated
	 */
	EClass getLUWModuleDistinctType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleGlobalVariable <em>Module Global Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Global Variable</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleGlobalVariable
	 * @generated
	 */
	EClass getLUWModuleGlobalVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType <em>Array Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType
	 * @generated
	 */
	EClass getLUWArrayDataType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType#getArrayIndexElementType <em>Array Index Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array Index Element Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWArrayDataType#getArrayIndexElementType()
	 * @see #getLUWArrayDataType()
	 * @generated
	 */
	EReference getLUWArrayDataType_ArrayIndexElementType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRowDataType <em>Row Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Row Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRowDataType
	 * @generated
	 */
	EClass getLUWRowDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage <em>PLSQL Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PLSQL Package</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage
	 * @generated
	 */
	EClass getPLSQLPackage();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage#getPackageBody <em>Package Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Package Body</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage#getPackageBody()
	 * @see #getPLSQLPackage()
	 * @generated
	 */
	EReference getPLSQLPackage_PackageBody();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody <em>PLSQL Package Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PLSQL Package Body</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody
	 * @generated
	 */
	EClass getPLSQLPackageBody();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Package</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody#getPackage()
	 * @see #getPLSQLPackageBody()
	 * @generated
	 */
	EReference getPLSQLPackageBody_Package();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType <em>Cursor Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cursor Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType
	 * @generated
	 */
	EClass getLUWCursorDataType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType#getRowType <em>Row Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Row Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWCursorDataType#getRowType()
	 * @see #getLUWCursorDataType()
	 * @generated
	 */
	EReference getLUWCursorDataType_RowType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCursorDataType <em>Module Cursor Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module Cursor Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleCursorDataType
	 * @generated
	 */
	EClass getLUWModuleCursorDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException <em>Buffer Pool Size Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Buffer Pool Size Exception</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException
	 * @generated
	 */
	EClass getLUWBufferPoolSizeException();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getSize()
	 * @see #getLUWBufferPoolSizeException()
	 * @generated
	 */
	EAttribute getLUWBufferPoolSizeException_Size();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getBufferPool <em>Buffer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Buffer Pool</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getBufferPool()
	 * @see #getLUWBufferPoolSizeException()
	 * @generated
	 */
	EReference getLUWBufferPoolSizeException_BufferPool();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getPartitions <em>Partitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partitions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPoolSizeException#getPartitions()
	 * @see #getLUWBufferPoolSizeException()
	 * @generated
	 */
	EReference getLUWBufferPoolSizeException_Partitions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember
	 * @generated
	 */
	EClass getLUWMember();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getType()
	 * @see #getLUWMember()
	 * @generated
	 */
	EAttribute getLUWMember_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getAlert <em>Alert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Alert</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getAlert()
	 * @see #getLUWMember()
	 * @generated
	 */
	EAttribute getLUWMember_Alert();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getDbPartitionNum <em>Db Partition Num</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Db Partition Num</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getDbPartitionNum()
	 * @see #getLUWMember()
	 * @generated
	 */
	EAttribute getLUWMember_DbPartitionNum();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getLogicalPort <em>Logical Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Logical Port</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getLogicalPort()
	 * @see #getLUWMember()
	 * @generated
	 */
	EAttribute getLUWMember_LogicalPort();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getNetName <em>Net Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Net Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMember#getNetName()
	 * @see #getLUWMember()
	 * @generated
	 */
	EAttribute getLUWMember_NetName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy <em>Security Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Security Policy</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy
	 * @generated
	 */
	EClass getLUWSecurityPolicy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getNotAuthorizedWrite <em>Not Authorized Write</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Not Authorized Write</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getNotAuthorizedWrite()
	 * @see #getLUWSecurityPolicy()
	 * @generated
	 */
	EAttribute getLUWSecurityPolicy_NotAuthorizedWrite();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Components</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getComponents()
	 * @see #getLUWSecurityPolicy()
	 * @generated
	 */
	EReference getLUWSecurityPolicy_Components();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getLabels <em>Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Labels</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getLabels()
	 * @see #getLUWSecurityPolicy()
	 * @generated
	 */
	EReference getLUWSecurityPolicy_Labels();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityPolicy#getTable()
	 * @see #getLUWSecurityPolicy()
	 * @generated
	 */
	EReference getLUWSecurityPolicy_Table();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent <em>Security Label Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Security Label Component</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent
	 * @generated
	 */
	EClass getLUWSecurityLabelComponent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getType()
	 * @see #getLUWSecurityLabelComponent()
	 * @generated
	 */
	EAttribute getLUWSecurityLabelComponent_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getLUWSecurityPolicy <em>LUW Security Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>LUW Security Policy</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getLUWSecurityPolicy()
	 * @see #getLUWSecurityLabelComponent()
	 * @generated
	 */
	EReference getLUWSecurityLabelComponent_LUWSecurityPolicy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Elements</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponent#getElements()
	 * @see #getLUWSecurityLabelComponent()
	 * @generated
	 */
	EReference getLUWSecurityLabelComponent_Elements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel <em>Security Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Security Label</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel
	 * @generated
	 */
	EClass getLUWSecurityLabel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel#getSecurityLabel <em>Security Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Security Label</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel#getSecurityLabel()
	 * @see #getLUWSecurityLabel()
	 * @generated
	 */
	EAttribute getLUWSecurityLabel_SecurityLabel();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel#getPolicy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Policy</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel#getPolicy()
	 * @see #getLUWSecurityLabel()
	 * @generated
	 */
	EReference getLUWSecurityLabel_Policy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement <em>Security Label Component Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Security Label Component Element</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement
	 * @generated
	 */
	EClass getLUWSecurityLabelComponentElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement#getValue()
	 * @see #getLUWSecurityLabelComponentElement()
	 * @generated
	 */
	EAttribute getLUWSecurityLabelComponentElement_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement#getParentValue <em>Parent Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parent Value</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement#getParentValue()
	 * @see #getLUWSecurityLabelComponentElement()
	 * @generated
	 */
	EAttribute getLUWSecurityLabelComponentElement_ParentValue();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement#getLUWSecurityLabelComponent <em>LUW Security Label Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>LUW Security Label Component</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentElement#getLUWSecurityLabelComponent()
	 * @see #getLUWSecurityLabelComponentElement()
	 * @generated
	 */
	EReference getLUWSecurityLabelComponentElement_LUWSecurityLabelComponent();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup <em>Storage Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Storage Group</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup
	 * @generated
	 */
	EClass getLUWStorageGroup();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getStoragePaths <em>Storage Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Storage Paths</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getStoragePaths()
	 * @see #getLUWStorageGroup()
	 * @generated
	 */
	EAttribute getLUWStorageGroup_StoragePaths();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getOverhead <em>Overhead</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overhead</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getOverhead()
	 * @see #getLUWStorageGroup()
	 * @generated
	 */
	EAttribute getLUWStorageGroup_Overhead();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getDeviceReadRate <em>Device Read Rate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device Read Rate</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getDeviceReadRate()
	 * @see #getLUWStorageGroup()
	 * @generated
	 */
	EAttribute getLUWStorageGroup_DeviceReadRate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getDataTag <em>Data Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Tag</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getDataTag()
	 * @see #getLUWStorageGroup()
	 * @generated
	 */
	EAttribute getLUWStorageGroup_DataTag();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#isDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#isDefault()
	 * @see #getLUWStorageGroup()
	 * @generated
	 */
	EAttribute getLUWStorageGroup_Default();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getDatabase()
	 * @see #getLUWStorageGroup()
	 * @generated
	 */
	EReference getLUWStorageGroup_Database();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getTableSpaces <em>Table Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Table Spaces</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageGroup#getTableSpaces()
	 * @see #getLUWStorageGroup()
	 * @generated
	 */
	EReference getLUWStorageGroup_TableSpaces();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable <em>Temporary Storage Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Temporary Storage Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable
	 * @generated
	 */
	EClass getLUWTemporaryStorageTable();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getPartitionKey <em>Partition Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Partition Key</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getPartitionKey()
	 * @see #getLUWTemporaryStorageTable()
	 * @generated
	 */
	EReference getLUWTemporaryStorageTable_PartitionKey();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getUserTemporaryTableSpace <em>User Temporary Table Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>User Temporary Table Space</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryStorageTable#getUserTemporaryTableSpace()
	 * @see #getLUWTemporaryStorageTable()
	 * @generated
	 */
	EReference getLUWTemporaryStorageTable_UserTemporaryTableSpace();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable <em>Temporary Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Temporary Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable
	 * @generated
	 */
	EClass getLUWTemporaryTable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable#getTable()
	 * @see #getLUWTemporaryTable()
	 * @generated
	 */
	EReference getLUWTemporaryTable_Table();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable#getLogOption <em>Log Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Log Option</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTable#getLogOption()
	 * @see #getLUWTemporaryTable()
	 * @generated
	 */
	EAttribute getLUWTemporaryTable_LogOption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType <em>Array Index Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Index Element Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType
	 * @generated
	 */
	EClass getArrayIndexElementType();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType#getLUWArrayDataType <em>LUW Array Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>LUW Array Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ArrayIndexElementType#getLUWArrayDataType()
	 * @see #getArrayIndexElementType()
	 * @generated
	 */
	EReference getArrayIndexElementType_LUWArrayDataType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement <em>Partition Every Clause Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Partition Every Clause Element</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement
	 * @generated
	 */
	EClass getLUWPartitionEveryClauseElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement#getValue()
	 * @see #getLUWPartitionEveryClauseElement()
	 * @generated
	 */
	EAttribute getLUWPartitionEveryClauseElement_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement#getDuration()
	 * @see #getLUWPartitionEveryClauseElement()
	 * @generated
	 */
	EAttribute getLUWPartitionEveryClauseElement_Duration();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement#getLUWPartitionElement <em>LUW Partition Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>LUW Partition Element</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement#getLUWPartitionElement()
	 * @see #getLUWPartitionEveryClauseElement()
	 * @generated
	 */
	EReference getLUWPartitionEveryClauseElement_LUWPartitionElement();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType <em>Container Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Container Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType
	 * @generated
	 */
	EEnum getLUWContainerType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType <em>Page Size Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Page Size Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType
	 * @generated
	 */
	EEnum getPageSizeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType <em>Buffer Pool Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Buffer Pool Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType
	 * @generated
	 */
	EEnum getBufferPoolType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType <em>Table Space Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Table Space Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType
	 * @generated
	 */
	EEnum getTableSpaceType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType <em>Management Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Management Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType
	 * @generated
	 */
	EEnum getManagementType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.CheckOptionType <em>Check Option Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Check Option Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.CheckOptionType
	 * @generated
	 */
	EEnum getCheckOptionType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod <em>Partition Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Partition Method</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod
	 * @generated
	 */
	EEnum getPartitionMethod();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.MaintenanceType <em>Maintenance Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Maintenance Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.MaintenanceType
	 * @generated
	 */
	EEnum getMaintenanceType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RefreshType <em>Refresh Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Refresh Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RefreshType
	 * @generated
	 */
	EEnum getRefreshType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType <em>Wrapper Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Wrapper Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType
	 * @generated
	 */
	EEnum getWrapperType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod <em>Data Partition Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Data Partition Method</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod
	 * @generated
	 */
	EEnum getDataPartitionMethod();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType <em>Cursor Block Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Cursor Block Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType
	 * @generated
	 */
	EEnum getCursorBlockType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType <em>Explain Snaphot Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Explain Snaphot Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType
	 * @generated
	 */
	EEnum getExplainSnaphotType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType <em>File System Caching Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>File System Caching Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType
	 * @generated
	 */
	EEnum getFileSystemCachingType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType <em>Index Page Split Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Index Page Split Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType
	 * @generated
	 */
	EEnum getLUWIndexPageSplitType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType <em>Index Compress Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Index Compress Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType
	 * @generated
	 */
	EEnum getLUWIndexCompressType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType <em>System Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>System Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType
	 * @generated
	 */
	EEnum getSystemType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType <em>Average Table Size Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Average Table Size Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType
	 * @generated
	 */
	EEnum getAverageTableSizeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode <em>Storage Table Compression Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Storage Table Compression Mode</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode
	 * @generated
	 */
	EEnum getLUWStorageTableCompressionMode();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType <em>Member Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Member Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType
	 * @generated
	 */
	EEnum getLUWMemberType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.MemberStateType <em>Member State Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Member State Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.MemberStateType
	 * @generated
	 */
	EEnum getMemberStateType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType <em>Security Label Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Security Label Component Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType
	 * @generated
	 */
	EEnum getLUWSecurityLabelComponentType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction <em>Security Label Not Authorized Write Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Security Label Not Authorized Write Action</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction
	 * @generated
	 */
	EEnum getLUWSecurityLabelNotAuthorizedWriteAction();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource <em>Federated Data Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Federated Data Source</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource
	 * @generated
	 */
	EEnum getLUWFederatedDataSource();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption <em>Temporary Table Logging Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Temporary Table Logging Option</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption
	 * @generated
	 */
	EEnum getLUWTemporaryTableLoggingOption();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LUWFactory getLUWFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionGroupImpl <em>Partition Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionGroupImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionGroup()
		 * @generated
		 */
		EClass LUW_PARTITION_GROUP = eINSTANCE.getLUWPartitionGroup();

		/**
		 * The meta object literal for the '<em><b>Partitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_GROUP__PARTITIONS = eINSTANCE.getLUWPartitionGroup_Partitions();

		/**
		 * The meta object literal for the '<em><b>Table Spaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_GROUP__TABLE_SPACES = eINSTANCE.getLUWPartitionGroup_TableSpaces();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_GROUP__DATABASE = eINSTANCE.getLUWPartitionGroup_Database();

		/**
		 * The meta object literal for the '<em><b>Buffer Pool</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_GROUP__BUFFER_POOL = eINSTANCE.getLUWPartitionGroup_BufferPool();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl <em>Table Space</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableSpaceImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTableSpace()
		 * @generated
		 */
		EClass LUW_TABLE_SPACE = eINSTANCE.getLUWTableSpace();

		/**
		 * The meta object literal for the '<em><b>Temporary Storage Tables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__TEMPORARY_STORAGE_TABLES = eINSTANCE.getLUWTableSpace_TemporaryStorageTables();

		/**
		 * The meta object literal for the '<em><b>Tablespace Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__TABLESPACE_TYPE = eINSTANCE.getLUWTableSpace_TablespaceType();

		/**
		 * The meta object literal for the '<em><b>Management Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__MANAGEMENT_TYPE = eINSTANCE.getLUWTableSpace_ManagementType();

		/**
		 * The meta object literal for the '<em><b>Extent Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__EXTENT_SIZE = eINSTANCE.getLUWTableSpace_ExtentSize();

		/**
		 * The meta object literal for the '<em><b>Pre Fetch Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__PRE_FETCH_SIZE = eINSTANCE.getLUWTableSpace_PreFetchSize();

		/**
		 * The meta object literal for the '<em><b>Overhead</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__OVERHEAD = eINSTANCE.getLUWTableSpace_Overhead();

		/**
		 * The meta object literal for the '<em><b>Transfer Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__TRANSFER_RATE = eINSTANCE.getLUWTableSpace_TransferRate();

		/**
		 * The meta object literal for the '<em><b>Recover Dropped Table On</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__RECOVER_DROPPED_TABLE_ON = eINSTANCE.getLUWTableSpace_RecoverDroppedTableOn();

		/**
		 * The meta object literal for the '<em><b>Page Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__PAGE_SIZE = eINSTANCE.getLUWTableSpace_PageSize();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__SIZE = eINSTANCE.getLUWTableSpace_Size();

		/**
		 * The meta object literal for the '<em><b>Auto Resize</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__AUTO_RESIZE = eINSTANCE.getLUWTableSpace_AutoResize();

		/**
		 * The meta object literal for the '<em><b>Initial Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__INITIAL_SIZE = eINSTANCE.getLUWTableSpace_InitialSize();

		/**
		 * The meta object literal for the '<em><b>Increase Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__INCREASE_SIZE = eINSTANCE.getLUWTableSpace_IncreaseSize();

		/**
		 * The meta object literal for the '<em><b>Maximum Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__MAXIMUM_SIZE = eINSTANCE.getLUWTableSpace_MaximumSize();

		/**
		 * The meta object literal for the '<em><b>Initial Size Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__INITIAL_SIZE_UNIT = eINSTANCE.getLUWTableSpace_InitialSizeUnit();

		/**
		 * The meta object literal for the '<em><b>Maximum Size Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__MAXIMUM_SIZE_UNIT = eINSTANCE.getLUWTableSpace_MaximumSizeUnit();

		/**
		 * The meta object literal for the '<em><b>Increase Size Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__INCREASE_SIZE_UNIT = eINSTANCE.getLUWTableSpace_IncreaseSizeUnit();

		/**
		 * The meta object literal for the '<em><b>Increase Percent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__INCREASE_PERCENT = eINSTANCE.getLUWTableSpace_IncreasePercent();

		/**
		 * The meta object literal for the '<em><b>File System Caching</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__FILE_SYSTEM_CACHING = eINSTANCE.getLUWTableSpace_FileSystemCaching();

		/**
		 * The meta object literal for the '<em><b>Average Seek Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__AVERAGE_SEEK_TIME = eINSTANCE.getLUWTableSpace_AverageSeekTime();

		/**
		 * The meta object literal for the '<em><b>Rotation Speed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__ROTATION_SPEED = eINSTANCE.getLUWTableSpace_RotationSpeed();

		/**
		 * The meta object literal for the '<em><b>Transfer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__TRANSFER = eINSTANCE.getLUWTableSpace_Transfer();

		/**
		 * The meta object literal for the '<em><b>System Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__SYSTEM_TYPE = eINSTANCE.getLUWTableSpace_SystemType();

		/**
		 * The meta object literal for the '<em><b>Average Table Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__AVERAGE_TABLE_SIZE = eINSTANCE.getLUWTableSpace_AverageTableSize();

		/**
		 * The meta object literal for the '<em><b>External Container Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__EXTERNAL_CONTAINER_COUNT = eINSTANCE.getLUWTableSpace_ExternalContainerCount();

		/**
		 * The meta object literal for the '<em><b>Inherit Overhead</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__INHERIT_OVERHEAD = eINSTANCE.getLUWTableSpace_InheritOverhead();

		/**
		 * The meta object literal for the '<em><b>Inherit Transferate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__INHERIT_TRANSFERATE = eINSTANCE.getLUWTableSpace_InheritTransferate();

		/**
		 * The meta object literal for the '<em><b>Rebalance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__REBALANCE = eINSTANCE.getLUWTableSpace_Rebalance();

		/**
		 * The meta object literal for the '<em><b>Data Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__DATA_TAG = eINSTANCE.getLUWTableSpace_DataTag();

		/**
		 * The meta object literal for the '<em><b>Suspend Rebalance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__SUSPEND_REBALANCE = eINSTANCE.getLUWTableSpace_SuspendRebalance();

		/**
		 * The meta object literal for the '<em><b>Resume Rebalance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE_SPACE__RESUME_REBALANCE = eINSTANCE.getLUWTableSpace_ResumeRebalance();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__GROUP = eINSTANCE.getLUWTableSpace_Group();

		/**
		 * The meta object literal for the '<em><b>Containers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__CONTAINERS = eINSTANCE.getLUWTableSpace_Containers();

		/**
		 * The meta object literal for the '<em><b>Buffer Pool</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__BUFFER_POOL = eINSTANCE.getLUWTableSpace_BufferPool();

		/**
		 * The meta object literal for the '<em><b>Index Data Tables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__INDEX_DATA_TABLES = eINSTANCE.getLUWTableSpace_IndexDataTables();

		/**
		 * The meta object literal for the '<em><b>LOB Data Tables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__LOB_DATA_TABLES = eINSTANCE.getLUWTableSpace_LOBDataTables();

		/**
		 * The meta object literal for the '<em><b>Regular Data Tables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__REGULAR_DATA_TABLES = eINSTANCE.getLUWTableSpace_RegularDataTables();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__DATABASE = eINSTANCE.getLUWTableSpace_Database();

		/**
		 * The meta object literal for the '<em><b>LOB Data Partition</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__LOB_DATA_PARTITION = eINSTANCE.getLUWTableSpace_LOBDataPartition();

		/**
		 * The meta object literal for the '<em><b>Regular Data Partition</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__REGULAR_DATA_PARTITION = eINSTANCE.getLUWTableSpace_RegularDataPartition();

		/**
		 * The meta object literal for the '<em><b>Indexes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__INDEXES = eINSTANCE.getLUWTableSpace_Indexes();

		/**
		 * The meta object literal for the '<em><b>Index Data Partition</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__INDEX_DATA_PARTITION = eINSTANCE.getLUWTableSpace_IndexDataPartition();

		/**
		 * The meta object literal for the '<em><b>Storage Group</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE_SPACE__STORAGE_GROUP = eINSTANCE.getLUWTableSpace_StorageGroup();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl <em>Database Partition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePartitionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDatabasePartition()
		 * @generated
		 */
		EClass LUW_DATABASE_PARTITION = eINSTANCE.getLUWDatabasePartition();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PARTITION__NUMBER = eINSTANCE.getLUWDatabasePartition_Number();

		/**
		 * The meta object literal for the '<em><b>Port Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PARTITION__PORT_NUMBER = eINSTANCE.getLUWDatabasePartition_PortNumber();

		/**
		 * The meta object literal for the '<em><b>Host Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PARTITION__HOST_NAME = eINSTANCE.getLUWDatabasePartition_HostName();

		/**
		 * The meta object literal for the '<em><b>Switch Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PARTITION__SWITCH_NAME = eINSTANCE.getLUWDatabasePartition_SwitchName();

		/**
		 * The meta object literal for the '<em><b>Catalog Partition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PARTITION__CATALOG_PARTITION = eINSTANCE.getLUWDatabasePartition_CatalogPartition();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE_PARTITION__GROUP = eINSTANCE.getLUWDatabasePartition_Group();

		/**
		 * The meta object literal for the '<em><b>Buffer Pool</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE_PARTITION__BUFFER_POOL = eINSTANCE.getLUWDatabasePartition_BufferPool();

		/**
		 * The meta object literal for the '<em><b>Containers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE_PARTITION__CONTAINERS = eINSTANCE.getLUWDatabasePartition_Containers();

		/**
		 * The meta object literal for the '<em><b>Size Exception</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE_PARTITION__SIZE_EXCEPTION = eINSTANCE.getLUWDatabasePartition_SizeException();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl <em>Database Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseContainerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDatabaseContainer()
		 * @generated
		 */
		EClass LUW_DATABASE_CONTAINER = eINSTANCE.getLUWDatabaseContainer();

		/**
		 * The meta object literal for the '<em><b>Container Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_CONTAINER__CONTAINER_TYPE = eINSTANCE.getLUWDatabaseContainer_ContainerType();

		/**
		 * The meta object literal for the '<em><b>Size In Pages</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_CONTAINER__SIZE_IN_PAGES = eINSTANCE.getLUWDatabaseContainer_SizeInPages();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_CONTAINER__SIZE = eINSTANCE.getLUWDatabaseContainer_Size();

		/**
		 * The meta object literal for the '<em><b>Size Units</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_CONTAINER__SIZE_UNITS = eINSTANCE.getLUWDatabaseContainer_SizeUnits();

		/**
		 * The meta object literal for the '<em><b>Table Space</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE_CONTAINER__TABLE_SPACE = eINSTANCE.getLUWDatabaseContainer_TableSpace();

		/**
		 * The meta object literal for the '<em><b>Partitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE_CONTAINER__PARTITIONS = eINSTANCE.getLUWDatabaseContainer_Partitions();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAdminServerImpl <em>Admin Server</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAdminServerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWAdminServer()
		 * @generated
		 */
		EClass LUW_ADMIN_SERVER = eINSTANCE.getLUWAdminServer();

		/**
		 * The meta object literal for the '<em><b>Instances</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_ADMIN_SERVER__INSTANCES = eINSTANCE.getLUWAdminServer_Instances();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl <em>Buffer Pool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWBufferPool()
		 * @generated
		 */
		EClass LUW_BUFFER_POOL = eINSTANCE.getLUWBufferPool();

		/**
		 * The meta object literal for the '<em><b>Create Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_BUFFER_POOL__CREATE_TYPE = eINSTANCE.getLUWBufferPool_CreateType();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_BUFFER_POOL__SIZE = eINSTANCE.getLUWBufferPool_Size();

		/**
		 * The meta object literal for the '<em><b>Page Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_BUFFER_POOL__PAGE_SIZE = eINSTANCE.getLUWBufferPool_PageSize();

		/**
		 * The meta object literal for the '<em><b>Block Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_BUFFER_POOL__BLOCK_SIZE = eINSTANCE.getLUWBufferPool_BlockSize();

		/**
		 * The meta object literal for the '<em><b>Num Block Pages</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_BUFFER_POOL__NUM_BLOCK_PAGES = eINSTANCE.getLUWBufferPool_NumBlockPages();

		/**
		 * The meta object literal for the '<em><b>Extended Storage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_BUFFER_POOL__EXTENDED_STORAGE = eINSTANCE.getLUWBufferPool_ExtendedStorage();

		/**
		 * The meta object literal for the '<em><b>Automatic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_BUFFER_POOL__AUTOMATIC = eINSTANCE.getLUWBufferPool_Automatic();

		/**
		 * The meta object literal for the '<em><b>Table Spaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_BUFFER_POOL__TABLE_SPACES = eINSTANCE.getLUWBufferPool_TableSpaces();

		/**
		 * The meta object literal for the '<em><b>Partitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_BUFFER_POOL__PARTITIONS = eINSTANCE.getLUWBufferPool_Partitions();

		/**
		 * The meta object literal for the '<em><b>Partition Group</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_BUFFER_POOL__PARTITION_GROUP = eINSTANCE.getLUWBufferPool_PartitionGroup();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_BUFFER_POOL__DATABASE = eINSTANCE.getLUWBufferPool_Database();

		/**
		 * The meta object literal for the '<em><b>Size Exception</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_BUFFER_POOL__SIZE_EXCEPTION = eINSTANCE.getLUWBufferPool_SizeException();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableImpl <em>Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTableImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTable()
		 * @generated
		 */
		EClass LUW_TABLE = eINSTANCE.getLUWTable();

		/**
		 * The meta object literal for the '<em><b>PCT Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE__PCT_FREE = eINSTANCE.getLUWTable_PCTFree();

		/**
		 * The meta object literal for the '<em><b>Restrict On Drop</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE__RESTRICT_ON_DROP = eINSTANCE.getLUWTable_RestrictOnDrop();

		/**
		 * The meta object literal for the '<em><b>Partition Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE__PARTITION_MODE = eINSTANCE.getLUWTable_PartitionMode();

		/**
		 * The meta object literal for the '<em><b>Append Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE__APPEND_MODE = eINSTANCE.getLUWTable_AppendMode();

		/**
		 * The meta object literal for the '<em><b>Log Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE__LOG_MODE = eINSTANCE.getLUWTable_LogMode();

		/**
		 * The meta object literal for the '<em><b>Lock Size Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE__LOCK_SIZE_ROW = eINSTANCE.getLUWTable_LockSizeRow();

		/**
		 * The meta object literal for the '<em><b>Volatile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TABLE__VOLATILE = eINSTANCE.getLUWTable_Volatile();

		/**
		 * The meta object literal for the '<em><b>Security Policy</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE__SECURITY_POLICY = eINSTANCE.getLUWTable_SecurityPolicy();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TABLE__OPTIONS = eINSTANCE.getLUWTable_Options();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWViewImpl <em>View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWViewImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWView()
		 * @generated
		 */
		EClass LUW_VIEW = eINSTANCE.getLUWView();

		/**
		 * The meta object literal for the '<em><b>Federated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_VIEW__FEDERATED = eINSTANCE.getLUWView_Federated();

		/**
		 * The meta object literal for the '<em><b>Optimize Query</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_VIEW__OPTIMIZE_QUERY = eINSTANCE.getLUWView_OptimizeQuery();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionKeyImpl <em>Partition Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionKeyImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionKey()
		 * @generated
		 */
		EClass LUW_PARTITION_KEY = eINSTANCE.getLUWPartitionKey();

		/**
		 * The meta object literal for the '<em><b>Temporary Storage Table</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_KEY__TEMPORARY_STORAGE_TABLE = eINSTANCE.getLUWPartitionKey_TemporaryStorageTable();

		/**
		 * The meta object literal for the '<em><b>Partition Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_PARTITION_KEY__PARTITION_METHOD = eINSTANCE.getLUWPartitionKey_PartitionMethod();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_KEY__TABLE = eINSTANCE.getLUWPartitionKey_Table();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_KEY__COLUMNS = eINSTANCE.getLUWPartitionKey_Columns();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNicknameImpl <em>Nickname</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNicknameImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWNickname()
		 * @generated
		 */
		EClass LUW_NICKNAME = eINSTANCE.getLUWNickname();

		/**
		 * The meta object literal for the '<em><b>Remote Data Set</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_NICKNAME__REMOTE_DATA_SET = eINSTANCE.getLUWNickname_RemoteDataSet();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_NICKNAME__SERVER = eINSTANCE.getLUWNickname_Server();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl <em>Function Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWFunctionMappingImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWFunctionMapping()
		 * @generated
		 */
		EClass LUW_FUNCTION_MAPPING = eINSTANCE.getLUWFunctionMapping();

		/**
		 * The meta object literal for the '<em><b>Server Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_FUNCTION_MAPPING__SERVER_TYPE = eINSTANCE.getLUWFunctionMapping_ServerType();

		/**
		 * The meta object literal for the '<em><b>Server Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_FUNCTION_MAPPING__SERVER_VERSION = eINSTANCE.getLUWFunctionMapping_ServerVersion();

		/**
		 * The meta object literal for the '<em><b>Server Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_FUNCTION_MAPPING__SERVER_NAME = eINSTANCE.getLUWFunctionMapping_ServerName();

		/**
		 * The meta object literal for the '<em><b>Creation Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_FUNCTION_MAPPING__CREATION_TIME = eINSTANCE.getLUWFunctionMapping_CreationTime();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_FUNCTION_MAPPING__DISABLED = eINSTANCE.getLUWFunctionMapping_Disabled();

		/**
		 * The meta object literal for the '<em><b>Insts Per Invoc</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_FUNCTION_MAPPING__INSTS_PER_INVOC = eINSTANCE.getLUWFunctionMapping_InstsPerInvoc();

		/**
		 * The meta object literal for the '<em><b>Insts Per Arg Byte</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_FUNCTION_MAPPING__INSTS_PER_ARG_BYTE = eINSTANCE.getLUWFunctionMapping_InstsPerArgByte();

		/**
		 * The meta object literal for the '<em><b>Ios Per Invoc</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_FUNCTION_MAPPING__IOS_PER_INVOC = eINSTANCE.getLUWFunctionMapping_IosPerInvoc();

		/**
		 * The meta object literal for the '<em><b>Ios Per Arg Byte</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_FUNCTION_MAPPING__IOS_PER_ARG_BYTE = eINSTANCE.getLUWFunctionMapping_IosPerArgByte();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_FUNCTION_MAPPING__OPTIONS = eINSTANCE.getLUWFunctionMapping_Options();

		/**
		 * The meta object literal for the '<em><b>Local Function</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_FUNCTION_MAPPING__LOCAL_FUNCTION = eINSTANCE.getLUWFunctionMapping_LocalFunction();

		/**
		 * The meta object literal for the '<em><b>Remote Function</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_FUNCTION_MAPPING__REMOTE_FUNCTION = eINSTANCE.getLUWFunctionMapping_RemoteFunction();

		/**
		 * The meta object literal for the '<em><b>LUW Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_FUNCTION_MAPPING__LUW_DATABASE = eINSTANCE.getLUWFunctionMapping_LUWDatabase();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl <em>Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWWrapperImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWWrapper()
		 * @generated
		 */
		EClass LUW_WRAPPER = eINSTANCE.getLUWWrapper();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_WRAPPER__VERSION = eINSTANCE.getLUWWrapper_Version();

		/**
		 * The meta object literal for the '<em><b>Library</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_WRAPPER__LIBRARY = eINSTANCE.getLUWWrapper_Library();

		/**
		 * The meta object literal for the '<em><b>Fenced</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_WRAPPER__FENCED = eINSTANCE.getLUWWrapper_Fenced();

		/**
		 * The meta object literal for the '<em><b>Wrapper Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_WRAPPER__WRAPPER_TYPE = eINSTANCE.getLUWWrapper_WrapperType();

		/**
		 * The meta object literal for the '<em><b>Data Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_WRAPPER__DATA_SOURCE = eINSTANCE.getLUWWrapper_DataSource();

		/**
		 * The meta object literal for the '<em><b>Discovered Libraries</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_WRAPPER__DISCOVERED_LIBRARIES = eINSTANCE.getLUWWrapper_DiscoveredLibraries();

		/**
		 * The meta object literal for the '<em><b>Servers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_WRAPPER__SERVERS = eINSTANCE.getLUWWrapper_Servers();

		/**
		 * The meta object literal for the '<em><b>LUW Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_WRAPPER__LUW_DATABASE = eINSTANCE.getLUWWrapper_LUWDatabase();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_WRAPPER__OPTIONS = eINSTANCE.getLUWWrapper_Options();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalNicknameImpl <em>Non Relational Nickname</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalNicknameImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWNonRelationalNickname()
		 * @generated
		 */
		EClass LUW_NON_RELATIONAL_NICKNAME = eINSTANCE.getLUWNonRelationalNickname();

		/**
		 * The meta object literal for the '<em><b>Non Rel Server</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_NON_RELATIONAL_NICKNAME__NON_REL_SERVER = eINSTANCE.getLUWNonRelationalNickname_NonRelServer();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalServerImpl <em>Non Relational Server</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalServerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWNonRelationalServer()
		 * @generated
		 */
		EClass LUW_NON_RELATIONAL_SERVER = eINSTANCE.getLUWNonRelationalServer();

		/**
		 * The meta object literal for the '<em><b>Non Rel Wrapper</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_NON_RELATIONAL_SERVER__NON_REL_WRAPPER = eINSTANCE.getLUWNonRelationalServer_NonRelWrapper();

		/**
		 * The meta object literal for the '<em><b>Non Rel Nicknames</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_NON_RELATIONAL_SERVER__NON_REL_NICKNAMES = eINSTANCE.getLUWNonRelationalServer_NonRelNicknames();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalWrapperImpl <em>Non Relational Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWNonRelationalWrapperImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWNonRelationalWrapper()
		 * @generated
		 */
		EClass LUW_NON_RELATIONAL_WRAPPER = eINSTANCE.getLUWNonRelationalWrapper();

		/**
		 * The meta object literal for the '<em><b>Non Rel Servers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_NON_RELATIONAL_WRAPPER__NON_REL_SERVERS = eINSTANCE.getLUWNonRelationalWrapper_NonRelServers();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalNicknameImpl <em>Relational Nickname</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalNicknameImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWRelationalNickname()
		 * @generated
		 */
		EClass LUW_RELATIONAL_NICKNAME = eINSTANCE.getLUWRelationalNickname();

		/**
		 * The meta object literal for the '<em><b>Rel Server</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_RELATIONAL_NICKNAME__REL_SERVER = eINSTANCE.getLUWRelationalNickname_RelServer();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericUserMappingImpl <em>Generic User Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericUserMappingImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGenericUserMapping()
		 * @generated
		 */
		EClass LUW_GENERIC_USER_MAPPING = eINSTANCE.getLUWGenericUserMapping();

		/**
		 * The meta object literal for the '<em><b>Remote User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_GENERIC_USER_MAPPING__REMOTE_USER = eINSTANCE.getLUWGenericUserMapping_RemoteUser();

		/**
		 * The meta object literal for the '<em><b>Remote Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_GENERIC_USER_MAPPING__REMOTE_PASSWORD = eINSTANCE.getLUWGenericUserMapping_RemotePassword();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalWrapperImpl <em>Relational Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalWrapperImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWRelationalWrapper()
		 * @generated
		 */
		EClass LUW_RELATIONAL_WRAPPER = eINSTANCE.getLUWRelationalWrapper();

		/**
		 * The meta object literal for the '<em><b>Rel Servers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_RELATIONAL_WRAPPER__REL_SERVERS = eINSTANCE.getLUWRelationalWrapper_RelServers();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl <em>Server</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWServerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWServer()
		 * @generated
		 */
		EClass LUW_SERVER = eINSTANCE.getLUWServer();

		/**
		 * The meta object literal for the '<em><b>Server Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_SERVER__SERVER_TYPE = eINSTANCE.getLUWServer_ServerType();

		/**
		 * The meta object literal for the '<em><b>Server Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_SERVER__SERVER_VERSION = eINSTANCE.getLUWServer_ServerVersion();

		/**
		 * The meta object literal for the '<em><b>User Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SERVER__USER_MAPPINGS = eINSTANCE.getLUWServer_UserMappings();

		/**
		 * The meta object literal for the '<em><b>Wrapper</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SERVER__WRAPPER = eINSTANCE.getLUWServer_Wrapper();

		/**
		 * The meta object literal for the '<em><b>Nicknames</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SERVER__NICKNAMES = eINSTANCE.getLUWServer_Nicknames();

		/**
		 * The meta object literal for the '<em><b>LUW Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SERVER__LUW_DATABASE = eINSTANCE.getLUWServer_LUWDatabase();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SERVER__OPTIONS = eINSTANCE.getLUWServer_Options();

		/**
		 * The meta object literal for the '<em><b>Remote Server</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SERVER__REMOTE_SERVER = eINSTANCE.getLUWServer_RemoteServer();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl <em>Type Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTypeMappingImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTypeMapping()
		 * @generated
		 */
		EClass LUW_TYPE_MAPPING = eINSTANCE.getLUWTypeMapping();

		/**
		 * The meta object literal for the '<em><b>Server Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TYPE_MAPPING__SERVER_TYPE = eINSTANCE.getLUWTypeMapping_ServerType();

		/**
		 * The meta object literal for the '<em><b>Server Vesion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TYPE_MAPPING__SERVER_VESION = eINSTANCE.getLUWTypeMapping_ServerVesion();

		/**
		 * The meta object literal for the '<em><b>Server Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TYPE_MAPPING__SERVER_NAME = eINSTANCE.getLUWTypeMapping_ServerName();

		/**
		 * The meta object literal for the '<em><b>Creation Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TYPE_MAPPING__CREATION_TIME = eINSTANCE.getLUWTypeMapping_CreationTime();

		/**
		 * The meta object literal for the '<em><b>Local Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TYPE_MAPPING__LOCAL_TYPE = eINSTANCE.getLUWTypeMapping_LocalType();

		/**
		 * The meta object literal for the '<em><b>Remote Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TYPE_MAPPING__REMOTE_TYPE = eINSTANCE.getLUWTypeMapping_RemoteType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWUserMappingImpl <em>User Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWUserMappingImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWUserMapping()
		 * @generated
		 */
		EClass LUW_USER_MAPPING = eINSTANCE.getLUWUserMapping();

		/**
		 * The meta object literal for the '<em><b>Local Auth Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_USER_MAPPING__LOCAL_AUTH_ID = eINSTANCE.getLUWUserMapping_LocalAuthId();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_USER_MAPPING__SERVER = eINSTANCE.getLUWUserMapping_Server();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_USER_MAPPING__OPTIONS = eINSTANCE.getLUWUserMapping_Options();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWOptionImpl <em>Option</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWOptionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWOption()
		 * @generated
		 */
		EClass LUW_OPTION = eINSTANCE.getLUWOption();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_OPTION__VALUE = eINSTANCE.getLUWOption_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl <em>Relational Server</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWRelationalServer()
		 * @generated
		 */
		EClass LUW_RELATIONAL_SERVER = eINSTANCE.getLUWRelationalServer();

		/**
		 * The meta object literal for the '<em><b>Cpu Ratio</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__CPU_RATIO = eINSTANCE.getLUWRelationalServer_CpuRatio();

		/**
		 * The meta object literal for the '<em><b>Io Ratio</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__IO_RATIO = eINSTANCE.getLUWRelationalServer_IoRatio();

		/**
		 * The meta object literal for the '<em><b>Comm Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__COMM_RATE = eINSTANCE.getLUWRelationalServer_CommRate();

		/**
		 * The meta object literal for the '<em><b>Fold Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__FOLD_ID = eINSTANCE.getLUWRelationalServer_FoldId();

		/**
		 * The meta object literal for the '<em><b>Fold PW</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__FOLD_PW = eINSTANCE.getLUWRelationalServer_FoldPW();

		/**
		 * The meta object literal for the '<em><b>Collating Sequence</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__COLLATING_SEQUENCE = eINSTANCE.getLUWRelationalServer_CollatingSequence();

		/**
		 * The meta object literal for the '<em><b>Pushdown</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__PUSHDOWN = eINSTANCE.getLUWRelationalServer_Pushdown();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__NODE = eINSTANCE.getLUWRelationalServer_Node();

		/**
		 * The meta object literal for the '<em><b>Db Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__DB_NAME = eINSTANCE.getLUWRelationalServer_DbName();

		/**
		 * The meta object literal for the '<em><b>Iud App Svpt Enforce</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__IUD_APP_SVPT_ENFORCE = eINSTANCE.getLUWRelationalServer_IudAppSvptEnforce();

		/**
		 * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_RELATIONAL_SERVER__PASSWORD = eINSTANCE.getLUWRelationalServer_Password();

		/**
		 * The meta object literal for the '<em><b>Rel Nicknames</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_RELATIONAL_SERVER__REL_NICKNAMES = eINSTANCE.getLUWRelationalServer_RelNicknames();

		/**
		 * The meta object literal for the '<em><b>Rel Wrapper</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_RELATIONAL_SERVER__REL_WRAPPER = eINSTANCE.getLUWRelationalServer_RelWrapper();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl <em>Database</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabaseImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDatabase()
		 * @generated
		 */
		EClass LUW_DATABASE = eINSTANCE.getLUWDatabase();

		/**
		 * The meta object literal for the '<em><b>Federated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE__FEDERATED = eINSTANCE.getLUWDatabase_Federated();

		/**
		 * The meta object literal for the '<em><b>Groups</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__GROUPS = eINSTANCE.getLUWDatabase_Groups();

		/**
		 * The meta object literal for the '<em><b>Wrappers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__WRAPPERS = eINSTANCE.getLUWDatabase_Wrappers();

		/**
		 * The meta object literal for the '<em><b>Servers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__SERVERS = eINSTANCE.getLUWDatabase_Servers();

		/**
		 * The meta object literal for the '<em><b>Function Mappings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__FUNCTION_MAPPINGS = eINSTANCE.getLUWDatabase_FunctionMappings();

		/**
		 * The meta object literal for the '<em><b>Type Mappings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__TYPE_MAPPINGS = eINSTANCE.getLUWDatabase_TypeMappings();

		/**
		 * The meta object literal for the '<em><b>Reverse Type Mappings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__REVERSE_TYPE_MAPPINGS = eINSTANCE.getLUWDatabase_ReverseTypeMappings();

		/**
		 * The meta object literal for the '<em><b>Bufferpools</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__BUFFERPOOLS = eINSTANCE.getLUWDatabase_Bufferpools();

		/**
		 * The meta object literal for the '<em><b>Tablespaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__TABLESPACES = eINSTANCE.getLUWDatabase_Tablespaces();

		/**
		 * The meta object literal for the '<em><b>Storage Groups</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__STORAGE_GROUPS = eINSTANCE.getLUWDatabase_StorageGroups();

		/**
		 * The meta object literal for the '<em><b>Default Storage Group</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATABASE__DEFAULT_STORAGE_GROUP = eINSTANCE.getLUWDatabase_DefaultStorageGroup();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl <em>Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWColumn()
		 * @generated
		 */
		EClass LUW_COLUMN = eINSTANCE.getLUWColumn();

		/**
		 * The meta object literal for the '<em><b>Lob Logged</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_COLUMN__LOB_LOGGED = eINSTANCE.getLUWColumn_LobLogged();

		/**
		 * The meta object literal for the '<em><b>Lob Compacted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_COLUMN__LOB_COMPACTED = eINSTANCE.getLUWColumn_LobCompacted();

		/**
		 * The meta object literal for the '<em><b>Compression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_COLUMN__COMPRESSION = eINSTANCE.getLUWColumn_Compression();

		/**
		 * The meta object literal for the '<em><b>Inline Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_COLUMN__INLINE_LENGTH = eINSTANCE.getLUWColumn_InlineLength();

		/**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_COLUMN__HIDDEN = eINSTANCE.getLUWColumn_Hidden();

		/**
		 * The meta object literal for the '<em><b>Security Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_COLUMN__SECURITY_LABEL = eINSTANCE.getLUWColumn_SecurityLabel();

		/**
		 * The meta object literal for the '<em><b>Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_COLUMN__OPTIONS = eINSTANCE.getLUWColumn_Options();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericNicknameImpl <em>Generic Nickname</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericNicknameImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGenericNickname()
		 * @generated
		 */
		EClass LUW_GENERIC_NICKNAME = eINSTANCE.getLUWGenericNickname();

		/**
		 * The meta object literal for the '<em><b>Generic Server</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_GENERIC_NICKNAME__GENERIC_SERVER = eINSTANCE.getLUWGenericNickname_GenericServer();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericServerImpl <em>Generic Server</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericServerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGenericServer()
		 * @generated
		 */
		EClass LUW_GENERIC_SERVER = eINSTANCE.getLUWGenericServer();

		/**
		 * The meta object literal for the '<em><b>Generic Nicknames</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_GENERIC_SERVER__GENERIC_NICKNAMES = eINSTANCE.getLUWGenericServer_GenericNicknames();

		/**
		 * The meta object literal for the '<em><b>Generic Wrapper</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_GENERIC_SERVER__GENERIC_WRAPPER = eINSTANCE.getLUWGenericServer_GenericWrapper();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl <em>Materialized Query Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMaterializedQueryTableImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWMaterializedQueryTable()
		 * @generated
		 */
		EClass LUW_MATERIALIZED_QUERY_TABLE = eINSTANCE.getLUWMaterializedQueryTable();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericWrapperImpl <em>Generic Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGenericWrapperImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGenericWrapper()
		 * @generated
		 */
		EClass LUW_GENERIC_WRAPPER = eINSTANCE.getLUWGenericWrapper();

		/**
		 * The meta object literal for the '<em><b>Generic Servers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_GENERIC_WRAPPER__GENERIC_SERVERS = eINSTANCE.getLUWGenericWrapper_GenericServers();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable <em>Storage Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTable
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWStorageTable()
		 * @generated
		 */
		EClass LUW_STORAGE_TABLE = eINSTANCE.getLUWStorageTable();

		/**
		 * The meta object literal for the '<em><b>Value Compression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_STORAGE_TABLE__VALUE_COMPRESSION = eINSTANCE.getLUWStorageTable_ValueCompression();

		/**
		 * The meta object literal for the '<em><b>Row Compression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_STORAGE_TABLE__ROW_COMPRESSION = eINSTANCE.getLUWStorageTable_RowCompression();

		/**
		 * The meta object literal for the '<em><b>Row Compression Empty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_STORAGE_TABLE__ROW_COMPRESSION_EMPTY = eINSTANCE.getLUWStorageTable_RowCompressionEmpty();

		/**
		 * The meta object literal for the '<em><b>Compression Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_STORAGE_TABLE__COMPRESSION_MODE = eINSTANCE.getLUWStorageTable_CompressionMode();

		/**
		 * The meta object literal for the '<em><b>Partition Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_STORAGE_TABLE__PARTITION_KEY = eINSTANCE.getLUWStorageTable_PartitionKey();

		/**
		 * The meta object literal for the '<em><b>Index Data Table Space</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_STORAGE_TABLE__INDEX_DATA_TABLE_SPACE = eINSTANCE.getLUWStorageTable_IndexDataTableSpace();

		/**
		 * The meta object literal for the '<em><b>LOB Data Table Space</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_STORAGE_TABLE__LOB_DATA_TABLE_SPACE = eINSTANCE.getLUWStorageTable_LOBDataTableSpace();

		/**
		 * The meta object literal for the '<em><b>Regular Data Table Space</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_STORAGE_TABLE__REGULAR_DATA_TABLE_SPACE = eINSTANCE.getLUWStorageTable_RegularDataTableSpace();

		/**
		 * The meta object literal for the '<em><b>Data Partitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_STORAGE_TABLE__DATA_PARTITIONS = eINSTANCE.getLUWStorageTable_DataPartitions();

		/**
		 * The meta object literal for the '<em><b>Data Partition Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_STORAGE_TABLE__DATA_PARTITION_KEY = eINSTANCE.getLUWStorageTable_DataPartitionKey();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteServerImpl <em>Relational Remote Server</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteServerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRelationalRemoteServer()
		 * @generated
		 */
		EClass RELATIONAL_REMOTE_SERVER = eINSTANCE.getRelationalRemoteServer();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONAL_REMOTE_SERVER__DATABASE = eINSTANCE.getRelationalRemoteServer_Database();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteDataSetImpl <em>Relational Remote Data Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteDataSetImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRelationalRemoteDataSet()
		 * @generated
		 */
		EClass RELATIONAL_REMOTE_DATA_SET = eINSTANCE.getRelationalRemoteDataSet();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONAL_REMOTE_DATA_SET__TABLE = eINSTANCE.getRelationalRemoteDataSet_Table();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RemoteServerImpl <em>Remote Server</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RemoteServerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRemoteServer()
		 * @generated
		 */
		EClass REMOTE_SERVER = eINSTANCE.getRemoteServer();

		/**
		 * The meta object literal for the '<em><b>LUW Server</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REMOTE_SERVER__LUW_SERVER = eINSTANCE.getRemoteServer_LUWServer();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RemoteDataSetImpl <em>Remote Data Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RemoteDataSetImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRemoteDataSet()
		 * @generated
		 */
		EClass REMOTE_DATA_SET = eINSTANCE.getRemoteDataSet();

		/**
		 * The meta object literal for the '<em><b>Nickname</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REMOTE_DATA_SET__NICKNAME = eINSTANCE.getRemoteDataSet_Nickname();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl <em>Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWIndex()
		 * @generated
		 */
		EClass LUW_INDEX = eINSTANCE.getLUWIndex();

		/**
		 * The meta object literal for the '<em><b>PCT Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__PCT_FREE = eINSTANCE.getLUWIndex_PCTFree();

		/**
		 * The meta object literal for the '<em><b>Min PCT Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__MIN_PCT_FREE = eINSTANCE.getLUWIndex_MinPCTFree();

		/**
		 * The meta object literal for the '<em><b>Reverse Scan</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__REVERSE_SCAN = eINSTANCE.getLUWIndex_ReverseScan();

		/**
		 * The meta object literal for the '<em><b>Not Partitioned</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__NOT_PARTITIONED = eINSTANCE.getLUWIndex_NotPartitioned();

		/**
		 * The meta object literal for the '<em><b>Xml Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__XML_PATTERN = eINSTANCE.getLUWIndex_XmlPattern();

		/**
		 * The meta object literal for the '<em><b>As SQL Data Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_INDEX__AS_SQL_DATA_TYPE = eINSTANCE.getLUWIndex_AsSQLDataType();

		/**
		 * The meta object literal for the '<em><b>As SQL Data Type Hashed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__AS_SQL_DATA_TYPE_HASHED = eINSTANCE.getLUWIndex_AsSQLDataTypeHashed();

		/**
		 * The meta object literal for the '<em><b>System Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__SYSTEM_REQUIRED = eINSTANCE.getLUWIndex_SystemRequired();

		/**
		 * The meta object literal for the '<em><b>Page Split Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__PAGE_SPLIT_TYPE = eINSTANCE.getLUWIndex_PageSplitType();

		/**
		 * The meta object literal for the '<em><b>Level2 Pct Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__LEVEL2_PCT_FREE = eINSTANCE.getLUWIndex_Level2PctFree();

		/**
		 * The meta object literal for the '<em><b>Min Pct Used</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__MIN_PCT_USED = eINSTANCE.getLUWIndex_MinPctUsed();

		/**
		 * The meta object literal for the '<em><b>Compress</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__COMPRESS = eINSTANCE.getLUWIndex_Compress();

		/**
		 * The meta object literal for the '<em><b>Collect Stats</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__COLLECT_STATS = eINSTANCE.getLUWIndex_CollectStats();

		/**
		 * The meta object literal for the '<em><b>Sampled Stats</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__SAMPLED_STATS = eINSTANCE.getLUWIndex_SampledStats();

		/**
		 * The meta object literal for the '<em><b>Detailed Stats</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__DETAILED_STATS = eINSTANCE.getLUWIndex_DetailedStats();

		/**
		 * The meta object literal for the '<em><b>Ignore Invalid Values</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__IGNORE_INVALID_VALUES = eINSTANCE.getLUWIndex_IgnoreInvalidValues();

		/**
		 * The meta object literal for the '<em><b>Exclude Null Keys</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_INDEX__EXCLUDE_NULL_KEYS = eINSTANCE.getLUWIndex_ExcludeNullKeys();

		/**
		 * The meta object literal for the '<em><b>Tablespace</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_INDEX__TABLESPACE = eINSTANCE.getLUWIndex_Tablespace();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAttributeDefinitionImpl <em>Attribute Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAttributeDefinitionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWAttributeDefinition()
		 * @generated
		 */
		EClass LUW_ATTRIBUTE_DEFINITION = eINSTANCE.getLUWAttributeDefinition();

		/**
		 * The meta object literal for the '<em><b>LOB Logged</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_ATTRIBUTE_DEFINITION__LOB_LOGGED = eINSTANCE.getLUWAttributeDefinition_LOBLogged();

		/**
		 * The meta object literal for the '<em><b>LOB Compacted</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_ATTRIBUTE_DEFINITION__LOB_COMPACTED = eINSTANCE.getLUWAttributeDefinition_LOBCompacted();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl <em>Federated Procedure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedProcedureImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getFederatedProcedure()
		 * @generated
		 */
		EClass FEDERATED_PROCEDURE = eINSTANCE.getFederatedProcedure();

		/**
		 * The meta object literal for the '<em><b>Remote Unique Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PROCEDURE__REMOTE_UNIQUE_ID = eINSTANCE.getFederatedProcedure_RemoteUniqueId();

		/**
		 * The meta object literal for the '<em><b>Remote Server</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PROCEDURE__REMOTE_SERVER = eINSTANCE.getFederatedProcedure_RemoteServer();

		/**
		 * The meta object literal for the '<em><b>Remote Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PROCEDURE__REMOTE_SCHEMA = eINSTANCE.getFederatedProcedure_RemoteSchema();

		/**
		 * The meta object literal for the '<em><b>Remote Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PROCEDURE__REMOTE_PACKAGE = eINSTANCE.getFederatedProcedure_RemotePackage();

		/**
		 * The meta object literal for the '<em><b>Remote Procedure Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PROCEDURE__REMOTE_PROCEDURE_NAME = eINSTANCE.getFederatedProcedure_RemoteProcedureName();

		/**
		 * The meta object literal for the '<em><b>Number Of Parameters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PROCEDURE__NUMBER_OF_PARAMETERS = eINSTANCE.getFederatedProcedure_NumberOfParameters();

		/**
		 * The meta object literal for the '<em><b>Result Sets To Client</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PROCEDURE__RESULT_SETS_TO_CLIENT = eINSTANCE.getFederatedProcedure_ResultSetsToClient();

		/**
		 * The meta object literal for the '<em><b>Number Of Ref Cursors</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PROCEDURE__NUMBER_OF_REF_CURSORS = eINSTANCE.getFederatedProcedure_NumberOfRefCursors();

		/**
		 * The meta object literal for the '<em><b>All Result Sets To Caller</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PROCEDURE__ALL_RESULT_SETS_TO_CALLER = eINSTANCE.getFederatedProcedure_AllResultSetsToCaller();

		/**
		 * The meta object literal for the '<em><b>Federated Procedure</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEDERATED_PROCEDURE__FEDERATED_PROCEDURE = eINSTANCE.getFederatedProcedure_FederatedProcedure();

		/**
		 * The meta object literal for the '<em><b>Remote Procedure</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEDERATED_PROCEDURE__REMOTE_PROCEDURE = eINSTANCE.getFederatedProcedure_RemoteProcedure();

		/**
		 * The meta object literal for the '<em><b>Federated Parameter</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEDERATED_PROCEDURE__FEDERATED_PARAMETER = eINSTANCE.getFederatedProcedure_FederatedParameter();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedParameterImpl <em>Federated Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.FederatedParameterImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getFederatedParameter()
		 * @generated
		 */
		EClass FEDERATED_PARAMETER = eINSTANCE.getFederatedParameter();

		/**
		 * The meta object literal for the '<em><b>Remote Code Page</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PARAMETER__REMOTE_CODE_PAGE = eINSTANCE.getFederatedParameter_RemoteCodePage();

		/**
		 * The meta object literal for the '<em><b>Remote Param Type ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEDERATED_PARAMETER__REMOTE_PARAM_TYPE_ID = eINSTANCE.getFederatedParameter_RemoteParamTypeID();

		/**
		 * The meta object literal for the '<em><b>Federated Procedure</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEDERATED_PARAMETER__FEDERATED_PROCEDURE = eINSTANCE.getFederatedParameter_FederatedProcedure();

		/**
		 * The meta object literal for the '<em><b>Remote Parameter</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEDERATED_PARAMETER__REMOTE_PARAMETER = eINSTANCE.getFederatedParameter_RemoteParameter();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionExpressionImpl <em>Partition Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionExpressionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionExpression()
		 * @generated
		 */
		EClass LUW_PARTITION_EXPRESSION = eINSTANCE.getLUWPartitionExpression();

		/**
		 * The meta object literal for the '<em><b>Nulls Last</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_PARTITION_EXPRESSION__NULLS_LAST = eINSTANCE.getLUWPartitionExpression_NullsLast();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_EXPRESSION__KEY = eINSTANCE.getLUWPartitionExpression_Key();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_EXPRESSION__COLUMN = eINSTANCE.getLUWPartitionExpression_Column();

		/**
		 * The meta object literal for the '<em><b>Partition Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_EXPRESSION__PARTITION_ELEMENTS = eINSTANCE.getLUWPartitionExpression_PartitionElements();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionElementImpl <em>Partition Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionElementImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionElement()
		 * @generated
		 */
		EClass LUW_PARTITION_ELEMENT = eINSTANCE.getLUWPartitionElement();

		/**
		 * The meta object literal for the '<em><b>Starting</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_PARTITION_ELEMENT__STARTING = eINSTANCE.getLUWPartitionElement_Starting();

		/**
		 * The meta object literal for the '<em><b>Ending</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_PARTITION_ELEMENT__ENDING = eINSTANCE.getLUWPartitionElement_Ending();

		/**
		 * The meta object literal for the '<em><b>LUW Partition Expression</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_ELEMENT__LUW_PARTITION_EXPRESSION = eINSTANCE.getLUWPartitionElement_LUWPartitionExpression();

		/**
		 * The meta object literal for the '<em><b>Partition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_ELEMENT__PARTITION = eINSTANCE.getLUWPartitionElement_Partition();

		/**
		 * The meta object literal for the '<em><b>Every Clause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_ELEMENT__EVERY_CLAUSE = eINSTANCE.getLUWPartitionElement_EveryClause();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl <em>Data Partition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDataPartition()
		 * @generated
		 */
		EClass LUW_DATA_PARTITION = eINSTANCE.getLUWDataPartition();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATA_PARTITION__ID = eINSTANCE.getLUWDataPartition_Id();

		/**
		 * The meta object literal for the '<em><b>Low Inclusive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATA_PARTITION__LOW_INCLUSIVE = eINSTANCE.getLUWDataPartition_LowInclusive();

		/**
		 * The meta object literal for the '<em><b>High Inclusive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATA_PARTITION__HIGH_INCLUSIVE = eINSTANCE.getLUWDataPartition_HighInclusive();

		/**
		 * The meta object literal for the '<em><b>LOB Data Table Space</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATA_PARTITION__LOB_DATA_TABLE_SPACE = eINSTANCE.getLUWDataPartition_LOBDataTableSpace();

		/**
		 * The meta object literal for the '<em><b>Regular Data Table Space</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATA_PARTITION__REGULAR_DATA_TABLE_SPACE = eINSTANCE.getLUWDataPartition_RegularDataTableSpace();

		/**
		 * The meta object literal for the '<em><b>Partition Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATA_PARTITION__PARTITION_ELEMENTS = eINSTANCE.getLUWDataPartition_PartitionElements();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATA_PARTITION__TABLE = eINSTANCE.getLUWDataPartition_Table();

		/**
		 * The meta object literal for the '<em><b>Index Data Table Space</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATA_PARTITION__INDEX_DATA_TABLE_SPACE = eINSTANCE.getLUWDataPartition_IndexDataTableSpace();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionKeyImpl <em>Data Partition Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDataPartitionKeyImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDataPartitionKey()
		 * @generated
		 */
		EClass LUW_DATA_PARTITION_KEY = eINSTANCE.getLUWDataPartitionKey();

		/**
		 * The meta object literal for the '<em><b>Partition Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATA_PARTITION_KEY__PARTITION_METHOD = eINSTANCE.getLUWDataPartitionKey_PartitionMethod();

		/**
		 * The meta object literal for the '<em><b>Partition Expressions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATA_PARTITION_KEY__PARTITION_EXPRESSIONS = eINSTANCE.getLUWDataPartitionKey_PartitionExpressions();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_DATA_PARTITION_KEY__TABLE = eINSTANCE.getLUWDataPartitionKey_Table();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl <em>Database Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWDatabasePackageImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWDatabasePackage()
		 * @generated
		 */
		EClass LUW_DATABASE_PACKAGE = eINSTANCE.getLUWDatabasePackage();

		/**
		 * The meta object literal for the '<em><b>Creator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PACKAGE__CREATOR = eINSTANCE.getLUWDatabasePackage_Creator();

		/**
		 * The meta object literal for the '<em><b>Binder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PACKAGE__BINDER = eINSTANCE.getLUWDatabasePackage_Binder();

		/**
		 * The meta object literal for the '<em><b>Cursor Block</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PACKAGE__CURSOR_BLOCK = eINSTANCE.getLUWDatabasePackage_CursorBlock();

		/**
		 * The meta object literal for the '<em><b>Number Of Sections</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PACKAGE__NUMBER_OF_SECTIONS = eINSTANCE.getLUWDatabasePackage_NumberOfSections();

		/**
		 * The meta object literal for the '<em><b>Optimization Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PACKAGE__OPTIMIZATION_CLASS = eINSTANCE.getLUWDatabasePackage_OptimizationClass();

		/**
		 * The meta object literal for the '<em><b>Explain Snapshot</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_DATABASE_PACKAGE__EXPLAIN_SNAPSHOT = eINSTANCE.getLUWDatabasePackage_ExplainSnapshot();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleImpl <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModule()
		 * @generated
		 */
		EClass LUW_MODULE = eINSTANCE.getLUWModule();

		/**
		 * The meta object literal for the '<em><b>Dialect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MODULE__DIALECT = eINSTANCE.getLUWModule_Dialect();

		/**
		 * The meta object literal for the '<em><b>Owning Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_MODULE__OWNING_SCHEMA = eINSTANCE.getLUWModule_OwningSchema();

		/**
		 * The meta object literal for the '<em><b>Module Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_MODULE__MODULE_OBJECTS = eINSTANCE.getLUWModule_ModuleObjects();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject <em>Module Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleObject
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleObject()
		 * @generated
		 */
		EClass LUW_MODULE_OBJECT = eINSTANCE.getLUWModuleObject();

		/**
		 * The meta object literal for the '<em><b>Published</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MODULE_OBJECT__PUBLISHED = eINSTANCE.getLUWModuleObject_Published();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_MODULE_OBJECT__MODULE = eINSTANCE.getLUWModuleObject_Module();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleFunctionImpl <em>Module Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleFunctionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleFunction()
		 * @generated
		 */
		EClass LUW_MODULE_FUNCTION = eINSTANCE.getLUWModuleFunction();

		/**
		 * The meta object literal for the '<em><b>Implemented</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MODULE_FUNCTION__IMPLEMENTED = eINSTANCE.getLUWModuleFunction_Implemented();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleProcedureImpl <em>Module Procedure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleProcedureImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleProcedure()
		 * @generated
		 */
		EClass LUW_MODULE_PROCEDURE = eINSTANCE.getLUWModuleProcedure();

		/**
		 * The meta object literal for the '<em><b>Implemented</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MODULE_PROCEDURE__IMPLEMENTED = eINSTANCE.getLUWModuleProcedure_Implemented();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleConditionImpl <em>Module Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleConditionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleCondition()
		 * @generated
		 */
		EClass LUW_MODULE_CONDITION = eINSTANCE.getLUWModuleCondition();

		/**
		 * The meta object literal for the '<em><b>Sqlstate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MODULE_CONDITION__SQLSTATE = eINSTANCE.getLUWModuleCondition_Sqlstate();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGlobalVariableImpl <em>Global Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWGlobalVariableImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWGlobalVariable()
		 * @generated
		 */
		EClass LUW_GLOBAL_VARIABLE = eINSTANCE.getLUWGlobalVariable();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_GLOBAL_VARIABLE__DEFAULT_VALUE = eINSTANCE.getLUWGlobalVariable_DefaultValue();

		/**
		 * The meta object literal for the '<em><b>Is Constant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_GLOBAL_VARIABLE__IS_CONSTANT = eINSTANCE.getLUWGlobalVariable_IsConstant();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_GLOBAL_VARIABLE__SCHEMA = eINSTANCE.getLUWGlobalVariable_Schema();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleType <em>Module Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWModuleType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleType()
		 * @generated
		 */
		EClass LUW_MODULE_TYPE = eINSTANCE.getLUWModuleType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleRowDataTypeImpl <em>Module Row Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleRowDataTypeImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleRowDataType()
		 * @generated
		 */
		EClass LUW_MODULE_ROW_DATA_TYPE = eINSTANCE.getLUWModuleRowDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleArrayDataTypeImpl <em>Module Array Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleArrayDataTypeImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleArrayDataType()
		 * @generated
		 */
		EClass LUW_MODULE_ARRAY_DATA_TYPE = eINSTANCE.getLUWModuleArrayDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleDistinctTypeImpl <em>Module Distinct Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleDistinctTypeImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleDistinctType()
		 * @generated
		 */
		EClass LUW_MODULE_DISTINCT_TYPE = eINSTANCE.getLUWModuleDistinctType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleGlobalVariableImpl <em>Module Global Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleGlobalVariableImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleGlobalVariable()
		 * @generated
		 */
		EClass LUW_MODULE_GLOBAL_VARIABLE = eINSTANCE.getLUWModuleGlobalVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWArrayDataTypeImpl <em>Array Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWArrayDataTypeImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWArrayDataType()
		 * @generated
		 */
		EClass LUW_ARRAY_DATA_TYPE = eINSTANCE.getLUWArrayDataType();

		/**
		 * The meta object literal for the '<em><b>Array Index Element Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_ARRAY_DATA_TYPE__ARRAY_INDEX_ELEMENT_TYPE = eINSTANCE.getLUWArrayDataType_ArrayIndexElementType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRowDataTypeImpl <em>Row Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRowDataTypeImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWRowDataType()
		 * @generated
		 */
		EClass LUW_ROW_DATA_TYPE = eINSTANCE.getLUWRowDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl <em>PLSQL Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getPLSQLPackage()
		 * @generated
		 */
		EClass PLSQL_PACKAGE = eINSTANCE.getPLSQLPackage();

		/**
		 * The meta object literal for the '<em><b>Package Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLSQL_PACKAGE__PACKAGE_BODY = eINSTANCE.getPLSQLPackage_PackageBody();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageBodyImpl <em>PLSQL Package Body</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageBodyImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getPLSQLPackageBody()
		 * @generated
		 */
		EClass PLSQL_PACKAGE_BODY = eINSTANCE.getPLSQLPackageBody();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PLSQL_PACKAGE_BODY__PACKAGE = eINSTANCE.getPLSQLPackageBody_Package();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWCursorDataTypeImpl <em>Cursor Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWCursorDataTypeImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWCursorDataType()
		 * @generated
		 */
		EClass LUW_CURSOR_DATA_TYPE = eINSTANCE.getLUWCursorDataType();

		/**
		 * The meta object literal for the '<em><b>Row Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_CURSOR_DATA_TYPE__ROW_TYPE = eINSTANCE.getLUWCursorDataType_RowType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleCursorDataTypeImpl <em>Module Cursor Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWModuleCursorDataTypeImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWModuleCursorDataType()
		 * @generated
		 */
		EClass LUW_MODULE_CURSOR_DATA_TYPE = eINSTANCE.getLUWModuleCursorDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolSizeExceptionImpl <em>Buffer Pool Size Exception</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWBufferPoolSizeExceptionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWBufferPoolSizeException()
		 * @generated
		 */
		EClass LUW_BUFFER_POOL_SIZE_EXCEPTION = eINSTANCE.getLUWBufferPoolSizeException();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_BUFFER_POOL_SIZE_EXCEPTION__SIZE = eINSTANCE.getLUWBufferPoolSizeException_Size();

		/**
		 * The meta object literal for the '<em><b>Buffer Pool</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_BUFFER_POOL_SIZE_EXCEPTION__BUFFER_POOL = eINSTANCE.getLUWBufferPoolSizeException_BufferPool();

		/**
		 * The meta object literal for the '<em><b>Partitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_BUFFER_POOL_SIZE_EXCEPTION__PARTITIONS = eINSTANCE.getLUWBufferPoolSizeException_Partitions();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMemberImpl <em>Member</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWMemberImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWMember()
		 * @generated
		 */
		EClass LUW_MEMBER = eINSTANCE.getLUWMember();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MEMBER__TYPE = eINSTANCE.getLUWMember_Type();

		/**
		 * The meta object literal for the '<em><b>Alert</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MEMBER__ALERT = eINSTANCE.getLUWMember_Alert();

		/**
		 * The meta object literal for the '<em><b>Db Partition Num</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MEMBER__DB_PARTITION_NUM = eINSTANCE.getLUWMember_DbPartitionNum();

		/**
		 * The meta object literal for the '<em><b>Logical Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MEMBER__LOGICAL_PORT = eINSTANCE.getLUWMember_LogicalPort();

		/**
		 * The meta object literal for the '<em><b>Net Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_MEMBER__NET_NAME = eINSTANCE.getLUWMember_NetName();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityPolicyImpl <em>Security Policy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityPolicyImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityPolicy()
		 * @generated
		 */
		EClass LUW_SECURITY_POLICY = eINSTANCE.getLUWSecurityPolicy();

		/**
		 * The meta object literal for the '<em><b>Not Authorized Write</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_SECURITY_POLICY__NOT_AUTHORIZED_WRITE = eINSTANCE.getLUWSecurityPolicy_NotAuthorizedWrite();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SECURITY_POLICY__COMPONENTS = eINSTANCE.getLUWSecurityPolicy_Components();

		/**
		 * The meta object literal for the '<em><b>Labels</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SECURITY_POLICY__LABELS = eINSTANCE.getLUWSecurityPolicy_Labels();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SECURITY_POLICY__TABLE = eINSTANCE.getLUWSecurityPolicy_Table();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentImpl <em>Security Label Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabelComponent()
		 * @generated
		 */
		EClass LUW_SECURITY_LABEL_COMPONENT = eINSTANCE.getLUWSecurityLabelComponent();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_SECURITY_LABEL_COMPONENT__TYPE = eINSTANCE.getLUWSecurityLabelComponent_Type();

		/**
		 * The meta object literal for the '<em><b>LUW Security Policy</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SECURITY_LABEL_COMPONENT__LUW_SECURITY_POLICY = eINSTANCE.getLUWSecurityLabelComponent_LUWSecurityPolicy();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SECURITY_LABEL_COMPONENT__ELEMENTS = eINSTANCE.getLUWSecurityLabelComponent_Elements();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelImpl <em>Security Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabel()
		 * @generated
		 */
		EClass LUW_SECURITY_LABEL = eINSTANCE.getLUWSecurityLabel();

		/**
		 * The meta object literal for the '<em><b>Security Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_SECURITY_LABEL__SECURITY_LABEL = eINSTANCE.getLUWSecurityLabel_SecurityLabel();

		/**
		 * The meta object literal for the '<em><b>Policy</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SECURITY_LABEL__POLICY = eINSTANCE.getLUWSecurityLabel_Policy();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentElementImpl <em>Security Label Component Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWSecurityLabelComponentElementImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabelComponentElement()
		 * @generated
		 */
		EClass LUW_SECURITY_LABEL_COMPONENT_ELEMENT = eINSTANCE.getLUWSecurityLabelComponentElement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_SECURITY_LABEL_COMPONENT_ELEMENT__VALUE = eINSTANCE.getLUWSecurityLabelComponentElement_Value();

		/**
		 * The meta object literal for the '<em><b>Parent Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_SECURITY_LABEL_COMPONENT_ELEMENT__PARENT_VALUE = eINSTANCE.getLUWSecurityLabelComponentElement_ParentValue();

		/**
		 * The meta object literal for the '<em><b>LUW Security Label Component</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_SECURITY_LABEL_COMPONENT_ELEMENT__LUW_SECURITY_LABEL_COMPONENT = eINSTANCE.getLUWSecurityLabelComponentElement_LUWSecurityLabelComponent();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl <em>Storage Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWStorageGroupImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWStorageGroup()
		 * @generated
		 */
		EClass LUW_STORAGE_GROUP = eINSTANCE.getLUWStorageGroup();

		/**
		 * The meta object literal for the '<em><b>Storage Paths</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_STORAGE_GROUP__STORAGE_PATHS = eINSTANCE.getLUWStorageGroup_StoragePaths();

		/**
		 * The meta object literal for the '<em><b>Overhead</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_STORAGE_GROUP__OVERHEAD = eINSTANCE.getLUWStorageGroup_Overhead();

		/**
		 * The meta object literal for the '<em><b>Device Read Rate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_STORAGE_GROUP__DEVICE_READ_RATE = eINSTANCE.getLUWStorageGroup_DeviceReadRate();

		/**
		 * The meta object literal for the '<em><b>Data Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_STORAGE_GROUP__DATA_TAG = eINSTANCE.getLUWStorageGroup_DataTag();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_STORAGE_GROUP__DEFAULT = eINSTANCE.getLUWStorageGroup_Default();

		/**
		 * The meta object literal for the '<em><b>Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_STORAGE_GROUP__DATABASE = eINSTANCE.getLUWStorageGroup_Database();

		/**
		 * The meta object literal for the '<em><b>Table Spaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_STORAGE_GROUP__TABLE_SPACES = eINSTANCE.getLUWStorageGroup_TableSpaces();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryStorageTableImpl <em>Temporary Storage Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryStorageTableImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTemporaryStorageTable()
		 * @generated
		 */
		EClass LUW_TEMPORARY_STORAGE_TABLE = eINSTANCE.getLUWTemporaryStorageTable();

		/**
		 * The meta object literal for the '<em><b>Partition Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TEMPORARY_STORAGE_TABLE__PARTITION_KEY = eINSTANCE.getLUWTemporaryStorageTable_PartitionKey();

		/**
		 * The meta object literal for the '<em><b>User Temporary Table Space</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TEMPORARY_STORAGE_TABLE__USER_TEMPORARY_TABLE_SPACE = eINSTANCE.getLUWTemporaryStorageTable_UserTemporaryTableSpace();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryTableImpl <em>Temporary Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWTemporaryTableImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTemporaryTable()
		 * @generated
		 */
		EClass LUW_TEMPORARY_TABLE = eINSTANCE.getLUWTemporaryTable();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_TEMPORARY_TABLE__TABLE = eINSTANCE.getLUWTemporaryTable_Table();

		/**
		 * The meta object literal for the '<em><b>Log Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_TEMPORARY_TABLE__LOG_OPTION = eINSTANCE.getLUWTemporaryTable_LogOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.ArrayIndexElementTypeImpl <em>Array Index Element Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.ArrayIndexElementTypeImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getArrayIndexElementType()
		 * @generated
		 */
		EClass ARRAY_INDEX_ELEMENT_TYPE = eINSTANCE.getArrayIndexElementType();

		/**
		 * The meta object literal for the '<em><b>LUW Array Data Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_INDEX_ELEMENT_TYPE__LUW_ARRAY_DATA_TYPE = eINSTANCE.getArrayIndexElementType_LUWArrayDataType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionEveryClauseElementImpl <em>Partition Every Clause Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPartitionEveryClauseElementImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWPartitionEveryClauseElement()
		 * @generated
		 */
		EClass LUW_PARTITION_EVERY_CLAUSE_ELEMENT = eINSTANCE.getLUWPartitionEveryClauseElement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_PARTITION_EVERY_CLAUSE_ELEMENT__VALUE = eINSTANCE.getLUWPartitionEveryClauseElement_Value();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUW_PARTITION_EVERY_CLAUSE_ELEMENT__DURATION = eINSTANCE.getLUWPartitionEveryClauseElement_Duration();

		/**
		 * The meta object literal for the '<em><b>LUW Partition Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LUW_PARTITION_EVERY_CLAUSE_ELEMENT__LUW_PARTITION_ELEMENT = eINSTANCE.getLUWPartitionEveryClauseElement_LUWPartitionElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType <em>Container Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWContainerType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWContainerType()
		 * @generated
		 */
		EEnum LUW_CONTAINER_TYPE = eINSTANCE.getLUWContainerType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType <em>Page Size Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PageSizeType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getPageSizeType()
		 * @generated
		 */
		EEnum PAGE_SIZE_TYPE = eINSTANCE.getPageSizeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType <em>Buffer Pool Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.BufferPoolType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getBufferPoolType()
		 * @generated
		 */
		EEnum BUFFER_POOL_TYPE = eINSTANCE.getBufferPoolType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType <em>Table Space Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.TableSpaceType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getTableSpaceType()
		 * @generated
		 */
		EEnum TABLE_SPACE_TYPE = eINSTANCE.getTableSpaceType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType <em>Management Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ManagementType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getManagementType()
		 * @generated
		 */
		EEnum MANAGEMENT_TYPE = eINSTANCE.getManagementType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.CheckOptionType <em>Check Option Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.CheckOptionType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getCheckOptionType()
		 * @generated
		 */
		EEnum CHECK_OPTION_TYPE = eINSTANCE.getCheckOptionType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod <em>Partition Method</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.PartitionMethod
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getPartitionMethod()
		 * @generated
		 */
		EEnum PARTITION_METHOD = eINSTANCE.getPartitionMethod();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.MaintenanceType <em>Maintenance Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.MaintenanceType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getMaintenanceType()
		 * @generated
		 */
		EEnum MAINTENANCE_TYPE = eINSTANCE.getMaintenanceType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.RefreshType <em>Refresh Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.RefreshType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getRefreshType()
		 * @generated
		 */
		EEnum REFRESH_TYPE = eINSTANCE.getRefreshType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType <em>Wrapper Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.WrapperType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getWrapperType()
		 * @generated
		 */
		EEnum WRAPPER_TYPE = eINSTANCE.getWrapperType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod <em>Data Partition Method</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.DataPartitionMethod
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getDataPartitionMethod()
		 * @generated
		 */
		EEnum DATA_PARTITION_METHOD = eINSTANCE.getDataPartitionMethod();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType <em>Cursor Block Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.CursorBlockType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getCursorBlockType()
		 * @generated
		 */
		EEnum CURSOR_BLOCK_TYPE = eINSTANCE.getCursorBlockType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType <em>Explain Snaphot Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.ExplainSnaphotType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getExplainSnaphotType()
		 * @generated
		 */
		EEnum EXPLAIN_SNAPHOT_TYPE = eINSTANCE.getExplainSnaphotType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType <em>File System Caching Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.FileSystemCachingType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getFileSystemCachingType()
		 * @generated
		 */
		EEnum FILE_SYSTEM_CACHING_TYPE = eINSTANCE.getFileSystemCachingType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType <em>Index Page Split Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWIndexPageSplitType()
		 * @generated
		 */
		EEnum LUW_INDEX_PAGE_SPLIT_TYPE = eINSTANCE.getLUWIndexPageSplitType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType <em>Index Compress Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWIndexCompressType()
		 * @generated
		 */
		EEnum LUW_INDEX_COMPRESS_TYPE = eINSTANCE.getLUWIndexCompressType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType <em>System Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.SystemType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getSystemType()
		 * @generated
		 */
		EEnum SYSTEM_TYPE = eINSTANCE.getSystemType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType <em>Average Table Size Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.AverageTableSizeType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getAverageTableSizeType()
		 * @generated
		 */
		EEnum AVERAGE_TABLE_SIZE_TYPE = eINSTANCE.getAverageTableSizeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode <em>Storage Table Compression Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWStorageTableCompressionMode
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWStorageTableCompressionMode()
		 * @generated
		 */
		EEnum LUW_STORAGE_TABLE_COMPRESSION_MODE = eINSTANCE.getLUWStorageTableCompressionMode();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType <em>Member Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWMemberType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWMemberType()
		 * @generated
		 */
		EEnum LUW_MEMBER_TYPE = eINSTANCE.getLUWMemberType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.MemberStateType <em>Member State Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.MemberStateType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getMemberStateType()
		 * @generated
		 */
		EEnum MEMBER_STATE_TYPE = eINSTANCE.getMemberStateType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType <em>Security Label Component Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelComponentType
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabelComponentType()
		 * @generated
		 */
		EEnum LUW_SECURITY_LABEL_COMPONENT_TYPE = eINSTANCE.getLUWSecurityLabelComponentType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction <em>Security Label Not Authorized Write Action</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabelNotAuthorizedWriteAction
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWSecurityLabelNotAuthorizedWriteAction()
		 * @generated
		 */
		EEnum LUW_SECURITY_LABEL_NOT_AUTHORIZED_WRITE_ACTION = eINSTANCE.getLUWSecurityLabelNotAuthorizedWriteAction();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource <em>Federated Data Source</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFederatedDataSource
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWFederatedDataSource()
		 * @generated
		 */
		EEnum LUW_FEDERATED_DATA_SOURCE = eINSTANCE.getLUWFederatedDataSource();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption <em>Temporary Table Logging Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTemporaryTableLoggingOption
		 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWPackageImpl#getLUWTemporaryTableLoggingOption()
		 * @generated
		 */
		EEnum LUW_TEMPORARY_TABLE_LOGGING_OPTION = eINSTANCE.getLUWTemporaryTableLoggingOption();

	}

} //LUWPackage
