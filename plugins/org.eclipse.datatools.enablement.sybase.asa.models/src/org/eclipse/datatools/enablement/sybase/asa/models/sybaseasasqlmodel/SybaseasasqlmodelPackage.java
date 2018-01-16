/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasasqlmodelPackage.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelFactory
 * @model kind="package"
 * @generated
 */
public interface SybaseasasqlmodelPackage extends EPackage
{
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sybaseasasqlmodel";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/connectivity/sqm/sybase/asa/sybaseasasqlmodel.ecore";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SybaseASAModel";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SybaseasasqlmodelPackage eINSTANCE = org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl.init();

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASADatabaseImpl <em>Sybase ASA Database</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASADatabaseImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASADatabase()
	 * @generated
	 */
	int SYBASE_ASA_DATABASE = 0;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__EANNOTATIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__NAME = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__DEPENDENCIES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__DESCRIPTION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__LABEL = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__COMMENTS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__EXTENSIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__PRIVILEGES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__VENDOR = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__VENDOR;

    /**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__VERSION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__VERSION;

    /**
	 * The feature id for the '<em><b>Schemas</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__SCHEMAS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__SCHEMAS;

    /**
	 * The feature id for the '<em><b>Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__EVENTS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__EVENTS;

    /**
	 * The feature id for the '<em><b>Catalogs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__CATALOGS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CATALOGS;

    /**
	 * The feature id for the '<em><b>Authorization Ids</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__AUTHORIZATION_IDS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__AUTHORIZATION_IDS;

    /**
	 * The feature id for the '<em><b>Data Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__DATA_TYPES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES;

    /**
	 * The feature id for the '<em><b>Web Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__WEB_SERVICES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__WEB_SERVICES;

    /**
	 * The feature id for the '<em><b>Db Spaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__DB_SPACES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DB_SPACES;

    /**
	 * The feature id for the '<em><b>Database File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__DATABASE_FILE_NAME = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME;

    /**
	 * The feature id for the '<em><b>Log File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__LOG_FILE_NAME = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME;

    /**
	 * The feature id for the '<em><b>Mirror File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__MIRROR_FILE_NAME = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME;

    /**
	 * The feature id for the '<em><b>Case Sensitive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__CASE_SENSITIVE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE;

    /**
	 * The feature id for the '<em><b>Collation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__COLLATION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__COLLATION;

    /**
	 * The feature id for the '<em><b>Blank Padding On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__BLANK_PADDING_ON = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON;

    /**
	 * The feature id for the '<em><b>Check Sum On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__CHECK_SUM_ON = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON;

    /**
	 * The feature id for the '<em><b>JConnect On</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__JCONNECT_ON = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JCONNECT_ON;

    /**
	 * The feature id for the '<em><b>Page Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__PAGE_SIZE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PAGE_SIZE;

    /**
	 * The feature id for the '<em><b>Encryption Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__ENCRYPTION_INFO = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO;

    /**
	 * The feature id for the '<em><b>Java Support</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__JAVA_SUPPORT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JAVA_SUPPORT;

    /**
	 * The feature id for the '<em><b>Password Case Sensitive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__PASSWORD_CASE_SENSITIVE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE;

    /**
	 * The feature id for the '<em><b>ASE Compatible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE__ASE_COMPATIBLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Database</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_DATABASE_FEATURE_COUNT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASATableImpl <em>Sybase ASA Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASATableImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASATable()
	 * @generated
	 */
	int SYBASE_ASA_TABLE = 1;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__EANNOTATIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__NAME = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__DEPENDENCIES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__DESCRIPTION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__LABEL = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__COMMENTS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__EXTENSIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__PRIVILEGES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__COLUMNS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__COLUMNS;

    /**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__SUPERTABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__SUPERTABLE;

    /**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__SUBTABLES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__SUBTABLES;

    /**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__SCHEMA = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__SCHEMA;

    /**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__UDT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__UDT;

    /**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__TRIGGERS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__TRIGGERS;

    /**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__INDEX = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__INDEX;

    /**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__SELF_REF_COLUMN_GENERATION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__SELF_REF_COLUMN_GENERATION;

    /**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__INSERTABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__INSERTABLE;

    /**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__UPDATABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__UPDATABLE;

    /**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__CONSTRAINTS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__CONSTRAINTS;

    /**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__REFERENCING_FOREIGN_KEYS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__REFERENCING_FOREIGN_KEYS;

    /**
	 * The feature id for the '<em><b>Db Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__DB_SPACE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE__DB_SPACE;

    /**
	 * The feature id for the '<em><b>Pctfree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE__PCTFREE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TABLE_FEATURE_COUNT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAForeignKeyImpl <em>Sybase ASA Foreign Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAForeignKeyImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASAForeignKey()
	 * @generated
	 */
	int SYBASE_ASA_FOREIGN_KEY = 2;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__EANNOTATIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__NAME = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__DEPENDENCIES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__DESCRIPTION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__LABEL = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__COMMENTS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__EXTENSIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__PRIVILEGES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Deferrable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__DEFERRABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__DEFERRABLE;

