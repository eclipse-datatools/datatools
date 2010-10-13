/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasabasesqlmodelPackage.java,v 1.5 2009/04/24 07:03:26 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
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
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelFactory
 * @model kind="package"
 * @generated
 */
public interface SybaseasabasesqlmodelPackage extends EPackage
{
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sybaseasabasesqlmodel";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/connectivity/sqm/sybase/asa/sybaseasabasesqlmodel.ecore";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SybaseASABaseModel";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SybaseasabasesqlmodelPackage eINSTANCE = org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl.init();

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseEventImpl <em>Sybase ASA Base Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseEventImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseEvent()
	 * @generated
	 */
	int SYBASE_ASA_BASE_EVENT = 0;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__EANNOTATIONS = SQLSchemaPackage.EVENT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__NAME = SQLSchemaPackage.EVENT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__DEPENDENCIES = SQLSchemaPackage.EVENT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__DESCRIPTION = SQLSchemaPackage.EVENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__LABEL = SQLSchemaPackage.EVENT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__COMMENTS = SQLSchemaPackage.EVENT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__EXTENSIONS = SQLSchemaPackage.EVENT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__PRIVILEGES = SQLSchemaPackage.EVENT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>For</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__FOR = SQLSchemaPackage.EVENT__FOR;

    /**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__CONDITION = SQLSchemaPackage.EVENT__CONDITION;

    /**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__ACTION = SQLSchemaPackage.EVENT__ACTION;

    /**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__ENABLED = SQLSchemaPackage.EVENT__ENABLED;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__DATABASE = SQLSchemaPackage.EVENT__DATABASE;

    /**
	 * The feature id for the '<em><b>Event Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__EVENT_TYPE = SQLSchemaPackage.EVENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Event Creator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__EVENT_CREATOR = SQLSchemaPackage.EVENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__LOCATION = SQLSchemaPackage.EVENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Schedules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT__SCHEDULES = SQLSchemaPackage.EVENT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Condition Details</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_EVENT__CONDITION_DETAILS = SQLSchemaPackage.EVENT_FEATURE_COUNT + 4;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_EVENT_FEATURE_COUNT = SQLSchemaPackage.EVENT_FEATURE_COUNT + 5;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl <em>Sybase ASA Base Database</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseDatabase()
	 * @generated
	 */
	int SYBASE_ASA_BASE_DATABASE = 1;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__EANNOTATIONS = SQLSchemaPackage.DATABASE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__NAME = SQLSchemaPackage.DATABASE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__DEPENDENCIES = SQLSchemaPackage.DATABASE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__DESCRIPTION = SQLSchemaPackage.DATABASE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__LABEL = SQLSchemaPackage.DATABASE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__COMMENTS = SQLSchemaPackage.DATABASE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__EXTENSIONS = SQLSchemaPackage.DATABASE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__PRIVILEGES = SQLSchemaPackage.DATABASE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__VENDOR = SQLSchemaPackage.DATABASE__VENDOR;

    /**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__VERSION = SQLSchemaPackage.DATABASE__VERSION;

    /**
	 * The feature id for the '<em><b>Schemas</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__SCHEMAS = SQLSchemaPackage.DATABASE__SCHEMAS;

    /**
	 * The feature id for the '<em><b>Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__EVENTS = SQLSchemaPackage.DATABASE__EVENTS;

    /**
	 * The feature id for the '<em><b>Catalogs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__CATALOGS = SQLSchemaPackage.DATABASE__CATALOGS;

    /**
	 * The feature id for the '<em><b>Authorization Ids</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__AUTHORIZATION_IDS = SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS;

    /**
	 * The feature id for the '<em><b>Data Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__DATA_TYPES = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Web Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__WEB_SERVICES = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Db Spaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__DB_SPACES = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Database File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Log File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Mirror File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Case Sensitive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Collation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__COLLATION = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Blank Padding On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Check Sum On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>JConnect On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__JCONNECT_ON = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 10;

    /**
	 * The feature id for the '<em><b>Page Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__PAGE_SIZE = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 11;

    /**
	 * The feature id for the '<em><b>Encryption Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 12;

    /**
	 * The feature id for the '<em><b>Java Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__JAVA_SUPPORT = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 13;

    /**
	 * The feature id for the '<em><b>Password Case Sensitive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 14;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DATABASE_FEATURE_COUNT = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 15;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EncryptionInfoImpl <em>Encryption Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EncryptionInfoImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getEncryptionInfo()
	 * @generated
	 */
	int ENCRYPTION_INFO = 3;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserDefinedTypeImpl <em>Sybase ASA Base User Defined Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserDefinedTypeImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseUserDefinedType()
	 * @generated
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE = 4;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePredefinedDataTypeImpl <em>Sybase ASA Base Predefined Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePredefinedDataTypeImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABasePredefinedDataType()
	 * @generated
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE = 5;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTableImpl <em>Sybase ASA Base Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseTable()
	 * @generated
	 */
	int SYBASE_ASA_BASE_TABLE = 6;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl <em>Sybase ASA Base Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseColumn()
	 * @generated
	 */
	int SYBASE_ASA_BASE_COLUMN = 7;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUniqueConstraintImpl <em>Sybase ASA Base Unique Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUniqueConstraintImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseUniqueConstraint()
	 * @generated
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT = 8;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePrimaryKeyImpl <em>Sybase ASA Base Primary Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePrimaryKeyImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABasePrimaryKey()
	 * @generated
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY = 9;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseForeignKeyImpl <em>Sybase ASA Base Foreign Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseForeignKeyImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseForeignKey()
	 * @generated
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY = 10;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseIndexImpl <em>Sybase ASA Base Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseIndexImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseIndex()
	 * @generated
	 */
	int SYBASE_ASA_BASE_INDEX = 11;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDBSpaceImpl <em>Sybase ASA Base DB Space</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDBSpaceImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseDBSpace()
	 * @generated
	 */
	int SYBASE_ASA_BASE_DB_SPACE = 12;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseViewTableImpl <em>Sybase ASA Base View Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseViewTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseViewTable()
	 * @generated
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE = 13;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseFunctionImpl <em>Sybase ASA Base Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseFunctionImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseFunction()
	 * @generated
	 */
	int SYBASE_ASA_BASE_FUNCTION = 14;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProcedureImpl <em>Sybase ASA Base Procedure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProcedureImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseProcedure()
	 * @generated
	 */
	int SYBASE_ASA_BASE_PROCEDURE = 15;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTempTableImpl <em>Sybase ASA Base Temp Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTempTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseTempTable()
	 * @generated
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE = 16;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTriggerImpl <em>Sybase ASA Base Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTriggerImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseTrigger()
	 * @generated
	 */
	int SYBASE_ASA_BASE_TRIGGER = 17;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProxyTableImpl <em>Sybase ASA Base Proxy Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProxyTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseProxyTable()
	 * @generated
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE = 18;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnCheckConstraintImpl <em>Sybase ASA Base Column Check Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnCheckConstraintImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseColumnCheckConstraint()
	 * @generated
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT = 19;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASAWebServiceImpl <em>Sybase ASA Web Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASAWebServiceImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASAWebService()
	 * @generated
	 */
	int SYBASE_ASA_WEB_SERVICE = 2;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Service id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__SERVICE_ID = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Service type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__SERVICE_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Auth required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__AUTH_REQUIRED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Secure required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__SECURE_REQUIRED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Url path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__URL_PATH = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>User name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__USER_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__PARAMETER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__STATEMENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

