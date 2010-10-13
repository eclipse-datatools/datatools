/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasesqlmodelPackage.java,v 1.1 2008/03/27 07:41:13 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

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
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelFactory
 * @model kind="package"
 * @generated
 */
public interface SybaseasesqlmodelPackage extends EPackage
{
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sybaseasesqlmodel"; //$NON-NLS-1$

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/connectivity/sqm/sybase/ase/sybaseasesqlmodel.ecore"; //$NON-NLS-1$

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SybaseASEModel"; //$NON-NLS-1$

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SybaseasesqlmodelPackage eINSTANCE = org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl.init();

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESchemaImpl <em>Sybase ASE Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESchemaImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASESchema()
	 * @generated
	 */
	int SYBASE_ASE_SCHEMA = 0;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__EANNOTATIONS = SQLSchemaPackage.SCHEMA__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__NAME = SQLSchemaPackage.SCHEMA__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__DEPENDENCIES = SQLSchemaPackage.SCHEMA__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__DESCRIPTION = SQLSchemaPackage.SCHEMA__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__LABEL = SQLSchemaPackage.SCHEMA__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__COMMENTS = SQLSchemaPackage.SCHEMA__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__EXTENSIONS = SQLSchemaPackage.SCHEMA__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__PRIVILEGES = SQLSchemaPackage.SCHEMA__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__TRIGGERS = SQLSchemaPackage.SCHEMA__TRIGGERS;

    /**
	 * The feature id for the '<em><b>Indices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__INDICES = SQLSchemaPackage.SCHEMA__INDICES;

    /**
	 * The feature id for the '<em><b>Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__TABLES = SQLSchemaPackage.SCHEMA__TABLES;

    /**
	 * The feature id for the '<em><b>Sequences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__SEQUENCES = SQLSchemaPackage.SCHEMA__SEQUENCES;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__DATABASE = SQLSchemaPackage.SCHEMA__DATABASE;

    /**
	 * The feature id for the '<em><b>Catalog</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__CATALOG = SQLSchemaPackage.SCHEMA__CATALOG;

    /**
	 * The feature id for the '<em><b>Assertions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__ASSERTIONS = SQLSchemaPackage.SCHEMA__ASSERTIONS;

    /**
	 * The feature id for the '<em><b>User Defined Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__USER_DEFINED_TYPES = SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES;

    /**
	 * The feature id for the '<em><b>Char Sets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__CHAR_SETS = SQLSchemaPackage.SCHEMA__CHAR_SETS;

    /**
	 * The feature id for the '<em><b>Routines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__ROUTINES = SQLSchemaPackage.SCHEMA__ROUTINES;

    /**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__OWNER = SQLSchemaPackage.SCHEMA__OWNER;

    /**
	 * The feature id for the '<em><b>Defaults</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__DEFAULTS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Rules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__RULES = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Encryption Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Sybase ASE Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SCHEMA_FEATURE_COUNT = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl <em>Sybase ASE Database</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEDatabase()
	 * @generated
	 */
	int SYBASE_ASE_DATABASE = 1;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__EANNOTATIONS = SQLSchemaPackage.DATABASE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__NAME = SQLSchemaPackage.DATABASE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__DEPENDENCIES = SQLSchemaPackage.DATABASE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__DESCRIPTION = SQLSchemaPackage.DATABASE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__LABEL = SQLSchemaPackage.DATABASE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__COMMENTS = SQLSchemaPackage.DATABASE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__EXTENSIONS = SQLSchemaPackage.DATABASE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__PRIVILEGES = SQLSchemaPackage.DATABASE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__VENDOR = SQLSchemaPackage.DATABASE__VENDOR;

    /**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__VERSION = SQLSchemaPackage.DATABASE__VERSION;

    /**
	 * The feature id for the '<em><b>Schemas</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__SCHEMAS = SQLSchemaPackage.DATABASE__SCHEMAS;

    /**
	 * The feature id for the '<em><b>Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__EVENTS = SQLSchemaPackage.DATABASE__EVENTS;

    /**
	 * The feature id for the '<em><b>Catalogs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__CATALOGS = SQLSchemaPackage.DATABASE__CATALOGS;

    /**
	 * The feature id for the '<em><b>Authorization Ids</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__AUTHORIZATION_IDS = SQLSchemaPackage.DATABASE__AUTHORIZATION_IDS;

    /**
	 * The feature id for the '<em><b>Data Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__DATA_TYPES = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Encryption Key Applicable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__ENCRYPTION_KEY_APPLICABLE = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__ROLES = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Caches</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__CACHES = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Web Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__WEB_SERVICES = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Webservice Applicable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__WEBSERVICE_APPLICABLE = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Sds Server</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_DATABASE__SDS_SERVER = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Temp DB Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE__TEMP_DB_NAME = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 7;

				/**
	 * The number of structural features of the '<em>Sybase ASE Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DATABASE_FEATURE_COUNT = SQLSchemaPackage.DATABASE_FEATURE_COUNT + 8;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl <em>Sybase ASE Web Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEWebService()
	 * @generated
	 */
	int SYBASE_ASE_WEB_SERVICE = 2;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Service id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__SERVICE_ID = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Service type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__SERVICE_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Auth required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__AUTH_REQUIRED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Secure required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__SECURE_REQUIRED = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Url path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__URL_PATH = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>User name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__USER_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__PARAMETER = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__STATEMENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Remarks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__REMARKS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Database</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

    /**
	 * The number of structural features of the '<em>Sybase ASE Web Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 10;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPredefinedDataTypeImpl <em>Sybase ASE Predefined Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPredefinedDataTypeImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEPredefinedDataType()
	 * @generated
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE = 3;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__EANNOTATIONS = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__NAME = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__DEPENDENCIES = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__DESCRIPTION = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__LABEL = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__COMMENTS = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__EXTENSIONS = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__PRIVILEGES = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Primitive Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE = SQLDataTypesPackage.PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE = SQLDataTypesPackage.PREDEFINED_DATA_TYPE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASE Predefined Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PREDEFINED_DATA_TYPE_FEATURE_COUNT = SQLDataTypesPackage.PREDEFINED_DATA_TYPE_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl <em>Sybase ASE Catalog</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASECatalog()
	 * @generated
	 */
	int SYBASE_ASE_CATALOG = 4;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__EANNOTATIONS = SQLSchemaPackage.CATALOG__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__NAME = SQLSchemaPackage.CATALOG__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__DEPENDENCIES = SQLSchemaPackage.CATALOG__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__DESCRIPTION = SQLSchemaPackage.CATALOG__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__LABEL = SQLSchemaPackage.CATALOG__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__COMMENTS = SQLSchemaPackage.CATALOG__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__EXTENSIONS = SQLSchemaPackage.CATALOG__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__PRIVILEGES = SQLSchemaPackage.CATALOG__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__DATABASE = SQLSchemaPackage.CATALOG__DATABASE;

    /**
	 * The feature id for the '<em><b>Schemas</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__SCHEMAS = SQLSchemaPackage.CATALOG__SCHEMAS;

    /**
	 * The feature id for the '<em><b>Segments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__SEGMENTS = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Data Devices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__DATA_DEVICES = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Log Devices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__LOG_DEVICES = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Override</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__OVERRIDE = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Default Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__DEFAULT_LOCATION = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>For Load</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__FOR_LOAD = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>For Proxy Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__FOR_PROXY_UPDATE = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Log IO Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__LOG_IO_SIZE = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Recovery Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__RECOVERY_ORDER = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Authorization Ids</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__AUTHORIZATION_IDS = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>Cache</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__CACHE = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 10;

    /**
	 * The feature id for the '<em><b>Catalog Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG__CATALOG_TYPE = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 11;

    /**
	 * The number of structural features of the '<em>Sybase ASE Catalog</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CATALOG_FEATURE_COUNT = SQLSchemaPackage.CATALOG_FEATURE_COUNT + 12;


    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProcedureImpl <em>Sybase ASE Procedure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProcedureImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEProcedure()
	 * @generated
	 */
	int SYBASE_ASE_PROCEDURE = 5;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__EANNOTATIONS = SQLRoutinesPackage.PROCEDURE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__NAME = SQLRoutinesPackage.PROCEDURE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__DEPENDENCIES = SQLRoutinesPackage.PROCEDURE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__DESCRIPTION = SQLRoutinesPackage.PROCEDURE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__LABEL = SQLRoutinesPackage.PROCEDURE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__COMMENTS = SQLRoutinesPackage.PROCEDURE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__EXTENSIONS = SQLRoutinesPackage.PROCEDURE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PROCEDURE__PRIVILEGES = SQLRoutinesPackage.PROCEDURE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__SPECIFIC_NAME = SQLRoutinesPackage.PROCEDURE__SPECIFIC_NAME;

				/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__LANGUAGE = SQLRoutinesPackage.PROCEDURE__LANGUAGE;

				/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__PARAMETER_STYLE = SQLRoutinesPackage.PROCEDURE__PARAMETER_STYLE;

				/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__DETERMINISTIC = SQLRoutinesPackage.PROCEDURE__DETERMINISTIC;

				/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__SQL_DATA_ACCESS = SQLRoutinesPackage.PROCEDURE__SQL_DATA_ACCESS;

				/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__CREATION_TS = SQLRoutinesPackage.PROCEDURE__CREATION_TS;

				/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__LAST_ALTERED_TS = SQLRoutinesPackage.PROCEDURE__LAST_ALTERED_TS;

				/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__AUTHORIZATION_ID = SQLRoutinesPackage.PROCEDURE__AUTHORIZATION_ID;

				/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__SECURITY = SQLRoutinesPackage.PROCEDURE__SECURITY;

				/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__EXTERNAL_NAME = SQLRoutinesPackage.PROCEDURE__EXTERNAL_NAME;

				/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__PARAMETERS = SQLRoutinesPackage.PROCEDURE__PARAMETERS;

				/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__SOURCE = SQLRoutinesPackage.PROCEDURE__SOURCE;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__SCHEMA = SQLRoutinesPackage.PROCEDURE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Max Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__MAX_RESULT_SETS = SQLRoutinesPackage.PROCEDURE__MAX_RESULT_SETS;

				/**
	 * The feature id for the '<em><b>Old Save Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__OLD_SAVE_POINT = SQLRoutinesPackage.PROCEDURE__OLD_SAVE_POINT;

				/**
	 * The feature id for the '<em><b>Result Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__RESULT_SET = SQLRoutinesPackage.PROCEDURE__RESULT_SET;

    /**
	 * The feature id for the '<em><b>Group Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__GROUP_NUMBER = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Transaction Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__TRANSACTION_MODE = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>System Procedure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__SYSTEM_PROCEDURE = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>With Recompile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE__WITH_RECOMPILE = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>Sybase ASE Procedure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROCEDURE_FEATURE_COUNT = SQLRoutinesPackage.PROCEDURE_FEATURE_COUNT + 4;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDefaultImpl <em>Sybase ASE Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDefaultImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEDefault()
	 * @generated
	 */
	int SYBASE_ASE_DEFAULT = 6;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT__STATEMENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASE Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_DEFAULT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERuleImpl <em>Sybase ASE Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERuleImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASERule()
	 * @generated
	 */
	int SYBASE_ASE_RULE = 7;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__STATEMENT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Access Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__ACCESS_RULE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Access Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE__ACCESS_TYPE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>Sybase ASE Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_RULE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl <em>Sybase ASE Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEIndex()
	 * @generated
	 */
	int SYBASE_ASE_INDEX = 8;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__EANNOTATIONS = SQLConstraintsPackage.INDEX__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__NAME = SQLConstraintsPackage.INDEX__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__DEPENDENCIES = SQLConstraintsPackage.INDEX__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__DESCRIPTION = SQLConstraintsPackage.INDEX__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__LABEL = SQLConstraintsPackage.INDEX__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__COMMENTS = SQLConstraintsPackage.INDEX__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__EXTENSIONS = SQLConstraintsPackage.INDEX__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__PRIVILEGES = SQLConstraintsPackage.INDEX__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__SCHEMA = SQLConstraintsPackage.INDEX__SCHEMA;

    /**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__CLUSTERED = SQLConstraintsPackage.INDEX__CLUSTERED;

    /**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__FILL_FACTOR = SQLConstraintsPackage.INDEX__FILL_FACTOR;

    /**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__UNIQUE = SQLConstraintsPackage.INDEX__UNIQUE;

    /**
	 * The feature id for the '<em><b>System Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__SYSTEM_GENERATED = SQLConstraintsPackage.INDEX__SYSTEM_GENERATED;

    /**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__MEMBERS = SQLConstraintsPackage.INDEX__MEMBERS;

    /**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__TABLE = SQLConstraintsPackage.INDEX__TABLE;

    /**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__FOREIGN_KEY = SQLConstraintsPackage.INDEX__FOREIGN_KEY;

    /**
	 * The feature id for the '<em><b>Included Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__INCLUDED_MEMBERS = SQLConstraintsPackage.INDEX__INCLUDED_MEMBERS;

    /**
	 * The feature id for the '<em><b>Max Row Per Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Reverse Page Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__REVERSE_PAGE_GAP = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Ignore Duplicate Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Sorted Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__SORTED_DATA = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Ignore Duplicate Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__SEGMENT = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Local Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__LOCAL_INDEX = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Partitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__PARTITIONS = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Consumer Num</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__CONSUMER_NUM = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Statistics Step</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__STATISTICS_STEP = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>Allow Duplicate Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 10;

    /**
	 * The feature id for the '<em><b>Suspect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__SUSPECT = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 11;

    /**
	 * The feature id for the '<em><b>Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX__CACHE_INFO = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 12;

    /**
	 * The number of structural features of the '<em>Sybase ASE Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_INDEX_FEATURE_COUNT = SQLConstraintsPackage.INDEX_FEATURE_COUNT + 13;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESegmentImpl <em>Sybase ASE Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESegmentImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASESegment()
	 * @generated
	 */
	int SYBASE_ASE_SEGMENT = 9;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Catalog</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__CATALOG = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Device Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__DEVICE_NAMES = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Thresholds</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT__THRESHOLDS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Sybase ASE Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_SEGMENT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEFuncBasedIndexMemberImpl <em>Sybase ASE Func Based Index Member</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEFuncBasedIndexMemberImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEFuncBasedIndexMember()
	 * @generated
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER = 10;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__EANNOTATIONS = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__NAME = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__DEPENDENCIES = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__DESCRIPTION = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__LABEL = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__COMMENTS = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__EXTENSIONS = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__PRIVILEGES = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Increment Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__INCREMENT_TYPE = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__INCREMENT_TYPE;

