package org.eclipse.datatools.enablement.ibm.db2.model;


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
 * <!-- begin-model-doc -->
 * DB2 Model is the OO representation of the concepts defined in the:
 * SQL Reference for Cross-Platform Development - v1.1
 * http://www7b.software.ibm.com/dmdd/library/techarticle/0206sqlref/0206sqlref.html
 * 
 * This book defines the DB2 UDB SQL language elements that are common to the IBM DB2 Universal Database Family of relational database products across the
 * following environments: Environment IBM Relational Database Product Short Name
 *  - z/OS and OS/390 DB2 Universal Database for z/OS and OS/390 Version 7 DB2 UDB for z/OS and OS/390
 *  - OS/400 DB2 Universal Database for iSeries Version 5 Release 2 DB2 UDB for iSeries UNIX
 *  - AIX
 *  - HP-UX Version 10
 *  - HP-UX Version 11
 *  - Linux
 *  - Solaris
 *  - Windows for 32-bit operating systems
 *  - OS/2
 *  - DB2 Universal Database for the Linux, UNIX and Windows Platforms Version 8
 *  - DB2 UDB for LUW
 * 
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelFactory
 * @model kind="package"
 * @generated
 */
public interface DB2ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "db2"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org.eclipse.datatools.enablement.ibm.db2.model/db2.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "DB2Model"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DB2ModelPackage eINSTANCE = org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseImpl <em>DB2 Database</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Database()
	 * @generated
	 */
	int DB2_DATABASE = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__EANNOTATIONS = SQLSchemaPackage.DATABASE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__NAME = SQLSchemaPackage.DATABASE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__DEPENDENCIES = SQLSchemaPackage.DATABASE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__DESCRIPTION = SQLSchemaPackage.DATABASE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__LABEL = SQLSchemaPackage.DATABASE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__COMMENTS = SQLSchemaPackage.DATABASE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__EXTENSIONS = SQLSchemaPackage.DATABASE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__PRIVILEGES = SQLSchemaPackage.DATABASE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__VENDOR = SQLSchemaPackage.DATABASE__VENDOR;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__VERSION = SQLSchemaPackage.DATABASE__VERSION;

	/**
	 * The feature id for the '<em><b>Schemas</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__SCHEMAS = SQLSchemaPackage.DATABASE__SCHEMAS;

	/**
	 * The feature id for the '<em><b>Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__EVENTS = SQLSchemaPackage.DATABASE__EVENTS;

	/**
	 * The feature id for the '<em><b>Catalogs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__CATALOGS = SQLSchemaPackage.DATABASE__CATALOGS;

	/**
	 * The feature id for the '<em><b>Authorization Ids</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__AUTHORIZATION_IDS = SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS;

	/**
	 * The feature id for the '<em><b>Partitioned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__PARTITIONED = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Organize By Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE__DEFAULT_ORGANIZE_BY_ROW = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DB2 Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_FEATURE_COUNT = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan <em>DB2 Access Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2AccessPlan()
	 * @generated
	 */
	int DB2_ACCESS_PLAN = 13;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ACCESS_PLAN__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ACCESS_PLAN__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ACCESS_PLAN__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ACCESS_PLAN__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ACCESS_PLAN__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ACCESS_PLAN__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ACCESS_PLAN__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ACCESS_PLAN__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The number of structural features of the '<em>DB2 Access Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ACCESS_PLAN_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl <em>DB2 Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Package()
	 * @generated
	 */
	int DB2_PACKAGE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__EANNOTATIONS = DB2_ACCESS_PLAN__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__NAME = DB2_ACCESS_PLAN__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__DEPENDENCIES = DB2_ACCESS_PLAN__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__DESCRIPTION = DB2_ACCESS_PLAN__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__LABEL = DB2_ACCESS_PLAN__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__COMMENTS = DB2_ACCESS_PLAN__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__EXTENSIONS = DB2_ACCESS_PLAN__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__PRIVILEGES = DB2_ACCESS_PLAN__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Operative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__OPERATIVE = DB2_ACCESS_PLAN_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Valid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__VALID = DB2_ACCESS_PLAN_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__VERSION = DB2_ACCESS_PLAN_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Default Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__DEFAULT_SCHEMA = DB2_ACCESS_PLAN_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sql Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__SQL_PATH = DB2_ACCESS_PLAN_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Reopt Var</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__REOPT_VAR = DB2_ACCESS_PLAN_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Isolation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__ISOLATION = DB2_ACCESS_PLAN_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__UNIQUE_ID = DB2_ACCESS_PLAN_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Last Bind TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__LAST_BIND_TS = DB2_ACCESS_PLAN_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__SCHEMA = DB2_ACCESS_PLAN_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE__STATEMENTS = DB2_ACCESS_PLAN_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>DB2 Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_FEATURE_COUNT = DB2_ACCESS_PLAN_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl <em>DB2 Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Table()
	 * @generated
	 */
	int DB2_TABLE = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__EANNOTATIONS = SQLTablesPackage.PERSISTENT_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__NAME = SQLTablesPackage.PERSISTENT_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__DEPENDENCIES = SQLTablesPackage.PERSISTENT_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__DESCRIPTION = SQLTablesPackage.PERSISTENT_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__LABEL = SQLTablesPackage.PERSISTENT_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__COMMENTS = SQLTablesPackage.PERSISTENT_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__EXTENSIONS = SQLTablesPackage.PERSISTENT_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__PRIVILEGES = SQLTablesPackage.PERSISTENT_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__COLUMNS = SQLTablesPackage.PERSISTENT_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__SUPERTABLE = SQLTablesPackage.PERSISTENT_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__SUBTABLES = SQLTablesPackage.PERSISTENT_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__SCHEMA = SQLTablesPackage.PERSISTENT_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__UDT = SQLTablesPackage.PERSISTENT_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__TRIGGERS = SQLTablesPackage.PERSISTENT_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__INDEX = SQLTablesPackage.PERSISTENT_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.PERSISTENT_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__INSERTABLE = SQLTablesPackage.PERSISTENT_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__UPDATABLE = SQLTablesPackage.PERSISTENT_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__CONSTRAINTS = SQLTablesPackage.PERSISTENT_TABLE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__REFERENCING_FOREIGN_KEYS = SQLTablesPackage.PERSISTENT_TABLE__REFERENCING_FOREIGN_KEYS;

	/**
	 * The feature id for the '<em><b>Data Capture</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__DATA_CAPTURE = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Activate Row Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__ACTIVATE_ROW_ACCESS_CONTROL = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Activate Column Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Organize By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__ORGANIZE_BY = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__PACKAGES = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Periods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__PERIODS = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>History Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__HISTORY_TABLE = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Temporal Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__TEMPORAL_TABLE = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__MASKS = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE__PERMISSIONS = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>DB2 Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TABLE_FEATURE_COUNT = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TriggerImpl <em>DB2 Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TriggerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Trigger()
	 * @generated
	 */
	int DB2_TRIGGER = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__EANNOTATIONS = SQLTablesPackage.TRIGGER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__NAME = SQLTablesPackage.TRIGGER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__DEPENDENCIES = SQLTablesPackage.TRIGGER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__DESCRIPTION = SQLTablesPackage.TRIGGER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__LABEL = SQLTablesPackage.TRIGGER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__COMMENTS = SQLTablesPackage.TRIGGER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__EXTENSIONS = SQLTablesPackage.TRIGGER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__PRIVILEGES = SQLTablesPackage.TRIGGER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__SCHEMA = SQLTablesPackage.TRIGGER__SCHEMA;

	/**
	 * The feature id for the '<em><b>Subject Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__SUBJECT_TABLE = SQLTablesPackage.TRIGGER__SUBJECT_TABLE;

	/**
	 * The feature id for the '<em><b>Action Statement</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__ACTION_STATEMENT = SQLTablesPackage.TRIGGER__ACTION_STATEMENT;

	/**
	 * The feature id for the '<em><b>Trigger Column</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__TRIGGER_COLUMN = SQLTablesPackage.TRIGGER__TRIGGER_COLUMN;

	/**
	 * The feature id for the '<em><b>Action Granularity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__ACTION_GRANULARITY = SQLTablesPackage.TRIGGER__ACTION_GRANULARITY;

	/**
	 * The feature id for the '<em><b>When</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__WHEN = SQLTablesPackage.TRIGGER__WHEN;

	/**
	 * The feature id for the '<em><b>Time Stamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__TIME_STAMP = SQLTablesPackage.TRIGGER__TIME_STAMP;

	/**
	 * The feature id for the '<em><b>Action Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__ACTION_TIME = SQLTablesPackage.TRIGGER__ACTION_TIME;

	/**
	 * The feature id for the '<em><b>Update Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__UPDATE_TYPE = SQLTablesPackage.TRIGGER__UPDATE_TYPE;

	/**
	 * The feature id for the '<em><b>Insert Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__INSERT_TYPE = SQLTablesPackage.TRIGGER__INSERT_TYPE;

	/**
	 * The feature id for the '<em><b>Delete Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__DELETE_TYPE = SQLTablesPackage.TRIGGER__DELETE_TYPE;

	/**
	 * The feature id for the '<em><b>Old Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__OLD_ROW = SQLTablesPackage.TRIGGER__OLD_ROW;

	/**
	 * The feature id for the '<em><b>New Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__NEW_ROW = SQLTablesPackage.TRIGGER__NEW_ROW;

	/**
	 * The feature id for the '<em><b>Old Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__OLD_TABLE = SQLTablesPackage.TRIGGER__OLD_TABLE;

	/**
	 * The feature id for the '<em><b>New Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__NEW_TABLE = SQLTablesPackage.TRIGGER__NEW_TABLE;

	/**
	 * The feature id for the '<em><b>Operative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__OPERATIVE = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Secured</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER__SECURED = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DB2 Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRIGGER_FEATURE_COUNT = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl <em>DB2 Procedure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Procedure()
	 * @generated
	 */
	int DB2_PROCEDURE = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__EANNOTATIONS = SQLRoutinesPackage.PROCEDURE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__NAME = SQLRoutinesPackage.PROCEDURE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__DEPENDENCIES = SQLRoutinesPackage.PROCEDURE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__DESCRIPTION = SQLRoutinesPackage.PROCEDURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__LABEL = SQLRoutinesPackage.PROCEDURE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__COMMENTS = SQLRoutinesPackage.PROCEDURE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__EXTENSIONS = SQLRoutinesPackage.PROCEDURE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__PRIVILEGES = SQLRoutinesPackage.PROCEDURE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__SPECIFIC_NAME = SQLRoutinesPackage.PROCEDURE__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__LANGUAGE = SQLRoutinesPackage.PROCEDURE__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__PARAMETER_STYLE = SQLRoutinesPackage.PROCEDURE__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__DETERMINISTIC = SQLRoutinesPackage.PROCEDURE__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__SQL_DATA_ACCESS = SQLRoutinesPackage.PROCEDURE__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__CREATION_TS = SQLRoutinesPackage.PROCEDURE__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__LAST_ALTERED_TS = SQLRoutinesPackage.PROCEDURE__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__AUTHORIZATION_ID = SQLRoutinesPackage.PROCEDURE__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__SECURITY = SQLRoutinesPackage.PROCEDURE__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__EXTERNAL_NAME = SQLRoutinesPackage.PROCEDURE__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__PARAMETERS = SQLRoutinesPackage.PROCEDURE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__SOURCE = SQLRoutinesPackage.PROCEDURE__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__SCHEMA = SQLRoutinesPackage.PROCEDURE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Max Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__MAX_RESULT_SETS = SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS;

	/**
	 * The feature id for the '<em><b>Old Save Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__OLD_SAVE_POINT = SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT;

	/**
	 * The feature id for the '<em><b>Result Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__RESULT_SET = SQLRoutinesPackage.PROCEDURE__RESULT_SET;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__FENCED = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__THREADSAFE = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__DB_INFO = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__IMPLICIT_SCHEMA = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__FEDERATED = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__PARM_CCSID = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__SPECIAL_REGISTER = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Change State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__CHANGE_STATE = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__DEBUG_ID = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__PROGRAM_TYPE = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__ORIG_SCHEMA_NAME = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__ORIG_PARM_SIG = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Extended Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__EXTENDED_OPTIONS = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Routine Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__ROUTINE_EXTENSIONS = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Model Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__MODEL_RESULT_SETS = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Null Input</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__NULL_INPUT = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__VERSION = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__DIALECT = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>External Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__EXTERNAL_ACTION = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Return</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__RETURN = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Java Options</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__JAVA_OPTIONS = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Deploy</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE__DEPLOY = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 21;

	/**
	 * The number of structural features of the '<em>DB2 Procedure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_FEATURE_COUNT = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 22;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl <em>DB2 Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Schema()
	 * @generated
	 */
	int DB2_SCHEMA = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__EANNOTATIONS = SQLSchemaPackage.SCHEMA__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__NAME = SQLSchemaPackage.SCHEMA__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__DEPENDENCIES = SQLSchemaPackage.SCHEMA__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__DESCRIPTION = SQLSchemaPackage.SCHEMA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__LABEL = SQLSchemaPackage.SCHEMA__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__COMMENTS = SQLSchemaPackage.SCHEMA__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__EXTENSIONS = SQLSchemaPackage.SCHEMA__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__PRIVILEGES = SQLSchemaPackage.SCHEMA__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__TRIGGERS = SQLSchemaPackage.SCHEMA__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Indices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__INDICES = SQLSchemaPackage.SCHEMA__INDICES;

	/**
	 * The feature id for the '<em><b>Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__TABLES = SQLSchemaPackage.SCHEMA__TABLES;

	/**
	 * The feature id for the '<em><b>Sequences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__SEQUENCES = SQLSchemaPackage.SCHEMA__SEQUENCES;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__DATABASE = SQLSchemaPackage.SCHEMA__DATABASE;

	/**
	 * The feature id for the '<em><b>Catalog</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__CATALOG = SQLSchemaPackage.SCHEMA__CATALOG;

	/**
	 * The feature id for the '<em><b>Assertions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__ASSERTIONS = SQLSchemaPackage.SCHEMA__ASSERTIONS;

	/**
	 * The feature id for the '<em><b>User Defined Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__USER_DEFINED_TYPES = SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES;

	/**
	 * The feature id for the '<em><b>Char Sets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__CHAR_SETS = SQLSchemaPackage.SCHEMA__CHAR_SETS;

	/**
	 * The feature id for the '<em><b>Routines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__ROUTINES = SQLSchemaPackage.SCHEMA__ROUTINES;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__OWNER = SQLSchemaPackage.SCHEMA__OWNER;

	/**
	 * The feature id for the '<em><b>Access Plans</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__ACCESS_PLANS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Olap Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__OLAP_OBJECTS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Jars</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__JARS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Xsr Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__XSR_OBJECTS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__PACKAGES = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__MASKS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__PERMISSIONS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__MODULES = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Global Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA__GLOBAL_VARIABLES = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>DB2 Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SCHEMA_FEATURE_COUNT = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine <em>DB2 Routine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Routine()
	 * @generated
	 */
	int DB2_ROUTINE = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__EANNOTATIONS = SQLRoutinesPackage.ROUTINE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__NAME = SQLRoutinesPackage.ROUTINE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__DEPENDENCIES = SQLRoutinesPackage.ROUTINE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__DESCRIPTION = SQLRoutinesPackage.ROUTINE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__LABEL = SQLRoutinesPackage.ROUTINE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__COMMENTS = SQLRoutinesPackage.ROUTINE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__EXTENSIONS = SQLRoutinesPackage.ROUTINE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__PRIVILEGES = SQLRoutinesPackage.ROUTINE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__SPECIFIC_NAME = SQLRoutinesPackage.ROUTINE__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__LANGUAGE = SQLRoutinesPackage.ROUTINE__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__PARAMETER_STYLE = SQLRoutinesPackage.ROUTINE__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__DETERMINISTIC = SQLRoutinesPackage.ROUTINE__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__SQL_DATA_ACCESS = SQLRoutinesPackage.ROUTINE__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__CREATION_TS = SQLRoutinesPackage.ROUTINE__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__LAST_ALTERED_TS = SQLRoutinesPackage.ROUTINE__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__AUTHORIZATION_ID = SQLRoutinesPackage.ROUTINE__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__SECURITY = SQLRoutinesPackage.ROUTINE__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__EXTERNAL_NAME = SQLRoutinesPackage.ROUTINE__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__PARAMETERS = SQLRoutinesPackage.ROUTINE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__SOURCE = SQLRoutinesPackage.ROUTINE__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__SCHEMA = SQLRoutinesPackage.ROUTINE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__FENCED = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__THREADSAFE = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__DB_INFO = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__IMPLICIT_SCHEMA = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__FEDERATED = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__PARM_CCSID = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__SPECIAL_REGISTER = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Change State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__CHANGE_STATE = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__DEBUG_ID = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__PROGRAM_TYPE = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__ORIG_SCHEMA_NAME = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__ORIG_PARM_SIG = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Extended Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__EXTENDED_OPTIONS = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Routine Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE__ROUTINE_EXTENSIONS = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>DB2 Routine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_FEATURE_COUNT = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 14;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseManagerImpl <em>DB2 Database Manager</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseManagerImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2DatabaseManager()
	 * @generated
	 */
	int DB2_DATABASE_MANAGER = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Databases</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__DATABASES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Db2 Process</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__DB2_PROCESS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Server</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__SERVER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Cluster</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER__CLUSTER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>DB2 Database Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DATABASE_MANAGER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ViewImpl <em>DB2 View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ViewImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2View()
	 * @generated
	 */
	int DB2_VIEW = 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__EANNOTATIONS = SQLTablesPackage.VIEW_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__NAME = SQLTablesPackage.VIEW_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__DEPENDENCIES = SQLTablesPackage.VIEW_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__DESCRIPTION = SQLTablesPackage.VIEW_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__LABEL = SQLTablesPackage.VIEW_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__COMMENTS = SQLTablesPackage.VIEW_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__EXTENSIONS = SQLTablesPackage.VIEW_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__PRIVILEGES = SQLTablesPackage.VIEW_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__COLUMNS = SQLTablesPackage.VIEW_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__SUPERTABLE = SQLTablesPackage.VIEW_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__SUBTABLES = SQLTablesPackage.VIEW_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__SCHEMA = SQLTablesPackage.VIEW_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__UDT = SQLTablesPackage.VIEW_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__TRIGGERS = SQLTablesPackage.VIEW_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__INDEX = SQLTablesPackage.VIEW_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.VIEW_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__INSERTABLE = SQLTablesPackage.VIEW_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__UPDATABLE = SQLTablesPackage.VIEW_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__QUERY_EXPRESSION = SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Check Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__CHECK_TYPE = SQLTablesPackage.VIEW_TABLE__CHECK_TYPE;

	/**
	 * The feature id for the '<em><b>Operative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW__OPERATIVE = SQLTablesPackage.VIEW_TABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DB2 View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_VIEW_FEATURE_COUNT = SQLTablesPackage.VIEW_TABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ApplicationProcessImpl <em>DB2 Application Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ApplicationProcessImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2ApplicationProcess()
	 * @generated
	 */
	int DB2_APPLICATION_PROCESS = 9;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Isolation Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__ISOLATION_LEVEL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unit Of Work</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS__UNIT_OF_WORK = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DB2 Application Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_APPLICATION_PROCESS_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TransactionImpl <em>DB2 Transaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TransactionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Transaction()
	 * @generated
	 */
	int DB2_TRANSACTION = 10;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRANSACTION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRANSACTION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRANSACTION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRANSACTION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRANSACTION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRANSACTION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRANSACTION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRANSACTION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The number of structural features of the '<em>DB2 Transaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_TRANSACTION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SystemSchemaImpl <em>DB2 System Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SystemSchemaImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2SystemSchema()
	 * @generated
	 */
	int DB2_SYSTEM_SCHEMA = 11;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__EANNOTATIONS = DB2_SCHEMA__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__NAME = DB2_SCHEMA__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__DEPENDENCIES = DB2_SCHEMA__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__DESCRIPTION = DB2_SCHEMA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__LABEL = DB2_SCHEMA__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__COMMENTS = DB2_SCHEMA__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__EXTENSIONS = DB2_SCHEMA__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__PRIVILEGES = DB2_SCHEMA__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__TRIGGERS = DB2_SCHEMA__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Indices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__INDICES = DB2_SCHEMA__INDICES;

	/**
	 * The feature id for the '<em><b>Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__TABLES = DB2_SCHEMA__TABLES;

	/**
	 * The feature id for the '<em><b>Sequences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__SEQUENCES = DB2_SCHEMA__SEQUENCES;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__DATABASE = DB2_SCHEMA__DATABASE;

	/**
	 * The feature id for the '<em><b>Catalog</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__CATALOG = DB2_SCHEMA__CATALOG;

	/**
	 * The feature id for the '<em><b>Assertions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__ASSERTIONS = DB2_SCHEMA__ASSERTIONS;

	/**
	 * The feature id for the '<em><b>User Defined Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__USER_DEFINED_TYPES = DB2_SCHEMA__USER_DEFINED_TYPES;

	/**
	 * The feature id for the '<em><b>Char Sets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__CHAR_SETS = DB2_SCHEMA__CHAR_SETS;

	/**
	 * The feature id for the '<em><b>Routines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__ROUTINES = DB2_SCHEMA__ROUTINES;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__OWNER = DB2_SCHEMA__OWNER;

	/**
	 * The feature id for the '<em><b>Access Plans</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__ACCESS_PLANS = DB2_SCHEMA__ACCESS_PLANS;

	/**
	 * The feature id for the '<em><b>Olap Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__OLAP_OBJECTS = DB2_SCHEMA__OLAP_OBJECTS;

	/**
	 * The feature id for the '<em><b>Jars</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__JARS = DB2_SCHEMA__JARS;

	/**
	 * The feature id for the '<em><b>Xsr Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__XSR_OBJECTS = DB2_SCHEMA__XSR_OBJECTS;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__PACKAGES = DB2_SCHEMA__PACKAGES;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__MASKS = DB2_SCHEMA__MASKS;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__PERMISSIONS = DB2_SCHEMA__PERMISSIONS;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__MODULES = DB2_SCHEMA__MODULES;

	/**
	 * The feature id for the '<em><b>Global Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA__GLOBAL_VARIABLES = DB2_SCHEMA__GLOBAL_VARIABLES;

	/**
	 * The number of structural features of the '<em>DB2 System Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SYSTEM_SCHEMA_FEATURE_COUNT = DB2_SCHEMA_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl <em>DB2 Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Source()
	 * @generated
	 */
	int DB2_SOURCE = 12;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__EANNOTATIONS = SQLRoutinesPackage.SOURCE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__NAME = SQLRoutinesPackage.SOURCE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__DEPENDENCIES = SQLRoutinesPackage.SOURCE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__DESCRIPTION = SQLRoutinesPackage.SOURCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__LABEL = SQLRoutinesPackage.SOURCE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__COMMENTS = SQLRoutinesPackage.SOURCE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__EXTENSIONS = SQLRoutinesPackage.SOURCE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__PRIVILEGES = SQLRoutinesPackage.SOURCE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__BODY = SQLRoutinesPackage.SOURCE__BODY;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__FILE_NAME = SQLRoutinesPackage.SOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__PACKAGE_NAME = SQLRoutinesPackage.SOURCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Db2 Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__DB2_PACKAGE_NAME = SQLRoutinesPackage.SOURCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Last Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__LAST_MODIFIED = SQLRoutinesPackage.SOURCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Supporting</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__SUPPORTING = SQLRoutinesPackage.SOURCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Primary</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE__PRIMARY = SQLRoutinesPackage.SOURCE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>DB2 Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_SOURCE_FEATURE_COUNT = SQLRoutinesPackage.SOURCE_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UserDefinedFunctionImpl <em>DB2 User Defined Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UserDefinedFunctionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2UserDefinedFunction()
	 * @generated
	 */
	int DB2_USER_DEFINED_FUNCTION = 14;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__EANNOTATIONS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__NAME = SQLRoutinesPackage.USER_DEFINED_FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__DEPENDENCIES = SQLRoutinesPackage.USER_DEFINED_FUNCTION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__DESCRIPTION = SQLRoutinesPackage.USER_DEFINED_FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__LABEL = SQLRoutinesPackage.USER_DEFINED_FUNCTION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__COMMENTS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__EXTENSIONS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__PRIVILEGES = SQLRoutinesPackage.USER_DEFINED_FUNCTION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__SPECIFIC_NAME = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__LANGUAGE = SQLRoutinesPackage.USER_DEFINED_FUNCTION__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__PARAMETER_STYLE = SQLRoutinesPackage.USER_DEFINED_FUNCTION__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__DETERMINISTIC = SQLRoutinesPackage.USER_DEFINED_FUNCTION__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__SQL_DATA_ACCESS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__CREATION_TS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__LAST_ALTERED_TS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__AUTHORIZATION_ID = SQLRoutinesPackage.USER_DEFINED_FUNCTION__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__SECURITY = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__EXTERNAL_NAME = SQLRoutinesPackage.USER_DEFINED_FUNCTION__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__PARAMETERS = SQLRoutinesPackage.USER_DEFINED_FUNCTION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__SOURCE = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__SCHEMA = SQLRoutinesPackage.USER_DEFINED_FUNCTION__SCHEMA;

	/**
	 * The feature id for the '<em><b>Null Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__NULL_CALL = SQLRoutinesPackage.USER_DEFINED_FUNCTION__NULL_CALL;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__STATIC = SQLRoutinesPackage.USER_DEFINED_FUNCTION__STATIC;

	/**
	 * The feature id for the '<em><b>Transform Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__TRANSFORM_GROUP = SQLRoutinesPackage.USER_DEFINED_FUNCTION__TRANSFORM_GROUP;

	/**
	 * The feature id for the '<em><b>Type Preserving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__TYPE_PRESERVING = SQLRoutinesPackage.USER_DEFINED_FUNCTION__TYPE_PRESERVING;

	/**
	 * The feature id for the '<em><b>Mutator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__MUTATOR = SQLRoutinesPackage.USER_DEFINED_FUNCTION__MUTATOR;

	/**
	 * The feature id for the '<em><b>Return Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__RETURN_TABLE = SQLRoutinesPackage.USER_DEFINED_FUNCTION__RETURN_TABLE;

	/**
	 * The feature id for the '<em><b>Return Scalar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__RETURN_SCALAR = SQLRoutinesPackage.USER_DEFINED_FUNCTION__RETURN_SCALAR;

	/**
	 * The feature id for the '<em><b>Return Cast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__RETURN_CAST = SQLRoutinesPackage.USER_DEFINED_FUNCTION__RETURN_CAST;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__FENCED = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__THREADSAFE = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__DB_INFO = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__IMPLICIT_SCHEMA = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__FEDERATED = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__PARM_CCSID = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__SPECIAL_REGISTER = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Change State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__CHANGE_STATE = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__DEBUG_ID = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__PROGRAM_TYPE = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__ORIG_SCHEMA_NAME = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__ORIG_PARM_SIG = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Extended Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__EXTENDED_OPTIONS = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Routine Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__ROUTINE_EXTENSIONS = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Final Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__FINAL_CALL = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Scratch Pad</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__SCRATCH_PAD = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Scratch Pad Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__SCRATCH_PAD_LENGTH = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Function Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__FUNCTION_TYPE = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__PREDICATE = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>External Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__EXTERNAL_ACTION = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__CARDINALITY = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Allow Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__ALLOW_PARALLEL = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Return Clause</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__RETURN_CLAUSE = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__ORIGIN = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Inherit Lock Request</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__INHERIT_LOCK_REQUEST = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__DIALECT = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__INLINE = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__VERSION = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Secured</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION__SECURED = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 28;

	/**
	 * The number of structural features of the '<em>DB2 User Defined Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_USER_DEFINED_FUNCTION_FEATURE_COUNT = SQLRoutinesPackage.USER_DEFINED_FUNCTION_FEATURE_COUNT + 29;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl <em>DB2 Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Method()
	 * @generated
	 */
	int DB2_METHOD = 15;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__EANNOTATIONS = SQLRoutinesPackage.METHOD__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__NAME = SQLRoutinesPackage.METHOD__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__DEPENDENCIES = SQLRoutinesPackage.METHOD__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__DESCRIPTION = SQLRoutinesPackage.METHOD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__LABEL = SQLRoutinesPackage.METHOD__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__COMMENTS = SQLRoutinesPackage.METHOD__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__EXTENSIONS = SQLRoutinesPackage.METHOD__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__PRIVILEGES = SQLRoutinesPackage.METHOD__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__SPECIFIC_NAME = SQLRoutinesPackage.METHOD__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__LANGUAGE = SQLRoutinesPackage.METHOD__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__PARAMETER_STYLE = SQLRoutinesPackage.METHOD__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__DETERMINISTIC = SQLRoutinesPackage.METHOD__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__SQL_DATA_ACCESS = SQLRoutinesPackage.METHOD__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__CREATION_TS = SQLRoutinesPackage.METHOD__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__LAST_ALTERED_TS = SQLRoutinesPackage.METHOD__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__AUTHORIZATION_ID = SQLRoutinesPackage.METHOD__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__SECURITY = SQLRoutinesPackage.METHOD__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__EXTERNAL_NAME = SQLRoutinesPackage.METHOD__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__PARAMETERS = SQLRoutinesPackage.METHOD__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__SOURCE = SQLRoutinesPackage.METHOD__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__SCHEMA = SQLRoutinesPackage.METHOD__SCHEMA;

	/**
	 * The feature id for the '<em><b>Null Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__NULL_CALL = SQLRoutinesPackage.METHOD__NULL_CALL;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__STATIC = SQLRoutinesPackage.METHOD__STATIC;

	/**
	 * The feature id for the '<em><b>Transform Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__TRANSFORM_GROUP = SQLRoutinesPackage.METHOD__TRANSFORM_GROUP;

	/**
	 * The feature id for the '<em><b>Type Preserving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__TYPE_PRESERVING = SQLRoutinesPackage.METHOD__TYPE_PRESERVING;

	/**
	 * The feature id for the '<em><b>Mutator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__MUTATOR = SQLRoutinesPackage.METHOD__MUTATOR;

	/**
	 * The feature id for the '<em><b>Return Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__RETURN_TABLE = SQLRoutinesPackage.METHOD__RETURN_TABLE;

	/**
	 * The feature id for the '<em><b>Return Scalar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__RETURN_SCALAR = SQLRoutinesPackage.METHOD__RETURN_SCALAR;

	/**
	 * The feature id for the '<em><b>Return Cast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__RETURN_CAST = SQLRoutinesPackage.METHOD__RETURN_CAST;

	/**
	 * The feature id for the '<em><b>Overriding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__OVERRIDING = SQLRoutinesPackage.METHOD__OVERRIDING;

	/**
	 * The feature id for the '<em><b>Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__CONSTRUCTOR = SQLRoutinesPackage.METHOD__CONSTRUCTOR;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__FENCED = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__THREADSAFE = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__DB_INFO = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__IMPLICIT_SCHEMA = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__FEDERATED = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__PARM_CCSID = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__SPECIAL_REGISTER = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Change State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__CHANGE_STATE = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__DEBUG_ID = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__PROGRAM_TYPE = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__ORIG_SCHEMA_NAME = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__ORIG_PARM_SIG = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Extended Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__EXTENDED_OPTIONS = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Routine Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__ROUTINE_EXTENSIONS = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Final Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__FINAL_CALL = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Scratch Pad</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__SCRATCH_PAD = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Scratch Pad Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__SCRATCH_PAD_LENGTH = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Function Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__FUNCTION_TYPE = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__PREDICATE = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>External Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__EXTERNAL_ACTION = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__CARDINALITY = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Allow Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__ALLOW_PARALLEL = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Return Clause</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__RETURN_CLAUSE = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__ORIGIN = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Inherit Lock Request</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__INHERIT_LOCK_REQUEST = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__DIALECT = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__INLINE = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__VERSION = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Secured</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__SECURED = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 28;

	/**
	 * The feature id for the '<em><b>Returns Self As Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__RETURNS_SELF_AS_RESULT = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 29;

	/**
	 * The feature id for the '<em><b>Implemented</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD__IMPLEMENTED = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 30;

	/**
	 * The number of structural features of the '<em>DB2 Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_METHOD_FEATURE_COUNT = SQLRoutinesPackage.METHOD_FEATURE_COUNT + 31;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl <em>DB2 Extended Options</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2ExtendedOptions()
	 * @generated
	 */
	int DB2_EXTENDED_OPTIONS = 16;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Classpath Compile Jars</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__CLASSPATH_COMPILE_JARS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pre Compile Opts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__PRE_COMPILE_OPTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>For Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__FOR_DEBUG = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Built</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__BUILT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Compile Opts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__COMPILE_OPTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Link Opts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__LINK_OPTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Bind Opts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__BIND_OPTS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Colid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS__COLID = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>DB2 Extended Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_EXTENDED_OPTIONS_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2AliasImpl <em>DB2 Alias</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2AliasImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Alias()
	 * @generated
	 */
	int DB2_ALIAS = 17;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__EANNOTATIONS = SQLTablesPackage.TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__NAME = SQLTablesPackage.TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__DEPENDENCIES = SQLTablesPackage.TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__DESCRIPTION = SQLTablesPackage.TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__LABEL = SQLTablesPackage.TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__COMMENTS = SQLTablesPackage.TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__EXTENSIONS = SQLTablesPackage.TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__PRIVILEGES = SQLTablesPackage.TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__COLUMNS = SQLTablesPackage.TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__SUPERTABLE = SQLTablesPackage.TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__SUBTABLES = SQLTablesPackage.TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__SCHEMA = SQLTablesPackage.TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__UDT = SQLTablesPackage.TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__TRIGGERS = SQLTablesPackage.TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__INDEX = SQLTablesPackage.TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__INSERTABLE = SQLTablesPackage.TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__UPDATABLE = SQLTablesPackage.TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Aliased Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS__ALIASED_TABLE = SQLTablesPackage.TABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DB2 Alias</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ALIAS_FEATURE_COUNT = SQLTablesPackage.TABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl <em>DB2 Materialized Query Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2MaterializedQueryTable()
	 * @generated
	 */
	int DB2_MATERIALIZED_QUERY_TABLE = 18;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__EANNOTATIONS = SQLTablesPackage.DERIVED_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__NAME = SQLTablesPackage.DERIVED_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__DEPENDENCIES = SQLTablesPackage.DERIVED_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__DESCRIPTION = SQLTablesPackage.DERIVED_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__LABEL = SQLTablesPackage.DERIVED_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__COMMENTS = SQLTablesPackage.DERIVED_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__EXTENSIONS = SQLTablesPackage.DERIVED_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__PRIVILEGES = SQLTablesPackage.DERIVED_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__COLUMNS = SQLTablesPackage.DERIVED_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__SUPERTABLE = SQLTablesPackage.DERIVED_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__SUBTABLES = SQLTablesPackage.DERIVED_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__SCHEMA = SQLTablesPackage.DERIVED_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__UDT = SQLTablesPackage.DERIVED_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__TRIGGERS = SQLTablesPackage.DERIVED_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__INDEX = SQLTablesPackage.DERIVED_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.DERIVED_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__INSERTABLE = SQLTablesPackage.DERIVED_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__UPDATABLE = SQLTablesPackage.DERIVED_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__QUERY_EXPRESSION = SQLTablesPackage.DERIVED_TABLE__QUERY_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Refresh</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__REFRESH = SQLTablesPackage.DERIVED_TABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Optimize Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY = SQLTablesPackage.DERIVED_TABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Maintained By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__MAINTAINED_BY = SQLTablesPackage.DERIVED_TABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Activate Row Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL = SQLTablesPackage.DERIVED_TABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Activate Column Access Control</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL = SQLTablesPackage.DERIVED_TABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Masks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__MASKS = SQLTablesPackage.DERIVED_TABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS = SQLTablesPackage.DERIVED_TABLE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>DB2 Materialized Query Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MATERIALIZED_QUERY_TABLE_FEATURE_COUNT = SQLTablesPackage.DERIVED_TABLE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IndexImpl <em>DB2 Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IndexImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Index()
	 * @generated
	 */
	int DB2_INDEX = 19;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__EANNOTATIONS = SQLConstraintsPackage.INDEX__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__NAME = SQLConstraintsPackage.INDEX__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__DEPENDENCIES = SQLConstraintsPackage.INDEX__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__DESCRIPTION = SQLConstraintsPackage.INDEX__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__LABEL = SQLConstraintsPackage.INDEX__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__COMMENTS = SQLConstraintsPackage.INDEX__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__EXTENSIONS = SQLConstraintsPackage.INDEX__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__PRIVILEGES = SQLConstraintsPackage.INDEX__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__SCHEMA = SQLConstraintsPackage.INDEX__SCHEMA;

	/**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__CLUSTERED = SQLConstraintsPackage.INDEX__CLUSTERED;

	/**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__FILL_FACTOR = SQLConstraintsPackage.INDEX__FILL_FACTOR;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__UNIQUE = SQLConstraintsPackage.INDEX__UNIQUE;

	/**
	 * The feature id for the '<em><b>System Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__SYSTEM_GENERATED = SQLConstraintsPackage.INDEX__SYSTEM_GENERATED;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__MEMBERS = SQLConstraintsPackage.INDEX__MEMBERS;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__TABLE = SQLConstraintsPackage.INDEX__TABLE;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__FOREIGN_KEY = SQLConstraintsPackage.INDEX__FOREIGN_KEY;

	/**
	 * The feature id for the '<em><b>Included Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__INCLUDED_MEMBERS = SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS;

	/**
	 * The feature id for the '<em><b>Index Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__INDEX_TYPE = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bus Period Without Overlap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__BUS_PERIOD_WITHOUT_OVERLAP = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Encoded Vector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__ENCODED_VECTOR = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>DB2 Multidimensional Index</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>DB2 Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_INDEX_FEATURE_COUNT = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MultidimensionalIndexImpl <em>DB2 Multidimensional Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MultidimensionalIndexImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2MultidimensionalIndex()
	 * @generated
	 */
	int DB2_MULTIDIMENSIONAL_INDEX = 20;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__EANNOTATIONS = SQLConstraintsPackage.INDEX__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__NAME = SQLConstraintsPackage.INDEX__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__DEPENDENCIES = SQLConstraintsPackage.INDEX__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__DESCRIPTION = SQLConstraintsPackage.INDEX__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__LABEL = SQLConstraintsPackage.INDEX__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__COMMENTS = SQLConstraintsPackage.INDEX__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__EXTENSIONS = SQLConstraintsPackage.INDEX__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__PRIVILEGES = SQLConstraintsPackage.INDEX__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__SCHEMA = SQLConstraintsPackage.INDEX__SCHEMA;

	/**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__CLUSTERED = SQLConstraintsPackage.INDEX__CLUSTERED;

	/**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__FILL_FACTOR = SQLConstraintsPackage.INDEX__FILL_FACTOR;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__UNIQUE = SQLConstraintsPackage.INDEX__UNIQUE;

	/**
	 * The feature id for the '<em><b>System Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__SYSTEM_GENERATED = SQLConstraintsPackage.INDEX__SYSTEM_GENERATED;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__MEMBERS = SQLConstraintsPackage.INDEX__MEMBERS;

	/**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__TABLE = SQLConstraintsPackage.INDEX__TABLE;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__FOREIGN_KEY = SQLConstraintsPackage.INDEX__FOREIGN_KEY;

	/**
	 * The feature id for the '<em><b>Included Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__INCLUDED_MEMBERS = SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS;

	/**
	 * The feature id for the '<em><b>Dimension Indexes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DB2 Multidimensional Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MULTIDIMENSIONAL_INDEX_FEATURE_COUNT = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function <em>DB2 Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Function()
	 * @generated
	 */
	int DB2_FUNCTION = 21;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__EANNOTATIONS = DB2_ROUTINE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__NAME = DB2_ROUTINE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__DEPENDENCIES = DB2_ROUTINE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__DESCRIPTION = DB2_ROUTINE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__LABEL = DB2_ROUTINE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__COMMENTS = DB2_ROUTINE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__EXTENSIONS = DB2_ROUTINE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__PRIVILEGES = DB2_ROUTINE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__SPECIFIC_NAME = DB2_ROUTINE__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__LANGUAGE = DB2_ROUTINE__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__PARAMETER_STYLE = DB2_ROUTINE__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__DETERMINISTIC = DB2_ROUTINE__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__SQL_DATA_ACCESS = DB2_ROUTINE__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__CREATION_TS = DB2_ROUTINE__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__LAST_ALTERED_TS = DB2_ROUTINE__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__AUTHORIZATION_ID = DB2_ROUTINE__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__SECURITY = DB2_ROUTINE__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__EXTERNAL_NAME = DB2_ROUTINE__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__PARAMETERS = DB2_ROUTINE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__SOURCE = DB2_ROUTINE__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__SCHEMA = DB2_ROUTINE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Fenced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__FENCED = DB2_ROUTINE__FENCED;

	/**
	 * The feature id for the '<em><b>Threadsafe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__THREADSAFE = DB2_ROUTINE__THREADSAFE;

	/**
	 * The feature id for the '<em><b>Db Info</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__DB_INFO = DB2_ROUTINE__DB_INFO;

	/**
	 * The feature id for the '<em><b>Implicit Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__IMPLICIT_SCHEMA = DB2_ROUTINE__IMPLICIT_SCHEMA;

	/**
	 * The feature id for the '<em><b>Federated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__FEDERATED = DB2_ROUTINE__FEDERATED;

	/**
	 * The feature id for the '<em><b>Parm Ccsid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__PARM_CCSID = DB2_ROUTINE__PARM_CCSID;

	/**
	 * The feature id for the '<em><b>Special Register</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__SPECIAL_REGISTER = DB2_ROUTINE__SPECIAL_REGISTER;

	/**
	 * The feature id for the '<em><b>Change State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__CHANGE_STATE = DB2_ROUTINE__CHANGE_STATE;

	/**
	 * The feature id for the '<em><b>Debug Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__DEBUG_ID = DB2_ROUTINE__DEBUG_ID;

	/**
	 * The feature id for the '<em><b>Program Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__PROGRAM_TYPE = DB2_ROUTINE__PROGRAM_TYPE;

	/**
	 * The feature id for the '<em><b>Orig Schema Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__ORIG_SCHEMA_NAME = DB2_ROUTINE__ORIG_SCHEMA_NAME;

	/**
	 * The feature id for the '<em><b>Orig Parm Sig</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__ORIG_PARM_SIG = DB2_ROUTINE__ORIG_PARM_SIG;

	/**
	 * The feature id for the '<em><b>Extended Options</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__EXTENDED_OPTIONS = DB2_ROUTINE__EXTENDED_OPTIONS;

	/**
	 * The feature id for the '<em><b>Routine Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__ROUTINE_EXTENSIONS = DB2_ROUTINE__ROUTINE_EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Final Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__FINAL_CALL = DB2_ROUTINE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scratch Pad</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__SCRATCH_PAD = DB2_ROUTINE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Scratch Pad Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__SCRATCH_PAD_LENGTH = DB2_ROUTINE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Function Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__FUNCTION_TYPE = DB2_ROUTINE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__PREDICATE = DB2_ROUTINE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>External Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__EXTERNAL_ACTION = DB2_ROUTINE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__CARDINALITY = DB2_ROUTINE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Allow Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__ALLOW_PARALLEL = DB2_ROUTINE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Return Clause</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__RETURN_CLAUSE = DB2_ROUTINE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__ORIGIN = DB2_ROUTINE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Inherit Lock Request</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__INHERIT_LOCK_REQUEST = DB2_ROUTINE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__DIALECT = DB2_ROUTINE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__INLINE = DB2_ROUTINE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__VERSION = DB2_ROUTINE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Secured</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION__SECURED = DB2_ROUTINE_FEATURE_COUNT + 14;

	/**
	 * The number of structural features of the '<em>DB2 Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_FUNCTION_FEATURE_COUNT = DB2_ROUTINE_FEATURE_COUNT + 15;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JavaOptionsImpl <em>DB2 Java Options</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JavaOptionsImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2JavaOptions()
	 * @generated
	 */
	int DB2_JAVA_OPTIONS = 22;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__CLASS_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__METHOD_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sqlj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__SQLJ = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Procedure</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__PROCEDURE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Jar</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS__JAR = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>DB2 Java Options</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAVA_OPTIONS_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureDeployImpl <em>DB2 Procedure Deploy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureDeployImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2ProcedureDeploy()
	 * @generated
	 */
	int DB2_PROCEDURE_DEPLOY = 23;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY__FILE_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DB2 Procedure Deploy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PROCEDURE_DEPLOY_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2OLAPObjectImpl <em>DB2OLAP Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2OLAPObjectImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2OLAPObject()
	 * @generated
	 */
	int DB2OLAP_OBJECT = 24;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DB2OLAP Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2OLAP_OBJECT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension <em>DB2 Routine Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2RoutineExtension()
	 * @generated
	 */
	int DB2_ROUTINE_EXTENSION = 25;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_EXTENSION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_EXTENSION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_EXTENSION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_EXTENSION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_EXTENSION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_EXTENSION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_EXTENSION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_EXTENSION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The number of structural features of the '<em>DB2 Routine Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_ROUTINE_EXTENSION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IdentitySpecifierImpl <em>DB2 Identity Specifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IdentitySpecifierImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2IdentitySpecifier()
	 * @generated
	 */
	int DB2_IDENTITY_SPECIFIER = 26;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__EANNOTATIONS = SQLSchemaPackage.IDENTITY_SPECIFIER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__NAME = SQLSchemaPackage.IDENTITY_SPECIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__DEPENDENCIES = SQLSchemaPackage.IDENTITY_SPECIFIER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__DESCRIPTION = SQLSchemaPackage.IDENTITY_SPECIFIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__LABEL = SQLSchemaPackage.IDENTITY_SPECIFIER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__COMMENTS = SQLSchemaPackage.IDENTITY_SPECIFIER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__EXTENSIONS = SQLSchemaPackage.IDENTITY_SPECIFIER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__PRIVILEGES = SQLSchemaPackage.IDENTITY_SPECIFIER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Generation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__GENERATION_TYPE = SQLSchemaPackage.IDENTITY_SPECIFIER__GENERATION_TYPE;

	/**
	 * The feature id for the '<em><b>Start Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__START_VALUE = SQLSchemaPackage.IDENTITY_SPECIFIER__START_VALUE;

	/**
	 * The feature id for the '<em><b>Increment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__INCREMENT = SQLSchemaPackage.IDENTITY_SPECIFIER__INCREMENT;

	/**
	 * The feature id for the '<em><b>Minimum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__MINIMUM = SQLSchemaPackage.IDENTITY_SPECIFIER__MINIMUM;

	/**
	 * The feature id for the '<em><b>Maximum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__MAXIMUM = SQLSchemaPackage.IDENTITY_SPECIFIER__MAXIMUM;

	/**
	 * The feature id for the '<em><b>Cycle Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__CYCLE_OPTION = SQLSchemaPackage.IDENTITY_SPECIFIER__CYCLE_OPTION;

	/**
	 * The feature id for the '<em><b>Cache</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__CACHE = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__ORDER = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>System Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__SYSTEM_GENERATED = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Restart Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER__RESTART_VALUE = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>DB2 Identity Specifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_IDENTITY_SPECIFIER_FEATURE_COUNT = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl <em>DB2 Jar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Jar()
	 * @generated
	 */
	int DB2_JAR = 27;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__FILE_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__PATH = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__OWNER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Created TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__CREATED_TS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__ALTERED_TS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Procedures</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__PROCEDURES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>DB2 Jar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_JAR_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl <em>DB2 Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Column()
	 * @generated
	 */
	int DB2_COLUMN = 28;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__EANNOTATIONS = SQLTablesPackage.COLUMN__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__NAME = SQLTablesPackage.COLUMN__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__DEPENDENCIES = SQLTablesPackage.COLUMN__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__DESCRIPTION = SQLTablesPackage.COLUMN__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__LABEL = SQLTablesPackage.COLUMN__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__COMMENTS = SQLTablesPackage.COLUMN__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__EXTENSIONS = SQLTablesPackage.COLUMN__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__PRIVILEGES = SQLTablesPackage.COLUMN__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__CONTAINED_TYPE = SQLTablesPackage.COLUMN__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__REFERENCED_TYPE = SQLTablesPackage.COLUMN__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__TABLE = SQLTablesPackage.COLUMN__TABLE;

	/**
	 * The feature id for the '<em><b>Identity Specifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__IDENTITY_SPECIFIER = SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER;

	/**
	 * The feature id for the '<em><b>Generate Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__GENERATE_EXPRESSION = SQLTablesPackage.COLUMN__GENERATE_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Implementation Dependent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__IMPLEMENTATION_DEPENDENT = SQLTablesPackage.COLUMN__IMPLEMENTATION_DEPENDENT;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__NULLABLE = SQLTablesPackage.COLUMN__NULLABLE;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__DEFAULT_VALUE = SQLTablesPackage.COLUMN__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Scope Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__SCOPE_CHECK = SQLTablesPackage.COLUMN__SCOPE_CHECK;

	/**
	 * The feature id for the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__SCOPE_CHECKED = SQLTablesPackage.COLUMN__SCOPE_CHECKED;

	/**
	 * The feature id for the '<em><b>Generation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__GENERATION_TYPE = SQLTablesPackage.COLUMN_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Row Change Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__ROW_CHANGE_TIMESTAMP = SQLTablesPackage.COLUMN_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Row Begin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__ROW_BEGIN = SQLTablesPackage.COLUMN_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Row End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__ROW_END = SQLTablesPackage.COLUMN_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Trans Start ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__TRANS_START_ID = SQLTablesPackage.COLUMN_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Begin Period</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__BEGIN_PERIOD = SQLTablesPackage.COLUMN_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>End Period</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN__END_PERIOD = SQLTablesPackage.COLUMN_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>DB2 Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_COLUMN_FEATURE_COUNT = SQLTablesPackage.COLUMN_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XSRObjectImpl <em>DB2XSR Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XSRObjectImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XSRObject()
	 * @generated
	 */
	int DB2XSR_OBJECT = 29;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DB2XSR Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XSR_OBJECT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaImpl <em>DB2XML Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchema()
	 * @generated
	 */
	int DB2XML_SCHEMA = 30;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__EANNOTATIONS = DB2XSR_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__NAME = DB2XSR_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__DEPENDENCIES = DB2XSR_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__DESCRIPTION = DB2XSR_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__LABEL = DB2XSR_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__COMMENTS = DB2XSR_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__EXTENSIONS = DB2XSR_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__PRIVILEGES = DB2XSR_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__SCHEMA = DB2XSR_OBJECT__SCHEMA;

	/**
	 * The feature id for the '<em><b>Decomposition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__DECOMPOSITION = DB2XSR_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__STATUS = DB2XSR_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Xml Schema Docs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA__XML_SCHEMA_DOCS = DB2XSR_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>DB2XML Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_FEATURE_COUNT = DB2XSR_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl <em>DB2XML Schema Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchemaDocument()
	 * @generated
	 */
	int DB2XML_SCHEMA_DOCUMENT = 31;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__FILE_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Schema Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__SCHEMA_LOCATION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__TARGET_NAMESPACE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Primary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__PRIMARY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Xml Schema</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Xml Schema Doc Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>DB2XML Schema Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOCUMENT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocPropertiesImpl <em>DB2XML Schema Doc Properties</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocPropertiesImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchemaDocProperties()
	 * @generated
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES = 32;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__VALUE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Xml Schema Doc</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DB2XML Schema Doc Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2XML_SCHEMA_DOC_PROPERTIES_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageStatementImpl <em>DB2 Package Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageStatementImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2PackageStatement()
	 * @generated
	 */
	int DB2_PACKAGE_STATEMENT = 33;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Statement Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__STATEMENT_NUMBER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Section Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__SECTION_NUMBER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__PACKAGE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sql Statement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT__SQL_STATEMENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>DB2 Package Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PACKAGE_STATEMENT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DistinctUserDefinedTypeImpl <em>DB2 Distinct User Defined Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DistinctUserDefinedTypeImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2DistinctUserDefinedType()
	 * @generated
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE = 34;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__NAME = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__DESCRIPTION = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__LABEL = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__COMMENTS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__EXTENSIONS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__PRIVILEGES = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__SCHEMA = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__ORDERING = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__ORDERING;

	/**
	 * The feature id for the '<em><b>Predefined Representation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION;

	/**
	 * The number of structural features of the '<em>DB2 Distinct User Defined Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PeriodImpl <em>DB2 Period</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PeriodImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Period()
	 * @generated
	 */
	int DB2_PERIOD = 35;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERIOD__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Begin Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERIOD__BEGIN_COLUMN = 1;

	/**
	 * The feature id for the '<em><b>End Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERIOD__END_COLUMN = 2;

	/**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERIOD__TABLE = 3;

	/**
	 * The number of structural features of the '<em>DB2 Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERIOD_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ClusterImpl <em>DB2 Cluster</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ClusterImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Cluster()
	 * @generated
	 */
	int DB2_CLUSTER = 36;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__LEVEL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__INSTANCE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER__MEMBERS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>DB2 Cluster</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_CLUSTER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl <em>DB2 Member</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Member()
	 * @generated
	 */
	int DB2_MEMBER = 37;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__ID = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Home Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__HOME_HOST = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Current Host</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__CURRENT_HOST = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__STATE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Cluster</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER__CLUSTER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>DB2 Member</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MEMBER_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UniqueConstraintExtensionImpl <em>DB2 Unique Constraint Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UniqueConstraintExtensionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2UniqueConstraintExtension()
	 * @generated
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION = 38;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>SQL Object</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__SQL_OBJECT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bus Period Without Overlap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION__BUS_PERIOD_WITHOUT_OVERLAP = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DB2 Unique Constraint Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_UNIQUE_CONSTRAINT_EXTENSION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl <em>DB2 Mask</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Mask()
	 * @generated
	 */
	int DB2_MASK = 39;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Correlation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__CORRELATION_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Case Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__CASE_EXPRESSION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__ENABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Subject Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__SUBJECT_TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Subject MQT</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__SUBJECT_MQT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Subject Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK__SUBJECT_COLUMN = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>DB2 Mask</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_MASK_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PermissionImpl <em>DB2 Permission</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PermissionImpl
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Permission()
	 * @generated
	 */
	int DB2_PERMISSION = 40;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Correlation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__CORRELATION_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Search Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__SEARCH_CONDITION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Enable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__ENABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Subject Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__SUBJECT_TABLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Subject MQT</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION__SUBJECT_MQT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>DB2 Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DB2_PERMISSION_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType <em>Isolation Level Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getIsolationLevelType()
	 * @generated
	 */
	int ISOLATION_LEVEL_TYPE = 41;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType <em>DB2 Index Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2IndexType()
	 * @generated
	 */
	int DB2_INDEX_TYPE = 42;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType <em>Data Capture Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDataCaptureType()
	 * @generated
	 */
	int DATA_CAPTURE_TYPE = 43;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.UnitType <em>Unit Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getUnitType()
	 * @generated
	 */
	int UNIT_TYPE = 44;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.GenerateType <em>Generate Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.GenerateType
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getGenerateType()
	 * @generated
	 */
	int GENERATE_TYPE = 45;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition <em>DB2XML Schema Decomposition</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchemaDecomposition()
	 * @generated
	 */
	int DB2XML_SCHEMA_DECOMPOSITION = 46;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus <em>DB2XML Schema Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchemaStatus()
	 * @generated
	 */
	int DB2XML_SCHEMA_STATUS = 47;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.OriginType <em>Origin Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.OriginType
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getOriginType()
	 * @generated
	 */
	int ORIGIN_TYPE = 48;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.ReoptType <em>Reopt Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.ReoptType
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getReoptType()
	 * @generated
	 */
	int REOPT_TYPE = 49;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect <em>Source Dialect</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getSourceDialect()
	 * @generated
	 */
	int SOURCE_DIALECT = 50;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PeriodType <em>DB2 Period Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2PeriodType
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2PeriodType()
	 * @generated
	 */
	int DB2_PERIOD_TYPE = 51;


	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization <em>DB2 Table Organization</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2TableOrganization()
	 * @generated
	 */
	int DB2_TABLE_ORGANIZATION = 52;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Database <em>DB2 Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Database</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Database
	 * @generated
	 */
	EClass getDB2Database();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Database#isPartitioned <em>Partitioned</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partitioned</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Database#isPartitioned()
	 * @see #getDB2Database()
	 * @generated
	 */
	EAttribute getDB2Database_Partitioned();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Database#isDefaultOrganizeByRow <em>Default Organize By Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Organize By Row</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Database#isDefaultOrganizeByRow()
	 * @see #getDB2Database()
	 * @generated
	 */
	EAttribute getDB2Database_DefaultOrganizeByRow();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package <em>DB2 Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Package</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package
	 * @generated
	 */
	EClass getDB2Package();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#isOperative <em>Operative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operative</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#isOperative()
	 * @see #getDB2Package()
	 * @generated
	 */
	EAttribute getDB2Package_Operative();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getValid <em>Valid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Valid</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getValid()
	 * @see #getDB2Package()
	 * @generated
	 */
	EAttribute getDB2Package_Valid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getVersion()
	 * @see #getDB2Package()
	 * @generated
	 */
	EAttribute getDB2Package_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getDefaultSchema <em>Default Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getDefaultSchema()
	 * @see #getDB2Package()
	 * @generated
	 */
	EAttribute getDB2Package_DefaultSchema();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getSqlPath <em>Sql Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sql Path</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getSqlPath()
	 * @see #getDB2Package()
	 * @generated
	 */
	EAttribute getDB2Package_SqlPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getReoptVar <em>Reopt Var</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reopt Var</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getReoptVar()
	 * @see #getDB2Package()
	 * @generated
	 */
	EAttribute getDB2Package_ReoptVar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getIsolation <em>Isolation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Isolation</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getIsolation()
	 * @see #getDB2Package()
	 * @generated
	 */
	EAttribute getDB2Package_Isolation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getUniqueID <em>Unique ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique ID</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getUniqueID()
	 * @see #getDB2Package()
	 * @generated
	 */
	EAttribute getDB2Package_UniqueID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getLastBindTS <em>Last Bind TS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Bind TS</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getLastBindTS()
	 * @see #getDB2Package()
	 * @generated
	 */
	EAttribute getDB2Package_LastBindTS();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getSchema()
	 * @see #getDB2Package()
	 * @generated
	 */
	EReference getDB2Package_Schema();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getStatements <em>Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Statements</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Package#getStatements()
	 * @see #getDB2Package()
	 * @generated
	 */
	EReference getDB2Package_Statements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table <em>DB2 Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table
	 * @generated
	 */
	EClass getDB2Table();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getDataCapture <em>Data Capture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Capture</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getDataCapture()
	 * @see #getDB2Table()
	 * @generated
	 */
	EAttribute getDB2Table_DataCapture();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#isActivateRowAccessControl <em>Activate Row Access Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activate Row Access Control</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#isActivateRowAccessControl()
	 * @see #getDB2Table()
	 * @generated
	 */
	EAttribute getDB2Table_ActivateRowAccessControl();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#isActivateColumnAccessControl <em>Activate Column Access Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activate Column Access Control</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#isActivateColumnAccessControl()
	 * @see #getDB2Table()
	 * @generated
	 */
	EAttribute getDB2Table_ActivateColumnAccessControl();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getOrganizeBy <em>Organize By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Organize By</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getOrganizeBy()
	 * @see #getDB2Table()
	 * @generated
	 */
	EAttribute getDB2Table_OrganizeBy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Packages</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getPackages()
	 * @see #getDB2Table()
	 * @generated
	 */
	EReference getDB2Table_Packages();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getPeriods <em>Periods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Periods</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getPeriods()
	 * @see #getDB2Table()
	 * @generated
	 */
	EReference getDB2Table_Periods();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getHistoryTable <em>History Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>History Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getHistoryTable()
	 * @see #getDB2Table()
	 * @generated
	 */
	EReference getDB2Table_HistoryTable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getTemporalTable <em>Temporal Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Temporal Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getTemporalTable()
	 * @see #getDB2Table()
	 * @generated
	 */
	EReference getDB2Table_TemporalTable();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getMasks <em>Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Masks</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getMasks()
	 * @see #getDB2Table()
	 * @generated
	 */
	EReference getDB2Table_Masks();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Permissions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Table#getPermissions()
	 * @see #getDB2Table()
	 * @generated
	 */
	EReference getDB2Table_Permissions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger <em>DB2 Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Trigger</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger
	 * @generated
	 */
	EClass getDB2Trigger();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger#isOperative <em>Operative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operative</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger#isOperative()
	 * @see #getDB2Trigger()
	 * @generated
	 */
	EAttribute getDB2Trigger_Operative();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger#isSecured <em>Secured</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Secured</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Trigger#isSecured()
	 * @see #getDB2Trigger()
	 * @generated
	 */
	EAttribute getDB2Trigger_Secured();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure <em>DB2 Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure
	 * @generated
	 */
	EClass getDB2Procedure();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isModelResultSets <em>Model Result Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Result Sets</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isModelResultSets()
	 * @see #getDB2Procedure()
	 * @generated
	 */
	EAttribute getDB2Procedure_ModelResultSets();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isNullInput <em>Null Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Null Input</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isNullInput()
	 * @see #getDB2Procedure()
	 * @generated
	 */
	EAttribute getDB2Procedure_NullInput();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getVersion()
	 * @see #getDB2Procedure()
	 * @generated
	 */
	EAttribute getDB2Procedure_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getDialect <em>Dialect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dialect</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getDialect()
	 * @see #getDB2Procedure()
	 * @generated
	 */
	EAttribute getDB2Procedure_Dialect();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isExternalAction <em>External Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Action</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#isExternalAction()
	 * @see #getDB2Procedure()
	 * @generated
	 */
	EAttribute getDB2Procedure_ExternalAction();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getReturn <em>Return</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Return</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getReturn()
	 * @see #getDB2Procedure()
	 * @generated
	 */
	EReference getDB2Procedure_Return();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getJavaOptions <em>Java Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Java Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getJavaOptions()
	 * @see #getDB2Procedure()
	 * @generated
	 */
	EReference getDB2Procedure_JavaOptions();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getDeploy <em>Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Deploy</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure#getDeploy()
	 * @see #getDB2Procedure()
	 * @generated
	 */
	EReference getDB2Procedure_Deploy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema <em>DB2 Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema
	 * @generated
	 */
	EClass getDB2Schema();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getAccessPlans <em>Access Plans</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Access Plans</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getAccessPlans()
	 * @see #getDB2Schema()
	 * @generated
	 */
	EReference getDB2Schema_AccessPlans();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getOlapObjects <em>Olap Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Olap Objects</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getOlapObjects()
	 * @see #getDB2Schema()
	 * @generated
	 */
	EReference getDB2Schema_OlapObjects();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getJars <em>Jars</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Jars</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getJars()
	 * @see #getDB2Schema()
	 * @generated
	 */
	EReference getDB2Schema_Jars();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getXsrObjects <em>Xsr Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Xsr Objects</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getXsrObjects()
	 * @see #getDB2Schema()
	 * @generated
	 */
	EReference getDB2Schema_XsrObjects();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Packages</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getPackages()
	 * @see #getDB2Schema()
	 * @generated
	 */
	EReference getDB2Schema_Packages();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getMasks <em>Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Masks</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getMasks()
	 * @see #getDB2Schema()
	 * @generated
	 */
	EReference getDB2Schema_Masks();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Permissions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getPermissions()
	 * @see #getDB2Schema()
	 * @generated
	 */
	EReference getDB2Schema_Permissions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Modules</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getModules()
	 * @see #getDB2Schema()
	 * @generated
	 */
	EReference getDB2Schema_Modules();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getGlobalVariables <em>Global Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Global Variables</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema#getGlobalVariables()
	 * @see #getDB2Schema()
	 * @generated
	 */
	EReference getDB2Schema_GlobalVariables();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine <em>DB2 Routine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Routine</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine
	 * @generated
	 */
	EClass getDB2Routine();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getFenced <em>Fenced</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fenced</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getFenced()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_Fenced();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getThreadsafe <em>Threadsafe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Threadsafe</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getThreadsafe()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_Threadsafe();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isDbInfo <em>Db Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Db Info</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isDbInfo()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_DbInfo();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isImplicitSchema <em>Implicit Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implicit Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isImplicitSchema()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_ImplicitSchema();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isFederated <em>Federated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Federated</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#isFederated()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_Federated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getParmCcsid <em>Parm Ccsid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parm Ccsid</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getParmCcsid()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_ParmCcsid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getSpecialRegister <em>Special Register</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Special Register</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getSpecialRegister()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_SpecialRegister();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getChangeState <em>Change State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Change State</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getChangeState()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_ChangeState();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getDebugId <em>Debug Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Debug Id</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getDebugId()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_DebugId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getProgramType <em>Program Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Program Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getProgramType()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_ProgramType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getOrigSchemaName <em>Orig Schema Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Orig Schema Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getOrigSchemaName()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_OrigSchemaName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getOrigParmSig <em>Orig Parm Sig</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Orig Parm Sig</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getOrigParmSig()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EAttribute getDB2Routine_OrigParmSig();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getExtendedOptions <em>Extended Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getExtendedOptions()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EReference getDB2Routine_ExtendedOptions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getRoutineExtensions <em>Routine Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Routine Extensions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine#getRoutineExtensions()
	 * @see #getDB2Routine()
	 * @generated
	 */
	EReference getDB2Routine_RoutineExtensions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager <em>DB2 Database Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Database Manager</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager
	 * @generated
	 */
	EClass getDB2DatabaseManager();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getDatabases <em>Databases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Databases</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getDatabases()
	 * @see #getDB2DatabaseManager()
	 * @generated
	 */
	EReference getDB2DatabaseManager_Databases();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getDb2Process <em>Db2 Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Db2 Process</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getDb2Process()
	 * @see #getDB2DatabaseManager()
	 * @generated
	 */
	EReference getDB2DatabaseManager_Db2Process();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getServer <em>Server</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Server</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getServer()
	 * @see #getDB2DatabaseManager()
	 * @generated
	 */
	EReference getDB2DatabaseManager_Server();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getCluster <em>Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Cluster</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2DatabaseManager#getCluster()
	 * @see #getDB2DatabaseManager()
	 * @generated
	 */
	EReference getDB2DatabaseManager_Cluster();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2View <em>DB2 View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 View</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2View
	 * @generated
	 */
	EClass getDB2View();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2View#isOperative <em>Operative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operative</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2View#isOperative()
	 * @see #getDB2View()
	 * @generated
	 */
	EAttribute getDB2View_Operative();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess <em>DB2 Application Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Application Process</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess
	 * @generated
	 */
	EClass getDB2ApplicationProcess();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess#getIsolationLevel <em>Isolation Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Isolation Level</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess#getIsolationLevel()
	 * @see #getDB2ApplicationProcess()
	 * @generated
	 */
	EAttribute getDB2ApplicationProcess_IsolationLevel();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess#getUnitOfWork <em>Unit Of Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Unit Of Work</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ApplicationProcess#getUnitOfWork()
	 * @see #getDB2ApplicationProcess()
	 * @generated
	 */
	EReference getDB2ApplicationProcess_UnitOfWork();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Transaction <em>DB2 Transaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Transaction</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Transaction
	 * @generated
	 */
	EClass getDB2Transaction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2SystemSchema <em>DB2 System Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 System Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2SystemSchema
	 * @generated
	 */
	EClass getDB2SystemSchema();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source <em>DB2 Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Source</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Source
	 * @generated
	 */
	EClass getDB2Source();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getFileName()
	 * @see #getDB2Source()
	 * @generated
	 */
	EAttribute getDB2Source_FileName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPackageName <em>Package Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPackageName()
	 * @see #getDB2Source()
	 * @generated
	 */
	EAttribute getDB2Source_PackageName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getDb2PackageName <em>Db2 Package Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Db2 Package Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getDb2PackageName()
	 * @see #getDB2Source()
	 * @generated
	 */
	EAttribute getDB2Source_Db2PackageName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getLastModified <em>Last Modified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Modified</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getLastModified()
	 * @see #getDB2Source()
	 * @generated
	 */
	EAttribute getDB2Source_LastModified();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getSupporting <em>Supporting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Supporting</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getSupporting()
	 * @see #getDB2Source()
	 * @generated
	 */
	EReference getDB2Source_Supporting();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPrimary <em>Primary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Primary</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Source#getPrimary()
	 * @see #getDB2Source()
	 * @generated
	 */
	EReference getDB2Source_Primary();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan <em>DB2 Access Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Access Plan</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan
	 * @generated
	 */
	EClass getDB2AccessPlan();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2UserDefinedFunction <em>DB2 User Defined Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 User Defined Function</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2UserDefinedFunction
	 * @generated
	 */
	EClass getDB2UserDefinedFunction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Method <em>DB2 Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Method</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Method
	 * @generated
	 */
	EClass getDB2Method();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Method#isReturnsSelfAsResult <em>Returns Self As Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Returns Self As Result</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Method#isReturnsSelfAsResult()
	 * @see #getDB2Method()
	 * @generated
	 */
	EAttribute getDB2Method_ReturnsSelfAsResult();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Method#isImplemented <em>Implemented</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implemented</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Method#isImplemented()
	 * @see #getDB2Method()
	 * @generated
	 */
	EAttribute getDB2Method_Implemented();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions <em>DB2 Extended Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Extended Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions
	 * @generated
	 */
	EClass getDB2ExtendedOptions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getClasspathCompileJars <em>Classpath Compile Jars</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Classpath Compile Jars</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getClasspathCompileJars()
	 * @see #getDB2ExtendedOptions()
	 * @generated
	 */
	EAttribute getDB2ExtendedOptions_ClasspathCompileJars();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getPreCompileOpts <em>Pre Compile Opts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pre Compile Opts</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getPreCompileOpts()
	 * @see #getDB2ExtendedOptions()
	 * @generated
	 */
	EAttribute getDB2ExtendedOptions_PreCompileOpts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#isForDebug <em>For Debug</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>For Debug</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#isForDebug()
	 * @see #getDB2ExtendedOptions()
	 * @generated
	 */
	EAttribute getDB2ExtendedOptions_ForDebug();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#isBuilt <em>Built</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Built</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#isBuilt()
	 * @see #getDB2ExtendedOptions()
	 * @generated
	 */
	EAttribute getDB2ExtendedOptions_Built();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getCompileOpts <em>Compile Opts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Compile Opts</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getCompileOpts()
	 * @see #getDB2ExtendedOptions()
	 * @generated
	 */
	EAttribute getDB2ExtendedOptions_CompileOpts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getLinkOpts <em>Link Opts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link Opts</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getLinkOpts()
	 * @see #getDB2ExtendedOptions()
	 * @generated
	 */
	EAttribute getDB2ExtendedOptions_LinkOpts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getBindOpts <em>Bind Opts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bind Opts</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getBindOpts()
	 * @see #getDB2ExtendedOptions()
	 * @generated
	 */
	EAttribute getDB2ExtendedOptions_BindOpts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getColid <em>Colid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Colid</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions#getColid()
	 * @see #getDB2ExtendedOptions()
	 * @generated
	 */
	EAttribute getDB2ExtendedOptions_Colid();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias <em>DB2 Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Alias</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias
	 * @generated
	 */
	EClass getDB2Alias();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias#getAliasedTable <em>Aliased Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Aliased Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Alias#getAliasedTable()
	 * @see #getDB2Alias()
	 * @generated
	 */
	EReference getDB2Alias_AliasedTable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable <em>DB2 Materialized Query Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Materialized Query Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable
	 * @generated
	 */
	EClass getDB2MaterializedQueryTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getRefresh <em>Refresh</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Refresh</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getRefresh()
	 * @see #getDB2MaterializedQueryTable()
	 * @generated
	 */
	EAttribute getDB2MaterializedQueryTable_Refresh();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isOptimizeQuery <em>Optimize Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optimize Query</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isOptimizeQuery()
	 * @see #getDB2MaterializedQueryTable()
	 * @generated
	 */
	EAttribute getDB2MaterializedQueryTable_OptimizeQuery();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getMaintainedBy <em>Maintained By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maintained By</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getMaintainedBy()
	 * @see #getDB2MaterializedQueryTable()
	 * @generated
	 */
	EAttribute getDB2MaterializedQueryTable_MaintainedBy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isActivateRowAccessControl <em>Activate Row Access Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activate Row Access Control</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isActivateRowAccessControl()
	 * @see #getDB2MaterializedQueryTable()
	 * @generated
	 */
	EAttribute getDB2MaterializedQueryTable_ActivateRowAccessControl();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isActivateColumnAccessControl <em>Activate Column Access Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activate Column Access Control</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#isActivateColumnAccessControl()
	 * @see #getDB2MaterializedQueryTable()
	 * @generated
	 */
	EAttribute getDB2MaterializedQueryTable_ActivateColumnAccessControl();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getMasks <em>Masks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Masks</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getMasks()
	 * @see #getDB2MaterializedQueryTable()
	 * @generated
	 */
	EReference getDB2MaterializedQueryTable_Masks();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Permissions</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MaterializedQueryTable#getPermissions()
	 * @see #getDB2MaterializedQueryTable()
	 * @generated
	 */
	EReference getDB2MaterializedQueryTable_Permissions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index <em>DB2 Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Index</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Index
	 * @generated
	 */
	EClass getDB2Index();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getIndexType <em>Index Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Index Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getIndexType()
	 * @see #getDB2Index()
	 * @generated
	 */
	EAttribute getDB2Index_IndexType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#isBusPeriodWithoutOverlap <em>Bus Period Without Overlap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Period Without Overlap</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#isBusPeriodWithoutOverlap()
	 * @see #getDB2Index()
	 * @generated
	 */
	EAttribute getDB2Index_BusPeriodWithoutOverlap();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#isEncodedVector <em>Encoded Vector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encoded Vector</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#isEncodedVector()
	 * @see #getDB2Index()
	 * @generated
	 */
	EAttribute getDB2Index_EncodedVector();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getDB2MultidimensionalIndex <em>DB2 Multidimensional Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>DB2 Multidimensional Index</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Index#getDB2MultidimensionalIndex()
	 * @see #getDB2Index()
	 * @generated
	 */
	EReference getDB2Index_DB2MultidimensionalIndex();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex <em>DB2 Multidimensional Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Multidimensional Index</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex
	 * @generated
	 */
	EClass getDB2MultidimensionalIndex();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex#getDimensionIndexes <em>Dimension Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dimension Indexes</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2MultidimensionalIndex#getDimensionIndexes()
	 * @see #getDB2MultidimensionalIndex()
	 * @generated
	 */
	EReference getDB2MultidimensionalIndex_DimensionIndexes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function <em>DB2 Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Function</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function
	 * @generated
	 */
	EClass getDB2Function();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isFinalCall <em>Final Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final Call</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isFinalCall()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_FinalCall();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isScratchPad <em>Scratch Pad</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scratch Pad</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isScratchPad()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_ScratchPad();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getScratchPadLength <em>Scratch Pad Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scratch Pad Length</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getScratchPadLength()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_ScratchPadLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getFunctionType <em>Function Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getFunctionType()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_FunctionType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getPredicate <em>Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicate</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getPredicate()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_Predicate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isExternalAction <em>External Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Action</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isExternalAction()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_ExternalAction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getCardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cardinality</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getCardinality()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_Cardinality();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isAllowParallel <em>Allow Parallel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Parallel</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isAllowParallel()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_AllowParallel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getReturnClause <em>Return Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Clause</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getReturnClause()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_ReturnClause();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getOrigin()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_Origin();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isInheritLockRequest <em>Inherit Lock Request</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inherit Lock Request</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isInheritLockRequest()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_InheritLockRequest();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getDialect <em>Dialect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dialect</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getDialect()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_Dialect();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isInline <em>Inline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inline</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isInline()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_Inline();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#getVersion()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_Version();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isSecured <em>Secured</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Secured</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function#isSecured()
	 * @see #getDB2Function()
	 * @generated
	 */
	EAttribute getDB2Function_Secured();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions <em>DB2 Java Options</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Java Options</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions
	 * @generated
	 */
	EClass getDB2JavaOptions();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getClassName()
	 * @see #getDB2JavaOptions()
	 * @generated
	 */
	EAttribute getDB2JavaOptions_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getMethodName <em>Method Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getMethodName()
	 * @see #getDB2JavaOptions()
	 * @generated
	 */
	EAttribute getDB2JavaOptions_MethodName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#isSqlj <em>Sqlj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sqlj</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#isSqlj()
	 * @see #getDB2JavaOptions()
	 * @generated
	 */
	EAttribute getDB2JavaOptions_Sqlj();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getProcedure <em>Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getProcedure()
	 * @see #getDB2JavaOptions()
	 * @generated
	 */
	EReference getDB2JavaOptions_Procedure();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getJar <em>Jar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Jar</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions#getJar()
	 * @see #getDB2JavaOptions()
	 * @generated
	 */
	EReference getDB2JavaOptions_Jar();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ProcedureDeploy <em>DB2 Procedure Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Procedure Deploy</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ProcedureDeploy
	 * @generated
	 */
	EClass getDB2ProcedureDeploy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2ProcedureDeploy#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ProcedureDeploy#getFileName()
	 * @see #getDB2ProcedureDeploy()
	 * @generated
	 */
	EAttribute getDB2ProcedureDeploy_FileName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject <em>DB2OLAP Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2OLAP Object</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject
	 * @generated
	 */
	EClass getDB2OLAPObject();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject#getSchema()
	 * @see #getDB2OLAPObject()
	 * @generated
	 */
	EReference getDB2OLAPObject_Schema();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension <em>DB2 Routine Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Routine Extension</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension
	 * @generated
	 */
	EClass getDB2RoutineExtension();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier <em>DB2 Identity Specifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Identity Specifier</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier
	 * @generated
	 */
	EClass getDB2IdentitySpecifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#getCache <em>Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cache</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#getCache()
	 * @see #getDB2IdentitySpecifier()
	 * @generated
	 */
	EAttribute getDB2IdentitySpecifier_Cache();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#isOrder <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#isOrder()
	 * @see #getDB2IdentitySpecifier()
	 * @generated
	 */
	EAttribute getDB2IdentitySpecifier_Order();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#isSystemGenerated <em>System Generated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Generated</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#isSystemGenerated()
	 * @see #getDB2IdentitySpecifier()
	 * @generated
	 */
	EAttribute getDB2IdentitySpecifier_SystemGenerated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#getRestartValue <em>Restart Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Restart Value</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#getRestartValue()
	 * @see #getDB2IdentitySpecifier()
	 * @generated
	 */
	EAttribute getDB2IdentitySpecifier_RestartValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar <em>DB2 Jar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Jar</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar
	 * @generated
	 */
	EClass getDB2Jar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getFileName()
	 * @see #getDB2Jar()
	 * @generated
	 */
	EAttribute getDB2Jar_FileName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getPath()
	 * @see #getDB2Jar()
	 * @generated
	 */
	EAttribute getDB2Jar_Path();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Owner</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getOwner()
	 * @see #getDB2Jar()
	 * @generated
	 */
	EAttribute getDB2Jar_Owner();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getCreatedTS <em>Created TS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created TS</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getCreatedTS()
	 * @see #getDB2Jar()
	 * @generated
	 */
	EAttribute getDB2Jar_CreatedTS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getAlteredTS <em>Altered TS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Altered TS</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getAlteredTS()
	 * @see #getDB2Jar()
	 * @generated
	 */
	EAttribute getDB2Jar_AlteredTS();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getProcedures <em>Procedures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Procedures</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getProcedures()
	 * @see #getDB2Jar()
	 * @generated
	 */
	EReference getDB2Jar_Procedures();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar#getSchema()
	 * @see #getDB2Jar()
	 * @generated
	 */
	EReference getDB2Jar_Schema();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column <em>DB2 Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Column</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Column
	 * @generated
	 */
	EClass getDB2Column();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getGenerationType <em>Generation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generation Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getGenerationType()
	 * @see #getDB2Column()
	 * @generated
	 */
	EAttribute getDB2Column_GenerationType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowChangeTimestamp <em>Row Change Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Change Timestamp</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowChangeTimestamp()
	 * @see #getDB2Column()
	 * @generated
	 */
	EAttribute getDB2Column_RowChangeTimestamp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowBegin <em>Row Begin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Begin</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowBegin()
	 * @see #getDB2Column()
	 * @generated
	 */
	EAttribute getDB2Column_RowBegin();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowEnd <em>Row End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row End</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isRowEnd()
	 * @see #getDB2Column()
	 * @generated
	 */
	EAttribute getDB2Column_RowEnd();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isTransStartID <em>Trans Start ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trans Start ID</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#isTransStartID()
	 * @see #getDB2Column()
	 * @generated
	 */
	EAttribute getDB2Column_TransStartID();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getBeginPeriod <em>Begin Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Begin Period</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getBeginPeriod()
	 * @see #getDB2Column()
	 * @generated
	 */
	EReference getDB2Column_BeginPeriod();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getEndPeriod <em>End Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>End Period</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Column#getEndPeriod()
	 * @see #getDB2Column()
	 * @generated
	 */
	EReference getDB2Column_EndPeriod();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject <em>DB2XSR Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2XSR Object</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject
	 * @generated
	 */
	EClass getDB2XSRObject();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject#getSchema()
	 * @see #getDB2XSRObject()
	 * @generated
	 */
	EReference getDB2XSRObject_Schema();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema <em>DB2XML Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2XML Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema
	 * @generated
	 */
	EClass getDB2XMLSchema();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getDecomposition <em>Decomposition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Decomposition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getDecomposition()
	 * @see #getDB2XMLSchema()
	 * @generated
	 */
	EAttribute getDB2XMLSchema_Decomposition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getStatus()
	 * @see #getDB2XMLSchema()
	 * @generated
	 */
	EAttribute getDB2XMLSchema_Status();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getXmlSchemaDocs <em>Xml Schema Docs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Xml Schema Docs</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema#getXmlSchemaDocs()
	 * @see #getDB2XMLSchema()
	 * @generated
	 */
	EReference getDB2XMLSchema_XmlSchemaDocs();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument <em>DB2XML Schema Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2XML Schema Document</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument
	 * @generated
	 */
	EClass getDB2XMLSchemaDocument();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getFileName()
	 * @see #getDB2XMLSchemaDocument()
	 * @generated
	 */
	EAttribute getDB2XMLSchemaDocument_FileName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getSchemaLocation <em>Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema Location</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getSchemaLocation()
	 * @see #getDB2XMLSchemaDocument()
	 * @generated
	 */
	EAttribute getDB2XMLSchemaDocument_SchemaLocation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getTargetNamespace <em>Target Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Namespace</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getTargetNamespace()
	 * @see #getDB2XMLSchemaDocument()
	 * @generated
	 */
	EAttribute getDB2XMLSchemaDocument_TargetNamespace();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#isPrimary <em>Primary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Primary</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#isPrimary()
	 * @see #getDB2XMLSchemaDocument()
	 * @generated
	 */
	EAttribute getDB2XMLSchemaDocument_Primary();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchema <em>Xml Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Xml Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchema()
	 * @see #getDB2XMLSchemaDocument()
	 * @generated
	 */
	EReference getDB2XMLSchemaDocument_XmlSchema();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchemaDocProperties <em>Xml Schema Doc Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Xml Schema Doc Properties</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument#getXmlSchemaDocProperties()
	 * @see #getDB2XMLSchemaDocument()
	 * @generated
	 */
	EReference getDB2XMLSchemaDocument_XmlSchemaDocProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties <em>DB2XML Schema Doc Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2XML Schema Doc Properties</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties
	 * @generated
	 */
	EClass getDB2XMLSchemaDocProperties();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getValue()
	 * @see #getDB2XMLSchemaDocProperties()
	 * @generated
	 */
	EAttribute getDB2XMLSchemaDocProperties_Value();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getXmlSchemaDoc <em>Xml Schema Doc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Xml Schema Doc</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties#getXmlSchemaDoc()
	 * @see #getDB2XMLSchemaDocProperties()
	 * @generated
	 */
	EReference getDB2XMLSchemaDocProperties_XmlSchemaDoc();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement <em>DB2 Package Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Package Statement</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement
	 * @generated
	 */
	EClass getDB2PackageStatement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getStatementNumber <em>Statement Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statement Number</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getStatementNumber()
	 * @see #getDB2PackageStatement()
	 * @generated
	 */
	EAttribute getDB2PackageStatement_StatementNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getSectionNumber <em>Section Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Section Number</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getSectionNumber()
	 * @see #getDB2PackageStatement()
	 * @generated
	 */
	EAttribute getDB2PackageStatement_SectionNumber();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Package</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getPackage()
	 * @see #getDB2PackageStatement()
	 * @generated
	 */
	EReference getDB2PackageStatement_Package();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getSqlStatement <em>Sql Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sql Statement</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement#getSqlStatement()
	 * @see #getDB2PackageStatement()
	 * @generated
	 */
	EReference getDB2PackageStatement_SqlStatement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2DistinctUserDefinedType <em>DB2 Distinct User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Distinct User Defined Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2DistinctUserDefinedType
	 * @generated
	 */
	EClass getDB2DistinctUserDefinedType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Period <em>DB2 Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Period</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Period
	 * @generated
	 */
	EClass getDB2Period();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getType()
	 * @see #getDB2Period()
	 * @generated
	 */
	EAttribute getDB2Period_Type();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getBeginColumn <em>Begin Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Begin Column</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getBeginColumn()
	 * @see #getDB2Period()
	 * @generated
	 */
	EReference getDB2Period_BeginColumn();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getEndColumn <em>End Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>End Column</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getEndColumn()
	 * @see #getDB2Period()
	 * @generated
	 */
	EReference getDB2Period_EndColumn();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Period#getTable()
	 * @see #getDB2Period()
	 * @generated
	 */
	EReference getDB2Period_Table();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster <em>DB2 Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Cluster</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster
	 * @generated
	 */
	EClass getDB2Cluster();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster#getLevel <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster#getLevel()
	 * @see #getDB2Cluster()
	 * @generated
	 */
	EAttribute getDB2Cluster_Level();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instance</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster#getInstance()
	 * @see #getDB2Cluster()
	 * @generated
	 */
	EReference getDB2Cluster_Instance();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster#getMembers <em>Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Members</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Cluster#getMembers()
	 * @see #getDB2Cluster()
	 * @generated
	 */
	EReference getDB2Cluster_Members();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Member <em>DB2 Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Member</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Member
	 * @generated
	 */
	EClass getDB2Member();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getId()
	 * @see #getDB2Member()
	 * @generated
	 */
	EAttribute getDB2Member_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getHomeHost <em>Home Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Home Host</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getHomeHost()
	 * @see #getDB2Member()
	 * @generated
	 */
	EAttribute getDB2Member_HomeHost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getCurrentHost <em>Current Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Current Host</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getCurrentHost()
	 * @see #getDB2Member()
	 * @generated
	 */
	EAttribute getDB2Member_CurrentHost();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getState()
	 * @see #getDB2Member()
	 * @generated
	 */
	EAttribute getDB2Member_State();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getCluster <em>Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Cluster</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Member#getCluster()
	 * @see #getDB2Member()
	 * @generated
	 */
	EReference getDB2Member_Cluster();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2UniqueConstraintExtension <em>DB2 Unique Constraint Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Unique Constraint Extension</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2UniqueConstraintExtension
	 * @generated
	 */
	EClass getDB2UniqueConstraintExtension();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2UniqueConstraintExtension#isBusPeriodWithoutOverlap <em>Bus Period Without Overlap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bus Period Without Overlap</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2UniqueConstraintExtension#isBusPeriodWithoutOverlap()
	 * @see #getDB2UniqueConstraintExtension()
	 * @generated
	 */
	EAttribute getDB2UniqueConstraintExtension_BusPeriodWithoutOverlap();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask <em>DB2 Mask</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Mask</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask
	 * @generated
	 */
	EClass getDB2Mask();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getCorrelationName <em>Correlation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Correlation Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getCorrelationName()
	 * @see #getDB2Mask()
	 * @generated
	 */
	EAttribute getDB2Mask_CorrelationName();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getCaseExpression <em>Case Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Case Expression</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getCaseExpression()
	 * @see #getDB2Mask()
	 * @generated
	 */
	EReference getDB2Mask_CaseExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#isEnable <em>Enable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enable</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#isEnable()
	 * @see #getDB2Mask()
	 * @generated
	 */
	EAttribute getDB2Mask_Enable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSchema()
	 * @see #getDB2Mask()
	 * @generated
	 */
	EReference getDB2Mask_Schema();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectTable <em>Subject Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subject Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectTable()
	 * @see #getDB2Mask()
	 * @generated
	 */
	EReference getDB2Mask_SubjectTable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectMQT <em>Subject MQT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subject MQT</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectMQT()
	 * @see #getDB2Mask()
	 * @generated
	 */
	EReference getDB2Mask_SubjectMQT();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectColumn <em>Subject Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subject Column</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask#getSubjectColumn()
	 * @see #getDB2Mask()
	 * @generated
	 */
	EReference getDB2Mask_SubjectColumn();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission <em>DB2 Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DB2 Permission</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission
	 * @generated
	 */
	EClass getDB2Permission();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getCorrelationName <em>Correlation Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Correlation Name</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getCorrelationName()
	 * @see #getDB2Permission()
	 * @generated
	 */
	EAttribute getDB2Permission_CorrelationName();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSearchCondition <em>Search Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Search Condition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSearchCondition()
	 * @see #getDB2Permission()
	 * @generated
	 */
	EReference getDB2Permission_SearchCondition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#isEnable <em>Enable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enable</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#isEnable()
	 * @see #getDB2Permission()
	 * @generated
	 */
	EAttribute getDB2Permission_Enable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSchema()
	 * @see #getDB2Permission()
	 * @generated
	 */
	EReference getDB2Permission_Schema();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSubjectTable <em>Subject Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subject Table</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSubjectTable()
	 * @see #getDB2Permission()
	 * @generated
	 */
	EReference getDB2Permission_SubjectTable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSubjectMQT <em>Subject MQT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subject MQT</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission#getSubjectMQT()
	 * @see #getDB2Permission()
	 * @generated
	 */
	EReference getDB2Permission_SubjectMQT();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType <em>Isolation Level Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Isolation Level Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType
	 * @generated
	 */
	EEnum getIsolationLevelType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType <em>DB2 Index Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DB2 Index Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType
	 * @generated
	 */
	EEnum getDB2IndexType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType <em>Data Capture Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Data Capture Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType
	 * @generated
	 */
	EEnum getDataCaptureType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.UnitType <em>Unit Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unit Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
	 * @generated
	 */
	EEnum getUnitType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.GenerateType <em>Generate Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Generate Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.GenerateType
	 * @generated
	 */
	EEnum getGenerateType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition <em>DB2XML Schema Decomposition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DB2XML Schema Decomposition</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition
	 * @generated
	 */
	EEnum getDB2XMLSchemaDecomposition();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus <em>DB2XML Schema Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DB2XML Schema Status</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus
	 * @generated
	 */
	EEnum getDB2XMLSchemaStatus();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.OriginType <em>Origin Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Origin Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.OriginType
	 * @generated
	 */
	EEnum getOriginType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.ReoptType <em>Reopt Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Reopt Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.ReoptType
	 * @generated
	 */
	EEnum getReoptType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect <em>Source Dialect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Source Dialect</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect
	 * @generated
	 */
	EEnum getSourceDialect();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PeriodType <em>DB2 Period Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DB2 Period Type</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2PeriodType
	 * @generated
	 */
	EEnum getDB2PeriodType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization <em>DB2 Table Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DB2 Table Organization</em>'.
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization
	 * @generated
	 */
	EEnum getDB2TableOrganization();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DB2ModelFactory getDB2ModelFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseImpl <em>DB2 Database</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Database()
		 * @generated
		 */
		EClass DB2_DATABASE = eINSTANCE.getDB2Database();

		/**
		 * The meta object literal for the '<em><b>Partitioned</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_DATABASE__PARTITIONED = eINSTANCE.getDB2Database_Partitioned();

		/**
		 * The meta object literal for the '<em><b>Default Organize By Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_DATABASE__DEFAULT_ORGANIZE_BY_ROW = eINSTANCE.getDB2Database_DefaultOrganizeByRow();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl <em>DB2 Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Package()
		 * @generated
		 */
		EClass DB2_PACKAGE = eINSTANCE.getDB2Package();

		/**
		 * The meta object literal for the '<em><b>Operative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE__OPERATIVE = eINSTANCE.getDB2Package_Operative();

		/**
		 * The meta object literal for the '<em><b>Valid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE__VALID = eINSTANCE.getDB2Package_Valid();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE__VERSION = eINSTANCE.getDB2Package_Version();

		/**
		 * The meta object literal for the '<em><b>Default Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE__DEFAULT_SCHEMA = eINSTANCE.getDB2Package_DefaultSchema();

		/**
		 * The meta object literal for the '<em><b>Sql Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE__SQL_PATH = eINSTANCE.getDB2Package_SqlPath();

		/**
		 * The meta object literal for the '<em><b>Reopt Var</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE__REOPT_VAR = eINSTANCE.getDB2Package_ReoptVar();

		/**
		 * The meta object literal for the '<em><b>Isolation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE__ISOLATION = eINSTANCE.getDB2Package_Isolation();

		/**
		 * The meta object literal for the '<em><b>Unique ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE__UNIQUE_ID = eINSTANCE.getDB2Package_UniqueID();

		/**
		 * The meta object literal for the '<em><b>Last Bind TS</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE__LAST_BIND_TS = eINSTANCE.getDB2Package_LastBindTS();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PACKAGE__SCHEMA = eINSTANCE.getDB2Package_Schema();

		/**
		 * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PACKAGE__STATEMENTS = eINSTANCE.getDB2Package_Statements();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl <em>DB2 Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TableImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Table()
		 * @generated
		 */
		EClass DB2_TABLE = eINSTANCE.getDB2Table();

		/**
		 * The meta object literal for the '<em><b>Data Capture</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_TABLE__DATA_CAPTURE = eINSTANCE.getDB2Table_DataCapture();

		/**
		 * The meta object literal for the '<em><b>Activate Row Access Control</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_TABLE__ACTIVATE_ROW_ACCESS_CONTROL = eINSTANCE.getDB2Table_ActivateRowAccessControl();

		/**
		 * The meta object literal for the '<em><b>Activate Column Access Control</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL = eINSTANCE.getDB2Table_ActivateColumnAccessControl();

		/**
		 * The meta object literal for the '<em><b>Organize By</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_TABLE__ORGANIZE_BY = eINSTANCE.getDB2Table_OrganizeBy();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_TABLE__PACKAGES = eINSTANCE.getDB2Table_Packages();

		/**
		 * The meta object literal for the '<em><b>Periods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_TABLE__PERIODS = eINSTANCE.getDB2Table_Periods();

		/**
		 * The meta object literal for the '<em><b>History Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_TABLE__HISTORY_TABLE = eINSTANCE.getDB2Table_HistoryTable();

		/**
		 * The meta object literal for the '<em><b>Temporal Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_TABLE__TEMPORAL_TABLE = eINSTANCE.getDB2Table_TemporalTable();

		/**
		 * The meta object literal for the '<em><b>Masks</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_TABLE__MASKS = eINSTANCE.getDB2Table_Masks();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_TABLE__PERMISSIONS = eINSTANCE.getDB2Table_Permissions();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TriggerImpl <em>DB2 Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TriggerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Trigger()
		 * @generated
		 */
		EClass DB2_TRIGGER = eINSTANCE.getDB2Trigger();

		/**
		 * The meta object literal for the '<em><b>Operative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_TRIGGER__OPERATIVE = eINSTANCE.getDB2Trigger_Operative();

		/**
		 * The meta object literal for the '<em><b>Secured</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_TRIGGER__SECURED = eINSTANCE.getDB2Trigger_Secured();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl <em>DB2 Procedure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Procedure()
		 * @generated
		 */
		EClass DB2_PROCEDURE = eINSTANCE.getDB2Procedure();

		/**
		 * The meta object literal for the '<em><b>Model Result Sets</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PROCEDURE__MODEL_RESULT_SETS = eINSTANCE.getDB2Procedure_ModelResultSets();

		/**
		 * The meta object literal for the '<em><b>Null Input</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PROCEDURE__NULL_INPUT = eINSTANCE.getDB2Procedure_NullInput();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PROCEDURE__VERSION = eINSTANCE.getDB2Procedure_Version();

		/**
		 * The meta object literal for the '<em><b>Dialect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PROCEDURE__DIALECT = eINSTANCE.getDB2Procedure_Dialect();

		/**
		 * The meta object literal for the '<em><b>External Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PROCEDURE__EXTERNAL_ACTION = eINSTANCE.getDB2Procedure_ExternalAction();

		/**
		 * The meta object literal for the '<em><b>Return</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PROCEDURE__RETURN = eINSTANCE.getDB2Procedure_Return();

		/**
		 * The meta object literal for the '<em><b>Java Options</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PROCEDURE__JAVA_OPTIONS = eINSTANCE.getDB2Procedure_JavaOptions();

		/**
		 * The meta object literal for the '<em><b>Deploy</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PROCEDURE__DEPLOY = eINSTANCE.getDB2Procedure_Deploy();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl <em>DB2 Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Schema()
		 * @generated
		 */
		EClass DB2_SCHEMA = eINSTANCE.getDB2Schema();

		/**
		 * The meta object literal for the '<em><b>Access Plans</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SCHEMA__ACCESS_PLANS = eINSTANCE.getDB2Schema_AccessPlans();

		/**
		 * The meta object literal for the '<em><b>Olap Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SCHEMA__OLAP_OBJECTS = eINSTANCE.getDB2Schema_OlapObjects();

		/**
		 * The meta object literal for the '<em><b>Jars</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SCHEMA__JARS = eINSTANCE.getDB2Schema_Jars();

		/**
		 * The meta object literal for the '<em><b>Xsr Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SCHEMA__XSR_OBJECTS = eINSTANCE.getDB2Schema_XsrObjects();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SCHEMA__PACKAGES = eINSTANCE.getDB2Schema_Packages();

		/**
		 * The meta object literal for the '<em><b>Masks</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SCHEMA__MASKS = eINSTANCE.getDB2Schema_Masks();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SCHEMA__PERMISSIONS = eINSTANCE.getDB2Schema_Permissions();

		/**
		 * The meta object literal for the '<em><b>Modules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SCHEMA__MODULES = eINSTANCE.getDB2Schema_Modules();

		/**
		 * The meta object literal for the '<em><b>Global Variables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SCHEMA__GLOBAL_VARIABLES = eINSTANCE.getDB2Schema_GlobalVariables();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine <em>DB2 Routine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Routine()
		 * @generated
		 */
		EClass DB2_ROUTINE = eINSTANCE.getDB2Routine();

		/**
		 * The meta object literal for the '<em><b>Fenced</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__FENCED = eINSTANCE.getDB2Routine_Fenced();

		/**
		 * The meta object literal for the '<em><b>Threadsafe</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__THREADSAFE = eINSTANCE.getDB2Routine_Threadsafe();

		/**
		 * The meta object literal for the '<em><b>Db Info</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__DB_INFO = eINSTANCE.getDB2Routine_DbInfo();

		/**
		 * The meta object literal for the '<em><b>Implicit Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__IMPLICIT_SCHEMA = eINSTANCE.getDB2Routine_ImplicitSchema();

		/**
		 * The meta object literal for the '<em><b>Federated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__FEDERATED = eINSTANCE.getDB2Routine_Federated();

		/**
		 * The meta object literal for the '<em><b>Parm Ccsid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__PARM_CCSID = eINSTANCE.getDB2Routine_ParmCcsid();

		/**
		 * The meta object literal for the '<em><b>Special Register</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__SPECIAL_REGISTER = eINSTANCE.getDB2Routine_SpecialRegister();

		/**
		 * The meta object literal for the '<em><b>Change State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__CHANGE_STATE = eINSTANCE.getDB2Routine_ChangeState();

		/**
		 * The meta object literal for the '<em><b>Debug Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__DEBUG_ID = eINSTANCE.getDB2Routine_DebugId();

		/**
		 * The meta object literal for the '<em><b>Program Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__PROGRAM_TYPE = eINSTANCE.getDB2Routine_ProgramType();

		/**
		 * The meta object literal for the '<em><b>Orig Schema Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__ORIG_SCHEMA_NAME = eINSTANCE.getDB2Routine_OrigSchemaName();

		/**
		 * The meta object literal for the '<em><b>Orig Parm Sig</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_ROUTINE__ORIG_PARM_SIG = eINSTANCE.getDB2Routine_OrigParmSig();

		/**
		 * The meta object literal for the '<em><b>Extended Options</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_ROUTINE__EXTENDED_OPTIONS = eINSTANCE.getDB2Routine_ExtendedOptions();

		/**
		 * The meta object literal for the '<em><b>Routine Extensions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_ROUTINE__ROUTINE_EXTENSIONS = eINSTANCE.getDB2Routine_RoutineExtensions();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseManagerImpl <em>DB2 Database Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DatabaseManagerImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2DatabaseManager()
		 * @generated
		 */
		EClass DB2_DATABASE_MANAGER = eINSTANCE.getDB2DatabaseManager();

		/**
		 * The meta object literal for the '<em><b>Databases</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_DATABASE_MANAGER__DATABASES = eINSTANCE.getDB2DatabaseManager_Databases();

		/**
		 * The meta object literal for the '<em><b>Db2 Process</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_DATABASE_MANAGER__DB2_PROCESS = eINSTANCE.getDB2DatabaseManager_Db2Process();

		/**
		 * The meta object literal for the '<em><b>Server</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_DATABASE_MANAGER__SERVER = eINSTANCE.getDB2DatabaseManager_Server();

		/**
		 * The meta object literal for the '<em><b>Cluster</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_DATABASE_MANAGER__CLUSTER = eINSTANCE.getDB2DatabaseManager_Cluster();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ViewImpl <em>DB2 View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ViewImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2View()
		 * @generated
		 */
		EClass DB2_VIEW = eINSTANCE.getDB2View();

		/**
		 * The meta object literal for the '<em><b>Operative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_VIEW__OPERATIVE = eINSTANCE.getDB2View_Operative();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ApplicationProcessImpl <em>DB2 Application Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ApplicationProcessImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2ApplicationProcess()
		 * @generated
		 */
		EClass DB2_APPLICATION_PROCESS = eINSTANCE.getDB2ApplicationProcess();

		/**
		 * The meta object literal for the '<em><b>Isolation Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_APPLICATION_PROCESS__ISOLATION_LEVEL = eINSTANCE.getDB2ApplicationProcess_IsolationLevel();

		/**
		 * The meta object literal for the '<em><b>Unit Of Work</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_APPLICATION_PROCESS__UNIT_OF_WORK = eINSTANCE.getDB2ApplicationProcess_UnitOfWork();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TransactionImpl <em>DB2 Transaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2TransactionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Transaction()
		 * @generated
		 */
		EClass DB2_TRANSACTION = eINSTANCE.getDB2Transaction();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SystemSchemaImpl <em>DB2 System Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SystemSchemaImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2SystemSchema()
		 * @generated
		 */
		EClass DB2_SYSTEM_SCHEMA = eINSTANCE.getDB2SystemSchema();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl <em>DB2 Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Source()
		 * @generated
		 */
		EClass DB2_SOURCE = eINSTANCE.getDB2Source();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_SOURCE__FILE_NAME = eINSTANCE.getDB2Source_FileName();

		/**
		 * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_SOURCE__PACKAGE_NAME = eINSTANCE.getDB2Source_PackageName();

		/**
		 * The meta object literal for the '<em><b>Db2 Package Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_SOURCE__DB2_PACKAGE_NAME = eINSTANCE.getDB2Source_Db2PackageName();

		/**
		 * The meta object literal for the '<em><b>Last Modified</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_SOURCE__LAST_MODIFIED = eINSTANCE.getDB2Source_LastModified();

		/**
		 * The meta object literal for the '<em><b>Supporting</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SOURCE__SUPPORTING = eINSTANCE.getDB2Source_Supporting();

		/**
		 * The meta object literal for the '<em><b>Primary</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_SOURCE__PRIMARY = eINSTANCE.getDB2Source_Primary();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan <em>DB2 Access Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2AccessPlan()
		 * @generated
		 */
		EClass DB2_ACCESS_PLAN = eINSTANCE.getDB2AccessPlan();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UserDefinedFunctionImpl <em>DB2 User Defined Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UserDefinedFunctionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2UserDefinedFunction()
		 * @generated
		 */
		EClass DB2_USER_DEFINED_FUNCTION = eINSTANCE.getDB2UserDefinedFunction();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl <em>DB2 Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Method()
		 * @generated
		 */
		EClass DB2_METHOD = eINSTANCE.getDB2Method();

		/**
		 * The meta object literal for the '<em><b>Returns Self As Result</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_METHOD__RETURNS_SELF_AS_RESULT = eINSTANCE.getDB2Method_ReturnsSelfAsResult();

		/**
		 * The meta object literal for the '<em><b>Implemented</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_METHOD__IMPLEMENTED = eINSTANCE.getDB2Method_Implemented();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl <em>DB2 Extended Options</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2ExtendedOptions()
		 * @generated
		 */
		EClass DB2_EXTENDED_OPTIONS = eINSTANCE.getDB2ExtendedOptions();

		/**
		 * The meta object literal for the '<em><b>Classpath Compile Jars</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_EXTENDED_OPTIONS__CLASSPATH_COMPILE_JARS = eINSTANCE.getDB2ExtendedOptions_ClasspathCompileJars();

		/**
		 * The meta object literal for the '<em><b>Pre Compile Opts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_EXTENDED_OPTIONS__PRE_COMPILE_OPTS = eINSTANCE.getDB2ExtendedOptions_PreCompileOpts();

		/**
		 * The meta object literal for the '<em><b>For Debug</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_EXTENDED_OPTIONS__FOR_DEBUG = eINSTANCE.getDB2ExtendedOptions_ForDebug();

		/**
		 * The meta object literal for the '<em><b>Built</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_EXTENDED_OPTIONS__BUILT = eINSTANCE.getDB2ExtendedOptions_Built();

		/**
		 * The meta object literal for the '<em><b>Compile Opts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_EXTENDED_OPTIONS__COMPILE_OPTS = eINSTANCE.getDB2ExtendedOptions_CompileOpts();

		/**
		 * The meta object literal for the '<em><b>Link Opts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_EXTENDED_OPTIONS__LINK_OPTS = eINSTANCE.getDB2ExtendedOptions_LinkOpts();

		/**
		 * The meta object literal for the '<em><b>Bind Opts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_EXTENDED_OPTIONS__BIND_OPTS = eINSTANCE.getDB2ExtendedOptions_BindOpts();

		/**
		 * The meta object literal for the '<em><b>Colid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_EXTENDED_OPTIONS__COLID = eINSTANCE.getDB2ExtendedOptions_Colid();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2AliasImpl <em>DB2 Alias</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2AliasImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Alias()
		 * @generated
		 */
		EClass DB2_ALIAS = eINSTANCE.getDB2Alias();

		/**
		 * The meta object literal for the '<em><b>Aliased Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_ALIAS__ALIASED_TABLE = eINSTANCE.getDB2Alias_AliasedTable();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl <em>DB2 Materialized Query Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaterializedQueryTableImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2MaterializedQueryTable()
		 * @generated
		 */
		EClass DB2_MATERIALIZED_QUERY_TABLE = eINSTANCE.getDB2MaterializedQueryTable();

		/**
		 * The meta object literal for the '<em><b>Refresh</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MATERIALIZED_QUERY_TABLE__REFRESH = eINSTANCE.getDB2MaterializedQueryTable_Refresh();

		/**
		 * The meta object literal for the '<em><b>Optimize Query</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MATERIALIZED_QUERY_TABLE__OPTIMIZE_QUERY = eINSTANCE.getDB2MaterializedQueryTable_OptimizeQuery();

		/**
		 * The meta object literal for the '<em><b>Maintained By</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MATERIALIZED_QUERY_TABLE__MAINTAINED_BY = eINSTANCE.getDB2MaterializedQueryTable_MaintainedBy();

		/**
		 * The meta object literal for the '<em><b>Activate Row Access Control</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_ROW_ACCESS_CONTROL = eINSTANCE.getDB2MaterializedQueryTable_ActivateRowAccessControl();

		/**
		 * The meta object literal for the '<em><b>Activate Column Access Control</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MATERIALIZED_QUERY_TABLE__ACTIVATE_COLUMN_ACCESS_CONTROL = eINSTANCE.getDB2MaterializedQueryTable_ActivateColumnAccessControl();

		/**
		 * The meta object literal for the '<em><b>Masks</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_MATERIALIZED_QUERY_TABLE__MASKS = eINSTANCE.getDB2MaterializedQueryTable_Masks();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_MATERIALIZED_QUERY_TABLE__PERMISSIONS = eINSTANCE.getDB2MaterializedQueryTable_Permissions();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IndexImpl <em>DB2 Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IndexImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Index()
		 * @generated
		 */
		EClass DB2_INDEX = eINSTANCE.getDB2Index();

		/**
		 * The meta object literal for the '<em><b>Index Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_INDEX__INDEX_TYPE = eINSTANCE.getDB2Index_IndexType();

		/**
		 * The meta object literal for the '<em><b>Bus Period Without Overlap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_INDEX__BUS_PERIOD_WITHOUT_OVERLAP = eINSTANCE.getDB2Index_BusPeriodWithoutOverlap();

		/**
		 * The meta object literal for the '<em><b>Encoded Vector</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_INDEX__ENCODED_VECTOR = eINSTANCE.getDB2Index_EncodedVector();

		/**
		 * The meta object literal for the '<em><b>DB2 Multidimensional Index</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_INDEX__DB2_MULTIDIMENSIONAL_INDEX = eINSTANCE.getDB2Index_DB2MultidimensionalIndex();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MultidimensionalIndexImpl <em>DB2 Multidimensional Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MultidimensionalIndexImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2MultidimensionalIndex()
		 * @generated
		 */
		EClass DB2_MULTIDIMENSIONAL_INDEX = eINSTANCE.getDB2MultidimensionalIndex();

		/**
		 * The meta object literal for the '<em><b>Dimension Indexes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_MULTIDIMENSIONAL_INDEX__DIMENSION_INDEXES = eINSTANCE.getDB2MultidimensionalIndex_DimensionIndexes();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2Function <em>DB2 Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2Function
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Function()
		 * @generated
		 */
		EClass DB2_FUNCTION = eINSTANCE.getDB2Function();

		/**
		 * The meta object literal for the '<em><b>Final Call</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__FINAL_CALL = eINSTANCE.getDB2Function_FinalCall();

		/**
		 * The meta object literal for the '<em><b>Scratch Pad</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__SCRATCH_PAD = eINSTANCE.getDB2Function_ScratchPad();

		/**
		 * The meta object literal for the '<em><b>Scratch Pad Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__SCRATCH_PAD_LENGTH = eINSTANCE.getDB2Function_ScratchPadLength();

		/**
		 * The meta object literal for the '<em><b>Function Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__FUNCTION_TYPE = eINSTANCE.getDB2Function_FunctionType();

		/**
		 * The meta object literal for the '<em><b>Predicate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__PREDICATE = eINSTANCE.getDB2Function_Predicate();

		/**
		 * The meta object literal for the '<em><b>External Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__EXTERNAL_ACTION = eINSTANCE.getDB2Function_ExternalAction();

		/**
		 * The meta object literal for the '<em><b>Cardinality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__CARDINALITY = eINSTANCE.getDB2Function_Cardinality();

		/**
		 * The meta object literal for the '<em><b>Allow Parallel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__ALLOW_PARALLEL = eINSTANCE.getDB2Function_AllowParallel();

		/**
		 * The meta object literal for the '<em><b>Return Clause</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__RETURN_CLAUSE = eINSTANCE.getDB2Function_ReturnClause();

		/**
		 * The meta object literal for the '<em><b>Origin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__ORIGIN = eINSTANCE.getDB2Function_Origin();

		/**
		 * The meta object literal for the '<em><b>Inherit Lock Request</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__INHERIT_LOCK_REQUEST = eINSTANCE.getDB2Function_InheritLockRequest();

		/**
		 * The meta object literal for the '<em><b>Dialect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__DIALECT = eINSTANCE.getDB2Function_Dialect();

		/**
		 * The meta object literal for the '<em><b>Inline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__INLINE = eINSTANCE.getDB2Function_Inline();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__VERSION = eINSTANCE.getDB2Function_Version();

		/**
		 * The meta object literal for the '<em><b>Secured</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_FUNCTION__SECURED = eINSTANCE.getDB2Function_Secured();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JavaOptionsImpl <em>DB2 Java Options</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JavaOptionsImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2JavaOptions()
		 * @generated
		 */
		EClass DB2_JAVA_OPTIONS = eINSTANCE.getDB2JavaOptions();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_JAVA_OPTIONS__CLASS_NAME = eINSTANCE.getDB2JavaOptions_ClassName();

		/**
		 * The meta object literal for the '<em><b>Method Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_JAVA_OPTIONS__METHOD_NAME = eINSTANCE.getDB2JavaOptions_MethodName();

		/**
		 * The meta object literal for the '<em><b>Sqlj</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_JAVA_OPTIONS__SQLJ = eINSTANCE.getDB2JavaOptions_Sqlj();

		/**
		 * The meta object literal for the '<em><b>Procedure</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_JAVA_OPTIONS__PROCEDURE = eINSTANCE.getDB2JavaOptions_Procedure();

		/**
		 * The meta object literal for the '<em><b>Jar</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_JAVA_OPTIONS__JAR = eINSTANCE.getDB2JavaOptions_Jar();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureDeployImpl <em>DB2 Procedure Deploy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureDeployImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2ProcedureDeploy()
		 * @generated
		 */
		EClass DB2_PROCEDURE_DEPLOY = eINSTANCE.getDB2ProcedureDeploy();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PROCEDURE_DEPLOY__FILE_NAME = eINSTANCE.getDB2ProcedureDeploy_FileName();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2OLAPObjectImpl <em>DB2OLAP Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2OLAPObjectImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2OLAPObject()
		 * @generated
		 */
		EClass DB2OLAP_OBJECT = eINSTANCE.getDB2OLAPObject();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2OLAP_OBJECT__SCHEMA = eINSTANCE.getDB2OLAPObject_Schema();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension <em>DB2 Routine Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2RoutineExtension()
		 * @generated
		 */
		EClass DB2_ROUTINE_EXTENSION = eINSTANCE.getDB2RoutineExtension();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IdentitySpecifierImpl <em>DB2 Identity Specifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IdentitySpecifierImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2IdentitySpecifier()
		 * @generated
		 */
		EClass DB2_IDENTITY_SPECIFIER = eINSTANCE.getDB2IdentitySpecifier();

		/**
		 * The meta object literal for the '<em><b>Cache</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_IDENTITY_SPECIFIER__CACHE = eINSTANCE.getDB2IdentitySpecifier_Cache();

		/**
		 * The meta object literal for the '<em><b>Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_IDENTITY_SPECIFIER__ORDER = eINSTANCE.getDB2IdentitySpecifier_Order();

		/**
		 * The meta object literal for the '<em><b>System Generated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_IDENTITY_SPECIFIER__SYSTEM_GENERATED = eINSTANCE.getDB2IdentitySpecifier_SystemGenerated();

		/**
		 * The meta object literal for the '<em><b>Restart Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_IDENTITY_SPECIFIER__RESTART_VALUE = eINSTANCE.getDB2IdentitySpecifier_RestartValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl <em>DB2 Jar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Jar()
		 * @generated
		 */
		EClass DB2_JAR = eINSTANCE.getDB2Jar();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_JAR__FILE_NAME = eINSTANCE.getDB2Jar_FileName();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_JAR__PATH = eINSTANCE.getDB2Jar_Path();

		/**
		 * The meta object literal for the '<em><b>Owner</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_JAR__OWNER = eINSTANCE.getDB2Jar_Owner();

		/**
		 * The meta object literal for the '<em><b>Created TS</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_JAR__CREATED_TS = eINSTANCE.getDB2Jar_CreatedTS();

		/**
		 * The meta object literal for the '<em><b>Altered TS</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_JAR__ALTERED_TS = eINSTANCE.getDB2Jar_AlteredTS();

		/**
		 * The meta object literal for the '<em><b>Procedures</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_JAR__PROCEDURES = eINSTANCE.getDB2Jar_Procedures();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_JAR__SCHEMA = eINSTANCE.getDB2Jar_Schema();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl <em>DB2 Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Column()
		 * @generated
		 */
		EClass DB2_COLUMN = eINSTANCE.getDB2Column();

		/**
		 * The meta object literal for the '<em><b>Generation Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_COLUMN__GENERATION_TYPE = eINSTANCE.getDB2Column_GenerationType();

		/**
		 * The meta object literal for the '<em><b>Row Change Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_COLUMN__ROW_CHANGE_TIMESTAMP = eINSTANCE.getDB2Column_RowChangeTimestamp();

		/**
		 * The meta object literal for the '<em><b>Row Begin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_COLUMN__ROW_BEGIN = eINSTANCE.getDB2Column_RowBegin();

		/**
		 * The meta object literal for the '<em><b>Row End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_COLUMN__ROW_END = eINSTANCE.getDB2Column_RowEnd();

		/**
		 * The meta object literal for the '<em><b>Trans Start ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_COLUMN__TRANS_START_ID = eINSTANCE.getDB2Column_TransStartID();

		/**
		 * The meta object literal for the '<em><b>Begin Period</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_COLUMN__BEGIN_PERIOD = eINSTANCE.getDB2Column_BeginPeriod();

		/**
		 * The meta object literal for the '<em><b>End Period</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_COLUMN__END_PERIOD = eINSTANCE.getDB2Column_EndPeriod();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XSRObjectImpl <em>DB2XSR Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XSRObjectImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XSRObject()
		 * @generated
		 */
		EClass DB2XSR_OBJECT = eINSTANCE.getDB2XSRObject();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2XSR_OBJECT__SCHEMA = eINSTANCE.getDB2XSRObject_Schema();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaImpl <em>DB2XML Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchema()
		 * @generated
		 */
		EClass DB2XML_SCHEMA = eINSTANCE.getDB2XMLSchema();

		/**
		 * The meta object literal for the '<em><b>Decomposition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2XML_SCHEMA__DECOMPOSITION = eINSTANCE.getDB2XMLSchema_Decomposition();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2XML_SCHEMA__STATUS = eINSTANCE.getDB2XMLSchema_Status();

		/**
		 * The meta object literal for the '<em><b>Xml Schema Docs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2XML_SCHEMA__XML_SCHEMA_DOCS = eINSTANCE.getDB2XMLSchema_XmlSchemaDocs();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl <em>DB2XML Schema Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchemaDocument()
		 * @generated
		 */
		EClass DB2XML_SCHEMA_DOCUMENT = eINSTANCE.getDB2XMLSchemaDocument();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2XML_SCHEMA_DOCUMENT__FILE_NAME = eINSTANCE.getDB2XMLSchemaDocument_FileName();

		/**
		 * The meta object literal for the '<em><b>Schema Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2XML_SCHEMA_DOCUMENT__SCHEMA_LOCATION = eINSTANCE.getDB2XMLSchemaDocument_SchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Target Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2XML_SCHEMA_DOCUMENT__TARGET_NAMESPACE = eINSTANCE.getDB2XMLSchemaDocument_TargetNamespace();

		/**
		 * The meta object literal for the '<em><b>Primary</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2XML_SCHEMA_DOCUMENT__PRIMARY = eINSTANCE.getDB2XMLSchemaDocument_Primary();

		/**
		 * The meta object literal for the '<em><b>Xml Schema</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA = eINSTANCE.getDB2XMLSchemaDocument_XmlSchema();

		/**
		 * The meta object literal for the '<em><b>Xml Schema Doc Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES = eINSTANCE.getDB2XMLSchemaDocument_XmlSchemaDocProperties();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocPropertiesImpl <em>DB2XML Schema Doc Properties</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocPropertiesImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchemaDocProperties()
		 * @generated
		 */
		EClass DB2XML_SCHEMA_DOC_PROPERTIES = eINSTANCE.getDB2XMLSchemaDocProperties();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2XML_SCHEMA_DOC_PROPERTIES__VALUE = eINSTANCE.getDB2XMLSchemaDocProperties_Value();

		/**
		 * The meta object literal for the '<em><b>Xml Schema Doc</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC = eINSTANCE.getDB2XMLSchemaDocProperties_XmlSchemaDoc();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageStatementImpl <em>DB2 Package Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageStatementImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2PackageStatement()
		 * @generated
		 */
		EClass DB2_PACKAGE_STATEMENT = eINSTANCE.getDB2PackageStatement();

		/**
		 * The meta object literal for the '<em><b>Statement Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE_STATEMENT__STATEMENT_NUMBER = eINSTANCE.getDB2PackageStatement_StatementNumber();

		/**
		 * The meta object literal for the '<em><b>Section Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PACKAGE_STATEMENT__SECTION_NUMBER = eINSTANCE.getDB2PackageStatement_SectionNumber();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PACKAGE_STATEMENT__PACKAGE = eINSTANCE.getDB2PackageStatement_Package();

		/**
		 * The meta object literal for the '<em><b>Sql Statement</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PACKAGE_STATEMENT__SQL_STATEMENT = eINSTANCE.getDB2PackageStatement_SqlStatement();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DistinctUserDefinedTypeImpl <em>DB2 Distinct User Defined Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2DistinctUserDefinedTypeImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2DistinctUserDefinedType()
		 * @generated
		 */
		EClass DB2_DISTINCT_USER_DEFINED_TYPE = eINSTANCE.getDB2DistinctUserDefinedType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PeriodImpl <em>DB2 Period</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PeriodImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Period()
		 * @generated
		 */
		EClass DB2_PERIOD = eINSTANCE.getDB2Period();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PERIOD__TYPE = eINSTANCE.getDB2Period_Type();

		/**
		 * The meta object literal for the '<em><b>Begin Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PERIOD__BEGIN_COLUMN = eINSTANCE.getDB2Period_BeginColumn();

		/**
		 * The meta object literal for the '<em><b>End Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PERIOD__END_COLUMN = eINSTANCE.getDB2Period_EndColumn();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PERIOD__TABLE = eINSTANCE.getDB2Period_Table();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ClusterImpl <em>DB2 Cluster</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ClusterImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Cluster()
		 * @generated
		 */
		EClass DB2_CLUSTER = eINSTANCE.getDB2Cluster();

		/**
		 * The meta object literal for the '<em><b>Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_CLUSTER__LEVEL = eINSTANCE.getDB2Cluster_Level();

		/**
		 * The meta object literal for the '<em><b>Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_CLUSTER__INSTANCE = eINSTANCE.getDB2Cluster_Instance();

		/**
		 * The meta object literal for the '<em><b>Members</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_CLUSTER__MEMBERS = eINSTANCE.getDB2Cluster_Members();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl <em>DB2 Member</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MemberImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Member()
		 * @generated
		 */
		EClass DB2_MEMBER = eINSTANCE.getDB2Member();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MEMBER__ID = eINSTANCE.getDB2Member_Id();

		/**
		 * The meta object literal for the '<em><b>Home Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MEMBER__HOME_HOST = eINSTANCE.getDB2Member_HomeHost();

		/**
		 * The meta object literal for the '<em><b>Current Host</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MEMBER__CURRENT_HOST = eINSTANCE.getDB2Member_CurrentHost();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MEMBER__STATE = eINSTANCE.getDB2Member_State();

		/**
		 * The meta object literal for the '<em><b>Cluster</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_MEMBER__CLUSTER = eINSTANCE.getDB2Member_Cluster();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UniqueConstraintExtensionImpl <em>DB2 Unique Constraint Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2UniqueConstraintExtensionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2UniqueConstraintExtension()
		 * @generated
		 */
		EClass DB2_UNIQUE_CONSTRAINT_EXTENSION = eINSTANCE.getDB2UniqueConstraintExtension();

		/**
		 * The meta object literal for the '<em><b>Bus Period Without Overlap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_UNIQUE_CONSTRAINT_EXTENSION__BUS_PERIOD_WITHOUT_OVERLAP = eINSTANCE.getDB2UniqueConstraintExtension_BusPeriodWithoutOverlap();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl <em>DB2 Mask</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MaskImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Mask()
		 * @generated
		 */
		EClass DB2_MASK = eINSTANCE.getDB2Mask();

		/**
		 * The meta object literal for the '<em><b>Correlation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MASK__CORRELATION_NAME = eINSTANCE.getDB2Mask_CorrelationName();

		/**
		 * The meta object literal for the '<em><b>Case Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_MASK__CASE_EXPRESSION = eINSTANCE.getDB2Mask_CaseExpression();

		/**
		 * The meta object literal for the '<em><b>Enable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_MASK__ENABLE = eINSTANCE.getDB2Mask_Enable();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_MASK__SCHEMA = eINSTANCE.getDB2Mask_Schema();

		/**
		 * The meta object literal for the '<em><b>Subject Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_MASK__SUBJECT_TABLE = eINSTANCE.getDB2Mask_SubjectTable();

		/**
		 * The meta object literal for the '<em><b>Subject MQT</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_MASK__SUBJECT_MQT = eINSTANCE.getDB2Mask_SubjectMQT();

		/**
		 * The meta object literal for the '<em><b>Subject Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_MASK__SUBJECT_COLUMN = eINSTANCE.getDB2Mask_SubjectColumn();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PermissionImpl <em>DB2 Permission</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PermissionImpl
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2Permission()
		 * @generated
		 */
		EClass DB2_PERMISSION = eINSTANCE.getDB2Permission();

		/**
		 * The meta object literal for the '<em><b>Correlation Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PERMISSION__CORRELATION_NAME = eINSTANCE.getDB2Permission_CorrelationName();

		/**
		 * The meta object literal for the '<em><b>Search Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PERMISSION__SEARCH_CONDITION = eINSTANCE.getDB2Permission_SearchCondition();

		/**
		 * The meta object literal for the '<em><b>Enable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DB2_PERMISSION__ENABLE = eINSTANCE.getDB2Permission_Enable();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PERMISSION__SCHEMA = eINSTANCE.getDB2Permission_Schema();

		/**
		 * The meta object literal for the '<em><b>Subject Table</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PERMISSION__SUBJECT_TABLE = eINSTANCE.getDB2Permission_SubjectTable();

		/**
		 * The meta object literal for the '<em><b>Subject MQT</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DB2_PERMISSION__SUBJECT_MQT = eINSTANCE.getDB2Permission_SubjectMQT();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType <em>Isolation Level Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getIsolationLevelType()
		 * @generated
		 */
		EEnum ISOLATION_LEVEL_TYPE = eINSTANCE.getIsolationLevelType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType <em>DB2 Index Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2IndexType
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2IndexType()
		 * @generated
		 */
		EEnum DB2_INDEX_TYPE = eINSTANCE.getDB2IndexType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType <em>Data Capture Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DataCaptureType
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDataCaptureType()
		 * @generated
		 */
		EEnum DATA_CAPTURE_TYPE = eINSTANCE.getDataCaptureType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.UnitType <em>Unit Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.UnitType
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getUnitType()
		 * @generated
		 */
		EEnum UNIT_TYPE = eINSTANCE.getUnitType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.GenerateType <em>Generate Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.GenerateType
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getGenerateType()
		 * @generated
		 */
		EEnum GENERATE_TYPE = eINSTANCE.getGenerateType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition <em>DB2XML Schema Decomposition</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchemaDecomposition()
		 * @generated
		 */
		EEnum DB2XML_SCHEMA_DECOMPOSITION = eINSTANCE.getDB2XMLSchemaDecomposition();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus <em>DB2XML Schema Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2XMLSchemaStatus()
		 * @generated
		 */
		EEnum DB2XML_SCHEMA_STATUS = eINSTANCE.getDB2XMLSchemaStatus();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.OriginType <em>Origin Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.OriginType
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getOriginType()
		 * @generated
		 */
		EEnum ORIGIN_TYPE = eINSTANCE.getOriginType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.ReoptType <em>Reopt Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.ReoptType
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getReoptType()
		 * @generated
		 */
		EEnum REOPT_TYPE = eINSTANCE.getReoptType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect <em>Source Dialect</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getSourceDialect()
		 * @generated
		 */
		EEnum SOURCE_DIALECT = eINSTANCE.getSourceDialect();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2PeriodType <em>DB2 Period Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2PeriodType
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2PeriodType()
		 * @generated
		 */
		EEnum DB2_PERIOD_TYPE = eINSTANCE.getDB2PeriodType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization <em>DB2 Table Organization</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2TableOrganization
		 * @see org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ModelPackageImpl#getDB2TableOrganization()
		 * @generated
		 */
		EEnum DB2_TABLE_ORGANIZATION = eINSTANCE.getDB2TableOrganization();

	}

} //DB2ModelPackage