    /**
	 * The number of structural features of the '<em>Sybase ASA Web Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_WEB_SERVICE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>Encrypted Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENCRYPTION_INFO__ENCRYPTED_TABLE = 0;

    /**
	 * The feature id for the '<em><b>Encryption Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENCRYPTION_INFO__ENCRYPTION_KEY = 1;

    /**
	 * The feature id for the '<em><b>Algorithm</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENCRYPTION_INFO__ALGORITHM = 2;

    /**
	 * The number of structural features of the '<em>Encryption Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENCRYPTION_INFO_FEATURE_COUNT = 3;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__EANNOTATIONS = SQLDataTypesPackage.DOMAIN__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__NAME = SQLDataTypesPackage.DOMAIN__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEPENDENCIES = SQLDataTypesPackage.DOMAIN__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__DESCRIPTION = SQLDataTypesPackage.DOMAIN__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__LABEL = SQLDataTypesPackage.DOMAIN__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__COMMENTS = SQLDataTypesPackage.DOMAIN__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__EXTENSIONS = SQLDataTypesPackage.DOMAIN__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__PRIVILEGES = SQLDataTypesPackage.DOMAIN__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__SCHEMA = SQLDataTypesPackage.DOMAIN__SCHEMA;

    /**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__ORDERING = SQLDataTypesPackage.DOMAIN__ORDERING;

    /**
	 * The feature id for the '<em><b>Predefined Representation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION = SQLDataTypesPackage.DOMAIN__PREDEFINED_REPRESENTATION;

    /**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__CONSTRAINT = SQLDataTypesPackage.DOMAIN__CONSTRAINT;

    /**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_VALUE = SQLDataTypesPackage.DOMAIN__DEFAULT_VALUE;

    /**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__NULLABLE = SQLDataTypesPackage.DOMAIN_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Default Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_TYPE = SQLDataTypesPackage.DOMAIN_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base User Defined Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER_DEFINED_TYPE_FEATURE_COUNT = SQLDataTypesPackage.DOMAIN_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__EANNOTATIONS = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__NAME = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DEPENDENCIES = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DESCRIPTION = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__LABEL = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__COMMENTS = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__EXTENSIONS = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__PRIVILEGES = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE = SQLDataTypesPackage.PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Predefined Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE_FEATURE_COUNT = SQLDataTypesPackage.PREDEFINED_DATA_TYPE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__EANNOTATIONS = SQLTablesPackage.PERSISTENT_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__NAME = SQLTablesPackage.PERSISTENT_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__DEPENDENCIES = SQLTablesPackage.PERSISTENT_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__DESCRIPTION = SQLTablesPackage.PERSISTENT_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__LABEL = SQLTablesPackage.PERSISTENT_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__COMMENTS = SQLTablesPackage.PERSISTENT_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__EXTENSIONS = SQLTablesPackage.PERSISTENT_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_TABLE__PRIVILEGES = SQLTablesPackage.PERSISTENT_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__COLUMNS = SQLTablesPackage.PERSISTENT_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__SUPERTABLE = SQLTablesPackage.PERSISTENT_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__SUBTABLES = SQLTablesPackage.PERSISTENT_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__SCHEMA = SQLTablesPackage.PERSISTENT_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__UDT = SQLTablesPackage.PERSISTENT_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__TRIGGERS = SQLTablesPackage.PERSISTENT_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__INDEX = SQLTablesPackage.PERSISTENT_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.PERSISTENT_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__INSERTABLE = SQLTablesPackage.PERSISTENT_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__UPDATABLE = SQLTablesPackage.PERSISTENT_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__CONSTRAINTS = SQLTablesPackage.PERSISTENT_TABLE__CONSTRAINTS;

				/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__REFERENCING_FOREIGN_KEYS = SQLTablesPackage.PERSISTENT_TABLE__REFERENCING_FOREIGN_KEYS;

    /**
	 * The feature id for the '<em><b>Db Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE__DB_SPACE = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TABLE_FEATURE_COUNT = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__EANNOTATIONS = SQLTablesPackage.COLUMN__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__NAME = SQLTablesPackage.COLUMN__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__DEPENDENCIES = SQLTablesPackage.COLUMN__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__DESCRIPTION = SQLTablesPackage.COLUMN__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__LABEL = SQLTablesPackage.COLUMN__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__COMMENTS = SQLTablesPackage.COLUMN__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__EXTENSIONS = SQLTablesPackage.COLUMN__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_COLUMN__PRIVILEGES = SQLTablesPackage.COLUMN__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__CONTAINED_TYPE = SQLTablesPackage.COLUMN__CONTAINED_TYPE;

				/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__REFERENCED_TYPE = SQLTablesPackage.COLUMN__REFERENCED_TYPE;

				/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__TABLE = SQLTablesPackage.COLUMN__TABLE;

				/**
	 * The feature id for the '<em><b>Identity Specifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__IDENTITY_SPECIFIER = SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER;

				/**
	 * The feature id for the '<em><b>Generate Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__GENERATE_EXPRESSION = SQLTablesPackage.COLUMN__GENERATE_EXPRESSION;

				/**
	 * The feature id for the '<em><b>Implementation Dependent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__IMPLEMENTATION_DEPENDENT = SQLTablesPackage.COLUMN__IMPLEMENTATION_DEPENDENT;

				/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__NULLABLE = SQLTablesPackage.COLUMN__NULLABLE;

				/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__DEFAULT_VALUE = SQLTablesPackage.COLUMN__DEFAULT_VALUE;

				/**
	 * The feature id for the '<em><b>Scope Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__SCOPE_CHECK = SQLTablesPackage.COLUMN__SCOPE_CHECK;

				/**
	 * The feature id for the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__SCOPE_CHECKED = SQLTablesPackage.COLUMN__SCOPE_CHECKED;

    /**
	 * The feature id for the '<em><b>Column Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT = SQLTablesPackage.COLUMN_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Type Of Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__TYPE_OF_DEFAULT = SQLTablesPackage.COLUMN_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__UNIQUE = SQLTablesPackage.COLUMN_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Is Computed Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN__IS_COMPUTED_COLUMN = SQLTablesPackage.COLUMN_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_FEATURE_COUNT = SQLTablesPackage.COLUMN_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__EANNOTATIONS = SQLConstraintsPackage.UNIQUE_CONSTRAINT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__NAME = SQLConstraintsPackage.UNIQUE_CONSTRAINT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__DEPENDENCIES = SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__DESCRIPTION = SQLConstraintsPackage.UNIQUE_CONSTRAINT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__LABEL = SQLConstraintsPackage.UNIQUE_CONSTRAINT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__COMMENTS = SQLConstraintsPackage.UNIQUE_CONSTRAINT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__EXTENSIONS = SQLConstraintsPackage.UNIQUE_CONSTRAINT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__PRIVILEGES = SQLConstraintsPackage.UNIQUE_CONSTRAINT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__DEFERRABLE = SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEFERRABLE;

    /**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__INITIALLY_DEFERRED = SQLConstraintsPackage.UNIQUE_CONSTRAINT__INITIALLY_DEFERRED;

    /**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__ENFORCED = SQLConstraintsPackage.UNIQUE_CONSTRAINT__ENFORCED;

    /**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__BASE_TABLE = SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE;

    /**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__MEMBERS = SQLConstraintsPackage.UNIQUE_CONSTRAINT__MEMBERS;

    /**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__FOREIGN_KEY = SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY;

    /**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__CLUSTERED = SQLConstraintsPackage.UNIQUE_CONSTRAINT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>System Gen Index</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX = SQLConstraintsPackage.UNIQUE_CONSTRAINT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Unique Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_UNIQUE_CONSTRAINT_FEATURE_COUNT = SQLConstraintsPackage.UNIQUE_CONSTRAINT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__EANNOTATIONS = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__NAME = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__DEPENDENCIES = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__DESCRIPTION = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__LABEL = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__COMMENTS = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__EXTENSIONS = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__PRIVILEGES = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__DEFERRABLE = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__DEFERRABLE;

    /**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__INITIALLY_DEFERRED = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__INITIALLY_DEFERRED;

    /**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__ENFORCED = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__ENFORCED;

    /**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__BASE_TABLE = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__BASE_TABLE;

    /**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__MEMBERS = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__MEMBERS;

    /**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__FOREIGN_KEY = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__FOREIGN_KEY;

    /**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY__CLUSTERED = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__CLUSTERED;

    /**
	 * The feature id for the '<em><b>System Gen Index</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_PRIMARY_KEY__SYSTEM_GEN_INDEX = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Primary Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PRIMARY_KEY_FEATURE_COUNT = SYBASE_ASA_BASE_UNIQUE_CONSTRAINT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__EANNOTATIONS = SQLConstraintsPackage.FOREIGN_KEY__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__NAME = SQLConstraintsPackage.FOREIGN_KEY__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__DEPENDENCIES = SQLConstraintsPackage.FOREIGN_KEY__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__DESCRIPTION = SQLConstraintsPackage.FOREIGN_KEY__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__LABEL = SQLConstraintsPackage.FOREIGN_KEY__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__COMMENTS = SQLConstraintsPackage.FOREIGN_KEY__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__EXTENSIONS = SQLConstraintsPackage.FOREIGN_KEY__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__PRIVILEGES = SQLConstraintsPackage.FOREIGN_KEY__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__DEFERRABLE = SQLConstraintsPackage.FOREIGN_KEY__DEFERRABLE;

    /**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__INITIALLY_DEFERRED = SQLConstraintsPackage.FOREIGN_KEY__INITIALLY_DEFERRED;

    /**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__ENFORCED = SQLConstraintsPackage.FOREIGN_KEY__ENFORCED;

    /**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__BASE_TABLE = SQLConstraintsPackage.FOREIGN_KEY__BASE_TABLE;

    /**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__MEMBERS = SQLConstraintsPackage.FOREIGN_KEY__MEMBERS;

    /**
	 * The feature id for the '<em><b>Match</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__MATCH = SQLConstraintsPackage.FOREIGN_KEY__MATCH;

    /**
	 * The feature id for the '<em><b>On Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__ON_UPDATE = SQLConstraintsPackage.FOREIGN_KEY__ON_UPDATE;

    /**
	 * The feature id for the '<em><b>On Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__ON_DELETE = SQLConstraintsPackage.FOREIGN_KEY__ON_DELETE;

    /**
	 * The feature id for the '<em><b>Unique Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__UNIQUE_CONSTRAINT = SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_CONSTRAINT;

    /**
	 * The feature id for the '<em><b>Referenced Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__REFERENCED_MEMBERS = SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_MEMBERS;

    /**
	 * The feature id for the '<em><b>Unique Index</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__UNIQUE_INDEX = SQLConstraintsPackage.FOREIGN_KEY__UNIQUE_INDEX;

    /**
	 * The feature id for the '<em><b>Referenced Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__REFERENCED_TABLE = SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE;

    /**
	 * The feature id for the '<em><b>Role Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__ROLE_NAME = SQLConstraintsPackage.FOREIGN_KEY_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY__CLUSTERED = SQLConstraintsPackage.FOREIGN_KEY_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Foreign Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FOREIGN_KEY_FEATURE_COUNT = SQLConstraintsPackage.FOREIGN_KEY_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__EANNOTATIONS = SQLConstraintsPackage.INDEX__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__NAME = SQLConstraintsPackage.INDEX__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__DEPENDENCIES = SQLConstraintsPackage.INDEX__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__DESCRIPTION = SQLConstraintsPackage.INDEX__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__LABEL = SQLConstraintsPackage.INDEX__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__COMMENTS = SQLConstraintsPackage.INDEX__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__EXTENSIONS = SQLConstraintsPackage.INDEX__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__PRIVILEGES = SQLConstraintsPackage.INDEX__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__SCHEMA = SQLConstraintsPackage.INDEX__SCHEMA;

    /**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__CLUSTERED = SQLConstraintsPackage.INDEX__CLUSTERED;

    /**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__FILL_FACTOR = SQLConstraintsPackage.INDEX__FILL_FACTOR;

    /**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__UNIQUE = SQLConstraintsPackage.INDEX__UNIQUE;

    /**
	 * The feature id for the '<em><b>System Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__SYSTEM_GENERATED = SQLConstraintsPackage.INDEX__SYSTEM_GENERATED;

    /**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__MEMBERS = SQLConstraintsPackage.INDEX__MEMBERS;

    /**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__TABLE = SQLConstraintsPackage.INDEX__TABLE;

    /**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__FOREIGN_KEY = SQLConstraintsPackage.INDEX__FOREIGN_KEY;

    /**
	 * The feature id for the '<em><b>Included Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__INCLUDED_MEMBERS = SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS;

    /**
	 * The feature id for the '<em><b>Db Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX__DB_SPACE = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_INDEX_FEATURE_COUNT = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__FILE_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Database</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base DB Space</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_DB_SPACE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__EANNOTATIONS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__NAME = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__DEPENDENCIES = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__DESCRIPTION = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__LABEL = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__COMMENTS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__EXTENSIONS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_VIEW_TABLE__PRIVILEGES = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__COLUMNS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__SUPERTABLE = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__SUBTABLES = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__SCHEMA = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__UDT = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__TRIGGERS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__INDEX = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__SELF_REF_COLUMN_GENERATION = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__INSERTABLE = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__UPDATABLE = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__QUERY_EXPRESSION = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__QUERY_EXPRESSION;

				/**
	 * The feature id for the '<em><b>Check Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__CHECK_TYPE = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__CHECK_TYPE;

    /**
	 * The feature id for the '<em><b>With Check Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__WITH_CHECK_OPTION = SybasesqlmodelPackage.SYBASE_VIEW_TABLE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Statement</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT = SybasesqlmodelPackage.SYBASE_VIEW_TABLE_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base View Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_VIEW_TABLE_FEATURE_COUNT = SybasesqlmodelPackage.SYBASE_VIEW_TABLE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__EANNOTATIONS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__NAME = SQLRoutinesPackage.USER_DEFINED_FUNCTION__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__DEPENDENCIES = SQLRoutinesPackage.USER_DEFINED_FUNCTION__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__DESCRIPTION = SQLRoutinesPackage.USER_DEFINED_FUNCTION__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__LABEL = SQLRoutinesPackage.USER_DEFINED_FUNCTION__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__COMMENTS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__EXTENSIONS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_FUNCTION__PRIVILEGES = SQLRoutinesPackage.USER_DEFINED_FUNCTION__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__SPECIFIC_NAME = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SPECIFIC_NAME;

				/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__LANGUAGE = SQLRoutinesPackage.USER_DEFINED_FUNCTION__LANGUAGE;

				/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__PARAMETER_STYLE = SQLRoutinesPackage.USER_DEFINED_FUNCTION__PARAMETER_STYLE;

				/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__DETERMINISTIC = SQLRoutinesPackage.USER_DEFINED_FUNCTION__DETERMINISTIC;

				/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__SQL_DATA_ACCESS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SQL_DATA_ACCESS;

				/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__CREATION_TS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__CREATION_TS;

				/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__LAST_ALTERED_TS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__LAST_ALTERED_TS;

				/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__AUTHORIZATION_ID = SQLRoutinesPackage.USER_DEFINED_FUNCTION__AUTHORIZATION_ID;

				/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__SECURITY = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SECURITY;

				/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__EXTERNAL_NAME = SQLRoutinesPackage.USER_DEFINED_FUNCTION__EXTERNAL_NAME;

				/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__PARAMETERS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__PARAMETERS;

				/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__SOURCE = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SOURCE;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__SCHEMA = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SCHEMA;

				/**
	 * The feature id for the '<em><b>Null Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__NULL_CALL = SQLRoutinesPackage.USER_DEFINED_FUNCTION__NULL_CALL;

				/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__STATIC = SQLRoutinesPackage.USER_DEFINED_FUNCTION__STATIC;

				/**
	 * The feature id for the '<em><b>Transform Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__TRANSFORM_GROUP = SQLRoutinesPackage.USER_DEFINED_FUNCTION__TRANSFORM_GROUP;

				/**
	 * The feature id for the '<em><b>Type Preserving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__TYPE_PRESERVING = SQLRoutinesPackage.USER_DEFINED_FUNCTION__TYPE_PRESERVING;

				/**
	 * The feature id for the '<em><b>Mutator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__MUTATOR = SQLRoutinesPackage.USER_DEFINED_FUNCTION__MUTATOR;

				/**
	 * The feature id for the '<em><b>Return Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__RETURN_TABLE = SQLRoutinesPackage.USER_DEFINED_FUNCTION__RETURN_TABLE;

				/**
	 * The feature id for the '<em><b>Return Scalar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__RETURN_SCALAR = SQLRoutinesPackage.USER_DEFINED_FUNCTION__RETURN_SCALAR;

				/**
	 * The feature id for the '<em><b>Return Cast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__RETURN_CAST = SQLRoutinesPackage.USER_DEFINED_FUNCTION__RETURN_CAST;

    /**
	 * The feature id for the '<em><b>On Exception Resume</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION__ON_EXCEPTION_RESUME = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_FUNCTION_FEATURE_COUNT = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__EANNOTATIONS = SQLRoutinesPackage.PROCEDURE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__NAME = SQLRoutinesPackage.PROCEDURE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__DEPENDENCIES = SQLRoutinesPackage.PROCEDURE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__DESCRIPTION = SQLRoutinesPackage.PROCEDURE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__LABEL = SQLRoutinesPackage.PROCEDURE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__COMMENTS = SQLRoutinesPackage.PROCEDURE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__EXTENSIONS = SQLRoutinesPackage.PROCEDURE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_PROCEDURE__PRIVILEGES = SQLRoutinesPackage.PROCEDURE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__SPECIFIC_NAME = SQLRoutinesPackage.PROCEDURE__SPECIFIC_NAME;

				/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__LANGUAGE = SQLRoutinesPackage.PROCEDURE__LANGUAGE;

				/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__PARAMETER_STYLE = SQLRoutinesPackage.PROCEDURE__PARAMETER_STYLE;

				/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__DETERMINISTIC = SQLRoutinesPackage.PROCEDURE__DETERMINISTIC;

				/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__SQL_DATA_ACCESS = SQLRoutinesPackage.PROCEDURE__SQL_DATA_ACCESS;

				/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__CREATION_TS = SQLRoutinesPackage.PROCEDURE__CREATION_TS;

				/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__LAST_ALTERED_TS = SQLRoutinesPackage.PROCEDURE__LAST_ALTERED_TS;

				/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__AUTHORIZATION_ID = SQLRoutinesPackage.PROCEDURE__AUTHORIZATION_ID;

				/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__SECURITY = SQLRoutinesPackage.PROCEDURE__SECURITY;

				/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__EXTERNAL_NAME = SQLRoutinesPackage.PROCEDURE__EXTERNAL_NAME;

				/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__PARAMETERS = SQLRoutinesPackage.PROCEDURE__PARAMETERS;

				/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__SOURCE = SQLRoutinesPackage.PROCEDURE__SOURCE;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__SCHEMA = SQLRoutinesPackage.PROCEDURE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Max Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__MAX_RESULT_SETS = SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS;

				/**
	 * The feature id for the '<em><b>Old Save Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__OLD_SAVE_POINT = SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT;

				/**
	 * The feature id for the '<em><b>Result Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__RESULT_SET = SQLRoutinesPackage.PROCEDURE__RESULT_SET;

    /**
	 * The feature id for the '<em><b>On Exception Resume</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE__ON_EXCEPTION_RESUME = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Procedure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROCEDURE_FEATURE_COUNT = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__EANNOTATIONS = SQLTablesPackage.TEMPORARY_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__NAME = SQLTablesPackage.TEMPORARY_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__DEPENDENCIES = SQLTablesPackage.TEMPORARY_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__DESCRIPTION = SQLTablesPackage.TEMPORARY_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__LABEL = SQLTablesPackage.TEMPORARY_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__COMMENTS = SQLTablesPackage.TEMPORARY_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__EXTENSIONS = SQLTablesPackage.TEMPORARY_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__PRIVILEGES = SQLTablesPackage.TEMPORARY_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__COLUMNS = SQLTablesPackage.TEMPORARY_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__SUPERTABLE = SQLTablesPackage.TEMPORARY_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__SUBTABLES = SQLTablesPackage.TEMPORARY_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__SCHEMA = SQLTablesPackage.TEMPORARY_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__UDT = SQLTablesPackage.TEMPORARY_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__TRIGGERS = SQLTablesPackage.TEMPORARY_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__INDEX = SQLTablesPackage.TEMPORARY_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.TEMPORARY_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__INSERTABLE = SQLTablesPackage.TEMPORARY_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__UPDATABLE = SQLTablesPackage.TEMPORARY_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__CONSTRAINTS = SQLTablesPackage.TEMPORARY_TABLE__CONSTRAINTS;

				/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__REFERENCING_FOREIGN_KEYS = SQLTablesPackage.TEMPORARY_TABLE__REFERENCING_FOREIGN_KEYS;

				/**
	 * The feature id for the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__LOCAL = SQLTablesPackage.TEMPORARY_TABLE__LOCAL;

				/**
	 * The feature id for the '<em><b>Delete On Commit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__DELETE_ON_COMMIT = SQLTablesPackage.TEMPORARY_TABLE__DELETE_ON_COMMIT;

    /**
	 * The feature id for the '<em><b>Transaction Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE__TRANSACTION_OPTION = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Temp Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TEMP_TABLE_FEATURE_COUNT = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__EANNOTATIONS = SQLTablesPackage.TRIGGER__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__NAME = SQLTablesPackage.TRIGGER__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__DEPENDENCIES = SQLTablesPackage.TRIGGER__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__DESCRIPTION = SQLTablesPackage.TRIGGER__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__LABEL = SQLTablesPackage.TRIGGER__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__COMMENTS = SQLTablesPackage.TRIGGER__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__EXTENSIONS = SQLTablesPackage.TRIGGER__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__PRIVILEGES = SQLTablesPackage.TRIGGER__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__SCHEMA = SQLTablesPackage.TRIGGER__SCHEMA;

    /**
	 * The feature id for the '<em><b>Subject Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__SUBJECT_TABLE = SQLTablesPackage.TRIGGER__SUBJECT_TABLE;

    /**
	 * The feature id for the '<em><b>Action Statement</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__ACTION_STATEMENT = SQLTablesPackage.TRIGGER__ACTION_STATEMENT;

    /**
	 * The feature id for the '<em><b>Trigger Column</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__TRIGGER_COLUMN = SQLTablesPackage.TRIGGER__TRIGGER_COLUMN;

    /**
	 * The feature id for the '<em><b>Action Granularity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__ACTION_GRANULARITY = SQLTablesPackage.TRIGGER__ACTION_GRANULARITY;

    /**
	 * The feature id for the '<em><b>When</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__WHEN = SQLTablesPackage.TRIGGER__WHEN;

    /**
	 * The feature id for the '<em><b>Time Stamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__TIME_STAMP = SQLTablesPackage.TRIGGER__TIME_STAMP;

    /**
	 * The feature id for the '<em><b>Action Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__ACTION_TIME = SQLTablesPackage.TRIGGER__ACTION_TIME;

    /**
	 * The feature id for the '<em><b>Update Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__UPDATE_TYPE = SQLTablesPackage.TRIGGER__UPDATE_TYPE;

    /**
	 * The feature id for the '<em><b>Insert Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__INSERT_TYPE = SQLTablesPackage.TRIGGER__INSERT_TYPE;

    /**
	 * The feature id for the '<em><b>Delete Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__DELETE_TYPE = SQLTablesPackage.TRIGGER__DELETE_TYPE;

    /**
	 * The feature id for the '<em><b>Old Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__OLD_ROW = SQLTablesPackage.TRIGGER__OLD_ROW;

    /**
	 * The feature id for the '<em><b>New Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__NEW_ROW = SQLTablesPackage.TRIGGER__NEW_ROW;

    /**
	 * The feature id for the '<em><b>Old Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__OLD_TABLE = SQLTablesPackage.TRIGGER__OLD_TABLE;

    /**
	 * The feature id for the '<em><b>New Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__NEW_TABLE = SQLTablesPackage.TRIGGER__NEW_TABLE;

    /**
	 * The feature id for the '<em><b>Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__ORDER = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Sybase ASA Base Action Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__SYBASE_ASA_BASE_ACTION_TIME = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Remote Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Update Column Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_TRIGGER_FEATURE_COUNT = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__EANNOTATIONS = SYBASE_ASA_BASE_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__NAME = SYBASE_ASA_BASE_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__DEPENDENCIES = SYBASE_ASA_BASE_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__DESCRIPTION = SYBASE_ASA_BASE_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__LABEL = SYBASE_ASA_BASE_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__COMMENTS = SYBASE_ASA_BASE_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__EXTENSIONS = SYBASE_ASA_BASE_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_PROXY_TABLE__PRIVILEGES = SYBASE_ASA_BASE_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__COLUMNS = SYBASE_ASA_BASE_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__SUPERTABLE = SYBASE_ASA_BASE_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__SUBTABLES = SYBASE_ASA_BASE_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__SCHEMA = SYBASE_ASA_BASE_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__UDT = SYBASE_ASA_BASE_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__TRIGGERS = SYBASE_ASA_BASE_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__INDEX = SYBASE_ASA_BASE_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__SELF_REF_COLUMN_GENERATION = SYBASE_ASA_BASE_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__INSERTABLE = SYBASE_ASA_BASE_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__UPDATABLE = SYBASE_ASA_BASE_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__CONSTRAINTS = SYBASE_ASA_BASE_TABLE__CONSTRAINTS;

				/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__REFERENCING_FOREIGN_KEYS = SYBASE_ASA_BASE_TABLE__REFERENCING_FOREIGN_KEYS;

    /**
	 * The feature id for the '<em><b>Db Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__DB_SPACE = SYBASE_ASA_BASE_TABLE__DB_SPACE;

    /**
	 * The feature id for the '<em><b>Remote Object Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION = SYBASE_ASA_BASE_TABLE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Existing</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_PROXY_TABLE__EXISTING = SYBASE_ASA_BASE_TABLE_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Proxy Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PROXY_TABLE_FEATURE_COUNT = SYBASE_ASA_BASE_TABLE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__EANNOTATIONS = SQLConstraintsPackage.CHECK_CONSTRAINT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__NAME = SQLConstraintsPackage.CHECK_CONSTRAINT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__DEPENDENCIES = SQLConstraintsPackage.CHECK_CONSTRAINT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__DESCRIPTION = SQLConstraintsPackage.CHECK_CONSTRAINT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__LABEL = SQLConstraintsPackage.CHECK_CONSTRAINT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__COMMENTS = SQLConstraintsPackage.CHECK_CONSTRAINT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__EXTENSIONS = SQLConstraintsPackage.CHECK_CONSTRAINT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__PRIVILEGES = SQLConstraintsPackage.CHECK_CONSTRAINT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__DEFERRABLE = SQLConstraintsPackage.CHECK_CONSTRAINT__DEFERRABLE;

    /**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__INITIALLY_DEFERRED = SQLConstraintsPackage.CHECK_CONSTRAINT__INITIALLY_DEFERRED;

    /**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__ENFORCED = SQLConstraintsPackage.CHECK_CONSTRAINT__ENFORCED;

    /**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__BASE_TABLE = SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE;

    /**
	 * The feature id for the '<em><b>Search Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__SEARCH_CONDITION = SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION;

    /**
	 * The feature id for the '<em><b>Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__COLUMN = SQLConstraintsPackage.CHECK_CONSTRAINT_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Column Check Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT_FEATURE_COUNT = SQLConstraintsPackage.CHECK_CONSTRAINT_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl <em>Schedule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSchedule()
	 * @generated
	 */
	int SCHEDULE = 20;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Recurring</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__RECURRING = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__START_TIME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Stop Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__STOP_TIME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__START_DATE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Days Of Week</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__DAYS_OF_WEEK = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Days Of Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__DAYS_OF_MONTH = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Interval Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__INTERVAL_UNIT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Interval Mount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__INTERVAL_MOUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Event</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__EVENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