    /**
	 * The feature id for the '<em><b>Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__COLUMN = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__COLUMN;

    /**
	 * The feature id for the '<em><b>Column Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER__COLUMN_EXPRESSION = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER__COLUMN_EXPRESSION;

    /**
	 * The number of structural features of the '<em>Sybase ASE Func Based Index Member</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_FUNC_BASED_INDEX_MEMBER_FEATURE_COUNT = SybasesqlmodelPackage.SYBASE_INDEX_MEMBER_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl <em>Sybase ASE Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASETable()
	 * @generated
	 */
	int SYBASE_ASE_TABLE = 11;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__EANNOTATIONS = SQLTablesPackage.PERSISTENT_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__NAME = SQLTablesPackage.PERSISTENT_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__DEPENDENCIES = SQLTablesPackage.PERSISTENT_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__DESCRIPTION = SQLTablesPackage.PERSISTENT_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__LABEL = SQLTablesPackage.PERSISTENT_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__COMMENTS = SQLTablesPackage.PERSISTENT_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__EXTENSIONS = SQLTablesPackage.PERSISTENT_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TABLE__PRIVILEGES = SQLTablesPackage.PERSISTENT_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__COLUMNS = SQLTablesPackage.PERSISTENT_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__SUPERTABLE = SQLTablesPackage.PERSISTENT_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__SUBTABLES = SQLTablesPackage.PERSISTENT_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__SCHEMA = SQLTablesPackage.PERSISTENT_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__UDT = SQLTablesPackage.PERSISTENT_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__TRIGGERS = SQLTablesPackage.PERSISTENT_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__INDEX = SQLTablesPackage.PERSISTENT_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.PERSISTENT_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__INSERTABLE = SQLTablesPackage.PERSISTENT_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__UPDATABLE = SQLTablesPackage.PERSISTENT_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__CONSTRAINTS = SQLTablesPackage.PERSISTENT_TABLE__CONSTRAINTS;

				/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__REFERENCING_FOREIGN_KEYS = SQLTablesPackage.PERSISTENT_TABLE__REFERENCING_FOREIGN_KEYS;

    /**
	 * The feature id for the '<em><b>Lock Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__LOCK_SCHEMA = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__FILL_FACTOR = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Max Row Per Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Exp Row Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__EXP_ROW_SIZE = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Reserve Page Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__RESERVE_PAGE_GAP = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Identity Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__IDENTITY_GAP = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__SEGMENT = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Concurrency Opt Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Partition Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__PARTITION_CONDITION = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Table Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>Text Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 10;

    /**
	 * The feature id for the '<em><b>Lock Promotion</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__LOCK_PROMOTION = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 11;

    /**
	 * The feature id for the '<em><b>Partitions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__PARTITIONS = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 12;

    /**
	 * The feature id for the '<em><b>Text Image Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 13;

    /**
	 * The feature id for the '<em><b>System Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE__SYSTEM_TABLE = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 14;

    /**
	 * The number of structural features of the '<em>Sybase ASE Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TABLE_FEATURE_COUNT = SQLTablesPackage.PERSISTENT_TABLE_FEATURE_COUNT + 15;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECheckConstraintImpl <em>Sybase ASE Check Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECheckConstraintImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASECheckConstraint()
	 * @generated
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT = 33;

				/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__EANNOTATIONS = SQLConstraintsPackage.CHECK_CONSTRAINT__EANNOTATIONS;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__NAME = SQLConstraintsPackage.CHECK_CONSTRAINT__NAME;

				/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__DEPENDENCIES = SQLConstraintsPackage.CHECK_CONSTRAINT__DEPENDENCIES;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__DESCRIPTION = SQLConstraintsPackage.CHECK_CONSTRAINT__DESCRIPTION;

				/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__LABEL = SQLConstraintsPackage.CHECK_CONSTRAINT__LABEL;

				/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__COMMENTS = SQLConstraintsPackage.CHECK_CONSTRAINT__COMMENTS;

				/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__EXTENSIONS = SQLConstraintsPackage.CHECK_CONSTRAINT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__PRIVILEGES = SQLConstraintsPackage.CHECK_CONSTRAINT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__DEFERRABLE = SQLConstraintsPackage.CHECK_CONSTRAINT__DEFERRABLE;

				/**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__INITIALLY_DEFERRED = SQLConstraintsPackage.CHECK_CONSTRAINT__INITIALLY_DEFERRED;

				/**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__ENFORCED = SQLConstraintsPackage.CHECK_CONSTRAINT__ENFORCED;

				/**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__BASE_TABLE = SQLConstraintsPackage.CHECK_CONSTRAINT__BASE_TABLE;

				/**
	 * The feature id for the '<em><b>Search Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__SEARCH_CONDITION = SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION;

				/**
	 * The feature id for the '<em><b>Creator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT__CREATOR = SQLConstraintsPackage.CHECK_CONSTRAINT_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>Sybase ASE Check Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CHECK_CONSTRAINT_FEATURE_COUNT = SQLConstraintsPackage.CHECK_CONSTRAINT_FEATURE_COUNT + 1;

				/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnCheckConstraintImpl <em>Sybase ASE Column Check Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnCheckConstraintImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEColumnCheckConstraint()
	 * @generated
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT = 12;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__EANNOTATIONS = SYBASE_ASE_CHECK_CONSTRAINT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__NAME = SYBASE_ASE_CHECK_CONSTRAINT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__DEPENDENCIES = SYBASE_ASE_CHECK_CONSTRAINT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__DESCRIPTION = SYBASE_ASE_CHECK_CONSTRAINT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__LABEL = SYBASE_ASE_CHECK_CONSTRAINT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COMMENTS = SYBASE_ASE_CHECK_CONSTRAINT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__EXTENSIONS = SYBASE_ASE_CHECK_CONSTRAINT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__PRIVILEGES = SYBASE_ASE_CHECK_CONSTRAINT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__DEFERRABLE = SYBASE_ASE_CHECK_CONSTRAINT__DEFERRABLE;

    /**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__INITIALLY_DEFERRED = SYBASE_ASE_CHECK_CONSTRAINT__INITIALLY_DEFERRED;

    /**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__ENFORCED = SYBASE_ASE_CHECK_CONSTRAINT__ENFORCED;

    /**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__BASE_TABLE = SYBASE_ASE_CHECK_CONSTRAINT__BASE_TABLE;

    /**
	 * The feature id for the '<em><b>Search Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__SEARCH_CONDITION = SYBASE_ASE_CHECK_CONSTRAINT__SEARCH_CONDITION;

    /**
	 * The feature id for the '<em><b>Creator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__CREATOR = SYBASE_ASE_CHECK_CONSTRAINT__CREATOR;

				/**
	 * The feature id for the '<em><b>Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN = SYBASE_ASE_CHECK_CONSTRAINT_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASE Column Check Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_CHECK_CONSTRAINT_FEATURE_COUNT = SYBASE_ASE_CHECK_CONSTRAINT_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl <em>Sybase ASE Column</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEColumn()
	 * @generated
	 */
	int SYBASE_ASE_COLUMN = 13;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__EANNOTATIONS = SQLTablesPackage.COLUMN__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__NAME = SQLTablesPackage.COLUMN__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__DEPENDENCIES = SQLTablesPackage.COLUMN__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__DESCRIPTION = SQLTablesPackage.COLUMN__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__LABEL = SQLTablesPackage.COLUMN__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__COMMENTS = SQLTablesPackage.COLUMN__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__EXTENSIONS = SQLTablesPackage.COLUMN__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__PRIVILEGES = SQLTablesPackage.COLUMN__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__CONTAINED_TYPE = SQLTablesPackage.COLUMN__CONTAINED_TYPE;

    /**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__REFERENCED_TYPE = SQLTablesPackage.COLUMN__REFERENCED_TYPE;

    /**
	 * The feature id for the '<em><b>Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__TABLE = SQLTablesPackage.COLUMN__TABLE;

    /**
	 * The feature id for the '<em><b>Identity Specifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__IDENTITY_SPECIFIER = SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER;

    /**
	 * The feature id for the '<em><b>Generate Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__GENERATE_EXPRESSION = SQLTablesPackage.COLUMN__GENERATE_EXPRESSION;

    /**
	 * The feature id for the '<em><b>Implementation Dependent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__IMPLEMENTATION_DEPENDENT = SQLTablesPackage.COLUMN__IMPLEMENTATION_DEPENDENT;

    /**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__NULLABLE = SQLTablesPackage.COLUMN__NULLABLE;

    /**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__DEFAULT_VALUE = SQLTablesPackage.COLUMN__DEFAULT_VALUE;

    /**
	 * The feature id for the '<em><b>Scope Check</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__SCOPE_CHECK = SQLTablesPackage.COLUMN__SCOPE_CHECK;

    /**
	 * The feature id for the '<em><b>Scope Checked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__SCOPE_CHECKED = SQLTablesPackage.COLUMN__SCOPE_CHECKED;

    /**
	 * The feature id for the '<em><b>Column Check</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__COLUMN_CHECK = SQLTablesPackage.COLUMN_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Bound Default</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__BOUND_DEFAULT = SQLTablesPackage.COLUMN_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Bound Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__BOUND_RULE = SQLTablesPackage.COLUMN_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Materialized</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__MATERIALIZED = SQLTablesPackage.COLUMN_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Encryption Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__ENCRYPTION_KEY = SQLTablesPackage.COLUMN_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Bind Default In Future Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__BIND_DEFAULT_IN_FUTURE_ONLY = SQLTablesPackage.COLUMN_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Bind Rule In Future Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN__BIND_RULE_IN_FUTURE_ONLY = SQLTablesPackage.COLUMN_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_COLUMN__HIDDEN = SQLTablesPackage.COLUMN_FEATURE_COUNT + 7;

    /**
	 * The number of structural features of the '<em>Sybase ASE Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_COLUMN_FEATURE_COUNT = SQLTablesPackage.COLUMN_FEATURE_COUNT + 8;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUniqueConstraintImpl <em>Sybase ASE Unique Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUniqueConstraintImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEUniqueConstraint()
	 * @generated
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT = 14;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__EANNOTATIONS = SQLConstraintsPackage.UNIQUE_CONSTRAINT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__NAME = SQLConstraintsPackage.UNIQUE_CONSTRAINT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__DEPENDENCIES = SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__DESCRIPTION = SQLConstraintsPackage.UNIQUE_CONSTRAINT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__LABEL = SQLConstraintsPackage.UNIQUE_CONSTRAINT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__COMMENTS = SQLConstraintsPackage.UNIQUE_CONSTRAINT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__EXTENSIONS = SQLConstraintsPackage.UNIQUE_CONSTRAINT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__PRIVILEGES = SQLConstraintsPackage.UNIQUE_CONSTRAINT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__DEFERRABLE = SQLConstraintsPackage.UNIQUE_CONSTRAINT__DEFERRABLE;

    /**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__INITIALLY_DEFERRED = SQLConstraintsPackage.UNIQUE_CONSTRAINT__INITIALLY_DEFERRED;

    /**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__ENFORCED = SQLConstraintsPackage.UNIQUE_CONSTRAINT__ENFORCED;

    /**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__BASE_TABLE = SQLConstraintsPackage.UNIQUE_CONSTRAINT__BASE_TABLE;

    /**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__MEMBERS = SQLConstraintsPackage.UNIQUE_CONSTRAINT__MEMBERS;

    /**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__FOREIGN_KEY = SQLConstraintsPackage.UNIQUE_CONSTRAINT__FOREIGN_KEY;

    /**
	 * The feature id for the '<em><b>System Gened Index</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX = SQLConstraintsPackage.UNIQUE_CONSTRAINT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>System Gened Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_NAME = SQLConstraintsPackage.UNIQUE_CONSTRAINT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASE Unique Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_UNIQUE_CONSTRAINT_FEATURE_COUNT = SQLConstraintsPackage.UNIQUE_CONSTRAINT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrimaryKeyImpl <em>Sybase ASE Primary Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrimaryKeyImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEPrimaryKey()
	 * @generated
	 */
	int SYBASE_ASE_PRIMARY_KEY = 15;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__EANNOTATIONS = SQLConstraintsPackage.PRIMARY_KEY__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__NAME = SQLConstraintsPackage.PRIMARY_KEY__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__DEPENDENCIES = SQLConstraintsPackage.PRIMARY_KEY__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__DESCRIPTION = SQLConstraintsPackage.PRIMARY_KEY__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__LABEL = SQLConstraintsPackage.PRIMARY_KEY__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__COMMENTS = SQLConstraintsPackage.PRIMARY_KEY__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__EXTENSIONS = SQLConstraintsPackage.PRIMARY_KEY__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__PRIVILEGES = SQLConstraintsPackage.PRIMARY_KEY__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__DEFERRABLE = SQLConstraintsPackage.PRIMARY_KEY__DEFERRABLE;

    /**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__INITIALLY_DEFERRED = SQLConstraintsPackage.PRIMARY_KEY__INITIALLY_DEFERRED;

    /**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__ENFORCED = SQLConstraintsPackage.PRIMARY_KEY__ENFORCED;

    /**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__BASE_TABLE = SQLConstraintsPackage.PRIMARY_KEY__BASE_TABLE;

    /**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__MEMBERS = SQLConstraintsPackage.PRIMARY_KEY__MEMBERS;

    /**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__FOREIGN_KEY = SQLConstraintsPackage.PRIMARY_KEY__FOREIGN_KEY;

    /**
	 * The feature id for the '<em><b>Ase Unique Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT = SQLConstraintsPackage.PRIMARY_KEY_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASE Primary Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIMARY_KEY_FEATURE_COUNT = SQLConstraintsPackage.PRIMARY_KEY_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.DeviceItemImpl <em>Device Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.DeviceItemImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getDeviceItem()
	 * @generated
	 */
	int DEVICE_ITEM = 16;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEVICE_ITEM__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEVICE_ITEM__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEVICE_ITEM__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEVICE_ITEM__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEVICE_ITEM__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEVICE_ITEM__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE_ITEM__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEVICE_ITEM__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