    /**
	 * The feature id for the '<em><b>Initially Deferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__INITIALLY_DEFERRED = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__INITIALLY_DEFERRED;

    /**
	 * The feature id for the '<em><b>Enforced</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__ENFORCED = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__ENFORCED;

    /**
	 * The feature id for the '<em><b>Base Table</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__BASE_TABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__BASE_TABLE;

    /**
	 * The feature id for the '<em><b>Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__MEMBERS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__MEMBERS;

    /**
	 * The feature id for the '<em><b>Match</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__MATCH = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__MATCH;

    /**
	 * The feature id for the '<em><b>On Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__ON_UPDATE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__ON_UPDATE;

    /**
	 * The feature id for the '<em><b>On Delete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__ON_DELETE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__ON_DELETE;

    /**
	 * The feature id for the '<em><b>Unique Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__UNIQUE_CONSTRAINT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__UNIQUE_CONSTRAINT;

    /**
	 * The feature id for the '<em><b>Referenced Members</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__REFERENCED_MEMBERS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__REFERENCED_MEMBERS;

    /**
	 * The feature id for the '<em><b>Unique Index</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__UNIQUE_INDEX = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__UNIQUE_INDEX;

    /**
	 * The feature id for the '<em><b>Referenced Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__REFERENCED_TABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__REFERENCED_TABLE;

    /**
	 * The feature id for the '<em><b>Role Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__ROLE_NAME = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__ROLE_NAME;

    /**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__CLUSTERED = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__CLUSTERED;

    /**
	 * The feature id for the '<em><b>Check On Commit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY__NULLABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Sybase ASA Foreign Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_FOREIGN_KEY_FEATURE_COUNT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAIndexImpl <em>Sybase ASA Index</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAIndexImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASAIndex()
	 * @generated
	 */
	int SYBASE_ASA_INDEX = 3;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__EANNOTATIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__NAME = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__DEPENDENCIES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__DESCRIPTION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__LABEL = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__COMMENTS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__EXTENSIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__PRIVILEGES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__SCHEMA = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__SCHEMA;

    /**
	 * The feature id for the '<em><b>Clustered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__CLUSTERED = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__CLUSTERED;

    /**
	 * The feature id for the '<em><b>Fill Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__FILL_FACTOR = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__FILL_FACTOR;

    /**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__UNIQUE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__UNIQUE;

    /**
	 * The feature id for the '<em><b>System Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__SYSTEM_GENERATED = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__SYSTEM_GENERATED;

    /**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__MEMBERS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__MEMBERS;

    /**
	 * The feature id for the '<em><b>Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__TABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__TABLE;

    /**
	 * The feature id for the '<em><b>Foreign Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__FOREIGN_KEY = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__FOREIGN_KEY;

    /**
	 * The feature id for the '<em><b>Included Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__INCLUDED_MEMBERS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__INCLUDED_MEMBERS;

    /**
	 * The feature id for the '<em><b>Db Space</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__DB_SPACE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DB_SPACE;

    /**
	 * The feature id for the '<em><b>Virtual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX__VIRTUAL = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_INDEX_FEATURE_COUNT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASATempTableImpl <em>Sybase ASA Temp Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASATempTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASATempTable()
	 * @generated
	 */
	int SYBASE_ASA_TEMP_TABLE = 4;