    /**
	 * The number of structural features of the '<em>Schedule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseRemoteProcedureImpl <em>Sybase ASA Base Remote Procedure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseRemoteProcedureImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseRemoteProcedure()
	 * @generated
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE = 21;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__EANNOTATIONS = SYBASE_ASA_BASE_PROCEDURE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__NAME = SYBASE_ASA_BASE_PROCEDURE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__DEPENDENCIES = SYBASE_ASA_BASE_PROCEDURE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__DESCRIPTION = SYBASE_ASA_BASE_PROCEDURE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__LABEL = SYBASE_ASA_BASE_PROCEDURE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__COMMENTS = SYBASE_ASA_BASE_PROCEDURE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__EXTENSIONS = SYBASE_ASA_BASE_PROCEDURE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_REMOTE_PROCEDURE__PRIVILEGES = SYBASE_ASA_BASE_PROCEDURE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__SPECIFIC_NAME = SYBASE_ASA_BASE_PROCEDURE__SPECIFIC_NAME;

				/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__LANGUAGE = SYBASE_ASA_BASE_PROCEDURE__LANGUAGE;

				/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__PARAMETER_STYLE = SYBASE_ASA_BASE_PROCEDURE__PARAMETER_STYLE;

				/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__DETERMINISTIC = SYBASE_ASA_BASE_PROCEDURE__DETERMINISTIC;

				/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__SQL_DATA_ACCESS = SYBASE_ASA_BASE_PROCEDURE__SQL_DATA_ACCESS;

				/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__CREATION_TS = SYBASE_ASA_BASE_PROCEDURE__CREATION_TS;

				/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__LAST_ALTERED_TS = SYBASE_ASA_BASE_PROCEDURE__LAST_ALTERED_TS;

				/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__AUTHORIZATION_ID = SYBASE_ASA_BASE_PROCEDURE__AUTHORIZATION_ID;

				/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__SECURITY = SYBASE_ASA_BASE_PROCEDURE__SECURITY;

				/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__EXTERNAL_NAME = SYBASE_ASA_BASE_PROCEDURE__EXTERNAL_NAME;

				/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__PARAMETERS = SYBASE_ASA_BASE_PROCEDURE__PARAMETERS;

				/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__SOURCE = SYBASE_ASA_BASE_PROCEDURE__SOURCE;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__SCHEMA = SYBASE_ASA_BASE_PROCEDURE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Max Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__MAX_RESULT_SETS = SYBASE_ASA_BASE_PROCEDURE__MAX_RESULT_SETS;

				/**
	 * The feature id for the '<em><b>Old Save Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__OLD_SAVE_POINT = SYBASE_ASA_BASE_PROCEDURE__OLD_SAVE_POINT;

				/**
	 * The feature id for the '<em><b>Result Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__RESULT_SET = SYBASE_ASA_BASE_PROCEDURE__RESULT_SET;

    /**
	 * The feature id for the '<em><b>On Exception Resume</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__ON_EXCEPTION_RESUME = SYBASE_ASA_BASE_PROCEDURE__ON_EXCEPTION_RESUME;

    /**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE__LOCATION = SYBASE_ASA_BASE_PROCEDURE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Remote Procedure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_REMOTE_PROCEDURE_FEATURE_COUNT = SYBASE_ASA_BASE_PROCEDURE_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseParameterImpl <em>Sybase ASA Base Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseParameterImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseParameter()
	 * @generated
	 */
	int SYBASE_ASA_BASE_PARAMETER = 22;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__EANNOTATIONS = SQLRoutinesPackage.PARAMETER__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__NAME = SQLRoutinesPackage.PARAMETER__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__DEPENDENCIES = SQLRoutinesPackage.PARAMETER__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__DESCRIPTION = SQLRoutinesPackage.PARAMETER__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__LABEL = SQLRoutinesPackage.PARAMETER__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__COMMENTS = SQLRoutinesPackage.PARAMETER__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__EXTENSIONS = SQLRoutinesPackage.PARAMETER__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__PRIVILEGES = SQLRoutinesPackage.PARAMETER__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__CONTAINED_TYPE = SQLRoutinesPackage.PARAMETER__CONTAINED_TYPE;

