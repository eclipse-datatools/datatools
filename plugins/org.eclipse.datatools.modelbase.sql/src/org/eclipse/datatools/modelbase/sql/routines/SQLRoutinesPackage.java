/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.routines;

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
 * @see org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory
 * @model kind="package"
 * @generated
 */
public interface SQLRoutinesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "routines"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/routines.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SQLRoutines"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLRoutinesPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl <em>Routine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getRoutine()
	 * @generated
	 */
	int ROUTINE = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__SPECIFIC_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__LANGUAGE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__PARAMETER_STYLE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__DETERMINISTIC = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__SQL_DATA_ACCESS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__CREATION_TS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__LAST_ALTERED_TS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__AUTHORIZATION_ID = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__SECURITY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__EXTERNAL_NAME = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__PARAMETERS = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__SOURCE = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE__SCHEMA = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Routine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.SourceImpl <em>Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SourceImpl
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getSource()
	 * @generated
	 */
	int SOURCE = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE__BODY = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOURCE_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__EANNOTATIONS = SQLSchemaPackage.TYPED_ELEMENT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = SQLSchemaPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DEPENDENCIES = SQLSchemaPackage.TYPED_ELEMENT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DESCRIPTION = SQLSchemaPackage.TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__LABEL = SQLSchemaPackage.TYPED_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__COMMENTS = SQLSchemaPackage.TYPED_ELEMENT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__EXTENSIONS = SQLSchemaPackage.TYPED_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__PRIVILEGES = SQLSchemaPackage.TYPED_ELEMENT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Contained Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__CONTAINED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__CONTAINED_TYPE;

	/**
	 * The feature id for the '<em><b>Referenced Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__REFERENCED_TYPE = SQLSchemaPackage.TYPED_ELEMENT__REFERENCED_TYPE;

	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__MODE = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Locator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__LOCATOR = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Routine</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__ROUTINE = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>String Type Option</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__STRING_TYPE_OPTION = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = SQLSchemaPackage.TYPED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl <em>Procedure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getProcedure()
	 * @generated
	 */
	int PROCEDURE = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__EANNOTATIONS = ROUTINE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__NAME = ROUTINE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__DEPENDENCIES = ROUTINE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__DESCRIPTION = ROUTINE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__LABEL = ROUTINE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__COMMENTS = ROUTINE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__EXTENSIONS = ROUTINE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__PRIVILEGES = ROUTINE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__SPECIFIC_NAME = ROUTINE__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__LANGUAGE = ROUTINE__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__PARAMETER_STYLE = ROUTINE__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__DETERMINISTIC = ROUTINE__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__SQL_DATA_ACCESS = ROUTINE__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__CREATION_TS = ROUTINE__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__LAST_ALTERED_TS = ROUTINE__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__AUTHORIZATION_ID = ROUTINE__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__SECURITY = ROUTINE__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__EXTERNAL_NAME = ROUTINE__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__PARAMETERS = ROUTINE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__SOURCE = ROUTINE__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__SCHEMA = ROUTINE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Max Result Sets</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__MAX_RESULT_SETS = ROUTINE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Old Save Point</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__OLD_SAVE_POINT = ROUTINE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Result Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE__RESULT_SET = ROUTINE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Procedure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCEDURE_FEATURE_COUNT = ROUTINE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__EANNOTATIONS = ROUTINE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__NAME = ROUTINE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DEPENDENCIES = ROUTINE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DESCRIPTION = ROUTINE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__LABEL = ROUTINE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__COMMENTS = ROUTINE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__EXTENSIONS = ROUTINE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__PRIVILEGES = ROUTINE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SPECIFIC_NAME = ROUTINE__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__LANGUAGE = ROUTINE__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__PARAMETER_STYLE = ROUTINE__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DETERMINISTIC = ROUTINE__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SQL_DATA_ACCESS = ROUTINE__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__CREATION_TS = ROUTINE__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__LAST_ALTERED_TS = ROUTINE__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__AUTHORIZATION_ID = ROUTINE__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SECURITY = ROUTINE__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__EXTERNAL_NAME = ROUTINE__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__PARAMETERS = ROUTINE__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SOURCE = ROUTINE__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SCHEMA = ROUTINE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Null Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__NULL_CALL = ROUTINE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__STATIC = ROUTINE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Transform Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__TRANSFORM_GROUP = ROUTINE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type Preserving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__TYPE_PRESERVING = ROUTINE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Mutator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__MUTATOR = ROUTINE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Return Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__RETURN_TABLE = ROUTINE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Return Scalar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__RETURN_SCALAR = ROUTINE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Return Cast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__RETURN_CAST = ROUTINE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = ROUTINE_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineResultTableImpl <em>Routine Result Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.RoutineResultTableImpl
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getRoutineResultTable()
	 * @generated
	 */
	int ROUTINE_RESULT_TABLE = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__EANNOTATIONS = SQLTablesPackage.TABLE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__NAME = SQLTablesPackage.TABLE__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__DEPENDENCIES = SQLTablesPackage.TABLE__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__DESCRIPTION = SQLTablesPackage.TABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__LABEL = SQLTablesPackage.TABLE__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__COMMENTS = SQLTablesPackage.TABLE__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__EXTENSIONS = SQLTablesPackage.TABLE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__PRIVILEGES = SQLTablesPackage.TABLE__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__COLUMNS = SQLTablesPackage.TABLE__COLUMNS;

	/**
	 * The feature id for the '<em><b>Supertable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__SUPERTABLE = SQLTablesPackage.TABLE__SUPERTABLE;

	/**
	 * The feature id for the '<em><b>Subtables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__SUBTABLES = SQLTablesPackage.TABLE__SUBTABLES;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__SCHEMA = SQLTablesPackage.TABLE__SCHEMA;

	/**
	 * The feature id for the '<em><b>Udt</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__UDT = SQLTablesPackage.TABLE__UDT;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__TRIGGERS = SQLTablesPackage.TABLE__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__INDEX = SQLTablesPackage.TABLE__INDEX;

	/**
	 * The feature id for the '<em><b>Self Ref Column Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__SELF_REF_COLUMN_GENERATION = SQLTablesPackage.TABLE__SELF_REF_COLUMN_GENERATION;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__INSERTABLE = SQLTablesPackage.TABLE__INSERTABLE;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE__UPDATABLE = SQLTablesPackage.TABLE__UPDATABLE;

	/**
	 * The number of structural features of the '<em>Routine Result Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROUTINE_RESULT_TABLE_FEATURE_COUNT = SQLTablesPackage.TABLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.MethodImpl <em>Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.MethodImpl
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getMethod()
	 * @generated
	 */
	int METHOD = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__EANNOTATIONS = FUNCTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__NAME = FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__DEPENDENCIES = FUNCTION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__DESCRIPTION = FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__LABEL = FUNCTION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__COMMENTS = FUNCTION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__EXTENSIONS = FUNCTION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__PRIVILEGES = FUNCTION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__SPECIFIC_NAME = FUNCTION__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__LANGUAGE = FUNCTION__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__PARAMETER_STYLE = FUNCTION__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__DETERMINISTIC = FUNCTION__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__SQL_DATA_ACCESS = FUNCTION__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__CREATION_TS = FUNCTION__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__LAST_ALTERED_TS = FUNCTION__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__AUTHORIZATION_ID = FUNCTION__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__SECURITY = FUNCTION__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__EXTERNAL_NAME = FUNCTION__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__PARAMETERS = FUNCTION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__SOURCE = FUNCTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__SCHEMA = FUNCTION__SCHEMA;

	/**
	 * The feature id for the '<em><b>Null Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__NULL_CALL = FUNCTION__NULL_CALL;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__STATIC = FUNCTION__STATIC;

	/**
	 * The feature id for the '<em><b>Transform Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__TRANSFORM_GROUP = FUNCTION__TRANSFORM_GROUP;

	/**
	 * The feature id for the '<em><b>Type Preserving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__TYPE_PRESERVING = FUNCTION__TYPE_PRESERVING;

	/**
	 * The feature id for the '<em><b>Mutator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__MUTATOR = FUNCTION__MUTATOR;

	/**
	 * The feature id for the '<em><b>Return Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__RETURN_TABLE = FUNCTION__RETURN_TABLE;

	/**
	 * The feature id for the '<em><b>Return Scalar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__RETURN_SCALAR = FUNCTION__RETURN_SCALAR;

	/**
	 * The feature id for the '<em><b>Return Cast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__RETURN_CAST = FUNCTION__RETURN_CAST;

	/**
	 * The feature id for the '<em><b>Overriding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__OVERRIDING = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__CONSTRUCTOR = FUNCTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.UserDefinedFunctionImpl <em>User Defined Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.UserDefinedFunctionImpl
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getUserDefinedFunction()
	 * @generated
	 */
	int USER_DEFINED_FUNCTION = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__EANNOTATIONS = FUNCTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__NAME = FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__DEPENDENCIES = FUNCTION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__DESCRIPTION = FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__LABEL = FUNCTION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__COMMENTS = FUNCTION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__EXTENSIONS = FUNCTION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__PRIVILEGES = FUNCTION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__SPECIFIC_NAME = FUNCTION__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__LANGUAGE = FUNCTION__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__PARAMETER_STYLE = FUNCTION__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__DETERMINISTIC = FUNCTION__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__SQL_DATA_ACCESS = FUNCTION__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__CREATION_TS = FUNCTION__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__LAST_ALTERED_TS = FUNCTION__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__AUTHORIZATION_ID = FUNCTION__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__SECURITY = FUNCTION__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__EXTERNAL_NAME = FUNCTION__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__PARAMETERS = FUNCTION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__SOURCE = FUNCTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__SCHEMA = FUNCTION__SCHEMA;

	/**
	 * The feature id for the '<em><b>Null Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__NULL_CALL = FUNCTION__NULL_CALL;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__STATIC = FUNCTION__STATIC;

	/**
	 * The feature id for the '<em><b>Transform Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__TRANSFORM_GROUP = FUNCTION__TRANSFORM_GROUP;

	/**
	 * The feature id for the '<em><b>Type Preserving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__TYPE_PRESERVING = FUNCTION__TYPE_PRESERVING;

	/**
	 * The feature id for the '<em><b>Mutator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__MUTATOR = FUNCTION__MUTATOR;

	/**
	 * The feature id for the '<em><b>Return Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__RETURN_TABLE = FUNCTION__RETURN_TABLE;

	/**
	 * The feature id for the '<em><b>Return Scalar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__RETURN_SCALAR = FUNCTION__RETURN_SCALAR;

	/**
	 * The feature id for the '<em><b>Return Cast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION__RETURN_CAST = FUNCTION__RETURN_CAST;

	/**
	 * The number of structural features of the '<em>User Defined Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_DEFINED_FUNCTION_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.BuiltInFunctionImpl <em>Built In Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.BuiltInFunctionImpl
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getBuiltInFunction()
	 * @generated
	 */
	int BUILT_IN_FUNCTION = 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__EANNOTATIONS = FUNCTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__NAME = FUNCTION__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__DEPENDENCIES = FUNCTION__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__DESCRIPTION = FUNCTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__LABEL = FUNCTION__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__COMMENTS = FUNCTION__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__EXTENSIONS = FUNCTION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__PRIVILEGES = FUNCTION__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>Specific Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__SPECIFIC_NAME = FUNCTION__SPECIFIC_NAME;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__LANGUAGE = FUNCTION__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Parameter Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__PARAMETER_STYLE = FUNCTION__PARAMETER_STYLE;

	/**
	 * The feature id for the '<em><b>Deterministic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__DETERMINISTIC = FUNCTION__DETERMINISTIC;

	/**
	 * The feature id for the '<em><b>Sql Data Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__SQL_DATA_ACCESS = FUNCTION__SQL_DATA_ACCESS;

	/**
	 * The feature id for the '<em><b>Creation TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__CREATION_TS = FUNCTION__CREATION_TS;

	/**
	 * The feature id for the '<em><b>Last Altered TS</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__LAST_ALTERED_TS = FUNCTION__LAST_ALTERED_TS;

	/**
	 * The feature id for the '<em><b>Authorization ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__AUTHORIZATION_ID = FUNCTION__AUTHORIZATION_ID;

	/**
	 * The feature id for the '<em><b>Security</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__SECURITY = FUNCTION__SECURITY;

	/**
	 * The feature id for the '<em><b>External Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__EXTERNAL_NAME = FUNCTION__EXTERNAL_NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__PARAMETERS = FUNCTION__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__SOURCE = FUNCTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__SCHEMA = FUNCTION__SCHEMA;

	/**
	 * The feature id for the '<em><b>Null Call</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__NULL_CALL = FUNCTION__NULL_CALL;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__STATIC = FUNCTION__STATIC;

	/**
	 * The feature id for the '<em><b>Transform Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__TRANSFORM_GROUP = FUNCTION__TRANSFORM_GROUP;

	/**
	 * The feature id for the '<em><b>Type Preserving</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__TYPE_PRESERVING = FUNCTION__TYPE_PRESERVING;

	/**
	 * The feature id for the '<em><b>Mutator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__MUTATOR = FUNCTION__MUTATOR;

	/**
	 * The feature id for the '<em><b>Return Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__RETURN_TABLE = FUNCTION__RETURN_TABLE;

	/**
	 * The feature id for the '<em><b>Return Scalar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__RETURN_SCALAR = FUNCTION__RETURN_SCALAR;

	/**
	 * The feature id for the '<em><b>Return Cast</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION__RETURN_CAST = FUNCTION__RETURN_CAST;

	/**
	 * The number of structural features of the '<em>Built In Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUILT_IN_FUNCTION_FEATURE_COUNT = FUNCTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.DataAccess <em>Data Access</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.DataAccess
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getDataAccess()
	 * @generated
	 */
	int DATA_ACCESS = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.routines.ParameterMode <em>Parameter Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.routines.ParameterMode
	 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getParameterMode()
	 * @generated
	 */
	int PARAMETER_MODE = 10;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.routines.Routine <em>Routine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Routine</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine
	 * @generated
	 */
	EClass getRoutine();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSpecificName <em>Specific Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Specific Name</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getSpecificName()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_SpecificName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getLanguage()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_Language();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getParameterStyle <em>Parameter Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter Style</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getParameterStyle()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_ParameterStyle();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#isDeterministic <em>Deterministic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deterministic</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#isDeterministic()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_Deterministic();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSqlDataAccess <em>Sql Data Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sql Data Access</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getSqlDataAccess()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_SqlDataAccess();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getCreationTS <em>Creation TS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation TS</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getCreationTS()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_CreationTS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getLastAlteredTS <em>Last Altered TS</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Altered TS</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getLastAlteredTS()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_LastAlteredTS();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getAuthorizationID <em>Authorization ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Authorization ID</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getAuthorizationID()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_AuthorizationID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSecurity <em>Security</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Security</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getSecurity()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_Security();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getExternalName <em>External Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Name</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getExternalName()
	 * @see #getRoutine()
	 * @generated
	 */
	EAttribute getRoutine_ExternalName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getParameters()
	 * @see #getRoutine()
	 * @generated
	 */
	EReference getRoutine_Parameters();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getSource()
	 * @see #getRoutine()
	 * @generated
	 */
	EReference getRoutine_Source();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.routines.Routine#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schema</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine#getSchema()
	 * @see #getRoutine()
	 * @generated
	 */
	EReference getRoutine_Schema();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.routines.Source <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Source</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Source
	 * @generated
	 */
	EClass getSource();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Source#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Source#getBody()
	 * @see #getSource()
	 * @generated
	 */
	EAttribute getSource_Body();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getMode <em>Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Parameter#getMode()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Mode();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#isLocator <em>Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Locator</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Parameter#isLocator()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Locator();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getRoutine <em>Routine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Routine</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Parameter#getRoutine()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_Routine();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter#getStringTypeOption <em>String Type Option</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>String Type Option</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Parameter#getStringTypeOption()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_StringTypeOption();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.routines.Procedure <em>Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Procedure</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Procedure
	 * @generated
	 */
	EClass getProcedure();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Procedure#getMaxResultSets <em>Max Result Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Result Sets</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Procedure#getMaxResultSets()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_MaxResultSets();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Procedure#isOldSavePoint <em>Old Save Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Old Save Point</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Procedure#isOldSavePoint()
	 * @see #getProcedure()
	 * @generated
	 */
	EAttribute getProcedure_OldSavePoint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.routines.Procedure#getResultSet <em>Result Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Result Set</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Procedure#getResultSet()
	 * @see #getProcedure()
	 * @generated
	 */
	EReference getProcedure_ResultSet();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.routines.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Function#isNullCall <em>Null Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Null Call</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function#isNullCall()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_NullCall();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Function#isStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function#isStatic()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_Static();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Function#getTransformGroup <em>Transform Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transform Group</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function#getTransformGroup()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_TransformGroup();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Function#isTypePreserving <em>Type Preserving</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Preserving</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function#isTypePreserving()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_TypePreserving();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Function#isMutator <em>Mutator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mutator</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function#isMutator()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_Mutator();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.routines.Function#getReturnTable <em>Return Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Return Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function#getReturnTable()
	 * @see #getFunction()
	 * @generated
	 */
	EReference getFunction_ReturnTable();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.routines.Function#getReturnScalar <em>Return Scalar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Return Scalar</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function#getReturnScalar()
	 * @see #getFunction()
	 * @generated
	 */
	EReference getFunction_ReturnScalar();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.routines.Function#getReturnCast <em>Return Cast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Return Cast</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function#getReturnCast()
	 * @see #getFunction()
	 * @generated
	 */
	EReference getFunction_ReturnCast();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable <em>Routine Result Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Routine Result Table</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable
	 * @generated
	 */
	EClass getRoutineResultTable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.routines.Method <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Method
	 * @generated
	 */
	EClass getMethod();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Method#isOverriding <em>Overriding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Overriding</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Method#isOverriding()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Overriding();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.routines.Method#isConstructor <em>Constructor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constructor</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Method#isConstructor()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Constructor();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction <em>User Defined Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User Defined Function</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction
	 * @generated
	 */
	EClass getUserDefinedFunction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.routines.BuiltInFunction <em>Built In Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Built In Function</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.BuiltInFunction
	 * @generated
	 */
	EClass getBuiltInFunction();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.routines.DataAccess <em>Data Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Data Access</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.DataAccess
	 * @generated
	 */
	EEnum getDataAccess();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.routines.ParameterMode <em>Parameter Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Parameter Mode</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.routines.ParameterMode
	 * @generated
	 */
	EEnum getParameterMode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SQLRoutinesFactory getSQLRoutinesFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl <em>Routine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getRoutine()
		 * @generated
		 */
		EClass ROUTINE = eINSTANCE.getRoutine();

		/**
		 * The meta object literal for the '<em><b>Specific Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__SPECIFIC_NAME = eINSTANCE.getRoutine_SpecificName();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__LANGUAGE = eINSTANCE.getRoutine_Language();

		/**
		 * The meta object literal for the '<em><b>Parameter Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__PARAMETER_STYLE = eINSTANCE.getRoutine_ParameterStyle();

		/**
		 * The meta object literal for the '<em><b>Deterministic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__DETERMINISTIC = eINSTANCE.getRoutine_Deterministic();

		/**
		 * The meta object literal for the '<em><b>Sql Data Access</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__SQL_DATA_ACCESS = eINSTANCE.getRoutine_SqlDataAccess();

		/**
		 * The meta object literal for the '<em><b>Creation TS</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__CREATION_TS = eINSTANCE.getRoutine_CreationTS();

		/**
		 * The meta object literal for the '<em><b>Last Altered TS</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__LAST_ALTERED_TS = eINSTANCE.getRoutine_LastAlteredTS();

		/**
		 * The meta object literal for the '<em><b>Authorization ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__AUTHORIZATION_ID = eINSTANCE.getRoutine_AuthorizationID();

		/**
		 * The meta object literal for the '<em><b>Security</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__SECURITY = eINSTANCE.getRoutine_Security();

		/**
		 * The meta object literal for the '<em><b>External Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROUTINE__EXTERNAL_NAME = eINSTANCE.getRoutine_ExternalName();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTINE__PARAMETERS = eINSTANCE.getRoutine_Parameters();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTINE__SOURCE = eINSTANCE.getRoutine_Source();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROUTINE__SCHEMA = eINSTANCE.getRoutine_Schema();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.SourceImpl <em>Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SourceImpl
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getSource()
		 * @generated
		 */
		EClass SOURCE = eINSTANCE.getSource();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOURCE__BODY = eINSTANCE.getSource_Body();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.ParameterImpl
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__MODE = eINSTANCE.getParameter_Mode();

		/**
		 * The meta object literal for the '<em><b>Locator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__LOCATOR = eINSTANCE.getParameter_Locator();

		/**
		 * The meta object literal for the '<em><b>Routine</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER__ROUTINE = eINSTANCE.getParameter_Routine();

		/**
		 * The meta object literal for the '<em><b>String Type Option</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER__STRING_TYPE_OPTION = eINSTANCE.getParameter_StringTypeOption();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl <em>Procedure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getProcedure()
		 * @generated
		 */
		EClass PROCEDURE = eINSTANCE.getProcedure();

		/**
		 * The meta object literal for the '<em><b>Max Result Sets</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__MAX_RESULT_SETS = eINSTANCE.getProcedure_MaxResultSets();

		/**
		 * The meta object literal for the '<em><b>Old Save Point</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCEDURE__OLD_SAVE_POINT = eINSTANCE.getProcedure_OldSavePoint();

		/**
		 * The meta object literal for the '<em><b>Result Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCEDURE__RESULT_SET = eINSTANCE.getProcedure_ResultSet();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.FunctionImpl
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '<em><b>Null Call</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__NULL_CALL = eINSTANCE.getFunction_NullCall();

		/**
		 * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__STATIC = eINSTANCE.getFunction_Static();

		/**
		 * The meta object literal for the '<em><b>Transform Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__TRANSFORM_GROUP = eINSTANCE.getFunction_TransformGroup();

		/**
		 * The meta object literal for the '<em><b>Type Preserving</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__TYPE_PRESERVING = eINSTANCE.getFunction_TypePreserving();

		/**
		 * The meta object literal for the '<em><b>Mutator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__MUTATOR = eINSTANCE.getFunction_Mutator();

		/**
		 * The meta object literal for the '<em><b>Return Table</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION__RETURN_TABLE = eINSTANCE.getFunction_ReturnTable();

		/**
		 * The meta object literal for the '<em><b>Return Scalar</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION__RETURN_SCALAR = eINSTANCE.getFunction_ReturnScalar();

		/**
		 * The meta object literal for the '<em><b>Return Cast</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION__RETURN_CAST = eINSTANCE.getFunction_ReturnCast();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.RoutineResultTableImpl <em>Routine Result Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.RoutineResultTableImpl
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getRoutineResultTable()
		 * @generated
		 */
		EClass ROUTINE_RESULT_TABLE = eINSTANCE.getRoutineResultTable();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.MethodImpl <em>Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.MethodImpl
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getMethod()
		 * @generated
		 */
		EClass METHOD = eINSTANCE.getMethod();

		/**
		 * The meta object literal for the '<em><b>Overriding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD__OVERRIDING = eINSTANCE.getMethod_Overriding();

		/**
		 * The meta object literal for the '<em><b>Constructor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD__CONSTRUCTOR = eINSTANCE.getMethod_Constructor();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.UserDefinedFunctionImpl <em>User Defined Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.UserDefinedFunctionImpl
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getUserDefinedFunction()
		 * @generated
		 */
		EClass USER_DEFINED_FUNCTION = eINSTANCE.getUserDefinedFunction();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.impl.BuiltInFunctionImpl <em>Built In Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.BuiltInFunctionImpl
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getBuiltInFunction()
		 * @generated
		 */
		EClass BUILT_IN_FUNCTION = eINSTANCE.getBuiltInFunction();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.DataAccess <em>Data Access</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.DataAccess
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getDataAccess()
		 * @generated
		 */
		EEnum DATA_ACCESS = eINSTANCE.getDataAccess();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.routines.ParameterMode <em>Parameter Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.routines.ParameterMode
		 * @see org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl#getParameterMode()
		 * @generated
		 */
		EEnum PARAMETER_MODE = eINSTANCE.getParameterMode();

	}

} //SQLRoutinesPackage