    /**
	 * The feature id for the '<em><b>Device Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE_ITEM__DEVICE_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE_ITEM__SIZE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Device Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE_ITEM_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SegmentThresholdImpl <em>Segment Threshold</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SegmentThresholdImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSegmentThreshold()
	 * @generated
	 */
	int SEGMENT_THRESHOLD = 17;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SEGMENT_THRESHOLD__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SEGMENT_THRESHOLD__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SEGMENT_THRESHOLD__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SEGMENT_THRESHOLD__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SEGMENT_THRESHOLD__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SEGMENT_THRESHOLD__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_THRESHOLD__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SEGMENT_THRESHOLD__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

    /**
	 * The feature id for the '<em><b>Procedure Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_THRESHOLD__PROCEDURE_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Free Space</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_THRESHOLD__FREE_SPACE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Segment Threshold</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_THRESHOLD_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.CacheInfoImpl <em>Cache Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.CacheInfoImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getCacheInfo()
	 * @generated
	 */
	int CACHE_INFO = 18;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CACHE_INFO__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CACHE_INFO__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CACHE_INFO__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CACHE_INFO__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CACHE_INFO__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CACHE_INFO__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_INFO__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CACHE_INFO__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

    /**
	 * The feature id for the '<em><b>Cache Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_INFO__CACHE_STRATEGY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Cache</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_INFO__CACHE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Cache Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_INFO_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl <em>Sybase ASE User Defined Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEUserDefinedType()
	 * @generated
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE = 19;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__EANNOTATIONS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__NAME = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__DEPENDENCIES = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__DESCRIPTION = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__LABEL = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__COMMENTS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__EXTENSIONS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__PRIVILEGES = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__SCHEMA = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__SCHEMA;

    /**
	 * The feature id for the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__ORDERING = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__ORDERING;

    /**
	 * The feature id for the '<em><b>Predefined Representation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION;

    /**
	 * The feature id for the '<em><b>Bound Default</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Bound Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Bind Default In Future Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__BIND_DEFAULT_IN_FUTURE_ONLY = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Bind Rule In Future Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__BIND_RULE_IN_FUTURE_ONLY = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Allow Nulls</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__ALLOW_NULLS = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Identity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE__IDENTITY = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 5;

    /**
	 * The number of structural features of the '<em>Sybase ASE User Defined Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER_DEFINED_TYPE_FEATURE_COUNT = SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE_FEATURE_COUNT + 6;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEEncryptionKeyImpl <em>Sybase ASE Encryption Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEEncryptionKeyImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEEncryptionKey()
	 * @generated
	 */
	int SYBASE_ASE_ENCRYPTION_KEY = 20;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASE Encryption Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ENCRYPTION_KEY_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.LockPromotionInfoImpl <em>Lock Promotion Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.LockPromotionInfoImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getLockPromotionInfo()
	 * @generated
	 */
	int LOCK_PROMOTION_INFO = 21;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOCK_PROMOTION_INFO__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOCK_PROMOTION_INFO__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOCK_PROMOTION_INFO__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOCK_PROMOTION_INFO__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOCK_PROMOTION_INFO__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOCK_PROMOTION_INFO__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK_PROMOTION_INFO__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LOCK_PROMOTION_INFO__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

    /**
	 * The feature id for the '<em><b>Row Lock Promotion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK_PROMOTION_INFO__ROW_LOCK_PROMOTION = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>LWM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK_PROMOTION_INFO__LWM = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>HWM</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK_PROMOTION_INFO__HWM = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>PCT</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK_PROMOTION_INFO__PCT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>Lock Promotion Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCK_PROMOTION_INFO_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERoleImpl <em>Sybase ASE Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERoleImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASERole()
	 * @generated
	 */
	int SYBASE_ASE_ROLE = 22;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__EANNOTATIONS = SQLAccessControlPackage.ROLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__NAME = SQLAccessControlPackage.ROLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__DEPENDENCIES = SQLAccessControlPackage.ROLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__DESCRIPTION = SQLAccessControlPackage.ROLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__LABEL = SQLAccessControlPackage.ROLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__COMMENTS = SQLAccessControlPackage.ROLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__EXTENSIONS = SQLAccessControlPackage.ROLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__PRIVILEGES = SQLAccessControlPackage.ROLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__OWNED_SCHEMA = SQLAccessControlPackage.ROLE__OWNED_SCHEMA;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__DATABASE = SQLAccessControlPackage.ROLE__DATABASE;

				/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__RECEIVED_ROLE_AUTHORIZATION = SQLAccessControlPackage.ROLE__RECEIVED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__GRANTED_ROLE_AUTHORIZATION = SQLAccessControlPackage.ROLE__GRANTED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__GRANTED_PRIVILEGE = SQLAccessControlPackage.ROLE__GRANTED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__RECEIVED_PRIVILEGE = SQLAccessControlPackage.ROLE__RECEIVED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE__ROLE_AUTHORIZATION = SQLAccessControlPackage.ROLE__ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Sql Container</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_ROLE__SQL_CONTAINER = SQLAccessControlPackage.ROLE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASE Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_ROLE_FEATURE_COUNT = SQLAccessControlPackage.ROLE_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECacheImpl <em>Sybase ASE Cache</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECacheImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASECache()
	 * @generated
	 */
	int SYBASE_ASE_CACHE = 23;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Database</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE__DATABASE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASE Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_CACHE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEViewTableImpl <em>Sybase ASE View Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEViewTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEViewTable()
	 * @generated
	 */
	int SYBASE_ASE_VIEW_TABLE = 24;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__EANNOTATIONS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__NAME = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__DEPENDENCIES = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__DESCRIPTION = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__LABEL = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__COMMENTS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__EXTENSIONS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_VIEW_TABLE__PRIVILEGES = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__COLUMNS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__SUPERTABLE = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__SUBTABLES = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__SCHEMA = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__UDT = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__TRIGGERS = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__INDEX = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__SELF_REF_COLUMN_GENERATION = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__INSERTABLE = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__UPDATABLE = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__QUERY_EXPRESSION = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__QUERY_EXPRESSION;

				/**
	 * The feature id for the '<em><b>Check Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__CHECK_TYPE = SybasesqlmodelPackage.SYBASE_VIEW_TABLE__CHECK_TYPE;

    /**
	 * The feature id for the '<em><b>With Check Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE__WITH_CHECK_OPTION = SybasesqlmodelPackage.SYBASE_VIEW_TABLE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASE View Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_VIEW_TABLE_FEATURE_COUNT = SybasesqlmodelPackage.SYBASE_VIEW_TABLE_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETempTableImpl <em>Sybase ASE Temp Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETempTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASETempTable()
	 * @generated
	 */
	int SYBASE_ASE_TEMP_TABLE = 25;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__EANNOTATIONS = SQLTablesPackage.TEMPORARY_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__NAME = SQLTablesPackage.TEMPORARY_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__DEPENDENCIES = SQLTablesPackage.TEMPORARY_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__DESCRIPTION = SQLTablesPackage.TEMPORARY_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__LABEL = SQLTablesPackage.TEMPORARY_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__COMMENTS = SQLTablesPackage.TEMPORARY_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__EXTENSIONS = SQLTablesPackage.TEMPORARY_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TEMP_TABLE__PRIVILEGES = SQLTablesPackage.TEMPORARY_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__COLUMNS = SQLTablesPackage.TEMPORARY_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__SUPERTABLE = SQLTablesPackage.TEMPORARY_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__SUBTABLES = SQLTablesPackage.TEMPORARY_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__SCHEMA = SQLTablesPackage.TEMPORARY_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__UDT = SQLTablesPackage.TEMPORARY_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__TRIGGERS = SQLTablesPackage.TEMPORARY_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__INDEX = SQLTablesPackage.TEMPORARY_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.TEMPORARY_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__INSERTABLE = SQLTablesPackage.TEMPORARY_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__UPDATABLE = SQLTablesPackage.TEMPORARY_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__CONSTRAINTS = SQLTablesPackage.TEMPORARY_TABLE__CONSTRAINTS;

				/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__REFERENCING_FOREIGN_KEYS = SQLTablesPackage.TEMPORARY_TABLE__REFERENCING_FOREIGN_KEYS;

				/**
	 * The feature id for the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__LOCAL = SQLTablesPackage.TEMPORARY_TABLE__LOCAL;

				/**
	 * The feature id for the '<em><b>Delete On Commit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__DELETE_ON_COMMIT = SQLTablesPackage.TEMPORARY_TABLE__DELETE_ON_COMMIT;

    /**
	 * The feature id for the '<em><b>Lock Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__LOCK_SCHEMA = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__FILL_FACTOR = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Max Row Per Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__MAX_ROW_PER_PAGE = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Exp Row Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__EXP_ROW_SIZE = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Reserve Page Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__RESERVE_PAGE_GAP = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Identity Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__IDENTITY_GAP = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__SEGMENT = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Concurrency Opt Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__CONCURRENCY_OPT_THRESHOLD = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Partition Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__PARTITION_CONDITION = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Table Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__TABLE_ONLY_CACHE_INFO = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>Text Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__TEXT_ONLY_CACHE_INFO = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 10;

    /**
	 * The feature id for the '<em><b>Lock Promotion</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__LOCK_PROMOTION = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 11;

    /**
	 * The feature id for the '<em><b>Partitions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__PARTITIONS = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 12;

    /**
	 * The feature id for the '<em><b>Text Image Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__TEXT_IMAGE_SEGMENT = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 13;

    /**
	 * The feature id for the '<em><b>System Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE__SYSTEM_TABLE = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 14;

    /**
	 * The number of structural features of the '<em>Sybase ASE Temp Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TEMP_TABLE_FEATURE_COUNT = SQLTablesPackage.TEMPORARY_TABLE_FEATURE_COUNT + 15;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProxyTableImpl <em>Sybase ASE Proxy Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProxyTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEProxyTable()
	 * @generated
	 */
	int SYBASE_ASE_PROXY_TABLE = 26;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__EANNOTATIONS = SYBASE_ASE_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__NAME = SYBASE_ASE_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__DEPENDENCIES = SYBASE_ASE_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__DESCRIPTION = SYBASE_ASE_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__LABEL = SYBASE_ASE_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__COMMENTS = SYBASE_ASE_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__EXTENSIONS = SYBASE_ASE_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PROXY_TABLE__PRIVILEGES = SYBASE_ASE_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__COLUMNS = SYBASE_ASE_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__SUPERTABLE = SYBASE_ASE_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__SUBTABLES = SYBASE_ASE_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__SCHEMA = SYBASE_ASE_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__UDT = SYBASE_ASE_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__TRIGGERS = SYBASE_ASE_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__INDEX = SYBASE_ASE_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__SELF_REF_COLUMN_GENERATION = SYBASE_ASE_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__INSERTABLE = SYBASE_ASE_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__UPDATABLE = SYBASE_ASE_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__CONSTRAINTS = SYBASE_ASE_TABLE__CONSTRAINTS;

				/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__REFERENCING_FOREIGN_KEYS = SYBASE_ASE_TABLE__REFERENCING_FOREIGN_KEYS;