    /**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__REFERENCED_TYPE = SQLRoutinesPackage.PARAMETER__REFERENCED_TYPE;

    /**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__MODE = SQLRoutinesPackage.PARAMETER__MODE;

    /**
	 * The feature id for the '<em><b>Locator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__LOCATOR = SQLRoutinesPackage.PARAMETER__LOCATOR;

    /**
	 * The feature id for the '<em><b>Routine</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__ROUTINE = SQLRoutinesPackage.PARAMETER__ROUTINE;

    /**
	 * The feature id for the '<em><b>String Type Option</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__STRING_TYPE_OPTION = SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION;

    /**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_PARAMETER__NULLABLE = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_PARAMETER__DEFAULT_VALUE = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>JDBC Parameter Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_PARAMETER__JDBC_PARAMETER_TYPE = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Parm Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER__PARM_TYPE = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_PARAMETER_FEATURE_COUNT = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 4;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseGroupImpl <em>Sybase ASA Base Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseGroupImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseGroup()
	 * @generated
	 */
	int SYBASE_ASA_BASE_GROUP = 23;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__EANNOTATIONS = SQLAccessControlPackage.USER__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__NAME = SQLAccessControlPackage.USER__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__DEPENDENCIES = SQLAccessControlPackage.USER__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__DESCRIPTION = SQLAccessControlPackage.USER__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__LABEL = SQLAccessControlPackage.USER__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__COMMENTS = SQLAccessControlPackage.USER__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__EXTENSIONS = SQLAccessControlPackage.USER__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__PRIVILEGES = SQLAccessControlPackage.USER__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__OWNED_SCHEMA = SQLAccessControlPackage.USER__OWNED_SCHEMA;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__DATABASE = SQLAccessControlPackage.USER__DATABASE;

				/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__RECEIVED_ROLE_AUTHORIZATION = SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__GRANTED_ROLE_AUTHORIZATION = SQLAccessControlPackage.USER__GRANTED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__GRANTED_PRIVILEGE = SQLAccessControlPackage.USER__GRANTED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__RECEIVED_PRIVILEGE = SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>Group</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__GROUP = SQLAccessControlPackage.USER__GROUP;

				/**
	 * The feature id for the '<em><b>User</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP__USER = SQLAccessControlPackage.USER_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Sql Container</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_GROUP__SQL_CONTAINER = SQLAccessControlPackage.USER_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_GROUP_FEATURE_COUNT = SQLAccessControlPackage.USER_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseSchemaImpl <em>Sybase ASA Base Schema</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseSchemaImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseSchema()
	 * @generated
	 */
    int SYBASE_ASA_BASE_SCHEMA = 25;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserImpl <em>Sybase ASA Base User</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseUser()
	 * @generated
	 */
    int SYBASE_ASA_BASE_USER = 24;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__EANNOTATIONS = SQLAccessControlPackage.USER__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__NAME = SQLAccessControlPackage.USER__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__DEPENDENCIES = SQLAccessControlPackage.USER__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__DESCRIPTION = SQLAccessControlPackage.USER__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__LABEL = SQLAccessControlPackage.USER__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__COMMENTS = SQLAccessControlPackage.USER__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER__EXTENSIONS = SQLAccessControlPackage.USER__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER__PRIVILEGES = SQLAccessControlPackage.USER__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__OWNED_SCHEMA = SQLAccessControlPackage.USER__OWNED_SCHEMA;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER__DATABASE = SQLAccessControlPackage.USER__DATABASE;

				/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__RECEIVED_ROLE_AUTHORIZATION = SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__GRANTED_ROLE_AUTHORIZATION = SQLAccessControlPackage.USER__GRANTED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__GRANTED_PRIVILEGE = SQLAccessControlPackage.USER__GRANTED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__RECEIVED_PRIVILEGE = SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>Group</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_USER__GROUP = SQLAccessControlPackage.USER__GROUP;

