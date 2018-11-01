/**
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *   Ingres Corporation - initial API and implementation
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ingres.models.ingressqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;

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
 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelFactory
 * @model kind="package"
 * @generated
 */
public interface IngressqlmodelPackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2008 Ingres Corporation and others.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 2.0\r\nwhich accompanies this distribution, and is available at\r\nhttps://www.eclipse.org/legal/epl-2.0/\r\n\r\nContributors:\r\n  Ingres Corporation - initial API and implementation";

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ingressqlmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/enablement/ingres/ingressqlmodel.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "IngresModel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IngressqlmodelPackage eINSTANCE = org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSynonymImpl <em>Ingres Synonym</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSynonymImpl
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresSynonym()
	 * @generated
	 */
	int INGRES_SYNONYM = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Table Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM__TABLE_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Ingres Synonym</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SYNONYM_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresDBEventImpl <em>Ingres DB Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresDBEventImpl
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresDBEvent()
	 * @generated
	 */
	int INGRES_DB_EVENT = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_DB_EVENT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_DB_EVENT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_DB_EVENT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_DB_EVENT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_DB_EVENT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_DB_EVENT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_DB_EVENT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_DB_EVENT__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ingres DB Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_DB_EVENT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSchemaImpl <em>Ingres Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSchemaImpl
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresSchema()
	 * @generated
	 */
	int INGRES_SCHEMA = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__EANNOTATIONS = SQLSchemaPackage.SCHEMA__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__NAME = SQLSchemaPackage.SCHEMA__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__DEPENDENCIES = SQLSchemaPackage.SCHEMA__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__DESCRIPTION = SQLSchemaPackage.SCHEMA__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__LABEL = SQLSchemaPackage.SCHEMA__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__COMMENTS = SQLSchemaPackage.SCHEMA__COMMENTS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__PRIVILEGES = SQLSchemaPackage.SCHEMA__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__TRIGGERS = SQLSchemaPackage.SCHEMA__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Indices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__INDICES = SQLSchemaPackage.SCHEMA__INDICES;

	/**
	 * The feature id for the '<em><b>Tables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__TABLES = SQLSchemaPackage.SCHEMA__TABLES;

	/**
	 * The feature id for the '<em><b>Sequences</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__SEQUENCES = SQLSchemaPackage.SCHEMA__SEQUENCES;

	/**
	 * The feature id for the '<em><b>Database</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__DATABASE = SQLSchemaPackage.SCHEMA__DATABASE;

	/**
	 * The feature id for the '<em><b>Catalog</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__CATALOG = SQLSchemaPackage.SCHEMA__CATALOG;

	/**
	 * The feature id for the '<em><b>Assertions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__ASSERTIONS = SQLSchemaPackage.SCHEMA__ASSERTIONS;

	/**
	 * The feature id for the '<em><b>User Defined Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__USER_DEFINED_TYPES = SQLSchemaPackage.SCHEMA__USER_DEFINED_TYPES;

	/**
	 * The feature id for the '<em><b>Char Sets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__CHAR_SETS = SQLSchemaPackage.SCHEMA__CHAR_SETS;

	/**
	 * The feature id for the '<em><b>Routines</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__ROUTINES = SQLSchemaPackage.SCHEMA__ROUTINES;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__OWNER = SQLSchemaPackage.SCHEMA__OWNER;

	/**
	 * The feature id for the '<em><b>DB Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__DB_EVENTS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Synonyms</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA__SYNONYMS = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Ingres Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_SCHEMA_FEATURE_COUNT = SQLSchemaPackage.SCHEMA_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresViewTableImpl <em>Ingres View Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresViewTableImpl
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresViewTable()
	 * @generated
	 */
	int INGRES_VIEW_TABLE = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__EANNOTATIONS = SQLTablesPackage.VIEW_TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__NAME = SQLTablesPackage.VIEW_TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__DEPENDENCIES = SQLTablesPackage.VIEW_TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__DESCRIPTION = SQLTablesPackage.VIEW_TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__LABEL = SQLTablesPackage.VIEW_TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__COMMENTS = SQLTablesPackage.VIEW_TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__PRIVILEGES = SQLTablesPackage.VIEW_TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__COLUMNS = SQLTablesPackage.VIEW_TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__SUPERTABLE = SQLTablesPackage.VIEW_TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__SUBTABLES = SQLTablesPackage.VIEW_TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__SCHEMA = SQLTablesPackage.VIEW_TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__UDT = SQLTablesPackage.VIEW_TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__TRIGGERS = SQLTablesPackage.VIEW_TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__INDEX = SQLTablesPackage.VIEW_TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.VIEW_TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__INSERTABLE = SQLTablesPackage.VIEW_TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__UPDATABLE = SQLTablesPackage.VIEW_TABLE__UPDATABLE;

	/**
	 * The feature id for the '<em><b>Query Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__QUERY_EXPRESSION = SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Check Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__CHECK_TYPE = SQLTablesPackage.VIEW_TABLE__CHECK_TYPE;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE__SOURCE = SQLTablesPackage.VIEW_TABLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ingres View Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_VIEW_TABLE_FEATURE_COUNT = SQLTablesPackage.VIEW_TABLE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresTriggerImpl <em>Ingres Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresTriggerImpl
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresTrigger()
	 * @generated
	 */
	int INGRES_TRIGGER = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__EANNOTATIONS = SQLTablesPackage.TRIGGER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__NAME = SQLTablesPackage.TRIGGER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__DEPENDENCIES = SQLTablesPackage.TRIGGER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__DESCRIPTION = SQLTablesPackage.TRIGGER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__LABEL = SQLTablesPackage.TRIGGER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__COMMENTS = SQLTablesPackage.TRIGGER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__PRIVILEGES = SQLTablesPackage.TRIGGER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__SCHEMA = SQLTablesPackage.TRIGGER__SCHEMA;

	/**
	 * The feature id for the '<em><b>Subject Table</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__SUBJECT_TABLE = SQLTablesPackage.TRIGGER__SUBJECT_TABLE;

	/**
	 * The feature id for the '<em><b>Action Statement</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__ACTION_STATEMENT = SQLTablesPackage.TRIGGER__ACTION_STATEMENT;

	/**
	 * The feature id for the '<em><b>Trigger Column</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__TRIGGER_COLUMN = SQLTablesPackage.TRIGGER__TRIGGER_COLUMN;

	/**
	 * The feature id for the '<em><b>Action Granularity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__ACTION_GRANULARITY = SQLTablesPackage.TRIGGER__ACTION_GRANULARITY;

	/**
	 * The feature id for the '<em><b>When</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__WHEN = SQLTablesPackage.TRIGGER__WHEN;

	/**
	 * The feature id for the '<em><b>Time Stamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__TIME_STAMP = SQLTablesPackage.TRIGGER__TIME_STAMP;

	/**
	 * The feature id for the '<em><b>Action Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__ACTION_TIME = SQLTablesPackage.TRIGGER__ACTION_TIME;

	/**
	 * The feature id for the '<em><b>Update Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__UPDATE_TYPE = SQLTablesPackage.TRIGGER__UPDATE_TYPE;

	/**
	 * The feature id for the '<em><b>Insert Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__INSERT_TYPE = SQLTablesPackage.TRIGGER__INSERT_TYPE;

	/**
	 * The feature id for the '<em><b>Delete Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__DELETE_TYPE = SQLTablesPackage.TRIGGER__DELETE_TYPE;

	/**
	 * The feature id for the '<em><b>Old Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__OLD_ROW = SQLTablesPackage.TRIGGER__OLD_ROW;

	/**
	 * The feature id for the '<em><b>New Row</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__NEW_ROW = SQLTablesPackage.TRIGGER__NEW_ROW;

	/**
	 * The feature id for the '<em><b>Old Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__OLD_TABLE = SQLTablesPackage.TRIGGER__OLD_TABLE;

	/**
	 * The feature id for the '<em><b>New Table</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__NEW_TABLE = SQLTablesPackage.TRIGGER__NEW_TABLE;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER__SOURCE = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ingres Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_TRIGGER_FEATURE_COUNT = SQLTablesPackage.TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl <em>Ingres Identity Specifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresIdentitySpecifier()
	 * @generated
	 */
	int INGRES_IDENTITY_SPECIFIER = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__EANNOTATIONS = SQLSchemaPackage.IDENTITY_SPECIFIER__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__NAME = SQLSchemaPackage.IDENTITY_SPECIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__DEPENDENCIES = SQLSchemaPackage.IDENTITY_SPECIFIER__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__DESCRIPTION = SQLSchemaPackage.IDENTITY_SPECIFIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__LABEL = SQLSchemaPackage.IDENTITY_SPECIFIER__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__COMMENTS = SQLSchemaPackage.IDENTITY_SPECIFIER__COMMENTS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__PRIVILEGES = SQLSchemaPackage.IDENTITY_SPECIFIER__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Generation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__GENERATION_TYPE = SQLSchemaPackage.IDENTITY_SPECIFIER__GENERATION_TYPE;

	/**
	 * The feature id for the '<em><b>Start Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__START_VALUE = SQLSchemaPackage.IDENTITY_SPECIFIER__START_VALUE;

	/**
	 * The feature id for the '<em><b>Increment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__INCREMENT = SQLSchemaPackage.IDENTITY_SPECIFIER__INCREMENT;

	/**
	 * The feature id for the '<em><b>Minimum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__MINIMUM = SQLSchemaPackage.IDENTITY_SPECIFIER__MINIMUM;

	/**
	 * The feature id for the '<em><b>Maximum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__MAXIMUM = SQLSchemaPackage.IDENTITY_SPECIFIER__MAXIMUM;

	/**
	 * The feature id for the '<em><b>Cycle Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__CYCLE_OPTION = SQLSchemaPackage.IDENTITY_SPECIFIER__CYCLE_OPTION;

	/**
	 * The feature id for the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__DATA_TYPE = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Seq Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__SEQ_LENGTH = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Seq Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__SEQ_PRECISION = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Maximum Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__MAXIMUM_OPTION = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Minimum Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__MINIMUM_OPTION = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Cache Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__CACHE_SIZE = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Cache Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__CACHE_OPTION = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Order Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER__ORDER_OPTION = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Ingres Identity Specifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INGRES_IDENTITY_SPECIFIER_FEATURE_COUNT = SQLSchemaPackage.IDENTITY_SPECIFIER_FEATURE_COUNT + 8;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym <em>Ingres Synonym</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ingres Synonym</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym
	 * @generated
	 */
	EClass getIngresSynonym();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym#getSchema()
	 * @see #getIngresSynonym()
	 * @generated
	 */
	EReference getIngresSynonym_Schema();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym#getTableName <em>Table Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table Name</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym#getTableName()
	 * @see #getIngresSynonym()
	 * @generated
	 */
	EAttribute getIngresSynonym_TableName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent <em>Ingres DB Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ingres DB Event</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent
	 * @generated
	 */
	EClass getIngresDBEvent();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent#getSchema()
	 * @see #getIngresDBEvent()
	 * @generated
	 */
	EReference getIngresDBEvent_Schema();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema <em>Ingres Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ingres Schema</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema
	 * @generated
	 */
	EClass getIngresSchema();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema#getDBEvents <em>DB Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>DB Events</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema#getDBEvents()
	 * @see #getIngresSchema()
	 * @generated
	 */
	EReference getIngresSchema_DBEvents();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema#getSynonyms <em>Synonyms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Synonyms</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema#getSynonyms()
	 * @see #getIngresSchema()
	 * @generated
	 */
	EReference getIngresSchema_Synonyms();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresViewTable <em>Ingres View Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ingres View Table</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresViewTable
	 * @generated
	 */
	EClass getIngresViewTable();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresViewTable#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresViewTable#getSource()
	 * @see #getIngresViewTable()
	 * @generated
	 */
	EReference getIngresViewTable_Source();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresTrigger <em>Ingres Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ingres Trigger</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresTrigger
	 * @generated
	 */
	EClass getIngresTrigger();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresTrigger#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresTrigger#getSource()
	 * @see #getIngresTrigger()
	 * @generated
	 */
	EReference getIngresTrigger_Source();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier <em>Ingres Identity Specifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ingres Identity Specifier</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier
	 * @generated
	 */
	EClass getIngresIdentitySpecifier();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getDataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Type</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getDataType()
	 * @see #getIngresIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIngresIdentitySpecifier_DataType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getSeqLength <em>Seq Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seq Length</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getSeqLength()
	 * @see #getIngresIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIngresIdentitySpecifier_SeqLength();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getSeqPrecision <em>Seq Precision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Seq Precision</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getSeqPrecision()
	 * @see #getIngresIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIngresIdentitySpecifier_SeqPrecision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getMaximumOption <em>Maximum Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Maximum Option</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getMaximumOption()
	 * @see #getIngresIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIngresIdentitySpecifier_MaximumOption();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getMinimumOption <em>Minimum Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minimum Option</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getMinimumOption()
	 * @see #getIngresIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIngresIdentitySpecifier_MinimumOption();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getCacheSize <em>Cache Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cache Size</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getCacheSize()
	 * @see #getIngresIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIngresIdentitySpecifier_CacheSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getCacheOption <em>Cache Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cache Option</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getCacheOption()
	 * @see #getIngresIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIngresIdentitySpecifier_CacheOption();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getOrderOption <em>Order Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order Option</em>'.
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getOrderOption()
	 * @see #getIngresIdentitySpecifier()
	 * @generated
	 */
	EAttribute getIngresIdentitySpecifier_OrderOption();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IngressqlmodelFactory getIngressqlmodelFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSynonymImpl <em>Ingres Synonym</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSynonymImpl
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresSynonym()
		 * @generated
		 */
		EClass INGRES_SYNONYM = eINSTANCE.getIngresSynonym();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INGRES_SYNONYM__SCHEMA = eINSTANCE.getIngresSynonym_Schema();

		/**
		 * The meta object literal for the '<em><b>Table Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INGRES_SYNONYM__TABLE_NAME = eINSTANCE.getIngresSynonym_TableName();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresDBEventImpl <em>Ingres DB Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresDBEventImpl
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresDBEvent()
		 * @generated
		 */
		EClass INGRES_DB_EVENT = eINSTANCE.getIngresDBEvent();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INGRES_DB_EVENT__SCHEMA = eINSTANCE.getIngresDBEvent_Schema();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSchemaImpl <em>Ingres Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresSchemaImpl
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresSchema()
		 * @generated
		 */
		EClass INGRES_SCHEMA = eINSTANCE.getIngresSchema();

		/**
		 * The meta object literal for the '<em><b>DB Events</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INGRES_SCHEMA__DB_EVENTS = eINSTANCE.getIngresSchema_DBEvents();

		/**
		 * The meta object literal for the '<em><b>Synonyms</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INGRES_SCHEMA__SYNONYMS = eINSTANCE.getIngresSchema_Synonyms();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresViewTableImpl <em>Ingres View Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresViewTableImpl
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresViewTable()
		 * @generated
		 */
		EClass INGRES_VIEW_TABLE = eINSTANCE.getIngresViewTable();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INGRES_VIEW_TABLE__SOURCE = eINSTANCE.getIngresViewTable_Source();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresTriggerImpl <em>Ingres Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresTriggerImpl
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresTrigger()
		 * @generated
		 */
		EClass INGRES_TRIGGER = eINSTANCE.getIngresTrigger();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INGRES_TRIGGER__SOURCE = eINSTANCE.getIngresTrigger_Source();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl <em>Ingres Identity Specifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngresIdentitySpecifierImpl
		 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.impl.IngressqlmodelPackageImpl#getIngresIdentitySpecifier()
		 * @generated
		 */
		EClass INGRES_IDENTITY_SPECIFIER = eINSTANCE.getIngresIdentitySpecifier();

		/**
		 * The meta object literal for the '<em><b>Data Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INGRES_IDENTITY_SPECIFIER__DATA_TYPE = eINSTANCE.getIngresIdentitySpecifier_DataType();

		/**
		 * The meta object literal for the '<em><b>Seq Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INGRES_IDENTITY_SPECIFIER__SEQ_LENGTH = eINSTANCE.getIngresIdentitySpecifier_SeqLength();

		/**
		 * The meta object literal for the '<em><b>Seq Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INGRES_IDENTITY_SPECIFIER__SEQ_PRECISION = eINSTANCE.getIngresIdentitySpecifier_SeqPrecision();

		/**
		 * The meta object literal for the '<em><b>Maximum Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INGRES_IDENTITY_SPECIFIER__MAXIMUM_OPTION = eINSTANCE.getIngresIdentitySpecifier_MaximumOption();

		/**
		 * The meta object literal for the '<em><b>Minimum Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INGRES_IDENTITY_SPECIFIER__MINIMUM_OPTION = eINSTANCE.getIngresIdentitySpecifier_MinimumOption();

		/**
		 * The meta object literal for the '<em><b>Cache Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INGRES_IDENTITY_SPECIFIER__CACHE_SIZE = eINSTANCE.getIngresIdentitySpecifier_CacheSize();

		/**
		 * The meta object literal for the '<em><b>Cache Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INGRES_IDENTITY_SPECIFIER__CACHE_OPTION = eINSTANCE.getIngresIdentitySpecifier_CacheOption();

		/**
		 * The meta object literal for the '<em><b>Order Option</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INGRES_IDENTITY_SPECIFIER__ORDER_OPTION = eINSTANCE.getIngresIdentitySpecifier_OrderOption();

	}

} //IngressqlmodelPackage
