/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionPackage.java,v 1.1 2008/03/27 07:41:13 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionFactory
 * @model kind="package"
 * @generated
 */
public interface PartitionPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "partition"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/connectivity/sqm/sybase/ase/sybasease../../org.eclipse.datatools.modelbase.sql/model/../../org.eclipse.datatools.modelbase.sql/model/sqlmodel.ecore/partition.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Partition"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PartitionPackage eINSTANCE = org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEPartitionImpl <em>Sybase ASE Partition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEPartitionImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASEPartition()
	 * @generated
	 */
	int SYBASE_ASE_PARTITION = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PARTITION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PARTITION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PARTITION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PARTITION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PARTITION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PARTITION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PARTITION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PARTITION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The number of structural features of the '<em>Sybase ASE Partition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PARTITION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERangePartitionImpl <em>Sybase ASE Range Partition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERangePartitionImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASERangePartition()
	 * @generated
	 */
	int SYBASE_ASE_RANGE_PARTITION = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__EANNOTATIONS = SYBASE_ASE_PARTITION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__NAME = SYBASE_ASE_PARTITION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__DEPENDENCIES = SYBASE_ASE_PARTITION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__DESCRIPTION = SYBASE_ASE_PARTITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__LABEL = SYBASE_ASE_PARTITION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__COMMENTS = SYBASE_ASE_PARTITION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__EXTENSIONS = SYBASE_ASE_PARTITION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__PRIVILEGES = SYBASE_ASE_PARTITION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__COLUMNS = SYBASE_ASE_PARTITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Range Partition Items</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION__RANGE_PARTITION_ITEMS = SYBASE_ASE_PARTITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Sybase ASE Range Partition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RANGE_PARTITION_FEATURE_COUNT = SYBASE_ASE_PARTITION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEHashPartitionImpl <em>Sybase ASE Hash Partition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEHashPartitionImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASEHashPartition()
	 * @generated
	 */
	int SYBASE_ASE_HASH_PARTITION = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__EANNOTATIONS = SYBASE_ASE_PARTITION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__NAME = SYBASE_ASE_PARTITION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__DEPENDENCIES = SYBASE_ASE_PARTITION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__DESCRIPTION = SYBASE_ASE_PARTITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__LABEL = SYBASE_ASE_PARTITION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__COMMENTS = SYBASE_ASE_PARTITION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__EXTENSIONS = SYBASE_ASE_PARTITION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__PRIVILEGES = SYBASE_ASE_PARTITION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__COLUMNS = SYBASE_ASE_PARTITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Partition Segment Pairs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__PARTITION_SEGMENT_PAIRS = SYBASE_ASE_PARTITION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Partition Num In Segments</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION__PARTITION_NUM_IN_SEGMENTS = SYBASE_ASE_PARTITION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Sybase ASE Hash Partition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_HASH_PARTITION_FEATURE_COUNT = SYBASE_ASE_PARTITION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEListPartitionImpl <em>Sybase ASE List Partition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEListPartitionImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASEListPartition()
	 * @generated
	 */
	int SYBASE_ASE_LIST_PARTITION = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__EANNOTATIONS = SYBASE_ASE_PARTITION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__NAME = SYBASE_ASE_PARTITION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__DEPENDENCIES = SYBASE_ASE_PARTITION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__DESCRIPTION = SYBASE_ASE_PARTITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__LABEL = SYBASE_ASE_PARTITION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__COMMENTS = SYBASE_ASE_PARTITION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__EXTENSIONS = SYBASE_ASE_PARTITION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__PRIVILEGES = SYBASE_ASE_PARTITION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__COLUMN = SYBASE_ASE_PARTITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>List Partition Items</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION__LIST_PARTITION_ITEMS = SYBASE_ASE_PARTITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Sybase ASE List Partition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_LIST_PARTITION_FEATURE_COUNT = SYBASE_ASE_PARTITION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERoundrobinPartitionImpl <em>Sybase ASE Roundrobin Partition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERoundrobinPartitionImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASERoundrobinPartition()
	 * @generated
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__EANNOTATIONS = SYBASE_ASE_PARTITION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__NAME = SYBASE_ASE_PARTITION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__DEPENDENCIES = SYBASE_ASE_PARTITION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__DESCRIPTION = SYBASE_ASE_PARTITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__LABEL = SYBASE_ASE_PARTITION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__COMMENTS = SYBASE_ASE_PARTITION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__EXTENSIONS = SYBASE_ASE_PARTITION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__PRIVILEGES = SYBASE_ASE_PARTITION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Partition Segment Pairs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__PARTITION_SEGMENT_PAIRS = SYBASE_ASE_PARTITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Partition Num In Segments</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION__PARTITION_NUM_IN_SEGMENTS = SYBASE_ASE_PARTITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Sybase ASE Roundrobin Partition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROUNDROBIN_PARTITION_FEATURE_COUNT = SYBASE_ASE_PARTITION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionSegmentPairImpl <em>Segment Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionSegmentPairImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getPartitionSegmentPair()
	 * @generated
	 */
	int PARTITION_SEGMENT_PAIR = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_SEGMENT_PAIR__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_SEGMENT_PAIR__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_SEGMENT_PAIR__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_SEGMENT_PAIR__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_SEGMENT_PAIR__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_SEGMENT_PAIR__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTITION_SEGMENT_PAIR__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_SEGMENT_PAIR__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

    /**
	 * The feature id for the '<em><b>Partition Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTITION_SEGMENT_PAIR__PARTITION_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTITION_SEGMENT_PAIR__SEGMENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Segment Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTITION_SEGMENT_PAIR_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionNumInSegmentsImpl <em>Num In Segments</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionNumInSegmentsImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getPartitionNumInSegments()
	 * @generated
	 */
	int PARTITION_NUM_IN_SEGMENTS = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_NUM_IN_SEGMENTS__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_NUM_IN_SEGMENTS__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_NUM_IN_SEGMENTS__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_NUM_IN_SEGMENTS__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_NUM_IN_SEGMENTS__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_NUM_IN_SEGMENTS__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTITION_NUM_IN_SEGMENTS__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PARTITION_NUM_IN_SEGMENTS__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

    /**
	 * The feature id for the '<em><b>Partition Numb</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTITION_NUM_IN_SEGMENTS__PARTITION_NUMB = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Segment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTITION_NUM_IN_SEGMENTS__SEGMENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Num In Segments</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTITION_NUM_IN_SEGMENTS_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.ListRangePartitionItemImpl <em>List Range Partition Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.ListRangePartitionItemImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getListRangePartitionItem()
	 * @generated
	 */
	int LIST_RANGE_PARTITION_ITEM = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST_RANGE_PARTITION_ITEM__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST_RANGE_PARTITION_ITEM__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST_RANGE_PARTITION_ITEM__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST_RANGE_PARTITION_ITEM__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST_RANGE_PARTITION_ITEM__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST_RANGE_PARTITION_ITEM__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_RANGE_PARTITION_ITEM__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST_RANGE_PARTITION_ITEM__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

    /**
	 * The feature id for the '<em><b>Partition Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_RANGE_PARTITION_ITEM__PARTITION_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_RANGE_PARTITION_ITEM__VALUES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_RANGE_PARTITION_ITEM__SEGMENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>List Range Partition Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_RANGE_PARTITION_ITEM_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition <em>Sybase ASE Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Partition</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition
	 * @generated
	 */
	EClass getSybaseASEPartition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition <em>Sybase ASE Range Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Range Partition</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition
	 * @generated
	 */
	EClass getSybaseASERangePartition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Columns</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition#getColumns()
	 * @see #getSybaseASERangePartition()
	 * @generated
	 */
	EReference getSybaseASERangePartition_Columns();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition#getRangePartitionItems <em>Range Partition Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Range Partition Items</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition#getRangePartitionItems()
	 * @see #getSybaseASERangePartition()
	 * @generated
	 */
	EReference getSybaseASERangePartition_RangePartitionItems();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition <em>Sybase ASE Hash Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Hash Partition</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition
	 * @generated
	 */
	EClass getSybaseASEHashPartition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Columns</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getColumns()
	 * @see #getSybaseASEHashPartition()
	 * @generated
	 */
	EReference getSybaseASEHashPartition_Columns();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getPartitionSegmentPairs <em>Partition Segment Pairs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partition Segment Pairs</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getPartitionSegmentPairs()
	 * @see #getSybaseASEHashPartition()
	 * @generated
	 */
	EReference getSybaseASEHashPartition_PartitionSegmentPairs();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getPartitionNumInSegments <em>Partition Num In Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Partition Num In Segments</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition#getPartitionNumInSegments()
	 * @see #getSybaseASEHashPartition()
	 * @generated
	 */
	EReference getSybaseASEHashPartition_PartitionNumInSegments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition <em>Sybase ASE List Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE List Partition</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition
	 * @generated
	 */
	EClass getSybaseASEListPartition();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Column</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition#getColumn()
	 * @see #getSybaseASEListPartition()
	 * @generated
	 */
	EReference getSybaseASEListPartition_Column();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition#getListPartitionItems <em>List Partition Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>List Partition Items</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition#getListPartitionItems()
	 * @see #getSybaseASEListPartition()
	 * @generated
	 */
	EReference getSybaseASEListPartition_ListPartitionItems();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition <em>Sybase ASE Roundrobin Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Roundrobin Partition</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition
	 * @generated
	 */
	EClass getSybaseASERoundrobinPartition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition#getPartitionSegmentPairs <em>Partition Segment Pairs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partition Segment Pairs</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition#getPartitionSegmentPairs()
	 * @see #getSybaseASERoundrobinPartition()
	 * @generated
	 */
	EReference getSybaseASERoundrobinPartition_PartitionSegmentPairs();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition#getPartitionNumInSegments <em>Partition Num In Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Partition Num In Segments</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition#getPartitionNumInSegments()
	 * @see #getSybaseASERoundrobinPartition()
	 * @generated
	 */
	EReference getSybaseASERoundrobinPartition_PartitionNumInSegments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair <em>Segment Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment Pair</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair
	 * @generated
	 */
	EClass getPartitionSegmentPair();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair#getPartitionName <em>Partition Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair#getPartitionName()
	 * @see #getPartitionSegmentPair()
	 * @generated
	 */
	EAttribute getPartitionSegmentPair_PartitionName();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair#getSegment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Segment</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair#getSegment()
	 * @see #getPartitionSegmentPair()
	 * @generated
	 */
	EReference getPartitionSegmentPair_Segment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments <em>Num In Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Num In Segments</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments
	 * @generated
	 */
	EClass getPartitionNumInSegments();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments#getPartitionNumb <em>Partition Numb</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition Numb</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments#getPartitionNumb()
	 * @see #getPartitionNumInSegments()
	 * @generated
	 */
	EAttribute getPartitionNumInSegments_PartitionNumb();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments#getSegment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Segment</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionNumInSegments#getSegment()
	 * @see #getPartitionNumInSegments()
	 * @generated
	 */
	EReference getPartitionNumInSegments_Segment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem <em>List Range Partition Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Range Partition Item</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem
	 * @generated
	 */
	EClass getListRangePartitionItem();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getPartitionName <em>Partition Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getPartitionName()
	 * @see #getListRangePartitionItem()
	 * @generated
	 */
	EAttribute getListRangePartitionItem_PartitionName();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getValues()
	 * @see #getListRangePartitionItem()
	 * @generated
	 */
	EAttribute getListRangePartitionItem_Values();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getSegment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Segment</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem#getSegment()
	 * @see #getListRangePartitionItem()
	 * @generated
	 */
	EReference getListRangePartitionItem_Segment();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PartitionFactory getPartitionFactory();

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
	interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEPartitionImpl <em>Sybase ASE Partition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEPartitionImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASEPartition()
		 * @generated
		 */
		EClass SYBASE_ASE_PARTITION = eINSTANCE.getSybaseASEPartition();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERangePartitionImpl <em>Sybase ASE Range Partition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERangePartitionImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASERangePartition()
		 * @generated
		 */
		EClass SYBASE_ASE_RANGE_PARTITION = eINSTANCE.getSybaseASERangePartition();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_RANGE_PARTITION__COLUMNS = eINSTANCE.getSybaseASERangePartition_Columns();

		/**
		 * The meta object literal for the '<em><b>Range Partition Items</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_RANGE_PARTITION__RANGE_PARTITION_ITEMS = eINSTANCE.getSybaseASERangePartition_RangePartitionItems();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEHashPartitionImpl <em>Sybase ASE Hash Partition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEHashPartitionImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASEHashPartition()
		 * @generated
		 */
		EClass SYBASE_ASE_HASH_PARTITION = eINSTANCE.getSybaseASEHashPartition();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_HASH_PARTITION__COLUMNS = eINSTANCE.getSybaseASEHashPartition_Columns();

		/**
		 * The meta object literal for the '<em><b>Partition Segment Pairs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_HASH_PARTITION__PARTITION_SEGMENT_PAIRS = eINSTANCE.getSybaseASEHashPartition_PartitionSegmentPairs();

		/**
		 * The meta object literal for the '<em><b>Partition Num In Segments</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_HASH_PARTITION__PARTITION_NUM_IN_SEGMENTS = eINSTANCE.getSybaseASEHashPartition_PartitionNumInSegments();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEListPartitionImpl <em>Sybase ASE List Partition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASEListPartitionImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASEListPartition()
		 * @generated
		 */
		EClass SYBASE_ASE_LIST_PARTITION = eINSTANCE.getSybaseASEListPartition();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_LIST_PARTITION__COLUMN = eINSTANCE.getSybaseASEListPartition_Column();

		/**
		 * The meta object literal for the '<em><b>List Partition Items</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_LIST_PARTITION__LIST_PARTITION_ITEMS = eINSTANCE.getSybaseASEListPartition_ListPartitionItems();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERoundrobinPartitionImpl <em>Sybase ASE Roundrobin Partition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.SybaseASERoundrobinPartitionImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getSybaseASERoundrobinPartition()
		 * @generated
		 */
		EClass SYBASE_ASE_ROUNDROBIN_PARTITION = eINSTANCE.getSybaseASERoundrobinPartition();

		/**
		 * The meta object literal for the '<em><b>Partition Segment Pairs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_ROUNDROBIN_PARTITION__PARTITION_SEGMENT_PAIRS = eINSTANCE.getSybaseASERoundrobinPartition_PartitionSegmentPairs();

		/**
		 * The meta object literal for the '<em><b>Partition Num In Segments</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_ROUNDROBIN_PARTITION__PARTITION_NUM_IN_SEGMENTS = eINSTANCE.getSybaseASERoundrobinPartition_PartitionNumInSegments();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionSegmentPairImpl <em>Segment Pair</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionSegmentPairImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getPartitionSegmentPair()
		 * @generated
		 */
		EClass PARTITION_SEGMENT_PAIR = eINSTANCE.getPartitionSegmentPair();

		/**
		 * The meta object literal for the '<em><b>Partition Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTITION_SEGMENT_PAIR__PARTITION_NAME = eINSTANCE.getPartitionSegmentPair_PartitionName();

		/**
		 * The meta object literal for the '<em><b>Segment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTITION_SEGMENT_PAIR__SEGMENT = eINSTANCE.getPartitionSegmentPair_Segment();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionNumInSegmentsImpl <em>Num In Segments</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionNumInSegmentsImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getPartitionNumInSegments()
		 * @generated
		 */
		EClass PARTITION_NUM_IN_SEGMENTS = eINSTANCE.getPartitionNumInSegments();

		/**
		 * The meta object literal for the '<em><b>Partition Numb</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTITION_NUM_IN_SEGMENTS__PARTITION_NUMB = eINSTANCE.getPartitionNumInSegments_PartitionNumb();

		/**
		 * The meta object literal for the '<em><b>Segment</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTITION_NUM_IN_SEGMENTS__SEGMENT = eINSTANCE.getPartitionNumInSegments_Segment();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.ListRangePartitionItemImpl <em>List Range Partition Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.ListRangePartitionItemImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl#getListRangePartitionItem()
		 * @generated
		 */
		EClass LIST_RANGE_PARTITION_ITEM = eINSTANCE.getListRangePartitionItem();

		/**
		 * The meta object literal for the '<em><b>Partition Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST_RANGE_PARTITION_ITEM__PARTITION_NAME = eINSTANCE.getListRangePartitionItem_PartitionName();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST_RANGE_PARTITION_ITEM__VALUES = eINSTANCE.getListRangePartitionItem_Values();

		/**
		 * The meta object literal for the '<em><b>Segment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_RANGE_PARTITION_ITEM__SEGMENT = eINSTANCE.getListRangePartitionItem_Segment();

	}

} //PartitionPackage