				/**
	 * The feature id for the '<em><b>Sql Container</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER__SQL_CONTAINER = SQLAccessControlPackage.USER_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Base User</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_USER_FEATURE_COUNT = SQLAccessControlPackage.USER_FEATURE_COUNT + 1;

				/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__EANNOTATIONS = SQLSchemaPackage.SCHEMA__EANNOTATIONS;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__NAME = SQLSchemaPackage.SCHEMA__NAME;

				/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__DEPENDENCIES = SQLSchemaPackage.SCHEMA__DEPENDENCIES;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__DESCRIPTION = SQLSchemaPackage.SCHEMA__DESCRIPTION;

				/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__LABEL = SQLSchemaPackage.SCHEMA__LABEL;

				/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__COMMENTS = SQLSchemaPackage.SCHEMA__COMMENTS;

				/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_SCHEMA__EXTENSIONS = SQLSchemaPackage.SCHEMA__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_BASE_SCHEMA__PRIVILEGES = SQLSchemaPackage.SCHEMA__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__TRIGGERS = SQLSchemaPackage.SCHEMA__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Indices</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__INDICES = SQLSchemaPackage.SCHEMA__INDICES;

				/**
	 * The feature id for the '<em><b>Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__TABLES = SQLSchemaPackage.SCHEMA__TABLES;

				/**
	 * The feature id for the '<em><b>Sequences</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__SEQUENCES = SQLSchemaPackage.SCHEMA__SEQUENCES;

				/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__DATABASE = SQLSchemaPackage.SCHEMA__DATABASE;

				/**
	 * The feature id for the '<em><b>Catalog</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__CATALOG = SQLSchemaPackage.SCHEMA__CATALOG;

				/**
	 * The feature id for the '<em><b>Assertions</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__ASSERTIONS = SQLSchemaPackage.SCHEMA__ASSERTIONS;

				/**
	 * The feature id for the '<em><b>User Defined Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__USER_DEFINED_TYPES = SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES;

				/**
	 * The feature id for the '<em><b>Char Sets</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__CHAR_SETS = SQLSchemaPackage.SCHEMA__CHAR_SETS;

				/**
	 * The feature id for the '<em><b>Routines</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__ROUTINES = SQLSchemaPackage.SCHEMA__ROUTINES;

				/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA__OWNER = SQLSchemaPackage.SCHEMA__OWNER;

				/**
	 * The number of structural features of the '<em>Sybase ASA Base Schema</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASA_BASE_SCHEMA_FEATURE_COUNT = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl <em>Sybase ASA Default Wrapper</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASADefaultWrapper()
	 * @generated
	 */
	int SYBASE_ASA_DEFAULT_WRAPPER = 26;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DEFAULT_WRAPPER__VALUE = 0;

    /**
	 * The feature id for the '<em><b>Is Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DEFAULT_WRAPPER__IS_LITERAL = 1;

    /**
	 * The feature id for the '<em><b>Partition Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DEFAULT_WRAPPER__PARTITION_SIZE = 2;

    /**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DEFAULT_WRAPPER__TYPE = 3;

    /**
	 * The number of structural features of the '<em>Sybase ASA Default Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DEFAULT_WRAPPER_FEATURE_COUNT = 4;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EventConditionImpl <em>Event Condition</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EventConditionImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getEventCondition()
	 * @generated
	 */
    int EVENT_CONDITION = 27;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONDITION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION__OPERATOR = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION__VALUE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Event</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION__EVENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Event Condition</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVENT_CONDITION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption <em>Transaction Option</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getTransactionOption()
	 * @generated
	 */
	int TRANSACTION_OPTION = 28;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault <em>Type Of Default</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getTypeOfDefault()
	 * @generated
	 */
	int TYPE_OF_DEFAULT = 29;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime <em>Sybase ASA Base Action Time</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseActionTime()
	 * @generated
	 */
	int SYBASE_ASA_BASE_ACTION_TIME = 30;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType <em>Event Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getEventType()
	 * @generated
	 */
	int EVENT_TYPE = 31;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType <em>Java Support Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getJavaSupportType()
	 * @generated
	 */
	int JAVA_SUPPORT_TYPE = 32;


    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType <em>Event Location Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getEventLocationType()
	 * @generated
	 */
	int EVENT_LOCATION_TYPE = 33;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType <em>Interval Unit Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getIntervalUnitType()
	 * @generated
	 */
	int INTERVAL_UNIT_TYPE = 34;


    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType <em>System Defined Default Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSystemDefinedDefaultType()
	 * @generated
	 */
	int SYSTEM_DEFINED_DEFAULT_TYPE = 35;


    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType <em>Allow Null Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getAllowNullType()
	 * @generated
	 */
	int ALLOW_NULL_TYPE = 36;


    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType <em>Parameter Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getParameterType()
	 * @generated
	 */
	int PARAMETER_TYPE = 37;


    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent <em>Sybase ASA Base Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Event</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent
	 * @generated
	 */
	EClass getSybaseASABaseEvent();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getEventCreator <em>Event Creator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Event Creator</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getEventCreator()
	 * @see #getSybaseASABaseEvent()
	 * @generated
	 */
	EReference getSybaseASABaseEvent_EventCreator();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getLocation()
	 * @see #getSybaseASABaseEvent()
	 * @generated
	 */
	EAttribute getSybaseASABaseEvent_Location();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getSchedules <em>Schedules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Schedules</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getSchedules()
	 * @see #getSybaseASABaseEvent()
	 * @generated
	 */
	EReference getSybaseASABaseEvent_Schedules();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getConditionDetails <em>Condition Details</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Condition Details</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getConditionDetails()
	 * @see #getSybaseASABaseEvent()
	 * @generated
	 */
    EReference getSybaseASABaseEvent_ConditionDetails();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getEventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getEventType()
	 * @see #getSybaseASABaseEvent()
	 * @generated
	 */
	EAttribute getSybaseASABaseEvent_EventType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase <em>Sybase ASA Base Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Database</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase
	 * @generated
	 */
	EClass getSybaseASABaseDatabase();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDataTypes <em>Data Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Types</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDataTypes()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EReference getSybaseASABaseDatabase_DataTypes();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getWebServices <em>Web Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Web Services</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getWebServices()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EReference getSybaseASABaseDatabase_WebServices();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDbSpaces <em>Db Spaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Db Spaces</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDbSpaces()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EReference getSybaseASABaseDatabase_DbSpaces();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDatabaseFileName <em>Database File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Database File Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getDatabaseFileName()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_DatabaseFileName();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getLogFileName <em>Log File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Log File Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getLogFileName()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_LogFileName();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getMirrorFileName <em>Mirror File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mirror File Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getMirrorFileName()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_MirrorFileName();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isCaseSensitive <em>Case Sensitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Case Sensitive</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isCaseSensitive()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_CaseSensitive();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getCollation <em>Collation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collation</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getCollation()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_Collation();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isBlankPaddingOn <em>Blank Padding On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blank Padding On</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isBlankPaddingOn()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_BlankPaddingOn();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isCheckSumOn <em>Check Sum On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Check Sum On</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isCheckSumOn()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_CheckSumOn();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isJConnectOn <em>JConnect On</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>JConnect On</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#isJConnectOn()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_JConnectOn();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getPageSize <em>Page Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Size</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getPageSize()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_PageSize();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getEncryptionInfo <em>Encryption Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Encryption Info</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getEncryptionInfo()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EReference getSybaseASABaseDatabase_EncryptionInfo();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getJavaSupport <em>Java Support</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Support</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getJavaSupport()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_JavaSupport();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getPasswordCaseSensitive <em>Password Case Sensitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Password Case Sensitive</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getPasswordCaseSensitive()
	 * @see #getSybaseASABaseDatabase()
	 * @generated
	 */
	EAttribute getSybaseASABaseDatabase_PasswordCaseSensitive();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo <em>Encryption Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Encryption Info</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo
	 * @generated
	 */
	EClass getEncryptionInfo();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo#isEncryptedTable <em>Encrypted Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encrypted Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo#isEncryptedTable()
	 * @see #getEncryptionInfo()
	 * @generated
	 */
	EAttribute getEncryptionInfo_EncryptedTable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo#getEncryptionKey <em>Encryption Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encryption Key</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo#getEncryptionKey()
	 * @see #getEncryptionInfo()
	 * @generated
	 */
	EAttribute getEncryptionInfo_EncryptionKey();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo#getAlgorithm <em>Algorithm</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Algorithm</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo#getAlgorithm()
	 * @see #getEncryptionInfo()
	 * @generated
	 */
	EAttribute getEncryptionInfo_Algorithm();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType <em>Sybase ASA Base User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base User Defined Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType
	 * @generated
	 */
	EClass getSybaseASABaseUserDefinedType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType#getNullable <em>Nullable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nullable</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType#getNullable()
	 * @see #getSybaseASABaseUserDefinedType()
	 * @generated
	 */
	EAttribute getSybaseASABaseUserDefinedType_Nullable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType#getDefaultType <em>Default Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType#getDefaultType()
	 * @see #getSybaseASABaseUserDefinedType()
	 * @generated
	 */
	EAttribute getSybaseASABaseUserDefinedType_DefaultType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType <em>Sybase ASA Base Predefined Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Predefined Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType
	 * @generated
	 */
	EClass getSybaseASABasePredefinedDataType();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType#getDatabase()
	 * @see #getSybaseASABasePredefinedDataType()
	 * @generated
	 */
	EReference getSybaseASABasePredefinedDataType_Database();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable <em>Sybase ASA Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable
	 * @generated
	 */
	EClass getSybaseASABaseTable();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable#getDbSpace <em>Db Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Db Space</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable#getDbSpace()
	 * @see #getSybaseASABaseTable()
	 * @generated
	 */
	EReference getSybaseASABaseTable_DbSpace();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn <em>Sybase ASA Base Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Column</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn
	 * @generated
	 */
	EClass getSybaseASABaseColumn();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#getColumnConstraint <em>Column Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Column Constraint</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#getColumnConstraint()
	 * @see #getSybaseASABaseColumn()
	 * @generated
	 */
	EReference getSybaseASABaseColumn_ColumnConstraint();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#getTypeOfDefault <em>Type Of Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Of Default</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#getTypeOfDefault()
	 * @see #getSybaseASABaseColumn()
	 * @generated
	 */
	EAttribute getSybaseASABaseColumn_TypeOfDefault();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#isUnique()
	 * @see #getSybaseASABaseColumn()
	 * @generated
	 */
	EAttribute getSybaseASABaseColumn_Unique();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#isIsComputedColumn <em>Is Computed Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Computed Column</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#isIsComputedColumn()
	 * @see #getSybaseASABaseColumn()
	 * @generated
	 */
	EAttribute getSybaseASABaseColumn_IsComputedColumn();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint <em>Sybase ASA Base Unique Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Unique Constraint</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint
	 * @generated
	 */
	EClass getSybaseASABaseUniqueConstraint();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint#isClustered <em>Clustered</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clustered</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint#isClustered()
	 * @see #getSybaseASABaseUniqueConstraint()
	 * @generated
	 */
	EAttribute getSybaseASABaseUniqueConstraint_Clustered();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint#getSystemGenIndex <em>System Gen Index</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>System Gen Index</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint#getSystemGenIndex()
	 * @see #getSybaseASABaseUniqueConstraint()
	 * @generated
	 */
    EReference getSybaseASABaseUniqueConstraint_SystemGenIndex();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePrimaryKey <em>Sybase ASA Base Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Primary Key</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePrimaryKey
	 * @generated
	 */
	EClass getSybaseASABasePrimaryKey();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey <em>Sybase ASA Base Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Foreign Key</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey
	 * @generated
	 */
	EClass getSybaseASABaseForeignKey();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey#getRoleName <em>Role Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey#getRoleName()
	 * @see #getSybaseASABaseForeignKey()
	 * @generated
	 */
	EAttribute getSybaseASABaseForeignKey_RoleName();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey#isClustered <em>Clustered</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clustered</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey#isClustered()
	 * @see #getSybaseASABaseForeignKey()
	 * @generated
	 */
	EAttribute getSybaseASABaseForeignKey_Clustered();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex <em>Sybase ASA Base Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Index</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex
	 * @generated
	 */
	EClass getSybaseASABaseIndex();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex#getDbSpace <em>Db Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Db Space</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex#getDbSpace()
	 * @see #getSybaseASABaseIndex()
	 * @generated
	 */
	EReference getSybaseASABaseIndex_DbSpace();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace <em>Sybase ASA Base DB Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base DB Space</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace
	 * @generated
	 */
	EClass getSybaseASABaseDBSpace();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace#getFileName()
	 * @see #getSybaseASABaseDBSpace()
	 * @generated
	 */
	EAttribute getSybaseASABaseDBSpace_FileName();