    /**
	 * The feature id for the '<em><b>Lock Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__LOCK_SCHEMA = SYBASE_ASE_TABLE__LOCK_SCHEMA;

    /**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__FILL_FACTOR = SYBASE_ASE_TABLE__FILL_FACTOR;

    /**
	 * The feature id for the '<em><b>Max Row Per Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__MAX_ROW_PER_PAGE = SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE;

    /**
	 * The feature id for the '<em><b>Exp Row Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__EXP_ROW_SIZE = SYBASE_ASE_TABLE__EXP_ROW_SIZE;

    /**
	 * The feature id for the '<em><b>Reserve Page Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__RESERVE_PAGE_GAP = SYBASE_ASE_TABLE__RESERVE_PAGE_GAP;

    /**
	 * The feature id for the '<em><b>Identity Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__IDENTITY_GAP = SYBASE_ASE_TABLE__IDENTITY_GAP;

    /**
	 * The feature id for the '<em><b>Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__SEGMENT = SYBASE_ASE_TABLE__SEGMENT;

    /**
	 * The feature id for the '<em><b>Concurrency Opt Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__CONCURRENCY_OPT_THRESHOLD = SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD;

    /**
	 * The feature id for the '<em><b>Partition Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__PARTITION_CONDITION = SYBASE_ASE_TABLE__PARTITION_CONDITION;

    /**
	 * The feature id for the '<em><b>Table Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__TABLE_ONLY_CACHE_INFO = SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO;

    /**
	 * The feature id for the '<em><b>Text Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__TEXT_ONLY_CACHE_INFO = SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO;

    /**
	 * The feature id for the '<em><b>Lock Promotion</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__LOCK_PROMOTION = SYBASE_ASE_TABLE__LOCK_PROMOTION;

    /**
	 * The feature id for the '<em><b>Partitions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__PARTITIONS = SYBASE_ASE_TABLE__PARTITIONS;

    /**
	 * The feature id for the '<em><b>Text Image Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__TEXT_IMAGE_SEGMENT = SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT;

    /**
	 * The feature id for the '<em><b>System Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__SYSTEM_TABLE = SYBASE_ASE_TABLE__SYSTEM_TABLE;

    /**
	 * The feature id for the '<em><b>External Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__EXTERNAL_TYPE = SYBASE_ASE_TABLE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Existing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__EXISTING = SYBASE_ASE_TABLE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Column Delimiter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__COLUMN_DELIMITER = SYBASE_ASE_TABLE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>External Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE__EXTERNAL_PATH = SYBASE_ASE_TABLE_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>Sybase ASE Proxy Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PROXY_TABLE_FEATURE_COUNT = SYBASE_ASE_TABLE_FEATURE_COUNT + 4;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceTableImpl <em>Sybase ASE Web Service Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEWebServiceTable()
	 * @generated
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE = 27;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__EANNOTATIONS = SYBASE_ASE_PROXY_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__NAME = SYBASE_ASE_PROXY_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__DEPENDENCIES = SYBASE_ASE_PROXY_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__DESCRIPTION = SYBASE_ASE_PROXY_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__LABEL = SYBASE_ASE_PROXY_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__COMMENTS = SYBASE_ASE_PROXY_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__EXTENSIONS = SYBASE_ASE_PROXY_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_WEB_SERVICE_TABLE__PRIVILEGES = SYBASE_ASE_PROXY_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__COLUMNS = SYBASE_ASE_PROXY_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__SUPERTABLE = SYBASE_ASE_PROXY_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__SUBTABLES = SYBASE_ASE_PROXY_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__SCHEMA = SYBASE_ASE_PROXY_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__UDT = SYBASE_ASE_PROXY_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__TRIGGERS = SYBASE_ASE_PROXY_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__INDEX = SYBASE_ASE_PROXY_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__SELF_REF_COLUMN_GENERATION = SYBASE_ASE_PROXY_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__INSERTABLE = SYBASE_ASE_PROXY_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__UPDATABLE = SYBASE_ASE_PROXY_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__CONSTRAINTS = SYBASE_ASE_PROXY_TABLE__CONSTRAINTS;

				/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__REFERENCING_FOREIGN_KEYS = SYBASE_ASE_PROXY_TABLE__REFERENCING_FOREIGN_KEYS;

    /**
	 * The feature id for the '<em><b>Lock Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__LOCK_SCHEMA = SYBASE_ASE_PROXY_TABLE__LOCK_SCHEMA;

    /**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__FILL_FACTOR = SYBASE_ASE_PROXY_TABLE__FILL_FACTOR;

    /**
	 * The feature id for the '<em><b>Max Row Per Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__MAX_ROW_PER_PAGE = SYBASE_ASE_PROXY_TABLE__MAX_ROW_PER_PAGE;

    /**
	 * The feature id for the '<em><b>Exp Row Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__EXP_ROW_SIZE = SYBASE_ASE_PROXY_TABLE__EXP_ROW_SIZE;

    /**
	 * The feature id for the '<em><b>Reserve Page Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__RESERVE_PAGE_GAP = SYBASE_ASE_PROXY_TABLE__RESERVE_PAGE_GAP;

    /**
	 * The feature id for the '<em><b>Identity Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__IDENTITY_GAP = SYBASE_ASE_PROXY_TABLE__IDENTITY_GAP;

    /**
	 * The feature id for the '<em><b>Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__SEGMENT = SYBASE_ASE_PROXY_TABLE__SEGMENT;

    /**
	 * The feature id for the '<em><b>Concurrency Opt Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__CONCURRENCY_OPT_THRESHOLD = SYBASE_ASE_PROXY_TABLE__CONCURRENCY_OPT_THRESHOLD;

    /**
	 * The feature id for the '<em><b>Partition Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__PARTITION_CONDITION = SYBASE_ASE_PROXY_TABLE__PARTITION_CONDITION;

    /**
	 * The feature id for the '<em><b>Table Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__TABLE_ONLY_CACHE_INFO = SYBASE_ASE_PROXY_TABLE__TABLE_ONLY_CACHE_INFO;

    /**
	 * The feature id for the '<em><b>Text Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__TEXT_ONLY_CACHE_INFO = SYBASE_ASE_PROXY_TABLE__TEXT_ONLY_CACHE_INFO;

    /**
	 * The feature id for the '<em><b>Lock Promotion</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__LOCK_PROMOTION = SYBASE_ASE_PROXY_TABLE__LOCK_PROMOTION;

    /**
	 * The feature id for the '<em><b>Partitions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__PARTITIONS = SYBASE_ASE_PROXY_TABLE__PARTITIONS;

    /**
	 * The feature id for the '<em><b>Text Image Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__TEXT_IMAGE_SEGMENT = SYBASE_ASE_PROXY_TABLE__TEXT_IMAGE_SEGMENT;

    /**
	 * The feature id for the '<em><b>System Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__SYSTEM_TABLE = SYBASE_ASE_PROXY_TABLE__SYSTEM_TABLE;

    /**
	 * The feature id for the '<em><b>External Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__EXTERNAL_TYPE = SYBASE_ASE_PROXY_TABLE__EXTERNAL_TYPE;

    /**
	 * The feature id for the '<em><b>Existing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__EXISTING = SYBASE_ASE_PROXY_TABLE__EXISTING;

    /**
	 * The feature id for the '<em><b>Column Delimiter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__COLUMN_DELIMITER = SYBASE_ASE_PROXY_TABLE__COLUMN_DELIMITER;

    /**
	 * The feature id for the '<em><b>External Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__EXTERNAL_PATH = SYBASE_ASE_PROXY_TABLE__EXTERNAL_PATH;

    /**
	 * The feature id for the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE__METHOD = SYBASE_ASE_PROXY_TABLE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>WSDLURI</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_WEB_SERVICE_TABLE__WSDLURI = SYBASE_ASE_PROXY_TABLE_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASE Web Service Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_WEB_SERVICE_TABLE_FEATURE_COUNT = SYBASE_ASE_PROXY_TABLE_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEBaseTableImpl <em>Sybase ASE Base Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEBaseTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEBaseTable()
	 * @generated
	 */
	int SYBASE_ASE_BASE_TABLE = 28;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__EANNOTATIONS = SybasesqlmodelPackage.SYBASE_BASE_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__NAME = SybasesqlmodelPackage.SYBASE_BASE_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__DEPENDENCIES = SybasesqlmodelPackage.SYBASE_BASE_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__DESCRIPTION = SybasesqlmodelPackage.SYBASE_BASE_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__LABEL = SybasesqlmodelPackage.SYBASE_BASE_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__COMMENTS = SybasesqlmodelPackage.SYBASE_BASE_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__EXTENSIONS = SybasesqlmodelPackage.SYBASE_BASE_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__PRIVILEGES = SybasesqlmodelPackage.SYBASE_BASE_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__COLUMNS = SybasesqlmodelPackage.SYBASE_BASE_TABLE__COLUMNS;

				/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__SUPERTABLE = SybasesqlmodelPackage.SYBASE_BASE_TABLE__SUPERTABLE;

				/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__SUBTABLES = SybasesqlmodelPackage.SYBASE_BASE_TABLE__SUBTABLES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__SCHEMA = SybasesqlmodelPackage.SYBASE_BASE_TABLE__SCHEMA;

				/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__UDT = SybasesqlmodelPackage.SYBASE_BASE_TABLE__UDT;

				/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__TRIGGERS = SybasesqlmodelPackage.SYBASE_BASE_TABLE__TRIGGERS;

				/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__INDEX = SybasesqlmodelPackage.SYBASE_BASE_TABLE__INDEX;

				/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__SELF_REF_COLUMN_GENERATION = SybasesqlmodelPackage.SYBASE_BASE_TABLE__SELF_REF_COLUMN_GENERATION;

				/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__INSERTABLE = SybasesqlmodelPackage.SYBASE_BASE_TABLE__INSERTABLE;

				/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__UPDATABLE = SybasesqlmodelPackage.SYBASE_BASE_TABLE__UPDATABLE;

				/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__CONSTRAINTS = SybasesqlmodelPackage.SYBASE_BASE_TABLE__CONSTRAINTS;

				/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_BASE_TABLE__REFERENCING_FOREIGN_KEYS = SybasesqlmodelPackage.SYBASE_BASE_TABLE__REFERENCING_FOREIGN_KEYS;

    /**
	 * The feature id for the '<em><b>Lock Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__LOCK_SCHEMA = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__FILL_FACTOR = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Max Row Per Page</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__MAX_ROW_PER_PAGE = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Exp Row Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__EXP_ROW_SIZE = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Reserve Page Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__RESERVE_PAGE_GAP = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Identity Gap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__IDENTITY_GAP = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__SEGMENT = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Concurrency Opt Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__CONCURRENCY_OPT_THRESHOLD = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Partition Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__PARTITION_CONDITION = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Table Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__TABLE_ONLY_CACHE_INFO = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>Text Only Cache Info</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__TEXT_ONLY_CACHE_INFO = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 10;

    /**
	 * The feature id for the '<em><b>Lock Promotion</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__LOCK_PROMOTION = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 11;

    /**
	 * The feature id for the '<em><b>Partitions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__PARTITIONS = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 12;

    /**
	 * The feature id for the '<em><b>Text Image Segment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__TEXT_IMAGE_SEGMENT = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 13;

    /**
	 * The feature id for the '<em><b>System Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE__SYSTEM_TABLE = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 14;

    /**
	 * The number of structural features of the '<em>Sybase ASE Base Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_BASE_TABLE_FEATURE_COUNT = SybasesqlmodelPackage.SYBASE_BASE_TABLE_FEATURE_COUNT + 15;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserImpl <em>Sybase ASE User</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEUser()
	 * @generated
	 */
    int SYBASE_ASE_USER = 29;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__EANNOTATIONS = SQLAccessControlPackage.USER__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__NAME = SQLAccessControlPackage.USER__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__DEPENDENCIES = SQLAccessControlPackage.USER__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__DESCRIPTION = SQLAccessControlPackage.USER__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__LABEL = SQLAccessControlPackage.USER__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__COMMENTS = SQLAccessControlPackage.USER__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER__EXTENSIONS = SQLAccessControlPackage.USER__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER__PRIVILEGES = SQLAccessControlPackage.USER__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__OWNED_SCHEMA = SQLAccessControlPackage.USER__OWNED_SCHEMA;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER__DATABASE = SQLAccessControlPackage.USER__DATABASE;

				/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__RECEIVED_ROLE_AUTHORIZATION = SQLAccessControlPackage.USER__RECEIVED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__GRANTED_ROLE_AUTHORIZATION = SQLAccessControlPackage.USER__GRANTED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__GRANTED_PRIVILEGE = SQLAccessControlPackage.USER__GRANTED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__RECEIVED_PRIVILEGE = SQLAccessControlPackage.USER__RECEIVED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>Group</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_USER__GROUP = SQLAccessControlPackage.USER__GROUP;

				/**
	 * The feature id for the '<em><b>Sql Container</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__SQL_CONTAINER = SQLAccessControlPackage.USER_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Login Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER__LOGIN_NAME = SQLAccessControlPackage.USER_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASE User</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_USER_FEATURE_COUNT = SQLAccessControlPackage.USER_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEGroupImpl <em>Sybase ASE Group</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEGroupImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEGroup()
	 * @generated
	 */
    int SYBASE_ASE_GROUP = 30;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__EANNOTATIONS = SQLAccessControlPackage.GROUP__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__NAME = SQLAccessControlPackage.GROUP__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__DEPENDENCIES = SQLAccessControlPackage.GROUP__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__DESCRIPTION = SQLAccessControlPackage.GROUP__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__LABEL = SQLAccessControlPackage.GROUP__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__COMMENTS = SQLAccessControlPackage.GROUP__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_GROUP__EXTENSIONS = SQLAccessControlPackage.GROUP__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_GROUP__PRIVILEGES = SQLAccessControlPackage.GROUP__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__OWNED_SCHEMA = SQLAccessControlPackage.GROUP__OWNED_SCHEMA;

    /**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_GROUP__DATABASE = SQLAccessControlPackage.GROUP__DATABASE;

				/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__RECEIVED_ROLE_AUTHORIZATION = SQLAccessControlPackage.GROUP__RECEIVED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__GRANTED_ROLE_AUTHORIZATION = SQLAccessControlPackage.GROUP__GRANTED_ROLE_AUTHORIZATION;

    /**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__GRANTED_PRIVILEGE = SQLAccessControlPackage.GROUP__GRANTED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__RECEIVED_PRIVILEGE = SQLAccessControlPackage.GROUP__RECEIVED_PRIVILEGE;

    /**
	 * The feature id for the '<em><b>User</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__USER = SQLAccessControlPackage.GROUP__USER;

    /**
	 * The feature id for the '<em><b>Sql Container</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP__SQL_CONTAINER = SQLAccessControlPackage.GROUP_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASE Group</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_GROUP_FEATURE_COUNT = SQLAccessControlPackage.GROUP_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrivilegeImpl <em>Sybase ASE Privilege</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrivilegeImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEPrivilege()
	 * @generated
	 */
    int SYBASE_ASE_PRIVILEGE = 31;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__EANNOTATIONS = SybasesqlmodelPackage.SYBASE_PRIVILEGE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__NAME = SybasesqlmodelPackage.SYBASE_PRIVILEGE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__DEPENDENCIES = SybasesqlmodelPackage.SYBASE_PRIVILEGE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__DESCRIPTION = SybasesqlmodelPackage.SYBASE_PRIVILEGE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__LABEL = SybasesqlmodelPackage.SYBASE_PRIVILEGE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__COMMENTS = SybasesqlmodelPackage.SYBASE_PRIVILEGE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIVILEGE__EXTENSIONS = SybasesqlmodelPackage.SYBASE_PRIVILEGE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIVILEGE__PRIVILEGES = SybasesqlmodelPackage.SYBASE_PRIVILEGE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Grantable</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__GRANTABLE = SybasesqlmodelPackage.SYBASE_PRIVILEGE__GRANTABLE;

    /**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__ACTION = SybasesqlmodelPackage.SYBASE_PRIVILEGE__ACTION;

    /**
	 * The feature id for the '<em><b>With Hierarchy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIVILEGE__WITH_HIERARCHY = SybasesqlmodelPackage.SYBASE_PRIVILEGE__WITH_HIERARCHY;

				/**
	 * The feature id for the '<em><b>Grantor</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__GRANTOR = SybasesqlmodelPackage.SYBASE_PRIVILEGE__GRANTOR;

    /**
	 * The feature id for the '<em><b>Grantee</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__GRANTEE = SybasesqlmodelPackage.SYBASE_PRIVILEGE__GRANTEE;

    /**
	 * The feature id for the '<em><b>Action Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_PRIVILEGE__ACTION_OBJECTS = SybasesqlmodelPackage.SYBASE_PRIVILEGE__ACTION_OBJECTS;

				/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__OBJECT = SybasesqlmodelPackage.SYBASE_PRIVILEGE__OBJECT;

				/**
	 * The feature id for the '<em><b>Revoked</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE__REVOKED = SybasesqlmodelPackage.SYBASE_PRIVILEGE__REVOKED;

    /**
	 * The number of structural features of the '<em>Sybase ASE Privilege</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_PRIVILEGE_FEATURE_COUNT = SybasesqlmodelPackage.SYBASE_PRIVILEGE_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETriggerImpl <em>Sybase ASE Trigger</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETriggerImpl
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASETrigger()
	 * @generated
	 */
    int SYBASE_ASE_TRIGGER = 32;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__EANNOTATIONS = SQLTablesPackage.TRIGGER__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__NAME = SQLTablesPackage.TRIGGER__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__DEPENDENCIES = SQLTablesPackage.TRIGGER__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__DESCRIPTION = SQLTablesPackage.TRIGGER__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__LABEL = SQLTablesPackage.TRIGGER__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__COMMENTS = SQLTablesPackage.TRIGGER__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TRIGGER__EXTENSIONS = SQLTablesPackage.TRIGGER__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASE_TRIGGER__PRIVILEGES = SQLTablesPackage.TRIGGER__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__SCHEMA = SQLTablesPackage.TRIGGER__SCHEMA;

