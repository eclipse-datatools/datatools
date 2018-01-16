/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybasesqlmodelPackage.java,v 1.1 2008/04/28 17:10:56 bfitzpatrick Exp $
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel;

import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;

import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;

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
 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory
 * @model kind="package"
 * @generated
 */
public interface SybasesqlmodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "sybasesqlmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/enablement/sybase/sybasesqlmodel.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SybaseModel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SybasesqlmodelPackage eINSTANCE = org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseParameterImpl <em>Sybase Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseParameterImpl
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseParameter()
	 * @generated
	 */
	int SYBASE_PARAMETER = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__EANNOTATIONS = SQLRoutinesPackage.PARAMETER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__NAME = SQLRoutinesPackage.PARAMETER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__DEPENDENCIES = SQLRoutinesPackage.PARAMETER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__DESCRIPTION = SQLRoutinesPackage.PARAMETER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__LABEL = SQLRoutinesPackage.PARAMETER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__COMMENTS = SQLRoutinesPackage.PARAMETER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__EXTENSIONS = SQLRoutinesPackage.PARAMETER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__PRIVILEGES = SQLRoutinesPackage.PARAMETER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__CONTAINED_TYPE = SQLRoutinesPackage.PARAMETER__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__REFERENCED_TYPE = SQLRoutinesPackage.PARAMETER__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__MODE = SQLRoutinesPackage.PARAMETER__MODE;

	/**
	 * The feature id for the '<em><b>Locator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__LOCATOR = SQLRoutinesPackage.PARAMETER__LOCATOR;

	/**
	 * The feature id for the '<em><b>Routine</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__ROUTINE = SQLRoutinesPackage.PARAMETER__ROUTINE;

	/**
	 * The feature id for the '<em><b>String Type Option</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__STRING_TYPE_OPTION = SQLRoutinesPackage.PARAMETER__STRING_TYPE_OPTION;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__NULLABLE = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__DEFAULT_VALUE = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>JDBC Parameter Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER__JDBC_PARAMETER_TYPE = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Sybase Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PARAMETER_FEATURE_COUNT = SQLRoutinesPackage.PARAMETER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseRoutineImpl <em>Sybase Routine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseRoutineImpl
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseRoutine()
	 * @generated
	 */
	int SYBASE_ROUTINE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__EANNOTATIONS = SQLRoutinesPackage.ROUTINE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__NAME = SQLRoutinesPackage.ROUTINE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__DEPENDENCIES = SQLRoutinesPackage.ROUTINE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__DESCRIPTION = SQLRoutinesPackage.ROUTINE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__LABEL = SQLRoutinesPackage.ROUTINE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__COMMENTS = SQLRoutinesPackage.ROUTINE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__EXTENSIONS = SQLRoutinesPackage.ROUTINE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__PRIVILEGES = SQLRoutinesPackage.ROUTINE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__SPECIFIC_NAME = SQLRoutinesPackage.ROUTINE__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__LANGUAGE = SQLRoutinesPackage.ROUTINE__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__PARAMETER_STYLE = SQLRoutinesPackage.ROUTINE__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__DETERMINISTIC = SQLRoutinesPackage.ROUTINE__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__SQL_DATA_ACCESS = SQLRoutinesPackage.ROUTINE__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__CREATION_TS = SQLRoutinesPackage.ROUTINE__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__LAST_ALTERED_TS = SQLRoutinesPackage.ROUTINE__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__AUTHORIZATION_ID = SQLRoutinesPackage.ROUTINE__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__SECURITY = SQLRoutinesPackage.ROUTINE__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__EXTERNAL_NAME = SQLRoutinesPackage.ROUTINE__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__PARAMETERS = SQLRoutinesPackage.ROUTINE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__SOURCE = SQLRoutinesPackage.ROUTINE__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE__SCHEMA = SQLRoutinesPackage.ROUTINE__SCHEMA;

	/**
	 * The number of structural features of the '<em>Sybase Routine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_ROUTINE_FEATURE_COUNT = SQLRoutinesPackage.ROUTINE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseBaseTableImpl <em>Sybase Base Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseBaseTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseBaseTable()
	 * @generated
	 */
	int SYBASE_BASE_TABLE = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__EANNOTATIONS = SQLTablesPackage.BASE_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__NAME = SQLTablesPackage.BASE_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__DEPENDENCIES = SQLTablesPackage.BASE_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__DESCRIPTION = SQLTablesPackage.BASE_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__LABEL = SQLTablesPackage.BASE_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__COMMENTS = SQLTablesPackage.BASE_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__EXTENSIONS = SQLTablesPackage.BASE_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__PRIVILEGES = SQLTablesPackage.BASE_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__COLUMNS = SQLTablesPackage.BASE_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__SUPERTABLE = SQLTablesPackage.BASE_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__SUBTABLES = SQLTablesPackage.BASE_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__SCHEMA = SQLTablesPackage.BASE_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__UDT = SQLTablesPackage.BASE_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__TRIGGERS = SQLTablesPackage.BASE_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__INDEX = SQLTablesPackage.BASE_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.BASE_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__INSERTABLE = SQLTablesPackage.BASE_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__UPDATABLE = SQLTablesPackage.BASE_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__CONSTRAINTS = SQLTablesPackage.BASE_TABLE__CONSTRAINTS;

	/**
	 * The feature id for the '<em><b>Referencing Foreign Keys</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE__REFERENCING_FOREIGN_KEYS = SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS;

	/**
	 * The number of structural features of the '<em>Sybase Base Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_BASE_TABLE_FEATURE_COUNT = SQLTablesPackage.BASE_TABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseViewTableImpl <em>Sybase View Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseViewTableImpl
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseViewTable()
	 * @generated
	 */
	int SYBASE_VIEW_TABLE = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__EANNOTATIONS = SQLTablesPackage.VIEW_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__NAME = SQLTablesPackage.VIEW_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__DEPENDENCIES = SQLTablesPackage.VIEW_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__DESCRIPTION = SQLTablesPackage.VIEW_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__LABEL = SQLTablesPackage.VIEW_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__COMMENTS = SQLTablesPackage.VIEW_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__EXTENSIONS = SQLTablesPackage.VIEW_TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__PRIVILEGES = SQLTablesPackage.VIEW_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__COLUMNS = SQLTablesPackage.VIEW_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__SUPERTABLE = SQLTablesPackage.VIEW_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__SUBTABLES = SQLTablesPackage.VIEW_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__SCHEMA = SQLTablesPackage.VIEW_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__UDT = SQLTablesPackage.VIEW_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__TRIGGERS = SQLTablesPackage.VIEW_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__INDEX = SQLTablesPackage.VIEW_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.VIEW_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__INSERTABLE = SQLTablesPackage.VIEW_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__UPDATABLE = SQLTablesPackage.VIEW_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__QUERY_EXPRESSION = SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Check Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE__CHECK_TYPE = SQLTablesPackage.VIEW_TABLE__CHECK_TYPE;

	/**
	 * The number of structural features of the '<em>Sybase View Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_VIEW_TABLE_FEATURE_COUNT = SQLTablesPackage.VIEW_TABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseAuthorizationIdentifierImpl <em>Sybase Authorization Identifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseAuthorizationIdentifierImpl
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseAuthorizationIdentifier()
	 * @generated
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__EANNOTATIONS = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__NAME = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__DEPENDENCIES = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__DESCRIPTION = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__LABEL = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__COMMENTS = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__EXTENSIONS = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__PRIVILEGES = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Owned Schema</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__DATABASE = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DATABASE;

	/**
	 * The feature id for the '<em><b>Received Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_ROLE_AUTHORIZATION;

	/**
	 * The feature id for the '<em><b>Granted Role Authorization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_ROLE_AUTHORIZATION;

	/**
	 * The feature id for the '<em><b>Granted Privilege</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__GRANTED_PRIVILEGE;

	/**
	 * The feature id for the '<em><b>Received Privilege</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE;

	/**
	 * The feature id for the '<em><b>Sql Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sybase Authorization Identifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZATION_IDENTIFIER_FEATURE_COUNT = SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseIndexMemberImpl <em>Sybase Index Member</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseIndexMemberImpl
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseIndexMember()
	 * @generated
	 */
	int SYBASE_INDEX_MEMBER = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__EANNOTATIONS = SQLConstraintsPackage.INDEX_MEMBER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__NAME = SQLConstraintsPackage.INDEX_MEMBER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__DEPENDENCIES = SQLConstraintsPackage.INDEX_MEMBER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__DESCRIPTION = SQLConstraintsPackage.INDEX_MEMBER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__LABEL = SQLConstraintsPackage.INDEX_MEMBER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__COMMENTS = SQLConstraintsPackage.INDEX_MEMBER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__EXTENSIONS = SQLConstraintsPackage.INDEX_MEMBER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__PRIVILEGES = SQLConstraintsPackage.INDEX_MEMBER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Increment Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__INCREMENT_TYPE = SQLConstraintsPackage.INDEX_MEMBER__INCREMENT_TYPE;

	/**
	 * The feature id for the '<em><b>Column</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__COLUMN = SQLConstraintsPackage.INDEX_MEMBER__COLUMN;

	/**
	 * The feature id for the '<em><b>Column Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER__COLUMN_EXPRESSION = SQLConstraintsPackage.INDEX_MEMBER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sybase Index Member</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_INDEX_MEMBER_FEATURE_COUNT = SQLConstraintsPackage.INDEX_MEMBER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject <em>Sybase Authorized Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseAuthorizedObject()
	 * @generated
	 */
	int SYBASE_AUTHORIZED_OBJECT = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZED_OBJECT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZED_OBJECT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZED_OBJECT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZED_OBJECT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZED_OBJECT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZED_OBJECT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZED_OBJECT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZED_OBJECT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The number of structural features of the '<em>Sybase Authorized Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_AUTHORIZED_OBJECT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasePrivilegeImpl <em>Sybase Privilege</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasePrivilegeImpl
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybasePrivilege()
	 * @generated
	 */
	int SYBASE_PRIVILEGE = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__EANNOTATIONS = SQLAccessControlPackage.PRIVILEGE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__NAME = SQLAccessControlPackage.PRIVILEGE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__DEPENDENCIES = SQLAccessControlPackage.PRIVILEGE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__DESCRIPTION = SQLAccessControlPackage.PRIVILEGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__LABEL = SQLAccessControlPackage.PRIVILEGE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__COMMENTS = SQLAccessControlPackage.PRIVILEGE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__EXTENSIONS = SQLAccessControlPackage.PRIVILEGE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__PRIVILEGES = SQLAccessControlPackage.PRIVILEGE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Grantable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__GRANTABLE = SQLAccessControlPackage.PRIVILEGE__GRANTABLE;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__ACTION = SQLAccessControlPackage.PRIVILEGE__ACTION;

	/**
	 * The feature id for the '<em><b>With Hierarchy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__WITH_HIERARCHY = SQLAccessControlPackage.PRIVILEGE__WITH_HIERARCHY;

	/**
	 * The feature id for the '<em><b>Grantor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__GRANTOR = SQLAccessControlPackage.PRIVILEGE__GRANTOR;

	/**
	 * The feature id for the '<em><b>Grantee</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__GRANTEE = SQLAccessControlPackage.PRIVILEGE__GRANTEE;

	/**
	 * The feature id for the '<em><b>Action Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__ACTION_OBJECTS = SQLAccessControlPackage.PRIVILEGE__ACTION_OBJECTS;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__OBJECT = SQLAccessControlPackage.PRIVILEGE__OBJECT;

	/**
	 * The feature id for the '<em><b>Revoked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE__REVOKED = SQLAccessControlPackage.PRIVILEGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sybase Privilege</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYBASE_PRIVILEGE_FEATURE_COUNT = SQLAccessControlPackage.PRIVILEGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType <em>JDBC Parameter Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getJDBCParameterType()
	 * @generated
	 */
	int JDBC_PARAMETER_TYPE = 8;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter <em>Sybase Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase Parameter</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter
	 * @generated
	 */
	EClass getSybaseParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#isNullable <em>Nullable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nullable</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#isNullable()
	 * @see #getSybaseParameter()
	 * @generated
	 */
	EAttribute getSybaseParameter_Nullable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#getDefaultValue()
	 * @see #getSybaseParameter()
	 * @generated
	 */
	EAttribute getSybaseParameter_DefaultValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#getJDBCParameterType <em>JDBC Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>JDBC Parameter Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter#getJDBCParameterType()
	 * @see #getSybaseParameter()
	 * @generated
	 */
	EAttribute getSybaseParameter_JDBCParameterType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine <em>Sybase Routine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase Routine</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine
	 * @generated
	 */
	EClass getSybaseRoutine();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable <em>Sybase Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase Base Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable
	 * @generated
	 */
	EClass getSybaseBaseTable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable <em>Sybase View Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase View Table</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable
	 * @generated
	 */
	EClass getSybaseViewTable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier <em>Sybase Authorization Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase Authorization Identifier</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier
	 * @generated
	 */
	EClass getSybaseAuthorizationIdentifier();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier#getSqlContainer <em>Sql Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sql Container</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier#getSqlContainer()
	 * @see #getSybaseAuthorizationIdentifier()
	 * @generated
	 */
	EReference getSybaseAuthorizationIdentifier_SqlContainer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember <em>Sybase Index Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase Index Member</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember
	 * @generated
	 */
	EClass getSybaseIndexMember();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember#getColumnExpression <em>Column Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Expression</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember#getColumnExpression()
	 * @see #getSybaseIndexMember()
	 * @generated
	 */
	EAttribute getSybaseIndexMember_ColumnExpression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject <em>Sybase Authorized Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase Authorized Object</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject
	 * @generated
	 */
	EClass getSybaseAuthorizedObject();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege <em>Sybase Privilege</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sybase Privilege</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege
	 * @generated
	 */
	EClass getSybasePrivilege();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege#isRevoked <em>Revoked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Revoked</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege#isRevoked()
	 * @see #getSybasePrivilege()
	 * @generated
	 */
	EAttribute getSybasePrivilege_Revoked();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType <em>JDBC Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>JDBC Parameter Type</em>'.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType
	 * @generated
	 */
	EEnum getJDBCParameterType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SybasesqlmodelFactory getSybasesqlmodelFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseParameterImpl <em>Sybase Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseParameterImpl
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseParameter()
		 * @generated
		 */
		EClass SYBASE_PARAMETER = eINSTANCE.getSybaseParameter();

		/**
		 * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_PARAMETER__NULLABLE = eINSTANCE.getSybaseParameter_Nullable();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_PARAMETER__DEFAULT_VALUE = eINSTANCE.getSybaseParameter_DefaultValue();

		/**
		 * The meta object literal for the '<em><b>JDBC Parameter Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_PARAMETER__JDBC_PARAMETER_TYPE = eINSTANCE.getSybaseParameter_JDBCParameterType();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseRoutineImpl <em>Sybase Routine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseRoutineImpl
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseRoutine()
		 * @generated
		 */
		EClass SYBASE_ROUTINE = eINSTANCE.getSybaseRoutine();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseBaseTableImpl <em>Sybase Base Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseBaseTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseBaseTable()
		 * @generated
		 */
		EClass SYBASE_BASE_TABLE = eINSTANCE.getSybaseBaseTable();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseViewTableImpl <em>Sybase View Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseViewTableImpl
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseViewTable()
		 * @generated
		 */
		EClass SYBASE_VIEW_TABLE = eINSTANCE.getSybaseViewTable();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseAuthorizationIdentifierImpl <em>Sybase Authorization Identifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseAuthorizationIdentifierImpl
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseAuthorizationIdentifier()
		 * @generated
		 */
		EClass SYBASE_AUTHORIZATION_IDENTIFIER = eINSTANCE.getSybaseAuthorizationIdentifier();

		/**
		 * The meta object literal for the '<em><b>Sql Container</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER = eINSTANCE.getSybaseAuthorizationIdentifier_SqlContainer();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseIndexMemberImpl <em>Sybase Index Member</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybaseIndexMemberImpl
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseIndexMember()
		 * @generated
		 */
		EClass SYBASE_INDEX_MEMBER = eINSTANCE.getSybaseIndexMember();

		/**
		 * The meta object literal for the '<em><b>Column Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_INDEX_MEMBER__COLUMN_EXPRESSION = eINSTANCE.getSybaseIndexMember_ColumnExpression();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject <em>Sybase Authorized Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybaseAuthorizedObject()
		 * @generated
		 */
		EClass SYBASE_AUTHORIZED_OBJECT = eINSTANCE.getSybaseAuthorizedObject();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasePrivilegeImpl <em>Sybase Privilege</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasePrivilegeImpl
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getSybasePrivilege()
		 * @generated
		 */
		EClass SYBASE_PRIVILEGE = eINSTANCE.getSybasePrivilege();

		/**
		 * The meta object literal for the '<em><b>Revoked</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYBASE_PRIVILEGE__REVOKED = eINSTANCE.getSybasePrivilege_Revoked();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType <em>JDBC Parameter Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType
		 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl.SybasesqlmodelPackageImpl#getJDBCParameterType()
		 * @generated
		 */
		EEnum JDBC_PARAMETER_TYPE = eINSTANCE.getJDBCParameterType();

	}

} //SybasesqlmodelPackage