    /**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace#getDatabase()
	 * @see #getSybaseASABaseDBSpace()
	 * @generated
	 */
	EReference getSybaseASABaseDBSpace_Database();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable <em>Sybase ASA Base View Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base View Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable
	 * @generated
	 */
	EClass getSybaseASABaseViewTable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable#isWithCheckOption <em>With Check Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>With Check Option</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable#isWithCheckOption()
	 * @see #getSybaseASABaseViewTable()
	 * @generated
	 */
	EAttribute getSybaseASABaseViewTable_WithCheckOption();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable#getStatement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Statement</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable#getStatement()
	 * @see #getSybaseASABaseViewTable()
	 * @generated
	 */
	EReference getSybaseASABaseViewTable_Statement();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseFunction <em>Sybase ASA Base Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Function</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseFunction
	 * @generated
	 */
	EClass getSybaseASABaseFunction();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseFunction#isOnExceptionResume <em>On Exception Resume</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Exception Resume</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseFunction#isOnExceptionResume()
	 * @see #getSybaseASABaseFunction()
	 * @generated
	 */
	EAttribute getSybaseASABaseFunction_OnExceptionResume();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure <em>Sybase ASA Base Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure
	 * @generated
	 */
	EClass getSybaseASABaseProcedure();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure#isOnExceptionResume <em>On Exception Resume</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Exception Resume</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure#isOnExceptionResume()
	 * @see #getSybaseASABaseProcedure()
	 * @generated
	 */
	EAttribute getSybaseASABaseProcedure_OnExceptionResume();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable <em>Sybase ASA Base Temp Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Temp Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable
	 * @generated
	 */
	EClass getSybaseASABaseTempTable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable#getTransactionOption <em>Transaction Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transaction Option</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable#getTransactionOption()
	 * @see #getSybaseASABaseTempTable()
	 * @generated
	 */
	EAttribute getSybaseASABaseTempTable_TransactionOption();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger <em>Sybase ASA Base Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Trigger</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger
	 * @generated
	 */
	EClass getSybaseASABaseTrigger();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getOrder <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getOrder()
	 * @see #getSybaseASABaseTrigger()
	 * @generated
	 */
	EAttribute getSybaseASABaseTrigger_Order();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getSybaseASABaseActionTime <em>Sybase ASA Base Action Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sybase ASA Base Action Time</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getSybaseASABaseActionTime()
	 * @see #getSybaseASABaseTrigger()
	 * @generated
	 */
	EAttribute getSybaseASABaseTrigger_SybaseASABaseActionTime();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getRemoteName <em>Remote Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getRemoteName()
	 * @see #getSybaseASABaseTrigger()
	 * @generated
	 */
	EAttribute getSybaseASABaseTrigger_RemoteName();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#isUpdateColumnType <em>Update Column Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Update Column Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#isUpdateColumnType()
	 * @see #getSybaseASABaseTrigger()
	 * @generated
	 */
    EAttribute getSybaseASABaseTrigger_UpdateColumnType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable <em>Sybase ASA Base Proxy Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Proxy Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable
	 * @generated
	 */
	EClass getSybaseASABaseProxyTable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable#getRemoteObjectLocation <em>Remote Object Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remote Object Location</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable#getRemoteObjectLocation()
	 * @see #getSybaseASABaseProxyTable()
	 * @generated
	 */
	EAttribute getSybaseASABaseProxyTable_RemoteObjectLocation();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable#isExisting <em>Existing</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Existing</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable#isExisting()
	 * @see #getSybaseASABaseProxyTable()
	 * @generated
	 */
    EAttribute getSybaseASABaseProxyTable_Existing();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint <em>Sybase ASA Base Column Check Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Column Check Constraint</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint
	 * @generated
	 */
	EClass getSybaseASABaseColumnCheckConstraint();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Column</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint#getColumn()
	 * @see #getSybaseASABaseColumnCheckConstraint()
	 * @generated
	 */
	EReference getSybaseASABaseColumnCheckConstraint_Column();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService <em>Sybase ASA Web Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Web Service</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService
	 * @generated
	 */
	EClass getSybaseASAWebService();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getService_id <em>Service id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service id</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getService_id()
	 * @see #getSybaseASAWebService()
	 * @generated
	 */
	EAttribute getSybaseASAWebService_Service_id();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getService_type <em>Service type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getService_type()
	 * @see #getSybaseASAWebService()
	 * @generated
	 */
	EAttribute getSybaseASAWebService_Service_type();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getAuth_required <em>Auth required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auth required</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getAuth_required()
	 * @see #getSybaseASAWebService()
	 * @generated
	 */
	EAttribute getSybaseASAWebService_Auth_required();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getSecure_required <em>Secure required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Secure required</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getSecure_required()
	 * @see #getSybaseASAWebService()
	 * @generated
	 */
	EAttribute getSybaseASAWebService_Secure_required();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getUrl_path <em>Url path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url path</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getUrl_path()
	 * @see #getSybaseASAWebService()
	 * @generated
	 */
	EAttribute getSybaseASAWebService_Url_path();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getUser_name <em>User name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getUser_name()
	 * @see #getSybaseASAWebService()
	 * @generated
	 */
	EAttribute getSybaseASAWebService_User_name();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getParameter()
	 * @see #getSybaseASAWebService()
	 * @generated
	 */
	EAttribute getSybaseASAWebService_Parameter();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getStatement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statement</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getStatement()
	 * @see #getSybaseASAWebService()
	 * @generated
	 */
	EAttribute getSybaseASAWebService_Statement();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getDatabase()
	 * @see #getSybaseASAWebService()
	 * @generated
	 */
	EReference getSybaseASAWebService_Database();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule <em>Schedule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schedule</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule
	 * @generated
	 */
	EClass getSchedule();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#isRecurring <em>Recurring</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recurring</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#isRecurring()
	 * @see #getSchedule()
	 * @generated
	 */
	EAttribute getSchedule_Recurring();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStartTime <em>Start Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Time</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStartTime()
	 * @see #getSchedule()
	 * @generated
	 */
	EAttribute getSchedule_StartTime();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStopTime <em>Stop Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stop Time</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStopTime()
	 * @see #getSchedule()
	 * @generated
	 */
	EAttribute getSchedule_StopTime();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStartDate <em>Start Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Date</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStartDate()
	 * @see #getSchedule()
	 * @generated
	 */
	EAttribute getSchedule_StartDate();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getDaysOfWeek <em>Days Of Week</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Days Of Week</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getDaysOfWeek()
	 * @see #getSchedule()
	 * @generated
	 */
	EAttribute getSchedule_DaysOfWeek();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getDaysOfMonth <em>Days Of Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Days Of Month</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getDaysOfMonth()
	 * @see #getSchedule()
	 * @generated
	 */
	EAttribute getSchedule_DaysOfMonth();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getIntervalUnit <em>Interval Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interval Unit</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getIntervalUnit()
	 * @see #getSchedule()
	 * @generated
	 */
	EAttribute getSchedule_IntervalUnit();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getIntervalMount <em>Interval Mount</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interval Mount</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getIntervalMount()
	 * @see #getSchedule()
	 * @generated
	 */
	EAttribute getSchedule_IntervalMount();

    /**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Event</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getEvent()
	 * @see #getSchedule()
	 * @generated
	 */
	EReference getSchedule_Event();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseRemoteProcedure <em>Sybase ASA Base Remote Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Remote Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseRemoteProcedure
	 * @generated
	 */
	EClass getSybaseASABaseRemoteProcedure();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseRemoteProcedure#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseRemoteProcedure#getLocation()
	 * @see #getSybaseASABaseRemoteProcedure()
	 * @generated
	 */
	EAttribute getSybaseASABaseRemoteProcedure_Location();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter <em>Sybase ASA Base Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Parameter</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter
	 * @generated
	 */
	EClass getSybaseASABaseParameter();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter#getParmType <em>Parm Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parm Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter#getParmType()
	 * @see #getSybaseASABaseParameter()
	 * @generated
	 */
	EAttribute getSybaseASABaseParameter_ParmType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseGroup <em>Sybase ASA Base Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Group</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseGroup
	 * @generated
	 */
	EClass getSybaseASABaseGroup();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema <em>Sybase ASA Base Schema</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base Schema</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema
	 * @generated
	 */
    EClass getSybaseASABaseSchema();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUser <em>Sybase ASA Base User</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Base User</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUser
	 * @generated
	 */
    EClass getSybaseASABaseUser();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper <em>Sybase ASA Default Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Default Wrapper</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper
	 * @generated
	 */
	EClass getSybaseASADefaultWrapper();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getValue()
	 * @see #getSybaseASADefaultWrapper()
	 * @generated
	 */
	EAttribute getSybaseASADefaultWrapper_Value();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#isIsLiteral <em>Is Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Literal</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#isIsLiteral()
	 * @see #getSybaseASADefaultWrapper()
	 * @generated
	 */
	EAttribute getSybaseASADefaultWrapper_IsLiteral();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getPartitionSize <em>Partition Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition Size</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getPartitionSize()
	 * @see #getSybaseASADefaultWrapper()
	 * @generated
	 */
	EAttribute getSybaseASADefaultWrapper_PartitionSize();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getType()
	 * @see #getSybaseASADefaultWrapper()
	 * @generated
	 */
	EAttribute getSybaseASADefaultWrapper_Type();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition <em>Event Condition</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Condition</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition
	 * @generated
	 */
    EClass getEventCondition();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getOperator()
	 * @see #getEventCondition()
	 * @generated
	 */
    EAttribute getEventCondition_Operator();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getValue()
	 * @see #getEventCondition()
	 * @generated
	 */
    EAttribute getEventCondition_Value();