    /**
	 * The feature id for the '<em><b>Subject Table</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__SUBJECT_TABLE = SQLTablesPackage.TRIGGER__SUBJECT_TABLE;

    /**
	 * The feature id for the '<em><b>Action Statement</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__ACTION_STATEMENT = SQLTablesPackage.TRIGGER__ACTION_STATEMENT;

    /**
	 * The feature id for the '<em><b>Trigger Column</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__TRIGGER_COLUMN = SQLTablesPackage.TRIGGER__TRIGGER_COLUMN;

    /**
	 * The feature id for the '<em><b>Action Granularity</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__ACTION_GRANULARITY = SQLTablesPackage.TRIGGER__ACTION_GRANULARITY;

    /**
	 * The feature id for the '<em><b>When</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__WHEN = SQLTablesPackage.TRIGGER__WHEN;

    /**
	 * The feature id for the '<em><b>Time Stamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__TIME_STAMP = SQLTablesPackage.TRIGGER__TIME_STAMP;

    /**
	 * The feature id for the '<em><b>Action Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__ACTION_TIME = SQLTablesPackage.TRIGGER__ACTION_TIME;

    /**
	 * The feature id for the '<em><b>Update Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__UPDATE_TYPE = SQLTablesPackage.TRIGGER__UPDATE_TYPE;

    /**
	 * The feature id for the '<em><b>Insert Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__INSERT_TYPE = SQLTablesPackage.TRIGGER__INSERT_TYPE;

    /**
	 * The feature id for the '<em><b>Delete Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__DELETE_TYPE = SQLTablesPackage.TRIGGER__DELETE_TYPE;

    /**
	 * The feature id for the '<em><b>Old Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__OLD_ROW = SQLTablesPackage.TRIGGER__OLD_ROW;

    /**
	 * The feature id for the '<em><b>New Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__NEW_ROW = SQLTablesPackage.TRIGGER__NEW_ROW;

    /**
	 * The feature id for the '<em><b>Old Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__OLD_TABLE = SQLTablesPackage.TRIGGER__OLD_TABLE;

    /**
	 * The feature id for the '<em><b>New Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__NEW_TABLE = SQLTablesPackage.TRIGGER__NEW_TABLE;

    /**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER__ENABLED = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASE Trigger</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SYBASE_ASE_TRIGGER_FEATURE_COUNT = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType <em>Transaction Mode Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getTransactionModeType()
	 * @generated
	 */
	int TRANSACTION_MODE_TYPE = 34;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType <em>Cache Strategy Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getCacheStrategyType()
	 * @generated
	 */
	int CACHE_STRATEGY_TYPE = 35;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType <em>Proxy Table External Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getProxyTableExternalType()
	 * @generated
	 */
	int PROXY_TABLE_EXTERNAL_TYPE = 37;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType <em>Access Rule Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getAccessRuleType()
	 * @generated
	 */
	int ACCESS_RULE_TYPE = 38;


    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType <em>Sybase ASE Catalog Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASECatalogType()
	 * @generated
	 */
	int SYBASE_ASE_CATALOG_TYPE = 39;


    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType <em>Locking Schema Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getLockingSchemaType()
	 * @generated
	 */
	int LOCKING_SCHEMA_TYPE = 36;


    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema <em>Sybase ASE Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Schema</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema
	 * @generated
	 */
	EClass getSybaseASESchema();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getDefaults <em>Defaults</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Defaults</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getDefaults()
	 * @see #getSybaseASESchema()
	 * @generated
	 */
	EReference getSybaseASESchema_Defaults();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Rules</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getRules()
	 * @see #getSybaseASESchema()
	 * @generated
	 */
	EReference getSybaseASESchema_Rules();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getEncryptionKeys <em>Encryption Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Encryption Keys</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema#getEncryptionKeys()
	 * @see #getSybaseASESchema()
	 * @generated
	 */
	EReference getSybaseASESchema_EncryptionKeys();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase <em>Sybase ASE Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Database</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase
	 * @generated
	 */
	EClass getSybaseASEDatabase();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getDataTypes <em>Data Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Types</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getDataTypes()
	 * @see #getSybaseASEDatabase()
	 * @generated
	 */
	EReference getSybaseASEDatabase_DataTypes();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#isEncryptionKeyApplicable <em>Encryption Key Applicable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Encryption Key Applicable</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#isEncryptionKeyApplicable()
	 * @see #getSybaseASEDatabase()
	 * @generated
	 */
	EAttribute getSybaseASEDatabase_EncryptionKeyApplicable();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Roles</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getRoles()
	 * @see #getSybaseASEDatabase()
	 * @generated
	 */
	EReference getSybaseASEDatabase_Roles();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getCaches <em>Caches</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Caches</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getCaches()
	 * @see #getSybaseASEDatabase()
	 * @generated
	 */
	EReference getSybaseASEDatabase_Caches();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getWebServices <em>Web Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Web Services</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getWebServices()
	 * @see #getSybaseASEDatabase()
	 * @generated
	 */
	EReference getSybaseASEDatabase_WebServices();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#isWebserviceApplicable <em>Webservice Applicable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Webservice Applicable</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#isWebserviceApplicable()
	 * @see #getSybaseASEDatabase()
	 * @generated
	 */
	EAttribute getSybaseASEDatabase_WebserviceApplicable();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getSdsServer <em>Sds Server</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Sds Server</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getSdsServer()
	 * @see #getSybaseASEDatabase()
	 * @generated
	 */
    EAttribute getSybaseASEDatabase_SdsServer();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getTempDBName <em>Temp DB Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Temp DB Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase#getTempDBName()
	 * @see #getSybaseASEDatabase()
	 * @generated
	 */
	EAttribute getSybaseASEDatabase_TempDBName();

				/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService <em>Sybase ASE Web Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Web Service</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService
	 * @generated
	 */
	EClass getSybaseASEWebService();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getService_id <em>Service id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service id</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getService_id()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EAttribute getSybaseASEWebService_Service_id();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getService_type <em>Service type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getService_type()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EAttribute getSybaseASEWebService_Service_type();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getAuth_required <em>Auth required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Auth required</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getAuth_required()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EAttribute getSybaseASEWebService_Auth_required();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getSecure_required <em>Secure required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Secure required</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getSecure_required()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EAttribute getSybaseASEWebService_Secure_required();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getUrl_path <em>Url path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url path</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getUrl_path()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EAttribute getSybaseASEWebService_Url_path();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getUser_name <em>User name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getUser_name()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EAttribute getSybaseASEWebService_User_name();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getParameter()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EAttribute getSybaseASEWebService_Parameter();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getStatement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statement</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getStatement()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EAttribute getSybaseASEWebService_Statement();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getRemarks <em>Remarks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Remarks</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getRemarks()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EAttribute getSybaseASEWebService_Remarks();

    /**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService#getDatabase()
	 * @see #getSybaseASEWebService()
	 * @generated
	 */
	EReference getSybaseASEWebService_Database();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType <em>Sybase ASE Predefined Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Predefined Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType
	 * @generated
	 */
	EClass getSybaseASEPredefinedDataType();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType#getDatabase()
	 * @see #getSybaseASEPredefinedDataType()
	 * @generated
	 */
	EReference getSybaseASEPredefinedDataType_Database();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog <em>Sybase ASE Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Catalog</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog
	 * @generated
	 */
	EClass getSybaseASECatalog();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getSegments <em>Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Segments</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getSegments()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EReference getSybaseASECatalog_Segments();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getDataDevices <em>Data Devices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Devices</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getDataDevices()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EReference getSybaseASECatalog_DataDevices();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getLogDevices <em>Log Devices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Log Devices</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getLogDevices()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EReference getSybaseASECatalog_LogDevices();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isOverride <em>Override</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Override</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isOverride()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EAttribute getSybaseASECatalog_Override();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getDefaultLocation <em>Default Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Location</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getDefaultLocation()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EAttribute getSybaseASECatalog_DefaultLocation();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isForLoad <em>For Load</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>For Load</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isForLoad()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EAttribute getSybaseASECatalog_ForLoad();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isForProxyUpdate <em>For Proxy Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>For Proxy Update</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#isForProxyUpdate()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EAttribute getSybaseASECatalog_ForProxyUpdate();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getCache <em>Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Cache</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getCache()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EReference getSybaseASECatalog_Cache();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getCatalogType <em>Catalog Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Catalog Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getCatalogType()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EAttribute getSybaseASECatalog_CatalogType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getLogIOSize <em>Log IO Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Log IO Size</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getLogIOSize()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EAttribute getSybaseASECatalog_LogIOSize();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getRecoveryOrder <em>Recovery Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recovery Order</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getRecoveryOrder()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EAttribute getSybaseASECatalog_RecoveryOrder();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getAuthorizationIds <em>Authorization Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Authorization Ids</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog#getAuthorizationIds()
	 * @see #getSybaseASECatalog()
	 * @generated
	 */
	EReference getSybaseASECatalog_AuthorizationIds();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure <em>Sybase ASE Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure
	 * @generated
	 */
	EClass getSybaseASEProcedure();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#getGroupNumber <em>Group Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group Number</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#getGroupNumber()
	 * @see #getSybaseASEProcedure()
	 * @generated
	 */
	EAttribute getSybaseASEProcedure_GroupNumber();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#getTransactionMode <em>Transaction Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transaction Mode</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#getTransactionMode()
	 * @see #getSybaseASEProcedure()
	 * @generated
	 */
	EAttribute getSybaseASEProcedure_TransactionMode();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#isSystemProcedure <em>System Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Procedure</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#isSystemProcedure()
	 * @see #getSybaseASEProcedure()
	 * @generated
	 */
	EAttribute getSybaseASEProcedure_SystemProcedure();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#isWithRecompile <em>With Recompile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>With Recompile</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure#isWithRecompile()
	 * @see #getSybaseASEProcedure()
	 * @generated
	 */
	EAttribute getSybaseASEProcedure_WithRecompile();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault <em>Sybase ASE Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Default</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault
	 * @generated
	 */
	EClass getSybaseASEDefault();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getSchema()
	 * @see #getSybaseASEDefault()
	 * @generated
	 */
	EReference getSybaseASEDefault_Schema();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getStatement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statement</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault#getStatement()
	 * @see #getSybaseASEDefault()
	 * @generated
	 */
	EAttribute getSybaseASEDefault_Statement();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule <em>Sybase ASE Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Rule</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule
	 * @generated
	 */
	EClass getSybaseASERule();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getSchema()
	 * @see #getSybaseASERule()
	 * @generated
	 */
	EReference getSybaseASERule_Schema();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getStatement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statement</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getStatement()
	 * @see #getSybaseASERule()
	 * @generated
	 */
	EAttribute getSybaseASERule_Statement();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#isAccessRule <em>Access Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Access Rule</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#isAccessRule()
	 * @see #getSybaseASERule()
	 * @generated
	 */
	EAttribute getSybaseASERule_AccessRule();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getAccessType <em>Access Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Access Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule#getAccessType()
	 * @see #getSybaseASERule()
	 * @generated
	 */
	EAttribute getSybaseASERule_AccessType();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex <em>Sybase ASE Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Index</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex
	 * @generated
	 */
	EClass getSybaseASEIndex();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getMaxRowPerPage <em>Max Row Per Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Row Per Page</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getMaxRowPerPage()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_MaxRowPerPage();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getReversePageGap <em>Reverse Page Gap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reverse Page Gap</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getReversePageGap()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_ReversePageGap();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isIgnoreDuplicateKey <em>Ignore Duplicate Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore Duplicate Key</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isIgnoreDuplicateKey()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_IgnoreDuplicateKey();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isSortedData <em>Sorted Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sorted Data</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isSortedData()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_SortedData();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isIgnoreDuplicateRow <em>Ignore Duplicate Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ignore Duplicate Row</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isIgnoreDuplicateRow()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_IgnoreDuplicateRow();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getSegment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Segment</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getSegment()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EReference getSybaseASEIndex_Segment();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isLocalIndex <em>Local Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local Index</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isLocalIndex()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_LocalIndex();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getPartitions <em>Partitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Partitions</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getPartitions()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EReference getSybaseASEIndex_Partitions();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getConsumerNum <em>Consumer Num</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Consumer Num</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getConsumerNum()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_ConsumerNum();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getStatisticsStep <em>Statistics Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Statistics Step</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getStatisticsStep()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_StatisticsStep();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isAllowDuplicateRow <em>Allow Duplicate Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Duplicate Row</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isAllowDuplicateRow()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_AllowDuplicateRow();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isSuspect <em>Suspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suspect</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isSuspect()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EAttribute getSybaseASEIndex_Suspect();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getCacheInfo <em>Cache Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cache Info</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getCacheInfo()
	 * @see #getSybaseASEIndex()
	 * @generated
	 */
	EReference getSybaseASEIndex_CacheInfo();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment <em>Sybase ASE Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Segment</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment
	 * @generated
	 */
	EClass getSybaseASESegment();

    /**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getCatalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Catalog</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getCatalog()
	 * @see #getSybaseASESegment()
	 * @generated
	 */
	EReference getSybaseASESegment_Catalog();

    /**
	 * Returns the meta object for the attribute list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getDeviceNames <em>Device Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Device Names</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getDeviceNames()
	 * @see #getSybaseASESegment()
	 * @generated
	 */
	EAttribute getSybaseASESegment_DeviceNames();