    /**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__EANNOTATIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__EANNOTATIONS;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__NAME = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__NAME;

    /**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__DEPENDENCIES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__DEPENDENCIES;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__DESCRIPTION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__LABEL = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__LABEL;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__COMMENTS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__COMMENTS;

    /**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__EXTENSIONS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__EXTENSIONS;

				/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__PRIVILEGES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__PRIVILEGES;

				/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__COLUMNS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__COLUMNS;

    /**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__SUPERTABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__SUPERTABLE;

    /**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__SUBTABLES = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__SUBTABLES;

    /**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__SCHEMA = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__SCHEMA;

    /**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__UDT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__UDT;

    /**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__TRIGGERS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__TRIGGERS;

    /**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__INDEX = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__INDEX;

    /**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__SELF_REF_COLUMN_GENERATION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__SELF_REF_COLUMN_GENERATION;

    /**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__INSERTABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__INSERTABLE;

    /**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__UPDATABLE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__UPDATABLE;

    /**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__CONSTRAINTS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__CONSTRAINTS;

    /**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__REFERENCING_FOREIGN_KEYS = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__REFERENCING_FOREIGN_KEYS;

    /**
	 * The feature id for the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__LOCAL = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__LOCAL;

    /**
	 * The feature id for the '<em><b>Delete On Commit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__DELETE_ON_COMMIT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__DELETE_ON_COMMIT;

    /**
	 * The feature id for the '<em><b>Transaction Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__TRANSACTION_OPTION = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE__TRANSACTION_OPTION;

    /**
	 * The feature id for the '<em><b>Pctfree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE__PCTFREE = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Sybase ASA Temp Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ASA_TEMP_TABLE_FEATURE_COUNT = SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE_FEATURE_COUNT + 1;


    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase <em>Sybase ASA Database</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Database</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase
	 * @generated
	 */
	EClass getSybaseASADatabase();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase#isASECompatible <em>ASE Compatible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ASE Compatible</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase#isASECompatible()
	 * @see #getSybaseASADatabase()
	 * @generated
	 */
	EAttribute getSybaseASADatabase_ASECompatible();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable <em>Sybase ASA Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable
	 * @generated
	 */
	EClass getSybaseASATable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable#getPctfree <em>Pctfree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pctfree</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable#getPctfree()
	 * @see #getSybaseASATable()
	 * @generated
	 */
	EAttribute getSybaseASATable_Pctfree();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey <em>Sybase ASA Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Foreign Key</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey
	 * @generated
	 */
	EClass getSybaseASAForeignKey();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey#isCheckOnCommit <em>Check On Commit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Check On Commit</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey#isCheckOnCommit()
	 * @see #getSybaseASAForeignKey()
	 * @generated
	 */
	EAttribute getSybaseASAForeignKey_CheckOnCommit();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey#isNullable <em>Nullable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nullable</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey#isNullable()
	 * @see #getSybaseASAForeignKey()
	 * @generated
	 */
	EAttribute getSybaseASAForeignKey_Nullable();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex <em>Sybase ASA Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Index</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex
	 * @generated
	 */
	EClass getSybaseASAIndex();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex#isVirtual <em>Virtual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Virtual</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex#isVirtual()
	 * @see #getSybaseASAIndex()
	 * @generated
	 */
	EAttribute getSybaseASAIndex_Virtual();

    /**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable <em>Sybase ASA Temp Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase ASA Temp Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable
	 * @generated
	 */
	EClass getSybaseASATempTable();

    /**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable#getPctfree <em>Pctfree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pctfree</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable#getPctfree()
	 * @see #getSybaseASATempTable()
	 * @generated
	 */
	EAttribute getSybaseASATempTable_Pctfree();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SybaseasasqlmodelFactory getSybaseasasqlmodelFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASADatabaseImpl <em>Sybase ASA Database</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASADatabaseImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASADatabase()
		 * @generated
		 */
		EClass SYBASE_ASA_DATABASE = eINSTANCE.getSybaseASADatabase();

        /**
		 * The meta object literal for the '<em><b>ASE Compatible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_DATABASE__ASE_COMPATIBLE = eINSTANCE.getSybaseASADatabase_ASECompatible();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASATableImpl <em>Sybase ASA Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASATableImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASATable()
		 * @generated
		 */
		EClass SYBASE_ASA_TABLE = eINSTANCE.getSybaseASATable();

        /**
		 * The meta object literal for the '<em><b>Pctfree</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_TABLE__PCTFREE = eINSTANCE.getSybaseASATable_Pctfree();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAForeignKeyImpl <em>Sybase ASA Foreign Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAForeignKeyImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASAForeignKey()
		 * @generated
		 */
		EClass SYBASE_ASA_FOREIGN_KEY = eINSTANCE.getSybaseASAForeignKey();

        /**
		 * The meta object literal for the '<em><b>Check On Commit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT = eINSTANCE.getSybaseASAForeignKey_CheckOnCommit();

        /**
		 * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_FOREIGN_KEY__NULLABLE = eINSTANCE.getSybaseASAForeignKey_Nullable();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAIndexImpl <em>Sybase ASA Index</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASAIndexImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASAIndex()
		 * @generated
		 */
		EClass SYBASE_ASA_INDEX = eINSTANCE.getSybaseASAIndex();

        /**
		 * The meta object literal for the '<em><b>Virtual</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_INDEX__VIRTUAL = eINSTANCE.getSybaseASAIndex_Virtual();

        /**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASATempTableImpl <em>Sybase ASA Temp Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASATempTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseasasqlmodelPackageImpl#getSybaseASATempTable()
		 * @generated
		 */
		EClass SYBASE_ASA_TEMP_TABLE = eINSTANCE.getSybaseASATempTable();

        /**
		 * The meta object literal for the '<em><b>Pctfree</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_ASA_TEMP_TABLE__PCTFREE = eINSTANCE.getSybaseASATempTable_Pctfree();

	}

} //SybaseasasqlmodelPackage