    /**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Event</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getEvent()
	 * @see #getEventCondition()
	 * @generated
	 */
    EReference getEventCondition_Event();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption <em>Transaction Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Transaction Option</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption
	 * @generated
	 */
	EEnum getTransactionOption();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault <em>Type Of Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Type Of Default</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault
	 * @generated
	 */
	EEnum getTypeOfDefault();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime <em>Sybase ASA Base Action Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sybase ASA Base Action Time</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime
	 * @generated
	 */
	EEnum getSybaseASABaseActionTime();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Event Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType
	 * @generated
	 */
	EEnum getEventType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType <em>Java Support Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Java Support Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType
	 * @generated
	 */
	EEnum getJavaSupportType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType <em>Event Location Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Event Location Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType
	 * @generated
	 */
	EEnum getEventLocationType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType <em>Interval Unit Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Interval Unit Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType
	 * @generated
	 */
	EEnum getIntervalUnitType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType <em>System Defined Default Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>System Defined Default Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType
	 * @generated
	 */
	EEnum getSystemDefinedDefaultType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType <em>Allow Null Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Allow Null Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType
	 * @generated
	 */
	EEnum getAllowNullType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType <em>Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Parameter Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType
	 * @generated
	 */
	EEnum getParameterType();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SybaseasabasesqlmodelFactory getSybaseasabasesqlmodelFactory();

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
	interface Literals 
	{
        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseEventImpl <em>Sybase ASA Base Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseEventImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseEvent()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_EVENT = eINSTANCE.getSybaseASABaseEvent();

        /**
		 * The meta object literal for the '<em><b>Event Creator</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_EVENT__EVENT_CREATOR = eINSTANCE.getSybaseASABaseEvent_EventCreator();

        /**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_EVENT__LOCATION = eINSTANCE.getSybaseASABaseEvent_Location();

        /**
		 * The meta object literal for the '<em><b>Schedules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_EVENT__SCHEDULES = eINSTANCE.getSybaseASABaseEvent_Schedules();

        /**
		 * The meta object literal for the '<em><b>Condition Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference SYBASE_ASA_BASE_EVENT__CONDITION_DETAILS = eINSTANCE.getSybaseASABaseEvent_ConditionDetails();

        /**
		 * The meta object literal for the '<em><b>Event Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_EVENT__EVENT_TYPE = eINSTANCE.getSybaseASABaseEvent_EventType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl <em>Sybase ASA Base Database</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseDatabase()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_DATABASE = eINSTANCE.getSybaseASABaseDatabase();

        /**
		 * The meta object literal for the '<em><b>Data Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_DATABASE__DATA_TYPES = eINSTANCE.getSybaseASABaseDatabase_DataTypes();

        /**
		 * The meta object literal for the '<em><b>Web Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_DATABASE__WEB_SERVICES = eINSTANCE.getSybaseASABaseDatabase_WebServices();

        /**
		 * The meta object literal for the '<em><b>Db Spaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_DATABASE__DB_SPACES = eINSTANCE.getSybaseASABaseDatabase_DbSpaces();

        /**
		 * The meta object literal for the '<em><b>Database File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME = eINSTANCE.getSybaseASABaseDatabase_DatabaseFileName();

        /**
		 * The meta object literal for the '<em><b>Log File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME = eINSTANCE.getSybaseASABaseDatabase_LogFileName();

        /**
		 * The meta object literal for the '<em><b>Mirror File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME = eINSTANCE.getSybaseASABaseDatabase_MirrorFileName();

        /**
		 * The meta object literal for the '<em><b>Case Sensitive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE = eINSTANCE.getSybaseASABaseDatabase_CaseSensitive();

        /**
		 * The meta object literal for the '<em><b>Collation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__COLLATION = eINSTANCE.getSybaseASABaseDatabase_Collation();

        /**
		 * The meta object literal for the '<em><b>Blank Padding On</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON = eINSTANCE.getSybaseASABaseDatabase_BlankPaddingOn();

        /**
		 * The meta object literal for the '<em><b>Check Sum On</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON = eINSTANCE.getSybaseASABaseDatabase_CheckSumOn();

        /**
		 * The meta object literal for the '<em><b>JConnect On</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__JCONNECT_ON = eINSTANCE.getSybaseASABaseDatabase_JConnectOn();

        /**
		 * The meta object literal for the '<em><b>Page Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__PAGE_SIZE = eINSTANCE.getSybaseASABaseDatabase_PageSize();

        /**
		 * The meta object literal for the '<em><b>Encryption Info</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO = eINSTANCE.getSybaseASABaseDatabase_EncryptionInfo();

        /**
		 * The meta object literal for the '<em><b>Java Support</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__JAVA_SUPPORT = eINSTANCE.getSybaseASABaseDatabase_JavaSupport();

        /**
		 * The meta object literal for the '<em><b>Password Case Sensitive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE = eINSTANCE.getSybaseASABaseDatabase_PasswordCaseSensitive();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EncryptionInfoImpl <em>Encryption Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EncryptionInfoImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getEncryptionInfo()
		 * @generated
		 */
		EClass ENCRYPTION_INFO = eINSTANCE.getEncryptionInfo();

        /**
		 * The meta object literal for the '<em><b>Encrypted Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENCRYPTION_INFO__ENCRYPTED_TABLE = eINSTANCE.getEncryptionInfo_EncryptedTable();

        /**
		 * The meta object literal for the '<em><b>Encryption Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENCRYPTION_INFO__ENCRYPTION_KEY = eINSTANCE.getEncryptionInfo_EncryptionKey();

        /**
		 * The meta object literal for the '<em><b>Algorithm</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENCRYPTION_INFO__ALGORITHM = eINSTANCE.getEncryptionInfo_Algorithm();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserDefinedTypeImpl <em>Sybase ASA Base User Defined Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserDefinedTypeImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseUserDefinedType()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_USER_DEFINED_TYPE = eINSTANCE.getSybaseASABaseUserDefinedType();

        /**
		 * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_USER_DEFINED_TYPE__NULLABLE = eINSTANCE.getSybaseASABaseUserDefinedType_Nullable();

        /**
		 * The meta object literal for the '<em><b>Default Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_TYPE = eINSTANCE.getSybaseASABaseUserDefinedType_DefaultType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePredefinedDataTypeImpl <em>Sybase ASA Base Predefined Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePredefinedDataTypeImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABasePredefinedDataType()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE = eINSTANCE.getSybaseASABasePredefinedDataType();

        /**
		 * The meta object literal for the '<em><b>Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE = eINSTANCE.getSybaseASABasePredefinedDataType_Database();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTableImpl <em>Sybase ASA Base Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseTable()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_TABLE = eINSTANCE.getSybaseASABaseTable();

        /**
		 * The meta object literal for the '<em><b>Db Space</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_TABLE__DB_SPACE = eINSTANCE.getSybaseASABaseTable_DbSpace();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl <em>Sybase ASA Base Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseColumn()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_COLUMN = eINSTANCE.getSybaseASABaseColumn();

        /**
		 * The meta object literal for the '<em><b>Column Constraint</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT = eINSTANCE.getSybaseASABaseColumn_ColumnConstraint();

        /**
		 * The meta object literal for the '<em><b>Type Of Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_COLUMN__TYPE_OF_DEFAULT = eINSTANCE.getSybaseASABaseColumn_TypeOfDefault();

        /**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_COLUMN__UNIQUE = eINSTANCE.getSybaseASABaseColumn_Unique();

        /**
		 * The meta object literal for the '<em><b>Is Computed Column</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_COLUMN__IS_COMPUTED_COLUMN = eINSTANCE.getSybaseASABaseColumn_IsComputedColumn();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUniqueConstraintImpl <em>Sybase ASA Base Unique Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUniqueConstraintImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseUniqueConstraint()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_UNIQUE_CONSTRAINT = eINSTANCE.getSybaseASABaseUniqueConstraint();

        /**
		 * The meta object literal for the '<em><b>Clustered</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__CLUSTERED = eINSTANCE.getSybaseASABaseUniqueConstraint_Clustered();

        /**
		 * The meta object literal for the '<em><b>System Gen Index</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX = eINSTANCE.getSybaseASABaseUniqueConstraint_SystemGenIndex();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePrimaryKeyImpl <em>Sybase ASA Base Primary Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABasePrimaryKeyImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABasePrimaryKey()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_PRIMARY_KEY = eINSTANCE.getSybaseASABasePrimaryKey();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseForeignKeyImpl <em>Sybase ASA Base Foreign Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseForeignKeyImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseForeignKey()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_FOREIGN_KEY = eINSTANCE.getSybaseASABaseForeignKey();

        /**
		 * The meta object literal for the '<em><b>Role Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_FOREIGN_KEY__ROLE_NAME = eINSTANCE.getSybaseASABaseForeignKey_RoleName();

        /**
		 * The meta object literal for the '<em><b>Clustered</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_FOREIGN_KEY__CLUSTERED = eINSTANCE.getSybaseASABaseForeignKey_Clustered();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseIndexImpl <em>Sybase ASA Base Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseIndexImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseIndex()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_INDEX = eINSTANCE.getSybaseASABaseIndex();

        /**
		 * The meta object literal for the '<em><b>Db Space</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_INDEX__DB_SPACE = eINSTANCE.getSybaseASABaseIndex_DbSpace();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDBSpaceImpl <em>Sybase ASA Base DB Space</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDBSpaceImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseDBSpace()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_DB_SPACE = eINSTANCE.getSybaseASABaseDBSpace();

        /**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_DB_SPACE__FILE_NAME = eINSTANCE.getSybaseASABaseDBSpace_FileName();

        /**
		 * The meta object literal for the '<em><b>Database</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_DB_SPACE__DATABASE = eINSTANCE.getSybaseASABaseDBSpace_Database();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseViewTableImpl <em>Sybase ASA Base View Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseViewTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseViewTable()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_VIEW_TABLE = eINSTANCE.getSybaseASABaseViewTable();

        /**
		 * The meta object literal for the '<em><b>With Check Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_VIEW_TABLE__WITH_CHECK_OPTION = eINSTANCE.getSybaseASABaseViewTable_WithCheckOption();

        /**
		 * The meta object literal for the '<em><b>Statement</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT = eINSTANCE.getSybaseASABaseViewTable_Statement();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseFunctionImpl <em>Sybase ASA Base Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseFunctionImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseFunction()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_FUNCTION = eINSTANCE.getSybaseASABaseFunction();

        /**
		 * The meta object literal for the '<em><b>On Exception Resume</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_FUNCTION__ON_EXCEPTION_RESUME = eINSTANCE.getSybaseASABaseFunction_OnExceptionResume();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProcedureImpl <em>Sybase ASA Base Procedure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProcedureImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseProcedure()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_PROCEDURE = eINSTANCE.getSybaseASABaseProcedure();

        /**
		 * The meta object literal for the '<em><b>On Exception Resume</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_PROCEDURE__ON_EXCEPTION_RESUME = eINSTANCE.getSybaseASABaseProcedure_OnExceptionResume();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTempTableImpl <em>Sybase ASA Base Temp Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTempTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseTempTable()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_TEMP_TABLE = eINSTANCE.getSybaseASABaseTempTable();

        /**
		 * The meta object literal for the '<em><b>Transaction Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_TEMP_TABLE__TRANSACTION_OPTION = eINSTANCE.getSybaseASABaseTempTable_TransactionOption();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTriggerImpl <em>Sybase ASA Base Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseTriggerImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseTrigger()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_TRIGGER = eINSTANCE.getSybaseASABaseTrigger();

        /**
		 * The meta object literal for the '<em><b>Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_TRIGGER__ORDER = eINSTANCE.getSybaseASABaseTrigger_Order();

        /**
		 * The meta object literal for the '<em><b>Sybase ASA Base Action Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_TRIGGER__SYBASE_ASA_BASE_ACTION_TIME = eINSTANCE.getSybaseASABaseTrigger_SybaseASABaseActionTime();

        /**
		 * The meta object literal for the '<em><b>Remote Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME = eINSTANCE.getSybaseASABaseTrigger_RemoteName();

        /**
		 * The meta object literal for the '<em><b>Update Column Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE = eINSTANCE.getSybaseASABaseTrigger_UpdateColumnType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProxyTableImpl <em>Sybase ASA Base Proxy Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProxyTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseProxyTable()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_PROXY_TABLE = eINSTANCE.getSybaseASABaseProxyTable();

        /**
		 * The meta object literal for the '<em><b>Remote Object Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION = eINSTANCE.getSybaseASABaseProxyTable_RemoteObjectLocation();

        /**
		 * The meta object literal for the '<em><b>Existing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SYBASE_ASA_BASE_PROXY_TABLE__EXISTING = eINSTANCE.getSybaseASABaseProxyTable_Existing();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnCheckConstraintImpl <em>Sybase ASA Base Column Check Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnCheckConstraintImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseColumnCheckConstraint()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT = eINSTANCE.getSybaseASABaseColumnCheckConstraint();

        /**
		 * The meta object literal for the '<em><b>Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__COLUMN = eINSTANCE.getSybaseASABaseColumnCheckConstraint_Column();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASAWebServiceImpl <em>Sybase ASA Web Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASAWebServiceImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASAWebService()
		 * @generated
		 */
		EClass SYBASE_ASA_WEB_SERVICE = eINSTANCE.getSybaseASAWebService();