    /**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getThresholds <em>Thresholds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Thresholds</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment#getThresholds()
	 * @see #getSybaseASESegment()
	 * @generated
	 */
	EReference getSybaseASESegment_Thresholds();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEFuncBasedIndexMember <em>Sybase ASE Func Based Index Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Func Based Index Member</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEFuncBasedIndexMember
	 * @generated
	 */
	EClass getSybaseASEFuncBasedIndexMember();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable <em>Sybase ASE Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable
	 * @generated
	 */
	EClass getSybaseASETable();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint <em>Sybase ASE Column Check Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Column Check Constraint</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint
	 * @generated
	 */
	EClass getSybaseASEColumnCheckConstraint();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Column</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint#getColumn()
	 * @see #getSybaseASEColumnCheckConstraint()
	 * @generated
	 */
	EReference getSybaseASEColumnCheckConstraint_Column();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn <em>Sybase ASE Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Column</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn
	 * @generated
	 */
	EClass getSybaseASEColumn();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getColumnCheck <em>Column Check</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Column Check</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getColumnCheck()
	 * @see #getSybaseASEColumn()
	 * @generated
	 */
	EReference getSybaseASEColumn_ColumnCheck();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getBoundDefault <em>Bound Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Bound Default</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getBoundDefault()
	 * @see #getSybaseASEColumn()
	 * @generated
	 */
	EReference getSybaseASEColumn_BoundDefault();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getBoundRule <em>Bound Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Bound Rule</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getBoundRule()
	 * @see #getSybaseASEColumn()
	 * @generated
	 */
	EReference getSybaseASEColumn_BoundRule();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isMaterialized <em>Materialized</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Materialized</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isMaterialized()
	 * @see #getSybaseASEColumn()
	 * @generated
	 */
	EAttribute getSybaseASEColumn_Materialized();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getEncryptionKey <em>Encryption Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Encryption Key</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#getEncryptionKey()
	 * @see #getSybaseASEColumn()
	 * @generated
	 */
	EReference getSybaseASEColumn_EncryptionKey();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isBindDefaultInFutureOnly <em>Bind Default In Future Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bind Default In Future Only</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isBindDefaultInFutureOnly()
	 * @see #getSybaseASEColumn()
	 * @generated
	 */
	EAttribute getSybaseASEColumn_BindDefaultInFutureOnly();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isBindRuleInFutureOnly <em>Bind Rule In Future Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bind Rule In Future Only</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isBindRuleInFutureOnly()
	 * @see #getSybaseASEColumn()
	 * @generated
	 */
	EAttribute getSybaseASEColumn_BindRuleInFutureOnly();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn#isHidden()
	 * @see #getSybaseASEColumn()
	 * @generated
	 */
    EAttribute getSybaseASEColumn_Hidden();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint <em>Sybase ASE Unique Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Unique Constraint</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint
	 * @generated
	 */
	EClass getSybaseASEUniqueConstraint();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint#getSystemGenedIndex <em>System Gened Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>System Gened Index</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint#getSystemGenedIndex()
	 * @see #getSybaseASEUniqueConstraint()
	 * @generated
	 */
	EReference getSybaseASEUniqueConstraint_SystemGenedIndex();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint#isSystemGenedName <em>System Gened Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Gened Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint#isSystemGenedName()
	 * @see #getSybaseASEUniqueConstraint()
	 * @generated
	 */
	EAttribute getSybaseASEUniqueConstraint_SystemGenedName();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey <em>Sybase ASE Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Primary Key</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey
	 * @generated
	 */
	EClass getSybaseASEPrimaryKey();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey#getAseUniqueConstraint <em>Ase Unique Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ase Unique Constraint</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey#getAseUniqueConstraint()
	 * @see #getSybaseASEPrimaryKey()
	 * @generated
	 */
	EReference getSybaseASEPrimaryKey_AseUniqueConstraint();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem <em>Device Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Device Item</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem
	 * @generated
	 */
	EClass getDeviceItem();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem#getDeviceName <em>Device Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem#getDeviceName()
	 * @see #getDeviceItem()
	 * @generated
	 */
	EAttribute getDeviceItem_DeviceName();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem#getSize()
	 * @see #getDeviceItem()
	 * @generated
	 */
	EAttribute getDeviceItem_Size();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold <em>Segment Threshold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment Threshold</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold
	 * @generated
	 */
	EClass getSegmentThreshold();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold#getProcedureName <em>Procedure Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Procedure Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold#getProcedureName()
	 * @see #getSegmentThreshold()
	 * @generated
	 */
	EAttribute getSegmentThreshold_ProcedureName();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold#getFreeSpace <em>Free Space</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Free Space</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold#getFreeSpace()
	 * @see #getSegmentThreshold()
	 * @generated
	 */
	EAttribute getSegmentThreshold_FreeSpace();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo <em>Cache Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cache Info</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo
	 * @generated
	 */
	EClass getCacheInfo();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo#getCacheStrategy <em>Cache Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cache Strategy</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo#getCacheStrategy()
	 * @see #getCacheInfo()
	 * @generated
	 */
	EAttribute getCacheInfo_CacheStrategy();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo#getCache <em>Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Cache</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo#getCache()
	 * @see #getCacheInfo()
	 * @generated
	 */
	EReference getCacheInfo_Cache();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType <em>Sybase ASE User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE User Defined Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType
	 * @generated
	 */
	EClass getSybaseASEUserDefinedType();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#getBoundDefault <em>Bound Default</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Bound Default</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#getBoundDefault()
	 * @see #getSybaseASEUserDefinedType()
	 * @generated
	 */
    EReference getSybaseASEUserDefinedType_BoundDefault();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#getBoundRule <em>Bound Rule</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Bound Rule</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#getBoundRule()
	 * @see #getSybaseASEUserDefinedType()
	 * @generated
	 */
    EReference getSybaseASEUserDefinedType_BoundRule();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isBindDefaultInFutureOnly <em>Bind Default In Future Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bind Default In Future Only</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isBindDefaultInFutureOnly()
	 * @see #getSybaseASEUserDefinedType()
	 * @generated
	 */
	EAttribute getSybaseASEUserDefinedType_BindDefaultInFutureOnly();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isBindRuleInFutureOnly <em>Bind Rule In Future Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bind Rule In Future Only</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isBindRuleInFutureOnly()
	 * @see #getSybaseASEUserDefinedType()
	 * @generated
	 */
	EAttribute getSybaseASEUserDefinedType_BindRuleInFutureOnly();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isAllowNulls <em>Allow Nulls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allow Nulls</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isAllowNulls()
	 * @see #getSybaseASEUserDefinedType()
	 * @generated
	 */
	EAttribute getSybaseASEUserDefinedType_AllowNulls();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isIdentity <em>Identity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType#isIdentity()
	 * @see #getSybaseASEUserDefinedType()
	 * @generated
	 */
	EAttribute getSybaseASEUserDefinedType_Identity();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey <em>Sybase ASE Encryption Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Encryption Key</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey
	 * @generated
	 */
	EClass getSybaseASEEncryptionKey();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey#getSchema()
	 * @see #getSybaseASEEncryptionKey()
	 * @generated
	 */
	EReference getSybaseASEEncryptionKey_Schema();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo <em>Lock Promotion Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lock Promotion Info</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo
	 * @generated
	 */
	EClass getLockPromotionInfo();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#isRowLockPromotion <em>Row Lock Promotion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Lock Promotion</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#isRowLockPromotion()
	 * @see #getLockPromotionInfo()
	 * @generated
	 */
	EAttribute getLockPromotionInfo_RowLockPromotion();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getLWM <em>LWM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>LWM</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getLWM()
	 * @see #getLockPromotionInfo()
	 * @generated
	 */
	EAttribute getLockPromotionInfo_LWM();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getHWM <em>HWM</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>HWM</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getHWM()
	 * @see #getLockPromotionInfo()
	 * @generated
	 */
	EAttribute getLockPromotionInfo_HWM();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getPCT <em>PCT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>PCT</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo#getPCT()
	 * @see #getLockPromotionInfo()
	 * @generated
	 */
	EAttribute getLockPromotionInfo_PCT();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERole <em>Sybase ASE Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Role</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERole
	 * @generated
	 */
	EClass getSybaseASERole();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache <em>Sybase ASE Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Cache</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache
	 * @generated
	 */
	EClass getSybaseASECache();

    /**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache#getDatabase <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Database</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache#getDatabase()
	 * @see #getSybaseASECache()
	 * @generated
	 */
	EReference getSybaseASECache_Database();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable <em>Sybase ASE View Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE View Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable
	 * @generated
	 */
	EClass getSybaseASEViewTable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable#isWithCheckOption <em>With Check Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>With Check Option</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable#isWithCheckOption()
	 * @see #getSybaseASEViewTable()
	 * @generated
	 */
	EAttribute getSybaseASEViewTable_WithCheckOption();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETempTable <em>Sybase ASE Temp Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Temp Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETempTable
	 * @generated
	 */
	EClass getSybaseASETempTable();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable <em>Sybase ASE Proxy Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Proxy Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable
	 * @generated
	 */
	EClass getSybaseASEProxyTable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getExternalType <em>External Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getExternalType()
	 * @see #getSybaseASEProxyTable()
	 * @generated
	 */
	EAttribute getSybaseASEProxyTable_ExternalType();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#isExisting <em>Existing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Existing</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#isExisting()
	 * @see #getSybaseASEProxyTable()
	 * @generated
	 */
	EAttribute getSybaseASEProxyTable_Existing();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getColumnDelimiter <em>Column Delimiter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Delimiter</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getColumnDelimiter()
	 * @see #getSybaseASEProxyTable()
	 * @generated
	 */
	EAttribute getSybaseASEProxyTable_ColumnDelimiter();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getExternalPath <em>External Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Path</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable#getExternalPath()
	 * @see #getSybaseASEProxyTable()
	 * @generated
	 */
	EAttribute getSybaseASEProxyTable_ExternalPath();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable <em>Sybase ASE Web Service Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Web Service Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable
	 * @generated
	 */
	EClass getSybaseASEWebServiceTable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable#getMethod()
	 * @see #getSybaseASEWebServiceTable()
	 * @generated
	 */
	EAttribute getSybaseASEWebServiceTable_Method();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable#getWSDLURI <em>WSDLURI</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>WSDLURI</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable#getWSDLURI()
	 * @see #getSybaseASEWebServiceTable()
	 * @generated
	 */
    EAttribute getSybaseASEWebServiceTable_WSDLURI();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable <em>Sybase ASE Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Base Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable
	 * @generated
	 */
	EClass getSybaseASEBaseTable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getLockSchema <em>Lock Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lock Schema</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getLockSchema()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EAttribute getSybaseASEBaseTable_LockSchema();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getFillFactor <em>Fill Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Factor</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getFillFactor()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EAttribute getSybaseASEBaseTable_FillFactor();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getMaxRowPerPage <em>Max Row Per Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Row Per Page</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getMaxRowPerPage()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EAttribute getSybaseASEBaseTable_MaxRowPerPage();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getExpRowSize <em>Exp Row Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exp Row Size</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getExpRowSize()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EAttribute getSybaseASEBaseTable_ExpRowSize();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getReservePageGap <em>Reserve Page Gap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reserve Page Gap</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getReservePageGap()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EAttribute getSybaseASEBaseTable_ReservePageGap();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getIdentityGap <em>Identity Gap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identity Gap</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getIdentityGap()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EAttribute getSybaseASEBaseTable_IdentityGap();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getSegment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Segment</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getSegment()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EReference getSybaseASEBaseTable_Segment();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getConcurrencyOptThreshold <em>Concurrency Opt Threshold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Concurrency Opt Threshold</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getConcurrencyOptThreshold()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EAttribute getSybaseASEBaseTable_ConcurrencyOptThreshold();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getPartitionCondition <em>Partition Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Partition Condition</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getPartitionCondition()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EReference getSybaseASEBaseTable_PartitionCondition();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTableOnlyCacheInfo <em>Table Only Cache Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Table Only Cache Info</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTableOnlyCacheInfo()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EReference getSybaseASEBaseTable_TableOnlyCacheInfo();

    /**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTextOnlyCacheInfo <em>Text Only Cache Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Text Only Cache Info</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTextOnlyCacheInfo()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EReference getSybaseASEBaseTable_TextOnlyCacheInfo();

    /**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getLockPromotion <em>Lock Promotion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lock Promotion</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getLockPromotion()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EReference getSybaseASEBaseTable_LockPromotion();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getPartitions <em>Partitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partitions</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getPartitions()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EAttribute getSybaseASEBaseTable_Partitions();

    /**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTextImageSegment <em>Text Image Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Text Image Segment</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTextImageSegment()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EReference getSybaseASEBaseTable_TextImageSegment();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#isSystemTable <em>System Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#isSystemTable()
	 * @see #getSybaseASEBaseTable()
	 * @generated
	 */
	EAttribute getSybaseASEBaseTable_SystemTable();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser <em>Sybase ASE User</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE User</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser
	 * @generated
	 */
    EClass getSybaseASEUser();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser#getLoginName <em>Login Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Login Name</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser#getLoginName()
	 * @see #getSybaseASEUser()
	 * @generated
	 */
    EAttribute getSybaseASEUser_LoginName();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEGroup <em>Sybase ASE Group</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Group</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEGroup
	 * @generated
	 */
    EClass getSybaseASEGroup();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrivilege <em>Sybase ASE Privilege</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Privilege</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrivilege
	 * @generated
	 */
    EClass getSybaseASEPrivilege();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETrigger <em>Sybase ASE Trigger</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Trigger</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETrigger
	 * @generated
	 */
    EClass getSybaseASETrigger();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETrigger#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETrigger#isEnabled()
	 * @see #getSybaseASETrigger()
	 * @generated
	 */
    EAttribute getSybaseASETrigger_Enabled();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint <em>Sybase ASE Check Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASE Check Constraint</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint
	 * @generated
	 */
	EClass getSybaseASECheckConstraint();

				/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint#getCreator <em>Creator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Creator</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint#getCreator()
	 * @see #getSybaseASECheckConstraint()
	 * @generated
	 */
	EReference getSybaseASECheckConstraint_Creator();

				/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType <em>Transaction Mode Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Transaction Mode Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType
	 * @generated
	 */
	EEnum getTransactionModeType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType <em>Cache Strategy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Cache Strategy Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType
	 * @generated
	 */
	EEnum getCacheStrategyType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType <em>Proxy Table External Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Proxy Table External Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType
	 * @generated
	 */
	EEnum getProxyTableExternalType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType <em>Access Rule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Access Rule Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType
	 * @generated
	 */
	EEnum getAccessRuleType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType <em>Sybase ASE Catalog Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sybase ASE Catalog Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType
	 * @generated
	 */
	EEnum getSybaseASECatalogType();

    /**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType <em>Locking Schema Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Locking Schema Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType
	 * @generated
	 */
	EEnum getLockingSchemaType();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SybaseasesqlmodelFactory getSybaseasesqlmodelFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESchemaImpl <em>Sybase ASE Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESchemaImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASESchema()
		 * @generated
		 */
		EClass SYBASE_ASE_SCHEMA = eINSTANCE.getSybaseASESchema();

