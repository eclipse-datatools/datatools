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
package org.eclipse.datatools.modelbase.sql.expressions;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
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
 * @see org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsFactory
 * @model kind="package"
 * @generated
 */
public interface SQLExpressionsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "expressions"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/expressions.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "SQLExpressions"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLExpressionsPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.expressions.QueryExpression <em>Query Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.expressions.QueryExpression
	 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getQueryExpression()
	 * @generated
	 */
	int QUERY_EXPRESSION = 0;

	/**
	 * The number of structural features of the '<em>Query Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.expressions.ValueExpression <em>Value Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.expressions.ValueExpression
	 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getValueExpression()
	 * @generated
	 */
	int VALUE_EXPRESSION = 1;

	/**
	 * The number of structural features of the '<em>Value Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.expressions.SearchCondition <em>Search Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.expressions.SearchCondition
	 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getSearchCondition()
	 * @generated
	 */
	int SEARCH_CONDITION = 2;

	/**
	 * The number of structural features of the '<em>Search Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.expressions.impl.QueryExpressionDefaultImpl <em>Query Expression Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.QueryExpressionDefaultImpl
	 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getQueryExpressionDefault()
	 * @generated
	 */
	int QUERY_EXPRESSION_DEFAULT = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>SQL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT__SQL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Query Expression Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUERY_EXPRESSION_DEFAULT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.expressions.impl.SearchConditionDefaultImpl <em>Search Condition Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SearchConditionDefaultImpl
	 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getSearchConditionDefault()
	 * @generated
	 */
	int SEARCH_CONDITION_DEFAULT = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>SQL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT__SQL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Search Condition Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEARCH_CONDITION_DEFAULT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.expressions.impl.ValueExpressionDefaultImpl <em>Value Expression Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.ValueExpressionDefaultImpl
	 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getValueExpressionDefault()
	 * @generated
	 */
	int VALUE_EXPRESSION_DEFAULT = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Privileges</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
	 * The feature id for the '<em><b>SQL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT__SQL = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Value Expression Default</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_EXPRESSION_DEFAULT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.expressions.QueryExpression <em>Query Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query Expression</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.expressions.QueryExpression
	 * @generated
	 */
	EClass getQueryExpression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.expressions.ValueExpression <em>Value Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Expression</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.expressions.ValueExpression
	 * @generated
	 */
	EClass getValueExpression();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.expressions.SearchCondition <em>Search Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Search Condition</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.expressions.SearchCondition
	 * @generated
	 */
	EClass getSearchCondition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault <em>Query Expression Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query Expression Default</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault
	 * @generated
	 */
	EClass getQueryExpressionDefault();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault#getSQL <em>SQL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SQL</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.expressions.QueryExpressionDefault#getSQL()
	 * @see #getQueryExpressionDefault()
	 * @generated
	 */
	EAttribute getQueryExpressionDefault_SQL();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault <em>Search Condition Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Search Condition Default</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault
	 * @generated
	 */
	EClass getSearchConditionDefault();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault#getSQL <em>SQL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SQL</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.expressions.SearchConditionDefault#getSQL()
	 * @see #getSearchConditionDefault()
	 * @generated
	 */
	EAttribute getSearchConditionDefault_SQL();

	/**
	 * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.expressions.ValueExpressionDefault <em>Value Expression Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Expression Default</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.expressions.ValueExpressionDefault
	 * @generated
	 */
	EClass getValueExpressionDefault();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.expressions.ValueExpressionDefault#getSQL <em>SQL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>SQL</em>'.
	 * @see org.eclipse.datatools.modelbase.sql.expressions.ValueExpressionDefault#getSQL()
	 * @see #getValueExpressionDefault()
	 * @generated
	 */
	EAttribute getValueExpressionDefault_SQL();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SQLExpressionsFactory getSQLExpressionsFactory();

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
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.expressions.QueryExpression <em>Query Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.expressions.QueryExpression
		 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getQueryExpression()
		 * @generated
		 */
		EClass QUERY_EXPRESSION = eINSTANCE.getQueryExpression();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.expressions.ValueExpression <em>Value Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.expressions.ValueExpression
		 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getValueExpression()
		 * @generated
		 */
		EClass VALUE_EXPRESSION = eINSTANCE.getValueExpression();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.expressions.SearchCondition <em>Search Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.expressions.SearchCondition
		 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getSearchCondition()
		 * @generated
		 */
		EClass SEARCH_CONDITION = eINSTANCE.getSearchCondition();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.expressions.impl.QueryExpressionDefaultImpl <em>Query Expression Default</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.QueryExpressionDefaultImpl
		 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getQueryExpressionDefault()
		 * @generated
		 */
		EClass QUERY_EXPRESSION_DEFAULT = eINSTANCE.getQueryExpressionDefault();

		/**
		 * The meta object literal for the '<em><b>SQL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY_EXPRESSION_DEFAULT__SQL = eINSTANCE.getQueryExpressionDefault_SQL();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.expressions.impl.SearchConditionDefaultImpl <em>Search Condition Default</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SearchConditionDefaultImpl
		 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getSearchConditionDefault()
		 * @generated
		 */
		EClass SEARCH_CONDITION_DEFAULT = eINSTANCE.getSearchConditionDefault();

		/**
		 * The meta object literal for the '<em><b>SQL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEARCH_CONDITION_DEFAULT__SQL = eINSTANCE.getSearchConditionDefault_SQL();

		/**
		 * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.expressions.impl.ValueExpressionDefaultImpl <em>Value Expression Default</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.ValueExpressionDefaultImpl
		 * @see org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl#getValueExpressionDefault()
		 * @generated
		 */
		EClass VALUE_EXPRESSION_DEFAULT = eINSTANCE.getValueExpressionDefault();

		/**
		 * The meta object literal for the '<em><b>SQL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_EXPRESSION_DEFAULT__SQL = eINSTANCE.getValueExpressionDefault_SQL();

	}

} //SQLExpressionsPackage