        /**
		 * The meta object literal for the '<em><b>Service id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_WEB_SERVICE__SERVICE_ID = eINSTANCE.getSybaseASAWebService_Service_id();

        /**
		 * The meta object literal for the '<em><b>Service type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_WEB_SERVICE__SERVICE_TYPE = eINSTANCE.getSybaseASAWebService_Service_type();

        /**
		 * The meta object literal for the '<em><b>Auth required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_WEB_SERVICE__AUTH_REQUIRED = eINSTANCE.getSybaseASAWebService_Auth_required();

        /**
		 * The meta object literal for the '<em><b>Secure required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_WEB_SERVICE__SECURE_REQUIRED = eINSTANCE.getSybaseASAWebService_Secure_required();

        /**
		 * The meta object literal for the '<em><b>Url path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_WEB_SERVICE__URL_PATH = eINSTANCE.getSybaseASAWebService_Url_path();

        /**
		 * The meta object literal for the '<em><b>User name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_WEB_SERVICE__USER_NAME = eINSTANCE.getSybaseASAWebService_User_name();

        /**
		 * The meta object literal for the '<em><b>Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_WEB_SERVICE__PARAMETER = eINSTANCE.getSybaseASAWebService_Parameter();

        /**
		 * The meta object literal for the '<em><b>Statement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_WEB_SERVICE__STATEMENT = eINSTANCE.getSybaseASAWebService_Statement();

        /**
		 * The meta object literal for the '<em><b>Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASA_WEB_SERVICE__DATABASE = eINSTANCE.getSybaseASAWebService_Database();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl <em>Schedule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSchedule()
		 * @generated
		 */
		EClass SCHEDULE = eINSTANCE.getSchedule();

        /**
		 * The meta object literal for the '<em><b>Recurring</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULE__RECURRING = eINSTANCE.getSchedule_Recurring();

        /**
		 * The meta object literal for the '<em><b>Start Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULE__START_TIME = eINSTANCE.getSchedule_StartTime();

        /**
		 * The meta object literal for the '<em><b>Stop Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULE__STOP_TIME = eINSTANCE.getSchedule_StopTime();

        /**
		 * The meta object literal for the '<em><b>Start Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULE__START_DATE = eINSTANCE.getSchedule_StartDate();

        /**
		 * The meta object literal for the '<em><b>Days Of Week</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULE__DAYS_OF_WEEK = eINSTANCE.getSchedule_DaysOfWeek();

        /**
		 * The meta object literal for the '<em><b>Days Of Month</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULE__DAYS_OF_MONTH = eINSTANCE.getSchedule_DaysOfMonth();

        /**
		 * The meta object literal for the '<em><b>Interval Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULE__INTERVAL_UNIT = eINSTANCE.getSchedule_IntervalUnit();

        /**
		 * The meta object literal for the '<em><b>Interval Mount</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULE__INTERVAL_MOUNT = eINSTANCE.getSchedule_IntervalMount();

        /**
		 * The meta object literal for the '<em><b>Event</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHEDULE__EVENT = eINSTANCE.getSchedule_Event();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseRemoteProcedureImpl <em>Sybase ASA Base Remote Procedure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseRemoteProcedureImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseRemoteProcedure()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_REMOTE_PROCEDURE = eINSTANCE.getSybaseASABaseRemoteProcedure();

        /**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_REMOTE_PROCEDURE__LOCATION = eINSTANCE.getSybaseASABaseRemoteProcedure_Location();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseParameterImpl <em>Sybase ASA Base Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseParameterImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseParameter()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_PARAMETER = eINSTANCE.getSybaseASABaseParameter();

        /**
		 * The meta object literal for the '<em><b>Parm Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_BASE_PARAMETER__PARM_TYPE = eINSTANCE.getSybaseASABaseParameter_ParmType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseGroupImpl <em>Sybase ASA Base Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseGroupImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseGroup()
		 * @generated
		 */
		EClass SYBASE_ASA_BASE_GROUP = eINSTANCE.getSybaseASABaseGroup();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseSchemaImpl <em>Sybase ASA Base Schema</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseSchemaImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseSchema()
		 * @generated
		 */
        EClass SYBASE_ASA_BASE_SCHEMA = eINSTANCE.getSybaseASABaseSchema();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserImpl <em>Sybase ASA Base User</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseUser()
		 * @generated
		 */
        EClass SYBASE_ASA_BASE_USER = eINSTANCE.getSybaseASABaseUser();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl <em>Sybase ASA Default Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASADefaultWrapper()
		 * @generated
		 */
		EClass SYBASE_ASA_DEFAULT_WRAPPER = eINSTANCE.getSybaseASADefaultWrapper();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_DEFAULT_WRAPPER__VALUE = eINSTANCE.getSybaseASADefaultWrapper_Value();

        /**
		 * The meta object literal for the '<em><b>Is Literal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_DEFAULT_WRAPPER__IS_LITERAL = eINSTANCE.getSybaseASADefaultWrapper_IsLiteral();

        /**
		 * The meta object literal for the '<em><b>Partition Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_DEFAULT_WRAPPER__PARTITION_SIZE = eINSTANCE.getSybaseASADefaultWrapper_PartitionSize();

        /**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_DEFAULT_WRAPPER__TYPE = eINSTANCE.getSybaseASADefaultWrapper_Type();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EventConditionImpl <em>Event Condition</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.EventConditionImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getEventCondition()
		 * @generated
		 */
        EClass EVENT_CONDITION = eINSTANCE.getEventCondition();

        /**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute EVENT_CONDITION__OPERATOR = eINSTANCE.getEventCondition_Operator();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute EVENT_CONDITION__VALUE = eINSTANCE.getEventCondition_Value();

        /**
		 * The meta object literal for the '<em><b>Event</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference EVENT_CONDITION__EVENT = eINSTANCE.getEventCondition_Event();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption <em>Transaction Option</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getTransactionOption()
		 * @generated
		 */
		EEnum TRANSACTION_OPTION = eINSTANCE.getTransactionOption();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault <em>Type Of Default</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getTypeOfDefault()
		 * @generated
		 */
		EEnum TYPE_OF_DEFAULT = eINSTANCE.getTypeOfDefault();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime <em>Sybase ASA Base Action Time</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSybaseASABaseActionTime()
		 * @generated
		 */
		EEnum SYBASE_ASA_BASE_ACTION_TIME = eINSTANCE.getSybaseASABaseActionTime();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType <em>Event Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getEventType()
		 * @generated
		 */
		EEnum EVENT_TYPE = eINSTANCE.getEventType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType <em>Java Support Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getJavaSupportType()
		 * @generated
		 */
		EEnum JAVA_SUPPORT_TYPE = eINSTANCE.getJavaSupportType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType <em>Event Location Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getEventLocationType()
		 * @generated
		 */
		EEnum EVENT_LOCATION_TYPE = eINSTANCE.getEventLocationType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType <em>Interval Unit Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getIntervalUnitType()
		 * @generated
		 */
		EEnum INTERVAL_UNIT_TYPE = eINSTANCE.getIntervalUnitType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType <em>System Defined Default Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getSystemDefinedDefaultType()
		 * @generated
		 */
		EEnum SYSTEM_DEFINED_DEFAULT_TYPE = eINSTANCE.getSystemDefinedDefaultType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType <em>Allow Null Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getAllowNullType()
		 * @generated
		 */
		EEnum ALLOW_NULL_TYPE = eINSTANCE.getAllowNullType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType <em>Parameter Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseasabasesqlmodelPackageImpl#getParameterType()
		 * @generated
		 */
		EEnum PARAMETER_TYPE = eINSTANCE.getParameterType();

	}

} //SybaseasabasesqlmodelPackage