        /**
		 * The meta object literal for the '<em><b>Defaults</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_SCHEMA__DEFAULTS = eINSTANCE.getSybaseASESchema_Defaults();

        /**
		 * The meta object literal for the '<em><b>Rules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_SCHEMA__RULES = eINSTANCE.getSybaseASESchema_Rules();

        /**
		 * The meta object literal for the '<em><b>Encryption Keys</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS = eINSTANCE.getSybaseASESchema_EncryptionKeys();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl <em>Sybase ASE Database</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDatabaseImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEDatabase()
		 * @generated
		 */
		EClass SYBASE_ASE_DATABASE = eINSTANCE.getSybaseASEDatabase();

        /**
		 * The meta object literal for the '<em><b>Data Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_DATABASE__DATA_TYPES = eINSTANCE.getSybaseASEDatabase_DataTypes();

        /**
		 * The meta object literal for the '<em><b>Encryption Key Applicable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_DATABASE__ENCRYPTION_KEY_APPLICABLE = eINSTANCE.getSybaseASEDatabase_EncryptionKeyApplicable();

        /**
		 * The meta object literal for the '<em><b>Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_DATABASE__ROLES = eINSTANCE.getSybaseASEDatabase_Roles();

        /**
		 * The meta object literal for the '<em><b>Caches</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_DATABASE__CACHES = eINSTANCE.getSybaseASEDatabase_Caches();

        /**
		 * The meta object literal for the '<em><b>Web Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_DATABASE__WEB_SERVICES = eINSTANCE.getSybaseASEDatabase_WebServices();

        /**
		 * The meta object literal for the '<em><b>Webservice Applicable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_DATABASE__WEBSERVICE_APPLICABLE = eINSTANCE.getSybaseASEDatabase_WebserviceApplicable();

        /**
		 * The meta object literal for the '<em><b>Sds Server</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SYBASE_ASE_DATABASE__SDS_SERVER = eINSTANCE.getSybaseASEDatabase_SdsServer();

        /**
		 * The meta object literal for the '<em><b>Temp DB Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_DATABASE__TEMP_DB_NAME = eINSTANCE.getSybaseASEDatabase_TempDBName();

								/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl <em>Sybase ASE Web Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEWebService()
		 * @generated
		 */
		EClass SYBASE_ASE_WEB_SERVICE = eINSTANCE.getSybaseASEWebService();

        /**
		 * The meta object literal for the '<em><b>Service id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE__SERVICE_ID = eINSTANCE.getSybaseASEWebService_Service_id();

        /**
		 * The meta object literal for the '<em><b>Service type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE__SERVICE_TYPE = eINSTANCE.getSybaseASEWebService_Service_type();

        /**
		 * The meta object literal for the '<em><b>Auth required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE__AUTH_REQUIRED = eINSTANCE.getSybaseASEWebService_Auth_required();

        /**
		 * The meta object literal for the '<em><b>Secure required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE__SECURE_REQUIRED = eINSTANCE.getSybaseASEWebService_Secure_required();

        /**
		 * The meta object literal for the '<em><b>Url path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE__URL_PATH = eINSTANCE.getSybaseASEWebService_Url_path();

        /**
		 * The meta object literal for the '<em><b>User name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE__USER_NAME = eINSTANCE.getSybaseASEWebService_User_name();

        /**
		 * The meta object literal for the '<em><b>Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE__PARAMETER = eINSTANCE.getSybaseASEWebService_Parameter();

        /**
		 * The meta object literal for the '<em><b>Statement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE__STATEMENT = eINSTANCE.getSybaseASEWebService_Statement();

        /**
		 * The meta object literal for the '<em><b>Remarks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE__REMARKS = eINSTANCE.getSybaseASEWebService_Remarks();

        /**
		 * The meta object literal for the '<em><b>Database</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_WEB_SERVICE__DATABASE = eINSTANCE.getSybaseASEWebService_Database();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPredefinedDataTypeImpl <em>Sybase ASE Predefined Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPredefinedDataTypeImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEPredefinedDataType()
		 * @generated
		 */
		EClass SYBASE_ASE_PREDEFINED_DATA_TYPE = eINSTANCE.getSybaseASEPredefinedDataType();

        /**
		 * The meta object literal for the '<em><b>Database</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE = eINSTANCE.getSybaseASEPredefinedDataType_Database();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl <em>Sybase ASE Catalog</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECatalogImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASECatalog()
		 * @generated
		 */
		EClass SYBASE_ASE_CATALOG = eINSTANCE.getSybaseASECatalog();

        /**
		 * The meta object literal for the '<em><b>Segments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_CATALOG__SEGMENTS = eINSTANCE.getSybaseASECatalog_Segments();

        /**
		 * The meta object literal for the '<em><b>Data Devices</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_CATALOG__DATA_DEVICES = eINSTANCE.getSybaseASECatalog_DataDevices();

        /**
		 * The meta object literal for the '<em><b>Log Devices</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_CATALOG__LOG_DEVICES = eINSTANCE.getSybaseASECatalog_LogDevices();

        /**
		 * The meta object literal for the '<em><b>Override</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_CATALOG__OVERRIDE = eINSTANCE.getSybaseASECatalog_Override();

        /**
		 * The meta object literal for the '<em><b>Default Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_CATALOG__DEFAULT_LOCATION = eINSTANCE.getSybaseASECatalog_DefaultLocation();

        /**
		 * The meta object literal for the '<em><b>For Load</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_CATALOG__FOR_LOAD = eINSTANCE.getSybaseASECatalog_ForLoad();

        /**
		 * The meta object literal for the '<em><b>For Proxy Update</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_CATALOG__FOR_PROXY_UPDATE = eINSTANCE.getSybaseASECatalog_ForProxyUpdate();

        /**
		 * The meta object literal for the '<em><b>Cache</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_CATALOG__CACHE = eINSTANCE.getSybaseASECatalog_Cache();

        /**
		 * The meta object literal for the '<em><b>Catalog Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_CATALOG__CATALOG_TYPE = eINSTANCE.getSybaseASECatalog_CatalogType();

        /**
		 * The meta object literal for the '<em><b>Log IO Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_CATALOG__LOG_IO_SIZE = eINSTANCE.getSybaseASECatalog_LogIOSize();

        /**
		 * The meta object literal for the '<em><b>Recovery Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_CATALOG__RECOVERY_ORDER = eINSTANCE.getSybaseASECatalog_RecoveryOrder();

        /**
		 * The meta object literal for the '<em><b>Authorization Ids</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_CATALOG__AUTHORIZATION_IDS = eINSTANCE.getSybaseASECatalog_AuthorizationIds();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProcedureImpl <em>Sybase ASE Procedure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProcedureImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEProcedure()
		 * @generated
		 */
		EClass SYBASE_ASE_PROCEDURE = eINSTANCE.getSybaseASEProcedure();

        /**
		 * The meta object literal for the '<em><b>Group Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_PROCEDURE__GROUP_NUMBER = eINSTANCE.getSybaseASEProcedure_GroupNumber();

        /**
		 * The meta object literal for the '<em><b>Transaction Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_PROCEDURE__TRANSACTION_MODE = eINSTANCE.getSybaseASEProcedure_TransactionMode();

        /**
		 * The meta object literal for the '<em><b>System Procedure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_PROCEDURE__SYSTEM_PROCEDURE = eINSTANCE.getSybaseASEProcedure_SystemProcedure();

        /**
		 * The meta object literal for the '<em><b>With Recompile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_PROCEDURE__WITH_RECOMPILE = eINSTANCE.getSybaseASEProcedure_WithRecompile();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDefaultImpl <em>Sybase ASE Default</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEDefaultImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEDefault()
		 * @generated
		 */
		EClass SYBASE_ASE_DEFAULT = eINSTANCE.getSybaseASEDefault();

        /**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_DEFAULT__SCHEMA = eINSTANCE.getSybaseASEDefault_Schema();

        /**
		 * The meta object literal for the '<em><b>Statement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_DEFAULT__STATEMENT = eINSTANCE.getSybaseASEDefault_Statement();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERuleImpl <em>Sybase ASE Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERuleImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASERule()
		 * @generated
		 */
		EClass SYBASE_ASE_RULE = eINSTANCE.getSybaseASERule();

        /**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_RULE__SCHEMA = eINSTANCE.getSybaseASERule_Schema();

        /**
		 * The meta object literal for the '<em><b>Statement</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_RULE__STATEMENT = eINSTANCE.getSybaseASERule_Statement();

        /**
		 * The meta object literal for the '<em><b>Access Rule</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_RULE__ACCESS_RULE = eINSTANCE.getSybaseASERule_AccessRule();

        /**
		 * The meta object literal for the '<em><b>Access Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_RULE__ACCESS_TYPE = eINSTANCE.getSybaseASERule_AccessType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl <em>Sybase ASE Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEIndex()
		 * @generated
		 */
		EClass SYBASE_ASE_INDEX = eINSTANCE.getSybaseASEIndex();

        /**
		 * The meta object literal for the '<em><b>Max Row Per Page</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE = eINSTANCE.getSybaseASEIndex_MaxRowPerPage();

        /**
		 * The meta object literal for the '<em><b>Reverse Page Gap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__REVERSE_PAGE_GAP = eINSTANCE.getSybaseASEIndex_ReversePageGap();

        /**
		 * The meta object literal for the '<em><b>Ignore Duplicate Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY = eINSTANCE.getSybaseASEIndex_IgnoreDuplicateKey();

        /**
		 * The meta object literal for the '<em><b>Sorted Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__SORTED_DATA = eINSTANCE.getSybaseASEIndex_SortedData();

        /**
		 * The meta object literal for the '<em><b>Ignore Duplicate Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW = eINSTANCE.getSybaseASEIndex_IgnoreDuplicateRow();

        /**
		 * The meta object literal for the '<em><b>Segment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_INDEX__SEGMENT = eINSTANCE.getSybaseASEIndex_Segment();

        /**
		 * The meta object literal for the '<em><b>Local Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__LOCAL_INDEX = eINSTANCE.getSybaseASEIndex_LocalIndex();

        /**
		 * The meta object literal for the '<em><b>Partitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_INDEX__PARTITIONS = eINSTANCE.getSybaseASEIndex_Partitions();

        /**
		 * The meta object literal for the '<em><b>Consumer Num</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__CONSUMER_NUM = eINSTANCE.getSybaseASEIndex_ConsumerNum();

        /**
		 * The meta object literal for the '<em><b>Statistics Step</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__STATISTICS_STEP = eINSTANCE.getSybaseASEIndex_StatisticsStep();

        /**
		 * The meta object literal for the '<em><b>Allow Duplicate Row</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW = eINSTANCE.getSybaseASEIndex_AllowDuplicateRow();

        /**
		 * The meta object literal for the '<em><b>Suspect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_INDEX__SUSPECT = eINSTANCE.getSybaseASEIndex_Suspect();

        /**
		 * The meta object literal for the '<em><b>Cache Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_INDEX__CACHE_INFO = eINSTANCE.getSybaseASEIndex_CacheInfo();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESegmentImpl <em>Sybase ASE Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESegmentImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASESegment()
		 * @generated
		 */
		EClass SYBASE_ASE_SEGMENT = eINSTANCE.getSybaseASESegment();

        /**
		 * The meta object literal for the '<em><b>Catalog</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_SEGMENT__CATALOG = eINSTANCE.getSybaseASESegment_Catalog();

        /**
		 * The meta object literal for the '<em><b>Device Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_SEGMENT__DEVICE_NAMES = eINSTANCE.getSybaseASESegment_DeviceNames();

        /**
		 * The meta object literal for the '<em><b>Thresholds</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_SEGMENT__THRESHOLDS = eINSTANCE.getSybaseASESegment_Thresholds();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEFuncBasedIndexMemberImpl <em>Sybase ASE Func Based Index Member</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEFuncBasedIndexMemberImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEFuncBasedIndexMember()
		 * @generated
		 */
		EClass SYBASE_ASE_FUNC_BASED_INDEX_MEMBER = eINSTANCE.getSybaseASEFuncBasedIndexMember();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl <em>Sybase ASE Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASETable()
		 * @generated
		 */
		EClass SYBASE_ASE_TABLE = eINSTANCE.getSybaseASETable();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnCheckConstraintImpl <em>Sybase ASE Column Check Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnCheckConstraintImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEColumnCheckConstraint()
		 * @generated
		 */
		EClass SYBASE_ASE_COLUMN_CHECK_CONSTRAINT = eINSTANCE.getSybaseASEColumnCheckConstraint();

        /**
		 * The meta object literal for the '<em><b>Column</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN = eINSTANCE.getSybaseASEColumnCheckConstraint_Column();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl <em>Sybase ASE Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEColumnImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEColumn()
		 * @generated
		 */
		EClass SYBASE_ASE_COLUMN = eINSTANCE.getSybaseASEColumn();

        /**
		 * The meta object literal for the '<em><b>Column Check</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_COLUMN__COLUMN_CHECK = eINSTANCE.getSybaseASEColumn_ColumnCheck();

        /**
		 * The meta object literal for the '<em><b>Bound Default</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_COLUMN__BOUND_DEFAULT = eINSTANCE.getSybaseASEColumn_BoundDefault();

        /**
		 * The meta object literal for the '<em><b>Bound Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_COLUMN__BOUND_RULE = eINSTANCE.getSybaseASEColumn_BoundRule();

        /**
		 * The meta object literal for the '<em><b>Materialized</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_COLUMN__MATERIALIZED = eINSTANCE.getSybaseASEColumn_Materialized();

        /**
		 * The meta object literal for the '<em><b>Encryption Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_COLUMN__ENCRYPTION_KEY = eINSTANCE.getSybaseASEColumn_EncryptionKey();

        /**
		 * The meta object literal for the '<em><b>Bind Default In Future Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_COLUMN__BIND_DEFAULT_IN_FUTURE_ONLY = eINSTANCE.getSybaseASEColumn_BindDefaultInFutureOnly();

        /**
		 * The meta object literal for the '<em><b>Bind Rule In Future Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_COLUMN__BIND_RULE_IN_FUTURE_ONLY = eINSTANCE.getSybaseASEColumn_BindRuleInFutureOnly();

        /**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SYBASE_ASE_COLUMN__HIDDEN = eINSTANCE.getSybaseASEColumn_Hidden();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUniqueConstraintImpl <em>Sybase ASE Unique Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUniqueConstraintImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEUniqueConstraint()
		 * @generated
		 */
		EClass SYBASE_ASE_UNIQUE_CONSTRAINT = eINSTANCE.getSybaseASEUniqueConstraint();

        /**
		 * The meta object literal for the '<em><b>System Gened Index</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX = eINSTANCE.getSybaseASEUniqueConstraint_SystemGenedIndex();

        /**
		 * The meta object literal for the '<em><b>System Gened Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_NAME = eINSTANCE.getSybaseASEUniqueConstraint_SystemGenedName();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrimaryKeyImpl <em>Sybase ASE Primary Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrimaryKeyImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEPrimaryKey()
		 * @generated
		 */
		EClass SYBASE_ASE_PRIMARY_KEY = eINSTANCE.getSybaseASEPrimaryKey();

        /**
		 * The meta object literal for the '<em><b>Ase Unique Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT = eINSTANCE.getSybaseASEPrimaryKey_AseUniqueConstraint();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.DeviceItemImpl <em>Device Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.DeviceItemImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getDeviceItem()
		 * @generated
		 */
		EClass DEVICE_ITEM = eINSTANCE.getDeviceItem();

        /**
		 * The meta object literal for the '<em><b>Device Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEVICE_ITEM__DEVICE_NAME = eINSTANCE.getDeviceItem_DeviceName();

        /**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEVICE_ITEM__SIZE = eINSTANCE.getDeviceItem_Size();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SegmentThresholdImpl <em>Segment Threshold</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SegmentThresholdImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSegmentThreshold()
		 * @generated
		 */
		EClass SEGMENT_THRESHOLD = eINSTANCE.getSegmentThreshold();

        /**
		 * The meta object literal for the '<em><b>Procedure Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT_THRESHOLD__PROCEDURE_NAME = eINSTANCE.getSegmentThreshold_ProcedureName();

        /**
		 * The meta object literal for the '<em><b>Free Space</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEGMENT_THRESHOLD__FREE_SPACE = eINSTANCE.getSegmentThreshold_FreeSpace();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.CacheInfoImpl <em>Cache Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.CacheInfoImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getCacheInfo()
		 * @generated
		 */
		EClass CACHE_INFO = eINSTANCE.getCacheInfo();

        /**
		 * The meta object literal for the '<em><b>Cache Strategy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CACHE_INFO__CACHE_STRATEGY = eINSTANCE.getCacheInfo_CacheStrategy();

        /**
		 * The meta object literal for the '<em><b>Cache</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CACHE_INFO__CACHE = eINSTANCE.getCacheInfo_Cache();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl <em>Sybase ASE User Defined Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEUserDefinedType()
		 * @generated
		 */
		EClass SYBASE_ASE_USER_DEFINED_TYPE = eINSTANCE.getSybaseASEUserDefinedType();

        /**
		 * The meta object literal for the '<em><b>Bound Default</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT = eINSTANCE.getSybaseASEUserDefinedType_BoundDefault();

        /**
		 * The meta object literal for the '<em><b>Bound Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE = eINSTANCE.getSybaseASEUserDefinedType_BoundRule();

        /**
		 * The meta object literal for the '<em><b>Bind Default In Future Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_USER_DEFINED_TYPE__BIND_DEFAULT_IN_FUTURE_ONLY = eINSTANCE.getSybaseASEUserDefinedType_BindDefaultInFutureOnly();

        /**
		 * The meta object literal for the '<em><b>Bind Rule In Future Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_USER_DEFINED_TYPE__BIND_RULE_IN_FUTURE_ONLY = eINSTANCE.getSybaseASEUserDefinedType_BindRuleInFutureOnly();

        /**
		 * The meta object literal for the '<em><b>Allow Nulls</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_USER_DEFINED_TYPE__ALLOW_NULLS = eINSTANCE.getSybaseASEUserDefinedType_AllowNulls();

        /**
		 * The meta object literal for the '<em><b>Identity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_USER_DEFINED_TYPE__IDENTITY = eINSTANCE.getSybaseASEUserDefinedType_Identity();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEEncryptionKeyImpl <em>Sybase ASE Encryption Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEEncryptionKeyImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEEncryptionKey()
		 * @generated
		 */
		EClass SYBASE_ASE_ENCRYPTION_KEY = eINSTANCE.getSybaseASEEncryptionKey();

        /**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_ENCRYPTION_KEY__SCHEMA = eINSTANCE.getSybaseASEEncryptionKey_Schema();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.LockPromotionInfoImpl <em>Lock Promotion Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.LockPromotionInfoImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getLockPromotionInfo()
		 * @generated
		 */
		EClass LOCK_PROMOTION_INFO = eINSTANCE.getLockPromotionInfo();

        /**
		 * The meta object literal for the '<em><b>Row Lock Promotion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCK_PROMOTION_INFO__ROW_LOCK_PROMOTION = eINSTANCE.getLockPromotionInfo_RowLockPromotion();

        /**
		 * The meta object literal for the '<em><b>LWM</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCK_PROMOTION_INFO__LWM = eINSTANCE.getLockPromotionInfo_LWM();

        /**
		 * The meta object literal for the '<em><b>HWM</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCK_PROMOTION_INFO__HWM = eINSTANCE.getLockPromotionInfo_HWM();

        /**
		 * The meta object literal for the '<em><b>PCT</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCK_PROMOTION_INFO__PCT = eINSTANCE.getLockPromotionInfo_PCT();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERoleImpl <em>Sybase ASE Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERoleImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASERole()
		 * @generated
		 */
		EClass SYBASE_ASE_ROLE = eINSTANCE.getSybaseASERole();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECacheImpl <em>Sybase ASE Cache</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECacheImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASECache()
		 * @generated
		 */
		EClass SYBASE_ASE_CACHE = eINSTANCE.getSybaseASECache();

        /**
		 * The meta object literal for the '<em><b>Database</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_CACHE__DATABASE = eINSTANCE.getSybaseASECache_Database();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEViewTableImpl <em>Sybase ASE View Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEViewTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEViewTable()
		 * @generated
		 */
		EClass SYBASE_ASE_VIEW_TABLE = eINSTANCE.getSybaseASEViewTable();

        /**
		 * The meta object literal for the '<em><b>With Check Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_VIEW_TABLE__WITH_CHECK_OPTION = eINSTANCE.getSybaseASEViewTable_WithCheckOption();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETempTableImpl <em>Sybase ASE Temp Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETempTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASETempTable()
		 * @generated
		 */
		EClass SYBASE_ASE_TEMP_TABLE = eINSTANCE.getSybaseASETempTable();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProxyTableImpl <em>Sybase ASE Proxy Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEProxyTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEProxyTable()
		 * @generated
		 */
		EClass SYBASE_ASE_PROXY_TABLE = eINSTANCE.getSybaseASEProxyTable();

        /**
		 * The meta object literal for the '<em><b>External Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_PROXY_TABLE__EXTERNAL_TYPE = eINSTANCE.getSybaseASEProxyTable_ExternalType();

        /**
		 * The meta object literal for the '<em><b>Existing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_PROXY_TABLE__EXISTING = eINSTANCE.getSybaseASEProxyTable_Existing();

        /**
		 * The meta object literal for the '<em><b>Column Delimiter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_PROXY_TABLE__COLUMN_DELIMITER = eINSTANCE.getSybaseASEProxyTable_ColumnDelimiter();

        /**
		 * The meta object literal for the '<em><b>External Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_PROXY_TABLE__EXTERNAL_PATH = eINSTANCE.getSybaseASEProxyTable_ExternalPath();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceTableImpl <em>Sybase ASE Web Service Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEWebServiceTable()
		 * @generated
		 */
		EClass SYBASE_ASE_WEB_SERVICE_TABLE = eINSTANCE.getSybaseASEWebServiceTable();

        /**
		 * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_WEB_SERVICE_TABLE__METHOD = eINSTANCE.getSybaseASEWebServiceTable_Method();

        /**
		 * The meta object literal for the '<em><b>WSDLURI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SYBASE_ASE_WEB_SERVICE_TABLE__WSDLURI = eINSTANCE.getSybaseASEWebServiceTable_WSDLURI();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEBaseTableImpl <em>Sybase ASE Base Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEBaseTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEBaseTable()
		 * @generated
		 */
		EClass SYBASE_ASE_BASE_TABLE = eINSTANCE.getSybaseASEBaseTable();

        /**
		 * The meta object literal for the '<em><b>Lock Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_BASE_TABLE__LOCK_SCHEMA = eINSTANCE.getSybaseASEBaseTable_LockSchema();

        /**
		 * The meta object literal for the '<em><b>Fill Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_BASE_TABLE__FILL_FACTOR = eINSTANCE.getSybaseASEBaseTable_FillFactor();

        /**
		 * The meta object literal for the '<em><b>Max Row Per Page</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_BASE_TABLE__MAX_ROW_PER_PAGE = eINSTANCE.getSybaseASEBaseTable_MaxRowPerPage();

        /**
		 * The meta object literal for the '<em><b>Exp Row Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_BASE_TABLE__EXP_ROW_SIZE = eINSTANCE.getSybaseASEBaseTable_ExpRowSize();

        /**
		 * The meta object literal for the '<em><b>Reserve Page Gap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_BASE_TABLE__RESERVE_PAGE_GAP = eINSTANCE.getSybaseASEBaseTable_ReservePageGap();

        /**
		 * The meta object literal for the '<em><b>Identity Gap</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_BASE_TABLE__IDENTITY_GAP = eINSTANCE.getSybaseASEBaseTable_IdentityGap();

        /**
		 * The meta object literal for the '<em><b>Segment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_BASE_TABLE__SEGMENT = eINSTANCE.getSybaseASEBaseTable_Segment();

        /**
		 * The meta object literal for the '<em><b>Concurrency Opt Threshold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_BASE_TABLE__CONCURRENCY_OPT_THRESHOLD = eINSTANCE.getSybaseASEBaseTable_ConcurrencyOptThreshold();

        /**
		 * The meta object literal for the '<em><b>Partition Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_BASE_TABLE__PARTITION_CONDITION = eINSTANCE.getSybaseASEBaseTable_PartitionCondition();

        /**
		 * The meta object literal for the '<em><b>Table Only Cache Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_BASE_TABLE__TABLE_ONLY_CACHE_INFO = eINSTANCE.getSybaseASEBaseTable_TableOnlyCacheInfo();

        /**
		 * The meta object literal for the '<em><b>Text Only Cache Info</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_BASE_TABLE__TEXT_ONLY_CACHE_INFO = eINSTANCE.getSybaseASEBaseTable_TextOnlyCacheInfo();

        /**
		 * The meta object literal for the '<em><b>Lock Promotion</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_BASE_TABLE__LOCK_PROMOTION = eINSTANCE.getSybaseASEBaseTable_LockPromotion();

        /**
		 * The meta object literal for the '<em><b>Partitions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_BASE_TABLE__PARTITIONS = eINSTANCE.getSybaseASEBaseTable_Partitions();

        /**
		 * The meta object literal for the '<em><b>Text Image Segment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_BASE_TABLE__TEXT_IMAGE_SEGMENT = eINSTANCE.getSybaseASEBaseTable_TextImageSegment();

        /**
		 * The meta object literal for the '<em><b>System Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASE_BASE_TABLE__SYSTEM_TABLE = eINSTANCE.getSybaseASEBaseTable_SystemTable();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserImpl <em>Sybase ASE User</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEUser()
		 * @generated
		 */
        EClass SYBASE_ASE_USER = eINSTANCE.getSybaseASEUser();

        /**
		 * The meta object literal for the '<em><b>Login Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SYBASE_ASE_USER__LOGIN_NAME = eINSTANCE.getSybaseASEUser_LoginName();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEGroupImpl <em>Sybase ASE Group</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEGroupImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEGroup()
		 * @generated
		 */
        EClass SYBASE_ASE_GROUP = eINSTANCE.getSybaseASEGroup();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrivilegeImpl <em>Sybase ASE Privilege</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEPrivilegeImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASEPrivilege()
		 * @generated
		 */
        EClass SYBASE_ASE_PRIVILEGE = eINSTANCE.getSybaseASEPrivilege();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETriggerImpl <em>Sybase ASE Trigger</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETriggerImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASETrigger()
		 * @generated
		 */
        EClass SYBASE_ASE_TRIGGER = eINSTANCE.getSybaseASETrigger();

        /**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SYBASE_ASE_TRIGGER__ENABLED = eINSTANCE.getSybaseASETrigger_Enabled();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECheckConstraintImpl <em>Sybase ASE Check Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASECheckConstraintImpl
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASECheckConstraint()
		 * @generated
		 */
		EClass SYBASE_ASE_CHECK_CONSTRAINT = eINSTANCE.getSybaseASECheckConstraint();

								/**
		 * The meta object literal for the '<em><b>Creator</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_ASE_CHECK_CONSTRAINT__CREATOR = eINSTANCE.getSybaseASECheckConstraint_Creator();

								/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType <em>Transaction Mode Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getTransactionModeType()
		 * @generated
		 */
		EEnum TRANSACTION_MODE_TYPE = eINSTANCE.getTransactionModeType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType <em>Cache Strategy Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getCacheStrategyType()
		 * @generated
		 */
		EEnum CACHE_STRATEGY_TYPE = eINSTANCE.getCacheStrategyType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType <em>Proxy Table External Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getProxyTableExternalType()
		 * @generated
		 */
		EEnum PROXY_TABLE_EXTERNAL_TYPE = eINSTANCE.getProxyTableExternalType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType <em>Access Rule Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getAccessRuleType()
		 * @generated
		 */
		EEnum ACCESS_RULE_TYPE = eINSTANCE.getAccessRuleType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType <em>Sybase ASE Catalog Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getSybaseASECatalogType()
		 * @generated
		 */
		EEnum SYBASE_ASE_CATALOG_TYPE = eINSTANCE.getSybaseASECatalogType();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType <em>Locking Schema Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType
		 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelPackageImpl#getLockingSchemaType()
		 * @generated
		 */
		EEnum LOCKING_SCHEMA_TYPE = eINSTANCE.getLockingSchemaType();

	}

} //SybaseasesqlmodelPackage
